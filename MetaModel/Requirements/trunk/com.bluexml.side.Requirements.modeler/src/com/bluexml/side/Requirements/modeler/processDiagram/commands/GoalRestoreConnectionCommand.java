/*******************************************************************************
 * No CopyrightText Defined in the configurator file.
 ******************************************************************************/
package com.bluexml.side.Requirements.modeler.processDiagram.commands;

import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.gef.EditPart;
import org.topcased.modeler.commands.AbstractRestoreConnectionCommand;
import org.topcased.modeler.di.model.Diagram;
import org.topcased.modeler.di.model.GraphEdge;
import org.topcased.modeler.di.model.GraphElement;
import org.topcased.modeler.diagrams.model.Diagrams;
import org.topcased.modeler.editor.ICreationUtils;
import org.topcased.modeler.utils.Utils;

import com.bluexml.side.Requirements.modeler.processDiagram.ProSimpleObjectConstants;
import com.bluexml.side.requirements.Agent;
import com.bluexml.side.requirements.Entity;
import com.bluexml.side.requirements.Goal;
import com.bluexml.side.requirements.GoalStep;
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
	 * @_generated
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
				/*if (eObjectTgt instanceof Goal) {
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
				}*/

				if (eObjectTgt instanceof Entity) {
					if (autoRef) {
						// autoRef not allowed
					} else {
						// if the graphElementSrc is the source of the edge or if it is the target and that the SourceTargetCouple is reversible
						createPrivilegeGroupFromGoalToEntity_EntryPoint(
								graphElementSrc, graphElementTgt);
					}
				}

				if (eObjectTgt instanceof Goal) {
					if (autoRef) {
						// autoRef not allowed
					} else {
						// if the graphElementSrc is the source of the edge or if it is the target and that the SourceTargetCouple is reversible
						createGoalStepFromGoalToGoal_NextGoals(graphElementSrc,
								graphElementTgt);
						// if graphElementSrc is the target of the edge or if it is the source and that the SourceTargetCouple is reversible
						createGoalStepFromGoalToGoal_NextGoals(graphElementTgt,
								graphElementSrc);
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
					ProSimpleObjectConstants.SIMPLE_OBJECT_IS_SUB_GOAL).size() == 0) {
				GraphEdge edge = Utils
						.createGraphEdge(ProSimpleObjectConstants.SIMPLE_OBJECT_IS_SUB_GOAL);
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
	private void createPrivilegeGroupFromGoalToEntity_EntryPoint(
			GraphElement srcElt, GraphElement targetElt) {
		Goal sourceObject = (Goal) Utils.getElement(srcElt);
		Entity targetObject = (Entity) Utils.getElement(targetElt);

		EList edgeObjectList = sourceObject.getPrivilegeGroup();
		for (Iterator it = edgeObjectList.iterator(); it.hasNext();) {
			Object obj = it.next();
			if (obj instanceof PrivilegeGroup) {
				PrivilegeGroup edgeObject = (PrivilegeGroup) obj;
				if (targetObject.equals(edgeObject.getEntryPoint())
						&& sourceObject.getPrivilegeGroup()
								.contains(edgeObject)) {
					// check if the relation does not exists yet
					List<GraphEdge> existing = getExistingEdges(srcElt,
							targetElt, PrivilegeGroup.class);
					if (!isAlreadyPresent(existing, edgeObject)) {
						ICreationUtils factory = getModeler()
								.getActiveConfiguration().getCreationUtils();
						// restore the link with its default presentation
						GraphElement edge = factory
								.createGraphElement(edgeObject);
						if (edge instanceof GraphEdge) {
							PrivilegeGroupEdgeCreationCommand cmd = new PrivilegeGroupEdgeCreationCommand(
									getEditDomain(), (GraphEdge) edge, srcElt,
									false);
							cmd.setTarget(targetElt);
							add(cmd);
						}
					}
				}
			}
		}
	}

	/**
	 * @param srcElt the source element
	 * @param targetElt the target element
	 * @_generated
	 */
	private void createGoalStepFromGoalToGoal_NextGoals(GraphElement srcElt,
			GraphElement targetElt) {
		Diagram diagram = (Diagram) srcElt.getContainer();
		Diagrams diagrams = (Diagrams) diagram.eContainer();
		com.bluexml.side.requirements.Process process = (com.bluexml.side.requirements.Process) diagrams.getModel();
		
		Goal sourceObject = (Goal) Utils.getElement(srcElt);
		Goal targetObject = (Goal) Utils.getElement(targetElt);

		EList edgeObjectList = sourceObject.getStep();
		for (Iterator it = edgeObjectList.iterator(); it.hasNext();) {
			Object obj = it.next();
			if (obj instanceof GoalStep) {
				GoalStep edgeObject = (GoalStep) obj;
				if (edgeObject.getNextGoals().contains(targetObject)
						&& sourceObject.getStep().contains(edgeObject)
						&& edgeObject.getProcess().equals(process)) {
					// check if the relation does not exists yet
					List<GraphEdge> existing = getExistingEdges(srcElt,
							targetElt, GoalStep.class);
					if (!isAlreadyPresent(existing, edgeObject)) {
						ICreationUtils factory = getModeler()
								.getActiveConfiguration().getCreationUtils();
						// restore the link with its default presentation
						GraphElement edge = factory
								.createGraphElement(edgeObject);
						if (edge instanceof GraphEdge) {
							GoalStepEdgeCreationCommand cmd = new GoalStepEdgeCreationCommand(
									getEditDomain(), (GraphEdge) edge, srcElt,
									false);
							cmd.setTarget(targetElt);
							add(cmd);
						}
					}
				}
			}
		}
	}

}