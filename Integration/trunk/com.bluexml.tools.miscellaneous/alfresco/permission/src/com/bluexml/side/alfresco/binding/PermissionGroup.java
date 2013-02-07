
package com.bluexml.side.alfresco.binding;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;
import javax.xml.bind.annotation.adapters.CollapsedStringAdapter;
import javax.xml.bind.annotation.adapters.NormalizedStringAdapter;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;


/**
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "", propOrder = {
    "includePermissionGroup"
})
@XmlRootElement(name = "permissionGroup")
public class PermissionGroup {

    @XmlAttribute(required = true)
    @XmlJavaTypeAdapter(NormalizedStringAdapter.class)
    protected String name;
    @XmlAttribute
    @XmlJavaTypeAdapter(NormalizedStringAdapter.class)
    protected String type;
    @XmlAttribute(name = "extends")
    @XmlJavaTypeAdapter(CollapsedStringAdapter.class)
    protected String _extends;
    @XmlAttribute
    @XmlJavaTypeAdapter(CollapsedStringAdapter.class)
    protected String expose;
    @XmlAttribute
    @XmlJavaTypeAdapter(CollapsedStringAdapter.class)
    protected String allowFullControl;
    @XmlAttribute
    @XmlJavaTypeAdapter(CollapsedStringAdapter.class)
    protected String requiresType;
    protected List<IncludePermissionGroup> includePermissionGroup;

    /**
     * Gets the value of the name property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getName() {
        return name;
    }

    /**
     * Sets the value of the name property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setName(String value) {
        this.name = value;
    }

    /**
     * Gets the value of the type property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getType() {
        return type;
    }

    /**
     * Sets the value of the type property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setType(String value) {
        this.type = value;
    }

    /**
     * Gets the value of the extends property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getExtends() {
        if (_extends == null) {
            return "false";
        } else {
            return _extends;
        }
    }

    /**
     * Sets the value of the extends property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setExtends(String value) {
        this._extends = value;
    }

    /**
     * Gets the value of the expose property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getExpose() {
        if (expose == null) {
            return "false";
        } else {
            return expose;
        }
    }

    /**
     * Sets the value of the expose property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setExpose(String value) {
        this.expose = value;
    }

    /**
     * Gets the value of the allowFullControl property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getAllowFullControl() {
        if (allowFullControl == null) {
            return "false";
        } else {
            return allowFullControl;
        }
    }

    /**
     * Sets the value of the allowFullControl property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setAllowFullControl(String value) {
        this.allowFullControl = value;
    }

    /**
     * Gets the value of the requiresType property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getRequiresType() {
        if (requiresType == null) {
            return "true";
        } else {
            return requiresType;
        }
    }

    /**
     * Sets the value of the requiresType property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setRequiresType(String value) {
        this.requiresType = value;
    }

    /**
     * Gets the value of the includePermissionGroup property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the includePermissionGroup property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getIncludePermissionGroup().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link IncludePermissionGroup }
     * 
     * 
     */
    public List<IncludePermissionGroup> getIncludePermissionGroup() {
        if (includePermissionGroup == null) {
            includePermissionGroup = new ArrayList<IncludePermissionGroup>();
        }
        return this.includePermissionGroup;
    }

}
