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
 * A representation of the model object '<em><b>Task</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link com.bluexml.side.workflow.Task#getName <em>Name</em>}</li>
 *   <li>{@link com.bluexml.side.workflow.Task#getSwimlane <em>Swimlane</em>}</li>
 *   <li>{@link com.bluexml.side.workflow.Task#getAssignment <em>Assignment</em>}</li>
 *   <li>{@link com.bluexml.side.workflow.Task#getEvent <em>Event</em>}</li>
 *   <li>{@link com.bluexml.side.workflow.Task#getTimer <em>Timer</em>}</li>
 * </ul>
 * </p>
 *
 * @see com.bluexml.side.workflow.WorkflowPackage#getTask()
 * @model
 * @generated
 */
public interface Task extends EObject {
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
	 * @see com.bluexml.side.workflow.WorkflowPackage#getTask_Name()
	 * @model unique="false" required="true" ordered="false"
	 * @generated
	 */
	String getName();

	/**
	 * Sets the value of the '{@link com.bluexml.side.workflow.Task#getName <em>Name</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Name</em>' attribute.
	 * @see #getName()
	 * @generated
	 */
	void setName(String value);

	/**
	 * Returns the value of the '<em><b>Swimlane</b></em>' reference.
	 * It is bidirectional and its opposite is '{@link com.bluexml.side.workflow.Swimlane#getManage <em>Manage</em>}'.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Swimlane</em>' reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Swimlane</em>' reference.
	 * @see #setSwimlane(Swimlane)
	 * @see com.bluexml.side.workflow.WorkflowPackage#getTask_Swimlane()
	 * @see com.bluexml.side.workflow.Swimlane#getManage
	 * @model opposite="manage" required="true" ordered="false"
	 * @generated
	 */
	Swimlane getSwimlane();

	/**
	 * Sets the value of the '{@link com.bluexml.side.workflow.Task#getSwimlane <em>Swimlane</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Swimlane</em>' reference.
	 * @see #getSwimlane()
	 * @generated
	 */
	void setSwimlane(Swimlane value);

	/**
	 * Returns the value of the '<em><b>Assignment</b></em>' containment reference list.
	 * The list contents are of type {@link com.bluexml.side.workflow.Assignment}.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Assignment</em>' containment reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Assignment</em>' containment reference list.
	 * @see com.bluexml.side.workflow.WorkflowPackage#getTask_Assignment()
	 * @model containment="true"
	 * @generated
	 */
	EList<Assignment> getAssignment();

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
	 * @see com.bluexml.side.workflow.WorkflowPackage#getTask_Event()
	 * @model containment="true"
	 * @generated
	 */
	EList<Event> getEvent();

	/**
	 * Returns the value of the '<em><b>Timer</b></em>' containment reference list.
	 * The list contents are of type {@link com.bluexml.side.workflow.Timer}.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Timer</em>' containment reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Timer</em>' containment reference list.
	 * @see com.bluexml.side.workflow.WorkflowPackage#getTask_Timer()
	 * @model containment="true"
	 * @generated
	 */
	EList<Timer> getTimer();

} // Task
