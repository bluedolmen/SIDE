package com.bluexml.alfresco.modules.sql.searcher;

import java.util.List;

public class SearchParameters {
	
	enum JoinType {
		LEFT_JOIN
	}
	
	public static class JoinCondition {
		private String tableName;
		private String firstColumn;
		private String secondColumn;
		private JoinType joinType;
		
		JoinCondition(String tableName_, String firstColumn_, String secondColumn_, JoinType joinType_) {
			tableName = tableName_;
			firstColumn = firstColumn_;
			secondColumn = secondColumn_;
			joinType = joinType_;
		}
	}
	
	private String typeName;
	private String alias;
	private String condition;
	private String path;
	private List<JoinCondition> joinConditions;
	
	public SearchParameters(String typeName_) {
		typeName = typeName_;
	}
	public String getTypeName() {
		return typeName;
	}
	
	public void setAlias(String alias_) {
		alias = alias_;
	}
	public String getAlias() {
		return alias;
	}
	public boolean hasAlias() {
		return alias != null;
	}
	
	public void setCondition(String condition_) {
		condition = condition_;
	}
	public String getCondition() {
		return condition;
	}
	public boolean hasCondition() {
		return condition != null;
	}
	
	public void setRestrictingPath(String path_) {
		path = path_;
		throw new UnsupportedOperationException();
	}
	public String getRestrictingPath() {
		return path;
	}
	public boolean hasRestrictingPath() {
		return path != null;
	}
	
	public void setJoinCondition(List<JoinCondition> joinConditions_) {
		joinConditions = joinConditions_;
	}
	public String getJoinCondition() {
		StringBuilder jc = new StringBuilder();
		for (JoinCondition joinCondition : joinConditions) {
			String joinOperator = null;
			switch (joinCondition.joinType) {
			case LEFT_JOIN: 
				joinOperator = "LEFT JOIN";
				break;
			default:
				throw new UnsupportedOperationException();	
			}
			jc.append(joinOperator);
			jc.append(" ");
			jc.append(joinCondition.tableName);
			jc.append(" ON ");
			jc.append(joinCondition.firstColumn);
			jc.append(" = ");
			jc.append(joinCondition.secondColumn);
			jc.append(" ");
		}
		return jc.toString();
	}
	public boolean hasJoinCondition() {
		return (joinConditions != null && !joinConditions.isEmpty());
	}
}
