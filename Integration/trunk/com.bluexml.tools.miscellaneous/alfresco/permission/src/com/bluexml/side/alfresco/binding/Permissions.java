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

package com.bluexml.side.alfresco.binding;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;


/**
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "", propOrder = {
    "namespaces",
    "permissionSet",
    "globalPermission"
})
@XmlRootElement(name = "permissions")
public class Permissions {

    @XmlElement(required = true)
    protected List<Namespaces> namespaces;
    protected List<PermissionSet> permissionSet;
    protected List<GlobalPermission> globalPermission;

    /**
     * Gets the value of the namespaces property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the namespaces property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getNamespaces().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link Namespaces }
     * 
     * 
     */
    public List<Namespaces> getNamespaces() {
        if (namespaces == null) {
            namespaces = new ArrayList<Namespaces>();
        }
        return this.namespaces;
    }

    /**
     * Gets the value of the permissionSet property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the permissionSet property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getPermissionSet().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link PermissionSet }
     * 
     * 
     */
    public List<PermissionSet> getPermissionSet() {
        if (permissionSet == null) {
            permissionSet = new ArrayList<PermissionSet>();
        }
        return this.permissionSet;
    }

    /**
     * Gets the value of the globalPermission property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the globalPermission property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getGlobalPermission().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link GlobalPermission }
     * 
     * 
     */
    public List<GlobalPermission> getGlobalPermission() {
        if (globalPermission == null) {
            globalPermission = new ArrayList<GlobalPermission>();
        }
        return this.globalPermission;
    }

}
