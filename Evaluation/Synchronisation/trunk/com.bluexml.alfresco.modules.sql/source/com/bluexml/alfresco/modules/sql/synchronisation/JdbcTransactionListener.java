package com.bluexml.alfresco.modules.sql.synchronisation;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

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
//		String currentTransactionId = AlfrescoTransactionSupport.getTransactionId();
//		
//		logger.debug("[setConnection] current Alfresco transaction id: " + currentTransactionId);
//		if (!StringUtils.equals(currentTransactionId, alfrescoTransactionId)) {
//			if (currentConnection != null) {
//				logger.debug("PREVIOUS CONNECTION WAS NOT CLOSED CORRECTLY... closing now");
//				DataSourceUtils.releaseConnection(currentConnection, dataSource);
//			}
//			alfrescoTransactionId = currentTransactionId;
//			currentConnection = DataSourceUtils.getConnection(dataSource);
//			try {
//				currentConnection.setAutoCommit(false);
//			} catch (SQLException e) {
//				logger.debug("Cannot set autocommit mode on the connection");
//				logger.debug(e.getMessage());
//				//e.printStackTrace();
//			}
//			AlfrescoTransactionSupport.bindListener(this);
//			logger.debug("[setConnection] opened: " + currentConnection);
//		} else {
//			logger.debug("[setConnection] same transaction, reusing previous opened connection");
//		}
//		logger.debug("[setConnection] connection : " + currentConnection);
		
		Connection connection = (Connection) AlfrescoTransactionSupport.getResource(SYNCHRO_CONTEXT_KEY);
        if (connection == null)
        {
        	logger.debug("Creating new connection for transaction with id " + AlfrescoTransactionSupport.getTransactionId());
        	connection = DataSourceUtils.getConnection(dataSource);
        	try {
				connection.setAutoCommit(false);
			} catch (SQLException e) {
				logger.debug("Cannot set autocommit mode on the connection");
				logger.debug(e.getMessage());
			}
            AlfrescoTransactionSupport.bindResource(SYNCHRO_CONTEXT_KEY, connection);
            AlfrescoTransactionSupport.bindListener(this);
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
	 */
	public void executeSQLQuery(String sql_query) {
		Connection connection = getConnection();
		Statement st = null;
		try {
			logger.debug("[executeSQLQuery] " + sql_query);
			st = connection.createStatement();
			int rowCount = st.executeUpdate(sql_query);
			logger.debug("[executeSQLQuery] Row count : " + rowCount);
		} catch (SQLException e) {
			logger.error("[executeSQLQuery]", e);
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
}

	public void afterCommit() {
		logger.debug("[afterCommit]" + AlfrescoTransactionSupport.getTransactionId());
		Connection connection = getConnection();
		logger.debug("[afterCommit] on " + connection);
		try {
			connection.commit();
		} catch (SQLException e) {
			logger.error("[afterCommit]", e);
		} finally {
			logger.debug("[afterCommit] Releasing existing connection");
			releaseConnection(connection);
		}
	}

	public void afterRollback() {
		Connection connection = getConnection();
		logger.debug("[afterRollback] on " + connection);
		try {
			connection.rollback();
		} catch (SQLException e) {
			logger.error("[afterRollback]", e);
		} finally {
			logger.debug("[afterRollback] Releasing existing connection");
			releaseConnection(connection);
		}
	}

	public void beforeCommit(boolean readOnly) {
		if (!readOnly) {
			logger.debug("[beforeCommit] " + AlfrescoTransactionSupport.getTransactionId());
		}
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
