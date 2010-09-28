package com.bluexml.side.Portal.modeler.diagram.actions;

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
import org.topcased.modeler.preferences.ModelerPreferenceConstants;
import org.topcased.modeler.utils.Utils;

import com.bluexml.side.Portal.modeler.PortalPlugin;
import com.bluexml.side.Portal.modeler.diagram.commands.delete.DeleteLinkGeneratedPortletCommand;
import com.bluexml.side.Portal.modeler.diagram.edit.PortletEditPart;
import com.bluexml.side.Portal.modeler.diagram.edit.isInternalPortletEditPart;
import com.bluexml.side.Portal.modeler.editor.ModelerContextMenuProvider;
import com.bluexml.side.portal.Portlet;
import com.bluexml.side.util.libs.ui.UIUtils;

public class DeleteLinkGeneratedPortel extends WorkbenchPartAction implements ISelectionChangedListener {
	public static String ID = "Unlink Generated Portlet";

	/**
	 * The selected EditPart object
	 */
	private ISelection selection;

	public DeleteLinkGeneratedPortel(IWorkbenchPart part) {
		super(part);
	}

	protected void init() {
		setId(ID);
		setText("Delete From Model");
		// load Eclipse icon
		ImageDescriptor img = UIUtils.getImage("org.topcased.modeler", "/icons/deleteFromModel.gif"); //$NON-NLS-1$ //$NON-NLS-2$
		setImageDescriptor(img);
	}

	@Override
	protected boolean calculateEnabled() {
		return selection != null && ModelerContextMenuProvider.checkAllElements(selection, isInternalPortletEditPart.class);
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

	public void run() {
		Modeler modeler = (Modeler)getWorkbenchPart();
		ConfirmationDialog dialog = new ConfirmationDialog(PortalPlugin.getActiveWorkbenchShell(), "Delete From Model", "Are you sure you want to delete these model elements ?", modeler.getPreferenceStore(), ModelerPreferenceConstants.DELETE_MODEL_ACTION_CONFIRM);
        int result = dialog.open();
        if(result != 0) {
        	return;
        }
		
		StructuredSelection ss = (StructuredSelection) this.selection;
		for (Object o : ss.toList()) {
			if (o instanceof isInternalPortletEditPart) {
				//Get edit part and graph element
				isInternalPortletEditPart link = (isInternalPortletEditPart) o;
				GraphEdge eo = (GraphEdge) link.getModel();

				//Get source edit part
				PortletEditPart pep = (PortletEditPart) link.getSource();
				
				//Get source model element
				Portlet portlet = (Portlet) Utils.getElement((GraphElement) pep.getModel());
				
				// call command to delete link
				DeleteLinkGeneratedPortletCommand dlgpc = new DeleteLinkGeneratedPortletCommand(portlet, eo);
				link.getViewer().getEditDomain().getCommandStack().execute(dlgpc);
			}
		}

	}

}
