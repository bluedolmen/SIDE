/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package com.bluexml.side.view;


/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Field</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link com.bluexml.side.view.Field#getPatern <em>Patern</em>}</li>
 *   <li>{@link com.bluexml.side.view.Field#getPatrenLanguage <em>Patren Language</em>}</li>
 * </ul>
 * </p>
 *
 * @see com.bluexml.side.view.ViewPackage#getField()
 * @model abstract="true"
 * @generated
 */
public interface Field extends FieldElement {
	/**
	 * Returns the value of the '<em><b>Patern</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Patern</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Patern</em>' attribute.
	 * @see #setPatern(String)
	 * @see com.bluexml.side.view.ViewPackage#getField_Patern()
	 * @model
	 * @generated
	 */
	String getPatern();

	/**
	 * Sets the value of the '{@link com.bluexml.side.view.Field#getPatern <em>Patern</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Patern</em>' attribute.
	 * @see #getPatern()
	 * @generated
	 */
	void setPatern(String value);

	/**
	 * Returns the value of the '<em><b>Patren Language</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Patren Language</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Patren Language</em>' attribute.
	 * @see #setPatrenLanguage(String)
	 * @see com.bluexml.side.view.ViewPackage#getField_PatrenLanguage()
	 * @model
	 * @generated
	 */
	String getPatrenLanguage();

	/**
	 * Sets the value of the '{@link com.bluexml.side.view.Field#getPatrenLanguage <em>Patren Language</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Patren Language</em>' attribute.
	 * @see #getPatrenLanguage()
	 * @generated
	 */
	void setPatrenLanguage(String value);
		
} // Field
