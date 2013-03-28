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
