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
