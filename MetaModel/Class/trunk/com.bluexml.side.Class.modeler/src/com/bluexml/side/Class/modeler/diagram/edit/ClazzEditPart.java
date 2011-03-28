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
import org.eclipse.jface.resource.JFaceResources;
import org.eclipse.jface.window.Window;
import org.eclipse.swt.SWT;
import org.eclipse.swt.graphics.Color;
import org.eclipse.swt.graphics.Font;
import org.eclipse.swt.graphics.FontData;
import org.eclipse.swt.widgets.Display;
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
import com.bluexml.side.Class.modeler.diagram.commands.ClazzRestoreConnectionCommand;
import com.bluexml.side.Class.modeler.diagram.commands.update.ClazzUpdateCommand;
import com.bluexml.side.Class.modeler.diagram.dialogs.ClazzEditDialog;
import com.bluexml.side.Class.modeler.diagram.figures.ClazzFigure;
import com.bluexml.side.Class.modeler.diagram.figures.NomenclatureFigure;
import com.bluexml.side.Class.modeler.diagram.policies.AssociationEdgeCreationEditPolicy;
import com.bluexml.side.Class.modeler.diagram.policies.ClazzLayoutEditPolicy;
import com.bluexml.side.Class.modeler.diagram.policies.GeneralizationEdgeCreationEditPolicy;
import com.bluexml.side.Class.modeler.diagram.policies.hasAspectEdgeCreationEditPolicy;
import com.bluexml.side.Class.modeler.diagram.policies.includeEdgeCreationEditPolicy;
import com.bluexml.side.Class.modeler.diagram.policies.isAssociationClassEdgeCreationEditPolicy;
import com.bluexml.side.Class.modeler.diagram.policies.isCommentedEdgeCreationEditPolicy;
import com.bluexml.side.Class.modeler.diagram.policies.isStereotypedEdgeCreationEditPolicy;
import com.bluexml.side.Class.modeler.diagram.preferences.CdDiagramPreferenceConstants;
import com.bluexml.side.Class.modeler.tools.ClazzNotation;
import com.bluexml.side.clazz.Clazz;
import com.bluexml.side.common.Comment;
import com.bluexml.side.common.Stereotype;

/**
 * The Clazz object controller
 * 
 * @generated
 */
public class ClazzEditPart extends EMFGraphNodeEditPart {
	/**
	 * Constructor
	 * 
	 * @param obj
	 *            the graph node
	 * @generated
	 */
	public ClazzEditPart(GraphNode obj) {
		super(obj);
	}

	private Clazz object;

	/**
	 * Constructor
	 * 
	 * @param obj
	 *            the graph node
	 * @param object
	 * @_generated
	 */
	public ClazzEditPart(GraphNode obj, Clazz object) {
		super(obj);
		this.object = object;
	}

	/**
	 * Creates edit policies and associates these with roles
	 * 
	 * @generated
	 */
	protected void createEditPolicies() {
		super.createEditPolicies();

		installEditPolicy(CdEditPolicyConstants.ASSOCIATION_EDITPOLICY, new AssociationEdgeCreationEditPolicy());
		
		installEditPolicy(CdEditPolicyConstants.ISASSOCIATIONCLASS_EDITPOLICY, new isAssociationClassEdgeCreationEditPolicy());
		
		installEditPolicy(CdEditPolicyConstants.GENERALIZATION_EDITPOLICY, new GeneralizationEdgeCreationEditPolicy());

		installEditPolicy(CdEditPolicyConstants.ISCOMMENTED_EDITPOLICY, new isCommentedEdgeCreationEditPolicy());

		installEditPolicy(CdEditPolicyConstants.ISSTEREOTYPED_EDITPOLICY, new isStereotypedEdgeCreationEditPolicy());

		installEditPolicy(CdEditPolicyConstants.INCLUDE_EDITPOLICY, new includeEdgeCreationEditPolicy());

		installEditPolicy(CdEditPolicyConstants.HASASPECT_EDITPOLICY, new hasAspectEdgeCreationEditPolicy());

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
	 * @_generated
	 */
	protected IFigure createFigure() {
		Clazz clazz = (Clazz) Utils.getElement(getGraphNode());

		CdConfiguration config = new CdConfiguration();

		GraphNode attributesListNode = (GraphNode) getGraphNode().getContained().get(0);
		EList attributesList = attributesListNode.getContained();
		while (attributesList.size() > 0)
			attributesList.remove(0);
		for (Object o : clazz.getAttributes()) {
			GraphElement elt = config.getCreationUtils().createGraphElement((EObject) o);
			attributesList.add(elt);
		}

		GraphNode operationsListNode = (GraphNode) getGraphNode().getContained().get(1);
		EList operationsList = operationsListNode.getContained();
		while (operationsList.size() > 0)
			operationsList.remove(0);
		for (Object o : clazz.getOperations()) {
			GraphElement elt = config.getCreationUtils().createGraphElement((EObject) o);
			operationsList.add(elt);
		}

		IFigure result = null;
		if (clazz != null) {

			boolean findedNomenclature = false;
			for (Object o : clazz.getStereotypes()) {
				Stereotype s = (Stereotype) o;
				findedNomenclature = s.getName().equalsIgnoreCase("Nomenclature");
				if (findedNomenclature)
					break;
			}
			if (findedNomenclature) {
				result = new NomenclatureFigure();
			} else {
				result = new ClazzFigure();
			}
		}
		return result;
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

	@Override
	protected Font getDefaultFont() {
		Clazz clazz = (Clazz) Utils.getElement(getGraphNode());

		FontData[] fData = Display.getDefault().getSystemFont().getFontData();

		if (clazz != null) {
			if (clazz.isAbstract()) {
				fData[0].setStyle(SWT.ITALIC | SWT.BOLD);
			} else {
				fData[0].setStyle(SWT.BOLD);
			}
		} else {
			fData[0].setStyle(SWT.BOLD);
		}

		JFaceResources.getFontRegistry().put("ItalicFont", fData);
		return JFaceResources.getFontRegistry().get("ItalicFont");
	}

	@Override
	protected Color getDefaultBackgroundColor() {
		Clazz clazz = (Clazz) Utils.getElement(getGraphNode());
		if (clazz == null)
			clazz = object;
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

		if (clazz.isDeprecated())
			return ModelerColorConstants.classlightRed;
		else if (findedNomenclature) {
			return ModelerColorConstants.lightYellow;
		} else {
			if (findedUI)
				return ModelerColorConstants.classBlue;
			else
				return ModelerColorConstants.classlightGreen;
		}
	}

	/**
	 * Set the name of Class with the stereotypes and the "from"
	 * 
	 * @see org.topcased.modeler.edit.EMFGraphNodeEditPart#refreshHeaderLabel()
	 */
	protected void refreshHeaderLabel() {
		IFigure ifig = getFigure();
		if (ifig instanceof ClazzFigure) {
			ClazzFigure fig = (ClazzFigure) ifig;
			ComposedLabel lbl = (ComposedLabel) fig.getLabel();
			Clazz clazz = (Clazz) Utils.getElement(getGraphNode());

			lbl.setPrefix(ClazzNotation.getStereotypesNotation(clazz));

			if (clazz.getName() != null) {
				lbl.setMain(clazz.getName());
			}

			boolean findedNomenclature = false;
			for (Object o : clazz.getStereotypes()) {
				Stereotype s = (Stereotype) o;
				findedNomenclature = s.getName().equalsIgnoreCase("Nomenclature");
				if (findedNomenclature)
					break;
			}

		} else if (ifig instanceof NomenclatureFigure) {
			NomenclatureFigure fig = (NomenclatureFigure) ifig;
			Clazz clazz = (Clazz) Utils.getElement(getGraphNode());
			((ComposedLabel) fig.getLabel()).setPrefix(ClazzNotation.getStereotypesNotation(clazz));
			((ComposedLabel) fig.getLabel()).setMain(clazz.getName());
		}
	}

	/**
	 * handle the edition of the classe
	 * 
	 * @see org.eclipse.gef.EditPart#performRequest(org.eclipse.gef.Request)
	 */
	@Override
	public void performRequest(Request request) {
		if (request.getType() == RequestConstants.REQ_OPEN) {
			Clazz classe = (Clazz) Utils.getElement(getGraphNode());

			ClazzEditDialog dlg = new ClazzEditDialog(classe, ModelerPlugin.getActiveWorkbenchShell());
			if (dlg.open() == Window.OK) {
				ClazzUpdateCommand command = new ClazzUpdateCommand(classe, dlg.getData());
				getViewer().getEditDomain().getCommandStack().execute(command);

				refresh();
			}
		} else {
			super.performRequest(request);
		}
	}

	@Override
	public void refresh() {
		super.refresh();

		Clazz clazz = (Clazz) Utils.getElement(getGraphNode());

		if (clazz != null) {

			boolean findedNomenclature = false;
			for (Object o : clazz.getStereotypes()) {
				Stereotype s = (Stereotype) o;
				findedNomenclature = s.getName().equalsIgnoreCase("Nomenclature");
				if (findedNomenclature)
					break;
			}

			if (!findedNomenclature) {

				boolean findView = false, findLayout = false;

				for (Object o : clazz.getComments()) {
					Comment c = (Comment) o;
					if (isStereotyped(c, "view")) {
						findView = true;
					}
					if (isStereotyped(c, "layout")) {
						findLayout = true;
					}
				}

				if (findView) {
					((ClazzFigure) getFigure()).getView().setVisible(true);
				} else {
					((ClazzFigure) getFigure()).getView().setVisible(false);
				}

				if (findLayout) {
					((ClazzFigure) getFigure()).getLayout().setVisible(true);
				} else {
					((ClazzFigure) getFigure()).getLayout().setVisible(false);
				}
			}
		}
	}

	private boolean isStereotyped(Comment c, String stereotype) {
		boolean result = false;

		for (Object obj : c.getStereotypes()) {
			if (obj instanceof Stereotype) {
				Stereotype s = (Stereotype) obj;
				if (s.getName().equalsIgnoreCase(stereotype)) {
					result = true;
				}
			}
		}
		return result;
	}

}