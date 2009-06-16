package com.bluexml.side.side.view.edit.ui.actions;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.plugin.EcorePlugin;
import org.eclipse.emf.edit.domain.EditingDomain;
import org.eclipse.emf.edit.domain.IEditingDomainProvider;
import org.eclipse.jface.action.Action;
import org.eclipse.jface.viewers.ISelectionChangedListener;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.viewers.SelectionChangedEvent;
import org.eclipse.ui.IWorkbenchPart;

import com.bluexml.side.side.view.edit.ui.utils.InternalModification;
import com.bluexml.side.side.view.edit.ui.utils.model.ViewUtils;
import com.bluexml.side.view.Col;
import com.bluexml.side.view.FieldContainer;




public class MergeCols  extends Action implements
ISelectionChangedListener {

	protected List<Col> selectedObject;
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
		selectedObject = new ArrayList<Col>();
		for (Iterator<?> objects = selection.iterator(); objects.hasNext();) {
			Object object = objects.next();
			if (object instanceof Col) {
				// We add cols having the same parent only
				Col col = (Col) object;
				if (selectedObject.size() > 0 && selectedObject.get(0).eContainer().equals(col.eContainer())) {
					selectedObject.add(col);
				} else if(selectedObject.size() == 0) {
					selectedObject.add((Col) object);
					container = col.eContainer();
				}
			} else {
				return false;
			}
		}
		return selectedObject.size() > 0;
	}
	
	@Override
	public void run() {
		super.run();
		doAction(selectedObject);
	}

	private void doAction(List<Col> lc) {
		InternalModification.dontMoveToDisabled();
		try {
			domain.getCommandStack().execute(ViewUtils.getCommandForColMerge(selectedObject,(FieldContainer)container,domain));
		} catch (Exception e) {
			e.printStackTrace();
			EcorePlugin.INSTANCE.log("Merge failed : " + e.getMessage());
			e.printStackTrace();
			InternalModification.moveToDisabled(); 
		}
		InternalModification.moveToDisabled(); 
	}

	@Override
	public String getText() {
		return "Merge";
	}

	public void setActiveWorkbenchPart(IWorkbenchPart workbenchPart) {
		if (workbenchPart instanceof IEditingDomainProvider) {
			domain = ((IEditingDomainProvider) workbenchPart).getEditingDomain();
		}
	}

}
