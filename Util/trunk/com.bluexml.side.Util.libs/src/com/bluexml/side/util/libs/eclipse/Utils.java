package com.bluexml.side.util.libs.eclipse;

import org.eclipse.core.runtime.internal.adaptor.ContextFinder;

public class Utils {

	@SuppressWarnings("restriction")
	public static ClassLoader getContextFinderClassLoader() {
		ClassLoader cl0 = Thread.currentThread().getContextClassLoader();
		ClassLoader cl = null;
		if (cl0 instanceof ContextFinder) {
			cl = cl0;
		} else {
			cl = new ContextFinder(cl0);
		}
		return cl;
	}
}
