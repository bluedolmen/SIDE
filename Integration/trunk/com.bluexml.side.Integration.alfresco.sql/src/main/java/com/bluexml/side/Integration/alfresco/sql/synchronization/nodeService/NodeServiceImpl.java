package com.bluexml.side.Integration.alfresco.sql.synchronization.nodeService;

import java.io.Serializable;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.alfresco.model.ContentModel;
import org.alfresco.repo.transaction.TransactionListener;
import org.alfresco.service.cmr.dictionary.AspectDefinition;
import org.alfresco.service.cmr.dictionary.DataTypeDefinition;
import org.alfresco.service.cmr.dictionary.DictionaryService;
import org.alfresco.service.cmr.dictionary.PropertyDefinition;
import org.alfresco.service.cmr.dictionary.TypeDefinition;
import org.alfresco.service.cmr.repository.NodeRef;
import org.alfresco.service.namespace.QName;
import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;

import com.bluexml.side.Integration.alfresco.sql.synchronization.common.JdbcTransactionListener;
import com.bluexml.side.Integration.alfresco.sql.synchronization.common.NodeFilterer;
import com.bluexml.side.Integration.alfresco.sql.synchronization.common.NodeHelper;
import com.bluexml.side.Integration.alfresco.sql.synchronization.dialects.SynchronizationDialect;
import com.bluexml.side.Integration.alfresco.sql.synchronization.dictionary.DatabaseDictionary;

public class NodeServiceImpl extends AbstractNodeServiceImpl {

	private Logger logger = Logger.getLogger(getClass());
	
	public void create(NodeRef nodeRef)  {		
		QName nodeType = nodeService.getType(nodeRef);
		String type_name = nodeType.getLocalName();
		List<String> sqlQueries = new ArrayList<String>();

		List<QName> parentNames = nodeHelper.getParentQNames(nodeRef);
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
				if (nodeFilterer.acceptOnName(key)) {
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
	
		invokeOnCreateNode(nodeRef);
	}

	public void delete(NodeRef nodeRef)  {
		List<QName> parentNames = nodeHelper.getParentQNames(nodeRef);
		for (QName type_qname : parentNames) {
			String type_name = type_qname.getLocalName();

			String simplified_type_name = databaseDictionary.resolveClassAsTableName(type_name);
			Serializable dbid = nodeService.getProperty(nodeRef, ContentModel.PROP_NODE_DBID);

			String sql_query = String.format("DELETE FROM %1$s WHERE id = %2$s", simplified_type_name, dbid);
			executeSQLQuery(sql_query);
		}
		
		invokeOnDeleteNode(nodeRef);
	}
	
	
	public void updateProperties(NodeRef nodeRef, Collection<QName> changes) { 
		String type_name = nodeService.getType(nodeRef).getLocalName();
		List<String> sqlQueries = new ArrayList<String>();

		List<QName> parentNames = nodeHelper.getParentQNames(nodeRef);
		Map<QName, Serializable> nodeProperties = nodeService.getProperties(nodeRef);

		for (QName type_qname : parentNames) {
			type_name = type_qname.getLocalName();

			String simplified_type_name = databaseDictionary.resolveClassAsTableName(type_name);
			Serializable dbid = nodeService.getProperty(nodeRef, ContentModel.PROP_NODE_DBID);

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
		
		String associationName = typeQName.getLocalName();
		
		Serializable sourceId = nodeService.getProperty(sourceNodeRef, ContentModel.PROP_NODE_DBID);
		Serializable targetId = nodeService.getProperty(targetNodeRef, ContentModel.PROP_NODE_DBID);

		createAssociation(associationName , sourceId, targetId);
		
		invokeOnCreateAssociation(sourceNodeRef, targetNodeRef, typeQName);
	}

	public void deleteAssociation(NodeRef sourceNodeRef, NodeRef targetNodeRef, QName typeQName)  {
		
		String associationName = typeQName.getLocalName();

		Serializable sourceId = nodeService.getProperty(sourceNodeRef, ContentModel.PROP_NODE_DBID);
		Serializable targetId = nodeService.getProperty(targetNodeRef, ContentModel.PROP_NODE_DBID);

		deleteAssociation(associationName, sourceId, targetId);
		
		invokeOnDeleteAssociation(sourceNodeRef, targetNodeRef, typeQName);
	}

	private void createAssociation(String associationName, Serializable sourceId, Serializable targetId)  {
		// Retrieve a simplified association name
		String databaseAssociationName = databaseDictionary.resolveAssociationAsTableName(associationName);
		String sourceClassName = databaseDictionary.getSourceAlias(associationName);
		String targetClassName = databaseDictionary.getTargetAlias(associationName);

		String sql_query = String.format("INSERT %1$s ( %2$s , %3$s ) VALUES ( %4$s , %5$s )", databaseAssociationName, sourceClassName, targetClassName, sourceId, targetId);
		executeSQLQuery(sql_query);
	}

	private void deleteAssociation(String associationName, Serializable sourceId, Serializable targetId)  {

		String databaseAssociationName = databaseDictionary.resolveAssociationAsTableName(associationName);
		String sourceClassName = databaseDictionary.getSourceAlias(associationName);
		String targetClassName = databaseDictionary.getTargetAlias(associationName);
		
		String sql_query = String.format("DELETE FROM %1$s WHERE %2$s = %3$s AND %4$s = %5$s", databaseAssociationName, sourceClassName, sourceId, targetClassName, targetId);
		executeSQLQuery(sql_query);		
	}

	
	/*
	 * Helper methods
	 */
	
	/*
	 * Helper methods that call the transaction listener by translating the SQL Exception
	 * into an higher level one
	 */
	private void executeSQLQuery(String sqlQuery) {
		try {
			transactionListener.executeSQLQuery(sqlQuery);
		} catch (SQLException e) {
			throw new NodeServiceFailureException(e);
		}
	}

	private void executeSQLQuery(List<String> sqlQueries) {
		try {
			transactionListener.executeSQLQuery(sqlQueries);
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
	
	
	//
	// IoC/DI Spring
	//

	// Dependencies
	private DictionaryService dictionaryService;
	private DatabaseDictionary databaseDictionary;
	private JdbcTransactionListener transactionListener;
	private NodeFilterer nodeFilterer;
	private NodeHelper nodeHelper;
	private SynchronizationDialect synchronizationDialect;


	public void setDictionaryService(DictionaryService dictionaryService_) {
		dictionaryService = dictionaryService_;
	}

	public void setDatabaseDictionary(DatabaseDictionary dbd_) {
		databaseDictionary = dbd_;
	}

	public void setNodeFilterer(NodeFilterer nodeFilterer_) {
		nodeFilterer = nodeFilterer_;
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
