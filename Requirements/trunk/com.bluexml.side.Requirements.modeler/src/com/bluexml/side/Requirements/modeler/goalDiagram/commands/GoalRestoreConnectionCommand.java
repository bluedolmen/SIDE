/*******************************************************************************
 * No CopyrightText Defined in the configurator file.
 ******************************************************************************/
package com.bluexml.side.Requirements.modeler.goalDiagram.commands;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.gef.EditPart;
import org.topcased.modeler.commands.AbstractRestoreConnectionCommand;
import org.topcased.modeler.di.model.GraphEdge;
import org.topcased.modeler.di.model.GraphElement;
import org.topcased.modeler.utils.Utils;

import com.bluexml.side.Requirements.modeler.goalDiagram.ReqSimpleObjectConstants;
import com.bluexml.side.requirements.Agent;
import com.bluexml.side.requirements.Goal;
import com.bluexml.side.requirements.PrivilegeGroup;

/**
 * Goal restore connection command
 *
 * @generated
 */
public class GoalRestoreConnectionCommand extends
		AbstractRestoreConnectionCommand {
	/**
	 * @param part the EditPart that is restored
	 * @generated
	 */
	public GoalRestoreConnectionCommand(EditPart part) {
		super(part);
	}

	/**
	 * @see org.topcased.modeler.commands.AbstractRestoreConnectionCommand#initializeCommands()
	 * @generated
	 */
	protected void initializeCommands() {

		GraphElement graphElementSrc = getGraphElement();
		EObject eObjectSrc = Utils.getElement(graphElementSrc);

		if (eObjectSrc instanceof Goal) {
			for (GraphElement graphElementTgt : getAllGraphElements()) {
				boolean autoRef = graphElementTgt.equals(graphElementSrc);

				EObject eObjectTgt = Utils.getElement(graphElementTgt);
				if (eObjectTgt instanceof Agent) {
					if (autoRef) {
						// autoRef not allowed
					} else {
						// if graphElementSrc is the target of the edge or if it is the source and that the SourceTargetCouple is reversible
						createis_responsibleFromAgentToGoal_Responsible(
								graphElementTgt, graphElementSrc);
					}
				}
				if (eObjectTgt instanceof Goal) {
					if (autoRef) {
						// autoRef not allowed
					} else {
						// if the graphElementSrc is the source of the edge or if it is the target and that the SourceTargetCouple is reversible
						createis_sub_goalFromGoalToGoal(graphElementSrc,
								graphElementTgt);
						// if graphElementSrc is the target of the edge or if it is the source and that the SourceTargetCouple is reversible
						createis_sub_goalFromGoalToGoal(graphElementTgt,
								graphElementSrc);
					}
				}
				if (eObjectTgt instanceof PrivilegeGroup) {
					if (autoRef) {
						// autoRef not allowed
					} else {
						// if the graphElementSrc is the source of the edge or if it is the target and that the SourceTargetCouple is reversible
						createhasPrivilegeGroupFromGoalToPrivilegeGroup(
								graphElementSrc, graphElementTgt);
					}
				}

			}
		}
	}

	/**
	 * @param srcElt the source element
	 * @param targetElt the target element
	 * @generated
	 */
	private void createis_responsibleFromAgentToGoal_Responsible(
			GraphElement srcElt, GraphElement targetElt) {
		Agent sourceObject = (Agent) Utils.getElement(srcElt);
		Goal targetObject = (Goal) Utils.getElement(targetElt);

		if (sourceObject.getIsResponsible().contains(targetObject)
				&& targetObject.getResponsible().contains(sourceObject)) {
			// check if the relation does not exists yet
			if (getExistingEdges(srcElt, targetElt,
					ReqSimpleObjectConstants.SIMPLE_OBJECT_IS_RESPONSIBLE)
					.size() == 0) {
				GraphEdge edge = Utils
						.createGraphEdge(ReqSimpleObjectConstants.SIMPLE_OBJECT_IS_RESPONSIBLE);
				is_responsibleEdgeCreationCommand cmd = new is_responsibleEdgeCreationCommand(
						null, edge, srcElt, false);
				cmd.setTarget(targetElt);
				add(cmd);
			}
		}
	}

	/**
	 * @param srcElt the source element
	 * @param targetElt the target element
	 * @generated
	 */
	private void createis_sub_goalFromGoalToGoal(GraphElement srcElt,
			GraphElement targetElt) {
		Goal sourceObject = (Goal) Utils.getElement(srcElt);
		Goal targetObject = (Goal) Utils.getElement(targetElt);

		if (sourceObject.getSubgoals().contains(targetObject)) {
			// check if the relation does not exists yet
			if (getExistingEdges(srcElt, targetElt,
					ReqSimpleObjectConstants.SIMPLE_OBJECT_IS_SUB_GOAL).size() == 0) {
				GraphEdge edge = Utils
						.createGraphEdge(ReqSimpleObjectConstants.SIMPLE_OBJECT_IS_SUB_GOAL);
				is_sub_goalEdgeCreationCommand cmd = new is_sub_goalEdgeCreationCommand(
						null, edge, srcElt, false);
				cmd.setTarget(targetElt);
				add(cmd);
			}
		}
	}

	/**
	 * @param srcElt the source element
	 * @param targetElt the target element
	 * @generated
	 */
	private void createhasPrivilegeGroupFromGoalToPrivilegeGroup(
			GraphElement srcElt, GraphElement targetElt) {
		Goal sourceObject = (Goal) Utils.getElement(srcElt);
		PrivilegeGroup targetObject = (PrivilegeGroup) Utils
				.getElement(targetElt);

		if (sourceObject.getPrivilegeGroup().contains(targetObject)) {
			// check if the relation does not exists yet
			if (getExistingEdges(srcElt, targetElt,
					ReqSimpleObjectConstants.SIMPLE_OBJECT_HASPRIVILEGEGROUP)
					.size() == 0) {
				GraphEdge edge = Utils
						.createGraphEdge(ReqSimpleObjectConstants.SIMPLE_OBJECT_HASPRIVILEGEGROUP);
				hasPrivilegeGroupEdgeCreationCommand cmd = new hasPrivilegeGroupEdgeCreationCommand(
						null, edge, srcElt, false);
				cmd.setTarget(targetElt);
				add(cmd);
			}
		}
	}

}