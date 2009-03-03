/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package com.bluexml.side.workflow;

import org.eclipse.emf.common.util.EList;

import org.eclipse.emf.ecore.EObject;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Action</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link com.bluexml.side.workflow.Action#getJavaClass <em>Java Class</em>}</li>
 *   <li>{@link com.bluexml.side.workflow.Action#getExpression <em>Expression</em>}</li>
 *   <li>{@link com.bluexml.side.workflow.Action#getScript <em>Script</em>}</li>
 *   <li>{@link com.bluexml.side.workflow.Action#getParentEvent <em>Parent Event</em>}</li>
 *   <li>{@link com.bluexml.side.workflow.Action#getParentTimer <em>Parent Timer</em>}</li>
 * </ul>
 * </p>
 *
 * @see com.bluexml.side.workflow.WorkflowPackage#getAction()
 * @model
 * @generated
 */
public interface Action extends EObject {
	/**
	 * Returns the value of the '<em><b>Java Class</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Java Class</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Java Class</em>' attribute.
	 * @see #setJavaClass(String)
	 * @see com.bluexml.side.workflow.WorkflowPackage#getAction_JavaClass()
	 * @model unique="false" required="true" ordered="false"
	 * @generated
	 */
	String getJavaClass();

	/**
	 * Sets the value of the '{@link com.bluexml.side.workflow.Action#getJavaClass <em>Java Class</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Java Class</em>' attribute.
	 * @see #getJavaClass()
	 * @generated
	 */
	void setJavaClass(String value);

	/**
	 * Returns the value of the '<em><b>Expression</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Expression</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Expression</em>' attribute.
	 * @see #setExpression(String)
	 * @see com.bluexml.side.workflow.WorkflowPackage#getAction_Expression()
	 * @model unique="false" required="true" ordered="false"
	 * @generated
	 */
	String getExpression();

	/**
	 * Sets the value of the '{@link com.bluexml.side.workflow.Action#getExpression <em>Expression</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Expression</em>' attribute.
	 * @see #getExpression()
	 * @generated
	 */
	void setExpression(String value);

	/**
	 * Returns the value of the '<em><b>Script</b></em>' containment reference list.
	 * The list contents are of type {@link com.bluexml.side.workflow.Script}.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Script</em>' containment reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Script</em>' containment reference list.
	 * @see com.bluexml.side.workflow.WorkflowPackage#getAction_Script()
	 * @model containment="true" ordered="false"
	 * @generated
	 */
	EList<Script> getScript();

	/**
	 * Returns the value of the '<em><b>Parent Event</b></em>' container reference.
	 * It is bidirectional and its opposite is '{@link com.bluexml.side.workflow.Event#getAction <em>Action</em>}'.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Parent Event</em>' container reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Parent Event</em>' container reference.
	 * @see #setParentEvent(Event)
	 * @see com.bluexml.side.workflow.WorkflowPackage#getAction_ParentEvent()
	 * @see com.bluexml.side.workflow.Event#getAction
	 * @model opposite="action" transient="false" ordered="false"
	 * @generated
	 */
	Event getParentEvent();

	/**
	 * Sets the value of the '{@link com.bluexml.side.workflow.Action#getParentEvent <em>Parent Event</em>}' container reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Parent Event</em>' container reference.
	 * @see #getParentEvent()
	 * @generated
	 */
	void setParentEvent(Event value);

	/**
	 * Returns the value of the '<em><b>Parent Timer</b></em>' container reference.
	 * It is bidirectional and its opposite is '{@link com.bluexml.side.workflow.Timer#getAction <em>Action</em>}'.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Parent Timer</em>' container reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Parent Timer</em>' container reference.
	 * @see #setParentTimer(Timer)
	 * @see com.bluexml.side.workflow.WorkflowPackage#getAction_ParentTimer()
	 * @see com.bluexml.side.workflow.Timer#getAction
	 * @model opposite="action" transient="false" ordered="false"
	 * @generated
	 */
	Timer getParentTimer();

	/**
	 * Sets the value of the '{@link com.bluexml.side.workflow.Action#getParentTimer <em>Parent Timer</em>}' container reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Parent Timer</em>' container reference.
	 * @see #getParentTimer()
	 * @generated
	 */
	void setParentTimer(Timer value);

} // Action
