package com.bluexml.side.Integration.alfresco.sql.synchronization.common;

import org.alfresco.service.namespace.QName;

public interface NodeFilterer {

	public boolean acceptOnName(QName qname);
	
	//public boolean accept(NodeRef nodeRef);
	
}
