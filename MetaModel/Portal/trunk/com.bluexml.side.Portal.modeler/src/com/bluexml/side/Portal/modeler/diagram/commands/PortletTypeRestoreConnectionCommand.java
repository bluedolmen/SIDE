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

import java.util.List;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.gef.EditPart;
import org.topcased.modeler.commands.AbstractRestoreConnectionCommand;
import org.topcased.modeler.di.model.GraphEdge;
import org.topcased.modeler.di.model.GraphElement;
import org.topcased.modeler.editor.ICreationUtils;
import org.topcased.modeler.utils.Utils;

import com.bluexml.side.portal.InstanciatePortletType;
import com.bluexml.side.portal.Portlet;
import com.bluexml.side.portal.PortletType;

/**
 * PortletType restore connection command
 *
 * @generated
 */
public class PortletTypeRestoreConnectionCommand extends AbstractRestoreConnectionCommand {
	/**
	 * @param part the EditPart that is restored
	 * @generated
	 */
	public PortletTypeRestoreConnectionCommand(EditPart part) {
		super(part);
	}

	/**
	 * @see org.topcased.modeler.commands.AbstractRestoreConnectionCommand#initializeCommands()
	 * @generated
	 */
	protected void initializeCommands() {

		GraphElement graphElementSrc = getGraphElement();
		EObject eObjectSrc = Utils.getElement(graphElementSrc);

		if (eObjectSrc instanceof PortletType) {
			for (GraphElement graphElementTgt : getAllGraphElements()) {
				boolean autoRef = graphElementTgt.equals(graphElementSrc);

				EObject eObjectTgt = Utils.getElement(graphElementTgt);

				if (eObjectTgt instanceof Portlet) {
					if (autoRef) {
						// autoRef not allowed
					} else {
						// if graphElementSrc is the target of the edge or if it is the source and that the SourceTargetCouple is reversible
						createInstanciatePortletTypeFromPortletToPortletType_PortletType(graphElementTgt, graphElementSrc);
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
	private void createInstanciatePortletTypeFromPortletToPortletType_PortletType(GraphElement srcElt, GraphElement targetElt) {
		Portlet sourceObject = (Portlet) Utils.getElement(srcElt);
		PortletType targetObject = (PortletType) Utils.getElement(targetElt);

		{
			{
				InstanciatePortletType edgeObject = sourceObject.getIsInstanceOfPortletType();
				if (edgeObject == null) {
					return;
				}
				if (targetObject.equals(edgeObject.getPortletType()) && edgeObject.equals(sourceObject.getIsInstanceOfPortletType())) {
					// check if the relation does not exists yet
					List<GraphEdge> existing = getExistingEdges(srcElt, targetElt, InstanciatePortletType.class);
					if (!isAlreadyPresent(existing, edgeObject)) {
						ICreationUtils factory = getModeler().getActiveConfiguration().getCreationUtils();
						// restore the link with its default presentation
						GraphElement edge = factory.createGraphElement(edgeObject);
						if (edge instanceof GraphEdge) {
							InstanciatePortletTypeEdgeCreationCommand cmd = new InstanciatePortletTypeEdgeCreationCommand(getEditDomain(), (GraphEdge) edge, srcElt, false);
							cmd.setTarget(targetElt);
							add(cmd);
						}
					}
				}
			}
		}
	}

}