/*******************************************************************************
 * No CopyrightText Defined in the configurator file.
 ******************************************************************************/
package com.bluexml.side.Requirements.modeler.goalDiagram.edit;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import org.eclipse.draw2d.IFigure;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.gef.EditPolicy;
import org.eclipse.gef.Request;
import org.eclipse.gef.RequestConstants;
import org.eclipse.gef.commands.Command;
import org.eclipse.gmf.runtime.diagram.ui.editpolicies.DecorationEditPolicy;
import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.jface.window.Window;
import org.eclipse.swt.graphics.Color;
import org.eclipse.swt.graphics.Font;
import org.eclipse.swt.graphics.FontData;
import org.eclipse.ui.IDecoratorManager;
import org.topcased.draw2d.figures.ILabel;
import org.topcased.modeler.ModelerEditPolicyConstants;
import org.topcased.modeler.di.model.DiagramElement;
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
import com.bluexml.side.Requirements.modeler.goalDiagram.commands.PrivilegeGroupRestoreConnectionCommand;
import com.bluexml.side.Requirements.modeler.goalDiagram.commands.update.PrivilegeNullUpdateCommand;
import com.bluexml.side.Requirements.modeler.goalDiagram.dialogs.PrivilegeDialog;
import com.bluexml.side.Requirements.modeler.goalDiagram.figures.PrivilegeGroupFigure;
import com.bluexml.side.Requirements.modeler.goalDiagram.policies.hasPrivilegeGroupEdgeCreationEditPolicy;
import com.bluexml.side.Requirements.modeler.goalDiagram.policies.isLinkedToEntityEdgeCreationEditPolicy;
import com.bluexml.side.Requirements.modeler.goalDiagram.preferences.ReqDiagramPreferenceConstants;
import com.bluexml.side.requirements.BasicElement;
import com.bluexml.side.requirements.Entity;
import com.bluexml.side.requirements.Privilege;
import com.bluexml.side.requirements.PrivilegeGroup;

/**
 * PrivilegeGroup controller
 * 
 * @generated
 */
public class PrivilegeGroupEditPart extends EMFGraphNodeEditPart {

	/**
	 * Constructor
	 * 
	 * @param obj
	 *            the graph node
	 * @generated
	 */
	public PrivilegeGroupEditPart(GraphNode obj) {
		super(obj);
	}

	/**
	 * @see org.eclipse.gef.editparts.AbstractEditPart#createEditPolicies()
	 * @generated
	 */
	protected void createEditPolicies() {
		super.createEditPolicies();

		installEditPolicy(ReqEditPolicyConstants.HASPRIVILEGEGROUP_EDITPOLICY,
				new hasPrivilegeGroupEdgeCreationEditPolicy());

		installEditPolicy(ReqEditPolicyConstants.ISLINKEDTOENTITY_EDITPOLICY,
				new isLinkedToEntityEdgeCreationEditPolicy());

		installEditPolicy(ModelerEditPolicyConstants.RESTORE_EDITPOLICY,
				new RestoreEditPolicy() {
					protected Command getRestoreConnectionsCommand(
							RestoreConnectionsRequest request) {
						return new PrivilegeGroupRestoreConnectionCommand(
								getHost());
					}
				});

		installEditPolicy(ModelerEditPolicyConstants.RESIZABLE_EDITPOLICY,
				new ResizableEditPolicy());

		installEditPolicy(EditPolicy.DIRECT_EDIT_ROLE,
				new LabelDirectEditPolicy());
		
	}

	/**
	 * @see org.eclipse.gef.editparts.AbstractGraphicalEditPart#createFigure()
	 * @_generated
	 */
	protected IFigure createFigure() {
		PrivilegeGroup pGroup = (PrivilegeGroup) Utils
				.getElement(getGraphNode());

		Map<BasicElement, Set<Privilege>> s = filterPrivileges(pGroup.getPrivileges());
		
		ReqConfiguration config = new ReqConfiguration();
		if (getGraphNode().getContained().size() > 0) {
			GraphNode privilegesListNode = (GraphNode) getGraphNode().getContained().get(0);
			EList<DiagramElement> privilegesList = privilegesListNode
					.getContained();
			while (privilegesList.size() > 0)
				privilegesList.remove(0);
			for (BasicElement elt : s.keySet()) {
				Set<Privilege> privileges = s.get(elt);
				
				GraphElement graphElt = config.getCreationUtils()
						.createGraphElement((EObject) privileges.toArray()[0]);
				if (elt != null)
					privilegesList.add(graphElt);
			}
		}

		IFigure result = new PrivilegeGroupFigure();
		return result;
	}

	/**
	 * @see org.topcased.modeler.edit.GraphNodeEditPart#getPreferenceDefaultBackgroundColor()
	 * @generated
	 */
	protected Color getPreferenceDefaultBackgroundColor() {
		String backgroundColor = getPreferenceStore()
				.getString(
						ReqDiagramPreferenceConstants.PRIVILEGEGROUP_DEFAULT_BACKGROUND_COLOR);
		if (backgroundColor.length() != 0) {
			return Utils.getColor(backgroundColor);
		}
		return null;
	}

	/**
	 * @see org.topcased.modeler.edit.GraphEdgeEditPart#getPreferenceDefaultForegroundColor()
	 * 
	 * @generated
	 */
	protected Color getPreferenceDefaultForegroundColor() {
		String foregroundColor = getPreferenceStore()
				.getString(
						ReqDiagramPreferenceConstants.PRIVILEGEGROUP_DEFAULT_FOREGROUND_COLOR);
		if (foregroundColor.length() != 0) {
			return Utils.getColor(foregroundColor);
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
				ReqDiagramPreferenceConstants.PRIVILEGEGROUP_DEFAULT_FONT);
		if (preferenceFont.length() != 0) {
			return Utils.getFont(new FontData(preferenceFont));
		}
		return null;

	}

	@Override
	public void performRequest(Request request) {
		PrivilegeGroup g = (PrivilegeGroup) Utils.getElement(getGraphNode());

		if (request.getType() == RequestConstants.REQ_OPEN) {
			if (g.getEntryPoint() != null) {
				PrivilegeDialog dialog = new PrivilegeDialog(ModelerPlugin
						.getActiveWorkbenchShell(), g);
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

	@Override
	protected void refreshChildren() {
		super.refreshChildren();

		PrivilegeGroup pGroup = (PrivilegeGroup) Utils
				.getElement(getGraphNode());

		Map<BasicElement, Set<Privilege>> s = filterPrivileges(pGroup.getPrivileges());
		s = sortPrivileges(s);
		
		ReqConfiguration config = new ReqConfiguration();
		if (getGraphNode().getContained().size() > 0) {
			GraphNode privilegesListNode = (GraphNode) getGraphNode().getContained().get(0);
			EList<DiagramElement> privilegesList = privilegesListNode
					.getContained();
			while (privilegesList.size() > 0)
				privilegesList.remove(0);
			for (BasicElement elt : s.keySet()) {
				Set<Privilege> privileges = s.get(elt);
				
				GraphElement graphElt = config.getCreationUtils()
						.createGraphElement((EObject) privileges.toArray()[0]);
				if (elt != null)
					privilegesList.add(graphElt);
			}
		}
	}

	private Map<BasicElement, Set<Privilege>> sortPrivileges(
			Map<BasicElement, Set<Privilege>> s) {
		Map<BasicElement, Set<Privilege>> result = new HashMap<BasicElement, Set<Privilege>>();
		for (BasicElement elt : s.keySet()) {
			if (elt instanceof Entity) {
				result.put(elt, s.get(elt));

				for (BasicElement elt2 : s.keySet()) {
					if (elt2.eContainer().equals(elt))
						result.put(elt2, s.get(elt2));
				}
			}
			
		}

		for (BasicElement elt : s.keySet()) {
			if (!result.containsKey(elt))
				result.put(elt, s.get(elt));
		}
		return result;
	}

	private Map<BasicElement, Set<Privilege>> filterPrivileges(
			EList<Privilege> privileges) {
		Map<BasicElement, Set<Privilege>> result = new HashMap<BasicElement, Set<Privilege>>();
		for (Privilege p : privileges) {
			if (result.containsKey(p.getElement())) {
				Set<Privilege> s = result.get(p.getElement());
				s.add(p);
			} else {
				Set<Privilege> s = new HashSet<Privilege>();
				s.add(p);
				result.put(p.getElement(), s);
			}
		}
		
		return result;
	}

	@Override
	protected void refreshTextAndFont() {
		ILabel lab = getLabel();
		if (lab != null)
			lab.setText("Strategy");
	}
}