package com.bluexml.side.Integration.alfresco.sql.synchronization.common;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

import org.alfresco.model.ContentModel;
import org.alfresco.service.namespace.NamespaceService;
import org.alfresco.service.namespace.QName;
import org.alfresco.service.transaction.TransactionService;
import org.apache.log4j.Logger;

import com.bluexml.side.Integration.alfresco.sql.synchronization.dictionary.DatabaseDictionary;
/**
 * A filter mode to limit the number of class to synchronize in the SQL Database
 * This filter limits using :
 * - the parameter "synchrodb.namespacePrefix" of the synchronisation.properties file:
 * 		it gives the namepsace prefix of the classes to authorize
 * 		Ex: synchrodb.namespacePrefix=http://www.bluexml.com/model/content/MyModel/ to authorize the types of 'MyModel'
 * - the parameter "synchrodb.externalTypesMapping" of the synchronisation.properties file:
 * 		it gives the list of classes which are alos authorized but which are outside the namespace prefix given by the previous parameter 
 *  	Ex: synchrodb.externalTypesMapping=cm:person,cm:authorityContainer,cm:authority to map the alfresco person, authorithyContainer,authorithy types
 *
 */
public class NamespacePrefixFilterer extends AbstractFilterer {

	private Logger logger = Logger.getLogger(getClass());

	public boolean acceptQName(QName qname) {
		return qname.getNamespaceURI().startsWith(namespacePrefix) || getExternalTypesMappingArray().containsKey(qname);
	}

	public boolean acceptPropertyQName(QName qname) {
		return super.acceptPropertyQName(qname) || ContentModel.PROP_NODE_DBID.equals(qname) || ContentModel.PROP_NODE_UUID.equals(qname) || inExternalAttributesMapping(qname);
	}
	
	//
	// IoC/DI Spring
	//
	
	// Dependencies
	private String namespacePrefix;

	public void setNamespacePrefix(String namespacePrefix_) {
		namespacePrefix = namespacePrefix_;
	}

	private String externalTypesMapping;
	public void setExternalTypesMapping(String externalTypesMapping_) {
		externalTypesMapping = externalTypesMapping_;
	}
	// contains the types external to the namespaces prefix which must be equally mapped  in database as they are target of association
	// this types are given by the parameter synchrodb.externalTypesMapping of the synchronization.properties file
	// Ex : synchrodb.externalTypesMapping=cm:person,cm:authorityContainer
	// BEWARE: a file synchronization-database-mapping.properties must be created which contains the mapping of the following external types and their associated attributes to map
	private HashMap<QName, ArrayList<QName>> externalTypesMappingArray;
	
	/*
	 * Get the list of the external types and of their mapped attributes
	 * 
	 * @see com.bluexml.side.Integration.alfresco.sql.synchronization.common.AbstractFilterer#getExternalTypesMappingArray()
	 */
	public HashMap<QName, ArrayList<QName>> getExternalTypesMappingArray() {
 		if (externalTypesMappingArray == null) {
			externalTypesMappingArray = new HashMap<QName, ArrayList<QName>>();
			if (externalTypesMapping != null) {
				String[] typeExpressions = externalTypesMapping.split( "," );
				for ( int i = 0; i < typeExpressions.length; i++ ) {
					String[] typePart = typeExpressions[i].split(":");
					if (typePart.length > 1) {
						String namespaceUri = namespaceService.getNamespaceURI(typePart[0]);
						QName typeQName = QName.createQName(namespaceUri, typePart[1]);
						if (logger.isDebugEnabled())
							logger.debug("process Type "+namespaceUri+":"+typePart[1]);
						List<String> attributes = databaseDictionary.getAttributesOfClass(typePart[1]);
						Iterator<String> iter = attributes.iterator();
						ArrayList<QName> attributeQNames = new ArrayList<QName>();
						while (iter.hasNext()) {
							String attribute = iter.next();
							if (logger.isDebugEnabled())
								logger.debug("   add Attribute "+attribute);
							QName attributeQName = QName.createQName(namespaceUri, attribute);
							attributeQNames.add(attributeQName);
						}
						externalTypesMappingArray.put(typeQName, attributeQNames);
					} else {
						logger.error("Mapping of external types failed for "+typePart+" in synchrnization.properties -> synchrodb.externalTypesMapping");					
					}
				}
			}
		}
		return externalTypesMappingArray;
	}

	/*
	 * Check if this attribute qname is in the mapped attributes of the external types 
	 */
	public boolean inExternalAttributesMapping(QName qname) {
		boolean found = false;
		if (logger.isDebugEnabled())
			logger.debug("   inExternalAttributesMapping qname "+qname);
		Iterator<QName> iter = getExternalTypesMappingArray().keySet().iterator();
		while (iter.hasNext()) {
			QName qn = iter.next();
			//if (logger.isDebugEnabled())
			//	logger.debug("   qn= "+qn);
			ArrayList<QName> qnames = externalTypesMappingArray.get(qn);
			Iterator it = qnames.iterator();
			while (it.hasNext()) {
				QName mappedqname = (QName) it.next();
				if (mappedqname.equals(qname)) {
					found = true;
					if (logger.isDebugEnabled()) logger.debug("  found!!!");
					break;
				}
			}
		}
		return found;
	}
	
	NamespaceService namespaceService;
	public void setNamespaceService(NamespaceService namespaceService_) {
		namespaceService = namespaceService_;
	}
	
	private DatabaseDictionary databaseDictionary;
	public void setDatabaseDictionary(DatabaseDictionary databaseDictionary_) {
		databaseDictionary = databaseDictionary_;
	}
	
	

}
