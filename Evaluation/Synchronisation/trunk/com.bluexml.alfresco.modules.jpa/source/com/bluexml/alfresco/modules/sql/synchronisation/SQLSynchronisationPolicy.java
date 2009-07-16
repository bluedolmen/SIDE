package com.bluexml.alfresco.modules.sql.synchronisation;

import java.io.Serializable;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.alfresco.repo.node.NodeServicePolicies.BeforeDeleteNodePolicy;
import org.alfresco.repo.node.NodeServicePolicies.OnCreateAssociationPolicy;
import org.alfresco.repo.node.NodeServicePolicies.OnCreateChildAssociationPolicy;
import org.alfresco.repo.node.NodeServicePolicies.OnCreateNodePolicy;
import org.alfresco.repo.node.NodeServicePolicies.OnDeleteAssociationPolicy;
import org.alfresco.repo.node.NodeServicePolicies.OnDeleteChildAssociationPolicy;
import org.alfresco.repo.node.NodeServicePolicies.OnDeleteNodePolicy;
import org.alfresco.repo.node.NodeServicePolicies.OnUpdatePropertiesPolicy;
import org.alfresco.repo.policy.Behaviour;
import org.alfresco.repo.policy.JavaBehaviour;
import org.alfresco.repo.policy.PolicyComponent;
import org.alfresco.repo.policy.Behaviour.NotificationFrequency;
import org.alfresco.service.ServiceRegistry;
import org.alfresco.service.cmr.dictionary.DictionaryService;
import org.alfresco.service.cmr.dictionary.TypeDefinition;
import org.alfresco.service.cmr.repository.AssociationRef;
import org.alfresco.service.cmr.repository.ChildAssociationRef;
import org.alfresco.service.cmr.repository.NodeRef;
import org.alfresco.service.cmr.repository.NodeService;
import org.alfresco.service.namespace.NamespaceService;
import org.alfresco.service.namespace.QName;
import org.apache.log4j.Logger;

import com.bluexml.alfresco.modules.sql.dao.BasicDAO;
import com.bluexml.alfresco.modules.sql.service.BasicController;
import com.bluexml.alfresco.modules.sql.utils.JPAAssociationDictionary;

public class SQLSynchronisationPolicy implements OnCreateNodePolicy, OnUpdatePropertiesPolicy, OnCreateAssociationPolicy, OnDeleteAssociationPolicy, OnCreateChildAssociationPolicy, OnDeleteChildAssociationPolicy, BeforeDeleteNodePolicy {


	// Behaviours
	private Behaviour onCreateNode;
	private Behaviour onUpdateProperties;
	private Behaviour onCreateAssociation;
	private Behaviour onCreateChildAssociation;
	
	private Behaviour beforeDeleteNode;
	private Behaviour onDeleteChildAssociation;
	private Behaviour onDeleteAssociation;

	private static Logger logger = Logger.getLogger(SQLSynchronisationPolicy.class);

	// // COPY of UICustomAssocationEditor
	private static final String BLUEXML_MODEL_URI = "http://www.bluexml.com/model/content/";
	private static final QName NODE_DBID_QNAME = QName.createQName(NamespaceService.SYSTEM_MODEL_1_0_URI, "node-dbid");
	private static final QName NODE_NAME_QNAME = QName.createQName(NamespaceService.CONTENT_MODEL_1_0_URI, "name");

	public void init() {

		logger.debug("[init] Initializing JPA synchronisation");
		
		this.onCreateNode = new JavaBehaviour(this, "onCreateNode", NotificationFrequency.TRANSACTION_COMMIT);
		this.onUpdateProperties = new JavaBehaviour(this, "onUpdateProperties", NotificationFrequency.TRANSACTION_COMMIT);
		this.onCreateAssociation = new JavaBehaviour(this, "onCreateAssociation", NotificationFrequency.TRANSACTION_COMMIT);
		this.onCreateChildAssociation = new JavaBehaviour(this, "onCreateChildAssociation", NotificationFrequency.TRANSACTION_COMMIT);
		
		this.beforeDeleteNode = new JavaBehaviour(this, "beforeDeleteNode", NotificationFrequency.FIRST_EVENT); 
		this.onDeleteAssociation = new JavaBehaviour(this, "onDeleteAssociation", NotificationFrequency.TRANSACTION_COMMIT); 
		this.onDeleteChildAssociation = new JavaBehaviour(this, "onDeleteChildAssociation", NotificationFrequency.EVERY_EVENT);

		// Bind behaviours to node policies
		policyComponent.bindClassBehaviour(QName.createQName(NamespaceService.ALFRESCO_URI, "onCreateNode"), this,this.onCreateNode);
		policyComponent.bindClassBehaviour(QName.createQName(NamespaceService.ALFRESCO_URI, "onUpdateProperties"), this,this.onUpdateProperties);
		policyComponent.bindClassBehaviour(QName.createQName(NamespaceService.ALFRESCO_URI, "beforeDeleteNode"), this,this.beforeDeleteNode);
		policyComponent.bindAssociationBehaviour(QName.createQName(NamespaceService.ALFRESCO_URI, "onCreateAssociation"), this,this.onCreateAssociation);
		policyComponent.bindAssociationBehaviour(QName.createQName(NamespaceService.ALFRESCO_URI, "onCreateChildAssociation"), this,this.onCreateChildAssociation);
		policyComponent.bindAssociationBehaviour(QName.createQName(NamespaceService.ALFRESCO_URI, "onDeleteChildAssociation"), this,this.onDeleteChildAssociation);
		policyComponent.bindAssociationBehaviour(QName.createQName(NamespaceService.ALFRESCO_URI, "onDeleteAssociation"), this,this.onDeleteAssociation);
	}


	
	public void onCreateNode(ChildAssociationRef childAssociationRef) {

		NodeRef nodeRef = childAssociationRef.getChildRef();
		logger.debug("-== onCreateNode ==-");
		
		// Only process bluexml nodes
		if (hasToBeProcessed(nodeRef)) {
			
			Object instance = getInstance(nodeRef);
			
			basicService.persist(instance);
			
		}
	}

	public void onUpdateProperties(NodeRef nodeRef, Map<QName, Serializable> before_properties, Map<QName, Serializable> after_properties) {
		logger.debug("-== onUpdateProperties ==-");
		
		if (hasToBeProcessed(nodeRef)) {
			
			logger.debug("---------------------");
			logger.debug(": Before properties :");
			logger.debug("---------------------");
			outputProperties(before_properties);
			logger.debug("--------------------");
			logger.debug(": After properties :");
			logger.debug("--------------------");
			outputProperties(after_properties);
						
			//Object instance = basicService.getById(clazz, dbid);
			Object instance = getInstance(nodeRef, after_properties);

			basicService.merge(instance);
		}

	}
	
	public void beforeDeleteNode(NodeRef nodeRef) {

		logger.debug("-== beforeDeleteNode ==-");
		
		// Only process bluexml nodes
		if (hasToBeProcessed(nodeRef)) {

			Class clazz = getClass(nodeRef);
			Long sourceId = Long.decode(nodeService.getProperty(nodeRef, NODE_DBID_QNAME).toString());
			
			basicService.remove(clazz, sourceId);
			
		}
	}

	@SuppressWarnings("unchecked")
	public void onCreateAssociation(AssociationRef associationRef) {
		logger.debug("-== onCreateAssociation ==-");
		
		QName associationType = associationRef.getTypeQName();
		if (associationType.getNamespaceURI().startsWith(BLUEXML_MODEL_URI)) {

			Class sourceClass = getClass(associationRef.getSourceRef());
			Class targetClass = getClass(associationRef.getTargetRef());
			Long sourceId = Long.decode(nodeService.getProperty(associationRef.getSourceRef(), NODE_DBID_QNAME).toString());
			Long targetId = Long.decode(nodeService.getProperty(associationRef.getTargetRef(), NODE_DBID_QNAME).toString());

			String associationName = associationRef.getTypeQName().getLocalName();
			String targetName = jpaAssociationDictionary.translate(associationName).toString(); //nodeService.getProperty(associationRef.getTargetRef(), NODE_NAME_QNAME);
			if (targetName == null) {
				logger.error("Problem getting the association translation. Cannot find any translation for entry \"" + associationName + "\".");
			}
			
			basicService.addTarget(sourceClass, sourceId, targetClass, targetId, targetName);
		}

	}

	public void onDeleteAssociation(AssociationRef associationRef) {
		logger.debug("-== onDeleteAssociation ==-");
		
		QName associationType = associationRef.getTypeQName();
		if (associationType.getNamespaceURI().startsWith(BLUEXML_MODEL_URI)) {

			Class sourceClass = getClass(associationRef.getSourceRef());
			Class targetClass = getClass(associationRef.getTargetRef());
			Long sourceId = Long.decode(nodeService.getProperty(associationRef.getSourceRef(), NODE_DBID_QNAME).toString());
			Long targetId = Long.decode(nodeService.getProperty(associationRef.getTargetRef(), NODE_DBID_QNAME).toString());

			String associationName = associationRef.getTypeQName().getLocalName();
			String targetName = jpaAssociationDictionary.translate(associationName).toString(); //nodeService.getProperty(associationRef.getTargetRef(), NODE_NAME_QNAME);
			if (targetName == null) {
				logger.error("Problem getting the association translation. Cannot find any translation for entry \"" + associationName + "\".");
			}
			
			basicService.removeTarget(sourceClass, sourceId, targetClass, targetId, targetName);
		}
	}


	public void onCreateChildAssociation(ChildAssociationRef associationref, boolean isNewNode) {
		QName association_type = associationref.getTypeQName();
		if (association_type.getNamespaceURI().startsWith(BLUEXML_MODEL_URI)) {
			logger.debug("Not Implemented");			
		}
	}

	public void onDeleteChildAssociation(ChildAssociationRef associationref) {
		QName association_type = associationref.getTypeQName();
		if (association_type.getNamespaceURI().startsWith(BLUEXML_MODEL_URI)) {
			logger.debug("Not Implemented");
		}

	}

	
	/*******************************************************
	 * Helper functions
	 *******************************************************/

	private void setInstanceProperties(Object instance, Map<QName, Serializable> properties, String uuid, Long dbid) {
		try {
			Class<? extends Object> clazz = instance.getClass();
			
			// Set uuid
			Class[] attributes = new Class[1];
			attributes[0] = String.class;
			Method methodUUID = clazz.getMethod("setUuid", attributes);
			methodUUID.invoke(instance, uuid);
			
			// Set dbid
			attributes[0] = Long.class;
			Method methodID = clazz.getMethod("setId", attributes);
			methodID.invoke(instance, dbid);
			
			// Set other attributes
			for (Map.Entry<QName, Serializable> property_value : properties.entrySet()) {
				QName propertyQName = property_value.getKey();
				String propertyLocalName = propertyQName.getLocalName();
				Serializable propertyValue = property_value.getValue();
				
				// Test purpose : only process bluexml properties
				if (propertyQName.getNamespaceURI().startsWith(BLUEXML_MODEL_URI)) {
					String attributeName = propertyLocalName.substring(propertyLocalName.lastIndexOf("_") + 1);
					String setMethodName = "set" + attributeName;
					
					Method[] methods = clazz.getMethods();
					Method searchedMethod = null;
					for (int i = 0; i < methods.length; i++) {
						Method method = methods[i];
						
						if (method.getName().equalsIgnoreCase(setMethodName)) {
							logger.debug("Method \"" + method.getName() + "\" found for property \"" + propertyQName + "\"");
							searchedMethod = method;
							break;
						}
					}
					
					if (searchedMethod == null) {
						logger.error("Cannot find the method \"" + setMethodName + "()\" on class \"" + clazz.getCanonicalName() + "\"");
					} else {
						logger.debug("Found method \"" + setMethodName + "()\", applying on value \"" + propertyValue + "\"");
						searchedMethod.invoke(instance, propertyValue);
					}
				}
			}
			
			
		} catch (IllegalArgumentException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SecurityException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (NoSuchMethodException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	/*
	 * Returns the Java class that represents the given nodeRef
	 * Should be merged with getObjectFromNodeRef ?
	 */
	private Class<? extends Object> getClass(NodeRef nodeRef) {
		QName nodeType = nodeService.getType(nodeRef);
		String typeName = nodeType.getLocalName();
		String className = typeName.replace("_", ".");
		Class clazz = null;

		try {
			clazz = Class.forName(className);
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return clazz;
	}
		
	/*
	 * Returns an object (instance of class) from the nodeRef (instance of alfresco meta-model)
	 */
	private Object getInstance(NodeRef nodeRef) {
		return getInstance(nodeRef, null);
	}
	
	/*
	 * Returns an object (instance of class) from the nodeRef (instance of alfresco meta-model)
	 * nodeRef: the node that should be transformed
	 * properties: the properties that should override those stored in the nodeRef (update mode)
	 */
	private Object getInstance(NodeRef nodeRef, Map<QName, Serializable> overridingProperties) {
		Object instance = null;
		Class<? extends Object> clazz = getClass(nodeRef);
		Map<QName, Serializable> properties = (overridingProperties == null ? nodeService.getProperties(nodeRef) : overridingProperties);
		String uuid = nodeRef.getId();
		Long dbid = Long.decode(nodeService.getProperty(nodeRef, NODE_DBID_QNAME).toString());
		
		try {
			instance = clazz.newInstance();
		} catch (InstantiationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		setInstanceProperties(instance, properties, uuid, dbid);

		return instance;
		
	}

	/*
	 * Returns the stored JPA object that represents the nodeRef
	 */
	private Object getStoredInstance(NodeRef nodeRef) {
		Object instance = null;
		Class<? extends Object> clazz = getClass(nodeRef);
		String uuid = nodeRef.getId();
		Long dbid = Long.decode(nodeService.getProperty(nodeRef, NODE_DBID_QNAME).toString());
		
		instance = basicService.getById(clazz, dbid);
		
		return instance;
	}
	
	private void outputProperties(Map<QName, Serializable> properties) {
		for (Map.Entry<QName, Serializable> entry : properties.entrySet()) {
			logger.debug(entry.getKey() + " : " + entry.getValue());
			if (entry.getValue() != null) {
				logger.debug("Java Type : " + entry.getValue().getClass().toString());
			}
		}
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
			value = getSQLFormatFromCollection((Collection<?>) property);
			is_string = true;
		} else {
			value = __escape(property.toString());
		}

		// value = StringEscapeUtils.escapeSql(value);
		value = (is_string || is_date ? "\"" : "") + value + (is_string || is_date ? "\"" : "");

		return value;
	}

	private String getSQLFormatFromCollection(Collection<?> properties) {
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

	private boolean hasToBeProcessed(NodeRef nodeRef) {
		boolean result = false;
		QName nodeType = nodeService.getType(nodeRef);
		String uri = nodeType.getNamespaceURI();
		if (uri.startsWith(BLUEXML_MODEL_URI)) {
			Serializable store_protocol = nodeService.getProperty(nodeRef, QName.createQName(NamespaceService.SYSTEM_MODEL_1_0_URI, "store-protocol"));
			// Only process nodes which are not in the archive space (the node
			// was deleted)
			if (!store_protocol.equals("archive")) {
				String type_name = nodeService.getType(nodeRef).getLocalName();
				if (!type_name.matches(".*_search$")) {
					result = true;
				}
			}
		}
		return result;
	}


	//
	// ACCESSORS FOR SPRING BEAN MANAGEMENT
	//
	
	// Dependencies
	private ServiceRegistry serviceRegistry;
	private NodeService nodeService;
	private PolicyComponent policyComponent;
	private DictionaryService dictionaryService;
	private BasicController basicService;
	private JPAAssociationDictionary jpaAssociationDictionary;

	public NodeService getNodeService() {
		return nodeService;
	}
	public void setNodeService(NodeService nodeService_) {
		nodeService = nodeService_;
	}

	public PolicyComponent getPolicyComponent() {
		return policyComponent;
	}
	public void setPolicyComponent(PolicyComponent policyComponent_) {
		policyComponent = policyComponent_;
	}

	public DictionaryService getDictionaryComponent() {
		return dictionaryService;
	}
	public void setDictionaryService(DictionaryService dictionaryService_) {
		dictionaryService = dictionaryService_;
	}

	public ServiceRegistry getServiceRegistry() {
		return serviceRegistry;
	}
	public void setServiceRegistry(ServiceRegistry serviceRegistry_) {
		serviceRegistry = serviceRegistry_;
	}

	public void setBasicService(BasicController basicService_) {
		basicService = basicService_;
	}
	
	public void setJpaAssociationDictionary(JPAAssociationDictionary jpaAssociationDictionary_) {
		jpaAssociationDictionary = jpaAssociationDictionary_;
	}



}
