/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package com.bluexml.side.form;


/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Workflow Form Collection</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link com.bluexml.side.form.WorkflowFormCollection#getLinked_process <em>Linked process</em>}</li>
 * </ul>
 * </p>
 *
 * @see com.bluexml.side.form.FormPackage#getWorkflowFormCollection()
 * @model
 * @generated
 */
public interface WorkflowFormCollection extends FormCollection {
	/**
	 * Returns the value of the '<em><b>Linked process</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Linked process</em>' reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Linked process</em>' reference.
	 * @see #setLinked_process(com.bluexml.side.workflow.Process)
	 * @see com.bluexml.side.form.FormPackage#getWorkflowFormCollection_Linked_process()
	 * @model
	 * @generated
	 */
	com.bluexml.side.workflow.Process getLinked_process();

	/**
	 * Sets the value of the '{@link com.bluexml.side.form.WorkflowFormCollection#getLinked_process <em>Linked process</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Linked process</em>' reference.
	 * @see #getLinked_process()
	 * @generated
	 */
	void setLinked_process(com.bluexml.side.workflow.Process value);
		
} // WorkflowFormCollection
