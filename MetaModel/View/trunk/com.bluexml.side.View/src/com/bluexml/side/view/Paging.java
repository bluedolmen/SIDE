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
 * A representation of the model object '<em><b>Paging</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link com.bluexml.side.view.Paging#getPaginationStyle <em>Pagination Style</em>}</li>
 *   <li>{@link com.bluexml.side.view.Paging#getMaxItems <em>Max Items</em>}</li>
 *   <li>{@link com.bluexml.side.view.Paging#getMaxPage <em>Max Page</em>}</li>
 * </ul>
 * </p>
 *
 * @see com.bluexml.side.view.ViewPackage#getPaging()
 * @model
 * @generated
 */
public interface Paging extends EObject {
	/**
	 * Returns the value of the '<em><b>Pagination Style</b></em>' attribute.
	 * The literals are from the enumeration {@link com.bluexml.side.view.PaginationStyle}.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Pagination Style</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Pagination Style</em>' attribute.
	 * @see com.bluexml.side.view.PaginationStyle
	 * @see #setPaginationStyle(PaginationStyle)
	 * @see com.bluexml.side.view.ViewPackage#getPaging_PaginationStyle()
	 * @model required="true"
	 * @generated
	 */
	PaginationStyle getPaginationStyle();

	/**
	 * Sets the value of the '{@link com.bluexml.side.view.Paging#getPaginationStyle <em>Pagination Style</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Pagination Style</em>' attribute.
	 * @see com.bluexml.side.view.PaginationStyle
	 * @see #getPaginationStyle()
	 * @generated
	 */
	void setPaginationStyle(PaginationStyle value);

	/**
	 * Returns the value of the '<em><b>Max Items</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * use -1 for unlimited
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>Max Items</em>' attribute.
	 * @see #setMaxItems(int)
	 * @see com.bluexml.side.view.ViewPackage#getPaging_MaxItems()
	 * @model
	 * @generated
	 */
	int getMaxItems();

	/**
	 * Sets the value of the '{@link com.bluexml.side.view.Paging#getMaxItems <em>Max Items</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Max Items</em>' attribute.
	 * @see #getMaxItems()
	 * @generated
	 */
	void setMaxItems(int value);

	/**
	 * Returns the value of the '<em><b>Max Page</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Max Page</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Max Page</em>' attribute.
	 * @see #setMaxPage(int)
	 * @see com.bluexml.side.view.ViewPackage#getPaging_MaxPage()
	 * @model
	 * @generated
	 */
	int getMaxPage();

	/**
	 * Sets the value of the '{@link com.bluexml.side.view.Paging#getMaxPage <em>Max Page</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Max Page</em>' attribute.
	 * @see #getMaxPage()
	 * @generated
	 */
	void setMaxPage(int value);
		
} // Paging
