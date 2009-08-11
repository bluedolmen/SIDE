package com.bluexml.side.Integration.alfresco.sql.searcher;

import java.util.ArrayList;
import java.util.List;

public class SearchParameters {
	
	enum JoinType {
		LEFT_JOIN
	}

	public static class Builder  {
		
		// Required parameters
		private final String typeName;
		
		// Optional parameters
		private String alias = null;
		private String condition = null;
		private String restrictingPath = null;
		private List<JoinCondition> joinConditions = new ArrayList<JoinCondition>();
		
		public Builder(String typeName_) {
			typeName = typeName_;
		}
		
		public Builder alias(String alias_) {
			alias = alias_;
			return this;
		}
		
		public Builder condition(String condition_) {
			condition = condition_;
			return this;
		}
		
		public Builder restrictingPath(String restrictingPath_) {
			restrictingPath = restrictingPath_;
			throw new UnsupportedOperationException();
			//return this;
		}
		
		public Builder joinConditions(List<JoinCondition> joinConditions_) {
			joinConditions = joinConditions_;
			return this;
		}
		
		public Builder joinCondition(JoinCondition joinCondition_) {
			joinConditions.add(joinCondition_);
			return this;
		}
		
		public SearchParameters build() {
			return new SearchParameters(this);
		}
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
	
	private final String typeName;
	private final String alias;
	private final String condition;
	private final String path;
	private final List<JoinCondition> joinConditions;
	
	private SearchParameters(Builder builder_) {
		typeName = builder_.typeName;
		alias = builder_.alias;
		condition = builder_.condition;
		path = builder_.restrictingPath;
		joinConditions = builder_.joinConditions;
	}
	
	public String getTypeName() {
		return typeName;
	}
	
	public String getAlias() {
		return alias;
	}
	public boolean hasAlias() {
		return alias != null;
	}
	
	public String getCondition() {
		return condition;
	}
	public boolean hasCondition() {
		return condition != null;
	}
	
	public String getRestrictingPath() {
		return path;
	}
	public boolean hasRestrictingPath() {
		return path != null;
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
