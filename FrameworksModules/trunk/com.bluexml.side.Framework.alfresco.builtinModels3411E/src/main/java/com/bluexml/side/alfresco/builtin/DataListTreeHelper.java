package com.bluexml.side.alfresco.builtin;

import java.util.ArrayList;
import java.util.List;

import org.alfresco.service.ServiceRegistry;
import org.alfresco.service.cmr.repository.AssociationRef;
import org.alfresco.service.cmr.repository.NodeRef;
import org.alfresco.service.cmr.repository.StoreRef;
import org.alfresco.service.cmr.search.ResultSet;
import org.alfresco.service.cmr.search.SearchService;
import org.alfresco.service.namespace.QName;
import org.alfresco.util.ISO9075;

public class DataListTreeHelper {
	public static final String SITES_SPACE_QNAME_PATH = "/app:company_home/st:sites/";
	protected ServiceRegistry services;
	protected NodeRef root = null;

	QName assoType = BuiltInModel.ASSO_TreeFilter_PARENT;
	QName nodeType = BuiltInModel.ASPECT_TreeFilter;
	QName rootProperty = BuiltInModel.PROP_TreeFilter_ROOT;

	protected String path = null;
	protected String site = null;

	protected boolean selectableTypeIsAspect = true;

	protected String rootName = null;

	protected boolean selectableRoot = true;

	public DataListTreeHelper(QName assoType, QName nodeType, QName rootProperty, String path, String site, boolean selectableTypeIsAspect, String rootName, Boolean selectableRoot, ServiceRegistry services) {
		this.assoType = assoType != null ? assoType : this.assoType;
		this.nodeType = nodeType != null ? nodeType : this.nodeType;
		this.rootProperty = rootProperty != null ? rootProperty : this.rootProperty;
		this.path = path != null ? path : this.path;
		this.site = site != null ? site : this.site;
		this.selectableTypeIsAspect = selectableTypeIsAspect;
		this.rootName = rootName != null ? rootName : this.rootName;
		this.selectableRoot = selectableRoot != null ? selectableRoot : this.selectableRoot;
		this.services = services;
	}

	public List<NodeRef> getTreeNodeChidren(NodeRef nodeRef) throws Exception {
		NodeRef currentNode = null;

		List<NodeRef> children = null;
		boolean isRoot = false;

		// set currentNode
		if (nodeRef == null) {
			isRoot = true;
			currentNode = getRootNode();
		} else {
			currentNode = nodeRef;
		}

		// set parentNode and children
		if (isRoot && selectableRoot) {
			children = new ArrayList<NodeRef>();
			children.add(currentNode);
		} else {
			children = new ArrayList<NodeRef>();
			List<AssociationRef> sourceAssocs = services.getNodeService().getSourceAssocs(currentNode, assoType);
			for (AssociationRef associationRef : sourceAssocs) {
				children.add(associationRef.getSourceRef());
			}

//			List<NodeRef> authorizedChildren = new ArrayList<NodeRef>();
			
			// TODO check permission, in js the getChildren return all nodes even if current user do not have the read permission so we need to check
			// TODO Test if this happen here
//			for (NodeRef child : children) {
//				if (services.getPermissionService().hasReadPermission(child).equals(AccessStatus.ALLOWED)) {
//					authorizedChildren.add(child);
//				}
//			}
//			children = authorizedChildren;
		}

		return children;

	}

	public NodeRef getRootNode() throws Exception {
		if (root == null) {
			String rootQuery = "";
			if (selectableTypeIsAspect) {
				rootQuery = "ASPECT:\"" + nodeType + "\"";
			} else {
				rootQuery = "TYPE:\"" + nodeType + "\"";
			}
			rootQuery += " AND ";

			String path = null;
			if (site != null) {
				path = SITES_SPACE_QNAME_PATH;
				if (site != null && site.length() > 0) {
					path += "cm:" + ISO9075.encode(site) + "//*";
				}
			} else if (this.path != null) {
				path = this.path;
			}

			if (path != null) {
				path = "PATH:\"" + path + "\"";
				rootQuery += path + " AND ";
			}

			rootQuery += " @" + rootProperty.toPrefixString(services.getNamespaceService()).replace(":", "\\:") + ":true";
			rootQuery += " AND ";
			rootQuery += " @cm\\:name:\"" + rootName + "\"";

			ResultSet query = services.getSearchService().query(StoreRef.STORE_REF_WORKSPACE_SPACESSTORE, SearchService.LANGUAGE_LUCENE, rootQuery);
			List<NodeRef> results = query.getNodeRefs();

			if (results.size() > 0) {
				this.root = results.get(0);
			} else {
				// error
				throw new Exception("No Root Found please create Root with name=" + rootName);
			}
		}
		return root;
	}

	/**
	 * @return the assoType
	 */
	public QName getAssoType() {
		return assoType;
	}

	/**
	 * @return the nodeType
	 */
	public QName getNodeType() {
		return nodeType;
	}

	/**
	 * @return the rootProperty
	 */
	public QName getRootProperty() {
		return rootProperty;
	}

	/**
	 * @return the path
	 */
	public String getPath() {
		return path;
	}

	/**
	 * @return the site
	 */
	public String getSite() {
		return site;
	}

	/**
	 * @return the selectableTypeIsAspect
	 */
	public boolean isSelectableTypeIsAspect() {
		return selectableTypeIsAspect;
	}

	/**
	 * @return the rootName
	 */
	public String getRootName() {
		return rootName;
	}

	/**
	 * @return the selectableRoot
	 */
	public boolean isSelectableRoot() {
		return selectableRoot;
	}

}
