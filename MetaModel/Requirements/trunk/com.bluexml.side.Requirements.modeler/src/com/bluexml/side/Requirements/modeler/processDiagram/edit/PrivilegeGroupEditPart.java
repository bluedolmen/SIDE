/*******************************************************************************
 * No CopyrightText Defined in the configurator file.
 ******************************************************************************/
package com.bluexml.side.Requirements.modeler.processDiagram.edit;

import org.eclipse.draw2d.IFigure;
import org.eclipse.gef.Request;
import org.eclipse.gef.RequestConstants;
import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.jface.window.Window;
import org.eclipse.swt.graphics.Color;
import org.eclipse.swt.graphics.Font;
import org.eclipse.swt.graphics.FontData;
import org.topcased.modeler.ModelerEditPolicyConstants;
import org.topcased.modeler.di.model.GraphEdge;
import org.topcased.modeler.edit.EMFGraphEdgeEditPart;
import org.topcased.modeler.editor.Modeler;
import org.topcased.modeler.editor.ModelerGraphicalViewer;
import org.topcased.modeler.internal.ModelerPlugin;
import org.topcased.modeler.utils.Utils;

import com.bluexml.side.Requirements.modeler.dialogs.PrivilegeDialog;
import com.bluexml.side.Requirements.modeler.goalDiagram.commands.update.PrivilegeNullUpdateCommand;
import com.bluexml.side.Requirements.modeler.processDiagram.figures.PrivilegeGroupFigure;
import com.bluexml.side.Requirements.modeler.processDiagram.preferences.ProDiagramPreferenceConstants;
import com.bluexml.side.requirements.PrivilegeGroup;

/**
 * PrivilegeGroup controller
 *
 * @generated
 */
public class PrivilegeGroupEditPart extends EMFGraphEdgeEditPart {

	/**
	 * Constructor
	 *
	 * @param model the graph object
	 * @generated
	 */
	public PrivilegeGroupEditPart(GraphEdge model) {
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

	}

	/**
	 * @return the Figure
	 * @generated
	 */
	protected IFigure createFigure() {
		PrivilegeGroupFigure connection = new PrivilegeGroupFigure();

		return connection;
	}

	/**
	 * @see org.topcased.modeler.edit.GraphEdgeEditPart#getPreferenceDefaultRouter()
	 * 
	 * @generated
	 */
	protected String getPreferenceDefaultRouter() {
		return getPreferenceStore()
				.getString(
						ProDiagramPreferenceConstants.PRIVILEGEGROUP_EDGE_DEFAULT_ROUTER);
	}

	/**
	 * @see org.topcased.modeler.edit.GraphEdgeEditPart#getPreferenceDefaultForegroundColor()
	 * 
	 * @generated
	 */
	protected Color getPreferenceDefaultForegroundColor() {
		String preferenceForeground = getPreferenceStore()
				.getString(
						ProDiagramPreferenceConstants.PRIVILEGEGROUP_EDGE_DEFAULT_FOREGROUND_COLOR);
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
				ProDiagramPreferenceConstants.PRIVILEGEGROUP_EDGE_DEFAULT_FONT);
		if (preferenceFont.length() != 0) {
			return Utils.getFont(new FontData(preferenceFont));
		}
		return null;
	}

	@Override
	public void performRequest(Request request) {
		PrivilegeGroup g = (PrivilegeGroup) Utils.getElement(getGraphEdge());
		Modeler editor = ((ModelerGraphicalViewer) getViewer()).getModelerEditor();

		if (request.getType() == RequestConstants.REQ_OPEN) {
			if (g.getEntryPoint() != null) {
				PrivilegeDialog dialog = new PrivilegeDialog(ModelerPlugin
						.getActiveWorkbenchShell(), g, editor);
				if (dialog.open() == Window.OK) {
					PrivilegeNullUpdateCommand command = new PrivilegeNullUpdateCommand();
					getViewer().getEditDomain().getCommandStack().execute(
							command);
					refresh();
				}
			} else
				MessageDialog.openError(null, "No entry point!",
						"No entry point has been defined ! Check your model !");
		} else {
			super.performRequest(request);
		}
	}
}