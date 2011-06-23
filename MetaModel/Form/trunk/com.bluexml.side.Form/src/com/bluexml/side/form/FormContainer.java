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
 *
 * @see com.bluexml.side.form.FormPackage#getFormContainer()
 * @model abstract="true"
 *        annotation="http://www.eclipse.org/emf/2002/Ecore constraints='validName TwoFormsWithSameName'"
 *        annotation="http://www.bluexml.com/OCL TwoFormsWithSameName='FormContainer.allInstances()->select(a | a.id = self.id and a <> self)->size() = 0'"
 * @generated
 */
public interface FormContainer extends FormGroup {
} // FormContainer
