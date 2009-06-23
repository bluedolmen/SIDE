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
package com.bluexml.side.Workflow.modeler.diagram.commands;

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

import com.bluexml.side.Workflow.modeler.diagram.WfSimpleObjectConstants;
import com.bluexml.side.clazz.Clazz;
import com.bluexml.side.workflow.Decision;
import com.bluexml.side.workflow.EndState;
import com.bluexml.side.workflow.Fork;
import com.bluexml.side.workflow.Join;
import com.bluexml.side.workflow.Node;
import com.bluexml.side.workflow.ProcessState;
import com.bluexml.side.workflow.StartState;
import com.bluexml.side.workflow.Swimlane;
import com.bluexml.side.workflow.TaskNode;
import com.bluexml.side.workflow.Transition;

/**
 * TaskNode restore connection command
 *
 * @generated
 */
public class TaskNodeRestoreConnectionCommand extends
		AbstractRestoreConnectionCommand {
	/**
	 * @param part the EditPart that is restored
	 * @generated
	 */
	public TaskNodeRestoreConnectionCommand(EditPart part) {
		super(part);
	}

	/**
	 * @see org.topcased.modeler.commands.AbstractRestoreConnectionCommand#initializeCommands()
	 * @generated
	 */
	protected void initializeCommands() {

		GraphElement graphElementSrc = getGraphElement();
		EObject eObjectSrc = Utils.getElement(graphElementSrc);

		if (eObjectSrc instanceof TaskNode) {
			for (GraphElement graphElementTgt : getAllGraphElements()) {
				boolean autoRef = graphElementTgt.equals(graphElementSrc);

				EObject eObjectTgt = Utils.getElement(graphElementTgt);

				if (eObjectTgt instanceof StartState) {
					if (autoRef) {
						// autoRef not allowed
					} else {
						// if graphElementSrc is the target of the edge or if it is the source and that the SourceTargetCouple is reversible
						createTransitionFromStartStateToTaskNode_To(
								graphElementTgt, graphElementSrc);
					}
				}

				if (eObjectTgt instanceof EndState) {
					if (autoRef) {
						// autoRef not allowed
					} else {
						// if the graphElementSrc is the source of the edge or if it is the target and that the SourceTargetCouple is reversible
						createTransitionFromTaskNodeToEndState_To(
								graphElementSrc, graphElementTgt);
					}
				}

				if (eObjectTgt instanceof Decision) {
					if (autoRef) {
						// autoRef not allowed
					} else {
						// if the graphElementSrc is the source of the edge or if it is the target and that the SourceTargetCouple is reversible
						createTransitionFromTaskNodeToDecision_To(
								graphElementSrc, graphElementTgt);
					}
				}

				if (eObjectTgt instanceof Fork) {
					if (autoRef) {
						// autoRef not allowed
					} else {
						// if the graphElementSrc is the source of the edge or if it is the target and that the SourceTargetCouple is reversible
						createTransitionFromTaskNodeToFork_To(graphElementSrc,
								graphElementTgt);
					}
				}

				if (eObjectTgt instanceof Join) {
					if (autoRef) {
						// autoRef not allowed
					} else {
						// if the graphElementSrc is the source of the edge or if it is the target and that the SourceTargetCouple is reversible
						createTransitionFromTaskNodeToJoin_To(graphElementSrc,
								graphElementTgt);
					}
				}

				if (eObjectTgt instanceof TaskNode) {
					if (autoRef) {
						// autoRef not allowed
					} else {
						// if the graphElementSrc is the source of the edge or if it is the target and that the SourceTargetCouple is reversible
						createTransitionFromTaskNodeToTaskNode_To(
								graphElementSrc, graphElementTgt);
						// if graphElementSrc is the target of the edge or if it is the source and that the SourceTargetCouple is reversible
						createTransitionFromTaskNodeToTaskNode_To(
								graphElementTgt, graphElementSrc);
					}
				}

				if (eObjectTgt instanceof Node) {
					if (autoRef) {
						// autoRef not allowed
					} else {
						// if the graphElementSrc is the source of the edge or if it is the target and that the SourceTargetCouple is reversible
						createTransitionFromTaskNodeToNode_To(graphElementSrc,
								graphElementTgt);
					}
				}

				if (eObjectTgt instanceof ProcessState) {
					if (autoRef) {
						// autoRef not allowed
					} else {
						// if the graphElementSrc is the source of the edge or if it is the target and that the SourceTargetCouple is reversible
						createTransitionFromTaskNodeToProcessState_To(
								graphElementSrc, graphElementTgt);
					}
				}

				if (eObjectTgt instanceof Decision) {
					if (autoRef) {
						// autoRef not allowed
					} else {
						// if graphElementSrc is the target of the edge or if it is the source and that the SourceTargetCouple is reversible
						createTransitionFromDecisionToTaskNode_To(
								graphElementTgt, graphElementSrc);
					}
				}

				if (eObjectTgt instanceof Fork) {
					if (autoRef) {
						// autoRef not allowed
					} else {
						// if graphElementSrc is the target of the edge or if it is the source and that the SourceTargetCouple is reversible
						createTransitionFromForkToTaskNode_To(graphElementTgt,
								graphElementSrc);
					}
				}

				if (eObjectTgt instanceof Join) {
					if (autoRef) {
						// autoRef not allowed
					} else {
						// if graphElementSrc is the target of the edge or if it is the source and that the SourceTargetCouple is reversible
						createTransitionFromJoinToTaskNode_To(graphElementTgt,
								graphElementSrc);
					}
				}

				if (eObjectTgt instanceof Node) {
					if (autoRef) {
						// autoRef not allowed
					} else {
						// if graphElementSrc is the target of the edge or if it is the source and that the SourceTargetCouple is reversible
						createTransitionFromNodeToTaskNode_To(graphElementTgt,
								graphElementSrc);
					}
				}

				if (eObjectTgt instanceof ProcessState) {
					if (autoRef) {
						// autoRef not allowed
					} else {
						// if graphElementSrc is the target of the edge or if it is the source and that the SourceTargetCouple is reversible
						createTransitionFromProcessStateToTaskNode_To(
								graphElementTgt, graphElementSrc);
					}
				}

				if (eObjectTgt instanceof Swimlane) {
					if (autoRef) {
						// autoRef not allowed
					} else {
						// if graphElementSrc is the target of the edge or if it is the source and that the SourceTargetCouple is reversible
						createmanageFromSwimlaneToTaskNode_Swimlane(
								graphElementTgt, graphElementSrc);
					}
				}
				if (eObjectTgt instanceof Clazz) {
					if (autoRef) {
						// autoRef not allowed
					} else {
						// if the graphElementSrc is the source of the edge or if it is the target and that the SourceTargetCouple is reversible
						createisAssociatedWithFromTaskNodeToClazz(
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
	private void createTransitionFromStartStateToTaskNode_To(
			GraphElement srcElt, GraphElement targetElt) {
		StartState sourceObject = (StartState) Utils.getElement(srcElt);
		TaskNode targetObject = (TaskNode) Utils.getElement(targetElt);

		EList edgeObjectList = sourceObject.getTransition();
		for (Iterator it = edgeObjectList.iterator(); it.hasNext();) {
			Object obj = it.next();
			if (obj instanceof Transition) {
				Transition edgeObject = (Transition) obj;
				if (targetObject.equals(edgeObject.getTo())
						&& sourceObject.getTransition().contains(edgeObject)) {
					// check if the relation does not exists yet
					List<GraphEdge> existing = getExistingEdges(srcElt,
							targetElt, Transition.class);
					if (!isAlreadyPresent(existing, edgeObject)) {
						ICreationUtils factory = getModeler()
								.getActiveConfiguration().getCreationUtils();
						// restore the link with its default presentation
						GraphElement edge = factory
								.createGraphElement(edgeObject);
						if (edge instanceof GraphEdge) {
							TransitionEdgeCreationCommand cmd = new TransitionEdgeCreationCommand(
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
	private void createTransitionFromTaskNodeToEndState_To(GraphElement srcElt,
			GraphElement targetElt) {
		TaskNode sourceObject = (TaskNode) Utils.getElement(srcElt);
		EndState targetObject = (EndState) Utils.getElement(targetElt);

		EList edgeObjectList = sourceObject.getTransition();
		for (Iterator it = edgeObjectList.iterator(); it.hasNext();) {
			Object obj = it.next();
			if (obj instanceof Transition) {
				Transition edgeObject = (Transition) obj;
				if (targetObject.equals(edgeObject.getTo())
						&& sourceObject.equals(edgeObject.getParentTaskNode())
						&& sourceObject.getTransition().contains(edgeObject)) {
					// check if the relation does not exists yet
					List<GraphEdge> existing = getExistingEdges(srcElt,
							targetElt, Transition.class);
					if (!isAlreadyPresent(existing, edgeObject)) {
						ICreationUtils factory = getModeler()
								.getActiveConfiguration().getCreationUtils();
						// restore the link with its default presentation
						GraphElement edge = factory
								.createGraphElement(edgeObject);
						if (edge instanceof GraphEdge) {
							TransitionEdgeCreationCommand cmd = new TransitionEdgeCreationCommand(
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
	private void createTransitionFromTaskNodeToDecision_To(GraphElement srcElt,
			GraphElement targetElt) {
		TaskNode sourceObject = (TaskNode) Utils.getElement(srcElt);
		Decision targetObject = (Decision) Utils.getElement(targetElt);

		EList edgeObjectList = sourceObject.getTransition();
		for (Iterator it = edgeObjectList.iterator(); it.hasNext();) {
			Object obj = it.next();
			if (obj instanceof Transition) {
				Transition edgeObject = (Transition) obj;
				if (targetObject.equals(edgeObject.getTo())
						&& sourceObject.equals(edgeObject.getParentTaskNode())
						&& sourceObject.getTransition().contains(edgeObject)) {
					// check if the relation does not exists yet
					List<GraphEdge> existing = getExistingEdges(srcElt,
							targetElt, Transition.class);
					if (!isAlreadyPresent(existing, edgeObject)) {
						ICreationUtils factory = getModeler()
								.getActiveConfiguration().getCreationUtils();
						// restore the link with its default presentation
						GraphElement edge = factory
								.createGraphElement(edgeObject);
						if (edge instanceof GraphEdge) {
							TransitionEdgeCreationCommand cmd = new TransitionEdgeCreationCommand(
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
	private void createTransitionFromTaskNodeToFork_To(GraphElement srcElt,
			GraphElement targetElt) {
		TaskNode sourceObject = (TaskNode) Utils.getElement(srcElt);
		Fork targetObject = (Fork) Utils.getElement(targetElt);

		EList edgeObjectList = sourceObject.getTransition();
		for (Iterator it = edgeObjectList.iterator(); it.hasNext();) {
			Object obj = it.next();
			if (obj instanceof Transition) {
				Transition edgeObject = (Transition) obj;
				if (targetObject.equals(edgeObject.getTo())
						&& sourceObject.equals(edgeObject.getParentTaskNode())
						&& sourceObject.getTransition().contains(edgeObject)) {
					// check if the relation does not exists yet
					List<GraphEdge> existing = getExistingEdges(srcElt,
							targetElt, Transition.class);
					if (!isAlreadyPresent(existing, edgeObject)) {
						ICreationUtils factory = getModeler()
								.getActiveConfiguration().getCreationUtils();
						// restore the link with its default presentation
						GraphElement edge = factory
								.createGraphElement(edgeObject);
						if (edge instanceof GraphEdge) {
							TransitionEdgeCreationCommand cmd = new TransitionEdgeCreationCommand(
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
	private void createTransitionFromTaskNodeToJoin_To(GraphElement srcElt,
			GraphElement targetElt) {
		TaskNode sourceObject = (TaskNode) Utils.getElement(srcElt);
		Join targetObject = (Join) Utils.getElement(targetElt);

		EList edgeObjectList = sourceObject.getTransition();
		for (Iterator it = edgeObjectList.iterator(); it.hasNext();) {
			Object obj = it.next();
			if (obj instanceof Transition) {
				Transition edgeObject = (Transition) obj;
				if (targetObject.equals(edgeObject.getTo())
						&& sourceObject.equals(edgeObject.getParentTaskNode())
						&& sourceObject.getTransition().contains(edgeObject)) {
					// check if the relation does not exists yet
					List<GraphEdge> existing = getExistingEdges(srcElt,
							targetElt, Transition.class);
					if (!isAlreadyPresent(existing, edgeObject)) {
						ICreationUtils factory = getModeler()
								.getActiveConfiguration().getCreationUtils();
						// restore the link with its default presentation
						GraphElement edge = factory
								.createGraphElement(edgeObject);
						if (edge instanceof GraphEdge) {
							TransitionEdgeCreationCommand cmd = new TransitionEdgeCreationCommand(
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
	private void createTransitionFromTaskNodeToTaskNode_To(GraphElement srcElt,
			GraphElement targetElt) {
		TaskNode sourceObject = (TaskNode) Utils.getElement(srcElt);
		TaskNode targetObject = (TaskNode) Utils.getElement(targetElt);

		EList edgeObjectList = sourceObject.getTransition();
		for (Iterator it = edgeObjectList.iterator(); it.hasNext();) {
			Object obj = it.next();
			if (obj instanceof Transition) {
				Transition edgeObject = (Transition) obj;
				if (targetObject.equals(edgeObject.getTo())
						&& sourceObject.equals(edgeObject.getParentTaskNode())
						&& sourceObject.getTransition().contains(edgeObject)) {
					// check if the relation does not exists yet
					List<GraphEdge> existing = getExistingEdges(srcElt,
							targetElt, Transition.class);
					if (!isAlreadyPresent(existing, edgeObject)) {
						ICreationUtils factory = getModeler()
								.getActiveConfiguration().getCreationUtils();
						// restore the link with its default presentation
						GraphElement edge = factory
								.createGraphElement(edgeObject);
						if (edge instanceof GraphEdge) {
							TransitionEdgeCreationCommand cmd = new TransitionEdgeCreationCommand(
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
	private void createTransitionFromTaskNodeToNode_To(GraphElement srcElt,
			GraphElement targetElt) {
		TaskNode sourceObject = (TaskNode) Utils.getElement(srcElt);
		Node targetObject = (Node) Utils.getElement(targetElt);

		EList edgeObjectList = sourceObject.getTransition();
		for (Iterator it = edgeObjectList.iterator(); it.hasNext();) {
			Object obj = it.next();
			if (obj instanceof Transition) {
				Transition edgeObject = (Transition) obj;
				if (targetObject.equals(edgeObject.getTo())
						&& sourceObject.equals(edgeObject.getParentTaskNode())
						&& sourceObject.getTransition().contains(edgeObject)) {
					// check if the relation does not exists yet
					List<GraphEdge> existing = getExistingEdges(srcElt,
							targetElt, Transition.class);
					if (!isAlreadyPresent(existing, edgeObject)) {
						ICreationUtils factory = getModeler()
								.getActiveConfiguration().getCreationUtils();
						// restore the link with its default presentation
						GraphElement edge = factory
								.createGraphElement(edgeObject);
						if (edge instanceof GraphEdge) {
							TransitionEdgeCreationCommand cmd = new TransitionEdgeCreationCommand(
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
	private void createTransitionFromTaskNodeToProcessState_To(
			GraphElement srcElt, GraphElement targetElt) {
		TaskNode sourceObject = (TaskNode) Utils.getElement(srcElt);
		ProcessState targetObject = (ProcessState) Utils.getElement(targetElt);

		EList edgeObjectList = sourceObject.getTransition();
		for (Iterator it = edgeObjectList.iterator(); it.hasNext();) {
			Object obj = it.next();
			if (obj instanceof Transition) {
				Transition edgeObject = (Transition) obj;
				if (targetObject.equals(edgeObject.getTo())
						&& sourceObject.getTransition().contains(edgeObject)) {
					// check if the relation does not exists yet
					List<GraphEdge> existing = getExistingEdges(srcElt,
							targetElt, Transition.class);
					if (!isAlreadyPresent(existing, edgeObject)) {
						ICreationUtils factory = getModeler()
								.getActiveConfiguration().getCreationUtils();
						// restore the link with its default presentation
						GraphElement edge = factory
								.createGraphElement(edgeObject);
						if (edge instanceof GraphEdge) {
							TransitionEdgeCreationCommand cmd = new TransitionEdgeCreationCommand(
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
	private void createTransitionFromDecisionToTaskNode_To(GraphElement srcElt,
			GraphElement targetElt) {
		Decision sourceObject = (Decision) Utils.getElement(srcElt);
		TaskNode targetObject = (TaskNode) Utils.getElement(targetElt);

		EList edgeObjectList = sourceObject.getTransition();
		for (Iterator it = edgeObjectList.iterator(); it.hasNext();) {
			Object obj = it.next();
			if (obj instanceof Transition) {
				Transition edgeObject = (Transition) obj;
				if (targetObject.equals(edgeObject.getTo())
						&& sourceObject.getTransition().contains(edgeObject)) {
					// check if the relation does not exists yet
					List<GraphEdge> existing = getExistingEdges(srcElt,
							targetElt, Transition.class);
					if (!isAlreadyPresent(existing, edgeObject)) {
						ICreationUtils factory = getModeler()
								.getActiveConfiguration().getCreationUtils();
						// restore the link with its default presentation
						GraphElement edge = factory
								.createGraphElement(edgeObject);
						if (edge instanceof GraphEdge) {
							TransitionEdgeCreationCommand cmd = new TransitionEdgeCreationCommand(
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
	private void createTransitionFromForkToTaskNode_To(GraphElement srcElt,
			GraphElement targetElt) {
		Fork sourceObject = (Fork) Utils.getElement(srcElt);
		TaskNode targetObject = (TaskNode) Utils.getElement(targetElt);

		EList edgeObjectList = sourceObject.getTransition();
		for (Iterator it = edgeObjectList.iterator(); it.hasNext();) {
			Object obj = it.next();
			if (obj instanceof Transition) {
				Transition edgeObject = (Transition) obj;
				if (targetObject.equals(edgeObject.getTo())
						&& sourceObject.getTransition().contains(edgeObject)) {
					// check if the relation does not exists yet
					List<GraphEdge> existing = getExistingEdges(srcElt,
							targetElt, Transition.class);
					if (!isAlreadyPresent(existing, edgeObject)) {
						ICreationUtils factory = getModeler()
								.getActiveConfiguration().getCreationUtils();
						// restore the link with its default presentation
						GraphElement edge = factory
								.createGraphElement(edgeObject);
						if (edge instanceof GraphEdge) {
							TransitionEdgeCreationCommand cmd = new TransitionEdgeCreationCommand(
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
	private void createTransitionFromJoinToTaskNode_To(GraphElement srcElt,
			GraphElement targetElt) {
		Join sourceObject = (Join) Utils.getElement(srcElt);
		TaskNode targetObject = (TaskNode) Utils.getElement(targetElt);

		EList edgeObjectList = sourceObject.getTransition();
		for (Iterator it = edgeObjectList.iterator(); it.hasNext();) {
			Object obj = it.next();
			if (obj instanceof Transition) {
				Transition edgeObject = (Transition) obj;
				if (targetObject.equals(edgeObject.getTo())
						&& sourceObject.getTransition().contains(edgeObject)) {
					// check if the relation does not exists yet
					List<GraphEdge> existing = getExistingEdges(srcElt,
							targetElt, Transition.class);
					if (!isAlreadyPresent(existing, edgeObject)) {
						ICreationUtils factory = getModeler()
								.getActiveConfiguration().getCreationUtils();
						// restore the link with its default presentation
						GraphElement edge = factory
								.createGraphElement(edgeObject);
						if (edge instanceof GraphEdge) {
							TransitionEdgeCreationCommand cmd = new TransitionEdgeCreationCommand(
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
	private void createTransitionFromNodeToTaskNode_To(GraphElement srcElt,
			GraphElement targetElt) {
		Node sourceObject = (Node) Utils.getElement(srcElt);
		TaskNode targetObject = (TaskNode) Utils.getElement(targetElt);

		EList edgeObjectList = sourceObject.getTransition();
		for (Iterator it = edgeObjectList.iterator(); it.hasNext();) {
			Object obj = it.next();
			if (obj instanceof Transition) {
				Transition edgeObject = (Transition) obj;
				if (targetObject.equals(edgeObject.getTo())
						&& sourceObject.getTransition().contains(edgeObject)) {
					// check if the relation does not exists yet
					List<GraphEdge> existing = getExistingEdges(srcElt,
							targetElt, Transition.class);
					if (!isAlreadyPresent(existing, edgeObject)) {
						ICreationUtils factory = getModeler()
								.getActiveConfiguration().getCreationUtils();
						// restore the link with its default presentation
						GraphElement edge = factory
								.createGraphElement(edgeObject);
						if (edge instanceof GraphEdge) {
							TransitionEdgeCreationCommand cmd = new TransitionEdgeCreationCommand(
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
	private void createTransitionFromProcessStateToTaskNode_To(
			GraphElement srcElt, GraphElement targetElt) {
		ProcessState sourceObject = (ProcessState) Utils.getElement(srcElt);
		TaskNode targetObject = (TaskNode) Utils.getElement(targetElt);

		EList edgeObjectList = sourceObject.getTransition();
		for (Iterator it = edgeObjectList.iterator(); it.hasNext();) {
			Object obj = it.next();
			if (obj instanceof Transition) {
				Transition edgeObject = (Transition) obj;
				if (targetObject.equals(edgeObject.getTo())
						&& sourceObject.getTransition().contains(edgeObject)) {
					// check if the relation does not exists yet
					List<GraphEdge> existing = getExistingEdges(srcElt,
							targetElt, Transition.class);
					if (!isAlreadyPresent(existing, edgeObject)) {
						ICreationUtils factory = getModeler()
								.getActiveConfiguration().getCreationUtils();
						// restore the link with its default presentation
						GraphElement edge = factory
								.createGraphElement(edgeObject);
						if (edge instanceof GraphEdge) {
							TransitionEdgeCreationCommand cmd = new TransitionEdgeCreationCommand(
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
	private void createmanageFromSwimlaneToTaskNode_Swimlane(
			GraphElement srcElt, GraphElement targetElt) {
		Swimlane sourceObject = (Swimlane) Utils.getElement(srcElt);
		TaskNode targetObject = (TaskNode) Utils.getElement(targetElt);

		if (sourceObject.getManage().contains(targetObject)
				&& sourceObject.equals(targetObject.getSwimlane())) {
			// check if the relation does not exists yet
			if (getExistingEdges(srcElt, targetElt,
					WfSimpleObjectConstants.SIMPLE_OBJECT_MANAGE).size() == 0) {
				GraphEdge edge = Utils
						.createGraphEdge(WfSimpleObjectConstants.SIMPLE_OBJECT_MANAGE);
				manageEdgeCreationCommand cmd = new manageEdgeCreationCommand(
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
	private void createisAssociatedWithFromTaskNodeToClazz(GraphElement srcElt,
			GraphElement targetElt) {
		TaskNode sourceObject = (TaskNode) Utils.getElement(srcElt);
		Clazz targetObject = (Clazz) Utils.getElement(targetElt);

		if (sourceObject.getClazz().contains(targetObject)) {
			// check if the relation does not exists yet
			if (getExistingEdges(srcElt, targetElt,
					WfSimpleObjectConstants.SIMPLE_OBJECT_ISASSOCIATEDWITH)
					.size() == 0) {
				GraphEdge edge = Utils
						.createGraphEdge(WfSimpleObjectConstants.SIMPLE_OBJECT_ISASSOCIATEDWITH);
				isAssociatedWithEdgeCreationCommand cmd = new isAssociatedWithEdgeCreationCommand(
						null, edge, srcElt, false);
				cmd.setTarget(targetElt);
				add(cmd);
			}
		}
	}

}