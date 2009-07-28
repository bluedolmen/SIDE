package com.bluexml.alfresco.modules.sql.utils;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import javax.sql.DataSource;

import org.alfresco.service.cmr.repository.NodeRef;
import org.apache.log4j.Logger;
import org.jdom.Document;
import org.jdom.output.Format;
import org.jdom.output.XMLOutputter;


public class DatabaseQuery {
	// Logger
	private static Logger logger = Logger.getLogger(DatabaseQuery.class);
		
	public ResultSet executeQuery(String sql_query) {
		ResultSet srs = null;
		Connection connection = getConnection();
		srs = executeSQLQuery(sql_query, connection);
		return srs;
	}

	public void closeQuery(ResultSet rs) {
		if (rs != null) {
			Statement st = null;
			Connection connection = null;
			try {
				st = rs.getStatement();
				if (st != null) {
					connection = st.getConnection();
				}
			} catch (SQLException e) {
				logger.error(e.getMessage(), e);
			}
			closeStatement(st);
			closeConnection(connection);
		}
	}

	private void closeStatement(Statement st) {
		if (st != null) {
			try {
				st.close();
			} catch (SQLException e) {
				logger.error(e.getMessage(), e);
			}
			st = null;
		}
	}

	private ResultSet executeSQLQuery(String sql_query, Connection connection) {
		ResultSet srs = null;
		if (connection != null) {
			Statement st = null;
			try {
				logger.debug("[SQL] Connection : " + connection);
				logger.debug("[SQL] " + sql_query);
				st = connection.createStatement(java.sql.ResultSet.TYPE_FORWARD_ONLY, java.sql.ResultSet.CONCUR_READ_ONLY);
				srs = st.executeQuery(sql_query);
			} catch (SQLException e) {
				logger.error(e.getMessage(), e);
			}
		}
		return srs;
	}

	private Connection getConnection() {
		Connection connection = null;
		DataSource dataSource = DataSourceManager.getDatasource();
		if (dataSource != null) {
			try {
				connection = dataSource.getConnection();
			} catch (SQLException e) {
				logger.error(e.getMessage(), e);
			}
		}
		logger.debug("[Connection] get : " + connection);
		return connection;
	}

	private void closeConnection(Connection connection) {
		logger.debug("[Connection] close : " + connection);
		if (connection != null) {
			try {
				connection.close();
			} catch (SQLException e) {
				logger.error(e.getMessage(), e);
			}
		}
	}

	/**
	 * Get Results as Collection of NodeRef
	 * 
	 * @param sql_query
	 * @return
	 * @throws Exception
	 */
	public ArrayList<NodeRef> getResultAsNodeRefs(String sql_query) throws Exception {
		ArrayList<NodeRef> records = new ArrayList<NodeRef>();
		ResultSet srs = executeQuery(sql_query);
		records = ResponseBuilder.buildFreemarckerResponseFromSQL(srs);
		closeQuery(srs);
		return records;
	}

	/**
	 * Get Result as XML
	 * 
	 * @param sql_query
	 * @return
	 * @throws Exception
	 */


	public Document getResultAsXML(String sql_query) throws Exception {
		ResultSet srs = executeQuery(sql_query);
		Document records = ResponseBuilder.buildXMLResponseFromSQL(srs);
		closeQuery(srs);
		return records;
	}

	public String getResultAsString(String sql_query) throws Exception {
		ResultSet srs = executeQuery(sql_query);
		Document records = ResponseBuilder.buildXMLResponseFromSQL(srs);
		closeQuery(srs);
		return convertDocument2String(records);
	}

	public String convertDocument2String(Document doc) {
		XMLOutputter outputter = new XMLOutputter();
		Format format = Format.getCompactFormat();
		format.setOmitEncoding(true);
		format.setOmitDeclaration(true);
		outputter.setFormat(format);
		return outputter.outputString(doc);

	}
	
	// SPRING BEAN MANAGEMENT
	
	// Dependencies
	private static DataSourceManager dataSourceManager;
	
	// Accessors
	public DataSourceManager getDataSourceManager() {
		return dataSourceManager;
	}
	
	public void setDataSourceManager(DataSourceManager datasourceManager_) {
		dataSourceManager = datasourceManager_;
	}

}
