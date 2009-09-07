package com.bluexml.side.Workflow.modeler.actions.popup;

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

import com.bluexml.side.Workflow.modeler.WorkflowImageRegistry;
import com.bluexml.side.Workflow.modeler.WorkflowPlugin;
import com.bluexml.side.form.FormContainer;
import com.bluexml.side.form.FormWorkflow;
import com.bluexml.side.form.WorkflowFormCollection;
import com.bluexml.side.form.presentation.FormEditor;
import com.bluexml.side.workflow.EndState;
import com.bluexml.side.workflow.Process;
import com.bluexml.side.workflow.State;
import com.bluexml.side.workflow.UserTask;

public class ShowFormAction extends WorkbenchPartAction {

	static public String ID = "com.bluexml.side.Workflow.modeler.actions.showForm"; //$NON-NLS-1$
	private UserTask selectedObject;

	public ShowFormAction(IWorkbenchPart part) {
		super(part);
	}

	@Override
	protected boolean calculateEnabled() {
		return (selectedObject != null);
	}

	@Override
	public void run() {
		// Search the good form model
		// Get project
		IFile iFile = XMIResource2IFile((XMIResource) selectedObject
				.eResource());
		IProject project = iFile.getProject();

		List<WorkflowFormCollection> wfl = searchFormModel(project);
		List<FormWorkflow> forms = searchForm(wfl, selectedObject);

		if (forms.size() == 0)
			MessageDialog.openInformation(null, WorkflowPlugin.Messages
					.getString("ShowFormAction.1"), //$NON-NLS-1$
					WorkflowPlugin.Messages.getString("ShowFormAction.2", project.getName()) //$NON-NLS-1$
					); //$NON-NLS-1$
		else {
			recomputeAndSelect(forms, selectedObject);
		}
	}

	private void selectForm(WorkflowFormCollection wfc, FormEditor editor,
			UserTask task) {

		for (FormContainer fc : wfc.getForms()) {
			if (fc instanceof FormWorkflow) {
				FormWorkflow fw = (FormWorkflow) fc;

				State s = (State) fw.getRef();
				
				
				if (s.getName().equals(task.getName())) {
					editor.setSelectionToViewer(Collections.singleton(fw));
				}
			}
		}
	}

	private void recomputeAndSelect(List<FormWorkflow> forms, UserTask task) {

		IWorkbenchPage page = PlatformUI.getWorkbench()
				.getActiveWorkbenchWindow().getActivePage();
		IEditorDescriptor desc = PlatformUI.getWorkbench().getEditorRegistry()
				.getDefaultEditor(
						XMIResource2IFile(
								(XMIResource) forms.get(0).eResource())
								.getName());

		for (FormWorkflow formWorkflow : forms) {
			IEditorPart editorPart;
			try {
				editorPart = page.openEditor(new FileEditorInput(
						XMIResource2IFile((XMIResource) formWorkflow
								.eResource())), desc.getId());
				if (editorPart instanceof FormEditor) {
					FormEditor editor = (FormEditor) editorPart;
					TreeViewer treeViewer = (TreeViewer) editor.getViewer();
					TreeItem item = treeViewer.getTree().getItem(0);
					item.setExpanded(true);
					treeViewer.refresh();
					WorkflowFormCollection wfc = (WorkflowFormCollection) item
							.getItem(0).getData();

					selectForm(wfc, editor, task);
				}
			} catch (PartInitException e) {
				e.printStackTrace();
			}
		}
	}

	private List<FormWorkflow> searchForm(List<WorkflowFormCollection> wfl,
			UserTask task) {
		List<FormWorkflow> result = new ArrayList<FormWorkflow>();
		Process p = (Process) task.eContainer();
		for (WorkflowFormCollection wfc : wfl) {

			Process p2 = wfc.getLinked_process();
			if (p2.getName().equals(p.getName())) {
				for (FormContainer fc : wfc.getForms()) {
					if (fc instanceof FormWorkflow) {
						FormWorkflow fw = (FormWorkflow) fc;

						State s = (State) fw.getRef();
						if (s.getName().equals(task.getName()))
							result.add(fw);
					}
				}
			}
		}
		return result;
	}

	private List<WorkflowFormCollection> searchFormModel(IProject project) {
		List<WorkflowFormCollection> result = new ArrayList<WorkflowFormCollection>();
		for (EObject obj : searchFile(project, "form")) { //$NON-NLS-1$
			if (obj instanceof WorkflowFormCollection)
				result.add((WorkflowFormCollection) obj);
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
		setText(WorkflowPlugin.Messages.getString("ShowFormAction.5")); //$NON-NLS-1$
		setImageDescriptor(WorkflowImageRegistry
				.getImageDescriptor("FORMMODEL")); //$NON-NLS-1$
	}

	public void setSelection(ISelection s) {
		if (!(s instanceof IStructuredSelection)) {
			return;
		}
		IStructuredSelection selection = (IStructuredSelection) s;

		selectedObject = null;
		// Recompute the command according to the current selection
		if (selection.getFirstElement() instanceof EMFGraphNodeEditPart) {
			EMFGraphNodeEditPart editPart = (EMFGraphNodeEditPart) selection
					.getFirstElement();
			if (editPart.getEObject() instanceof UserTask
					&& !(editPart.getEObject() instanceof EndState)) {
				selectedObject = (UserTask) editPart.getEObject();
			}
		}
	}

	private EObject openModel(IFile model) throws IOException {
		ResourceSetImpl set = new ResourceSetImpl();
		Resource inputResource = set.createResource(URI.createFileURI(model
				.getRawLocation().toFile().getCanonicalPath()));
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
			return ResourcesPlugin.getWorkspace().getRoot().getFile(
					new Path(platformResourcePath.toString()));
		} else if ("file".equals(scheme)) { //$NON-NLS-1$
			StringBuffer platformResourcePath = new StringBuffer();
			for (int j = ResourcesPlugin.getWorkspace().getRoot().getLocation()
					.segments().length, size = uri.segmentCount(); j < size; ++j) {
				platformResourcePath.append('/');
				platformResourcePath.append(uri.segment(j));
			}
			return ResourcesPlugin.getWorkspace().getRoot().getFile(
					new Path(platformResourcePath.toString()));
		}
		return null;
	}

}
