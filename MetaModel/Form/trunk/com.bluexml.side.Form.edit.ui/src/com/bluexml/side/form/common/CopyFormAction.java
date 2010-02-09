package com.bluexml.side.form.common;

import java.util.Iterator;

import org.eclipse.emf.common.command.Command;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.util.EcoreUtil;
import org.eclipse.emf.edit.command.AddCommand;
import org.eclipse.emf.edit.domain.EditingDomain;
import org.eclipse.emf.edit.domain.IEditingDomainProvider;
import org.eclipse.jface.action.Action;
import org.eclipse.jface.viewers.ISelectionChangedListener;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.viewers.SelectionChangedEvent;
import org.eclipse.ui.IWorkbenchPart;

import com.bluexml.side.form.FormContainer;
import com.bluexml.side.form.FormPackage;


public class CopyFormAction extends Action implements
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
			if (object instanceof FormContainer) {
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
		doAction((FormContainer) selectedObject);
	}

	@SuppressWarnings("unchecked")
	private void doAction(FormContainer form) {
		FormContainer newForm = (FormContainer) EcoreUtil.copy(form);
		((FormContainer) newForm).setLabel("Copy of " + ((FormContainer) newForm).getLabel());
		Command addFormCmd = AddCommand.create(domain, form.eContainer(), FormPackage.eINSTANCE.getFormCollection_Forms(), newForm);
		//Form newForm = (Form) cpyCmd.getResult().iterator().next();
		domain.getCommandStack().execute(addFormCmd);
	}

	@Override
	public String getText() {
		return "Copy Form";
	}

	public void setActiveWorkbenchPart(IWorkbenchPart workbenchPart) {
		if (workbenchPart instanceof IEditingDomainProvider) {
			domain = ((IEditingDomainProvider) workbenchPart).getEditingDomain();
		}
	}
}
