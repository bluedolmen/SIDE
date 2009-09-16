/*******************************************************************************
 * No CopyrightText Defined in the configurator file.
 ******************************************************************************/
package com.bluexml.side.Requirements.modeler.processDiagram.edit;

import org.eclipse.draw2d.IFigure;
import org.eclipse.swt.graphics.Color;
import org.eclipse.swt.graphics.Font;
import org.eclipse.swt.graphics.FontData;
import org.topcased.modeler.ModelerEditPolicyConstants;
import org.topcased.modeler.di.model.EdgeObject;
import org.topcased.modeler.di.model.GraphEdge;
import org.topcased.modeler.edit.EMFGraphEdgeEditPart;
import org.topcased.modeler.edit.policies.EdgeObjectOffsetEditPolicy;
import org.topcased.modeler.figures.EdgeObjectOffsetEditableLabel;
import org.topcased.modeler.figures.IEdgeObjectFigure;
import org.topcased.modeler.utils.Utils;

import com.bluexml.side.Requirements.modeler.processDiagram.ProEdgeObjectConstants;
import com.bluexml.side.Requirements.modeler.processDiagram.figures.RelationShipFigure;
import com.bluexml.side.Requirements.modeler.processDiagram.preferences.ProDiagramPreferenceConstants;
import com.bluexml.side.requirements.RelationShip;

/**
 * RelationShip controller
 *
 * @generated
 */
public class RelationShipEditPart extends EMFGraphEdgeEditPart {

	/**
	 * Constructor
	 *
	 * @param model the graph object
	 * @generated
	 */
	public RelationShipEditPart(GraphEdge model) {
		super(model);
	}

	/**
	 * @see org.eclipse.gef.editparts.AbstractEditPart#createEditPolicies()
	 * @generated
	 */
	protected void createEditPolicies() {
		super.createEditPolicies();

		installEditPolicy(ModelerEditPolicyConstants.CHANGE_FONT_EDITPOLICY,
				null);

		installEditPolicy(
				ModelerEditPolicyConstants.CHANGE_FOREGROUND_COLOR_EDITPOLICY,
				null);
		
		installEditPolicy(ModelerEditPolicyConstants.EDGE_OBJECTS_OFFSET_EDITPOLICY, new EdgeObjectOffsetEditPolicy());
	}

	/**
	 * @return the Figure
	 * @generated
	 */
	protected IFigure createFigure() {
		RelationShipFigure connection = new RelationShipFigure();

		return connection;
	}

	/**
	 * @see org.topcased.modeler.edit.GraphEdgeEditPart#getPreferenceDefaultRouter()
	 * 
	 * @generated
	 */
	protected String getPreferenceDefaultRouter() {
		return getPreferenceStore().getString(
				ProDiagramPreferenceConstants.RELATIONSHIP_EDGE_DEFAULT_ROUTER);
	}

	/**
	 * @see org.topcased.modeler.edit.GraphEdgeEditPart#getPreferenceDefaultForegroundColor()
	 * 
	 * @generated
	 */
	protected Color getPreferenceDefaultForegroundColor() {
		String preferenceForeground = getPreferenceStore()
				.getString(
						ProDiagramPreferenceConstants.RELATIONSHIP_EDGE_DEFAULT_FOREGROUND_COLOR);
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
				ProDiagramPreferenceConstants.RELATIONSHIP_EDGE_DEFAULT_FONT);
		if (preferenceFont.length() != 0) {
			return Utils.getFont(new FontData(preferenceFont));
		}
		return null;
	}
	
	@Override
	public IEdgeObjectFigure getEdgeObjectFigure(EdgeObject edgeObject) {
		if (ProEdgeObjectConstants.MIDDLENAME_EDGE_OBJECT_ID.equals(edgeObject.getId()))
		{
			return ((RelationShipFigure) getFigure()).getmiddleNameEdgeObjectFigure();
		}
		return null;
	}
	
	protected void refreshEdgeObjects() {
		super.refreshEdgeObjects();
		
		RelationShip relationship = (RelationShip) Utils.getElement(getGraphEdge());
		RelationShipFigure fig = ((RelationShipFigure) getFigure());
		EdgeObjectOffsetEditableLabel label = (EdgeObjectOffsetEditableLabel) fig.getmiddleNameEdgeObjectFigure();
		label.setText(relationship.getName());
	}
}