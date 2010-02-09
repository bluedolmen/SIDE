package com.bluexml.side.form.workflow;

import java.util.Collections;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.core.runtime.Path;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.xmi.XMIResource;
import org.eclipse.jface.action.Action;
import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.jface.viewers.ISelectionChangedListener;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.viewers.SelectionChangedEvent;
import org.eclipse.jface.viewers.TreeViewer;
import org.eclipse.swt.widgets.TreeItem;
import org.eclipse.ui.IEditorDescriptor;
import org.eclipse.ui.IEditorPart;
import org.eclipse.ui.IWorkbenchPage;
import org.eclipse.ui.PartInitException;
import org.eclipse.ui.PlatformUI;
import org.eclipse.ui.part.FileEditorInput;
import org.topcased.modeler.di.model.Diagram;
import org.topcased.modeler.di.model.DiagramElement;
import org.topcased.modeler.di.model.GraphElement;
import org.topcased.modeler.editor.Modeler;
import org.topcased.modeler.utils.Utils;

import com.bluexml.side.common.ModelElement;
import com.bluexml.side.form.FormUI;
import com.bluexml.side.form.FormWorkflow;
import com.bluexml.side.workflow.Process;
import com.bluexml.side.workflow.State;
import com.bluexml.side.workflow.presentation.WorkflowEditor;

public class ShowLinkedTaskAction extends Action implements
		ISelectionChangedListener {

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
			if (object instanceof FormWorkflow) {
				selectedObject = (EObject) object;
			} else {
				return false;
			}
		}

		return selectedObject != null;
	}

	@Override
	public void run() {
		super.run();
		doAction((FormWorkflow) selectedObject);
	}

	private void doAction(FormWorkflow formWorkflow) {
		ModelElement me = formWorkflow.getRef();
		if (me != null && me instanceof State) {
			State s = (State) me;
			XMIResource r = (XMIResource) s.eResource();
			IFile file = XMIResource2IFile(r);
			IFile diagramFile = file.getParent().getFile(
					new Path(file.getName() + "di"));

			if (diagramFile.exists()) {
				IWorkbenchPage page = PlatformUI.getWorkbench()
						.getActiveWorkbenchWindow().getActivePage();
				IEditorDescriptor desc = PlatformUI.getWorkbench()
						.getEditorRegistry().getDefaultEditor(diagramFile.getName());
				try {
					IEditorPart editor = page.openEditor(new FileEditorInput(
							diagramFile), desc.getId());
					
					if (editor instanceof Modeler) {
						Modeler modeler = (Modeler) editor;
						selectDiagramElement(modeler.getActiveDiagram(), modeler, s);
					}
				} catch (PartInitException e) {
					e.printStackTrace();
				}

			} else {
				IWorkbenchPage page = PlatformUI.getWorkbench()
						.getActiveWorkbenchWindow().getActivePage();
				IEditorDescriptor desc = PlatformUI.getWorkbench()
						.getEditorRegistry().getDefaultEditor(file.getName());
				try {
					IEditorPart editor = page.openEditor(new FileEditorInput(
							file), desc.getId());
					if (editor instanceof WorkflowEditor) {
						WorkflowEditor wEditor = (WorkflowEditor) editor;
						TreeViewer treeViewer = (TreeViewer) wEditor
								.getViewer();
						TreeItem item = treeViewer.getTree().getItem(0);
						item.setExpanded(true);
						treeViewer.refresh();

						Process p = (Process) item.getItem(0).getData();
						selectState(p, wEditor, s);
					}
				} catch (PartInitException e) {
					e.printStackTrace();
				}
			}

		} else {
			MessageDialog.openError(null, FormUI.Messages
					.getString("ShowLinkedTask_1"), FormUI.Messages
					.getString("ShowLinkedTask_2"));
		}
	}

	private void selectDiagramElement(Diagram activeDiagram, Modeler modeler, State s) {
		for (DiagramElement el : activeDiagram.getContained()) {
			if (el instanceof GraphElement) {
				GraphElement gel = (GraphElement) el;
				EObject obj = Utils.getElement(gel);
				if (obj instanceof State) {
					State s2 = (State) obj;
					if (s2.getName().equals(s.getName()))
						modeler.gotoEObject(s2);
				}
			}
		}
	}

	private void selectState(Process p, WorkflowEditor wEditor, State s) {
		Set<State> set = new HashSet<State>();
		set.add(p.getStartstate());
		set.addAll(p.getEndstate());
		set.addAll(p.getNode());
		set.addAll(p.getTasknode());

		for (Object object : set.toArray()) {
			State s2 = (State) object;
			if (s2.getName().equals(s.getName()))
				wEditor.setSelectionToViewer(Collections.singleton(s2));
		}

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

	@Override
	public String getText() {
		return FormUI.Messages.getString("ShowLinkedTask_0");
	}
}
