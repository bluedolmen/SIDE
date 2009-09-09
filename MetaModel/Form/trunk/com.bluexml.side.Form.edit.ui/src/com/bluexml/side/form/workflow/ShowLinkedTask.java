package com.bluexml.side.form.workflow;

import java.util.Iterator;

import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.core.runtime.Path;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.xmi.XMIResource;
import org.eclipse.emf.edit.domain.EditingDomain;
import org.eclipse.emf.edit.domain.IEditingDomainProvider;
import org.eclipse.jface.action.Action;
import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.jface.viewers.ISelectionChangedListener;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.viewers.SelectionChangedEvent;
import org.eclipse.ui.IEditorDescriptor;
import org.eclipse.ui.IWorkbenchPage;
import org.eclipse.ui.IWorkbenchPart;
import org.eclipse.ui.PartInitException;
import org.eclipse.ui.PlatformUI;
import org.eclipse.ui.part.FileEditorInput;

import com.bluexml.side.common.ModelElement;
import com.bluexml.side.form.FormWorkflow;
import com.bluexml.side.util.libs.Messages;
import com.bluexml.side.workflow.State;

public class ShowLinkedTask extends Action implements ISelectionChangedListener {

	protected EObject selectedObject;
	private EditingDomain domain;
	private Messages Messages = new Messages(
			"com.bluexml.side.Form.edit.ui", "com.bluexml.side.form.messages");

	public ShowLinkedTask() {
		// TODO Auto-generated constructor stub
	}
	
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

			IWorkbenchPage page = PlatformUI.getWorkbench()
					.getActiveWorkbenchWindow().getActivePage();
			IEditorDescriptor desc = PlatformUI.getWorkbench()
					.getEditorRegistry().getDefaultEditor(file.getName());
			try {
				page.openEditor(new FileEditorInput(file), desc.getId());
			} catch (PartInitException e) {
				e.printStackTrace();
			}

		} else {
			MessageDialog.openError(null, Messages
					.getString("ShowLinkedTask_1"), Messages
					.getString("ShowLinkedTask_2"));
		}
	}

	@Override
	public String getText() {
		return Messages.getString("ShowLinkedTask_0");
	}

	public void setActiveWorkbenchPart(IWorkbenchPart workbenchPart) {
		if (workbenchPart instanceof IEditingDomainProvider) {
			domain = ((IEditingDomainProvider) workbenchPart)
					.getEditingDomain();
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

}
