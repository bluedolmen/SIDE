package com.bluexml.side.Integration.alfresco.sql.synchronization.dialects.oracle;

import java.util.HashMap;
import java.util.Map;

import org.alfresco.service.cmr.dictionary.DataTypeDefinition;
import org.alfresco.service.namespace.QName;

import com.bluexml.side.Integration.alfresco.sql.synchronization.dialects.DefaultDialect;

@SuppressWarnings("serial")
public class OracleDialect extends DefaultDialect {

	private static final Integer ORACLE_MAX_CHAR_LENGTH = 4000;

	private static Map<QName, String> oracleSqlTypeMapping = new HashMap<QName, String>() {
		{
			put(DataTypeDefinition.ANY, "BLOB"); 
			put(DataTypeDefinition.BOOLEAN, "VARCHAR(5)"); 
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

	
    public Map<QName, String> getSqlTypeMapping () {
    	return oracleSqlTypeMapping;
    }
    		
	public String booleanFormat(String input) {
		// Oracle does not define boolean whihc are replace by varchar an must be quoted
		return "'" + input + "'";
	}

	public Integer getXCharDefaultLength() {
		return ORACLE_MAX_CHAR_LENGTH;
	}
	
}
