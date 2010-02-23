package com.bluexml.side.Integration.alfresco.sql.synchronization.common;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;

import org.alfresco.repo.transaction.AlfrescoTransactionSupport;
import org.alfresco.repo.transaction.TransactionListener;
import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.jdbc.datasource.DataSourceUtils;

public class JdbcTransactionListener implements TransactionListener {

	private Logger logger = Logger.getLogger(getClass());
    private static final String SYNCHRO_CONTEXT_KEY = JdbcTransactionListener.class.getName() + ".context";
	

	private Connection getConnection() {		
		Connection connection = (Connection) AlfrescoTransactionSupport.getResource(SYNCHRO_CONTEXT_KEY);
        if (connection == null)
        {
        	if (logger.isDebugEnabled())
        		logger.debug("Creating new connection for transaction with id " + AlfrescoTransactionSupport.getTransactionId());
        	connection = DataSourceUtils.getConnection(dataSource);
        	try {
				connection.setAutoCommit(false);
			} catch (SQLException e) {
				logger.error("Cannot set autocommit mode on the connection");
				logger.debug(e);
			}
            AlfrescoTransactionSupport.bindResource(SYNCHRO_CONTEXT_KEY, connection);
            AlfrescoTransactionSupport.bindListener(this);
            if (logger.isDebugEnabled())
            	logger.debug("Attached connection to transaction " + AlfrescoTransactionSupport.getTransactionId());
        }
        return connection;
	}

	private void releaseConnection(Connection connection) {
		DataSourceUtils.releaseConnection(connection, dataSource);
		AlfrescoTransactionSupport.unbindResource(SYNCHRO_CONTEXT_KEY);
	}
	
	/**
	 * Execute an SQL query by performing all checking operation and
	 * opening/closure of related sql artefacts
	 * 
	 * @param query
	 * @throws SQLException 
	 */
	public int executeSQLQuery(String sqlQuery) throws SQLException {
		Connection connection = getConnection();
		Statement st = null;
		int rowCount = -1;
		try {
			if (logger.isDebugEnabled())
				logger.debug("[executeSQLQuery] " + sqlQuery);
			st = connection.createStatement();
			rowCount = st.executeUpdate(sqlQuery);
			if (logger.isDebugEnabled())
				logger.debug("[executeSQLQuery] Row count: " + rowCount);
		} catch (SQLException e) {
			logger.error("[executeSQLQuery]", e);
			throw(e);
		} finally {
			if (st != null) {
				try {
					st.close();
				} catch (SQLException e) {
					logger.error("[executeSQLQuery]", e);
				}
				st = null;
			}
		}
		
		return rowCount;
	}
	
	public int executeSelectQuery(String sqlQuery) throws SQLException {
		Connection connection = getConnection();
		Statement st = null;
		int rowCount = -1;
		try {
			if (logger.isDebugEnabled())
				logger.debug("[executeSQLQuery] " + sqlQuery);
			st = connection.createStatement();
			ResultSet results = st.executeQuery(sqlQuery);
			results.last();
			rowCount = results.getRow();
			if (logger.isDebugEnabled())
				logger.debug("[executeSQLQuery] Row count: " + rowCount);
		} catch (SQLException e) {
			logger.error("[executeSQLQuery]", e);
			throw(e);
		} finally {
			if (st != null) {
				try {
					st.close();
				} catch (SQLException e) {
					logger.error("[executeSQLQuery]", e);
				}
				st = null;
			}
		}
		
		return rowCount;
	}

	public int[] executeSQLQuery(List<String> sqlQueries) throws SQLException {
		Connection connection = getConnection();
		Statement st = null;
		int[] rowCount = new int[0];
		
		// avoid unnecessary processing if there is no queries (opening/closing of a statement) 
		if (!sqlQueries.isEmpty()){
			try {
				st = connection.createStatement();
				for (String sqlQuery : sqlQueries) {
					if (logger.isDebugEnabled())
						logger.debug("[executeSQLQuery(List<String>)] " + sqlQuery);
					st.addBatch(sqlQuery);
				}
				rowCount = st.executeBatch();
				if (logger.isDebugEnabled()) {
					// Just print a log message
					List<String> rowCountAsString = new ArrayList<String>();
					for (int i = 0 ; i < rowCount.length; i++) rowCountAsString.add(String.format("%1$s",rowCount[i]));
					logger.debug("[executeSQLQuery] Row count: [" + StringUtils.join(rowCountAsString.iterator(), ",") + "]");
				}
			} catch (SQLException e) {
				logger.error("[executeSQLQuery]", e);
				throw(e);
			} finally {
				if (st != null) {
					try {
						st.close();
					} catch (SQLException e) {
						logger.error("[executeSQLQuery] cannot close statement ", e);
					}
					st = null;
				}
			}
		}
		
		return rowCount;
	}

	public void afterCommit() {
//		if (logger.isDebugEnabled())
//			logger.debug("[afterCommit]" + AlfrescoTransactionSupport.getTransactionId());
		Connection connection = getConnection();
		try {
			connection.commit();
		} catch (SQLException e) {
			/*
			 * If an exception occurs here, then the data will not be committed to database
			 * and hence the database will no longer be synchronized
			 */
			logger.error("COMMIT INTO THE SYNCHRONIZATION DATABASE FAILED! THE DATABASE IS NOW UNCONSISTENT...", e);
		} finally {
			if (logger.isDebugEnabled())
				logger.debug("[afterCommit] Releasing existing connection");
			releaseConnection(connection);
		}
	}

	public void afterRollback() {
		Connection connection = getConnection();
//		if (logger.isDebugEnabled())
//			logger.debug("[afterRollback] on " + connection);
		try {
			connection.rollback();
		} catch (SQLException e) {
			logger.error("[afterRollback]", e);
		} finally {
			if (logger.isDebugEnabled())
				logger.debug("[afterRollback] Releasing existing connection");
			releaseConnection(connection);
		}
	}

	public void beforeCommit(boolean readOnly) {
//		if (!readOnly) {
//			logger.debug("[beforeCommit] " + AlfrescoTransactionSupport.getTransactionId());
//		}
	}

	public void beforeCompletion() {
		// nothing
	}

	public void flush() {
		// nothing
	}


	//
	// IoC/DI Spring
	//

	private DataSource dataSource;

	public void setDataSource(DataSource dataSource_) {
		dataSource = dataSource_;
	}
}
