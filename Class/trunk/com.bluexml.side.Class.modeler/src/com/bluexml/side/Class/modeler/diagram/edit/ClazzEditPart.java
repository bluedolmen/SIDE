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
package com.bluexml.side.Class.modeler.diagram.edit;

import org.eclipse.draw2d.IFigure;
import org.eclipse.gef.EditPolicy;
import org.eclipse.gef.commands.Command;
import org.eclipse.swt.graphics.Color;
import org.eclipse.swt.graphics.Font;
import org.eclipse.swt.graphics.FontData;
import org.topcased.modeler.ModelerEditPolicyConstants;
import org.topcased.modeler.di.model.GraphNode;
import org.topcased.modeler.edit.EMFGraphNodeEditPart;
import org.topcased.modeler.edit.policies.LabelDirectEditPolicy;
import org.topcased.modeler.edit.policies.ResizableEditPolicy;
import org.topcased.modeler.edit.policies.RestoreEditPolicy;
import org.topcased.modeler.requests.RestoreConnectionsRequest;
import org.topcased.modeler.utils.Utils;

import com.bluexml.side.Class.modeler.diagram.CdEditPolicyConstants;
import com.bluexml.side.Class.modeler.diagram.commands.ClazzRestoreConnectionCommand;
import com.bluexml.side.Class.modeler.diagram.figures.ClazzFigure;
import com.bluexml.side.Class.modeler.diagram.policies.AssociationEdgeCreationEditPolicy;
import com.bluexml.side.Class.modeler.diagram.policies.ClazzLayoutEditPolicy;
import com.bluexml.side.Class.modeler.diagram.policies.GeneralizationEdgeCreationEditPolicy;
import com.bluexml.side.Class.modeler.diagram.policies.hasViewEdgeCreationEditPolicy;
import com.bluexml.side.Class.modeler.diagram.policies.includeEdgeCreationEditPolicy;
import com.bluexml.side.Class.modeler.diagram.policies.isAssociationClassEdgeCreationEditPolicy;
import com.bluexml.side.Class.modeler.diagram.policies.isCommentedEdgeCreationEditPolicy;
import com.bluexml.side.Class.modeler.diagram.policies.isStereotypedEdgeCreationEditPolicy;
import com.bluexml.side.Class.modeler.diagram.preferences.CdDiagramPreferenceConstants;

/**
 * The Clazz object controller
 *
 * @generated
 */
public class ClazzEditPart extends EMFGraphNodeEditPart {
	/**
	 * Constructor
	 *
	 * @param obj the graph node
	 * @generated
	 */
	public ClazzEditPart(GraphNode obj) {
		super(obj);
	}

	/**
	 * Creates edit policies and associates these with roles
	 *
	 * @generated
	 */
	protected void createEditPolicies() {
		super.createEditPolicies();

		installEditPolicy(CdEditPolicyConstants.ASSOCIATION_EDITPOLICY, new AssociationEdgeCreationEditPolicy());

		installEditPolicy(CdEditPolicyConstants.ISCOMMENTED_EDITPOLICY, new isCommentedEdgeCreationEditPolicy());

		installEditPolicy(CdEditPolicyConstants.ISSTEREOTYPED_EDITPOLICY, new isStereotypedEdgeCreationEditPolicy());

		installEditPolicy(CdEditPolicyConstants.ISASSOCIATIONCLASS_EDITPOLICY, new isAssociationClassEdgeCreationEditPolicy());

		installEditPolicy(CdEditPolicyConstants.INCLUDE_EDITPOLICY, new includeEdgeCreationEditPolicy());

		installEditPolicy(CdEditPolicyConstants.HASVIEW_EDITPOLICY, new hasViewEdgeCreationEditPolicy());

		installEditPolicy(CdEditPolicyConstants.GENERALIZATION_EDITPOLICY, new GeneralizationEdgeCreationEditPolicy());

		installEditPolicy(ModelerEditPolicyConstants.RESTORE_EDITPOLICY, new RestoreEditPolicy() {
			protected Command getRestoreConnectionsCommand(RestoreConnectionsRequest request) {
				return new ClazzRestoreConnectionCommand(getHost());
			}
		});

		installEditPolicy(ModelerEditPolicyConstants.RESIZABLE_EDITPOLICY, new ResizableEditPolicy());

		installEditPolicy(ModelerEditPolicyConstants.CHANGE_BACKGROUND_COLOR_EDITPOLICY, null);
		installEditPolicy(ModelerEditPolicyConstants.CHANGE_FOREGROUND_COLOR_EDITPOLICY, null);
		installEditPolicy(EditPolicy.LAYOUT_ROLE, new ClazzLayoutEditPolicy());
		installEditPolicy(EditPolicy.DIRECT_EDIT_ROLE, new LabelDirectEditPolicy());
	}

	/**
	 * @see org.eclipse.gef.editparts.AbstractGraphicalEditPart#createFigure()
	 * @generated
	 */
	protected IFigure createFigure() {

		return new ClazzFigure();
	}

	/**
	 * @see org.topcased.modeler.edit.GraphNodeEditPart#getPreferenceDefaultBackgroundColor()
	 * @generated
	 */
	protected Color getPreferenceDefaultBackgroundColor() {
		String backgroundColor = getPreferenceStore().getString(CdDiagramPreferenceConstants.CLAZZ_DEFAULT_BACKGROUND_COLOR);
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
		String foregroundColor = getPreferenceStore().getString(CdDiagramPreferenceConstants.CLAZZ_DEFAULT_FOREGROUND_COLOR);
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
		String preferenceFont = getPreferenceStore().getString(CdDiagramPreferenceConstants.CLAZZ_DEFAULT_FONT);
		if (preferenceFont.length() != 0) {
			return Utils.getFont(new FontData(preferenceFont));
		}
		return null;

	}

}