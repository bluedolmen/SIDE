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
 * A representation of the model object '<em><b>URL Field</b></em>'.
 * <!-- end-user-doc -->
 *
 * <!-- begin-model-doc -->
 * Definition: The 'URLField' specifies a url address.
 * Constraint/limit: 
 * -	If the input is not a url, the URLField background color will change.
 * -	If you want to add a control of the input, transform to a RegexField and use a Java Regular Expression
 * Operations: same as CharField
 * Example: http://www.bluexml.com
 * Inherits: CharField
 * <!-- end-model-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link com.bluexml.side.form.URLField#isVerify_exists <em>Verify exists</em>}</li>
 * </ul>
 * </p>
 *
 * @see com.bluexml.side.form.FormPackage#getURLField()
 * @model
 * @generated
 */
public interface URLField extends CharField {
	/**
	 * Returns the value of the '<em><b>Verify exists</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Verify exists</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * Definition: if true, the input value of url will be checked by the modeler; if the url cannot be accessed, an error message is displayed.
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>Verify exists</em>' attribute.
	 * @see #setVerify_exists(boolean)
	 * @see com.bluexml.side.form.FormPackage#getURLField_Verify_exists()
	 * @model
	 * @generated
	 */
	boolean isVerify_exists();

	/**
	 * Sets the value of the '{@link com.bluexml.side.form.URLField#isVerify_exists <em>Verify exists</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Verify exists</em>' attribute.
	 * @see #isVerify_exists()
	 * @generated
	 */
	void setVerify_exists(boolean value);

} // URLField
