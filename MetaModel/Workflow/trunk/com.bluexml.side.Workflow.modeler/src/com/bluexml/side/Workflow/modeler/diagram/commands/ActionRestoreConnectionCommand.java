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
import com.bluexml.side.workflow.Action;
import com.bluexml.side.workflow.Transition;

/**
 * Action restore connection command
 *
 * @generated
 */
public class ActionRestoreConnectionCommand extends
		AbstractRestoreConnectionCommand {
	/**
	 * @param part the EditPart that is restored
	 * @generated
	 */
	public ActionRestoreConnectionCommand(EditPart part) {
		super(part);
	}

	/**
	 * @see org.topcased.modeler.commands.AbstractRestoreConnectionCommand#initializeCommands()
	 * @generated
	 */
	protected void initializeCommands() {

		GraphElement graphElementSrc = getGraphElement();
		EObject eObjectSrc = Utils.getElement(graphElementSrc);

		if (eObjectSrc instanceof Action) {
			for (GraphElement graphElementTgt : getAllGraphElements()) {
				boolean autoRef = graphElementTgt.equals(graphElementSrc);

				EObject eObjectTgt = Utils.getElement(graphElementTgt);
				if (eObjectTgt instanceof Transition) {
					if (autoRef) {
						// autoRef not allowed
					} else {
						// if graphElementSrc is the target of the edge or if it is the source and that the SourceTargetCouple is reversible
						createactionsFromTransitionToAction(graphElementTgt,
								graphElementSrc);
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
	private void createactionsFromTransitionToAction(GraphElement srcElt,
			GraphElement targetElt) {
		Transition sourceObject = (Transition) Utils.getElement(srcElt);
		Action targetObject = (Action) Utils.getElement(targetElt);

		if (sourceObject.getAction().contains(targetObject)) {
			// check if the relation does not exists yet
			if (getExistingEdges(srcElt, targetElt,
					WfSimpleObjectConstants.SIMPLE_OBJECT_ACTIONS).size() == 0) {
				GraphEdge edge = Utils
						.createGraphEdge(WfSimpleObjectConstants.SIMPLE_OBJECT_ACTIONS);
				actionsEdgeCreationCommand cmd = new actionsEdgeCreationCommand(
						null, edge, srcElt, false);
				cmd.setTarget(targetElt);
				add(cmd);
			}
		}
	}

}