/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package com.bluexml.side.workflow.util;

import org.eclipse.emf.common.notify.Adapter;
import org.eclipse.emf.common.notify.Notifier;
import org.eclipse.emf.common.notify.impl.AdapterFactoryImpl;
import org.eclipse.emf.ecore.EObject;

import com.bluexml.side.common.ModelElement;
import com.bluexml.side.common.NamedModelElement;
import com.bluexml.side.workflow.Action;
import com.bluexml.side.workflow.Assignment;
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
import com.bluexml.side.workflow.Variable;
import com.bluexml.side.workflow.WorkflowModelElement;
import com.bluexml.side.workflow.WorkflowPackage;

/**
 * <!-- begin-user-doc -->
 * The <b>Adapter Factory</b> for the model.
 * It provides an adapter <code>createXXX</code> method for each class of the model.
 * <!-- end-user-doc -->
 * @see com.bluexml.side.workflow.WorkflowPackage
 * @generated
 */
public class WorkflowAdapterFactory extends AdapterFactoryImpl {
	/**
	 * The cached model package.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected static WorkflowPackage modelPackage;

	/**
	 * Creates an instance of the adapter factory.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public WorkflowAdapterFactory() {
		if (modelPackage == null) {
			modelPackage = WorkflowPackage.eINSTANCE;
		}
	}

	/**
	 * Returns whether this factory is applicable for the type of the object.
	 * <!-- begin-user-doc -->
	 * This implementation returns <code>true</code> if the object is either the model's package or is an instance object of the model.
	 * <!-- end-user-doc -->
	 * @return whether this factory is applicable for the type of the object.
	 * @generated
	 */
	@Override
	public boolean isFactoryForType(Object object) {
		if (object == modelPackage) {
			return true;
		}
		if (object instanceof EObject) {
			return ((EObject)object).eClass().getEPackage() == modelPackage;
		}
		return false;
	}

	/**
	 * The switch that delegates to the <code>createXXX</code> methods.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected WorkflowSwitch<Adapter> modelSwitch =
		new WorkflowSwitch<Adapter>() {
			@Override
			public Adapter caseWorkflowModelElement(WorkflowModelElement object) {
				return createWorkflowModelElementAdapter();
			}
			@Override
			public Adapter caseProcess(com.bluexml.side.workflow.Process object) {
				return createProcessAdapter();
			}
			@Override
			public Adapter caseSwimlane(Swimlane object) {
				return createSwimlaneAdapter();
			}
			@Override
			public Adapter caseStartState(StartState object) {
				return createStartStateAdapter();
			}
			@Override
			public Adapter caseEndState(EndState object) {
				return createEndStateAdapter();
			}
			@Override
			public Adapter caseNode(Node object) {
				return createNodeAdapter();
			}
			@Override
			public Adapter caseTaskNode(TaskNode object) {
				return createTaskNodeAdapter();
			}
			@Override
			public Adapter caseProcessState(ProcessState object) {
				return createProcessStateAdapter();
			}
			@Override
			public Adapter caseFork(Fork object) {
				return createForkAdapter();
			}
			@Override
			public Adapter caseJoin(Join object) {
				return createJoinAdapter();
			}
			@Override
			public Adapter caseDecision(Decision object) {
				return createDecisionAdapter();
			}
			@Override
			public Adapter caseEvent(Event object) {
				return createEventAdapter();
			}
			@Override
			public Adapter caseAction(Action object) {
				return createActionAdapter();
			}
			@Override
			public Adapter caseScript(Script object) {
				return createScriptAdapter();
			}
			@Override
			public Adapter caseTimer(Timer object) {
				return createTimerAdapter();
			}
			@Override
			public Adapter caseAssignment(Assignment object) {
				return createAssignmentAdapter();
			}
			@Override
			public Adapter caseVariable(Variable object) {
				return createVariableAdapter();
			}
			@Override
			public Adapter caseTransition(Transition object) {
				return createTransitionAdapter();
			}
			@Override
			public Adapter caseState(State object) {
				return createStateAdapter();
			}
			@Override
			public Adapter caseModelElement(ModelElement object) {
				return createModelElementAdapter();
			}
			@Override
			public Adapter caseNamedModelElement(NamedModelElement object) {
				return createNamedModelElementAdapter();
			}
			@Override
			public Adapter casePackage(com.bluexml.side.common.Package object) {
				return createPackageAdapter();
			}
			@Override
			public Adapter defaultCase(EObject object) {
				return createEObjectAdapter();
			}
		};

	/**
	 * Creates an adapter for the <code>target</code>.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param target the object to adapt.
	 * @return the adapter for the <code>target</code>.
	 * @generated
	 */
	@Override
	public Adapter createAdapter(Notifier target) {
		return modelSwitch.doSwitch((EObject)target);
	}


	/**
	 * Creates a new adapter for an object of class '{@link com.bluexml.side.workflow.WorkflowModelElement <em>Model Element</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see com.bluexml.side.workflow.WorkflowModelElement
	 * @generated
	 */
	public Adapter createWorkflowModelElementAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link com.bluexml.side.workflow.Process <em>Process</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see com.bluexml.side.workflow.Process
	 * @generated
	 */
	public Adapter createProcessAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link com.bluexml.side.workflow.Swimlane <em>Swimlane</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see com.bluexml.side.workflow.Swimlane
	 * @generated
	 */
	public Adapter createSwimlaneAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link com.bluexml.side.workflow.StartState <em>Start State</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see com.bluexml.side.workflow.StartState
	 * @generated
	 */
	public Adapter createStartStateAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link com.bluexml.side.workflow.EndState <em>End State</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see com.bluexml.side.workflow.EndState
	 * @generated
	 */
	public Adapter createEndStateAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link com.bluexml.side.workflow.Node <em>Node</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see com.bluexml.side.workflow.Node
	 * @generated
	 */
	public Adapter createNodeAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link com.bluexml.side.workflow.TaskNode <em>Task Node</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see com.bluexml.side.workflow.TaskNode
	 * @generated
	 */
	public Adapter createTaskNodeAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link com.bluexml.side.workflow.ProcessState <em>Process State</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see com.bluexml.side.workflow.ProcessState
	 * @generated
	 */
	public Adapter createProcessStateAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link com.bluexml.side.workflow.Fork <em>Fork</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see com.bluexml.side.workflow.Fork
	 * @generated
	 */
	public Adapter createForkAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link com.bluexml.side.workflow.Join <em>Join</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see com.bluexml.side.workflow.Join
	 * @generated
	 */
	public Adapter createJoinAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link com.bluexml.side.workflow.Decision <em>Decision</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see com.bluexml.side.workflow.Decision
	 * @generated
	 */
	public Adapter createDecisionAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link com.bluexml.side.workflow.Event <em>Event</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see com.bluexml.side.workflow.Event
	 * @generated
	 */
	public Adapter createEventAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link com.bluexml.side.workflow.Action <em>Action</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see com.bluexml.side.workflow.Action
	 * @generated
	 */
	public Adapter createActionAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link com.bluexml.side.workflow.Script <em>Script</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see com.bluexml.side.workflow.Script
	 * @generated
	 */
	public Adapter createScriptAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link com.bluexml.side.workflow.Timer <em>Timer</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see com.bluexml.side.workflow.Timer
	 * @generated
	 */
	public Adapter createTimerAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link com.bluexml.side.workflow.Assignment <em>Assignment</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see com.bluexml.side.workflow.Assignment
	 * @generated
	 */
	public Adapter createAssignmentAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link com.bluexml.side.workflow.Variable <em>Variable</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see com.bluexml.side.workflow.Variable
	 * @generated
	 */
	public Adapter createVariableAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link com.bluexml.side.workflow.Transition <em>Transition</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see com.bluexml.side.workflow.Transition
	 * @generated
	 */
	public Adapter createTransitionAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link com.bluexml.side.workflow.State <em>State</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see com.bluexml.side.workflow.State
	 * @generated
	 */
	public Adapter createStateAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link com.bluexml.side.common.ModelElement <em>Model Element</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see com.bluexml.side.common.ModelElement
	 * @generated
	 */
	public Adapter createModelElementAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link com.bluexml.side.common.NamedModelElement <em>Named Model Element</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see com.bluexml.side.common.NamedModelElement
	 * @generated
	 */
	public Adapter createNamedModelElementAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link com.bluexml.side.common.Package <em>Package</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see com.bluexml.side.common.Package
	 * @generated
	 */
	public Adapter createPackageAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for the default case.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @generated
	 */
	public Adapter createEObjectAdapter() {
		return null;
	}

} //WorkflowAdapterFactory
