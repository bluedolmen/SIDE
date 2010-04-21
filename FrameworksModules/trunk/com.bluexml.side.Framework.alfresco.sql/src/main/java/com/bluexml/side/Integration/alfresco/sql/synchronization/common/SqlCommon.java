package com.bluexml.side.Integration.alfresco.sql.synchronization.common;

public interface SqlCommon {

	public static final String BLUEXML_SQL_EXTENSION_URI = "http://www.bluexml.com/alfresco/sql";
	
	public enum TableType {
		TABLE_CLASS,
		TABLE_ASSOCIATION,
		TABLE_UNSPECIFIED
	}

}
