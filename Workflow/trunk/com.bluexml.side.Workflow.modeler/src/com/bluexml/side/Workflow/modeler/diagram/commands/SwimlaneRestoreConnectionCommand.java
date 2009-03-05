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
package com.bluexml.side.Workflow.modeler.diagram.commands;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.gef.EditPart;
import org.topcased.modeler.commands.AbstractRestoreConnectionCommand;
import org.topcased.modeler.di.model.GraphEdge;
import org.topcased.modeler.di.model.GraphElement;
import org.topcased.modeler.utils.Utils;

import com.bluexml.side.Workflow.modeler.diagram.WfSimpleObjectConstants;
import com.bluexml.side.workflow.Swimlane;
import com.bluexml.side.workflow.TaskNode;

/**
 * Swimlane restore connection command
 *
 * @generated
 */
public class SwimlaneRestoreConnectionCommand extends
		AbstractRestoreConnectionCommand {
	/**
	 * @param part the EditPart that is restored
	 * @generated
	 */
	public SwimlaneRestoreConnectionCommand(EditPart part) {
		super(part);
	}

	/**
	 * @see org.topcased.modeler.commands.AbstractRestoreConnectionCommand#initializeCommands()
	 * @generated
	 */
	protected void initializeCommands() {

		GraphElement graphElementSrc = getGraphElement();
		EObject eObjectSrc = Utils.getElement(graphElementSrc);

		if (eObjectSrc instanceof Swimlane) {
			for (GraphElement graphElementTgt : getAllGraphElements()) {
				boolean autoRef = graphElementTgt.equals(graphElementSrc);

				EObject eObjectTgt = Utils.getElement(graphElementTgt);
				if (eObjectTgt instanceof TaskNode) {
					if (autoRef) {
						// autoRef not allowed
					} else {
						// if the graphElementSrc is the source of the edge or if it is the target and that the SourceTargetCouple is reversible
						createmanageFromSwimlaneToTaskNode_Swimlane(
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
	private void createmanageFromSwimlaneToTaskNode_Swimlane(
			GraphElement srcElt, GraphElement targetElt) {
		Swimlane sourceObject = (Swimlane) Utils.getElement(srcElt);
		TaskNode targetObject = (TaskNode) Utils.getElement(targetElt);

		if (sourceObject.getManage().contains(targetObject)
				&& sourceObject.equals(targetObject.getSwimlane())) {
			// check if the relation does not exists yet
			if (getExistingEdges(srcElt, targetElt,
					WfSimpleObjectConstants.SIMPLE_OBJECT_MANAGE).size() == 0) {
				GraphEdge edge = Utils
						.createGraphEdge(WfSimpleObjectConstants.SIMPLE_OBJECT_MANAGE);
				manageEdgeCreationCommand cmd = new manageEdgeCreationCommand(
						null, edge, srcElt, false);
				cmd.setTarget(targetElt);
				add(cmd);
			}
		}
	}

}