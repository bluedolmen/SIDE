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
 * A representation of the model object '<em><b>Script</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link com.bluexml.side.workflow.Script#getVariable <em>Variable</em>}</li>
 *   <li>{@link com.bluexml.side.workflow.Script#getExpression <em>Expression</em>}</li>
 * </ul>
 * </p>
 *
 * @see com.bluexml.side.workflow.WorkflowPackage#getScript()
 * @model
 * @generated
 */
public interface Script extends EObject {
	/**
	 * Returns the value of the '<em><b>Variable</b></em>' containment reference list.
	 * The list contents are of type {@link com.bluexml.side.workflow.Variable}.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Variable</em>' containment reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Variable</em>' containment reference list.
	 * @see com.bluexml.side.workflow.WorkflowPackage#getScript_Variable()
	 * @model containment="true"
	 * @generated
	 */
	EList<Variable> getVariable();

	/**
	 * Returns the value of the '<em><b>Expression</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Expression</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Expression</em>' attribute.
	 * @see #setExpression(String)
	 * @see com.bluexml.side.workflow.WorkflowPackage#getScript_Expression()
	 * @model required="true"
	 * @generated
	 */
	String getExpression();

	/**
	 * Sets the value of the '{@link com.bluexml.side.workflow.Script#getExpression <em>Expression</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Expression</em>' attribute.
	 * @see #getExpression()
	 * @generated
	 */
	void setExpression(String value);

} // Script
