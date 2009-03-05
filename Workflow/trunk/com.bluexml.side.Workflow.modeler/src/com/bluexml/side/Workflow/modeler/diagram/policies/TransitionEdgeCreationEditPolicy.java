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
package com.bluexml.side.Workflow.modeler.diagram.policies;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.gef.EditDomain;
import org.eclipse.gef.commands.Command;
import org.topcased.modeler.commands.CreateTypedEdgeCommand;
import org.topcased.modeler.di.model.GraphEdge;
import org.topcased.modeler.di.model.GraphElement;
import org.topcased.modeler.edit.policies.AbstractEdgeCreationEditPolicy;
import org.topcased.modeler.utils.SourceTargetData;
import org.topcased.modeler.utils.Utils;

import com.bluexml.side.Workflow.modeler.diagram.commands.TransitionEdgeCreationCommand;
import com.bluexml.side.workflow.Transition;

/**
 * Transition edge creation
 *
 * @generated
 */
public class TransitionEdgeCreationEditPolicy extends
		AbstractEdgeCreationEditPolicy {
	/**
	 * @see org.topcased.modeler.edit.policies.AbstractEdgeCreationEditPolicy#createCommand(org.eclipse.gef.EditDomain, org.topcased.modeler.di.model.GraphEdge, org.topcased.modeler.di.model.GraphElement)
	 * @generated
	 */
	protected CreateTypedEdgeCommand createCommand(EditDomain domain,
			GraphEdge edge, GraphElement source) {
		return new TransitionEdgeCreationCommand(domain, edge, source);
	}

	/**
	 * @see org.topcased.modeler.edit.policies.AbstractEdgeCreationEditPolicy#checkEdge(org.topcased.modeler.di.model.GraphEdge)
	 * @generated
	 */
	protected boolean checkEdge(GraphEdge edge) {
		return Utils.getElement(edge) instanceof Transition;
	}

	/**
	 * @see org.topcased.modeler.edit.policies.AbstractEdgeCreationEditPolicy#checkSource(org.topcased.modeler.di.model.GraphElement)
	 * @generated
	 */
	protected boolean checkSource(GraphElement source) {
		EObject object = Utils.getElement(source);
		if (object instanceof com.bluexml.side.workflow.StartState) {
			return true;
		}
		if (object instanceof com.bluexml.side.workflow.StartState) {
			return true;
		}
		if (object instanceof com.bluexml.side.workflow.StartState) {
			return true;
		}
		if (object instanceof com.bluexml.side.workflow.StartState) {
			return true;
		}
		if (object instanceof com.bluexml.side.workflow.StartState) {
			return true;
		}
		if (object instanceof com.bluexml.side.workflow.StartState) {
			return true;
		}
		if (object instanceof com.bluexml.side.workflow.TaskNode) {
			return true;
		}
		if (object instanceof com.bluexml.side.workflow.TaskNode) {
			return true;
		}
		if (object instanceof com.bluexml.side.workflow.TaskNode) {
			return true;
		}
		if (object instanceof com.bluexml.side.workflow.TaskNode) {
			return true;
		}
		if (object instanceof com.bluexml.side.workflow.TaskNode) {
			return true;
		}
		if (object instanceof com.bluexml.side.workflow.TaskNode) {
			return true;
		}
		if (object instanceof com.bluexml.side.workflow.Decision) {
			return true;
		}
		if (object instanceof com.bluexml.side.workflow.Decision) {
			return true;
		}
		if (object instanceof com.bluexml.side.workflow.Decision) {
			return true;
		}
		if (object instanceof com.bluexml.side.workflow.Decision) {
			return true;
		}
		if (object instanceof com.bluexml.side.workflow.Decision) {
			return true;
		}
		if (object instanceof com.bluexml.side.workflow.Decision) {
			return true;
		}
		if (object instanceof com.bluexml.side.workflow.Fork) {
			return true;
		}
		if (object instanceof com.bluexml.side.workflow.Fork) {
			return true;
		}
		if (object instanceof com.bluexml.side.workflow.Fork) {
			return true;
		}
		if (object instanceof com.bluexml.side.workflow.Fork) {
			return true;
		}
		if (object instanceof com.bluexml.side.workflow.Fork) {
			return true;
		}
		if (object instanceof com.bluexml.side.workflow.Fork) {
			return true;
		}
		if (object instanceof com.bluexml.side.workflow.Join) {
			return true;
		}
		if (object instanceof com.bluexml.side.workflow.Join) {
			return true;
		}
		if (object instanceof com.bluexml.side.workflow.Join) {
			return true;
		}
		if (object instanceof com.bluexml.side.workflow.Join) {
			return true;
		}
		if (object instanceof com.bluexml.side.workflow.Join) {
			return true;
		}
		if (object instanceof com.bluexml.side.workflow.Join) {
			return true;
		}
		if (object instanceof com.bluexml.side.workflow.Node) {
			return true;
		}
		if (object instanceof com.bluexml.side.workflow.Node) {
			return true;
		}
		if (object instanceof com.bluexml.side.workflow.Node) {
			return true;
		}
		if (object instanceof com.bluexml.side.workflow.Node) {
			return true;
		}
		if (object instanceof com.bluexml.side.workflow.Node) {
			return true;
		}
		if (object instanceof com.bluexml.side.workflow.Node) {
			return true;
		}
		if (object instanceof com.bluexml.side.workflow.Node) {
			return true;
		}
		return false;
	}

	/**
	 * @see org.topcased.modeler.edit.policies.AbstractEdgeCreationEditPolicy#checkTargetForSource(org.topcased.modeler.di.model.GraphElement, org.topcased.modeler.di.model.GraphElement)
	 * @generated
	 */
	protected boolean checkTargetForSource(GraphElement source,
			GraphElement target) {
		EObject sourceObject = Utils.getElement(source);
		EObject targetObject = Utils.getElement(target);

		if (sourceObject instanceof com.bluexml.side.workflow.StartState
				&& targetObject instanceof com.bluexml.side.workflow.EndState) {
			if (!sourceObject.equals(targetObject)) {
				return true;
			}
		}

		if (sourceObject instanceof com.bluexml.side.workflow.StartState
				&& targetObject instanceof com.bluexml.side.workflow.Decision) {
			if (!sourceObject.equals(targetObject)) {
				return true;
			}
		}

		if (sourceObject instanceof com.bluexml.side.workflow.StartState
				&& targetObject instanceof com.bluexml.side.workflow.Join) {
			if (!sourceObject.equals(targetObject)) {
				return true;
			}
		}

		if (sourceObject instanceof com.bluexml.side.workflow.StartState
				&& targetObject instanceof com.bluexml.side.workflow.Fork) {
			if (!sourceObject.equals(targetObject)) {
				return true;
			}
		}

		if (sourceObject instanceof com.bluexml.side.workflow.StartState
				&& targetObject instanceof com.bluexml.side.workflow.TaskNode) {
			if (!sourceObject.equals(targetObject)) {
				return true;
			}
		}

		if (sourceObject instanceof com.bluexml.side.workflow.StartState
				&& targetObject instanceof com.bluexml.side.workflow.Node) {
			if (!sourceObject.equals(targetObject)) {
				return true;
			}
		}

		if (sourceObject instanceof com.bluexml.side.workflow.TaskNode
				&& targetObject instanceof com.bluexml.side.workflow.EndState) {
			if (!sourceObject.equals(targetObject)) {
				return true;
			}
		}

		if (sourceObject instanceof com.bluexml.side.workflow.TaskNode
				&& targetObject instanceof com.bluexml.side.workflow.Decision) {
			if (!sourceObject.equals(targetObject)) {
				return true;
			}
		}

		if (sourceObject instanceof com.bluexml.side.workflow.TaskNode
				&& targetObject instanceof com.bluexml.side.workflow.Fork) {
			if (!sourceObject.equals(targetObject)) {
				return true;
			}
		}

		if (sourceObject instanceof com.bluexml.side.workflow.TaskNode
				&& targetObject instanceof com.bluexml.side.workflow.Join) {
			if (!sourceObject.equals(targetObject)) {
				return true;
			}
		}

		if (sourceObject instanceof com.bluexml.side.workflow.TaskNode
				&& targetObject instanceof com.bluexml.side.workflow.TaskNode) {
			if (!sourceObject.equals(targetObject)) {
				return true;
			}
		}

		if (sourceObject instanceof com.bluexml.side.workflow.TaskNode
				&& targetObject instanceof com.bluexml.side.workflow.Node) {
			if (!sourceObject.equals(targetObject)) {
				return true;
			}
		}

		if (sourceObject instanceof com.bluexml.side.workflow.Decision
				&& targetObject instanceof com.bluexml.side.workflow.Decision) {
			if (!sourceObject.equals(targetObject)) {
				return true;
			}
		}

		if (sourceObject instanceof com.bluexml.side.workflow.Decision
				&& targetObject instanceof com.bluexml.side.workflow.Fork) {
			if (!sourceObject.equals(targetObject)) {
				return true;
			}
		}

		if (sourceObject instanceof com.bluexml.side.workflow.Decision
				&& targetObject instanceof com.bluexml.side.workflow.Join) {
			if (!sourceObject.equals(targetObject)) {
				return true;
			}
		}

		if (sourceObject instanceof com.bluexml.side.workflow.Decision
				&& targetObject instanceof com.bluexml.side.workflow.TaskNode) {
			if (!sourceObject.equals(targetObject)) {
				return true;
			}
		}

		if (sourceObject instanceof com.bluexml.side.workflow.Decision
				&& targetObject instanceof com.bluexml.side.workflow.EndState) {
			if (!sourceObject.equals(targetObject)) {
				return true;
			}
		}

		if (sourceObject instanceof com.bluexml.side.workflow.Decision
				&& targetObject instanceof com.bluexml.side.workflow.Node) {
			if (!sourceObject.equals(targetObject)) {
				return true;
			}
		}

		if (sourceObject instanceof com.bluexml.side.workflow.Fork
				&& targetObject instanceof com.bluexml.side.workflow.Decision) {
			if (!sourceObject.equals(targetObject)) {
				return true;
			}
		}

		if (sourceObject instanceof com.bluexml.side.workflow.Fork
				&& targetObject instanceof com.bluexml.side.workflow.Fork) {
			if (!sourceObject.equals(targetObject)) {
				return true;
			}
		}

		if (sourceObject instanceof com.bluexml.side.workflow.Fork
				&& targetObject instanceof com.bluexml.side.workflow.Join) {
			if (!sourceObject.equals(targetObject)) {
				return true;
			}
		}

		if (sourceObject instanceof com.bluexml.side.workflow.Fork
				&& targetObject instanceof com.bluexml.side.workflow.TaskNode) {
			if (!sourceObject.equals(targetObject)) {
				return true;
			}
		}

		if (sourceObject instanceof com.bluexml.side.workflow.Fork
				&& targetObject instanceof com.bluexml.side.workflow.Node) {
			if (!sourceObject.equals(targetObject)) {
				return true;
			}
		}

		if (sourceObject instanceof com.bluexml.side.workflow.Fork
				&& targetObject instanceof com.bluexml.side.workflow.EndState) {
			if (!sourceObject.equals(targetObject)) {
				return true;
			}
		}

		if (sourceObject instanceof com.bluexml.side.workflow.Join
				&& targetObject instanceof com.bluexml.side.workflow.Decision) {
			if (!sourceObject.equals(targetObject)) {
				return true;
			}
		}

		if (sourceObject instanceof com.bluexml.side.workflow.Join
				&& targetObject instanceof com.bluexml.side.workflow.Fork) {
			if (!sourceObject.equals(targetObject)) {
				return true;
			}
		}

		if (sourceObject instanceof com.bluexml.side.workflow.Join
				&& targetObject instanceof com.bluexml.side.workflow.Join) {
			if (!sourceObject.equals(targetObject)) {
				return true;
			}
		}

		if (sourceObject instanceof com.bluexml.side.workflow.Join
				&& targetObject instanceof com.bluexml.side.workflow.TaskNode) {
			if (!sourceObject.equals(targetObject)) {
				return true;
			}
		}

		if (sourceObject instanceof com.bluexml.side.workflow.Join
				&& targetObject instanceof com.bluexml.side.workflow.EndState) {
			if (!sourceObject.equals(targetObject)) {
				return true;
			}
		}

		if (sourceObject instanceof com.bluexml.side.workflow.Join
				&& targetObject instanceof com.bluexml.side.workflow.Node) {
			if (!sourceObject.equals(targetObject)) {
				return true;
			}
		}

		if (sourceObject instanceof com.bluexml.side.workflow.Node
				&& targetObject instanceof com.bluexml.side.workflow.Decision) {
			if (!sourceObject.equals(targetObject)) {
				return true;
			}
		}

		if (sourceObject instanceof com.bluexml.side.workflow.Node
				&& targetObject instanceof com.bluexml.side.workflow.StartState) {
			if (!sourceObject.equals(targetObject)) {
				return true;
			}
		}

		if (sourceObject instanceof com.bluexml.side.workflow.Node
				&& targetObject instanceof com.bluexml.side.workflow.TaskNode) {
			if (!sourceObject.equals(targetObject)) {
				return true;
			}
		}

		if (sourceObject instanceof com.bluexml.side.workflow.Node
				&& targetObject instanceof com.bluexml.side.workflow.Node) {
			if (!sourceObject.equals(targetObject)) {
				return true;
			}
		}

		if (sourceObject instanceof com.bluexml.side.workflow.Node
				&& targetObject instanceof com.bluexml.side.workflow.Fork) {
			if (!sourceObject.equals(targetObject)) {
				return true;
			}
		}

		if (sourceObject instanceof com.bluexml.side.workflow.Node
				&& targetObject instanceof com.bluexml.side.workflow.Join) {
			if (!sourceObject.equals(targetObject)) {
				return true;
			}
		}

		if (sourceObject instanceof com.bluexml.side.workflow.Node
				&& targetObject instanceof com.bluexml.side.workflow.EndState) {
			if (!sourceObject.equals(targetObject)) {
				return true;
			}
		}
		return false;
	}

	/**
	 * @see org.topcased.modeler.edit.policies.AbstractEdgeCreationEditPolicy#checkCommand(org.eclipse.gef.commands.Command)
	 * @generated
	 */
	protected boolean checkCommand(Command command) {
		return command instanceof TransitionEdgeCreationCommand;
	}

	/**
	 * @see org.topcased.modeler.edit.policies.AbstractEdgeCreationEditPolicy#getSourceTargetData(org.topcased.modeler.di.model.GraphElement, org.topcased.modeler.di.model.GraphElement)
	 * @generated
	 */
	protected SourceTargetData getSourceTargetData(GraphElement source,
			GraphElement target) {
		EObject sourceObject = Utils.getElement(source);
		EObject targetObject = Utils.getElement(target);

		if (sourceObject instanceof com.bluexml.side.workflow.StartState
				&& targetObject instanceof com.bluexml.side.workflow.EndState) {
			return new SourceTargetData(false, false, SourceTargetData.SOURCE,
					"com.bluexml.side.workflow.StartState", "transition", null,
					"to", "transition", null, null, null);
		}
		if (sourceObject instanceof com.bluexml.side.workflow.StartState
				&& targetObject instanceof com.bluexml.side.workflow.Decision) {
			return new SourceTargetData(false, false, SourceTargetData.SOURCE,
					"com.bluexml.side.workflow.StartState", "transition", "to",
					null, "transition", null, null, null);
		}
		if (sourceObject instanceof com.bluexml.side.workflow.StartState
				&& targetObject instanceof com.bluexml.side.workflow.Join) {
			return new SourceTargetData(false, false, SourceTargetData.SOURCE,
					"com.bluexml.side.workflow.StartState", "transition", "to",
					null, "transition", null, null, null);
		}
		if (sourceObject instanceof com.bluexml.side.workflow.StartState
				&& targetObject instanceof com.bluexml.side.workflow.Fork) {
			return new SourceTargetData(false, false, SourceTargetData.SOURCE,
					"com.bluexml.side.workflow.StartState", "transition", "to",
					null, "transition", null, null, null);
		}
		if (sourceObject instanceof com.bluexml.side.workflow.StartState
				&& targetObject instanceof com.bluexml.side.workflow.TaskNode) {
			return new SourceTargetData(false, false, SourceTargetData.SOURCE,
					"com.bluexml.side.workflow.StartState", "transition", "to",
					null, "transition", null, null, null);
		}
		if (sourceObject instanceof com.bluexml.side.workflow.StartState
				&& targetObject instanceof com.bluexml.side.workflow.Node) {
			return new SourceTargetData(false, false, SourceTargetData.SOURCE,
					"com.bluexml.side.workflow.StartState", "transition", null,
					"to", "transition", null, null, null);
		}
		if (sourceObject instanceof com.bluexml.side.workflow.TaskNode
				&& targetObject instanceof com.bluexml.side.workflow.EndState) {
			return new SourceTargetData(false, false, SourceTargetData.SOURCE,
					"com.bluexml.side.workflow.TaskNode", "transition",
					"parentTaskNode", "to", "transition", null, null, null);
		}
		if (sourceObject instanceof com.bluexml.side.workflow.TaskNode
				&& targetObject instanceof com.bluexml.side.workflow.Decision) {
			return new SourceTargetData(false, false, SourceTargetData.SOURCE,
					"com.bluexml.side.workflow.TaskNode", "transition",
					"parentTaskNode", "to", "transition", null, null, null);
		}
		if (sourceObject instanceof com.bluexml.side.workflow.TaskNode
				&& targetObject instanceof com.bluexml.side.workflow.Fork) {
			return new SourceTargetData(false, false, SourceTargetData.SOURCE,
					"com.bluexml.side.workflow.TaskNode", "transition",
					"parentTaskNode", "to", "transition", null, null, null);
		}
		if (sourceObject instanceof com.bluexml.side.workflow.TaskNode
				&& targetObject instanceof com.bluexml.side.workflow.Join) {
			return new SourceTargetData(false, false, SourceTargetData.SOURCE,
					"com.bluexml.side.workflow.TaskNode", "transition",
					"parentTaskNode", "to", "transition", null, null, null);
		}
		if (sourceObject instanceof com.bluexml.side.workflow.TaskNode
				&& targetObject instanceof com.bluexml.side.workflow.TaskNode) {
			return new SourceTargetData(false, false, SourceTargetData.SOURCE,
					"com.bluexml.side.workflow.TaskNode", "transition",
					"parentTaskNode", "to", "transition", null, null, null);
		}
		if (sourceObject instanceof com.bluexml.side.workflow.TaskNode
				&& targetObject instanceof com.bluexml.side.workflow.Node) {
			return new SourceTargetData(false, false, SourceTargetData.SOURCE,
					"com.bluexml.side.workflow.TaskNode", "transition",
					"parentTaskNode", "to", "transition", null, null, null);
		}
		if (sourceObject instanceof com.bluexml.side.workflow.Decision
				&& targetObject instanceof com.bluexml.side.workflow.Decision) {
			return new SourceTargetData(false, false, SourceTargetData.SOURCE,
					"com.bluexml.side.workflow.Decision", "transition", null,
					"to", "transition", null, null, null);
		}
		if (sourceObject instanceof com.bluexml.side.workflow.Decision
				&& targetObject instanceof com.bluexml.side.workflow.Fork) {
			return new SourceTargetData(false, false, SourceTargetData.SOURCE,
					"com.bluexml.side.workflow.Decision", "transition", null,
					"to", "transition", null, null, null);
		}
		if (sourceObject instanceof com.bluexml.side.workflow.Decision
				&& targetObject instanceof com.bluexml.side.workflow.Join) {
			return new SourceTargetData(false, false, SourceTargetData.SOURCE,
					"com.bluexml.side.workflow.Decision", "transition", null,
					"to", "transition", null, null, null);
		}
		if (sourceObject instanceof com.bluexml.side.workflow.Decision
				&& targetObject instanceof com.bluexml.side.workflow.TaskNode) {
			return new SourceTargetData(false, false, SourceTargetData.SOURCE,
					"com.bluexml.side.workflow.Decision", "transition", null,
					"to", "transition", null, null, null);
		}
		if (sourceObject instanceof com.bluexml.side.workflow.Decision
				&& targetObject instanceof com.bluexml.side.workflow.EndState) {
			return new SourceTargetData(false, false, SourceTargetData.SOURCE,
					"com.bluexml.side.workflow.Decision", "transition", null,
					"to", "transition", null, null, null);
		}
		if (sourceObject instanceof com.bluexml.side.workflow.Decision
				&& targetObject instanceof com.bluexml.side.workflow.Node) {
			return new SourceTargetData(false, false, SourceTargetData.SOURCE,
					"com.bluexml.side.workflow.Decision", "transition", null,
					"to", "transition", null, null, null);
		}
		if (sourceObject instanceof com.bluexml.side.workflow.Fork
				&& targetObject instanceof com.bluexml.side.workflow.Decision) {
			return new SourceTargetData(false, false, SourceTargetData.SOURCE,
					"com.bluexml.side.workflow.Fork", "transition", null, "to",
					"transition", null, null, null);
		}
		if (sourceObject instanceof com.bluexml.side.workflow.Fork
				&& targetObject instanceof com.bluexml.side.workflow.Fork) {
			return new SourceTargetData(false, false, SourceTargetData.SOURCE,
					"com.bluexml.side.workflow.Fork", "transition", null, "to",
					"transition", null, null, null);
		}
		if (sourceObject instanceof com.bluexml.side.workflow.Fork
				&& targetObject instanceof com.bluexml.side.workflow.Join) {
			return new SourceTargetData(false, false, SourceTargetData.SOURCE,
					"com.bluexml.side.workflow.Fork", "transition", null, "to",
					"transition", null, null, null);
		}
		if (sourceObject instanceof com.bluexml.side.workflow.Fork
				&& targetObject instanceof com.bluexml.side.workflow.TaskNode) {
			return new SourceTargetData(false, false, SourceTargetData.SOURCE,
					"com.bluexml.side.workflow.Fork", "transition", null, "to",
					"transition", null, null, null);
		}
		if (sourceObject instanceof com.bluexml.side.workflow.Fork
				&& targetObject instanceof com.bluexml.side.workflow.Node) {
			return new SourceTargetData(false, false, SourceTargetData.SOURCE,
					"com.bluexml.side.workflow.Fork", "transition", null, "to",
					"transition", null, null, null);
		}
		if (sourceObject instanceof com.bluexml.side.workflow.Fork
				&& targetObject instanceof com.bluexml.side.workflow.EndState) {
			return new SourceTargetData(false, false, SourceTargetData.SOURCE,
					"com.bluexml.side.workflow.Fork", "transition", null, "to",
					"transition", null, null, null);
		}
		if (sourceObject instanceof com.bluexml.side.workflow.Join
				&& targetObject instanceof com.bluexml.side.workflow.Decision) {
			return new SourceTargetData(false, false, SourceTargetData.SOURCE,
					"com.bluexml.side.workflow.Join", "transition", null, "to",
					"transition", null, null, null);
		}
		if (sourceObject instanceof com.bluexml.side.workflow.Join
				&& targetObject instanceof com.bluexml.side.workflow.Fork) {
			return new SourceTargetData(false, false, SourceTargetData.SOURCE,
					"com.bluexml.side.workflow.Join", "transition", null, "to",
					"transition", null, null, null);
		}
		if (sourceObject instanceof com.bluexml.side.workflow.Join
				&& targetObject instanceof com.bluexml.side.workflow.Join) {
			return new SourceTargetData(false, false, SourceTargetData.SOURCE,
					"com.bluexml.side.workflow.Join", "transition", null, "to",
					"transition", null, null, null);
		}
		if (sourceObject instanceof com.bluexml.side.workflow.Join
				&& targetObject instanceof com.bluexml.side.workflow.TaskNode) {
			return new SourceTargetData(false, false, SourceTargetData.SOURCE,
					"com.bluexml.side.workflow.Join", "transition", null, "to",
					"transition", null, null, null);
		}
		if (sourceObject instanceof com.bluexml.side.workflow.Join
				&& targetObject instanceof com.bluexml.side.workflow.EndState) {
			return new SourceTargetData(false, false, SourceTargetData.SOURCE,
					"com.bluexml.side.workflow.Join", "transition", null, "to",
					"transition", null, null, null);
		}
		if (sourceObject instanceof com.bluexml.side.workflow.Join
				&& targetObject instanceof com.bluexml.side.workflow.Node) {
			return new SourceTargetData(false, false, SourceTargetData.SOURCE,
					"com.bluexml.side.workflow.Join", "transition", null, "to",
					"transition", null, null, null);
		}
		if (sourceObject instanceof com.bluexml.side.workflow.Node
				&& targetObject instanceof com.bluexml.side.workflow.Decision) {
			return new SourceTargetData(false, false, SourceTargetData.SOURCE,
					"com.bluexml.side.workflow.Node", "transition", null, "to",
					"transition", null, null, null);
		}
		if (sourceObject instanceof com.bluexml.side.workflow.Node
				&& targetObject instanceof com.bluexml.side.workflow.StartState) {
			return new SourceTargetData(false, false, SourceTargetData.SOURCE,
					"com.bluexml.side.workflow.Node", "transition", null, "to",
					"transition", null, null, null);
		}
		if (sourceObject instanceof com.bluexml.side.workflow.Node
				&& targetObject instanceof com.bluexml.side.workflow.TaskNode) {
			return new SourceTargetData(false, false, SourceTargetData.SOURCE,
					"com.bluexml.side.workflow.Node", "transition", null, "to",
					"transition", null, null, null);
		}
		if (sourceObject instanceof com.bluexml.side.workflow.Node
				&& targetObject instanceof com.bluexml.side.workflow.Node) {
			return new SourceTargetData(false, false, SourceTargetData.SOURCE,
					"com.bluexml.side.workflow.Node", "transition", null, "to",
					"transition", null, null, null);
		}
		if (sourceObject instanceof com.bluexml.side.workflow.Node
				&& targetObject instanceof com.bluexml.side.workflow.Fork) {
			return new SourceTargetData(false, false, SourceTargetData.SOURCE,
					"com.bluexml.side.workflow.Node", "transition", null, "to",
					"transition", null, null, null);
		}
		if (sourceObject instanceof com.bluexml.side.workflow.Node
				&& targetObject instanceof com.bluexml.side.workflow.Join) {
			return new SourceTargetData(false, false, SourceTargetData.SOURCE,
					"com.bluexml.side.workflow.Node", "transition", null, "to",
					"transition", null, null, null);
		}
		if (sourceObject instanceof com.bluexml.side.workflow.Node
				&& targetObject instanceof com.bluexml.side.workflow.EndState) {
			return new SourceTargetData(false, false, SourceTargetData.SOURCE,
					"com.bluexml.side.workflow.Node", "transition", null, "to",
					"transition", null, null, null);
		}
		return null;
	}

}