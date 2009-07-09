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

} // ModuleConstraint
