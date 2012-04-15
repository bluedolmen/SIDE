package com.bluexml.side.Integration.alfresco.sql.synchronization.schemaManagement;

import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
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

/**
 * Create the SQL relational schema of the synchronized alfresco class
 * The schema is defined using the database dictionary (package dictionary)
 * The package dictionary is used to load the mapping between the class/attribute/association 
 * and the Database model using all property files of name "synchronisation-database-mapping.properties"
 * 
 * The property file "synchronization.properties" is used to set up the execution parameters of the SQL module: these parameters are loaded by the various beans of the module in the spring context xml file
 * In particular, this file defined the synchrodb.namespacePrefix parameter which gives the namespace prefix of the Alfresco model to take into account (filter by the NamespacePrefixFilterer)
 * and the synchrodb.externalTypesMapping parameter whihc lists the type which are not in the models of the namespace prefix but whihc must be mapped (usually type of the standard alfresco model)
 * 
 * ATTENTION: only the final type (those having no subtypes) are mapped in the SQL model.
 * For the association, the aspect which contains association towards type are replaced by the type which bear this aspect.
 * 
 * ATTENTION: if you want to map an association contained in an aspect and this aspect will be associated at runtime to the objects of a type, you must 
 * manually add the corresponding entries in a "synchronisation-database-mapping.properties" in order this class created the mapped table and in order that the SQL module NodeService synchronizes the data in these tables.
 *
 */
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

		if (logger.isDebugEnabled())
			logger.debug("===============================start createFromModel");
		if (ready) {
			Connection connection = DataSourceUtils.getConnection(dataSource);
			
			List<CreateTableStatement> createStatements = doCreateStatement(modelName);
			
			if (logger.isDebugEnabled())
				logger.debug("Do check status ");
			CheckTableStatus checkTableStatus = doCheckStatus(createStatements, connection);
			statusByModel.put(modelName, checkTableStatus);
			
			if (logger.isDebugEnabled())
				logger.debug(" checkTableStatus= "+checkTableStatus+ " - CheckTableStatus.CREATE_TABLES="+CheckTableStatus.CREATE_TABLES);
			if (checkTableStatus == CheckTableStatus.CREATE_TABLES) {
		
				if (logger.isDebugEnabled())
					logger.debug("Do doExecuteCreateStatements ");
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
		
		if (logger.isDebugEnabled())
			logger.debug("===============================end createFromModel");
	}

	// BEGIN RB - to take into account not bluexml type whihc are target of association, process of the list synchrodb.externalTypesMapping of the synchronization.properties file
	private void createFromExternalTypeMapping() {
		// externalTypesMappingArray contains the types external to the namespaces prefix which must be equally mapped in database as they are target of association
		// this types and an associated attributes (the unique identifier) are given by the parameter synchrodb.externalTypesMapping of the synchronization.properties file
		// Ex : synchrodb.externalTypesMapping=cm:person,cm:authorityContainer,cm:authority
		if (logger.isDebugEnabled())
			logger.debug("===============================start createFromExternalTypeMapping");
		if (ready) {
			Connection connection = DataSourceUtils.getConnection(dataSource);
			
			HashMap<QName, ArrayList<QName>> externalTypesMappingArray = filterer.getExternalTypesMappingArray();		
			Iterator<QName> iterator = externalTypesMappingArray.keySet().iterator();
			List<CreateTableStatement> createStatements = new ArrayList<CreateTableStatement>();;  
			if (logger.isDebugEnabled())
				logger.debug("External Types size="+externalTypesMappingArray.size());
	        while(iterator.hasNext()){        
	            QName externalType = (QName) iterator.next();
				if (logger.isDebugEnabled())
					logger.debug("create statement on external type "+externalType);
				CreateTableStatement currentCreateStatement = createClass(externalType); 								
				createStatements.add(currentCreateStatement);
	        }	
			
			if (logger.isDebugEnabled())
				logger.debug("Do check status ");
			CheckTableStatus checkTableStatus = doCheckStatus(createStatements, connection);
			
			if (logger.isDebugEnabled())
				logger.debug(" checkTableStatus= "+checkTableStatus+ " - CheckTableStatus.CREATE_TABLES="+CheckTableStatus.CREATE_TABLES);
			if (checkTableStatus == CheckTableStatus.CREATE_TABLES) {
		
				if (logger.isDebugEnabled())
					logger.debug("Do doExecuteCreateStatements ");
				boolean creationSuccess = doExecuteCreateStatements(createStatements, connection);
				if (logger.isDebugEnabled())
					logger.debug("  creationSuccess= "+creationSuccess);
				if (! creationSuccess) {
					logger.error("Creation of tables failed");
					ready = false;
				}
				
				DataSourceUtils.releaseConnection(connection, dataSource);
				
			} else {
				if (logger.isDebugEnabled())
					logger.debug("Creation of externalType was not performed since the previous process marked the schema as not ready or creation has yet be done");
			}
			
		}
		if (logger.isDebugEnabled())
			logger.debug("===============================end createFromExternalTypeMapping");
    }
	// END RB - to take into account not bluexml type whihc are target of association, process of the list synchrodb.externalTypesMapping of the synchronization.properties file
	

	private void replicateFromModel(QName modelName){
		
		if (logger.isDebugEnabled())
			logger.debug("===============================start replicateFromModel");
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
		if (logger.isDebugEnabled())
			logger.debug("===============================end replicateFromModel");
	}
	
	public boolean isReady() {
		return ready;
	}
	
	
	private void executeCreateStatement(CreateTableStatement createStatement, Connection connection) throws SQLException {
		Statement sqlStatement = connection.createStatement();
		try {			
			if (logger.isDebugEnabled())
				logger.debug("create statement : "+createStatement.toSQLString());
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
			if (logger.isDebugEnabled())
				logger.debug("type loop - process type : "+type.getLocalName());
			if (filterer.acceptTypeQName(type)) {
				// only create table for final leaf type
				if (dictionaryService.getSubTypes(type, true).size()==1) {
					CreateTableStatement currentCreateStatement = createClass(type); 								
					createStatements.add(currentCreateStatement);
				}
			}
		}
		
		for (QName associationName : dictionaryService.getAssociations(modelName)) {
			if (logger.isDebugEnabled())
				logger.debug("association loop - process association : "+associationName.getLocalName());
			if (filterer.acceptAssociationQName(associationName)) {
				ClassDefinition sourceClassDefinition = dictionaryService.getAssociation(associationName).getSourceClass();
				ClassDefinition targetClassDefinition = dictionaryService.getAssociation(associationName).getTargetClass();
				// BEGIN RB - to take into account association in aspect and from parent class, the association name has been prefixed with the type name in the synchonization-database-mapping.properties file
				if (filterer.acceptTypeQName(sourceClassDefinition.getName())) {
					if (sourceClassDefinition.isAspect()) {
						if (logger.isDebugEnabled())
							logger.debug("association loop - source class is aspect");
						// loop on all the type of the model
						for (QName type : dictionaryService.getTypes(modelName) ) {
							// look at type having the aspect and no subtypes 
							if (filterer.acceptTypeQName(type) && dictionaryService.getSubTypes(type, false).size()==0) {
								TypeDefinition typeDefinition = dictionaryService.getType(type);
								// for each types in the model, found the type who have the current aspect
								for (QName aspect : typeDefinition.getDefaultAspectNames()) {
									if (aspect.isMatch(sourceClassDefinition.getName())) {
										// this type has the aspect => in the synchronisation-database-mapping.properties a key is "association.name.<newAssocName>=<value>"
										if (logger.isDebugEnabled())
											logger.debug("association loop - find source class type="+type.getLocalName());	
										//String newAssocName = associationName.getLocalName().replaceFirst(sourceClassDefinition.getName().getLocalName(), type.getLocalName());
										// a particular case to consider: if the current type is a subtype of the target class, replace the target class by the the current type to avoid to create association with other subtypes considering it is a loopback
										ArrayList<QName> children = getLeafSubTypes(targetClassDefinition.getName());
										if (children.contains(type)) {
											String newAssocName = getAssociationName(associationName, type, type );
											if (logger.isDebugEnabled())
												logger.debug("association loop - new assocNAme="+newAssocName);	
												//newAssocName = newAssocName.replaceFirst(targetClassDefinition.getName().getLocalName(), type.getLocalName());
												QName newAssocQname = QName.createQName(associationName.getNamespaceURI(), newAssocName);
												createStatements.addAll(createStatementsWithSubTypes(newAssocQname, type, type));
										} else {
											for (QName target : children) {
												// no subtypes
												String newAssocName = getAssociationName(associationName, type, target );
												if (logger.isDebugEnabled())
													logger.debug("association loop - new assocNAme="+newAssocName);	
												// the target class may have subtypes and in that case association table must be created for each accepted subtypes
												QName newAssocQname = QName.createQName(associationName.getNamespaceURI(), newAssocName);
												createStatements.addAll(createStatementsWithSubTypes(newAssocQname, type, target));
											} 
										}
									}
								}
							}
						}
						// loop on all the external class defined in the synchronization.properties configuration files
						// external class may have this aspect defined in the model at runtime and then it is necessary to also define association table for this external class
						// we know that they may have this aspect in the synchronization-database-mapping where it may have been manually added
						for (QName type : filterer.getExternalTypesMappingArray().keySet() ) {
							// look at type which are defined in the database dictionary as source of this association. 
							if (dictionaryService.getSubTypes(type, false).size()==0) {
								// check if an association has been defined in a database mapping configuration file for this type => in the synchronisation-database-mapping.properties a key will be "association.name.<newAssocName>=<value>"
								ArrayList<QName> children = getLeafSubTypes(targetClassDefinition.getName());
								for (QName target : children) {
									// no subtypes
									String newAssocName = getAssociationName(associationName, type, target );
									if (databaseDictionary.resolveAssociationAsTableName(newAssocName) != null) {
										QName newAssocQname = QName.createQName(associationName.getNamespaceURI(), newAssocName);
										createStatements.addAll(createStatementsWithSubTypes(newAssocQname, type, target));

									}
								}
							}
						}
					} else {
						if (logger.isDebugEnabled())
							logger.debug("association loop - source class is "+sourceClassDefinition.getTitle());
						createStatements.addAll(createStatementsWithSubTypes(associationName, sourceClassDefinition.getName(), targetClassDefinition.getName()));
						// END RB - to take into account association in aspect and from parent class, the association name has been prefixed with the type name in the synchonization-database-mapping.properties file
					}
				}
			}
		}
		
		if (logger.isDebugEnabled())
			logger.debug("=== doCreateStatement createStatements.size: "+createStatements.size());
		return createStatements;
	}

	
	private CheckTableStatus doCheckStatus (List<CreateTableStatement> createStatements, Connection connection) {
		List<String> matchedTables = new ArrayList<String>();
		List<String> unmatchedTables = new ArrayList<String>();
		List<String> nonExistingTables = new ArrayList<String>();
		
		if (logger.isDebugEnabled())
			logger.debug("  start doCheckStatus");
		for (CreateTableStatement createStatement : createStatements) {
			TableStatus tableStatus = createStatement.checkStatus(connection);
			if (logger.isDebugEnabled())
				logger.debug("  tableStatus="+tableStatus);
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
		if (logger.isDebugEnabled())
			logger.debug("  matchedTables.isEmpty="+matchedTables.isEmpty()+" & unmatchedTables.isEmpty="+unmatchedTables.isEmpty());
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
		if (logger.isDebugEnabled())
			logger.debug("  start doExecuteCreateStatements");
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
		if (logger.isDebugEnabled())
			logger.debug("  start createClass on tableNAme "+tableName);
		
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
				if (logger.isDebugEnabled())
					logger.debug("  accept property : "+originalName+"-"+resolvedColumnName);
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
		if (logger.isDebugEnabled())
			logger.debug("createAssociation - tableName ="+tableName);	

		Map<String, List<String>> columns = new LinkedHashMap<String, List<String>>();
		columns.put(ASSOCIATION_ID_COLUMN_NAME, new ArrayList<String>() {{add("INTEGER");}});
		final String sourceIdColumnName = databaseDictionary.resolveAttributeAsColumnName(ALFRESCO_DBID_COLUMN_NAME, sourceClassQName.getLocalName());
		final String targetIdColumnName = databaseDictionary.resolveAttributeAsColumnName(ALFRESCO_DBID_COLUMN_NAME, targetClassQName.getLocalName());
		CreateTableStatement.Builder builder;
		

		if (databaseDictionary.getSourceAlias(associationName).equals(databaseDictionary.getTargetAlias(associationName))) {
			// special case for loop
			columns.put(databaseDictionary.getSourceAlias(associationName)+"1", new ArrayList<String>() {{add("INTEGER"); }});
			columns.put(databaseDictionary.getTargetAlias(associationName)+"2", new ArrayList<String>() {{add("INTEGER"); }});

			builder = 
			synchronizationDialect.newCreateTableStatementBuilder(tableName)
			.columns(columns)
			.tableType(TableType.TABLE_ASSOCIATION)
			.pkConstraint(databaseDictionary.getSourceAlias(associationName)+"1")
			.pkConstraint(databaseDictionary.getTargetAlias(associationName)+"2")
			.fkConstraint(databaseDictionary.getSourceAlias(associationName)+"1", databaseDictionary.getSourceClass(associationName), sourceIdColumnName)
			.fkConstraint(databaseDictionary.getTargetAlias(associationName)+"2", databaseDictionary.getTargetClass(associationName), targetIdColumnName);			
		} else {
			columns.put(databaseDictionary.getSourceAlias(associationName), new ArrayList<String>() {{add("INTEGER"); }});
			columns.put(databaseDictionary.getTargetAlias(associationName), new ArrayList<String>() {{add("INTEGER"); }});

			builder = 
			synchronizationDialect.newCreateTableStatementBuilder(tableName)
			.columns(columns)
			.tableType(TableType.TABLE_ASSOCIATION)
			.pkConstraint(databaseDictionary.getSourceAlias(associationName))
			.pkConstraint(databaseDictionary.getTargetAlias(associationName))
			.fkConstraint(databaseDictionary.getSourceAlias(associationName), databaseDictionary.getSourceClass(associationName), sourceIdColumnName)
			.fkConstraint(databaseDictionary.getTargetAlias(associationName), databaseDictionary.getTargetClass(associationName), targetIdColumnName);
		}
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

		createFromExternalTypeMapping();

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
		if (logger.isDebugEnabled()) logger.debug("Checking meta-data");
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

	/**
	 * create the statements for assocation according to the following synopsis:
	 * - if source is an aspect, create a statement for each type which has this aspect:
	 *   in this case, the associationName of each statement is prefixed with the corresponding found type having this aspect
	 * - if source or target have subtypes, create a statement for each final leaf subtypes:
	 *   in this case, the parent source type in the associationName of each statement is replaced with the corresponding source subtypes if any and the parent target type is replaced with the corresponding target subtypes if any
	 *   in the case where the source = target, there is no combination of subtypes of source/target and the parent source and target of the associationName are replaced by the same subtype
	 * @param associationName the association name
	 * @param source the source type of the association
	 * @param target the target type of the association
	 * @return the list of table create statement
	 */
	private List<CreateTableStatement> createStatementsWithSubTypes(QName associationName, QName source, QName target) {
		List<CreateTableStatement> createStatements = new ArrayList<CreateTableStatement>();
		if (filterer.acceptTypeQName(associationName) && filterer.acceptTypeQName(source) && filterer.acceptTypeQName(target) ) {
			ArrayList<QName> sources = getLeafSubTypes(source);
			ArrayList<QName> targets = getLeafSubTypes(target);
			if (sources.size()>1) {
				for (int i=0; i < sources.size();i++) {	
					QName s = sources.get(i);
					if (filterer.acceptTypeQName(s) && !s.equals(source)) {
						if (source.equals(target)) {
							String newAssocName = associationName.getLocalName().replaceAll(source.getLocalName(), s.getLocalName());
							QName newAssocQname = QName.createQName(associationName.getNamespaceURI(), newAssocName);
							if (logger.isDebugEnabled())
								logger.debug(" source has subtype and equal target - create assoc with subtype ="+newAssocQname.getLocalName());	
							CreateTableStatement currentCreateStatement = createAssociation(newAssocQname, s, targets.get(i));
							createStatements.add(currentCreateStatement);
							
						} else {
							if (targets.size()>1) {
								for (int j=0; j < targets.size();j++) {										
									QName t = targets.get(j);
									if (filterer.acceptTypeQName(t) && !t.equals(target)) {
										String newAssocName = associationName.getLocalName().replaceFirst(source.getLocalName(), s.getLocalName()).replaceFirst(target.getLocalName(), t.getLocalName());
										QName newAssocQname = QName.createQName(associationName.getNamespaceURI(), newAssocName);
										if (logger.isDebugEnabled())
											logger.debug(" target has subtype - create assoc with subtype ="+newAssocQname.getLocalName());	
										CreateTableStatement currentCreateStatement = createAssociation(newAssocQname, s, t);
										createStatements.add(currentCreateStatement);
									}
								}
							} else {
								if (filterer.acceptTypeQName(target)) {
									String newAssocName = associationName.getLocalName().replaceFirst(source.getLocalName(), s.getLocalName());
									QName newAssocQname = QName.createQName(associationName.getNamespaceURI(), newAssocName);
									if (logger.isDebugEnabled())
										logger.debug(" source has subtype but not target - create assoc with ="+newAssocQname.getLocalName());	
									CreateTableStatement currentCreateStatement = createAssociation(newAssocQname, s, target);
									createStatements.add(currentCreateStatement);
								}
							}
							
						}
					}
				}
			} else {
				if (targets.size()>1) {
					for (int i=0; i < targets.size();i++) {															
						QName t = targets.get(i);
						if (filterer.acceptTypeQName(t) && !t.equals(target)) {
							String newAssocName = associationName.getLocalName().replaceFirst(target.getLocalName(), t.getLocalName());
							QName newAssocQname = QName.createQName(associationName.getNamespaceURI(), newAssocName);
							if (logger.isDebugEnabled())
								logger.debug(" source has no subtype but target has - create assoc with subtype ="+newAssocQname.getLocalName());	
							CreateTableStatement currentCreateStatement = createAssociation(newAssocQname, source, targets.get(i));
							createStatements.add(currentCreateStatement);
						}
					}
				} else {
					if (filterer.acceptTypeQName(target)) {
						if (logger.isDebugEnabled())
							logger.debug(" source and target have no subtype - create assoc with ="+associationName.getLocalName());	
						CreateTableStatement currentCreateStatement = createAssociation(associationName, source, target);
						createStatements.add(currentCreateStatement);
					}
				}
			}
		}
		return createStatements;
	}

	/**
	 * Get the final leaf subtypes of type
	 * @param type the type to get the leaf subtypes
	 * @return the list of leaf subtypes if any or a list containing just the type has it has no subtype
	 */
	private ArrayList<QName> getLeafSubTypes(QName type) {
		ArrayList<QName> leafSubTypes = new ArrayList<QName>();
		Collection<QName> subTypes = dictionaryService.getSubTypes(type, true);
		if (subTypes.size()>1) {
			Iterator<QName> it = subTypes.iterator();
			while (it.hasNext()) {
				QName subtype = (QName) it.next();
				if (!subtype.equals(type)) {
					Collection<QName> st = dictionaryService.getSubTypes(subtype, false);
					if (st.size()==0) {
						leafSubTypes.add(subtype);
					}
				}
			}
		} else {
			leafSubTypes.add(type);
		}
		return leafSubTypes;
	}

	
	private String getAssociationName(QName association, QName qnSource, QName qnTarget ) {
		String associationName = association.getLocalName();
		if (logger.isDebugEnabled()) logger.debug("getAssociationName - associationName="+associationName);
		ClassDefinition sourceClassDefinition = dictionaryService.getAssociation(association).getSourceClass();
		ClassDefinition targetClassDefinition = dictionaryService.getAssociation(association).getTargetClass();
		if (sourceClassDefinition.isAspect() || dictionaryService.getSubTypes(sourceClassDefinition.getName(), false).size()>0) {
			if (dictionaryService.getSubTypes(targetClassDefinition.getName(), false).size()>0) {
				associationName=associationName.replaceFirst(sourceClassDefinition.getName().getLocalName(),qnSource.getLocalName()).replaceFirst(targetClassDefinition.getName().getLocalName(),qnTarget.getLocalName());						
			} else associationName=associationName.replaceFirst(sourceClassDefinition.getName().getLocalName(),qnSource.getLocalName());
		}
		if (logger.isDebugEnabled()) logger.debug("Schema Creation - getAssociationName - associationName="+associationName);
		return associationName;
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
