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
 * A representation of the model object '<em><b>Sorting</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link com.bluexml.side.view.Sorting#getSortOrder <em>Sort Order</em>}</li>
 *   <li>{@link com.bluexml.side.view.Sorting#isSorted <em>Sorted</em>}</li>
 * </ul>
 * </p>
 *
 * @see com.bluexml.side.view.ViewPackage#getSorting()
 * @model
 * @generated
 */
public interface Sorting extends EObject {
	/**
	 * Returns the value of the '<em><b>Sort Order</b></em>' attribute.
	 * The literals are from the enumeration {@link com.bluexml.side.view.SortOrder}.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * must be set if sortable
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>Sort Order</em>' attribute.
	 * @see com.bluexml.side.view.SortOrder
	 * @see #setSortOrder(SortOrder)
	 * @see com.bluexml.side.view.ViewPackage#getSorting_SortOrder()
	 * @model
	 * @generated
	 */
	SortOrder getSortOrder();

	/**
	 * Sets the value of the '{@link com.bluexml.side.view.Sorting#getSortOrder <em>Sort Order</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Sort Order</em>' attribute.
	 * @see com.bluexml.side.view.SortOrder
	 * @see #getSortOrder()
	 * @generated
	 */
	void setSortOrder(SortOrder value);

	/**
	 * Returns the value of the '<em><b>Sorted</b></em>' attribute.
	 * The default value is <code>"false"</code>.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Sorted</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Sorted</em>' attribute.
	 * @see #setSorted(boolean)
	 * @see com.bluexml.side.view.ViewPackage#getSorting_Sorted()
	 * @model default="false"
	 * @generated
	 */
	boolean isSorted();

	/**
	 * Sets the value of the '{@link com.bluexml.side.view.Sorting#isSorted <em>Sorted</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Sorted</em>' attribute.
	 * @see #isSorted()
	 * @generated
	 */
	void setSorted(boolean value);
		
} // Sorting
