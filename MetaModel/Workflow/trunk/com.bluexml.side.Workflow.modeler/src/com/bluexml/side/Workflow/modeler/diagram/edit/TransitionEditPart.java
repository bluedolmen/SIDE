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
package com.bluexml.side.Workflow.modeler.diagram.edit;

import org.eclipse.draw2d.ConnectionRouter;
import org.eclipse.draw2d.IFigure;
import org.eclipse.draw2d.PolylineConnection;
import org.eclipse.draw2d.PolylineDecoration;
import org.eclipse.gmf.runtime.draw2d.ui.internal.figures.ConnectionLayerEx;
import org.eclipse.swt.graphics.Color;
import org.eclipse.swt.graphics.Font;
import org.eclipse.swt.graphics.FontData;
import org.topcased.modeler.ModelerEditPolicyConstants;
import org.topcased.modeler.di.model.GraphEdge;
import org.topcased.modeler.edit.EMFGraphEdgeEditPart;
import org.topcased.modeler.figures.EdgeObjectOffsetEditableLabel;
import org.topcased.modeler.utils.Utils;

import com.bluexml.side.Workflow.modeler.diagram.WfEditPolicyConstants;
import com.bluexml.side.Workflow.modeler.diagram.figures.TransitionFigure;
import com.bluexml.side.Workflow.modeler.diagram.policies.actionsEdgeCreationEditPolicy;
import com.bluexml.side.Workflow.modeler.diagram.policies.hasTimerEdgeCreationEditPolicy;
import com.bluexml.side.Workflow.modeler.diagram.preferences.WfDiagramPreferenceConstants;
import com.bluexml.side.workflow.Transition;

/**
 * Transition controller
 *
 * @generated
 */
public class TransitionEditPart extends EMFGraphEdgeEditPart {

	/**
	 * Constructor
	 *
	 * @param model the graph object
	 * @generated
	 */
	public TransitionEditPart(GraphEdge model) {
		super(model);
	}

	/**
	 * @see org.eclipse.gef.editparts.AbstractEditPart#createEditPolicies()
	 * @generated
	 */
	protected void createEditPolicies() {
		super.createEditPolicies();

		installEditPolicy(WfEditPolicyConstants.ACTIONS_EDITPOLICY,
				new actionsEdgeCreationEditPolicy());
		installEditPolicy(WfEditPolicyConstants.HASTIMER_EDITPOLICY,
				new hasTimerEdgeCreationEditPolicy());

		installEditPolicy(ModelerEditPolicyConstants.CHANGE_FONT_EDITPOLICY,
				null);

	}

	/**
	 * @return the Figure
	 * @generated
	 */
	protected IFigure createFigure() {
		TransitionFigure connection = new TransitionFigure();

		createTargetDecoration(connection);

		return connection;
	}

	/**
	 * @param connection the PolylineConnection
	 * @generated
	 */
	private void createTargetDecoration(PolylineConnection connection) {

		PolylineDecoration decoration = new PolylineDecoration();
		decoration.setScale(10, 5);
		connection.setTargetDecoration(decoration);

	}

	/**
	 * @see org.topcased.modeler.edit.GraphEdgeEditPart#getPreferenceDefaultRouter()
	 * 
	 * @generated
	 */
	protected String getPreferenceDefaultRouter() {
		return getPreferenceStore().getString(
				WfDiagramPreferenceConstants.TRANSITION_EDGE_DEFAULT_ROUTER);
	}

	/**
	 * @see org.topcased.modeler.edit.GraphEdgeEditPart#getPreferenceDefaultForegroundColor()
	 * 
	 * @generated
	 */
	protected Color getPreferenceDefaultForegroundColor() {
		String preferenceForeground = getPreferenceStore()
				.getString(
						WfDiagramPreferenceConstants.TRANSITION_EDGE_DEFAULT_FOREGROUND_COLOR);
		if (preferenceForeground.length() != 0) {
			return Utils.getColor(preferenceForeground);
		}
		return null;
	}

	/**
	 * @see org.topcased.modeler.edit.GraphEdgeEditPart#getPreferenceDefaultFont()
	 * 
	 * @generated
	 */
	protected Font getPreferenceDefaultFont() {
		String preferenceFont = getPreferenceStore().getString(
				WfDiagramPreferenceConstants.TRANSITION_EDGE_DEFAULT_FONT);
		if (preferenceFont.length() != 0) {
			return Utils.getFont(new FontData(preferenceFont));
		}
		return null;
	}

	private void updateMiddleNameLabel() {
		Transition transition = (Transition) Utils.getElement(getGraphEdge());

		TransitionFigure fig = ((TransitionFigure) getFigure());
		EdgeObjectOffsetEditableLabel label = (EdgeObjectOffsetEditableLabel) fig
				.getMiddleNameEdgeObject();
		String name = transition.getName();

		label.setText(name);
	}

	@Override
	protected void refreshTextAndFont() {
		super.refreshTextAndFont();
	}
	
	@Override
	protected void refreshEdgeObjects() {
		updateMiddleNameLabel();
		super.refreshEdgeObjects();
	}
	
	@Override
	protected ConnectionRouter getDefaultRouter(ConnectionLayerEx cLayer) {
		return cLayer.getRectilinearRouter();
	}
	
}