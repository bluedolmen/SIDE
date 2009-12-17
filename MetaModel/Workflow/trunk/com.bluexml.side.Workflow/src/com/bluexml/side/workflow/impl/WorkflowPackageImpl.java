/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package com.bluexml.side.workflow.impl;

import org.eclipse.emf.ecore.EAttribute;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EEnum;
import org.eclipse.emf.ecore.EOperation;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.EReference;
import org.eclipse.emf.ecore.EValidator;
import org.eclipse.emf.ecore.impl.EPackageImpl;

import com.bluexml.side.clazz.ClazzPackage;
import com.bluexml.side.common.CommonPackage;
import com.bluexml.side.workflow.Action;
import com.bluexml.side.workflow.Attribute;
import com.bluexml.side.workflow.BPMEventType;
import com.bluexml.side.workflow.Decision;
import com.bluexml.side.workflow.EndState;
import com.bluexml.side.workflow.Event;
import com.bluexml.side.workflow.Fork;
import com.bluexml.side.workflow.Join;
import com.bluexml.side.workflow.Node;
import com.bluexml.side.workflow.ProcessState;
import com.bluexml.side.workflow.Script;
import com.bluexml.side.workflow.StartState;
import com.bluexml.side.workflow.State;
import com.bluexml.side.workflow.Swimlane;
import com.bluexml.side.workflow.TaskNode;
import com.bluexml.side.workflow.Timer;
import com.bluexml.side.workflow.Transition;
import com.bluexml.side.workflow.TransitionTask;
import com.bluexml.side.workflow.UserTask;
import com.bluexml.side.workflow.Variable;
import com.bluexml.side.workflow.WfPackage;
import com.bluexml.side.workflow.WorkflowFactory;
import com.bluexml.side.workflow.WorkflowModelElement;
import com.bluexml.side.workflow.WorkflowPackage;
import com.bluexml.side.workflow.util.WorkflowValidator;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model <b>Package</b>.
 * <!-- end-user-doc -->
 * @generated
 */
public class WorkflowPackageImpl extends EPackageImpl implements WorkflowPackage {
	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass workflowModelElementEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass processEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass swimlaneEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass startStateEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass endStateEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass nodeEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass taskNodeEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass userTaskEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass processStateEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass forkEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass joinEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass decisionEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass eventEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass actionEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass scriptEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass timerEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass variableEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass transitionEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass stateEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass attributeEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass transitionTaskEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass wfPackageEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EEnum bpmEventTypeEEnum = null;

	/**
	 * Creates an instance of the model <b>Package</b>, registered with
	 * {@link org.eclipse.emf.ecore.EPackage.Registry EPackage.Registry} by the package
	 * package URI value.
	 * <p>Note: the correct way to create the package is via the static
	 * factory method {@link #init init()}, which also performs
	 * initialization of the package, or returns the registered package,
	 * if one already exists.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.eclipse.emf.ecore.EPackage.Registry
	 * @see com.bluexml.side.workflow.WorkflowPackage#eNS_URI
	 * @see #init()
	 * @generated
	 */
	private WorkflowPackageImpl() {
		super(eNS_URI, WorkflowFactory.eINSTANCE);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private static boolean isInited = false;

	/**
	 * Creates, registers, and initializes the <b>Package</b> for this model, and for any others upon which it depends.
	 * 
	 * <p>This method is used to initialize {@link WorkflowPackage#eINSTANCE} when that field is accessed.
	 * Clients should not invoke it directly. Instead, they should simply access that field to obtain the package.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #eNS_URI
	 * @see #createPackageContents()
	 * @see #initializePackageContents()
	 * @generated
	 */
	public static WorkflowPackage init() {
		if (isInited) return (WorkflowPackage)EPackage.Registry.INSTANCE.getEPackage(WorkflowPackage.eNS_URI);

		// Obtain or create and register package
		WorkflowPackageImpl theWorkflowPackage = (WorkflowPackageImpl)(EPackage.Registry.INSTANCE.get(eNS_URI) instanceof WorkflowPackageImpl ? EPackage.Registry.INSTANCE.get(eNS_URI) : new WorkflowPackageImpl());

		isInited = true;

		// Initialize simple dependencies
		ClazzPackage.eINSTANCE.eClass();

		// Create package meta-data objects
		theWorkflowPackage.createPackageContents();

		// Initialize created meta-data
		theWorkflowPackage.initializePackageContents();

		// Register package validator
		EValidator.Registry.INSTANCE.put
			(theWorkflowPackage, 
			 new EValidator.Descriptor() {
				 public EValidator getEValidator() {
					 return WorkflowValidator.INSTANCE;
				 }
			 });

		// Mark meta-data to indicate it can't be changed
		theWorkflowPackage.freeze();

  
		// Update the registry and return the package
		EPackage.Registry.INSTANCE.put(WorkflowPackage.eNS_URI, theWorkflowPackage);
		return theWorkflowPackage;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getWorkflowModelElement() {
		return workflowModelElementEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getProcess() {
		return processEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getProcess_Swimlane() {
		return (EReference)processEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getProcess_Startstate() {
		return (EReference)processEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getProcess_Endstate() {
		return (EReference)processEClass.getEStructuralFeatures().get(2);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getProcess_Node() {
		return (EReference)processEClass.getEStructuralFeatures().get(3);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getProcess_Tasknode() {
		return (EReference)processEClass.getEStructuralFeatures().get(4);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getProcess_Processstate() {
		return (EReference)processEClass.getEStructuralFeatures().get(5);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getProcess_Fork() {
		return (EReference)processEClass.getEStructuralFeatures().get(6);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getProcess_Join() {
		return (EReference)processEClass.getEStructuralFeatures().get(7);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getProcess_Decision() {
		return (EReference)processEClass.getEStructuralFeatures().get(8);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getProcess_Elements() {
		return (EReference)processEClass.getEStructuralFeatures().get(9);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getProcess_ContentType() {
		return (EReference)processEClass.getEStructuralFeatures().get(10);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getProcess_Title() {
		return (EAttribute)processEClass.getEStructuralFeatures().get(11);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getProcess_ProcessDescription() {
		return (EAttribute)processEClass.getEStructuralFeatures().get(12);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getSwimlane() {
		return swimlaneEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getSwimlane_Name() {
		return (EAttribute)swimlaneEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getSwimlane_Manage() {
		return (EReference)swimlaneEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getSwimlane_Actorid() {
		return (EAttribute)swimlaneEClass.getEStructuralFeatures().get(2);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getSwimlane_Pooledactors() {
		return (EAttribute)swimlaneEClass.getEStructuralFeatures().get(3);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getSwimlane_Clazz() {
		return (EAttribute)swimlaneEClass.getEStructuralFeatures().get(4);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getStartState() {
		return startStateEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getStartState_Initiator() {
		return (EReference)startStateEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getEndState() {
		return endStateEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getNode() {
		return nodeEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getNode_Action() {
		return (EReference)nodeEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getTaskNode() {
		return taskNodeEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getTaskNode_Timer() {
		return (EReference)taskNodeEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getTaskNode_Swimlane() {
		return (EReference)taskNodeEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getUserTask() {
		return userTaskEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getUserTask_Attributes() {
		return (EReference)userTaskEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getUserTask_Clazz() {
		return (EReference)userTaskEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getProcessState() {
		return processStateEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getProcessState_Subprocess() {
		return (EReference)processStateEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getProcessState_Variable() {
		return (EReference)processStateEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getFork() {
		return forkEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getJoin() {
		return joinEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getDecision() {
		return decisionEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getEvent() {
		return eventEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getEvent_Type() {
		return (EAttribute)eventEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getEvent_Action() {
		return (EReference)eventEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getAction() {
		return actionEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getAction_JavaClass() {
		return (EAttribute)actionEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getAction_Expression() {
		return (EAttribute)actionEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getAction_Script() {
		return (EReference)actionEClass.getEStructuralFeatures().get(2);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getScript() {
		return scriptEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getScript_Variable() {
		return (EReference)scriptEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getScript_Expression() {
		return (EAttribute)scriptEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getTimer() {
		return timerEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getTimer_Duedate() {
		return (EAttribute)timerEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getVariable() {
		return variableEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getVariable_Name() {
		return (EAttribute)variableEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getVariable_Access() {
		return (EAttribute)variableEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getVariable_MappedName() {
		return (EAttribute)variableEClass.getEStructuralFeatures().get(2);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getTransition() {
		return transitionEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getTransition_Name() {
		return (EAttribute)transitionEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getTransition_Condition() {
		return (EAttribute)transitionEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getTransition_Action() {
		return (EReference)transitionEClass.getEStructuralFeatures().get(2);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getTransition_ParentTaskNode() {
		return (EReference)transitionEClass.getEStructuralFeatures().get(3);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getTransition_Timer() {
		return (EReference)transitionEClass.getEStructuralFeatures().get(4);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getTransition_To() {
		return (EReference)transitionEClass.getEStructuralFeatures().get(5);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getTransition_Title() {
		return (EAttribute)transitionEClass.getEStructuralFeatures().get(6);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getState() {
		return stateEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getState_Name() {
		return (EAttribute)stateEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getState_Event() {
		return (EReference)stateEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getState_Title() {
		return (EAttribute)stateEClass.getEStructuralFeatures().get(2);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getState_StateDescription() {
		return (EAttribute)stateEClass.getEStructuralFeatures().get(3);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getAttribute() {
		return attributeEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getAttribute_Typ() {
		return (EAttribute)attributeEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getAttribute_Title() {
		return (EAttribute)attributeEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getAttribute_Name() {
		return (EAttribute)attributeEClass.getEStructuralFeatures().get(2);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getAttribute_AllowedValues() {
		return (EAttribute)attributeEClass.getEStructuralFeatures().get(3);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getTransitionTask() {
		return transitionTaskEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getTransitionTask_Transition() {
		return (EReference)transitionTaskEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getWfPackage() {
		return wfPackageEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EEnum getBPMEventType() {
		return bpmEventTypeEEnum;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public WorkflowFactory getWorkflowFactory() {
		return (WorkflowFactory)getEFactoryInstance();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private boolean isCreated = false;

	/**
	 * Creates the meta-model objects for the package.  This method is
	 * guarded to have no affect on any invocation but its first.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void createPackageContents() {
		if (isCreated) return;
		isCreated = true;

		// Create classes and their features
		workflowModelElementEClass = createEClass(WORKFLOW_MODEL_ELEMENT);

		processEClass = createEClass(PROCESS);
		createEReference(processEClass, PROCESS__SWIMLANE);
		createEReference(processEClass, PROCESS__STARTSTATE);
		createEReference(processEClass, PROCESS__ENDSTATE);
		createEReference(processEClass, PROCESS__NODE);
		createEReference(processEClass, PROCESS__TASKNODE);
		createEReference(processEClass, PROCESS__PROCESSSTATE);
		createEReference(processEClass, PROCESS__FORK);
		createEReference(processEClass, PROCESS__JOIN);
		createEReference(processEClass, PROCESS__DECISION);
		createEReference(processEClass, PROCESS__ELEMENTS);
		createEReference(processEClass, PROCESS__CONTENT_TYPE);
		createEAttribute(processEClass, PROCESS__TITLE);
		createEAttribute(processEClass, PROCESS__PROCESS_DESCRIPTION);

		swimlaneEClass = createEClass(SWIMLANE);
		createEAttribute(swimlaneEClass, SWIMLANE__NAME);
		createEReference(swimlaneEClass, SWIMLANE__MANAGE);
		createEAttribute(swimlaneEClass, SWIMLANE__ACTORID);
		createEAttribute(swimlaneEClass, SWIMLANE__POOLEDACTORS);
		createEAttribute(swimlaneEClass, SWIMLANE__CLAZZ);

		startStateEClass = createEClass(START_STATE);
		createEReference(startStateEClass, START_STATE__INITIATOR);

		taskNodeEClass = createEClass(TASK_NODE);
		createEReference(taskNodeEClass, TASK_NODE__TIMER);
		createEReference(taskNodeEClass, TASK_NODE__SWIMLANE);

		userTaskEClass = createEClass(USER_TASK);
		createEReference(userTaskEClass, USER_TASK__ATTRIBUTES);
		createEReference(userTaskEClass, USER_TASK__CLAZZ);

		endStateEClass = createEClass(END_STATE);

		nodeEClass = createEClass(NODE);
		createEReference(nodeEClass, NODE__ACTION);

		processStateEClass = createEClass(PROCESS_STATE);
		createEReference(processStateEClass, PROCESS_STATE__SUBPROCESS);
		createEReference(processStateEClass, PROCESS_STATE__VARIABLE);

		forkEClass = createEClass(FORK);

		joinEClass = createEClass(JOIN);

		decisionEClass = createEClass(DECISION);

		eventEClass = createEClass(EVENT);
		createEAttribute(eventEClass, EVENT__TYPE);
		createEReference(eventEClass, EVENT__ACTION);

		actionEClass = createEClass(ACTION);
		createEAttribute(actionEClass, ACTION__JAVA_CLASS);
		createEAttribute(actionEClass, ACTION__EXPRESSION);
		createEReference(actionEClass, ACTION__SCRIPT);

		scriptEClass = createEClass(SCRIPT);
		createEReference(scriptEClass, SCRIPT__VARIABLE);
		createEAttribute(scriptEClass, SCRIPT__EXPRESSION);

		timerEClass = createEClass(TIMER);
		createEAttribute(timerEClass, TIMER__DUEDATE);

		variableEClass = createEClass(VARIABLE);
		createEAttribute(variableEClass, VARIABLE__NAME);
		createEAttribute(variableEClass, VARIABLE__ACCESS);
		createEAttribute(variableEClass, VARIABLE__MAPPED_NAME);

		transitionEClass = createEClass(TRANSITION);
		createEAttribute(transitionEClass, TRANSITION__NAME);
		createEAttribute(transitionEClass, TRANSITION__CONDITION);
		createEReference(transitionEClass, TRANSITION__ACTION);
		createEReference(transitionEClass, TRANSITION__PARENT_TASK_NODE);
		createEReference(transitionEClass, TRANSITION__TIMER);
		createEReference(transitionEClass, TRANSITION__TO);
		createEAttribute(transitionEClass, TRANSITION__TITLE);

		stateEClass = createEClass(STATE);
		createEAttribute(stateEClass, STATE__NAME);
		createEReference(stateEClass, STATE__EVENT);
		createEAttribute(stateEClass, STATE__TITLE);
		createEAttribute(stateEClass, STATE__STATE_DESCRIPTION);

		attributeEClass = createEClass(ATTRIBUTE);
		createEAttribute(attributeEClass, ATTRIBUTE__TYP);
		createEAttribute(attributeEClass, ATTRIBUTE__TITLE);
		createEAttribute(attributeEClass, ATTRIBUTE__NAME);
		createEAttribute(attributeEClass, ATTRIBUTE__ALLOWED_VALUES);

		transitionTaskEClass = createEClass(TRANSITION_TASK);
		createEReference(transitionTaskEClass, TRANSITION_TASK__TRANSITION);

		wfPackageEClass = createEClass(WF_PACKAGE);

		// Create enums
		bpmEventTypeEEnum = createEEnum(BPM_EVENT_TYPE);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private boolean isInitialized = false;

	/**
	 * Complete the initialization of the package and its meta-model.  This
	 * method is guarded to have no affect on any invocation but its first.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void initializePackageContents() {
		if (isInitialized) return;
		isInitialized = true;

		// Initialize package
		setName(eNAME);
		setNsPrefix(eNS_PREFIX);
		setNsURI(eNS_URI);

		// Obtain other dependent packages
		CommonPackage theCommonPackage = (CommonPackage)EPackage.Registry.INSTANCE.getEPackage(CommonPackage.eNS_URI);
		ClazzPackage theClazzPackage = (ClazzPackage)EPackage.Registry.INSTANCE.getEPackage(ClazzPackage.eNS_URI);

		// Create type parameters

		// Set bounds for type parameters

		// Add supertypes to classes
		workflowModelElementEClass.getESuperTypes().add(theCommonPackage.getModelElement());
		processEClass.getESuperTypes().add(theCommonPackage.getPackage());
		swimlaneEClass.getESuperTypes().add(this.getWorkflowModelElement());
		startStateEClass.getESuperTypes().add(this.getUserTask());
		taskNodeEClass.getESuperTypes().add(this.getUserTask());
		userTaskEClass.getESuperTypes().add(this.getTransitionTask());
		endStateEClass.getESuperTypes().add(this.getState());
		nodeEClass.getESuperTypes().add(this.getTransitionTask());
		processStateEClass.getESuperTypes().add(this.getTransitionTask());
		forkEClass.getESuperTypes().add(this.getTransitionTask());
		joinEClass.getESuperTypes().add(this.getTransitionTask());
		decisionEClass.getESuperTypes().add(this.getTransitionTask());
		actionEClass.getESuperTypes().add(this.getWorkflowModelElement());
		timerEClass.getESuperTypes().add(this.getAction());
		transitionEClass.getESuperTypes().add(theCommonPackage.getModelElement());
		stateEClass.getESuperTypes().add(this.getWorkflowModelElement());
		attributeEClass.getESuperTypes().add(theCommonPackage.getModelElement());
		transitionTaskEClass.getESuperTypes().add(this.getState());
		wfPackageEClass.getESuperTypes().add(theCommonPackage.getPackage());

		// Initialize classes and features; add operations and parameters
		initEClass(workflowModelElementEClass, WorkflowModelElement.class, "WorkflowModelElement", IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);

		initEClass(processEClass, com.bluexml.side.workflow.Process.class, "Process", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEReference(getProcess_Swimlane(), this.getSwimlane(), null, "swimlane", null, 0, -1, com.bluexml.side.workflow.Process.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getProcess_Startstate(), this.getStartState(), null, "startstate", null, 1, 1, com.bluexml.side.workflow.Process.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, !IS_ORDERED);
		initEReference(getProcess_Endstate(), this.getEndState(), null, "endstate", null, 1, -1, com.bluexml.side.workflow.Process.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getProcess_Node(), this.getNode(), null, "node", null, 0, -1, com.bluexml.side.workflow.Process.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getProcess_Tasknode(), this.getTaskNode(), null, "tasknode", null, 0, -1, com.bluexml.side.workflow.Process.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getProcess_Processstate(), this.getProcessState(), null, "processstate", null, 0, -1, com.bluexml.side.workflow.Process.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getProcess_Fork(), this.getFork(), null, "fork", null, 0, -1, com.bluexml.side.workflow.Process.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getProcess_Join(), this.getJoin(), null, "join", null, 0, -1, com.bluexml.side.workflow.Process.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getProcess_Decision(), this.getDecision(), null, "decision", null, 0, -1, com.bluexml.side.workflow.Process.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getProcess_Elements(), this.getWorkflowModelElement(), null, "elements", null, 0, -1, com.bluexml.side.workflow.Process.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getProcess_ContentType(), theClazzPackage.getClazz(), null, "contentType", null, 0, 1, com.bluexml.side.workflow.Process.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getProcess_Title(), ecorePackage.getEString(), "title", null, 0, 1, com.bluexml.side.workflow.Process.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getProcess_ProcessDescription(), ecorePackage.getEString(), "processDescription", null, 0, 1, com.bluexml.side.workflow.Process.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(swimlaneEClass, Swimlane.class, "Swimlane", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEAttribute(getSwimlane_Name(), ecorePackage.getEString(), "name", null, 1, 1, Swimlane.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, !IS_UNIQUE, !IS_DERIVED, !IS_ORDERED);
		initEReference(getSwimlane_Manage(), this.getTaskNode(), this.getTaskNode_Swimlane(), "manage", null, 0, -1, Swimlane.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getSwimlane_Actorid(), ecorePackage.getEString(), "actorid", null, 0, 1, Swimlane.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, !IS_UNIQUE, !IS_DERIVED, !IS_ORDERED);
		initEAttribute(getSwimlane_Pooledactors(), ecorePackage.getEString(), "pooledactors", null, 0, 1, Swimlane.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, !IS_UNIQUE, !IS_DERIVED, !IS_ORDERED);
		initEAttribute(getSwimlane_Clazz(), ecorePackage.getEString(), "clazz", null, 0, 1, Swimlane.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, !IS_UNIQUE, !IS_DERIVED, !IS_ORDERED);

		EOperation op = addEOperation(swimlaneEClass, ecorePackage.getEBoolean(), "EqualsForMerge", 0, 1, IS_UNIQUE, IS_ORDERED);
		addEParameter(op, this.getSwimlane(), "other", 0, 1, IS_UNIQUE, IS_ORDERED);

		initEClass(startStateEClass, StartState.class, "StartState", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEReference(getStartState_Initiator(), this.getSwimlane(), null, "initiator", null, 1, 1, StartState.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(taskNodeEClass, TaskNode.class, "TaskNode", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEReference(getTaskNode_Timer(), this.getTimer(), null, "timer", null, 0, -1, TaskNode.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getTaskNode_Swimlane(), this.getSwimlane(), this.getSwimlane_Manage(), "swimlane", null, 1, 1, TaskNode.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, !IS_ORDERED);

		initEClass(userTaskEClass, UserTask.class, "UserTask", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEReference(getUserTask_Attributes(), this.getAttribute(), null, "attributes", null, 0, -1, UserTask.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getUserTask_Clazz(), theClazzPackage.getClazz(), null, "clazz", null, 0, -1, UserTask.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(endStateEClass, EndState.class, "EndState", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);

		initEClass(nodeEClass, Node.class, "Node", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEReference(getNode_Action(), this.getAction(), null, "action", null, 0, 1, Node.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, !IS_ORDERED);

		initEClass(processStateEClass, ProcessState.class, "ProcessState", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEReference(getProcessState_Subprocess(), this.getProcess(), null, "subprocess", null, 1, 1, ProcessState.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, !IS_ORDERED);
		initEReference(getProcessState_Variable(), this.getVariable(), null, "variable", null, 0, -1, ProcessState.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(forkEClass, Fork.class, "Fork", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);

		initEClass(joinEClass, Join.class, "Join", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);

		initEClass(decisionEClass, Decision.class, "Decision", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);

		initEClass(eventEClass, Event.class, "Event", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEAttribute(getEvent_Type(), this.getBPMEventType(), "type", null, 1, 1, Event.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, !IS_UNIQUE, !IS_DERIVED, !IS_ORDERED);
		initEReference(getEvent_Action(), this.getAction(), null, "action", null, 0, -1, Event.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(actionEClass, Action.class, "Action", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEAttribute(getAction_JavaClass(), ecorePackage.getEString(), "javaClass", null, 0, 1, Action.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, !IS_UNIQUE, !IS_DERIVED, !IS_ORDERED);
		initEAttribute(getAction_Expression(), ecorePackage.getEString(), "expression", null, 0, 1, Action.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, !IS_UNIQUE, !IS_DERIVED, !IS_ORDERED);
		initEReference(getAction_Script(), this.getScript(), null, "script", null, 0, -1, Action.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, !IS_ORDERED);

		initEClass(scriptEClass, Script.class, "Script", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEReference(getScript_Variable(), this.getVariable(), null, "variable", null, 0, -1, Script.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getScript_Expression(), ecorePackage.getEString(), "expression", null, 1, 1, Script.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(timerEClass, Timer.class, "Timer", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEAttribute(getTimer_Duedate(), ecorePackage.getEString(), "duedate", null, 1, 1, Timer.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, !IS_UNIQUE, !IS_DERIVED, !IS_ORDERED);

		initEClass(variableEClass, Variable.class, "Variable", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEAttribute(getVariable_Name(), ecorePackage.getEString(), "name", null, 1, 1, Variable.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, !IS_UNIQUE, !IS_DERIVED, !IS_ORDERED);
		initEAttribute(getVariable_Access(), ecorePackage.getEString(), "access", null, 1, 1, Variable.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, !IS_UNIQUE, !IS_DERIVED, !IS_ORDERED);
		initEAttribute(getVariable_MappedName(), ecorePackage.getEString(), "mappedName", null, 0, 1, Variable.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, !IS_UNIQUE, !IS_DERIVED, !IS_ORDERED);

		initEClass(transitionEClass, Transition.class, "Transition", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEAttribute(getTransition_Name(), ecorePackage.getEString(), "name", null, 1, 1, Transition.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, !IS_UNIQUE, !IS_DERIVED, !IS_ORDERED);
		initEAttribute(getTransition_Condition(), ecorePackage.getEString(), "condition", null, 0, 1, Transition.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, !IS_UNIQUE, !IS_DERIVED, !IS_ORDERED);
		initEReference(getTransition_Action(), this.getAction(), null, "action", null, 0, -1, Transition.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getTransition_ParentTaskNode(), this.getTaskNode(), null, "parentTaskNode", null, 0, 1, Transition.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, !IS_ORDERED);
		initEReference(getTransition_Timer(), this.getTimer(), null, "timer", null, 0, -1, Transition.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getTransition_To(), this.getState(), null, "to", null, 1, 1, Transition.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getTransition_Title(), ecorePackage.getEString(), "title", null, 0, 1, Transition.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(stateEClass, State.class, "State", IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEAttribute(getState_Name(), ecorePackage.getEString(), "name", null, 1, 1, State.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, !IS_UNIQUE, !IS_DERIVED, !IS_ORDERED);
		initEReference(getState_Event(), this.getEvent(), null, "event", null, 0, -1, State.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getState_Title(), ecorePackage.getEString(), "title", null, 0, 1, State.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getState_StateDescription(), ecorePackage.getEString(), "stateDescription", null, 0, 1, State.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(attributeEClass, Attribute.class, "Attribute", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEAttribute(getAttribute_Typ(), theCommonPackage.getDataType(), "typ", "String", 0, 1, Attribute.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getAttribute_Title(), ecorePackage.getEString(), "title", null, 0, 1, Attribute.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getAttribute_Name(), ecorePackage.getEString(), "name", null, 0, 1, Attribute.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getAttribute_AllowedValues(), ecorePackage.getEString(), "allowedValues", null, 0, -1, Attribute.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, !IS_ORDERED);

		initEClass(transitionTaskEClass, TransitionTask.class, "TransitionTask", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEReference(getTransitionTask_Transition(), this.getTransition(), null, "transition", null, 0, -1, TransitionTask.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		addEOperation(transitionTaskEClass, this.getState(), "getAllNextStates", 0, -1, IS_UNIQUE, IS_ORDERED);

		initEClass(wfPackageEClass, WfPackage.class, "WfPackage", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);

		// Initialize enums and add enum literals
		initEEnum(bpmEventTypeEEnum, BPMEventType.class, "BPMEventType");
		addEEnumLiteral(bpmEventTypeEEnum, BPMEventType.TASK_CREATE);
		addEEnumLiteral(bpmEventTypeEEnum, BPMEventType.TASK_START);
		addEEnumLiteral(bpmEventTypeEEnum, BPMEventType.TASK_ASSIGN);
		addEEnumLiteral(bpmEventTypeEEnum, BPMEventType.TASK_END);
		addEEnumLiteral(bpmEventTypeEEnum, BPMEventType.NODE_ENTER);
		addEEnumLiteral(bpmEventTypeEEnum, BPMEventType.NODE_LEAVE);
		addEEnumLiteral(bpmEventTypeEEnum, BPMEventType.BEFORE_SIGNAL);
		addEEnumLiteral(bpmEventTypeEEnum, BPMEventType.AFTER_SIGNAL);
		addEEnumLiteral(bpmEventTypeEEnum, BPMEventType.SUPERSTATE_ENTER);
		addEEnumLiteral(bpmEventTypeEEnum, BPMEventType.SUPERSTATE_LEAVE);
		addEEnumLiteral(bpmEventTypeEEnum, BPMEventType.TRANSITION);
		addEEnumLiteral(bpmEventTypeEEnum, BPMEventType.TIMER);
		addEEnumLiteral(bpmEventTypeEEnum, BPMEventType.SUBPROCESS_CREATED);
		addEEnumLiteral(bpmEventTypeEEnum, BPMEventType.SUBPROCESS_END);
		addEEnumLiteral(bpmEventTypeEEnum, BPMEventType.PROCESS_START);
		addEEnumLiteral(bpmEventTypeEEnum, BPMEventType.PROCESS_END);

		// Create resource
		createResource(eNS_URI);

		// Create annotations
		// http://www.bluexml.com/OCL
		createOCLAnnotations();
		// http://www.eclipse.org/emf/2002/Ecore
		createEcoreAnnotations();
	}

	/**
	 * Initializes the annotations for <b>http://www.bluexml.com/OCL</b>.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected void createOCLAnnotations() {
		String source = "http://www.bluexml.com/OCL";		
		addAnnotation
		  (processEClass, 
		   source, 
		   new String[] {
			 "PackageNameNull", "not self.name.oclIsUndefined() and self.name <> \'\'",
			 "OneStartTask", "self.startstate -> size() = 1",
			 "atLeastOneEndTask", "self.endstate -> size() >= 1"
		   });				
		addAnnotation
		  (swimlaneEClass, 
		   source, 
		   new String[] {
			 "ActorNameMustBeUnique", "self.getContainer().oclAsType(Process).swimlane -> select(n|n.name = self.name and n <> self )->size()=0",
			 "MustManageAtLeastOneTask", "(not (self.manage->isEmpty())) or (StartState.allInstances()->collect(ss | ss.initiator)->includes(self))",
			 "noSpecialCharacters", "self.name.regexMatch(\'[\\w]*\') = true",
			 "ActoridOrPooledactor", "not((self.pooledactors  -> isEmpty() or self.pooledactors =\'\') and\n(self.actorid -> isEmpty() or self.actorid=\'\'))"
		   });				
		addAnnotation
		  (swimlaneEClass.getEOperations().get(0), 
		   source, 
		   new String[] {
			 "body", "self.name = other.name"
		   });						
		addAnnotation
		  (taskNodeEClass, 
		   source, 
		   new String[] {
			 "TaskMustBePointerByTransition", "Transition.allInstances()-> select(t| t.to = self)->size()=1 or self.transition -> notEmpty()\n",
			 "TaskMustHaveOneTransitionOut", "self.transition -> size() >0"
		   });								
		addAnnotation
		  (forkEClass, 
		   source, 
		   new String[] {
			 "ForkMustBeFollowedByJoin", "self.getAllNextStates()->select(s | s.oclIsTypeOf(Join))->size()>0"
		   });				
		addAnnotation
		  (decisionEClass, 
		   source, 
		   new String[] {
			 "DecisionMustHaveOnlyOneTransitionWithCondition", "self.transition -> select (t1| t1.condition -> isEmpty() or t1.condition =\'\') -> size() = 1"
		   });												
		addAnnotation
		  (variableEClass, 
		   source, 
		   new String[] {
			 "accessMatchesWithReadWriteRequired", "self.access.regexMatch(\'[read,|write,|required,]*[read|write|required]\') = true"
		   });							
		addAnnotation
		  (transitionEClass, 
		   source, 
		   new String[] {
			 "NoTransitionWithSameName", "Transition.allInstances() -> select(n|n.name = self.name and n <> self )->size()=0",
			 "SourceAndTargetMustBeSet", "not self.to.oclIsUndefined() and not self.getContainer().oclIsUndefined()",
			 "noSpecialCharacters", "self.name.regexMatch(\'[\\w]*\') = true",
			 "titleMustNotBeNull", "if (self.getContainer().oclIsKindOf(UserTask)) then\n\tif (self.title = null or self.title.size() = 0) then\n\t\tfalse\n\telse\n\t\ttrue\n\tendif\nelse \n\ttrue\nendif"
		   });														
		addAnnotation
		  (stateEClass, 
		   source, 
		   new String[] {
			 "noSpecialCharacters", "self.name.regexMatch(\'[\\w]*\') = true",
			 "NoStateWithSameName", "State.allInstances()->select(s | s.getContainer() = self.getContainer())-> select(n|n.name = self.name and n <> self )->size()=0",
			 "NameNull", "not self.name.oclIsUndefined() and self.name <> \'\'"
		   });			
		addAnnotation
		  (attributeEClass, 
		   source, 
		   new String[] {
			 "UniqueNameForTaskAttribute", "Attribute.allInstances() -> select(n|n.name = self.name and n <> self )->size()=0"
		   });							
		addAnnotation
		  (transitionTaskEClass.getEOperations().get(0), 
		   source, 
		   new String[] {
			 "body", "self.transition->collect(t | t.to)->union(self.transition->select(t | t.to.oclIsKindOf(TransitionTask))->collect(t | t.to.oclAsType(TransitionTask).getAllNextStates())->flatten())",
			 "description", "Get all next states"
		   });
	}

	/**
	 * Initializes the annotations for <b>http://www.eclipse.org/emf/2002/Ecore</b>.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected void createEcoreAnnotations() {
		String source = "http://www.eclipse.org/emf/2002/Ecore";			
		addAnnotation
		  (processEClass, 
		   source, 
		   new String[] {
			 "constraints", "PackageNameNull OneStartTask atLeastOneEndTask"
		   });				
		addAnnotation
		  (swimlaneEClass, 
		   source, 
		   new String[] {
			 "constraints", "ActorNameMustBeUnique MustManageAtLeastOneTask noSpecialCharacters ActoridOrPooledactor"
		   });									
		addAnnotation
		  (taskNodeEClass, 
		   source, 
		   new String[] {
			 "constraints", "TaskMustBePointerByTransition TaskMustHaveOneTransitionOut"
		   });								
		addAnnotation
		  (forkEClass, 
		   source, 
		   new String[] {
			 "constraints", "ForkMustBeFollowedByJoin"
		   });				
		addAnnotation
		  (decisionEClass, 
		   source, 
		   new String[] {
			 "constraints", "DecisionMustHaveOnlyOneTransitionWithCondition"
		   });												
		addAnnotation
		  (variableEClass, 
		   source, 
		   new String[] {
			 "constraints", "accessMatchesWithReadWriteRequired"
		   });							
		addAnnotation
		  (transitionEClass, 
		   source, 
		   new String[] {
			 "constraints", "NoTransitionWithSameName SourceAndTargetMustBeSet noSpecialCharacters titleMustNotBeNull"
		   });														
		addAnnotation
		  (stateEClass, 
		   source, 
		   new String[] {
			 "constraints", "NoStateWithSameName noSpecialCharacters NameNull"
		   });			
		addAnnotation
		  (attributeEClass, 
		   source, 
		   new String[] {
			 "constraints", "UniqueNameForTaskAttribute"
		   });					
	}

} //WorkflowPackageImpl
