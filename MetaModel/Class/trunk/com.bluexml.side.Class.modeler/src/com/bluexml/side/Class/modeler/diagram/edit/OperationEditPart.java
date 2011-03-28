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
import org.topcased.draw2d.figures.Label;
import org.topcased.modeler.ModelerColorConstants;
import org.topcased.modeler.ModelerEditPolicyConstants;
import org.topcased.modeler.di.model.GraphNode;
import org.topcased.modeler.edit.EMFGraphNodeEditPart;
import org.topcased.modeler.edit.policies.ResizableEditPolicy;
import org.topcased.modeler.edit.policies.RestoreEditPolicy;
import org.topcased.modeler.internal.ModelerPlugin;
import org.topcased.modeler.requests.RestoreConnectionsRequest;
import org.topcased.modeler.utils.Utils;

import com.bluexml.side.Class.modeler.diagram.commands.OperationRestoreConnectionCommand;
import com.bluexml.side.Class.modeler.diagram.commands.update.OperationUpdateCommand;
import com.bluexml.side.Class.modeler.diagram.dialogs.OperationEditDialog;
import com.bluexml.side.Class.modeler.diagram.figures.OperationFigure;
import com.bluexml.side.Class.modeler.diagram.preferences.CdDiagramPreferenceConstants;
import com.bluexml.side.clazz.Clazz;
import com.bluexml.side.common.Operation;
import com.bluexml.side.common.Parameter;
import com.bluexml.side.common.Stereotype;
import com.bluexml.side.common.Visibility;

/**
 * The Operation object controller
 * 
 * @generated
 */
public class OperationEditPart extends EMFGraphNodeEditPart {

	private Operation op;

	public OperationEditPart(GraphNode obj, Operation op) {
		super(obj);
		this.op = op;
	}

	/**
	 * Constructor
	 * 
	 * @param obj
	 *            the graph node
	 * @generated
	 */
	public OperationEditPart(GraphNode obj) {
		super(obj);
	}

	/**
	 * Creates edit policies and associates these with roles
	 * 
	 * @generated
	 */
	protected void createEditPolicies() {
		super.createEditPolicies();

		installEditPolicy(ModelerEditPolicyConstants.RESTORE_EDITPOLICY, new RestoreEditPolicy() {
			protected Command getRestoreConnectionsCommand(RestoreConnectionsRequest request) {
				return new OperationRestoreConnectionCommand(getHost());
			}
		});

		installEditPolicy(ModelerEditPolicyConstants.RESIZABLE_EDITPOLICY, new ResizableEditPolicy());

		installEditPolicy(ModelerEditPolicyConstants.CHANGE_BACKGROUND_COLOR_EDITPOLICY, null);
		installEditPolicy(ModelerEditPolicyConstants.CHANGE_FOREGROUND_COLOR_EDITPOLICY, null);
	}

	/**
	 * @see org.eclipse.gef.editparts.AbstractGraphicalEditPart#createFigure()
	 * @generated
	 */
	protected IFigure createFigure() {

		return new OperationFigure();
	}

	/**
	 * @see org.topcased.modeler.edit.GraphNodeEditPart#getPreferenceDefaultBackgroundColor()
	 * @generated
	 */
	protected Color getPreferenceDefaultBackgroundColor() {
		String backgroundColor = getPreferenceStore().getString(CdDiagramPreferenceConstants.OPERATION_DEFAULT_BACKGROUND_COLOR);
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
		String foregroundColor = getPreferenceStore().getString(CdDiagramPreferenceConstants.OPERATION_DEFAULT_FOREGROUND_COLOR);
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
		String preferenceFont = getPreferenceStore().getString(CdDiagramPreferenceConstants.OPERATION_DEFAULT_FONT);
		if (preferenceFont.length() != 0) {
			return Utils.getFont(new FontData(preferenceFont));
		}
		return null;

	}

	@Override
	protected Font getDefaultFont() {
		FontData[] fData = Display.getDefault().getSystemFont().getFontData();
		fData[0].setStyle(SWT.BOLD);
		JFaceResources.getFontRegistry().put("ItalicFont", fData);
		return JFaceResources.getFontRegistry().get("ItalicFont");
	}

	@Override
	protected Color getDefaultBackgroundColor() {
		Clazz clazz = (Clazz) Utils.getElement(getGraphNode()).eContainer();
		if (clazz == null)
			return ModelerColorConstants.classBlue;

		boolean findedNomenclature = false;
		boolean findedUI = false;
		for (Object o : clazz.getStereotypes()) {
			Stereotype s = (Stereotype) o;
			findedNomenclature = s.getName().equalsIgnoreCase("Nomenclature");
			findedUI = s.getName().equalsIgnoreCase("UI");
			if (findedNomenclature || findedUI)
				break;
		}
		if (findedNomenclature) {
			return ModelerColorConstants.lightYellow;
		} else {
			if (findedUI)
				return ModelerColorConstants.classBlue;
			else
				return ModelerColorConstants.classlightGreen;
		}
	}

	@Override
	public void performRequest(Request request) {
		Operation operation = (Operation) Utils.getElement(getGraphNode());

		if (request.getType() == RequestConstants.REQ_OPEN) {

			OperationEditDialog operationDlg = new OperationEditDialog(operation, ModelerPlugin.getActiveWorkbenchShell());
			if (operationDlg.open() == Window.OK) {
				OperationUpdateCommand command = new OperationUpdateCommand(operation, operationDlg.getData());
				getViewer().getEditDomain().getCommandStack().execute(command);
			}
		} else {
			//super.performRequest(request);
		}
	}

	/**
	 * Set the name of the operation, with its argument and its stereotypes
	 * 
	 * @see org.topcased.modeler.edit.EMFGraphNodeEditPart#refreshHeaderLabel()
	 */
	@Override
	protected void refreshHeaderLabel() {

		OperationFigure fig = (OperationFigure) getFigure();

		String suffix = new String();
		suffix += "(";
		int i = 1;
		for (Object o : op.getParameters()) {
			Parameter p = (Parameter) o;
			suffix += p.getValueType().toString() + " " + p.getName();
			if (i < op.getParameters().size()) {
				suffix += ",";
			}
		}
		suffix += ")";

		Label lbl = (Label) fig.getLabel();
		lbl.setOpaque(false);
		Operation operation = op;

		String visibility = "";
		if (operation.getVisibility() == Visibility.PRIVATE)
			visibility = "-";
		else if (operation.getVisibility() == Visibility.PROTECTED)
			visibility = "#";
		else if (operation.getVisibility() == Visibility.PUBLIC)
			visibility = "+";

		if (operation.getName() != null && operation.getName().length() != 0) {
			StringBuffer prefix = new StringBuffer();
			prefix.append(visibility + op.getReturnType().toString() + " ");
			lbl.setText(prefix + operation.getName() + suffix);
		}
	}
}