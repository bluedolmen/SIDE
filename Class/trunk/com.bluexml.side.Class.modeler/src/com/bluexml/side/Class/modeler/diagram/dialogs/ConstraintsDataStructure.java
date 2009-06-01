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

import com.bluexml.side.clazz.Attribute;
import com.bluexml.side.common.MetaInfo;



public class ConstraintsDataStructure {

	/**
	 * Internal class to handle a parameter
	 */
	public class ConstraintObject {

		/** Parameter name */
		private String key;

		/** Parameter type */
		private String value;

		/**
		 * The constructor
		 * 
		 * @param n
		 *            parameter name
		 * @param t
		 *            parameter type
		 */
		public ConstraintObject(String _key, String _value) {
			key = _key;
			value = _value;
		}

		/**
		 * @return the key
		 */
		public String getKey() {
			return key;
		}

		/**
		 * @param key
		 *            the key to set
		 */
		public void setKey(String key) {
			this.key = key;
		}

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

	} // End internal class

	/** A collection for ParameterObject objects */
	private ArrayList<ConstraintObject> data;

	/**
	 * The constructor
	 * 
	 * @param operation
	 *            the Operation
	 */
	public ConstraintsDataStructure(Attribute attr) {
		data = new ArrayList<ConstraintObject>();
		if (attr != null) {
			for (Object o : attr.getMetainfo()) {
				MetaInfo c = (MetaInfo) o;
				
				String val = null;
				if (c.getValue() != null) {
					val = c.getValue().toString();
				}
				
				data.add(new ConstraintObject(c.getKey(), val));
			}
		}
	}

	public ConstraintsDataStructure() {
		data = new ArrayList<ConstraintObject>();
	}

	/**
	 * Add a parameter to the structure
	 * 
	 * @param parameter
	 *            the parameter to add
	 */
	public void add(MetaInfo c) {
		data.add(new ConstraintObject(c.getKey(), c.getValue().toString()));
	}

	/**
	 * Add a parameter to the structure
	 * 
	 * @param name
	 *            the parameter name
	 * @param type
	 *            the parameter type
	 */
	public void add(String name, String type) {
		data.add(new ConstraintObject(name, type));
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
	 * Get the datas
	 * 
	 * @return a Collection of
	 */
	public Collection getData() {
		return data;
	}
}
