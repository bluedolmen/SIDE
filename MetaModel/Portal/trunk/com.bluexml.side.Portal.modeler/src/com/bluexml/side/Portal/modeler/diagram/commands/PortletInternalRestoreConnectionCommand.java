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
package com.bluexml.side.Portal.modeler.diagram.commands;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.gef.EditPart;
import org.topcased.modeler.commands.AbstractRestoreConnectionCommand;
import org.topcased.modeler.di.model.GraphEdge;
import org.topcased.modeler.di.model.GraphElement;
import org.topcased.modeler.utils.Utils;

import com.bluexml.side.Portal.modeler.diagram.PdSimpleObjectConstants;
import com.bluexml.side.portal.Portlet;
import com.bluexml.side.portal.PortletInternal;

/**
 * PortletInternal restore connection command
 *
 * @generated
 */
public class PortletInternalRestoreConnectionCommand extends AbstractRestoreConnectionCommand {
	/**
	 * @param part the EditPart that is restored
	 * @generated
	 */
	public PortletInternalRestoreConnectionCommand(EditPart part) {
		super(part);
	}

	/**
	 * @see org.topcased.modeler.commands.AbstractRestoreConnectionCommand#initializeCommands()
	 * @generated
	 */
	protected void initializeCommands() {

		GraphElement graphElementSrc = getGraphElement();
		EObject eObjectSrc = Utils.getElement(graphElementSrc);

		if (eObjectSrc instanceof PortletInternal) {
			for (GraphElement graphElementTgt : getAllGraphElements()) {
				boolean autoRef = graphElementTgt.equals(graphElementSrc);

				EObject eObjectTgt = Utils.getElement(graphElementTgt);
				if (eObjectTgt instanceof Portlet) {
					if (autoRef) {
						// autoRef not allowed
					} else {
						// if graphElementSrc is the target of the edge or if it is the source and that the SourceTargetCouple is reversible
						createisInternalPortletFromPortletToPortletInternal(graphElementTgt, graphElementSrc);
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
	private void createisInternalPortletFromPortletToPortletInternal(GraphElement srcElt, GraphElement targetElt) {
		Portlet sourceObject = (Portlet) Utils.getElement(srcElt);
		PortletInternal targetObject = (PortletInternal) Utils.getElement(targetElt);

		if (targetObject.equals(sourceObject.getIsPortletInternal())) {
			// check if the relation does not exists yet
			if (getExistingEdges(srcElt, targetElt, PdSimpleObjectConstants.SIMPLE_OBJECT_ISINTERNALPORTLET).size() == 0) {
				GraphEdge edge = Utils.createGraphEdge(PdSimpleObjectConstants.SIMPLE_OBJECT_ISINTERNALPORTLET);
				isInternalPortletEdgeCreationCommand cmd = new isInternalPortletEdgeCreationCommand(null, edge, srcElt, false);
				cmd.setTarget(targetElt);
				add(cmd);
			}
		}
	}

}