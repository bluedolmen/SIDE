package com.bluexml.side.side.view.edit.ui.actions;

import java.util.Iterator;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.plugin.EcorePlugin;
import org.eclipse.emf.edit.domain.EditingDomain;
import org.eclipse.emf.edit.domain.IEditingDomainProvider;
import org.eclipse.jface.action.Action;
import org.eclipse.jface.viewers.ISelectionChangedListener;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.viewers.SelectionChangedEvent;
import org.eclipse.ui.IWorkbenchPart;

import com.bluexml.side.side.view.edit.ui.utils.ColConfManager;
import com.bluexml.side.side.view.edit.ui.utils.InternalModification;
import com.bluexml.side.view.Col;




public class PasteColConfAction  extends Action implements
ISelectionChangedListener {

	protected Col selectedObject = null;
	protected EObject container;
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
		if (selection.size() == 1) {
			Iterator<?> objects = selection.iterator();
			Object object = objects.next();
			if (object instanceof Col) {
				selectedObject = (Col)object;
			}
		}
		return (selectedObject != null && ColConfManager.getActualCopiedCol() != null);
	}
	
	@Override
	public void run() {
		super.run();
		doAction(selectedObject);
	}

	private void doAction(Col c) {
		InternalModification.dontMoveToDisabled();
		try {
			domain.getCommandStack().execute(ColConfManager.paste(c, domain));
		} catch (Exception e) {
			e.printStackTrace();
			EcorePlugin.INSTANCE.log("Paste Column : " + e.getMessage());
			e.printStackTrace();
			InternalModification.moveToDisabled(); 
		}
		InternalModification.moveToDisabled(); 
	}

	@Override
	public String getText() {
		return "Paste Column Configuration";
	}

	public void setActiveWorkbenchPart(IWorkbenchPart workbenchPart) {
		if (workbenchPart instanceof IEditingDomainProvider) {
			domain = ((IEditingDomainProvider) workbenchPart).getEditingDomain();
		}
	}

}
