package com.bluexml.side.Integration.alfresco.sql.searcher;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Collection;
import java.util.List;

import org.alfresco.service.cmr.repository.NodeRef;
import org.alfresco.service.cmr.repository.StoreRef;
import org.apache.log4j.Logger;
import org.springframework.jdbc.core.ColumnMapRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;

import com.bluexml.side.Integration.alfresco.sql.searcher.SearchQuery.JoinCondition;


public class SQLSearchServiceImpl implements SQLSearchService {
	/** The logger. */
	private Logger logger = Logger.getLogger(getClass());
	
	private static String TRUE_SQL_STATEMENT = "TRUE";
	
	public Collection<NodeRef> selectNodes(String typeName, String condition) {
		if (condition == null) {
			condition = TRUE_SQL_STATEMENT;
		}
		
		String tableName = tagResolver.translate(typeName); 
//			databaseDictionary.resolveClassAsTableName(typeName);	
//		if (tableName == null) {
//			throw new InvalidTypeException(typeName);
//		}
		
		String sqlQuery = String.format("SELECT uuid FROM %1$s WHERE %2$s", tableName, condition);
		return executeQuery(sqlQuery);
	}

	// TODO : check whether AS is not a mysql dialect (else remove it or use the dialect interface to provide a generic statement)
	public Collection<NodeRef> selectNodes(SearchQuery searchQuery_) {
		StringBuilder sqlQuery = new StringBuilder();
		String tableName = searchQuery_.getTableName(); 
		
		sqlQuery.append("SELECT ");
		if (searchQuery_.hasAlias()) {
			sqlQuery.append(searchQuery_.getAlias());
		} else {
			sqlQuery.append(tableName);
		}
		sqlQuery.append(".uuid FROM ");
		sqlQuery.append(tableName);
		
		if (searchQuery_.hasAlias()) {
			sqlQuery.append(" AS ");
			sqlQuery.append(searchQuery_.getAlias());
			sqlQuery.append(" ");
		}
		
		if (searchQuery_.hasJoinConditions()) {
			StringBuilder jc = new StringBuilder();
			for (JoinCondition joinCondition : searchQuery_.getJoinConditions()) {
				String joinOperator = null;
				switch (joinCondition.getJoinType()) {
				case LEFT_JOIN: 
					joinOperator = "LEFT JOIN";
					break;
				default:
					throw new UnsupportedOperationException();	
				}
				jc.append(joinOperator);
				jc.append(" ");
				jc.append(joinCondition.getTableName());
				jc.append(" AS ");
				jc.append(joinCondition.getAlias());
				jc.append(" ON ");
				if (joinCondition.getAlias() != null) {
					jc.append(joinCondition.getAlias());
					jc.append(".");
				}
				jc.append(joinCondition.getOwnColumn());
				jc.append(" = ");
				if (joinCondition.getForeignTableName() != null) {
					jc.append(joinCondition.getForeignTableName());
					jc.append(".");
				}
				jc.append(joinCondition.getForeignColumn());
				jc.append(" ");
			}

			sqlQuery.append(" ");
			sqlQuery.append(jc);
			sqlQuery.append(" ");
		}

		if (searchQuery_.hasCondition() || searchQuery_.hasRestrictingPath()) {
			sqlQuery.append(" WHERE ");
		}
		
		if (searchQuery_.hasCondition()) {
			sqlQuery.append(searchQuery_.getCondition());
		}
		
		if (searchQuery_.hasCondition() && searchQuery_.hasRestrictingPath()) {
			sqlQuery.append(" AND ");
		}
		
		if (searchQuery_.hasRestrictingPath()) {
			sqlQuery.append(searchQuery_.getRestrictingPath());
		}

		return executeQuery(sqlQuery.toString());
	}

	/*
	 * Helper methods
	 */
	@SuppressWarnings("unchecked")
	private Collection<NodeRef> executeQuery (String sqlQuery) {
		if (logger.isDebugEnabled())
			logger.debug("Executing SQL query \"" + sqlQuery + "\"");
		List<NodeRef> result = jdbcTemplate.query(sqlQuery, 
				new ColumnMapRowMapper() {
					public Object mapRow(ResultSet rs, int i) throws SQLException {
						return new NodeRef(StoreRef.STORE_REF_WORKSPACE_SPACESSTORE, rs.getString(1));
					}
				}
			);
		
		return result;		
	}
	
	/*
	 * Spring IoC/DI material
	 */

	private JdbcTemplate jdbcTemplate;
	private TagResolver tagResolver;
	
	public void setJdbcTemplate(JdbcTemplate jdbcTemplate_) {
		jdbcTemplate = jdbcTemplate_;
	}

	public void setTagResolver(TagResolver tagResolver_) {
		tagResolver = tagResolver_;
	}
	
}
