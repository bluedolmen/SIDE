/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package com.bluexml.side.workflow;

import org.eclipse.emf.ecore.EObject;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Assignment</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link com.bluexml.side.workflow.Assignment#getActorid <em>Actorid</em>}</li>
 *   <li>{@link com.bluexml.side.workflow.Assignment#getPooledactors <em>Pooledactors</em>}</li>
 *   <li>{@link com.bluexml.side.workflow.Assignment#getClazz <em>Clazz</em>}</li>
 * </ul>
 * </p>
 *
 * @see com.bluexml.side.workflow.WorkflowPackage#getAssignment()
 * @model
 * @generated
 */
public interface Assignment extends EObject {
	/**
	 * Returns the value of the '<em><b>Actorid</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Actorid</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Actorid</em>' attribute.
	 * @see #setActorid(String)
	 * @see com.bluexml.side.workflow.WorkflowPackage#getAssignment_Actorid()
	 * @model unique="false" required="true" ordered="false"
	 * @generated
	 */
	String getActorid();

	/**
	 * Sets the value of the '{@link com.bluexml.side.workflow.Assignment#getActorid <em>Actorid</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Actorid</em>' attribute.
	 * @see #getActorid()
	 * @generated
	 */
	void setActorid(String value);

	/**
	 * Returns the value of the '<em><b>Pooledactors</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Pooledactors</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Pooledactors</em>' attribute.
	 * @see #setPooledactors(String)
	 * @see com.bluexml.side.workflow.WorkflowPackage#getAssignment_Pooledactors()
	 * @model unique="false" required="true" ordered="false"
	 * @generated
	 */
	String getPooledactors();

	/**
	 * Sets the value of the '{@link com.bluexml.side.workflow.Assignment#getPooledactors <em>Pooledactors</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Pooledactors</em>' attribute.
	 * @see #getPooledactors()
	 * @generated
	 */
	void setPooledactors(String value);

	/**
	 * Returns the value of the '<em><b>Clazz</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Clazz</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Clazz</em>' attribute.
	 * @see #setClazz(String)
	 * @see com.bluexml.side.workflow.WorkflowPackage#getAssignment_Clazz()
	 * @model unique="false" required="true" ordered="false"
	 * @generated
	 */
	String getClazz();

	/**
	 * Sets the value of the '{@link com.bluexml.side.workflow.Assignment#getClazz <em>Clazz</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Clazz</em>' attribute.
	 * @see #getClazz()
	 * @generated
	 */
	void setClazz(String value);

} // Assignment
