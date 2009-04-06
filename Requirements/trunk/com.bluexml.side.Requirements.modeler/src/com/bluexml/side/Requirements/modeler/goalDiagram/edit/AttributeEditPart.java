/*******************************************************************************
 * No CopyrightText Defined in the configurator file.
 ******************************************************************************/
package com.bluexml.side.Requirements.modeler.goalDiagram.edit;

import org.eclipse.draw2d.IFigure;
import org.eclipse.draw2d.PositionConstants;
import org.eclipse.gef.EditPolicy;
import org.eclipse.gef.commands.Command;
import org.eclipse.swt.graphics.Color;
import org.eclipse.swt.graphics.Font;
import org.eclipse.swt.graphics.FontData;
import org.eclipse.swt.graphics.Image;
import org.topcased.modeler.ModelerEditPolicyConstants;
import org.topcased.modeler.di.model.GraphNode;
import org.topcased.modeler.edit.EMFGraphNodeEditPart;
import org.topcased.modeler.edit.policies.LabelDirectEditPolicy;
import org.topcased.modeler.edit.policies.RestoreEditPolicy;
import org.topcased.modeler.requests.RestoreConnectionsRequest;
import org.topcased.modeler.utils.Utils;

import com.bluexml.side.Requirements.modeler.goalDiagram.commands.AttributeRestoreConnectionCommand;
import com.bluexml.side.Requirements.modeler.goalDiagram.figures.AttributeFigure;
import com.bluexml.side.Requirements.modeler.goalDiagram.preferences.ReqDiagramPreferenceConstants;
import com.bluexml.side.requirements.Attribute;
import com.bluexml.side.requirements.AttributeType;

/**
 * The Attribute object controller
 *
 * @generated
 */
public class AttributeEditPart extends EMFGraphNodeEditPart {
	/**
	 * Constructor
	 *
	 * @param obj the graph node
	 * @generated
	 */
	public AttributeEditPart(GraphNode obj) {
		super(obj);
	}

	/**
	 * Creates edit policies and associates these with roles
	 *
	 * @generated
	 */
	protected void createEditPolicies() {
		super.createEditPolicies();

		installEditPolicy(ModelerEditPolicyConstants.RESTORE_EDITPOLICY,
				new RestoreEditPolicy() {
					protected Command getRestoreConnectionsCommand(
							RestoreConnectionsRequest request) {
						return new AttributeRestoreConnectionCommand(getHost());
					}
				});

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

		return new AttributeFigure();
	}

	/**
	 * @see org.topcased.modeler.edit.GraphNodeEditPart#getPreferenceDefaultBackgroundColor()
	 * @generated
	 */
	protected Color getPreferenceDefaultBackgroundColor() {
		String backgroundColor = getPreferenceStore()
				.getString(
						ReqDiagramPreferenceConstants.ATTRIBUTE_DEFAULT_BACKGROUND_COLOR);
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
		String foregroundColor = getPreferenceStore()
				.getString(
						ReqDiagramPreferenceConstants.ATTRIBUTE_DEFAULT_FOREGROUND_COLOR);
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
				ReqDiagramPreferenceConstants.ATTRIBUTE_DEFAULT_FONT);
		if (preferenceFont.length() != 0) {
			return Utils.getFont(new FontData(preferenceFont));
		}
		return null;

	}
	
	@Override
	protected void refreshHeaderLabel() {
		super.refreshHeaderLabel();

		AttributeFigure fig = (AttributeFigure) getFigure();
		Attribute a = (Attribute) Utils.getElement(getGraphNode());
		if (a.getType().equals(AttributeType.TEXTUAL_VALUE))
			fig.setImageFigure(new Image(null, AttributeFigure.class.getResourceAsStream("img/text.png")), PositionConstants.RIGHT);
		else if (a.getType().equals(AttributeType.NUMERICAL_VALUE))
			fig.setImageFigure(new Image(null, AttributeFigure.class.getResourceAsStream("img/number.png")), PositionConstants.RIGHT);
		else if (a.getType().equals(AttributeType.TEMPORAL_VALUE))
			fig.setImageFigure(new Image(null, AttributeFigure.class.getResourceAsStream("img/date.png")), PositionConstants.RIGHT);
		else
			fig.setImageFigure(new Image(null, AttributeFigure.class.getResourceAsStream("img/other.png")), PositionConstants.RIGHT);
		fig.repaint();
	}

}