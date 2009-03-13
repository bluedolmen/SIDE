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
package com.bluexml.side.Class.modeler.diagram.commands;

import java.util.Iterator;
import java.util.List;

import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.gef.EditPart;
import org.topcased.modeler.commands.AbstractRestoreConnectionCommand;
import org.topcased.modeler.di.model.GraphEdge;
import org.topcased.modeler.di.model.GraphElement;
import org.topcased.modeler.editor.ICreationUtils;
import org.topcased.modeler.utils.Utils;

import com.bluexml.side.Class.modeler.diagram.CdSimpleObjectConstants;
import com.bluexml.side.clazz.Aspect;
import com.bluexml.side.clazz.Association;
import com.bluexml.side.clazz.Clazz;

/**
 * Aspect restore connection command
 *
 * @generated
 */
public class AspectRestoreConnectionCommand extends
		AbstractRestoreConnectionCommand {
	/**
	 * @param part the EditPart that is restored
	 * @generated
	 */
	public AspectRestoreConnectionCommand(EditPart part) {
		super(part);
	}

	/**
	 * @see org.topcased.modeler.commands.AbstractRestoreConnectionCommand#initializeCommands()
	 * @generated
	 */
	protected void initializeCommands() {

		GraphElement graphElementSrc = getGraphElement();
		EObject eObjectSrc = Utils.getElement(graphElementSrc);

		if (eObjectSrc instanceof Aspect) {
			for (GraphElement graphElementTgt : getAllGraphElements()) {
				boolean autoRef = graphElementTgt.equals(graphElementSrc);

				EObject eObjectTgt = Utils.getElement(graphElementTgt);

				if (eObjectTgt instanceof Clazz) {
					if (autoRef) {
						// autoRef not allowed
					} else {
						// if the graphElementSrc is the source of the edge or if it is the target and that the SourceTargetCouple is reversible
						createAssociationFromAspectToClazz_Destination(
								graphElementSrc, graphElementTgt);
					}
				}

				if (eObjectTgt instanceof Clazz) {
					if (autoRef) {
						// autoRef not allowed
					} else {
						// if graphElementSrc is the target of the edge or if it is the source and that the SourceTargetCouple is reversible
						createincludeFromClazzToAspect(graphElementTgt,
								graphElementSrc);
					}
				}
				if (eObjectTgt instanceof Clazz) {
					if (autoRef) {
						// autoRef not allowed
					} else {
						// if graphElementSrc is the target of the edge or if it is the source and that the SourceTargetCouple is reversible
						createhasAspectFromClazzToAspect(graphElementTgt,
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
	private void createAssociationFromAspectToClazz_Destination(
			GraphElement srcElt, GraphElement targetElt) {
		Aspect sourceObject = (Aspect) Utils.getElement(srcElt);
		Clazz targetObject = (Clazz) Utils.getElement(targetElt);

		EList edgeObjectList = ((com.bluexml.side.clazz.ClassPackage) Utils
				.getDiagramModelObject(srcElt)).getAssociationSet();
		for (Iterator it = edgeObjectList.iterator(); it.hasNext();) {
			Object obj = it.next();
			if (obj instanceof Association) {
				Association edgeObject = (Association) obj;
				if (targetObject.equals(edgeObject.getDestination())
						&& sourceObject.equals(edgeObject.getSource())
						&& sourceObject.getAssociations().contains(edgeObject)) {
					// check if the relation does not exists yet
					List<GraphEdge> existing = getExistingEdges(srcElt,
							targetElt, Association.class);
					if (!isAlreadyPresent(existing, edgeObject)) {
						ICreationUtils factory = getModeler()
								.getActiveConfiguration().getCreationUtils();
						// restore the link with its default presentation
						GraphElement edge = factory
								.createGraphElement(edgeObject);
						if (edge instanceof GraphEdge) {
							AssociationEdgeCreationCommand cmd = new AssociationEdgeCreationCommand(
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
	 * @generated
	 */
	private void createincludeFromClazzToAspect(GraphElement srcElt,
			GraphElement targetElt) {
		Clazz sourceObject = (Clazz) Utils.getElement(srcElt);
		Aspect targetObject = (Aspect) Utils.getElement(targetElt);

		if (sourceObject.getAspects().contains(targetObject)) {
			// check if the relation does not exists yet
			if (getExistingEdges(srcElt, targetElt,
					CdSimpleObjectConstants.SIMPLE_OBJECT_INCLUDE).size() == 0) {
				GraphEdge edge = Utils
						.createGraphEdge(CdSimpleObjectConstants.SIMPLE_OBJECT_INCLUDE);
				includeEdgeCreationCommand cmd = new includeEdgeCreationCommand(
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
	private void createhasAspectFromClazzToAspect(GraphElement srcElt,
			GraphElement targetElt) {
		Clazz sourceObject = (Clazz) Utils.getElement(srcElt);
		Aspect targetObject = (Aspect) Utils.getElement(targetElt);

		if (sourceObject.getAspects().contains(targetObject)) {
			// check if the relation does not exists yet
			if (getExistingEdges(srcElt, targetElt,
					CdSimpleObjectConstants.SIMPLE_OBJECT_HASASPECT).size() == 0) {
				GraphEdge edge = Utils
						.createGraphEdge(CdSimpleObjectConstants.SIMPLE_OBJECT_HASASPECT);
				hasAspectEdgeCreationCommand cmd = new hasAspectEdgeCreationCommand(
						null, edge, srcElt, false);
				cmd.setTarget(targetElt);
				add(cmd);
			}
		}
	}

}