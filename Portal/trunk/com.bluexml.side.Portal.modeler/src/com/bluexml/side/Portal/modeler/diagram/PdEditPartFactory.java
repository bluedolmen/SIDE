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

import org.eclipse.emf.ecore.EObject;
import org.eclipse.gef.EditPart;
import org.topcased.modeler.di.model.Diagram;
import org.topcased.modeler.di.model.GraphEdge;
import org.topcased.modeler.di.model.GraphNode;
import org.topcased.modeler.di.model.SimpleSemanticModelElement;
import org.topcased.modeler.edit.EMFGraphEdgeEditPart;
import org.topcased.modeler.edit.EMFGraphNodeEditPart;
import org.topcased.modeler.editor.ModelerEditPartFactory;
import org.topcased.modeler.utils.Utils;

import com.bluexml.side.Portal.modeler.diagram.edit.HavePortletEditPart;
import com.bluexml.side.Portal.modeler.diagram.edit.InstanciatePortletTypeEditPart;
import com.bluexml.side.Portal.modeler.diagram.edit.PageEditPart;
import com.bluexml.side.Portal.modeler.diagram.edit.PdDiagramEditPart;
import com.bluexml.side.Portal.modeler.diagram.edit.PortalLayoutEditPart;
import com.bluexml.side.Portal.modeler.diagram.edit.PortletEditPart;
import com.bluexml.side.Portal.modeler.diagram.edit.PortletInternalEditPart;
import com.bluexml.side.Portal.modeler.diagram.edit.PortletTypeEditPart;
import com.bluexml.side.Portal.modeler.diagram.edit.UseLayoutEditPart;
import com.bluexml.side.Portal.modeler.diagram.edit.isChildPageEditPart;
import com.bluexml.side.Portal.modeler.diagram.edit.isInternalPortletEditPart;
import com.bluexml.side.portal.util.PortalSwitch;

/**
 * Part Factory : associates a model object to its controller. <br>
 *
 * @generated
 */
public class PdEditPartFactory extends ModelerEditPartFactory {
	/**
	 * @see org.eclipse.gef.EditPartFactory#createEditPart(org.eclipse.gef.EditPart,java.lang.Object)
	 * @generated
	 */
	public EditPart createEditPart(EditPart context, Object model) {
		if (model instanceof Diagram) {
			return new PdDiagramEditPart((Diagram) model);
		} else if (model instanceof GraphNode) {
			final GraphNode node = (GraphNode) model;
			EObject element = Utils.getElement(node);
			if (element != null) {
				if ("http://www.kerblue.org/portal/1.0".equals(element.eClass().getEPackage().getNsURI())) {
					return (EditPart) new NodePortalSwitch(node).doSwitch(element);
				}
			}

			if (node.getSemanticModel() instanceof SimpleSemanticModelElement) {
				// Manage the Element that are not associated with a model object
			}
		} else if (model instanceof GraphEdge) {
			final GraphEdge edge = (GraphEdge) model;
			EObject element = Utils.getElement(edge);
			if (element != null) {
				if ("http://www.kerblue.org/portal/1.0".equals(element.eClass().getEPackage().getNsURI())) {
					return (EditPart) new EdgePortalSwitch(edge).doSwitch(element);
				}
			}

			if (edge.getSemanticModel() instanceof SimpleSemanticModelElement) {
				// Manage the Element that are not associated with a model object
				if (PdSimpleObjectConstants.SIMPLE_OBJECT_USELAYOUT.equals(((SimpleSemanticModelElement) edge.getSemanticModel()).getTypeInfo())) {
					return new UseLayoutEditPart(edge);
				}
				if (PdSimpleObjectConstants.SIMPLE_OBJECT_ISINTERNALPORTLET.equals(((SimpleSemanticModelElement) edge.getSemanticModel()).getTypeInfo())) {
					return new isInternalPortletEditPart(edge);
				}
			}
		}
		return super.createEditPart(context, model);
	}

	/**
	 * @generated
	 */
	private class NodePortalSwitch extends PortalSwitch {
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
		public NodePortalSwitch(GraphNode node) {
			this.node = node;
		}

		/**
		 * @see com.bluexml.side.util.PortalSwitch#casePage(com.bluexml.side.portal.Page)
		 * @generated
		 */
		public Object casePage(com.bluexml.side.portal.Page object) {
			return new PageEditPart(node);
		}

		/**
		 * @see com.bluexml.side.util.PortalSwitch#casePortalLayout(com.bluexml.side.portal.PortalLayout)
		 * @generated
		 */
		public Object casePortalLayout(com.bluexml.side.portal.PortalLayout object) {
			return new PortalLayoutEditPart(node);
		}

		/**
		 * @see com.bluexml.side.util.PortalSwitch#casePortlet(com.bluexml.side.portal.Portlet)
		 * @generated
		 */
		public Object casePortlet(com.bluexml.side.portal.Portlet object) {
			return new PortletEditPart(node);
		}

		/**
		 * @see com.bluexml.side.util.PortalSwitch#casePortletType(com.bluexml.side.portal.PortletType)
		 * @generated
		 */
		public Object casePortletType(com.bluexml.side.portal.PortletType object) {
			return new PortletTypeEditPart(node);
		}

		/**
		 * @see com.bluexml.side.util.PortalSwitch#casePortletInternal(com.bluexml.side.portal.PortletInternal)
		 * @generated
		 */
		public Object casePortletInternal(com.bluexml.side.portal.PortletInternal object) {
			return new PortletInternalEditPart(node);
		}

		/**
		 * @see com.bluexml.side.util.PortalSwitch#defaultCase(org.eclipse.emf.ecore.EObject)
		 * @generated
		 */
		public Object defaultCase(EObject object) {
			return new EMFGraphNodeEditPart(node);
		}
	}

	/**
	 * @generated
	 */
	private class EdgePortalSwitch extends PortalSwitch {
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
		public EdgePortalSwitch(GraphEdge edge) {
			this.edge = edge;
		}

		/**
		 * @see com.bluexml.side.util.PortalSwitch#caseisChildPage(com.bluexml.side.portal.isChildPage)
		 * @generated
		 */
		public Object caseisChildPage(com.bluexml.side.portal.isChildPage object) {
			return new isChildPageEditPart(edge);
		}

		/**
		 * @see com.bluexml.side.util.PortalSwitch#caseHavePortlet(com.bluexml.side.portal.HavePortlet)
		 * @generated
		 */
		public Object caseHavePortlet(com.bluexml.side.portal.HavePortlet object) {
			return new HavePortletEditPart(edge);
		}

		/**
		 * @see com.bluexml.side.util.PortalSwitch#caseInstanciatePortletType(com.bluexml.side.portal.InstanciatePortletType)
		 * @generated
		 */
		public Object caseInstanciatePortletType(com.bluexml.side.portal.InstanciatePortletType object) {
			return new InstanciatePortletTypeEditPart(edge);
		}

		/**
		 * @see com.bluexml.side.util.PortalSwitch#defaultCase(org.eclipse.emf.ecore.EObject)
		 * @generated
		 */
		public Object defaultCase(EObject object) {
			return new EMFGraphEdgeEditPart(edge);
		}
	}

}