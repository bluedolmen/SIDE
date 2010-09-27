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

import com.bluexml.side.Portal.modeler.diagram.edit.PortletEditPart;
import com.bluexml.side.Portal.modeler.diagram.edit.PortletInternalEditPart;
import com.bluexml.side.Portal.modeler.diagram.edit.isInternalPortletEditPart;
import com.bluexml.side.Portal.modeler.editor.ModelerContextMenuProvider;
import com.bluexml.side.Portal.modeler.editor.PortalEditor;
import com.bluexml.side.portal.Portlet;
import com.bluexml.side.portal.PortletInternal;
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
		System.out.println("DeleteLinkGeneratedPortel.selectionChanged()");
		this.selection = event.getSelection();
	}


	public void run() {
		StructuredSelection ss = (StructuredSelection) this.selection;
		for (Object o : ss.toList()) {
			if (o instanceof isInternalPortletEditPart) {
				//Get edit part and graph element
				isInternalPortletEditPart link = (isInternalPortletEditPart) o;
				GraphEdge eo = (GraphEdge) link.getModel();
				
				//Get source and target edit part
				PortletEditPart pep = (PortletEditPart) link.getSource();
				PortletInternalEditPart pip = (PortletInternalEditPart) link.getTarget();
				
				//Get source and target model element
				Portlet p = (Portlet) Utils.getElement((GraphElement) pep.getModel());
				PortletInternal pi = (PortletInternal) Utils.getElement((GraphElement) pip.getModel());
				
				p.setIsPortletInternal(null);
				
				//Create delete command and execute it
				DeleteGraphElementCommand cmd = new DeleteGraphElementCommand(eo, true);
				link.getViewer().getEditDomain().getCommandStack().execute(cmd);
			}
		}

	}

}
