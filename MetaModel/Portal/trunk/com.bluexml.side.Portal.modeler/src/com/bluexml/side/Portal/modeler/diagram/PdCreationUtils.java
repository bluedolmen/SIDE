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
package com.bluexml.side.Portal.modeler.diagram;

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
import org.topcased.modeler.editor.AbstractCreationUtils;
import org.topcased.modeler.graphconf.DiagramGraphConf;

import com.bluexml.side.Portal.modeler.PortalPlugin;
import com.bluexml.side.portal.util.PortalSwitch;

/**
 * This utility class allows to create a GraphElement associated with a Model Object
 *
 * @generated
 */
public class PdCreationUtils extends AbstractCreationUtils {

	/**
	 * Constructor
	 *
	 * @param diagramConf the Diagram Graphical Configuration
	 * @generated
	 */
	public PdCreationUtils(DiagramGraphConf diagramConf) {
		super(diagramConf);
	}

	/**
	 * @generated
	 */
	private class GraphicPortalSwitch extends PortalSwitch {
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
		public GraphicPortalSwitch(String presentation) {
			this.presentation = presentation;
		}

		/**
		 * @see com.bluexml.side.util.PortalSwitch#casePage(com.bluexml.side.portal.Page)
		 * @generated
		 */
		public Object casePage(com.bluexml.side.portal.Page object) {
			if ("default".equals(presentation)) {
				return createGraphElementPage(object, presentation);
			}
			return null;
		}

		/**
		 * @see com.bluexml.side.util.PortalSwitch#casePortalLayout(com.bluexml.side.portal.PortalLayout)
		 * @generated
		 */
		public Object casePortalLayout(com.bluexml.side.portal.PortalLayout object) {
			if ("default".equals(presentation)) {
				return createGraphElementPortalLayout(object, presentation);
			}
			return null;
		}

		/**
		 * @see com.bluexml.side.util.PortalSwitch#casePortlet(com.bluexml.side.portal.Portlet)
		 * @generated
		 */
		public Object casePortlet(com.bluexml.side.portal.Portlet object) {
			if ("default".equals(presentation)) {
				return createGraphElementPortlet(object, presentation);
			}
			return null;
		}

		/**
		 * @see com.bluexml.side.util.PortalSwitch#casePortletType(com.bluexml.side.portal.PortletType)
		 * @generated
		 */
		public Object casePortletType(com.bluexml.side.portal.PortletType object) {
			if ("default".equals(presentation)) {
				return createGraphElementPortletType(object, presentation);
			}
			return null;
		}

		/**
		 * @see com.bluexml.side.util.PortalSwitch#casePortletInternal(com.bluexml.side.portal.PortletInternal)
		 * @generated
		 */
		public Object casePortletInternal(com.bluexml.side.portal.PortletInternal object) {
			if ("default".equals(presentation)) {
				return createGraphElementPortletInternal(object, presentation);
			}
			return null;
		}

		/**
		 * @see com.bluexml.side.util.PortalSwitch#caseisChildPage(com.bluexml.side.portal.isChildPage)
		 * @generated
		 */
		public Object caseisChildPage(com.bluexml.side.portal.isChildPage object) {
			if ("default".equals(presentation)) {
				return createGraphElementisChildPage(object, presentation);
			}
			return null;
		}

		/**
		 * @see com.bluexml.side.util.PortalSwitch#caseInstanciatePortletType(com.bluexml.side.portal.InstanciatePortletType)
		 * @generated
		 */
		public Object caseInstanciatePortletType(com.bluexml.side.portal.InstanciatePortletType object) {
			if ("default".equals(presentation)) {
				return createGraphElementInstanciatePortletType(object, presentation);
			}
			return null;
		}

		/**
		 * @see com.bluexml.side.util.PortalSwitch#caseHavePortlet(com.bluexml.side.portal.HavePortlet)
		 * @generated
		 */
		public Object caseHavePortlet(com.bluexml.side.portal.HavePortlet object) {
			if ("default".equals(presentation)) {
				return createGraphElementHavePortlet(object, presentation);
			}
			return null;
		}

		/**
		 * @see com.bluexml.side.util.PortalSwitch#defaultCase(org.eclipse.emf.ecore.EObject)
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

		if ("http://www.kerblue.org/portal/1.0".equals(obj.eClass().getEPackage().getNsURI())) {
			graphElt = new GraphicPortalSwitch(presentation).doSwitch(obj);
		}

		return (GraphElement) graphElt;
	}

	/**
	 * @param element the model element
	 * @param presentation the presentation of the graphical element
	 * @return the complete GraphElement
	 * @generated
	 */
	protected GraphElement createGraphElementPage(com.bluexml.side.portal.Page element, String presentation) {
		return createGraphNode(element, presentation);
	}

	/**
	 * @param element the model element
	 * @param presentation the presentation of the graphical element
	 * @return the complete GraphElement
	 * @generated
	 */
	protected GraphElement createGraphElementPortalLayout(com.bluexml.side.portal.PortalLayout element, String presentation) {
		return createGraphNode(element, presentation);
	}

	/**
	 * @param element the model element
	 * @param presentation the presentation of the graphical element
	 * @return the complete GraphElement
	 * @generated
	 */
	protected GraphElement createGraphElementPortlet(com.bluexml.side.portal.Portlet element, String presentation) {
		return createGraphNode(element, presentation);
	}

	/**
	 * @param element the model element
	 * @param presentation the presentation of the graphical element
	 * @return the complete GraphElement
	 * @generated
	 */
	protected GraphElement createGraphElementPortletType(com.bluexml.side.portal.PortletType element, String presentation) {
		return createGraphNode(element, presentation);
	}

	/**
	 * @param element the model element
	 * @param presentation the presentation of the graphical element
	 * @return the complete GraphElement
	 * @generated
	 */
	protected GraphElement createGraphElementPortletInternal(com.bluexml.side.portal.PortletInternal element, String presentation) {
		return createGraphNode(element, presentation);
	}

	/**
	 * @param element the model element
	 * @param presentation the presentation of the graphical element
	 * @return the complete GraphElement
	 * @generated
	 */
	protected GraphElement createGraphElementisChildPage(com.bluexml.side.portal.isChildPage element, String presentation) {
		GraphEdge graphEdge = createGraphEdge(element, presentation);
		return graphEdge;
	}

	/**
	 * @param element the model element
	 * @param presentation the presentation of the graphical element
	 * @return the complete GraphElement
	 * @generated
	 */
	protected GraphElement createGraphElementInstanciatePortletType(com.bluexml.side.portal.InstanciatePortletType element, String presentation) {
		GraphEdge graphEdge = createGraphEdge(element, presentation);
		return graphEdge;
	}

	/**
	 * @param element the model element
	 * @param presentation the presentation of the graphical element
	 * @return the complete GraphElement
	 * @generated
	 */
	protected GraphElement createGraphElementHavePortlet(com.bluexml.side.portal.HavePortlet element, String presentation) {
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
		IEditorInput editorInput = PortalPlugin.getActivePage().getActiveEditor().getEditorInput();
		if (editorInput instanceof IFileEditorInput) {
			IProject project = ((IFileEditorInput) editorInput).getFile().getProject();
			Preferences root = Platform.getPreferencesService().getRootNode();
			try {
				if (root.node(ProjectScope.SCOPE).node(project.getName()).nodeExists(PortalPlugin.getId())) {
					return new ScopedPreferenceStore(new ProjectScope(project), PortalPlugin.getId());
				}
			} catch (BackingStoreException e) {
				e.printStackTrace();
			}
		}
		return PortalPlugin.getDefault().getPreferenceStore();
	}
}
