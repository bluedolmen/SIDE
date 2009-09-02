/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package com.bluexml.side.workflow;


/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Node</b></em>'.
 * <!-- end-user-doc -->
 *
 * <!-- begin-model-doc -->
 * Definition: A 'Node' is a task that are no performed by 'Swimlane'. The type node serves the situation where you want to write your own code in a node.
 * 
 * Constraint/limit: 'Attributes' can not be associated to a 'Node'.
 * 
 * Inherits: 
 * - TransitionTask.
 * <!-- end-model-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link com.bluexml.side.workflow.Node#getAction <em>Action</em>}</li>
 * </ul>
 * </p>
 *
 * @see com.bluexml.side.workflow.WorkflowPackage#getNode()
 * @model
 * @generated
 */
public interface Node extends TransitionTask {
	/**
	 * Returns the value of the '<em><b>Action</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Action</em>' containment reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Action</em>' containment reference.
	 * @see #setAction(Action)
	 * @see com.bluexml.side.workflow.WorkflowPackage#getNode_Action()
	 * @model containment="true" ordered="false"
	 * @generated
	 */
	Action getAction();

	/**
	 * Sets the value of the '{@link com.bluexml.side.workflow.Node#getAction <em>Action</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Action</em>' containment reference.
	 * @see #getAction()
	 * @generated
	 */
	void setAction(Action value);

} // Node
