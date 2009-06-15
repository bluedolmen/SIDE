/*******************************************************************************
 * No CopyrightText Defined in the configurator file.
 ******************************************************************************/
package com.bluexml.side.Requirements.modeler.goalDiagram;

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

import com.bluexml.side.Requirements.modeler.goalDiagram.edit.AgentEditPart;
import com.bluexml.side.Requirements.modeler.goalDiagram.edit.AttributeEditPart;
import com.bluexml.side.Requirements.modeler.goalDiagram.edit.EntityEditPart;
import com.bluexml.side.Requirements.modeler.goalDiagram.edit.GoalEditPart;
import com.bluexml.side.Requirements.modeler.goalDiagram.edit.PrivilegeEditPart;
import com.bluexml.side.Requirements.modeler.goalDiagram.edit.PrivilegeGroupEditPart;
import com.bluexml.side.Requirements.modeler.goalDiagram.edit.RelationShipEditPart;
import com.bluexml.side.Requirements.modeler.goalDiagram.edit.ReqDiagramEditPart;
import com.bluexml.side.Requirements.modeler.goalDiagram.edit.is_responsibleEditPart;
import com.bluexml.side.Requirements.modeler.goalDiagram.edit.is_sub_goalEditPart;
import com.bluexml.side.requirements.util.RequirementsSwitch;

/**
 * Part Factory : associates a model object to its controller. <br>
 *
 * @generated
 */
public class ReqEditPartFactory extends ModelerEditPartFactory {
	/**
	 * @see org.eclipse.gef.EditPartFactory#createEditPart(org.eclipse.gef.EditPart,java.lang.Object)
	 * @generated
	 */
	public EditPart createEditPart(EditPart context, Object model) {
		if (model instanceof Diagram) {
			return new ReqDiagramEditPart((Diagram) model);
		} else if (model instanceof GraphNode) {
			final GraphNode node = (GraphNode) model;
			EObject element = Utils.getElement(node);
			if (element != null) {
				if ("http://www.kerblue.org/requirements/1.0".equals(element
						.eClass().getEPackage().getNsURI())) {
					return (EditPart) new NodeRequirementsSwitch(node)
							.doSwitch(element);
				}
			}

			if (node.getSemanticModel() instanceof SimpleSemanticModelElement) {
				// Manage the Element that are not associated with a model object
			}
		} else if (model instanceof GraphEdge) {
			final GraphEdge edge = (GraphEdge) model;
			EObject element = Utils.getElement(edge);
			if (element != null) {
				if ("http://www.kerblue.org/requirements/1.0".equals(element
						.eClass().getEPackage().getNsURI())) {
					return (EditPart) new EdgeRequirementsSwitch(edge)
							.doSwitch(element);
				}
			}

			if (edge.getSemanticModel() instanceof SimpleSemanticModelElement) {
				// Manage the Element that are not associated with a model object
				if (ReqSimpleObjectConstants.SIMPLE_OBJECT_IS_RESPONSIBLE
						.equals(((SimpleSemanticModelElement) edge
								.getSemanticModel()).getTypeInfo())) {
					return new is_responsibleEditPart(edge);
				}
				if (ReqSimpleObjectConstants.SIMPLE_OBJECT_IS_SUB_GOAL
						.equals(((SimpleSemanticModelElement) edge
								.getSemanticModel()).getTypeInfo())) {
					return new is_sub_goalEditPart(edge);
				}
			}
		}
		return super.createEditPart(context, model);
	}

	/**
	 * @generated
	 */
	private class NodeRequirementsSwitch extends RequirementsSwitch {
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
		public NodeRequirementsSwitch(GraphNode node) {
			this.node = node;
		}

		/**
		 * @see com.bluexml.side.requirements.util.RequirementsSwitch#caseGoal(com.bluexml.side.requirements.Goal)
		 * @generated
		 */
		public Object caseGoal(com.bluexml.side.requirements.Goal object) {
			return new GoalEditPart(node);
		}

		/**
		 * @see com.bluexml.side.requirements.util.RequirementsSwitch#caseAgent(com.bluexml.side.requirements.Agent)
		 * @generated
		 */
		public Object caseAgent(com.bluexml.side.requirements.Agent object) {
			return new AgentEditPart(node);
		}

		/**
		 * @see com.bluexml.side.requirements.util.RequirementsSwitch#caseEntity(com.bluexml.side.requirements.Entity)
		 * @generated
		 */
		public Object caseEntity(com.bluexml.side.requirements.Entity object) {
			String feature = DIUtils.getPropertyValue(node,
					ModelerPropertyConstants.ESTRUCTURAL_FEATURE_ID);
			if (!"".equals(feature)) {
				int featureID = Integer.parseInt(feature);
				return new EListEditPart(node, object.eClass()
						.getEStructuralFeature(featureID));
			} else {
				return new EntityEditPart(node);
			}
		}

		/**
		 * @see com.bluexml.side.requirements.util.RequirementsSwitch#caseAttribute(com.bluexml.side.requirements.Attribute)
		 * @generated
		 */
		public Object caseAttribute(
				com.bluexml.side.requirements.Attribute object) {
			return new AttributeEditPart(node);
		}

		/**
		 * @see com.bluexml.side.requirements.util.RequirementsSwitch#casePrivilege(com.bluexml.side.requirements.Privilege)
		 * @generated
		 */
		public Object casePrivilege(
				com.bluexml.side.requirements.Privilege object) {
			return new PrivilegeEditPart(node);
		}

		/**
		 * @see com.bluexml.side.requirements.util.RequirementsSwitch#defaultCase(org.eclipse.emf.ecore.EObject)
		 * @generated
		 */
		public Object defaultCase(EObject object) {
			return new EMFGraphNodeEditPart(node);
		}
	}

	/**
	 * @generated
	 */
	private class EdgeRequirementsSwitch extends RequirementsSwitch {
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
		public EdgeRequirementsSwitch(GraphEdge edge) {
			this.edge = edge;
		}

		/**
		 * @see com.bluexml.side.requirements.util.RequirementsSwitch#caseRelationShip(com.bluexml.side.requirements.RelationShip)
		 * @generated
		 */
		public Object caseRelationShip(
				com.bluexml.side.requirements.RelationShip object) {
			return new RelationShipEditPart(edge);
		}

		/**
		 * @see com.bluexml.side.requirements.util.RequirementsSwitch#casePrivilegeGroup(com.bluexml.side.requirements.PrivilegeGroup)
		 * @generated
		 */
		public Object casePrivilegeGroup(
				com.bluexml.side.requirements.PrivilegeGroup object) {
			return new PrivilegeGroupEditPart(edge);
		}

		/**
		 * @see com.bluexml.side.requirements.util.RequirementsSwitch#defaultCase(org.eclipse.emf.ecore.EObject)
		 * @generated
		 */
		public Object defaultCase(EObject object) {
			return new EMFGraphEdgeEditPart(edge);
		}
	}

}