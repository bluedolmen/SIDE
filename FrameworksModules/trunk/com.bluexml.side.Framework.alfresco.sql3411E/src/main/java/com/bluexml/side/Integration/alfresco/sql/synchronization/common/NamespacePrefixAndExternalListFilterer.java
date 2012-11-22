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
 * 		it gives the list of classes which are also authorized but which are outside the namespace prefix given by the previous parameter 
 *  	Ex: synchrodb.externalTypesMapping=cm:person,cm:authorityContainer,cm:authority to map the alfresco person, authorithyContainer,authorithy types
 * - the parameter "synchrodb.externalAttributesMapping" of the synchronisation.properties file:
 * 		it gives the list of attributes which are also authorized but which are outside the namespace prefix given by the first parameter 
 *  	Ex: synchrodb.externalAttributesMapping=cm:title to map the cm:title attribute of the aspect cm:titled
 *      if, in the synchronization-database-mapping.properties file, it exists an entry of the form: 
 *        class.attribute.name.<class>.title=<value>
 *    this entry must be mapped in the database as a column 'title' of the <class> table and the values of the attribute 'cm:title'  of the aspect 'cm:titled' aspect
 *    BEWARE: that if an attribute of the class has the same name that an attribute of an aspect you mapped, only one column is created and all the values of the class attribute and the aspect attribute are mapped in this column.
 * - the parameter "synchrodb.excludeTypes" of the synchronisation.properties file:
 * 		it gives the list of the types under the namespace (synchrodb.namespacePrefix) which must be excluded of the synchronisation
 * - the parameter "synchrodb.excludeAttribues" of the synchronisation.properties file:
 * 		it gives the list of the attributes of the types under the namespace (synchrodb.namespacePrefix) which must be excluded of the synchronisation
 *
 */
public class NamespacePrefixAndExternalListFilterer extends AbstractFilterer {

	private Logger logger = Logger.getLogger(getClass());

	public boolean acceptQName(QName qname) {
		return (qname.getNamespaceURI().startsWith(namespacePrefix) && !getExcludedTypesMappingArray().contains(qname)) || getExternalTypesMappingArray().containsKey(qname);
	}

	public boolean acceptPropertyQName(QName qname) {
		return (super.acceptPropertyQName(qname) && !getExcludedAttributesMappingArray().contains(qname)) || ContentModel.PROP_NODE_DBID.equals(qname) || ContentModel.PROP_NODE_UUID.equals(qname) || inAttributesOfExternalTypeMapping(qname);
	}
	
	public boolean acceptPropertyQName(String className, QName propertyQname) {
		return acceptPropertyQName(propertyQname) || inExternalAttributesMapping(className,propertyQname);
	}
	
	//
	// IoC/DI Spring
	//
	
	// Dependencies
	private String namespacePrefix;

	public void setNamespacePrefix(String namespacePrefix_) {
		namespacePrefix = namespacePrefix_;
	}

	
	// the parameter synchrodb.excludedTypesMapping of the synchronisation.properties file which list the namespace types to exclude of the synchronisation
	private String excludedTypesMapping;
	public void setExcludedTypesMapping(String excludedTypesMapping_) {
		excludedTypesMapping = excludedTypesMapping_;
	}
	// contains the types under the namespace (synchrodb.namespacePrefix) which must be excluded of the synchronisation
	private ArrayList<QName> excludedTypesMappingArray = null;
	/*
	 * Get the list of the types under the namespace (synchrodb.namespacePrefix) which must be excluded of the synchronisation
	 * 
	 * @see com.bluexml.side.Integration.alfresco.sql.synchronization.common.AbstractFilterer#getExternalTypesMappingArray()
	 */
	public ArrayList<QName> getExcludedTypesMappingArray() {
 		if (excludedTypesMappingArray == null) {
 			excludedTypesMappingArray = new ArrayList<QName>();
 			getExcludedList(excludedTypesMapping, excludedTypesMappingArray);
 		}
		return excludedTypesMappingArray;
	}

	// the parameter synchrodb.excludedAttributesMapping of the synchronisation.properties file which list the attributes of the namespace type to exclude of the synchronisation
	private String excludedAttributesMapping;
	public void setExcludedAttributesMapping(String excludedAttributesMapping_) {
		excludedAttributesMapping = excludedAttributesMapping_;
	}
	// contains the attributes under the namespace (synchrodb.namespacePrefix) which must be excluded of the synchronisation
	private ArrayList<QName> excludedAttributesMappingArray = null;
	/*
	 * Get the list of the attributes of the types under the namespace (synchrodb.namespacePrefix) which must be excluded of the synchronisation
	 * 
	 * @see com.bluexml.side.Integration.alfresco.sql.synchronization.common.AbstractFilterer#getExternalTypesMappingArray()
	 */
	public ArrayList<QName> getExcludedAttributesMappingArray() {
 		if (excludedAttributesMappingArray == null) {
 			excludedAttributesMappingArray = new ArrayList<QName>();
 			getExcludedList(excludedAttributesMapping, excludedAttributesMappingArray);
 		}
 		return excludedAttributesMappingArray;
	}

	
	
	// the parameter synchrodb.externalTypesMapping of the synchronisation.properties file which list the external types to include in the synchronisation	
	// this external types must be declared in a synchronization-database-mapping.properties file to give the mapping declaration of corresponding table and column name
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
	 * Get the list of the external types to map in the form of a list:  <prefix1>:<type1>,<prefix2>:<type2>,...
	 * 
	 * @see com.bluexml.side.Integration.alfresco.sql.synchronization.common.AbstractFilterer#getExternalTypesMappingArray()
	 */
	public HashMap<QName, ArrayList<QName>> getExternalTypesMappingArray() {
 		if (externalTypesMappingArray == null) {
			externalTypesMappingArray = new HashMap<QName, ArrayList<QName>>();
			if (logger.isDebugEnabled())
				logger.debug("externalTypesMapping "+externalTypesMapping);
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
						logger.error("Mapping of external types failed for "+typePart+" in synchronization.properties -> synchrodb.externalTypesMapping");					
					}
				}
			}
		}
		return externalTypesMappingArray;
	}

	/*
	 * Check if this attribute qname is in the mapped attributes of the external types 
	 */
	public boolean inAttributesOfExternalTypeMapping(QName qname) {
		boolean found = false;
		//if (logger.isDebugEnabled())
			//logger.debug("   inAttributesOfExternalTypeMapping qname "+qname);
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

	// the parameter synchrodb.externalAttributesMapping of the synchronisation.properties file which list the attributes of the external types to include in the synchronisation	
	// this external attributes must be declared in the namespace synchronization-database-mapping.properties file for each associated types to give the mapping declaration of corresponding column name
	private String externalAttributesMapping;
	public void setExternalAttributesMapping(String externalAttributesMapping_) {
		externalAttributesMapping = externalAttributesMapping_;
	}
	// contains the attributes list external to the namespaces prefix which must be equally mapped  in database as they are associated to some internal types either through applied aspect or by inheritance
	// this attributes are given by the parameter synchrodb.externalAttributesMapping of the synchronization.properties file
	// Ex : synchrodb.externalAttributesMapping=cm:title
	// 'cm:title' indicates that the attribute of the aspect 'cm:titled' must be taken into account into the SQL database through:
	//  if, in the synchronization-database-mapping.properties file, it exists an entry of the form: 
	//        class.attribute.name.<class>.title=<value>
	//    this entry must be mapped in the database as a column 'title' of the <class> table and the values of the attribute 'cm:title'  of the aspect 'cm:titled' aspect
	// BEWARE: that if an attribute of the class has the same name that an attribute of an aspect you mapped, only one column is created and all the values of the class attribute and the aspect attribute are mapped in this column.
	private HashMap<String, ArrayList<QName>> externalAttributesMappingArray;
	
	/*
	 * Get the list of the external aspects to map in the form of a list:  <external prefix1>:<external aspect1>|<internal prefix>:<interbal type>,,...
	 * 
	 * @see com.bluexml.side.Integration.alfresco.sql.synchronization.common.AbstractFilterer#getExternalTypesMappingArray()
	 */
	public HashMap<String, ArrayList<QName>> getExternalAttributesMappingArray() {
 		if (externalAttributesMappingArray == null) {
 			externalAttributesMappingArray = new HashMap<String, ArrayList<QName>>();
			if (logger.isDebugEnabled())
				logger.debug("externalAttributesMapping "+externalAttributesMapping);
			if (externalAttributesMapping != null) {
				String[] attributeExpressions = externalAttributesMapping.split( "," );
				if (logger.isDebugEnabled())
					logger.debug("attributeExpressions.length "+attributeExpressions.length);
				for ( int i = 0; i < attributeExpressions.length; i++ ) {
					String[] attributePart = attributeExpressions[i].split(":");
					if (logger.isDebugEnabled())
						logger.debug("attributePart.length "+attributePart.length);
					if (attributePart.length > 1) {
						String namespaceUri = namespaceService.getNamespaceURI(attributePart[0]);
						QName attributeQName = QName.createQName(namespaceUri, attributePart[1]);
						if (logger.isDebugEnabled())
							logger.debug("process attribute: "+namespaceUri+":"+attributePart[1]);
						List<String> classes = databaseDictionary.getClassesOfAttribute(attributePart[1]);
						Iterator<String> iter = classes.iterator();
						while (iter.hasNext()) {
							String className = iter.next();
							if (logger.isDebugEnabled())
								logger.debug("   add class "+className+" and attribute "+attributeQName);
							if (externalAttributesMappingArray.containsKey(className)) {
								ArrayList<QName> al = externalAttributesMappingArray.get(className);
								al.add(attributeQName);								
							} else {
								ArrayList<QName> al = new ArrayList<QName>();								
								al.add(attributeQName);								
								externalAttributesMappingArray.put(className, al);
							}
						}
					} else {
						logger.error("Mapping of external attribute failed for "+attributePart+" in synchronization.properties -> synchrodb.externalTypesMapping");					
					}
				}
			}
		}
		return externalAttributesMappingArray;
	}

	/*
	 * Check if this class qname and this attribute qname are in the external attribute mapped list 
	 */
	public boolean inExternalAttributesMapping(String className, QName propertyQname) {
		boolean found = false;
		//if (logger.isDebugEnabled())
			//logger.debug("   inExternalAttributesMapping className "+className+" propertyQname "+propertyQname);
		Iterator<String> iter = getExternalAttributesMappingArray().keySet().iterator();
		while (iter.hasNext()) {
			String type = iter.next();
			if (type.equals(className)) {
				ArrayList<QName> al = getExternalAttributesMappingArray().get(type);
				Iterator it = al.iterator();
				while (it.hasNext()) {
					QName attributeqn = (QName) it.next();
					if (attributeqn.equals(propertyQname)) {
						found = true;
						if (logger.isDebugEnabled()) logger.debug(" inExternalAttributesMapping  found!!! attributeqn="+attributeqn);
						break;	
					}
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
	
	/**
	 * feed up an array list from a coma-separated string
	 * @param excludedList the coma separated string
	 * @param excludedListArray the array list
	 */
	public void getExcludedList(String excludedList, ArrayList<QName> excludedListArray) {
		if (logger.isDebugEnabled())
			logger.debug("excludedList "+excludedList);
		if (excludedList != null) {
			String[] expressions = excludedList.split( "," );
			for ( int i = 0; i < expressions.length; i++ ) {
				String[] part = expressions[i].split(":");
				if (part.length > 1) {
					String namespaceUri = namespaceService.getNamespaceURI(part[0]);
					QName qName = QName.createQName(namespaceUri, part[1]);
					if (logger.isDebugEnabled())
						logger.debug("process Type "+namespaceUri+":"+part[1]);
					excludedListArray.add(qName);
				} else {
					logger.error("Mapping of exluded types or attributes failed for "+part+" in excluded list :" + excludedList);					
				}
			}
		}
	}


}
