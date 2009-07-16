package com.bluexml.alfresco.modules.sql.searcher;

import java.util.Collection;

import org.alfresco.service.cmr.repository.NodeRef;

public interface SQLSearchService {

	//public ResultSet query(String query);
	public Collection<NodeRef> query (String typeName);
	
}
