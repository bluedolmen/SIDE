
package com.bluexml.xforms.controller.binding;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for modelChoiceType complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="modelChoiceType">
 *   &lt;complexContent>
 *     &lt;extension base="{}fieldType">
 *       &lt;sequence>
 *         &lt;element name="alfrescoName" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="minBound" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *         &lt;element name="maxBound" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *         &lt;element name="realClass" type="{}classType"/>
 *         &lt;element name="target" type="{}formType" maxOccurs="unbounded"/>
 *       &lt;/sequence>
 *       &lt;attribute name="inline" type="{http://www.w3.org/2001/XMLSchema}boolean" />
 *       &lt;attribute name="fieldSize" type="{http://www.w3.org/2001/XMLSchema}string" />
 *     &lt;/extension>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "modelChoiceType", propOrder = {
    "alfrescoName",
    "minBound",
    "maxBound",
    "realClass",
    "target"
})
public class ModelChoiceType
    extends FieldType
{

    @XmlElement(required = true)
    protected String alfrescoName;
    protected int minBound;
    protected int maxBound;
    @XmlElement(required = true)
    protected ClassType realClass;
    @XmlElement(required = true)
    protected List<FormType> target;
    @XmlAttribute
    protected Boolean inline;
    @XmlAttribute
    protected String fieldSize;

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
     * Gets the value of the minBound property.
     * 
     */
    public int getMinBound() {
        return minBound;
    }

    /**
     * Sets the value of the minBound property.
     * 
     */
    public void setMinBound(int value) {
        this.minBound = value;
    }

    /**
     * Gets the value of the maxBound property.
     * 
     */
    public int getMaxBound() {
        return maxBound;
    }

    /**
     * Sets the value of the maxBound property.
     * 
     */
    public void setMaxBound(int value) {
        this.maxBound = value;
    }

    /**
     * Gets the value of the realClass property.
     * 
     * @return
     *     possible object is
     *     {@link ClassType }
     *     
     */
    public ClassType getRealClass() {
        return realClass;
    }

    /**
     * Sets the value of the realClass property.
     * 
     * @param value
     *     allowed object is
     *     {@link ClassType }
     *     
     */
    public void setRealClass(ClassType value) {
        this.realClass = value;
    }

    /**
     * Gets the value of the target property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the target property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getTarget().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link FormType }
     * 
     * 
     */
    public List<FormType> getTarget() {
        if (target == null) {
            target = new ArrayList<FormType>();
        }
        return this.target;
    }

    /**
     * Gets the value of the inline property.
     * 
     * @return
     *     possible object is
     *     {@link Boolean }
     *     
     */
    public Boolean isInline() {
        return inline;
    }

    /**
     * Sets the value of the inline property.
     * 
     * @param value
     *     allowed object is
     *     {@link Boolean }
     *     
     */
    public void setInline(Boolean value) {
        this.inline = value;
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

}
