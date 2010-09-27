package com.bluexml.side.Portal.modeler.diagram.actions;

import org.eclipse.gef.ui.actions.WorkbenchPartAction;
import org.eclipse.jface.resource.ImageDescriptor;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.ISelectionChangedListener;
import org.eclipse.jface.viewers.SelectionChangedEvent;
import org.eclipse.jface.viewers.StructuredSelection;
import org.eclipse.ui.IWorkbenchPart;
import org.topcased.modeler.commands.DeleteGraphElementCommand;
import org.topcased.modeler.di.model.GraphEdge;
import org.topcased.modeler.di.model.GraphElement;
import org.topcased.modeler.utils.Utils;

import com.bluexml.side.Portal.modeler.diagram.edit.PageEditPart;
import com.bluexml.side.Portal.modeler.diagram.edit.PortalLayoutEditPart;
import com.bluexml.side.Portal.modeler.diagram.edit.UseLayoutEditPart;
import com.bluexml.side.Portal.modeler.editor.ModelerContextMenuProvider;
import com.bluexml.side.portal.HavePortlet;
import com.bluexml.side.portal.Page;
import com.bluexml.side.portal.PortalLayout;
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
		StructuredSelection ss = (StructuredSelection) this.selection;
		for (Object o : ss.toList()) {
			if (o instanceof UseLayoutEditPart) {
				//Get edit part and graph element
				UseLayoutEditPart useLayout = (UseLayoutEditPart) o;
				GraphEdge eo = (GraphEdge) useLayout.getModel();

				//Get source and target edit part
				PageEditPart pep = (PageEditPart) useLayout.getSource();
				PortalLayoutEditPart lep = (PortalLayoutEditPart) useLayout.getTarget();

				//Get source and target model element
				Page p = (Page) Utils.getElement((GraphElement) pep.getModel());
				PortalLayout l = (PortalLayout) Utils.getElement((GraphElement) lep.getModel());

				//Remove aspect from class
				p.setUseLayout(null);
				for (HavePortlet hp : p.getPortlets()) {
					if (hp.getPositionGroup() != null) {
						hp.getPositionGroup().removeAll(hp.getPositionGroup());
					}
				}
				
				//Create delete command and execute it
				DeleteGraphElementCommand cmd = new DeleteGraphElementCommand(eo, true);
				useLayout.getViewer().getEditDomain().getCommandStack().execute(cmd);
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
		//		return true;
	}

	/**
	 * Sets the selected EditPart and refreshes the enabled state of this
	 * action.
	 * 
	 * @param event
	 * @see ISelectionChangedListener#selectionChanged(SelectionChangedEvent)
	 */
	public void selectionChanged(SelectionChangedEvent event) {
		System.out.println("DeleteLinkPageLayoutAction.selectionChanged()");
		this.selection = event.getSelection();
	}

}
