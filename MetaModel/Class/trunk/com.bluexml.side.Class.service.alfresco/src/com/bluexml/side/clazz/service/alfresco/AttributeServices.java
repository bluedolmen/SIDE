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
		throw new Exception ("node must be an attribute");
	}
	public String getFtlTypeConverter(EObject node) throws Exception {
		if (node instanceof Attribute) {
			Attribute object = (Attribute) node;
			if (object.getTyp() == DataType.BOOLEAN) {
				return "?string";
			} else if (object.getTyp() == DataType.BYTE) {
				return "?int";
			} else if (object.getTyp() == DataType.CHAR) {
				return "?string";
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
				return "?any";
			} else if (object.getTyp() == DataType.SHORT) {
				return "?int";
			} else if (object.getTyp() == DataType.STRING) {
				return "?string";
			} else if (object.getTyp() == DataType.TIME) {
				return "?datetime";
			}
		}
		throw new Exception ("node must be an attribute");
	}
}
