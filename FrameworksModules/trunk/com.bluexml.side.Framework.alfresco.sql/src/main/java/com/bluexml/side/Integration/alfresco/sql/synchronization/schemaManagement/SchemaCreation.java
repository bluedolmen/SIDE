package com.bluexml.side.Integration.alfresco.sql.synchronization.schemaManagement;

import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.sql.DataSource;
import javax.transaction.UserTransaction;

import org.alfresco.model.ContentModel;
import org.alfresco.repo.dictionary.DictionaryDAO;
import org.alfresco.repo.dictionary.DictionaryListener;
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

import com.bluexml.side.Integration.alfresco.sql.synchronization.common.Filterer;
import com.bluexml.side.Integration.alfresco.sql.synchronization.common.SqlCommon.TableType;
import com.bluexml.side.Integration.alfresco.sql.synchronization.dialects.CreateTableStatement;
import com.bluexml.side.Integration.alfresco.sql.synchronization.dialects.SynchronizationDialect;
import com.bluexml.side.Integration.alfresco.sql.synchronization.dictionary.DatabaseDictionary;

public class SchemaCreation implements DictionaryListener {
	
	public static final String ASSOCIATION_ID_COLUMN_NAME = "id";
	private static final String ALFRESCO_DBID_COLUMN_NAME = ContentModel.PROP_NODE_DBID.getLocalName();
	
	private Logger logger = Logger.getLogger(getClass());
	private boolean ready = true;
	
	private Set<QName> replicatedModels = new HashSet<QName>();
	private Map<QName,CheckTableStatus> statusByModel = new HashMap<QName, CheckTableStatus>();
	
	enum CheckTableStatus {
		CREATE_TABLES,
		NO_ACTION_CLEAN,
		NO_ACTION_DIRTY
	}
	
	public void init() {
		logger.debug("Initializing the synchronized database");
		dictionaryDAO.register(this);
		
		// Remove?
		checkMetaData();

	}
	
	private void createFromModel(QName modelName) {

		if (ready) {
			Connection connection = DataSourceUtils.getConnection(dataSource);
			
			List<CreateTableStatement> createStatements = doCreateStatement(modelName);
			
			CheckTableStatus checkTableStatus = doCheckStatus(createStatements, connection);
			statusByModel.put(modelName, checkTableStatus);
			
			if (checkTableStatus == CheckTableStatus.CREATE_TABLES) {
		
				boolean creationSuccess = doExecuteCreateStatements(createStatements, connection);
				if (! creationSuccess) {
					logger.error("Creation of tables failed");
					ready = false;
				}
				
				DataSourceUtils.releaseConnection(connection, dataSource);
				
			} else {
				if (logger.isDebugEnabled())
					logger.debug("Creation of model \"" + modelName + "\" was not performed since the previous process marked the schema as not ready or creation has yet be done");
			}
			
		}
		
	}
	
	private void replicateFromModel(QName modelName){
		
		CheckTableStatus checkTableStatus = statusByModel.get(modelName);

		if (ready && CheckTableStatus.CREATE_TABLES.equals(checkTableStatus)) {

			boolean replicationSuccess = doExecuteReplication(modelName);
			if (!replicationSuccess) {
				logger.error("Replication of existing data failed");
				ready = false;
			}

		} else {
			if (logger.isDebugEnabled())
				logger.debug("Replication of model \"" + modelName + "\" was not performed since the previous process marked the schema as not ready or replication has yet be done");
		}
		
		replicatedModels.add(modelName);
	}
	
	public boolean isReady() {
		return ready;
	}
	
	
	private void executeCreateStatement(CreateTableStatement createStatement, Connection connection) throws SQLException {
		Statement sqlStatement = connection.createStatement();
		try {			
			sqlStatement.executeUpdate(createStatement.toSQLString());
		} catch (SQLException e) {
			logger.error("Cannot create table due to the following error: ", e);
			throw(e);
		} finally {
			sqlStatement.close();
		}
	}
	
	
	private List<CreateTableStatement> doCreateStatement(QName modelName) {
		List<CreateTableStatement> createStatements = new ArrayList<CreateTableStatement>();
		
		for (QName type : dictionaryService.getTypes(modelName)) {
			if (filterer.acceptTypeQName(type)) {
				CreateTableStatement currentCreateStatement = createClass(type); 								
				createStatements.add(currentCreateStatement);
			}
		}

		for (QName associationName : dictionaryService.getAssociations(modelName)) {
			if (filterer.acceptAssociationQName(associationName)) {
				ClassDefinition sourceClassDefinition = dictionaryService.getAssociation(associationName).getSourceClass();
				ClassDefinition targetClassDefinition = dictionaryService.getAssociation(associationName).getTargetClass();
				
				CreateTableStatement currentCreateStatement = createAssociation(associationName, sourceClassDefinition.getName(), targetClassDefinition.getName());
				createStatements.add(currentCreateStatement);
			}
		}
		
		return createStatements;
	}
		
	private CheckTableStatus doCheckStatus (List<CreateTableStatement> createStatements, Connection connection) {
		List<String> matchedTables = new ArrayList<String>();
		List<String> unmatchedTables = new ArrayList<String>();
		List<String> nonExistingTables = new ArrayList<String>();
		
		for (CreateTableStatement createStatement : createStatements) {
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
				logger.error(" - The following tables match the definition: [" + StringUtils.join(matchedTables.iterator(), ",") + "]");
			}
			if (! unmatchedTables.isEmpty()) {
				logger.error(" - The following tables do not match the definition: [" + StringUtils.join(unmatchedTables.iterator(), ",") + "]");
			}
			if (! nonExistingTables.isEmpty()) {
				logger.error(" - The following tables do not exist: [" + StringUtils.join(nonExistingTables.iterator(), ",") + "]");
			}
			status = CheckTableStatus.NO_ACTION_DIRTY;
			ready = false;
		}
		
		if (logger.isDebugEnabled())
			logger.debug("Global checking status: " + status.name());
		return status;
	}
	
	private boolean doExecuteCreateStatements(List<CreateTableStatement> createStatements, Connection connection) {
		boolean creationSuccess = true;
		try {
			for (CreateTableStatement createStatement : createStatements) {
				if (logger.isDebugEnabled())
					logger.debug(createStatement.getNativeSQL(connection));
				executeCreateStatement(createStatement, connection);
			}
		} catch (SQLException e) {
			creationSuccess = false;
		}
		return creationSuccess;
	}
	
	private boolean doExecuteReplication(QName modelName) {
		boolean success = true;
		
		UserTransaction userTransaction = transactionService.getUserTransaction();
		try {
			userTransaction.begin();
			contentReplication.addExistingData(modelName);
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
	
	private CreateTableStatement createClass(QName classQName) {
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
			
			if (filterer.acceptPropertyQName(propertyName)) {
				List<String> options = new ArrayList<String>();			
				String sqlType = synchronizationDialect.getSQLMapping(propertyDefinition);
				options.add(sqlType);
				String originalName = propertyName.getLocalName();
				String resolvedColumnName = databaseDictionary.resolveAttributeAsColumnName(originalName, className); 
				columns.put((resolvedColumnName != null ? resolvedColumnName : originalName), options);
			}
		}
		
		String idColumnName = databaseDictionary.resolveAttributeAsColumnName(ALFRESCO_DBID_COLUMN_NAME, className);
		CreateTableStatement.Builder builder = 
			synchronizationDialect.newCreateTableStatementBuilder(tableName).columns(columns).tableType(TableType.TABLE_CLASS).pkConstraint(idColumnName);
		
		customActionManager.doInCreateType(classQName, builder);
		
		return builder.build();
	}
	
	/*
	 * Returns a create statement for associations
	 * @param associationQName, the QName of the newly create association table
	 * @param sourceClassQName, the source association of the association ; this parameter is used to get the source id column name
	 * @param targetClassQName, same as source for target
	 */
	@SuppressWarnings("serial")
	private CreateTableStatement createAssociation(QName associationQName, QName sourceClassQName, QName targetClassQName) {
		final String associationName = associationQName.getLocalName();
		String tableName = databaseDictionary.resolveAssociationAsTableName(associationName);

		Map<String, List<String>> columns = new LinkedHashMap<String, List<String>>();
		columns.put(ASSOCIATION_ID_COLUMN_NAME, new ArrayList<String>() {{add("INTEGER");}});
		
		columns.put(databaseDictionary.getSourceAlias(associationName), new ArrayList<String>() {{add("INTEGER"); }});
		columns.put(databaseDictionary.getTargetAlias(associationName), new ArrayList<String>() {{add("INTEGER"); }});

		final String sourceIdColumnName = databaseDictionary.resolveAttributeAsColumnName(ALFRESCO_DBID_COLUMN_NAME, sourceClassQName.getLocalName());
		final String targetIdColumnName = databaseDictionary.resolveAttributeAsColumnName(ALFRESCO_DBID_COLUMN_NAME, targetClassQName.getLocalName());

		CreateTableStatement.Builder builder = 
			synchronizationDialect.newCreateTableStatementBuilder(tableName)
			.columns(columns)
			.tableType(TableType.TABLE_ASSOCIATION)
			.pkConstraint(databaseDictionary.getSourceAlias(associationName))
			.pkConstraint(databaseDictionary.getTargetAlias(associationName))
			.fkConstraint(databaseDictionary.getSourceAlias(associationName), databaseDictionary.getSourceClass(associationName), sourceIdColumnName)
			.fkConstraint(databaseDictionary.getTargetAlias(associationName), databaseDictionary.getTargetClass(associationName), targetIdColumnName);
		
		customActionManager.doInCreateAssociation(associationQName, builder);

		return builder.build();
	}
	
		
	/*
	 * Dictionary Listener methods
	 */
	
	public void afterDictionaryDestroy() {
		// do nothing more
	}

	public void onDictionaryInit() {
		// do nothing more
	}

	public void afterDictionaryInit() {
		
		logger.debug("Checking for new model to replicate in synchronization database");
		Set<QName> acceptableModelNames = new HashSet<QName>();
		for (QName modelName : dictionaryDAO.getModels()) {
			if (filterer.acceptModelQName(modelName)) {
				acceptableModelNames.add(modelName);
			}
		}
		if (logger.isDebugEnabled())
			logger.debug("Acceptable models: [" + StringUtils.join(acceptableModelNames.iterator(),",") + "]");
		acceptableModelNames.removeAll(replicatedModels);
		if (logger.isDebugEnabled())
			logger.debug("New models: [" + StringUtils.join(acceptableModelNames.iterator(),",") + "]");
		
		for (QName modelName : acceptableModelNames) {
			createFromModel(modelName);
		}
		for (QName modelName : acceptableModelNames) {
			replicateFromModel(modelName);
		}
	}


	
	/*
	 * Helper functions
	 */
	
	
	private void checkMetaData() {
		logger.debug("Checking meta-data");
		DatabaseMetaData dmd = null;

		Connection connection = DataSourceUtils.getConnection(dataSource);	

		try {
			dmd = connection.getMetaData();
			
			String dbname = dmd.getDatabaseProductName();
			String dbversion = dmd.getDatabaseProductVersion();
			if (logger.isDebugEnabled())
				logger.debug("Running sql synchronization on " + dbname + " " + dbversion);
			
		} catch (SQLException e) {
			logger.error(e);
		} finally {
			DataSourceUtils.releaseConnection(connection, dataSource);
		}
	}

	
	//
	// IoC/DI Spring
	//

	private DataSource dataSource;
	private DictionaryService dictionaryService;
	private DatabaseDictionary databaseDictionary;
	private Filterer filterer;
	private ContentReplication contentReplication;
	private TransactionService transactionService;
	private SynchronizationDialect synchronizationDialect;
	private CustomActionManager customActionManager;
	private DictionaryDAO dictionaryDAO;
	
	public void setDataSource(DataSource dataSource_) {
		dataSource = dataSource_;
	}

	public void setDictionaryService(DictionaryService dictionaryService_) {
		dictionaryService = dictionaryService_;
	}

	public void setDatabaseDictionary(DatabaseDictionary databaseDictionary_) {
		databaseDictionary = databaseDictionary_;
	}
	
	public void setFilterer(Filterer filterer_) {
		filterer = filterer_;
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

	public void setDictionaryDAO(DictionaryDAO dictionaryDAO_) {
		dictionaryDAO = dictionaryDAO_;
	}
	

}
