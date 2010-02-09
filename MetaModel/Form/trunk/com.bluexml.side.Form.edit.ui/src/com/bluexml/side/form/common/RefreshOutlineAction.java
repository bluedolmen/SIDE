package com.bluexml.side.form.common;

import java.io.BufferedInputStream;
import java.io.DataInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;

import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IFolder;
import org.eclipse.core.resources.IProject;
import org.eclipse.core.resources.IWorkspaceRoot;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IPath;
import org.eclipse.core.runtime.NullProgressMonitor;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.plugin.EcorePlugin;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.eclipse.emf.ecore.resource.impl.ResourceSetImpl;
import org.eclipse.emf.ecore.xmi.XMIResource;
import org.eclipse.emf.ecore.xmi.impl.XMIResourceFactoryImpl;
import org.eclipse.jface.action.Action;
import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.jface.viewers.ISelectionChangedListener;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.viewers.SelectionChangedEvent;
import org.eclipse.swt.widgets.Display;
import org.eclipse.ui.PlatformUI;

import com.bluexml.side.form.FormContainer;
import com.bluexml.side.form.editor.views.service.OutlineViewService;
import com.bluexml.side.util.libs.view.OutlineHTMLView;

import fr.obeo.acceleo.chain.ActionSet;
import fr.obeo.acceleo.chain.ChainFactory;
import fr.obeo.acceleo.chain.EmfMetamodel;
import fr.obeo.acceleo.chain.Folder;
import fr.obeo.acceleo.chain.Generate;
import fr.obeo.acceleo.chain.Generator;
import fr.obeo.acceleo.chain.Log;
import fr.obeo.acceleo.chain.Model;
import fr.obeo.acceleo.chain.Repository;
import fr.obeo.acceleo.chain.impl.spec.CChain;
import fr.obeo.acceleo.ecore.factories.EFactory;
import fr.obeo.acceleo.ecore.factories.FactoryException;
import fr.obeo.acceleo.gen.IGenFilter;
import fr.obeo.acceleo.gen.template.eval.LaunchManager;
import fr.obeo.acceleo.tools.resources.Resources;

public class RefreshOutlineAction extends Action implements
		ISelectionChangedListener {

	protected URI fileURI;
	protected EObject selectedObject;

	public void selectionChanged(SelectionChangedEvent event) {
		if (event.getSelection() instanceof IStructuredSelection) {
			setEnabled(updateSelection((IStructuredSelection) event
					.getSelection()));
		} else {
			setEnabled(false);
		}
	}

	public boolean updateSelection(IStructuredSelection selection) {
		selectedObject = null;
		for (Iterator<?> objects = selection.iterator(); objects.hasNext();) {
			Object object = objects.next();
			if (object instanceof FormContainer) {
				selectedObject = (FormContainer) object;
				XMIResource xmiRessource = (XMIResource)((EObject)object).eResource();
				if (xmiRessource != null) {
					fileURI = xmiRessource.getURI();
				}
			} else {
				return false;
			}
		}

		return selectedObject != null;
	}

	/**
	 * public boolean updateSelection(IStructuredSelection selection) {
		fileURI = null;
		for (Iterator<?> objects = selection.iterator(); objects.hasNext();) {
			Object object = objects.next();
			if (object instanceof XMIResource) {
				fileURI = ((XMIResource) object).getURI();
			} else {
				return false;
			}
		}

		return (fileURI != null);
	}
	 */

	@Override
	public void run() {
		super.run();
		try {
			doAction(fileURI);
		} catch (Exception e) {
			MessageDialog.openError(null, e.getMessage(), "Error during outline generation, see log for details.");
			EcorePlugin.INSTANCE.log(e);
		}
	}

	private static final IGenFilter genFilter = new IGenFilter() {
		public boolean filter(java.io.File script, IFile targetFile, EObject object) throws CoreException {
			return true;
		}
	};

	@SuppressWarnings("deprecation")
	private void doAction(URI uri) throws CoreException, FactoryException, IOException {
		if (uri != null) {
			if (selectedObject != null)
				OutlineViewService.setNameOfSelectedForm(((FormContainer)selectedObject).getId());
			
			IFile output_file = doGeneration(uri);
			InputStream is = output_file.getContents();
			BufferedInputStream bis = new BufferedInputStream(is);
			DataInputStream dis = new DataInputStream(bis);

			String content = "";
			while (dis.available() != 0) {
				content += dis.readLine();
			}
			OutlineHTMLView v = (OutlineHTMLView) PlatformUI.getWorkbench().getActiveWorkbenchWindow().getActivePage().showView("com.bluexml.view.OutlineHTMLView");
			v.setContent(content);

			doClean();
		}
	}

	public void doClean() throws CoreException {		
		IWorkspaceRoot myWorkspaceRoot = ResourcesPlugin.getWorkspace().getRoot();
		IProject myproject = myWorkspaceRoot.getProject(".form");
		myproject.delete(true, new NullProgressMonitor());
	}
	
	public IFile doGeneration(URI uri) throws CoreException, FactoryException, IOException {
		String metamodelURI = "http://www.kerblue.org/form/1.0";

		Display.getDefault().syncExec(new Runnable() {
			public void run() {
				PlatformUI.getWorkbench().getActiveWorkbenchWindow().getActivePage().saveAllEditors(true);
			};
		});
		// References to files in the project
		IWorkspaceRoot myWorkspaceRoot = ResourcesPlugin.getWorkspace().getRoot();
		// Temporary project
		IProject tmpProject = myWorkspaceRoot.getProject(".form");

		// create and open if necessary
		if (!tmpProject.exists()) {
			tmpProject.create(null);
		}
		if (!tmpProject.isOpen()) {
			tmpProject.open(null);
		}

		CChain chain = new CChain();

		// Repository
		Repository repository = ChainFactory.eINSTANCE.createRepository();
		EFactory.eSet(chain, "repository", repository);

		// Action Set
		ActionSet actionSet = ChainFactory.eINSTANCE.createActionSet();
		EFactory.eAdd(chain, "actions", actionSet);

		// Model file
		Model model = ChainFactory.eINSTANCE.createModel();
		EFactory.eAdd(repository, "files", model);
 		IFile modelPath = (IFile) myWorkspaceRoot.findMember(uri.path().substring(10));
 		if (modelPath == null)
 			modelPath = (IFile) myWorkspaceRoot.findMember(uri.path());
		EFactory.eSet(model, "path", modelPath.getFullPath().toString());

		// Target folder
		Folder folder = ChainFactory.eINSTANCE.createFolder();

		//IPath outputPath = new Path(modelPath.getFullPath().toString()+".out");
		IPath outputPath = tmpProject.getFullPath().append("out");
		EFactory.eAdd(repository, "files", folder);
		EFactory.eSet(folder, "path", outputPath.toString());

		// Log
		Log log = ChainFactory.eINSTANCE.createLog();
		EFactory.eAdd(repository, "files", log);
		EFactory.eSet(log, "path", modelPath.getFullPath().removeLastSegments(1).append(modelPath.getFullPath().removeFileExtension().lastSegment() + ".log.txt").toString());

		// Metamodel file
		EmfMetamodel pim = ChainFactory.eINSTANCE.createEmfMetamodel();
		EFactory.eAdd(repository, "files", pim);
		EFactory.eSet(pim, "path", metamodelURI);

		List<String> templates = new ArrayList<String>();

		templates.add("/com.bluexml.side.Form.edit.ui/com/bluexml/side/form/editor/views/default.mt");

		for (String templateFile : templates) {
			// Generator
			Generator generator = ChainFactory.eINSTANCE.createGenerator();
			EFactory.eAdd(repository, "files", generator);
			EFactory.eSet(generator, "path", templateFile);

			// Action
			Generate gAction = ChainFactory.eINSTANCE.createGenerate();
			EFactory.eAdd(actionSet, "actions", gAction);
			EFactory.eSet(gAction, "folder", folder);
			EFactory.eSet(gAction, "log", log);
			EFactory.eSet(gAction, "metamodel", pim);
			EFactory.eSet(gAction, "model", model);
			EFactory.eSet(gAction, "generator", generator);
		}

		// Register the default resource factory -- only needed for
		// stand-alone!
		IFile fchain = tmpProject.getFile("form.chain");
		URI chainURI = Resources.createPlatformResourceURI(fchain.getFullPath().toString());
		ResourceSet resourceSet = new ResourceSetImpl();
		resourceSet.getResourceFactoryRegistry().getExtensionToFactoryMap().put(Resource.Factory.Registry.DEFAULT_EXTENSION, new XMIResourceFactoryImpl());
		Resource chainResource = resourceSet.createResource(chainURI);
		chainResource.getContents().add(chain);
		chain.setFile(fchain);
		chainResource.save(Collections.EMPTY_MAP);

		chain.launch(genFilter, new NullProgressMonitor(), LaunchManager.create("run", true));

		//Update Outline View
		IFolder output_folder = (IFolder) myWorkspaceRoot.findMember(outputPath);
		IFile output_file = (IFile) output_folder.members()[0];
		return output_file;
	}
	
	@Override
	public String getText() {
		return "Refresh Outline";
	}
}
