/*******************************************************************************
 * No CopyrightText Defined in the configurator file.
 ******************************************************************************/
package com.bluexml.side.Requirements.modeler.processDiagram.commands;

import java.util.HashSet;
import java.util.Set;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.gef.EditPart;
import org.topcased.modeler.commands.AbstractRestoreConnectionCommand;
import org.topcased.modeler.di.model.GraphEdge;
import org.topcased.modeler.di.model.GraphElement;
import org.topcased.modeler.utils.Utils;

import com.bluexml.side.Requirements.modeler.processDiagram.ProSimpleObjectConstants;
import com.bluexml.side.requirements.Agent;
import com.bluexml.side.requirements.Goal;

/**
 * Agent restore connection command
 *
 * @generated
 */
public class AgentRestoreConnectionCommand extends
		AbstractRestoreConnectionCommand {
	/**
	 * @param part the EditPart that is restored
	 * @generated
	 */
	public AgentRestoreConnectionCommand(EditPart part) {
		super(part);
	}

	/**
	 * @see org.topcased.modeler.commands.AbstractRestoreConnectionCommand#initializeCommands()
	 * @generated
	 */
	protected void initializeCommands() {

		GraphElement graphElementSrc = getGraphElement();
		EObject eObjectSrc = Utils.getElement(graphElementSrc);

		if (eObjectSrc instanceof Agent) {
			for (GraphElement graphElementTgt : getAllGraphElements()) {
				boolean autoRef = graphElementTgt.equals(graphElementSrc);

				EObject eObjectTgt = Utils.getElement(graphElementTgt);
				if (eObjectTgt instanceof Goal) {
					if (autoRef) {
						// autoRef not allowed
					} else {
						// if the graphElementSrc is the source of the edge or if it is the target and that the SourceTargetCouple is reversible
						createis_responsibleFromAgentToGoal_Responsible(
								graphElementSrc, graphElementTgt);
					}
				}

			}
		}
	}

	/**
	 * @param srcElt the source element
	 * @param targetElt the target element
	 * @generated NOT
	 */
	private void createis_responsibleFromAgentToGoal_Responsible(
			GraphElement srcElt, GraphElement targetElt) {
		Agent sourceObject = (Agent) Utils.getElement(srcElt);
		Goal targetObject = (Goal) Utils.getElement(targetElt);

		boolean createEdge = false;
		for (Goal g : getAllParentGoals(targetObject, true))
			if (!createEdge)
				createEdge = sourceObject.getIsResponsible().contains(g)
				&& g.getResponsible().contains(sourceObject);
		
		
		if (createEdge) {
			// check if the relation does not exists yet
			if (getExistingEdges(srcElt, targetElt,
					ProSimpleObjectConstants.SIMPLE_OBJECT_IS_RESPONSIBLE)
					.size() == 0) {
				GraphEdge edge = Utils
						.createGraphEdge(ProSimpleObjectConstants.SIMPLE_OBJECT_IS_RESPONSIBLE);
				is_responsibleEdgeCreationCommand cmd = new is_responsibleEdgeCreationCommand(
						null, edge, srcElt, false);
				cmd.setTarget(targetElt);
				add(cmd);
			}
		}
	}
	
	private Set<Goal> getAllParentGoals(Goal g, boolean includesMe) {
		Set<Goal> result = new HashSet<Goal>();
		if (includesMe)
			result.add(g);
		if (g.eContainer() != null && g.eContainer() instanceof Goal)
			result.addAll(getAllParentGoals((Goal) g.eContainer(), true));
		return result;
	}

}