package com.bluexml.side.framework.alfresco.unicity;

import java.util.Map;

import org.alfresco.service.cmr.repository.NodeRef;
import org.alfresco.service.namespace.QName;

public interface UnicityChecker {
	
	public abstract boolean exist(QName nodeType, Map<?, ?> prop, String uuid) throws Exception;

	public abstract boolean exist(NodeRef node) throws Exception;

}