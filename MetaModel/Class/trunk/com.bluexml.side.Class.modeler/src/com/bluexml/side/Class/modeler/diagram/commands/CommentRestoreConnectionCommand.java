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

import org.eclipse.emf.ecore.EObject;
import org.eclipse.gef.EditPart;
import org.topcased.modeler.commands.AbstractRestoreConnectionCommand;
import org.topcased.modeler.di.model.GraphEdge;
import org.topcased.modeler.di.model.GraphElement;
import org.topcased.modeler.utils.Utils;

import com.bluexml.side.Class.modeler.diagram.CdSimpleObjectConstants;
import com.bluexml.side.clazz.Association;
import com.bluexml.side.clazz.Clazz;
import com.bluexml.side.common.Comment;
import com.bluexml.side.common.Stereotype;

/**
 * Comment restore connection command
 * 
 * @generated
 */
public class CommentRestoreConnectionCommand extends
		AbstractRestoreConnectionCommand {
	/**
	 * @param part the EditPart that is restored
	 * @generated
	 */
	public CommentRestoreConnectionCommand(EditPart part) {
		super(part);
	}

	/**
	 * @see org.topcased.modeler.commands.AbstractRestoreConnectionCommand#initializeCommands()
	 * @generated
	 */
	protected void initializeCommands() {

		GraphElement graphElementSrc = getGraphElement();
		EObject eObjectSrc = Utils.getElement(graphElementSrc);

		if (eObjectSrc instanceof Comment) {
			for (GraphElement graphElementTgt : getAllGraphElements()) {
				boolean autoRef = graphElementTgt.equals(graphElementSrc);

				EObject eObjectTgt = Utils.getElement(graphElementTgt);
				if (eObjectTgt instanceof Clazz) {
					if (autoRef) {
						// autoRef not allowed
					} else {
						// if graphElementSrc is the target of the edge or if it is the source and that the SourceTargetCouple is reversible
						createisCommentedFromClazzToComment(graphElementTgt,
								graphElementSrc);
					}
				}
				if (eObjectTgt instanceof Association) {
					if (autoRef) {
						// autoRef not allowed
					} else {
						// if graphElementSrc is the target of the edge or if it is the source and that the SourceTargetCouple is reversible
						createisCommentedFromAssociationToComment(
								graphElementTgt, graphElementSrc);
					}
				}
				if (eObjectTgt instanceof Stereotype) {
					if (autoRef) {
						// autoRef not allowed
					} else {
						// if the graphElementSrc is the source of the edge or if it is the target and that the SourceTargetCouple is reversible
						createisStereotypedFromCommentToStereotype(
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
	private void createisCommentedFromClazzToComment(GraphElement srcElt,
			GraphElement targetElt) {
		Clazz sourceObject = (Clazz) Utils.getElement(srcElt);
		Comment targetObject = (Comment) Utils.getElement(targetElt);

		if (sourceObject.getComments().contains(targetObject)) {
			// check if the relation does not exists yet
			if (getExistingEdges(srcElt, targetElt,
					CdSimpleObjectConstants.SIMPLE_OBJECT_ISCOMMENTED).size() == 0) {
				GraphEdge edge = Utils
						.createGraphEdge(CdSimpleObjectConstants.SIMPLE_OBJECT_ISCOMMENTED);
				isCommentedEdgeCreationCommand cmd = new isCommentedEdgeCreationCommand(
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
	private void createisCommentedFromAssociationToComment(GraphElement srcElt,
			GraphElement targetElt) {
		Association sourceObject = (Association) Utils.getElement(srcElt);
		Comment targetObject = (Comment) Utils.getElement(targetElt);

		if (sourceObject.getComments().contains(targetObject)) {
			// check if the relation does not exists yet
			if (getExistingEdges(srcElt, targetElt,
					CdSimpleObjectConstants.SIMPLE_OBJECT_ISCOMMENTED).size() == 0) {
				GraphEdge edge = Utils
						.createGraphEdge(CdSimpleObjectConstants.SIMPLE_OBJECT_ISCOMMENTED);
				isCommentedEdgeCreationCommand cmd = new isCommentedEdgeCreationCommand(
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
	private void createisStereotypedFromCommentToStereotype(
			GraphElement srcElt, GraphElement targetElt) {
		Comment sourceObject = (Comment) Utils.getElement(srcElt);
		Stereotype targetObject = (Stereotype) Utils.getElement(targetElt);

		if (sourceObject.getStereotypes().contains(targetObject)) {
			// check if the relation does not exists yet
			if (getExistingEdges(srcElt, targetElt,
					CdSimpleObjectConstants.SIMPLE_OBJECT_ISSTEREOTYPED).size() == 0) {
				GraphEdge edge = Utils
						.createGraphEdge(CdSimpleObjectConstants.SIMPLE_OBJECT_ISSTEREOTYPED);
				isStereotypedEdgeCreationCommand cmd = new isStereotypedEdgeCreationCommand(
						null, edge, srcElt, false);
				cmd.setTarget(targetElt);
				add(cmd);
			}
		}
	}

}