package com.bluexml.side.Integration.alfresco.sql.synchronization.dictionary;

public class ErrorRisingRenamingStrategy extends AbstractRenamingStrategy {

	@Override
	public String renameTable(String tableName) {
		if (tableName.length() > getMaxTableNameLength()) {
			logger.error("Regarding your synchronization database, table name \"" + tableName + "\" exceeds the maximal name length");
		}
		return tableName;
	}

}
