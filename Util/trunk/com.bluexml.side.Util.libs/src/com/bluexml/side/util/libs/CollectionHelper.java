package com.bluexml.side.util.libs;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class CollectionHelper<K, I> {

	public Map<K, List<I>> addToMap(Map<K, List<I>> map, K key, I item, boolean isSet) {
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

	public List<I> getAllItems(Map<K, List<I>> map) {
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
