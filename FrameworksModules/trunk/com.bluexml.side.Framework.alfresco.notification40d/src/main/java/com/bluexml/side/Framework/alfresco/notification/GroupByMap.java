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
package com.bluexml.side.Framework.alfresco.notification;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class GroupByMap<T> {
	private HashMap<Object, List<T>> internalMap = new HashMap<Object, List<T>>();

	public void put(Object key, T value) {
		if (internalMap.containsKey(key)) {
			internalMap.get(key).add(value);
		} else {
			List<T> l = new ArrayList<T>();
			l.add(value);
			internalMap.put(key, l);
		}
	}

	public Set<Map.Entry<Object, List<T>>> getEntrySet() {
		return internalMap.entrySet();
	}

	public String toString() {
		return internalMap.toString();
	}
}
