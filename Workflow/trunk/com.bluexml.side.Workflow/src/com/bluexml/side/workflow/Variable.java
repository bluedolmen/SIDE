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
 * A representation of the model object '<em><b>Variable</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link com.bluexml.side.workflow.Variable#getName <em>Name</em>}</li>
 *   <li>{@link com.bluexml.side.workflow.Variable#getAccess <em>Access</em>}</li>
 *   <li>{@link com.bluexml.side.workflow.Variable#getMappedName <em>Mapped Name</em>}</li>
 * </ul>
 * </p>
 *
 * @see com.bluexml.side.workflow.WorkflowPackage#getVariable()
 * @model annotation="http://www.bluexml.com/OCL accessMatchesWithReadWriteRequired='self.access.regexMatch(\'[read,|write,|required,]*[read|write|required]\') = true'"
 *        annotation="http://www.eclipse.org/emf/2002/Ecore constraints='accessMatchesWithReadWriteRequired'"
 * @generated
 */
public interface Variable extends EObject {
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
	 * @see com.bluexml.side.workflow.WorkflowPackage#getVariable_Name()
	 * @model unique="false" required="true" ordered="false"
	 * @generated
	 */
	String getName();

	/**
	 * Sets the value of the '{@link com.bluexml.side.workflow.Variable#getName <em>Name</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Name</em>' attribute.
	 * @see #getName()
	 * @generated
	 */
	void setName(String value);

	/**
	 * Returns the value of the '<em><b>Access</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Access</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Access</em>' attribute.
	 * @see #setAccess(String)
	 * @see com.bluexml.side.workflow.WorkflowPackage#getVariable_Access()
	 * @model unique="false" required="true" ordered="false"
	 * @generated
	 */
	String getAccess();

	/**
	 * Sets the value of the '{@link com.bluexml.side.workflow.Variable#getAccess <em>Access</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Access</em>' attribute.
	 * @see #getAccess()
	 * @generated
	 */
	void setAccess(String value);

	/**
	 * Returns the value of the '<em><b>Mapped Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Mapped Name</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Mapped Name</em>' attribute.
	 * @see #setMappedName(String)
	 * @see com.bluexml.side.workflow.WorkflowPackage#getVariable_MappedName()
	 * @model unique="false" ordered="false"
	 * @generated
	 */
	String getMappedName();

	/**
	 * Sets the value of the '{@link com.bluexml.side.workflow.Variable#getMappedName <em>Mapped Name</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Mapped Name</em>' attribute.
	 * @see #getMappedName()
	 * @generated
	 */
	void setMappedName(String value);

} // Variable
