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
package com.bluexml.side.Class.modeler.diagram.actions;

import org.eclipse.gef.EditPart;
import org.eclipse.gef.ui.actions.WorkbenchPartAction;
import org.eclipse.jface.resource.ImageDescriptor;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.ISelectionChangedListener;
import org.eclipse.jface.viewers.SelectionChangedEvent;
import org.eclipse.jface.viewers.StructuredSelection;
import org.eclipse.ui.IWorkbenchPart;
import org.topcased.modeler.di.model.GraphEdge;
import org.topcased.modeler.di.model.GraphElement;
import org.topcased.modeler.dialogs.ConfirmationDialog;
import org.topcased.modeler.editor.Modeler;
import org.topcased.modeler.utils.Utils;

import com.bluexml.side.Class.modeler.ClazzPlugin;
import com.bluexml.side.Class.modeler.diagram.commands.delete.DeleteLinkClassGeneralizationCommand;
import com.bluexml.side.Class.modeler.diagram.edit.GeneralizationEditPart;
import com.bluexml.side.clazz.AbstractClass;
import com.bluexml.side.util.libs.ui.UIUtils;

public class DeleteLinkClassGeneralizationAction extends WorkbenchPartAction implements ISelectionChangedListener {
	public static String ID = "Unlink Generalization";

	/**
	 * The selected EditPart object
	 */
	private ISelection selection;

	/**
	 * @param part
	 */
	public DeleteLinkClassGeneralizationAction(IWorkbenchPart part) {
		super(part);
	}

	protected void init() {
		setId(ID);
		setText("Delete From Model");
		// load Eclipse icon
		ImageDescriptor img = UIUtils.getImage("org.topcased.modeler", "/icons/deleteFromModel.gif"); //$NON-NLS-1$ //$NON-NLS-2$
		setImageDescriptor(img);
	}

	public void run() {
		Modeler modeler = (Modeler) getWorkbenchPart();
		ConfirmationDialog dialog = new ConfirmationDialog(ClazzPlugin.getActiveWorkbenchShell(), "Delete From Model", "Are you sure you want to delete these model elements ?", modeler.getPreferenceStore(), "deleteModelActionConfirm");
		int result = dialog.open();
		if (result != 0) {
			return;
		}

		StructuredSelection ss = (StructuredSelection) this.selection;
		for (Object o : ss.toList()) {
			if (o instanceof GeneralizationEditPart) {
				//Get edit part and graph element
				GeneralizationEditPart editPart = (GeneralizationEditPart) o;
				GraphEdge eo = (GraphEdge) editPart.getModel();

				//Get source and target edit part
				EditPart sp = editPart.getSource();
				EditPart tp = editPart.getTarget();

				//Get source and target model element
				AbstractClass cs = (AbstractClass) Utils.getElement((GraphElement) sp.getModel());
				AbstractClass ct = (AbstractClass) Utils.getElement((GraphElement) tp.getModel());

				DeleteLinkClassGeneralizationCommand dcgc = new DeleteLinkClassGeneralizationCommand(eo, cs, ct);
				editPart.getViewer().getEditDomain().getCommandStack().execute(dcgc);
			}
		}
	}

	/**
	 * Determine if the action must appear in the context menu
	 * 
	 * @see org.eclipse.gef.ui.actions.WorkbenchPartAction#calculateEnabled()
	 */
	protected boolean calculateEnabled() {
		return true;
	}

	/**
	 * Sets the selected EditPart and refreshes the enabled state of this
	 * action.
	 * 
	 * @param event
	 * @see ISelectionChangedListener#selectionChanged(SelectionChangedEvent)
	 */
	public void selectionChanged(SelectionChangedEvent event) {
		this.selection = event.getSelection();
	}

}
