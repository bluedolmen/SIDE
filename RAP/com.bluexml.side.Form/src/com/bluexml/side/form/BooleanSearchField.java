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
 * A representation of the model object '<em><b>Boolean Search Field</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link com.bluexml.side.form.BooleanSearchField#getOperators <em>Operators</em>}</li>
 *   <li>{@link com.bluexml.side.form.BooleanSearchField#getDefaultOperator <em>Default Operator</em>}</li>
 * </ul>
 * </p>
 *
 * @see com.bluexml.side.form.FormPackage#getBooleanSearchField()
 * @model
 * @generated
 */
public interface BooleanSearchField extends SearchField {
	/**
	 * Returns the value of the '<em><b>Operators</b></em>' attribute list.
	 * The list contents are of type {@link com.bluexml.side.form.BooleanFieldSearchOperators}.
	 * The literals are from the enumeration {@link com.bluexml.side.form.BooleanFieldSearchOperators}.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Operators</em>' attribute list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Operators</em>' attribute list.
	 * @see com.bluexml.side.form.BooleanFieldSearchOperators
	 * @see com.bluexml.side.form.FormPackage#getBooleanSearchField_Operators()
	 * @model
	 * @generated
	 */
	EList<BooleanFieldSearchOperators> getOperators();

	/**
	 * Returns the value of the '<em><b>Default Operator</b></em>' attribute.
	 * The literals are from the enumeration {@link com.bluexml.side.form.BooleanFieldSearchOperators}.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Default Operator</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Default Operator</em>' attribute.
	 * @see com.bluexml.side.form.BooleanFieldSearchOperators
	 * @see #setDefaultOperator(BooleanFieldSearchOperators)
	 * @see com.bluexml.side.form.FormPackage#getBooleanSearchField_DefaultOperator()
	 * @model
	 * @generated
	 */
	BooleanFieldSearchOperators getDefaultOperator();

	/**
	 * Sets the value of the '{@link com.bluexml.side.form.BooleanSearchField#getDefaultOperator <em>Default Operator</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Default Operator</em>' attribute.
	 * @see com.bluexml.side.form.BooleanFieldSearchOperators
	 * @see #getDefaultOperator()
	 * @generated
	 */
	void setDefaultOperator(BooleanFieldSearchOperators value);
		
} // BooleanSearchField
