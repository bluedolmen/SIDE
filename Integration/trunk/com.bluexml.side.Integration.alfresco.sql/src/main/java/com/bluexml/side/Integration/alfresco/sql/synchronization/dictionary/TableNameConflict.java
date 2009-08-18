package com.bluexml.side.Integration.alfresco.sql.synchronization.dictionary;

import org.alfresco.error.AlfrescoRuntimeException;

public class TableNameConflict extends AlfrescoRuntimeException {

	public TableNameConflict(String conflictingTableName) {
		super("Table name conflict detected on value \"" + conflictingTableName + "\"");
	}

	/**
	 * 
	 */
	private static final long serialVersionUID = 2266933534788687096L;

}
