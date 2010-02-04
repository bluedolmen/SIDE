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
 * A representation of the model object '<em><b>Choice Search Field</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link com.bluexml.side.form.ChoiceSearchField#getOperators <em>Operators</em>}</li>
 *   <li>{@link com.bluexml.side.form.ChoiceSearchField#getDefaultOperator <em>Default Operator</em>}</li>
 * </ul>
 * </p>
 *
 * @see com.bluexml.side.form.FormPackage#getChoiceSearchField()
 * @model
 * @generated
 */
public interface ChoiceSearchField extends SearchField {
	/**
	 * Returns the value of the '<em><b>Operators</b></em>' attribute list.
	 * The list contents are of type {@link com.bluexml.side.form.ChoiceFieldSearchOperators}.
	 * The literals are from the enumeration {@link com.bluexml.side.form.ChoiceFieldSearchOperators}.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Operators</em>' attribute list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Operators</em>' attribute list.
	 * @see com.bluexml.side.form.ChoiceFieldSearchOperators
	 * @see com.bluexml.side.form.FormPackage#getChoiceSearchField_Operators()
	 * @model
	 * @generated
	 */
	EList<ChoiceFieldSearchOperators> getOperators();

	/**
	 * Returns the value of the '<em><b>Default Operator</b></em>' attribute.
	 * The literals are from the enumeration {@link com.bluexml.side.form.ChoiceFieldSearchOperators}.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Default Operator</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Default Operator</em>' attribute.
	 * @see com.bluexml.side.form.ChoiceFieldSearchOperators
	 * @see #setDefaultOperator(ChoiceFieldSearchOperators)
	 * @see com.bluexml.side.form.FormPackage#getChoiceSearchField_DefaultOperator()
	 * @model
	 * @generated
	 */
	ChoiceFieldSearchOperators getDefaultOperator();

	/**
	 * Sets the value of the '{@link com.bluexml.side.form.ChoiceSearchField#getDefaultOperator <em>Default Operator</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Default Operator</em>' attribute.
	 * @see com.bluexml.side.form.ChoiceFieldSearchOperators
	 * @see #getDefaultOperator()
	 * @generated
	 */
	void setDefaultOperator(ChoiceFieldSearchOperators value);
		
} // ChoiceSearchField
