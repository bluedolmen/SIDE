package com.bluexml.alfresco.modules.sql.synchronisation.schemaManagement;

public enum TableStatus {
	EXISTS_SIMILAR,
	EXISTS_MATCHED,
	EXISTS_UNMATCHED,
	NOT_EXISTS,
	NOT_CHECKABLE
}
