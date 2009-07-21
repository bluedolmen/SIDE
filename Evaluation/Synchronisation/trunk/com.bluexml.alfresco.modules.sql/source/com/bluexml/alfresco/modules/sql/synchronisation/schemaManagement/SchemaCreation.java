package com.bluexml.alfresco.modules.sql.synchronisation.schemaManagement;

import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import javax.sql.DataSource;
import javax.transaction.UserTransaction;

import org.alfresco.model.ContentModel;
import org.alfresco.service.cmr.dictionary.AspectDefinition;
import org.alfresco.service.cmr.dictionary.ClassDefinition;
import org.alfresco.service.cmr.dictionary.DictionaryService;
import org.alfresco.service.cmr.dictionary.PropertyDefinition;
import org.alfresco.service.cmr.dictionary.TypeDefinition;
import org.alfresco.service.namespace.QName;
import org.alfresco.service.transaction.TransactionService;
import org.apache.log4j.Logger;

import com.bluexml.alfresco.modules.sql.synchronisation.NodeFilterer;
import com.bluexml.alfresco.modules.sql.synchronisation.dialects.DefaultDialect;
import com.bluexml.alfresco.modules.sql.synchronisation.dialects.SynchronisationDialect;
import com.bluexml.alfresco.modules.sql.synchronisation.dictionary.DatabaseDictionary;

public class SchemaCreation {
	
//	private static final String BLUEXML_CONTENT_URI = "http://www.bluexml.com/model/content";
	public static final String ASSOCIATION_ID_COLUMN_NAME = "id";
	private static final String ALFRESCO_DBID_COLUMN_NAME = ContentModel.PROP_NODE_DBID.getLocalName();
	
	private Logger logger = Logger.getLogger(getClass());
	private Connection connection = null;
	private SynchronisationDialect synchronisationDialect = null;
		
	private void executeCreateStatement(CreateStatement createStatement) throws SQLException {
		try {
			Statement sqlStatement = connection.createStatement();
			
			sqlStatement.executeUpdate(createStatement.toString());
		} catch (SQLException e) {
			logger.error("Cannot create table due to the following error: " + e.getMessage());
			//e.printStackTrace();
			throw(e);
		}	
	}
	
	public void init() {
		logger.debug("Initializing the synchronized database");
		try {
			connection = dataSource.getConnection();
			synchronisationDialect = new DefaultDialect();
			
			checkMetaData();
			
			List<CreateStatement> createStatements = new ArrayList<CreateStatement>();
			
			for (QName type : dictionaryService.getAllTypes()) {
				if (nodeFilterer.accept(type)) {
					createStatements.add(createClass(type));
				}
			}
			
			for (QName associationName : dictionaryService.getAllAssociations()) {
				if (nodeFilterer.accept(associationName)) {
					ClassDefinition sourceClassDefinition = dictionaryService.getAssociation(associationName).getSourceClass();
					ClassDefinition targetClassDefinition = dictionaryService.getAssociation(associationName).getTargetClass();
					createStatements.add(createAssociation(associationName, sourceClassDefinition.getName(), targetClassDefinition.getName()));
				}
			}
			
			boolean creationFailed = false;
			try {
				for (CreateStatement createStatement : createStatements) {
					logger.debug(createStatement.getNativeSQL(connection));
					executeCreateStatement(createStatement);
				}
			} catch (SQLException e) {
				creationFailed = true;
			}
		
			connection.close();

			if (! creationFailed) {
				UserTransaction userTransaction = transactionService.getUserTransaction();
				try {
					userTransaction.begin();
					contentReplication.addExistingData();
					userTransaction.commit();
				} catch (Exception e) {
					try {
						userTransaction.rollback();
					} catch (Exception e1) {
						logger.error("Cannot rollback transaction !");
						e1.printStackTrace();
					}
					e.printStackTrace();
				}		
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	
	private CreateStatement createClass(QName classQName) {
		String className = classQName.getLocalName();
		String tableName = databaseDictionary.resolveClassAsTableName(className);
		
		Map<String, List<String>> columns = new LinkedHashMap<String, List<String>>();
//		columns.put(idColumnName, new ArrayList<String>() {{add("INTEGER");}});
		
		TypeDefinition typeDefinition = dictionaryService.getType(classQName);
		
		Map<QName, PropertyDefinition> allProperties = new HashMap<QName, PropertyDefinition>();
		allProperties.putAll(typeDefinition.getProperties());
		
		for (AspectDefinition aspectDefinition : typeDefinition.getDefaultAspects()) {
			allProperties.putAll(aspectDefinition.getProperties());
		}
		
		for (Map.Entry<QName, PropertyDefinition> entry : allProperties.entrySet()) {
			PropertyDefinition propertyDefinition = entry.getValue();
			QName propertyName = propertyDefinition.getName();
			
			if (nodeFilterer.accept(propertyName)) {
				List<String> options = new ArrayList<String>();			
				String sqlType = synchronisationDialect.getSQLMapping(propertyDefinition);
				options.add(sqlType);
				String originalName = propertyName.getLocalName();
				String resolvedColumnName = databaseDictionary.resolveAttributeAsColumnName(originalName, className); 
				columns.put((resolvedColumnName != null ? resolvedColumnName : originalName), options);
			}
		}
		
		CreateStatement createStatement = new CreateStatement(tableName, columns);
		String idColumnName = databaseDictionary.resolveAttributeAsColumnName(ALFRESCO_DBID_COLUMN_NAME, className);
		createStatement.addPkConstraint(idColumnName);

		return createStatement;
	}
	
	/*
	 * Returns a create statement for associations
	 * @param associationQName, the QName of the newly create association table
	 * @param sourceClassQName, the source association of the association ; this parameter is used to get the source id column name
	 * @param targetClassQName, same as source for target
	 */
	@SuppressWarnings("serial")
	private CreateStatement createAssociation(QName associationQName, QName sourceClassQName, QName targetClassQName) {
		final String associationName = associationQName.getLocalName();
		String tableName = databaseDictionary.resolveAssociationAsTableName(associationName);

		Map<String, List<String>> columns = new LinkedHashMap<String, List<String>>();
		columns.put(ASSOCIATION_ID_COLUMN_NAME, new ArrayList<String>() {{add("INTEGER");}});
		
		columns.put(databaseDictionary.getSourceAlias(associationName), new ArrayList<String>() {{add("INTEGER"); }});
		columns.put(databaseDictionary.getTargetAlias(associationName), new ArrayList<String>() {{add("INTEGER"); }});

		CreateStatement createStatement = new CreateStatement(tableName, columns);
		createStatement.addPkConstraint(ASSOCIATION_ID_COLUMN_NAME);
		
		String idColumnName = databaseDictionary.resolveAttributeAsColumnName(ALFRESCO_DBID_COLUMN_NAME, sourceClassQName.getLocalName());
		createStatement.addFkConstraint(databaseDictionary.getSourceAlias(associationName), databaseDictionary.getSourceClass(associationName), idColumnName);
		
		idColumnName = databaseDictionary.resolveAttributeAsColumnName(ALFRESCO_DBID_COLUMN_NAME, targetClassQName.getLocalName());
		createStatement.addFkConstraint(databaseDictionary.getTargetAlias(associationName), databaseDictionary.getTargetClass(associationName), idColumnName);

		return createStatement;
	}
	

	
	/*
	 * Helper functions
	 */
	
	
	private void checkMetaData() {
		logger.debug("Checking meta-data");
		DatabaseMetaData dmd = null;
		ResultSet rs = null;
		
		try {
			dmd = connection.getMetaData();
			
			String dbname = dmd.getDatabaseProductName();
			String dbversion = dmd.getDatabaseProductVersion();
			logger.debug("Running sql synchronisation on " + dbname + " " + dbversion);
			
			
			try {
				DatabaseMetaData databaseMetaData = connection.getMetaData();
				rs = databaseMetaData.getTables(null, null, "%", null);

				if (! rs.isAfterLast()) {
					do {
						rs.next();						
						String tableName = rs.getString("TABLE_NAME");
						logger.debug("1) Existing table: " + tableName);
					} while (! rs.isAfterLast());
				}
				rs.close();
				
				rs = databaseMetaData.getTables("", "", "%", null);

				if (! rs.isAfterLast()) {
					do {
						rs.next();						
						String tableName = rs.getString("TABLE_NAME");
						logger.debug("1) Existing table: " + tableName);
					} while (! rs.isAfterLast());
				}
				rs.close();

			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

//			rs = dmd.getSchemas();
			
						
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
	private NodeFilterer nodeFilterer;
	private ContentReplication contentReplication;
	private TransactionService transactionService;


	public void setDataSource(DataSource dataSource_) {
		dataSource = dataSource_;
	}

	public void setDictionaryService(DictionaryService dictionaryService_) {
		dictionaryService = dictionaryService_;
	}

	public void setDatabaseDictionary(DatabaseDictionary databaseDictionary_) {
		databaseDictionary = databaseDictionary_;
	}
	
	public void setNodeFilterer(NodeFilterer nodeFilterer_) {
		nodeFilterer = nodeFilterer_;
	}
	
	public void setContentReplication (ContentReplication contentReplication_) {
		contentReplication = contentReplication_;
	}
	
	public void setTransactionService(TransactionService transactionService_) {
		transactionService = transactionService_;
	}

}
