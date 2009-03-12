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
import com.bluexml.side.Portal.modeler.diagram.commands.PageRestoreConnectionCommand;
import com.bluexml.side.Portal.modeler.diagram.commands.update.PageUpdateCommand;
import com.bluexml.side.Portal.modeler.diagram.dialogs.PageEditDialog;
import com.bluexml.side.Portal.modeler.diagram.figures.PageFigure;
import com.bluexml.side.Portal.modeler.diagram.policies.HavePortletEdgeCreationEditPolicy;
import com.bluexml.side.Portal.modeler.diagram.policies.UseLayoutEdgeCreationEditPolicy;
import com.bluexml.side.Portal.modeler.diagram.policies.isChildPageEdgeCreationEditPolicy;
import com.bluexml.side.Portal.modeler.diagram.preferences.PdDiagramPreferenceConstants;
import com.bluexml.side.portal.Page;

/**
 * The Page object controller
 *
 * @generated
 */
public class PageEditPart extends EMFGraphNodeEditPart {
	/**
	 * Constructor
	 *
	 * @param obj the graph node
	 * @generated
	 */
	public PageEditPart(GraphNode obj) {
		super(obj);
	}

	/**
	 * Creates edit policies and associates these with roles
	 *
	 * @generated
	 */
	protected void createEditPolicies() {
		super.createEditPolicies();

		installEditPolicy(PdEditPolicyConstants.ISCHILDPAGE_EDITPOLICY, new isChildPageEdgeCreationEditPolicy());

		installEditPolicy(PdEditPolicyConstants.USELAYOUT_EDITPOLICY, new UseLayoutEdgeCreationEditPolicy());

		installEditPolicy(PdEditPolicyConstants.HAVEPORTLET_EDITPOLICY, new HavePortletEdgeCreationEditPolicy());

		installEditPolicy(ModelerEditPolicyConstants.RESTORE_EDITPOLICY, new RestoreEditPolicy() {
			protected Command getRestoreConnectionsCommand(RestoreConnectionsRequest request) {
				return new PageRestoreConnectionCommand(getHost());
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

		return new PageFigure();
	}

	/**
	 * @see org.topcased.modeler.edit.GraphNodeEditPart#getPreferenceDefaultBackgroundColor()
	 * @generated
	 */
	protected Color getPreferenceDefaultBackgroundColor() {
		String backgroundColor = getPreferenceStore().getString(PdDiagramPreferenceConstants.PAGE_DEFAULT_BACKGROUND_COLOR);
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
		String foregroundColor = getPreferenceStore().getString(PdDiagramPreferenceConstants.PAGE_DEFAULT_FOREGROUND_COLOR);
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
		String preferenceFont = getPreferenceStore().getString(PdDiagramPreferenceConstants.PAGE_DEFAULT_FONT);
		if (preferenceFont.length() != 0) {
			return Utils.getFont(new FontData(preferenceFont));
		}
		return null;

	}

	@Override
	public void performRequest(Request request) {
		if (request.getType() == RequestConstants.REQ_OPEN) {
			Page page = (Page) Utils.getElement(getGraphNode());

			PageEditDialog dlg = new PageEditDialog(page, PortalPlugin.getActiveWorkbenchShell());
			if (dlg.open() == Window.OK) {
				PageUpdateCommand command = new PageUpdateCommand(page, dlg.getData());
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
		Page page = (Page) Utils.getElement(getGraphNode());
		page.setID(value);
		refresh();
		super.handleNameChange(value);
	}

	/**
	 * Set the name of the Page
	 * 
	 * @see org.topcased.modeler.edit.EMFGraphNodeEditPart#refreshHeaderLabel()
	 */
	protected void refreshHeaderLabel() {
		IFigure ifig = getFigure();
		if (ifig instanceof PageFigure) {
			PageFigure fig = (PageFigure) ifig;
			ILabel lbl = fig.getLabel();
			Page pg = (Page) Utils.getElement(getGraphNode());

			lbl.setText(pg.getID());
			//lbl.setFont(JFaceResources.getFontRegistry().get("BoldFont"));
		}
	}
}