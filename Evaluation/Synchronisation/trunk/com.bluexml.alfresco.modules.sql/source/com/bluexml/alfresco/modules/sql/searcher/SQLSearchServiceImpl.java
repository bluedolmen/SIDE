package com.bluexml.alfresco.modules.sql.searcher;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Collection;
import java.util.List;

import org.alfresco.service.cmr.repository.NodeRef;
import org.alfresco.service.cmr.repository.StoreRef;
import org.springframework.jdbc.core.ColumnMapRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;

public class SQLSearchServiceImpl implements SQLSearchService {
	
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
	
//	public Collection<NodeRef> query(String typeName) {
//		Collection<NodeRef> result = null;
//		
//		String query_ = "SELECT * FROM " + typeName;
//		try {
//			result = databaseQuery.getResultAsNodeRefs(query_);
//		} catch (Exception e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//		
//		return result;
//	}
		
	@SuppressWarnings("unchecked")
	public Collection<NodeRef> selectNodes(String typeName, String condition) {
		String sqlQuery = String.format("SELECT id, uuid FROM %1$s WHERE %2$s",typeName, condition);
		List<NodeRef> result = jdbcTemplate.query(sqlQuery, 
				new ColumnMapRowMapper() {
					public Object mapRow(ResultSet rs, int i) throws SQLException {
						return new NodeRef(StoreRef.STORE_REF_WORKSPACE_SPACESSTORE, rs.getString(2));
					}
		});
		
		return result;
	}

	
	// SPRING BEAN MANAGEMENT

//	private DatabaseQuery databaseQuery;
//	private NodeService nodeService;
	private JdbcTemplate jdbcTemplate;
	
	public void setJdbcTemplate(JdbcTemplate jdbcTemplate_) {
		jdbcTemplate = jdbcTemplate_;
	}

//	public DatabaseQuery getDatabaseQuery() {
//		return databaseQuery;
//	}
//	
//	public void setDatabaseQuery(DatabaseQuery databaseQuery_) {
//		databaseQuery = databaseQuery_;
//	}
//
//	public NodeService getNodeService() {
//		return nodeService;
//	}
//
//	public void setNodeService(NodeService nodeService_) {
//		nodeService = nodeService_;
//	}
//
//	public TenantService getTenantService() {
//		return tenantService;
//	}
//
//	public void setTenantService(TenantService tenantService_) {
//		tenantService = tenantService_;
//	}


	
}
