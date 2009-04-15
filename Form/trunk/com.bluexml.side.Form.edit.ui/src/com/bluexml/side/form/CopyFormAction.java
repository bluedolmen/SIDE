package com.bluexml.side.form;

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
			if (object instanceof Form) {
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
		doAction((Form) selectedObject);
	}
	
	@SuppressWarnings("unchecked")
	private void doAction(Form form) {
		Form newForm = (Form) EcoreUtil.copy(form);
		newForm.setName("Copy of " + newForm.getName());
		((Form) newForm).getRoot().setLabel("Copy of " + ((Form) newForm).getRoot().getLabel());
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
