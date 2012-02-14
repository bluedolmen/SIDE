package com.bluexml.side.form.clazz;

import java.util.Iterator;

import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.edit.command.MoveCommand;
import org.eclipse.emf.edit.domain.EditingDomain;
import org.eclipse.emf.edit.domain.IEditingDomainProvider;
import org.eclipse.jface.action.Action;
import org.eclipse.jface.viewers.ISelectionChangedListener;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.viewers.SelectionChangedEvent;
import org.eclipse.ui.IWorkbenchPart;

import com.bluexml.side.form.FormCollection;
import com.bluexml.side.form.FormElement;
import com.bluexml.side.form.FormGroup;
import com.bluexml.side.form.FormUI;

public class MoveSiblingUp extends Action implements ISelectionChangedListener {

	protected EObject selectedObject;
	private EditingDomain domain;

	@Override
	public void selectionChanged(SelectionChangedEvent event) {
		if (event.getSelection() instanceof IStructuredSelection) {
			setEnabled(updateSelection((IStructuredSelection) event.getSelection()));
		} else {
			setEnabled(false);
		}

	}

	public boolean updateSelection(IStructuredSelection selection) {
		selectedObject = null;
		for (Iterator<?> objects = selection.iterator(); objects.hasNext();) {
			Object object = objects.next();
			if (object instanceof FormElement) {
				selectedObject = (EObject) object;
			} else {
				return false;
			}
		}

		return selectedObject != null;
	}

	@Override
	public void run() {
		doAction((FormElement) selectedObject);
	}

	private void doAction(FormElement fc) {
		int newIndex = 0;
		Object value = fc;

		EList<?> list = null;

		EObject eContainer = fc.eContainer();
		if (eContainer instanceof FormGroup) {
			FormGroup parent = (FormGroup) eContainer;
			list = parent.getChildren();
		} else if (eContainer instanceof FormCollection) {
			FormCollection fcc = (FormCollection) eContainer;
			list = fcc.getForms();
		}

		newIndex = getNewIndex(fc, list);

		MoveCommand command = new MoveCommand(domain, list, value, newIndex);
		domain.getCommandStack().execute(command);
	}

	public int getNewIndex(FormElement fc, EList<?> list) {
		int newIndex;
		int indexOf = list.indexOf(fc);
		newIndex = indexOf > 0 ? indexOf - 1 : 0;
		return newIndex;
	}

	@Override
	public String getText() {
		return FormUI.Messages.getString("Actions_moveSiblingUp");
	}

	public void setActiveWorkbenchPart(IWorkbenchPart workbenchPart) {
		if (workbenchPart instanceof IEditingDomainProvider) {
			domain = ((IEditingDomainProvider) workbenchPart).getEditingDomain();
		}
	}
}
