/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package com.bluexml.side.workflow;

import org.eclipse.emf.common.util.EList;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Process State</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link com.bluexml.side.workflow.ProcessState#getSubprocess <em>Subprocess</em>}</li>
 *   <li>{@link com.bluexml.side.workflow.ProcessState#getTransition <em>Transition</em>}</li>
 *   <li>{@link com.bluexml.side.workflow.ProcessState#getEvent <em>Event</em>}</li>
 * </ul>
 * </p>
 *
 * @see com.bluexml.side.workflow.WorkflowPackage#getProcessState()
 * @model
 * @generated
 */
public interface ProcessState extends State {
	/**
	 * Returns the value of the '<em><b>Subprocess</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Subprocess</em>' reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Subprocess</em>' reference.
	 * @see #setSubprocess(com.bluexml.side.workflow.Process)
	 * @see com.bluexml.side.workflow.WorkflowPackage#getProcessState_Subprocess()
	 * @model required="true" ordered="false"
	 * @generated
	 */
	com.bluexml.side.workflow.Process getSubprocess();

	/**
	 * Sets the value of the '{@link com.bluexml.side.workflow.ProcessState#getSubprocess <em>Subprocess</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Subprocess</em>' reference.
	 * @see #getSubprocess()
	 * @generated
	 */
	void setSubprocess(com.bluexml.side.workflow.Process value);

	/**
	 * Returns the value of the '<em><b>Transition</b></em>' containment reference list.
	 * The list contents are of type {@link com.bluexml.side.workflow.Transition}.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Transition</em>' containment reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Transition</em>' containment reference list.
	 * @see com.bluexml.side.workflow.WorkflowPackage#getProcessState_Transition()
	 * @model containment="true"
	 * @generated
	 */
	EList<Transition> getTransition();

	/**
	 * Returns the value of the '<em><b>Event</b></em>' containment reference list.
	 * The list contents are of type {@link com.bluexml.side.workflow.Event}.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Event</em>' containment reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Event</em>' containment reference list.
	 * @see com.bluexml.side.workflow.WorkflowPackage#getProcessState_Event()
	 * @model containment="true"
	 * @generated
	 */
	EList<Event> getEvent();

} // ProcessState
