/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package com.bluexml.side.form;


/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Search Form</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link com.bluexml.side.form.SearchForm#getDataForm <em>Data Form</em>}</li>
 * </ul>
 * </p>
 *
 * @see com.bluexml.side.form.FormPackage#getSearchForm()
 * @model
 * @generated
 */
public interface SearchForm extends FormContainer {
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
	 * @see com.bluexml.side.form.FormPackage#getSearchForm_DataForm()
	 * @model
	 * @generated
	 */
	FormClass getDataForm();

	/**
	 * Sets the value of the '{@link com.bluexml.side.form.SearchForm#getDataForm <em>Data Form</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Data Form</em>' reference.
	 * @see #getDataForm()
	 * @generated
	 */
	void setDataForm(FormClass value);
		
} // SearchForm
