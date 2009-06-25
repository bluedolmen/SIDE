package com.bluexml.side.side.view.edit.ui.utils.model;

import java.util.HashMap;
import java.util.Map;

import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.plugin.EcorePlugin;
import org.eclipse.emf.ecore.util.EcoreUtil;

import com.bluexml.side.clazz.Attribute;
import com.bluexml.side.common.DataType;
import com.bluexml.side.common.MetaInfo;
import com.bluexml.side.view.Field;
import com.bluexml.side.view.ViewFactory;

public class ClassUtils {
	/**
	 * Return a field corresponding to the given attribute
	 * @param att
	 * @return
	 */
	static public Field getFieldForAttribute(Attribute att) {
		Field field = null;
		if (att != null) {
			Map<String, String> metaInfoMap = InitializeMetaInfo(att.getMetainfo());
			
			if (att.getTyp().equals(DataType.STRING)) { 
				// Email Field
				if (Boolean.parseBoolean(metaInfoMap.get("email"))) {
					field = ViewFactory.eINSTANCE.createEmailField();
				} else {
				// Char Field
					field = ViewFactory.eINSTANCE.createTextField();
				}
			// Date Time Field
			} else if (att.getTyp().equals(DataType.DATE_TIME)) {
				field = ViewFactory.eINSTANCE.createDateTimeField();
			// Date Field
			} else if (att.getTyp().equals(DataType.DATE)) {
				field = ViewFactory.eINSTANCE.createDateField();
			// Time Field
			} else if (att.getTyp().equals(DataType.TIME)) {
				field = ViewFactory.eINSTANCE.createTimeField();
			} else if(att.getTyp().equals(DataType.BOOLEAN)) {
			// Boolean Field
				field = ViewFactory.eINSTANCE.createBooleanField();
			} else if(att.getTyp().equals(DataType.INT)) {
			// Integer Field
				field = ViewFactory.eINSTANCE.createIntegerField();
			} else if(att.getTyp().equals(DataType.LONG)) {
			// Long Field
				field = ViewFactory.eINSTANCE.createIntegerField();
			} else if(att.getTyp().equals(DataType.FLOAT)) {
			// Float Field
				field = ViewFactory.eINSTANCE.createFloatField();
			} else if(att.getTyp().equals(DataType.DOUBLE)) {
			// Decimal Field
				//field = ViewFactory.eINSTANCE.createDecimalField();
			} else if(att.getTyp().equals(DataType.SHORT)) {
			// Short Field
				field = ViewFactory.eINSTANCE.createIntegerField();
			} else {
				EcorePlugin.INSTANCE.log("No field available for " + att.getTyp());
			}
			if (field != null) {
				field.setName(att.getLabel());
				field.setMapTo(att);
			}
			
		}
		
		return field;
	}
	
	public static Map<String,String> InitializeMetaInfo(EList<MetaInfo> metainfo) {
		Map<String,String> metaInfoMap = new HashMap<String,String>(metainfo.size());
		for (MetaInfo m : metainfo) {
			metaInfoMap.put(m.getKey(), m.getValue());
		}
		return metaInfoMap;
	}
}
