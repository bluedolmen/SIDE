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
package com.bluexml.side.clazz.service.alfresco;

import org.eclipse.emf.ecore.EObject;

import com.bluexml.side.clazz.Attribute;
import com.bluexml.side.common.DataType;

public class AttributeServices {

	public String getPropertyType(EObject node) throws Exception {
		if (node instanceof Attribute) {
			Attribute object = (Attribute) node;
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
				//return "d:content";
				return "d:any";
			} else if (object.getTyp() == DataType.SHORT) {
				return "d:int";
			} else if (object.getTyp() == DataType.STRING) {
				return "d:text";
			} else if (object.getTyp() == DataType.TIME) {
				return "d:datetime";
			}
		}
		throw new Exception("node must be an attribute");
	}

	public String getShareSearchFormControl(Attribute object) throws Exception {
		String numberRange = "/org/alfresco/components/form/controls/numberrange.ftl";
		String dateRange = "/org/alfresco/components/form/controls/daterange.ftl";
		
		String selectMany = "/org/alfresco/components/form/controls/selectmany.ftl";
		if (object.getValueList() != null) {
			return selectMany;
		}
		if (object.getTyp() == DataType.BOOLEAN) {
			return "";
		} else if (object.getTyp() == DataType.BYTE) {
			return numberRange;
		} else if (object.getTyp() == DataType.CHAR) {
			return "";
		} else if (object.getTyp() == DataType.DATE) {
			return dateRange;
		} else if (object.getTyp() == DataType.DATE_TIME) {
			return dateRange;
		} else if (object.getTyp() == DataType.DOUBLE) {
			return numberRange;
		} else if (object.getTyp() == DataType.FLOAT) {
			return numberRange;
		} else if (object.getTyp() == DataType.INT) {
			return numberRange;
		} else if (object.getTyp() == DataType.LONG) {
			return numberRange;
		} else if (object.getTyp() == DataType.OBJECT) {
			//return "d:content";
			return "";
		} else if (object.getTyp() == DataType.SHORT) {
			return numberRange;
		} else if (object.getTyp() == DataType.STRING) {
			return "";
		} else if (object.getTyp() == DataType.TIME) {
			return dateRange;
		}

		throw new Exception("no controle found for " + object);
	}

	public static DataType getPropertyType(String type) throws Exception {
		if (type.equals("d:boolean")) {
			return DataType.BOOLEAN;
		} else if (type.equals("d:int")) {
			return DataType.INT;
		} else if (type.equals("d:text")) {
			return DataType.STRING;
		} else if (type.equals("d:date")) {
			return DataType.DATE;
		} else if (type.equals("d:datetime")) {
			return DataType.DATE_TIME;
		} else if (type.equals("d:double")) {
			return DataType.DOUBLE;
		} else if (type.equals("d:float")) {
			return DataType.FLOAT;
		} else if (type.equals("d:long")) {
			return DataType.LONG;
		} else {
			System.err.println("Property type fall to default Object Type :" + type);
			return DataType.OBJECT;
		}
		//		throw new Exception("Property Type not managed :" + type);
	}

	public String getFtlTypeConverter(EObject node) throws Exception {
		if (node instanceof Attribute) {
			Attribute object = (Attribute) node;
			String string = "?js_string";
			if (object.getTyp() == DataType.BOOLEAN) {
				return "?string";
			} else if (object.getTyp() == DataType.BYTE) {
				return "?int";
			} else if (object.getTyp() == DataType.CHAR) {
				return string;
			} else if (object.getTyp() == DataType.DATE) {
				return "?date";
			} else if (object.getTyp() == DataType.DATE_TIME) {
				return "?datetime";
			} else if (object.getTyp() == DataType.DOUBLE) {
				return "?double";
			} else if (object.getTyp() == DataType.FLOAT) {
				return "?float";
			} else if (object.getTyp() == DataType.INT) {
				return "?int";
			} else if (object.getTyp() == DataType.LONG) {
				return "?long";
			} else if (object.getTyp() == DataType.OBJECT) {
				//return "?content";
				return string;
			} else if (object.getTyp() == DataType.SHORT) {
				return "?int";
			} else if (object.getTyp() == DataType.STRING) {
				return string;
			} else if (object.getTyp() == DataType.TIME) {
				return "?datetime";
			}
		}
		throw new Exception("node must be an attribute");
	}
}
