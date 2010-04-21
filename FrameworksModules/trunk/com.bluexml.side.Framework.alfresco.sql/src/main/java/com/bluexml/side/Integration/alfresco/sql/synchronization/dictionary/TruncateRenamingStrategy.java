package com.bluexml.side.Integration.alfresco.sql.synchronization.dictionary;

public class TruncateRenamingStrategy extends AbstractRenamingStrategy {

	public String renameTable(String tableName) {
		int tableNameLength = tableName.length();
		int maxTableNameLength = getMaxTableNameLength();
		int maxLength = (tableNameLength < maxTableNameLength ? tableNameLength : maxTableNameLength);
		String result = tableName.substring(0, maxLength);

		if (maxTableNameLength < tableNameLength) {
			if (logger.isInfoEnabled())
				logger.info("Truncating table name \"" + tableName + "\" to \"" + result + "\"");
		}
		return result;
	}

}
