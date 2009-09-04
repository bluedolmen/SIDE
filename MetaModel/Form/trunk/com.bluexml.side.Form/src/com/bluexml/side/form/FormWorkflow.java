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
 * Definition: The Form Workflow is a form associated to a task of a Workflow. This means that this Form Workflow allows to shape the form used to enter the tasks attributes. If no attributes have been defined for a task, only the buttons (next task, accept, reject, ...) allowing to make progress the workflow will be displayed on the form.
 * Constraint/Limit: the FormWorkflow is linked to a single task of a workflow.
 * Operations: 
 * - The operation 'Load Ressources...' enables to select and import Workflow Diagrams in the Form Model. One of the first steps to modelize workflow form is to connect this form diagram with the workflow model through this operation: Right click on the editor and select 'Load Resource' to choose a workflow model (with suffix .workflow and not a '.workflowdi' workflow diagram). The Real Task attribute of Form Workflow may then be set up by selecting the name of the targeted task of the loaded workflow model. 
 * - The operation 'Initialize form task' creates the default form from a selected Task. The selected Task is set up with the attribute 'Real task'. This action creates all fields corresponding to the Attributes defined in the workflow model: this default form may then be refined according to the needs.
 * - The operation ‘Refresh Outline’ refreshes an outline html view of the form. This view is useful to see the transformations made on the form before generating it and to have a general view of what will be generated. It is important to note that this outline view does not give the final generated form and the shape may be different according to the targeted technology.
 * - The operation ‘Restore’ allows restoring a field which has been previously deleted.
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
 * @model annotation="http://www.bluexml.com/OCL ClassMustMatchWithProcessContentType='(not(self.getContainer().oclIsUndefined()) and not(self.getContainer().oclAsType(WorkflowFormCollection).linked_process.contentType.oclIsUndefined()) and (not self.DataForm.oclIsUndefined())) implies (self.getContainer().oclAsType(WorkflowFormCollection).linked_process.contentType = self.DataForm.real_class)'"
 *        annotation="http://www.eclipse.org/emf/2002/Ecore constraints='ClassMustMatchWithProcessContentType'"
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
