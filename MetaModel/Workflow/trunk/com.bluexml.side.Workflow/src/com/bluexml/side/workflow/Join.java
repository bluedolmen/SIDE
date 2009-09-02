/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package com.bluexml.side.workflow;


/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Join</b></em>'.
 * <!-- end-user-doc -->
 *
 * <!-- begin-model-doc -->
 * Definition: The default join assumes that all tokens that arrive in the join are children of the same parent. This situation is created when using the fork as mentioned above and when all tokens created by a fork arrive in the same join. A join will end every token that enters the join. When all sibling tokens have arrived in the join, the parent token will be propagated over the (unique!) leaving transition. When there are still sibling tokens active, the join will behave as a wait state.
 * 
 * Inherits: 
 * - TransitionTask.
 * <!-- end-model-doc -->
 *
 *
 * @see com.bluexml.side.workflow.WorkflowPackage#getJoin()
 * @model
 * @generated
 */
public interface Join extends TransitionTask {

} // Join
