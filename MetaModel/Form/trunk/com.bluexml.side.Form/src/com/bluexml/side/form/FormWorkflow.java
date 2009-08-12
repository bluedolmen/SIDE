/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package com.bluexml.side.form;


/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Form Workflow</b></em>'.
 * <!-- end-user-doc -->
 *
 * <!-- begin-model-doc -->
 * Definition: The Form Workflow is a form associated to a Workflow.
 * Operations: 
 * - The operation 'Load Ressources...' enables to select and import Workflow Diagrams in the Form Model.
 * Inherits:  FormContainer.
 * <!-- end-model-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link com.bluexml.side.form.FormWorkflow#getDataForm <em>Data Form</em>}</li>
 * </ul>
 * </p>
 *
 * @see com.bluexml.side.form.FormPackage#getFormWorkflow()
 * @model
 * @generated
 */
public interface FormWorkflow extends FormContainer {

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
	 * @see com.bluexml.side.form.FormPackage#getFormWorkflow_DataForm()
	 * @model
	 * @generated
	 */
	FormClass getDataForm();

	/**
	 * Sets the value of the '{@link com.bluexml.side.form.FormWorkflow#getDataForm <em>Data Form</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Data Form</em>' reference.
	 * @see #getDataForm()
	 * @generated
	 */
	void setDataForm(FormClass value);
} // FormWorkflow
