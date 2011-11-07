/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package com.bluexml.side.workflow;

import org.eclipse.emf.common.util.EList;

import com.bluexml.side.common.ModelElement;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Transition</b></em>'.
 * <!-- end-user-doc -->
 *
 * <!-- begin-model-doc -->
 * Definition: A 'Transition' represents a link between two nodes. 
 * 
 * Constraint/limit: Transitions must have a source node and a destination node.
 * <!-- end-model-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link com.bluexml.side.workflow.Transition#getName <em>Name</em>}</li>
 *   <li>{@link com.bluexml.side.workflow.Transition#getCondition <em>Condition</em>}</li>
 *   <li>{@link com.bluexml.side.workflow.Transition#getAction <em>Action</em>}</li>
 *   <li>{@link com.bluexml.side.workflow.Transition#getParentTaskNode <em>Parent Task Node</em>}</li>
 *   <li>{@link com.bluexml.side.workflow.Transition#getTimer <em>Timer</em>}</li>
 *   <li>{@link com.bluexml.side.workflow.Transition#getTo <em>To</em>}</li>
 *   <li>{@link com.bluexml.side.workflow.Transition#getTitle <em>Title</em>}</li>
 * </ul>
 * </p>
 *
 * @see com.bluexml.side.workflow.WorkflowPackage#getTransition()
 * @model annotation="http://www.bluexml.com/OCL NoTransitionWithSameName='Transition.allInstances() -> select(n|n.name = self.name and n <> self )->size()=0' SourceAndTargetMustBeSet='not self.to.oclIsUndefined() and not self.getContainer().oclIsUndefined()' noSpecialCharacters='self.name.regexMatch(\'[\\w]*\') = true' titleMustNotBeNull='if (self.getContainer().oclIsKindOf(UserTask)) then\n\tif (self.title = null or self.title.size() = 0) then\n\t\tfalse\n\telse\n\t\ttrue\n\tendif\nelse \n\ttrue\nendif'"
 *        annotation="http://www.eclipse.org/emf/2002/Ecore constraints='NoTransitionWithSameName SourceAndTargetMustBeSet noSpecialCharacters titleMustNotBeNull'"
 * @generated
 */
public interface Transition extends ModelElement {
	/**
	 * Returns the value of the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Name</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * Definition: The 'name' attribute specifies the name of the Transition.
	 * 
	 * Constraint/limit: The 'name' of the Transition must be unique.
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>Name</em>' attribute.
	 * @see #setName(String)
	 * @see com.bluexml.side.workflow.WorkflowPackage#getTransition_Name()
	 * @model unique="false" required="true" ordered="false"
	 * @generated
	 */
	String getName();

	/**
	 * Sets the value of the '{@link com.bluexml.side.workflow.Transition#getName <em>Name</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Name</em>' attribute.
	 * @see #getName()
	 * @generated
	 */
	void setName(String value);

	/**
	 * Returns the value of the '<em><b>Condition</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Condition</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * Definition: The 'condition' attribute specifies an expression that must be evaluate. The result is a boolean. If it returns the value true, the transition could be taken.
	 * 
	 * Example: Condition=#{choix == 1}
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>Condition</em>' attribute.
	 * @see #setCondition(String)
	 * @see com.bluexml.side.workflow.WorkflowPackage#getTransition_Condition()
	 * @model unique="false" ordered="false"
	 * @generated
	 */
	String getCondition();

	/**
	 * Sets the value of the '{@link com.bluexml.side.workflow.Transition#getCondition <em>Condition</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Condition</em>' attribute.
	 * @see #getCondition()
	 * @generated
	 */
	void setCondition(String value);

	/**
	 * Returns the value of the '<em><b>Action</b></em>' containment reference list.
	 * The list contents are of type {@link com.bluexml.side.workflow.Action}.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Action</em>' containment reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Action</em>' containment reference list.
	 * @see com.bluexml.side.workflow.WorkflowPackage#getTransition_Action()
	 * @model containment="true"
	 * @generated
	 */
	EList<Action> getAction();

	/**
	 * Returns the value of the '<em><b>Parent Task Node</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Parent Task Node</em>' container reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Parent Task Node</em>' reference.
	 * @see #setParentTaskNode(TaskNode)
	 * @see com.bluexml.side.workflow.WorkflowPackage#getTransition_ParentTaskNode()
	 * @model ordered="false"
	 * @generated
	 */
	TaskNode getParentTaskNode();

	/**
	 * Sets the value of the '{@link com.bluexml.side.workflow.Transition#getParentTaskNode <em>Parent Task Node</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Parent Task Node</em>' reference.
	 * @see #getParentTaskNode()
	 * @generated
	 */
	void setParentTaskNode(TaskNode value);

	/**
	 * Returns the value of the '<em><b>Timer</b></em>' containment reference list.
	 * The list contents are of type {@link com.bluexml.side.workflow.Timer}.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Timer</em>' containment reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Timer</em>' containment reference list.
	 * @see com.bluexml.side.workflow.WorkflowPackage#getTransition_Timer()
	 * @model containment="true"
	 * @generated
	 */
	EList<Timer> getTimer();

	/**
	 * Returns the value of the '<em><b>To</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>To</em>' reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>To</em>' reference.
	 * @see #setTo(State)
	 * @see com.bluexml.side.workflow.WorkflowPackage#getTransition_To()
	 * @model required="true"
	 * @generated
	 */
	State getTo();

	/**
	 * Sets the value of the '{@link com.bluexml.side.workflow.Transition#getTo <em>To</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>To</em>' reference.
	 * @see #getTo()
	 * @generated
	 */
	void setTo(State value);

	/**
	 * Returns the value of the '<em><b>Title</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Title</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * Definition: The 'title' attribute specifies the text that will appear in the target platform.
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>Title</em>' attribute.
	 * @see #setTitle(String)
	 * @see com.bluexml.side.workflow.WorkflowPackage#getTransition_Title()
	 * @model
	 * @generated
	 */
	String getTitle();

	/**
	 * Sets the value of the '{@link com.bluexml.side.workflow.Transition#getTitle <em>Title</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Title</em>' attribute.
	 * @see #getTitle()
	 * @generated
	 */
	void setTitle(String value);

} // Transition
