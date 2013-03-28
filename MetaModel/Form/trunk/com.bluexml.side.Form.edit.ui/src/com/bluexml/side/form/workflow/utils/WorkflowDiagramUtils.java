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
package com.bluexml.side.form.workflow.utils;

import org.eclipse.emf.ecore.plugin.EcorePlugin;

import com.bluexml.side.clazz.Clazz;
import com.bluexml.side.common.DataType;
import com.bluexml.side.common.ModelElement;
import com.bluexml.side.form.ActionField;
import com.bluexml.side.form.Field;
import com.bluexml.side.form.FormFactory;
import com.bluexml.side.form.ModelChoiceField;
import com.bluexml.side.workflow.Attribute;
import com.bluexml.side.workflow.Transition;

public class WorkflowDiagramUtils {
	
	/**
	 * Will return the field corresponding to the attribute
	 * @param att
	 * @return
	 */
	public static Field getFieldForAttribute(Attribute att) {
		Field field = null;
		if (att != null) {
			if (att.getTyp().equals(DataType.STRING)) { 
				field = FormFactory.eINSTANCE.createCharField();
			// Date Time Field
			} else if (att.getTyp().equals(DataType.DATE_TIME)) {
				field = FormFactory.eINSTANCE.createDateTimeField();
			// Date Field
			} else if (att.getTyp().equals(DataType.DATE)) {
				field = FormFactory.eINSTANCE.createDateField();
			// Time Field
			} else if (att.getTyp().equals(DataType.TIME)) {
				field = FormFactory.eINSTANCE.createTimeField();
			} else if(att.getTyp().equals(DataType.BOOLEAN)) {
			// Boolean Field
				field = FormFactory.eINSTANCE.createBooleanField();
			} else if(att.getTyp().equals(DataType.INT)) {
			// Integer Field
				field = FormFactory.eINSTANCE.createIntegerField();
			} else if(att.getTyp().equals(DataType.FLOAT)) {
			// Float Field
				field = FormFactory.eINSTANCE.createFloatField();
			} else if(att.getTyp().equals(DataType.DOUBLE)) {
			// Decimal Field
				field = FormFactory.eINSTANCE.createDecimalField();
			} else if(att.getTyp().equals(DataType.SHORT)) {
			// Short Field
				field = FormFactory.eINSTANCE.createIntegerField();
			} else {
				EcorePlugin.INSTANCE.log("No field available for " + att.getTyp());
			}
			
			if (field == null) {
				//field = formFactory.eINSTANCE.createField();
			} else {
				field.setRef((ModelElement)att);
				field.setId(att.getName());
				if (att.getTitle() != null && att.getTitle().length() > 0) {
					field.setLabel(att.getTitle());
				} else {
					field.setLabel(att.getName());
				}
				field.setId(att.getName());
			}
		}
		return field;
	}

	public static ActionField getOperationForTransition(Transition t) {
		ActionField af = null;
		if (t != null) {
			af = FormFactory.eINSTANCE.createActionField();
			af.setId(t.getName());
			af.setLabel(t.getTitle());
			af.setRef(t);
		}
		return af;
	}

	public static Field getFieldForClazzLink(Clazz c) {
		Field f = null;
		
		if (c != null) {
			ModelChoiceField mcf = FormFactory.eINSTANCE.createModelChoiceField();
			mcf.setReal_class(c);
			mcf.setId(c.getName());
			mcf.setLabel(c.getTitle());
			mcf.setMax_bound(1);
			f = mcf;
		}
		
		return f;
	}
	
	}
