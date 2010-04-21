package com.bluexml.side.Integration.alfresco.sql.synchronization.dialects;

import org.alfresco.service.cmr.dictionary.PropertyDefinition;
import org.alfresco.service.namespace.QName;

public interface SynchronizationDialect {

	/*
	 * Returns the SQL type representing the Alfresco property definition
	 * Property definition contains the data type as well as additional constraints
	 * which enable to restrict the sql types (e.g. VARCHAR)
	 */
	public String getSQLMapping(PropertyDefinition propertyDefinition);

	/*
	 * Returns the SQL type representing the Alfresco property definition
	 * This is the default behavior without the additional constraints 
	 */
	public String getSQLMapping(QName dataTypeName);
	
	/*
	 * Create a CreateStatement that is potentially customized for the considered dialect
	 */
	public CreateTableStatement.Builder newCreateTableStatementBuilder(String tableName);
	
	/*
	 * Returns the default length that should be applied on a X-Char (CHAR, VARCHAR)
	 * sql type if no constraint is given
	 */
	public Integer getXCharDefaultLength();
	
	/*
	 * Returns the SQL-escaped string from the original string
	 */
	public String escape(String input);
	
	/*
	 * Used on string values, returning the SQL statement with appropriate quotes
	 */
	public String quoteString(String input);
}
