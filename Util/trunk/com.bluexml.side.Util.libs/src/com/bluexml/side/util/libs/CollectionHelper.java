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
package com.bluexml.side.util.libs;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class CollectionHelper<K, I> {
	Map<K, List<I>> map;

	public CollectionHelper(Map<K, List<I>> map) {
		this.map = map;
	}

	public Map<K, List<I>> addToMap(K key, I item, boolean isSet) {
		if (map.containsKey(key)) {
			if (!isSet || isSet && !contains(map.get(key), item)) {
				map.get(key).add(item);
			}

		} else {
			List<I> l = new ArrayList<I>();
			l.add(item);
			map.put(key, l);
		}
		return map;
	}

	public List<I> getAllItemsExcept(List<K> keys) {		
		List<I> i = new ArrayList<I>();
		for (K s : map.keySet()) {
			if (!keys.contains(s)) {
				List<I> list = map.get(s);
				if (list != null) {
					i.addAll(list);
				}
			}
		}
		return i;
	}

	public List<I> getAllItemsFor(List<K> keys) {
		List<I> i = new ArrayList<I>();
		for (K s : keys) {
			List<I> list = map.get(s);
			if (list != null) {
				i.addAll(list);
			}
		}
		return i;
	}

	public List<I> getAllItems() {
		List<I> i = new ArrayList<I>();
		for (List<I> l : map.values()) {
			i.addAll(l);
		}
		return i;
	}

	public boolean contains(List<I> l, I item) {
		for (I i : l) {
			if (i.equals(item)) {
				return true;
			}
		}
		return false;
	}
}
