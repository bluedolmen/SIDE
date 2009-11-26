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
package com.bluexml.side.Class.modeler.diagram.dialogs;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;

import com.bluexml.side.clazz.Enumeration;
import com.bluexml.side.clazz.EnumerationLiteral;

public class EnumerationLiteralDataStructure {

	/**
	 * Internal class to handle a parameter
	 */
	public class EnumerationLiteralObject {

		/** Parameter name */
		private String name;
		private String value;

		/**
		 * @return the value
		 */
		public String getValue() {
			return value;
		}

		/**
		 * @param value
		 *            the value to set
		 */
		public void setValue(String value) {
			this.value = value;
		}

		/**
		 * The constructor
		 * 
		 * @param n
		 *            parameter name
		 */
		public EnumerationLiteralObject(String n, String v) {
			name = n;
			value = v;
		}

		/**
		 * Get property name
		 * 
		 * @return name of the property
		 */
		public String getName() {
			return name;
		}

		/**
		 * Set new name for the parameter
		 * 
		 * @param newName
		 *            the new name
		 */
		public void setName(String newName) {
			name = newName;
		}

	} // End internal class

	/** A collection for ParameterObject objects */
	private ArrayList<EnumerationLiteralObject> data;

	/**
	 * The constructor
	 * 
	 * @param operation
	 *            the Operation
	 */
	public EnumerationLiteralDataStructure(Enumeration object) {
		data = new ArrayList<EnumerationLiteralObject>();
		if (object != null) {
			addAll(object.getLiterals());
		}
	}

	/**
	 * Add a parameter to the structure
	 * 
	 * @param parameter
	 *            the parameter to add
	 */
	public void add(EnumerationLiteral literal) {
		data.add(new EnumerationLiteralObject(literal.getName(), literal.getValue()));
	}

	/**
	 * Add a parameter to the structure
	 * 
	 * @param name
	 *            the parameter name
	 */
	public void add(String name, String value) {
		data.add(new EnumerationLiteralObject(name, value));
	}

	/**
	 * Remove a parameter or the name or etc..; from the structure
	 * 
	 * @param object
	 *            the object to remove
	 */
	public void remove(Object object) {
		data.remove(object);
	}

	/**
	 * Add a collection of parameters to the operation
	 * 
	 * @param parameters
	 *            the collection of parameters to add
	 */
	public void addAll(Collection<EnumerationLiteral> parameters) {
		Iterator<EnumerationLiteral> itParameters = parameters.iterator();
		while (itParameters.hasNext()) {
			EnumerationLiteral parameter = itParameters.next();
			add(parameter);
		}
	}

	/**
	 * Get the datas
	 * 
	 * @return a Collection of
	 */
	public Collection<EnumerationLiteralObject> getData() {
		return data;
	}

	/**
	 * Get the name of a given object
	 * 
	 * @param object
	 *            OperationDataObject object
	 * @return the name
	 */
	public String getDisplayName(Object object) {
		String result = ((EnumerationLiteralObject) object).getName();
		if (result == null || result.length() == 0) {
			result = "Property name no set";
		}
		return result;
	}

	/**
	 * Get the name of a given object
	 * 
	 * @param object
	 *            OperationDataObject object
	 * @return the name
	 */
	public String getDisplayValue(Object object) {
		String result = ((EnumerationLiteralObject) object).getValue();
		if (result == null || result.length() == 0) {
			result = "Property value no set";
		}
		return result;
	}

	/**
	 * Set the name of the parameter object
	 * 
	 * @param object
	 *            a ParameterObject
	 * @param name
	 *            the new name
	 */
	public void setName(Object object, String name) {
		((EnumerationLiteralObject) object).setName(name);
	}

	/**
	 * Set the name of the parameter object
	 * 
	 * @param object
	 *            a ParameterObject
	 * @param name
	 *            the new value
	 */
	public void setValue(Object object, String value) {
		((EnumerationLiteralObject) object).setValue(value);
	}

	public void replace(int index_first, int index_last) {
		EnumerationLiteralObject object_first = data.get(index_first);
		EnumerationLiteralObject object_last = data.get(index_last);
		data.set(index_first, object_last);
		data.set(index_last, object_first);
	}
}
