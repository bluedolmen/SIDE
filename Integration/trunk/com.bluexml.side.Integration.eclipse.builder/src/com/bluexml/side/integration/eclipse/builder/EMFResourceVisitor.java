/**
 * 
 */
package com.bluexml.side.integration.eclipse.builder;

import org.eclipse.core.resources.IResource;
import org.eclipse.core.resources.IResourceVisitor;

class EMFResourceVisitor implements IResourceVisitor {
	/**
	 * 
	 */
	private final SIDEBuilderChecker checker;

	/**
	 * @param sideBuilder
	 */
	EMFResourceVisitor(SIDEBuilderChecker checker) {
		this.checker = checker;
	}

	public boolean visit(IResource resource) {
		try {
			checker.checkModel(resource, resource.getFullPath().toString());
		} catch (Exception e) {
			throw new RuntimeException(e);
		}

		//return true to continue visiting children.
		return true;
	}
}