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

import com.bluexml.side.common.Operation;
import com.bluexml.side.common.Parameter;



public class OperationDataStructure
{

    /**
     * Internal class to handle a parameter
     */
    public class ParameterObject
    {

        /** Parameter name */
        private String name;

        /** Parameter type */
        private String type;

        /**
         * The constructor
         * 
         * @param n parameter name
         * @param t parameter type
         */
        public ParameterObject(String n, String t)
        {
            name = n;
            type = t;
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
         * Get property type
         * 
         * @return type of the property
         */
        public String getType()
        {
            return type;
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

        /**
         * Set new type for the parameter
         * 
         * @param newType the new type 
         */
        public void setType(String newType)
        {
            type = newType;
        }

    } // End internal class

    /** A collection for ParameterObject objects */
    private ArrayList data;

    /**
     * The constructor
     * 
     * @param operation the Operation
     */
    public OperationDataStructure(Operation operation)
    {
        data = new ArrayList();
        if (operation != null)
        {
            addAll(operation.getParameters());
        }
    }

    /**
     * Add a parameter to the structure
     * 
     * @param parameter the parameter to add
     */
    public void add(Parameter parameter)
    {
        data.add(new ParameterObject(parameter.getName(), parameter.getValueType().toString()));
    }

    /**
     * Add a parameter to the structure
     * 
     * @param name the parameter name
     * @param type the parameter type
     */
    public void add(String name, String type)
    {
        data.add(new ParameterObject(name, type));
    }

    /**
     * Remove a parameter or the name or etc..; from the structure
     * 
     * @param object the object to remove
     */
    public void remove(Object object)
    {
        data.remove(object);
    }

    /**
     * Add a collection of parameters to the operation
     * 
     * @param parameters the collection of parameters to add
     */
    public void addAll(Collection parameters)
    {
        Iterator itParameters = parameters.iterator();
        while (itParameters.hasNext())
        {
            Parameter parameter = (Parameter) itParameters.next();
            add(parameter);
        }
    }

    /**
     * Get the datas
     * 
     * @return a Collection of
     */
    public Collection getData()
    {
        return data;
    }

    /**
     * Get the name of a given object
     * 
     * @param object OperationDataObject object
     * @return the name
     */
    public String getDisplayName(Object object)
    {
        String result = ((ParameterObject) object).getName();
        if (result == null || result.length() == 0)
        {
            result = "Property name no set";
        }
        return result;
    }

    /**
     * Get the type name of a given object
     * 
     * @param object OperationDataObject object
     * @return the name of the type
     */
    public String getDisplayType(Object object)
    {
        String type = getType(object);
        if (type == null)
        {
            return null;
        }
        return type;
    }

    /**
     * Get the type of a given object
     * 
     * @param object OperationDataObject object
     * @return the type
     */
    public String getType(Object object)
    {
        return ((ParameterObject) object).getType();
    }

    /**
     * Set the name of the parameter object
     * 
     * @param object a ParameterObject
     * @param name the new name
     */
    public void setName(Object object, String name)
    {
        ((ParameterObject) object).setName(name);
    }

    /**
     * Set the type of the parameter object
     * 
     * @param object a ParameterObject
     * @param type the new type
     */
    public void setType(Object object, String type)
    {
        ((ParameterObject) object).setType(type);
    }
}
