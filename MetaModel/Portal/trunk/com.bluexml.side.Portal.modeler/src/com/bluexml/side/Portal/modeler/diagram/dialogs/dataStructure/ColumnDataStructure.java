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
package com.bluexml.side.Portal.modeler.diagram.dialogs.dataStructure;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;

import com.bluexml.side.portal.Column;
import com.bluexml.side.portal.PortalLayout;


public class ColumnDataStructure {
	
	/**
     * Internal class to handle a column
     */
    public class ColumnObject
    {

        /** Parameter name */
        private String name;
        private String type;
        private int width;

        /**
         * The constructor
         * 
         * @param n parameter name
         */
        public ColumnObject(String p_name, String p_type, int p_width)
        {
            name = p_name;
            type = p_type;
            width = p_width;
        }

        /**
         * Get property name
         * 
         * @return name of the property
         */
        public String getName()
        {
            return name;
        }

        /**
         * Set new name for the parameter
         * 
         * @param newName the new name
         */
        public void setName(String newName)
        {
            name = newName;
        }

		public String getType() {
			return type;
		}

		public void setType(String type) {
			this.type = type;
		}

		public int getWidth() {
			return width;
		}

		public void setWidth(int width) {
			this.width = width;
		}

    } // End internal class
	
	
	/** A collection for ViewAttribute objects */
	private List<ColumnObject> listCol;
	
	/**
	 * 
	 * @param v
	 */
	@SuppressWarnings("unchecked")
	public ColumnDataStructure(List<Column> c) {				
		listCol = new ArrayList();
        if (c != null)
        {
        	addAll(c);
        }
	}
	
	public ColumnDataStructure() {				
		listCol = new ArrayList<ColumnObject>();        
	}
	
	/**
     * Add a column to the structure
     * 
     * @param name the parameter name
     */
    public void add(String p_name, String p_type, int p_width)
    {
    	listCol.add(new ColumnObject(p_name, p_type, p_width));
    }

    public void add(Column c)
    {
    	listCol.add(new ColumnObject(c.getName(), c.getUnit().getLiteral(), c.getWidth()));
    }
    
    public void add(PortalLayout p) {
    	addAll(p.getColumns());
    }
    
    /**
     * Add a collection of column to the PortalLayout
     * 
     * @param columns the collection of column to add
     */
    public void addAll(Collection<Column> columns)
    {
        Iterator<Column> itColumns = columns.iterator();
        while (itColumns.hasNext())
        {
        	Column column = (Column) itColumns.next();
            add(column);
        }
    }
    
    public List<ColumnObject> getData() {
    	return listCol;
    }
    
    /**
     * Get the name of a given object
     * 
     * @param object ColumnObject object
     * @return the name
     */
    public String getName(Object object)
    {
        String result = ((ColumnObject) object).getName();
        if (result == null || result.length() == 0)
        {
            result = "Property name not set";
        }
        return result;
    }

    /**
     * Get the type of a given object
     * 
     * @param object ColumnObject object
     * @return the name of the type
     */
    public String getType(Object object)
    {
        String type = ((ColumnObject) object).getType();       
        return type;
    }
    
    /**
     * Get the type of a given object
     * 
     * @param object ColumnObject object
     * @return the name of the type
     */
    public int getWidth(Object object)
    {
        int width = ((ColumnObject) object).getWidth();       
        return width;
    }
    
    /**
     * Remove a colum from the structure
     * 
     * @param object the object to remove
     */
    public void remove(Object object)
    {
    	listCol.remove(object);
    }
        

    /**
     * Set the name of the parameter object
     * 
     * @param object a ColumnObject
     * @param name the new name
     */
    public void setName(Object object, String name)
    {
        ((ColumnObject) object).setName(name);
    }

    /**
     * Set the type of the column object
     * 
     * @param object a ColumnObject
     * @param type the new type
     */
    public void setType(Object object, String type)
    {
        ((ColumnObject) object).setType(type);
    }
    
    /**
     * Set the type of the column object
     * 
     * @param object a ColumnObject
     * @param type the new type
     */
    public void setWidth(Object object, int width)
    {
        ((ColumnObject) object).setWidth(width);
    }
        
}
