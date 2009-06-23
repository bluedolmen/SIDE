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
 * A representation of the model object '<em><b>Event</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link com.bluexml.side.workflow.Event#getType <em>Type</em>}</li>
 *   <li>{@link com.bluexml.side.workflow.Event#getAction <em>Action</em>}</li>
 * </ul>
 * </p>
 *
 * @see com.bluexml.side.workflow.WorkflowPackage#getEvent()
 * @model
 * @generated
 */
public interface Event extends EObject {
	/**
	 * Returns the value of the '<em><b>Type</b></em>' attribute.
	 * The literals are from the enumeration {@link com.bluexml.side.workflow.BPMEventType}.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Type</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Type</em>' attribute.
	 * @see com.bluexml.side.workflow.BPMEventType
	 * @see #setType(BPMEventType)
	 * @see com.bluexml.side.workflow.WorkflowPackage#getEvent_Type()
	 * @model unique="false" required="true" ordered="false"
	 * @generated
	 */
	BPMEventType getType();

	/**
	 * Sets the value of the '{@link com.bluexml.side.workflow.Event#getType <em>Type</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Type</em>' attribute.
	 * @see com.bluexml.side.workflow.BPMEventType
	 * @see #getType()
	 * @generated
	 */
	void setType(BPMEventType value);

	/**
	 * Returns the value of the '<em><b>Action</b></em>' containment reference list.
	 * The list contents are of type {@link com.bluexml.side.workflow.Action}.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Action</em>' containment reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Action</em>' containment reference list.
	 * @see com.bluexml.side.workflow.WorkflowPackage#getEvent_Action()
	 * @model containment="true"
	 * @generated
	 */
	EList<Action> getAction();

} // Event
