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
 *   <li>{@link com.bluexml.side.application.DeployerConfiguration#isShared <em>Shared</em>}</li>
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

	/**
	 * Returns the value of the '<em><b>Shared</b></em>' attribute.
	 * The default value is <code>"false"</code>.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Shared</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Shared</em>' attribute.
	 * @see #setShared(boolean)
	 * @see com.bluexml.side.application.ApplicationPackage#getDeployerConfiguration_Shared()
	 * @model default="false"
	 * @generated
	 */
	boolean isShared();

	/**
	 * Sets the value of the '{@link com.bluexml.side.application.DeployerConfiguration#isShared <em>Shared</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Shared</em>' attribute.
	 * @see #isShared()
	 * @generated
	 */
	void setShared(boolean value);

} // DeployerConfiguration
