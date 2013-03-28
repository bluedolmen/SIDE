/*
    Copyright (C) 2007-20013  BlueXML - www.bluexml.com

    This program is free software: you can redistribute it and/or modify
    it under the terms of the GNU Affero General Public License as
    published by the Free Software Foundation, either version 3 of the
    License, or (at your option) any later version.

    This program is distributed in the hope that it will be useful,
    but WITHOUT ANY WARRANTY; without even the implied warranty of
    MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
    GNU Affero General Public License for more details.

    You should have received a copy of the GNU Affero General Public License
    along with this program.  If not, see <http://www.gnu.org/licenses/>.
*/
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

	/*
	 * Used on boolean values, returning the SQL statement with appropriate format
	 */
	public String booleanFormat(String value);
}
