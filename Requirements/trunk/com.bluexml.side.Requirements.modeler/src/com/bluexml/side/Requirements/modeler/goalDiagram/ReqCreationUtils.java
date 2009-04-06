/*******************************************************************************
 * No CopyrightText Defined in the configurator file.
 ******************************************************************************/
package com.bluexml.side.Requirements.modeler.goalDiagram;

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

import com.bluexml.side.Requirements.modeler.RequirementsPlugin;
import com.bluexml.side.requirements.RequirementsPackage;
import com.bluexml.side.requirements.util.RequirementsSwitch;

/**
 * This utility class allows to create a GraphElement associated with a Model Object
 *
 * @generated
 */
public class ReqCreationUtils extends AbstractCreationUtils {

	/**
	 * Constructor
	 *
	 * @param diagramConf the Diagram Graphical Configuration
	 * @generated
	 */
	public ReqCreationUtils(DiagramGraphConf diagramConf) {
		super(diagramConf);
	}

	/**
	 * @generated
	 */
	private class GraphicRequirementsSwitch extends RequirementsSwitch {
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
		public GraphicRequirementsSwitch(String presentation) {
			this.presentation = presentation;
		}

		/**
		 * @see com.bluexml.side.requirements.util.RequirementsSwitch#caseGoal(com.bluexml.side.requirements.Goal)
		 * @generated
		 */
		public Object caseGoal(com.bluexml.side.requirements.Goal object) {
			if ("default".equals(presentation)) {
				return createGraphElementGoal(object, presentation);
			}
			return null;
		}

		/**
		 * @see com.bluexml.side.requirements.util.RequirementsSwitch#caseAgent(com.bluexml.side.requirements.Agent)
		 * @generated
		 */
		public Object caseAgent(com.bluexml.side.requirements.Agent object) {
			if ("default".equals(presentation)) {
				return createGraphElementAgent(object, presentation);
			}
			return null;
		}

		/**
		 * @see com.bluexml.side.requirements.util.RequirementsSwitch#caseEntity(com.bluexml.side.requirements.Entity)
		 * @generated
		 */
		public Object caseEntity(com.bluexml.side.requirements.Entity object) {
			if ("default".equals(presentation)) {
				return createGraphElementEntity(object, presentation);
			}
			return null;
		}

		/**
		 * @see com.bluexml.side.requirements.util.RequirementsSwitch#caseAttribute(com.bluexml.side.requirements.Attribute)
		 * @generated
		 */
		public Object caseAttribute(
				com.bluexml.side.requirements.Attribute object) {
			if ("default".equals(presentation)) {
				return createGraphElementAttribute(object, presentation);
			}
			return null;
		}

		/**
		 * @see com.bluexml.side.requirements.util.RequirementsSwitch#caseRelationShip(com.bluexml.side.requirements.RelationShip)
		 * @generated
		 */
		public Object caseRelationShip(
				com.bluexml.side.requirements.RelationShip object) {
			if ("default".equals(presentation)) {
				return createGraphElementRelationShip(object, presentation);
			}
			return null;
		}

		/**
		 * @see com.bluexml.side.requirements.util.RequirementsSwitch#casePrivilegeGroup(com.bluexml.side.requirements.PrivilegeGroup)
		 * @generated
		 */
		public Object casePrivilegeGroup(
				com.bluexml.side.requirements.PrivilegeGroup object) {
			if ("default".equals(presentation)) {
				return createGraphElementPrivilegeGroup(object, presentation);
			}
			return null;
		}

		/**
		 * @see com.bluexml.side.requirements.util.RequirementsSwitch#defaultCase(org.eclipse.emf.ecore.EObject)
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

		if ("http://www.kerblue.org/requirements/1.0".equals(obj.eClass()
				.getEPackage().getNsURI())) {
			graphElt = new GraphicRequirementsSwitch(presentation)
					.doSwitch(obj);
		}

		return (GraphElement) graphElt;
	}

	/**
	 * @param element the model element
	 * @param presentation the presentation of the graphical element
	 * @return the complete GraphElement
	 * @generated
	 */
	protected GraphElement createGraphElementGoal(
			com.bluexml.side.requirements.Goal element, String presentation) {
		return createGraphNode(element, presentation);
	}

	/**
	 * @param element the model element
	 * @param presentation the presentation of the graphical element
	 * @return the complete GraphElement
	 * @generated
	 */
	protected GraphElement createGraphElementAgent(
			com.bluexml.side.requirements.Agent element, String presentation) {
		return createGraphNode(element, presentation);
	}

	/**
	 * @param element the model element
	 * @param presentation the presentation of the graphical element
	 * @return the complete GraphElement
	 * @_generated
	 */
	protected GraphElement createGraphElementEntity(com.bluexml.side.requirements.Entity element, String presentation) {
		GraphNode nodeParent = createGraphNode(element, presentation);
		GraphNode attributes = createGraphNode(element, RequirementsPackage.ENTITY__ATTRIBUTES, presentation);
		attributes.setContainer(nodeParent);
		return nodeParent;
	}

	/**
	 * @param element the model element
	 * @param presentation the presentation of the graphical element
	 * @return the complete GraphElement
	 * @generated
	 */
	protected GraphElement createGraphElementAttribute(
			com.bluexml.side.requirements.Attribute element, String presentation) {
		return createGraphNode(element, presentation);
	}

	/**
	 * @param element the model element
	 * @param presentation the presentation of the graphical element
	 * @return the complete GraphElement
	 * @generated
	 */
	protected GraphElement createGraphElementRelationShip(
			com.bluexml.side.requirements.RelationShip element,
			String presentation) {
		GraphEdge graphEdge = createGraphEdge(element, presentation);
		return graphEdge;
	}

	/**
	 * @param element the model element
	 * @param presentation the presentation of the graphical element
	 * @return the complete GraphElement
	 * @generated
	 */
	protected GraphElement createGraphElementPrivilegeGroup(
			com.bluexml.side.requirements.PrivilegeGroup element,
			String presentation) {
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
		IEditorInput editorInput = RequirementsPlugin.getActivePage()
				.getActiveEditor().getEditorInput();
		if (editorInput instanceof IFileEditorInput) {
			IProject project = ((IFileEditorInput) editorInput).getFile()
					.getProject();
			Preferences root = Platform.getPreferencesService().getRootNode();
			try {
				if (root.node(ProjectScope.SCOPE).node(project.getName())
						.nodeExists(RequirementsPlugin.getId())) {
					return new ScopedPreferenceStore(new ProjectScope(project),
							RequirementsPlugin.getId());
				}
			} catch (BackingStoreException e) {
				e.printStackTrace();
			}
		}
		return RequirementsPlugin.getDefault().getPreferenceStore();
	}
}
