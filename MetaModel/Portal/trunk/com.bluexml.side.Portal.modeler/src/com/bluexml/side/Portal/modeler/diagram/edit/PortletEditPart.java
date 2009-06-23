/*******************************************************************************
 * This program is free software; you can redistribute it and/or modify
 *  it under the terms of the GNU General Public License as published by
 *  the Free Software Foundation; either version 2.
 * 
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 * 
 * You should have received a copy of the GNU General Public License
 * along with this program; if not, write to the Free Software
 * Foundation, Inc., 59 Temple Place, Boston, MA 02111.
 ******************************************************************************/
package com.bluexml.side.Portal.modeler.diagram.edit;

import org.eclipse.draw2d.IFigure;
import org.eclipse.gef.EditPolicy;
import org.eclipse.gef.Request;
import org.eclipse.gef.RequestConstants;
import org.eclipse.gef.commands.Command;
import org.eclipse.jface.resource.JFaceResources;
import org.eclipse.jface.window.Window;
import org.eclipse.swt.graphics.Color;
import org.eclipse.swt.graphics.Font;
import org.eclipse.swt.graphics.FontData;
import org.topcased.draw2d.figures.EditableLabel;
import org.topcased.draw2d.figures.ILabel;
import org.topcased.modeler.ModelerEditPolicyConstants;
import org.topcased.modeler.di.model.GraphNode;
import org.topcased.modeler.edit.EMFGraphNodeEditPart;
import org.topcased.modeler.edit.policies.LabelDirectEditPolicy;
import org.topcased.modeler.edit.policies.ResizableEditPolicy;
import org.topcased.modeler.edit.policies.RestoreEditPolicy;
import org.topcased.modeler.requests.RestoreConnectionsRequest;
import org.topcased.modeler.utils.Utils;

import com.bluexml.side.Portal.modeler.PortalPlugin;
import com.bluexml.side.Portal.modeler.diagram.PdEditPolicyConstants;
import com.bluexml.side.Portal.modeler.diagram.commands.PortletRestoreConnectionCommand;
import com.bluexml.side.Portal.modeler.diagram.commands.update.PortletUpdateCommand;
import com.bluexml.side.Portal.modeler.diagram.dialogs.PortletEditDialog;
import com.bluexml.side.Portal.modeler.diagram.figures.PortletFigure;
import com.bluexml.side.Portal.modeler.diagram.policies.HavePortletEdgeCreationEditPolicy;
import com.bluexml.side.Portal.modeler.diagram.policies.InstanciatePortletTypeEdgeCreationEditPolicy;
import com.bluexml.side.Portal.modeler.diagram.policies.isInternalPortletEdgeCreationEditPolicy;
import com.bluexml.side.Portal.modeler.diagram.preferences.PdDiagramPreferenceConstants;
import com.bluexml.side.portal.Portlet;

/**
 * The Portlet object controller
 *
 * @generated
 */
public class PortletEditPart extends EMFGraphNodeEditPart {
	/**
	 * Constructor
	 *
	 * @param obj the graph node
	 * @generated
	 */
	public PortletEditPart(GraphNode obj) {
		super(obj);
	}

	/**
	 * Creates edit policies and associates these with roles
	 *
	 * @generated
	 */
	protected void createEditPolicies() {
		super.createEditPolicies();

		installEditPolicy(PdEditPolicyConstants.ISINTERNALPORTLET_EDITPOLICY, new isInternalPortletEdgeCreationEditPolicy());

		installEditPolicy(PdEditPolicyConstants.HAVEPORTLET_EDITPOLICY, new HavePortletEdgeCreationEditPolicy());

		installEditPolicy(PdEditPolicyConstants.INSTANCIATEPORTLETTYPE_EDITPOLICY, new InstanciatePortletTypeEdgeCreationEditPolicy());

		installEditPolicy(ModelerEditPolicyConstants.RESTORE_EDITPOLICY, new RestoreEditPolicy() {
			protected Command getRestoreConnectionsCommand(RestoreConnectionsRequest request) {
				return new PortletRestoreConnectionCommand(getHost());
			}
		});

		installEditPolicy(ModelerEditPolicyConstants.RESIZABLE_EDITPOLICY, new ResizableEditPolicy());

		installEditPolicy(EditPolicy.DIRECT_EDIT_ROLE, new LabelDirectEditPolicy());
	}

	/**
	 * @see org.eclipse.gef.editparts.AbstractGraphicalEditPart#createFigure()
	 * @generated
	 */
	protected IFigure createFigure() {

		return new PortletFigure();
	}

	/**
	 * @see org.topcased.modeler.edit.GraphNodeEditPart#getPreferenceDefaultBackgroundColor()
	 * @generated
	 */
	protected Color getPreferenceDefaultBackgroundColor() {
		String backgroundColor = getPreferenceStore().getString(PdDiagramPreferenceConstants.PORTLET_DEFAULT_BACKGROUND_COLOR);
		if (backgroundColor.length() != 0) {
			return Utils.getColor(backgroundColor);
		}
		return null;
	}

	/**
	 * @see org.topcased.modeler.edit.GraphNodeEditPart#getPreferenceDefaultForegroundColor()
	 * @generated
	 */
	protected Color getPreferenceDefaultForegroundColor() {
		String foregroundColor = getPreferenceStore().getString(PdDiagramPreferenceConstants.PORTLET_DEFAULT_FOREGROUND_COLOR);
		if (foregroundColor.length() != 0) {
			return Utils.getColor(foregroundColor);
		}
		return null;
	}

	/**
	 * @see org.topcased.modeler.edit.GraphNodeEditPart#getPreferenceDefaultFont()
	 * @generated
	 */
	protected Font getPreferenceDefaultFont() {
		String preferenceFont = getPreferenceStore().getString(PdDiagramPreferenceConstants.PORTLET_DEFAULT_FONT);
		if (preferenceFont.length() != 0) {
			return Utils.getFont(new FontData(preferenceFont));
		}
		return null;

	}

	@Override
	public void performRequest(Request request) {
		if (request.getType() == RequestConstants.REQ_OPEN) {
			Portlet portlet = (Portlet) Utils.getElement(getGraphNode());

			PortletEditDialog dlg = new PortletEditDialog(portlet, PortalPlugin.getActiveWorkbenchShell());
			if (dlg.open() == Window.OK) {
				PortletUpdateCommand command = new PortletUpdateCommand(portlet, dlg.getData());
				getViewer().getEditDomain().getCommandStack().execute(command);
				refresh();
			}
		} else {
			super.performRequest(request);
		}
	}

	@Override
	public void handleNameChange(String value) {
		EditableLabel editableLabel = (EditableLabel) getHeader();
		Portlet portlet = (Portlet) Utils.getElement(getGraphNode());
		portlet.setName(value);
		refresh();
		super.handleNameChange(value);
	}

	/**
	 * Set the name of the Portlet
	 * 
	 * @see org.topcased.modeler.edit.EMFGraphNodeEditPart#refreshHeaderLabel()
	 */
	protected void refreshHeaderLabel() {
		IFigure ifig = getFigure();
		if (ifig instanceof PortletFigure) {
			PortletFigure fig = (PortletFigure) ifig;
			ILabel lbl = fig.getLabel();
			Portlet po = (Portlet) Utils.getElement(getGraphNode());

			lbl.setText(po.getName());
			lbl.setFont(JFaceResources.getFontRegistry().get("BoldFont"));
		}
	}
}