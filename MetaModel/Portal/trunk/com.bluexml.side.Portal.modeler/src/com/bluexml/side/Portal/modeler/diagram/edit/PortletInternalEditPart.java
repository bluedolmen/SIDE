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
import org.eclipse.emf.edit.ui.provider.AdapterFactoryLabelProvider;
import org.eclipse.gef.EditPolicy;
import org.eclipse.gef.Request;
import org.eclipse.gef.RequestConstants;
import org.eclipse.gef.commands.Command;
import org.eclipse.jface.resource.JFaceResources;
import org.eclipse.jface.viewers.ILabelProvider;
import org.eclipse.jface.window.Window;
import org.eclipse.swt.graphics.Color;
import org.eclipse.swt.graphics.Font;
import org.eclipse.swt.graphics.FontData;
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
import com.bluexml.side.Portal.modeler.diagram.commands.PortletInternalRestoreConnectionCommand;
import com.bluexml.side.Portal.modeler.diagram.commands.update.PortletInternalUpdateCommand;
import com.bluexml.side.Portal.modeler.diagram.dialogs.PortletInternalEditDialog;
import com.bluexml.side.Portal.modeler.diagram.figures.PortletInternalFigure;
import com.bluexml.side.Portal.modeler.diagram.policies.isInternalPortletEdgeCreationEditPolicy;
import com.bluexml.side.Portal.modeler.diagram.preferences.PdDiagramPreferenceConstants;
import com.bluexml.side.form.provider.FormItemProviderAdapterFactory;
import com.bluexml.side.portal.InternalPortletType;
import com.bluexml.side.portal.PortletInternal;
import com.bluexml.side.view.provider.ViewItemProviderAdapterFactory;

/**
 * The PortletInternal object controller
 * 
 * @generated
 */
public class PortletInternalEditPart extends EMFGraphNodeEditPart {
	/**
	 * Constructor
	 * 
	 * @param obj
	 *            the graph node
	 * @generated
	 */
	public PortletInternalEditPart(GraphNode obj) {
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

		installEditPolicy(ModelerEditPolicyConstants.RESTORE_EDITPOLICY, new RestoreEditPolicy() {
			protected Command getRestoreConnectionsCommand(RestoreConnectionsRequest request) {
				return new PortletInternalRestoreConnectionCommand(getHost());
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

		return new PortletInternalFigure();
	}

	/**
	 * @see org.topcased.modeler.edit.GraphNodeEditPart#getPreferenceDefaultBackgroundColor()
	 * @generated
	 */
	protected Color getPreferenceDefaultBackgroundColor() {
		String backgroundColor = getPreferenceStore().getString(PdDiagramPreferenceConstants.PORTLETINTERNAL_DEFAULT_BACKGROUND_COLOR);
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
		String foregroundColor = getPreferenceStore().getString(PdDiagramPreferenceConstants.PORTLETINTERNAL_DEFAULT_FOREGROUND_COLOR);
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
		String preferenceFont = getPreferenceStore().getString(PdDiagramPreferenceConstants.PORTLETINTERNAL_DEFAULT_FONT);
		if (preferenceFont.length() != 0) {
			return Utils.getFont(new FontData(preferenceFont));
		}
		return null;

	}

	@Override
	public void performRequest(Request request) {
		if (request.getType() == RequestConstants.REQ_OPEN) {
			PortletInternal portlet = (PortletInternal) Utils.getElement(getGraphNode());

			PortletInternalEditDialog dlg = new PortletInternalEditDialog(portlet, PortalPlugin.getActiveWorkbenchShell());
			if (dlg.open() == Window.OK) {
				PortletInternalUpdateCommand command = new PortletInternalUpdateCommand(portlet, dlg.getData());
				getViewer().getEditDomain().getCommandStack().execute(command);
				refresh();
			}
		} else {
			super.performRequest(request);
		}
	}

	/**
	 * Set the name of the PortletInternal
	 * 
	 * @see org.topcased.modeler.edit.EMFGraphNodeEditPart#refreshHeaderLabel()
	 */
	protected void refreshHeaderLabel() {
		IFigure ifig = getFigure();
		if (ifig instanceof PortletInternalFigure) {
			PortletInternalFigure fig = (PortletInternalFigure) ifig;
			ILabel lbl = fig.getLabel();
			PortletInternal pi = (PortletInternal) Utils.getElement(getGraphNode());
			String textLabel = getPortletInternalLabel(pi);
			lbl.setText(textLabel);

			lbl.setFont(JFaceResources.getFontRegistry().get("BoldFont"));
		}
	}

	/**
	 * @param pi
	 * @return
	 */
	private String getPortletInternalLabel(PortletInternal pi) {
		String textLabel = "";
		if (pi.getType() != null && pi.getType().toString().length() > 0) {
			if (pi.getType().equals(InternalPortletType.FORM) && pi.getForm() != null) {
				ILabelProvider labelProvider = new AdapterFactoryLabelProvider(new FormItemProviderAdapterFactory());
				textLabel += labelProvider.getText(pi.getForm());
			} else if (pi.getType().equals(InternalPortletType.VIEW) && pi.getView() != null) {
				ILabelProvider labelProvider = new AdapterFactoryLabelProvider(new ViewItemProviderAdapterFactory());
				textLabel = pi.getType() + " ";
				textLabel += labelProvider.getText(pi.getView());
			}
		}
		return textLabel;
	}

}