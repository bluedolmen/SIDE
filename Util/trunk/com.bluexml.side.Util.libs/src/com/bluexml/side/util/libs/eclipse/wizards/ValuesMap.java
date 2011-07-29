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
