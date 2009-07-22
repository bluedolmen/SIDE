package com.bluexml.alfresco.modules.sql.synchronisation.schemaManagement;

import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;

/*
 * This inner class is used to store the create statements
 * This class also knows how to serialize the create statement into DDL SQL
 * The constructor get in input a map < ColumnName, List_of_Options >, options being
 * either type declaration of constraints (the order of options must follow the SQL definition)
 */
public class CreateStatement {
	private static final Logger logger = Logger.getLogger(CreateStatement.class);
	
	private Map<String, List<String>> columns;
	private String tableName;
	private List<String> pkColumns = new ArrayList<String>() ;
	private Map<String, TableColumn> fkConstraints = new LinkedHashMap<String, TableColumn>();
	
	protected CreateStatement(String tableName_, Map<String, List<String>> columns_) {
		tableName = tableName_;
		columns = columns_;
	}
	
	public void resetConstraints() {
		pkColumns.clear();
		fkConstraints.clear();
	}
	
	public void addPkConstraint(List<String> columnNames) {
		pkColumns.addAll(columnNames);
	}
	
	public void addPkConstraint(String columName) {
		pkColumns.add(columName);
	}
	
	public void addFkConstraint(String sourceColumnName, String targetTableName, String targetColumnName) {
		TableColumn tc = new TableColumn(targetTableName, targetColumnName);
		fkConstraints.put(sourceColumnName, tc);
	}
	
	public String toString() {
		StringBuffer result = new StringBuffer();
		result.append("CREATE TABLE " + tableName + " (\n");
		List<String> tableDefinitionLines = new ArrayList<String>();
		
		for (String columnName : columns.keySet()) {
			StringBuffer columnString = new StringBuffer();
			List<String> options = columns.get(columnName);
			
			columnString.append("\t" + columnName);
			for (String option : options) {
				columnString.append(" " + option);
			}
			tableDefinitionLines.add(columnString.toString());
		}
		
		if (! pkColumns.isEmpty()) {
			String constraint = "\tPRIMARY KEY (" + StringUtils.join(pkColumns.iterator(),",") + ")";
			tableDefinitionLines.add(constraint);
		}
		
		if (! fkConstraints.isEmpty()) {
			for (String fkColumnId : fkConstraints.keySet()) {
				TableColumn tc = fkConstraints.get(fkColumnId);
				String constraint = "\tFOREIGN KEY (" + fkColumnId + ") REFERENCES " + tc.table + " (" + tc.column + ")";
				tableDefinitionLines.add(constraint);
			}
		}
		
		result.append(StringUtils.join(tableDefinitionLines.iterator(),",\n"));
		result.append("\n);");
		
		return result.toString();
	}
	
	/*
	 * Returns a simple table status based on the table name and on the column names.
	 * Further checking should be made on the types of the tables
	 */
	public TableStatus checkStatus(Connection connection) {
		
		TableStatus status = TableStatus.EXISTS_MATCHED;
		ResultSet rs = null;

		try {
			DatabaseMetaData databaseMetaData = connection.getMetaData();
			rs = databaseMetaData.getColumns(null, null, tableName, "%");

			if (rs.isAfterLast()) {
				status = TableStatus.NOT_EXISTS;
			} else {
				logger.debug("Checking table '" + tableName + "'");
				rs.next();						
				do {
					String columnName = rs.getString("COLUMN_NAME");
					if (! columns.containsKey(columnName)) {
						status = TableStatus.EXISTS_UNMATCHED;
					}
					Integer dataType = rs.getInt("DATA_TYPE");
					String dataTypeDepName = rs.getString("TYPE_NAME");
					// TODO : Implement type checking to return EXIST_SIMILAR if types are compatible
					
					logger.debug("Column '" + columnName + "' with type '" + dataTypeDepName + "'(" + dataType + ")");
					rs.next();						
				} while (! rs.isAfterLast());
			}
			rs.close();
		} catch (SQLException e) {
			logger.error("Cannot get meta-data for checking table status");
			logger.debug(e);
			return TableStatus.NOT_CHECKABLE;
		}
		
		return status;

	}
	
	public String getNativeSQL(Connection connection) {
		String createStatement = toString();
		String query = null;
		try { 
			query = connection.nativeSQL(createStatement);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		if (! createStatement.toString().equals(query)) {
			logger.debug("Original query : " + createStatement);
		}
		
		return query;
	}
	
	private class TableColumn {
		public String table;
		public String column;
		
		TableColumn(String table_, String column_) {
			table = table_;
			column = column_;
		}
	}
	
	public String getTableName() {
		return tableName;
	}
	
}
