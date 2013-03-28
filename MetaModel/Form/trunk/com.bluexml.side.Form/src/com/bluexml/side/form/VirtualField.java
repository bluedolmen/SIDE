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
 * A representation of the model object '<em><b>Virtual Field</b></em>'.
 * <!-- end-user-doc -->
 *
 * <!-- begin-model-doc -->
 * Definition: When you have a form inside another (see ModelChoiceField) you can drag & drop fields to an upper form. These fields won’t be moved from their original form but will be virtualized on the targeted form. A Virtual Field is just a link to the original field.
 * Example: let’s consider a Person data type, a ContactInformation data type with a Fax attribute and  an association from Person to ContactInformation. If you want to insert in the Person form, three Fax input fields, you will use the Virtual field mechanism to move up directly the Fax attribute inside Person form.
 * Inherits: Field.
 * 
 * <!-- end-model-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link com.bluexml.side.form.VirtualField#getLink <em>Link</em>}</li>
 * </ul>
 * </p>
 *
 * @see com.bluexml.side.form.FormPackage#getVirtualField()
 * @model annotation="http://www.eclipse.org/emf/2002/Ecore constraints='NoLinkForVirtualField'"
 *        annotation="http://www.bluexml.com/OCL NoLinkForVirtualField='not self.link.oclIsUndefined()'"
 * @generated
 */
public interface VirtualField extends Field {
	/**
	 * Returns the value of the '<em><b>Link</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Link</em>' reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Link</em>' reference.
	 * @see #setLink(Field)
	 * @see com.bluexml.side.form.FormPackage#getVirtualField_Link()
	 * @model
	 * @generated
	 */
	Field getLink();

	/**
	 * Sets the value of the '{@link com.bluexml.side.form.VirtualField#getLink <em>Link</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Link</em>' reference.
	 * @see #getLink()
	 * @generated
	 */
	void setLink(Field value);

} // VirtualField
