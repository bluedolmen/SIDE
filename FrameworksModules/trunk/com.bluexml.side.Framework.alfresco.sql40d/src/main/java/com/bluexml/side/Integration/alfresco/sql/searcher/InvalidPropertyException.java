package com.bluexml.side.Integration.alfresco.sql.searcher;

import org.alfresco.error.AlfrescoRuntimeException;

public class InvalidPropertyException extends AlfrescoRuntimeException {

	public InvalidPropertyException(String propertyName) {
		super("Invalid property:" + propertyName);
	}

	/**
	 * 
	 */
	private static final long serialVersionUID = 4056158876320530958L;

}
