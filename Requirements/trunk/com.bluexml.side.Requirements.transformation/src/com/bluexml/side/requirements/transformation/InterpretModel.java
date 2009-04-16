package com.bluexml.side.requirements.transformation;

import java.io.File;
import java.util.Set;

import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IFolder;
import org.eclipse.core.resources.IProject;
import org.eclipse.core.resources.IResource;
import org.eclipse.core.resources.IWorkspaceRoot;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IConfigurationElement;
import org.eclipse.core.runtime.IPath;
import org.eclipse.core.runtime.NullProgressMonitor;
import org.eclipse.core.runtime.Path;
import org.eclipse.core.runtime.Platform;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.eclipse.emf.ecore.resource.impl.ResourceSetImpl;
import org.eclipse.jface.action.IAction;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.window.Window;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.ui.IObjectActionDelegate;
import org.eclipse.ui.IWorkbenchPart;

import com.bluexml.side.requirements.transformation.actions.dialog.TransformModelDialog;

public class InterpretModel implements IObjectActionDelegate {

	private ISelection selection;

	public void setActivePart(IAction action, IWorkbenchPart targetPart) {
	}

	public void run(IAction action) {
		Shell shell = new Shell();
		TransformModelDialog dialog = new TransformModelDialog(shell);
		if (dialog.open() == Window.OK) {

			if (selection instanceof IStructuredSelection) {
				IStructuredSelection iss = (IStructuredSelection) selection;
				if (iss.getFirstElement() instanceof IFile) {
					IFile rwm_model = (IFile) iss.getFirstElement();

					try {
						IWorkspaceRoot myWorkspaceRoot = ResourcesPlugin.getWorkspace().getRoot();
						// Temporary project
						IProject tmpProject = myWorkspaceRoot.getProject(".requirements");

						// create and open if necessary
						if (!tmpProject.exists()) {
							tmpProject.create(null);
						}
						if (!tmpProject.isOpen()) {
							tmpProject.open(null);
						}
						
						tmpProject.setDefaultCharset("UTF-8", null);

						IFolder outputFolder = tmpProject.getFolder("output");
						if (!outputFolder.exists())
							outputFolder.create(true, true, null);
						IFolder modelFolder = tmpProject.getFolder("models");
						if (!modelFolder.exists())
							modelFolder.create(true, true, null);

						// Create sub-directory
						IPath path = rwm_model.getFullPath().removeLastSegments(1).removeFirstSegments(1);
						IFolder newFolder = rwm_model.getProject().getFolder(path.toString() + File.separator + "Generated files for " + rwm_model.getName());
						if (newFolder.exists())
							newFolder.delete(true, null);

						for (IConfigurationElement config : dialog.getSelectedConfigurations())
							try {
								execute(config, rwm_model, outputFolder, modelFolder, newFolder);
							} catch (Exception e) {
								e.printStackTrace();
							}

						// Copy all files
						outputFolder.refreshLocal(IResource.DEPTH_INFINITE, null);
						outputFolder.copy(newFolder.getFullPath(), true, new NullProgressMonitor());
						newFolder.refreshLocal(IResource.DEPTH_INFINITE, null);

						// Delete the temporary project

						//tmpProject.delete(true, new NullProgressMonitor());

					} catch (CoreException e) {
						e.printStackTrace();
					}
				}
			}

		}
	}

	private void execute(IConfigurationElement config, IFile model, IFolder outputFolder, IFolder modelFolder, IFolder exportFolder) throws Exception {
		System.out.println("Starting model interpretation : " + config.getAttribute("label"));

		IConfigurationElement elt = config.getChildren("transformation")[0];

		// Execute transformation

		TransformModel t = new TransformModel();
		t.setASMFile(elt.getAttribute("asm"));
		t.setInputModelName(elt.getAttribute("input_model_name"));
		t.setInputMetamodelName(elt.getAttribute("input_metamodel_name"));
		t.setOutputModelName(elt.getAttribute("output_model_name"));
		t.setOutputMetamodelName(elt.getAttribute("output_metamodel_name"));
		t.setTargetMetamodel(elt.getAttribute("target_MM"));
		t.setContributor(config.getContributor().getName());
		String outputModelName;
		if (elt.getAttribute("new_name") == null || elt.getAttribute("new_name").length() == 0)
			outputModelName = model.getName() + ".tmp";
		else
			outputModelName = elt.getAttribute("new_name");
		t.execute(model, modelFolder.getLocation().toFile() + File.separator + outputModelName);
		Set<String> outModels = t.getOutputModels();

		if (config.getChildren("templateGeneration").length != 0) {

			// Execute generation
			ResourceSet resourceSet = new ResourceSetImpl();
			org.eclipse.emf.common.util.URI emfUri = org.eclipse.emf.common.util.URI.createURI(Platform.getBundle(config.getContributor().getName()).getEntry(elt.getAttribute("target_MM")).toString());
			Resource out_metamodel_resource = resourceSet.getResource(emfUri, true);

			for (String outModel : outModels) {
				GenerateModel g = new GenerateModel();
				for (IConfigurationElement templateElt : config.getChildren("templateGeneration")) {
					g.addTemplate("/" + config.getContributor().getName() + "/" + templateElt.getAttribute("mtFile"));
				}
				g.execute(outModel, outputFolder.getFullPath(), out_metamodel_resource);
			}
		} else {
			//Copy models to the output folder
			for (String outModel : outModels) {
				IFile tmpFile = outputFolder.getFile(outputModelName);
				if (tmpFile.exists())
					tmpFile.delete(true, null);
				ResourcesPlugin.getWorkspace().getRoot().getFile(new Path(outModel)).copy(tmpFile.getFullPath(), true, null);
			}
		}

		// Execute ANT scripts

		ExecuteANTScript a = new ExecuteANTScript();
		a.setContributor(config.getContributor().getName());
		for (IConfigurationElement antSriptElt : config.getChildren("antScript")) {
			a.addScript(antSriptElt.getAttribute("antScript"));
		}
		a.execute(outputFolder);
	}

	public void selectionChanged(IAction action, ISelection _selection) {
		selection = _selection;
	}

}
