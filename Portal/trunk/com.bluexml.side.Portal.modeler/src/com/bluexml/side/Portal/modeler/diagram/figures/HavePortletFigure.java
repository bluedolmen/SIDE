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
package com.bluexml.side.Portal.modeler.diagram.figures;

import org.eclipse.gmf.runtime.draw2d.ui.figures.PolylineConnectionEx;
import org.eclipse.swt.SWT;
import org.eclipse.swt.graphics.Color;

/**
 * @generated
 */
public class HavePortletFigure extends PolylineConnectionEx {

	/**
	 * The constructor
	 *
	 * @generated
	 */
	public HavePortletFigure() {
		super();
		setLineStyle(SWT.LINE_SOLID);
	}

	public void setKoColor() {
		setForegroundColor(new Color(null, 231, 5, 5));
	}

	public void setOkColor() {
		setForegroundColor(new Color(null, 0, 0, 0));
	}

}