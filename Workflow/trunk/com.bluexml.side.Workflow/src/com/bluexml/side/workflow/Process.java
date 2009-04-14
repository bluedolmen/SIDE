/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package com.bluexml.side.workflow;

import com.bluexml.side.clazz.Clazz;
import org.eclipse.emf.common.util.EList;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Process</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link com.bluexml.side.workflow.Process#getSwimlane <em>Swimlane</em>}</li>
 *   <li>{@link com.bluexml.side.workflow.Process#getStartstate <em>Startstate</em>}</li>
 *   <li>{@link com.bluexml.side.workflow.Process#getEndstate <em>Endstate</em>}</li>
 *   <li>{@link com.bluexml.side.workflow.Process#getNode <em>Node</em>}</li>
 *   <li>{@link com.bluexml.side.workflow.Process#getTasknode <em>Tasknode</em>}</li>
 *   <li>{@link com.bluexml.side.workflow.Process#getProcessstate <em>Processstate</em>}</li>
 *   <li>{@link com.bluexml.side.workflow.Process#getFork <em>Fork</em>}</li>
 *   <li>{@link com.bluexml.side.workflow.Process#getJoin <em>Join</em>}</li>
 *   <li>{@link com.bluexml.side.workflow.Process#getDecision <em>Decision</em>}</li>
 *   <li>{@link com.bluexml.side.workflow.Process#getElements <em>Elements</em>}</li>
 *   <li>{@link com.bluexml.side.workflow.Process#getContentType <em>Content Type</em>}</li>
 * </ul>
 * </p>
 *
 * @see com.bluexml.side.workflow.WorkflowPackage#getProcess()
 * @model annotation="http://www.bluexml.com/OCL PackageNameNull='not self.name.oclIsUndefined() and self.name <> \'\''"
 *        annotation="http://www.eclipse.org/emf/2002/Ecore constraints='PackageNameNull'"
 * @generated
 */
public interface Process extends com.bluexml.side.common.Package {
	/**
	 * Returns the value of the '<em><b>Swimlane</b></em>' containment reference list.
	 * The list contents are of type {@link com.bluexml.side.workflow.Swimlane}.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Swimlane</em>' containment reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Swimlane</em>' containment reference list.
	 * @see com.bluexml.side.workflow.WorkflowPackage#getProcess_Swimlane()
	 * @model containment="true"
	 * @generated
	 */
	EList<Swimlane> getSwimlane();

	/**
	 * Returns the value of the '<em><b>Startstate</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Startstate</em>' containment reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Startstate</em>' containment reference.
	 * @see #setStartstate(StartState)
	 * @see com.bluexml.side.workflow.WorkflowPackage#getProcess_Startstate()
	 * @model containment="true" required="true" ordered="false"
	 * @generated
	 */
	StartState getStartstate();

	/**
	 * Sets the value of the '{@link com.bluexml.side.workflow.Process#getStartstate <em>Startstate</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Startstate</em>' containment reference.
	 * @see #getStartstate()
	 * @generated
	 */
	void setStartstate(StartState value);

	/**
	 * Returns the value of the '<em><b>Endstate</b></em>' containment reference list.
	 * The list contents are of type {@link com.bluexml.side.workflow.EndState}.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Endstate</em>' containment reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Endstate</em>' containment reference list.
	 * @see com.bluexml.side.workflow.WorkflowPackage#getProcess_Endstate()
	 * @model containment="true" required="true"
	 * @generated
	 */
	EList<EndState> getEndstate();

	/**
	 * Returns the value of the '<em><b>Node</b></em>' containment reference list.
	 * The list contents are of type {@link com.bluexml.side.workflow.Node}.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Node</em>' containment reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Node</em>' containment reference list.
	 * @see com.bluexml.side.workflow.WorkflowPackage#getProcess_Node()
	 * @model containment="true"
	 * @generated
	 */
	EList<Node> getNode();

	/**
	 * Returns the value of the '<em><b>Tasknode</b></em>' containment reference list.
	 * The list contents are of type {@link com.bluexml.side.workflow.TaskNode}.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Tasknode</em>' containment reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Tasknode</em>' containment reference list.
	 * @see com.bluexml.side.workflow.WorkflowPackage#getProcess_Tasknode()
	 * @model containment="true"
	 * @generated
	 */
	EList<TaskNode> getTasknode();

	/**
	 * Returns the value of the '<em><b>Processstate</b></em>' containment reference list.
	 * The list contents are of type {@link com.bluexml.side.workflow.ProcessState}.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Processstate</em>' containment reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Processstate</em>' containment reference list.
	 * @see com.bluexml.side.workflow.WorkflowPackage#getProcess_Processstate()
	 * @model containment="true"
	 * @generated
	 */
	EList<ProcessState> getProcessstate();

	/**
	 * Returns the value of the '<em><b>Fork</b></em>' containment reference list.
	 * The list contents are of type {@link com.bluexml.side.workflow.Fork}.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Fork</em>' containment reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Fork</em>' containment reference list.
	 * @see com.bluexml.side.workflow.WorkflowPackage#getProcess_Fork()
	 * @model containment="true"
	 * @generated
	 */
	EList<Fork> getFork();

	/**
	 * Returns the value of the '<em><b>Join</b></em>' containment reference list.
	 * The list contents are of type {@link com.bluexml.side.workflow.Join}.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Join</em>' containment reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Join</em>' containment reference list.
	 * @see com.bluexml.side.workflow.WorkflowPackage#getProcess_Join()
	 * @model containment="true"
	 * @generated
	 */
	EList<Join> getJoin();

	/**
	 * Returns the value of the '<em><b>Decision</b></em>' containment reference list.
	 * The list contents are of type {@link com.bluexml.side.workflow.Decision}.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Decision</em>' containment reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Decision</em>' containment reference list.
	 * @see com.bluexml.side.workflow.WorkflowPackage#getProcess_Decision()
	 * @model containment="true"
	 * @generated
	 */
	EList<Decision> getDecision();

	/**
	 * Returns the value of the '<em><b>Elements</b></em>' containment reference list.
	 * The list contents are of type {@link com.bluexml.side.workflow.WorkflowModelElement}.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Elements</em>' containment reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Elements</em>' containment reference list.
	 * @see com.bluexml.side.workflow.WorkflowPackage#getProcess_Elements()
	 * @model containment="true"
	 * @generated
	 */
	EList<WorkflowModelElement> getElements();

	/**
	 * Returns the value of the '<em><b>Content Type</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Content Type</em>' reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Content Type</em>' reference.
	 * @see #setContentType(Clazz)
	 * @see com.bluexml.side.workflow.WorkflowPackage#getProcess_ContentType()
	 * @model required="true"
	 * @generated
	 */
	Clazz getContentType();

	/**
	 * Sets the value of the '{@link com.bluexml.side.workflow.Process#getContentType <em>Content Type</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Content Type</em>' reference.
	 * @see #getContentType()
	 * @generated
	 */
	void setContentType(Clazz value);

} // Process
