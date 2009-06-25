package com.bluexml.side.side.view.edit.ui.actions;

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

import com.bluexml.side.clazz.Association;
import com.bluexml.side.clazz.Attribute;
import com.bluexml.side.view.AbstractView;

public class AddLinkedFieldAction  extends Action implements
ISelectionChangedListener {
	protected EObject toAdd;
	private EditingDomain domain;
	
	public AddLinkedFieldAction(EObject a) {
		super();
		toAdd = a;
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
		
		for (Iterator<?> objects = selection.iterator(); objects.hasNext();) {
			Object object = objects.next();
			if (object instanceof AbstractView) {
				toAdd = (Attribute) object;
			} else {
				return false;
			}
		}

		return toAdd != null;
	}
	
	@Override
	public void run() {
		super.run();
		doAction();
	}
	
	private void doAction() {
		CompoundCommand cc = new CompoundCommand();
		
		
		domain.getCommandStack().execute(cc);
	}

	@Override
	public String getText() {
		String text = "";
		if (toAdd != null && toAdd instanceof Association) {
			Association asso = (Association) toAdd;
			text = asso.getTitle();
			if (text == null || text.length() == 0) {
				text = asso.getName();
			}
		} else if (toAdd != null && toAdd instanceof Attribute) {
			Attribute att = (Attribute) toAdd;
			text = att.getTitle();
			if (text == null || text.length() == 0) {
				text = att.getName();
			}
		}
		return text;
	}
	
	public void setActiveWorkbenchPart(IWorkbenchPart workbenchPart) {
		if (workbenchPart instanceof IEditingDomainProvider) {
			domain = ((IEditingDomainProvider) workbenchPart).getEditingDomain();
		}
	}
}
