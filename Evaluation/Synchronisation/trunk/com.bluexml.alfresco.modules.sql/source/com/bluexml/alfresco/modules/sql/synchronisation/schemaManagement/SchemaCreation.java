package com.bluexml.alfresco.modules.sql.synchronisation.schemaManagement;

import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import javax.sql.DataSource;

import org.alfresco.service.cmr.dictionary.AspectDefinition;
import org.alfresco.service.cmr.dictionary.DictionaryService;
import org.alfresco.service.cmr.dictionary.PropertyDefinition;
import org.alfresco.service.cmr.dictionary.TypeDefinition;
import org.alfresco.service.namespace.QName;
import org.apache.log4j.Logger;

import com.bluexml.alfresco.modules.sql.synchronisation.dialects.DefaultDialect;
import com.bluexml.alfresco.modules.sql.synchronisation.dialects.SynchronisationDialect;
import com.bluexml.alfresco.modules.sql.synchronisation.dictionary.DatabaseDictionary;

public class SchemaCreation {
	
	private static final String BLUEXML_CONTENT_URI = "http://www.bluexml.com/model/content";
	private static final String ID_COLUMN_NAME = "id";
	
	private Logger logger = Logger.getLogger(getClass());
	private Connection connection = null;
	private SynchronisationDialect synchonisationDialect = null;
		
	private void executeCreateStatement(CreateStatement createStatement) {
		try {
			Statement sqlStatement = connection.createStatement();
			
			sqlStatement.executeUpdate(createStatement.toString());
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}	
	}
	
	public void init() {
		logger.debug("Initializing the synchronized database");
		try {
			connection = dataSource.getConnection();
			synchonisationDialect = new DefaultDialect();
			
			checkMetaData();
			
			List<CreateStatement> createStatements = new ArrayList<CreateStatement>();
			
			for (QName type : dictionaryService.getAllTypes()) {
				if (type.getNamespaceURI().startsWith(BLUEXML_CONTENT_URI)) {
					createStatements.add(createClass(type));
				}
			}
			
			for (QName association : dictionaryService.getAllAssociations()) {
				if (association.getNamespaceURI().startsWith(BLUEXML_CONTENT_URI)) {
					createStatements.add(createAssociation(association));
				}
			}
			
			for (CreateStatement createStatement : createStatements) {
				logger.debug(createStatement.getNativeSQL(connection));
				executeCreateStatement(createStatement);
			}
		
			connection.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	
	@SuppressWarnings("serial")
	private CreateStatement createClass(QName clazz) {
		String className = clazz.getLocalName();
		String tableName = databaseDictionary.resolveClassAsTableName(className);
		
		Map<String, List<String>> columns = new LinkedHashMap<String, List<String>>();
		columns.put(ID_COLUMN_NAME, new ArrayList<String>() {{add("INTEGER");}});
		
		TypeDefinition typeDefinition = dictionaryService.getType(clazz);
		
		Map<QName, PropertyDefinition> allProperties = new HashMap<QName, PropertyDefinition>();
		allProperties.putAll(typeDefinition.getProperties());
		
		for (AspectDefinition aspectDefinition : typeDefinition.getDefaultAspects()) {
			allProperties.putAll(aspectDefinition.getProperties());
		}
		
		for (Map.Entry<QName, PropertyDefinition> entry : allProperties.entrySet()) {
			PropertyDefinition propertyDefinition = entry.getValue();
			QName propertyName = propertyDefinition.getName();
			
			if (propertyName.getNamespaceURI().startsWith(BLUEXML_CONTENT_URI)) {
				
				List<String> options = new ArrayList<String>();			
				String sqlType = synchonisationDialect.getSQLMapping(propertyDefinition);
				options.add(sqlType);
				columns.put(databaseDictionary.resolveAttributeAsColumnName(propertyName.getLocalName(), className), options);
			}
		}
		
		CreateStatement createStatement = new CreateStatement(tableName, columns);
		createStatement.addPkConstraint(ID_COLUMN_NAME);

		return createStatement;
	}
	
	@SuppressWarnings("serial")
	private CreateStatement createAssociation(QName association) {
		final String associationName = association.getLocalName();
		String tableName = databaseDictionary.resolveAssociationAsTableName(associationName);

		Map<String, List<String>> columns = new LinkedHashMap<String, List<String>>();
		columns.put(ID_COLUMN_NAME, new ArrayList<String>() {{add("INTEGER");}});
		
		columns.put(databaseDictionary.getSourceAlias(associationName), 
				new ArrayList<String>() {{add("INTEGER"); }});
		columns.put(databaseDictionary.getTargetAlias(associationName), 
				new ArrayList<String>() {{add("INTEGER"); }});

		CreateStatement createStatement = new CreateStatement(tableName, columns);
		createStatement.addPkConstraint(ID_COLUMN_NAME);
		createStatement.addFkConstraint(databaseDictionary.getSourceAlias(associationName), databaseDictionary.getSourceClass(associationName), ID_COLUMN_NAME);
		createStatement.addFkConstraint(databaseDictionary.getTargetAlias(associationName), databaseDictionary.getTargetClass(associationName), ID_COLUMN_NAME);

		return createStatement;
	}
	

	
	/*
	 * Helper functions
	 */
	
	
	private void checkMetaData() {
		logger.debug("Checking meta-data");
		DatabaseMetaData dmd = null;
//		ResultSet rs = null;
		
		try {
			dmd = connection.getMetaData();
			
			String dbname = dmd.getDatabaseProductName();
			String dbversion = dmd.getDatabaseProductVersion();
			logger.debug("Running sql synchronisation on " + dbname + " " + dbversion);
						
//			rs = dmd.getTypeInfo();
//			System.out.println("Supported data types:");
//			while(rs.next()){
//			  logger.debug("Database Type Name \"" + rs.getString("TYPE_NAME") + "\" mapped to "
//					  + "JDBC type \"" + rs.getShort("DATA_TYPE") + "\"");
//			}
//			rs.close();

			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	
	//
	// IoC/DI Spring
	//

	private DataSource dataSource;
	private DictionaryService dictionaryService;
	private DatabaseDictionary databaseDictionary;

	public void setDataSource(DataSource dataSource_) {
		dataSource = dataSource_;
	}

	public void setDictionaryService(DictionaryService dictionaryService_) {
		dictionaryService = dictionaryService_;
	}

	public void setDatabaseDictionary(DatabaseDictionary databaseDictionary_) {
		databaseDictionary = databaseDictionary_;
	}
}
