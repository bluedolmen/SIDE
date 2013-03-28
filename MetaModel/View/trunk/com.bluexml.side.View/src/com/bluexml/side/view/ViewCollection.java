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

import org.eclipse.emf.common.util.EList;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Collection</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link com.bluexml.side.view.ViewCollection#getViews <em>Views</em>}</li>
 *   <li>{@link com.bluexml.side.view.ViewCollection#getComposedViews <em>Composed Views</em>}</li>
 * </ul>
 * </p>
 *
 * @see com.bluexml.side.view.ViewPackage#getViewCollection()
 * @model annotation="http://www.eclipse.org/emf/2002/Ecore constraints='nameNotNull'"
 *        annotation="http://www.bluexml.com/OCL nameNotNull='not self.name.oclIsUndefined() and self.name <> \'\''"
 * @generated
 */
public interface ViewCollection extends com.bluexml.side.common.Package {
	/**
	 * Returns the value of the '<em><b>Views</b></em>' containment reference list.
	 * The list contents are of type {@link com.bluexml.side.view.AbstractView}.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Views</em>' containment reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Views</em>' containment reference list.
	 * @see com.bluexml.side.view.ViewPackage#getViewCollection_Views()
	 * @model containment="true"
	 * @generated
	 */
	EList<AbstractView> getViews();

	/**
	 * Returns the value of the '<em><b>Composed Views</b></em>' containment reference list.
	 * The list contents are of type {@link com.bluexml.side.view.ComposedView}.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Composed Views</em>' reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Composed Views</em>' containment reference list.
	 * @see com.bluexml.side.view.ViewPackage#getViewCollection_ComposedViews()
	 * @model containment="true"
	 * @generated
	 */
	EList<ComposedView> getComposedViews();

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @model kind="operation"
	 *        annotation="http://www.bluexml.com/OCL description='method to get all instances of AbstractView' body='ViewCollection.allInstances().views ->union(ViewCollection.allInstances().composedViews)'"
	 * @generated
	 */
	EList<AbstractView> getAllViews();

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @model kind="operation"
	 *        annotation="http://www.bluexml.com/OCL description='method to get all instances of AbstractView' body='AbstractView.allInstances()'"
	 * @generated
	 */
	EList<AbstractView> getAllViewsAndSubViews();
		
} // ViewCollection
