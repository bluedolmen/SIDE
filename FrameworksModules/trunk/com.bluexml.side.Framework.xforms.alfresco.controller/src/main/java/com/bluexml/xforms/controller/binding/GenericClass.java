
package com.bluexml.xforms.controller.binding;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for GenericClass complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="GenericClass">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="attributes" type="{}GenericAttributes" minOccurs="0"/>
 *         &lt;element name="associations" type="{}GenericAssociations" minOccurs="0"/>
 *       &lt;/sequence>
 *       &lt;attribute name="qualifiedName" use="required" type="{http://www.w3.org/2001/XMLSchema}anySimpleType" />
 *       &lt;attribute name="id" type="{http://www.w3.org/2001/XMLSchema}anySimpleType" />
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "GenericClass", propOrder = {
    "attributes",
    "associations"
})
public class GenericClass {

    protected GenericAttributes attributes;
    protected GenericAssociations associations;
    @XmlAttribute(required = true)
    @XmlSchemaType(name = "anySimpleType")
    protected String qualifiedName;
    @XmlAttribute
    @XmlSchemaType(name = "anySimpleType")
    protected String id;

    /**
     * Gets the value of the attributes property.
     * 
     * @return
     *     possible object is
     *     {@link GenericAttributes }
     *     
     */
    public GenericAttributes getAttributes() {
        return attributes;
    }

    /**
     * Sets the value of the attributes property.
     * 
     * @param value
     *     allowed object is
     *     {@link GenericAttributes }
     *     
     */
    public void setAttributes(GenericAttributes value) {
        this.attributes = value;
    }

    /**
     * Gets the value of the associations property.
     * 
     * @return
     *     possible object is
     *     {@link GenericAssociations }
     *     
     */
    public GenericAssociations getAssociations() {
        return associations;
    }

    /**
     * Sets the value of the associations property.
     * 
     * @param value
     *     allowed object is
     *     {@link GenericAssociations }
     *     
     */
    public void setAssociations(GenericAssociations value) {
        this.associations = value;
    }

    /**
     * Gets the value of the qualifiedName property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getQualifiedName() {
        return qualifiedName;
    }

    /**
     * Sets the value of the qualifiedName property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setQualifiedName(String value) {
        this.qualifiedName = value;
    }

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

}
