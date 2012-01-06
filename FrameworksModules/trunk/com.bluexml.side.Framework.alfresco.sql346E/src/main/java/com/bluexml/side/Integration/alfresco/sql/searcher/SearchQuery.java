package com.bluexml.side.Integration.alfresco.sql.searcher;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.alfresco.error.AlfrescoRuntimeException;
import org.alfresco.service.cmr.dictionary.AssociationDefinition;
import org.alfresco.service.cmr.dictionary.DictionaryService;
import org.alfresco.service.namespace.QName;
import org.apache.log4j.Logger;

import com.bluexml.side.Integration.alfresco.sql.synchronization.dictionary.DatabaseDictionary;

public class SearchQuery {

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
	
	SearchQuery(Builder builder_) {
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
		private static final String PROPERTY_ACCESSOR_OPERATOR = "."; // regular expression
		private static final String PROPERTY_ACCESSOR_OPERATOR_REGEX = "\\."; // regular expression
		private static final String ASSOCIATION_ACCESSOR_OPERATOR = "->";
		private static final String ASSOCIATION_ACCESSOR_OPERATOR_REGEX = "->";
		private static final String SQL_PROPERTY_SEPARATOR = "."; // TODO : Move this parameter in SqlDialect ?
		
		private static final String TYPE_TABLE_ID_COLUMN_NAME = "id";
		private static final String ALIAS_PREFIX = "TABLE";
		private static final Pattern pAlias = Pattern.compile(ALIAS_PREFIX + "[0-9]+");
		private static final Pattern pTag = Pattern.compile("\\{~([^~]+)~\\}");

		
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
			Matcher matcher = pAlias.matcher(alias_);
			if (matcher.matches()) {
				throw new AlfrescoRuntimeException("The provided alias may conflict with default ones given to joined tables, please avoid prefix \"TABLE[0-9]+\"");
			}
			alias = alias_;
			return this;
		}
		
		public Builder condition(String condition_) {
			
			Matcher matcher = pTag.matcher(condition_);
			StringBuffer result = new StringBuffer();
			
			 while (matcher.find()) {
				 String propertyName = matcher.group(1);
				 String fqColumnName = translateProperty(propertyName);
			     matcher.appendReplacement(result, fqColumnName);
			 }
			 matcher.appendTail(result);
			
			 condition = result.toString();
			 
			return this;
		}
		
		
		public Builder restrictingPath(String restrictingPath_) {
			restrictingPath = restrictingPath_;
			throw new UnsupportedOperationException();
			//return this;
		}
		
				
		public SearchQuery build() {
			return new SearchQuery(this);
		}
		
		/*
		 * Helper methods
		 */
		
		/*
		 * Joins sequentially the list of associations in parameter
		 * Returns the final "pointed" type
		 * 
		 */
		private String followPath(List<String> path_) {
			String result = null;
			for (String part : path_) {
				result = leftJoin(part);
			}
			return result;
		}
		
		// TODO : Replace contains by find to remove the double declaration of OPERATOR/OPERATOR_REGEX ?!
		private String translateProperty(String propertyString_) {
			String result = null;
			if (propertyString_.contains(PROPERTY_ACCESSOR_OPERATOR)) {
				// follow associations
				assert(propertyString_.contains(ASSOCIATION_ACCESSOR_OPERATOR) && propertyString_.split(PROPERTY_ACCESSOR_OPERATOR_REGEX).length == 2);
				List<String> propertySeparated = Arrays.asList(propertyString_.split(PROPERTY_ACCESSOR_OPERATOR_REGEX));
				List<String> associationSeparated = Arrays.asList(propertySeparated.get(0).split(ASSOCIATION_ACCESSOR_OPERATOR_REGEX));
				String targetFinalType = followPath(associationSeparated);
				result = 
					joinedTables.get(databaseDictionary.resolveClassAsTableName(targetFinalType)) + SQL_PROPERTY_SEPARATOR + 
					databaseDictionary.resolveAttributeAsColumnName(propertySeparated.get(1), targetFinalType);
			} else {
				assert(! propertyString_.contains(ASSOCIATION_ACCESSOR_OPERATOR));
				// Manage the property directly
				result = tableName + SQL_PROPERTY_SEPARATOR + databaseDictionary.resolveAttributeAsColumnName(propertyString_, typeName);
			}
			return result;
		}
		
//		private static QName getTypeQNameThroughAssociation(QName typeQName_, QName associationQName_) {
//			QName otherTypeQName = null;
//			TypeDefinition typeDefinition = dictionaryService.getType(typeQName_);
//			Map<QName, AssociationDefinition> associationDefinitions = new HashMap<QName, AssociationDefinition>();
//			associationDefinitions.putAll(typeDefinition.getAssociations());
//			associationDefinitions.putAll(typeDefinition.getChildAssociations());
//			
//			if (associationDefinitions.containsKey(associationQName_)) {
//				AssociationDefinition associationDefinition = associationDefinitions.get(associationQName_);
//				ClassDefinition targetClassDefinition = associationDefinition.getTargetClass();
//				if (targetClassDefinition.isAspect()) {
//					throw new AlfrescoRuntimeException("Cannot support associations to aspects");
//				} else {
//					otherTypeQName = targetClassDefinition.getName();
//				}
//			} else {
//				throw new                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                  AlfrescoRuntimeException("Cannot find a (peer or child) association with name \"" + associationQName_.getLocalName() + "\" in type of name \"" + typeQName_.getLocalName() + "\"");
//			}
//
//			return otherTypeQName;
//		}

		/*
		 * Performs join of the tables corresponding to the association and to the target type
		 * Returns the local name of the target type of the association
		 */
		private String leftJoin(String associationName_) {
			AssociationDefinition associationDefinition = dictionaryService.getAssociation(QName.createQName(namespaceURI, associationName_));
			if (associationDefinition == null) {
				throw new InvalidAssociationException(associationName_);
			}
			
			QName sourceClassQName = associationDefinition.getSourceClass().getName();
			QName targetClassQName = associationDefinition.getTargetClass().getName();
						
			String sourceTableName = databaseDictionary.resolveClassAsTableName(sourceClassQName.getLocalName());
			String targetTableName = databaseDictionary.resolveClassAsTableName(targetClassQName.getLocalName());
			String associationTableName = databaseDictionary.resolveAssociationAsTableName(associationName_);
									
			// Check whether the association table was already taken into account
			if (! joinedTables.containsKey(associationTableName)) {
				// Check whether the join has been previously made on source or target tables
				if (! typeName.equals(sourceClassQName.getLocalName()) && ! joinedTables.containsKey(sourceTableName)) {
					// There is a problem, an association can be added only if the source type was previously joined (as a table)
					throw new AlfrescoRuntimeException("Cannot join the association \"" + associationName_ + "\" since source type \"" + sourceClassQName.getLocalName() + "\" has not yet been joined.");
				} else {
					if (logger.isDebugEnabled())
						logger.debug("Join condition is made explicitely on column with name \"" + TYPE_TABLE_ID_COLUMN_NAME + "\"");

					// the source type name is the "master" type name or has already been defined previously as a left join
					join(
							associationTableName, 
							databaseDictionary.getSourceAlias(associationName_),
							sourceTableName,
							TYPE_TABLE_ID_COLUMN_NAME,
							JoinType.LEFT_JOIN
						);
					join(
							targetTableName,
							TYPE_TABLE_ID_COLUMN_NAME,
							associationTableName,
							databaseDictionary.getTargetAlias(associationName_),
							JoinType.LEFT_JOIN
						);
				}
				
			}

			return targetClassQName.getLocalName();
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

		/*
		 * Setters for static variables
		 */
		
		private static DatabaseDictionary databaseDictionary;
		private static DictionaryService dictionaryService;
		
		protected static void setDatabaseDictionary(DatabaseDictionary databaseDictionary_) {
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
		
		public void setDatabaseDictionary(DatabaseDictionary databaseDictionary_) {
			Builder.setDatabaseDictionary(databaseDictionary_);
		}
		
		public void setDictionaryService(DictionaryService dictionaryService_) {
			Builder.setDictionaryService(dictionaryService_);
		}
	}
	
}
