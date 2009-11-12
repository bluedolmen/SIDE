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

import org.eclipse.draw2d.Graphics;
import org.eclipse.gmf.runtime.draw2d.ui.figures.PolylineConnectionEx;
import org.eclipse.swt.SWT;

import com.bluexml.side.Workflow.modeler.actions.HideResponsibilityLinks;

/**
 * @generated
 */
public class initializeFigure extends PolylineConnectionEx {

	/**
	 * The constructor
	 *
	 * @generated
	 */
	public initializeFigure() {
		super();
		setLineStyle(SWT.LINE_SOLID);
	}
	
	@Override
	public void paint(Graphics graphics) {
		if (HideResponsibilityLinks.showResponsibilityLinks)
			super.paint(graphics);
	}

}