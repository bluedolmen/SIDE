package com.bluexml.side.Integration.alfresco.sql.synchronization.schemaManagement;

import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;

import com.bluexml.side.Integration.alfresco.sql.synchronization.common.SqlCommon.TableType;

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
	private CustomActionManager customActionManager = null;
	private TableType tableType;
	
	protected CreateStatement(String tableName_, Map<String, List<String>> columns_, TableType tableType_) {
		tableName = tableName_;
		columns = columns_;
		tableType = (tableType_ == null ? TableType.TABLE_UNSPECIFIED : tableType_);
	}
	
	protected CreateStatement(String tableName_, Map<String, List<String>> columns_, TableType tableType_, CustomActionManager customActionManager_) {
		tableName = tableName_;
		columns = columns_;
		tableType = (tableType_ == null ? TableType.TABLE_UNSPECIFIED : tableType_);
		customActionManager = customActionManager_;
	}
	
	public void addColumns(Map<String, List<String>> columns_) {
		columns.putAll(columns_);
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
		result.append("\n)");
		
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

			if (!rs.next()) {
				status = TableStatus.NOT_EXISTS;
			} else {
				logger.debug("Checking table '" + tableName + "'");
				
				Map<String, Integer> tableColumns = new LinkedHashMap<String, Integer>();
				do {
					String columnName = rs.getString("COLUMN_NAME");
					Integer dataType = rs.getInt("DATA_TYPE");
					String dataTypeDepName = rs.getString("TYPE_NAME");
					logger.debug("Column '" + columnName + "' with type '" + dataTypeDepName + "'(" + dataType + ")");
					tableColumns.put(columnName, dataType);
				} while (rs.next());
				rs.close();
				// TODO : Implement type checking to return EXIST_SIMILAR if types are compatible
					
				Set<String> propertySet = columns.keySet();
				propertySet.removeAll(tableColumns.keySet());
				
				if (! propertySet.isEmpty()) {
					status = TableStatus.EXISTS_UNMATCHED;
				}
				
				if (customActionManager != null) {
					status = customActionManager.doInSchemaChecking(tableColumns, status, tableType);
				} else {
					logger.debug("Cannot execute any custom checking since no custom action manager has been defined on create statement for table '" + tableName + "'");
				}					
			}

		} catch (SQLException e) {
			logger.error("Cannot get meta-data for checking table status");
			logger.debug(e);
			return TableStatus.NOT_CHECKABLE;
		}
		
		logger.debug("Checking table output status '" + tableName + "': " + status.name());

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
