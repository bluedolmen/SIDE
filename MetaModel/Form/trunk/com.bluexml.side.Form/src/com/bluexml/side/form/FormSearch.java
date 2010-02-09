/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package com.bluexml.side.form;


/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Search</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link com.bluexml.side.form.FormSearch#getDataForm <em>Data Form</em>}</li>
 *   <li>{@link com.bluexml.side.form.FormSearch#getCombinationOperator <em>Combination Operator</em>}</li>
 * </ul>
 * </p>
 *
 * @see com.bluexml.side.form.FormPackage#getFormSearch()
 * @model
 * @generated
 */
public interface FormSearch extends FormContainer {
	/**
	 * Returns the value of the '<em><b>Data Form</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Data Form</em>' reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Data Form</em>' reference.
	 * @see #setDataForm(FormClass)
	 * @see com.bluexml.side.form.FormPackage#getFormSearch_DataForm()
	 * @model
	 * @generated
	 */
	FormClass getDataForm();

	/**
	 * Sets the value of the '{@link com.bluexml.side.form.FormSearch#getDataForm <em>Data Form</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Data Form</em>' reference.
	 * @see #getDataForm()
	 * @generated
	 */
	void setDataForm(FormClass value);

	/**
	 * Returns the value of the '<em><b>Combination Operator</b></em>' attribute.
	 * The literals are from the enumeration {@link com.bluexml.side.form.CombinationOperators}.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Combination Operator</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Combination Operator</em>' attribute.
	 * @see com.bluexml.side.form.CombinationOperators
	 * @see #setCombinationOperator(CombinationOperators)
	 * @see com.bluexml.side.form.FormPackage#getFormSearch_CombinationOperator()
	 * @model
	 * @generated
	 */
	CombinationOperators getCombinationOperator();

	/**
	 * Sets the value of the '{@link com.bluexml.side.form.FormSearch#getCombinationOperator <em>Combination Operator</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Combination Operator</em>' attribute.
	 * @see com.bluexml.side.form.CombinationOperators
	 * @see #getCombinationOperator()
	 * @generated
	 */
	void setCombinationOperator(CombinationOperators value);
		
} // FormSearch
