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

import org.eclipse.core.resources.IProject;
import org.eclipse.core.resources.ProjectScope;
import org.eclipse.core.runtime.Platform;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.jface.preference.IPreferenceStore;
import org.eclipse.ui.IEditorInput;
import org.eclipse.ui.IFileEditorInput;
import org.eclipse.ui.preferences.ScopedPreferenceStore;
import org.osgi.service.prefs.BackingStoreException;
import org.osgi.service.prefs.Preferences;
import org.topcased.modeler.di.model.GraphEdge;
import org.topcased.modeler.di.model.GraphElement;
import org.topcased.modeler.di.model.GraphNode;
import org.topcased.modeler.editor.AbstractCreationUtils;
import org.topcased.modeler.graphconf.DiagramGraphConf;

import com.bluexml.side.Workflow.modeler.WorkflowPlugin;
import com.bluexml.side.workflow.WorkflowPackage;
import com.bluexml.side.workflow.util.WorkflowSwitch;

/**
 * This utility class allows to create a GraphElement associated with a Model Object
 *
 * @generated
 */
public class WfCreationUtils extends AbstractCreationUtils {

	/**
	 * Constructor
	 *
	 * @param diagramConf the Diagram Graphical Configuration
	 * @generated
	 */
	public WfCreationUtils(DiagramGraphConf diagramConf) {
		super(diagramConf);
	}

	/**
	 * @generated
	 */
	private class GraphicWorkflowSwitch extends WorkflowSwitch {
		/**
		 * The presentation of the graphical element
		 *
		 * @generated
		 */
		private String presentation;

		/**
		 * Constructor
		 *
		 * @param presentation the presentation of the graphical element
		 * @generated
		 */
		public GraphicWorkflowSwitch(String presentation) {
			this.presentation = presentation;
		}

		/**
		 * @see com.bluexml.side.workflow.util.WorkflowSwitch#caseTaskNode(com.bluexml.side.workflow.TaskNode)
		 * @generated
		 */
		public Object caseTaskNode(com.bluexml.side.workflow.TaskNode object) {
			if ("default".equals(presentation)) {
				return createGraphElementTaskNode(object, presentation);
			}
			return null;
		}

		/**
		 * @see com.bluexml.side.workflow.util.WorkflowSwitch#caseNode(com.bluexml.side.workflow.Node)
		 * @generated
		 */
		public Object caseNode(com.bluexml.side.workflow.Node object) {
			if ("default".equals(presentation)) {
				return createGraphElementNode(object, presentation);
			}
			return null;
		}

		/**
		 * @see com.bluexml.side.workflow.util.WorkflowSwitch#caseStartState(com.bluexml.side.workflow.StartState)
		 * @generated
		 */
		public Object caseStartState(com.bluexml.side.workflow.StartState object) {
			if ("default".equals(presentation)) {
				return createGraphElementStartState(object, presentation);
			}
			return null;
		}

		/**
		 * @see com.bluexml.side.workflow.util.WorkflowSwitch#caseEndState(com.bluexml.side.workflow.EndState)
		 * @generated
		 */
		public Object caseEndState(com.bluexml.side.workflow.EndState object) {
			if ("default".equals(presentation)) {
				return createGraphElementEndState(object, presentation);
			}
			return null;
		}

		/**
		 * @see com.bluexml.side.workflow.util.WorkflowSwitch#caseJoin(com.bluexml.side.workflow.Join)
		 * @generated
		 */
		public Object caseJoin(com.bluexml.side.workflow.Join object) {
			if ("default".equals(presentation)) {
				return createGraphElementJoin(object, presentation);
			}
			return null;
		}

		/**
		 * @see com.bluexml.side.workflow.util.WorkflowSwitch#caseFork(com.bluexml.side.workflow.Fork)
		 * @generated
		 */
		public Object caseFork(com.bluexml.side.workflow.Fork object) {
			if ("default".equals(presentation)) {
				return createGraphElementFork(object, presentation);
			}
			return null;
		}

		/**
		 * @see com.bluexml.side.workflow.util.WorkflowSwitch#caseDecision(com.bluexml.side.workflow.Decision)
		 * @generated
		 */
		public Object caseDecision(com.bluexml.side.workflow.Decision object) {
			if ("default".equals(presentation)) {
				return createGraphElementDecision(object, presentation);
			}
			return null;
		}

		/**
		 * @see com.bluexml.side.workflow.util.WorkflowSwitch#caseTimer(com.bluexml.side.workflow.Timer)
		 * @generated
		 */
		public Object caseTimer(com.bluexml.side.workflow.Timer object) {
			if ("default".equals(presentation)) {
				return createGraphElementTimer(object, presentation);
			}
			return null;
		}

		/**
		 * @see com.bluexml.side.workflow.util.WorkflowSwitch#caseAction(com.bluexml.side.workflow.Action)
		 * @generated
		 */
		public Object caseAction(com.bluexml.side.workflow.Action object) {
			if ("default".equals(presentation)) {
				return createGraphElementAction(object, presentation);
			}
			return null;
		}

		/**
		 * @see com.bluexml.side.workflow.util.WorkflowSwitch#caseEvent(com.bluexml.side.workflow.Event)
		 * @generated
		 */
		public Object caseEvent(com.bluexml.side.workflow.Event object) {
			if ("default".equals(presentation)) {
				return createGraphElementEvent(object, presentation);
			}
			return null;
		}

		/**
		 * @see com.bluexml.side.workflow.util.WorkflowSwitch#caseAttribute(com.bluexml.side.workflow.Attribute)
		 * @generated
		 */
		public Object caseAttribute(com.bluexml.side.workflow.Attribute object) {
			if ("default".equals(presentation)) {
				return createGraphElementAttribute(object, presentation);
			}
			return null;
		}

		/**
		 * @see com.bluexml.side.workflow.util.WorkflowSwitch#caseSwimlane(com.bluexml.side.workflow.Swimlane)
		 * @generated
		 */
		public Object caseSwimlane(com.bluexml.side.workflow.Swimlane object) {
			if ("default".equals(presentation)) {
				return createGraphElementSwimlane(object, presentation);
			}
			return null;
		}

		/**
		 * @see com.bluexml.side.workflow.util.WorkflowSwitch#caseTransition(com.bluexml.side.workflow.Transition)
		 * @generated
		 */
		public Object caseTransition(com.bluexml.side.workflow.Transition object) {
			if ("default".equals(presentation)) {
				return createGraphElementTransition(object, presentation);
			}
			return null;
		}

		/**
		 * @see com.bluexml.side.workflow.util.WorkflowSwitch#defaultCase(org.eclipse.emf.ecore.EObject)
		 * @generated
		 */
		public Object defaultCase(EObject object) {
			return null;
		}
	}

	/**
	 * @see org.topcased.modeler.editor.ICreationUtils#createGraphElement(org.eclipse.emf.ecore.EObject, java.lang.String)
	 * @generated
	 */
	public GraphElement createGraphElement(EObject obj, String presentation) {
		Object graphElt = null;

		if ("http://www.kerblue.org/workflow/1.0".equals(obj.eClass()
				.getEPackage().getNsURI())) {
			graphElt = new GraphicWorkflowSwitch(presentation).doSwitch(obj);
		}
		
		return (GraphElement) graphElt;
	}

	/**
	 * @param element the model element
	 * @param presentation the presentation of the graphical element
	 * @return the complete GraphElement
	 * @_generated
	 */
	protected GraphElement createGraphElementTaskNode(
			com.bluexml.side.workflow.TaskNode element, String presentation) {
		GraphNode nodeParent = createGraphNode(element, presentation);

		GraphNode attributes = createGraphNode(element,
				WorkflowPackage.TASK_NODE__ATTRIBUTES, presentation);
		attributes.setContainer(nodeParent);

		GraphNode events = createGraphNode(element,
				WorkflowPackage.TASK_NODE__EVENT, presentation);
		events.setContainer(nodeParent);

		return nodeParent;
	}

	/**
	 * @param element the model element
	 * @param presentation the presentation of the graphical element
	 * @return the complete GraphElement
	 * @_generated
	 */
	protected GraphElement createGraphElementNode(
			com.bluexml.side.workflow.Node element, String presentation) {
		GraphNode nodeParent = createGraphNode(element, presentation);

		GraphNode events = createGraphNode(element,
				WorkflowPackage.NODE__EVENT, presentation);
		events.setContainer(nodeParent);

		return nodeParent;
	}

	/**
	 * @param element the model element
	 * @param presentation the presentation of the graphical element
	 * @return the complete GraphElement
	 * @_generated
	 */
	protected GraphElement createGraphElementStartState(
			com.bluexml.side.workflow.StartState element, String presentation) {
		GraphNode nodeParent = createGraphNode(element, presentation);

		GraphNode attributes = createGraphNode(element,
				WorkflowPackage.START_STATE__ATTRIBUTES, presentation);
		attributes.setContainer(nodeParent);

		GraphNode events = createGraphNode(element,
				WorkflowPackage.START_STATE__EVENT, presentation);
		events.setContainer(nodeParent);

		return nodeParent;
	}

	/**
	 * @param element the model element
	 * @param presentation the presentation of the graphical element
	 * @return the complete GraphElement
	 * @_generated
	 */
	protected GraphElement createGraphElementEndState(
			com.bluexml.side.workflow.EndState element, String presentation) {
		GraphNode nodeParent = createGraphNode(element, presentation);

		GraphNode events = createGraphNode(element,
				WorkflowPackage.END_STATE__EVENT, presentation);
		events.setContainer(nodeParent);

		return nodeParent;
	}

	/**
	 * @param element the model element
	 * @param presentation the presentation of the graphical element
	 * @return the complete GraphElement
	 * @generated
	 */
	protected GraphElement createGraphElementJoin(
			com.bluexml.side.workflow.Join element, String presentation) {
		return createGraphNode(element, presentation);
	}

	/**
	 * @param element the model element
	 * @param presentation the presentation of the graphical element
	 * @return the complete GraphElement
	 * @generated
	 */
	protected GraphElement createGraphElementFork(
			com.bluexml.side.workflow.Fork element, String presentation) {
		return createGraphNode(element, presentation);
	}

	/**
	 * @param element the model element
	 * @param presentation the presentation of the graphical element
	 * @return the complete GraphElement
	 * @generated
	 */
	protected GraphElement createGraphElementDecision(
			com.bluexml.side.workflow.Decision element, String presentation) {
		return createGraphNode(element, presentation);
	}

	/**
	 * @param element the model element
	 * @param presentation the presentation of the graphical element
	 * @return the complete GraphElement
	 * @generated
	 */
	protected GraphElement createGraphElementTimer(
			com.bluexml.side.workflow.Timer element, String presentation) {
		return createGraphNode(element, presentation);
	}

	/**
	 * @param element the model element
	 * @param presentation the presentation of the graphical element
	 * @return the complete GraphElement
	 * @generated
	 */
	protected GraphElement createGraphElementAction(
			com.bluexml.side.workflow.Action element, String presentation) {
		return createGraphNode(element, presentation);
	}

	/**
	 * @param element the model element
	 * @param presentation the presentation of the graphical element
	 * @return the complete GraphElement
	 * @generated
	 */
	protected GraphElement createGraphElementEvent(
			com.bluexml.side.workflow.Event element, String presentation) {
		return createGraphNode(element, presentation);
	}

	/**
	 * @param element the model element
	 * @param presentation the presentation of the graphical element
	 * @return the complete GraphElement
	 * @generated
	 */
	protected GraphElement createGraphElementAttribute(
			com.bluexml.side.workflow.Attribute element, String presentation) {
		return createGraphNode(element, presentation);
	}

	/**
	 * @param element the model element
	 * @param presentation the presentation of the graphical element
	 * @return the complete GraphElement
	 * @generated
	 */
	protected GraphElement createGraphElementSwimlane(
			com.bluexml.side.workflow.Swimlane element, String presentation) {
		return createGraphNode(element, presentation);
	}

	/**
	 * @param element the model element
	 * @param presentation the presentation of the graphical element
	 * @return the complete GraphElement
	 * @generated
	 */
	protected GraphElement createGraphElementTransition(
			com.bluexml.side.workflow.Transition element, String presentation) {
		GraphEdge graphEdge = createGraphEdge(element, presentation);
		return graphEdge;
	}

	/**
	 * Create the ModelObject with its initial children
	 * 
	 * @param obj the model object
	 * @return the model object with its children
	 * @generated
	 */
	public EObject createModelObject(EObject obj) {
		return obj;
	}

	/**
	 * Get the preference store associated with the current editor.
	 * 
	 * @return IPreferenceStore
	 * @generated
	 */
	private IPreferenceStore getPreferenceStore() {
		IEditorInput editorInput = WorkflowPlugin.getActivePage()
				.getActiveEditor().getEditorInput();
		if (editorInput instanceof IFileEditorInput) {
			IProject project = ((IFileEditorInput) editorInput).getFile()
					.getProject();
			Preferences root = Platform.getPreferencesService().getRootNode();
			try {
				if (root.node(ProjectScope.SCOPE).node(project.getName())
						.nodeExists(WorkflowPlugin.getId())) {
					return new ScopedPreferenceStore(new ProjectScope(project),
							WorkflowPlugin.getId());
				}
			} catch (BackingStoreException e) {
				e.printStackTrace();
			}
		}
		return WorkflowPlugin.getDefault().getPreferenceStore();
	}
}
