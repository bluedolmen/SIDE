package com.bluexml.xforms.generator.tools;

import java.util.Comparator;

import com.bluexml.side.clazz.Attribute;

/**
 * The Class AttributeComparator.
 */
public class AttributeComparator implements Comparator<Attribute> {

	/** The Constant INSTANCE. */
	public static final AttributeComparator INSTANCE = new AttributeComparator();

	/**
	 * Instantiates a new attribute comparator.
	 */
	private AttributeComparator() {
		super();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.util.Comparator#compare(java.lang.Object, java.lang.Object)
	 */
	public int compare(Attribute o1, Attribute o2) {
		return o1.getName().compareTo(o2.getName());
	}

}
