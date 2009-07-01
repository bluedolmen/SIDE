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
 * A representation of the model object '<em><b>Process State</b></em>'.
 * <!-- end-user-doc -->
 *
 * <!-- begin-model-doc -->
 * Definition: The 'ProcessState' represents a subprocess. It may have variables. The process state is a state that is associated with another process definition. When graph execution arrives in the process state, a new process instance of the sub-process is created and it is associated with the path of execution that arrived in the process state. The path of execution of the super process will wait till the sub process instance has ended. When the sub process instance ends, the path of execution of the super process will leave the process state and continue graph execution in the super process.
 * <!-- end-model-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link com.bluexml.side.workflow.ProcessState#getSubprocess <em>Subprocess</em>}</li>
 *   <li>{@link com.bluexml.side.workflow.ProcessState#getVariable <em>Variable</em>}</li>
 * </ul>
 * </p>
 *
 * @see com.bluexml.side.workflow.WorkflowPackage#getProcessState()
 * @model
 * @generated
 */
public interface ProcessState extends TransitionTask {
	/**
	 * Returns the value of the '<em><b>Subprocess</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Subprocess</em>' reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Subprocess</em>' reference.
	 * @see #setSubprocess(com.bluexml.side.workflow.Process)
	 * @see com.bluexml.side.workflow.WorkflowPackage#getProcessState_Subprocess()
	 * @model required="true" ordered="false"
	 * @generated
	 */
	com.bluexml.side.workflow.Process getSubprocess();

	/**
	 * Sets the value of the '{@link com.bluexml.side.workflow.ProcessState#getSubprocess <em>Subprocess</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Subprocess</em>' reference.
	 * @see #getSubprocess()
	 * @generated
	 */
	void setSubprocess(com.bluexml.side.workflow.Process value);

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
	 * @see com.bluexml.side.workflow.WorkflowPackage#getProcessState_Variable()
	 * @model containment="true"
	 * @generated
	 */
	EList<Variable> getVariable();

} // ProcessState
