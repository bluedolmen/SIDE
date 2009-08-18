package com.bluexml.side.Integration.alfresco.sql.synchronization.schemaManagement;

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
import javax.transaction.UserTransaction;

import org.alfresco.model.ContentModel;
import org.alfresco.service.cmr.dictionary.AspectDefinition;
import org.alfresco.service.cmr.dictionary.ClassDefinition;
import org.alfresco.service.cmr.dictionary.DictionaryService;
import org.alfresco.service.cmr.dictionary.PropertyDefinition;
import org.alfresco.service.cmr.dictionary.TypeDefinition;
import org.alfresco.service.namespace.QName;
import org.alfresco.service.transaction.TransactionService;
import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.jdbc.datasource.DataSourceUtils;

import com.bluexml.side.Integration.alfresco.sql.synchronization.common.NodeFilterer;
import com.bluexml.side.Integration.alfresco.sql.synchronization.common.SqlCommon.TableType;
import com.bluexml.side.Integration.alfresco.sql.synchronization.dialects.DefaultDialect;
import com.bluexml.side.Integration.alfresco.sql.synchronization.dialects.SynchronizationDialect;
import com.bluexml.side.Integration.alfresco.sql.synchronization.dictionary.DatabaseDictionary;

public class SchemaCreation {
	
//	private static final String BLUEXML_CONTENT_URI = "http://www.bluexml.com/model/content";
	public static final String ASSOCIATION_ID_COLUMN_NAME = "id";
	private static final String ALFRESCO_DBID_COLUMN_NAME = ContentModel.PROP_NODE_DBID.getLocalName();
	
	private Logger logger = Logger.getLogger(getClass());
	private Connection connection = null;
	private boolean ready = true;
	
	enum CheckTableStatus {
		CREATE_TABLES,
		NO_ACTION_CLEAN,
		NO_ACTION_DIRTY
	}
	
	public void init() {
		logger.debug("Initializing the synchronized database");
		connection = DataSourceUtils.getConnection(dataSource);
		
		synchronizationDialect = new DefaultDialect();
		
		checkMetaData();
		
		List<CreateStatement> createStatements = doCreateStatement();
		
		CheckTableStatus checkTableStatus = doCheckStatus(createStatements);
		
		if (checkTableStatus == CheckTableStatus.CREATE_TABLES) {
			boolean creationSuccess = doExecuteCreateStatements(createStatements);
			if (! creationSuccess) {
				logger.error("Creation of tables failed");
				ready = false;
			} else {
				boolean replicationSuccess = doExecuteReplication();
				if (!replicationSuccess) {
					logger.error("Replication of existing data failed");
					ready = false;
				}
			}
		}

		DataSourceUtils.releaseConnection(connection, dataSource);
		
	}

	public boolean isReady() {
		return ready;
	}
	
	
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
	
	
	private List<CreateStatement> doCreateStatement() {
		List<CreateStatement> createStatements = new ArrayList<CreateStatement>();
		
		for (QName type : dictionaryService.getAllTypes()) {
			if (nodeFilterer.acceptOnName(type)) {
				CreateStatement currentCreateStatement = createClass(type); 
				
				customActionManager.doInCreateType(type, currentCreateStatement);
				
				createStatements.add(currentCreateStatement);

			}
		}
		
		for (QName associationName : dictionaryService.getAllAssociations()) {
			if (nodeFilterer.acceptOnName(associationName)) {
				ClassDefinition sourceClassDefinition = dictionaryService.getAssociation(associationName).getSourceClass();
				ClassDefinition targetClassDefinition = dictionaryService.getAssociation(associationName).getTargetClass();
				
				CreateStatement currentCreateStatement = createAssociation(associationName, sourceClassDefinition.getName(), targetClassDefinition.getName());

				customActionManager.doInCreateAssociation(associationName, currentCreateStatement);

				createStatements.add(currentCreateStatement);

			}
		}
		
		return createStatements;
	}
		
	private CheckTableStatus doCheckStatus (List<CreateStatement> createStatements) {
		List<String> matchedTables = new ArrayList<String>();
		List<String> unmatchedTables = new ArrayList<String>();
		List<String> nonExistingTables = new ArrayList<String>();
		
		for (CreateStatement createStatement : createStatements) {
			TableStatus tableStatus = createStatement.checkStatus(connection);
			if (tableStatus.equals(TableStatus.EXISTS_MATCHED)) {
				matchedTables.add(createStatement.getTableName());
			} else if (tableStatus.equals(TableStatus.NOT_EXISTS)) {
				nonExistingTables.add(createStatement.getTableName());
			} else if (tableStatus.equals(TableStatus.EXISTS_UNMATCHED)) {
			// if a table cannot be matched then list it
				unmatchedTables.add(createStatement.getTableName());
			} else if (tableStatus.equals(TableStatus.NOT_CHECKABLE)) {
				// if at least one table cannot be checked, then stop and warn user
				logger.warn("Cannot check existing tables (problem during analysis of table '" + createStatement.getTableName() + "'");
				break;
			}
		}
		
		CheckTableStatus status = null;
		if (matchedTables.isEmpty() && unmatchedTables.isEmpty()) {
			status = CheckTableStatus.CREATE_TABLES;
		} else if (unmatchedTables.isEmpty() && nonExistingTables.isEmpty()) {
			status = CheckTableStatus.NO_ACTION_CLEAN;
		} else {
			logger.error("The synchronization database is dirty (manual intervention is needed)...");
			if (! matchedTables.isEmpty()) {
				logger.error(" - The following tables match the definition: " + StringUtils.join(matchedTables.iterator(), ","));
			}
			if (! unmatchedTables.isEmpty()) {
				logger.error(" - The following tables do not match the definition: " + StringUtils.join(unmatchedTables.iterator(), ","));
			}
			if (! nonExistingTables.isEmpty()) {
				logger.error(" - The following tables do not exist: " + StringUtils.join(nonExistingTables.iterator(), ","));
			}
			status = CheckTableStatus.NO_ACTION_DIRTY;
			ready = false;
		}
		
		logger.debug("Global checking status: " + status.name());
		return status;
	}
	
	private boolean doExecuteCreateStatements(List<CreateStatement> createStatements) {
		boolean creationSuccess = true;
		try {
			for (CreateStatement createStatement : createStatements) {
				logger.debug(createStatement.getNativeSQL(connection));
				executeCreateStatement(createStatement);
			}
		} catch (SQLException e) {
			creationSuccess = false;
		}
		return creationSuccess;
	}
	
	private boolean doExecuteReplication() {
		boolean success = true;
		
		UserTransaction userTransaction = transactionService.getUserTransaction();
		try {
			userTransaction.begin();
			contentReplication.addExistingData();
			userTransaction.commit();
		} catch (Exception e) {
			success = false;
			try {
				userTransaction.rollback();
			} catch (Exception e1) {
				logger.error("Cannot rollback transaction !");
				e1.printStackTrace();
			}
			e.printStackTrace();
		}		
		return success;
	}
	
	private CreateStatement createClass(QName classQName) {
		String className = classQName.getLocalName();
		String tableName = databaseDictionary.resolveClassAsTableName(className);
		
		Map<String, List<String>> columns = new LinkedHashMap<String, List<String>>();
		TypeDefinition typeDefinition = dictionaryService.getType(classQName);
		
		Map<QName, PropertyDefinition> allProperties = new HashMap<QName, PropertyDefinition>();
		allProperties.putAll(typeDefinition.getProperties());
		
		for (AspectDefinition aspectDefinition : typeDefinition.getDefaultAspects()) {
			allProperties.putAll(aspectDefinition.getProperties());
		}
		
		for (Map.Entry<QName, PropertyDefinition> entry : allProperties.entrySet()) {
			PropertyDefinition propertyDefinition = entry.getValue();
			QName propertyName = propertyDefinition.getName(); 
			// TODO : perform checking of existing table here (availability of the current Java type)
			
			if (nodeFilterer.acceptOnName(propertyName)) {
				List<String> options = new ArrayList<String>();			
				String sqlType = synchronizationDialect.getSQLMapping(propertyDefinition);
				options.add(sqlType);
				String originalName = propertyName.getLocalName();
				String resolvedColumnName = databaseDictionary.resolveAttributeAsColumnName(originalName, className); 
				columns.put((resolvedColumnName != null ? resolvedColumnName : originalName), options);
			}
		}
		
		CreateStatement createStatement = new CreateStatement(tableName, columns, TableType.TABLE_CLASS, customActionManager);
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

		CreateStatement createStatement = new CreateStatement(tableName, columns, TableType.TABLE_ASSOCIATION, customActionManager);
		createStatement.addPkConstraint(
				new ArrayList<String>() {{ 
					add(databaseDictionary.getSourceAlias(associationName));
					add(databaseDictionary.getTargetAlias(associationName)); // ASSOCIATION_ID_COLUMN_NAME)
				}}
		);
		
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
//		ResultSet rs = null;
		
		try {
			dmd = connection.getMetaData();
			
			String dbname = dmd.getDatabaseProductName();
			String dbversion = dmd.getDatabaseProductVersion();
			logger.debug("Running sql synchronization on " + dbname + " " + dbversion);
			
			
						
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
	private SynchronizationDialect synchronizationDialect;
	private CustomActionManager customActionManager;

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
	
	public void setSynchronizationDialect(SynchronizationDialect synchronizationDialect_) {
		synchronizationDialect = synchronizationDialect_;
	}
	
	public void setCustomActionManager(CustomActionManager customActionManager_) {
		customActionManager = customActionManager_;
	}

}
