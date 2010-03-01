
package com.bluexml.xforms.controller.binding;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for formFieldType complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="formFieldType">
 *   &lt;complexContent>
 *     &lt;extension base="{}fieldType">
 *       &lt;sequence>
 *         &lt;element name="alfrescoName" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="type" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="staticEnumType" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *       &lt;/sequence>
 *       &lt;attribute name="default" type="{http://www.w3.org/2001/XMLSchema}string" />
 *       &lt;attribute name="dummyValue" type="{http://www.w3.org/2001/XMLSchema}string" />
 *       &lt;attribute name="multiple" type="{http://www.w3.org/2001/XMLSchema}boolean" />
 *       &lt;attribute name="readOnly" type="{http://www.w3.org/2001/XMLSchema}boolean" />
 *       &lt;attribute name="searchEnum" type="{http://www.w3.org/2001/XMLSchema}boolean" />
 *     &lt;/extension>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "formFieldType", propOrder = {
    "alfrescoName",
    "type",
    "staticEnumType"
})
public class FormFieldType
    extends FieldType
{

    @XmlElement(required = true)
    protected String alfrescoName;
    @XmlElement(required = true)
    protected String type;
    protected String staticEnumType;
    @XmlAttribute(name = "default")
    protected String _default;
    @XmlAttribute
    protected String dummyValue;
    @XmlAttribute
    protected Boolean multiple;
    @XmlAttribute
    protected Boolean readOnly;
    @XmlAttribute
    protected Boolean searchEnum;

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
     * Gets the value of the staticEnumType property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getStaticEnumType() {
        return staticEnumType;
    }

    /**
     * Sets the value of the staticEnumType property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setStaticEnumType(String value) {
        this.staticEnumType = value;
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
     * Gets the value of the dummyValue property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getDummyValue() {
        return dummyValue;
    }

    /**
     * Sets the value of the dummyValue property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setDummyValue(String value) {
        this.dummyValue = value;
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

    /**
     * Gets the value of the searchEnum property.
     * 
     * @return
     *     possible object is
     *     {@link Boolean }
     *     
     */
    public Boolean isSearchEnum() {
        return searchEnum;
    }

    /**
     * Sets the value of the searchEnum property.
     * 
     * @param value
     *     allowed object is
     *     {@link Boolean }
     *     
     */
    public void setSearchEnum(Boolean value) {
        this.searchEnum = value;
    }

}
