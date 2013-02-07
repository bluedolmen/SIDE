
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
