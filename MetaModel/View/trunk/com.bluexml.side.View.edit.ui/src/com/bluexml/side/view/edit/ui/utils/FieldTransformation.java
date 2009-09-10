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