/*******************************************************************************
 * No CopyrightText Defined in the configurator file.
 ******************************************************************************/
package com.bluexml.side.Requirements.modeler.processDiagram.edit;

import org.eclipse.draw2d.IFigure;
import org.eclipse.draw2d.PositionConstants;
import org.eclipse.gef.EditPolicy;
import org.eclipse.gef.Request;
import org.eclipse.gef.RequestConstants;
import org.eclipse.gef.commands.Command;
import org.eclipse.jface.window.Window;
import org.eclipse.swt.graphics.Color;
import org.eclipse.swt.graphics.Font;
import org.eclipse.swt.graphics.FontData;
import org.topcased.modeler.ModelerEditPolicyConstants;
import org.topcased.modeler.di.model.GraphNode;
import org.topcased.modeler.edit.EMFGraphNodeEditPart;
import org.topcased.modeler.edit.policies.LabelDirectEditPolicy;
import org.topcased.modeler.edit.policies.ResizableEditPolicy;
import org.topcased.modeler.edit.policies.RestoreEditPolicy;
import org.topcased.modeler.internal.ModelerPlugin;
import org.topcased.modeler.requests.RestoreConnectionsRequest;
import org.topcased.modeler.utils.Utils;

import com.bluexml.side.Requirements.modeler.dialogs.BasicElementDialog;
import com.bluexml.side.Requirements.modeler.dialogs.BasicElementUpdateCommand;
import com.bluexml.side.Requirements.modeler.goalDiagram.figures.AgentFigure;
import com.bluexml.side.Requirements.modeler.processDiagram.ProEditPolicyConstants;
import com.bluexml.side.Requirements.modeler.processDiagram.commands.AgentRestoreConnectionCommand;
import com.bluexml.side.Requirements.modeler.processDiagram.policies.is_responsibleEdgeCreationEditPolicy;
import com.bluexml.side.Requirements.modeler.processDiagram.preferences.ProDiagramPreferenceConstants;
import com.bluexml.side.requirements.BasicElement;

/**
 * The Agent object controller
 *
 * @generated
 */
public class AgentEditPart extends EMFGraphNodeEditPart {
	/**
	 * Constructor
	 *
	 * @param obj the graph node
	 * @generated
	 */
	public AgentEditPart(GraphNode obj) {
		super(obj);
	}

	/**
	 * Creates edit policies and associates these with roles
	 *
	 * @generated
	 */
	protected void createEditPolicies() {
		super.createEditPolicies();

		installEditPolicy(ProEditPolicyConstants.IS_RESPONSIBLE_EDITPOLICY,
				new is_responsibleEdgeCreationEditPolicy());

		installEditPolicy(ModelerEditPolicyConstants.RESTORE_EDITPOLICY,
				new RestoreEditPolicy() {
					protected Command getRestoreConnectionsCommand(
							RestoreConnectionsRequest request) {
						return new AgentRestoreConnectionCommand(getHost());
					}
				});

		ResizableEditPolicy resizableEditPolicy = new ResizableEditPolicy();
		resizableEditPolicy.setResizeDirections(PositionConstants.EAST_WEST);
		installEditPolicy(ModelerEditPolicyConstants.RESIZABLE_EDITPOLICY,
				resizableEditPolicy);

		installEditPolicy(
				ModelerEditPolicyConstants.CHANGE_BACKGROUND_COLOR_EDITPOLICY,
				null);
		installEditPolicy(
				ModelerEditPolicyConstants.CHANGE_FOREGROUND_COLOR_EDITPOLICY,
				null);
		installEditPolicy(EditPolicy.DIRECT_EDIT_ROLE,
				new LabelDirectEditPolicy());
	}

	/**
	 * @see org.eclipse.gef.editparts.AbstractGraphicalEditPart#createFigure()
	 * @generated
	 */
	protected IFigure createFigure() {

		return new AgentFigure();
	}

	/**
	 * @see org.topcased.modeler.edit.GraphNodeEditPart#getPreferenceDefaultBackgroundColor()
	 * @generated
	 */
	protected Color getPreferenceDefaultBackgroundColor() {
		String backgroundColor = getPreferenceStore().getString(
				ProDiagramPreferenceConstants.AGENT_DEFAULT_BACKGROUND_COLOR);
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
		String foregroundColor = getPreferenceStore().getString(
				ProDiagramPreferenceConstants.AGENT_DEFAULT_FOREGROUND_COLOR);
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
		String preferenceFont = getPreferenceStore().getString(
				ProDiagramPreferenceConstants.AGENT_DEFAULT_FONT);
		if (preferenceFont.length() != 0) {
			return Utils.getFont(new FontData(preferenceFont));
		}
		return null;

	}

	@Override
	public void performRequest(Request request) {
		BasicElement element = (BasicElement) Utils.getElement(getGraphNode());

		if (request.getType() == RequestConstants.REQ_OPEN) {
			BasicElementDialog dialog = new BasicElementDialog(ModelerPlugin
					.getActiveWorkbenchShell(), element);
			if (dialog.open() == Window.OK) {
				BasicElementUpdateCommand command = new BasicElementUpdateCommand(
						element, dialog.getData());
				getViewer().getEditDomain().getCommandStack().execute(command);
				refresh();
			}
		} else {
			super.performRequest(request);
		}
	}

}