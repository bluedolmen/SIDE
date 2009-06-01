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
package com.bluexml.side.Class.modeler.diagram;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.gef.EditPart;
import org.topcased.modeler.ModelerPropertyConstants;
import org.topcased.modeler.di.model.Diagram;
import org.topcased.modeler.di.model.GraphEdge;
import org.topcased.modeler.di.model.GraphNode;
import org.topcased.modeler.di.model.SimpleSemanticModelElement;
import org.topcased.modeler.di.model.util.DIUtils;
import org.topcased.modeler.edit.EListEditPart;
import org.topcased.modeler.edit.EMFGraphEdgeEditPart;
import org.topcased.modeler.edit.EMFGraphNodeEditPart;
import org.topcased.modeler.editor.ModelerEditPartFactory;
import org.topcased.modeler.utils.Utils;

import com.bluexml.side.Class.modeler.diagram.edit.AspectEditPart;
import com.bluexml.side.Class.modeler.diagram.edit.AssociationEditPart;
import com.bluexml.side.Class.modeler.diagram.edit.AttributeEditPart;
import com.bluexml.side.Class.modeler.diagram.edit.CdDiagramEditPart;
import com.bluexml.side.Class.modeler.diagram.edit.ClassCommentEditPart;
import com.bluexml.side.Class.modeler.diagram.edit.ClazzEditPart;
import com.bluexml.side.Class.modeler.diagram.edit.EnumerationEditPart;
import com.bluexml.side.Class.modeler.diagram.edit.EnumerationLiteralEditPart;
import com.bluexml.side.Class.modeler.diagram.edit.GeneralizationEditPart;
import com.bluexml.side.Class.modeler.diagram.edit.OperationEditPart;
import com.bluexml.side.Class.modeler.diagram.edit.dependsEditPart;
import com.bluexml.side.Class.modeler.diagram.edit.hasAspectEditPart;
import com.bluexml.side.Class.modeler.diagram.edit.hasViewEditPart;
import com.bluexml.side.Class.modeler.diagram.edit.includeEditPart;
import com.bluexml.side.Class.modeler.diagram.edit.isAssociationClassEditPart;
import com.bluexml.side.Class.modeler.diagram.edit.isCommentedEditPart;
import com.bluexml.side.Class.modeler.diagram.edit.isStereotypedEditPart;
import com.bluexml.side.clazz.util.ClazzSwitch;

/**
 * Part Factory : associates a model object to its controller. <br>
 *
 * @generated
 */
public class CdEditPartFactory extends ModelerEditPartFactory {
	/**
	 * @see org.eclipse.gef.EditPartFactory#createEditPart(org.eclipse.gef.EditPart,java.lang.Object)
	 * @generated
	 */
	public EditPart createEditPart(EditPart context, Object model) {
		if (model instanceof Diagram) {
			return new CdDiagramEditPart((Diagram) model);
		} else if (model instanceof GraphNode) {
			final GraphNode node = (GraphNode) model;
			EObject element = Utils.getElement(node);
			if (element != null) {
				if ("http://www.kerblue.org/class/1.0".equals(element.eClass().getEPackage().getNsURI())) {
					return (EditPart) new NodeClazzSwitch(node).doSwitch(element);
				}
			}

			if (node.getSemanticModel() instanceof SimpleSemanticModelElement) {
				// Manage the Element that are not associated with a model object
			}
		} else if (model instanceof GraphEdge) {
			final GraphEdge edge = (GraphEdge) model;
			EObject element = Utils.getElement(edge);
			if (element != null) {
				if ("http://www.kerblue.org/class/1.0".equals(element.eClass().getEPackage().getNsURI())) {
					return (EditPart) new EdgeClazzSwitch(edge).doSwitch(element);
				}
			}

			if (edge.getSemanticModel() instanceof SimpleSemanticModelElement) {
				// Manage the Element that are not associated with a model object
				if (CdSimpleObjectConstants.SIMPLE_OBJECT_ISCOMMENTED.equals(((SimpleSemanticModelElement) edge.getSemanticModel()).getTypeInfo())) {
					return new isCommentedEditPart(edge);
				}
				if (CdSimpleObjectConstants.SIMPLE_OBJECT_ISSTEREOTYPED.equals(((SimpleSemanticModelElement) edge.getSemanticModel()).getTypeInfo())) {
					return new isStereotypedEditPart(edge);
				}
				if (CdSimpleObjectConstants.SIMPLE_OBJECT_ISASSOCIATIONCLASS.equals(((SimpleSemanticModelElement) edge.getSemanticModel()).getTypeInfo())) {
					return new isAssociationClassEditPart(edge);
				}
				if (CdSimpleObjectConstants.SIMPLE_OBJECT_INCLUDE.equals(((SimpleSemanticModelElement) edge.getSemanticModel()).getTypeInfo())) {
					return new includeEditPart(edge);
				}
				if (CdSimpleObjectConstants.SIMPLE_OBJECT_HASVIEW.equals(((SimpleSemanticModelElement) edge.getSemanticModel()).getTypeInfo())) {
					return new hasViewEditPart(edge);
				}
				if (CdSimpleObjectConstants.SIMPLE_OBJECT_GENERALIZATION.equals(((SimpleSemanticModelElement) edge.getSemanticModel()).getTypeInfo())) {
					return new GeneralizationEditPart(edge);
				}
				if (CdSimpleObjectConstants.SIMPLE_OBJECT_HASASPECT.equals(((SimpleSemanticModelElement) edge.getSemanticModel()).getTypeInfo())) {
					return new hasAspectEditPart(edge);
				}
				if (CdSimpleObjectConstants.SIMPLE_OBJECT_DEPENDS.equals(((SimpleSemanticModelElement) edge.getSemanticModel()).getTypeInfo())) {
					return new dependsEditPart(edge);
				}
			}
		}
		return super.createEditPart(context, model);
	}

	/**
	 * @generated
	 */
	private class NodeClazzSwitch extends ClazzSwitch {
		/**
		 * The graphical node
		 * @generated
		 */
		private GraphNode node;

		/**
		 * Constructor
		 * 
		 * @param node the graphical node
		 * @generated
		 */
		public NodeClazzSwitch(GraphNode node) {
			this.node = node;
		}

		/**
		 * @see com.bluexml.side.clazz.util.ClazzSwitch#caseClazz(com.bluexml.side.clazz.Clazz)
		 * @generated
		 */
		public Object caseClazz(com.bluexml.side.clazz.Clazz object) {
			String feature = DIUtils.getPropertyValue(node, ModelerPropertyConstants.ESTRUCTURAL_FEATURE_ID);
			if (!"".equals(feature)) {
				int featureID = Integer.parseInt(feature);
				return new EListEditPart(node, object.eClass().getEStructuralFeature(featureID));
			} else {
				return new ClazzEditPart(node);
			}
		}

		/**
		 * @see com.bluexml.side.clazz.util.ClazzSwitch#caseAspect(com.bluexml.side.clazz.Aspect)
		 * @generated
		 */
		public Object caseAspect(com.bluexml.side.clazz.Aspect object) {
			String feature = DIUtils.getPropertyValue(node, ModelerPropertyConstants.ESTRUCTURAL_FEATURE_ID);
			if (!"".equals(feature)) {
				int featureID = Integer.parseInt(feature);
				return new EListEditPart(node, object.eClass().getEStructuralFeature(featureID));
			} else {
				return new AspectEditPart(node);
			}
		}

		/**
		 * @see org.topcased.MMUseCase.util.OblSwitch#caseAttribute(org.topcased.MMUseCase.Attribute)
		 * @_generated
		 */
		public Object caseAttribute(com.bluexml.side.clazz.Attribute object) {
			return new AttributeEditPart(node, object);
		}

		/**
		 * @see org.topcased.MMUseCase.util.OblSwitch#caseOperation(org.topcased.MMUseCase.Operation)
		 * @_generated
		 */
		public Object caseOperation(com.bluexml.side.common.Operation object) {
			return new OperationEditPart(node, object);
		}

		/**
		 * @see com.bluexml.side.clazz.util.ClazzSwitch#caseClassComment(com.bluexml.side.clazz.ClassComment)
		 * @generated
		 */
		public Object caseClassComment(com.bluexml.side.clazz.ClassComment object) {
			return new ClassCommentEditPart(node);
		}

		/**
		 * @see com.bluexml.side.clazz.util.ClazzSwitch#caseEnumeration(com.bluexml.side.clazz.Enumeration)
		 * @generated
		 */
		public Object caseEnumeration(com.bluexml.side.clazz.Enumeration object) {
			String feature = DIUtils.getPropertyValue(node, ModelerPropertyConstants.ESTRUCTURAL_FEATURE_ID);
			if (!"".equals(feature)) {
				int featureID = Integer.parseInt(feature);
				return new EListEditPart(node, object.eClass().getEStructuralFeature(featureID));
			} else {
				return new EnumerationEditPart(node);
			}
		}

		/**
		 * @see com.bluexml.side.clazz.util.ClazzSwitch#caseEnumerationLiteral(com.bluexml.side.clazz.EnumerationLiteral)
		 * @generated
		 */
		public Object caseEnumerationLiteral(com.bluexml.side.clazz.EnumerationLiteral object) {
			return new EnumerationLiteralEditPart(node);
		}

		
		/**
		 * @see com.bluexml.side.clazz.util.ClazzSwitch#defaultCase(org.eclipse.emf.ecore.EObject)
		 * @generated
		 */
		public Object defaultCase(EObject object) {
			return new EMFGraphNodeEditPart(node);
		}
	}

	/**
	 * @generated
	 */
	private class EdgeClazzSwitch extends ClazzSwitch {
		/**
		 * The graphical edge
		 * @generated
		 */
		private GraphEdge edge;

		/**
		 * Constructor
		 * 
		 * @param edge the graphical edge
		 * @generated
		 */
		public EdgeClazzSwitch(GraphEdge edge) {
			this.edge = edge;
		}

		/**
		 * @see com.bluexml.side.clazz.util.ClazzSwitch#caseAssociation(com.bluexml.side.clazz.Association)
		 * @generated
		 */
		public Object caseAssociation(com.bluexml.side.clazz.Association object) {
			return new AssociationEditPart(edge);
		}

		/**
		 * @see com.bluexml.side.clazz.util.ClazzSwitch#defaultCase(org.eclipse.emf.ecore.EObject)
		 * @generated
		 */
		public Object defaultCase(EObject object) {
			return new EMFGraphEdgeEditPart(edge);
		}
	}

}