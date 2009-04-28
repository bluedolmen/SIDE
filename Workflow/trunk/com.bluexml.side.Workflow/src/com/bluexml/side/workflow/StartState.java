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
 *   <li>{@link com.bluexml.side.workflow.StartState#getAssignmentType <em>Assignment Type</em>}</li>
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

} // StartState
