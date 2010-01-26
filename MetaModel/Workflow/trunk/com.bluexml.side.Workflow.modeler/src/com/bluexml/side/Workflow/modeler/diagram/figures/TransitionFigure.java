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
package com.bluexml.side.Workflow.modeler.diagram.figures;

import org.eclipse.draw2d.Locator;
import org.eclipse.gmf.runtime.draw2d.ui.figures.PolylineConnectionEx;
import org.eclipse.swt.SWT;
import org.eclipse.swt.graphics.Color;
import org.topcased.modeler.edit.locators.EdgeObjectOffsetLocator;
import org.topcased.modeler.figures.EdgeObjectOffsetEditableLabel;
import org.topcased.modeler.figures.IEdgeObjectFigure;
import org.topcased.modeler.figures.IEdgeObjectOffsetFigure;

/**
 * @generated
 */
public class TransitionFigure extends PolylineConnectionEx {

	private IEdgeObjectFigure middleNameEdgeObject;

	private Locator middleNameLocator;

	/**
	 * The constructor
	 *
	 * @_generated
	 */
	public TransitionFigure() {
		super();
		setLineStyle(SWT.LINE_SOLID);

		middleNameEdgeObject = new EdgeObjectOffsetEditableLabel(this);
		float[] rgb = {0f,0f,0f}; 
		middleNameEdgeObject.setForegroundColor(Color.cocoa_new(null, rgb));
		middleNameLocator = new EdgeObjectOffsetLocator(
				(IEdgeObjectOffsetFigure) middleNameEdgeObject);
		add(middleNameEdgeObject, middleNameLocator);
	}

	public IEdgeObjectFigure getMiddleNameEdgeObject() {
		return middleNameEdgeObject;
	}

	public void setMiddleNameEdgeObject(IEdgeObjectFigure middleNameEdgeObject) {
		this.middleNameEdgeObject = middleNameEdgeObject;
	}

	public Locator getMiddleNameLocator() {
		return middleNameLocator;
	}

	public void setMiddleNameLocator(Locator middleNameLocator) {
		this.middleNameLocator = middleNameLocator;
	}

	public Locator getLocator(IEdgeObjectFigure edgeObjectfigure) {

		if (edgeObjectfigure == middleNameEdgeObject) {
			return middleNameLocator;
		}

		return null;
	}
}