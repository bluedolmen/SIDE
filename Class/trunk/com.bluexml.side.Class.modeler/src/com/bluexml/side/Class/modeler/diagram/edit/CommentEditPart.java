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
import org.eclipse.gef.Request;
import org.eclipse.gef.RequestConstants;
import org.eclipse.gef.commands.Command;
import org.eclipse.gef.tools.DirectEditManager;
import org.eclipse.jface.viewers.CellEditor;
import org.eclipse.jface.viewers.TextCellEditor;
import org.eclipse.jface.window.Window;
import org.eclipse.swt.SWT;
import org.eclipse.swt.graphics.Color;
import org.eclipse.swt.widgets.Composite;
import org.topcased.draw2d.figures.ComposedLabel;
import org.topcased.draw2d.figures.ILabel;
import org.topcased.modeler.ModelerColorConstants;
import org.topcased.modeler.ModelerEditPolicyConstants;
import org.topcased.modeler.di.model.GraphNode;
import org.topcased.modeler.edit.EMFGraphNodeEditPart;
import org.topcased.modeler.edit.LabelCellEditorLocator;
import org.topcased.modeler.edit.ModelerLabelDirectEditManager;
import org.topcased.modeler.edit.policies.LabelDirectEditPolicy;
import org.topcased.modeler.edit.policies.ResizableEditPolicy;
import org.topcased.modeler.edit.policies.RestoreEditPolicy;
import org.topcased.modeler.internal.ModelerPlugin;
import org.topcased.modeler.requests.RestoreConnectionsRequest;
import org.topcased.modeler.utils.Utils;

import com.bluexml.side.Class.modeler.diagram.CdEditPolicyConstants;
import com.bluexml.side.Class.modeler.diagram.commands.CommentRestoreConnectionCommand;
import com.bluexml.side.Class.modeler.diagram.commands.update.CommentUpdateCommand;
import com.bluexml.side.Class.modeler.diagram.dialogs.CommentEditDialog;
import com.bluexml.side.Class.modeler.diagram.figures.CommentFigure;
import com.bluexml.side.Class.modeler.diagram.policies.isCommentedEdgeCreationEditPolicy;
import com.bluexml.side.Class.modeler.diagram.policies.isStereotypedEdgeCreationEditPolicy;
import com.bluexml.side.Class.modeler.tools.ClazzNotation;
import com.bluexml.side.common.Comment;
import com.bluexml.side.common.Stereotype;

/**
 * The Comment object controller
 * 
 * @generated
 */
public class CommentEditPart extends EMFGraphNodeEditPart {
	/**
	 * Constructor
	 * 
	 * @param obj
	 *            the graph node
	 * @generated
	 */
	public CommentEditPart(GraphNode obj) {
		super(obj);
	}

	/**
	 * Creates edit policies and associates these with roles
	 * 
	 * @generated
	 */
	protected void createEditPolicies() {
		super.createEditPolicies();

		installEditPolicy(CdEditPolicyConstants.ISCOMMENTED_EDITPOLICY,
				new isCommentedEdgeCreationEditPolicy());

		installEditPolicy(CdEditPolicyConstants.ISSTEREOTYPED_EDITPOLICY,
				new isStereotypedEdgeCreationEditPolicy());

		installEditPolicy(ModelerEditPolicyConstants.RESTORE_EDITPOLICY,
				new RestoreEditPolicy() {
					protected Command getRestoreConnectionsCommand(
							RestoreConnectionsRequest request) {
						return new CommentRestoreConnectionCommand(getHost());
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

		return new CommentFigure();
	}

	protected void performDirectEdit() {
		DirectEditManager manager = getDirectEditManager();
		if (manager == null) {
			ILabel label = ((CommentFigure) getFigure()).getLabel();
			manager = new ModelerLabelDirectEditManager(this,
					TextCellEditor.class, new LabelCellEditorLocator(
							getFigure()), label) {
				protected CellEditor createCellEditorOn(Composite composite) {
					return new TextCellEditor(composite, SWT.MULTI | SWT.WRAP);
				}
			};

		}
		manager.show();
	}

	@Override
	protected Color getDefaultBackgroundColor() {
		Comment com = (Comment) Utils.getElement(getGraphNode());
		if (com == null)
			return ModelerColorConstants.classlightGreen;

		boolean findedView = false;
		boolean findedLayout = false;
		for (Object o : com.getStereotypes()) {
			Stereotype s = (Stereotype) o;
			findedView = s.getName().equalsIgnoreCase("View");
			findedLayout = s.getName().equalsIgnoreCase("Layout");
			if (findedView || findedLayout)
				break;
		}
		if (findedView) {
			return ModelerColorConstants.lightBrown;
		} else {
			if (findedLayout)
				return ModelerColorConstants.lightPurple;
			else
				return ModelerColorConstants.classlightGreen;
		}
	}

	@Override
	protected void refreshHeaderLabel() {
		CommentFigure fig = (CommentFigure) getFigure();
		Comment com = (Comment) Utils.getElement(getGraphNode());
		((ComposedLabel) fig.getLabel()).setPrefix(ClazzNotation
				.getStereotypesNotation(com));
		((ComposedLabel) fig.getLabel()).setMain(com.getValue());
	}

	@Override
	public void performRequest(Request request) {
		if (request.getType() == RequestConstants.REQ_OPEN) {
			Comment comment = (Comment) Utils.getElement(getGraphNode());

			CommentEditDialog dlg = new CommentEditDialog(comment,
					ModelerPlugin.getActiveWorkbenchShell());
			if (dlg.open() == Window.OK) {
				CommentUpdateCommand command = new CommentUpdateCommand(
						comment, dlg.getData());
				getViewer().getEditDomain().getCommandStack().execute(command);

				refresh();
			}
		} else {
			super.performRequest(request);
		}
	}
}