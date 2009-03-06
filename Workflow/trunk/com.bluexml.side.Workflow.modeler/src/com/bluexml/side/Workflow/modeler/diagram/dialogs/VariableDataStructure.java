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
package com.bluexml.side.Workflow.modeler.diagram.dialogs;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;

import com.bluexml.side.workflow.Script;
import com.bluexml.side.workflow.Variable;

public class VariableDataStructure
{

    /**
     * Internal class to handle a parameter
     */
    public class ParameterObject
    {

        /** Parameter name */
        private String name;

        /** Parameter type */
        private String access;

        /**
         * The constructor
         * 
         * @param n parameter name
         * @param a parameter type
         */
        public ParameterObject(String n, String a)
        {
            name = n;
            access = a;
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
        public String getAccess()
        {
            return access;
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
         * @param newAccess the new type 
         */
        public void setAccess(String newAccess)
        {
            access = newAccess;
        }

    } // End internal class

    /** A collection for ParameterObject objects */
    private ArrayList<Object> data;

    /**
     * The constructor
     * 
     * @param operation the Operation
     */
    public VariableDataStructure(Script script)
    {
        data = new ArrayList<Object>();
        if (script != null)
        {
            addAll(script.getVariable());
        }
    }

    /**
     * Add a parameter to the structure
     * 
     * @param parameter the parameter to add
     */
    public void add(Variable variable)
    {
        data.add(new ParameterObject(variable.getName(), variable.getAccess()));
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
    public void addAll(Collection<Variable> parameters)
    {
        Iterator<Variable> itParameters = parameters.iterator();
        while (itParameters.hasNext())
        {
            Variable parameter = (Variable) itParameters.next();
            add(parameter);
        }
    }

    /**
     * Get the datas
     * 
     * @return a Collection of
     */
    public Collection<?> getData()
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
    public String getDisplayAccess(Object object)
    {
        String type = getAccess(object);
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
    public String getAccess(Object object)
    {
        return ((ParameterObject) object).getAccess();
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
     * @param access the new type
     */
    public void setType(Object object, String access)
    {
        ((ParameterObject) object).setAccess(access);
    }
}
