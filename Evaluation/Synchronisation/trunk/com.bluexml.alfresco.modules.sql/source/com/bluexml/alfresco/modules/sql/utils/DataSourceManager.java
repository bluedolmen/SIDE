package com.bluexml.alfresco.modules.sql.utils;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

import javax.sql.DataSource;

import org.apache.commons.dbcp.BasicDataSource;
import org.apache.log4j.Logger;

public class DataSourceManager {
	private static final String FILE_PATH_CUSTOM_REPOSITORY = "alfresco/extension/custom-repository.properties";
	private static Logger logger = Logger.getLogger(DataSourceManager.class);
	private DataSource dataSource = null;
	
	// TODO : Declare as a spring bean

	public void init() {
		Properties db_properties = new Properties();
		InputStream myStream = null;
		myStream = DataSourceManager.class.getClassLoader().getResourceAsStream(FILE_PATH_CUSTOM_REPOSITORY);

		if (myStream != null) {
			try {
				db_properties.load(myStream);
				myStream.close();
			} catch (IOException e) {
				logger.error("[init]", e);
			}

			// Try loading the mysql jdbc driver
			try {
				Class.forName(db_properties.getProperty("db.driver"));
			} catch (ClassNotFoundException e) {
				logger.error("[initDBConnection] Error in loading jdbc driver", e);
			}

			// Try to connect and perform request to the database locally
			String url = db_properties.getProperty("db.replicate.url");
			String user = db_properties.getProperty("db.username");
			String password = db_properties.getProperty("db.password");
			try {

				dataSource = new BasicDataSource();
				BasicDataSource basicDataSource = (BasicDataSource) dataSource;
				basicDataSource.setUrl(url);
				basicDataSource.setUsername(user);
				basicDataSource.setPassword(password);
				basicDataSource.setDefaultAutoCommit(false);

				dataSource.getConnection().close();

			} catch (Exception e) {
				logger.error("[initDBConnection] Problem in getting access to the replicate database.\n" + "Please check your database and your custom-repository.properties for the keys db.replicate.url " + "db.username and db.password !", e);
				dataSource = null;
			}
		} else {
			logger.error("Error reading the custom-repository.properties file (initDBConnection)");
		}

		
	}
	
	public DataSource getDatasource() {
		return dataSource;
	}
}
