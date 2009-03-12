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

import java.util.List;

import org.eclipse.draw2d.IFigure;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.gef.EditPolicy;
import org.eclipse.gef.Request;
import org.eclipse.gef.RequestConstants;
import org.eclipse.gef.commands.Command;
import org.eclipse.gef.editparts.AbstractEditPart;
import org.eclipse.jface.resource.JFaceResources;
import org.eclipse.jface.window.Window;
import org.eclipse.swt.SWT;
import org.eclipse.swt.graphics.Color;
import org.eclipse.swt.graphics.Font;
import org.eclipse.swt.graphics.FontData;
import org.eclipse.swt.widgets.Display;
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
import com.bluexml.side.Class.modeler.diagram.commands.EnumerationRestoreConnectionCommand;
import com.bluexml.side.Class.modeler.diagram.commands.update.EnumerationUpdateCommand;
import com.bluexml.side.Class.modeler.diagram.dialogs.EnumerationEditDialog;
import com.bluexml.side.Class.modeler.diagram.figures.EnumerationFigure;
import com.bluexml.side.Class.modeler.diagram.policies.EnumerationLayoutEditPolicy;
import com.bluexml.side.clazz.Enumeration;

/**
 * The Enumeration object controller
 * 
 * @generated
 */
public class EnumerationEditPart extends EMFGraphNodeEditPart {
	/**
	 * Constructor
	 * 
	 * @param obj
	 *            the graph node
	 * @generated
	 */
	public EnumerationEditPart(GraphNode obj) {
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
						return new EnumerationRestoreConnectionCommand(
								getHost());
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
		installEditPolicy(EditPolicy.LAYOUT_ROLE,
				new EnumerationLayoutEditPolicy());
		installEditPolicy(EditPolicy.DIRECT_EDIT_ROLE,
				new LabelDirectEditPolicy());
	}

	/**
	 * @see org.eclipse.gef.editparts.AbstractGraphicalEditPart#createFigure()
	 * @generated
	 */
	protected IFigure createFigure() {

		return new EnumerationFigure();
	}

	@Override
	public void performRequest(Request request) {
		if (request.getType() == RequestConstants.REQ_OPEN) {
			Enumeration object = (Enumeration) Utils.getElement(getGraphNode());

			EnumerationEditDialog dlg = new EnumerationEditDialog(object,
					ModelerPlugin.getActiveWorkbenchShell());
			if (dlg.open() == Window.OK) {
				EnumerationUpdateCommand command = new EnumerationUpdateCommand(
						object, dlg.getData());
				getViewer().getEditDomain().getCommandStack().execute(command);

				CdConfiguration config = new CdConfiguration();
				GraphNode literalsListNode = (GraphNode) getGraphNode()
						.getContained().get(0);
				EList literals = literalsListNode.getContained();
				while (literals.size() > 0)
					literals.remove(0);
				for (Object o : object.getLiterals()) {
					GraphElement elt = config.getCreationUtils()
							.createGraphElement((EObject) o);
					literals.add(elt);
				}
			}
		} else {
			super.performRequest(request);
		}
	}

	@Override
	protected Color getDefaultBackgroundColor() {
		return ModelerColorConstants.lightOrange;
	}

	@Override
	public void refresh() {
		List childrens = getChildren();
		for (Object child : childrens) {
			for (Object child2 : ((AbstractEditPart) child).getChildren()) {
				if (child2 instanceof AbstractEditPart) {
					AbstractEditPart edit_child = (AbstractEditPart) child2;
					edit_child.refresh();
				}
			}
		}
		super.refresh();
	}

	@Override
	protected Font getDefaultFont() {
		FontData[] fData = Display.getDefault().getSystemFont().getFontData();
		fData[0].setStyle(SWT.BOLD);
		JFaceResources.getFontRegistry().put("ItalicFont", fData);
		return JFaceResources.getFontRegistry().get("ItalicFont");
	}

}