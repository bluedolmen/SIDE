package com.bluexml.side.Integration.alfresco.sql.synchronization.nodeService;

import java.io.Serializable;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.alfresco.error.AlfrescoRuntimeException;
import org.alfresco.model.ContentModel;
import org.alfresco.repo.transaction.AlfrescoTransactionSupport;
import org.alfresco.repo.transaction.TransactionListener;
import org.alfresco.service.cmr.dictionary.AspectDefinition;
import org.alfresco.service.cmr.dictionary.DataTypeDefinition;
import org.alfresco.service.cmr.dictionary.DictionaryService;
import org.alfresco.service.cmr.dictionary.PropertyDefinition;
import org.alfresco.service.cmr.dictionary.TypeDefinition;
import org.alfresco.service.cmr.repository.AssociationRef;
import org.alfresco.service.cmr.repository.ChildAssociationRef;
import org.alfresco.service.cmr.repository.NodeRef;
import org.alfresco.service.cmr.repository.StoreRef;
import org.alfresco.service.namespace.QName;
import org.alfresco.service.namespace.RegexQNamePattern;
import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;

import com.bluexml.side.Integration.alfresco.sql.synchronization.common.JdbcTransactionListener;
import com.bluexml.side.Integration.alfresco.sql.synchronization.common.Filterer;
import com.bluexml.side.Integration.alfresco.sql.synchronization.common.NodeHelper;
import com.bluexml.side.Integration.alfresco.sql.synchronization.dialects.SynchronizationDialect;
import com.bluexml.side.Integration.alfresco.sql.synchronization.dictionary.DatabaseDictionary;

public class NodeServiceImpl extends AbstractNodeServiceImpl {

	private Logger logger = Logger.getLogger(getClass());
	private final static String PROCESSED_ASSOCIATION_REF_CONTEXT = NodeServiceImpl.class.getCanonicalName() + ".associationRef";
	private final static String PROCESSED_CHILD_ASSOCIATION_REF_CONTEXT = NodeServiceImpl.class.getCanonicalName() + ".childAssociationRef";
	private final static String PROCESSED_ASSOCIATIONS_OPERATION_TYPE = NodeServiceImpl.class.getCanonicalName() + ".type";
	
	private enum OperationType {
		CREATE,
		DELETE
	}
	
	private Collection<NodeRef> synchronizedNodes = new ArrayList<NodeRef>();
	private Collection<AssociationRef> synchronizedAssociations = new ArrayList<AssociationRef>();
	
	/**
	 * @return the synchronizedAssociations
	 */
	public Collection<AssociationRef> getSynchronizedAssociations() {
		return synchronizedAssociations;
	}

	/**
	 * @param synchronizedAssociations the synchronizedAssociations to set
	 */
	public void setSynchronizedAssociations(
			Collection<AssociationRef> synchronizedAssociations) {
		this.synchronizedAssociations = synchronizedAssociations;
	}

	private void createCore(NodeRef nodeRef) {
		QName nodeType = nodeService.getType(nodeRef);
		String type_name = nodeType.getLocalName();
		List<String> sqlQueries = new ArrayList<String>();

		List<QName> parentNames = nodeHelper.getParentAndSelfQNames(nodeRef);
		Map<QName, Serializable> nodeProperties = nodeService.getProperties(nodeRef);
		
		for (QName type_qname : parentNames) {
			type_name = type_qname.getLocalName();

			String simplified_type_name = databaseDictionary.resolveClassAsTableName(type_name);

			Map<String, String> properties = new LinkedHashMap<String, String>();
			// We will only process the properties which are related
			// to the current type
			TypeDefinition currentTypeDefinition = dictionaryService.getType(type_qname);
			Map<QName, PropertyDefinition> currentTypeProperties = new HashMap<QName, PropertyDefinition>();
			currentTypeProperties.putAll(currentTypeDefinition.getProperties());
			for (AspectDefinition ad : currentTypeDefinition.getDefaultAspects()) {
				currentTypeProperties.putAll(ad.getProperties());
			}

			Set<QName> iterablePropertiesKeySet = new HashSet<QName>(nodeProperties.keySet());
			Set<QName> currentTypePropertiesKeySet = currentTypeProperties.keySet();
			iterablePropertiesKeySet.retainAll(currentTypePropertiesKeySet);

			for (QName key : iterablePropertiesKeySet) {
				if (filterer.acceptPropertyQName(key)) {
					PropertyDefinition propertyDefinition = dictionaryService.getProperty(key);
					String value = getSQLFormatFromSerializable(nodeProperties.get(key), propertyDefinition);
					
					String originalName = key.getLocalName();
					String resolvedColumnName = databaseDictionary.resolveAttributeAsColumnName(originalName, type_name);
					
					properties.put((resolvedColumnName != null ? resolvedColumnName : originalName), value);
				}
			}

			String ids = StringUtils.join(properties.keySet().iterator(), " , ");
			String values = StringUtils.join(properties.values().iterator(), " , ");

			if (!properties.isEmpty()) {
				String sql_query = String.format("INSERT INTO %1$s ( %2$s ) VALUES ( %3$s )", simplified_type_name, ids, values);
				sqlQueries.add(sql_query);
			} else {
				logger.error("You must accept at least the node id in the definition of the node filterer");
			}			
		}
		
		executeSQLQuery(sqlQueries);		
	}
	
	public void create(NodeRef nodeRef)  {		
		createCore(nodeRef);
	
		invokeOnCreateNode(nodeRef);
	}

	public void createWithAssociations(NodeRef nodeRef) {
		if (!synchronizedNodes.contains(nodeRef)){
			createCore(nodeRef);
			
			createAllRelatedAssociations(nodeRef);
		
			invokeOnCreateNode(nodeRef);
		}
	}
	
	public void delete(NodeRef nodeRef)  {
		/*
		 * Remove all the incoming/outcoming associations. This is necessary to ensure that foreign keys constraints
		 * are not violated.
		 * When moving a node from workspace to archive, the Alfresco Node Service notify a deletion of the node.
		 * However, all the associations are still existing and not deleted in the alfresco content store.
		 * In terms of SQL replication, these references are a nonsense and have to be removed.
		 * This method has a slight inconvenient since on normal deletion, the delete association methods will be
		 * called in addition. Even if the SQL statement is harmless, this process is however useless and lose some time.
		 * Practically, when a node is permanently deleted, no delete association policy seems to be raised...
		 */
		deleteAllRelatedAssociations(nodeRef);
		
		List<QName> parentNames = nodeHelper.getParentAndSelfQNames(nodeRef);
		List<String> sqlQueries = new ArrayList<String>(parentNames.size());
		
		for (QName type_qname : parentNames) {
			String type_name = type_qname.getLocalName();

			String simplified_type_name = databaseDictionary.resolveClassAsTableName(type_name);
			Serializable dbid = getDbId(nodeRef);

			String sql_query = String.format("DELETE FROM %1$s WHERE id = %2$s", simplified_type_name, dbid);
			sqlQueries.add(sql_query);
		}

		executeSQLQuery(sqlQueries);

		invokeOnDeleteNode(nodeRef);
	}
	
	
	public void updateProperties(NodeRef nodeRef, Collection<QName> changes) { 
		String type_name = nodeService.getType(nodeRef).getLocalName();
		List<String> sqlQueries = new ArrayList<String>();

		List<QName> parentNames = nodeHelper.getParentAndSelfQNames(nodeRef);
		Map<QName, Serializable> nodeProperties = nodeService.getProperties(nodeRef);

		for (QName type_qname : parentNames) {
			type_name = type_qname.getLocalName();

			String simplified_type_name = databaseDictionary.resolveClassAsTableName(type_name);
			Serializable dbid = getDbId(nodeRef);

			TypeDefinition currentTypeDefinition = dictionaryService.getType(type_qname);
			Map<QName, PropertyDefinition> currentTypeProperties = new HashMap<QName, PropertyDefinition>();
			currentTypeProperties.putAll(currentTypeDefinition.getProperties());
			for (AspectDefinition ad : currentTypeDefinition.getDefaultAspects()) {
				currentTypeProperties.putAll(ad.getProperties());
			}
						
			Set<QName> iterablePropertiesKeySet = new HashSet<QName>(changes);
			Set<QName> currentTypePropertiesKeySet = currentTypeProperties.keySet();
			iterablePropertiesKeySet.retainAll(currentTypePropertiesKeySet); 
			// Remove intrinsic properties
			iterablePropertiesKeySet.remove(ContentModel.PROP_NODE_DBID);
			iterablePropertiesKeySet.remove(ContentModel.PROP_NODE_UUID);
			
			for (QName key : iterablePropertiesKeySet ) {
				Serializable property = nodeProperties.get(key); 
				PropertyDefinition propertyDefinition = dictionaryService.getProperty(key);
				String value = getSQLFormatFromSerializable(property, propertyDefinition);
				
				String originalName = key.getLocalName();
				String resolvedColumnName = databaseDictionary.resolveAttributeAsColumnName(originalName, type_name);
				
				String sql_query = String.format("UPDATE %1$s SET %2$s = %3$s WHERE id = %4$s", 
						simplified_type_name, (resolvedColumnName != null ? resolvedColumnName : originalName), value, dbid);
				sqlQueries.add(sql_query);
			}
		}
		
		executeSQLQuery(sqlQueries);

		invokeOnUpdateProperties(nodeRef, changes);
	}


	public void createAssociation(NodeRef sourceNodeRef, NodeRef targetNodeRef, QName typeQName) {
		
		if (!isSynchronized(sourceNodeRef)){
			createCore(sourceNodeRef);
			synchronizedNodes.add(sourceNodeRef);
		}
		
		if (!isSynchronized(targetNodeRef)){
			createCore(targetNodeRef);
			synchronizedNodes.add(targetNodeRef);
		}
		
		String associationName = typeQName.getLocalName();
		
		Serializable sourceId = getDbId(sourceNodeRef);
		Serializable targetId = getDbId(targetNodeRef);

		String databaseAssociationName = databaseDictionary.resolveAssociationAsTableName(associationName);
		String sourceClassName = databaseDictionary.getSourceAlias(associationName);
		String targetClassName = databaseDictionary.getTargetAlias(associationName);

		String sql_query = String.format("INSERT %1$s ( %2$s , %3$s ) VALUES ( %4$s , %5$s )", databaseAssociationName, sourceClassName, targetClassName, sourceId, targetId);
		
		if (executeSQLQuery(sql_query) > 0) {
			invokeOnCreateAssociation(sourceNodeRef, targetNodeRef, typeQName);
			AssociationRef assocRef = new AssociationRef(sourceNodeRef, typeQName, targetNodeRef);
			synchronizedAssociations.add(assocRef);
		}
	}

	public void deleteAssociation(NodeRef sourceNodeRef, NodeRef targetNodeRef, QName typeQName)  {
		
		String associationName = typeQName.getLocalName();

		Serializable sourceId = getDbId(sourceNodeRef);
		Serializable targetId = getDbId(targetNodeRef);

		String databaseAssociationName = databaseDictionary.resolveAssociationAsTableName(associationName);
		String sourceClassName = databaseDictionary.getSourceAlias(associationName);
		String targetClassName = databaseDictionary.getTargetAlias(associationName);
		
		String sql_query = String.format("DELETE FROM %1$s WHERE %2$s = %3$s AND %4$s = %5$s", databaseAssociationName, sourceClassName, sourceId, targetClassName, targetId);
		
		if (executeSQLQuery(sql_query) > 0) {		
			invokeOnDeleteAssociation(sourceNodeRef, targetNodeRef, typeQName);
		}
	}

	/*
	 * Create associations related to a particular nodeRef.
	 * Related associations are those source/target parent/child associations which are linked to the given node
	 * and which have both association ends (nodes) on the workspace spaces store.
	 */
	private void createAllRelatedAssociations(NodeRef nodeRef) {
		Set<AssociationRef> assocs  = new HashSet<AssociationRef>();
		assocs.addAll(nodeService.getSourceAssocs(nodeRef, RegexQNamePattern.MATCH_ALL));
		assocs.addAll(nodeService.getTargetAssocs(nodeRef, RegexQNamePattern.MATCH_ALL));
		//assocs.removeAll(getProcessedAssociations());
		
		for (AssociationRef assoc : assocs) {
			if (	
					filterer.acceptAssociationQName(assoc.getTypeQName()) && 
					StoreRef.STORE_REF_WORKSPACE_SPACESSTORE.equals(assoc.getSourceRef().getStoreRef()) &&
					StoreRef.STORE_REF_WORKSPACE_SPACESSTORE.equals(assoc.getTargetRef().getStoreRef())
				) {
				createAssociation(assoc.getSourceRef(), assoc.getTargetRef(), assoc.getTypeQName());
			}
		}
		
		Set<ChildAssociationRef> childAssocs = new HashSet<ChildAssociationRef>();
		childAssocs.addAll(nodeService.getChildAssocs(nodeRef));
		childAssocs.addAll(nodeService.getParentAssocs(nodeRef));
		//childAssocs.removeAll(getProcessedChildAssociations());

		for (ChildAssociationRef assoc : childAssocs) {
			if (
					filterer.acceptAssociationQName(assoc.getTypeQName()) && 
					StoreRef.STORE_REF_WORKSPACE_SPACESSTORE.equals(assoc.getParentRef().getStoreRef()) &&
					StoreRef.STORE_REF_WORKSPACE_SPACESSTORE.equals(assoc.getChildRef().getStoreRef())
				) {
				createAssociation(assoc.getParentRef(), assoc.getChildRef(), assoc.getTypeQName());
			}
		}
		
		//setProcessed(assocs, childAssocs, OperationType.CREATE);
	}
	
	/*
	 * deleteAllRelatedAssociations uses a transaction support to store the processed associations.
	 * This mechanism is used since this method is called on the trigger "beforeDelete" (triggering on
	 * onDelete was not possible since the node is become inaccessible at this step). When cascading
	 * delete, we might delete previously deleted associations depending on the configuration. 
	 * More, using an approach like the one performed in the "createAllRelatedAssociations" method is
	 * not possible since, when executed, delete method did not actually removed the node from the
	 * workspace.
	 * The execution of the supplementary delete statements is harmless, so the current transactional
	 * mechanism could be removed (triggers are not raised if the delete had no effect). However,
	 * the output would be polluted by extra delete statements and useless connections would be made to
	 * the replicate database. A comparison in terms of time and memory performances could be performed 
	 * to evaluate the benefits of each of the approaches.
	 */
	private void deleteAllRelatedAssociations(NodeRef nodeRef) {
		Set<AssociationRef> assocs  = new HashSet<AssociationRef>();
		assocs.addAll(nodeService.getSourceAssocs(nodeRef, RegexQNamePattern.MATCH_ALL));
		assocs.addAll(nodeService.getTargetAssocs(nodeRef, RegexQNamePattern.MATCH_ALL));
		assocs.removeAll(getProcessedAssociations());
		
		for (AssociationRef assoc : assocs) {
			if (filterer.acceptAssociationQName(assoc.getTypeQName()) ) {
				deleteAssociation(assoc.getSourceRef(), assoc.getTargetRef(), assoc.getTypeQName());
			}
		}
		
		Set<ChildAssociationRef> childAssocs = new HashSet<ChildAssociationRef>();
		childAssocs.addAll(nodeService.getChildAssocs(nodeRef));
		childAssocs.addAll(nodeService.getParentAssocs(nodeRef));
		childAssocs.removeAll(getProcessedChildAssociations());

		for (ChildAssociationRef assoc : childAssocs) {
			if (filterer.acceptAssociationQName(assoc.getTypeQName()) ) {
				// TODO : check whether the node has several parents => certainly a problem (warn ?) in terms of the composition semantics
				deleteAssociation(assoc.getParentRef(), assoc.getChildRef(), assoc.getTypeQName());
			}
		}
		
		setProcessed(assocs, childAssocs, OperationType.DELETE);
	}
	
	/*
	 * Helper methods
	 */
	
	/*
	 * Helper methods that call the transaction listener by translating the SQL Exception
	 * into an higher level one
	 */
	private int executeSQLQuery(String sqlQuery) {
		try {
			return transactionListener.executeSQLQuery(sqlQuery);
		} catch (SQLException e) {
			throw new NodeServiceFailureException(e);
		}
	}

	private int[] executeSQLQuery(List<String> sqlQueries) {
		try {
			return transactionListener.executeSQLQuery(sqlQueries);
		} catch (SQLException e) {
			throw new NodeServiceFailureException(e);
		}
	}
	
	private int executeSelectQuery(String sqlQuery){
		try {
			return transactionListener.executeSelectQuery(sqlQuery);
		} catch (SQLException e) {
			throw new NodeServiceFailureException(e);
		}
	}

	private String getSQLFormatFromSerializable(Serializable property, PropertyDefinition propertyDefinition) {
		String value = null;
		QName dataTypeName = propertyDefinition.getDataType().getName();
		
		if (property == null) {
			value = "NULL";
		} else if (dataTypeName.equals(DataTypeDefinition.DATE) ) {
			DateFormat dateformat = new SimpleDateFormat("yyyy-MM-dd");
			value = String.format("{d '%1$s'}", dateformat.format((Date) property) );
		}  else if (dataTypeName.equals(DataTypeDefinition.DATETIME) ) {
			DateFormat dateformat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS");
			value = String.format("{ts '%1$s'}", dateformat.format((Date) property) );			
		} else {
			value = property.toString();
			
			/* escape string values */
			if (
					dataTypeName.equals(DataTypeDefinition.TEXT) ||
					dataTypeName.equals(DataTypeDefinition.PATH) ||
					dataTypeName.equals(DataTypeDefinition.NODE_REF)
				) {
				value = synchronizationDialect.quoteString(synchronizationDialect.escape(value));
			} else if ("".equals(value)) {
				/*
				 * not a string quoted value and does not have a value implies a NULL value
				 */
				value = "NULL"; 
			}
		}
		
		return value;
	}
	
	/*
	 * Material relative to resource transaction management
	 * We store the set of processed associations when we delete or create a node 
	 */
	private void setProcessed(Collection<AssociationRef> associationRefs, Collection<ChildAssociationRef> childAssociationRefs, OperationType operationType) {
		if (! (associationRefs.isEmpty() && childAssociationRefs.isEmpty()) ) {
			Set<AssociationRef> processedAssociationRefs = AlfrescoTransactionSupport.getResource(PROCESSED_ASSOCIATION_REF_CONTEXT);
			Set<ChildAssociationRef> processedChildAssociationRefs = AlfrescoTransactionSupport.getResource(PROCESSED_CHILD_ASSOCIATION_REF_CONTEXT);
			OperationType previousOperationType = AlfrescoTransactionSupport.getResource(PROCESSED_ASSOCIATIONS_OPERATION_TYPE);
			
			if (previousOperationType != null) {
				if (previousOperationType != operationType) {
					logger.error("Previous operation does not match the current one. In the same transaction, a mix between creating and deleting nodes cannot be performed");
					throw new AlfrescoRuntimeException("Previous operation does not match the current one.");
				}
			} else {
				AlfrescoTransactionSupport.bindResource(PROCESSED_ASSOCIATIONS_OPERATION_TYPE, operationType);				
			}
			
			if (processedAssociationRefs == null) {
				processedAssociationRefs = new HashSet<AssociationRef>(associationRefs);
				AlfrescoTransactionSupport.bindResource(PROCESSED_ASSOCIATION_REF_CONTEXT, processedAssociationRefs);
			} else {
				processedAssociationRefs.addAll(associationRefs);
			}
	
			if (processedChildAssociationRefs == null) {
				processedChildAssociationRefs = new HashSet<ChildAssociationRef>(childAssociationRefs);
				AlfrescoTransactionSupport.bindResource(PROCESSED_CHILD_ASSOCIATION_REF_CONTEXT, processedChildAssociationRefs);
			} else {
				processedChildAssociationRefs.addAll(childAssociationRefs);
			}
			
		}
	}
	
	private Set<AssociationRef> getProcessedAssociations() {
		Set<AssociationRef> processedAssociationRefs = AlfrescoTransactionSupport.getResource(PROCESSED_ASSOCIATION_REF_CONTEXT);
		if (processedAssociationRefs == null) {
			processedAssociationRefs = Collections.emptySet();
		}
		
		return processedAssociationRefs;
	}
	
	private Set<AssociationRef> getProcessedChildAssociations() {
		Set<AssociationRef> processedChildAssociationRefs = AlfrescoTransactionSupport.getResource(PROCESSED_CHILD_ASSOCIATION_REF_CONTEXT);
		
		if (processedChildAssociationRefs == null) {
			processedChildAssociationRefs = Collections.emptySet();
		}
		
		return processedChildAssociationRefs;
	}
	
	private boolean isSynchronized(NodeRef node){
		QName type = nodeService.getType(node);
		
		String typeName = type.getLocalName();
		String table = databaseDictionary.resolveClassAsTableName(typeName);
		
		Serializable id = getDbId(node);
		
		String sql_query = String.format("SELECT id FROM %1$s WHERE id = %2$s", table, id);

		return executeSelectQuery(sql_query) == 1;
	}

	//
	// IoC/DI Spring
	//

	// Dependencies
	private DictionaryService dictionaryService;
	private DatabaseDictionary databaseDictionary;
	private JdbcTransactionListener transactionListener;
	private Filterer filterer;
	private NodeHelper nodeHelper;
	private SynchronizationDialect synchronizationDialect;


	public void setDictionaryService(DictionaryService dictionaryService_) {
		dictionaryService = dictionaryService_;
	}

	public void setDatabaseDictionary(DatabaseDictionary dbd_) {
		databaseDictionary = dbd_;
	}

	public void setFilterer(Filterer filterer_) {
		filterer = filterer_;
	}
	
	public void setNodeHelper(NodeHelper nodeHelper_) {
		nodeHelper = nodeHelper_;
	}

	public void setSynchronizationDialect(SynchronizationDialect synchronizationDialect_) {
		synchronizationDialect = synchronizationDialect_;
	}
	
	public void setTransactionListener(TransactionListener transactionListener_) {
		if (! (transactionListener_ instanceof JdbcTransactionListener) ) {
			logger.error("NodeServiceImpl needs a JdbcTransactionListener since implementation is relative to sql synchronization");
		}
		transactionListener = (JdbcTransactionListener) transactionListener_;
	}
	
}
