/*******************************************************************************
 * 	Copyright (C) BlueXML 2005-2008
 *
 * This program is free software; you can redistribute it and/or modify
 *  it under the terms of the GNU General Public License as published by
 *  the Free Software Foundation; either version 2.
 * 
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 * 
 * You should have received a copy of the GNU General Public License
 * along with this program; if not, write to the Free Software
 * Foundation, Inc., 59 Temple Place, Boston, MA 02111.
 ******************************************************************************/
package com.bluexml.side.clazz.generator.alfresco.services;
import com.bluexml.side.clazz.Attribute;
import com.bluexml.side.common.DataType;

import fr.obeo.acceleo.gen.template.eval.ENode;
import fr.obeo.acceleo.gen.template.eval.ENodeCastException;

public class AttributeServices {
	
	public String getPropertyType(ENode node) throws ENodeCastException {
		if (node.getEObject() instanceof Attribute) {
			Attribute object = (Attribute) node.getEObject();
			if (object.getTyp() == DataType.BOOLEAN) {
				return "d:boolean";
			} else if (object.getTyp() == DataType.BYTE) {
				return "d:int";
			} else if (object.getTyp() == DataType.CHAR) {
				return "d:text";
			} else if (object.getTyp() == DataType.DATE) {
				return "d:date";
			} else if (object.getTyp() == DataType.DATE_TIME) {
				return "d:datetime";
			} else if (object.getTyp() == DataType.DOUBLE) {
				return "d:double";
			} else if (object.getTyp() == DataType.FLOAT) {
				return "d:float";
			} else if (object.getTyp() == DataType.INT) {
				return "d:int";
			} else if (object.getTyp() == DataType.LONG) {
				return "d:long";
			} else if (object.getTyp() == DataType.OBJECT) {
				return "d:content";
			} else if (object.getTyp() == DataType.SHORT) {
				return "d:int";
			} else if (object.getTyp() == DataType.STRING) {
				return "d:text";
			} else if (object.getTyp() == DataType.VOID) {
				return "d:any";
			} else if (object.getTyp() == DataType.TIME) {
				return "d:datetime";
			}
		}
		return "d:any";
	}
	
	/*
	 * JPA generation helper functions
	 * @author : Brice Pajot
	 * @date   : 2009-06-22
	 * 
	 * These types should conform to the alfresco model definition and to the mapping
	 * of the Alfresco's data dictionary to Java
	 * see also http://wiki.alfresco.com/wiki/Data_Dictionary_Guide
	 */
	public String getJPAJavaType(Attribute a) {
		String result = null;
		
		if (a.getTyp() == DataType.BOOLEAN) {
			result = "Boolean";
		} else if (a.getTyp() == DataType.BYTE) {
			result = "Integer"; 
		} else if (a.getTyp() == DataType.CHAR) {
			result = "String";
		} else if (a.getTyp() == DataType.DATE) {
			result = "Date";
		} else if (a.getTyp() == DataType.DATE_TIME) {
			result = "Date";
		} else if (a.getTyp() == DataType.TIME) {
			result = "Date";
		} else if (a.getTyp() == DataType.DOUBLE) {
			result = "Double"; 
		} else if (a.getTyp() == DataType.FLOAT) {
			result = "Float";
		} else if (a.getTyp() == DataType.INT) {
			result = "Integer";
		} else if (a.getTyp() == DataType.LONG) {
			result = "Long";
		} else if (a.getTyp() == DataType.OBJECT) {
			result = "Serializable";
		} else if (a.getTyp() == DataType.SHORT) {
			result = "Integer";
		} else if (a.getTyp() == DataType.STRING) {
			result = "String";
		} else if (a.getTyp() == DataType.VOID) {
			result = "Serializable";
		}

		return result;
	}

}
