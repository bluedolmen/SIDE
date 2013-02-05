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
