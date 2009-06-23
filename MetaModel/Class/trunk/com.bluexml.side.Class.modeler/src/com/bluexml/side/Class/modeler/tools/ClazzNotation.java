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
package com.bluexml.side.Class.modeler.tools;

import com.bluexml.side.common.ModelElement;
import com.bluexml.side.common.Stereotype;

public class ClazzNotation {

	public static String getStereotypesNotation(ModelElement element) {
		String prefix = "<<";
		String suffix = ">>";
		if (element != null) {
			if (element.getStereotypes().size()>0) {
				String middle = "";
				for (Object o : element.getStereotypes())
					if (o instanceof Stereotype) {
						Stereotype s = (Stereotype) o;
						if (s.getName() != null & s.getName().length()>0)
							middle += s.getName()+",";
					}
				if (middle.length() > 0)
					middle = middle.substring(0, middle.length()-1);
				return prefix + middle + suffix;
			}
		}
		return "";
	}

}
