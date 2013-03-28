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
			put(DataTypeDefinition.MLTEXT, "VARCHAR");	
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
