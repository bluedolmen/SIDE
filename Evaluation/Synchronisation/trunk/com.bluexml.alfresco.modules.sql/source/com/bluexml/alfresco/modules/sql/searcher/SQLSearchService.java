package com.bluexml.alfresco.modules.sql.searcher;

import java.util.Collection;

import org.alfresco.service.cmr.repository.NodeRef;

public interface SQLSearchService {

	/*
	 * SELECT NodeRef(uuid) FROM typeName WHERE [condition]
	 */
	public Collection<NodeRef> selectNodes (String typeName, String condition);
	
	public Collection<NodeRef> selectNodes (SearchParameters searchParameters);
	
}
