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
/*******************************************************************************
 * No CopyrightText Defined in the configurator file.
 ******************************************************************************/
package com.bluexml.side.Requirements.modeler.goalDiagram.edit;

import org.eclipse.draw2d.IFigure;
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
import com.bluexml.side.Requirements.modeler.goalDiagram.ReqEditPolicyConstants;
import com.bluexml.side.Requirements.modeler.goalDiagram.commands.GoalRestoreConnectionCommand;
import com.bluexml.side.Requirements.modeler.goalDiagram.figures.GoalFigure;
import com.bluexml.side.Requirements.modeler.goalDiagram.policies.PrivilegeGroupEdgeCreationEditPolicy;
import com.bluexml.side.Requirements.modeler.goalDiagram.policies.is_responsibleEdgeCreationEditPolicy;
import com.bluexml.side.Requirements.modeler.goalDiagram.policies.is_sub_goalEdgeCreationEditPolicy;
import com.bluexml.side.Requirements.modeler.goalDiagram.preferences.ReqDiagramPreferenceConstants;
import com.bluexml.side.requirements.BasicElement;
import com.bluexml.side.requirements.Goal;

/**
 * The Goal object controller
 *
 * @generated
 */
public class GoalEditPart extends EMFGraphNodeEditPart {
	/**
	 * Constructor
	 *
	 * @param obj the graph node
	 * @generated
	 */
	public GoalEditPart(GraphNode obj) {
		super(obj);
	}

	/**
	 * Creates edit policies and associates these with roles
	 *
	 * @generated
	 */
	protected void createEditPolicies() {
		super.createEditPolicies();

		installEditPolicy(ReqEditPolicyConstants.IS_RESPONSIBLE_EDITPOLICY,
				new is_responsibleEdgeCreationEditPolicy());

		installEditPolicy(ReqEditPolicyConstants.IS_SUB_GOAL_EDITPOLICY,
				new is_sub_goalEdgeCreationEditPolicy());

		installEditPolicy(ReqEditPolicyConstants.PRIVILEGEGROUP_EDITPOLICY,
				new PrivilegeGroupEdgeCreationEditPolicy());

		installEditPolicy(ModelerEditPolicyConstants.RESTORE_EDITPOLICY,
				new RestoreEditPolicy() {
					protected Command getRestoreConnectionsCommand(
							RestoreConnectionsRequest request) {
						return new GoalRestoreConnectionCommand(getHost());
					}
				});

		installEditPolicy(ModelerEditPolicyConstants.RESIZABLE_EDITPOLICY,
				new ResizableEditPolicy());

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

		return new GoalFigure();
	}

	/**
	 * @see org.topcased.modeler.edit.GraphNodeEditPart#getPreferenceDefaultBackgroundColor()
	 * @generated
	 */
	protected Color getPreferenceDefaultBackgroundColor() {
		String backgroundColor = getPreferenceStore().getString(
				ReqDiagramPreferenceConstants.GOAL_DEFAULT_BACKGROUND_COLOR);
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
				ReqDiagramPreferenceConstants.GOAL_DEFAULT_FOREGROUND_COLOR);
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
				ReqDiagramPreferenceConstants.GOAL_DEFAULT_FONT);
		if (preferenceFont.length() != 0) {
			return Utils.getFont(new FontData(preferenceFont));
		}
		return null;

	}

	@Override
	public void refresh() {
		super.refresh();
		Goal g = (Goal) Utils.getElement(getGraphNode());
		((GoalFigure) getFigure()).getLabel().setText(g.getName());
	}

	@Override
	public void performRequest(Request request) {
		BasicElement element = (BasicElement) Utils.getElement(getGraphNode());

		if (request.getType() == RequestConstants.REQ_OPEN) {
			BasicElementDialog dialog = new BasicElementDialog(ModelerPlugin
					.getActiveWorkbenchShell(), element);
			if (dialog.open() == Window.OK) {
				BasicElementUpdateCommand command = new BasicElementUpdateCommand(element, dialog
						.getData());
				getViewer().getEditDomain().getCommandStack().execute(command);
				refresh();
			}
		} else {
			super.performRequest(request);
		}
	}

}
