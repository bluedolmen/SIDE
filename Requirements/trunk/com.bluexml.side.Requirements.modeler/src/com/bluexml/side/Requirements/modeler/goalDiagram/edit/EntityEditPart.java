/*******************************************************************************
 * No CopyrightText Defined in the configurator file.
 ******************************************************************************/
package com.bluexml.side.Requirements.modeler.goalDiagram.edit;

import org.eclipse.draw2d.IFigure;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.gef.EditPolicy;
import org.eclipse.gef.Request;
import org.eclipse.gef.RequestConstants;
import org.eclipse.gef.commands.Command;
import org.eclipse.jface.resource.JFaceResources;
import org.eclipse.jface.window.Window;
import org.eclipse.swt.SWT;
import org.eclipse.swt.graphics.Color;
import org.eclipse.swt.graphics.Font;
import org.eclipse.swt.graphics.FontData;
import org.eclipse.swt.widgets.Display;
import org.topcased.modeler.ModelerEditPolicyConstants;
import org.topcased.modeler.di.model.GraphElement;
import org.topcased.modeler.di.model.GraphNode;
import org.topcased.modeler.edit.EMFGraphNodeEditPart;
import org.topcased.modeler.edit.policies.LabelDirectEditPolicy;
import org.topcased.modeler.edit.policies.ResizableEditPolicy;
import org.topcased.modeler.edit.policies.RestoreEditPolicy;
import org.topcased.modeler.internal.ModelerPlugin;
import org.topcased.modeler.requests.RestoreConnectionsRequest;
import org.topcased.modeler.utils.Utils;

import com.bluexml.side.Requirements.modeler.goalDiagram.ReqConfiguration;
import com.bluexml.side.Requirements.modeler.goalDiagram.ReqEditPolicyConstants;
import com.bluexml.side.Requirements.modeler.goalDiagram.commands.EntityRestoreConnectionCommand;
import com.bluexml.side.Requirements.modeler.goalDiagram.commands.update.EntityUpdateCommand;
import com.bluexml.side.Requirements.modeler.goalDiagram.dialogs.EntityDialog;
import com.bluexml.side.Requirements.modeler.goalDiagram.figures.EntityFigure;
import com.bluexml.side.Requirements.modeler.goalDiagram.policies.EntityLayoutEditPolicy;
import com.bluexml.side.Requirements.modeler.goalDiagram.policies.PrivilegeGroupEdgeCreationEditPolicy;
import com.bluexml.side.Requirements.modeler.goalDiagram.policies.RelationShipEdgeCreationEditPolicy;
import com.bluexml.side.Requirements.modeler.goalDiagram.preferences.ReqDiagramPreferenceConstants;
import com.bluexml.side.requirements.Entity;

/**
 * The Entity object controller
 *
 * @generated
 */
public class EntityEditPart extends EMFGraphNodeEditPart {
	/**
	 * Constructor
	 *
	 * @param obj the graph node
	 * @generated
	 */
	public EntityEditPart(GraphNode obj) {
		super(obj);
	}

	/**
	 * Creates edit policies and associates these with roles
	 *
	 * @generated
	 */
	protected void createEditPolicies() {
		super.createEditPolicies();

		installEditPolicy(ReqEditPolicyConstants.RELATIONSHIP_EDITPOLICY,
				new RelationShipEdgeCreationEditPolicy());

		installEditPolicy(ReqEditPolicyConstants.PRIVILEGEGROUP_EDITPOLICY,
				new PrivilegeGroupEdgeCreationEditPolicy());

		installEditPolicy(ModelerEditPolicyConstants.RESTORE_EDITPOLICY,
				new RestoreEditPolicy() {
					protected Command getRestoreConnectionsCommand(
							RestoreConnectionsRequest request) {
						return new EntityRestoreConnectionCommand(getHost());
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
		installEditPolicy(EditPolicy.LAYOUT_ROLE, new EntityLayoutEditPolicy());
		installEditPolicy(EditPolicy.DIRECT_EDIT_ROLE,
				new LabelDirectEditPolicy());
	}

	/**
	 * @see org.eclipse.gef.editparts.AbstractGraphicalEditPart#createFigure()
	 * @_generated
	 */
	protected IFigure createFigure() {
		Entity entity = (Entity) Utils.getElement(getGraphNode());

		ReqConfiguration config = new ReqConfiguration();
		if (getGraphNode().getContained().size() > 0) {
			GraphNode attributesListNode = (GraphNode) getGraphNode()
					.getContained().get(0);
			EList attributesList = attributesListNode.getContained();
			while (attributesList.size() > 0)
				attributesList.remove(0);
			for (Object o : entity.getAttributes()) {
				GraphElement elt = config.getCreationUtils()
						.createGraphElement((EObject) o);
				if (elt != null)
					attributesList.add(elt);
			}
		}

		IFigure result = new EntityFigure();
		return result;
	}

	/**
	 * @see org.topcased.modeler.edit.GraphNodeEditPart#getPreferenceDefaultBackgroundColor()
	 * @generated
	 */
	protected Color getPreferenceDefaultBackgroundColor() {
		String backgroundColor = getPreferenceStore().getString(
				ReqDiagramPreferenceConstants.ENTITY_DEFAULT_BACKGROUND_COLOR);
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
				ReqDiagramPreferenceConstants.ENTITY_DEFAULT_FOREGROUND_COLOR);
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
				ReqDiagramPreferenceConstants.ENTITY_DEFAULT_FONT);
		if (preferenceFont.length() != 0) {
			return Utils.getFont(new FontData(preferenceFont));
		}
		return null;

	}

	@Override
	protected Font getDefaultFont() {
		FontData[] fData = Display.getDefault().getSystemFont().getFontData();
		fData[0].setStyle(SWT.BOLD);
		fData[0].setHeight(18);
		JFaceResources.getFontRegistry().put("BoldFont", fData);
		return JFaceResources.getFontRegistry().get("BoldFont");
	}

	@Override
	public void performRequest(Request request) {
		Entity e = (Entity) Utils.getElement(getGraphNode());

		if (request.getType() == RequestConstants.REQ_OPEN) {
			EntityDialog dialog = new EntityDialog(ModelerPlugin
					.getActiveWorkbenchShell(), e);
			if (dialog.open() == Window.OK) {
				EntityUpdateCommand command = new EntityUpdateCommand(e, dialog
						.getData());
				getViewer().getEditDomain().getCommandStack().execute(command);
				refresh();
			}
		} else {
			super.performRequest(request);
		}
	}

}