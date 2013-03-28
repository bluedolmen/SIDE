/*
    Copyright (C) 2007-20013  BlueXML - www.bluexml.com

    This program is free software: you can redistribute it and/or modify
    it under the terms of the GNU Affero General Public License as
    published by the Free Software Foundation, either version 3 of the
    License, or (at your option) any later version.

    This program is distributed in the hope that it will be useful,
    but WITHOUT ANY WARRANTY; without even the implied warranty of
    MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
    GNU Affero General Public License for more details.

    You should have received a copy of the GNU Affero General Public License
    along with this program.  If not, see <http://www.gnu.org/licenses/>.
*/
package com.bluexml.side.view.edit.ui.actions;

import java.util.Iterator;

import org.eclipse.emf.common.command.CompoundCommand;
import org.eclipse.emf.ecore.plugin.EcorePlugin;
import org.eclipse.emf.edit.domain.EditingDomain;
import org.eclipse.emf.edit.domain.IEditingDomainProvider;
import org.eclipse.jface.action.Action;
import org.eclipse.jface.viewers.ISelectionChangedListener;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.viewers.SelectionChangedEvent;
import org.eclipse.ui.IWorkbenchPart;

import com.bluexml.side.view.AbstractView;
import com.bluexml.side.view.AbstractViewOf;
import com.bluexml.side.view.ViewCollection;
import com.bluexml.side.view.edit.ui.utils.InternalModification;
import com.bluexml.side.view.edit.ui.utils.model.ClassUtils;




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
			CompoundCommand cmd = new CompoundCommand();
			for(AbstractView view : av.getViews()) {
				if (view instanceof AbstractViewOf) {
					cmd.append(ClassUtils.synchronizeView((AbstractViewOf)view, domain));
				}
			}
			domain.getCommandStack().execute(cmd);
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
