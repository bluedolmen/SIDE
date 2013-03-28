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

import org.eclipse.emf.ecore.EObject;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Module Constraint</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link com.bluexml.side.application.ModuleConstraint#getModuleId <em>Module Id</em>}</li>
 *   <li>{@link com.bluexml.side.application.ModuleConstraint#getVersionMin <em>Version Min</em>}</li>
 *   <li>{@link com.bluexml.side.application.ModuleConstraint#getVersionMax <em>Version Max</em>}</li>
 *   <li>{@link com.bluexml.side.application.ModuleConstraint#getModuleType <em>Module Type</em>}</li>
 *   <li>{@link com.bluexml.side.application.ModuleConstraint#getTechnologyVersion <em>Technology Version</em>}</li>
 *   <li>{@link com.bluexml.side.application.ModuleConstraint#getClassifier <em>Classifier</em>}</li>
 * </ul>
 * </p>
 *
 * @see com.bluexml.side.application.ApplicationPackage#getModuleConstraint()
 * @model
 * @generated
 */
public interface ModuleConstraint extends EObject {
	/**
	 * Returns the value of the '<em><b>Module Id</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Module Id</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Module Id</em>' attribute.
	 * @see #setModuleId(String)
	 * @see com.bluexml.side.application.ApplicationPackage#getModuleConstraint_ModuleId()
	 * @model required="true"
	 * @generated
	 */
	String getModuleId();

	/**
	 * Sets the value of the '{@link com.bluexml.side.application.ModuleConstraint#getModuleId <em>Module Id</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Module Id</em>' attribute.
	 * @see #getModuleId()
	 * @generated
	 */
	void setModuleId(String value);

	/**
	 * Returns the value of the '<em><b>Version Min</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Version Min</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Version Min</em>' attribute.
	 * @see #setVersionMin(String)
	 * @see com.bluexml.side.application.ApplicationPackage#getModuleConstraint_VersionMin()
	 * @model
	 * @generated
	 */
	String getVersionMin();

	/**
	 * Sets the value of the '{@link com.bluexml.side.application.ModuleConstraint#getVersionMin <em>Version Min</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Version Min</em>' attribute.
	 * @see #getVersionMin()
	 * @generated
	 */
	void setVersionMin(String value);

	/**
	 * Returns the value of the '<em><b>Version Max</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Version Max</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Version Max</em>' attribute.
	 * @see #setVersionMax(String)
	 * @see com.bluexml.side.application.ApplicationPackage#getModuleConstraint_VersionMax()
	 * @model
	 * @generated
	 */
	String getVersionMax();

	/**
	 * Sets the value of the '{@link com.bluexml.side.application.ModuleConstraint#getVersionMax <em>Version Max</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Version Max</em>' attribute.
	 * @see #getVersionMax()
	 * @generated
	 */
	void setVersionMax(String value);

	/**
	 * Returns the value of the '<em><b>Module Type</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Module Type</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Module Type</em>' attribute.
	 * @see #setModuleType(String)
	 * @see com.bluexml.side.application.ApplicationPackage#getModuleConstraint_ModuleType()
	 * @model required="true"
	 * @generated
	 */
	String getModuleType();

	/**
	 * Sets the value of the '{@link com.bluexml.side.application.ModuleConstraint#getModuleType <em>Module Type</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Module Type</em>' attribute.
	 * @see #getModuleType()
	 * @generated
	 */
	void setModuleType(String value);

	/**
	 * Returns the value of the '<em><b>Technology Version</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Technology Version</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Technology Version</em>' attribute.
	 * @see #setTechnologyVersion(String)
	 * @see com.bluexml.side.application.ApplicationPackage#getModuleConstraint_TechnologyVersion()
	 * @model required="true"
	 * @generated
	 */
	String getTechnologyVersion();

	/**
	 * Sets the value of the '{@link com.bluexml.side.application.ModuleConstraint#getTechnologyVersion <em>Technology Version</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Technology Version</em>' attribute.
	 * @see #getTechnologyVersion()
	 * @generated
	 */
	void setTechnologyVersion(String value);

	/**
	 * Returns the value of the '<em><b>Classifier</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Classifier</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Classifier</em>' attribute.
	 * @see #setClassifier(String)
	 * @see com.bluexml.side.application.ApplicationPackage#getModuleConstraint_Classifier()
	 * @model
	 * @generated
	 */
	String getClassifier();

	/**
	 * Sets the value of the '{@link com.bluexml.side.application.ModuleConstraint#getClassifier <em>Classifier</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Classifier</em>' attribute.
	 * @see #getClassifier()
	 * @generated
	 */
	void setClassifier(String value);

} // ModuleConstraint
