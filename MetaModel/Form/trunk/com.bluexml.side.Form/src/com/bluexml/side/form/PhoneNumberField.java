/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package com.bluexml.side.form;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Phone Number Field</b></em>'.
 * <!-- end-user-doc -->
 *
 * <!-- begin-model-doc -->
 * Definition: The 'PhoneNumberField' specifies a phone number.
 * Inherits: CharField
 * <!-- end-model-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link com.bluexml.side.form.PhoneNumberField#getInput_formats <em>Input formats</em>}</li>
 * </ul>
 * </p>
 *
 * @see com.bluexml.side.form.FormPackage#getPhoneNumberField()
 * @model
 * @generated
 */
public interface PhoneNumberField extends CharField {
	/**
	 * Returns the value of the '<em><b>Input formats</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Input formats</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * Definition: The 'input_formats' attribute specifies a format for phone number which is conform to a Java Regular Expression (java.util.regex).
	 * 
	 * Example:
	 * 
	 * - (\\d-)?(\\d{3}-)?\\d{3}-\\d{4} to match an american phone number format.
	 * 
	 * 
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>Input formats</em>' attribute.
	 * @see #setInput_formats(String)
	 * @see com.bluexml.side.form.FormPackage#getPhoneNumberField_Input_formats()
	 * @model
	 * @generated
	 */
	String getInput_formats();

	/**
	 * Sets the value of the '{@link com.bluexml.side.form.PhoneNumberField#getInput_formats <em>Input formats</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Input formats</em>' attribute.
	 * @see #getInput_formats()
	 * @generated
	 */
	void setInput_formats(String value);

} // PhoneNumberField
