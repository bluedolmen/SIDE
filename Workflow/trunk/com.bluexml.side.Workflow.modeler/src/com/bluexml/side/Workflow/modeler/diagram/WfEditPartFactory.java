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
package com.bluexml.side.Workflow.modeler.diagram;

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

import com.bluexml.side.Workflow.modeler.diagram.edit.ActionEditPart;
import com.bluexml.side.Workflow.modeler.diagram.edit.DecisionEditPart;
import com.bluexml.side.Workflow.modeler.diagram.edit.EndStateEditPart;
import com.bluexml.side.Workflow.modeler.diagram.edit.EventEditPart;
import com.bluexml.side.Workflow.modeler.diagram.edit.ForkEditPart;
import com.bluexml.side.Workflow.modeler.diagram.edit.JoinEditPart;
import com.bluexml.side.Workflow.modeler.diagram.edit.NodeEditPart;
import com.bluexml.side.Workflow.modeler.diagram.edit.StartStateEditPart;
import com.bluexml.side.Workflow.modeler.diagram.edit.SwimlaneEditPart;
import com.bluexml.side.Workflow.modeler.diagram.edit.TaskNodeEditPart;
import com.bluexml.side.Workflow.modeler.diagram.edit.TimerEditPart;
import com.bluexml.side.Workflow.modeler.diagram.edit.TransitionEditPart;
import com.bluexml.side.Workflow.modeler.diagram.edit.WfDiagramEditPart;
import com.bluexml.side.Workflow.modeler.diagram.edit.manageEditPart;
import com.bluexml.side.workflow.util.WorkflowSwitch;

/**
 * Part Factory : associates a model object to its controller. <br>
 *
 * @generated
 */
public class WfEditPartFactory extends ModelerEditPartFactory {
	/**
	 * @see org.eclipse.gef.EditPartFactory#createEditPart(org.eclipse.gef.EditPart,java.lang.Object)
	 * @generated
	 */
	public EditPart createEditPart(EditPart context, Object model) {
		if (model instanceof Diagram) {
			return new WfDiagramEditPart((Diagram) model);
		} else if (model instanceof GraphNode) {
			final GraphNode node = (GraphNode) model;
			EObject element = Utils.getElement(node);
			if (element != null) {
				if ("http://www.kerblue.org/workflow/1.0".equals(element
						.eClass().getEPackage().getNsURI())) {
					return (EditPart) new NodeWorkflowSwitch(node)
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
				if ("http://www.kerblue.org/workflow/1.0".equals(element
						.eClass().getEPackage().getNsURI())) {
					return (EditPart) new EdgeWorkflowSwitch(edge)
							.doSwitch(element);
				}
			}

			if (edge.getSemanticModel() instanceof SimpleSemanticModelElement) {
				// Manage the Element that are not associated with a model object
				if (WfSimpleObjectConstants.SIMPLE_OBJECT_MANAGE
						.equals(((SimpleSemanticModelElement) edge
								.getSemanticModel()).getTypeInfo())) {
					return new manageEditPart(edge);
				}
			}
		}
		return super.createEditPart(context, model);
	}

	/**
	 * @generated
	 */
	private class NodeWorkflowSwitch extends WorkflowSwitch {
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
		public NodeWorkflowSwitch(GraphNode node) {
			this.node = node;
		}

		/**
		 * @see com.bluexml.side.workflow.util.WorkflowSwitch#caseTaskNode(com.bluexml.side.workflow.TaskNode)
		 * @generated
		 */
		public Object caseTaskNode(com.bluexml.side.workflow.TaskNode object) {
			String feature = DIUtils.getPropertyValue(node,
					ModelerPropertyConstants.ESTRUCTURAL_FEATURE_ID);
			if (!"".equals(feature)) {
				int featureID = Integer.parseInt(feature);
				return new EListEditPart(node, object.eClass()
						.getEStructuralFeature(featureID));
			} else {
				return new TaskNodeEditPart(node);
			}
		}

		/**
		 * @see com.bluexml.side.workflow.util.WorkflowSwitch#caseNode(com.bluexml.side.workflow.Node)
		 * @generated
		 */
		public Object caseNode(com.bluexml.side.workflow.Node object) {
			String feature = DIUtils.getPropertyValue(node,
					ModelerPropertyConstants.ESTRUCTURAL_FEATURE_ID);
			if (!"".equals(feature)) {
				int featureID = Integer.parseInt(feature);
				return new EListEditPart(node, object.eClass()
						.getEStructuralFeature(featureID));
			} else {
				return new NodeEditPart(node);
			}
		}

		/**
		 * @see com.bluexml.side.workflow.util.WorkflowSwitch#caseStartState(com.bluexml.side.workflow.StartState)
		 * @generated
		 */
		public Object caseStartState(com.bluexml.side.workflow.StartState object) {
			String feature = DIUtils.getPropertyValue(node,
					ModelerPropertyConstants.ESTRUCTURAL_FEATURE_ID);
			if (!"".equals(feature)) {
				int featureID = Integer.parseInt(feature);
				return new EListEditPart(node, object.eClass()
						.getEStructuralFeature(featureID));
			} else {
				return new StartStateEditPart(node);
			}
		}

		/**
		 * @see com.bluexml.side.workflow.util.WorkflowSwitch#caseEndState(com.bluexml.side.workflow.EndState)
		 * @generated
		 */
		public Object caseEndState(com.bluexml.side.workflow.EndState object) {
			String feature = DIUtils.getPropertyValue(node,
					ModelerPropertyConstants.ESTRUCTURAL_FEATURE_ID);
			if (!"".equals(feature)) {
				int featureID = Integer.parseInt(feature);
				return new EListEditPart(node, object.eClass()
						.getEStructuralFeature(featureID));
			} else {
				return new EndStateEditPart(node);
			}
		}

		/**
		 * @see com.bluexml.side.workflow.util.WorkflowSwitch#caseEvent(com.bluexml.side.workflow.Event)
		 * @generated
		 */
		public Object caseEvent(com.bluexml.side.workflow.Event object) {
			return new EventEditPart(node);
		}

		/**
		 * @see com.bluexml.side.workflow.util.WorkflowSwitch#caseAction(com.bluexml.side.workflow.Action)
		 * @generated
		 */
		public Object caseAction(com.bluexml.side.workflow.Action object) {
			return new ActionEditPart(node);
		}

		/**
		 * @see com.bluexml.side.workflow.util.WorkflowSwitch#caseSwimlane(com.bluexml.side.workflow.Swimlane)
		 * @generated
		 */
		public Object caseSwimlane(com.bluexml.side.workflow.Swimlane object) {
			return new SwimlaneEditPart(node);
		}

		/**
		 * @see com.bluexml.side.workflow.util.WorkflowSwitch#caseDecision(com.bluexml.side.workflow.Decision)
		 * @generated
		 */
		public Object caseDecision(com.bluexml.side.workflow.Decision object) {
			return new DecisionEditPart(node);
		}

		/**
		 * @see com.bluexml.side.workflow.util.WorkflowSwitch#caseFork(com.bluexml.side.workflow.Fork)
		 * @generated
		 */
		public Object caseFork(com.bluexml.side.workflow.Fork object) {
			return new ForkEditPart(node);
		}

		/**
		 * @see com.bluexml.side.workflow.util.WorkflowSwitch#caseJoin(com.bluexml.side.workflow.Join)
		 * @generated
		 */
		public Object caseJoin(com.bluexml.side.workflow.Join object) {
			return new JoinEditPart(node);
		}

		/**
		 * @see com.bluexml.side.workflow.util.WorkflowSwitch#caseTimer(com.bluexml.side.workflow.Timer)
		 * @generated
		 */
		public Object caseTimer(com.bluexml.side.workflow.Timer object) {
			return new TimerEditPart(node);
		}

		/**
		 * @see com.bluexml.side.workflow.util.WorkflowSwitch#defaultCase(org.eclipse.emf.ecore.EObject)
		 * @generated
		 */
		public Object defaultCase(EObject object) {
			return new EMFGraphNodeEditPart(node);
		}
	}

	/**
	 * @generated
	 */
	private class EdgeWorkflowSwitch extends WorkflowSwitch {
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
		public EdgeWorkflowSwitch(GraphEdge edge) {
			this.edge = edge;
		}

		/**
		 * @see com.bluexml.side.workflow.util.WorkflowSwitch#caseTransition(com.bluexml.side.workflow.Transition)
		 * @generated
		 */
		public Object caseTransition(com.bluexml.side.workflow.Transition object) {
			return new TransitionEditPart(edge);
		}

		/**
		 * @see com.bluexml.side.workflow.util.WorkflowSwitch#defaultCase(org.eclipse.emf.ecore.EObject)
		 * @generated
		 */
		public Object defaultCase(EObject object) {
			return new EMFGraphEdgeEditPart(edge);
		}
	}

}