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
package com.bluexml.side.util.generator;

import java.util.List;

import org.eclipse.core.resources.IFile;

public class UnresolvedConflictException extends Exception {

	private static final long serialVersionUID = 8998363903297942301L;
	List<IFile> unresolvedConflict = null;

	public UnresolvedConflictException(List<IFile> unresolved) {
		super("check generated files :");
		this.unresolvedConflict = unresolved;

	}

	public String getMessage() {
		if (unresolvedConflict == null) {
			return super.getMessage();
		}
		String message = super.getMessage();
		message += "";
		for (IFile f : unresolvedConflict) {
			message += f.getFullPath().toOSString() + "\n";
		}
		return message;
	}
}
