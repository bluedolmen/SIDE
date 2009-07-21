package com.bluexml.alfresco.modules.sql.synchronisation;

import java.io.Serializable;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.alfresco.model.ContentModel;
import org.alfresco.repo.transaction.TransactionListener;
import org.alfresco.service.cmr.dictionary.AspectDefinition;
import org.alfresco.service.cmr.dictionary.DataTypeDefinition;
import org.alfresco.service.cmr.dictionary.DictionaryService;
import org.alfresco.service.cmr.dictionary.PropertyDefinition;
import org.alfresco.service.cmr.dictionary.TypeDefinition;
import org.alfresco.service.cmr.repository.AssociationRef;
import org.alfresco.service.cmr.repository.ChildAssociationRef;
import org.alfresco.service.cmr.repository.NodeRef;
import org.alfresco.service.cmr.repository.NodeService;
import org.alfresco.service.namespace.QName;
import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;

import com.bluexml.alfresco.modules.sql.synchronisation.dictionary.DatabaseDictionary;

public class NodeServiceImpl implements com.bluexml.alfresco.modules.sql.synchronisation.NodeService {

	private Logger logger = Logger.getLogger(getClass());
	
	public void create(NodeRef nodeRef) {
		QName nodeType = nodeService.getType(nodeRef);
		String type_name = nodeType.getLocalName();

		List<QName> parentNames = getParentQNames(nodeRef);
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

			Collection<QName> iterableProperties = nodeService.getProperties(nodeRef).keySet();
			iterableProperties.retainAll(currentTypeProperties.keySet());

			for (QName key : iterableProperties) {
				if (nodeFilterer.accept(key)) {
					Serializable property = nodeService.getProperty(nodeRef, key);
					PropertyDefinition propertyDefinition = dictionaryService.getProperty(key);
					String value = getSQLFormatFromSerializable(property, propertyDefinition);
					
					String originalName = key.getLocalName();
					String resolvedColumnName = databaseDictionary.resolveAttributeAsColumnName(originalName, type_name);
					
					properties.put((resolvedColumnName != null ? resolvedColumnName : originalName), value);
				}
			}

			String ids = StringUtils.join(properties.keySet().iterator(), " , ");
			String values = StringUtils.join(properties.values().iterator(), " , ");

			if (!properties.isEmpty()) {
				String sql_query = String.format("INSERT INTO %1$s ( %2$s ) VALUES ( %3$s )", simplified_type_name, ids, values);
				executeSQLQuery(sql_query);
			} else {
				logger.error("You must accept at least the node id in the definition of the node filterer");
			}
			
		}
	}

	public void delete(NodeRef nodeRef) {
		List<QName> parentNames = getParentQNames(nodeRef);
		for (QName type_qname : parentNames) {
			String type_name = type_qname.getLocalName();

			String simplified_type_name = databaseDictionary.resolveClassAsTableName(type_name);
			Serializable dbid = nodeService.getProperty(nodeRef, ContentModel.PROP_NODE_DBID);

			String sql_query = String.format("DELETE FROM %1$s WHERE id = %2$s", simplified_type_name, dbid);
			executeSQLQuery(sql_query);
		}
	}
	
	public void update(NodeRef nodeRef, Map<QName, Serializable> changes) {
		String type_name = nodeService.getType(nodeRef).getLocalName();

		List<QName> parentNames = getParentQNames(nodeRef);

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
			Collection<QName> iterableProperties = changes.keySet();
			iterableProperties.retainAll(currentTypeProperties.keySet());

			for (QName key : iterableProperties) {
				if (changes.get(key) != null) {
					Serializable property = nodeService.getProperty(nodeRef, key);
					PropertyDefinition propertyDefinition = dictionaryService.getProperty(key);
					String value = getSQLFormatFromSerializable(property, propertyDefinition);
					
					String originalName = key.getLocalName();
					String resolvedColumnName = databaseDictionary.resolveAttributeAsColumnName(originalName, type_name);
					
					String sql_query = String.format("UPDATE %1$s SET %2$s = %3$s WHERE id = %4$s", 
							simplified_type_name, (resolvedColumnName != null ? resolvedColumnName : originalName), value, dbid);
					// Update string only if they are non empty...
					executeSQLQuery(sql_query);
				}
			}
		}

	}


	public void addAssociation(AssociationRef associationRef) {
		
		QName associationType = associationRef.getTypeQName();
		String associationName = associationType.getLocalName();
		
		Serializable sourceId = nodeService.getProperty(associationRef.getSourceRef(), ContentModel.PROP_NODE_DBID);
		Serializable targetId = nodeService.getProperty(associationRef.getTargetRef(), ContentModel.PROP_NODE_DBID);

		addAssociation(associationName , sourceId, targetId);
	}

	public void removeAssociation(AssociationRef associationRef) {
		
		QName associationType = associationRef.getTypeQName();
		String associationName = associationType.getLocalName();

		Serializable sourceId = nodeService.getProperty(associationRef.getSourceRef(), ContentModel.PROP_NODE_DBID);
		Serializable targetId = nodeService.getProperty(associationRef.getTargetRef(), ContentModel.PROP_NODE_DBID);

		
		removeAssociation(associationName, sourceId, targetId);
	}

	public void addChildAssociation(ChildAssociationRef associationRef) {
		QName associationType = associationRef.getTypeQName();
		String associationName = associationType.getLocalName();
		
		Serializable sourceId = nodeService.getProperty(associationRef.getParentRef(), ContentModel.PROP_NODE_DBID);
		Serializable targetId = nodeService.getProperty(associationRef.getChildRef(), ContentModel.PROP_NODE_DBID);
		
		addAssociation(associationName, sourceId, targetId);
	}

	public void removeChildAssociation(ChildAssociationRef associationRef) {
		QName associationType = associationRef.getTypeQName();
		String associationName = associationType.getLocalName();
		
		Serializable sourceId = nodeService.getProperty(associationRef.getParentRef(), ContentModel.PROP_NODE_DBID);
		Serializable targetId = nodeService.getProperty(associationRef.getChildRef(), ContentModel.PROP_NODE_DBID);
		
		removeAssociation(associationName, sourceId, targetId);
	}

	private void addAssociation(String associationName, Serializable sourceId, Serializable targetId) {
		// Retrieve a simplified association name
		String databaseAssociationName = databaseDictionary.resolveAssociationAsTableName(associationName);
		String sourceClassName = databaseDictionary.getSourceAlias(associationName);
		String targetClassName = databaseDictionary.getTargetAlias(associationName);

		String sql_query = String.format("INSERT %1$s ( %2$s , %3$s ) VALUES ( %4$s , %5$s )", databaseAssociationName, sourceClassName, targetClassName, sourceId, targetId);
		executeSQLQuery(sql_query);
	}

	private void removeAssociation(String associationName, Serializable sourceId, Serializable targetId) {

		String databaseAssociationName = databaseDictionary.resolveAssociationAsTableName(associationName);
		String sourceClassName = databaseDictionary.getSourceAlias(associationName);
		String targetClassName = databaseDictionary.getTargetAlias(associationName);
		
		String sql_query = String.format("DELETE FROM %1$s WHERE %2$s = %3$s AND %4$s = %5$s", databaseAssociationName, sourceClassName, sourceId, targetClassName, targetId);
		executeSQLQuery(sql_query);		
	}

	
	/*
	 * Helper methods
	 */
	
	private void executeSQLQuery(String query) {
		transactionListener.executeSQLQuery(query);
	}
	
	
	/**
	 * Return the parent QNames of the nodeRef including self
	 * 
	 * @param nodeRef
	 * @return a list of parent names
	 */
	private List<QName> getParentQNames(NodeRef nodeRef) {
		List<QName> result = new ArrayList<QName>();

		QName currentType = nodeService.getType(nodeRef);

		while (nodeFilterer.accept(currentType)) {
			result.add(currentType);
			TypeDefinition nodeRefTypeDefinition = dictionaryService.getType(currentType);
			if (nodeRefTypeDefinition == null ) {
				logger.error("Cannot find the type definition of type " + currentType);
				break;
			}
			QName parentType = nodeRefTypeDefinition.getParentName();
			currentType = parentType;
		}
		return result;
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
				value = "'" + value.replaceAll("\'", "\\'") + "'";
			} else if ("".equals(value)) {
				/*
				 * not a string quoted value and does not have a value implies a NULL value
				 */
				value = "NULL"; 
			}
		}
		
//		else if (property instanceof Collection) {
//			value = getSQLFormatFromCollection((Collection) property);
//			is_string = true;
//		}

		return value;
	}

	
	/*
	 * Obsolete : Collection management
	 */
//	private static List<String> __escape(Collection<String> values) {
//		ArrayList<String> result = new ArrayList<String>();
//		for (String value : values) {
//			result.add(__escape(value));
//		}
//		return result;
//	}
//
//	private String getSQLFormatFromCollection(Collection properties) {
//		StringBuffer sb = new StringBuffer();
//		boolean first = true;
//		for (Object property : properties) {
//			if (!first) {
//				sb.append(" ");
//			}
//			sb.append(__escape(property.toString()));
//			first = false;
//		}
//		return sb.toString();
//	}

	
	//
	// IoC/DI Spring
	//

	// Dependencies
	private NodeService nodeService;
	private DictionaryService dictionaryService;
	private DatabaseDictionary databaseDictionary;
	private JdbcTransactionListener transactionListener;
	private NodeFilterer nodeFilterer;

	public void setNodeService(NodeService nodeService_) {
		nodeService = nodeService_;
	}

	public void setDictionaryService(DictionaryService dictionaryService_) {
		dictionaryService = dictionaryService_;
	}

	public void setDatabaseDictionary(DatabaseDictionary dbd_) {
		databaseDictionary = dbd_;
	}

	public void setNodeFilterer(NodeFilterer nodeFilterer_) {
		nodeFilterer = nodeFilterer_;
	}
	
	public void setTransactionListener(TransactionListener transactionListener_) {
		if (! (transactionListener_ instanceof JdbcTransactionListener) ) {
			logger.error("NodeServiceImpl needs a JdbcTransactionListener since implementation is relative to sql synchronisation");
		}
		transactionListener = (JdbcTransactionListener) transactionListener_;
	}


}
