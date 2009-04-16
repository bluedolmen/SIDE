package com.bluexml.side.form.common;

import java.util.Iterator;

import org.eclipse.emf.common.command.Command;
import org.eclipse.emf.common.command.CompoundCommand;
import org.eclipse.emf.edit.command.AddCommand;
import org.eclipse.emf.edit.domain.EditingDomain;
import org.eclipse.emf.edit.domain.IEditingDomainProvider;
import org.eclipse.jface.action.Action;
import org.eclipse.jface.viewers.ISelectionChangedListener;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.viewers.SelectionChangedEvent;
import org.eclipse.ui.IWorkbenchPart;

import com.bluexml.side.form.FormClass;
import com.bluexml.side.form.FormElement;
import com.bluexml.side.form.FormPackage;

public class RestoreFormElementAction  extends Action implements
ISelectionChangedListener {
	protected FormClass selectedObject;
	protected FormElement toRestore;
	private EditingDomain domain;
	
	public RestoreFormElementAction(FormElement f, FormClass cl) {
		super();
		toRestore = f;
		selectedObject = cl;
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
			if (object instanceof FormClass) {
				selectedObject = (FormClass) object;
			} else {
				return false;
			}
		}

		return selectedObject != null;
	}
	
	@Override
	public void run() {
		super.run();
		doAction();
	}
	
	private void doAction() {
		CompoundCommand cc = new CompoundCommand();
		Command addCmd = AddCommand.create(domain, selectedObject, FormPackage.eINSTANCE.getFormGroup_Children(), toRestore);
		cc.append(addCmd);
		domain.getCommandStack().execute(cc);
	}

	@Override
	public String getText() {
		return toRestore.getLabel();
	}
	
	public void setActiveWorkbenchPart(IWorkbenchPart workbenchPart) {
		if (workbenchPart instanceof IEditingDomainProvider) {
			domain = ((IEditingDomainProvider) workbenchPart).getEditingDomain();
		}
	}
}
