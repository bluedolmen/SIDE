package com.bluexml.side.form;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;

import org.eclipse.emf.common.command.Command;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.edit.command.AddCommand;
import org.eclipse.emf.edit.domain.EditingDomain;
import org.eclipse.emf.edit.domain.IEditingDomainProvider;
import org.eclipse.jface.action.Action;
import org.eclipse.jface.viewers.ISelectionChangedListener;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.viewers.SelectionChangedEvent;
import org.eclipse.ui.IWorkbenchPart;

public class GroupAttributeAction extends Action implements
		ISelectionChangedListener {

	protected List<EObject> selectedObjects;
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
		selectedObjects = new ArrayList<EObject>();
		for (Iterator<?> objects = selection.iterator(); objects.hasNext();) {
			Object object = objects.next();
			if (object instanceof Field || object instanceof FormAspect) {
				selectedObjects.add((EObject) object);
			} else {
				return false;
			}
		}

		return selectedObjects.size() > 0;
	}

	@Override
	public void run() {
		super.run();
		doAction(selectedObjects);
	}

	@SuppressWarnings("unchecked")
	private void doAction(List<EObject> l) {
		
		FormElement fe = (FormElement) l.get(0).eContainer();
		int index = ((FormGroup)fe).getChildren().lastIndexOf(l.get(0));
		FormGroup newGroup = formFactory.eINSTANCE.createFormGroup();
		newGroup.setLabel("New group");
		newGroup.getChildren().addAll((Collection<? extends FormElement>) l);
		
		Command cmd = AddCommand.create(domain, fe, formPackage.eINSTANCE.getFormGroup_Children(), newGroup, index);
		domain.getCommandStack().execute(cmd);
	}

	@Override
	public String getText() {
		return "Group in a new group";
	}

	public void setActiveWorkbenchPart(IWorkbenchPart workbenchPart) {
		if (workbenchPart instanceof IEditingDomainProvider) {
			domain = ((IEditingDomainProvider) workbenchPart).getEditingDomain();
		}
	}

}
