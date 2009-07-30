package com.bluexml.side.Integration.alfresco.sql.searcher;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Collection;
import java.util.List;

import org.alfresco.service.cmr.repository.NodeRef;
import org.alfresco.service.cmr.repository.StoreRef;
import org.springframework.jdbc.core.ColumnMapRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;

public class SQLSearchServiceImpl implements SQLSearchService {
	private static String TRUE_SQL_STATEMENT = "TRUE";
	
	public Collection<NodeRef> selectNodes(String typeName, String condition) {
		if (condition == null) {
			condition = TRUE_SQL_STATEMENT;
		}
		String sqlQuery = String.format("SELECT uuid FROM %1$s WHERE %2$s",typeName, condition);
		return executeQuery(sqlQuery);
	}

	public Collection<NodeRef> selectNodes(SearchParameters searchParameters) {
		StringBuilder sqlQuery = new StringBuilder();
		
		sqlQuery.append("SELECT uuid FROM ");
		sqlQuery.append(searchParameters.getTypeName());
		
		if (searchParameters.hasAlias()) {
			sqlQuery.append(" ");
			sqlQuery.append(searchParameters.getAlias());
			sqlQuery.append(" ");
		}
		
		if (searchParameters.hasJoinCondition()) {
			sqlQuery.append(" ");
			sqlQuery.append(searchParameters.getJoinCondition());
			sqlQuery.append(" ");
		}

		if (searchParameters.hasCondition() || searchParameters.hasRestrictingPath()) {
			sqlQuery.append(" WHERE ");
		}
		
		if (searchParameters.hasCondition()) {
			sqlQuery.append(searchParameters.getCondition());
		}
		
		if (searchParameters.hasCondition() && searchParameters.hasRestrictingPath()) {
			sqlQuery.append(" AND ");
		}
		
		if (searchParameters.hasRestrictingPath()) {
			sqlQuery.append(searchParameters.getRestrictingPath());
		}

		return executeQuery(sqlQuery.toString());
	}

	/*
	 * Helper methods
	 */
	@SuppressWarnings("unchecked")
	private Collection<NodeRef> executeQuery (String sqlQuery) {
		List<NodeRef> result = jdbcTemplate.query(sqlQuery, 
				new ColumnMapRowMapper() {
					public Object mapRow(ResultSet rs, int i) throws SQLException {
						return new NodeRef(StoreRef.STORE_REF_WORKSPACE_SPACESSTORE, rs.getString(1));
					}
		});
		
		return result;		
	}
	
	/*
	 * Spring IoC/DI material
	 */

	private JdbcTemplate jdbcTemplate;
	
	public void setJdbcTemplate(JdbcTemplate jdbcTemplate_) {
		jdbcTemplate = jdbcTemplate_;
	}


	
}
