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
