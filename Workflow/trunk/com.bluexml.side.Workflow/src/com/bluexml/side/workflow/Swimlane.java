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
 *   <li>{@link com.bluexml.side.workflow.Swimlane#getManage <em>Manage</em>}</li>
 *   <li>{@link com.bluexml.side.workflow.Swimlane#getActorid <em>Actorid</em>}</li>
 *   <li>{@link com.bluexml.side.workflow.Swimlane#getPooledactors <em>Pooledactors</em>}</li>
 *   <li>{@link com.bluexml.side.workflow.Swimlane#getClazz <em>Clazz</em>}</li>
 * </ul>
 * </p>
 *
 * @see com.bluexml.side.workflow.WorkflowPackage#getSwimlane()
 * @model annotation="http://www.bluexml.com/OCL ActorNameMustBeUnique='Swimlane.allInstances() -> select(n|n.name = self.name and n <> self )->size()=0' MustManageAtLeastOneTask='(not (self.manage->isEmpty())) or (StartState.allInstances()->collect(ss | ss.initiator)->includes(self))' noSpecialCharacters='self.name.regexMatch(\'[\\w]*\') = true' OnlyOneActorCalledInitiator='Swimlane.allInstances() -> select(s| s.name =\'initiator\')->size() = 1' ActoridOrPooledactorMustBeSetForAllExeptOneActor='Swimlane.allInstances() -> select(s | s.name <>\'initiator\' and (s.actorid -> isEmpty() or s.actorid=\'\')  and (s.pooledactors  -> isEmpty() or s.pooledactors =\'\'))->size() <=1\n'"
 *        annotation="http://www.eclipse.org/emf/2002/Ecore constraints='ActorNameMustBeUnique MustManageAtLeastOneTask noSpecialCharacters OnlyOneActorCalledInitiator ActoridOrPooledactorMustBeSetForAllExeptOneActor'"
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
	 * @return the value of the '<em>Actorid</em>' attribute.
	 * @see #setActorid(String)
	 * @see com.bluexml.side.workflow.WorkflowPackage#getSwimlane_Actorid()
	 * @model unique="false" required="true" ordered="false"
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
