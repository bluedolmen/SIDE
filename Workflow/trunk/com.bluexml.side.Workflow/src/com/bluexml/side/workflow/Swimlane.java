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
 * A representation of the model object '<em><b>Swimlane</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link com.bluexml.side.workflow.Swimlane#getName <em>Name</em>}</li>
 *   <li>{@link com.bluexml.side.workflow.Swimlane#getAssignment <em>Assignment</em>}</li>
 *   <li>{@link com.bluexml.side.workflow.Swimlane#getManage <em>Manage</em>}</li>
 * </ul>
 * </p>
 *
 * @see com.bluexml.side.workflow.WorkflowPackage#getSwimlane()
 * @model
 * @generated
 */
public interface Swimlane extends EObject {
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
	 * @see com.bluexml.side.workflow.WorkflowPackage#getSwimlane_Name()
	 * @model unique="false" required="true" ordered="false"
	 * @generated
	 */
	String getName();

	/**
	 * Sets the value of the '{@link com.bluexml.side.workflow.Swimlane#getName <em>Name</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Name</em>' attribute.
	 * @see #getName()
	 * @generated
	 */
	void setName(String value);

	/**
	 * Returns the value of the '<em><b>Assignment</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Assignment</em>' containment reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Assignment</em>' containment reference.
	 * @see #setAssignment(Assignment)
	 * @see com.bluexml.side.workflow.WorkflowPackage#getSwimlane_Assignment()
	 * @model containment="true" required="true" ordered="false"
	 * @generated
	 */
	Assignment getAssignment();

	/**
	 * Sets the value of the '{@link com.bluexml.side.workflow.Swimlane#getAssignment <em>Assignment</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Assignment</em>' containment reference.
	 * @see #getAssignment()
	 * @generated
	 */
	void setAssignment(Assignment value);

	/**
	 * Returns the value of the '<em><b>Manage</b></em>' reference list.
	 * The list contents are of type {@link com.bluexml.side.workflow.Task}.
	 * It is bidirectional and its opposite is '{@link com.bluexml.side.workflow.Task#getSwimlane <em>Swimlane</em>}'.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Manage</em>' reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Manage</em>' reference list.
	 * @see com.bluexml.side.workflow.WorkflowPackage#getSwimlane_Manage()
	 * @see com.bluexml.side.workflow.Task#getSwimlane
	 * @model opposite="swimlane"
	 * @generated
	 */
	EList<Task> getManage();

} // Swimlane
