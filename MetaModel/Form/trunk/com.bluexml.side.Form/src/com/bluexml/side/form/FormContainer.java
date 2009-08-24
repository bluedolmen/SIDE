/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package com.bluexml.side.form;


/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Form Container</b></em>'.
 * <!-- end-user-doc -->
 *
 * <!-- begin-model-doc -->
 * Definition: The 'FormContainer' represents a form and is used as a container for elements. FormClass and FormWorkflow inherits of FormContainer. FormContainer is an abstract element and so does not directly appear in the Form modeler.
 * A FormCollection is at least composed of one FormContainer.
 * Inherits: FormGroup.
 * <!-- end-model-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link com.bluexml.side.form.FormContainer#getName <em>Name</em>}</li>
 * </ul>
 * </p>
 *
 * @see com.bluexml.side.form.FormPackage#getFormContainer()
 * @model abstract="true"
 *        annotation="http://www.eclipse.org/emf/2002/Ecore constraints='validName'"
 *        annotation="http://www.bluexml.com/OCL validName='not self.name.oclIsUndefined() and self.name <> \'\''"
 * @generated
 */
public interface FormContainer extends FormGroup {

	/**
	 * Returns the value of the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Name</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * Definition: The 'name' attribute specifies the name of the form container.
	 * Constraint/limit: this name must be filled up and unique in the form model between all the FormContainer (FormClass and FormWorkflow)
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>Name</em>' attribute.
	 * @see #setName(String)
	 * @see com.bluexml.side.form.FormPackage#getFormContainer_Name()
	 * @model
	 * @generated
	 */
	String getName();

	/**
	 * Sets the value of the '{@link com.bluexml.side.form.FormContainer#getName <em>Name</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Name</em>' attribute.
	 * @see #getName()
	 * @generated
	 */
	void setName(String value);

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * Definition: to get the name of the FormContainer (FormClass or FormWorkflow)
	 * <!-- end-model-doc -->
	 * @model kind="operation"
	 *        annotation="http://www.bluexml.com/OCL body='if self.label.oclIsUndefined() or self.label.size() = 0 then\r self.name \relse\r self.label \rendif'"
	 * @generated
	 */
	String getLabel();
} // FormContainer
