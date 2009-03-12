/*******************************************************************************
 * 	Copyright (C) BlueXML 2005-2008
 *
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
package com.bluexml.side.Class.modeler.diagram.figures.common;

import org.eclipse.draw2d.Graphics;

import com.bluexml.side.Class.modeler.diagram.figure.ColorScaleUtil;
import com.bluexml.side.Class.modeler.diagram.figures.ViewFigure;


public class RectangleFigure extends org.eclipse.draw2d.RectangleFigure {

	private ViewFigure viewFigure;

	public RectangleFigure(ViewFigure viewFigure) {
		this.viewFigure = viewFigure;
	}

	@Override
	public void paint(Graphics graphics) {
		super.paint(graphics);
		if (viewFigure.getLabel() != null)
			setSize(viewFigure.getLabel().getSize().width+10, viewFigure.getLabel().getSize().height+10);
		ColorScaleUtil.paintFigure(graphics, this);
		paintBorder(graphics);
	}
}
