/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package com.bluexml.side.workflow;


import org.eclipse.emf.ecore.EAttribute;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EEnum;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.EReference;

import com.bluexml.side.common.CommonPackage;

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
	 * The feature id for the '<em><b>Stereotypes</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int WORKFLOW_MODEL_ELEMENT__STEREOTYPES = CommonPackage.MODEL_ELEMENT__STEREOTYPES;

	/**
	 * The feature id for the '<em><b>Tags</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int WORKFLOW_MODEL_ELEMENT__TAGS = CommonPackage.MODEL_ELEMENT__TAGS;

	/**
	 * The feature id for the '<em><b>Comments</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int WORKFLOW_MODEL_ELEMENT__COMMENTS = CommonPackage.MODEL_ELEMENT__COMMENTS;

	/**
	 * The feature id for the '<em><b>Documentation</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int WORKFLOW_MODEL_ELEMENT__DOCUMENTATION = CommonPackage.MODEL_ELEMENT__DOCUMENTATION;

	/**
	 * The feature id for the '<em><b>Description</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int WORKFLOW_MODEL_ELEMENT__DESCRIPTION = CommonPackage.MODEL_ELEMENT__DESCRIPTION;

	/**
	 * The feature id for the '<em><b>Metainfo</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int WORKFLOW_MODEL_ELEMENT__METAINFO = CommonPackage.MODEL_ELEMENT__METAINFO;

	/**
	 * The feature id for the '<em><b>Constraints</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int WORKFLOW_MODEL_ELEMENT__CONSTRAINTS = CommonPackage.MODEL_ELEMENT__CONSTRAINTS;

	/**
	 * The feature id for the '<em><b>Namespace</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int WORKFLOW_MODEL_ELEMENT__NAMESPACE = CommonPackage.MODEL_ELEMENT__NAMESPACE;

	/**
	 * The number of structural features of the '<em>Model Element</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int WORKFLOW_MODEL_ELEMENT_FEATURE_COUNT = CommonPackage.MODEL_ELEMENT_FEATURE_COUNT + 0;

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
	 * The feature id for the '<em><b>Documentation</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PROCESS__DOCUMENTATION = CommonPackage.PACKAGE__DOCUMENTATION;

	/**
	 * The feature id for the '<em><b>Description</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PROCESS__DESCRIPTION = CommonPackage.PACKAGE__DESCRIPTION;

	/**
	 * The feature id for the '<em><b>Metainfo</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PROCESS__METAINFO = CommonPackage.PACKAGE__METAINFO;

	/**
	 * The feature id for the '<em><b>Constraints</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PROCESS__CONSTRAINTS = CommonPackage.PACKAGE__CONSTRAINTS;

	/**
	 * The feature id for the '<em><b>Namespace</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PROCESS__NAMESPACE = CommonPackage.PACKAGE__NAMESPACE;

	/**
	 * The feature id for the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PROCESS__NAME = CommonPackage.PACKAGE__NAME;

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
	 * The feature id for the '<em><b>Namespace Set</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PROCESS__NAMESPACE_SET = CommonPackage.PACKAGE__NAMESPACE_SET;

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
	 * The feature id for the '<em><b>Elements</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PROCESS__ELEMENTS = CommonPackage.PACKAGE_FEATURE_COUNT + 9;

	/**
	 * The feature id for the '<em><b>Content Type</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PROCESS__CONTENT_TYPE = CommonPackage.PACKAGE_FEATURE_COUNT + 10;

	/**
	 * The feature id for the '<em><b>Title</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PROCESS__TITLE = CommonPackage.PACKAGE_FEATURE_COUNT + 11;

	/**
	 * The feature id for the '<em><b>Process Description</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PROCESS__PROCESS_DESCRIPTION = CommonPackage.PACKAGE_FEATURE_COUNT + 12;

	/**
	 * The number of structural features of the '<em>Process</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PROCESS_FEATURE_COUNT = CommonPackage.PACKAGE_FEATURE_COUNT + 13;

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
	 * The feature id for the '<em><b>Stereotypes</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SWIMLANE__STEREOTYPES = WORKFLOW_MODEL_ELEMENT__STEREOTYPES;

	/**
	 * The feature id for the '<em><b>Tags</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SWIMLANE__TAGS = WORKFLOW_MODEL_ELEMENT__TAGS;

	/**
	 * The feature id for the '<em><b>Comments</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SWIMLANE__COMMENTS = WORKFLOW_MODEL_ELEMENT__COMMENTS;

	/**
	 * The feature id for the '<em><b>Documentation</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SWIMLANE__DOCUMENTATION = WORKFLOW_MODEL_ELEMENT__DOCUMENTATION;

	/**
	 * The feature id for the '<em><b>Description</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SWIMLANE__DESCRIPTION = WORKFLOW_MODEL_ELEMENT__DESCRIPTION;

	/**
	 * The feature id for the '<em><b>Metainfo</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SWIMLANE__METAINFO = WORKFLOW_MODEL_ELEMENT__METAINFO;

	/**
	 * The feature id for the '<em><b>Constraints</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SWIMLANE__CONSTRAINTS = WORKFLOW_MODEL_ELEMENT__CONSTRAINTS;

	/**
	 * The feature id for the '<em><b>Namespace</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SWIMLANE__NAMESPACE = WORKFLOW_MODEL_ELEMENT__NAMESPACE;

	/**
	 * The feature id for the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SWIMLANE__NAME = WORKFLOW_MODEL_ELEMENT_FEATURE_COUNT + 0;

	/**
	 * The feature id for the '<em><b>Manage</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SWIMLANE__MANAGE = WORKFLOW_MODEL_ELEMENT_FEATURE_COUNT + 1;

	/**
	 * The feature id for the '<em><b>Actorid</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SWIMLANE__ACTORID = WORKFLOW_MODEL_ELEMENT_FEATURE_COUNT + 2;

	/**
	 * The feature id for the '<em><b>Pooledactors</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SWIMLANE__POOLEDACTORS = WORKFLOW_MODEL_ELEMENT_FEATURE_COUNT + 3;

	/**
	 * The feature id for the '<em><b>Clazz</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SWIMLANE__CLAZZ = WORKFLOW_MODEL_ELEMENT_FEATURE_COUNT + 4;

	/**
	 * The number of structural features of the '<em>Swimlane</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SWIMLANE_FEATURE_COUNT = WORKFLOW_MODEL_ELEMENT_FEATURE_COUNT + 5;

	/**
	 * The meta object id for the '{@link com.bluexml.side.workflow.impl.StateImpl <em>State</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see com.bluexml.side.workflow.impl.StateImpl
	 * @see com.bluexml.side.workflow.impl.WorkflowPackageImpl#getState()
	 * @generated
	 */
	int STATE = 18;

	/**
	 * The feature id for the '<em><b>Stereotypes</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int STATE__STEREOTYPES = WORKFLOW_MODEL_ELEMENT__STEREOTYPES;

	/**
	 * The feature id for the '<em><b>Tags</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int STATE__TAGS = WORKFLOW_MODEL_ELEMENT__TAGS;

	/**
	 * The feature id for the '<em><b>Comments</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int STATE__COMMENTS = WORKFLOW_MODEL_ELEMENT__COMMENTS;

	/**
	 * The feature id for the '<em><b>Documentation</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int STATE__DOCUMENTATION = WORKFLOW_MODEL_ELEMENT__DOCUMENTATION;

	/**
	 * The feature id for the '<em><b>Description</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int STATE__DESCRIPTION = WORKFLOW_MODEL_ELEMENT__DESCRIPTION;

	/**
	 * The feature id for the '<em><b>Metainfo</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int STATE__METAINFO = WORKFLOW_MODEL_ELEMENT__METAINFO;

	/**
	 * The feature id for the '<em><b>Constraints</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int STATE__CONSTRAINTS = WORKFLOW_MODEL_ELEMENT__CONSTRAINTS;

	/**
	 * The feature id for the '<em><b>Namespace</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int STATE__NAMESPACE = WORKFLOW_MODEL_ELEMENT__NAMESPACE;

	/**
	 * The feature id for the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int STATE__NAME = WORKFLOW_MODEL_ELEMENT_FEATURE_COUNT + 0;

	/**
	 * The feature id for the '<em><b>Event</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int STATE__EVENT = WORKFLOW_MODEL_ELEMENT_FEATURE_COUNT + 1;

	/**
	 * The feature id for the '<em><b>Title</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int STATE__TITLE = WORKFLOW_MODEL_ELEMENT_FEATURE_COUNT + 2;

	/**
	 * The feature id for the '<em><b>State Description</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int STATE__STATE_DESCRIPTION = WORKFLOW_MODEL_ELEMENT_FEATURE_COUNT + 3;

	/**
	 * The number of structural features of the '<em>State</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int STATE_FEATURE_COUNT = WORKFLOW_MODEL_ELEMENT_FEATURE_COUNT + 4;

	/**
	 * The meta object id for the '{@link com.bluexml.side.workflow.impl.TransitionTaskImpl <em>Transition Task</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see com.bluexml.side.workflow.impl.TransitionTaskImpl
	 * @see com.bluexml.side.workflow.impl.WorkflowPackageImpl#getTransitionTask()
	 * @generated
	 */
	int TRANSITION_TASK = 20;

	/**
	 * The feature id for the '<em><b>Stereotypes</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TRANSITION_TASK__STEREOTYPES = STATE__STEREOTYPES;

	/**
	 * The feature id for the '<em><b>Tags</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TRANSITION_TASK__TAGS = STATE__TAGS;

	/**
	 * The feature id for the '<em><b>Comments</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TRANSITION_TASK__COMMENTS = STATE__COMMENTS;

	/**
	 * The feature id for the '<em><b>Documentation</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TRANSITION_TASK__DOCUMENTATION = STATE__DOCUMENTATION;

	/**
	 * The feature id for the '<em><b>Description</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TRANSITION_TASK__DESCRIPTION = STATE__DESCRIPTION;

	/**
	 * The feature id for the '<em><b>Metainfo</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TRANSITION_TASK__METAINFO = STATE__METAINFO;

	/**
	 * The feature id for the '<em><b>Constraints</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TRANSITION_TASK__CONSTRAINTS = STATE__CONSTRAINTS;

	/**
	 * The feature id for the '<em><b>Namespace</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TRANSITION_TASK__NAMESPACE = STATE__NAMESPACE;

	/**
	 * The feature id for the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TRANSITION_TASK__NAME = STATE__NAME;

	/**
	 * The feature id for the '<em><b>Event</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TRANSITION_TASK__EVENT = STATE__EVENT;

	/**
	 * The feature id for the '<em><b>Title</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TRANSITION_TASK__TITLE = STATE__TITLE;

	/**
	 * The feature id for the '<em><b>State Description</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TRANSITION_TASK__STATE_DESCRIPTION = STATE__STATE_DESCRIPTION;

	/**
	 * The feature id for the '<em><b>Transition</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TRANSITION_TASK__TRANSITION = STATE_FEATURE_COUNT + 0;

	/**
	 * The number of structural features of the '<em>Transition Task</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TRANSITION_TASK_FEATURE_COUNT = STATE_FEATURE_COUNT + 1;

	/**
	 * The meta object id for the '{@link com.bluexml.side.workflow.impl.UserTaskImpl <em>User Task</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see com.bluexml.side.workflow.impl.UserTaskImpl
	 * @see com.bluexml.side.workflow.impl.WorkflowPackageImpl#getUserTask()
	 * @generated
	 */
	int USER_TASK = 5;

	/**
	 * The feature id for the '<em><b>Stereotypes</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int USER_TASK__STEREOTYPES = TRANSITION_TASK__STEREOTYPES;

	/**
	 * The feature id for the '<em><b>Tags</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int USER_TASK__TAGS = TRANSITION_TASK__TAGS;

	/**
	 * The feature id for the '<em><b>Comments</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int USER_TASK__COMMENTS = TRANSITION_TASK__COMMENTS;

	/**
	 * The feature id for the '<em><b>Documentation</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int USER_TASK__DOCUMENTATION = TRANSITION_TASK__DOCUMENTATION;

	/**
	 * The feature id for the '<em><b>Description</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int USER_TASK__DESCRIPTION = TRANSITION_TASK__DESCRIPTION;

	/**
	 * The feature id for the '<em><b>Metainfo</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int USER_TASK__METAINFO = TRANSITION_TASK__METAINFO;

	/**
	 * The feature id for the '<em><b>Constraints</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int USER_TASK__CONSTRAINTS = TRANSITION_TASK__CONSTRAINTS;

	/**
	 * The feature id for the '<em><b>Namespace</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int USER_TASK__NAMESPACE = TRANSITION_TASK__NAMESPACE;

	/**
	 * The feature id for the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int USER_TASK__NAME = TRANSITION_TASK__NAME;

	/**
	 * The feature id for the '<em><b>Event</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int USER_TASK__EVENT = TRANSITION_TASK__EVENT;

	/**
	 * The feature id for the '<em><b>Title</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int USER_TASK__TITLE = TRANSITION_TASK__TITLE;

	/**
	 * The feature id for the '<em><b>State Description</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int USER_TASK__STATE_DESCRIPTION = TRANSITION_TASK__STATE_DESCRIPTION;

	/**
	 * The feature id for the '<em><b>Transition</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int USER_TASK__TRANSITION = TRANSITION_TASK__TRANSITION;

	/**
	 * The feature id for the '<em><b>Attributes</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int USER_TASK__ATTRIBUTES = TRANSITION_TASK_FEATURE_COUNT + 0;

	/**
	 * The feature id for the '<em><b>Clazz</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int USER_TASK__CLAZZ = TRANSITION_TASK_FEATURE_COUNT + 1;

	/**
	 * The feature id for the '<em><b>Advanced Task Definition</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int USER_TASK__ADVANCED_TASK_DEFINITION = TRANSITION_TASK_FEATURE_COUNT + 2;

	/**
	 * The number of structural features of the '<em>User Task</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int USER_TASK_FEATURE_COUNT = TRANSITION_TASK_FEATURE_COUNT + 3;

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
	 * The feature id for the '<em><b>Stereotypes</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int START_STATE__STEREOTYPES = USER_TASK__STEREOTYPES;

	/**
	 * The feature id for the '<em><b>Tags</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int START_STATE__TAGS = USER_TASK__TAGS;

	/**
	 * The feature id for the '<em><b>Comments</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int START_STATE__COMMENTS = USER_TASK__COMMENTS;

	/**
	 * The feature id for the '<em><b>Documentation</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int START_STATE__DOCUMENTATION = USER_TASK__DOCUMENTATION;

	/**
	 * The feature id for the '<em><b>Description</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int START_STATE__DESCRIPTION = USER_TASK__DESCRIPTION;

	/**
	 * The feature id for the '<em><b>Metainfo</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int START_STATE__METAINFO = USER_TASK__METAINFO;

	/**
	 * The feature id for the '<em><b>Constraints</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int START_STATE__CONSTRAINTS = USER_TASK__CONSTRAINTS;

	/**
	 * The feature id for the '<em><b>Namespace</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int START_STATE__NAMESPACE = USER_TASK__NAMESPACE;

	/**
	 * The feature id for the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int START_STATE__NAME = USER_TASK__NAME;

	/**
	 * The feature id for the '<em><b>Event</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int START_STATE__EVENT = USER_TASK__EVENT;

	/**
	 * The feature id for the '<em><b>Title</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int START_STATE__TITLE = USER_TASK__TITLE;

	/**
	 * The feature id for the '<em><b>State Description</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int START_STATE__STATE_DESCRIPTION = USER_TASK__STATE_DESCRIPTION;

	/**
	 * The feature id for the '<em><b>Transition</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int START_STATE__TRANSITION = USER_TASK__TRANSITION;

	/**
	 * The feature id for the '<em><b>Attributes</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int START_STATE__ATTRIBUTES = USER_TASK__ATTRIBUTES;

	/**
	 * The feature id for the '<em><b>Clazz</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int START_STATE__CLAZZ = USER_TASK__CLAZZ;

	/**
	 * The feature id for the '<em><b>Advanced Task Definition</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int START_STATE__ADVANCED_TASK_DEFINITION = USER_TASK__ADVANCED_TASK_DEFINITION;

	/**
	 * The feature id for the '<em><b>Initiator</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int START_STATE__INITIATOR = USER_TASK_FEATURE_COUNT + 0;

	/**
	 * The number of structural features of the '<em>Start State</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int START_STATE_FEATURE_COUNT = USER_TASK_FEATURE_COUNT + 1;

	/**
	 * The meta object id for the '{@link com.bluexml.side.workflow.impl.EndStateImpl <em>End State</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see com.bluexml.side.workflow.impl.EndStateImpl
	 * @see com.bluexml.side.workflow.impl.WorkflowPackageImpl#getEndState()
	 * @generated
	 */
	int END_STATE = 6;

	/**
	 * The meta object id for the '{@link com.bluexml.side.workflow.impl.NodeImpl <em>Node</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see com.bluexml.side.workflow.impl.NodeImpl
	 * @see com.bluexml.side.workflow.impl.WorkflowPackageImpl#getNode()
	 * @generated
	 */
	int NODE = 7;

	/**
	 * The meta object id for the '{@link com.bluexml.side.workflow.impl.TaskNodeImpl <em>Task Node</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see com.bluexml.side.workflow.impl.TaskNodeImpl
	 * @see com.bluexml.side.workflow.impl.WorkflowPackageImpl#getTaskNode()
	 * @generated
	 */
	int TASK_NODE = 4;

	/**
	 * The feature id for the '<em><b>Stereotypes</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TASK_NODE__STEREOTYPES = USER_TASK__STEREOTYPES;

	/**
	 * The feature id for the '<em><b>Tags</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TASK_NODE__TAGS = USER_TASK__TAGS;

	/**
	 * The feature id for the '<em><b>Comments</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TASK_NODE__COMMENTS = USER_TASK__COMMENTS;

	/**
	 * The feature id for the '<em><b>Documentation</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TASK_NODE__DOCUMENTATION = USER_TASK__DOCUMENTATION;

	/**
	 * The feature id for the '<em><b>Description</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TASK_NODE__DESCRIPTION = USER_TASK__DESCRIPTION;

	/**
	 * The feature id for the '<em><b>Metainfo</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TASK_NODE__METAINFO = USER_TASK__METAINFO;

	/**
	 * The feature id for the '<em><b>Constraints</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TASK_NODE__CONSTRAINTS = USER_TASK__CONSTRAINTS;

	/**
	 * The feature id for the '<em><b>Namespace</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TASK_NODE__NAMESPACE = USER_TASK__NAMESPACE;

	/**
	 * The feature id for the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TASK_NODE__NAME = USER_TASK__NAME;

	/**
	 * The feature id for the '<em><b>Event</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TASK_NODE__EVENT = USER_TASK__EVENT;

	/**
	 * The feature id for the '<em><b>Title</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TASK_NODE__TITLE = USER_TASK__TITLE;

	/**
	 * The feature id for the '<em><b>State Description</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TASK_NODE__STATE_DESCRIPTION = USER_TASK__STATE_DESCRIPTION;

	/**
	 * The feature id for the '<em><b>Transition</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TASK_NODE__TRANSITION = USER_TASK__TRANSITION;

	/**
	 * The feature id for the '<em><b>Attributes</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TASK_NODE__ATTRIBUTES = USER_TASK__ATTRIBUTES;

	/**
	 * The feature id for the '<em><b>Clazz</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TASK_NODE__CLAZZ = USER_TASK__CLAZZ;

	/**
	 * The feature id for the '<em><b>Advanced Task Definition</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TASK_NODE__ADVANCED_TASK_DEFINITION = USER_TASK__ADVANCED_TASK_DEFINITION;

	/**
	 * The feature id for the '<em><b>Timer</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TASK_NODE__TIMER = USER_TASK_FEATURE_COUNT + 0;

	/**
	 * The feature id for the '<em><b>Swimlane</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TASK_NODE__SWIMLANE = USER_TASK_FEATURE_COUNT + 1;

	/**
	 * The number of structural features of the '<em>Task Node</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TASK_NODE_FEATURE_COUNT = USER_TASK_FEATURE_COUNT + 2;

	/**
	 * The feature id for the '<em><b>Stereotypes</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int END_STATE__STEREOTYPES = STATE__STEREOTYPES;

	/**
	 * The feature id for the '<em><b>Tags</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int END_STATE__TAGS = STATE__TAGS;

	/**
	 * The feature id for the '<em><b>Comments</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int END_STATE__COMMENTS = STATE__COMMENTS;

	/**
	 * The feature id for the '<em><b>Documentation</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int END_STATE__DOCUMENTATION = STATE__DOCUMENTATION;

	/**
	 * The feature id for the '<em><b>Description</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int END_STATE__DESCRIPTION = STATE__DESCRIPTION;

	/**
	 * The feature id for the '<em><b>Metainfo</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int END_STATE__METAINFO = STATE__METAINFO;

	/**
	 * The feature id for the '<em><b>Constraints</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int END_STATE__CONSTRAINTS = STATE__CONSTRAINTS;

	/**
	 * The feature id for the '<em><b>Namespace</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int END_STATE__NAMESPACE = STATE__NAMESPACE;

	/**
	 * The feature id for the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int END_STATE__NAME = STATE__NAME;

	/**
	 * The feature id for the '<em><b>Event</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int END_STATE__EVENT = STATE__EVENT;

	/**
	 * The feature id for the '<em><b>Title</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int END_STATE__TITLE = STATE__TITLE;

	/**
	 * The feature id for the '<em><b>State Description</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int END_STATE__STATE_DESCRIPTION = STATE__STATE_DESCRIPTION;

	/**
	 * The number of structural features of the '<em>End State</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int END_STATE_FEATURE_COUNT = STATE_FEATURE_COUNT + 0;

	/**
	 * The feature id for the '<em><b>Stereotypes</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int NODE__STEREOTYPES = TRANSITION_TASK__STEREOTYPES;

	/**
	 * The feature id for the '<em><b>Tags</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int NODE__TAGS = TRANSITION_TASK__TAGS;

	/**
	 * The feature id for the '<em><b>Comments</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int NODE__COMMENTS = TRANSITION_TASK__COMMENTS;

	/**
	 * The feature id for the '<em><b>Documentation</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int NODE__DOCUMENTATION = TRANSITION_TASK__DOCUMENTATION;

	/**
	 * The feature id for the '<em><b>Description</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int NODE__DESCRIPTION = TRANSITION_TASK__DESCRIPTION;

	/**
	 * The feature id for the '<em><b>Metainfo</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int NODE__METAINFO = TRANSITION_TASK__METAINFO;

	/**
	 * The feature id for the '<em><b>Constraints</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int NODE__CONSTRAINTS = TRANSITION_TASK__CONSTRAINTS;

	/**
	 * The feature id for the '<em><b>Namespace</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int NODE__NAMESPACE = TRANSITION_TASK__NAMESPACE;

	/**
	 * The feature id for the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int NODE__NAME = TRANSITION_TASK__NAME;

	/**
	 * The feature id for the '<em><b>Event</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int NODE__EVENT = TRANSITION_TASK__EVENT;

	/**
	 * The feature id for the '<em><b>Title</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int NODE__TITLE = TRANSITION_TASK__TITLE;

	/**
	 * The feature id for the '<em><b>State Description</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int NODE__STATE_DESCRIPTION = TRANSITION_TASK__STATE_DESCRIPTION;

	/**
	 * The feature id for the '<em><b>Transition</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int NODE__TRANSITION = TRANSITION_TASK__TRANSITION;

	/**
	 * The feature id for the '<em><b>Action</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int NODE__ACTION = TRANSITION_TASK_FEATURE_COUNT + 0;

	/**
	 * The number of structural features of the '<em>Node</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int NODE_FEATURE_COUNT = TRANSITION_TASK_FEATURE_COUNT + 1;

	/**
	 * The meta object id for the '{@link com.bluexml.side.workflow.impl.ProcessStateImpl <em>Process State</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see com.bluexml.side.workflow.impl.ProcessStateImpl
	 * @see com.bluexml.side.workflow.impl.WorkflowPackageImpl#getProcessState()
	 * @generated
	 */
	int PROCESS_STATE = 8;

	/**
	 * The feature id for the '<em><b>Stereotypes</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PROCESS_STATE__STEREOTYPES = TRANSITION_TASK__STEREOTYPES;

	/**
	 * The feature id for the '<em><b>Tags</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PROCESS_STATE__TAGS = TRANSITION_TASK__TAGS;

	/**
	 * The feature id for the '<em><b>Comments</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PROCESS_STATE__COMMENTS = TRANSITION_TASK__COMMENTS;

	/**
	 * The feature id for the '<em><b>Documentation</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PROCESS_STATE__DOCUMENTATION = TRANSITION_TASK__DOCUMENTATION;

	/**
	 * The feature id for the '<em><b>Description</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PROCESS_STATE__DESCRIPTION = TRANSITION_TASK__DESCRIPTION;

	/**
	 * The feature id for the '<em><b>Metainfo</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PROCESS_STATE__METAINFO = TRANSITION_TASK__METAINFO;

	/**
	 * The feature id for the '<em><b>Constraints</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PROCESS_STATE__CONSTRAINTS = TRANSITION_TASK__CONSTRAINTS;

	/**
	 * The feature id for the '<em><b>Namespace</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PROCESS_STATE__NAMESPACE = TRANSITION_TASK__NAMESPACE;

	/**
	 * The feature id for the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PROCESS_STATE__NAME = TRANSITION_TASK__NAME;

	/**
	 * The feature id for the '<em><b>Event</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PROCESS_STATE__EVENT = TRANSITION_TASK__EVENT;

	/**
	 * The feature id for the '<em><b>Title</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PROCESS_STATE__TITLE = TRANSITION_TASK__TITLE;

	/**
	 * The feature id for the '<em><b>State Description</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PROCESS_STATE__STATE_DESCRIPTION = TRANSITION_TASK__STATE_DESCRIPTION;

	/**
	 * The feature id for the '<em><b>Transition</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PROCESS_STATE__TRANSITION = TRANSITION_TASK__TRANSITION;

	/**
	 * The feature id for the '<em><b>Subprocess</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PROCESS_STATE__SUBPROCESS = TRANSITION_TASK_FEATURE_COUNT + 0;

	/**
	 * The feature id for the '<em><b>Variable</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PROCESS_STATE__VARIABLE = TRANSITION_TASK_FEATURE_COUNT + 1;

	/**
	 * The number of structural features of the '<em>Process State</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PROCESS_STATE_FEATURE_COUNT = TRANSITION_TASK_FEATURE_COUNT + 2;

	/**
	 * The meta object id for the '{@link com.bluexml.side.workflow.impl.ForkImpl <em>Fork</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see com.bluexml.side.workflow.impl.ForkImpl
	 * @see com.bluexml.side.workflow.impl.WorkflowPackageImpl#getFork()
	 * @generated
	 */
	int FORK = 9;

	/**
	 * The feature id for the '<em><b>Stereotypes</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int FORK__STEREOTYPES = TRANSITION_TASK__STEREOTYPES;

	/**
	 * The feature id for the '<em><b>Tags</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int FORK__TAGS = TRANSITION_TASK__TAGS;

	/**
	 * The feature id for the '<em><b>Comments</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int FORK__COMMENTS = TRANSITION_TASK__COMMENTS;

	/**
	 * The feature id for the '<em><b>Documentation</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int FORK__DOCUMENTATION = TRANSITION_TASK__DOCUMENTATION;

	/**
	 * The feature id for the '<em><b>Description</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int FORK__DESCRIPTION = TRANSITION_TASK__DESCRIPTION;

	/**
	 * The feature id for the '<em><b>Metainfo</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int FORK__METAINFO = TRANSITION_TASK__METAINFO;

	/**
	 * The feature id for the '<em><b>Constraints</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int FORK__CONSTRAINTS = TRANSITION_TASK__CONSTRAINTS;

	/**
	 * The feature id for the '<em><b>Namespace</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int FORK__NAMESPACE = TRANSITION_TASK__NAMESPACE;

	/**
	 * The feature id for the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int FORK__NAME = TRANSITION_TASK__NAME;

	/**
	 * The feature id for the '<em><b>Event</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int FORK__EVENT = TRANSITION_TASK__EVENT;

	/**
	 * The feature id for the '<em><b>Title</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int FORK__TITLE = TRANSITION_TASK__TITLE;

	/**
	 * The feature id for the '<em><b>State Description</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int FORK__STATE_DESCRIPTION = TRANSITION_TASK__STATE_DESCRIPTION;

	/**
	 * The feature id for the '<em><b>Transition</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int FORK__TRANSITION = TRANSITION_TASK__TRANSITION;

	/**
	 * The number of structural features of the '<em>Fork</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int FORK_FEATURE_COUNT = TRANSITION_TASK_FEATURE_COUNT + 0;

	/**
	 * The meta object id for the '{@link com.bluexml.side.workflow.impl.JoinImpl <em>Join</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see com.bluexml.side.workflow.impl.JoinImpl
	 * @see com.bluexml.side.workflow.impl.WorkflowPackageImpl#getJoin()
	 * @generated
	 */
	int JOIN = 10;

	/**
	 * The feature id for the '<em><b>Stereotypes</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int JOIN__STEREOTYPES = TRANSITION_TASK__STEREOTYPES;

	/**
	 * The feature id for the '<em><b>Tags</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int JOIN__TAGS = TRANSITION_TASK__TAGS;

	/**
	 * The feature id for the '<em><b>Comments</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int JOIN__COMMENTS = TRANSITION_TASK__COMMENTS;

	/**
	 * The feature id for the '<em><b>Documentation</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int JOIN__DOCUMENTATION = TRANSITION_TASK__DOCUMENTATION;

	/**
	 * The feature id for the '<em><b>Description</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int JOIN__DESCRIPTION = TRANSITION_TASK__DESCRIPTION;

	/**
	 * The feature id for the '<em><b>Metainfo</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int JOIN__METAINFO = TRANSITION_TASK__METAINFO;

	/**
	 * The feature id for the '<em><b>Constraints</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int JOIN__CONSTRAINTS = TRANSITION_TASK__CONSTRAINTS;

	/**
	 * The feature id for the '<em><b>Namespace</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int JOIN__NAMESPACE = TRANSITION_TASK__NAMESPACE;

	/**
	 * The feature id for the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int JOIN__NAME = TRANSITION_TASK__NAME;

	/**
	 * The feature id for the '<em><b>Event</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int JOIN__EVENT = TRANSITION_TASK__EVENT;

	/**
	 * The feature id for the '<em><b>Title</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int JOIN__TITLE = TRANSITION_TASK__TITLE;

	/**
	 * The feature id for the '<em><b>State Description</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int JOIN__STATE_DESCRIPTION = TRANSITION_TASK__STATE_DESCRIPTION;

	/**
	 * The feature id for the '<em><b>Transition</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int JOIN__TRANSITION = TRANSITION_TASK__TRANSITION;

	/**
	 * The number of structural features of the '<em>Join</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int JOIN_FEATURE_COUNT = TRANSITION_TASK_FEATURE_COUNT + 0;

	/**
	 * The meta object id for the '{@link com.bluexml.side.workflow.impl.DecisionImpl <em>Decision</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see com.bluexml.side.workflow.impl.DecisionImpl
	 * @see com.bluexml.side.workflow.impl.WorkflowPackageImpl#getDecision()
	 * @generated
	 */
	int DECISION = 11;

	/**
	 * The feature id for the '<em><b>Stereotypes</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DECISION__STEREOTYPES = TRANSITION_TASK__STEREOTYPES;

	/**
	 * The feature id for the '<em><b>Tags</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DECISION__TAGS = TRANSITION_TASK__TAGS;

	/**
	 * The feature id for the '<em><b>Comments</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DECISION__COMMENTS = TRANSITION_TASK__COMMENTS;

	/**
	 * The feature id for the '<em><b>Documentation</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DECISION__DOCUMENTATION = TRANSITION_TASK__DOCUMENTATION;

	/**
	 * The feature id for the '<em><b>Description</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DECISION__DESCRIPTION = TRANSITION_TASK__DESCRIPTION;

	/**
	 * The feature id for the '<em><b>Metainfo</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DECISION__METAINFO = TRANSITION_TASK__METAINFO;

	/**
	 * The feature id for the '<em><b>Constraints</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DECISION__CONSTRAINTS = TRANSITION_TASK__CONSTRAINTS;

	/**
	 * The feature id for the '<em><b>Namespace</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DECISION__NAMESPACE = TRANSITION_TASK__NAMESPACE;

	/**
	 * The feature id for the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DECISION__NAME = TRANSITION_TASK__NAME;

	/**
	 * The feature id for the '<em><b>Event</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DECISION__EVENT = TRANSITION_TASK__EVENT;

	/**
	 * The feature id for the '<em><b>Title</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DECISION__TITLE = TRANSITION_TASK__TITLE;

	/**
	 * The feature id for the '<em><b>State Description</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DECISION__STATE_DESCRIPTION = TRANSITION_TASK__STATE_DESCRIPTION;

	/**
	 * The feature id for the '<em><b>Transition</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DECISION__TRANSITION = TRANSITION_TASK__TRANSITION;

	/**
	 * The number of structural features of the '<em>Decision</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DECISION_FEATURE_COUNT = TRANSITION_TASK_FEATURE_COUNT + 0;

	/**
	 * The meta object id for the '{@link com.bluexml.side.workflow.impl.EventImpl <em>Event</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see com.bluexml.side.workflow.impl.EventImpl
	 * @see com.bluexml.side.workflow.impl.WorkflowPackageImpl#getEvent()
	 * @generated
	 */
	int EVENT = 12;

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
	int ACTION = 13;

	/**
	 * The feature id for the '<em><b>Stereotypes</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ACTION__STEREOTYPES = WORKFLOW_MODEL_ELEMENT__STEREOTYPES;

	/**
	 * The feature id for the '<em><b>Tags</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ACTION__TAGS = WORKFLOW_MODEL_ELEMENT__TAGS;

	/**
	 * The feature id for the '<em><b>Comments</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ACTION__COMMENTS = WORKFLOW_MODEL_ELEMENT__COMMENTS;

	/**
	 * The feature id for the '<em><b>Documentation</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ACTION__DOCUMENTATION = WORKFLOW_MODEL_ELEMENT__DOCUMENTATION;

	/**
	 * The feature id for the '<em><b>Description</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ACTION__DESCRIPTION = WORKFLOW_MODEL_ELEMENT__DESCRIPTION;

	/**
	 * The feature id for the '<em><b>Metainfo</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ACTION__METAINFO = WORKFLOW_MODEL_ELEMENT__METAINFO;

	/**
	 * The feature id for the '<em><b>Constraints</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ACTION__CONSTRAINTS = WORKFLOW_MODEL_ELEMENT__CONSTRAINTS;

	/**
	 * The feature id for the '<em><b>Namespace</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ACTION__NAMESPACE = WORKFLOW_MODEL_ELEMENT__NAMESPACE;

	/**
	 * The feature id for the '<em><b>Java Class</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ACTION__JAVA_CLASS = WORKFLOW_MODEL_ELEMENT_FEATURE_COUNT + 0;

	/**
	 * The feature id for the '<em><b>Expression</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ACTION__EXPRESSION = WORKFLOW_MODEL_ELEMENT_FEATURE_COUNT + 1;

	/**
	 * The feature id for the '<em><b>Script</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ACTION__SCRIPT = WORKFLOW_MODEL_ELEMENT_FEATURE_COUNT + 2;

	/**
	 * The number of structural features of the '<em>Action</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ACTION_FEATURE_COUNT = WORKFLOW_MODEL_ELEMENT_FEATURE_COUNT + 3;

	/**
	 * The meta object id for the '{@link com.bluexml.side.workflow.impl.ScriptImpl <em>Script</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see com.bluexml.side.workflow.impl.ScriptImpl
	 * @see com.bluexml.side.workflow.impl.WorkflowPackageImpl#getScript()
	 * @generated
	 */
	int SCRIPT = 14;

	/**
	 * The feature id for the '<em><b>Variable</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SCRIPT__VARIABLE = 0;

	/**
	 * The feature id for the '<em><b>Expression</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SCRIPT__EXPRESSION = 1;

	/**
	 * The number of structural features of the '<em>Script</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SCRIPT_FEATURE_COUNT = 2;

	/**
	 * The meta object id for the '{@link com.bluexml.side.workflow.impl.TimerImpl <em>Timer</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see com.bluexml.side.workflow.impl.TimerImpl
	 * @see com.bluexml.side.workflow.impl.WorkflowPackageImpl#getTimer()
	 * @generated
	 */
	int TIMER = 15;

	/**
	 * The feature id for the '<em><b>Stereotypes</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TIMER__STEREOTYPES = ACTION__STEREOTYPES;

	/**
	 * The feature id for the '<em><b>Tags</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TIMER__TAGS = ACTION__TAGS;

	/**
	 * The feature id for the '<em><b>Comments</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TIMER__COMMENTS = ACTION__COMMENTS;

	/**
	 * The feature id for the '<em><b>Documentation</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TIMER__DOCUMENTATION = ACTION__DOCUMENTATION;

	/**
	 * The feature id for the '<em><b>Description</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TIMER__DESCRIPTION = ACTION__DESCRIPTION;

	/**
	 * The feature id for the '<em><b>Metainfo</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TIMER__METAINFO = ACTION__METAINFO;

	/**
	 * The feature id for the '<em><b>Constraints</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TIMER__CONSTRAINTS = ACTION__CONSTRAINTS;

	/**
	 * The feature id for the '<em><b>Namespace</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TIMER__NAMESPACE = ACTION__NAMESPACE;

	/**
	 * The feature id for the '<em><b>Java Class</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TIMER__JAVA_CLASS = ACTION__JAVA_CLASS;

	/**
	 * The feature id for the '<em><b>Expression</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TIMER__EXPRESSION = ACTION__EXPRESSION;

	/**
	 * The feature id for the '<em><b>Script</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TIMER__SCRIPT = ACTION__SCRIPT;

	/**
	 * The feature id for the '<em><b>Duedate</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TIMER__DUEDATE = ACTION_FEATURE_COUNT + 0;

	/**
	 * The number of structural features of the '<em>Timer</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TIMER_FEATURE_COUNT = ACTION_FEATURE_COUNT + 1;

	/**
	 * The meta object id for the '{@link com.bluexml.side.workflow.impl.VariableImpl <em>Variable</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see com.bluexml.side.workflow.impl.VariableImpl
	 * @see com.bluexml.side.workflow.impl.WorkflowPackageImpl#getVariable()
	 * @generated
	 */
	int VARIABLE = 16;

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
	 * The feature id for the '<em><b>Mapped Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int VARIABLE__MAPPED_NAME = 2;

	/**
	 * The number of structural features of the '<em>Variable</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int VARIABLE_FEATURE_COUNT = 3;

	/**
	 * The meta object id for the '{@link com.bluexml.side.workflow.impl.TransitionImpl <em>Transition</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see com.bluexml.side.workflow.impl.TransitionImpl
	 * @see com.bluexml.side.workflow.impl.WorkflowPackageImpl#getTransition()
	 * @generated
	 */
	int TRANSITION = 17;

	/**
	 * The feature id for the '<em><b>Stereotypes</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TRANSITION__STEREOTYPES = CommonPackage.MODEL_ELEMENT__STEREOTYPES;

	/**
	 * The feature id for the '<em><b>Tags</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TRANSITION__TAGS = CommonPackage.MODEL_ELEMENT__TAGS;

	/**
	 * The feature id for the '<em><b>Comments</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TRANSITION__COMMENTS = CommonPackage.MODEL_ELEMENT__COMMENTS;

	/**
	 * The feature id for the '<em><b>Documentation</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TRANSITION__DOCUMENTATION = CommonPackage.MODEL_ELEMENT__DOCUMENTATION;

	/**
	 * The feature id for the '<em><b>Description</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TRANSITION__DESCRIPTION = CommonPackage.MODEL_ELEMENT__DESCRIPTION;

	/**
	 * The feature id for the '<em><b>Metainfo</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TRANSITION__METAINFO = CommonPackage.MODEL_ELEMENT__METAINFO;

	/**
	 * The feature id for the '<em><b>Constraints</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TRANSITION__CONSTRAINTS = CommonPackage.MODEL_ELEMENT__CONSTRAINTS;

	/**
	 * The feature id for the '<em><b>Namespace</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TRANSITION__NAMESPACE = CommonPackage.MODEL_ELEMENT__NAMESPACE;

	/**
	 * The feature id for the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TRANSITION__NAME = CommonPackage.MODEL_ELEMENT_FEATURE_COUNT + 0;

	/**
	 * The feature id for the '<em><b>Condition</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TRANSITION__CONDITION = CommonPackage.MODEL_ELEMENT_FEATURE_COUNT + 1;

	/**
	 * The feature id for the '<em><b>Action</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TRANSITION__ACTION = CommonPackage.MODEL_ELEMENT_FEATURE_COUNT + 2;

	/**
	 * The feature id for the '<em><b>Parent Task Node</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TRANSITION__PARENT_TASK_NODE = CommonPackage.MODEL_ELEMENT_FEATURE_COUNT + 3;

	/**
	 * The feature id for the '<em><b>Timer</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TRANSITION__TIMER = CommonPackage.MODEL_ELEMENT_FEATURE_COUNT + 4;

	/**
	 * The feature id for the '<em><b>To</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TRANSITION__TO = CommonPackage.MODEL_ELEMENT_FEATURE_COUNT + 5;

	/**
	 * The feature id for the '<em><b>Title</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TRANSITION__TITLE = CommonPackage.MODEL_ELEMENT_FEATURE_COUNT + 6;

	/**
	 * The number of structural features of the '<em>Transition</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TRANSITION_FEATURE_COUNT = CommonPackage.MODEL_ELEMENT_FEATURE_COUNT + 7;

	/**
	 * The meta object id for the '{@link com.bluexml.side.workflow.impl.AttributeImpl <em>Attribute</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see com.bluexml.side.workflow.impl.AttributeImpl
	 * @see com.bluexml.side.workflow.impl.WorkflowPackageImpl#getAttribute()
	 * @generated
	 */
	int ATTRIBUTE = 19;

	/**
	 * The feature id for the '<em><b>Stereotypes</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ATTRIBUTE__STEREOTYPES = CommonPackage.MODEL_ELEMENT__STEREOTYPES;

	/**
	 * The feature id for the '<em><b>Tags</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ATTRIBUTE__TAGS = CommonPackage.MODEL_ELEMENT__TAGS;

	/**
	 * The feature id for the '<em><b>Comments</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ATTRIBUTE__COMMENTS = CommonPackage.MODEL_ELEMENT__COMMENTS;

	/**
	 * The feature id for the '<em><b>Documentation</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ATTRIBUTE__DOCUMENTATION = CommonPackage.MODEL_ELEMENT__DOCUMENTATION;

	/**
	 * The feature id for the '<em><b>Description</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ATTRIBUTE__DESCRIPTION = CommonPackage.MODEL_ELEMENT__DESCRIPTION;

	/**
	 * The feature id for the '<em><b>Metainfo</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ATTRIBUTE__METAINFO = CommonPackage.MODEL_ELEMENT__METAINFO;

	/**
	 * The feature id for the '<em><b>Constraints</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ATTRIBUTE__CONSTRAINTS = CommonPackage.MODEL_ELEMENT__CONSTRAINTS;

	/**
	 * The feature id for the '<em><b>Namespace</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ATTRIBUTE__NAMESPACE = CommonPackage.MODEL_ELEMENT__NAMESPACE;

	/**
	 * The feature id for the '<em><b>Typ</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ATTRIBUTE__TYP = CommonPackage.MODEL_ELEMENT_FEATURE_COUNT + 0;

	/**
	 * The feature id for the '<em><b>Title</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ATTRIBUTE__TITLE = CommonPackage.MODEL_ELEMENT_FEATURE_COUNT + 1;

	/**
	 * The feature id for the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ATTRIBUTE__NAME = CommonPackage.MODEL_ELEMENT_FEATURE_COUNT + 2;

	/**
	 * The feature id for the '<em><b>Allowed Values</b></em>' attribute list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ATTRIBUTE__ALLOWED_VALUES = CommonPackage.MODEL_ELEMENT_FEATURE_COUNT + 3;

	/**
	 * The number of structural features of the '<em>Attribute</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ATTRIBUTE_FEATURE_COUNT = CommonPackage.MODEL_ELEMENT_FEATURE_COUNT + 4;

	/**
	 * The meta object id for the '{@link com.bluexml.side.workflow.impl.WfPackageImpl <em>Wf Package</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see com.bluexml.side.workflow.impl.WfPackageImpl
	 * @see com.bluexml.side.workflow.impl.WorkflowPackageImpl#getWfPackage()
	 * @generated
	 */
	int WF_PACKAGE = 21;

	/**
	 * The feature id for the '<em><b>Stereotypes</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int WF_PACKAGE__STEREOTYPES = CommonPackage.PACKAGE__STEREOTYPES;

	/**
	 * The feature id for the '<em><b>Tags</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int WF_PACKAGE__TAGS = CommonPackage.PACKAGE__TAGS;

	/**
	 * The feature id for the '<em><b>Comments</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int WF_PACKAGE__COMMENTS = CommonPackage.PACKAGE__COMMENTS;

	/**
	 * The feature id for the '<em><b>Documentation</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int WF_PACKAGE__DOCUMENTATION = CommonPackage.PACKAGE__DOCUMENTATION;

	/**
	 * The feature id for the '<em><b>Description</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int WF_PACKAGE__DESCRIPTION = CommonPackage.PACKAGE__DESCRIPTION;

	/**
	 * The feature id for the '<em><b>Metainfo</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int WF_PACKAGE__METAINFO = CommonPackage.PACKAGE__METAINFO;

	/**
	 * The feature id for the '<em><b>Constraints</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int WF_PACKAGE__CONSTRAINTS = CommonPackage.PACKAGE__CONSTRAINTS;

	/**
	 * The feature id for the '<em><b>Namespace</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int WF_PACKAGE__NAMESPACE = CommonPackage.PACKAGE__NAMESPACE;

	/**
	 * The feature id for the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int WF_PACKAGE__NAME = CommonPackage.PACKAGE__NAME;

	/**
	 * The feature id for the '<em><b>Stereotype Set</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int WF_PACKAGE__STEREOTYPE_SET = CommonPackage.PACKAGE__STEREOTYPE_SET;

	/**
	 * The feature id for the '<em><b>Package Set</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int WF_PACKAGE__PACKAGE_SET = CommonPackage.PACKAGE__PACKAGE_SET;

	/**
	 * The feature id for the '<em><b>Namespace Set</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int WF_PACKAGE__NAMESPACE_SET = CommonPackage.PACKAGE__NAMESPACE_SET;

	/**
	 * The number of structural features of the '<em>Wf Package</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int WF_PACKAGE_FEATURE_COUNT = CommonPackage.PACKAGE_FEATURE_COUNT + 0;

	/**
	 * The meta object id for the '{@link com.bluexml.side.workflow.BPMEventType <em>BPM Event Type</em>}' enum.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see com.bluexml.side.workflow.BPMEventType
	 * @see com.bluexml.side.workflow.impl.WorkflowPackageImpl#getBPMEventType()
	 * @generated
	 */
	int BPM_EVENT_TYPE = 22;

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
	 * Returns the meta object for the containment reference list '{@link com.bluexml.side.workflow.Process#getElements <em>Elements</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference list '<em>Elements</em>'.
	 * @see com.bluexml.side.workflow.Process#getElements()
	 * @see #getProcess()
	 * @generated
	 */
	EReference getProcess_Elements();

	/**
	 * Returns the meta object for the reference '{@link com.bluexml.side.workflow.Process#getContentType <em>Content Type</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference '<em>Content Type</em>'.
	 * @see com.bluexml.side.workflow.Process#getContentType()
	 * @see #getProcess()
	 * @generated
	 */
	EReference getProcess_ContentType();

	/**
	 * Returns the meta object for the attribute '{@link com.bluexml.side.workflow.Process#getTitle <em>Title</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Title</em>'.
	 * @see com.bluexml.side.workflow.Process#getTitle()
	 * @see #getProcess()
	 * @generated
	 */
	EAttribute getProcess_Title();

	/**
	 * Returns the meta object for the attribute '{@link com.bluexml.side.workflow.Process#getProcessDescription <em>Process Description</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Process Description</em>'.
	 * @see com.bluexml.side.workflow.Process#getProcessDescription()
	 * @see #getProcess()
	 * @generated
	 */
	EAttribute getProcess_ProcessDescription();

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
	 * Returns the meta object for class '{@link com.bluexml.side.workflow.TaskNode <em>Task Node</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Task Node</em>'.
	 * @see com.bluexml.side.workflow.TaskNode
	 * @generated
	 */
	EClass getTaskNode();

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
	 * Returns the meta object for class '{@link com.bluexml.side.workflow.UserTask <em>User Task</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>User Task</em>'.
	 * @see com.bluexml.side.workflow.UserTask
	 * @generated
	 */
	EClass getUserTask();

	/**
	 * Returns the meta object for the containment reference list '{@link com.bluexml.side.workflow.UserTask#getAttributes <em>Attributes</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference list '<em>Attributes</em>'.
	 * @see com.bluexml.side.workflow.UserTask#getAttributes()
	 * @see #getUserTask()
	 * @generated
	 */
	EReference getUserTask_Attributes();

	/**
	 * Returns the meta object for the reference list '{@link com.bluexml.side.workflow.UserTask#getClazz <em>Clazz</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference list '<em>Clazz</em>'.
	 * @see com.bluexml.side.workflow.UserTask#getClazz()
	 * @see #getUserTask()
	 * @generated
	 */
	EReference getUserTask_Clazz();

	/**
	 * Returns the meta object for the reference '{@link com.bluexml.side.workflow.UserTask#getAdvancedTaskDefinition <em>Advanced Task Definition</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference '<em>Advanced Task Definition</em>'.
	 * @see com.bluexml.side.workflow.UserTask#getAdvancedTaskDefinition()
	 * @see #getUserTask()
	 * @generated
	 */
	EReference getUserTask_AdvancedTaskDefinition();

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
	 * Returns the meta object for class '{@link com.bluexml.side.workflow.Join <em>Join</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Join</em>'.
	 * @see com.bluexml.side.workflow.Join
	 * @generated
	 */
	EClass getJoin();

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
	 * Returns the meta object for class '{@link com.bluexml.side.workflow.Script <em>Script</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Script</em>'.
	 * @see com.bluexml.side.workflow.Script
	 * @generated
	 */
	EClass getScript();

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
	 * Returns the meta object for the attribute '{@link com.bluexml.side.workflow.Variable#getMappedName <em>Mapped Name</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Mapped Name</em>'.
	 * @see com.bluexml.side.workflow.Variable#getMappedName()
	 * @see #getVariable()
	 * @generated
	 */
	EAttribute getVariable_MappedName();

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
	 * Returns the meta object for the reference '{@link com.bluexml.side.workflow.Transition#getParentTaskNode <em>Parent Task Node</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference '<em>Parent Task Node</em>'.
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
	 * Returns the meta object for the attribute '{@link com.bluexml.side.workflow.State#getName <em>Name</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Name</em>'.
	 * @see com.bluexml.side.workflow.State#getName()
	 * @see #getState()
	 * @generated
	 */
	EAttribute getState_Name();

	/**
	 * Returns the meta object for the containment reference list '{@link com.bluexml.side.workflow.State#getEvent <em>Event</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference list '<em>Event</em>'.
	 * @see com.bluexml.side.workflow.State#getEvent()
	 * @see #getState()
	 * @generated
	 */
	EReference getState_Event();

	/**
	 * Returns the meta object for the attribute '{@link com.bluexml.side.workflow.State#getTitle <em>Title</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Title</em>'.
	 * @see com.bluexml.side.workflow.State#getTitle()
	 * @see #getState()
	 * @generated
	 */
	EAttribute getState_Title();

	/**
	 * Returns the meta object for the attribute '{@link com.bluexml.side.workflow.State#getStateDescription <em>State Description</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>State Description</em>'.
	 * @see com.bluexml.side.workflow.State#getStateDescription()
	 * @see #getState()
	 * @generated
	 */
	EAttribute getState_StateDescription();

	/**
	 * Returns the meta object for class '{@link com.bluexml.side.workflow.Attribute <em>Attribute</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Attribute</em>'.
	 * @see com.bluexml.side.workflow.Attribute
	 * @generated
	 */
	EClass getAttribute();

	/**
	 * Returns the meta object for the attribute '{@link com.bluexml.side.workflow.Attribute#getTyp <em>Typ</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Typ</em>'.
	 * @see com.bluexml.side.workflow.Attribute#getTyp()
	 * @see #getAttribute()
	 * @generated
	 */
	EAttribute getAttribute_Typ();

	/**
	 * Returns the meta object for the attribute '{@link com.bluexml.side.workflow.Attribute#getTitle <em>Title</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Title</em>'.
	 * @see com.bluexml.side.workflow.Attribute#getTitle()
	 * @see #getAttribute()
	 * @generated
	 */
	EAttribute getAttribute_Title();

	/**
	 * Returns the meta object for the attribute '{@link com.bluexml.side.workflow.Attribute#getName <em>Name</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Name</em>'.
	 * @see com.bluexml.side.workflow.Attribute#getName()
	 * @see #getAttribute()
	 * @generated
	 */
	EAttribute getAttribute_Name();

	/**
	 * Returns the meta object for the attribute list '{@link com.bluexml.side.workflow.Attribute#getAllowedValues <em>Allowed Values</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute list '<em>Allowed Values</em>'.
	 * @see com.bluexml.side.workflow.Attribute#getAllowedValues()
	 * @see #getAttribute()
	 * @generated
	 */
	EAttribute getAttribute_AllowedValues();

	/**
	 * Returns the meta object for class '{@link com.bluexml.side.workflow.TransitionTask <em>Transition Task</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Transition Task</em>'.
	 * @see com.bluexml.side.workflow.TransitionTask
	 * @generated
	 */
	EClass getTransitionTask();

	/**
	 * Returns the meta object for the containment reference list '{@link com.bluexml.side.workflow.TransitionTask#getTransition <em>Transition</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference list '<em>Transition</em>'.
	 * @see com.bluexml.side.workflow.TransitionTask#getTransition()
	 * @see #getTransitionTask()
	 * @generated
	 */
	EReference getTransitionTask_Transition();

	/**
	 * Returns the meta object for class '{@link com.bluexml.side.workflow.WfPackage <em>Wf Package</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Wf Package</em>'.
	 * @see com.bluexml.side.workflow.WfPackage
	 * @generated
	 */
	EClass getWfPackage();

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
		 * The meta object literal for the '<em><b>Elements</b></em>' containment reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference PROCESS__ELEMENTS = eINSTANCE.getProcess_Elements();

		/**
		 * The meta object literal for the '<em><b>Content Type</b></em>' reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference PROCESS__CONTENT_TYPE = eINSTANCE.getProcess_ContentType();

		/**
		 * The meta object literal for the '<em><b>Title</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute PROCESS__TITLE = eINSTANCE.getProcess_Title();

		/**
		 * The meta object literal for the '<em><b>Process Description</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute PROCESS__PROCESS_DESCRIPTION = eINSTANCE.getProcess_ProcessDescription();

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
		 * The meta object literal for the '{@link com.bluexml.side.workflow.impl.TaskNodeImpl <em>Task Node</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see com.bluexml.side.workflow.impl.TaskNodeImpl
		 * @see com.bluexml.side.workflow.impl.WorkflowPackageImpl#getTaskNode()
		 * @generated
		 */
		EClass TASK_NODE = eINSTANCE.getTaskNode();

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
		 * The meta object literal for the '{@link com.bluexml.side.workflow.impl.UserTaskImpl <em>User Task</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see com.bluexml.side.workflow.impl.UserTaskImpl
		 * @see com.bluexml.side.workflow.impl.WorkflowPackageImpl#getUserTask()
		 * @generated
		 */
		EClass USER_TASK = eINSTANCE.getUserTask();

		/**
		 * The meta object literal for the '<em><b>Attributes</b></em>' containment reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference USER_TASK__ATTRIBUTES = eINSTANCE.getUserTask_Attributes();

		/**
		 * The meta object literal for the '<em><b>Clazz</b></em>' reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference USER_TASK__CLAZZ = eINSTANCE.getUserTask_Clazz();

		/**
		 * The meta object literal for the '<em><b>Advanced Task Definition</b></em>' reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference USER_TASK__ADVANCED_TASK_DEFINITION = eINSTANCE.getUserTask_AdvancedTaskDefinition();

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
		 * The meta object literal for the '{@link com.bluexml.side.workflow.impl.JoinImpl <em>Join</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see com.bluexml.side.workflow.impl.JoinImpl
		 * @see com.bluexml.side.workflow.impl.WorkflowPackageImpl#getJoin()
		 * @generated
		 */
		EClass JOIN = eINSTANCE.getJoin();

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
		 * The meta object literal for the '{@link com.bluexml.side.workflow.impl.ScriptImpl <em>Script</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see com.bluexml.side.workflow.impl.ScriptImpl
		 * @see com.bluexml.side.workflow.impl.WorkflowPackageImpl#getScript()
		 * @generated
		 */
		EClass SCRIPT = eINSTANCE.getScript();

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
		 * The meta object literal for the '<em><b>Duedate</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute TIMER__DUEDATE = eINSTANCE.getTimer_Duedate();

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
		 * The meta object literal for the '<em><b>Mapped Name</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute VARIABLE__MAPPED_NAME = eINSTANCE.getVariable_MappedName();

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
		 * The meta object literal for the '<em><b>Parent Task Node</b></em>' reference feature.
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
		 * The meta object literal for the '<em><b>Name</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute STATE__NAME = eINSTANCE.getState_Name();

		/**
		 * The meta object literal for the '<em><b>Event</b></em>' containment reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference STATE__EVENT = eINSTANCE.getState_Event();

		/**
		 * The meta object literal for the '<em><b>Title</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute STATE__TITLE = eINSTANCE.getState_Title();

		/**
		 * The meta object literal for the '<em><b>State Description</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute STATE__STATE_DESCRIPTION = eINSTANCE.getState_StateDescription();

		/**
		 * The meta object literal for the '{@link com.bluexml.side.workflow.impl.AttributeImpl <em>Attribute</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see com.bluexml.side.workflow.impl.AttributeImpl
		 * @see com.bluexml.side.workflow.impl.WorkflowPackageImpl#getAttribute()
		 * @generated
		 */
		EClass ATTRIBUTE = eINSTANCE.getAttribute();

		/**
		 * The meta object literal for the '<em><b>Typ</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute ATTRIBUTE__TYP = eINSTANCE.getAttribute_Typ();

		/**
		 * The meta object literal for the '<em><b>Title</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute ATTRIBUTE__TITLE = eINSTANCE.getAttribute_Title();

		/**
		 * The meta object literal for the '<em><b>Name</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute ATTRIBUTE__NAME = eINSTANCE.getAttribute_Name();

		/**
		 * The meta object literal for the '<em><b>Allowed Values</b></em>' attribute list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute ATTRIBUTE__ALLOWED_VALUES = eINSTANCE.getAttribute_AllowedValues();

		/**
		 * The meta object literal for the '{@link com.bluexml.side.workflow.impl.TransitionTaskImpl <em>Transition Task</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see com.bluexml.side.workflow.impl.TransitionTaskImpl
		 * @see com.bluexml.side.workflow.impl.WorkflowPackageImpl#getTransitionTask()
		 * @generated
		 */
		EClass TRANSITION_TASK = eINSTANCE.getTransitionTask();

		/**
		 * The meta object literal for the '<em><b>Transition</b></em>' containment reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference TRANSITION_TASK__TRANSITION = eINSTANCE.getTransitionTask_Transition();

		/**
		 * The meta object literal for the '{@link com.bluexml.side.workflow.impl.WfPackageImpl <em>Wf Package</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see com.bluexml.side.workflow.impl.WfPackageImpl
		 * @see com.bluexml.side.workflow.impl.WorkflowPackageImpl#getWfPackage()
		 * @generated
		 */
		EClass WF_PACKAGE = eINSTANCE.getWfPackage();

		/**
		 * The meta object literal for the '{@link com.bluexml.side.workflow.BPMEventType <em>BPM Event Type</em>}' enum.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see com.bluexml.side.workflow.BPMEventType
		 * @see com.bluexml.side.workflow.impl.WorkflowPackageImpl#getBPMEventType()
		 * @generated
		 */
		EEnum BPM_EVENT_TYPE = eINSTANCE.getBPMEventType();

	}

} //WorkflowPackage
