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

import com.bluexml.side.clazz.AbstractClass;

/**
 * The Class ClasseComparator.
 */
public class ClasseComparator implements Comparator<AbstractClass> {

	/** The Constant INSTANCE. */
	public static final ClasseComparator INSTANCE = new ClasseComparator();

	/**
	 * Instantiates a new classe comparator.
	 */
	private ClasseComparator() {
		super();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.util.Comparator#compare(java.lang.Object, java.lang.Object)
	 */
	public int compare(AbstractClass o1, AbstractClass o2) {
		String so1 = ModelTools.getCompleteName(o1);
		String so2 = ModelTools.getCompleteName(o2);
		if (so1 == null || so2 == null) {
			so1 = ModelTools.getCompleteName(o1);
			so2 = ModelTools.getCompleteName(o2);
			return 0;
		}
		return so1.compareTo(so2);
	}

}
