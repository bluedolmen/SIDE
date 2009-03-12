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
import org.eclipse.draw2d.PolygonDecoration;
import org.eclipse.draw2d.PolylineConnection;
import org.eclipse.draw2d.PolylineDecoration;
import org.eclipse.draw2d.RotatableDecoration;
import org.eclipse.draw2d.geometry.PointList;
import org.eclipse.gef.Request;
import org.eclipse.gef.RequestConstants;
import org.eclipse.jface.window.Window;
import org.topcased.draw2d.figures.Label;
import org.topcased.modeler.ModelerColorConstants;
import org.topcased.modeler.ModelerEditPolicyConstants;
import org.topcased.modeler.di.model.GraphEdge;
import org.topcased.modeler.edit.EMFGraphEdgeEditPart;
import org.topcased.modeler.figures.EdgeObjectOffsetEditableLabel;
import org.topcased.modeler.internal.ModelerPlugin;
import org.topcased.modeler.utils.Utils;

import com.bluexml.side.Class.modeler.diagram.CdEditPolicyConstants;
import com.bluexml.side.Class.modeler.diagram.commands.update.AssociationUpdateCommand;
import com.bluexml.side.Class.modeler.diagram.dialogs.AssociationEditDialog;
import com.bluexml.side.Class.modeler.diagram.figures.AssociationFigure;
import com.bluexml.side.Class.modeler.diagram.policies.isAssociationClassEdgeCreationEditPolicy;
import com.bluexml.side.Class.modeler.diagram.policies.isCommentedEdgeCreationEditPolicy;
import com.bluexml.side.Class.modeler.diagram.policies.isStereotypedEdgeCreationEditPolicy;
import com.bluexml.side.clazz.Association;
import com.bluexml.side.clazz.AssociationType;

/**
 * Association controller <br>
 * <!-- begin-user-doc --> <!-- end-user-doc -->
 * 
 * @_generated
 */
public class AssociationEditPart extends EMFGraphEdgeEditPart {

	private RotatableDecoration srcDecor;

	private RotatableDecoration targetDecor;

	/**
	 * Constructor <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @param model
	 *            the graph object
	 * @generated
	 */
	public AssociationEditPart(GraphEdge model) {
		super(model);
	}

	/**
	 * Create a decoration for the composition
	 * 
	 * @return the new decoration
	 */
	private PolygonDecoration createCompositionDecoration() {
		PolygonDecoration decoration = new PolygonDecoration();
		PointList decorationPointList = new PointList();
		decorationPointList.addPoint(0, 0);
		decorationPointList.addPoint(-1, 1);
		decorationPointList.addPoint(-2, 0);
		decorationPointList.addPoint(-1, -1);
		decoration.setTemplate(decorationPointList);
		decoration.setScale(10, 5);
		return decoration;
	}

	/**
	 * Create a decoration for the aggregation
	 * 
	 * @return the new decoration
	 */
	private PolygonDecoration createAggregationDecoration() {
		PolygonDecoration decoration = new PolygonDecoration();
		PointList decorationPointList = new PointList();
		decorationPointList.addPoint(0, 0);
		decorationPointList.addPoint(-1, 1);
		decorationPointList.addPoint(-2, 0);
		decorationPointList.addPoint(-1, -1);
		decoration.setBackgroundColor(ModelerColorConstants.white);
		decoration.setTemplate(decorationPointList);
		decoration.setScale(10, 5);
		return decoration;
	}

	/**
	 * Create a decoration for the navigeable association
	 * 
	 * @return the new decoration
	 */
	private PolylineDecoration createNavigableDecoration() {
		PolylineDecoration decoration = new PolylineDecoration();
		decoration.setScale(10, 5);
		return decoration;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @see org.eclipse.gef.editparts.AbstractEditPart#createEditPolicies()
	 * @generated
	 */
	protected void createEditPolicies() {
		super.createEditPolicies();

		installEditPolicy(CdEditPolicyConstants.ISCOMMENTED_EDITPOLICY,
				new isCommentedEdgeCreationEditPolicy());
		installEditPolicy(CdEditPolicyConstants.ISSTEREOTYPED_EDITPOLICY,
				new isStereotypedEdgeCreationEditPolicy());
		installEditPolicy(CdEditPolicyConstants.ISASSOCIATIONCLASS_EDITPOLICY,
				new isAssociationClassEdgeCreationEditPolicy());

		installEditPolicy(ModelerEditPolicyConstants.CHANGE_FONT_EDITPOLICY,
				null);

		installEditPolicy(
				ModelerEditPolicyConstants.CHANGE_FOREGROUND_COLOR_EDITPOLICY,
				null);
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @return the Figure
	 * @generated NOT
	 */
	protected IFigure createFigure() {
		return new AssociationFigure();
	}

	/**
	 * Handle the double-click to edit the association
	 * 
	 * @see org.eclipse.gef.EditPart#performRequest(org.eclipse.gef.Request)
	 */
	public void performRequest(Request request) {
		Association association = (Association) Utils
				.getElement(getGraphEdge());

		if (request.getType() == RequestConstants.REQ_OPEN) {

			AssociationEditDialog associationDlg = new AssociationEditDialog(
					association, ModelerPlugin.getActiveWorkbenchShell());
			if (associationDlg.open() == Window.OK) {
				AssociationUpdateCommand command = new AssociationUpdateCommand(
						association, associationDlg.getAssociationData());
				getViewer().getEditDomain().getCommandStack().execute(command);
				refreshEdgeObjects();
			}
		} else {
			super.performRequest(request);
		}
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated NOT
	 */
	protected void refreshEdgeObjects() {
		super.refreshEdgeObjects();

		// Check whether the association has its two Properties set
		if (((GraphEdge) getModel()).getAnchor().size() != 0) {
			updateSourceDecoration();
			updateTargetDecoration();
			updateSrcCountLabel();
			updateSrcNameLabel();
			updateTargetCountLabel();
			updateTargetNameLabel();
		}

		updateMiddleNameLabel();
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc --> Update the srcName Label
	 * 
	 * @generated NOT
	 */
	private void updateSrcNameLabel() {
		Label srcName = (Label) ((AssociationFigure) getFigure())
				.getsrcNameEdgeObjectFigure();

		Association association = (Association) Utils
				.getElement(getGraphEdge());
		srcName.setText(association.getRoleSrc());
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc --> Update the srcCount Label
	 * 
	 * @generated NOT
	 */
	private void updateSrcCountLabel() {
		Label srcCount = (Label) ((AssociationFigure) getFigure())
				.getsrcCountEdgeObjectFigure();

		Association association = (Association) Utils
				.getElement(getGraphEdge());
		if (association != null) {
			String lowerBound = association.getMinSRC();
			String upperBound = association.getMaxSRC();

			if (upperBound == null) {
				upperBound = new String("0");
			}

			if (lowerBound == null) {
				lowerBound = new String("0");
			}

			if (upperBound.equals("-1")) {
				upperBound = "*";
			}

			if (lowerBound.equals(upperBound)) {
				if (!(lowerBound.equals("1")))
					srcCount.setText(lowerBound);
				else
					srcCount.setText("");
			} else {
				srcCount.setText(lowerBound + ".." + upperBound);
			}
		}
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc --> Update the targetName Label
	 * 
	 * @generated NOT
	 */
	private void updateTargetNameLabel() {
		Label targetName = (Label) ((AssociationFigure) getFigure())
				.gettargetNameEdgeObjectFigure();

		Association association = (Association) Utils
				.getElement(getGraphEdge());

		targetName.setText(association.getRoleTarget());
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc --> Update the targetCount
	 * Label
	 * 
	 * @generated NOT
	 */
	private void updateTargetCountLabel() {
		Label targetCount = (Label) ((AssociationFigure) getFigure())
				.gettargetCountEdgeObjectFigure();

		Association association = (Association) Utils
				.getElement(getGraphEdge());
		if (association != null) {

			String lowerBound = association.getMinTARGET();
			String upperBound = association.getMaxTARGET();

			if (upperBound == null) {
				upperBound = new String("0");
			}

			if (lowerBound == null) {
				lowerBound = new String("0");
			}

			if (upperBound.equals("-1")) {
				upperBound = "*";
			}

			if (lowerBound.equals(upperBound)) {
				if (!(lowerBound.equals("1")))
					targetCount.setText(lowerBound);
				else
					targetCount.setText("");
			} else {
				targetCount.setText(lowerBound + ".." + upperBound);
			}
		}

	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc --> Update the middleName Label
	 * 
	 * @generated NOT
	 */
	private void updateMiddleNameLabel() {
		Association association = (Association) Utils
				.getElement(getGraphEdge());

		AssociationFigure fig = ((AssociationFigure) getFigure());
		EdgeObjectOffsetEditableLabel label = (EdgeObjectOffsetEditableLabel) fig
				.getmiddleNameEdgeObjectFigure();
		label.setText(association.getName());
	}

	/**
	 * Update the source decoration
	 */
	private void updateSourceDecoration() {
		Association association = (Association) Utils
				.getElement(getGraphEdge());

		boolean isNavigable = association.isIsNavigableSRC();

		if (!(isNavigable)) {
			if (association.getAssociationType() == AssociationType.COMPOSITION) {
				srcDecor = createCompositionDecoration();
			} else if (association.getAssociationType() == AssociationType.AGGREGATION) {
				srcDecor = createAggregationDecoration();
			} else {
				srcDecor = null;
			}
		} else {
			srcDecor = createNavigableDecoration();
		}
		((PolylineConnection) figure).setSourceDecoration(srcDecor);
	}

	/**
	 * Update the target decoration
	 */
	private void updateTargetDecoration() {
		Association association = (Association) Utils
				.getElement(getGraphEdge());

		boolean isNavigable = association.isIsNavigableTARGET();

		if (!isNavigable) {
			if (association.getAssociationType() == AssociationType.COMPOSITION) {
				targetDecor = createCompositionDecoration();
			} else if (association.getAssociationType() == AssociationType.AGGREGATION) {
				targetDecor = createAggregationDecoration();
			} else {
				targetDecor = null;
			}
		} else {
			targetDecor = createNavigableDecoration();
		}
		((PolylineConnection) figure).setTargetDecoration(targetDecor);
	}
}