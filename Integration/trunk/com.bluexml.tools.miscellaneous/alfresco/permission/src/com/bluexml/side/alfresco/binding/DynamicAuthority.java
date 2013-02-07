
package com.bluexml.side.alfresco.binding;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;
import javax.xml.bind.annotation.adapters.NormalizedStringAdapter;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;


/**
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "")
@XmlRootElement(name = "dynamicAuthority")
public class DynamicAuthority {

    @XmlAttribute(required = true)
    @XmlJavaTypeAdapter(NormalizedStringAdapter.class)
    protected String evaluator;

    /**
     * Gets the value of the evaluator property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getEvaluator() {
        return evaluator;
    }

    /**
     * Sets the value of the evaluator property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setEvaluator(String value) {
        this.evaluator = value;
    }

}
