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

import org.eclipse.gef.EditDomain;
import org.topcased.modeler.commands.CreateTypedEdgeCommand;
import org.topcased.modeler.commands.DeleteGraphElementCommand;
import org.topcased.modeler.di.model.GraphEdge;
import org.topcased.modeler.di.model.GraphElement;
import org.topcased.modeler.di.model.GraphNode;
import org.topcased.modeler.utils.Utils;

/**
 * actions edge creation command
 *
 * @generated
 */
public class actionsEdgeCreationCommand extends CreateTypedEdgeCommand {

	/**
	 * @param domain the edit domain
	 * @param newObj the graph edge of the new connection
	 * @param src the graph element of the source
	 * @generated
	 */
	public actionsEdgeCreationCommand(EditDomain domain, GraphEdge newObj,
			GraphElement src) {
		this(domain, newObj, src, true);
	}

	/**
	 * @param domain the edit domain
	 * @param newObj the graph edge of the new connection
	 * @param src the graph element of the source
	 * @param needModelUpdate set it to true if the model need to be updated
	 * @generated
	 */
	public actionsEdgeCreationCommand(EditDomain domain, GraphEdge newObj,
			GraphElement src, boolean needModelUpdate) {
		super(domain, newObj, src, needModelUpdate);
	}

	/**
	 * @_generated
	 */
	protected void redoModel() {
		//remove previous link
		if (getTarget() instanceof GraphNode) {
			GraphElement node = getTarget();
			for (GraphEdge edge : Utils.getTargetEdges(node))
				if (getEdge() != edge) {
					DeleteGraphElementCommand command = new DeleteGraphElementCommand(edge, true);
					command.execute();
				}
		}
		
		super.redoModel();
	}

	/**
	 * @generated
	 */
	protected void undoModel() {
		//TODO add specific code if super method is not sufficient
		super.undoModel();
	}

}