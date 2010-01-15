
package com.bluexml.xforms.controller.binding;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
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
 *       &lt;sequence>
 *         &lt;element name="inRepository" type="{http://www.w3.org/2001/XMLSchema}boolean"/>
 *       &lt;/sequence>
 *     &lt;/extension>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "fileFieldType", propOrder = {
    "inRepository"
})
public class FileFieldType
    extends FormFieldType
{

    protected boolean inRepository;

    /**
     * Gets the value of the inRepository property.
     * 
     */
    public boolean isInRepository() {
        return inRepository;
    }

    /**
     * Sets the value of the inRepository property.
     * 
     */
    public void setInRepository(boolean value) {
        this.inRepository = value;
    }

}
