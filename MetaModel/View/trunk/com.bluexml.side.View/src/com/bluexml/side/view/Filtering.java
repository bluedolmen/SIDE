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
package com.bluexml.side.view;

import org.eclipse.emf.ecore.EObject;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Filtering</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link com.bluexml.side.view.Filtering#getDefaultFilterValue <em>Default Filter Value</em>}</li>
 * </ul>
 * </p>
 *
 * @see com.bluexml.side.view.ViewPackage#getFiltering()
 * @model
 * @generated
 */
public interface Filtering extends EObject {
	/**
	 * Returns the value of the '<em><b>Default Filter Value</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Default Filter Value</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Default Filter Value</em>' attribute.
	 * @see #setDefaultFilterValue(String)
	 * @see com.bluexml.side.view.ViewPackage#getFiltering_DefaultFilterValue()
	 * @model
	 * @generated
	 */
	String getDefaultFilterValue();

	/**
	 * Sets the value of the '{@link com.bluexml.side.view.Filtering#getDefaultFilterValue <em>Default Filter Value</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Default Filter Value</em>' attribute.
	 * @see #getDefaultFilterValue()
	 * @generated
	 */
	void setDefaultFilterValue(String value);
		
} // Filtering
