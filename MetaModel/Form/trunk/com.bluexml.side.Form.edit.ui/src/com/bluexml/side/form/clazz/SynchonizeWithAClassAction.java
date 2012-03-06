package com.bluexml.side.form.clazz;

import java.util.Iterator;

import org.eclipse.emf.common.command.CompoundCommand;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.edit.domain.EditingDomain;
import org.eclipse.emf.edit.domain.IEditingDomainProvider;
import org.eclipse.jface.action.Action;
import org.eclipse.jface.viewers.ISelectionChangedListener;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.viewers.SelectionChangedEvent;
import org.eclipse.ui.IWorkbenchPart;

import com.bluexml.side.form.FormClass;
import com.bluexml.side.form.FormContainer;
import com.bluexml.side.form.FormSearch;
import com.bluexml.side.form.clazz.utils.SynchronizeWithClass;

public class SynchonizeWithAClassAction extends Action implements ISelectionChangedListener {
	protected EObject selectedObject;
	private EditingDomain domain;

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
			if (object instanceof FormClass || object instanceof FormSearch) {
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

	private void doAction(FormContainer fc) {
		// We will iterate on each child on make action for each FormClass
		SynchronizeWithClass synchronizer = new SynchronizeWithClass(domain);
		synchronizer.synchronize(fc);
		CompoundCommand cc = synchronizer.getCc();
		domain.getCommandStack().execute(cc);
		
	}

	@Override
	public String getText() {
		return "Synchronize with Class Diagram";
	}

	public void setActiveWorkbenchPart(IWorkbenchPart workbenchPart) {
		if (workbenchPart instanceof IEditingDomainProvider) {
			domain = ((IEditingDomainProvider) workbenchPart).getEditingDomain();
		}
	}
}
