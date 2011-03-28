/*******************************************************************************
 * 	Copyright (C) BlueXML 2005-2008
 *
 * No CopyrightText Defined in the configurator file.
 ******************************************************************************/
package com.bluexml.side.Class.modeler.diagram.commands;

import java.util.Iterator;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.gef.EditPart;
import org.topcased.modeler.ModelerPropertyConstants;
import org.topcased.modeler.commands.AbstractRestoreConnectionCommand;
import org.topcased.modeler.di.model.GraphEdge;
import org.topcased.modeler.di.model.GraphElement;
import org.topcased.modeler.di.model.util.DIUtils;
import org.topcased.modeler.utils.Utils;
import org.w3c.dom.Comment;

import com.bluexml.side.Class.modeler.diagram.CdSimpleObjectConstants;
import com.bluexml.side.clazz.Association;

/**
 * Classe restore connection command
 * 
 * @generated
 */
public class AssociationRestoreConnectionCommand extends AbstractRestoreConnectionCommand {
	/**
	 * @param part
	 *            the EditPart that is restored
	 * @generated
	 */
	public AssociationRestoreConnectionCommand(EditPart part) {
		super(part);
	}

	/**
	 * @see org.topcased.modeler.commands.AbstractRestoreConnectionCommand#initializeCommands()
	 * @generated
	 */
	protected void initializeCommands() {

		GraphElement elt = getGraphElement();
		EObject eltObject = Utils.getElement(elt);

		if (eltObject instanceof Association) {
			Iterator<?> itDiagContents = getModeler().getActiveDiagram().eAllContents();
			while (itDiagContents.hasNext()) {
				Object obj = itDiagContents.next();
				// FIXME Change the way to handle EList GraphNodes
				if (obj instanceof GraphElement && DIUtils.getProperty((GraphElement) obj, ModelerPropertyConstants.ESTRUCTURAL_FEATURE_ID) == null) {
					boolean autoRef = obj.equals(elt);
					GraphElement elt2 = (GraphElement) obj;
					EObject eltObject2 = Utils.getElement(elt2);

					if (eltObject2 instanceof Comment) {
						if (autoRef) {
							// autoRef not allowed
						} else {
							// if the elt is the source of the edge or if it is the target and that the SourceTargetCouple is reversible
							createisCommentedFromAssociationToComment(elt, elt2);
						}
					}
				}
			}
		}
	}

	/**
	 * @param srcElt
	 *            the source element
	 * @param targetElt
	 *            the target element
	 * @generated
	 */
	private void createisCommentedFromAssociationToComment(GraphElement srcElt, GraphElement targetElt) {
		Association sourceObject = (Association) Utils.getElement(srcElt);
		Comment targetObject = (Comment) Utils.getElement(targetElt);

		if (sourceObject.getComments().contains(targetObject)) {
			// check if the relation does not exists yet
			if (getExistingEdges(srcElt, targetElt, CdSimpleObjectConstants.SIMPLE_OBJECT_ISCOMMENTED).size() == 0) {
				GraphEdge edge = Utils.createGraphEdge(CdSimpleObjectConstants.SIMPLE_OBJECT_ISCOMMENTED);
				isCommentedEdgeCreationCommand cmd = new isCommentedEdgeCreationCommand(null, edge, srcElt, false);
				cmd.setTarget(targetElt);
				add(cmd);
			}
		}
	}

}