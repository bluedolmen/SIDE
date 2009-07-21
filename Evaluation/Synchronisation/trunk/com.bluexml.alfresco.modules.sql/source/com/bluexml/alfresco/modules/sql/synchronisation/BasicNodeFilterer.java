package com.bluexml.alfresco.modules.sql.synchronisation;

import java.io.Serializable;

import org.alfresco.service.cmr.repository.NodeRef;
import org.alfresco.service.cmr.repository.NodeService;
import org.alfresco.service.namespace.NamespaceService;
import org.alfresco.service.namespace.QName;

public class BasicNodeFilterer implements NodeFilterer {

	private static final String BLUEXML_MODEL_URI = "http://www.bluexml.com/model/content/";
	
	public boolean accept(NodeRef nodeRef) {
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

	public boolean accept(QName qname) {
		boolean accepted = true;
		
		accepted &= qname.getNamespaceURI().startsWith(BLUEXML_MODEL_URI);
		accepted |= (qname.getNamespaceURI().equals(NamespaceService.SYSTEM_MODEL_1_0_URI) && 
						("node-dbid".equals(qname.getLocalName()) || "node-uuid".equals(qname.getLocalName()) ) 
					);
		
		return accepted;
	}
	
	//
	// IoC/DI Spring
	//

	// Dependencies
	private NodeService nodeService;

	public void setNodeService(NodeService nodeService_) {
		nodeService = nodeService_;
	}


	
}
