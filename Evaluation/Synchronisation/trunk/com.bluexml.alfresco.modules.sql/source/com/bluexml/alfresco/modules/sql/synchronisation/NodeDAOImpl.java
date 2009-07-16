package com.bluexml.alfresco.modules.sql.synchronisation;

import java.io.Serializable;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.sql.DataSource;

import org.alfresco.repo.policy.PolicyComponent;
import org.alfresco.service.cmr.dictionary.AspectDefinition;
import org.alfresco.service.cmr.dictionary.DictionaryService;
import org.alfresco.service.cmr.dictionary.PropertyDefinition;
import org.alfresco.service.cmr.dictionary.TypeDefinition;
import org.alfresco.service.cmr.repository.AssociationRef;
import org.alfresco.service.cmr.repository.ChildAssociationRef;
import org.alfresco.service.cmr.repository.NodeRef;
import org.alfresco.service.cmr.repository.NodeService;
import org.alfresco.service.namespace.NamespaceService;
import org.alfresco.service.namespace.QName;
import org.apache.commons.lang.StringUtils;

import com.bluexml.alfresco.modules.sql.synchronisation.dictionary.DatabaseDictionary;

public class NodeDAOImpl implements NodeDAO {

	private static final String BLUEXML_MODEL_URI = "http://www.bluexml.com/model/content/";

	
	public void create(NodeRef nodeRef) {
		QName nodeType = nodeService.getType(nodeRef);
		String type_name = nodeType.getLocalName();

		List<QName> parentNames = getParentQNames(nodeRef);
		for (QName type_qname : parentNames) {
			type_name = type_qname.getLocalName();

			String simplified_type_name = databaseDictionary.resolveClassAsTableName(type_name);
			String uuid = nodeRef.getId();
			Serializable dbid = nodeService.getProperty(nodeRef, QName.createQName(NamespaceService.SYSTEM_MODEL_1_0_URI,"node-dbid"));

			Map<String, String> properties = new HashMap<String, String>();
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
				if (key.getNamespaceURI().startsWith(BLUEXML_MODEL_URI)) {
					Serializable property = nodeService.getProperty(nodeRef, key);
					String value = getSQLFormatFromSerializable(property);
					properties.put(databaseDictionary.resolveAttributeAsColumnName(key.getLocalName(), type_name), value);
				}
			}

			List<String> valueList = __escape(properties.keySet());
			String ids = StringUtils.join(valueList.iterator(), "`,`");
			String values = StringUtils.join(properties.values().iterator(), ",");

			String sql_query = "";
			if (!properties.isEmpty()) {
				sql_query = "INSERT INTO `" + simplified_type_name
						+ "`(id,uuid,`" + ids + "`) VALUES(" + dbid + ",\""
						+ uuid + "\"," + values + ")";
			} else {
				sql_query = "INSERT INTO `" + simplified_type_name
						+ "`(id,uuid) VALUES(" + dbid + ",\"" + uuid
						+ "\")";
			}
			executeSQLQuery(sql_query);
		}
	}

	public void delete(NodeRef nodeRef) {
		List<QName> parentNames = getParentQNames(nodeRef);
		for (QName type_qname : parentNames) {
			String type_name = type_qname.getLocalName();

			String simplified_type_name = databaseDictionary.resolveClassAsTableName(type_name);
			Serializable dbid = nodeService.getProperty(nodeRef, QName.createQName(NamespaceService.SYSTEM_MODEL_1_0_URI,"node-dbid"));

			String sql_query = "DELETE FROM `" + simplified_type_name + "`" + " WHERE id = " + dbid.toString();
			executeSQLQuery(sql_query);
		}
	}
	
	public void update(NodeRef nodeRef, Map<QName, Serializable> changes) {
		String type_name = nodeService.getType(nodeRef).getLocalName();

		List<QName> parentNames = getParentQNames(nodeRef);

		for (QName type_qname : parentNames) {
			type_name = type_qname.getLocalName();

			String simplified_type_name = databaseDictionary.resolveClassAsTableName(type_name);
			Serializable dbid = nodeService.getProperty(nodeRef, QName.createQName(NamespaceService.SYSTEM_MODEL_1_0_URI, "node-dbid"));

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
					String value = getSQLFormatFromSerializable(property);
					String sql_query = "UPDATE `"
							+ simplified_type_name
							+ "`"
							+ " SET `"
							+ databaseDictionary.resolveAttributeAsColumnName(
									key.getLocalName(), type_name)
							+ "`=" + value + " WHERE id = "
							+ dbid.toString();
					// Update string only if they are non empty...
					executeSQLQuery(sql_query);
				}
			}
		}

	}


	public void addAssociation(AssociationRef associationRef) {
		
		QName associationType = associationRef.getTypeQName();
		String associationName = associationType.getLocalName();
		
		Serializable sourceId = nodeService.getProperty(associationRef.getSourceRef(), QName.createQName(NamespaceService.SYSTEM_MODEL_1_0_URI, "node-dbid"));
		Serializable targetId = nodeService.getProperty(associationRef.getTargetRef(), QName.createQName(NamespaceService.SYSTEM_MODEL_1_0_URI, "node-dbid"));

		addAssociation(associationName , sourceId, targetId);
	}

	public void removeAssociation(AssociationRef associationRef) {
		
		QName associationType = associationRef.getTypeQName();
		String associationName = associationType.getLocalName();

		Serializable sourceId = nodeService.getProperty(associationRef.getSourceRef(), QName.createQName(NamespaceService.SYSTEM_MODEL_1_0_URI, "node-dbid"));
		Serializable targetId = nodeService.getProperty(associationRef.getTargetRef(), QName.createQName(NamespaceService.SYSTEM_MODEL_1_0_URI, "node-dbid"));

		
		removeAssociation(associationName, sourceId, targetId);
	}

	public void addChildAssociation(ChildAssociationRef associationRef) {
		QName associationType = associationRef.getTypeQName();
		String associationName = associationType.getLocalName();
		
		Serializable sourceId = nodeService.getProperty(associationRef.getParentRef(), QName.createQName(NamespaceService.SYSTEM_MODEL_1_0_URI, "node-dbid"));
		Serializable targetId = nodeService.getProperty(associationRef.getChildRef(), QName.createQName(NamespaceService.SYSTEM_MODEL_1_0_URI, "node-dbid"));
		
		addAssociation(associationName, sourceId, targetId);
	}

	public void removeChildAssociation(ChildAssociationRef associationRef) {
		QName associationType = associationRef.getTypeQName();
		String associationName = associationType.getLocalName();
		
		Serializable sourceId = nodeService.getProperty(associationRef.getParentRef(), QName.createQName(NamespaceService.SYSTEM_MODEL_1_0_URI, "node-dbid"));
		Serializable targetId = nodeService.getProperty(associationRef.getChildRef(), QName.createQName(NamespaceService.SYSTEM_MODEL_1_0_URI, "node-dbid"));
		
		removeAssociation(associationName, sourceId, targetId);
	}

	private void addAssociation(String associationName, Serializable sourceId, Serializable targetId) {
		// Retrieve a simplified association name
		String databaseAssociationName = databaseDictionary.resolveAssociationAsTableName(associationName);
		String sourceClassName = databaseDictionary.getSourceClass(associationName);
		String targetClassName = databaseDictionary.getTargetClass(associationName);

		String sql_query = "INSERT `" + databaseAssociationName + "`"
		+ "(`" + sourceClassName + "`,`" + targetClassName
		+ "`) VALUES (" + sourceId.toString() + ","
		+ targetId.toString() + ")";
		executeSQLQuery(sql_query);
	}

	private void removeAssociation(String associationName, Serializable sourceId, Serializable targetId) {

		String databaseAssociationName = databaseDictionary.resolveAssociationAsTableName(associationName);
		String sourceClassName = databaseDictionary.getSourceClass(associationName);
		String targetClassName = databaseDictionary.getTargetClass(associationName);
		
		String sql_query = "DELETE FROM `" + databaseAssociationName + "`" + " WHERE `" + sourceClassName + "` = "
		+ sourceId.toString() + " AND `" + targetClassName
		+ "` = " + targetId.toString();
		executeSQLQuery(sql_query);		
	}

	/*
	 * Helper methods
	 */
	
	void executeSQLQuery(String query) {
		throw new UnsupportedOperationException();
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

		while (currentType.getNamespaceURI().startsWith(BLUEXML_MODEL_URI)) {
			result.add(currentType);
			TypeDefinition nodeRefTypeDefinition = dictionaryService.getType(currentType);
			QName parentType = nodeRefTypeDefinition.getParentName();
			currentType = parentType;
		}
		return result;
	}
	
	private static String __escape(String value) {
		String result = value.replaceAll("\"", "\"\"");
		return result;
	}

	private static List<String> __escape(Collection<String> values) {
		ArrayList<String> result = new ArrayList<String>();
		for (String value : values) {
			result.add(__escape(value));
		}
		return result;
	}

	private String getSQLFormatFromSerializable(Serializable property) {
		boolean is_string = property instanceof String;
		boolean is_date = property instanceof Date;
		boolean is_collection = property instanceof Collection;
		String value = "";
		if (property == null) {
			value = "NULL";
		} else if (is_date) {
			DateFormat dateformat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			value = dateformat.format((Date) property);
		} else if (is_collection) {
			value = getSQLFormatFromCollection((Collection) property);
			is_string = true;
		} else {
			value = __escape(property.toString());
		}

		// value = StringEscapeUtils.escapeSql(value);
		value = (is_string || is_date ? "\"" : "") + value
				+ (is_string || is_date ? "\"" : "");

		return value;
	}

	private String getSQLFormatFromCollection(Collection properties) {
		StringBuffer sb = new StringBuffer();
		boolean first = true;
		for (Object property : properties) {
			if (!first) {
				sb.append(" ");
			}
			sb.append(__escape(property.toString()));
			first = false;
		}
		return sb.toString();
	}

	
	//
	// IoC/DI Spring
	//

	// Dependencies
	private NodeService nodeService;
	private DictionaryService dictionaryService;
	private DatabaseDictionary databaseDictionary;
	private DataSource dataSource;

	public void setNodeService(NodeService nodeService_) {
		nodeService = nodeService_;
	}

	public void setDictionaryService(DictionaryService dictionaryService_) {
		dictionaryService = dictionaryService_;
	}

	public void setDatabaseDictionary(DatabaseDictionary dbd_) {
		databaseDictionary = dbd_;
	}

	public void setDataSource(DataSource dataSource_) {
		dataSource = dataSource_;
	}




}
