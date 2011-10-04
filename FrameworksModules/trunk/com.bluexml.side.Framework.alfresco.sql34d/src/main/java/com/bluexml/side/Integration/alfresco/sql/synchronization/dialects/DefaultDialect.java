package com.bluexml.side.Integration.alfresco.sql.synchronization.dialects;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.alfresco.repo.dictionary.constraint.StringLengthConstraint;
import org.alfresco.service.cmr.dictionary.ConstraintDefinition;
import org.alfresco.service.cmr.dictionary.DataTypeDefinition;
import org.alfresco.service.cmr.dictionary.PropertyDefinition;
import org.alfresco.service.namespace.QName;
import org.apache.log4j.Logger;

@SuppressWarnings("serial")
public class DefaultDialect implements SynchronizationDialect {
	
	private static final Logger logger = Logger.getLogger(DefaultDialect.class);
	private static final String STRING_LENGTH_CONSTRAINT_IDENTIFIER = "LENGTH";
	private static final String UNSPECIFIED_TYPE_NAME = "UNSPECIFIED";
	private static final Integer MAX_CHAR_LENGTH = 255;

	private static Map<QName, String> sqlTypeMapping = new HashMap<QName, String>() {

		{
			put(DataTypeDefinition.ANY, "BLOB"); 
			put(DataTypeDefinition.BOOLEAN, "BOOLEAN"); 
			put(DataTypeDefinition.CONTENT, "BLOB"); 
			put(DataTypeDefinition.DATE, "DATE"); 
			put(DataTypeDefinition.DATETIME, "TIMESTAMP"); 
			put(DataTypeDefinition.DOUBLE, "DOUBLE");
			put(DataTypeDefinition.FLOAT, "FLOAT");
			put(DataTypeDefinition.INT, "INTEGER");
			put(DataTypeDefinition.LONG, "INTEGER");
			put(DataTypeDefinition.PATH, "VARCHAR(512)");
			put(DataTypeDefinition.TEXT, "VARCHAR");	
		}
	};

	
	public String getSQLMapping(PropertyDefinition propertyDefinition) {
		DataTypeDefinition dataTypeDefinition = propertyDefinition.getDataType();
		QName dataTypeName = dataTypeDefinition.getName();
		String result = UNSPECIFIED_TYPE_NAME;
		
		if (!sqlTypeMapping.containsKey(dataTypeName)) {
			logger.error("Do not know how to map type \"" + dataTypeName + "\" to SQL");
		} else {
			result = decorateCharType(sqlTypeMapping.get(dataTypeName), getMaxLength(propertyDefinition));
		}

		return result;
	}

	public String getSQLMapping(QName dataTypeName) {
		String result = "UNSPECIFIED";
		
		if (!sqlTypeMapping.containsKey(dataTypeName)) {
			logger.error("Do not know how to map type \"" + dataTypeName + "\" to SQL");
		} else {
			result = decorateCharType(sqlTypeMapping.get(dataTypeName), getXCharDefaultLength());
		}

		return result;
	}

	
	public Integer getXCharDefaultLength() {
		return MAX_CHAR_LENGTH;
	}
	
	/*
	 * Helper functions
	 */
	
	private String decorateCharType(String type, Integer charLength) {
		if (type.endsWith("CHAR")) {
			Integer maxLength = getXCharDefaultLength();
			type += "(" + maxLength + ")";
		}
		return type;
		
	}
	
	private Integer getMaxLength(PropertyDefinition propertyDefinition) {
		Integer result = getXCharDefaultLength();
		List<ConstraintDefinition> constraints = propertyDefinition.getConstraints();
		
		for (ConstraintDefinition constraint : constraints) {
			String constraintType = constraint.getConstraint().getType();
			if (STRING_LENGTH_CONSTRAINT_IDENTIFIER == constraintType) {
				StringLengthConstraint slConstraint = (StringLengthConstraint) constraint.getConstraint();
				result = slConstraint.getMaxLength();
				break;
			}
		}
		return result;
	}

	public String escape(String input) {
		/*
		 * Currently only replace single quote by doubled single quote
		 */
		return input.replaceAll("'", "''");
	}

	public String quoteString(String input) {
		// TODO Auto-generated method stub
		return "'" + input + "'";
	}

	public CreateTableStatement.Builder newCreateTableStatementBuilder(String tableName_) {
		return new CreateTableStatement.Builder(tableName_);
	}



	
}
