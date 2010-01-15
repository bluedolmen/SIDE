
package com.bluexml.xforms.controller.binding;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
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
 *         &lt;element name="name" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="taskId" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="dataForm" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="title" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="startTask" type="{http://www.w3.org/2001/XMLSchema}boolean"/>
 *         &lt;element name="actorId" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="pooledActors" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/extension>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "workflowTaskType", propOrder = {
    "name",
    "taskId",
    "dataForm",
    "title",
    "startTask",
    "actorId",
    "pooledActors"
})
public class WorkflowTaskType
    extends CanisterType
{

    @XmlElement(required = true)
    protected String name;
    @XmlElement(required = true)
    protected String taskId;
    @XmlElement(required = true)
    protected String dataForm;
    @XmlElement(required = true)
    protected String title;
    protected boolean startTask;
    protected String actorId;
    protected String pooledActors;

    /**
     * Gets the value of the name property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getName() {
        return name;
    }

    /**
     * Sets the value of the name property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setName(String value) {
        this.name = value;
    }

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
     * Gets the value of the startTask property.
     * 
     */
    public boolean isStartTask() {
        return startTask;
    }

    /**
     * Sets the value of the startTask property.
     * 
     */
    public void setStartTask(boolean value) {
        this.startTask = value;
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

}
