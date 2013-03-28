/*
    Copyright (C) 2007-20013  BlueXML - www.bluexml.com

    This program is free software: you can redistribute it and/or modify
    it under the terms of the GNU Affero General Public License as
    published by the Free Software Foundation, either version 3 of the
    License, or (at your option) any later version.

    This program is distributed in the hope that it will be useful,
    but WITHOUT ANY WARRANTY; without even the implied warranty of
    MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
    GNU Affero General Public License for more details.

    You should have received a copy of the GNU Affero General Public License
    along with this program.  If not, see <http://www.gnu.org/licenses/>.
*/

package com.bluexml.side.util.libs.maven.pom;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;


/**
 * 
 *         This elements describes all that pertains to distribution for a project.
 *         It is primarily used for deployment of artifacts and the site
 *         produced by the build.
 *       
 * 
 * <p>Java class for DistributionManagement complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="DistributionManagement">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;all>
 *         &lt;element name="repository" type="{http://maven.apache.org/POM/4.0.0}DeploymentRepository" minOccurs="0"/>
 *         &lt;element name="snapshotRepository" type="{http://maven.apache.org/POM/4.0.0}DeploymentRepository" minOccurs="0"/>
 *         &lt;element name="site" type="{http://maven.apache.org/POM/4.0.0}Site" minOccurs="0"/>
 *         &lt;element name="downloadUrl" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="relocation" type="{http://maven.apache.org/POM/4.0.0}Relocation" minOccurs="0"/>
 *         &lt;element name="status" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *       &lt;/all>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "DistributionManagement", propOrder = {

})
public class DistributionManagement {

    protected DeploymentRepository repository;
    protected DeploymentRepository snapshotRepository;
    protected Site site;
    protected String downloadUrl;
    protected Relocation relocation;
    protected String status;

    /**
     * Gets the value of the repository property.
     * 
     * @return
     *     possible object is
     *     {@link DeploymentRepository }
     *     
     */
    public DeploymentRepository getRepository() {
        return repository;
    }

    /**
     * Sets the value of the repository property.
     * 
     * @param value
     *     allowed object is
     *     {@link DeploymentRepository }
     *     
     */
    public void setRepository(DeploymentRepository value) {
        this.repository = value;
    }

    /**
     * Gets the value of the snapshotRepository property.
     * 
     * @return
     *     possible object is
     *     {@link DeploymentRepository }
     *     
     */
    public DeploymentRepository getSnapshotRepository() {
        return snapshotRepository;
    }

    /**
     * Sets the value of the snapshotRepository property.
     * 
     * @param value
     *     allowed object is
     *     {@link DeploymentRepository }
     *     
     */
    public void setSnapshotRepository(DeploymentRepository value) {
        this.snapshotRepository = value;
    }

    /**
     * Gets the value of the site property.
     * 
     * @return
     *     possible object is
     *     {@link Site }
     *     
     */
    public Site getSite() {
        return site;
    }

    /**
     * Sets the value of the site property.
     * 
     * @param value
     *     allowed object is
     *     {@link Site }
     *     
     */
    public void setSite(Site value) {
        this.site = value;
    }

    /**
     * Gets the value of the downloadUrl property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getDownloadUrl() {
        return downloadUrl;
    }

    /**
     * Sets the value of the downloadUrl property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setDownloadUrl(String value) {
        this.downloadUrl = value;
    }

    /**
     * Gets the value of the relocation property.
     * 
     * @return
     *     possible object is
     *     {@link Relocation }
     *     
     */
    public Relocation getRelocation() {
        return relocation;
    }

    /**
     * Sets the value of the relocation property.
     * 
     * @param value
     *     allowed object is
     *     {@link Relocation }
     *     
     */
    public void setRelocation(Relocation value) {
        this.relocation = value;
    }

    /**
     * Gets the value of the status property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getStatus() {
        return status;
    }

    /**
     * Sets the value of the status property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setStatus(String value) {
        this.status = value;
    }

}
