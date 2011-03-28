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
package com.bluexml.side.Class.modeler.diagram.policies;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.gef.EditDomain;
import org.eclipse.gef.commands.Command;
import org.topcased.modeler.commands.CreateTypedEdgeCommand;
import org.topcased.modeler.di.model.GraphEdge;
import org.topcased.modeler.di.model.GraphElement;
import org.topcased.modeler.edit.policies.AbstractEdgeCreationEditPolicy;
import org.topcased.modeler.utils.SourceTargetData;
import org.topcased.modeler.utils.Utils;

import com.bluexml.side.Class.modeler.diagram.commands.AssociationEdgeCreationCommand;
import com.bluexml.side.clazz.Association;

/**
 * Association edge creation
 * 
 * @generated
 */
public class AssociationEdgeCreationEditPolicy extends AbstractEdgeCreationEditPolicy {
	/**
	 * @see org.topcased.modeler.edit.policies.AbstractEdgeCreationEditPolicy#createCommand(org.eclipse.gef.EditDomain,
	 *      org.topcased.modeler.di.model.GraphEdge,
	 *      org.topcased.modeler.di.model.GraphElement)
	 * @generated
	 */
	protected CreateTypedEdgeCommand createCommand(EditDomain domain, GraphEdge edge, GraphElement source) {
		return new AssociationEdgeCreationCommand(domain, edge, source);
	}

	/**
	 * @see org.topcased.modeler.edit.policies.AbstractEdgeCreationEditPolicy#checkEdge(org.topcased.modeler.di.model.GraphEdge)
	 * @generated
	 */
	protected boolean checkEdge(GraphEdge edge) {
		return Utils.getElement(edge) instanceof Association;
	}

	/**
	 * @see org.topcased.modeler.edit.policies.AbstractEdgeCreationEditPolicy#checkSource(org.topcased.modeler.di.model.GraphElement)
	 * @_generated
	 */
	protected boolean checkSource(GraphElement source) {
		EObject object = Utils.getElement(source);

		boolean allowClazz = object instanceof com.bluexml.side.clazz.Clazz;
		boolean allowAspect = object instanceof com.bluexml.side.clazz.Aspect;
		if (allowClazz || allowAspect) {
			return true;
		}
		return false;
	}

	/**
	 * @see org.topcased.modeler.edit.policies.AbstractEdgeCreationEditPolicy#checkTargetForSource(org.topcased.modeler.di.model.GraphElement,
	 *      org.topcased.modeler.di.model.GraphElement)
	 * @_generated
	 */
	protected boolean checkTargetForSource(GraphElement source, GraphElement target) {
		EObject sourceObject = Utils.getElement(source);
		EObject targetObject = Utils.getElement(target);

		return isAllowed(sourceObject, targetObject);
	}

	private boolean isAllowed(EObject sourceObject, EObject targetObject) {
		boolean allowClassToClass = sourceObject instanceof com.bluexml.side.clazz.Clazz && targetObject instanceof com.bluexml.side.clazz.Clazz;
		boolean allowClassToAspect = sourceObject instanceof com.bluexml.side.clazz.Clazz && targetObject instanceof com.bluexml.side.clazz.Aspect;

		boolean allowAspectToAspect = sourceObject instanceof com.bluexml.side.clazz.Aspect && targetObject instanceof com.bluexml.side.clazz.Aspect;
		boolean allowAspectToClass = sourceObject instanceof com.bluexml.side.clazz.Aspect && targetObject instanceof com.bluexml.side.clazz.Clazz;

		return allowClassToClass || allowClassToAspect || allowAspectToAspect || allowAspectToClass;
	}

	/**
	 * @see org.topcased.modeler.edit.policies.AbstractEdgeCreationEditPolicy#checkCommand(org.eclipse.gef.commands.Command)
	 * @generated
	 */
	protected boolean checkCommand(Command command) {
		return command instanceof AssociationEdgeCreationCommand;
	}

	/**
	 * @see org.topcased.modeler.edit.policies.AbstractEdgeCreationEditPolicy#getSourceTargetData(org.topcased.modeler.di.model.GraphElement,
	 *      org.topcased.modeler.di.model.GraphElement)
	 * @_generated
	 */
	protected SourceTargetData getSourceTargetData(GraphElement source, GraphElement target) {
		EObject sourceObject = Utils.getElement(source);
		EObject targetObject = Utils.getElement(target);

		if (isAllowed(sourceObject, targetObject)) {
			return new SourceTargetData(false, true, SourceTargetData.DIAGRAM, "com.bluexml.side.clazz.ClassPackage", "associationSet", "firstEndClazz", "secondEndClazz", "associations", null, "associations", null);
		}
		return null;
	}

}