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
 * A representation of the model object '<em><b>Decision</b></em>'.
 * <!-- end-user-doc -->
 *
 * <!-- begin-model-doc -->
 * Definition: The 'Decision' evaluates the attribute 'condition' of the leaving transitions. The leaving transision with a true condition will be taken. If all conditions are false, the taken leaving transition will be the one without condition.
 * 
 * Constraint/limit:
 * - 'Decision' can have n leaving 'Transition'.
 * - 'Decision' must have only one leaving 'Transition' witout a condition.
 * 
 * Inherits: 
 * - TransitionTask.
 * <!-- end-model-doc -->
 *
 *
 * @see com.bluexml.side.workflow.WorkflowPackage#getDecision()
 * @model annotation="http://www.bluexml.com/OCL DecisionMustHaveOnlyOneTransitionWithCondition='self.transition -> select (t1| t1.condition -> isEmpty() or t1.condition =\'\') -> size() = 1'"
 *        annotation="http://www.eclipse.org/emf/2002/Ecore constraints='DecisionMustHaveOnlyOneTransitionWithCondition'"
 * @generated
 */
public interface Decision extends TransitionTask {

} // Decision
