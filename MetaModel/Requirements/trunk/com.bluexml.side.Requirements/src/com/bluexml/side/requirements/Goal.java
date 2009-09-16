/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package com.bluexml.side.requirements;

import org.eclipse.emf.common.util.EList;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Goal</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link com.bluexml.side.requirements.Goal#getSubgoals <em>Subgoals</em>}</li>
 *   <li>{@link com.bluexml.side.requirements.Goal#getPriority <em>Priority</em>}</li>
 *   <li>{@link com.bluexml.side.requirements.Goal#getResponsible <em>Responsible</em>}</li>
 *   <li>{@link com.bluexml.side.requirements.Goal#getPrivilegeGroup <em>Privilege Group</em>}</li>
 *   <li>{@link com.bluexml.side.requirements.Goal#getStep <em>Step</em>}</li>
 * </ul>
 * </p>
 *
 * @see com.bluexml.side.requirements.RequirementsPackage#getGoal()
 * @model
 * @generated
 */
public interface Goal extends BasicElement {
	/**
	 * Returns the value of the '<em><b>Subgoals</b></em>' containment reference list.
	 * The list contents are of type {@link com.bluexml.side.requirements.Goal}.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Subgoals</em>' containment reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Subgoals</em>' containment reference list.
	 * @see com.bluexml.side.requirements.RequirementsPackage#getGoal_Subgoals()
	 * @model containment="true"
	 * @generated
	 */
	EList<Goal> getSubgoals();

	/**
	 * Returns the value of the '<em><b>Priority</b></em>' attribute.
	 * The literals are from the enumeration {@link com.bluexml.side.requirements.PriorityLevel}.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Priority</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Priority</em>' attribute.
	 * @see com.bluexml.side.requirements.PriorityLevel
	 * @see #setPriority(PriorityLevel)
	 * @see com.bluexml.side.requirements.RequirementsPackage#getGoal_Priority()
	 * @model
	 * @generated
	 */
	PriorityLevel getPriority();

	/**
	 * Sets the value of the '{@link com.bluexml.side.requirements.Goal#getPriority <em>Priority</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Priority</em>' attribute.
	 * @see com.bluexml.side.requirements.PriorityLevel
	 * @see #getPriority()
	 * @generated
	 */
	void setPriority(PriorityLevel value);

	/**
	 * Returns the value of the '<em><b>Responsible</b></em>' reference list.
	 * The list contents are of type {@link com.bluexml.side.requirements.Agent}.
	 * It is bidirectional and its opposite is '{@link com.bluexml.side.requirements.Agent#getIsResponsible <em>Is Responsible</em>}'.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Responsible</em>' reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Responsible</em>' reference list.
	 * @see com.bluexml.side.requirements.RequirementsPackage#getGoal_Responsible()
	 * @see com.bluexml.side.requirements.Agent#getIsResponsible
	 * @model opposite="isResponsible"
	 * @generated
	 */
	EList<Agent> getResponsible();

	/**
	 * Returns the value of the '<em><b>Privilege Group</b></em>' containment reference list.
	 * The list contents are of type {@link com.bluexml.side.requirements.PrivilegeGroup}.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Privilege Group</em>' containment reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Privilege Group</em>' containment reference list.
	 * @see com.bluexml.side.requirements.RequirementsPackage#getGoal_PrivilegeGroup()
	 * @model containment="true"
	 * @generated
	 */
	EList<PrivilegeGroup> getPrivilegeGroup();

	/**
	 * Returns the value of the '<em><b>Step</b></em>' containment reference list.
	 * The list contents are of type {@link com.bluexml.side.requirements.GoalStep}.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Step</em>' containment reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Step</em>' containment reference list.
	 * @see com.bluexml.side.requirements.RequirementsPackage#getGoal_Step()
	 * @model containment="true"
	 * @generated
	 */
	EList<GoalStep> getStep();

} // Goal
