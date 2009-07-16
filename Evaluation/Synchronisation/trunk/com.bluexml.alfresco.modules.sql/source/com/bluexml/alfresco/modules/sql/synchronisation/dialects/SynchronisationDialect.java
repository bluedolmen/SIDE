package com.bluexml.alfresco.modules.sql.synchronisation.dialects;

import org.alfresco.service.cmr.dictionary.PropertyDefinition;

public interface SynchronisationDialect {

	/*
	 * Returns the SQL type representing the Alfresco property definition
	 * Property definition contains the data type as well as additional constraints
	 * which enable to restrict the sql types (e.g. VARCHAR)
	 */
	public String getSQLMapping(PropertyDefinition propertyDefinition);
	
	/*
	 * Returns the default length that should be applied on a X-Char (CHAR, VARCHAR)
	 * sql type if no constraint is given
	 */
	public Integer getXCharDefaultLength();
	
}
