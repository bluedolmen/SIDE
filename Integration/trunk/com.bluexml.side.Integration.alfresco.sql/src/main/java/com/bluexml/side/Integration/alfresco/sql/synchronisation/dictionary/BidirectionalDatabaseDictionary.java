package com.bluexml.side.Integration.alfresco.sql.synchronisation.dictionary;

public interface BidirectionalDatabaseDictionary extends DatabaseDictionary {

	public abstract String resolveTableAsClassName(String tableName);

	public abstract String resolveTableAsAssociationName(String tableName);

}
