package com.bluexml.alfresco.modules.sql.synchronisation.common;

import org.alfresco.service.cmr.repository.NodeRef;
import org.alfresco.service.namespace.QName;

public interface NodeFilterer {

	public boolean acceptOnName(QName qname);
	
	public boolean accept(NodeRef nodeRef);
	
}
