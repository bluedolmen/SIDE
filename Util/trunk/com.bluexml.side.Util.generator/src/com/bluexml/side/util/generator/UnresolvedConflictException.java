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
