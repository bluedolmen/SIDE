/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package com.bluexml.side.workflow;

import org.eclipse.emf.common.util.EList;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Swimlane</b></em>'.
 * <!-- end-user-doc -->
 *
 * <!-- begin-model-doc -->
 * Definition: The 'Swimlane' represents the actor that manages one or many tasks of the workflow process. It is a mechanism to specify that multiple tasks in the process should be done by the same actor. So after the first task instance is created for a given swimlane, the actor should be remembered in the process for all subsequent tasks that are in the same swimlane.
 * 
 * Constraint/limit: A 'Swimlane' must manage at least one task.
 * 
 * <!-- end-model-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link com.bluexml.side.workflow.Swimlane#getName <em>Name</em>}</li>
 *   <li>{@link com.bluexml.side.workflow.Swimlane#getManage <em>Manage</em>}</li>
 *   <li>{@link com.bluexml.side.workflow.Swimlane#getActorid <em>Actorid</em>}</li>
 *   <li>{@link com.bluexml.side.workflow.Swimlane#getPooledactors <em>Pooledactors</em>}</li>
 *   <li>{@link com.bluexml.side.workflow.Swimlane#getClazz <em>Clazz</em>}</li>
 * </ul>
 * </p>
 *
 * @see com.bluexml.side.workflow.WorkflowPackage#getSwimlane()
 * @model annotation="http://www.bluexml.com/OCL ActorNameMustBeUnique='Swimlane.allInstances() -> select(n|n.name = self.name and n <> self )->size()=0' MustManageAtLeastOneTask='(not (self.manage->isEmpty())) or (StartState.allInstances()->collect(ss | ss.initiator)->includes(self))' noSpecialCharacters='self.name.regexMatch(\'[\\w]*\') = true' ActoridOrPooledactor='not((self.pooledactors  -> isEmpty() or self.pooledactors =\'\') and\n(self.actorid -> isEmpty() or self.actorid=\'\'))'"
 *        annotation="http://www.eclipse.org/emf/2002/Ecore constraints='ActorNameMustBeUnique MustManageAtLeastOneTask noSpecialCharacters ActoridOrPooledactor'"
 * @generated
 */
public interface Swimlane extends WorkflowModelElement {
	/**
	 * Returns the value of the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Name</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * Definition: The 'name' attribute specifies the name of the Swimlane.
	 * 
	 * Constraint/limit: The 'name' of the Swimlane must be unique.
	 * <!-- end-model-doc -->
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
	 * Returns the value of the '<em><b>Manage</b></em>' reference list.
	 * The list contents are of type {@link com.bluexml.side.workflow.TaskNode}.
	 * It is bidirectional and its opposite is '{@link com.bluexml.side.workflow.TaskNode#getSwimlane <em>Swimlane</em>}'.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Manage</em>' reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Manage</em>' reference list.
	 * @see com.bluexml.side.workflow.WorkflowPackage#getSwimlane_Manage()
	 * @see com.bluexml.side.workflow.TaskNode#getSwimlane
	 * @model opposite="swimlane"
	 * @generated
	 */
	EList<TaskNode> getManage();

	/**
	 * Returns the value of the '<em><b>Actorid</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Actorid</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * Definition: The 'actorid' attribute specifies the name of an account in the target deploy platform.
	 * 
	 * Constraint/limit: 
	 * - The actorid value must be the same as the value of the attribute 'name'.
	 * - If there is a value for the attribute 'actorid', no value must be setted for the attribute 'pooledactors'.
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>Actorid</em>' attribute.
	 * @see #setActorid(String)
	 * @see com.bluexml.side.workflow.WorkflowPackage#getSwimlane_Actorid()
	 * @model unique="false" ordered="false"
	 * @generated
	 */
	String getActorid();

	/**
	 * Sets the value of the '{@link com.bluexml.side.workflow.Swimlane#getActorid <em>Actorid</em>}' attribute.
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
	 * <!-- begin-model-doc -->
	 * Definition: The 'pooledactors' attribute specifies the name of a group in the target platform.
	 * 
	 * Constraint/limit: If there is a value for the attribute 'pooledactors', no value must be setted for the attribute 'actorid'.
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>Pooledactors</em>' attribute.
	 * @see #setPooledactors(String)
	 * @see com.bluexml.side.workflow.WorkflowPackage#getSwimlane_Pooledactors()
	 * @model unique="false" ordered="false"
	 * @generated
	 */
	String getPooledactors();

	/**
	 * Sets the value of the '{@link com.bluexml.side.workflow.Swimlane#getPooledactors <em>Pooledactors</em>}' attribute.
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
	 * @see com.bluexml.side.workflow.WorkflowPackage#getSwimlane_Clazz()
	 * @model unique="false" ordered="false"
	 * @generated
	 */
	String getClazz();

	/**
	 * Sets the value of the '{@link com.bluexml.side.workflow.Swimlane#getClazz <em>Clazz</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Clazz</em>' attribute.
	 * @see #getClazz()
	 * @generated
	 */
	void setClazz(String value);

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @model annotation="http://www.bluexml.com/OCL body='self.name = other.name'"
	 * @generated
	 */
	boolean EqualsForMerge(Swimlane other);

} // Swimlane
