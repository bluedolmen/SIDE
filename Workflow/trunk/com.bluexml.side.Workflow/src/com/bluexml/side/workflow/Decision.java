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
 * A representation of the model object '<em><b>Decision</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link com.bluexml.side.workflow.Decision#getTransitioncondition <em>Transitioncondition</em>}</li>
 *   <li>{@link com.bluexml.side.workflow.Decision#getTransition <em>Transition</em>}</li>
 *   <li>{@link com.bluexml.side.workflow.Decision#getEvent <em>Event</em>}</li>
 * </ul>
 * </p>
 *
 * @see com.bluexml.side.workflow.WorkflowPackage#getDecision()
 * @model
 * @generated
 */
public interface Decision extends State {
	/**
	 * Returns the value of the '<em><b>Transitioncondition</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Transitioncondition</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Transitioncondition</em>' attribute.
	 * @see #setTransitioncondition(String)
	 * @see com.bluexml.side.workflow.WorkflowPackage#getDecision_Transitioncondition()
	 * @model unique="false" required="true" ordered="false"
	 * @generated
	 */
	String getTransitioncondition();

	/**
	 * Sets the value of the '{@link com.bluexml.side.workflow.Decision#getTransitioncondition <em>Transitioncondition</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Transitioncondition</em>' attribute.
	 * @see #getTransitioncondition()
	 * @generated
	 */
	void setTransitioncondition(String value);

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
	 * @see com.bluexml.side.workflow.WorkflowPackage#getDecision_Transition()
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
	 * @see com.bluexml.side.workflow.WorkflowPackage#getDecision_Event()
	 * @model containment="true"
	 * @generated
	 */
	EList<Event> getEvent();

} // Decision
