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

package com.bluexml.xforms.controller.binding;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for attributeType complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="attributeType">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="name" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="alfrescoName" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="type" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="enumQName" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="classType" type="{}classType" minOccurs="0"/>
 *         &lt;element name="aspectType" type="{}aspectType" minOccurs="0"/>
 *       &lt;/sequence>
 *       &lt;attribute name="default" type="{http://www.w3.org/2001/XMLSchema}string" />
 *       &lt;attribute name="fieldSize" type="{http://www.w3.org/2001/XMLSchema}string" />
 *       &lt;attribute name="dynamicEnum" type="{http://www.w3.org/2001/XMLSchema}boolean" />
 *       &lt;attribute name="multiple" type="{http://www.w3.org/2001/XMLSchema}boolean" />
 *       &lt;attribute name="readOnly" type="{http://www.w3.org/2001/XMLSchema}boolean" />
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "attributeType", propOrder = {
    "name",
    "alfrescoName",
    "type",
    "enumQName",
    "classType",
    "aspectType"
})
public class AttributeType {

    @XmlElement(required = true)
    protected String name;
    @XmlElement(required = true)
    protected String alfrescoName;
    @XmlElement(required = true)
    protected String type;
    protected String enumQName;
    protected ClassType classType;
    protected AspectType aspectType;
    @XmlAttribute(name = "default")
    protected String _default;
    @XmlAttribute
    protected String fieldSize;
    @XmlAttribute
    protected Boolean dynamicEnum;
    @XmlAttribute
    protected Boolean multiple;
    @XmlAttribute
    protected Boolean readOnly;

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
     * Gets the value of the alfrescoName property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getAlfrescoName() {
        return alfrescoName;
    }

    /**
     * Sets the value of the alfrescoName property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setAlfrescoName(String value) {
        this.alfrescoName = value;
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
     * Gets the value of the enumQName property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getEnumQName() {
        return enumQName;
    }

    /**
     * Sets the value of the enumQName property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setEnumQName(String value) {
        this.enumQName = value;
    }

    /**
     * Gets the value of the classType property.
     * 
     * @return
     *     possible object is
     *     {@link ClassType }
     *     
     */
    public ClassType getClassType() {
        return classType;
    }

    /**
     * Sets the value of the classType property.
     * 
     * @param value
     *     allowed object is
     *     {@link ClassType }
     *     
     */
    public void setClassType(ClassType value) {
        this.classType = value;
    }

    /**
     * Gets the value of the aspectType property.
     * 
     * @return
     *     possible object is
     *     {@link AspectType }
     *     
     */
    public AspectType getAspectType() {
        return aspectType;
    }

    /**
     * Sets the value of the aspectType property.
     * 
     * @param value
     *     allowed object is
     *     {@link AspectType }
     *     
     */
    public void setAspectType(AspectType value) {
        this.aspectType = value;
    }

    /**
     * Gets the value of the default property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getDefault() {
        return _default;
    }

    /**
     * Sets the value of the default property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setDefault(String value) {
        this._default = value;
    }

    /**
     * Gets the value of the fieldSize property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getFieldSize() {
        return fieldSize;
    }

    /**
     * Sets the value of the fieldSize property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setFieldSize(String value) {
        this.fieldSize = value;
    }

    /**
     * Gets the value of the dynamicEnum property.
     * 
     * @return
     *     possible object is
     *     {@link Boolean }
     *     
     */
    public Boolean isDynamicEnum() {
        return dynamicEnum;
    }

    /**
     * Sets the value of the dynamicEnum property.
     * 
     * @param value
     *     allowed object is
     *     {@link Boolean }
     *     
     */
    public void setDynamicEnum(Boolean value) {
        this.dynamicEnum = value;
    }

    /**
     * Gets the value of the multiple property.
     * 
     * @return
     *     possible object is
     *     {@link Boolean }
     *     
     */
    public Boolean isMultiple() {
        return multiple;
    }

    /**
     * Sets the value of the multiple property.
     * 
     * @param value
     *     allowed object is
     *     {@link Boolean }
     *     
     */
    public void setMultiple(Boolean value) {
        this.multiple = value;
    }

    /**
     * Gets the value of the readOnly property.
     * 
     * @return
     *     possible object is
     *     {@link Boolean }
     *     
     */
    public Boolean isReadOnly() {
        return readOnly;
    }

    /**
     * Sets the value of the readOnly property.
     * 
     * @param value
     *     allowed object is
     *     {@link Boolean }
     *     
     */
    public void setReadOnly(Boolean value) {
        this.readOnly = value;
    }

}
