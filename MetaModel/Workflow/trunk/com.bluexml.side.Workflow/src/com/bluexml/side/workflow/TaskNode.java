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
 * A representation of the model object '<em><b>Task Node</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link com.bluexml.side.workflow.TaskNode#getTimer <em>Timer</em>}</li>
 *   <li>{@link com.bluexml.side.workflow.TaskNode#getSwimlane <em>Swimlane</em>}</li>
 * </ul>
 * </p>
 *
 * @see com.bluexml.side.workflow.WorkflowPackage#getTaskNode()
 * @model annotation="http://www.bluexml.com/OCL TaskMustBePointerByTransition='Transition.allInstances()-> select(t| t.to = self)->size()=1 or self.transition -> notEmpty()\n'"
 *        annotation="http://www.eclipse.org/emf/2002/Ecore constraints='NoTaskWithSameName TaskMustBePointerByTransition'"
 * @generated
 */
public interface TaskNode extends UserTask {
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
	 * @see com.bluexml.side.workflow.WorkflowPackage#getTaskNode_Timer()
	 * @model containment="true"
	 * @generated
	 */
	EList<Timer> getTimer();

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
	 * @see com.bluexml.side.workflow.WorkflowPackage#getTaskNode_Swimlane()
	 * @see com.bluexml.side.workflow.Swimlane#getManage
	 * @model opposite="manage" required="true" ordered="false"
	 * @generated
	 */
	Swimlane getSwimlane();

	/**
	 * Sets the value of the '{@link com.bluexml.side.workflow.TaskNode#getSwimlane <em>Swimlane</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Swimlane</em>' reference.
	 * @see #getSwimlane()
	 * @generated
	 */
	void setSwimlane(Swimlane value);

} // TaskNode
