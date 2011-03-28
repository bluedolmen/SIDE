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
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.gef.EditPolicy;
import org.eclipse.gef.Request;
import org.eclipse.gef.RequestConstants;
import org.eclipse.gef.commands.Command;
import org.eclipse.jface.window.Window;
import org.eclipse.swt.graphics.Color;
import org.eclipse.swt.graphics.Font;
import org.eclipse.swt.graphics.FontData;
import org.topcased.draw2d.figures.ComposedLabel;
import org.topcased.modeler.ModelerColorConstants;
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

import com.bluexml.side.Class.modeler.diagram.CdConfiguration;
import com.bluexml.side.Class.modeler.diagram.CdEditPolicyConstants;
import com.bluexml.side.Class.modeler.diagram.commands.AspectRestoreConnectionCommand;
import com.bluexml.side.Class.modeler.diagram.commands.update.AspectUpdateCommand;
import com.bluexml.side.Class.modeler.diagram.dialogs.AspectEditDialog;
import com.bluexml.side.Class.modeler.diagram.figures.AspectFigure;
import com.bluexml.side.Class.modeler.diagram.policies.AspectLayoutEditPolicy;
import com.bluexml.side.Class.modeler.diagram.policies.AssociationEdgeCreationEditPolicy;
import com.bluexml.side.Class.modeler.diagram.policies.GeneralizationEdgeCreationEditPolicy;
import com.bluexml.side.Class.modeler.diagram.policies.hasAspectEdgeCreationEditPolicy;
import com.bluexml.side.Class.modeler.diagram.policies.includeEdgeCreationEditPolicy;
import com.bluexml.side.Class.modeler.diagram.policies.isAssociationClassEdgeCreationEditPolicy;
import com.bluexml.side.Class.modeler.diagram.preferences.CdDiagramPreferenceConstants;
import com.bluexml.side.clazz.Aspect;

/**
 * The Aspect object controller
 * 
 * @generated
 */
public class AspectEditPart extends EMFGraphNodeEditPart {
	/**
	 * Constructor
	 * 
	 * @param obj
	 *            the graph node
	 * @generated
	 */
	public AspectEditPart(GraphNode obj) {
		super(obj);
	}

	/**
	 * Creates edit policies and associates these with roles
	 * 
	 * @generated
	 */
	protected void createEditPolicies() {
		super.createEditPolicies();

		installEditPolicy(CdEditPolicyConstants.INCLUDE_EDITPOLICY, new includeEdgeCreationEditPolicy());

		installEditPolicy(CdEditPolicyConstants.HASASPECT_EDITPOLICY, new hasAspectEdgeCreationEditPolicy());

		installEditPolicy(CdEditPolicyConstants.ASSOCIATION_EDITPOLICY, new AssociationEdgeCreationEditPolicy());

		installEditPolicy(CdEditPolicyConstants.ISASSOCIATIONCLASS_EDITPOLICY, new isAssociationClassEdgeCreationEditPolicy());

		installEditPolicy(CdEditPolicyConstants.GENERALIZATION_EDITPOLICY, new GeneralizationEdgeCreationEditPolicy());

		installEditPolicy(ModelerEditPolicyConstants.RESTORE_EDITPOLICY, new RestoreEditPolicy() {
			protected Command getRestoreConnectionsCommand(RestoreConnectionsRequest request) {
				return new AspectRestoreConnectionCommand(getHost());
			}
		});

		installEditPolicy(ModelerEditPolicyConstants.RESIZABLE_EDITPOLICY, new ResizableEditPolicy());

		installEditPolicy(ModelerEditPolicyConstants.CHANGE_BACKGROUND_COLOR_EDITPOLICY, null);
		installEditPolicy(ModelerEditPolicyConstants.CHANGE_FOREGROUND_COLOR_EDITPOLICY, null);
		installEditPolicy(EditPolicy.LAYOUT_ROLE, new AspectLayoutEditPolicy());
		installEditPolicy(EditPolicy.DIRECT_EDIT_ROLE, new LabelDirectEditPolicy());
	}

	/**
	 * @see org.eclipse.gef.editparts.AbstractGraphicalEditPart#createFigure()
	 * @_generated
	 */
	protected IFigure createFigure() {
		Aspect object = (Aspect) Utils.getElement(getGraphNode());

		CdConfiguration config = new CdConfiguration();

		GraphNode attributesListNode = (GraphNode) getGraphNode().getContained().get(0);
		EList attributesList = attributesListNode.getContained();
		while (attributesList.size() > 0)
			attributesList.remove(0);
		for (Object o : object.getAttributes()) {
			GraphElement elt = config.getCreationUtils().createGraphElement((EObject) o);
			attributesList.add(elt);
		}

		IFigure result = new AspectFigure();

		return result;
	}

	/**
	 * @see org.topcased.modeler.edit.GraphNodeEditPart#getPreferenceDefaultBackgroundColor()
	 * @generated
	 */
	protected Color getPreferenceDefaultBackgroundColor() {
		String backgroundColor = getPreferenceStore().getString(CdDiagramPreferenceConstants.ASPECT_DEFAULT_BACKGROUND_COLOR);
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
		String foregroundColor = getPreferenceStore().getString(CdDiagramPreferenceConstants.ASPECT_DEFAULT_FOREGROUND_COLOR);
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
		String preferenceFont = getPreferenceStore().getString(CdDiagramPreferenceConstants.ASPECT_DEFAULT_FONT);
		if (preferenceFont.length() != 0) {
			return Utils.getFont(new FontData(preferenceFont));
		}
		return null;

	}

	@Override
	protected Color getDefaultBackgroundColor() {
		return ModelerColorConstants.classlightGreen;
	}

	@Override
	protected void refreshHeaderLabel() {
		IFigure ifig = getFigure();
		if (ifig instanceof AspectFigure) {
			AspectFigure fig = (AspectFigure) ifig;
			ComposedLabel lbl = (ComposedLabel) fig.getLabel();
			Aspect object = (Aspect) Utils.getElement(getGraphNode());

			if (object.getName() != null) {
				lbl.setMain(object.getName());
			}

			lbl.setPrefix("<<Aspect>>");
		} else
			super.refreshHeaderLabel();
	}

	/**
	 * handle the edition of the aspect
	 * 
	 * @see org.eclipse.gef.EditPart#performRequest(org.eclipse.gef.Request)
	 */
	@Override
	public void performRequest(Request request) {
		if (request.getType() == RequestConstants.REQ_OPEN) {
			Aspect object = (Aspect) Utils.getElement(getGraphNode());

			AspectEditDialog dlg = new AspectEditDialog(object, ModelerPlugin.getActiveWorkbenchShell());
			if (dlg.open() == Window.OK) {
				AspectUpdateCommand command = new AspectUpdateCommand(object, dlg.getData());
				getViewer().getEditDomain().getCommandStack().execute(command);

				refresh();
			}
		} else {
			super.performRequest(request);
		}
	}

}