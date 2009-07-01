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
 * <!-- begin-model-doc -->
 * Definition: If a 'Timer' is associated to a transition, the transition will be taken when the 'Timer' expires.
 * <!-- end-model-doc -->
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
public interface Timer extends Action {
	/**
	 * Returns the value of the '<em><b>Duedate</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Duedate</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * Definition: The attribute 'duedate' specifies the duration of a 'Timer'.
	 * 
	 * Constraint/limit: The syntax of the attribute 'Timer' follow the jpdl syntax (http://docs.jboss.org/jbpm/v3/userguide/businesscalendar.html#duration): quantity [business] unit.
	 * 
	 * Example:
	 * - 1 year.
	 * - 1 week.
	 * - 1 month.
	 * - 1 hour.
	 * - 1 min.
	 * - 1 second.
	 * <!-- end-model-doc -->
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
