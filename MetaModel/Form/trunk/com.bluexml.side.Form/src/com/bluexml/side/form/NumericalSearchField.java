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
 * A representation of the model object '<em><b>Numerical Search Field</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link com.bluexml.side.form.NumericalSearchField#getOperators <em>Operators</em>}</li>
 *   <li>{@link com.bluexml.side.form.NumericalSearchField#getDefaultOperator <em>Default Operator</em>}</li>
 * </ul>
 * </p>
 *
 * @see com.bluexml.side.form.FormPackage#getNumericalSearchField()
 * @model
 * @generated
 */
public interface NumericalSearchField extends SearchField {
	/**
	 * Returns the value of the '<em><b>Operators</b></em>' attribute list.
	 * The list contents are of type {@link com.bluexml.side.form.NumericalFieldSearchOperators}.
	 * The literals are from the enumeration {@link com.bluexml.side.form.NumericalFieldSearchOperators}.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Operators</em>' attribute list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Operators</em>' attribute list.
	 * @see com.bluexml.side.form.NumericalFieldSearchOperators
	 * @see com.bluexml.side.form.FormPackage#getNumericalSearchField_Operators()
	 * @model
	 * @generated
	 */
	EList<NumericalFieldSearchOperators> getOperators();

	/**
	 * Returns the value of the '<em><b>Default Operator</b></em>' attribute.
	 * The literals are from the enumeration {@link com.bluexml.side.form.NumericalFieldSearchOperators}.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Default Operator</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Default Operator</em>' attribute.
	 * @see com.bluexml.side.form.NumericalFieldSearchOperators
	 * @see #setDefaultOperator(NumericalFieldSearchOperators)
	 * @see com.bluexml.side.form.FormPackage#getNumericalSearchField_DefaultOperator()
	 * @model
	 * @generated
	 */
	NumericalFieldSearchOperators getDefaultOperator();

	/**
	 * Sets the value of the '{@link com.bluexml.side.form.NumericalSearchField#getDefaultOperator <em>Default Operator</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Default Operator</em>' attribute.
	 * @see com.bluexml.side.form.NumericalFieldSearchOperators
	 * @see #getDefaultOperator()
	 * @generated
	 */
	void setDefaultOperator(NumericalFieldSearchOperators value);
		
} // NumericalSearchField
