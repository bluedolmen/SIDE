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
package com.bluexml.side.Integration.alfresco.sql.searcher;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.bluexml.side.Integration.alfresco.sql.synchronization.dictionary.DatabaseDictionary;
 
public class AtSignTagResolverImpl implements TagResolver {
	final Pattern pTag = Pattern.compile("@.?\\{.+\\}");
	final Pattern pType = Pattern.compile("@T\\{(.+)\\}"); 
	final Pattern pProperty = Pattern.compile("@P\\{(.+,.+)\\}"); 
	final Pattern pAssociation = Pattern.compile("@A\\{(.+)\\}"); 

	public String translate(String input) {
		//@T{ } @A{} @P{}
		
		String result = input;
		
		if (pTag.matcher(input).matches()) {
			// Only process transformation if a tag can be found
			result = translateProperty(result);
			result = translateTypes(result);
			result = translateAssociation(result);
		}
		
		return result;
	}
	
	/*
	 * Helper methods
	 */
	private String translateTypes(String input) {
		Matcher matcher = pType.matcher(input);
		StringBuffer result = new StringBuffer();
		
		 while (matcher.find()) {
			 String typeName = matcher.group(1);
			 String tableName = databaseDictionary.resolveClassAsTableName(typeName);
		     matcher.appendReplacement(result, tableName);
		 }
		 matcher.appendTail(result);

		 return result.toString();
	}
	
	private String translateProperty(String input) {
		Matcher matcher = pProperty.matcher(input);
		StringBuffer result = new StringBuffer();
		
		 while (matcher.find()) {
			 String propertyName = matcher.group(1);
			 String typeName = matcher.group(2);
			 String columnName = databaseDictionary.resolveAttributeAsColumnName(propertyName, typeName);
		     matcher.appendReplacement(result, columnName);
		 }
		 matcher.appendTail(result);

		 return result.toString();
	}
	
	private String translateAssociation(String input) {
		Matcher matcher = pAssociation.matcher(input);
		StringBuffer result = new StringBuffer();
		
		 while (matcher.find()) {
			 String associationName = matcher.group(1);
			 String tableName = databaseDictionary.resolveAssociationAsTableName(associationName);
		     matcher.appendReplacement(result, tableName);
		 }
		 matcher.appendTail(result);

		 return result.toString();		
	}
	/*
	 * Spring IoC/DI material
	 */

	private DatabaseDictionary databaseDictionary;
	
	public void setDatabaseDictionary(DatabaseDictionary databaseDictionary_) {
		databaseDictionary = databaseDictionary_;
	}
}
