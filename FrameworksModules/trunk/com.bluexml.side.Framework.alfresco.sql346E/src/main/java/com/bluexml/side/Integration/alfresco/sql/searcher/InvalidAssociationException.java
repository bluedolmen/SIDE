package com.bluexml.side.Integration.alfresco.sql.searcher;

import org.alfresco.error.AlfrescoRuntimeException;

public class InvalidAssociationException extends AlfrescoRuntimeException {

	public InvalidAssociationException(String associationName) {
		super("Invalid association: " + associationName);
	}

	/**
	 * 
	 */
	private static final long serialVersionUID = -7468742257616204176L;

}
