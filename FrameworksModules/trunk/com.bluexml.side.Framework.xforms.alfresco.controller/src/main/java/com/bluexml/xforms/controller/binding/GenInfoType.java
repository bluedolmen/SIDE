
package com.bluexml.xforms.controller.binding;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for genInfoType complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="genInfoType">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="readOnlyFormsSuffix" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="debugMode" type="{http://www.w3.org/2001/XMLSchema}boolean"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "genInfoType", propOrder = {
    "readOnlyFormsSuffix",
    "debugMode"
})
public class GenInfoType {

    @XmlElement(required = true)
    protected String readOnlyFormsSuffix;
    protected boolean debugMode;

    /**
     * Gets the value of the readOnlyFormsSuffix property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getReadOnlyFormsSuffix() {
        return readOnlyFormsSuffix;
    }

    /**
     * Sets the value of the readOnlyFormsSuffix property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setReadOnlyFormsSuffix(String value) {
        this.readOnlyFormsSuffix = value;
    }

    /**
     * Gets the value of the debugMode property.
     * 
     */
    public boolean isDebugMode() {
        return debugMode;
    }

    /**
     * Sets the value of the debugMode property.
     * 
     */
    public void setDebugMode(boolean value) {
        this.debugMode = value;
    }

}
