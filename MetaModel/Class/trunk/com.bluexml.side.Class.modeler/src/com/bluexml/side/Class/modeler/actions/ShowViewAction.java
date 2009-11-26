package com.bluexml.side.Class.modeler.actions;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.eclipse.core.resources.IContainer;
import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IProject;
import org.eclipse.core.resources.IResource;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.core.runtime.Path;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.resource.impl.ResourceSetImpl;
import org.eclipse.emf.ecore.xmi.XMIResource;
import org.eclipse.gef.ui.actions.WorkbenchPartAction;
import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.viewers.TreeViewer;
import org.eclipse.swt.widgets.TreeItem;
import org.eclipse.ui.IEditorDescriptor;
import org.eclipse.ui.IEditorPart;
import org.eclipse.ui.IWorkbenchPage;
import org.eclipse.ui.IWorkbenchPart;
import org.eclipse.ui.PartInitException;
import org.eclipse.ui.PlatformUI;
import org.eclipse.ui.part.FileEditorInput;
import org.topcased.modeler.edit.EMFGraphNodeEditPart;

import com.bluexml.side.Class.modeler.ClazzImageRegistry;
import com.bluexml.side.Class.modeler.ClazzPlugin;
import com.bluexml.side.Class.modeler.editor.ClazzEditor;
import com.bluexml.side.clazz.Clazz;
import com.bluexml.side.common.Container;
import com.bluexml.side.view.AbstractView;
import com.bluexml.side.view.AbstractViewOf;
import com.bluexml.side.view.ViewCollection;
import com.bluexml.side.view.presentation.ViewEditor;

public class ShowViewAction extends WorkbenchPartAction {

	static public String ID = "com.bluexml.side.Class.modeler.actions.showView"; //$NON-NLS-1$
	private Clazz selectedObject;

	public ShowViewAction(IWorkbenchPart part) {
		super(part);
	}

	@Override
	protected boolean calculateEnabled() {
		ClazzEditor editor = (ClazzEditor) getWorkbenchPart();
		setSelection(editor.getSelection());
		return (selectedObject != null);
	}

	@Override
	public void run() {
		// Search the good form model
		// Get project
		IFile iFile = XMIResource2IFile((XMIResource) selectedObject.eResource());
		IProject project = iFile.getProject();

		List<ViewCollection> wfl = searchFormModel(project);
		List<AbstractViewOf> forms = searchForm(wfl, selectedObject);

		if (forms.size() == 0)
			MessageDialog.openInformation(null, ClazzPlugin.Messages.getString("ShowViewAction.1"), //$NON-NLS-1$
					ClazzPlugin.Messages.getString("ShowViewAction.2", project.getName()) //$NON-NLS-1$
					); //$NON-NLS-1$
		else {
			recomputeAndSelect(forms, selectedObject);
		}
	}

	private void selectView(ViewCollection wfc, ViewEditor editor, AbstractViewOf view) {

		for (AbstractView fc : wfc.getViews()) {
			if (fc instanceof AbstractViewOf) {
				AbstractViewOf fw = (AbstractViewOf) fc;
				if ((view.getName() == null && fw.getName() == null) || (view.getName().equals(fw.getName()) && view.getViewOf().eClass().equals(fw.getViewOf().eClass()))) {
					Container co = fw.getViewOf();
					if (co instanceof Clazz && view.getViewOf() instanceof Clazz) {
						Clazz c = (Clazz) co;

						if (c.getFullName().equals(((Clazz) view.getViewOf()).getFullName())) {
							editor.setSelectionToViewer(Collections.singleton(fw));
						}
					}
				}
			}
		}
	}

	private void recomputeAndSelect(List<AbstractViewOf> views, Clazz clazz) {

		IWorkbenchPage page = PlatformUI.getWorkbench().getActiveWorkbenchWindow().getActivePage();
		IEditorDescriptor desc = PlatformUI.getWorkbench().getEditorRegistry().getDefaultEditor(XMIResource2IFile((XMIResource) views.get(0).eResource()).getName());

		for (AbstractViewOf view : views) {
			IEditorPart editorPart;
			try {
				editorPart = page.openEditor(new FileEditorInput(XMIResource2IFile((XMIResource) view.eResource())), desc.getId());
				if (editorPart instanceof ViewEditor) {
					ViewEditor editor = (ViewEditor) editorPart;
					TreeViewer treeViewer = (TreeViewer) editor.getViewer();
					TreeItem item = treeViewer.getTree().getItem(0);
					item.setExpanded(true);
					treeViewer.refresh();
					ViewCollection wfc = (ViewCollection) item.getItem(0).getData();

					selectView(wfc, editor, view);
				}
			} catch (PartInitException e) {
				e.printStackTrace();
			}
		}
	}

	private List<AbstractViewOf> searchForm(List<ViewCollection> wfl, Clazz clazz) {
		List<AbstractViewOf> result = new ArrayList<AbstractViewOf>();
		for (ViewCollection wfc : wfl) {

			for (AbstractView fc : wfc.getViews()) {
				if (fc instanceof AbstractViewOf) {
					AbstractViewOf fw = (AbstractViewOf) fc;
					Container co = fw.getViewOf();
					if (co instanceof Clazz) {
						Clazz c = (Clazz) co;
						if (c.getFullName().equals(clazz.getFullName()))
							result.add(fw);
					}
				}
			}
		}
		return result;
	}

	private List<ViewCollection> searchFormModel(IProject project) {
		List<ViewCollection> result = new ArrayList<ViewCollection>();
		for (EObject obj : searchFile(project, "view")) { //$NON-NLS-1$
			if (obj instanceof ViewCollection)
				result.add((ViewCollection) obj);
		}
		return result;
	}

	private List<EObject> searchFile(IContainer container, String extension) {
		List<EObject> result = new ArrayList<EObject>();

		try {
			for (IResource r : container.members()) {
				if (r instanceof IContainer)
					result.addAll(searchFile((IContainer) r, extension));
				else if (r instanceof IFile) {
					IFile file = (IFile) r;
					if (r.getFileExtension().equals(extension))
						result.add(openModel(file));
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		return result;
	}

	protected void init() {
		setId(ID);
		setText(ClazzPlugin.Messages.getString("ShowViewAction.5")); //$NON-NLS-1$
		setImageDescriptor(ClazzImageRegistry.getImageDescriptor("VIEWMODEL")); //$NON-NLS-1$
	}

	public void setSelection(ISelection s) {
		if (!(s instanceof IStructuredSelection)) {
			return;
		}
		IStructuredSelection selection = (IStructuredSelection) s;

		selectedObject = null;
		// Recompute the command according to the current selection
		if (selection.getFirstElement() instanceof EMFGraphNodeEditPart) {
			EMFGraphNodeEditPart editPart = (EMFGraphNodeEditPart) selection.getFirstElement();
			if (editPart.getEObject() instanceof Clazz) {
				selectedObject = (Clazz) editPart.getEObject();
			}
		}
	}

	private EObject openModel(IFile model) throws IOException {
		ResourceSetImpl set = new ResourceSetImpl();
		Resource inputResource = set.createResource(URI.createFileURI(model.getRawLocation().toFile().getCanonicalPath()));
		inputResource.load(null);
		EList<?> l = inputResource.getContents();
		return (EObject) l.get(0);
	}

	private IFile XMIResource2IFile(XMIResource resource) {
		URI uri = resource.getURI();
		uri = resource.getResourceSet().getURIConverter().normalize(uri);
		String scheme = uri.scheme();
		if ("platform".equals(scheme) && uri.segmentCount() > 1 //$NON-NLS-1$
				&& "resource".equals(uri.segment(0))) { //$NON-NLS-1$
			StringBuffer platformResourcePath = new StringBuffer();
			for (int j = 1, size = uri.segmentCount(); j < size; ++j) {
				platformResourcePath.append('/');
				platformResourcePath.append(uri.segment(j));
			}
			return ResourcesPlugin.getWorkspace().getRoot().getFile(new Path(platformResourcePath.toString()));
		} else if ("file".equals(scheme)) { //$NON-NLS-1$
			StringBuffer platformResourcePath = new StringBuffer();
			for (int j = ResourcesPlugin.getWorkspace().getRoot().getLocation().segments().length, size = uri.segmentCount(); j < size; ++j) {
				platformResourcePath.append('/');
				platformResourcePath.append(uri.segment(j));
			}
			return ResourcesPlugin.getWorkspace().getRoot().getFile(new Path(platformResourcePath.toString()));
		}
		return null;
	}

}
