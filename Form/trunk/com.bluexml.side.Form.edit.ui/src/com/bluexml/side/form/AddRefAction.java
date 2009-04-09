package com.bluexml.side.form;

import java.util.Iterator;
import java.util.Random;

import org.eclipse.emf.common.command.Command;
import org.eclipse.emf.common.command.CompoundCommand;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.edit.command.AddCommand;
import org.eclipse.emf.edit.domain.EditingDomain;
import org.eclipse.emf.edit.domain.IEditingDomainProvider;
import org.eclipse.jface.action.Action;
import org.eclipse.jface.viewers.ISelectionChangedListener;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.viewers.SelectionChangedEvent;
import org.eclipse.ui.IWorkbenchPart;

import com.bluexml.side.clazz.Clazz;
import com.bluexml.side.form.utils.ClassInitialization;

public class AddRefAction extends Action implements
ISelectionChangedListener {
	
	protected EObject selectedObject;
	private EditingDomain domain;
	private Clazz c;
	private Reference ref;

	public AddRefAction(Clazz p_c, Reference p_ref) {
		super();
		c = p_c;
		ref = p_ref;
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
			if (object instanceof Reference) {
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
		doAction();
	}
	
	@SuppressWarnings("unchecked")
	private void doAction() {
		if (c != null && ref != null) {
			Form form =  formFactory.eINSTANCE.createForm();
			
			FormClass formClass = formFactory.eINSTANCE.createFormClass();
			form.setRoot(formClass);
			
			formClass.setReal_class(c);
			Random random = new Random();
			int pick = random.nextInt(Integer.MAX_VALUE);
			formClass.setId(c.getName() + "_" + pick);
			if (c.getTitle() != null && c.getTitle().length() > 0) {
				formClass.setLabel(c.getTitle());
				form.setName(c.getTitle() + " ref from " + ((FormClass)ref.eContainer()).getLabel() + " (" + ref.getLabel() + ")");
			} else {
				formClass.setLabel(c.getName());
				form.setName(c.getName() + " ref from " + ((FormClass)ref.eContainer()).getLabel() + " (" + ref.getLabel() + ")");
			};
			
			ref.getTarget().add(formClass);
			// Commands :
			// Add the Form	
			Command addFormCmd = AddCommand.create(domain, ref.eContainer().eContainer().eContainer(), formPackage.eINSTANCE.getFormCollection_Forms(), form);
			// Add the reference
			CompoundCommand cc = new CompoundCommand();
			cc.append(addFormCmd);
			cc.append(ClassInitialization.initializeClass(formClass, domain));
			domain.getCommandStack().execute(cc);
		}
	}
	
	@Override
	public String getText() {
		String label = ((c.getTitle() != null && c.getTitle().length() > 0) ? c.getTitle() : c.getName());
		if (c.isIsAbstract()) {
			label += " (Abstract Type)";
		}
		return label;
	}
	
	public void setActiveWorkbenchPart(IWorkbenchPart workbenchPart) {
		if (workbenchPart instanceof IEditingDomainProvider) {
			domain = ((IEditingDomainProvider) workbenchPart).getEditingDomain();
		}
	}
}
