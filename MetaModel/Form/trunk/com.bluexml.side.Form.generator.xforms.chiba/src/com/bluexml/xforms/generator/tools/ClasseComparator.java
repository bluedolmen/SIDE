package com.bluexml.xforms.generator.tools;

import java.util.Comparator;

import com.bluexml.side.clazz.Clazz;

/**
 * The Class ClasseComparator.
 */
public class ClasseComparator implements Comparator<Clazz> {

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
	public int compare(Clazz o1, Clazz o2) {
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
