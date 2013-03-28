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
package com.bluexml.xforms.generator.tools;

import java.util.Comparator;

import com.bluexml.side.clazz.Aspect;

/**
 * The Class AspectComparator.
 */
public class AspectComparator implements Comparator<Aspect> {

	/** The Constant INSTANCE. */
	public static final AspectComparator INSTANCE = new AspectComparator();

	/**
	 * Instantiates a new aspect comparator.
	 */
	private AspectComparator() {
		super();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.util.Comparator#compare(java.lang.Object, java.lang.Object)
	 */
	public int compare(Aspect o1, Aspect o2) {
		return ModelTools.getCompleteName(o1).compareTo(ModelTools.getCompleteName(o2));
	}

}
