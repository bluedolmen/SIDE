/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package com.bluexml.side.workflow;


/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Timer</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link com.bluexml.side.workflow.Timer#getDuedate <em>Duedate</em>}</li>
 * </ul>
 * </p>
 *
 * @see com.bluexml.side.workflow.WorkflowPackage#getTimer()
 * @model
 * @generated
 */
public interface Timer extends WorkflowModelElement {
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

} // Timer
