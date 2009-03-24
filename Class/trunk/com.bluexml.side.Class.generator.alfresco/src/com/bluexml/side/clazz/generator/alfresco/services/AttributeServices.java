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
import com.bluexml.side.clazz.*;
import fr.obeo.acceleo.gen.template.eval.ENode;
import fr.obeo.acceleo.gen.template.eval.ENodeCastException;

public class AttributeServices {
	
	public String getPropertyType(ENode node) throws ENodeCastException {
		if (node.getEObject() instanceof Attribute) {
			Attribute object = (Attribute) node.getEObject();
			if (object.getTyp() == AttributeType.BOOLEAN) {
				return "d:boolean";
			} else if (object.getTyp() == AttributeType.BYTE) {
				return "d:int";
			} else if (object.getTyp() == AttributeType.CHAR) {
				return "d:text";
			} else if (object.getTyp() == AttributeType.DATE) {
				return "d:date";
			} else if (object.getTyp() == AttributeType.DATE_TIME) {
				return "d:datetime";
			} else if (object.getTyp() == AttributeType.DOUBLE) {
				return "d:double";
			} else if (object.getTyp() == AttributeType.FLOAT) {
				return "d:float";
			} else if (object.getTyp() == AttributeType.INT) {
				return "d:int";
			} else if (object.getTyp() == AttributeType.LONG) {
				return "d:long";
			} else if (object.getTyp() == AttributeType.OBJECT) {
				return "d:content";
			} else if (object.getTyp() == AttributeType.SHORT) {
				return "d:int";
			} else if (object.getTyp() == AttributeType.STRING) {
				return "d:text";
			} else if (object.getTyp() == AttributeType.VOID) {
				return "d:any";
			}
		}
		return "";
	}
}
