package com.bluexml.side.view.edit.ui.actions;

import java.util.Iterator;

import org.eclipse.emf.common.command.Command;
import org.eclipse.emf.common.command.CompoundCommand;
import org.eclipse.emf.edit.command.AddCommand;
import org.eclipse.emf.edit.domain.EditingDomain;
import org.eclipse.emf.edit.domain.IEditingDomainProvider;
import org.eclipse.jface.action.Action;
import org.eclipse.jface.resource.ImageDescriptor;
import org.eclipse.jface.viewers.ISelectionChangedListener;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.viewers.SelectionChangedEvent;
import org.eclipse.ui.IWorkbenchPart;

import com.bluexml.side.view.AbstractView;
import com.bluexml.side.view.FieldElement;
import com.bluexml.side.view.ViewPackage;

public class RestoreFieldAction  extends Action implements
ISelectionChangedListener {
	protected AbstractView selectedObject;
	protected FieldElement toRestore;
	private EditingDomain domain;
	
	public RestoreFieldAction(FieldElement f, AbstractView av) {
		super();
		toRestore = f;
		selectedObject = av;
	}
	
	public RestoreFieldAction(FieldElement f, ImageDescriptor imgDes, AbstractView av) {
		super(f.getName(),imgDes);
		toRestore = f;
		
		selectedObject = av;
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
			if (object instanceof AbstractView) {
				selectedObject = (AbstractView) object;
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
		Command addCmd = AddCommand.create(domain, selectedObject, ViewPackage.eINSTANCE.getFieldContainer_Children(), toRestore);
		cc.append(addCmd);
		domain.getCommandStack().execute(cc);
	}

	@Override
	public String getText() {
		return toRestore.getName();
	}
	
	public void setActiveWorkbenchPart(IWorkbenchPart workbenchPart) {
		if (workbenchPart instanceof IEditingDomainProvider) {
			domain = ((IEditingDomainProvider) workbenchPart).getEditingDomain();
		}
	}
}
