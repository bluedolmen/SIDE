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
 * A representation of the model object '<em><b>Char Search Field</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link com.bluexml.side.form.CharSearchField#getOperators <em>Operators</em>}</li>
 *   <li>{@link com.bluexml.side.form.CharSearchField#getDefaultOperator <em>Default Operator</em>}</li>
 * </ul>
 * </p>
 *
 * @see com.bluexml.side.form.FormPackage#getCharSearchField()
 * @model
 * @generated
 */
public interface CharSearchField extends SearchField {
	/**
	 * Returns the value of the '<em><b>Operators</b></em>' attribute list.
	 * The list contents are of type {@link com.bluexml.side.form.CharFieldSearchOperators}.
	 * The literals are from the enumeration {@link com.bluexml.side.form.CharFieldSearchOperators}.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Operators</em>' attribute list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Operators</em>' attribute list.
	 * @see com.bluexml.side.form.CharFieldSearchOperators
	 * @see com.bluexml.side.form.FormPackage#getCharSearchField_Operators()
	 * @model
	 * @generated
	 */
	EList<CharFieldSearchOperators> getOperators();

	/**
	 * Returns the value of the '<em><b>Default Operator</b></em>' attribute.
	 * The literals are from the enumeration {@link com.bluexml.side.form.CharFieldSearchOperators}.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Default Operator</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Default Operator</em>' attribute.
	 * @see com.bluexml.side.form.CharFieldSearchOperators
	 * @see #setDefaultOperator(CharFieldSearchOperators)
	 * @see com.bluexml.side.form.FormPackage#getCharSearchField_DefaultOperator()
	 * @model
	 * @generated
	 */
	CharFieldSearchOperators getDefaultOperator();

	/**
	 * Sets the value of the '{@link com.bluexml.side.form.CharSearchField#getDefaultOperator <em>Default Operator</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Default Operator</em>' attribute.
	 * @see com.bluexml.side.form.CharFieldSearchOperators
	 * @see #getDefaultOperator()
	 * @generated
	 */
	void setDefaultOperator(CharFieldSearchOperators value);
		
} // CharSearchField
