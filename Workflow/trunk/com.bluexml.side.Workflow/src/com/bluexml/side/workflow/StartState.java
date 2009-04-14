/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package com.bluexml.side.workflow;

import org.eclipse.emf.common.util.EList;

import com.bluexml.side.clazz.Clazz;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Start State</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link com.bluexml.side.workflow.StartState#getTransition <em>Transition</em>}</li>
 *   <li>{@link com.bluexml.side.workflow.StartState#getEvent <em>Event</em>}</li>
 *   <li>{@link com.bluexml.side.workflow.StartState#getAssignmentType <em>Assignment Type</em>}</li>
 *   <li>{@link com.bluexml.side.workflow.StartState#getInitiator <em>Initiator</em>}</li>
 *   <li>{@link com.bluexml.side.workflow.StartState#getAttributes <em>Attributes</em>}</li>
 *   <li>{@link com.bluexml.side.workflow.StartState#getClazz <em>Clazz</em>}</li>
 * </ul>
 * </p>
 *
 * @see com.bluexml.side.workflow.WorkflowPackage#getStartState()
 * @model annotation="http://www.bluexml.com/OCL OneStartTask='StartState.allInstances() -> size() = 1'"
 *        annotation="http://www.eclipse.org/emf/2002/Ecore constraints='OneStartTask'"
 * @generated
 */
public interface StartState extends State {
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
	 * @see com.bluexml.side.workflow.WorkflowPackage#getStartState_Transition()
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
	 * @see com.bluexml.side.workflow.WorkflowPackage#getStartState_Event()
	 * @model containment="true"
	 * @generated
	 */
	EList<Event> getEvent();

	/**
	 * Returns the value of the '<em><b>Assignment Type</b></em>' attribute.
	 * The literals are from the enumeration {@link com.bluexml.side.workflow.BPMAssignmentType}.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Assignment Type</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Assignment Type</em>' attribute.
	 * @see com.bluexml.side.workflow.BPMAssignmentType
	 * @see #setAssignmentType(BPMAssignmentType)
	 * @see com.bluexml.side.workflow.WorkflowPackage#getStartState_AssignmentType()
	 * @model
	 * @generated
	 */
	BPMAssignmentType getAssignmentType();

	/**
	 * Sets the value of the '{@link com.bluexml.side.workflow.StartState#getAssignmentType <em>Assignment Type</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Assignment Type</em>' attribute.
	 * @see com.bluexml.side.workflow.BPMAssignmentType
	 * @see #getAssignmentType()
	 * @generated
	 */
	void setAssignmentType(BPMAssignmentType value);

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

	/**
	 * Returns the value of the '<em><b>Attributes</b></em>' containment reference list.
	 * The list contents are of type {@link com.bluexml.side.workflow.Attribute}.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Attributes</em>' containment reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Attributes</em>' containment reference list.
	 * @see com.bluexml.side.workflow.WorkflowPackage#getStartState_Attributes()
	 * @model containment="true"
	 * @generated
	 */
	EList<Attribute> getAttributes();

	/**
	 * Returns the value of the '<em><b>Clazz</b></em>' reference list.
	 * The list contents are of type {@link com.bluexml.side.clazz.Clazz}.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Clazz</em>' reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Clazz</em>' reference list.
	 * @see com.bluexml.side.workflow.WorkflowPackage#getStartState_Clazz()
	 * @model
	 * @generated
	 */
	EList<Clazz> getClazz();

} // StartState
