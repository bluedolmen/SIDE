package com.bluexml.side.Integration.alfresco.sql.searcher;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.alfresco.error.AlfrescoRuntimeException;
import org.alfresco.service.cmr.dictionary.AssociationDefinition;
import org.alfresco.service.cmr.dictionary.ClassDefinition;
import org.alfresco.service.cmr.dictionary.DictionaryService;
import org.alfresco.service.cmr.dictionary.TypeDefinition;
import org.alfresco.service.namespace.QName;
import org.apache.log4j.Logger;

import com.bluexml.side.Integration.alfresco.sql.synchronization.dictionary.BidirectionalDatabaseDictionary;

@Deprecated
public class SearchParameters {
	
	enum JoinType {
		LEFT_JOIN
	}

	public static class JoinCondition {
		private String tableName;
		private String alias;
		private String ownColumn;
		private String foreignTableName;
		private String foreignColumn;
		private JoinType joinType;
		
		public JoinCondition(String tableName_, String alias_, String ownColumn_, String foreignTableName_, String foreignColumn_, JoinType joinType_) {
			tableName = tableName_;
			alias = alias_;
			ownColumn = ownColumn_;
			foreignTableName = foreignTableName_;
			foreignColumn = foreignColumn_;
			joinType = joinType_;
		}
		
		public String getTableName () {
			return tableName;
		}
		
		public String getAlias() {
			return alias;
		}
		
		public String getOwnColumn() {
			return ownColumn;
		}

		public String getForeignTableName() {
			return foreignTableName;
		}
		
		public String getForeignColumn() {
			return foreignColumn;
		}
		
		public JoinType getJoinType() {
			return joinType;
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
	
	public List<JoinCondition> getJoinConditions() {
		return Collections.unmodifiableList(joinConditions);
	}
	
	public boolean hasJoinConditions() {
		return (joinConditions != null && !joinConditions.isEmpty());
	}
	
	
	
	/*
	 * Builder
	 * Builder "pattern" is implemented as an inner-class in order to limit
	 * visibility of parameters and SearchParameter constructor. This pattern also avoids unwanted extensions
	 */
	
	public static class Builder {
		private Logger logger = Logger.getLogger(getClass());
		private static final String TYPE_TABLE_ID_COLUMN_NAME = "id";
		private static final String ALIAS_PREFIX = "TABLE";
		
		// Required parameters
		private final String tableName;
		private final String typeName; // temporary
		private final QName typeQName; // temporary
		private final String namespaceURI; // temporary
		
		// Optional parameters
		private String alias = null;
		private String condition = null;
		private String restrictingPath = null;
		private List<JoinCondition> joinConditions = new ArrayList<JoinCondition>();

		// counter
		private int aliasId = 0;
		// A map of <String, String> that store for each joined table the corresponding alias
		Map<String, String> joinedTables = new HashMap<String, String>();
		
		public String getNewAlias() {
			return ALIAS_PREFIX + aliasId++;
		}
		
		public Builder(String typeName_) {			
			//String tableName = tagResolver.translate(typeName_);
			String tableName = databaseDictionary.resolveClassAsTableName(typeName_);
			if (tableName == null) {
				throw new InvalidTypeException(typeName_);
			}

			Collection<QName> allTypes = dictionaryService.getAllTypes();
			List<QName> correspondingTypes = new ArrayList<QName>();
			for (QName type : allTypes) {
				if (type.getLocalName().equals(typeName_)) {
					correspondingTypes.add(type); 
				}
			}
			if (correspondingTypes.size() == 0) {
				throw new AlfrescoRuntimeException("Cannot find type \"" + typeName_ + "\" in Alfresco types");				
			}
			if (correspondingTypes.size() > 1) {
				throw new AlfrescoRuntimeException("Type \"" + typeName_ + "\" has several corresponding Alfresco types");
			}
			
			typeQName = correspondingTypes.get(0);
			namespaceURI = typeQName.getNamespaceURI();
			this.tableName = tableName;
			this.typeName = typeName_;
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
		
		
		/*
		 * Follow a path of multiple associations
		 */
//		public Builder leftJoin(List<String> associationNames_) {
//			QName previousTypeQName = typeQName;
//			for (String associationName : associationNames_) {
//				QName targetTypeQName = getTypeQNameThroughAssociation(previousTypeQName, QName.createQName(namespaceURI,associationName));
//				leftJoinType(targetTypeQName.getLocalName(), associationName);
//				previousTypeQName = targetTypeQName;
//			}
//			return this;
//		}
		
		public Builder leftJoin(String associationName_) {
			AssociationDefinition associationDefinition = dictionaryService.getAssociation(QName.createQName(namespaceURI, associationName_));
			if (associationDefinition == null) {
				throw new InvalidAssociationException(associationName_);
			}
			
			QName associationQName = associationDefinition.getName();
			QName sourceClassQName = associationDefinition.getSourceClass().getName();
			QName targetClassQName = associationDefinition.getTargetClass().getName();
			
			String sourceTableName = databaseDictionary.resolveClassAsTableName(sourceClassQName.getLocalName());
			String targetTableName = databaseDictionary.resolveClassAsTableName(targetClassQName.getLocalName());
			String associationTableName = databaseDictionary.resolveAssociationAsTableName(associationQName.getLocalName());
						
			if (logger.isInfoEnabled())
				logger.info("Join condition is made explicitely on column with name \"" + TYPE_TABLE_ID_COLUMN_NAME + "\"");
			
			// TODO : CHECK !!!
			join(associationTableName, TYPE_TABLE_ID_COLUMN_NAME, sourceTableName, databaseDictionary.getSourceAlias(associationQName.getLocalName()), JoinType.LEFT_JOIN);
//			join(otherTableName, databaseDictionary.getTargetAlias(fqAssociationName), TYPE_TABLE_ID_COLUMN_NAME, JoinType.LEFT_JOIN);

			// Here we suppose that the association namespace URI is the same has the enclosing type
			QName otherTypeQName = getTypeQNameThroughAssociation(typeQName, QName.createQName(namespaceURI, associationName_));
			
			return leftJoinType(otherTypeQName.getLocalName(), associationName_);
		}
		
		public Builder leftJoinType(String otherTypeName_, String associationName_) {
			//String otherTableName = tagResolver.translate(otherTypeName_);
			String otherTableName = databaseDictionary.resolveClassAsTableName(otherTypeName_);
			if (otherTableName == null) {
				throw new InvalidTypeException(otherTypeName_);
			}
			//String associationTableName = tagResolver.translate(associationName_);
			String associationTableName = databaseDictionary.resolveAssociationAsTableName(associationName_);
			if (associationTableName == null) {
				throw new InvalidAssociationException(associationName_);
			}
			
			//String fqAssociationName = databaseDictionary.resolveTableAsAssociationName(associationTableName); // fq : full-qualified
			String fqAssociationName = associationName_;
			
			if (logger.isInfoEnabled())
				logger.info("Join condition is made explicitely on column with name \"" + TYPE_TABLE_ID_COLUMN_NAME + "\"");
//			join(associationTableName, TYPE_TABLE_ID_COLUMN_NAME, databaseDictionary.getSourceAlias(fqAssociationName), JoinType.LEFT_JOIN);
//			join(otherTableName, databaseDictionary.getTargetAlias(fqAssociationName), TYPE_TABLE_ID_COLUMN_NAME, JoinType.LEFT_JOIN);
			
			return this;
		}
		
		private void join(String tableName_, String columnName_, String otherTableName_, String otherColumnName_, JoinType joinType_) {
			if (otherTableName_ == null) {
				 // If the other table name is not given, then we suppose that this join refers to the current type
				otherTableName_ = typeName;
			}
			
			if (joinedTables.containsKey(tableName_)) {
				throw new AlfrescoRuntimeException("Table \"" + tableName_ + "\" is already joined");
			} else {
				joinedTables.put(tableName_, getNewAlias());
			}
			
			if (joinedTables.containsKey(otherTableName_) && joinedTables.get(otherTableName_) != null) {
				// the other table name has an alias, then use it instead
				otherTableName_ = joinedTables.get(otherTableName_);
			}
			
			joinConditions.add(new JoinCondition(tableName_, joinedTables.get(tableName_), columnName_, otherTableName_, otherColumnName_, joinType_));
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
		 * Helper methods
		 */
		private QName getTypeQNameThroughAssociation(QName typeQName_, QName associationQName_) {
			QName otherTypeQName = null;
			TypeDefinition typeDefinition = dictionaryService.getType(typeQName_);
			Map<QName, AssociationDefinition> associationDefinitions = new HashMap<QName, AssociationDefinition>();
			associationDefinitions.putAll(typeDefinition.getAssociations());
			associationDefinitions.putAll(typeDefinition.getChildAssociations());
			
			if (associationDefinitions.containsKey(associationQName_)) {
				AssociationDefinition associationDefinition = associationDefinitions.get(associationQName_);
				ClassDefinition targetClassDefinition = associationDefinition.getTargetClass();
				if (targetClassDefinition.isAspect()) {
					throw new AlfrescoRuntimeException("Cannot support associations to aspects");
				} else {
					otherTypeQName = targetClassDefinition.getName();
				}
			} else {
				throw new                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                  AlfrescoRuntimeException("Cannot find a (peer or child) association with name \"" + associationQName_.getLocalName() + "\" in type of name \"" + typeQName_.getLocalName() + "\"");
			}

			return otherTypeQName;
		}
		
		/*
		 * Setters for static variables
		 */
		
		private static TagResolver tagResolver;
		private static BidirectionalDatabaseDictionary databaseDictionary;
		private static DictionaryService dictionaryService;
		
		protected static void setTagResolver(TagResolver tagResolver_) {
			tagResolver = tagResolver_;
		}
		
		protected static void setDatabaseDictionary(BidirectionalDatabaseDictionary databaseDictionary_) {
			databaseDictionary = databaseDictionary_;
		}
		
		protected static void setDictionaryService(DictionaryService dictionaryService_) {
			dictionaryService = dictionaryService_;
		}
	}
	
	public static class BuilderFactory {
		public static Builder createInstance(String typeName_) {
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
