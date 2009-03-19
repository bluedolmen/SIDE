/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package com.bluexml.side.application;

import org.eclipse.emf.common.util.EList;

import org.eclipse.emf.ecore.EObject;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Configuration Element</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link com.bluexml.side.application.ConfigurationElement#getId_metamodel <em>Id metamodel</em>}</li>
 *   <li>{@link com.bluexml.side.application.ConfigurationElement#getId_generator <em>Id generator</em>}</li>
 *   <li>{@link com.bluexml.side.application.ConfigurationElement#getOptions <em>Options</em>}</li>
 * </ul>
 * </p>
 *
 * @see com.bluexml.side.application.ApplicationPackage#getConfigurationElement()
 * @model
 * @generated
 */
public interface ConfigurationElement extends EObject {
	/**
	 * Returns the value of the '<em><b>Id metamodel</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Id metamodel</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Id metamodel</em>' attribute.
	 * @see #setId_metamodel(String)
	 * @see com.bluexml.side.application.ApplicationPackage#getConfigurationElement_Id_metamodel()
	 * @model
	 * @generated
	 */
	String getId_metamodel();

	/**
	 * Sets the value of the '{@link com.bluexml.side.application.ConfigurationElement#getId_metamodel <em>Id metamodel</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Id metamodel</em>' attribute.
	 * @see #getId_metamodel()
	 * @generated
	 */
	void setId_metamodel(String value);

	/**
	 * Returns the value of the '<em><b>Id generator</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Id generator</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Id generator</em>' attribute.
	 * @see #setId_generator(String)
	 * @see com.bluexml.side.application.ApplicationPackage#getConfigurationElement_Id_generator()
	 * @model
	 * @generated
	 */
	String getId_generator();

	/**
	 * Sets the value of the '{@link com.bluexml.side.application.ConfigurationElement#getId_generator <em>Id generator</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Id generator</em>' attribute.
	 * @see #getId_generator()
	 * @generated
	 */
	void setId_generator(String value);

	/**
	 * Returns the value of the '<em><b>Options</b></em>' containment reference list.
	 * The list contents are of type {@link com.bluexml.side.application.Option}.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Options</em>' containment reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Options</em>' containment reference list.
	 * @see com.bluexml.side.application.ApplicationPackage#getConfigurationElement_Options()
	 * @model containment="true"
	 * @generated
	 */
	EList<Option> getOptions();

} // ConfigurationElement
