/*******************************************************************************
 * Copyright (c) 2006 IBM Corporation and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *     IBM Corporation - initial API and implementation
 *******************************************************************************/
package com.bluexml.side.Requirements.modeler.wizards;

import java.io.IOException;
import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import org.eclipse.core.resources.IFile;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.eclipse.emf.ecore.resource.impl.ResourceSetImpl;
import org.eclipse.emf.ecore.xmi.impl.EcoreResourceFactoryImpl;
import org.eclipse.emf.ecore.xmi.impl.XMIResourceFactoryImpl;
import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.wizard.Wizard;
import org.eclipse.ui.IImportWizard;
import org.eclipse.ui.ISelectionService;
import org.eclipse.ui.IWorkbench;
import org.eclipse.ui.IWorkbenchWindow;
import org.eclipse.ui.PlatformUI;

import com.bluexml.side.Util.ecore.EResourceUtils;
import com.bluexml.side.requirements.AnnotableElement;
import com.bluexml.side.requirements.Annotation;
import com.bluexml.side.requirements.AnnotationStatus;
import com.bluexml.side.requirements.BasicElement;
import com.bluexml.side.requirements.RequirementsDefinition;
import com.bluexml.side.requirements.RequirementsFactory;
import com.bluexml.side.requirements.util.RequirementsResource;
import com.bluexml.side.requirements.util.RequirementsResourceFactory;


public class ImportAnnotationWizard extends Wizard implements IImportWizard {
	
	ImportAnnotationWizardPage mainPage;
	
	private int annotationsReaded;
	private int annotationsImported;
	private int annotationsExisting;

	public ImportAnnotationWizard() {
		super();
	}

	/* (non-Javadoc)
	 * @see org.eclipse.jface.wizard.Wizard#performFinish()
	 */
	public boolean performFinish() {
		annotationsImported = 0;
		annotationsReaded = 0;
		annotationsExisting = 0;
		
		ResourceSet rs = new ResourceSetImpl();
		Resource.Factory.Registry f = rs.getResourceFactoryRegistry();
		Map<String,Object> extm = f.getExtensionToFactoryMap();
		extm.put("xmi", new XMIResourceFactoryImpl());
		extm.put("ecore", new EcoreResourceFactoryImpl());
		extm.put("requirements", new RequirementsResourceFactory());
		
		IWorkbenchWindow iww = PlatformUI.getWorkbench().getActiveWorkbenchWindow();
		ISelectionService iss = iww.getSelectionService();
		ISelection sel = iss.getSelection();
		if (sel instanceof IStructuredSelection) {
			IStructuredSelection ssel = (IStructuredSelection) sel;
			if (ssel.getFirstElement() instanceof IFile) {
				IFile model = (IFile) ssel.getFirstElement();
				Map<String, Annotation> annotations = new HashMap<String, Annotation>();
				Map<String, EObject> objects = new HashMap<String, EObject>();
				RequirementsDefinition def = null;
				RequirementsResource xmi = null;
				
				Map<?,?> m = new HashMap<Object, Object>();
				try {
					URI uri = URI.createFileURI(model.getRawLocation().toString());
					Resource r = EResourceUtils.createResource(uri,rs);//rs.getResource(uri, true);
					r.load(m);
					xmi = (RequirementsResource) r;
					for (Iterator<EObject> iterator = xmi.getAllContents(); iterator.hasNext();) {
						EObject obj = (EObject) iterator.next();
						if (def == null)
							if (obj instanceof RequirementsDefinition)
								def = (RequirementsDefinition) obj;
						if (obj instanceof Annotation) {
							Annotation a = (Annotation) obj;
							annotations.put(a.getId(), a);
						}
						if (obj instanceof BasicElement) {
							BasicElement elt = (BasicElement) obj;
							objects.put(elt.getId(), elt);
						}
					}
					
				} catch (Exception e1) {
					MessageDialog.openError(getShell(), "Problem during reading of input model !", e1.getMessage());
					return false;
				}
				
				if (model.getName().endsWith(".requirements")) {
					
					//Connexion to database
					String host = mainPage.host.getText();
					String username = mainPage.user.getText();
					String password = mainPage.password.getText();
					String database = mainPage.database.getText();
					
					
					try {
						String pilote = "com.mysql.jdbc.Driver";
						Class.forName(pilote);

						Connection connexion = DriverManager.getConnection("jdbc:mysql://"+host+"/"+database,username,password);

						Statement instruction = connexion.createStatement();

						ResultSet resultat = instruction.executeQuery("SELECT * FROM annotation;");
						while(resultat.next()){
							annotationsReaded++;
							
							String id = Integer.toString(resultat.getInt("id"));
							String elementId = resultat.getString("elementId");
							String author = resultat.getString("author");
							String annotation = resultat.getString("annotation");
							String comment = resultat.getString("comment");
							Date date = null;
							try {
								date = resultat.getDate("date");
							} catch (Exception e) {
								//Nothing to do
							}
							
							if (!annotations.containsKey(id)) {
								Annotation a = RequirementsFactory.eINSTANCE.createAnnotation();
								a.setAnnotation(annotation);
								a.setAuthor(author);
								a.setComment(comment);
								a.setDate(date);
								a.setId(id);
								a.setStatus(AnnotationStatus.NEW);
								
								if (objects.containsKey(elementId)) {
									EObject obj = objects.get(elementId);
									if (obj instanceof AnnotableElement) {
										annotationsImported++;
										AnnotableElement a_elt = (AnnotableElement) obj;
										a_elt.getAnnotation().add(a);
									}
								}
							} else {
								annotationsExisting++;
							}
						}
						
						MessageDialog.openInformation(getShell(), "Success", annotationsReaded+" annotation(s) have been read and "+annotationsImported+" annotation(s) have been imported. "+annotationsExisting+" annotation(s) are already present.");
					}
					catch (Exception e){
						MessageDialog.openError(getShell(), "Problem during connexion !", e.getMessage());
						return false;
					}
				}
				
				//Save the file
				if (def != null && xmi != null) {
					xmi.getContents().clear();
					xmi.getContents().add(def);
					try {
						xmi.save(m);
					} catch (IOException e) {
						MessageDialog.openError(getShell(), "Problem during saving !", e.getMessage());
						return false;
					}	
				}
					
			} else {
				MessageDialog.openError(getShell(), "Incompatible selected file", "You don't have selected a file or it is not a requirements model.");
				return false;
			}
		}
		return true;
	}
	 
	/* (non-Javadoc)
	 * @see org.eclipse.ui.IWorkbenchWizard#init(org.eclipse.ui.IWorkbench, org.eclipse.jface.viewers.IStructuredSelection)
	 */
	public void init(IWorkbench workbench, IStructuredSelection selection) {
		setWindowTitle("Annotations Import Wizard"); //NON-NLS-1
		setNeedsProgressMonitor(true);
		mainPage = new ImportAnnotationWizardPage("Import annotations"); //NON-NLS-1
	}
	
	/* (non-Javadoc)
     * @see org.eclipse.jface.wizard.IWizard#addPages()
     */
    public void addPages() {
        super.addPages(); 
        addPage(mainPage);        
    }
}
