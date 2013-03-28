/*
    Copyright (C) 2007-20013  BlueXML - www.bluexml.com

    This program is free software: you can redistribute it and/or modify
    it under the terms of the GNU Affero General Public License as
    published by the Free Software Foundation, either version 3 of the
    License, or (at your option) any later version.

    This program is distributed in the hope that it will be useful,
    but WITHOUT ANY WARRANTY; without even the implied warranty of
    MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
    GNU Affero General Public License for more details.

    You should have received a copy of the GNU Affero General Public License
    along with this program.  If not, see <http://www.gnu.org/licenses/>.
*/
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
import org.eclipse.swt.widgets.Display;
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
		middleNameEdgeObject.setForegroundColor(Display.getCurrent().getSystemColor(SWT.COLOR_BLACK));
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
