package com.bluexml.alfresco.modules.sql.synchronisation.dialects;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.alfresco.repo.dictionary.constraint.StringLengthConstraint;
import org.alfresco.service.cmr.dictionary.ConstraintDefinition;
import org.alfresco.service.cmr.dictionary.DataTypeDefinition;
import org.alfresco.service.cmr.dictionary.PropertyDefinition;
import org.alfresco.service.namespace.QName;
import org.apache.log4j.Logger;

public class DefaultDialect implements SynchronisationDialect {
	
	private static final Logger logger = Logger.getLogger(DefaultDialect.class);
	private static final String STRING_LENGTH_CONSTRAINT_IDENTIFIER = "LENGTH";
	private static final Integer MAX_CHAR_LENGTH = 255;

	private static Map<QName, String> sqlTypeMapping = new HashMap<QName, String>() {

		private static final long serialVersionUID = 1L;

		{
			put(DataTypeDefinition.ANY, "BLOB"); //java.sql.Types.JAVA_OBJECT);
			put(DataTypeDefinition.BOOLEAN, "BOOLEAN"); //java.sql.Types.BOOLEAN);
			put(DataTypeDefinition.CONTENT, "BLOB"); //java.sql.Types.BLOB);
			put(DataTypeDefinition.DATE, "DATE"); //java.sql.Types.DATE);
			put(DataTypeDefinition.DATETIME, "TIMESTAMP"); //java.sql.Types.TIMESTAMP);
			put(DataTypeDefinition.DOUBLE, "DOUBLE"); //java.sql.Types.DOUBLE);
			put(DataTypeDefinition.FLOAT, "FLOAT"); //java.sql.Types.FLOAT);
			put(DataTypeDefinition.INT, "INTEGER"); //java.sql.Types.INTEGER);
			put(DataTypeDefinition.LONG, "INTEGER"); //java.sql.Types.INTEGER);
			put(DataTypeDefinition.PATH, "VARCHAR"); //java.sql.Types.VARCHAR);
			put(DataTypeDefinition.TEXT, "VARCHAR"); //java.sql.Types.VARCHAR);			
		}
	};

	
	public String getSQLMapping(PropertyDefinition propertyDefinition) {
		DataTypeDefinition dataTypeDefinition = propertyDefinition.getDataType();
		QName dataTypeName = dataTypeDefinition.getName();
		String result = "UNSPECIFIED";

		if (!sqlTypeMapping.containsKey(dataTypeName)) {
			logger.error("Do not know how to map type \"" + dataTypeName + "\" to SQL");
		} else {
			result = sqlTypeMapping.get(dataTypeName);
			
			if (result.endsWith("CHAR")) {
				Integer maxLength = getMaxLength(propertyDefinition);
				result += "(" + maxLength + ")";
			}
		}

		return result;
		
	}

	public Integer getXCharDefaultLength() {
		return MAX_CHAR_LENGTH;
	}
	
	/*
	 * Helper functions
	 */
	
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



	
}
