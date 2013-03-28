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
package com.bluexml.side.util.libs.eclipse.wizards;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class ValuesMap<T, V> extends HashMap<T, V> {
	List<ValueMapListener<T, V>> l = new ArrayList<ValueMapListener<T, V>>();

	public void addListener(ValueMapListener<T, V> listener) {
		l.add(listener);
	}

	/**
	 * 
	 */
	private static final long serialVersionUID = -4802220349066130323L;

	/*
	 * (non-Javadoc)
	 * @see java.util.HashMap#put(java.lang.Object, java.lang.Object)
	 */
	@Override
	public V put(T arg0, V arg1) {
		V put = super.put(arg0, arg1);
		for (ValueMapListener<T, V> listener : l) {
			listener.put(arg0, arg1);
		}
		return put;
	}

	/*
	 * (non-Javadoc)
	 * @see java.util.HashMap#remove(java.lang.Object)
	 */
	@Override
	public V remove(Object arg0) {
		V remove = super.remove(arg0);
		for (ValueMapListener<T, V> listener : l) {
			listener.remove(arg0);
		}
		return remove;
	}

}
