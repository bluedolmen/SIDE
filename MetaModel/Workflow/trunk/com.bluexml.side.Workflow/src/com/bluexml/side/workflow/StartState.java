/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package com.bluexml.side.workflow;


/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Start State</b></em>'.
 * <!-- end-user-doc -->
 *
 * <!-- begin-model-doc -->
 * Definition: The 'StartState' represent the first state of the workflow process. Note that a process without a start-state is valid, but cannot be executed.
 * 
 * Constraint/limit: 
 * - A worflow process must have only one 'StartState'.
 * - A 'Swimlane' must be associated with the 'StartState'.
 * 
 * Inherits: 
 * - UserTask.
 * 
 * 
 * <!-- end-model-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link com.bluexml.side.workflow.StartState#getInitiator <em>Initiator</em>}</li>
 * </ul>
 * </p>
 *
 * @see com.bluexml.side.workflow.WorkflowPackage#getStartState()
 * @model
 * @generated
 */
public interface StartState extends UserTask {
	/**
	 * Returns the value of the '<em><b>Initiator</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Initiator</em>' reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Initiator</em>' reference.
	 * @see #setInitiator(Swimlane)
	 * @see com.bluexml.side.workflow.WorkflowPackage#getStartState_Initiator()
	 * @model required="true"
	 * @generated
	 */
	Swimlane getInitiator();

	/**
	 * Sets the value of the '{@link com.bluexml.side.workflow.StartState#getInitiator <em>Initiator</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Initiator</em>' reference.
	 * @see #getInitiator()
	 * @generated
	 */
	void setInitiator(Swimlane value);

} // StartState
