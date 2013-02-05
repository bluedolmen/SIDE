package com.bluexml.side.Integration.alfresco.sql.searcher;

import org.alfresco.error.AlfrescoRuntimeException;

public class InvalidTypeException extends AlfrescoRuntimeException {

	public InvalidTypeException(String typeName) {
		super("Invalid type:" + typeName);
	}

	/**
	 * 
	 */
	private static final long serialVersionUID = -4169505014109175L;

}
