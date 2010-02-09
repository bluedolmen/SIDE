package com.bluexml.side.form.common.utils;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Properties;

import com.bluexml.side.form.Field;
import com.bluexml.side.form.ModelChoiceField;
import com.bluexml.side.form.VirtualField;
import com.bluexml.side.form.common.TransformFieldAction;

public class FieldTransformation {
	
	/**
	 * Transform a field into another kind of field.
	 * @param master
	 * @param copy
	 */
	public static void transform(Field master, Field copy) {
		// First we made the copy of common fields
		copy.setId(master.getId());
		copy.setLabel(master.getLabel());
		copy.setHelp_text(master.getHelp_text());
		copy.setError_messages(master.getError_messages());
		copy.setHidden(master.isHidden());
		copy.setInitial(master.getInitial());
		copy.setMandatory(master.isMandatory());
		copy.setRef(master.getRef());
		
		// Special case
		// Model Choice, Reference
		if (master instanceof ModelChoiceField && copy instanceof ModelChoiceField) {
			((ModelChoiceField)copy).setMax_bound(((ModelChoiceField)master).getMax_bound());
			((ModelChoiceField)copy).setMin_bound(((ModelChoiceField)master).getMin_bound());
			((ModelChoiceField)copy).setReal_class(((ModelChoiceField)master).getReal_class());
//			((ModelChoiceField)copy).setAssociation_class(((ModelChoiceField)master).getAssociation_class());
		}
		// Virtual Field
		if (copy instanceof VirtualField) {
			((VirtualField)copy).setMandatory(false);
		}
		//return copy;
	}
	
	protected static HashMap<String, ArrayList<String>> map = null;
	private static Properties prop = null;
	
	protected static void getPropertiesFile() {
		prop = new Properties();
		InputStream myStream = TransformFieldAction.class.getResourceAsStream("/properties/compatibility.properties");
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