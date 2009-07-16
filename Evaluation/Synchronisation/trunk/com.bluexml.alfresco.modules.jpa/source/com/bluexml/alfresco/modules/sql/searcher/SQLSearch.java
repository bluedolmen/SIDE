package com.bluexml.alfresco.modules.sql.searcher;

import java.util.Collection;

import org.alfresco.repo.tenant.TenantService;
import org.alfresco.service.cmr.repository.NodeRef;
import org.alfresco.service.cmr.repository.NodeService;

import com.bluexml.alfresco.modules.sql.utils.DatabaseQuery;

public class SQLSearch implements SQLSearchService {
	
//	public ResultSet query(String typeName) {
//		String query_ = "SELECT * FROM " + typeName;
//		//String countQuery_ = "SELECT COUNT(*) FROM " + typeName;
//		
//		java.sql.ResultSet resultSet = databaseQuery.executeQuery(query_);
//		//java.sql.ResultSet countResultSet = databaseQuery.executeQuery(countQuery_);
//		
//  		Path[] propertyPaths = new Path[0];
//  		SearchParameters searchParameters = new SearchParameters();
//		SQLResultSet sqlResultSet = new SQLResultSet(resultSet, nodeService, tenantService, propertyPaths, searchParameters);
//		
//		return sqlResultSet;
//	}
	
	public Collection<NodeRef> query(String typeName) {
		Collection<NodeRef> result = null;
		
		String query_ = "SELECT * FROM " + typeName;
		try {
			result = databaseQuery.getResultAsNodeRefs(query_);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return result;
	}
	
	// SPRING BEAN MANAGEMENT

	private static DatabaseQuery databaseQuery;
	private static NodeService nodeService;
	private static TenantService tenantService;

	public DatabaseQuery getDatabaseQuery() {
		return databaseQuery;
	}
	
	public void setDatabaseQuery(DatabaseQuery databaseQuery_) {
		databaseQuery = databaseQuery_;
	}

	public NodeService getNodeService() {
		return nodeService;
	}

	public void setNodeService(NodeService nodeService_) {
		nodeService = nodeService_;
	}

	public TenantService getTenantService() {
		return tenantService;
	}

	public void setTenantService(TenantService tenantService_) {
		tenantService = tenantService_;
	}

	
}
