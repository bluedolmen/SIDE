<%
metamodel http://www.kerblue.org/class/1.0
import templates.servicesTemplates.Common
import com.bluexml.side.clazz.service.alfresco.ClassServices
import com.bluexml.side.clazz.service.alfresco.CommonServices
import com.bluexml.side.clazz.service.alfresco.AttributeServices
import com.bluexml.side.clazz.service.alfresco.AssociationServices

%>
<%script type="clazz.ClassPackage" name="validatedFilename"%>
<%if (eContainer() == null) {%><%getProperty("javaEmbeddedAPIPath")%>/<%getProperty("javaSource")%>/com/bluexml/side/alfresco/crud/<%getProperty("java.classes.abstractClassFactory")%>.java<%}%>
<%script type="clazz.ClassPackage" name="alfrescoGenerator" file="<%validatedFilename%>"%>
package <%getProperty("javaPackageAPI")%>;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import org.alfresco.model.ContentModel;
import org.alfresco.service.ServiceRegistry;
import org.alfresco.service.cmr.model.FileExistsException;
import org.alfresco.service.cmr.repository.AssociationExistsException;
import org.alfresco.service.cmr.repository.AssociationRef;
import org.alfresco.service.cmr.repository.ChildAssociationRef;
import org.alfresco.service.cmr.repository.NodeRef;
import org.alfresco.service.cmr.repository.NodeService;
import org.alfresco.service.cmr.repository.StoreRef;
import org.alfresco.service.cmr.search.ResultSet;
import org.alfresco.service.cmr.search.SearchService;
import org.alfresco.service.namespace.QName;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.bluexml.side.alfresco.model.<%getRootPackage().name%>.*;

public class <%getProperty("java.classes.abstractClassFactory")%> {
	protected final static String ALF_CONTENT_URI = ContentModel.TYPE_CONTENT.getNamespaceURI();
	protected boolean inTransaction;
	protected TreeMap<String, NodeRef> parentFolderCache = null;

	protected static Log logger = LogFactory.getLog(<%getProperty("java.classes.abstractClassFactory")%>.class);
	protected NodeService nodeService;
	protected ServiceRegistry serviceRegistry;

	public NodeService getNodeService() {
		return nodeService;
	}

	public void setNodeService(NodeService nodeService) {
		this.nodeService = nodeService;
	}

	public ServiceRegistry getServiceRegistry() {
		return serviceRegistry;
	}

	public void setServiceRegistry(ServiceRegistry serviceRegistry) {
		this.serviceRegistry = serviceRegistry;
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
	protected NodeRef createNode(String where, QName assocTypeQName, QName assocQName, QName nodeTypeQName, String nodeName, Map<QName, Serializable> properties) throws Exception {
		String lwhere = where;
		if (lwhere == null) {
			lwhere = getDefaultNodePath();
		}
		if (logger.isDebugEnabled()) {
			logger.debug(" createNode: NodePath=" + where);
		}

		NodeRef parent = createPath(lwhere);
		NodeRef newNode = null;

		QName lassocQName = assocQName;
		if (lassocQName == null) {
			lassocQName = QName.createQName("{" + ALF_CONTENT_URI + "}" + properties.get(ContentModel.PROP_NAME));
		}
		if (properties != null) {
			newNode = nodeService.createNode(parent, assocTypeQName, lassocQName, nodeTypeQName, properties).getChildRef();
		} else {
			newNode = nodeService.createNode(parent, assocTypeQName, lassocQName, nodeTypeQName).getChildRef();
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

	protected String getDefaultNodePath() {
		return "/app:company_home";
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.bluexml.side.Integration.alfresco.xforms.webscript.DataLayerInterface
	 * #createPath(java .lang.String)
	 */
	public NodeRef createPath(String where) throws Exception {
		// fix for bug #931: we may be in a transaction. If so, we MUST not try
		// to create the same
		// folder twice. Otherwise, the transaction gets marked for rollback!
		NodeRef parent = (isInTransaction() ? getParentFolderNodeRef(where) : null);
		if (parent == null) {
			List<NodeRef> results = requestXPath(where);
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
	 * Adds a noderef associated with a folder location.
	 * <p/>
	 * Part of correction for bug #931. Also enforces a monitor on the cache.
	 * 
	 * @param where
	 * @param parent
	 */
	protected synchronized void addParentFolderNodeRef(String where, NodeRef parent) {
		if (parentFolderCache == null) {
			clearParentFolderCache();
		}
		parentFolderCache.put(where, parent);
	}

	/**
	 * @return the inTransaction
	 */
	protected boolean isInTransaction() {
		return inTransaction;
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
	protected synchronized NodeRef getParentFolderNodeRef(String where) {
		if (parentFolderCache == null) {
			clearParentFolderCache();
		}
		return parentFolderCache.get(where);
	}

	/**
	 * Part of correction for bug #931.
	 */
	protected synchronized void clearParentFolderCache() {
		parentFolderCache = new TreeMap<String, NodeRef>();
	}

	/**
	 * Creates a folder in the repository.
	 * 
	 * @param parentPath
	 * @param nodeName
	 * @return
	 * @throws Exception
	 */
	protected NodeRef createFolder(String parentPath, String nodeName) throws Exception {
		String lparentPath = parentPath;
		if (lparentPath == null) {
			lparentPath = getDefaultNodePath();
		}
		QName assocTypeQName = ContentModel.ASSOC_CONTAINS;
		QName assocQName = QName.createQName(ALF_CONTENT_URI + nodeName);
		QName nodeTypeQName = QName.createQName(ALF_CONTENT_URI + "folder");
		NodeRef result = createNode(lparentPath, assocTypeQName, assocQName, nodeTypeQName, nodeName, null);
		return result;
	}

	public List<NodeRef> requestLucene(String lucene) throws Exception {
		NodeRef root = nodeService.getRootNode(StoreRef.STORE_REF_WORKSPACE_SPACESSTORE);
		ResultSet result = serviceRegistry.getSearchService().query(root.getStoreRef(), SearchService.LANGUAGE_LUCENE, lucene);
		return result.getNodeRefs();
	}

	public List<NodeRef> requestXPath(String xpath) throws Exception {
		NodeRef root = nodeService.getRootNode(StoreRef.STORE_REF_WORKSPACE_SPACESSTORE);
		ResultSet result = serviceRegistry.getSearchService().query(root.getStoreRef(), SearchService.LANGUAGE_XPATH, xpath);
		return result.getNodeRefs();
	}

	/**
	 * Updates properties of a node.
	 * 
	 * @param nodeToUpdate
	 * @param properties
	 */
	protected void updateProperties(NodeRef nodeToUpdate, Map<QName, Serializable> properties) {
		for (Map.Entry<QName, Serializable> entry : properties.entrySet()) {
			QName propertyName = entry.getKey();
			Serializable convertedValue = entry.getValue();
			// must convert value to proper one according to properties
			// definitions
			// PropertyDefinition propertyDef =
			// serviceRegistry.getDictionaryService().getProperty(propertyName);
			// convertedValue = makePropertyValue(propertyDef, convertedValue);

			// set property value
			nodeService.setProperty(nodeToUpdate, propertyName, convertedValue);
		}
	}

	public void delete(String objectId) {
		NodeRef nodeRef = new NodeRef(objectId);
		List<ChildAssociationRef> assos = this.serviceRegistry.getNodeService().getChildAssocs(nodeRef);
		for (ChildAssociationRef asso : assos) {
			QName associationName = asso.getTypeQName();
			if (associationName.getNamespaceURI().startsWith(ModelQNames.NURI_CONTENT_MODEL)) {
				NodeRef childRef = asso.getChildRef();
				delete(childRef.toString());
			}
		}
		nodeService.deleteNode(nodeRef);
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
	protected boolean createChildAssociation(QName assoType, NodeRef nodeRef, QName targetQualifiedName, NodeRef targetNodeRef) {
		ChildAssociationRef assoc = new ChildAssociationRef(assoType, nodeRef, targetQualifiedName, targetNodeRef, false, -1);
		try {
			this.nodeService.addChild(assoc.getParentRef(), assoc.getChildRef(), assoc.getTypeQName(), assoc.getTypeQName());
		} catch (AssociationExistsException e) {
			logger.warn(e.getMessage());
			return false;
		}
		return true;
	}

	/**
	 * Creates a simple association of the specified type between the node and
	 * the target.
	 * 
	 * @param nodeRef
	 * @param assoType
	 * @param targetNodeRef
	 * @return false if an exception occurred
	 */
	protected boolean createSimpleAssociation(NodeRef nodeRef, QName assoType, NodeRef targetNodeRef) {
		AssociationRef assoc = new AssociationRef(nodeRef, assoType, targetNodeRef);
		try {
			this.nodeService.createAssociation(assoc.getSourceRef(), assoc.getTargetRef(), assoc.getTypeQName());
		} catch (AssociationExistsException e) {
			logger.error(e.getMessage(), e);
			return false;
		}
		return true;
	}

	/**
	 * 
	 * @param asso
	 *            the association to delete
	 */
	protected void deleteSimpleAssociation(AssociationRef asso) {
		nodeService.removeAssociation(asso.getSourceRef(), asso.getTargetRef(), asso.getTypeQName());
	}
	
	protected List<NodeRef> getAssociatedNode(NodeRef nodeRef, QName assoName) {
		List<NodeRef> l = new ArrayList<NodeRef>();		
		List<AssociationRef> lref=nodeService.getTargetAssocs(nodeRef, assoName);
		for (AssociationRef associationRef : lref) {
			l.add(associationRef.getTargetRef());
		}
		return l;
	}
}
