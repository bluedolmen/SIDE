package com.bluexml.side.Requirements.modeler.actions;

import java.io.IOException;
import java.util.Collections;
import java.util.Date;
import java.util.Map;
import java.util.StringTokenizer;

import org.eclipse.core.resources.IContainer;
import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IFolder;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.Path;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.eclipse.emf.ecore.resource.impl.ResourceSetImpl;
import org.eclipse.emf.ecore.xmi.impl.XMIResourceFactoryImpl;
import org.eclipse.jface.action.IAction;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.ui.IObjectActionDelegate;
import org.eclipse.ui.IWorkbenchPart;

import com.bluexml.side.requirements.RequirementsDefinition;

public class BackupSpecification implements IObjectActionDelegate {

	private ISelection _selection;

	public void setActivePart(IAction action, IWorkbenchPart targetPart) {
	}

	public void run(IAction action) {
		if (_selection instanceof IStructuredSelection) {
			IStructuredSelection iss = (IStructuredSelection) _selection;
			if (iss.getFirstElement() instanceof IFile) {
				IFile requirements_model = (IFile) iss.getFirstElement();
				try {
					IContainer parent = requirements_model.getParent();
					IFolder backupFolder = parent.getFolder(new Path(".backup"));
					if (!backupFolder.exists())
						backupFolder.create(true, true, null);
					
					RequirementsDefinition reqDefs = getDefinition(requirements_model);
					if (reqDefs != null) {
						String version = reqDefs.getVersion();
						if (version == null || version.length() == 0)
							version = "1.0";
						else {
							//increment version
							StringTokenizer st = new StringTokenizer(version,".");
							if (st.countTokens() == 2) {
								String major = st.nextToken();
								String minor = st.nextToken();
								version = major.concat("." + String.valueOf(Integer.valueOf(minor)+1));
							} else 
								version = "1.0";
							
						}
						
						reqDefs.setVersion(version);
						reqDefs.setDate(new Date());
						//and save the model
						saveDefinition(reqDefs,requirements_model);
						
						//Backup of the model
						String nameFile = requirements_model.getName().substring(0, requirements_model.getName().lastIndexOf(".")).concat("."+version).concat(".requirements");
						requirements_model.copy(backupFolder.getFullPath().append(nameFile), true, null);
					}
				} catch (CoreException e) {
					e.printStackTrace();
				}
			}
		}
	}

	private void saveDefinition(RequirementsDefinition reqDefs, IFile rwm_model) {
		URI fileURI = URI.createURI(rwm_model.getLocationURI().toString());
		Resource resource = new XMIResourceFactoryImpl().createResource(fileURI);
		resource.getContents().add(reqDefs);
		try {
			resource.save(Collections.EMPTY_MAP);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	private RequirementsDefinition getDefinition(IFile rwm_model) {
		RequirementsDefinition reqDefs = null;
		try {
			// Register the XMI resource factory for the .rwm extension
			Resource.Factory.Registry reg = Resource.Factory.Registry.INSTANCE;
			Map<String, Object> m = reg.getExtensionToFactoryMap();
			m.put("requirements", new XMIResourceFactoryImpl());
			ResourceSet resSet=new ResourceSetImpl();
			Resource res = resSet.getResource(URI.createURI(rwm_model.getLocationURI().toString()),true);

			reqDefs = (RequirementsDefinition) res.getContents().get(0);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return reqDefs;
	}

	public void selectionChanged(IAction action, ISelection selection) {
		_selection = selection;
	}

}
