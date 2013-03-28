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
package com.bluexml.side.view.edit.ui.utils;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Properties;

import com.bluexml.side.view.Field;
import com.bluexml.side.view.edit.ui.actions.TransformField;


public class FieldTransformation {
	
	/**
	 * Transform a field into another kind of field.
	 * @param master
	 * @param copy
	 */
	public static void transform(Field master, Field copy) {
		// First we made the copy of common fields
		copy.setName(master.getName());
		copy.setDescription(master.getDescription());
		copy.setDocumentation(master.getDocumentation());
		copy.setMapTo(master.getMapTo());
		copy.setPatern(master.getPatern());
		copy.setPaternLanguage(master.getPaternLanguage());
		copy.setPrefix(master.getPrefix());
		copy.setStyling(master.getStyling());
		copy.setSuffix(master.getSuffix());
	}
	
	protected static HashMap<String, ArrayList<String>> map = null;
	private static Properties prop = null;
	
	protected static void getPropertiesFile() {
		prop = new Properties();
		InputStream myStream = TransformField.class.getResourceAsStream("/properties/compatibility.properties");
		if (myStream != null) {			
			try {
				prop.load(myStream);
				myStream.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
	
	static public List<String> getAvailableTransformation(Field f) {
		if (prop == null){				
			getPropertiesFile();
		}
		List<String> list = null;
		String fieldName = f.eClass().getName();
		if(prop.containsKey(fieldName)) {
			String value = prop.getProperty(fieldName);
			String[] values = value.split( "," );
			list = (List<String>) Arrays.asList( values );
		}
		return list;
	}
}
