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
import org.topcased.modeler.ModelerColorConstants;
import org.topcased.modeler.ModelerEditPolicyConstants;
import org.topcased.modeler.di.model.GraphNode;
import org.topcased.modeler.edit.EMFGraphNodeEditPart;
import org.topcased.modeler.edit.policies.ResizableEditPolicy;
import org.topcased.modeler.edit.policies.RestoreEditPolicy;
import org.topcased.modeler.internal.ModelerPlugin;
import org.topcased.modeler.requests.RestoreConnectionsRequest;
import org.topcased.modeler.utils.Utils;

import com.bluexml.side.Class.modeler.diagram.commands.AttributeRestoreConnectionCommand;
import com.bluexml.side.Class.modeler.diagram.commands.update.AttributeUpdateCommand;
import com.bluexml.side.Class.modeler.diagram.dialogs.AttributeEditDialog;
import com.bluexml.side.Class.modeler.diagram.figures.AttributeFigure;
import com.bluexml.side.Class.modeler.diagram.preferences.CdDiagramPreferenceConstants;
import com.bluexml.side.clazz.Attribute;
import com.bluexml.side.clazz.Visibility;

/**
 * The Attribute object controller
 * 
 * @generated
 */
public class AttributeEditPart extends EMFGraphNodeEditPart {

	private Attribute attribute;

	/**
	 * Constructor
	 * 
	 * @param obj
	 *            the graph node
	 * @generated
	 */
	public AttributeEditPart(GraphNode obj) {
		super(obj);
	}

	public AttributeEditPart(GraphNode node, Attribute object) {
		super(node);
		attribute = object;
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

		installEditPolicy(ModelerEditPolicyConstants.RESIZABLE_EDITPOLICY,
				new ResizableEditPolicy());

		installEditPolicy(
				ModelerEditPolicyConstants.CHANGE_BACKGROUND_COLOR_EDITPOLICY,
				null);
		installEditPolicy(
				ModelerEditPolicyConstants.CHANGE_FOREGROUND_COLOR_EDITPOLICY,
				null);
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
						CdDiagramPreferenceConstants.ATTRIBUTE_DEFAULT_BACKGROUND_COLOR);
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
						CdDiagramPreferenceConstants.ATTRIBUTE_DEFAULT_FOREGROUND_COLOR);
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
				CdDiagramPreferenceConstants.ATTRIBUTE_DEFAULT_FONT);
		if (preferenceFont.length() != 0) {
			return Utils.getFont(new FontData(preferenceFont));
		}
		return null;

	}

	@Override
	protected Font getDefaultFont() {
		FontData[] fData = Display.getDefault().getSystemFont().getFontData();
		fData[0].setStyle(SWT.ITALIC);
		JFaceResources.getFontRegistry().put("ItalicFont", fData);
		return JFaceResources.getFontRegistry().get("ItalicFont");
	}

	@Override
	protected void refreshHeaderLabel() {
		AttributeFigure fig = (AttributeFigure) getFigure();
		String enumeration = "";
		if (attribute.getValueList() != null)
			enumeration = "[" + attribute.getValueList().getName() + "]";

		String visibility = "";
		if (attribute.getVisibility().equals(Visibility.PUBLIC))
			visibility = "+";
		else if (attribute.getVisibility().equals(Visibility.PRIVATE))
			visibility = "-";
		else if (attribute.getVisibility().equals(Visibility.PROTECTED))
			visibility = "#";

		fig.getLabel().setText(
				visibility + attribute.getName() + " : " + attribute.getTyp()
						+ enumeration);
		fig.getLabel().setOpaque(false);
	}

	@Override
	protected Color getDefaultBackgroundColor() {

		if (attribute.getMetainfo().size() > 0) {
			getFigure().setOpaque(true);
			return ModelerColorConstants.lightOrange;
		} else {
			getFigure().setOpaque(false);
			return ModelerColorConstants.classBlue;
		}
	}

	/**
	 * handle the edition of the property
	 * 
	 * @see org.eclipse.gef.EditPart#performRequest(org.eclipse.gef.Request)
	 */
	@Override
	public void performRequest(Request request) {
		if (request.getType() == RequestConstants.REQ_OPEN) {
			Attribute property = (Attribute) Utils.getElement(getGraphNode());

			AttributeEditDialog propertyDlg = new AttributeEditDialog(property,
					ModelerPlugin.getActiveWorkbenchShell());
			if (propertyDlg.open() == Window.OK) {
				AttributeUpdateCommand command = new AttributeUpdateCommand(
						property, propertyDlg.getData());
				getViewer().getEditDomain().getCommandStack().execute(command);
			}
		} else {
			// super.performRequest(request);
		}
	}
}