package com.bluexml.xforms.generator.tools;

import java.util.Comparator;

import com.bluexml.side.clazz.Aspect;

/**
 * The Class AspectComparator.
 */
public class AspectComparator implements Comparator<Aspect> {

	/** The Constant INSTANCE. */
	public static final AspectComparator INSTANCE = new AspectComparator();

	/**
	 * Instantiates a new aspect comparator.
	 */
	private AspectComparator() {
		super();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.util.Comparator#compare(java.lang.Object, java.lang.Object)
	 */
	public int compare(Aspect o1, Aspect o2) {
		return ModelTools.getCompleteName(o1).compareTo(ModelTools.getCompleteName(o2));
	}

}
