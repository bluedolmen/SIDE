/*******************************************************************************
 * No CopyrightText Defined in the configurator file.
 ******************************************************************************/
package com.bluexml.side.Requirements.modeler.goalDiagram.commands;

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

import com.bluexml.side.Requirements.modeler.goalDiagram.ReqSimpleObjectConstants;
import com.bluexml.side.requirements.Entity;
import com.bluexml.side.requirements.PrivilegeGroup;
import com.bluexml.side.requirements.RelationShip;

/**
 * Entity restore connection command
 *
 * @generated
 */
public class EntityRestoreConnectionCommand extends
		AbstractRestoreConnectionCommand {
	/**
	 * @param part the EditPart that is restored
	 * @generated
	 */
	public EntityRestoreConnectionCommand(EditPart part) {
		super(part);
	}

	/**
	 * @see org.topcased.modeler.commands.AbstractRestoreConnectionCommand#initializeCommands()
	 * @generated
	 */
	protected void initializeCommands() {

		GraphElement graphElementSrc = getGraphElement();
		EObject eObjectSrc = Utils.getElement(graphElementSrc);

		if (eObjectSrc instanceof Entity) {
			for (GraphElement graphElementTgt : getAllGraphElements()) {
				boolean autoRef = graphElementTgt.equals(graphElementSrc);

				EObject eObjectTgt = Utils.getElement(graphElementTgt);

				if (eObjectTgt instanceof Entity) {
					if (autoRef) {
						createRelationShipFromEntityToEntity_Target(
								graphElementSrc, graphElementSrc);
					} else {
						// if the graphElementSrc is the source of the edge or if it is the target and that the SourceTargetCouple is reversible
						createRelationShipFromEntityToEntity_Target(
								graphElementSrc, graphElementTgt);
						// if graphElementSrc is the target of the edge or if it is the source and that the SourceTargetCouple is reversible
						createRelationShipFromEntityToEntity_Target(
								graphElementTgt, graphElementSrc);
					}
				}

				if (eObjectTgt instanceof PrivilegeGroup) {
					if (autoRef) {
						// autoRef not allowed
					} else {
						// if graphElementSrc is the target of the edge or if it is the source and that the SourceTargetCouple is reversible
						createisLinkedToEntityFromPrivilegeGroupToEntity(
								graphElementTgt, graphElementSrc);
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
	private void createRelationShipFromEntityToEntity_Target(
			GraphElement srcElt, GraphElement targetElt) {
		Entity sourceObject = (Entity) Utils.getElement(srcElt);
		Entity targetObject = (Entity) Utils.getElement(targetElt);

		EList edgeObjectList = ((com.bluexml.side.requirements.RequirementsDefinition) Utils
				.getDiagramModelObject(srcElt)).getChildElements();
		for (Iterator it = edgeObjectList.iterator(); it.hasNext();) {
			Object obj = it.next();
			if (obj instanceof RelationShip) {
				RelationShip edgeObject = (RelationShip) obj;
				if (targetObject.equals(edgeObject.getTarget())
						&& sourceObject.equals(edgeObject.getSource())) {
					// check if the relation does not exists yet
					List<GraphEdge> existing = getExistingEdges(srcElt,
							targetElt, RelationShip.class);
					if (!isAlreadyPresent(existing, edgeObject)) {
						ICreationUtils factory = getModeler()
								.getActiveConfiguration().getCreationUtils();
						// restore the link with its default presentation
						GraphElement edge = factory
								.createGraphElement(edgeObject);
						if (edge instanceof GraphEdge) {
							RelationShipEdgeCreationCommand cmd = new RelationShipEdgeCreationCommand(
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