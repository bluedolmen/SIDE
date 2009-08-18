package com.bluexml.side.Integration.alfresco.sql.synchronization.common;

import java.io.Serializable;

import org.alfresco.model.ContentModel;
import org.alfresco.service.cmr.repository.NodeRef;
import org.alfresco.service.cmr.repository.NodeService;
import org.alfresco.service.cmr.repository.StoreRef;
import org.alfresco.service.namespace.QName;

public class NamespacePrefixNodeFilterer implements NodeFilterer {

	public boolean accept(NodeRef nodeRef) {
		boolean result = false;
		QName nodeType = nodeService.getType(nodeRef);
		String uri = nodeType.getNamespaceURI();
		if (uri.startsWith(namespacePrefix)) {
			Serializable store_protocol = nodeService.getProperty(nodeRef, ContentModel.PROP_STORE_PROTOCOL);
			// Only process nodes which are not in the archive space (the node
			// was deleted)
			if (!store_protocol.equals(StoreRef.PROTOCOL_ARCHIVE) ){
				result = true;
			}
		}
		return result;
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
