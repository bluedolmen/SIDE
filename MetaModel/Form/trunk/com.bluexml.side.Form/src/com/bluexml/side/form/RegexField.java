/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package com.bluexml.side.form;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Regex Field</b></em>'.
 * <!-- end-user-doc -->
 *
 * <!-- begin-model-doc -->
 * Definition: The ‘RegexField’ allows setting a constraint on the value of an input field. The langage used to express this constraint is standard Java Regular expression (java.util.regex). 
 * To create a RegexField (not available in the Data model), you must use a ‘Transform to RegexField’ operation on the field where you want to make a control of the user input.
 * Example: “.+@.+\\.[a-z]+” to validate the structure of an email
 * 
 * 
 * Inherits: CharField.
 * <!-- end-model-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link com.bluexml.side.form.RegexField#getRegex <em>Regex</em>}</li>
 * </ul>
 * </p>
 *
 * @see com.bluexml.side.form.FormPackage#getRegexField()
 * @model
 * @generated
 */
public interface RegexField extends CharField {
	/**
	 * Returns the value of the '<em><b>Regex</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Regex</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * Definition: the regex expression.
	 * Constraint/Limit: the value must be conform to the Standard Java Regular Expression semantic (java.util.regex).
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>Regex</em>' attribute.
	 * @see #setRegex(String)
	 * @see com.bluexml.side.form.FormPackage#getRegexField_Regex()
	 * @model
	 * @generated
	 */
	String getRegex();

	/**
	 * Sets the value of the '{@link com.bluexml.side.form.RegexField#getRegex <em>Regex</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Regex</em>' attribute.
	 * @see #getRegex()
	 * @generated
	 */
	void setRegex(String value);

} // RegexField
