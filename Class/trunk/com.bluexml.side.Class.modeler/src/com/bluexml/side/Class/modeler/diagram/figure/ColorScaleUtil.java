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
package com.bluexml.side.Class.modeler.diagram.figure;

import org.eclipse.draw2d.Figure;
import org.eclipse.draw2d.Graphics;
import org.eclipse.draw2d.geometry.Dimension;
import org.eclipse.draw2d.geometry.Point;
import org.eclipse.swt.graphics.Color;

import com.bluexml.side.Class.modeler.diagram.figure.colorScaler.ColorScale;
import com.bluexml.side.Class.modeler.diagram.figure.colorScaler.GreyScale;

public class ColorScaleUtil {

	public static void paintFigure(Graphics graphics, Figure figure, ColorScale colorScale) {
		Dimension d = figure.getSize();
		int NB_RECTANGLE = d.height/10;
		
		if (NB_RECTANGLE < 10)
			NB_RECTANGLE = 10;
		
		Dimension dPart = new Dimension(d.width, d.height/NB_RECTANGLE);
		
		Point location = null;
		Color color = null;
		
		for (int i = 0; i < NB_RECTANGLE; ++i) {
			if (i == 0) {
				location = figure.getLocation();
			} else if (i == NB_RECTANGLE-1) {
				dPart.height = d.height - (location.y - figure.getLocation().y);
			}
			color = colorScale.getColor(i+1, NB_RECTANGLE); 
			
			BXRectangle r = new BXRectangle();
			r.setLocation(location);
			r.setSize(dPart);
			r.setBackgroundColor(color);
			r.paint(graphics);
			
			location.y += dPart.height - 1;
		}
	}
	
	public static void paintFigure(Graphics graphics, Figure figure) {
		paintFigure(graphics, figure, new GreyScale());
	}
}
