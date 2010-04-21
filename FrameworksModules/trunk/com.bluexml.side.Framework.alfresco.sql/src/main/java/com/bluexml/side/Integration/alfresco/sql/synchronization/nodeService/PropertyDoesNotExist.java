package com.bluexml.side.Integration.alfresco.sql.synchronization.nodeService;

import org.alfresco.service.cmr.repository.NodeRef;
import org.alfresco.service.namespace.QName;

public class PropertyDoesNotExist extends NodeServiceFailureException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 6247506898280470790L;
	private QName failingProperty;
	private NodeRef nodeRef;
		
	public PropertyDoesNotExist(QName propertyName, NodeRef nodeRef_) {
		failingProperty = propertyName;
		nodeRef = nodeRef_;
	}

	public String toString() {
		return "Cannot find property " + failingProperty + " in the node " + nodeRef;
	}
}
