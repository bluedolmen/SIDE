/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package com.bluexml.side.workflow;

import org.eclipse.emf.ecore.EObject;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Timer</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link com.bluexml.side.workflow.Timer#getName <em>Name</em>}</li>
 *   <li>{@link com.bluexml.side.workflow.Timer#getDuedate <em>Duedate</em>}</li>
 *   <li>{@link com.bluexml.side.workflow.Timer#getAction <em>Action</em>}</li>
 * </ul>
 * </p>
 *
 * @see com.bluexml.side.workflow.WorkflowPackage#getTimer()
 * @model
 * @generated
 */
public interface Timer extends EObject {
	/**
	 * Returns the value of the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Name</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Name</em>' attribute.
	 * @see #setName(String)
	 * @see com.bluexml.side.workflow.WorkflowPackage#getTimer_Name()
	 * @model unique="false" required="true" ordered="false"
	 * @generated
	 */
	String getName();

	/**
	 * Sets the value of the '{@link com.bluexml.side.workflow.Timer#getName <em>Name</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Name</em>' attribute.
	 * @see #getName()
	 * @generated
	 */
	void setName(String value);

	/**
	 * Returns the value of the '<em><b>Duedate</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Duedate</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Duedate</em>' attribute.
	 * @see #setDuedate(String)
	 * @see com.bluexml.side.workflow.WorkflowPackage#getTimer_Duedate()
	 * @model unique="false" required="true" ordered="false"
	 * @generated
	 */
	String getDuedate();

	/**
	 * Sets the value of the '{@link com.bluexml.side.workflow.Timer#getDuedate <em>Duedate</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Duedate</em>' attribute.
	 * @see #getDuedate()
	 * @generated
	 */
	void setDuedate(String value);

	/**
	 * Returns the value of the '<em><b>Action</b></em>' containment reference.
	 * It is bidirectional and its opposite is '{@link com.bluexml.side.workflow.Action#getParentTimer <em>Parent Timer</em>}'.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Action</em>' containment reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Action</em>' containment reference.
	 * @see #setAction(Action)
	 * @see com.bluexml.side.workflow.WorkflowPackage#getTimer_Action()
	 * @see com.bluexml.side.workflow.Action#getParentTimer
	 * @model opposite="parentTimer" containment="true" required="true" ordered="false"
	 * @generated
	 */
	Action getAction();

	/**
	 * Sets the value of the '{@link com.bluexml.side.workflow.Timer#getAction <em>Action</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Action</em>' containment reference.
	 * @see #getAction()
	 * @generated
	 */
	void setAction(Action value);

} // Timer
