package com.bluexml.alfresco.modules.sql.synchronisation.pathManagement;

public class PathServiceFailureException extends RuntimeException {

	/**
	 * 
	 */
	private static final long serialVersionUID = -6095688930850038008L;

	public PathServiceFailureException(Throwable t) {
		super(t);
	}
	
	public PathServiceFailureException() {
		super();
	}
}
