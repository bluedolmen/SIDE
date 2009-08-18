package com.bluexml.side.Integration.alfresco.sql.searcher;

import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;

import com.bluexml.side.Integration.alfresco.sql.synchronization.dictionary.BidirectionalDatabaseDictionary;

public class SearchParameters {
	
	enum JoinType {
		LEFT_JOIN
	}

	private static class JoinCondition {
		private String tableName;
		private String firstColumn;
		private String secondColumn;
		private JoinType joinType;
		
		public JoinCondition(String tableName_, String firstColumn_, String secondColumn_, JoinType joinType_) {
			tableName = tableName_;
			firstColumn = firstColumn_;
			secondColumn = secondColumn_;
			joinType = joinType_;
		}
	}
	
	private final String tableName;
	private final String alias;
	private final String condition;
	private final String path;
	private final List<JoinCondition> joinConditions;
	
	SearchParameters(Builder builder_) {
		tableName = builder_.tableName;
		alias = builder_.alias;
		condition = builder_.condition;
		path = builder_.restrictingPath;
		joinConditions = builder_.joinConditions;
	}
	
	public String getTableName() {
		return tableName;
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
	
	
	
	/*
	 * Builder
	 * Builder "pattern" is implemented as an inner-class in order to limit
	 * visibility of parameters and SearchParameter constructor. This pattern also avoids unwanted extensions
	 */
	
	private static class Builder {
		private Logger logger = Logger.getLogger(getClass());
		private static final String TYPE_TABLE_ID_COLUMN_NAME = "id";
		
		// Required parameters
		private final String tableName;
		
		// Optional parameters
		private String alias = null;
		private String condition = null;
		private String restrictingPath = null;
		private List<JoinCondition> joinConditions = new ArrayList<JoinCondition>();
		
		public Builder(String typeName_) {			
			String tableName = tagResolver.translate(typeName_);
			if (tableName == null) {
				throw new InvalidTypeException(typeName_);
			}
			this.tableName = tableName;
		}
		
		public Builder alias(String alias_) {
			alias = alias_;
			return this;
		}
		
		public Builder condition(String condition_) {
			condition = tagResolver.translate(condition_);
			return this;
		}
		
		public Builder restrictingPath(String restrictingPath_) {
			restrictingPath = restrictingPath_;
			throw new UnsupportedOperationException();
			//return this;
		}
		
		public Builder leftJoinType(String otherTypeName_, String associationName_) {
			String otherTableName = tagResolver.translate(otherTypeName_);
			if (otherTableName == null) {
				throw new InvalidTypeException(otherTypeName_);
			}
			String associationTableName = tagResolver.translate(associationName_);
			if (associationTableName == null) {
				throw new InvalidAssociationException(associationName_);
			}
			
			String fqAssociationName = databaseDictionary.resolveTableAsAssociationName(associationTableName); // fq : full-qualified
			
			logger.info("Join condition is made explicitely on column with name \"" + TYPE_TABLE_ID_COLUMN_NAME + "\"");
			joinConditions.add(new JoinCondition(associationTableName, TYPE_TABLE_ID_COLUMN_NAME, databaseDictionary.getSourceAlias(fqAssociationName), JoinType.LEFT_JOIN));
			joinConditions.add(new JoinCondition(otherTableName, databaseDictionary.getTargetAlias(fqAssociationName), TYPE_TABLE_ID_COLUMN_NAME, JoinType.LEFT_JOIN));
			
			return this;
		}
		
		public Builder join(String tableName_, String columnName_, String otherColumnName_, JoinType joinType_) {
			joinConditions.add(new JoinCondition(tableName_, columnName_, otherColumnName_, joinType_));
			return this;
		}
		
//			public Builder joinConditions(List<JoinCondition> joinConditions_) {
//				joinConditions = joinConditions_;
//				return this;
//			}
//			
//			public Builder joinCondition(JoinCondition joinCondition_) {
//				joinConditions.add(joinCondition_);
//				return this;
//			}
		
		public SearchParameters build() {
			return new SearchParameters(this);
		}
		
		/*
		 * Setters for static variables
		 */
		
		private static TagResolver tagResolver;
		private static BidirectionalDatabaseDictionary databaseDictionary;
		
		protected static void setTagResolver(TagResolver tagResolver_) {
			tagResolver = tagResolver_;
		}
		
		protected static void setDatabaseDictionary(BidirectionalDatabaseDictionary databaseDictionary_) {
			databaseDictionary = databaseDictionary_;
		}
		
	}
	
	public static class BuilderFactory {
		public Builder createInstance(String typeName_) {
			return new Builder(typeName_);
		}
		
		/*
		 * Spring IoC/DI
		 */
		
		public void setTagResolver(TagResolver tagResolver_) {
			Builder.setTagResolver(tagResolver_);
			
		}
		
		public void setDatabaseDictionary(BidirectionalDatabaseDictionary databaseDictionary_) {
			Builder.setDatabaseDictionary(databaseDictionary_);
		}
	}
}
