
package com.bluexml.xforms.controller.binding;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for GenericDelete complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="GenericDelete">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="id" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="assocObject" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "GenericDelete", propOrder = {
    "id",
    "assocObject"
})
public class GenericDelete {

    @XmlElement(required = true)
    protected String id;
    @XmlElement(required = true)
    protected String assocObject;

    /**
     * Gets the value of the id property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getId() {
        return id;
    }

    /**
     * Sets the value of the id property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setId(String value) {
        this.id = value;
    }

    /**
     * Gets the value of the assocObject property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getAssocObject() {
        return assocObject;
    }

    /**
     * Sets the value of the assocObject property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setAssocObject(String value) {
        this.assocObject = value;
    }

}
