
package com.bluexml.xforms.controller.binding;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for formType complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="formType">
 *   &lt;complexContent>
 *     &lt;extension base="{}canisterType">
 *       &lt;sequence>
 *         &lt;element name="field" type="{}formFieldType" maxOccurs="unbounded"/>
 *         &lt;element name="virtual" type="{}virtualFieldType" maxOccurs="unbounded"/>
 *         &lt;element name="reference" type="{}referenceType" maxOccurs="unbounded"/>
 *         &lt;element name="fileField" type="{}fileFieldType" maxOccurs="unbounded"/>
 *         &lt;element name="modelChoice" type="{}modelChoiceType" maxOccurs="unbounded"/>
 *         &lt;element name="realClass" type="{}classType"/>
 *       &lt;/sequence>
 *       &lt;attribute name="contentEnabled" type="{http://www.w3.org/2001/XMLSchema}boolean" />
 *     &lt;/extension>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "formType", propOrder = {
    "field",
    "virtual",
    "reference",
    "fileField",
    "modelChoice",
    "realClass"
})
public class FormType
    extends CanisterType
{

    @XmlElement(required = true)
    protected List<FormFieldType> field;
    @XmlElement(required = true)
    protected List<VirtualFieldType> virtual;
    @XmlElement(required = true)
    protected List<ReferenceType> reference;
    @XmlElement(required = true)
    protected List<FileFieldType> fileField;
    @XmlElement(required = true)
    protected List<ModelChoiceType> modelChoice;
    @XmlElement(required = true)
    protected ClassType realClass;
    @XmlAttribute
    protected Boolean contentEnabled;

    /**
     * Gets the value of the field property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the field property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getField().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link FormFieldType }
     * 
     * 
     */
    public List<FormFieldType> getField() {
        if (field == null) {
            field = new ArrayList<FormFieldType>();
        }
        return this.field;
    }

    /**
     * Gets the value of the virtual property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the virtual property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getVirtual().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link VirtualFieldType }
     * 
     * 
     */
    public List<VirtualFieldType> getVirtual() {
        if (virtual == null) {
            virtual = new ArrayList<VirtualFieldType>();
        }
        return this.virtual;
    }

    /**
     * Gets the value of the reference property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the reference property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getReference().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link ReferenceType }
     * 
     * 
     */
    public List<ReferenceType> getReference() {
        if (reference == null) {
            reference = new ArrayList<ReferenceType>();
        }
        return this.reference;
    }

    /**
     * Gets the value of the fileField property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the fileField property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getFileField().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link FileFieldType }
     * 
     * 
     */
    public List<FileFieldType> getFileField() {
        if (fileField == null) {
            fileField = new ArrayList<FileFieldType>();
        }
        return this.fileField;
    }

    /**
     * Gets the value of the modelChoice property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the modelChoice property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getModelChoice().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link ModelChoiceType }
     * 
     * 
     */
    public List<ModelChoiceType> getModelChoice() {
        if (modelChoice == null) {
            modelChoice = new ArrayList<ModelChoiceType>();
        }
        return this.modelChoice;
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
     * Gets the value of the contentEnabled property.
     * 
     * @return
     *     possible object is
     *     {@link Boolean }
     *     
     */
    public Boolean isContentEnabled() {
        return contentEnabled;
    }

    /**
     * Sets the value of the contentEnabled property.
     * 
     * @param value
     *     allowed object is
     *     {@link Boolean }
     *     
     */
    public void setContentEnabled(Boolean value) {
        this.contentEnabled = value;
    }

}
