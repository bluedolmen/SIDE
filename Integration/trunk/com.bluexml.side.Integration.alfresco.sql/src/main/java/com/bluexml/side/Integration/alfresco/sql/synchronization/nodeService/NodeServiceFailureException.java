package com.bluexml.side.Integration.alfresco.sql.synchronization.nodeService;

public class NodeServiceFailureException extends RuntimeException {

	/**
	 * 
	 */
	private static final long serialVersionUID = -6095688930850038008L;

	public NodeServiceFailureException(Throwable t) {
		super(t);
	}
	
	public NodeServiceFailureException() {
		super();
	}
}
