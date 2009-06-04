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
import com.bluexml.side.requirements.Entity;
import com.bluexml.side.requirements.Goal;
import com.bluexml.side.requirements.PrivilegeGroup;

/**
 * PrivilegeGroup restore connection command
 *
 * @generated
 */
public class PrivilegeGroupRestoreConnectionCommand extends
		AbstractRestoreConnectionCommand {
	/**
	 * @param part the EditPart that is restored
	 * @generated
	 */
	public PrivilegeGroupRestoreConnectionCommand(EditPart part) {
		super(part);
	}

	/**
	 * @see org.topcased.modeler.commands.AbstractRestoreConnectionCommand#initializeCommands()
	 * @generated
	 */
	protected void initializeCommands() {

		GraphElement graphElementSrc = getGraphElement();
		EObject eObjectSrc = Utils.getElement(graphElementSrc);

		if (eObjectSrc instanceof PrivilegeGroup) {
			for (GraphElement graphElementTgt : getAllGraphElements()) {
				boolean autoRef = graphElementTgt.equals(graphElementSrc);

				EObject eObjectTgt = Utils.getElement(graphElementTgt);
				if (eObjectTgt instanceof Goal) {
					if (autoRef) {
						// autoRef not allowed
					} else {
						// if graphElementSrc is the target of the edge or if it is the source and that the SourceTargetCouple is reversible
						createhasPrivilegeGroupFromGoalToPrivilegeGroup(
								graphElementTgt, graphElementSrc);
					}
				}
				if (eObjectTgt instanceof Entity) {
					if (autoRef) {
						// autoRef not allowed
					} else {
						// if the graphElementSrc is the source of the edge or if it is the target and that the SourceTargetCouple is reversible
						createisLinkedToEntityFromPrivilegeGroupToEntity(
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

	/**
	 * @param srcElt the source element
	 * @param targetElt the target element
	 * @generated
	 */
	private void createisLinkedToEntityFromPrivilegeGroupToEntity(
			GraphElement srcElt, GraphElement targetElt) {
		PrivilegeGroup sourceObject = (PrivilegeGroup) Utils.getElement(srcElt);
		Entity targetObject = (Entity) Utils.getElement(targetElt);

		if (targetObject.equals(sourceObject.getEntryPoint())) {
			// check if the relation does not exists yet
			if (getExistingEdges(srcElt, targetElt,
					ReqSimpleObjectConstants.SIMPLE_OBJECT_ISLINKEDTOENTITY)
					.size() == 0) {
				GraphEdge edge = Utils
						.createGraphEdge(ReqSimpleObjectConstants.SIMPLE_OBJECT_ISLINKEDTOENTITY);
				isLinkedToEntityEdgeCreationCommand cmd = new isLinkedToEntityEdgeCreationCommand(
						null, edge, srcElt, false);
				cmd.setTarget(targetElt);
				add(cmd);
			}
		}
	}

}