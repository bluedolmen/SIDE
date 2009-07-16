package com.bluexml.alfresco.modules.sql.synchronisation.dictionary;

public class ErrorRisingRenamingStrategy extends AbstractRenamingStrategy {

	@Override
	public String renameTable(String tableName) {
		if (tableName.length() > getMaxTableNameLength()) {
			logger.error("Regarding your synchronisation database, table name \"" + tableName + "\" exceeds the maximal name length");
		}
		return tableName;
	}

}
