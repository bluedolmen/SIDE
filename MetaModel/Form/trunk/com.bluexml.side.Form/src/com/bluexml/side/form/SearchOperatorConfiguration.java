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

import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EObject;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Search Operator Configuration</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link com.bluexml.side.form.SearchOperatorConfiguration#getDefaultOperator <em>Default Operator</em>}</li>
 *   <li>{@link com.bluexml.side.form.SearchOperatorConfiguration#getProposedOperators <em>Proposed Operators</em>}</li>
 * </ul>
 * </p>
 *
 * @see com.bluexml.side.form.FormPackage#getSearchOperatorConfiguration()
 * @model
 * @generated
 */
public interface SearchOperatorConfiguration extends EObject {
	/**
	 * Returns the value of the '<em><b>Default Operator</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Default Operator</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Default Operator</em>' attribute.
	 * @see #setDefaultOperator(String)
	 * @see com.bluexml.side.form.FormPackage#getSearchOperatorConfiguration_DefaultOperator()
	 * @model
	 * @generated
	 */
	String getDefaultOperator();

	/**
	 * Sets the value of the '{@link com.bluexml.side.form.SearchOperatorConfiguration#getDefaultOperator <em>Default Operator</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Default Operator</em>' attribute.
	 * @see #getDefaultOperator()
	 * @generated
	 */
	void setDefaultOperator(String value);

	/**
	 * Returns the value of the '<em><b>Proposed Operators</b></em>' attribute list.
	 * The list contents are of type {@link java.lang.String}.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Proposed Operators</em>' attribute list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Proposed Operators</em>' attribute list.
	 * @see com.bluexml.side.form.FormPackage#getSearchOperatorConfiguration_ProposedOperators()
	 * @model
	 * @generated
	 */
	EList<String> getProposedOperators();
		
} // SearchOperatorConfiguration
