/**
 * 
 */
package com.bluexml.side.Integration.alfresco.xforms.webscript;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.StringTokenizer;
import java.util.TreeMap;
import java.util.Map.Entry;

import org.alfresco.model.ContentModel;
import org.alfresco.repo.domain.PropertyValue;
import org.alfresco.service.ServiceRegistry;
import org.alfresco.service.cmr.dictionary.AssociationDefinition;
import org.alfresco.service.cmr.dictionary.ClassDefinition;
import org.alfresco.service.cmr.dictionary.DataTypeDefinition;
import org.alfresco.service.cmr.dictionary.DictionaryException;
import org.alfresco.service.cmr.dictionary.DictionaryService;
import org.alfresco.service.cmr.dictionary.PropertyDefinition;
import org.alfresco.service.cmr.dictionary.TypeDefinition;
import org.alfresco.service.cmr.model.FileExistsException;
import org.alfresco.service.cmr.repository.AssociationExistsException;
import org.alfresco.service.cmr.repository.AssociationRef;
import org.alfresco.service.cmr.repository.ChildAssociationRef;
import org.alfresco.service.cmr.repository.ContentIOException;
import org.alfresco.service.cmr.repository.ContentWriter;
import org.alfresco.service.cmr.repository.NodeRef;
import org.alfresco.service.cmr.repository.NodeService;
import org.alfresco.service.cmr.repository.StoreRef;
import org.alfresco.service.cmr.repository.datatype.TypeConversionException;
import org.alfresco.service.cmr.search.ResultSet;
import org.alfresco.service.cmr.search.SearchService;
import org.alfresco.service.cmr.security.AuthenticationService;
import org.alfresco.service.namespace.QName;
import org.alfresco.service.namespace.RegexQNamePattern;
import org.apache.commons.lang.StringEscapeUtils;
import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.xml.serialize.OutputFormat;
import org.apache.xml.serialize.XMLSerializer;
import org.joda.time.format.DateTimeFormatter;
import org.joda.time.format.ISODateTimeFormat;
import org.w3c.dom.Document;
import org.w3c.dom.Element;

/**
 * Adapted from BxDS dataLayer module.<br/>
 * Amendments are essentially commenting, javadoc-ing and deletion of sections not used by
 * AlfrescoController.
 * 
 * @author Amenel
 * 
 */
public class DataLayer implements DataLayerInterface {

	public static String defaultStorePath = "/app:company_home/app:dictionary/cm:BlueXMLDATA";
	private final static String ALF_CONTENT_URI = "{http://www.alfresco.org/model/content/1.0}";
	private static final String BLUEXML_MODEL_URI = "http://www.bluexml.com/model/content/";
	private static final String VIEW_FIELD_SEPARATOR = ".";
	private static final String VIEW_TOKEN_SEPARATOR = "@";
	private static Log logger = LogFactory.getLog(DataLayer.class);
	protected static DateTimeFormatter dateTimeFormatter = ISODateTimeFormat.dateTime();

	private ServiceRegistry serviceRegistry;
	private AuthenticationService authenticationService;
	private DictionaryService dictionaryService;
	private NodeService nodeService;
	private boolean inTransaction;
	private TreeMap<String, NodeRef> parentFolderCache = null;

	// format enum for generated properties: affects the BlueXML URI namespace only
	public enum FormatPattern {
			// use the uuid
			NONE("UUID"),
			// the first text field is used for the label
			FIRST("FIRST"),
			// all text fields
			ALLTEXT("ALLTEXT"),
			// all fields, including dates and numerical ones.
			ALL("ALL");

		private final String associatedKeyText;

		private FormatPattern(String keyText) {
			this.associatedKeyText = keyText;
		}

		public String get() {
			return associatedKeyText;
		}

		@Override
		public String toString() {
			return get();
		}
	}

	public DataLayer(ServiceRegistry serviceRegistry) {
		super();
		this.serviceRegistry = serviceRegistry;
		this.authenticationService = serviceRegistry.getAuthenticationService();
		this.dictionaryService = serviceRegistry.getDictionaryService();
		this.nodeService = serviceRegistry.getNodeService();
		this.inTransaction = false;
		this.parentFolderCache = null;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.bluexml.side.Integration.alfresco.xforms.webscript.DataLayerInterface#create(java.lang
	 * .String, org.w3c.dom.Element, java.lang.String)
	 */
	@SuppressWarnings("unchecked")
	public NodeRef create(String where, Element what, String nodeName) throws Exception {
		XmlParser parser = new XmlParser();

		// parse the object to get a Map representation of it
		Map<String, Object> entry = null;
		entry = parser.parse(this, what);

		// resolve QualifiedName to QName
		QName nodeTypeQName = getDataTypeQName((String) entry.get("dataType"));

		// build attribute values
		Map<QName, Serializable> properties = buildAttributesMap((Map<String, Object>) entry
				.get("attributes"), nodeTypeQName, false);
		QName assocTypeQName = ContentModel.ASSOC_CONTAINS;
		// create the node
		NodeRef newNode = createNode(where, assocTypeQName, null, nodeTypeQName, nodeName,
				properties);
		// create the associations
		createAssociations(newNode, nodeTypeQName, (List<AssociationBean>) entry
				.get("associations"));
		return newNode;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.bluexml.side.Integration.alfresco.xforms.webscript.DataLayerInterface#update(java.lang
	 * .String, org.w3c.dom.Element)
	 */
	@SuppressWarnings("unchecked")
	public NodeRef update(String nodeId, Element what) throws Exception {
		NodeRef nodeToUpdate = new NodeRef(nodeId);
		XmlParser parser = new XmlParser();
		// get Map representation of the object
		Map<String, Object> entry = null;
		entry = parser.parse(this, what);
		// resolve QualifiedName to QName
		String datatype = (String) entry.get("dataType");
		QName nodeTypeQName = getDataTypeQName(datatype);
		// build the map that contains attribute values
		Map<QName, Serializable> properties = buildAttributesMap((Map<String, Object>) entry
				.get("attributes"), nodeTypeQName, true);
		// update properties
		updateProperties(nodeToUpdate, properties);
		// delete previous associations?
		String associationsAction = (String) entry.get("associationsAction");
		boolean deleteAllAssociations = StringUtils.equals(associationsAction, "replace");
		// update associations
		updateAssociations(nodeToUpdate, nodeTypeQName, (List<AssociationBean>) entry
				.get("associations"), deleteAllAssociations);
		return nodeToUpdate;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.bluexml.side.Integration.alfresco.xforms.webscript.DataLayerInterface#request(java.lang
	 * .String)
	 */
	public List<NodeRef> request(String xpath) throws Exception {
		// Amenel: replaced Repository.getStoreRef with a direct reference to
		// STORE_REF_WORKSPACE_SPACESSTORE
		// NodeRef root = nodeService.getRootNode(Repository.getStoreRef());
		NodeRef root = nodeService.getRootNode(StoreRef.STORE_REF_WORKSPACE_SPACESSTORE);
		ResultSet result = serviceRegistry.getSearchService().query(root.getStoreRef(),
				SearchService.LANGUAGE_XPATH, xpath);
		return result.getNodeRefs();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.bluexml.side.Integration.alfresco.xforms.webscript.DataLayerInterface#deleteNode(java
	 * .lang.String)
	 */
	public void delete(String objectId) {
		NodeRef nodeRef = new NodeRef(objectId);
		List<ChildAssociationRef> assos = this.serviceRegistry.getNodeService().getChildAssocs(
				nodeRef);
		for (ChildAssociationRef asso : assos) {
			QName associationName = asso.getTypeQName();
			if (associationName.getNamespaceURI().startsWith(BLUEXML_MODEL_URI)) {
				NodeRef childRef = asso.getChildRef();
				delete(childRef.toString());
			}
		}
		nodeService.deleteNode(nodeRef);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.bluexml.side.Integration.alfresco.xforms.webscript.DataLayerInterface#read(java.lang.
	 * String)
	 */
	public String read(String objectId) {
		Document resultDocument = readAsDocument(objectId);
		String result = convertDocument2String(resultDocument);
		return result;
	}

	/**
	 * Reads an object from the repository.
	 * 
	 * @param objectId
	 *            the full node reference
	 * @return a DOM representation of the object, including all properties and associations.
	 */
	private Document readAsDocument(String objectId) {
		NodeRef nodeRef = new NodeRef(objectId);
		QName type = nodeService.getType(nodeRef);

		Map<QName, Serializable> properties = nodeService.getProperties(nodeRef);
		Map<QName, Serializable> convertedproperties = new HashMap<QName, Serializable>();
		for (Entry<QName, Serializable> entry : properties.entrySet()) {
			QName propertyName = entry.getKey();
			Serializable convertedValue = entry.getValue();
			// must convert value to proper one according to properties definitions
			PropertyDefinition propertyDef = dictionaryService.getProperty(propertyName);
			convertedValue = makePropertyValue(propertyDef, convertedValue);
			if (convertedValue instanceof Date) {
				convertedValue = dateTimeFormatter.print(((Date) convertedValue).getTime());
			}
			convertedproperties.put(propertyName, convertedValue);
		}

		Document resultDocument = XmlBuilder.buildEntry(type, objectId, convertedproperties,
				getAllAssociations(nodeRef));
		return resultDocument;
	}

	/**
	 * Creates a folder in the repository.
	 * 
	 * @param parentPath
	 * @param nodeName
	 * @return
	 * @throws Exception
	 */
	private NodeRef createFolder(String parentPath, String nodeName) throws Exception {
		String lparentPath = parentPath;
		if (lparentPath == null) {
			lparentPath = defaultStorePath;
		}
		QName assocTypeQName = ContentModel.ASSOC_CONTAINS;
		QName assocQName = QName.createQName(ALF_CONTENT_URI + nodeName);
		QName nodeTypeQName = QName.createQName(ALF_CONTENT_URI + "folder");
		NodeRef result = createNode(lparentPath, assocTypeQName, assocQName, nodeTypeQName,
				nodeName, null);
		return result;
	}

	/**
	 * Create a node using the specified parameters.
	 * 
	 * @param where
	 *            the parent container
	 * @param assocTypeQName
	 *            the relation between the parent and the node to create
	 * @param assocQName
	 *            the qualified name of the association
	 * @param nodeTypeQName
	 *            the type of the node
	 * @param nodeName
	 *            the name we want the node to appear under
	 * @param properties
	 *            can be null
	 * @return
	 * @throws Exception
	 */
	private NodeRef createNode(String where, QName assocTypeQName, QName assocQName,
			QName nodeTypeQName, String nodeName, Map<QName, Serializable> properties)
			throws Exception {
		String lwhere = where;
		if (lwhere == null) {
			lwhere = getDefaultNodePath(nodeTypeQName);
		}
		if (logger.isDebugEnabled()) {
			logger.debug(" createNode: NodePath=" + where);
		}

		NodeRef parent = createPath(lwhere);
		NodeRef newNode = null;
		
		QName lassocQName = assocQName;
		if (lassocQName == null) {
			lassocQName = QName.createQName(ALF_CONTENT_URI + parent.getId());
		}
		if (properties != null) {
			newNode = nodeService.createNode(parent, assocTypeQName, lassocQName, nodeTypeQName,
					properties).getChildRef();
		} else {
			newNode = nodeService.createNode(parent, assocTypeQName, lassocQName, nodeTypeQName)
					.getChildRef();
		}
		// set NodeName
		if (nodeName != null) {
			try {
				serviceRegistry.getFileFolderService().rename(newNode, nodeName);
			} catch (FileExistsException e) {
				logger.debug("Failed to rename", e);
			}
		}
		logger.debug(" NodeProperties :" + nodeService.getProperties(newNode));
		return newNode;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.bluexml.side.Integration.alfresco.xforms.webscript.DataLayerInterface#createPath(java
	 * .lang.String)
	 */
	public NodeRef createPath(String where) throws Exception {
		// fix for bug #931: we may be in a transaction. If so, we MUST not try to create the same
		// folder twice. Otherwise, the transaction gets marked for rollback!
		NodeRef parent = (isInTransaction() ? getParentFolderNodeRef(where) : null);
		if (parent == null) {
			List<NodeRef> results = request(where);
			if (results.size() == 0) {
				// create the missing folder
				int index = where.lastIndexOf("/");
				int indexName = where.lastIndexOf(":");
				String folderName = where.substring(indexName + 1);
				String folderPath = where.substring(0, index);
				parent = createFolder(folderPath, folderName);
			} else {
				parent = results.get(0);
			}
			// any parent must find its way into our transaction cache
			if (isInTransaction()) {
				addParentFolderNodeRef(where, parent);
			}
		}
		return parent;
	}

	/**
	 * Returns the first QName (among available types) whose local name matches the data type name.
	 * 
	 * @param dataTypeName
	 * @return
	 */
	private QName getDataTypeQName(String dataTypeName) {
		Collection<QName> datatypes = dictionaryService.getAllTypes();

		for (QName type : datatypes) {
			if (type.getLocalName().equals(dataTypeName)) {
				return type;
			}
		}
		logger.error("DataType not found :" + dataTypeName);
		return null;
	}

	/**
	 * Returns the first QName (among available properties from all registered (and live ?) models)
	 * whose local name matches the property name.
	 * 
	 * @param propertyName
	 * @return
	 */
	private QName getPropertyQName(String propertyName) {
		Collection<QName> properties = dictionaryService.getAllProperties(null);
		for (QName type : properties) {
			if (type.getLocalName().equals(propertyName)) {
				return type;
			}
		}
		logger.error("Property not found :" + propertyName);
		return null;
	}

	/**
	 * Returns the first QName (among associations defined for the specified type) whose local name
	 * matches the association name. Since we are constrained to a type, there will be, most
	 * probably, only one such QName.
	 * 
	 * @param type
	 * @param associationQualifiedName
	 * @return
	 */
	private QName getAssociationQName(QName type, String associationQualifiedName) {
		TypeDefinition def = dictionaryService.getType(type);
		for (QName key : def.getAssociations().keySet()) {
			if (key.getLocalName().equals(associationQualifiedName)) {
				return key;
			}
		}
		logger.error("Association Not Found :" + associationQualifiedName + " for type :" + type);
		return null;
	}

	/**
	 * Builds a Map that complies with the alfresco createNode method.
	 * 
	 * @param attributesMap
	 *            Map<localName,value>
	 * @param nodeTypeQName
	 * @return
	 * @throws Exception
	 */
	private Map<QName, Serializable> buildAttributesMap(Map<String, Object> attributesMap,
			QName nodeTypeQName, boolean isUpdating) throws Exception {
		final String CREATOR = ALF_CONTENT_URI + "creator";
		final String CREATED = ALF_CONTENT_URI + "created";

		Map<QName, PropertyDefinition> properties = dictionaryService.getType(nodeTypeQName)
				.getProperties();
		//
		// build mandatory properties with default values
		Map<QName, Serializable> mandatoryProperties = new HashMap<QName, Serializable>();
		// #1296: if updating, the object exists and all mandatory props are set, so skip this step
		if (!isUpdating) {
			mandatoryProperties.put(QName.createQName(CREATOR), authenticationService
					.getCurrentUserName());
			mandatoryProperties.put(QName.createQName(CREATED), new Date());
			for (QName propQName : properties.keySet()) {
				PropertyDefinition def = properties.get(propQName);
				if (def.isMandatory()) {
					mandatoryProperties.put(propQName, def.getDefaultValue());
				}
			}
		}
		//
		// resolve qualified names to QNames and convert values
		Map<QName, Serializable> resultMap = new HashMap<QName, Serializable>();
		for (String attName : attributesMap.keySet()) {
			// resolve Qualified attribute name to QName
			QName resolvedName = getPropertyQName(attName);
			// TODO : BEWARE of Multi-valued fields
			if (resolvedName != null) {
				// build attribute object ton convert him to write type
				PropertyDefinition propertyDef = dictionaryService.getProperty(nodeTypeQName,
						resolvedName);
				Serializable value = makePropertyValue(propertyDef, (Serializable) attributesMap
						.get(attName));
				resultMap.put(resolvedName, value);
			} else {
				logger.error("Attribute Not Found :" + attName);
			}
		}
		// if not present add mandatory properties to the result Map
		Set<QName> newSet = new HashSet<QName>();
		newSet.addAll(mandatoryProperties.keySet());
		newSet.addAll(resultMap.keySet());
		for (QName prop : newSet) {
			if (!resultMap.containsKey(prop)) {
				resultMap.put(prop, mandatoryProperties.get(prop));
			}
		}
		return resultMap;
	}

	/**
	 * Extracted from AbstractNodeService.<-- original DataLayer comment <br/>
	 * 
	 * 
	 * @param propertyDef
	 * @param pValue
	 * @return
	 */
	@SuppressWarnings("unchecked")
	private Serializable makePropertyValue(PropertyDefinition propertyDef, Serializable pValue) {
		Serializable value = pValue;
		// get property attributes
		QName propertyTypeQName = null;
		if (propertyDef == null) // property not recognised
		{
			// allow it for now - persisting excess properties can be useful
			// sometimes
			propertyTypeQName = DataTypeDefinition.ANY;
		} else {
			propertyTypeQName = propertyDef.getDataType().getName();
			// check that multi-valued properties are allowed
			boolean isMultiValued = propertyDef.isMultiValued();
			if (isMultiValued && !(value instanceof Collection)) {
				if (value != null) {
					// put the value into a collection
					// the implementation gives back a Serializable list
					value = (Serializable) Collections.singletonList(value);
				}
			} else if (!isMultiValued && (value instanceof Collection)) {
				// we only allow this case if the property type is ANY
				if (!propertyTypeQName.equals(DataTypeDefinition.ANY)) {
					throw new DictionaryException(
							"A single-valued property of this type may not be a collection: \n"
									+ "   Property: " + propertyDef + "\n" + "   Type: "
									+ propertyTypeQName + "\n" + "   Value: " + value);
				}
			}
		}
		try {
			return convertType(propertyTypeQName, value);
		} catch (TypeConversionException e) {
			String classe = "unknown";
			if (value != null) {
				classe = value.getClass().toString();
			}
			throw new TypeConversionException(
					"The property value is not compatible with the type defined for the property: \n"
							+ "   property: " + (propertyDef == null ? "unknown" : propertyDef)
							+ "\n" + "   value: " + value + "\n" + "   value type: "
							+ classe, e);
		}
	}

	/**
	 * Determines whether the value is legal for the given type by trying to convert the value to
	 * the type. If not, an exception is thrown.
	 * 
	 * @param type
	 * @param candidate
	 *            the value to check
	 * @return
	 */
	private static Serializable convertType(QName type, Serializable candidate) {

		if (candidate == null) {
			return null;
		}
		return new PropertyValue(type, candidate).getValue(type);
	}

	/**
	 * Create associations on a node.<br/>
	 * Association classes are no longer supported.
	 * 
	 * @param nodeRef
	 * @param source
	 * @param list
	 *            : a Map that describes the association set to create
	 *            <ul>
	 *            <li>Key : association QualifiedName</li>
	 *            <li>Value : Map<String, String> asso</li>
	 *            </ul>
	 *            asso :<br>
	 *            </br>
	 *            <ul>
	 *            <li>target : the target NodeRef</li>
	 *            <li>targetQualifiedName : the target qualified name</li>
	 *            </ul>
	 * 
	 * @return
	 */
	private NodeRef createAssociations(NodeRef nodeRef, QName source, List<AssociationBean> list) {
		for (AssociationBean associationBean : list) {
			createAssociation(nodeRef, source, associationBean);
		}
		return nodeRef;
	}

	/**
	 * Creates a single association originating from a node. Depending on the information in the
	 * bean, creates a child or simple association.
	 * 
	 * @param nodeRef
	 * @param source
	 * @param associationBean
	 */
	private synchronized void createAssociation(NodeRef nodeRef, QName source,
			AssociationBean associationBean) {
		if (logger.isDebugEnabled()) {
			logger.debug("createAssociation : NodeRef =" + nodeRef + " NodeType=" + source
					+ " AssoBean=" + associationBean);
		}
		// extract datas from assos Map
		QName targetQualifiedName = getDataTypeQName(associationBean.getTargetQualifiedName());
		String targetRef = associationBean.getTargetId();
		// get targetNodeRef
		NodeRef targetNodeRef = new NodeRef(targetRef);
		QName assoType = getAssociationQName(source, associationBean.getAssociationName());
		AssociationDefinition assoDef = dictionaryService.getAssociation(assoType);
		boolean success;
		if (assoDef.isChild()) {
			// association is a ChildAssociation
			success = createChildAssociation(assoType, nodeRef, targetQualifiedName, targetNodeRef);
		} else {
			// association is a simple association
			success = createSimpleAssociation(nodeRef, assoType, targetNodeRef);
		}
		if (success == false) {
			logger.error("createAssociation: failure.");
		} else {
			if (logger.isDebugEnabled()) {
				logger.debug("createAssociation: success");
			}
		}
	}

	/**
	 * Creates a child association.
	 * 
	 * @param assoType
	 * @param nodeRef
	 *            the parent node
	 * @param targetQualifiedName
	 * @param targetNodeRef
	 *            the child node
	 * @return false if an exception occurred
	 */
	private boolean createChildAssociation(QName assoType, NodeRef nodeRef,
			QName targetQualifiedName, NodeRef targetNodeRef) {
		ChildAssociationRef assoc = new ChildAssociationRef(assoType, nodeRef, targetQualifiedName,
				targetNodeRef, false, -1);
		try {
			this.nodeService.addChild(assoc.getParentRef(), assoc.getChildRef(), assoc
					.getTypeQName(), assoc.getTypeQName());
		} catch (AssociationExistsException e) {
			logger.warn(e.getMessage());
			return false;
		}
		return true;
	}

	/**
	 * Creates a simple association of the specified type between the node and the target.
	 * 
	 * @param nodeRef
	 * @param assoType
	 * @param targetNodeRef
	 * @return false if an exception occurred
	 */
	private boolean createSimpleAssociation(NodeRef nodeRef, QName assoType, NodeRef targetNodeRef) {
		AssociationRef assoc = new AssociationRef(nodeRef, assoType, targetNodeRef);
		try {
			this.nodeService.createAssociation(assoc.getSourceRef(), assoc.getTargetRef(), assoc
					.getTypeQName());
		} catch (AssociationExistsException e) {
			logger.error(e.getMessage(), e);
			return false;
		}
		return true;
	}

	/**
	 * Updates properties of a node.
	 * 
	 * @param nodeToUpdate
	 * @param properties
	 */
	private void updateProperties(NodeRef nodeToUpdate, Map<QName, Serializable> properties) {
		for (Entry<QName, Serializable> entry : properties.entrySet()) {
			QName propertyName = entry.getKey();
			Serializable convertedValue = entry.getValue();
			// must convert value to proper one according to properties definitions
			PropertyDefinition propertyDef = dictionaryService.getProperty(propertyName);
			convertedValue = makePropertyValue(propertyDef, convertedValue);

			// set property value
			nodeService.setProperty(nodeToUpdate, propertyName, convertedValue);
		}
	}

	/**
	 * Updates the associations on a node.
	 * 
	 * @param nodeRef
	 * @param nodeType
	 * @param list
	 *            the associations Map see createAssociations()
	 * @param deleteAllAssociations
	 * @return
	 * @throws Exception
	 */
	private NodeRef updateAssociations(NodeRef nodeRef, QName nodeType, List<AssociationBean> list,
			boolean deleteAllAssociations) throws Exception {
		logger.debug("UpdateAssociations");
		if (deleteAllAssociations) {
			// remove all associations of any Type !!
			QName removeNodeType = nodeService.getType(nodeRef);
			removeAllAssociationsForType(nodeRef, removeNodeType);

			for (AssociationBean associationBean : list) {
				try {
					createAssociation(nodeRef, nodeType, associationBean);
				} catch (Exception e) {
					logger.error("Failed to process association", e);
				}
			}
		} else {
			for (AssociationBean associationBean : list) {
				try {
					QName assoType = getAssociationQName(nodeType, associationBean
							.getAssociationName());
					AssociationBean.Actions ca = associationBean.getAction();

					if (ca.equals(AssociationBean.Actions.ADD)) {
						createAssociation(nodeRef, nodeType, associationBean);
					} else if (ca.equals(AssociationBean.Actions.DELETE)) {
						removeAssociation(nodeRef, nodeType, associationBean);
					} else if (ca.equals(AssociationBean.Actions.ADD_DELETE_OTHER)) {
						removeAllAssociationsWithType(nodeRef, assoType);
						createAssociation(nodeRef, nodeType, associationBean);
					} else if (ca.equals(AssociationBean.Actions.DELETE_ALL)) {
						removeAllAssociationsWithType(nodeRef, assoType);
					}
				} catch (Exception e) {
					logger.error("Failed to process association", e);
				}
			}
		}
		return nodeRef;
	}

	/**
	 * Removes all associations of a specified association type where the node is either the source
	 * or the parent.
	 * 
	 * @param nodeRef
	 * @param assoQName
	 *            the type of associations to remove
	 */
	private void removeAllAssociationsWithType(NodeRef nodeRef, QName assoQName) {
		AssociationDefinition assoDef = dictionaryService.getAssociation(assoQName);

		if (!assoDef.isChild()) {
			// the node is the source of the relationship
			List<AssociationRef> assos = null;
			assos = nodeService.getTargetAssocs(nodeRef, assoQName);
			for (AssociationRef asso : assos) {
				deleteSimpleAssociation(asso);
			}
		} else {
			// the node is the parent
			List<ChildAssociationRef> assos = null;
			assos = nodeService.getChildAssocs(nodeRef);
			for (ChildAssociationRef asso : assos) {
				ChildAssociationRef childAssocRef = new ChildAssociationRef(assoQName, asso
						.getParentRef(), assoQName, asso.getChildRef(), false, -1);
				nodeService.removeChildAssociation(childAssocRef);
			}
		}
	}

	/**
	 * Removes all associations that are valid for a specific data type, including inherited
	 * associations. Any previous associations involving the node as parent or source are removed.
	 * Assocs as target or child are kept.
	 * 
	 * @param nodeRef
	 * @param nodeType
	 */
	private void removeAllAssociationsForType(NodeRef nodeRef, QName nodeType) {
		ClassDefinition nodeClass = dictionaryService.getType(nodeType);
		// remove all child and source associations defined in content type
		for (QName assoQName : nodeClass.getAssociations().keySet()) {
			removeAllAssociationsWithType(nodeRef, assoQName);
		}
		// remove remaining assocs with children. Really any such assocs ?
		List<ChildAssociationRef> assos = nodeService.getChildAssocs(nodeRef);
		for (ChildAssociationRef asso : assos) {
			nodeService.removeChild(asso.getParentRef(), asso.getChildRef());
		}
		// remove associations inherited from ancestor content types
		QName parentName = nodeClass.getParentName();
		if (parentName != null) {
			removeAllAssociationsForType(nodeRef, parentName);
		}
	}

	/**
	 * Removes the single association where this node is the source and the target has the id
	 * carried by the bean.
	 * 
	 * @param nodeRef
	 * @param nodeType
	 * @param associationBean
	 */
	private void removeAssociation(NodeRef nodeRef, QName nodeType, AssociationBean associationBean) {
		logger.debug("RemoveAssociation : NodeRef =" + nodeRef + " NodeType=" + nodeType
				+ " AssoBean=" + associationBean);
		QName assoType = getAssociationQName(nodeType, associationBean.getAssociationName());
		AssociationRef asso = new AssociationRef(nodeRef, assoType, new NodeRef(associationBean
				.getTargetId()));
		deleteSimpleAssociation(asso);
	}

	/**
	 * 
	 * @param asso
	 *            the association to delete
	 */
	private void deleteSimpleAssociation(AssociationRef asso) {
		nodeService
				.removeAssociation(asso.getSourceRef(), asso.getTargetRef(), asso.getTypeQName());
	}

	/**
	 * 
	 * @param doc
	 * @return
	 */
	private static String convertDocument2String(Document doc) {
		StringBuilder stringBuilder = new StringBuilder();
		try {
			ByteArrayOutputStream stream = new ByteArrayOutputStream();
			OutputFormat outputformat = new OutputFormat();
			outputformat.setEncoding("UTF-8");
			outputformat.setIndenting(false);
			outputformat.setPreserveSpace(true);
			outputformat.setOmitXMLDeclaration(true);
			outputformat.setOmitDocumentType(true);
			XMLSerializer serializer = new XMLSerializer();
			serializer.setOutputFormat(outputformat);
			serializer.setOutputByteStream(stream);
			serializer.asDOMSerializer();
			serializer.serialize(doc.getDocumentElement());

			stringBuilder = new StringBuilder(stream.toString());
		} catch (Exception e) {
			e.printStackTrace();
		}
		return stringBuilder.toString();
	}

	/**
	 * Collects an AssociationBean object for each item linked to the nodeRef.
	 * 
	 * @param nodeRef
	 * @return the list of associations
	 */
	private List<AssociationBean> getAllAssociations(NodeRef nodeRef) {
		List<AssociationBean> associations = new ArrayList<AssociationBean>();

		TypeDefinition def = dictionaryService.getType(nodeService.getType(nodeRef));

		// get the qnames of associations in the data type definition
		List<QName> directAssociations = new ArrayList<QName>(def.getAssociations().keySet());

		// add associations where the data type is the source
		for (QName directAssociation : directAssociations) {
			addAssociations(nodeRef, directAssociation, associations);
		}

		// add associations where the data type is the parent
		List<ChildAssociationRef> assos = nodeService.getChildAssocs(nodeRef);
		for (ChildAssociationRef asso : assos) {
			QName associationName = asso.getTypeQName();
			if (associationName.getNamespaceURI().startsWith(BLUEXML_MODEL_URI)) {
				NodeRef childRef = asso.getChildRef();
				String targetLabel = getLabelForNode(childRef, FormatPattern.ALLTEXT.get());
				associations.add(new AssociationBean(associationName.getLocalName(), childRef
						.toString(), targetLabel, AssociationBean.AssoType.Composition, nodeService
						.getType(childRef).getLocalName()));
			}
		}
		return associations;
	}

	/**
	 * Adds the associations (other than child assocs) that match the given association name. Only
	 * those in the BlueXML namespace are added. Assocs may be multiple: several associated items
	 * with the same qname.
	 * 
	 * @param nodeRef
	 * @param directAssociation
	 * @param associations
	 */
	private void addAssociations(NodeRef nodeRef, QName directAssociation,
			List<AssociationBean> associations) {

		List<AssociationRef> assos = nodeService.getTargetAssocs(nodeRef, directAssociation);
		// there may be multiple items in the list if the assoc is multiple
		for (AssociationRef asso : assos) {
			QName associationName = asso.getTypeQName();
			if (associationName.getNamespaceURI().startsWith(BLUEXML_MODEL_URI)) {
				NodeRef childRef = asso.getTargetRef();
				String targetLabel = getLabelForNode(childRef, FormatPattern.ALLTEXT.get());
				associations.add(new AssociationBean(associationName.getLocalName(), childRef
						.toString(), targetLabel, nodeService.getType(childRef).getLocalName()));
			}
		}
	}

	/**
	 * 
	 * @param nodeRef
	 * @param pattern
	 *            the pattern for formatting the label for the node. Contains references to
	 *            properties of the node or of an association. This text is taken "as-is", with no
	 *            decoding, encoding, escaping or unescaping done.
	 * @return
	 */
	public String getLabelForNode(NodeRef nodeRef, String pattern) {
		String lpattern = pattern;
		if (StringUtils.trimToNull(lpattern) == null) {
			lpattern = FormatPattern.ALLTEXT.get();
		}
		if (StringUtils.equals(lpattern, FormatPattern.NONE.get())) {
			return nodeRef.toString();
		}
		Map<QName, Serializable> properties = nodeService.getProperties(nodeRef);
		Set<QName> names = properties.keySet();
		//
		if (StringUtils.equals(lpattern, FormatPattern.FIRST.get())) {
			return getLabelForNodeFirst(nodeRef, properties, names);
		} else if (StringUtils.equals(lpattern, FormatPattern.ALLTEXT.get())) {
			return getLabelForNodeAllText(nodeRef, properties, names);
		} else if (StringUtils.equals(lpattern, FormatPattern.ALL.get())) {
			return getLabelForNodeAll(nodeRef, properties, names);
		} else {
			// Interpret the format pattern
			return getNameForNode(lpattern, nodeRef);
		}
	}

	/**
	 * Builds a label for the given node from values of its properties or associations.
	 * 
	 * @param pattern
	 *            the format pattern
	 * @param nodeRef
	 * @return
	 */
	private String getNameForNode(String pattern, NodeRef nodeRef) {

		Map<QName, Serializable> properties = collectProperties(nodeRef);
		String result = "";
		String view = pattern;

		StringTokenizer st = new StringTokenizer(view, VIEW_TOKEN_SEPARATOR);
		boolean lastTokenWasText = false;
		boolean first = true;
		while (st.hasMoreTokens()) {
			String token = st.nextToken().trim();
			if (token.startsWith("{") && token.endsWith("}")) {
				result += token.substring(1, token.length() - 1);
				first = false;
				lastTokenWasText = true;
			} else if (token.contains(VIEW_FIELD_SEPARATOR)) {
				StringTokenizer st2 = new StringTokenizer(token, VIEW_FIELD_SEPARATOR);
				String clazz = null;
				String attribute = null;
				String association = null;

				if (st2.hasMoreTokens()) {
					clazz = st2.nextToken();
				}
				if (st2.hasMoreTokens()) {
					attribute = st2.nextToken();
				}
				if (st2.hasMoreTokens()) {
					association = st2.nextToken();
				}

				if (clazz != null) {
					//
					QName nodeType = nodeService.getType(nodeRef);
					if (nodeType.getLocalName().endsWith("_" + clazz)) {
						if (association == null) {
							if (attribute != null) {
								for (QName qname : properties.keySet()) {
									String key = qname.getLocalName();
									if (key.endsWith("_" + attribute)) {
										Serializable value = properties.get(qname);
										if (value != null && value.toString().length() > 0) {
											if (lastTokenWasText == false) {
												result += " ";
											}
											result += value;
											lastTokenWasText = false;
											first = false;
										}
									}
								}
							}
						} else {
							if (attribute != null) {
								Map<String, List<AssociationRef>> nodeAssos = collectAssociations(nodeRef);
								String associationFirstPartName = nodeType.toString() + "_"
										+ association;

								String associationName = getAssociationNameStartingBy(
										associationFirstPartName, nodeAssos);

								if (associationName.length() > 0) {
									List<?> assocs = nodeAssos.get(associationName);
									for (Object o : assocs) {
										if (o instanceof AssociationRef) {
											AssociationRef ref = (AssociationRef) o;
											Map<QName, Serializable> targetProperties = nodeService
													.getProperties(ref.getTargetRef());
											QName targetType = nodeService.getType(ref
													.getTargetRef());
											if (lastTokenWasText == false) {
												result += " ";
											}
											result += getPropertyValue(targetProperties,
													targetType, attribute);
											lastTokenWasText = false;
											first = false;
										}
									}
								}
							}
						}
					} else {
						logger.error("The class " + clazz + " is not appropriate for this node ('"
								+ nodeRef.toString() + "').");
					}
				}
			} else {
				// simple attribute
				for (QName qname : properties.keySet()) {
					String key = qname.getLocalName();
					if (key.endsWith("_" + token)) {
						Serializable value = properties.get(qname);
						if (value != null && value.toString().length() > 0) {
							if (lastTokenWasText == false && first == false) {
								result += " ";
							}
							result += value;
							lastTokenWasText = false;
							first = false;
						}
					}
				}
			}
		}

		if (result.length() == 0) {
			return nodeRef.getId();
		}
		// return result.substring(0, result.length() - 1);
		return result;
	}

	/**
	 * Adapted from org.alfresco.web.bean.repository.Node::getAssociations<br/>
	 * Returns the target associations of the node that stem from a generation.
	 * 
	 * @param nodeRef
	 *            the source node
	 * @return the list of target associations
	 */
	private final Map<String, List<AssociationRef>> collectAssociations(NodeRef nodeRef) {
		Map<String, List<AssociationRef>> result = new HashMap<String, List<AssociationRef>>();

		List<AssociationRef> assocs = nodeService.getTargetAssocs(nodeRef,
				RegexQNamePattern.MATCH_ALL);
		for (AssociationRef assocRef : assocs) {
			QName typeQName = assocRef.getTypeQName();
			String assocName = typeQName.toString();
			if (typeQName.getNamespaceURI().startsWith(BLUEXML_MODEL_URI)) {
				List<AssociationRef> list = result.get(assocName);
				// create the list if this is first association with 'assocName'
				if (list == null) {
					list = new ArrayList<AssociationRef>();
					result.put(assocName, list);
				}
				// add the association to the list
				list.add(assocRef);
			}
		}

		return result;
	}

	/**
	 * Returns the properties of the node that stem from a generation.
	 * 
	 * @param nodeRef
	 * @return
	 */
	private final Map<QName, Serializable> collectProperties(NodeRef nodeRef) {
		Map<QName, Serializable> result = new HashMap<QName, Serializable>();

		Map<QName, Serializable> properties = nodeService.getProperties(nodeRef);
		Set<QName> qnames = properties.keySet();
		for (QName qname : qnames) {
			if (qname.getNamespaceURI().startsWith(BLUEXML_MODEL_URI)) {
				result.put(qname, properties.get(qname));
			}
		}

		return result;
	}

	/**
	 * 
	 * @param associationFirstPartName
	 * @param map
	 * @return
	 */
	private String getAssociationNameStartingBy(String associationFirstPartName, Map<String, ?> map) {
		String associatioName = "";

		for (Iterator<String> i = map.keySet().iterator(); i.hasNext();) {
			String name = i.next();
			if (name.startsWith(associationFirstPartName)) {
				associatioName = name;
			}
		}
		return associatioName;
	}

	/**
	 * Return (if found) the value of an attribute
	 * 
	 * @param targetProperties
	 * @param targetType
	 * @param attributeName
	 * @return
	 */
	private String getPropertyValue(Map<QName, Serializable> targetProperties, QName targetType,
			String attributeName) {
		String name = targetType.toString() + "_" + attributeName;
		QName qname = QName.createQName(name);
		if (targetProperties.containsKey(qname)) {
			if (targetProperties.get(qname) != null) {
				return (String) targetProperties.get(qname);
			}
			return "";
		}
		if (dictionaryService.getType(targetType).getParentName() != null) {
			return getPropertyValue(targetProperties, dictionaryService.getType(targetType)
					.getParentName(), attributeName);
		}
		return "";
	}

	/**
	 * Gets the value of the first non-null textual property in the BlueXML namespace.
	 * 
	 * @param nodeRef
	 * @param properties
	 * @param names
	 * @return The first text property value, or the node id if no textual property is found.
	 */
	private String getLabelForNodeFirst(NodeRef nodeRef, Map<QName, Serializable> properties,
			Set<QName> names) {
		Serializable propValue;
		for (QName propertyName : names) {
			if (propertyName.getNamespaceURI().startsWith(BLUEXML_MODEL_URI)) {
				PropertyDefinition propertyDef = dictionaryService.getProperty(propertyName);
				String propTypeName = propertyDef.getDataType().getJavaClassName();
				if (StringUtils.equalsIgnoreCase(propTypeName, "java.lang.String")) {
					propValue = makePropertyValue(propertyDef, properties.get(propertyName));
					if (propValue != null) {
						String propStr = propValue.toString();
						if (StringUtils.trimToNull(propStr) != null) {
							return propStr;
						}
					}
				}
			}
		}
		return nodeRef.getId();
	}

	/**
	 * Gets a label built from all non-null text fields found amongst the properties in the BlueXML
	 * namespace. Each value is prefixed with the property's title. e.g.
	 * "First Name: John, Last Name: Doe, Hometown: Here-and-There".
	 * 
	 * @param nodeRef
	 * @param properties
	 * @param names
	 * @return the computed label, or the node id if the label is empty.
	 */
	private String getLabelForNodeAllText(NodeRef nodeRef, Map<QName, Serializable> properties,
			Set<QName> names) {
		Serializable propValue;
		String res = "";
		int nb = 0;
		for (QName propertyName : names) {
			if (propertyName.getNamespaceURI().startsWith(BLUEXML_MODEL_URI)) {
				PropertyDefinition propertyDef = dictionaryService.getProperty(propertyName);
				String propTypeName = propertyDef.getDataType().getJavaClassName();
				if (StringUtils.equalsIgnoreCase(propTypeName, "java.lang.String")) {
					propValue = makePropertyValue(propertyDef, properties.get(propertyName));
					if (propValue != null) {
						String propStr = propValue.toString();
						if (StringUtils.trimToNull(propStr) != null) {
							if (nb > 0) {
								res += ", ";
							}
							res += StringUtils.trimToEmpty(getDisplayLabelForProperty(propertyDef))
									+ ": " + propStr;
							nb++;
						}
					}
				}
			}
		}
		return StringUtils.trimToNull(res) == null ? nodeRef.getId() : res;
	}

	/**
	 * Gets a label built from all non-null properties in the BlueXML namespace. Similar to
	 * {@link #getLabelForNodeAllText(Map, Set)} except there's no restriction to textual fields.
	 * 
	 * @param nodeRef
	 * @param properties
	 * @param names
	 * @return the computed label, or the node id if the label is empty.
	 */
	private String getLabelForNodeAll(NodeRef nodeRef, Map<QName, Serializable> properties,
			Set<QName> names) {
		Serializable propValue;
		String res = "";
		int nb = 0;
		for (QName propertyName : names) {
			if (propertyName.getNamespaceURI().startsWith(BLUEXML_MODEL_URI)) {
				PropertyDefinition propertyDef = dictionaryService.getProperty(propertyName);
				propValue = makePropertyValue(propertyDef, properties.get(propertyName));
				if (propValue != null) {
					String propStr = propValue.toString();
					if (StringUtils.trimToNull(propStr) != null) {
						if (nb > 0) {
							res += ", ";
						}
						res += StringUtils.trimToEmpty(getDisplayLabelForProperty(propertyDef))
								+ ": " + propStr;
						nb++;
					}
				}
			}
		}
		return StringUtils.trimToNull(res) == null ? nodeRef.getId() : res;
	}

	/**
	 * Computes a label for a property definition in a BlueXML model.
	 * 
	 * @param propertyDef
	 * @return the title set in the model, or the name (in case no title was defined)
	 */
	private String getDisplayLabelForProperty(PropertyDefinition propertyDef) {
		String res = propertyDef.getTitle();
		return StringUtils.trimToNull(res) == null ? propertyDef.getName().getLocalName() : res;
	}

	/**
	 * @return the inTransaction
	 */
	private boolean isInTransaction() {
		return inTransaction;
	}

	/**
	 * Sets the "in transaction" status and <b>clears</b> the folder cache.
	 * 
	 * @param inTransaction
	 *            the inTransaction to set
	 */
	public void setInTransaction(boolean inTransaction) {
		this.inTransaction = inTransaction;
		// MANDATORY: clear the folder cache
		clearParentFolderCache();
	}

	/**
	 * Returns the cached NodeRef for the folder at "where".
	 * <p/>
	 * Part of correction for bug #931. Part of the monitor on the cache.
	 * 
	 * @param where
	 *            the location of the folder
	 * @return the noderef
	 */
	private synchronized NodeRef getParentFolderNodeRef(String where) {
		if (parentFolderCache == null) {
			clearParentFolderCache();
		}
		return parentFolderCache.get(where);
	}

	/**
	 * Adds a noderef associated with a folder location.
	 * <p/>
	 * Part of correction for bug #931. Also enforces a monitor on the cache.
	 * 
	 * @param where
	 * @param parent
	 */
	private synchronized void addParentFolderNodeRef(String where, NodeRef parent) {
		if (parentFolderCache == null) {
			clearParentFolderCache();
		}
		parentFolderCache.put(where, parent);
	}

	/**
	 * Part of correction for bug #931.
	 */
	private synchronized void clearParentFolderCache() {
		parentFolderCache = new TreeMap<String, NodeRef>();
	}

	private String getDefaultNodePath(QName nodeType) {
		QName lnodeType = getDataTypeQName(nodeType.getLocalName());
		return defaultStorePath + "/cm:" + lnodeType.getLocalName();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.bluexml.side.Integration.alfresco.xforms.webscript.DataLayerInterface#attachContent(java
	 * .lang.String, java.lang.String)
	 */
	public boolean attachContent(String receiver, String filename, String filepath,
			String mimeType, String contentType, boolean shouldAppendSuffix) throws Exception {
		NodeRef newNode = new NodeRef(receiver);
		QName nodeTypeQName = QName.createQName(BLUEXML_MODEL_URI + "1.0", contentType);

		boolean applyName = (StringUtils.trimToNull(filename) != null);

		String resultId = uploadContentToNode(newNode, filename, filepath, mimeType, nodeTypeQName,
				applyName, shouldAppendSuffix);
		if (StringUtils.trimToNull(resultId) == null) {
			return false;
		}
		return true;
	}

	/**
	 * @param newNode
	 *            an existing node
	 * @param filename
	 *            the display name that should be set on the node if the content writing succeeds.
	 *            NO GUARANTEE is given about whether the name is indeed applied.
	 * @param filepath
	 * @param mimeType
	 * @param nodeTypeQName
	 *            the node content type. Non-BlueXML types are supported.
	 * @param applyName
	 *            if false, there will be no attempt to set the file name
	 * @param shouldAppendSuffix
	 *            if the renaming is activated, an index [e.g. '(1)'] may be appended to the
	 *            filename. There will be no failure due to an unavailable name.
	 * @return the node ref (protocol, store and id) to the node, or empty string if exception.
	 */
	public String uploadContentToNode(NodeRef newNode, String filename, String filepath,
			String mimeType, QName nodeTypeQName, boolean applyName, boolean shouldAppendSuffix) {
		String resultId, FAILURE = "";
		ContentWriter writer = serviceRegistry.getContentService().getWriter(newNode,
				nodeTypeQName, false);

		// set MIME type property
		writer.setMimetype(mimeType);

		// upload the file content to the node
		long sizeUploaded = writeFileContentIntoNode(filepath, writer);
		if (sizeUploaded != -1) {
			// set other properties
			Map<QName, Serializable> properties = this.serviceRegistry.getNodeService()
					.getProperties(newNode);
			properties.put(ContentModel.PROP_CONTENT, writer.getContentData());
			this.serviceRegistry.getNodeService().setProperties(newNode, properties);
			resultId = StringEscapeUtils.escapeXml(newNode.toString());
			logger.debug(" File '" + filename + "' (size: " + sizeUploaded + ") uploaded to node: "
					+ resultId);
			// set the node name
			if (applyName) {
				if (shouldAppendSuffix) {
					String currentName = filename;
					int idx = 0;
					while (idx < 1000) { // is there any need to go beyond
						try {
							serviceRegistry.getFileFolderService().rename(newNode, currentName);
							break;
						} catch (FileExistsException e) {
							idx++;
							currentName = filename + " (" + idx + ")";
						} catch (org.alfresco.service.cmr.model.FileNotFoundException e) {
							logger.debug("Failed to rename: the node to rename does not exist!", e);
							return FAILURE;
						}
					}
				} else {
					try {
						serviceRegistry.getFileFolderService().rename(newNode, filename);
					} catch (FileExistsException e) {
						logger.debug("Failed to rename: the file already exists!", e);
						return FAILURE;
					} catch (org.alfresco.service.cmr.model.FileNotFoundException e) {
						logger.debug("Failed to rename: the node to rename does not exist!", e);
						return FAILURE;
					}
				}
			}
			return resultId;
		}
		return FAILURE;
	}

	/**
	 * Writes the content of a file into a node. The file is read as a binary file.
	 * 
	 * @param filename
	 *            the name of the node
	 * @param filepath
	 *            complete path to the file, including name and extension
	 * @param location
	 *            address of a folder in the content repository
	 * @param writer
	 *            the appropriate content writer to the node. <b>Must have been already gotten from
	 *            the content service.</b>
	 * @return the length in bytes of the uploaded content if no exception was thrown during the
	 *         process, -1 otherwise
	 */
	private long writeFileContentIntoNode(String filepath, ContentWriter writer) {

		File theFile = new File(filepath);
		long length = theFile.length();

		try {
			writer.putContent(theFile);
		} catch (ContentIOException e) {
			return -1;
		}
		return length;
	}

}
