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
package com.bluexml.side.Class.modeler.diagram.figures;

import org.eclipse.draw2d.ConnectionEndpointLocator;
import org.eclipse.draw2d.Locator;
import org.topcased.modeler.edit.locators.EdgeObjectOffsetLocator;
import org.topcased.modeler.figures.EdgeObjectEditableLabel;
import org.topcased.modeler.figures.EdgeObjectOffsetEditableLabel;
import org.topcased.modeler.figures.IEdgeObjectFigure;
import org.topcased.modeler.figures.IEdgeObjectOffsetFigure;
import org.topcased.modeler.figures.IGraphEdgeFigure;

import com.bluexml.side.Class.modeler.diagram.figure.ConveyorLineFigure;


/**
 * <!-- begin-user-doc --> <!-- end-user-doc -->
 * 
 * @_generated
 */
public class AssociationFigure extends ConveyorLineFigure implements
		IGraphEdgeFigure {

	private IEdgeObjectFigure srcNameEdgeObject;

	private Locator srcCountLocator;

	private IEdgeObjectFigure targetNameEdgeObject;

	private Locator srcNameLocator;

	private IEdgeObjectFigure srcCountEdgeObject;

	private Locator targetCountLocator;

	private IEdgeObjectFigure middleNameEdgeObject;

	private Locator targetNameLocator;

	private IEdgeObjectFigure targetCountEdgeObject;

	private Locator middleNameLocator;

	/**
	 * The constructor <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @_generated
	 */
	public AssociationFigure() {
		super();

		srcNameEdgeObject = new EdgeObjectEditableLabel(this);
		srcNameLocator = new ConnectionEndpointLocator(this, false);
		add(srcNameEdgeObject, srcNameLocator);
		srcCountEdgeObject = new EdgeObjectEditableLabel(this);
		srcCountLocator = new ConnectionEndpointLocator(this, false);
		add(srcCountEdgeObject, srcCountLocator);
		targetNameEdgeObject = new EdgeObjectEditableLabel(this);
		targetNameLocator = new ConnectionEndpointLocator(this, true);
		add(targetNameEdgeObject, targetNameLocator);
		targetCountEdgeObject = new EdgeObjectEditableLabel(this);
		targetCountLocator = new ConnectionEndpointLocator(this, true);
		add(targetCountEdgeObject, targetCountLocator);
		middleNameEdgeObject = new EdgeObjectOffsetEditableLabel(this);
		middleNameLocator = new EdgeObjectOffsetLocator(
				(IEdgeObjectOffsetFigure) middleNameEdgeObject);
		add(middleNameEdgeObject, middleNameLocator);
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @return the object figure
	 * @_generated
	 */
	public IEdgeObjectFigure getsrcNameEdgeObjectFigure() {
		return srcNameEdgeObject;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @return the object figure
	 * @_generated
	 */
	public IEdgeObjectFigure getsrcCountEdgeObjectFigure() {
		return srcCountEdgeObject;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @return the object figure
	 * @_generated
	 */
	public IEdgeObjectFigure gettargetNameEdgeObjectFigure() {
		return targetNameEdgeObject;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @return the object figure
	 * @_generated
	 */
	public IEdgeObjectFigure gettargetCountEdgeObjectFigure() {
		return targetCountEdgeObject;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @return the object figure
	 * @_generated
	 */
	public IEdgeObjectFigure getmiddleNameEdgeObjectFigure() {
		return middleNameEdgeObject;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @see org.topcased.modeler.figures.IGraphEdgeFigure#getLocator(org.topcased.modeler.figures.IEdgeObjectFigure)
	 * @_generated
	 */
	public Locator getLocator(IEdgeObjectFigure edgeObjectfigure) {

		if (edgeObjectfigure == srcNameEdgeObject) {
			return srcNameLocator;
		}
		if (edgeObjectfigure == srcCountEdgeObject) {
			return srcCountLocator;
		}
		if (edgeObjectfigure == targetNameEdgeObject) {
			return targetNameLocator;
		}
		if (edgeObjectfigure == targetCountEdgeObject) {
			return targetCountLocator;
		}
		if (edgeObjectfigure == middleNameEdgeObject) {
			return middleNameLocator;
		}

		return null;
	}

}