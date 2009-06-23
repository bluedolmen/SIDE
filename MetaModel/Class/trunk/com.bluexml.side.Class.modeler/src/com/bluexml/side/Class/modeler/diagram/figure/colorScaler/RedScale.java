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
package com.bluexml.side.Class.modeler.diagram.figure.colorScaler;

import org.eclipse.swt.graphics.Color;

public class RedScale implements ColorScale {

	private static int COLOR_MIN = 170;
	private static int COLOR_MAX = 220;
	
	public Color getColor(int index, int count) {
		if (index == 1)
			return new Color(null,255,COLOR_MIN,COLOR_MIN);
		else if (index == count)
			return new Color(null,255,COLOR_MAX,COLOR_MAX);
		else {
			int i = COLOR_MIN + (COLOR_MAX-COLOR_MIN)*index/count; 
			return new Color(null,255,i,i);
		}
	}

}
