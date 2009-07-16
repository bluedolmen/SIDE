package com.bluexml.alfresco.modules.sql.synchronisation.dictionary;

import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.SQLException;

import javax.sql.DataSource;

import org.apache.log4j.Logger;

public abstract class AbstractRenamingStrategy {

	protected Logger logger = Logger.getLogger(getClass());
	private DataSource dataSource;
	private Integer maxTableNameLength = -1;
	private Integer maxColumnNameLength = -1;
	
	public abstract String renameTable(String tableName);
		
	private void checkMetaData() {
		logger.debug("Checking meta-data for retrieving max length of table names");
		DatabaseMetaData dmd = null;
		
		try {
			Connection connection = dataSource.getConnection();
			dmd = connection.getMetaData();			
			
			maxTableNameLength = dmd.getMaxTableNameLength();
			logger.debug("Support max table name length = " + maxTableNameLength);

			maxColumnNameLength = dmd.getMaxColumnNameLength();
			logger.debug("Support max column name length = " + maxColumnNameLength);
			
			connection.close();
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	/*
	 * Spring IOC/DI material
	 */
	
	public void setDataSource(DataSource dataSource_) {
		dataSource = dataSource_;
		checkMetaData();
		if (maxTableNameLength <= 0) {
			logger.warn("Cannot set the max table name length from synchronisation database meta-data, this value has to be set manually");
		}
		if (maxColumnNameLength <= 0) {
			logger.warn("Cannot set the max column name length from synchronisation database meta-data, this value has to be set manually");
		}
	}
	
	public Integer getMaxTableNameLength() {
		return maxTableNameLength;
	}	
	public void setMaxTableNameLength(Integer length_) {
		maxTableNameLength = length_;
	}
	
	public Integer getMaxColumnNameLength() {
		return maxColumnNameLength;
	}
	public void setMaxColumnNameLength(Integer length_) {
		maxColumnNameLength = length_;
	}
}

