package com.bluexml.side.form.common;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;

import org.eclipse.emf.common.command.Command;
import org.eclipse.emf.common.command.CompoundCommand;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.util.EcoreUtil;
import org.eclipse.emf.edit.command.AddCommand;
import org.eclipse.emf.edit.command.DeleteCommand;
import org.eclipse.emf.edit.command.MoveCommand;
import org.eclipse.emf.edit.domain.EditingDomain;
import org.eclipse.emf.edit.domain.IEditingDomainProvider;
import org.eclipse.jface.action.Action;
import org.eclipse.jface.viewers.ISelectionChangedListener;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.viewers.SelectionChangedEvent;
import org.eclipse.ui.IWorkbenchPart;

import com.bluexml.side.form.Field;
import com.bluexml.side.form.FormAspect;
import com.bluexml.side.form.FormElement;
import com.bluexml.side.form.FormFactory;
import com.bluexml.side.form.FormGroup;
import com.bluexml.side.form.FormPackage;
import com.bluexml.side.form.common.utils.FormDiagramUtils;
import com.bluexml.side.form.common.utils.InternalModification;

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
		InternalModification.dontMoveToDisabled();
		CompoundCommand cc = new CompoundCommand();

		FormElement fe = (FormElement) l.get(0).eContainer();
		int index = ((FormGroup)fe).getChildren().lastIndexOf(l.get(0));
		FormGroup newGroup = FormFactory.eINSTANCE.createFormGroup();
		newGroup.setLabel("New group");
		//newGroup.getChildren().addAll((Collection<? extends FormElement>) l);
		//AddCommand.create(domain, newGroup, FormPackage.eINSTANCE.getFormGroup_Children(), l);
		Command addCmd = AddCommand.create(domain, fe, FormPackage.eINSTANCE.getFormGroup_Children(), newGroup, index);
		cc.append(addCmd);
		Command mvCmd = AddCommand.create(domain, newGroup, FormPackage.eINSTANCE.getFormGroup_Children(), EcoreUtil.copyAll(l));
		cc.append(mvCmd);
		Command delCmd = DeleteCommand.create(domain, l);
		cc.append(delCmd);

		domain.getCommandStack().execute(cc);
		InternalModification.moveToDisabled();
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
