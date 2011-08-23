
package com.bluexml.xforms.controller.binding;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for fileFieldType complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="fileFieldType">
 *   &lt;complexContent>
 *     &lt;extension base="{}formFieldType">
 *       &lt;attribute name="inRepository" type="{http://www.w3.org/2001/XMLSchema}boolean" />
 *     &lt;/extension>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "fileFieldType")
public class FileFieldType
    extends FormFieldType
{

    @XmlAttribute
    protected Boolean inRepository;

    /**
     * Gets the value of the inRepository property.
     * 
     * @return
     *     possible object is
     *     {@link Boolean }
     *     
     */
    public Boolean isInRepository() {
        return inRepository;
    }

    /**
     * Sets the value of the inRepository property.
     * 
     * @param value
     *     allowed object is
     *     {@link Boolean }
     *     
     */
    public void setInRepository(Boolean value) {
        this.inRepository = value;
    }

}
