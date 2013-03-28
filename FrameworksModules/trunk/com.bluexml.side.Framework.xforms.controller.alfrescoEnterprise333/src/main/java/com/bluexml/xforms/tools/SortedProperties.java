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
package com.bluexml.xforms.tools;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Properties;

/**
 * The Class SortedProperties.
 */
@SuppressWarnings("unchecked")
public class SortedProperties extends Properties {

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 9084018450112374396L;

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.util.Hashtable#keys()
	 */
	@Override
	public synchronized java.util.Enumeration keys() {
		ArrayList<?> list = Collections.list(super.keys());
		Collections.sort(list, new Comparator() {
			public int compare(Object o1, Object o2) {
				String s1 = o1.toString();
				String s2 = o2.toString();
				return s1.compareTo(s2);
			}
		});
		return Collections.enumeration(list);
	}
}
