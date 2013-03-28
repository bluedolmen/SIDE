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

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for classType complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="classType">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="package" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="name" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="alfrescoName" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="attribute" type="{}attributeType" maxOccurs="unbounded"/>
 *         &lt;element name="aspect" type="{}aspectType" maxOccurs="unbounded"/>
 *         &lt;element name="association" type="{}associationType" maxOccurs="unbounded"/>
 *         &lt;element name="parentClass" type="{}classType" minOccurs="0"/>
 *         &lt;element name="subClass" type="{}classType" maxOccurs="unbounded"/>
 *       &lt;/sequence>
 *       &lt;attribute name="rendered" type="{http://www.w3.org/2001/XMLSchema}boolean" />
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "classType", propOrder = {
    "_package",
    "name",
    "alfrescoName",
    "attribute",
    "aspect",
    "association",
    "parentClass",
    "subClass"
})
public class ClassType {

    @XmlElement(name = "package", required = true)
    protected String _package;
    @XmlElement(required = true)
    protected String name;
    @XmlElement(required = true)
    protected String alfrescoName;
    @XmlElement(required = true)
    protected List<AttributeType> attribute;
    @XmlElement(required = true)
    protected List<AspectType> aspect;
    @XmlElement(required = true)
    protected List<AssociationType> association;
    protected ClassType parentClass;
    @XmlElement(required = true)
    protected List<ClassType> subClass;
    @XmlAttribute
    protected Boolean rendered;

    /**
     * Gets the value of the package property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getPackage() {
        return _package;
    }

    /**
     * Sets the value of the package property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setPackage(String value) {
        this._package = value;
    }

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
     * Gets the value of the attribute property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the attribute property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getAttribute().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link AttributeType }
     * 
     * 
     */
    public List<AttributeType> getAttribute() {
        if (attribute == null) {
            attribute = new ArrayList<AttributeType>();
        }
        return this.attribute;
    }

    /**
     * Gets the value of the aspect property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the aspect property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getAspect().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link AspectType }
     * 
     * 
     */
    public List<AspectType> getAspect() {
        if (aspect == null) {
            aspect = new ArrayList<AspectType>();
        }
        return this.aspect;
    }

    /**
     * Gets the value of the association property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the association property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getAssociation().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link AssociationType }
     * 
     * 
     */
    public List<AssociationType> getAssociation() {
        if (association == null) {
            association = new ArrayList<AssociationType>();
        }
        return this.association;
    }

    /**
     * Gets the value of the parentClass property.
     * 
     * @return
     *     possible object is
     *     {@link ClassType }
     *     
     */
    public ClassType getParentClass() {
        return parentClass;
    }

    /**
     * Sets the value of the parentClass property.
     * 
     * @param value
     *     allowed object is
     *     {@link ClassType }
     *     
     */
    public void setParentClass(ClassType value) {
        this.parentClass = value;
    }

    /**
     * Gets the value of the subClass property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the subClass property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getSubClass().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link ClassType }
     * 
     * 
     */
    public List<ClassType> getSubClass() {
        if (subClass == null) {
            subClass = new ArrayList<ClassType>();
        }
        return this.subClass;
    }

    /**
     * Gets the value of the rendered property.
     * 
     * @return
     *     possible object is
     *     {@link Boolean }
     *     
     */
    public Boolean isRendered() {
        return rendered;
    }

    /**
     * Sets the value of the rendered property.
     * 
     * @param value
     *     allowed object is
     *     {@link Boolean }
     *     
     */
    public void setRendered(Boolean value) {
        this.rendered = value;
    }

}
