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
import com.bluexml.side.Portal.modeler.diagram.commands.PortalLayoutRestoreConnectionCommand;
import com.bluexml.side.Portal.modeler.diagram.commands.update.PortalLayoutUpdateCommand;
import com.bluexml.side.Portal.modeler.diagram.dialogs.PortalLayoutEditDialog;
import com.bluexml.side.Portal.modeler.diagram.figures.PortalLayoutFigure;
import com.bluexml.side.Portal.modeler.diagram.policies.UseLayoutEdgeCreationEditPolicy;
import com.bluexml.side.Portal.modeler.diagram.preferences.PdDiagramPreferenceConstants;
import com.bluexml.side.portal.PortalLayout;

/**
 * The PortalLayout object controller
 *
 * @generated
 */
public class PortalLayoutEditPart extends EMFGraphNodeEditPart {
	/**
	 * Constructor
	 *
	 * @param obj the graph node
	 * @generated
	 */
	public PortalLayoutEditPart(GraphNode obj) {
		super(obj);
	}

	/**
	 * Creates edit policies and associates these with roles
	 *
	 * @generated
	 */
	protected void createEditPolicies() {
		super.createEditPolicies();

		installEditPolicy(PdEditPolicyConstants.USELAYOUT_EDITPOLICY, new UseLayoutEdgeCreationEditPolicy());

		installEditPolicy(ModelerEditPolicyConstants.RESTORE_EDITPOLICY, new RestoreEditPolicy() {
			protected Command getRestoreConnectionsCommand(RestoreConnectionsRequest request) {
				return new PortalLayoutRestoreConnectionCommand(getHost());
			}
		});

		installEditPolicy(ModelerEditPolicyConstants.RESIZABLE_EDITPOLICY, new ResizableEditPolicy());

		installEditPolicy(ModelerEditPolicyConstants.CHANGE_BACKGROUND_COLOR_EDITPOLICY, null);
		installEditPolicy(ModelerEditPolicyConstants.CHANGE_FOREGROUND_COLOR_EDITPOLICY, null);
		installEditPolicy(EditPolicy.DIRECT_EDIT_ROLE, new LabelDirectEditPolicy());
	}

	/**
	 * @see org.eclipse.gef.editparts.AbstractGraphicalEditPart#createFigure()
	 * @generated
	 */
	protected IFigure createFigure() {

		return new PortalLayoutFigure();
	}

	/**
	 * @see org.topcased.modeler.edit.GraphNodeEditPart#getPreferenceDefaultBackgroundColor()
	 * @generated
	 */
	protected Color getPreferenceDefaultBackgroundColor() {
		String backgroundColor = getPreferenceStore().getString(PdDiagramPreferenceConstants.PORTALLAYOUT_DEFAULT_BACKGROUND_COLOR);
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
		String foregroundColor = getPreferenceStore().getString(PdDiagramPreferenceConstants.PORTALLAYOUT_DEFAULT_FOREGROUND_COLOR);
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
		String preferenceFont = getPreferenceStore().getString(PdDiagramPreferenceConstants.PORTALLAYOUT_DEFAULT_FONT);
		if (preferenceFont.length() != 0) {
			return Utils.getFont(new FontData(preferenceFont));
		}
		return null;

	}

	@Override
	public void performRequest(Request request) {
		if (request.getType() == RequestConstants.REQ_OPEN) {
			PortalLayout portalLayout = (PortalLayout) Utils.getElement(getGraphNode());

			PortalLayoutEditDialog dlg = new PortalLayoutEditDialog(portalLayout, PortalPlugin.getActiveWorkbenchShell());
			if (dlg.open() == Window.OK) {
				PortalLayoutUpdateCommand command = new PortalLayoutUpdateCommand(portalLayout, dlg.getData());
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
		PortalLayout portalLayout = (PortalLayout) Utils.getElement(getGraphNode());
		portalLayout.setName(value);
		refresh();
		super.handleNameChange(value);
	}

	/**
	 * Set the name of the Layout
	 * 
	 * @see org.topcased.modeler.edit.EMFGraphNodeEditPart#refreshHeaderLabel()
	 */
	@Override
	protected void refreshHeaderLabel() {
		IFigure ifig = getFigure();
		if (ifig instanceof PortalLayoutFigure) {
			PortalLayoutFigure fig = (PortalLayoutFigure) ifig;
			org.topcased.draw2d.figures.ILabel lbl = fig.getLabel();
			PortalLayout pl = (PortalLayout) Utils.getElement(getGraphNode());

			lbl.setText(pl.getName());
			lbl.setFont(JFaceResources.getFontRegistry().get("BoldFont"));
		}
	}
}