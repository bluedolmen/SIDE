package com.bluexml.side.side.view.edit.ui.actions;

import java.util.Iterator;

import org.eclipse.emf.ecore.plugin.EcorePlugin;
import org.eclipse.emf.edit.domain.EditingDomain;
import org.eclipse.emf.edit.domain.IEditingDomainProvider;
import org.eclipse.jface.action.Action;
import org.eclipse.jface.viewers.ISelectionChangedListener;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.viewers.SelectionChangedEvent;
import org.eclipse.ui.IWorkbenchPart;

import com.bluexml.side.side.view.edit.ui.utils.InitView;
import com.bluexml.side.side.view.edit.ui.utils.InternalModification;
import com.bluexml.side.view.AbstractView;
import com.bluexml.side.view.ViewCollection;




public class SynchronizeViews  extends Action implements
ISelectionChangedListener {

	protected ViewCollection selectedObject;
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
			if (object instanceof ViewCollection) {
				selectedObject = (ViewCollection) object;
			} else {
				return false;
			}
		}
		return selectedObject != null;
	}
	
	@Override
	public void run() {
		super.run();
		doAction(selectedObject);
	}

	private void doAction(ViewCollection av) {
		InternalModification.dontMoveToDisabled();
		try {
			//domain.getCommandStack().execute();
		} catch (Exception e) {
			e.printStackTrace();
			EcorePlugin.INSTANCE.log("Synchronization failed : " + e.getMessage());
			e.printStackTrace();
			InternalModification.moveToDisabled(); 
		}
		InternalModification.moveToDisabled(); 
	}

	@Override
	public String getText() {
		return "Synchronize views with Class Diagram";
	}

	public void setActiveWorkbenchPart(IWorkbenchPart workbenchPart) {
		if (workbenchPart instanceof IEditingDomainProvider) {
			domain = ((IEditingDomainProvider) workbenchPart).getEditingDomain();
		}
	}

}
