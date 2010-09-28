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
import org.topcased.modeler.utils.Utils;

import com.bluexml.side.Portal.modeler.PortalPlugin;
import com.bluexml.side.Portal.modeler.diagram.commands.delete.DeleteLinkPageLayoutActionCommand;
import com.bluexml.side.Portal.modeler.diagram.edit.PageEditPart;
import com.bluexml.side.Portal.modeler.diagram.edit.UseLayoutEditPart;
import com.bluexml.side.Portal.modeler.editor.ModelerContextMenuProvider;
import com.bluexml.side.portal.Page;
import com.bluexml.side.util.libs.ui.UIUtils;

public class DeleteLinkPageLayoutAction extends WorkbenchPartAction implements ISelectionChangedListener {
	public static String ID = "Unlink Layout";

	/**
	 * The selected EditPart object
	 */
	private ISelection selection;

	/**
	 * @param part
	 */
	public DeleteLinkPageLayoutAction(IWorkbenchPart part) {
		super(part);
		//setImageDescriptor(OblPlugin.getImageDescriptor("icons/actions/add.gif"));
	}

	protected void init() {
		setId(ID);
		setText("Delete From Model");
		// load Eclipse icon
		ImageDescriptor img = UIUtils.getImage("org.topcased.modeler", "/icons/deleteFromModel.gif"); //$NON-NLS-1$ //$NON-NLS-2$
		setImageDescriptor(img);
	}

	public void run() {
		Modeler modeler = (Modeler)getWorkbenchPart();
		ConfirmationDialog dialog = new ConfirmationDialog(PortalPlugin.getActiveWorkbenchShell(), "Delete From Model", "Are you sure you want to delete these model elements ?", modeler.getPreferenceStore(), "deleteModelActionConfirm");
        int result = dialog.open();
        if(result != 0) {
        	return;
        }
		
		StructuredSelection ss = (StructuredSelection) this.selection;
		for (Object o : ss.toList()) {
			if (o instanceof UseLayoutEditPart) {
				//Get edit part and graph element
				UseLayoutEditPart useLayout = (UseLayoutEditPart) o;
				GraphEdge eo = (GraphEdge) useLayout.getModel();

				//Get source edit part
				PageEditPart pep = (PageEditPart) useLayout.getSource();

				//Get source model element
				Page p = (Page) Utils.getElement((GraphElement) pep.getModel());

				DeleteLinkPageLayoutActionCommand dlpac = new DeleteLinkPageLayoutActionCommand(p, eo);
				useLayout.getViewer().getEditDomain().getCommandStack().execute(dlpac);
			}
		}
	}

	/**
	 * Determine if the action must appear in the context menu
	 * 
	 * @see org.eclipse.gef.ui.actions.WorkbenchPartAction#calculateEnabled()
	 */
	protected boolean calculateEnabled() {
		return ModelerContextMenuProvider.checkAllElements(selection, UseLayoutEditPart.class);
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
