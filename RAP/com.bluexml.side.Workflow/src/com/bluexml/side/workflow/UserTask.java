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
 * A representation of the model object '<em><b>User Task</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link com.bluexml.side.workflow.UserTask#getAttributes <em>Attributes</em>}</li>
 *   <li>{@link com.bluexml.side.workflow.UserTask#getClazz <em>Clazz</em>}</li>
 *   <li>{@link com.bluexml.side.workflow.UserTask#getAdvancedTaskDefinition <em>Advanced Task Definition</em>}</li>
 * </ul>
 * </p>
 *
 * @see com.bluexml.side.workflow.WorkflowPackage#getUserTask()
 * @model
 * @generated
 */
public interface UserTask extends TransitionTask {
	/**
	 * Returns the value of the '<em><b>Attributes</b></em>' containment reference list.
	 * The list contents are of type {@link com.bluexml.side.workflow.Attribute}.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Attributes</em>' containment reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Attributes</em>' containment reference list.
	 * @see com.bluexml.side.workflow.WorkflowPackage#getUserTask_Attributes()
	 * @model containment="true"
	 * @generated
	 */
	EList<Attribute> getAttributes();

	/**
	 * Returns the value of the '<em><b>Clazz</b></em>' reference list.
	 * The list contents are of type {@link com.bluexml.side.clazz.Clazz}.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Clazz</em>' reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Clazz</em>' reference list.
	 * @see com.bluexml.side.workflow.WorkflowPackage#getUserTask_Clazz()
	 * @model
	 * @generated
	 */
	EList<Clazz> getClazz();

	/**
	 * Returns the value of the '<em><b>Advanced Task Definition</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Advanced Task Definition</em>' reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Advanced Task Definition</em>' reference.
	 * @see #setAdvancedTaskDefinition(Clazz)
	 * @see com.bluexml.side.workflow.WorkflowPackage#getUserTask_AdvancedTaskDefinition()
	 * @model
	 * @generated
	 */
	Clazz getAdvancedTaskDefinition();

	/**
	 * Sets the value of the '{@link com.bluexml.side.workflow.UserTask#getAdvancedTaskDefinition <em>Advanced Task Definition</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Advanced Task Definition</em>' reference.
	 * @see #getAdvancedTaskDefinition()
	 * @generated
	 */
	void setAdvancedTaskDefinition(Clazz value);
		
} // UserTask
