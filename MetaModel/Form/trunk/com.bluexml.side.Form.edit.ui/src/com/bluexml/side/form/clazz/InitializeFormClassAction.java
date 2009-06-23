package com.bluexml.side.form.clazz;

import java.util.Iterator;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.edit.domain.EditingDomain;
import org.eclipse.emf.edit.domain.IEditingDomainProvider;
import org.eclipse.jface.action.Action;
import org.eclipse.jface.viewers.ISelectionChangedListener;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.viewers.SelectionChangedEvent;
import org.eclipse.ui.IWorkbenchPart;

import com.bluexml.side.form.FormClass;
import com.bluexml.side.form.clazz.utils.ClassInitialization;

public class InitializeFormClassAction extends Action implements
		ISelectionChangedListener {

	protected EObject selectedObject;
	private EditingDomain domain;

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
			if (object instanceof FormClass) {
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
		doAction((FormClass) selectedObject);
	}

	private void doAction(FormClass fc) {
		domain.getCommandStack().execute(ClassInitialization.initializeClass(fc, domain));
	}

	@Override
	public String getText() {
		return "Initialize form class";
	}

	public void setActiveWorkbenchPart(IWorkbenchPart workbenchPart) {
		if (workbenchPart instanceof IEditingDomainProvider) {
			domain = ((IEditingDomainProvider) workbenchPart).getEditingDomain();
		}
	}

}
