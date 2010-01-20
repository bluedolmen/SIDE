
package com.bluexml.xforms.controller.binding;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElements;
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
 *       &lt;sequence maxOccurs="unbounded" minOccurs="0">
 *         &lt;choice>
 *           &lt;element name="create" type="{}GenericCreate"/>
 *           &lt;element name="update" type="{}GenericUpdate"/>
 *           &lt;element name="delete" type="{}GenericDelete"/>
 *           &lt;element name="requester" type="{}ServiceRequestSource"/>
 *           &lt;element name="attach" type="{}AttachContentInfo"/>
 *         &lt;/choice>
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
    "createOrUpdateOrDelete"
})
@XmlRootElement(name = "batch")
public class Batch {

    @XmlElements({
        @XmlElement(name = "create", type = GenericCreate.class),
        @XmlElement(name = "update", type = GenericUpdate.class),
        @XmlElement(name = "delete", type = GenericDelete.class),
        @XmlElement(name = "attach", type = AttachContentInfo.class),
        @XmlElement(name = "requester", type = ServiceRequestSource.class)
    })
    protected List<Object> createOrUpdateOrDelete;

    /**
     * Gets the value of the createOrUpdateOrDelete property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the createOrUpdateOrDelete property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getCreateOrUpdateOrDelete().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link GenericCreate }
     * {@link GenericUpdate }
     * {@link GenericDelete }
     * {@link AttachContentInfo }
     * {@link ServiceRequestSource }
     * 
     * 
     */
    public List<Object> getCreateOrUpdateOrDelete() {
        if (createOrUpdateOrDelete == null) {
            createOrUpdateOrDelete = new ArrayList<Object>();
        }
        return this.createOrUpdateOrDelete;
    }

}
