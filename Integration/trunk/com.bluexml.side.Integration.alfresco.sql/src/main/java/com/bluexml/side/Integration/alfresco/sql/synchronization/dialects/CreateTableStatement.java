package com.bluexml.side.Integration.alfresco.sql.synchronization.dialects;

import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;

import com.bluexml.side.Integration.alfresco.sql.synchronization.common.SqlCommon.TableType;
import com.bluexml.side.Integration.alfresco.sql.synchronization.schemaManagement.CustomActionManager;
import com.bluexml.side.Integration.alfresco.sql.synchronization.schemaManagement.TableStatus;

/*
 * This inner class is used to store the create statements
 * This class also knows how to serialize the create statement into DDL SQL
 * The constructor get in input a map < ColumnName, List_of_Options >, options being
 * either type declaration of constraints (the order of options must follow the SQL definition)
 */
public class CreateTableStatement {
	private static final Logger logger = Logger.getLogger(CreateTableStatement.class);
	
	private Map<String, List<String>> columns;
	private String tableName;
	private List<String> pkColumns = new ArrayList<String>() ;
	private Map<String, TableColumn> fkConstraints = new LinkedHashMap<String, TableColumn>();
	private TableType tableType;
	
	private static class TableColumn {
		public String table;
		public String column;
		
		TableColumn(String table_, String column_) {
			table = table_;
			column = column_;
		}
	}
	
	public static class Builder {

		private final String tableName;

		private Map<String, List<String>> columns = new HashMap<String, List<String>>();
		private List<String> pkColumns = new ArrayList<String>() ;
		private Map<String, TableColumn> fkConstraints = new LinkedHashMap<String, TableColumn>();
		private TableType tableType = TableType.TABLE_UNSPECIFIED;

		public Builder(String tableName_) {
			tableName = tableName_;
		}
		
		public Builder tableType(TableType tableType_) {
			tableType = tableType_;
			return this;
		}
		
		public Builder columns(Map<String, List<String>> columns_) {
			columns.putAll(columns_);
			return this;
		}

		public Builder pkConstraint(List<String> columnNames) {
			pkColumns.addAll(columnNames);
			return this;
		}
		
		public Builder pkConstraint(String columName) {
			pkColumns.add(columName);
			return this;
		}
		
		public Builder fkConstraint(String sourceColumnName, String targetTableName, String targetColumnName) {
			TableColumn tc = new TableColumn(targetTableName, targetColumnName);
			fkConstraints.put(sourceColumnName, tc);
			return this;
		}
 
		public CreateTableStatement build() {
			return new CreateTableStatement(this);
		}
	}
	
	protected CreateTableStatement(Builder builder_) {
		this.tableName = builder_.tableName;
		this.tableType = builder_.tableType;
		this.columns = builder_.columns;
		this.pkColumns = builder_.pkColumns;
		this.fkConstraints = builder_.fkConstraints;
	}
	
	public String toSQLString() {
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
				if (logger.isDebugEnabled())
					logger.debug("Checking table '" + tableName + "'");
				
				Map<String, Integer> tableColumns = new LinkedHashMap<String, Integer>();
				do {
					String columnName = rs.getString("COLUMN_NAME");
					Integer dataType = rs.getInt("DATA_TYPE");
					if (logger.isDebugEnabled()) {
						String dataTypeDepName = rs.getString("TYPE_NAME");
						logger.debug("Column '" + columnName + "' with type '" + dataTypeDepName + "'(" + dataType + ")");
					}
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
					if (logger.isDebugEnabled())
						logger.debug("Cannot execute any custom checking since no custom action manager has been defined on create statement for table '" + tableName + "'");
				}					
			}

		} catch (SQLException e) {
			logger.error("Cannot get meta-data for checking table status");
			logger.debug(e);
			return TableStatus.NOT_CHECKABLE;
		}
		
		if (logger.isDebugEnabled())
			logger.debug("Checking table output status '" + tableName + "': " + status.name());

		return status;

	}
	
	public String getNativeSQL(Connection connection) {
		String createStatement = toSQLString();
		String query = null;
		try { 
			query = connection.nativeSQL(createStatement);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		if (! createStatement.toString().equals(query)) {
			if (logger.isDebugEnabled())
				logger.debug("Original query : " + createStatement);
		}
		
		return query;
	}
	
	
	public String getTableName() {
		return tableName;
	}
	
	/*
	 * IoC/DI Spring
	 */
	
	/* Add custom action manager statically */
	private static CustomActionManager customActionManager;
	
	public void setCustomActionManager (CustomActionManager customActionManager_) {
		customActionManager = customActionManager_;
	}
}
