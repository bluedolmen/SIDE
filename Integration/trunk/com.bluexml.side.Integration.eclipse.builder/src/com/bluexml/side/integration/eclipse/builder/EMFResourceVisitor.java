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
