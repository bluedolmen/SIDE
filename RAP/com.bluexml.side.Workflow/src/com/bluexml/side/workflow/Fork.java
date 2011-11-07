/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package com.bluexml.side.workflow;


/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Fork</b></em>'.
 * <!-- end-user-doc -->
 *
 * <!-- begin-model-doc -->
 * Definition: The 'Fork' splits one path of execution into multiple concurrent paths of execution.
 * 
 * Inherits: 
 * - TransitionTask.
 * <!-- end-model-doc -->
 *
 *
 * @see com.bluexml.side.workflow.WorkflowPackage#getFork()
 * @model annotation="http://www.bluexml.com/OCL ForkMustBeFollowedByJoin='self.getAllNextStates()->select(s | s.oclIsTypeOf(Join))->size()>0'"
 *        annotation="http://www.eclipse.org/emf/2002/Ecore constraints='ForkMustBeFollowedByJoin'"
 * @generated
 */
public interface Fork extends TransitionTask {

} // Fork
