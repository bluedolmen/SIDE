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

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Aggregation</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link KerblueForms.Aggregation#getClasses <em>Classes</em>}</li>
 *   <li>{@link KerblueForms.Aggregation#isAggregate_instances <em>Aggregate instances</em>}</li>
 * </ul>
 * </p>
 *
 * @see KerblueForms.KerblueFormsPackage#getAggregation()
 * @model
 * @generated
 */
public interface Aggregation extends FormElement {
	/**
	 * Returns the value of the '<em><b>Classes</b></em>' reference list.
	 * The list contents are of type {@link KerblueForms.FormClass}.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Classes</em>' reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Classes</em>' reference list.
	 * @see KerblueForms.KerblueFormsPackage#getAggregation_Classes()
	 * @model required="true"
	 * @generated
	 */
	EList<FormClass> getClasses();

	/**
	 * Returns the value of the '<em><b>Aggregate instances</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Aggregate instances</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Aggregate instances</em>' attribute.
	 * @see #setAggregate_instances(boolean)
	 * @see KerblueForms.KerblueFormsPackage#getAggregation_Aggregate_instances()
	 * @model
	 * @generated
	 */
	boolean isAggregate_instances();

	/**
	 * Sets the value of the '{@link KerblueForms.Aggregation#isAggregate_instances <em>Aggregate instances</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Aggregate instances</em>' attribute.
	 * @see #isAggregate_instances()
	 * @generated
	 */
	void setAggregate_instances(boolean value);

} // Aggregation
