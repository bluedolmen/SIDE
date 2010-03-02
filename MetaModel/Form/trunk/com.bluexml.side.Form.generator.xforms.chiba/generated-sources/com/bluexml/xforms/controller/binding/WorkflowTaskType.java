
package com.bluexml.xforms.controller.binding;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for workflowTaskType complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="workflowTaskType">
 *   &lt;complexContent>
 *     &lt;extension base="{}canisterType">
 *       &lt;sequence>
 *         &lt;element name="taskId" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="dataForm" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="title" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="actorId" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="pooledActors" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="field" type="{}formFieldType" maxOccurs="unbounded"/>
 *         &lt;element name="modelChoice" type="{}modelChoiceType" maxOccurs="unbounded"/>
 *       &lt;/sequence>
 *       &lt;attribute name="startTask" type="{http://www.w3.org/2001/XMLSchema}boolean" />
 *     &lt;/extension>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "workflowTaskType", propOrder = {
    "taskId",
    "dataForm",
    "title",
    "actorId",
    "pooledActors",
    "field",
    "modelChoice"
})
public class WorkflowTaskType
    extends CanisterType
{

    @XmlElement(required = true)
    protected String taskId;
    @XmlElement(required = true)
    protected String dataForm;
    @XmlElement(required = true)
    protected String title;
    protected String actorId;
    protected String pooledActors;
    @XmlElement(required = true)
    protected List<FormFieldType> field;
    @XmlElement(required = true)
    protected List<ModelChoiceType> modelChoice;
    @XmlAttribute
    protected Boolean startTask;

    /**
     * Gets the value of the taskId property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getTaskId() {
        return taskId;
    }

    /**
     * Sets the value of the taskId property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setTaskId(String value) {
        this.taskId = value;
    }

    /**
     * Gets the value of the dataForm property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getDataForm() {
        return dataForm;
    }

    /**
     * Sets the value of the dataForm property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setDataForm(String value) {
        this.dataForm = value;
    }

    /**
     * Gets the value of the title property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getTitle() {
        return title;
    }

    /**
     * Sets the value of the title property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setTitle(String value) {
        this.title = value;
    }

    /**
     * Gets the value of the actorId property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getActorId() {
        return actorId;
    }

    /**
     * Sets the value of the actorId property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setActorId(String value) {
        this.actorId = value;
    }

    /**
     * Gets the value of the pooledActors property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getPooledActors() {
        return pooledActors;
    }

    /**
     * Sets the value of the pooledActors property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setPooledActors(String value) {
        this.pooledActors = value;
    }

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
     * Gets the value of the startTask property.
     * 
     * @return
     *     possible object is
     *     {@link Boolean }
     *     
     */
    public Boolean isStartTask() {
        return startTask;
    }

    /**
     * Sets the value of the startTask property.
     * 
     * @param value
     *     allowed object is
     *     {@link Boolean }
     *     
     */
    public void setStartTask(Boolean value) {
        this.startTask = value;
    }

}
