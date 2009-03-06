/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package com.bluexml.side.workflow;

import com.bluexml.side.common.CommonPackage;

import org.eclipse.emf.ecore.EAttribute;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EEnum;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.EReference;

/**
 * <!-- begin-user-doc -->
 * The <b>Package</b> for the model.
 * It contains accessors for the meta objects to represent
 * <ul>
 *   <li>each class,</li>
 *   <li>each feature of each class,</li>
 *   <li>each enum,</li>
 *   <li>and each data type</li>
 * </ul>
 * <!-- end-user-doc -->
 * @see com.bluexml.side.workflow.WorkflowFactory
 * @model kind="package"
 * @generated
 */
public interface WorkflowPackage extends EPackage {
	/**
	 * The package name.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	String eNAME = "workflow";

	/**
	 * The package namespace URI.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	String eNS_URI = "http://www.kerblue.org/workflow/1.0";

	/**
	 * The package namespace name.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	String eNS_PREFIX = "workflow";

	/**
	 * The singleton instance of the package.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	WorkflowPackage eINSTANCE = com.bluexml.side.workflow.impl.WorkflowPackageImpl.init();

	/**
	 * The meta object id for the '{@link com.bluexml.side.workflow.impl.WorkflowModelElementImpl <em>Model Element</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see com.bluexml.side.workflow.impl.WorkflowModelElementImpl
	 * @see com.bluexml.side.workflow.impl.WorkflowPackageImpl#getWorkflowModelElement()
	 * @generated
	 */
	int WORKFLOW_MODEL_ELEMENT = 0;

	/**
	 * The number of structural features of the '<em>Model Element</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int WORKFLOW_MODEL_ELEMENT_FEATURE_COUNT = 0;

	/**
	 * The meta object id for the '{@link com.bluexml.side.workflow.impl.ProcessImpl <em>Process</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see com.bluexml.side.workflow.impl.ProcessImpl
	 * @see com.bluexml.side.workflow.impl.WorkflowPackageImpl#getProcess()
	 * @generated
	 */
	int PROCESS = 1;

	/**
	 * The feature id for the '<em><b>Stereotypes</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PROCESS__STEREOTYPES = CommonPackage.PACKAGE__STEREOTYPES;

	/**
	 * The feature id for the '<em><b>Tags</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PROCESS__TAGS = CommonPackage.PACKAGE__TAGS;

	/**
	 * The feature id for the '<em><b>Comments</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PROCESS__COMMENTS = CommonPackage.PACKAGE__COMMENTS;

	/**
	 * The feature id for the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PROCESS__NAME = CommonPackage.PACKAGE__NAME;

	/**
	 * The feature id for the '<em><b>Documentation</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PROCESS__DOCUMENTATION = CommonPackage.PACKAGE__DOCUMENTATION;

	/**
	 * The feature id for the '<em><b>Stereotype Set</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PROCESS__STEREOTYPE_SET = CommonPackage.PACKAGE__STEREOTYPE_SET;

	/**
	 * The feature id for the '<em><b>Package Set</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PROCESS__PACKAGE_SET = CommonPackage.PACKAGE__PACKAGE_SET;

	/**
	 * The feature id for the '<em><b>Swimlane</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PROCESS__SWIMLANE = CommonPackage.PACKAGE_FEATURE_COUNT + 0;

	/**
	 * The feature id for the '<em><b>Startstate</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PROCESS__STARTSTATE = CommonPackage.PACKAGE_FEATURE_COUNT + 1;

	/**
	 * The feature id for the '<em><b>Endstate</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PROCESS__ENDSTATE = CommonPackage.PACKAGE_FEATURE_COUNT + 2;

	/**
	 * The feature id for the '<em><b>Node</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PROCESS__NODE = CommonPackage.PACKAGE_FEATURE_COUNT + 3;

	/**
	 * The feature id for the '<em><b>Tasknode</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PROCESS__TASKNODE = CommonPackage.PACKAGE_FEATURE_COUNT + 4;

	/**
	 * The feature id for the '<em><b>Processstate</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PROCESS__PROCESSSTATE = CommonPackage.PACKAGE_FEATURE_COUNT + 5;

	/**
	 * The feature id for the '<em><b>Fork</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PROCESS__FORK = CommonPackage.PACKAGE_FEATURE_COUNT + 6;

	/**
	 * The feature id for the '<em><b>Join</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PROCESS__JOIN = CommonPackage.PACKAGE_FEATURE_COUNT + 7;

	/**
	 * The feature id for the '<em><b>Decision</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PROCESS__DECISION = CommonPackage.PACKAGE_FEATURE_COUNT + 8;

	/**
	 * The number of structural features of the '<em>Process</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PROCESS_FEATURE_COUNT = CommonPackage.PACKAGE_FEATURE_COUNT + 9;

	/**
	 * The meta object id for the '{@link com.bluexml.side.workflow.impl.SwimlaneImpl <em>Swimlane</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see com.bluexml.side.workflow.impl.SwimlaneImpl
	 * @see com.bluexml.side.workflow.impl.WorkflowPackageImpl#getSwimlane()
	 * @generated
	 */
	int SWIMLANE = 2;

	/**
	 * The feature id for the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SWIMLANE__NAME = 0;

	/**
	 * The feature id for the '<em><b>Manage</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SWIMLANE__MANAGE = 1;

	/**
	 * The feature id for the '<em><b>Actorid</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SWIMLANE__ACTORID = 2;

	/**
	 * The feature id for the '<em><b>Pooledactors</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SWIMLANE__POOLEDACTORS = 3;

	/**
	 * The feature id for the '<em><b>Clazz</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SWIMLANE__CLAZZ = 4;

	/**
	 * The number of structural features of the '<em>Swimlane</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SWIMLANE_FEATURE_COUNT = 5;

	/**
	 * The meta object id for the '{@link com.bluexml.side.workflow.impl.StateImpl <em>State</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see com.bluexml.side.workflow.impl.StateImpl
	 * @see com.bluexml.side.workflow.impl.WorkflowPackageImpl#getState()
	 * @generated
	 */
	int STATE = 17;

	/**
	 * The number of structural features of the '<em>State</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int STATE_FEATURE_COUNT = 0;

	/**
	 * The meta object id for the '{@link com.bluexml.side.workflow.impl.StartStateImpl <em>Start State</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see com.bluexml.side.workflow.impl.StartStateImpl
	 * @see com.bluexml.side.workflow.impl.WorkflowPackageImpl#getStartState()
	 * @generated
	 */
	int START_STATE = 3;

	/**
	 * The feature id for the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int START_STATE__NAME = STATE_FEATURE_COUNT + 0;

	/**
	 * The feature id for the '<em><b>Transition</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int START_STATE__TRANSITION = STATE_FEATURE_COUNT + 1;

	/**
	 * The feature id for the '<em><b>Event</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int START_STATE__EVENT = STATE_FEATURE_COUNT + 2;

	/**
	 * The feature id for the '<em><b>Assignment Type</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int START_STATE__ASSIGNMENT_TYPE = STATE_FEATURE_COUNT + 3;

	/**
	 * The feature id for the '<em><b>Initiator</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int START_STATE__INITIATOR = STATE_FEATURE_COUNT + 4;

	/**
	 * The number of structural features of the '<em>Start State</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int START_STATE_FEATURE_COUNT = STATE_FEATURE_COUNT + 5;

	/**
	 * The meta object id for the '{@link com.bluexml.side.workflow.impl.EndStateImpl <em>End State</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see com.bluexml.side.workflow.impl.EndStateImpl
	 * @see com.bluexml.side.workflow.impl.WorkflowPackageImpl#getEndState()
	 * @generated
	 */
	int END_STATE = 4;

	/**
	 * The feature id for the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int END_STATE__NAME = STATE_FEATURE_COUNT + 0;

	/**
	 * The feature id for the '<em><b>Event</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int END_STATE__EVENT = STATE_FEATURE_COUNT + 1;

	/**
	 * The number of structural features of the '<em>End State</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int END_STATE_FEATURE_COUNT = STATE_FEATURE_COUNT + 2;

	/**
	 * The meta object id for the '{@link com.bluexml.side.workflow.impl.NodeImpl <em>Node</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see com.bluexml.side.workflow.impl.NodeImpl
	 * @see com.bluexml.side.workflow.impl.WorkflowPackageImpl#getNode()
	 * @generated
	 */
	int NODE = 5;

	/**
	 * The feature id for the '<em><b>Action</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int NODE__ACTION = STATE_FEATURE_COUNT + 0;

	/**
	 * The feature id for the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int NODE__NAME = STATE_FEATURE_COUNT + 1;

	/**
	 * The feature id for the '<em><b>Transition</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int NODE__TRANSITION = STATE_FEATURE_COUNT + 2;

	/**
	 * The feature id for the '<em><b>Event</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int NODE__EVENT = STATE_FEATURE_COUNT + 3;

	/**
	 * The number of structural features of the '<em>Node</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int NODE_FEATURE_COUNT = STATE_FEATURE_COUNT + 4;

	/**
	 * The meta object id for the '{@link com.bluexml.side.workflow.impl.TaskNodeImpl <em>Task Node</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see com.bluexml.side.workflow.impl.TaskNodeImpl
	 * @see com.bluexml.side.workflow.impl.WorkflowPackageImpl#getTaskNode()
	 * @generated
	 */
	int TASK_NODE = 6;

	/**
	 * The feature id for the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TASK_NODE__NAME = STATE_FEATURE_COUNT + 0;

	/**
	 * The feature id for the '<em><b>Transition</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TASK_NODE__TRANSITION = STATE_FEATURE_COUNT + 1;

	/**
	 * The feature id for the '<em><b>Event</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TASK_NODE__EVENT = STATE_FEATURE_COUNT + 2;

	/**
	 * The feature id for the '<em><b>Timer</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TASK_NODE__TIMER = STATE_FEATURE_COUNT + 3;

	/**
	 * The feature id for the '<em><b>Swimlane</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TASK_NODE__SWIMLANE = STATE_FEATURE_COUNT + 4;

	/**
	 * The number of structural features of the '<em>Task Node</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TASK_NODE_FEATURE_COUNT = STATE_FEATURE_COUNT + 5;

	/**
	 * The meta object id for the '{@link com.bluexml.side.workflow.impl.ProcessStateImpl <em>Process State</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see com.bluexml.side.workflow.impl.ProcessStateImpl
	 * @see com.bluexml.side.workflow.impl.WorkflowPackageImpl#getProcessState()
	 * @generated
	 */
	int PROCESS_STATE = 7;

	/**
	 * The feature id for the '<em><b>Subprocess</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PROCESS_STATE__SUBPROCESS = 0;

	/**
	 * The feature id for the '<em><b>Variable</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PROCESS_STATE__VARIABLE = 1;

	/**
	 * The number of structural features of the '<em>Process State</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PROCESS_STATE_FEATURE_COUNT = 2;

	/**
	 * The meta object id for the '{@link com.bluexml.side.workflow.impl.ForkImpl <em>Fork</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see com.bluexml.side.workflow.impl.ForkImpl
	 * @see com.bluexml.side.workflow.impl.WorkflowPackageImpl#getFork()
	 * @generated
	 */
	int FORK = 8;

	/**
	 * The feature id for the '<em><b>Transition</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int FORK__TRANSITION = STATE_FEATURE_COUNT + 0;

	/**
	 * The feature id for the '<em><b>Event</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int FORK__EVENT = STATE_FEATURE_COUNT + 1;

	/**
	 * The number of structural features of the '<em>Fork</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int FORK_FEATURE_COUNT = STATE_FEATURE_COUNT + 2;

	/**
	 * The meta object id for the '{@link com.bluexml.side.workflow.impl.JoinImpl <em>Join</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see com.bluexml.side.workflow.impl.JoinImpl
	 * @see com.bluexml.side.workflow.impl.WorkflowPackageImpl#getJoin()
	 * @generated
	 */
	int JOIN = 9;

	/**
	 * The feature id for the '<em><b>Transition</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int JOIN__TRANSITION = STATE_FEATURE_COUNT + 0;

	/**
	 * The feature id for the '<em><b>Event</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int JOIN__EVENT = STATE_FEATURE_COUNT + 1;

	/**
	 * The number of structural features of the '<em>Join</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int JOIN_FEATURE_COUNT = STATE_FEATURE_COUNT + 2;

	/**
	 * The meta object id for the '{@link com.bluexml.side.workflow.impl.DecisionImpl <em>Decision</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see com.bluexml.side.workflow.impl.DecisionImpl
	 * @see com.bluexml.side.workflow.impl.WorkflowPackageImpl#getDecision()
	 * @generated
	 */
	int DECISION = 10;

	/**
	 * The feature id for the '<em><b>Transition</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DECISION__TRANSITION = STATE_FEATURE_COUNT + 0;

	/**
	 * The feature id for the '<em><b>Event</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DECISION__EVENT = STATE_FEATURE_COUNT + 1;

	/**
	 * The number of structural features of the '<em>Decision</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DECISION_FEATURE_COUNT = STATE_FEATURE_COUNT + 2;

	/**
	 * The meta object id for the '{@link com.bluexml.side.workflow.impl.EventImpl <em>Event</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see com.bluexml.side.workflow.impl.EventImpl
	 * @see com.bluexml.side.workflow.impl.WorkflowPackageImpl#getEvent()
	 * @generated
	 */
	int EVENT = 11;

	/**
	 * The feature id for the '<em><b>Type</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int EVENT__TYPE = 0;

	/**
	 * The feature id for the '<em><b>Action</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int EVENT__ACTION = 1;

	/**
	 * The number of structural features of the '<em>Event</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int EVENT_FEATURE_COUNT = 2;

	/**
	 * The meta object id for the '{@link com.bluexml.side.workflow.impl.ActionImpl <em>Action</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see com.bluexml.side.workflow.impl.ActionImpl
	 * @see com.bluexml.side.workflow.impl.WorkflowPackageImpl#getAction()
	 * @generated
	 */
	int ACTION = 12;

	/**
	 * The feature id for the '<em><b>Java Class</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ACTION__JAVA_CLASS = 0;

	/**
	 * The feature id for the '<em><b>Expression</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ACTION__EXPRESSION = 1;

	/**
	 * The feature id for the '<em><b>Script</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ACTION__SCRIPT = 2;

	/**
	 * The feature id for the '<em><b>Parent Event</b></em>' container reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ACTION__PARENT_EVENT = 3;

	/**
	 * The feature id for the '<em><b>Parent Timer</b></em>' container reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ACTION__PARENT_TIMER = 4;

	/**
	 * The number of structural features of the '<em>Action</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ACTION_FEATURE_COUNT = 5;

	/**
	 * The meta object id for the '{@link com.bluexml.side.workflow.impl.ScriptImpl <em>Script</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see com.bluexml.side.workflow.impl.ScriptImpl
	 * @see com.bluexml.side.workflow.impl.WorkflowPackageImpl#getScript()
	 * @generated
	 */
	int SCRIPT = 13;

	/**
	 * The feature id for the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SCRIPT__NAME = 0;

	/**
	 * The feature id for the '<em><b>Variable</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SCRIPT__VARIABLE = 1;

	/**
	 * The feature id for the '<em><b>Expression</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SCRIPT__EXPRESSION = 2;

	/**
	 * The number of structural features of the '<em>Script</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SCRIPT_FEATURE_COUNT = 3;

	/**
	 * The meta object id for the '{@link com.bluexml.side.workflow.impl.TimerImpl <em>Timer</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see com.bluexml.side.workflow.impl.TimerImpl
	 * @see com.bluexml.side.workflow.impl.WorkflowPackageImpl#getTimer()
	 * @generated
	 */
	int TIMER = 14;

	/**
	 * The feature id for the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TIMER__NAME = 0;

	/**
	 * The feature id for the '<em><b>Duedate</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TIMER__DUEDATE = 1;

	/**
	 * The feature id for the '<em><b>Action</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TIMER__ACTION = 2;

	/**
	 * The number of structural features of the '<em>Timer</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TIMER_FEATURE_COUNT = 3;

	/**
	 * The meta object id for the '{@link com.bluexml.side.workflow.impl.VariableImpl <em>Variable</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see com.bluexml.side.workflow.impl.VariableImpl
	 * @see com.bluexml.side.workflow.impl.WorkflowPackageImpl#getVariable()
	 * @generated
	 */
	int VARIABLE = 15;

	/**
	 * The feature id for the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int VARIABLE__NAME = 0;

	/**
	 * The feature id for the '<em><b>Access</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int VARIABLE__ACCESS = 1;

	/**
	 * The number of structural features of the '<em>Variable</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int VARIABLE_FEATURE_COUNT = 2;

	/**
	 * The meta object id for the '{@link com.bluexml.side.workflow.impl.TransitionImpl <em>Transition</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see com.bluexml.side.workflow.impl.TransitionImpl
	 * @see com.bluexml.side.workflow.impl.WorkflowPackageImpl#getTransition()
	 * @generated
	 */
	int TRANSITION = 16;

	/**
	 * The feature id for the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TRANSITION__NAME = 0;

	/**
	 * The feature id for the '<em><b>Condition</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TRANSITION__CONDITION = 1;

	/**
	 * The feature id for the '<em><b>Action</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TRANSITION__ACTION = 2;

	/**
	 * The feature id for the '<em><b>Parent Task Node</b></em>' container reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TRANSITION__PARENT_TASK_NODE = 3;

	/**
	 * The feature id for the '<em><b>Timer</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TRANSITION__TIMER = 4;

	/**
	 * The feature id for the '<em><b>To</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TRANSITION__TO = 5;

	/**
	 * The feature id for the '<em><b>Title</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TRANSITION__TITLE = 6;

	/**
	 * The number of structural features of the '<em>Transition</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TRANSITION_FEATURE_COUNT = 7;

	/**
	 * The meta object id for the '{@link com.bluexml.side.workflow.BPMEventType <em>BPM Event Type</em>}' enum.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see com.bluexml.side.workflow.BPMEventType
	 * @see com.bluexml.side.workflow.impl.WorkflowPackageImpl#getBPMEventType()
	 * @generated
	 */
	int BPM_EVENT_TYPE = 18;

	/**
	 * The meta object id for the '{@link com.bluexml.side.workflow.BPMAssignmentType <em>BPM Assignment Type</em>}' enum.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see com.bluexml.side.workflow.BPMAssignmentType
	 * @see com.bluexml.side.workflow.impl.WorkflowPackageImpl#getBPMAssignmentType()
	 * @generated
	 */
	int BPM_ASSIGNMENT_TYPE = 19;


	/**
	 * Returns the meta object for class '{@link com.bluexml.side.workflow.WorkflowModelElement <em>Model Element</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Model Element</em>'.
	 * @see com.bluexml.side.workflow.WorkflowModelElement
	 * @generated
	 */
	EClass getWorkflowModelElement();

	/**
	 * Returns the meta object for class '{@link com.bluexml.side.workflow.Process <em>Process</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Process</em>'.
	 * @see com.bluexml.side.workflow.Process
	 * @generated
	 */
	EClass getProcess();

	/**
	 * Returns the meta object for the containment reference list '{@link com.bluexml.side.workflow.Process#getSwimlane <em>Swimlane</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference list '<em>Swimlane</em>'.
	 * @see com.bluexml.side.workflow.Process#getSwimlane()
	 * @see #getProcess()
	 * @generated
	 */
	EReference getProcess_Swimlane();

	/**
	 * Returns the meta object for the containment reference '{@link com.bluexml.side.workflow.Process#getStartstate <em>Startstate</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference '<em>Startstate</em>'.
	 * @see com.bluexml.side.workflow.Process#getStartstate()
	 * @see #getProcess()
	 * @generated
	 */
	EReference getProcess_Startstate();

	/**
	 * Returns the meta object for the containment reference list '{@link com.bluexml.side.workflow.Process#getEndstate <em>Endstate</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference list '<em>Endstate</em>'.
	 * @see com.bluexml.side.workflow.Process#getEndstate()
	 * @see #getProcess()
	 * @generated
	 */
	EReference getProcess_Endstate();

	/**
	 * Returns the meta object for the containment reference list '{@link com.bluexml.side.workflow.Process#getNode <em>Node</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference list '<em>Node</em>'.
	 * @see com.bluexml.side.workflow.Process#getNode()
	 * @see #getProcess()
	 * @generated
	 */
	EReference getProcess_Node();

	/**
	 * Returns the meta object for the containment reference list '{@link com.bluexml.side.workflow.Process#getTasknode <em>Tasknode</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference list '<em>Tasknode</em>'.
	 * @see com.bluexml.side.workflow.Process#getTasknode()
	 * @see #getProcess()
	 * @generated
	 */
	EReference getProcess_Tasknode();

	/**
	 * Returns the meta object for the containment reference list '{@link com.bluexml.side.workflow.Process#getProcessstate <em>Processstate</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference list '<em>Processstate</em>'.
	 * @see com.bluexml.side.workflow.Process#getProcessstate()
	 * @see #getProcess()
	 * @generated
	 */
	EReference getProcess_Processstate();

	/**
	 * Returns the meta object for the containment reference list '{@link com.bluexml.side.workflow.Process#getFork <em>Fork</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference list '<em>Fork</em>'.
	 * @see com.bluexml.side.workflow.Process#getFork()
	 * @see #getProcess()
	 * @generated
	 */
	EReference getProcess_Fork();

	/**
	 * Returns the meta object for the containment reference list '{@link com.bluexml.side.workflow.Process#getJoin <em>Join</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference list '<em>Join</em>'.
	 * @see com.bluexml.side.workflow.Process#getJoin()
	 * @see #getProcess()
	 * @generated
	 */
	EReference getProcess_Join();

	/**
	 * Returns the meta object for the containment reference list '{@link com.bluexml.side.workflow.Process#getDecision <em>Decision</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference list '<em>Decision</em>'.
	 * @see com.bluexml.side.workflow.Process#getDecision()
	 * @see #getProcess()
	 * @generated
	 */
	EReference getProcess_Decision();

	/**
	 * Returns the meta object for class '{@link com.bluexml.side.workflow.Swimlane <em>Swimlane</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Swimlane</em>'.
	 * @see com.bluexml.side.workflow.Swimlane
	 * @generated
	 */
	EClass getSwimlane();

	/**
	 * Returns the meta object for the attribute '{@link com.bluexml.side.workflow.Swimlane#getName <em>Name</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Name</em>'.
	 * @see com.bluexml.side.workflow.Swimlane#getName()
	 * @see #getSwimlane()
	 * @generated
	 */
	EAttribute getSwimlane_Name();

	/**
	 * Returns the meta object for the reference list '{@link com.bluexml.side.workflow.Swimlane#getManage <em>Manage</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference list '<em>Manage</em>'.
	 * @see com.bluexml.side.workflow.Swimlane#getManage()
	 * @see #getSwimlane()
	 * @generated
	 */
	EReference getSwimlane_Manage();

	/**
	 * Returns the meta object for the attribute '{@link com.bluexml.side.workflow.Swimlane#getActorid <em>Actorid</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Actorid</em>'.
	 * @see com.bluexml.side.workflow.Swimlane#getActorid()
	 * @see #getSwimlane()
	 * @generated
	 */
	EAttribute getSwimlane_Actorid();

	/**
	 * Returns the meta object for the attribute '{@link com.bluexml.side.workflow.Swimlane#getPooledactors <em>Pooledactors</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Pooledactors</em>'.
	 * @see com.bluexml.side.workflow.Swimlane#getPooledactors()
	 * @see #getSwimlane()
	 * @generated
	 */
	EAttribute getSwimlane_Pooledactors();

	/**
	 * Returns the meta object for the attribute '{@link com.bluexml.side.workflow.Swimlane#getClazz <em>Clazz</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Clazz</em>'.
	 * @see com.bluexml.side.workflow.Swimlane#getClazz()
	 * @see #getSwimlane()
	 * @generated
	 */
	EAttribute getSwimlane_Clazz();

	/**
	 * Returns the meta object for class '{@link com.bluexml.side.workflow.StartState <em>Start State</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Start State</em>'.
	 * @see com.bluexml.side.workflow.StartState
	 * @generated
	 */
	EClass getStartState();

	/**
	 * Returns the meta object for the attribute '{@link com.bluexml.side.workflow.StartState#getName <em>Name</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Name</em>'.
	 * @see com.bluexml.side.workflow.StartState#getName()
	 * @see #getStartState()
	 * @generated
	 */
	EAttribute getStartState_Name();

	/**
	 * Returns the meta object for the containment reference list '{@link com.bluexml.side.workflow.StartState#getTransition <em>Transition</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference list '<em>Transition</em>'.
	 * @see com.bluexml.side.workflow.StartState#getTransition()
	 * @see #getStartState()
	 * @generated
	 */
	EReference getStartState_Transition();

	/**
	 * Returns the meta object for the containment reference list '{@link com.bluexml.side.workflow.StartState#getEvent <em>Event</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference list '<em>Event</em>'.
	 * @see com.bluexml.side.workflow.StartState#getEvent()
	 * @see #getStartState()
	 * @generated
	 */
	EReference getStartState_Event();

	/**
	 * Returns the meta object for the attribute '{@link com.bluexml.side.workflow.StartState#getAssignmentType <em>Assignment Type</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Assignment Type</em>'.
	 * @see com.bluexml.side.workflow.StartState#getAssignmentType()
	 * @see #getStartState()
	 * @generated
	 */
	EAttribute getStartState_AssignmentType();

	/**
	 * Returns the meta object for the reference '{@link com.bluexml.side.workflow.StartState#getInitiator <em>Initiator</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference '<em>Initiator</em>'.
	 * @see com.bluexml.side.workflow.StartState#getInitiator()
	 * @see #getStartState()
	 * @generated
	 */
	EReference getStartState_Initiator();

	/**
	 * Returns the meta object for class '{@link com.bluexml.side.workflow.EndState <em>End State</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>End State</em>'.
	 * @see com.bluexml.side.workflow.EndState
	 * @generated
	 */
	EClass getEndState();

	/**
	 * Returns the meta object for the attribute '{@link com.bluexml.side.workflow.EndState#getName <em>Name</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Name</em>'.
	 * @see com.bluexml.side.workflow.EndState#getName()
	 * @see #getEndState()
	 * @generated
	 */
	EAttribute getEndState_Name();

	/**
	 * Returns the meta object for the containment reference list '{@link com.bluexml.side.workflow.EndState#getEvent <em>Event</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference list '<em>Event</em>'.
	 * @see com.bluexml.side.workflow.EndState#getEvent()
	 * @see #getEndState()
	 * @generated
	 */
	EReference getEndState_Event();

	/**
	 * Returns the meta object for class '{@link com.bluexml.side.workflow.Node <em>Node</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Node</em>'.
	 * @see com.bluexml.side.workflow.Node
	 * @generated
	 */
	EClass getNode();

	/**
	 * Returns the meta object for the containment reference '{@link com.bluexml.side.workflow.Node#getAction <em>Action</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference '<em>Action</em>'.
	 * @see com.bluexml.side.workflow.Node#getAction()
	 * @see #getNode()
	 * @generated
	 */
	EReference getNode_Action();

	/**
	 * Returns the meta object for the attribute '{@link com.bluexml.side.workflow.Node#getName <em>Name</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Name</em>'.
	 * @see com.bluexml.side.workflow.Node#getName()
	 * @see #getNode()
	 * @generated
	 */
	EAttribute getNode_Name();

	/**
	 * Returns the meta object for the containment reference list '{@link com.bluexml.side.workflow.Node#getTransition <em>Transition</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference list '<em>Transition</em>'.
	 * @see com.bluexml.side.workflow.Node#getTransition()
	 * @see #getNode()
	 * @generated
	 */
	EReference getNode_Transition();

	/**
	 * Returns the meta object for the containment reference list '{@link com.bluexml.side.workflow.Node#getEvent <em>Event</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference list '<em>Event</em>'.
	 * @see com.bluexml.side.workflow.Node#getEvent()
	 * @see #getNode()
	 * @generated
	 */
	EReference getNode_Event();

	/**
	 * Returns the meta object for class '{@link com.bluexml.side.workflow.TaskNode <em>Task Node</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Task Node</em>'.
	 * @see com.bluexml.side.workflow.TaskNode
	 * @generated
	 */
	EClass getTaskNode();

	/**
	 * Returns the meta object for the attribute '{@link com.bluexml.side.workflow.TaskNode#getName <em>Name</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Name</em>'.
	 * @see com.bluexml.side.workflow.TaskNode#getName()
	 * @see #getTaskNode()
	 * @generated
	 */
	EAttribute getTaskNode_Name();

	/**
	 * Returns the meta object for the containment reference list '{@link com.bluexml.side.workflow.TaskNode#getTransition <em>Transition</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference list '<em>Transition</em>'.
	 * @see com.bluexml.side.workflow.TaskNode#getTransition()
	 * @see #getTaskNode()
	 * @generated
	 */
	EReference getTaskNode_Transition();

	/**
	 * Returns the meta object for the containment reference list '{@link com.bluexml.side.workflow.TaskNode#getEvent <em>Event</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference list '<em>Event</em>'.
	 * @see com.bluexml.side.workflow.TaskNode#getEvent()
	 * @see #getTaskNode()
	 * @generated
	 */
	EReference getTaskNode_Event();

	/**
	 * Returns the meta object for the containment reference list '{@link com.bluexml.side.workflow.TaskNode#getTimer <em>Timer</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference list '<em>Timer</em>'.
	 * @see com.bluexml.side.workflow.TaskNode#getTimer()
	 * @see #getTaskNode()
	 * @generated
	 */
	EReference getTaskNode_Timer();

	/**
	 * Returns the meta object for the reference '{@link com.bluexml.side.workflow.TaskNode#getSwimlane <em>Swimlane</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference '<em>Swimlane</em>'.
	 * @see com.bluexml.side.workflow.TaskNode#getSwimlane()
	 * @see #getTaskNode()
	 * @generated
	 */
	EReference getTaskNode_Swimlane();

	/**
	 * Returns the meta object for class '{@link com.bluexml.side.workflow.ProcessState <em>Process State</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Process State</em>'.
	 * @see com.bluexml.side.workflow.ProcessState
	 * @generated
	 */
	EClass getProcessState();

	/**
	 * Returns the meta object for the reference '{@link com.bluexml.side.workflow.ProcessState#getSubprocess <em>Subprocess</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference '<em>Subprocess</em>'.
	 * @see com.bluexml.side.workflow.ProcessState#getSubprocess()
	 * @see #getProcessState()
	 * @generated
	 */
	EReference getProcessState_Subprocess();

	/**
	 * Returns the meta object for the containment reference list '{@link com.bluexml.side.workflow.ProcessState#getVariable <em>Variable</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference list '<em>Variable</em>'.
	 * @see com.bluexml.side.workflow.ProcessState#getVariable()
	 * @see #getProcessState()
	 * @generated
	 */
	EReference getProcessState_Variable();

	/**
	 * Returns the meta object for class '{@link com.bluexml.side.workflow.Fork <em>Fork</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Fork</em>'.
	 * @see com.bluexml.side.workflow.Fork
	 * @generated
	 */
	EClass getFork();

	/**
	 * Returns the meta object for the containment reference list '{@link com.bluexml.side.workflow.Fork#getTransition <em>Transition</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference list '<em>Transition</em>'.
	 * @see com.bluexml.side.workflow.Fork#getTransition()
	 * @see #getFork()
	 * @generated
	 */
	EReference getFork_Transition();

	/**
	 * Returns the meta object for the containment reference list '{@link com.bluexml.side.workflow.Fork#getEvent <em>Event</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference list '<em>Event</em>'.
	 * @see com.bluexml.side.workflow.Fork#getEvent()
	 * @see #getFork()
	 * @generated
	 */
	EReference getFork_Event();

	/**
	 * Returns the meta object for class '{@link com.bluexml.side.workflow.Join <em>Join</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Join</em>'.
	 * @see com.bluexml.side.workflow.Join
	 * @generated
	 */
	EClass getJoin();

	/**
	 * Returns the meta object for the containment reference list '{@link com.bluexml.side.workflow.Join#getTransition <em>Transition</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference list '<em>Transition</em>'.
	 * @see com.bluexml.side.workflow.Join#getTransition()
	 * @see #getJoin()
	 * @generated
	 */
	EReference getJoin_Transition();

	/**
	 * Returns the meta object for the containment reference list '{@link com.bluexml.side.workflow.Join#getEvent <em>Event</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference list '<em>Event</em>'.
	 * @see com.bluexml.side.workflow.Join#getEvent()
	 * @see #getJoin()
	 * @generated
	 */
	EReference getJoin_Event();

	/**
	 * Returns the meta object for class '{@link com.bluexml.side.workflow.Decision <em>Decision</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Decision</em>'.
	 * @see com.bluexml.side.workflow.Decision
	 * @generated
	 */
	EClass getDecision();

	/**
	 * Returns the meta object for the containment reference list '{@link com.bluexml.side.workflow.Decision#getTransition <em>Transition</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference list '<em>Transition</em>'.
	 * @see com.bluexml.side.workflow.Decision#getTransition()
	 * @see #getDecision()
	 * @generated
	 */
	EReference getDecision_Transition();

	/**
	 * Returns the meta object for the containment reference list '{@link com.bluexml.side.workflow.Decision#getEvent <em>Event</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference list '<em>Event</em>'.
	 * @see com.bluexml.side.workflow.Decision#getEvent()
	 * @see #getDecision()
	 * @generated
	 */
	EReference getDecision_Event();

	/**
	 * Returns the meta object for class '{@link com.bluexml.side.workflow.Event <em>Event</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Event</em>'.
	 * @see com.bluexml.side.workflow.Event
	 * @generated
	 */
	EClass getEvent();

	/**
	 * Returns the meta object for the attribute '{@link com.bluexml.side.workflow.Event#getType <em>Type</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Type</em>'.
	 * @see com.bluexml.side.workflow.Event#getType()
	 * @see #getEvent()
	 * @generated
	 */
	EAttribute getEvent_Type();

	/**
	 * Returns the meta object for the containment reference list '{@link com.bluexml.side.workflow.Event#getAction <em>Action</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference list '<em>Action</em>'.
	 * @see com.bluexml.side.workflow.Event#getAction()
	 * @see #getEvent()
	 * @generated
	 */
	EReference getEvent_Action();

	/**
	 * Returns the meta object for class '{@link com.bluexml.side.workflow.Action <em>Action</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Action</em>'.
	 * @see com.bluexml.side.workflow.Action
	 * @generated
	 */
	EClass getAction();

	/**
	 * Returns the meta object for the attribute '{@link com.bluexml.side.workflow.Action#getJavaClass <em>Java Class</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Java Class</em>'.
	 * @see com.bluexml.side.workflow.Action#getJavaClass()
	 * @see #getAction()
	 * @generated
	 */
	EAttribute getAction_JavaClass();

	/**
	 * Returns the meta object for the attribute '{@link com.bluexml.side.workflow.Action#getExpression <em>Expression</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Expression</em>'.
	 * @see com.bluexml.side.workflow.Action#getExpression()
	 * @see #getAction()
	 * @generated
	 */
	EAttribute getAction_Expression();

	/**
	 * Returns the meta object for the containment reference list '{@link com.bluexml.side.workflow.Action#getScript <em>Script</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference list '<em>Script</em>'.
	 * @see com.bluexml.side.workflow.Action#getScript()
	 * @see #getAction()
	 * @generated
	 */
	EReference getAction_Script();

	/**
	 * Returns the meta object for the container reference '{@link com.bluexml.side.workflow.Action#getParentEvent <em>Parent Event</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the container reference '<em>Parent Event</em>'.
	 * @see com.bluexml.side.workflow.Action#getParentEvent()
	 * @see #getAction()
	 * @generated
	 */
	EReference getAction_ParentEvent();

	/**
	 * Returns the meta object for the container reference '{@link com.bluexml.side.workflow.Action#getParentTimer <em>Parent Timer</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the container reference '<em>Parent Timer</em>'.
	 * @see com.bluexml.side.workflow.Action#getParentTimer()
	 * @see #getAction()
	 * @generated
	 */
	EReference getAction_ParentTimer();

	/**
	 * Returns the meta object for class '{@link com.bluexml.side.workflow.Script <em>Script</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Script</em>'.
	 * @see com.bluexml.side.workflow.Script
	 * @generated
	 */
	EClass getScript();

	/**
	 * Returns the meta object for the attribute '{@link com.bluexml.side.workflow.Script#getName <em>Name</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Name</em>'.
	 * @see com.bluexml.side.workflow.Script#getName()
	 * @see #getScript()
	 * @generated
	 */
	EAttribute getScript_Name();

	/**
	 * Returns the meta object for the containment reference list '{@link com.bluexml.side.workflow.Script#getVariable <em>Variable</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference list '<em>Variable</em>'.
	 * @see com.bluexml.side.workflow.Script#getVariable()
	 * @see #getScript()
	 * @generated
	 */
	EReference getScript_Variable();

	/**
	 * Returns the meta object for the attribute '{@link com.bluexml.side.workflow.Script#getExpression <em>Expression</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Expression</em>'.
	 * @see com.bluexml.side.workflow.Script#getExpression()
	 * @see #getScript()
	 * @generated
	 */
	EAttribute getScript_Expression();

	/**
	 * Returns the meta object for class '{@link com.bluexml.side.workflow.Timer <em>Timer</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Timer</em>'.
	 * @see com.bluexml.side.workflow.Timer
	 * @generated
	 */
	EClass getTimer();

	/**
	 * Returns the meta object for the attribute '{@link com.bluexml.side.workflow.Timer#getName <em>Name</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Name</em>'.
	 * @see com.bluexml.side.workflow.Timer#getName()
	 * @see #getTimer()
	 * @generated
	 */
	EAttribute getTimer_Name();

	/**
	 * Returns the meta object for the attribute '{@link com.bluexml.side.workflow.Timer#getDuedate <em>Duedate</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Duedate</em>'.
	 * @see com.bluexml.side.workflow.Timer#getDuedate()
	 * @see #getTimer()
	 * @generated
	 */
	EAttribute getTimer_Duedate();

	/**
	 * Returns the meta object for the containment reference '{@link com.bluexml.side.workflow.Timer#getAction <em>Action</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference '<em>Action</em>'.
	 * @see com.bluexml.side.workflow.Timer#getAction()
	 * @see #getTimer()
	 * @generated
	 */
	EReference getTimer_Action();

	/**
	 * Returns the meta object for class '{@link com.bluexml.side.workflow.Variable <em>Variable</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Variable</em>'.
	 * @see com.bluexml.side.workflow.Variable
	 * @generated
	 */
	EClass getVariable();

	/**
	 * Returns the meta object for the attribute '{@link com.bluexml.side.workflow.Variable#getName <em>Name</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Name</em>'.
	 * @see com.bluexml.side.workflow.Variable#getName()
	 * @see #getVariable()
	 * @generated
	 */
	EAttribute getVariable_Name();

	/**
	 * Returns the meta object for the attribute '{@link com.bluexml.side.workflow.Variable#getAccess <em>Access</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Access</em>'.
	 * @see com.bluexml.side.workflow.Variable#getAccess()
	 * @see #getVariable()
	 * @generated
	 */
	EAttribute getVariable_Access();

	/**
	 * Returns the meta object for class '{@link com.bluexml.side.workflow.Transition <em>Transition</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Transition</em>'.
	 * @see com.bluexml.side.workflow.Transition
	 * @generated
	 */
	EClass getTransition();

	/**
	 * Returns the meta object for the attribute '{@link com.bluexml.side.workflow.Transition#getName <em>Name</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Name</em>'.
	 * @see com.bluexml.side.workflow.Transition#getName()
	 * @see #getTransition()
	 * @generated
	 */
	EAttribute getTransition_Name();

	/**
	 * Returns the meta object for the attribute '{@link com.bluexml.side.workflow.Transition#getCondition <em>Condition</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Condition</em>'.
	 * @see com.bluexml.side.workflow.Transition#getCondition()
	 * @see #getTransition()
	 * @generated
	 */
	EAttribute getTransition_Condition();

	/**
	 * Returns the meta object for the containment reference list '{@link com.bluexml.side.workflow.Transition#getAction <em>Action</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference list '<em>Action</em>'.
	 * @see com.bluexml.side.workflow.Transition#getAction()
	 * @see #getTransition()
	 * @generated
	 */
	EReference getTransition_Action();

	/**
	 * Returns the meta object for the container reference '{@link com.bluexml.side.workflow.Transition#getParentTaskNode <em>Parent Task Node</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the container reference '<em>Parent Task Node</em>'.
	 * @see com.bluexml.side.workflow.Transition#getParentTaskNode()
	 * @see #getTransition()
	 * @generated
	 */
	EReference getTransition_ParentTaskNode();

	/**
	 * Returns the meta object for the containment reference list '{@link com.bluexml.side.workflow.Transition#getTimer <em>Timer</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference list '<em>Timer</em>'.
	 * @see com.bluexml.side.workflow.Transition#getTimer()
	 * @see #getTransition()
	 * @generated
	 */
	EReference getTransition_Timer();

	/**
	 * Returns the meta object for the reference '{@link com.bluexml.side.workflow.Transition#getTo <em>To</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference '<em>To</em>'.
	 * @see com.bluexml.side.workflow.Transition#getTo()
	 * @see #getTransition()
	 * @generated
	 */
	EReference getTransition_To();

	/**
	 * Returns the meta object for the attribute '{@link com.bluexml.side.workflow.Transition#getTitle <em>Title</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Title</em>'.
	 * @see com.bluexml.side.workflow.Transition#getTitle()
	 * @see #getTransition()
	 * @generated
	 */
	EAttribute getTransition_Title();

	/**
	 * Returns the meta object for class '{@link com.bluexml.side.workflow.State <em>State</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>State</em>'.
	 * @see com.bluexml.side.workflow.State
	 * @generated
	 */
	EClass getState();

	/**
	 * Returns the meta object for enum '{@link com.bluexml.side.workflow.BPMEventType <em>BPM Event Type</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for enum '<em>BPM Event Type</em>'.
	 * @see com.bluexml.side.workflow.BPMEventType
	 * @generated
	 */
	EEnum getBPMEventType();

	/**
	 * Returns the meta object for enum '{@link com.bluexml.side.workflow.BPMAssignmentType <em>BPM Assignment Type</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for enum '<em>BPM Assignment Type</em>'.
	 * @see com.bluexml.side.workflow.BPMAssignmentType
	 * @generated
	 */
	EEnum getBPMAssignmentType();

	/**
	 * Returns the factory that creates the instances of the model.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the factory that creates the instances of the model.
	 * @generated
	 */
	WorkflowFactory getWorkflowFactory();

	/**
	 * <!-- begin-user-doc -->
	 * Defines literals for the meta objects that represent
	 * <ul>
	 *   <li>each class,</li>
	 *   <li>each feature of each class,</li>
	 *   <li>each enum,</li>
	 *   <li>and each data type</li>
	 * </ul>
	 * <!-- end-user-doc -->
	 * @generated
	 */
	interface Literals {
		/**
		 * The meta object literal for the '{@link com.bluexml.side.workflow.impl.WorkflowModelElementImpl <em>Model Element</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see com.bluexml.side.workflow.impl.WorkflowModelElementImpl
		 * @see com.bluexml.side.workflow.impl.WorkflowPackageImpl#getWorkflowModelElement()
		 * @generated
		 */
		EClass WORKFLOW_MODEL_ELEMENT = eINSTANCE.getWorkflowModelElement();

		/**
		 * The meta object literal for the '{@link com.bluexml.side.workflow.impl.ProcessImpl <em>Process</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see com.bluexml.side.workflow.impl.ProcessImpl
		 * @see com.bluexml.side.workflow.impl.WorkflowPackageImpl#getProcess()
		 * @generated
		 */
		EClass PROCESS = eINSTANCE.getProcess();

		/**
		 * The meta object literal for the '<em><b>Swimlane</b></em>' containment reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference PROCESS__SWIMLANE = eINSTANCE.getProcess_Swimlane();

		/**
		 * The meta object literal for the '<em><b>Startstate</b></em>' containment reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference PROCESS__STARTSTATE = eINSTANCE.getProcess_Startstate();

		/**
		 * The meta object literal for the '<em><b>Endstate</b></em>' containment reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference PROCESS__ENDSTATE = eINSTANCE.getProcess_Endstate();

		/**
		 * The meta object literal for the '<em><b>Node</b></em>' containment reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference PROCESS__NODE = eINSTANCE.getProcess_Node();

		/**
		 * The meta object literal for the '<em><b>Tasknode</b></em>' containment reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference PROCESS__TASKNODE = eINSTANCE.getProcess_Tasknode();

		/**
		 * The meta object literal for the '<em><b>Processstate</b></em>' containment reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference PROCESS__PROCESSSTATE = eINSTANCE.getProcess_Processstate();

		/**
		 * The meta object literal for the '<em><b>Fork</b></em>' containment reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference PROCESS__FORK = eINSTANCE.getProcess_Fork();

		/**
		 * The meta object literal for the '<em><b>Join</b></em>' containment reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference PROCESS__JOIN = eINSTANCE.getProcess_Join();

		/**
		 * The meta object literal for the '<em><b>Decision</b></em>' containment reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference PROCESS__DECISION = eINSTANCE.getProcess_Decision();

		/**
		 * The meta object literal for the '{@link com.bluexml.side.workflow.impl.SwimlaneImpl <em>Swimlane</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see com.bluexml.side.workflow.impl.SwimlaneImpl
		 * @see com.bluexml.side.workflow.impl.WorkflowPackageImpl#getSwimlane()
		 * @generated
		 */
		EClass SWIMLANE = eINSTANCE.getSwimlane();

		/**
		 * The meta object literal for the '<em><b>Name</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute SWIMLANE__NAME = eINSTANCE.getSwimlane_Name();

		/**
		 * The meta object literal for the '<em><b>Manage</b></em>' reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference SWIMLANE__MANAGE = eINSTANCE.getSwimlane_Manage();

		/**
		 * The meta object literal for the '<em><b>Actorid</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute SWIMLANE__ACTORID = eINSTANCE.getSwimlane_Actorid();

		/**
		 * The meta object literal for the '<em><b>Pooledactors</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute SWIMLANE__POOLEDACTORS = eINSTANCE.getSwimlane_Pooledactors();

		/**
		 * The meta object literal for the '<em><b>Clazz</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute SWIMLANE__CLAZZ = eINSTANCE.getSwimlane_Clazz();

		/**
		 * The meta object literal for the '{@link com.bluexml.side.workflow.impl.StartStateImpl <em>Start State</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see com.bluexml.side.workflow.impl.StartStateImpl
		 * @see com.bluexml.side.workflow.impl.WorkflowPackageImpl#getStartState()
		 * @generated
		 */
		EClass START_STATE = eINSTANCE.getStartState();

		/**
		 * The meta object literal for the '<em><b>Name</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute START_STATE__NAME = eINSTANCE.getStartState_Name();

		/**
		 * The meta object literal for the '<em><b>Transition</b></em>' containment reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference START_STATE__TRANSITION = eINSTANCE.getStartState_Transition();

		/**
		 * The meta object literal for the '<em><b>Event</b></em>' containment reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference START_STATE__EVENT = eINSTANCE.getStartState_Event();

		/**
		 * The meta object literal for the '<em><b>Assignment Type</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute START_STATE__ASSIGNMENT_TYPE = eINSTANCE.getStartState_AssignmentType();

		/**
		 * The meta object literal for the '<em><b>Initiator</b></em>' reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference START_STATE__INITIATOR = eINSTANCE.getStartState_Initiator();

		/**
		 * The meta object literal for the '{@link com.bluexml.side.workflow.impl.EndStateImpl <em>End State</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see com.bluexml.side.workflow.impl.EndStateImpl
		 * @see com.bluexml.side.workflow.impl.WorkflowPackageImpl#getEndState()
		 * @generated
		 */
		EClass END_STATE = eINSTANCE.getEndState();

		/**
		 * The meta object literal for the '<em><b>Name</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute END_STATE__NAME = eINSTANCE.getEndState_Name();

		/**
		 * The meta object literal for the '<em><b>Event</b></em>' containment reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference END_STATE__EVENT = eINSTANCE.getEndState_Event();

		/**
		 * The meta object literal for the '{@link com.bluexml.side.workflow.impl.NodeImpl <em>Node</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see com.bluexml.side.workflow.impl.NodeImpl
		 * @see com.bluexml.side.workflow.impl.WorkflowPackageImpl#getNode()
		 * @generated
		 */
		EClass NODE = eINSTANCE.getNode();

		/**
		 * The meta object literal for the '<em><b>Action</b></em>' containment reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference NODE__ACTION = eINSTANCE.getNode_Action();

		/**
		 * The meta object literal for the '<em><b>Name</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute NODE__NAME = eINSTANCE.getNode_Name();

		/**
		 * The meta object literal for the '<em><b>Transition</b></em>' containment reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference NODE__TRANSITION = eINSTANCE.getNode_Transition();

		/**
		 * The meta object literal for the '<em><b>Event</b></em>' containment reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference NODE__EVENT = eINSTANCE.getNode_Event();

		/**
		 * The meta object literal for the '{@link com.bluexml.side.workflow.impl.TaskNodeImpl <em>Task Node</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see com.bluexml.side.workflow.impl.TaskNodeImpl
		 * @see com.bluexml.side.workflow.impl.WorkflowPackageImpl#getTaskNode()
		 * @generated
		 */
		EClass TASK_NODE = eINSTANCE.getTaskNode();

		/**
		 * The meta object literal for the '<em><b>Name</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute TASK_NODE__NAME = eINSTANCE.getTaskNode_Name();

		/**
		 * The meta object literal for the '<em><b>Transition</b></em>' containment reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference TASK_NODE__TRANSITION = eINSTANCE.getTaskNode_Transition();

		/**
		 * The meta object literal for the '<em><b>Event</b></em>' containment reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference TASK_NODE__EVENT = eINSTANCE.getTaskNode_Event();

		/**
		 * The meta object literal for the '<em><b>Timer</b></em>' containment reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference TASK_NODE__TIMER = eINSTANCE.getTaskNode_Timer();

		/**
		 * The meta object literal for the '<em><b>Swimlane</b></em>' reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference TASK_NODE__SWIMLANE = eINSTANCE.getTaskNode_Swimlane();

		/**
		 * The meta object literal for the '{@link com.bluexml.side.workflow.impl.ProcessStateImpl <em>Process State</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see com.bluexml.side.workflow.impl.ProcessStateImpl
		 * @see com.bluexml.side.workflow.impl.WorkflowPackageImpl#getProcessState()
		 * @generated
		 */
		EClass PROCESS_STATE = eINSTANCE.getProcessState();

		/**
		 * The meta object literal for the '<em><b>Subprocess</b></em>' reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference PROCESS_STATE__SUBPROCESS = eINSTANCE.getProcessState_Subprocess();

		/**
		 * The meta object literal for the '<em><b>Variable</b></em>' containment reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference PROCESS_STATE__VARIABLE = eINSTANCE.getProcessState_Variable();

		/**
		 * The meta object literal for the '{@link com.bluexml.side.workflow.impl.ForkImpl <em>Fork</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see com.bluexml.side.workflow.impl.ForkImpl
		 * @see com.bluexml.side.workflow.impl.WorkflowPackageImpl#getFork()
		 * @generated
		 */
		EClass FORK = eINSTANCE.getFork();

		/**
		 * The meta object literal for the '<em><b>Transition</b></em>' containment reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference FORK__TRANSITION = eINSTANCE.getFork_Transition();

		/**
		 * The meta object literal for the '<em><b>Event</b></em>' containment reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference FORK__EVENT = eINSTANCE.getFork_Event();

		/**
		 * The meta object literal for the '{@link com.bluexml.side.workflow.impl.JoinImpl <em>Join</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see com.bluexml.side.workflow.impl.JoinImpl
		 * @see com.bluexml.side.workflow.impl.WorkflowPackageImpl#getJoin()
		 * @generated
		 */
		EClass JOIN = eINSTANCE.getJoin();

		/**
		 * The meta object literal for the '<em><b>Transition</b></em>' containment reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference JOIN__TRANSITION = eINSTANCE.getJoin_Transition();

		/**
		 * The meta object literal for the '<em><b>Event</b></em>' containment reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference JOIN__EVENT = eINSTANCE.getJoin_Event();

		/**
		 * The meta object literal for the '{@link com.bluexml.side.workflow.impl.DecisionImpl <em>Decision</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see com.bluexml.side.workflow.impl.DecisionImpl
		 * @see com.bluexml.side.workflow.impl.WorkflowPackageImpl#getDecision()
		 * @generated
		 */
		EClass DECISION = eINSTANCE.getDecision();

		/**
		 * The meta object literal for the '<em><b>Transition</b></em>' containment reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference DECISION__TRANSITION = eINSTANCE.getDecision_Transition();

		/**
		 * The meta object literal for the '<em><b>Event</b></em>' containment reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference DECISION__EVENT = eINSTANCE.getDecision_Event();

		/**
		 * The meta object literal for the '{@link com.bluexml.side.workflow.impl.EventImpl <em>Event</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see com.bluexml.side.workflow.impl.EventImpl
		 * @see com.bluexml.side.workflow.impl.WorkflowPackageImpl#getEvent()
		 * @generated
		 */
		EClass EVENT = eINSTANCE.getEvent();

		/**
		 * The meta object literal for the '<em><b>Type</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute EVENT__TYPE = eINSTANCE.getEvent_Type();

		/**
		 * The meta object literal for the '<em><b>Action</b></em>' containment reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference EVENT__ACTION = eINSTANCE.getEvent_Action();

		/**
		 * The meta object literal for the '{@link com.bluexml.side.workflow.impl.ActionImpl <em>Action</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see com.bluexml.side.workflow.impl.ActionImpl
		 * @see com.bluexml.side.workflow.impl.WorkflowPackageImpl#getAction()
		 * @generated
		 */
		EClass ACTION = eINSTANCE.getAction();

		/**
		 * The meta object literal for the '<em><b>Java Class</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute ACTION__JAVA_CLASS = eINSTANCE.getAction_JavaClass();

		/**
		 * The meta object literal for the '<em><b>Expression</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute ACTION__EXPRESSION = eINSTANCE.getAction_Expression();

		/**
		 * The meta object literal for the '<em><b>Script</b></em>' containment reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference ACTION__SCRIPT = eINSTANCE.getAction_Script();

		/**
		 * The meta object literal for the '<em><b>Parent Event</b></em>' container reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference ACTION__PARENT_EVENT = eINSTANCE.getAction_ParentEvent();

		/**
		 * The meta object literal for the '<em><b>Parent Timer</b></em>' container reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference ACTION__PARENT_TIMER = eINSTANCE.getAction_ParentTimer();

		/**
		 * The meta object literal for the '{@link com.bluexml.side.workflow.impl.ScriptImpl <em>Script</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see com.bluexml.side.workflow.impl.ScriptImpl
		 * @see com.bluexml.side.workflow.impl.WorkflowPackageImpl#getScript()
		 * @generated
		 */
		EClass SCRIPT = eINSTANCE.getScript();

		/**
		 * The meta object literal for the '<em><b>Name</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute SCRIPT__NAME = eINSTANCE.getScript_Name();

		/**
		 * The meta object literal for the '<em><b>Variable</b></em>' containment reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference SCRIPT__VARIABLE = eINSTANCE.getScript_Variable();

		/**
		 * The meta object literal for the '<em><b>Expression</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute SCRIPT__EXPRESSION = eINSTANCE.getScript_Expression();

		/**
		 * The meta object literal for the '{@link com.bluexml.side.workflow.impl.TimerImpl <em>Timer</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see com.bluexml.side.workflow.impl.TimerImpl
		 * @see com.bluexml.side.workflow.impl.WorkflowPackageImpl#getTimer()
		 * @generated
		 */
		EClass TIMER = eINSTANCE.getTimer();

		/**
		 * The meta object literal for the '<em><b>Name</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute TIMER__NAME = eINSTANCE.getTimer_Name();

		/**
		 * The meta object literal for the '<em><b>Duedate</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute TIMER__DUEDATE = eINSTANCE.getTimer_Duedate();

		/**
		 * The meta object literal for the '<em><b>Action</b></em>' containment reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference TIMER__ACTION = eINSTANCE.getTimer_Action();

		/**
		 * The meta object literal for the '{@link com.bluexml.side.workflow.impl.VariableImpl <em>Variable</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see com.bluexml.side.workflow.impl.VariableImpl
		 * @see com.bluexml.side.workflow.impl.WorkflowPackageImpl#getVariable()
		 * @generated
		 */
		EClass VARIABLE = eINSTANCE.getVariable();

		/**
		 * The meta object literal for the '<em><b>Name</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute VARIABLE__NAME = eINSTANCE.getVariable_Name();

		/**
		 * The meta object literal for the '<em><b>Access</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute VARIABLE__ACCESS = eINSTANCE.getVariable_Access();

		/**
		 * The meta object literal for the '{@link com.bluexml.side.workflow.impl.TransitionImpl <em>Transition</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see com.bluexml.side.workflow.impl.TransitionImpl
		 * @see com.bluexml.side.workflow.impl.WorkflowPackageImpl#getTransition()
		 * @generated
		 */
		EClass TRANSITION = eINSTANCE.getTransition();

		/**
		 * The meta object literal for the '<em><b>Name</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute TRANSITION__NAME = eINSTANCE.getTransition_Name();

		/**
		 * The meta object literal for the '<em><b>Condition</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute TRANSITION__CONDITION = eINSTANCE.getTransition_Condition();

		/**
		 * The meta object literal for the '<em><b>Action</b></em>' containment reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference TRANSITION__ACTION = eINSTANCE.getTransition_Action();

		/**
		 * The meta object literal for the '<em><b>Parent Task Node</b></em>' container reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference TRANSITION__PARENT_TASK_NODE = eINSTANCE.getTransition_ParentTaskNode();

		/**
		 * The meta object literal for the '<em><b>Timer</b></em>' containment reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference TRANSITION__TIMER = eINSTANCE.getTransition_Timer();

		/**
		 * The meta object literal for the '<em><b>To</b></em>' reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference TRANSITION__TO = eINSTANCE.getTransition_To();

		/**
		 * The meta object literal for the '<em><b>Title</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute TRANSITION__TITLE = eINSTANCE.getTransition_Title();

		/**
		 * The meta object literal for the '{@link com.bluexml.side.workflow.impl.StateImpl <em>State</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see com.bluexml.side.workflow.impl.StateImpl
		 * @see com.bluexml.side.workflow.impl.WorkflowPackageImpl#getState()
		 * @generated
		 */
		EClass STATE = eINSTANCE.getState();

		/**
		 * The meta object literal for the '{@link com.bluexml.side.workflow.BPMEventType <em>BPM Event Type</em>}' enum.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see com.bluexml.side.workflow.BPMEventType
		 * @see com.bluexml.side.workflow.impl.WorkflowPackageImpl#getBPMEventType()
		 * @generated
		 */
		EEnum BPM_EVENT_TYPE = eINSTANCE.getBPMEventType();

		/**
		 * The meta object literal for the '{@link com.bluexml.side.workflow.BPMAssignmentType <em>BPM Assignment Type</em>}' enum.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see com.bluexml.side.workflow.BPMAssignmentType
		 * @see com.bluexml.side.workflow.impl.WorkflowPackageImpl#getBPMAssignmentType()
		 * @generated
		 */
		EEnum BPM_ASSIGNMENT_TYPE = eINSTANCE.getBPMAssignmentType();

	}

} //WorkflowPackage
