package com.bluexml.side.Integration.alfresco.sql.synchronization.common;

import org.alfresco.model.ContentModel;
import org.alfresco.service.namespace.QName;

public class NamespacePrefixFilterer extends AbstractFilterer {
	
	public boolean acceptQName(QName qname) {
		return qname.getNamespaceURI().startsWith(namespacePrefix);
	}

	public boolean acceptPropertyQName(QName qname) {
		return super.acceptPropertyQName(qname) || ContentModel.PROP_NODE_DBID.equals(qname) || ContentModel.PROP_NODE_UUID.equals(qname);
	}
	
	//
	// IoC/DI Spring
	//

	// Dependencies
	private String namespacePrefix;

	public void setNamespacePrefix(String namespacePrefix_) {
		namespacePrefix = namespacePrefix_;
	}

	
}
