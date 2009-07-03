/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package com.bluexml.side.application;


/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Deployer Configuration</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link com.bluexml.side.application.DeployerConfiguration#getDeployerName <em>Deployer Name</em>}</li>
 * </ul>
 * </p>
 *
 * @see com.bluexml.side.application.ApplicationPackage#getDeployerConfiguration()
 * @model
 * @generated
 */
public interface DeployerConfiguration extends ComponantConfiguration {

	/**
	 * Returns the value of the '<em><b>Deployer Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Deployer Name</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Deployer Name</em>' attribute.
	 * @see #setDeployerName(String)
	 * @see com.bluexml.side.application.ApplicationPackage#getDeployerConfiguration_DeployerName()
	 * @model
	 * @generated
	 */
	String getDeployerName();

	/**
	 * Sets the value of the '{@link com.bluexml.side.application.DeployerConfiguration#getDeployerName <em>Deployer Name</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Deployer Name</em>' attribute.
	 * @see #getDeployerName()
	 * @generated
	 */
	void setDeployerName(String value);

} // DeployerConfiguration
