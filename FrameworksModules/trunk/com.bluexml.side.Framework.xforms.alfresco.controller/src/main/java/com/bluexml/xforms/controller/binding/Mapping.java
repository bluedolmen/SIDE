
package com.bluexml.xforms.controller.binding;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementRef;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for anonymous complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType>
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="genInfo" type="{}genInfoType"/>
 *         &lt;element name="enum" type="{}enumType" maxOccurs="unbounded"/>
 *         &lt;element name="aspect" type="{}aspectType" maxOccurs="unbounded"/>
 *         &lt;element name="class" type="{}classType" maxOccurs="unbounded"/>
 *         &lt;element ref="{}canister" maxOccurs="unbounded"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "", propOrder = {
    "genInfo",
    "_enum",
    "aspect",
    "clazz",
    "canister"
})
@XmlRootElement(name = "mapping")
public class Mapping {

    @XmlElement(required = true)
    protected GenInfoType genInfo;
    @XmlElement(name = "enum", required = true)
    protected List<EnumType> _enum;
    @XmlElement(required = true)
    protected List<AspectType> aspect;
    @XmlElement(name = "class", required = true)
    protected List<ClassType> clazz;
    @XmlElementRef(name = "canister", type = JAXBElement.class)
    protected List<JAXBElement<? extends CanisterType>> canister;

    /**
     * Gets the value of the genInfo property.
     * 
     * @return
     *     possible object is
     *     {@link GenInfoType }
     *     
     */
    public GenInfoType getGenInfo() {
        return genInfo;
    }

    /**
     * Sets the value of the genInfo property.
     * 
     * @param value
     *     allowed object is
     *     {@link GenInfoType }
     *     
     */
    public void setGenInfo(GenInfoType value) {
        this.genInfo = value;
    }

    /**
     * Gets the value of the enum property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the enum property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getEnum().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link EnumType }
     * 
     * 
     */
    public List<EnumType> getEnum() {
        if (_enum == null) {
            _enum = new ArrayList<EnumType>();
        }
        return this._enum;
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
     * Gets the value of the clazz property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the clazz property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getClazz().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link ClassType }
     * 
     * 
     */
    public List<ClassType> getClazz() {
        if (clazz == null) {
            clazz = new ArrayList<ClassType>();
        }
        return this.clazz;
    }

    /**
     * Gets the value of the canister property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the canister property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getCanister().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link JAXBElement }{@code <}{@link WorkflowTaskType }{@code >}
     * {@link JAXBElement }{@code <}{@link CanisterType }{@code >}
     * {@link JAXBElement }{@code <}{@link SearchFormType }{@code >}
     * {@link JAXBElement }{@code <}{@link FormType }{@code >}
     * 
     * 
     */
    public List<JAXBElement<? extends CanisterType>> getCanister() {
        if (canister == null) {
            canister = new ArrayList<JAXBElement<? extends CanisterType>>();
        }
        return this.canister;
    }

}
