package com.bluexml.side.Integration.alfresco.sql.synchronization.common;

import org.alfresco.model.ContentModel;
import org.alfresco.service.cmr.repository.NodeRef;
import org.alfresco.service.cmr.repository.NodeService;
import org.alfresco.service.cmr.repository.StoreRef;
import org.alfresco.service.namespace.QName;

public class NamespacePrefixNodeFilterer implements NodeFilterer {

	public boolean accept(NodeRef nodeRef) {
		QName nodeType = nodeService.getType(nodeRef);
		
		return StoreRef.STORE_REF_WORKSPACE_SPACESSTORE.equals(nodeRef.getStoreRef()) && nodeType.getNamespaceURI().startsWith(namespacePrefix);
	}

	public boolean acceptOnName(QName qname) {
		boolean accepted = true;
		
		accepted &= qname.getNamespaceURI().startsWith(namespacePrefix);
		accepted |= ContentModel.PROP_NODE_DBID.equals(qname) || ContentModel.PROP_NODE_UUID.equals(qname); 
		
		return accepted;
	}
	
	//
	// IoC/DI Spring
	//

	// Dependencies
	private NodeService nodeService;
	private String namespacePrefix;

	public void setNodeService(NodeService nodeService_) {
		nodeService = nodeService_;
	}

	public void setNamespacePrefix(String namespacePrefix_) {
		namespacePrefix = namespacePrefix_;
	}
	
}
