/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package com.bluexml.side.workflow.util;


import java.util.Map;

import org.eclipse.emf.common.util.BasicDiagnostic;
import org.eclipse.emf.common.util.Diagnostic;
import org.eclipse.emf.common.util.DiagnosticChain;
import org.eclipse.emf.ecore.EAnnotation;
import org.eclipse.emf.ecore.EClassifier;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.plugin.EcorePlugin;
import org.eclipse.emf.ecore.util.EObjectValidator;
import org.eclipse.ocl.ParserException;
import org.eclipse.ocl.Query;
import org.eclipse.ocl.ecore.Constraint;
import org.eclipse.ocl.ecore.OCL;

import com.bluexml.side.util.metaModel.validate.OCLextension.KerblueOCL;
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
import com.bluexml.side.workflow.WorkflowModelElement;
import com.bluexml.side.workflow.WorkflowPackage;
import java.util.Arrays;
import java.util.List;

/**
 * <!-- begin-user-doc -->
 * The <b>Validator</b> for the model.
 * <!-- end-user-doc -->
 * @see com.bluexml.side.workflow.WorkflowPackage
 * @generated
 */
public class WorkflowValidator extends EObjectValidator {
	/**
	 * The cached model package
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public static final WorkflowValidator INSTANCE = new WorkflowValidator();

	/**
	 * A constant for the {@link org.eclipse.emf.common.util.Diagnostic#getSource() source} of diagnostic {@link org.eclipse.emf.common.util.Diagnostic#getCode() codes} from this package.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.eclipse.emf.common.util.Diagnostic#getSource()
	 * @see org.eclipse.emf.common.util.Diagnostic#getCode()
	 * @generated
	 */
	public static final String DIAGNOSTIC_SOURCE = "com.bluexml.side.workflow";

	/**
	 * A constant with a fixed name that can be used as the base value for additional hand written constants.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private static final int GENERATED_DIAGNOSTIC_CODE_COUNT = 0;

	/**
	 * A constant with a fixed name that can be used as the base value for additional hand written constants in a derived class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected static final int DIAGNOSTIC_CODE_COUNT = GENERATED_DIAGNOSTIC_CODE_COUNT;

	/**
	 * The parsed OCL expression for the definition of the '<em>PackageNameNull</em>' invariant constraint.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private static Constraint process_PackageNameNullInvOCL;
	/**
	 * The parsed OCL expression for the definition of the '<em>OneStartTask</em>' invariant constraint.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private static Constraint process_OneStartTaskInvOCL;

	/**
	 * The parsed OCL expression for the definition of the '<em>atLeastOneEndTask</em>' invariant constraint.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private static Constraint process_atLeastOneEndTaskInvOCL;

	/**
	 * The parsed OCL expression for the definition of the '<em>ActorNameMustBeUnique</em>' invariant constraint.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private static Constraint swimlane_ActorNameMustBeUniqueInvOCL;
	/**
	 * The parsed OCL expression for the definition of the '<em>MustManageAtLeastOneTask</em>' invariant constraint.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private static Constraint swimlane_MustManageAtLeastOneTaskInvOCL;
	/**
	 * The parsed OCL expression for the definition of the '<em>noSpecialCharacters</em>' invariant constraint.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private static Constraint swimlane_noSpecialCharactersInvOCL;
	/**
	 * The parsed OCL expression for the definition of the '<em>ActoridOrPooledactor</em>' invariant constraint.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private static Constraint swimlane_ActoridOrPooledactorInvOCL;

	/**
	 * The parsed OCL expression for the definition of the '<em>TaskMustBePointerByTransition</em>' invariant constraint.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private static Constraint taskNode_TaskMustBePointerByTransitionInvOCL;
	/**
	 * The parsed OCL expression for the definition of the '<em>TaskMustHaveOneTransitionOut</em>' invariant constraint.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private static Constraint taskNode_TaskMustHaveOneTransitionOutInvOCL;

	/**
	 * The parsed OCL expression for the definition of the '<em>ForkMustBeFollowedByJoin</em>' invariant constraint.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private static Constraint fork_ForkMustBeFollowedByJoinInvOCL;

	/**
	 * The parsed OCL expression for the definition of the '<em>DecisionMustHaveOnlyOneTransitionWithCondition</em>' invariant constraint.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private static Constraint decision_DecisionMustHaveOnlyOneTransitionWithConditionInvOCL;

	/**
	 * The parsed OCL expression for the definition of the '<em>accessMatchesWithReadWriteRequired</em>' invariant constraint.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private static Constraint variable_accessMatchesWithReadWriteRequiredInvOCL;

	/**
	 * The parsed OCL expression for the definition of the '<em>NoTransitionWithSameName</em>' invariant constraint.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private static Constraint transition_NoTransitionWithSameNameInvOCL;
	/**
	 * The parsed OCL expression for the definition of the '<em>SourceAndTargetMustBeSet</em>' invariant constraint.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private static Constraint transition_SourceAndTargetMustBeSetInvOCL;
	/**
	 * The parsed OCL expression for the definition of the '<em>noSpecialCharacters</em>' invariant constraint.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private static Constraint transition_noSpecialCharactersInvOCL;
	/**
	 * The parsed OCL expression for the definition of the '<em>titleMustNotBeNull</em>' invariant constraint.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private static Constraint transition_titleMustNotBeNullInvOCL;

	/**
	 * The parsed OCL expression for the definition of the '<em>NoStateWithSameName</em>' invariant constraint.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private static Constraint state_NoStateWithSameNameInvOCL;
	/**
	 * The parsed OCL expression for the definition of the '<em>noSpecialCharacters</em>' invariant constraint.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private static Constraint state_noSpecialCharactersInvOCL;
	/**
	 * The parsed OCL expression for the definition of the '<em>NameNull</em>' invariant constraint.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private static Constraint state_NameNullInvOCL;

	/**
	 * The parsed OCL expression for the definition of the '<em>UniqueNameForTaskAttribute</em>' invariant constraint.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private static Constraint attribute_UniqueNameForTaskAttributeInvOCL;
	private static final String OCL_ANNOTATION_SOURCE = "http://www.bluexml.com/OCL";
	
	private static final OCL OCL_ENV = KerblueOCL.newInstance();
	/**
	 * Creates an instance of the switch.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public WorkflowValidator() {
		super();
	}

	/**
	 * Returns the package of this validator switch.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EPackage getEPackage() {
	  return WorkflowPackage.eINSTANCE;
	}

	/**
	 * Calls <code>validateXXX</code> for the corresonding classifier of the model.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected boolean validate(int classifierID, Object value, DiagnosticChain diagnostics, Map<Object, Object> context) {
		switch (classifierID) {
			case WorkflowPackage.WORKFLOW_MODEL_ELEMENT:
				return validateWorkflowModelElement((WorkflowModelElement)value, diagnostics, context);
			case WorkflowPackage.PROCESS:
				return validateProcess((com.bluexml.side.workflow.Process)value, diagnostics, context);
			case WorkflowPackage.SWIMLANE:
				return validateSwimlane((Swimlane)value, diagnostics, context);
			case WorkflowPackage.START_STATE:
				return validateStartState((StartState)value, diagnostics, context);
			case WorkflowPackage.TASK_NODE:
				return validateTaskNode((TaskNode)value, diagnostics, context);
			case WorkflowPackage.USER_TASK:
				return validateUserTask((UserTask)value, diagnostics, context);
			case WorkflowPackage.END_STATE:
				return validateEndState((EndState)value, diagnostics, context);
			case WorkflowPackage.NODE:
				return validateNode((Node)value, diagnostics, context);
			case WorkflowPackage.PROCESS_STATE:
				return validateProcessState((ProcessState)value, diagnostics, context);
			case WorkflowPackage.FORK:
				return validateFork((Fork)value, diagnostics, context);
			case WorkflowPackage.JOIN:
				return validateJoin((Join)value, diagnostics, context);
			case WorkflowPackage.DECISION:
				return validateDecision((Decision)value, diagnostics, context);
			case WorkflowPackage.EVENT:
				return validateEvent((Event)value, diagnostics, context);
			case WorkflowPackage.ACTION:
				return validateAction((Action)value, diagnostics, context);
			case WorkflowPackage.SCRIPT:
				return validateScript((Script)value, diagnostics, context);
			case WorkflowPackage.TIMER:
				return validateTimer((Timer)value, diagnostics, context);
			case WorkflowPackage.VARIABLE:
				return validateVariable((Variable)value, diagnostics, context);
			case WorkflowPackage.TRANSITION:
				return validateTransition((Transition)value, diagnostics, context);
			case WorkflowPackage.STATE:
				return validateState((State)value, diagnostics, context);
			case WorkflowPackage.ATTRIBUTE:
				return validateAttribute((Attribute)value, diagnostics, context);
			case WorkflowPackage.TRANSITION_TASK:
				return validateTransitionTask((TransitionTask)value, diagnostics, context);
			case WorkflowPackage.WF_PACKAGE:
				return validateWfPackage((WfPackage)value, diagnostics, context);
			case WorkflowPackage.BPM_EVENT_TYPE:
				return validateBPMEventType((BPMEventType)value, diagnostics, context);
			default:
				return true;
		}
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean validateWorkflowModelElement(WorkflowModelElement workflowModelElement, DiagnosticChain diagnostics, Map<Object, Object> context) {
		return validate_EveryDefaultConstraint(workflowModelElement, diagnostics, context);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean validateProcess(com.bluexml.side.workflow.Process process, DiagnosticChain diagnostics, Map<Object, Object> context) {
		boolean result = validate_EveryMultiplicityConforms(process, diagnostics, context);
		if (result || diagnostics != null) result &= validate_EveryDataValueConforms(process, diagnostics, context);
		if (result || diagnostics != null) result &= validate_EveryReferenceIsContained(process, diagnostics, context);
		if (result || diagnostics != null) result &= validate_EveryProxyResolves(process, diagnostics, context);
		if (result || diagnostics != null) result &= validate_UniqueID(process, diagnostics, context);
		if (result || diagnostics != null) result &= validate_EveryKeyUnique(process, diagnostics, context);
		if (result || diagnostics != null) result &= validate_EveryMapEntryUnique(process, diagnostics, context);
		if (result || diagnostics != null) result &= validateProcess_PackageNameNull(process, diagnostics, context);
		if (result || diagnostics != null) result &= validateProcess_OneStartTask(process, diagnostics, context);
		if (result || diagnostics != null) result &= validateProcess_atLeastOneEndTask(process, diagnostics, context);
		return result;
	}

	/**
	 * Validates the PackageNameNull constraint of '<em>Process</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean validateProcess_PackageNameNull(com.bluexml.side.workflow.Process process, DiagnosticChain diagnostics, Map<Object, Object> context) {
        if (process_PackageNameNullInvOCL == null) {
			OCL.Helper helper = OCL_ENV.createOCLHelper();
			helper.setContext(WorkflowPackage.Literals.PROCESS);

			EAnnotation ocl = WorkflowPackage.Literals.PROCESS.getEAnnotation(OCL_ANNOTATION_SOURCE);
			String expr = ocl.getDetails().get("PackageNameNull");

			try {
				process_PackageNameNullInvOCL = helper.createInvariant(expr);
			}
			catch (ParserException e) {
				throw new UnsupportedOperationException(e.getLocalizedMessage());
			}
		}

		Query<EClassifier, ?, ?> query = OCL_ENV.createQuery(process_PackageNameNullInvOCL);

		if (!query.check(process)) {
			if (diagnostics != null) {
				diagnostics.add
					(new BasicDiagnostic
						((doThrowError( WorkflowPackage.Literals.PROCESS.getEAnnotation("http://www.eclipse.org/emf/2002/Ecore"),"PackageNameNull")? Diagnostic.ERROR : Diagnostic.WARNING),
						 DIAGNOSTIC_SOURCE,
						 0,
						 EcorePlugin.INSTANCE.getString("_UI_GenericConstraint_diagnostic", new Object[] { "PackageNameNull", getObjectLabel(process, context) }),
						 new Object[] { process }));
			}
			return false;
		}
		return true;
	}

	/**
	 * Validates the OneStartTask constraint of '<em>Process</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean validateProcess_OneStartTask(com.bluexml.side.workflow.Process process, DiagnosticChain diagnostics, Map<Object, Object> context) {
        if (process_OneStartTaskInvOCL == null) {
			OCL.Helper helper = OCL_ENV.createOCLHelper();
			helper.setContext(WorkflowPackage.Literals.PROCESS);

			EAnnotation ocl = WorkflowPackage.Literals.PROCESS.getEAnnotation(OCL_ANNOTATION_SOURCE);
			String expr = ocl.getDetails().get("OneStartTask");

			try {
				process_OneStartTaskInvOCL = helper.createInvariant(expr);
			}
			catch (ParserException e) {
				throw new UnsupportedOperationException(e.getLocalizedMessage());
			}
		}

		Query<EClassifier, ?, ?> query = OCL_ENV.createQuery(process_OneStartTaskInvOCL);

		if (!query.check(process)) {
			if (diagnostics != null) {
				diagnostics.add
					(new BasicDiagnostic
						((doThrowError( WorkflowPackage.Literals.PROCESS.getEAnnotation("http://www.eclipse.org/emf/2002/Ecore"),"OneStartTask")? Diagnostic.ERROR : Diagnostic.WARNING),
						 DIAGNOSTIC_SOURCE,
						 0,
						 EcorePlugin.INSTANCE.getString("_UI_GenericConstraint_diagnostic", new Object[] { "OneStartTask", getObjectLabel(process, context) }),
						 new Object[] { process }));
			}
			return false;
		}
		return true;
	}

	/**
	 * Validates the atLeastOneEndTask constraint of '<em>Process</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean validateProcess_atLeastOneEndTask(com.bluexml.side.workflow.Process process, DiagnosticChain diagnostics, Map<Object, Object> context) {
        if (process_atLeastOneEndTaskInvOCL == null) {
			OCL.Helper helper = OCL_ENV.createOCLHelper();
			helper.setContext(WorkflowPackage.Literals.PROCESS);

			EAnnotation ocl = WorkflowPackage.Literals.PROCESS.getEAnnotation(OCL_ANNOTATION_SOURCE);
			String expr = ocl.getDetails().get("atLeastOneEndTask");

			try {
				process_atLeastOneEndTaskInvOCL = helper.createInvariant(expr);
			}
			catch (ParserException e) {
				throw new UnsupportedOperationException(e.getLocalizedMessage());
			}
		}

		Query<EClassifier, ?, ?> query = OCL_ENV.createQuery(process_atLeastOneEndTaskInvOCL);

		if (!query.check(process)) {
			if (diagnostics != null) {
				diagnostics.add
					(new BasicDiagnostic
						((doThrowError( WorkflowPackage.Literals.PROCESS.getEAnnotation("http://www.eclipse.org/emf/2002/Ecore"),"atLeastOneEndTask")? Diagnostic.ERROR : Diagnostic.WARNING),
						 DIAGNOSTIC_SOURCE,
						 0,
						 EcorePlugin.INSTANCE.getString("_UI_GenericConstraint_diagnostic", new Object[] { "atLeastOneEndTask", getObjectLabel(process, context) }),
						 new Object[] { process }));
			}
			return false;
		}
		return true;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean validateSwimlane(Swimlane swimlane, DiagnosticChain diagnostics, Map<Object, Object> context) {
		boolean result = validate_EveryMultiplicityConforms(swimlane, diagnostics, context);
		if (result || diagnostics != null) result &= validate_EveryDataValueConforms(swimlane, diagnostics, context);
		if (result || diagnostics != null) result &= validate_EveryReferenceIsContained(swimlane, diagnostics, context);
		if (result || diagnostics != null) result &= validate_EveryProxyResolves(swimlane, diagnostics, context);
		if (result || diagnostics != null) result &= validate_UniqueID(swimlane, diagnostics, context);
		if (result || diagnostics != null) result &= validate_EveryKeyUnique(swimlane, diagnostics, context);
		if (result || diagnostics != null) result &= validate_EveryMapEntryUnique(swimlane, diagnostics, context);
		if (result || diagnostics != null) result &= validateSwimlane_ActorNameMustBeUnique(swimlane, diagnostics, context);
		if (result || diagnostics != null) result &= validateSwimlane_MustManageAtLeastOneTask(swimlane, diagnostics, context);
		if (result || diagnostics != null) result &= validateSwimlane_noSpecialCharacters(swimlane, diagnostics, context);
		if (result || diagnostics != null) result &= validateSwimlane_ActoridOrPooledactor(swimlane, diagnostics, context);
		return result;
	}

	/**
	 * Validates the ActorNameMustBeUnique constraint of '<em>Swimlane</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean validateSwimlane_ActorNameMustBeUnique(Swimlane swimlane, DiagnosticChain diagnostics, Map<Object, Object> context) {
        if (swimlane_ActorNameMustBeUniqueInvOCL == null) {
			OCL.Helper helper = OCL_ENV.createOCLHelper();
			helper.setContext(WorkflowPackage.Literals.SWIMLANE);

			EAnnotation ocl = WorkflowPackage.Literals.SWIMLANE.getEAnnotation(OCL_ANNOTATION_SOURCE);
			String expr = ocl.getDetails().get("ActorNameMustBeUnique");

			try {
				swimlane_ActorNameMustBeUniqueInvOCL = helper.createInvariant(expr);
			}
			catch (ParserException e) {
				throw new UnsupportedOperationException(e.getLocalizedMessage());
			}
		}

		Query<EClassifier, ?, ?> query = OCL_ENV.createQuery(swimlane_ActorNameMustBeUniqueInvOCL);

		if (!query.check(swimlane)) {
			if (diagnostics != null) {
				diagnostics.add
					(new BasicDiagnostic
						((doThrowError( WorkflowPackage.Literals.SWIMLANE.getEAnnotation("http://www.eclipse.org/emf/2002/Ecore"),"ActorNameMustBeUnique")? Diagnostic.ERROR : Diagnostic.WARNING),
						 DIAGNOSTIC_SOURCE,
						 0,
						 EcorePlugin.INSTANCE.getString("_UI_GenericConstraint_diagnostic", new Object[] { "ActorNameMustBeUnique", getObjectLabel(swimlane, context) }),
						 new Object[] { swimlane }));
			}
			return false;
		}
		return true;
	}

	/**
	 * Validates the MustManageAtLeastOneTask constraint of '<em>Swimlane</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean validateSwimlane_MustManageAtLeastOneTask(Swimlane swimlane, DiagnosticChain diagnostics, Map<Object, Object> context) {
        if (swimlane_MustManageAtLeastOneTaskInvOCL == null) {
			OCL.Helper helper = OCL_ENV.createOCLHelper();
			helper.setContext(WorkflowPackage.Literals.SWIMLANE);

			EAnnotation ocl = WorkflowPackage.Literals.SWIMLANE.getEAnnotation(OCL_ANNOTATION_SOURCE);
			String expr = ocl.getDetails().get("MustManageAtLeastOneTask");

			try {
				swimlane_MustManageAtLeastOneTaskInvOCL = helper.createInvariant(expr);
			}
			catch (ParserException e) {
				throw new UnsupportedOperationException(e.getLocalizedMessage());
			}
		}

		Query<EClassifier, ?, ?> query = OCL_ENV.createQuery(swimlane_MustManageAtLeastOneTaskInvOCL);

		if (!query.check(swimlane)) {
			if (diagnostics != null) {
				diagnostics.add
					(new BasicDiagnostic
						((doThrowError( WorkflowPackage.Literals.SWIMLANE.getEAnnotation("http://www.eclipse.org/emf/2002/Ecore"),"MustManageAtLeastOneTask")? Diagnostic.ERROR : Diagnostic.WARNING),
						 DIAGNOSTIC_SOURCE,
						 0,
						 EcorePlugin.INSTANCE.getString("_UI_GenericConstraint_diagnostic", new Object[] { "MustManageAtLeastOneTask", getObjectLabel(swimlane, context) }),
						 new Object[] { swimlane }));
			}
			return false;
		}
		return true;
	}

	/**
	 * Validates the noSpecialCharacters constraint of '<em>Swimlane</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean validateSwimlane_noSpecialCharacters(Swimlane swimlane, DiagnosticChain diagnostics, Map<Object, Object> context) {
        if (swimlane_noSpecialCharactersInvOCL == null) {
			OCL.Helper helper = OCL_ENV.createOCLHelper();
			helper.setContext(WorkflowPackage.Literals.SWIMLANE);

			EAnnotation ocl = WorkflowPackage.Literals.SWIMLANE.getEAnnotation(OCL_ANNOTATION_SOURCE);
			String expr = ocl.getDetails().get("noSpecialCharacters");

			try {
				swimlane_noSpecialCharactersInvOCL = helper.createInvariant(expr);
			}
			catch (ParserException e) {
				throw new UnsupportedOperationException(e.getLocalizedMessage());
			}
		}

		Query<EClassifier, ?, ?> query = OCL_ENV.createQuery(swimlane_noSpecialCharactersInvOCL);

		if (!query.check(swimlane)) {
			if (diagnostics != null) {
				diagnostics.add
					(new BasicDiagnostic
						((doThrowError( WorkflowPackage.Literals.SWIMLANE.getEAnnotation("http://www.eclipse.org/emf/2002/Ecore"),"noSpecialCharacters")? Diagnostic.ERROR : Diagnostic.WARNING),
						 DIAGNOSTIC_SOURCE,
						 0,
						 EcorePlugin.INSTANCE.getString("_UI_GenericConstraint_diagnostic", new Object[] { "noSpecialCharacters", getObjectLabel(swimlane, context) }),
						 new Object[] { swimlane }));
			}
			return false;
		}
		return true;
	}

	/**
	 * Validates the ActoridOrPooledactor constraint of '<em>Swimlane</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean validateSwimlane_ActoridOrPooledactor(Swimlane swimlane, DiagnosticChain diagnostics, Map<Object, Object> context) {
        if (swimlane_ActoridOrPooledactorInvOCL == null) {
			OCL.Helper helper = OCL_ENV.createOCLHelper();
			helper.setContext(WorkflowPackage.Literals.SWIMLANE);

			EAnnotation ocl = WorkflowPackage.Literals.SWIMLANE.getEAnnotation(OCL_ANNOTATION_SOURCE);
			String expr = ocl.getDetails().get("ActoridOrPooledactor");

			try {
				swimlane_ActoridOrPooledactorInvOCL = helper.createInvariant(expr);
			}
			catch (ParserException e) {
				throw new UnsupportedOperationException(e.getLocalizedMessage());
			}
		}

		Query<EClassifier, ?, ?> query = OCL_ENV.createQuery(swimlane_ActoridOrPooledactorInvOCL);

		if (!query.check(swimlane)) {
			if (diagnostics != null) {
				diagnostics.add
					(new BasicDiagnostic
						((doThrowError( WorkflowPackage.Literals.SWIMLANE.getEAnnotation("http://www.eclipse.org/emf/2002/Ecore"),"ActoridOrPooledactor")? Diagnostic.ERROR : Diagnostic.WARNING),
						 DIAGNOSTIC_SOURCE,
						 0,
						 EcorePlugin.INSTANCE.getString("_UI_GenericConstraint_diagnostic", new Object[] { "ActoridOrPooledactor", getObjectLabel(swimlane, context) }),
						 new Object[] { swimlane }));
			}
			return false;
		}
		return true;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean validateStartState(StartState startState, DiagnosticChain diagnostics, Map<Object, Object> context) {
		boolean result = validate_EveryMultiplicityConforms(startState, diagnostics, context);
		if (result || diagnostics != null) result &= validate_EveryDataValueConforms(startState, diagnostics, context);
		if (result || diagnostics != null) result &= validate_EveryReferenceIsContained(startState, diagnostics, context);
		if (result || diagnostics != null) result &= validate_EveryProxyResolves(startState, diagnostics, context);
		if (result || diagnostics != null) result &= validate_UniqueID(startState, diagnostics, context);
		if (result || diagnostics != null) result &= validate_EveryKeyUnique(startState, diagnostics, context);
		if (result || diagnostics != null) result &= validate_EveryMapEntryUnique(startState, diagnostics, context);
		if (result || diagnostics != null) result &= validateState_NoStateWithSameName(startState, diagnostics, context);
		if (result || diagnostics != null) result &= validateState_noSpecialCharacters(startState, diagnostics, context);
		if (result || diagnostics != null) result &= validateState_NameNull(startState, diagnostics, context);
		return result;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean validateEndState(EndState endState, DiagnosticChain diagnostics, Map<Object, Object> context) {
		boolean result = validate_EveryMultiplicityConforms(endState, diagnostics, context);
		if (result || diagnostics != null) result &= validate_EveryDataValueConforms(endState, diagnostics, context);
		if (result || diagnostics != null) result &= validate_EveryReferenceIsContained(endState, diagnostics, context);
		if (result || diagnostics != null) result &= validate_EveryProxyResolves(endState, diagnostics, context);
		if (result || diagnostics != null) result &= validate_UniqueID(endState, diagnostics, context);
		if (result || diagnostics != null) result &= validate_EveryKeyUnique(endState, diagnostics, context);
		if (result || diagnostics != null) result &= validate_EveryMapEntryUnique(endState, diagnostics, context);
		if (result || diagnostics != null) result &= validateState_NoStateWithSameName(endState, diagnostics, context);
		if (result || diagnostics != null) result &= validateState_noSpecialCharacters(endState, diagnostics, context);
		if (result || diagnostics != null) result &= validateState_NameNull(endState, diagnostics, context);
		return result;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean validateNode(Node node, DiagnosticChain diagnostics, Map<Object, Object> context) {
		boolean result = validate_EveryMultiplicityConforms(node, diagnostics, context);
		if (result || diagnostics != null) result &= validate_EveryDataValueConforms(node, diagnostics, context);
		if (result || diagnostics != null) result &= validate_EveryReferenceIsContained(node, diagnostics, context);
		if (result || diagnostics != null) result &= validate_EveryProxyResolves(node, diagnostics, context);
		if (result || diagnostics != null) result &= validate_UniqueID(node, diagnostics, context);
		if (result || diagnostics != null) result &= validate_EveryKeyUnique(node, diagnostics, context);
		if (result || diagnostics != null) result &= validate_EveryMapEntryUnique(node, diagnostics, context);
		if (result || diagnostics != null) result &= validateState_NoStateWithSameName(node, diagnostics, context);
		if (result || diagnostics != null) result &= validateState_noSpecialCharacters(node, diagnostics, context);
		if (result || diagnostics != null) result &= validateState_NameNull(node, diagnostics, context);
		return result;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean validateTaskNode(TaskNode taskNode, DiagnosticChain diagnostics, Map<Object, Object> context) {
		boolean result = validate_EveryMultiplicityConforms(taskNode, diagnostics, context);
		if (result || diagnostics != null) result &= validate_EveryDataValueConforms(taskNode, diagnostics, context);
		if (result || diagnostics != null) result &= validate_EveryReferenceIsContained(taskNode, diagnostics, context);
		if (result || diagnostics != null) result &= validate_EveryProxyResolves(taskNode, diagnostics, context);
		if (result || diagnostics != null) result &= validate_UniqueID(taskNode, diagnostics, context);
		if (result || diagnostics != null) result &= validate_EveryKeyUnique(taskNode, diagnostics, context);
		if (result || diagnostics != null) result &= validate_EveryMapEntryUnique(taskNode, diagnostics, context);
		if (result || diagnostics != null) result &= validateState_NoStateWithSameName(taskNode, diagnostics, context);
		if (result || diagnostics != null) result &= validateState_noSpecialCharacters(taskNode, diagnostics, context);
		if (result || diagnostics != null) result &= validateState_NameNull(taskNode, diagnostics, context);
		if (result || diagnostics != null) result &= validateTaskNode_TaskMustBePointerByTransition(taskNode, diagnostics, context);
		if (result || diagnostics != null) result &= validateTaskNode_TaskMustHaveOneTransitionOut(taskNode, diagnostics, context);
		return result;
	}

	/**
	 * Validates the TaskMustBePointerByTransition constraint of '<em>Task Node</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean validateTaskNode_TaskMustBePointerByTransition(TaskNode taskNode, DiagnosticChain diagnostics, Map<Object, Object> context) {
        if (taskNode_TaskMustBePointerByTransitionInvOCL == null) {
			OCL.Helper helper = OCL_ENV.createOCLHelper();
			helper.setContext(WorkflowPackage.Literals.TASK_NODE);

			EAnnotation ocl = WorkflowPackage.Literals.TASK_NODE.getEAnnotation(OCL_ANNOTATION_SOURCE);
			String expr = ocl.getDetails().get("TaskMustBePointerByTransition");

			try {
				taskNode_TaskMustBePointerByTransitionInvOCL = helper.createInvariant(expr);
			}
			catch (ParserException e) {
				throw new UnsupportedOperationException(e.getLocalizedMessage());
			}
		}

		Query<EClassifier, ?, ?> query = OCL_ENV.createQuery(taskNode_TaskMustBePointerByTransitionInvOCL);

		if (!query.check(taskNode)) {
			if (diagnostics != null) {
				diagnostics.add
					(new BasicDiagnostic
						((doThrowError( WorkflowPackage.Literals.TASK_NODE.getEAnnotation("http://www.eclipse.org/emf/2002/Ecore"),"TaskMustBePointerByTransition")? Diagnostic.ERROR : Diagnostic.WARNING),
						 DIAGNOSTIC_SOURCE,
						 0,
						 EcorePlugin.INSTANCE.getString("_UI_GenericConstraint_diagnostic", new Object[] { "TaskMustBePointerByTransition", getObjectLabel(taskNode, context) }),
						 new Object[] { taskNode }));
			}
			return false;
		}
		return true;
	}

	/**
	 * Validates the TaskMustHaveOneTransitionOut constraint of '<em>Task Node</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean validateTaskNode_TaskMustHaveOneTransitionOut(TaskNode taskNode, DiagnosticChain diagnostics, Map<Object, Object> context) {
        if (taskNode_TaskMustHaveOneTransitionOutInvOCL == null) {
			OCL.Helper helper = OCL_ENV.createOCLHelper();
			helper.setContext(WorkflowPackage.Literals.TASK_NODE);

			EAnnotation ocl = WorkflowPackage.Literals.TASK_NODE.getEAnnotation(OCL_ANNOTATION_SOURCE);
			String expr = ocl.getDetails().get("TaskMustHaveOneTransitionOut");

			try {
				taskNode_TaskMustHaveOneTransitionOutInvOCL = helper.createInvariant(expr);
			}
			catch (ParserException e) {
				throw new UnsupportedOperationException(e.getLocalizedMessage());
			}
		}

		Query<EClassifier, ?, ?> query = OCL_ENV.createQuery(taskNode_TaskMustHaveOneTransitionOutInvOCL);

		if (!query.check(taskNode)) {
			if (diagnostics != null) {
				diagnostics.add
					(new BasicDiagnostic
						((doThrowError( WorkflowPackage.Literals.TASK_NODE.getEAnnotation("http://www.eclipse.org/emf/2002/Ecore"),"TaskMustHaveOneTransitionOut")? Diagnostic.ERROR : Diagnostic.WARNING),
						 DIAGNOSTIC_SOURCE,
						 0,
						 EcorePlugin.INSTANCE.getString("_UI_GenericConstraint_diagnostic", new Object[] { "TaskMustHaveOneTransitionOut", getObjectLabel(taskNode, context) }),
						 new Object[] { taskNode }));
			}
			return false;
		}
		return true;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean validateUserTask(UserTask userTask, DiagnosticChain diagnostics, Map<Object, Object> context) {
		boolean result = validate_EveryMultiplicityConforms(userTask, diagnostics, context);
		if (result || diagnostics != null) result &= validate_EveryDataValueConforms(userTask, diagnostics, context);
		if (result || diagnostics != null) result &= validate_EveryReferenceIsContained(userTask, diagnostics, context);
		if (result || diagnostics != null) result &= validate_EveryProxyResolves(userTask, diagnostics, context);
		if (result || diagnostics != null) result &= validate_UniqueID(userTask, diagnostics, context);
		if (result || diagnostics != null) result &= validate_EveryKeyUnique(userTask, diagnostics, context);
		if (result || diagnostics != null) result &= validate_EveryMapEntryUnique(userTask, diagnostics, context);
		if (result || diagnostics != null) result &= validateState_NoStateWithSameName(userTask, diagnostics, context);
		if (result || diagnostics != null) result &= validateState_noSpecialCharacters(userTask, diagnostics, context);
		if (result || diagnostics != null) result &= validateState_NameNull(userTask, diagnostics, context);
		return result;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean validateProcessState(ProcessState processState, DiagnosticChain diagnostics, Map<Object, Object> context) {
		boolean result = validate_EveryMultiplicityConforms(processState, diagnostics, context);
		if (result || diagnostics != null) result &= validate_EveryDataValueConforms(processState, diagnostics, context);
		if (result || diagnostics != null) result &= validate_EveryReferenceIsContained(processState, diagnostics, context);
		if (result || diagnostics != null) result &= validate_EveryProxyResolves(processState, diagnostics, context);
		if (result || diagnostics != null) result &= validate_UniqueID(processState, diagnostics, context);
		if (result || diagnostics != null) result &= validate_EveryKeyUnique(processState, diagnostics, context);
		if (result || diagnostics != null) result &= validate_EveryMapEntryUnique(processState, diagnostics, context);
		if (result || diagnostics != null) result &= validateState_NoStateWithSameName(processState, diagnostics, context);
		if (result || diagnostics != null) result &= validateState_noSpecialCharacters(processState, diagnostics, context);
		if (result || diagnostics != null) result &= validateState_NameNull(processState, diagnostics, context);
		return result;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean validateFork(Fork fork, DiagnosticChain diagnostics, Map<Object, Object> context) {
		boolean result = validate_EveryMultiplicityConforms(fork, diagnostics, context);
		if (result || diagnostics != null) result &= validate_EveryDataValueConforms(fork, diagnostics, context);
		if (result || diagnostics != null) result &= validate_EveryReferenceIsContained(fork, diagnostics, context);
		if (result || diagnostics != null) result &= validate_EveryProxyResolves(fork, diagnostics, context);
		if (result || diagnostics != null) result &= validate_UniqueID(fork, diagnostics, context);
		if (result || diagnostics != null) result &= validate_EveryKeyUnique(fork, diagnostics, context);
		if (result || diagnostics != null) result &= validate_EveryMapEntryUnique(fork, diagnostics, context);
		if (result || diagnostics != null) result &= validateState_NoStateWithSameName(fork, diagnostics, context);
		if (result || diagnostics != null) result &= validateState_noSpecialCharacters(fork, diagnostics, context);
		if (result || diagnostics != null) result &= validateState_NameNull(fork, diagnostics, context);
		if (result || diagnostics != null) result &= validateFork_ForkMustBeFollowedByJoin(fork, diagnostics, context);
		return result;
	}

	/**
	 * Validates the ForkMustBeFollowedByJoin constraint of '<em>Fork</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean validateFork_ForkMustBeFollowedByJoin(Fork fork, DiagnosticChain diagnostics, Map<Object, Object> context) {
        if (fork_ForkMustBeFollowedByJoinInvOCL == null) {
			OCL.Helper helper = OCL_ENV.createOCLHelper();
			helper.setContext(WorkflowPackage.Literals.FORK);

			EAnnotation ocl = WorkflowPackage.Literals.FORK.getEAnnotation(OCL_ANNOTATION_SOURCE);
			String expr = ocl.getDetails().get("ForkMustBeFollowedByJoin");

			try {
				fork_ForkMustBeFollowedByJoinInvOCL = helper.createInvariant(expr);
			}
			catch (ParserException e) {
				throw new UnsupportedOperationException(e.getLocalizedMessage());
			}
		}

		Query<EClassifier, ?, ?> query = OCL_ENV.createQuery(fork_ForkMustBeFollowedByJoinInvOCL);

		if (!query.check(fork)) {
			if (diagnostics != null) {
				diagnostics.add
					(new BasicDiagnostic
						((doThrowError( WorkflowPackage.Literals.FORK.getEAnnotation("http://www.eclipse.org/emf/2002/Ecore"),"ForkMustBeFollowedByJoin")? Diagnostic.ERROR : Diagnostic.WARNING),
						 DIAGNOSTIC_SOURCE,
						 0,
						 EcorePlugin.INSTANCE.getString("_UI_GenericConstraint_diagnostic", new Object[] { "ForkMustBeFollowedByJoin", getObjectLabel(fork, context) }),
						 new Object[] { fork }));
			}
			return false;
		}
		return true;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean validateJoin(Join join, DiagnosticChain diagnostics, Map<Object, Object> context) {
		boolean result = validate_EveryMultiplicityConforms(join, diagnostics, context);
		if (result || diagnostics != null) result &= validate_EveryDataValueConforms(join, diagnostics, context);
		if (result || diagnostics != null) result &= validate_EveryReferenceIsContained(join, diagnostics, context);
		if (result || diagnostics != null) result &= validate_EveryProxyResolves(join, diagnostics, context);
		if (result || diagnostics != null) result &= validate_UniqueID(join, diagnostics, context);
		if (result || diagnostics != null) result &= validate_EveryKeyUnique(join, diagnostics, context);
		if (result || diagnostics != null) result &= validate_EveryMapEntryUnique(join, diagnostics, context);
		if (result || diagnostics != null) result &= validateState_NoStateWithSameName(join, diagnostics, context);
		if (result || diagnostics != null) result &= validateState_noSpecialCharacters(join, diagnostics, context);
		if (result || diagnostics != null) result &= validateState_NameNull(join, diagnostics, context);
		return result;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean validateDecision(Decision decision, DiagnosticChain diagnostics, Map<Object, Object> context) {
		boolean result = validate_EveryMultiplicityConforms(decision, diagnostics, context);
		if (result || diagnostics != null) result &= validate_EveryDataValueConforms(decision, diagnostics, context);
		if (result || diagnostics != null) result &= validate_EveryReferenceIsContained(decision, diagnostics, context);
		if (result || diagnostics != null) result &= validate_EveryProxyResolves(decision, diagnostics, context);
		if (result || diagnostics != null) result &= validate_UniqueID(decision, diagnostics, context);
		if (result || diagnostics != null) result &= validate_EveryKeyUnique(decision, diagnostics, context);
		if (result || diagnostics != null) result &= validate_EveryMapEntryUnique(decision, diagnostics, context);
		if (result || diagnostics != null) result &= validateState_NoStateWithSameName(decision, diagnostics, context);
		if (result || diagnostics != null) result &= validateState_noSpecialCharacters(decision, diagnostics, context);
		if (result || diagnostics != null) result &= validateState_NameNull(decision, diagnostics, context);
		if (result || diagnostics != null) result &= validateDecision_DecisionMustHaveOnlyOneTransitionWithCondition(decision, diagnostics, context);
		return result;
	}

	/**
	 * Validates the DecisionMustHaveOnlyOneTransitionWithCondition constraint of '<em>Decision</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean validateDecision_DecisionMustHaveOnlyOneTransitionWithCondition(Decision decision, DiagnosticChain diagnostics, Map<Object, Object> context) {
        if (decision_DecisionMustHaveOnlyOneTransitionWithConditionInvOCL == null) {
			OCL.Helper helper = OCL_ENV.createOCLHelper();
			helper.setContext(WorkflowPackage.Literals.DECISION);

			EAnnotation ocl = WorkflowPackage.Literals.DECISION.getEAnnotation(OCL_ANNOTATION_SOURCE);
			String expr = ocl.getDetails().get("DecisionMustHaveOnlyOneTransitionWithCondition");

			try {
				decision_DecisionMustHaveOnlyOneTransitionWithConditionInvOCL = helper.createInvariant(expr);
			}
			catch (ParserException e) {
				throw new UnsupportedOperationException(e.getLocalizedMessage());
			}
		}

		Query<EClassifier, ?, ?> query = OCL_ENV.createQuery(decision_DecisionMustHaveOnlyOneTransitionWithConditionInvOCL);

		if (!query.check(decision)) {
			if (diagnostics != null) {
				diagnostics.add
					(new BasicDiagnostic
						((doThrowError( WorkflowPackage.Literals.DECISION.getEAnnotation("http://www.eclipse.org/emf/2002/Ecore"),"DecisionMustHaveOnlyOneTransitionWithCondition")? Diagnostic.ERROR : Diagnostic.WARNING),
						 DIAGNOSTIC_SOURCE,
						 0,
						 EcorePlugin.INSTANCE.getString("_UI_GenericConstraint_diagnostic", new Object[] { "DecisionMustHaveOnlyOneTransitionWithCondition", getObjectLabel(decision, context) }),
						 new Object[] { decision }));
			}
			return false;
		}
		return true;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean validateEvent(Event event, DiagnosticChain diagnostics, Map<Object, Object> context) {
		return validate_EveryDefaultConstraint(event, diagnostics, context);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean validateAction(Action action, DiagnosticChain diagnostics, Map<Object, Object> context) {
		return validate_EveryDefaultConstraint(action, diagnostics, context);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean validateScript(Script script, DiagnosticChain diagnostics, Map<Object, Object> context) {
		return validate_EveryDefaultConstraint(script, diagnostics, context);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean validateTimer(Timer timer, DiagnosticChain diagnostics, Map<Object, Object> context) {
		return validate_EveryDefaultConstraint(timer, diagnostics, context);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean validateVariable(Variable variable, DiagnosticChain diagnostics, Map<Object, Object> context) {
		boolean result = validate_EveryMultiplicityConforms(variable, diagnostics, context);
		if (result || diagnostics != null) result &= validate_EveryDataValueConforms(variable, diagnostics, context);
		if (result || diagnostics != null) result &= validate_EveryReferenceIsContained(variable, diagnostics, context);
		if (result || diagnostics != null) result &= validate_EveryProxyResolves(variable, diagnostics, context);
		if (result || diagnostics != null) result &= validate_UniqueID(variable, diagnostics, context);
		if (result || diagnostics != null) result &= validate_EveryKeyUnique(variable, diagnostics, context);
		if (result || diagnostics != null) result &= validate_EveryMapEntryUnique(variable, diagnostics, context);
		if (result || diagnostics != null) result &= validateVariable_accessMatchesWithReadWriteRequired(variable, diagnostics, context);
		return result;
	}

	/**
	 * Validates the accessMatchesWithReadWriteRequired constraint of '<em>Variable</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean validateVariable_accessMatchesWithReadWriteRequired(Variable variable, DiagnosticChain diagnostics, Map<Object, Object> context) {
        if (variable_accessMatchesWithReadWriteRequiredInvOCL == null) {
			OCL.Helper helper = OCL_ENV.createOCLHelper();
			helper.setContext(WorkflowPackage.Literals.VARIABLE);

			EAnnotation ocl = WorkflowPackage.Literals.VARIABLE.getEAnnotation(OCL_ANNOTATION_SOURCE);
			String expr = ocl.getDetails().get("accessMatchesWithReadWriteRequired");

			try {
				variable_accessMatchesWithReadWriteRequiredInvOCL = helper.createInvariant(expr);
			}
			catch (ParserException e) {
				throw new UnsupportedOperationException(e.getLocalizedMessage());
			}
		}

		Query<EClassifier, ?, ?> query = OCL_ENV.createQuery(variable_accessMatchesWithReadWriteRequiredInvOCL);

		if (!query.check(variable)) {
			if (diagnostics != null) {
				diagnostics.add
					(new BasicDiagnostic
						((doThrowError( WorkflowPackage.Literals.VARIABLE.getEAnnotation("http://www.eclipse.org/emf/2002/Ecore"),"accessMatchesWithReadWriteRequired")? Diagnostic.ERROR : Diagnostic.WARNING),
						 DIAGNOSTIC_SOURCE,
						 0,
						 EcorePlugin.INSTANCE.getString("_UI_GenericConstraint_diagnostic", new Object[] { "accessMatchesWithReadWriteRequired", getObjectLabel(variable, context) }),
						 new Object[] { variable }));
			}
			return false;
		}
		return true;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean validateTransition(Transition transition, DiagnosticChain diagnostics, Map<Object, Object> context) {
		boolean result = validate_EveryMultiplicityConforms(transition, diagnostics, context);
		if (result || diagnostics != null) result &= validate_EveryDataValueConforms(transition, diagnostics, context);
		if (result || diagnostics != null) result &= validate_EveryReferenceIsContained(transition, diagnostics, context);
		if (result || diagnostics != null) result &= validate_EveryProxyResolves(transition, diagnostics, context);
		if (result || diagnostics != null) result &= validate_UniqueID(transition, diagnostics, context);
		if (result || diagnostics != null) result &= validate_EveryKeyUnique(transition, diagnostics, context);
		if (result || diagnostics != null) result &= validate_EveryMapEntryUnique(transition, diagnostics, context);
		if (result || diagnostics != null) result &= validateTransition_NoTransitionWithSameName(transition, diagnostics, context);
		if (result || diagnostics != null) result &= validateTransition_SourceAndTargetMustBeSet(transition, diagnostics, context);
		if (result || diagnostics != null) result &= validateTransition_noSpecialCharacters(transition, diagnostics, context);
		if (result || diagnostics != null) result &= validateTransition_titleMustNotBeNull(transition, diagnostics, context);
		return result;
	}

	/**
	 * Validates the NoTransitionWithSameName constraint of '<em>Transition</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean validateTransition_NoTransitionWithSameName(Transition transition, DiagnosticChain diagnostics, Map<Object, Object> context) {
        if (transition_NoTransitionWithSameNameInvOCL == null) {
			OCL.Helper helper = OCL_ENV.createOCLHelper();
			helper.setContext(WorkflowPackage.Literals.TRANSITION);

			EAnnotation ocl = WorkflowPackage.Literals.TRANSITION.getEAnnotation(OCL_ANNOTATION_SOURCE);
			String expr = ocl.getDetails().get("NoTransitionWithSameName");

			try {
				transition_NoTransitionWithSameNameInvOCL = helper.createInvariant(expr);
			}
			catch (ParserException e) {
				throw new UnsupportedOperationException(e.getLocalizedMessage());
			}
		}

		Query<EClassifier, ?, ?> query = OCL_ENV.createQuery(transition_NoTransitionWithSameNameInvOCL);

		if (!query.check(transition)) {
			if (diagnostics != null) {
				diagnostics.add
					(new BasicDiagnostic
						((doThrowError( WorkflowPackage.Literals.TRANSITION.getEAnnotation("http://www.eclipse.org/emf/2002/Ecore"),"NoTransitionWithSameName")? Diagnostic.ERROR : Diagnostic.WARNING),
						 DIAGNOSTIC_SOURCE,
						 0,
						 EcorePlugin.INSTANCE.getString("_UI_GenericConstraint_diagnostic", new Object[] { "NoTransitionWithSameName", getObjectLabel(transition, context) }),
						 new Object[] { transition }));
			}
			return false;
		}
		return true;
	}

	/**
	 * Validates the SourceAndTargetMustBeSet constraint of '<em>Transition</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean validateTransition_SourceAndTargetMustBeSet(Transition transition, DiagnosticChain diagnostics, Map<Object, Object> context) {
        if (transition_SourceAndTargetMustBeSetInvOCL == null) {
			OCL.Helper helper = OCL_ENV.createOCLHelper();
			helper.setContext(WorkflowPackage.Literals.TRANSITION);

			EAnnotation ocl = WorkflowPackage.Literals.TRANSITION.getEAnnotation(OCL_ANNOTATION_SOURCE);
			String expr = ocl.getDetails().get("SourceAndTargetMustBeSet");

			try {
				transition_SourceAndTargetMustBeSetInvOCL = helper.createInvariant(expr);
			}
			catch (ParserException e) {
				throw new UnsupportedOperationException(e.getLocalizedMessage());
			}
		}

		Query<EClassifier, ?, ?> query = OCL_ENV.createQuery(transition_SourceAndTargetMustBeSetInvOCL);

		if (!query.check(transition)) {
			if (diagnostics != null) {
				diagnostics.add
					(new BasicDiagnostic
						((doThrowError( WorkflowPackage.Literals.TRANSITION.getEAnnotation("http://www.eclipse.org/emf/2002/Ecore"),"SourceAndTargetMustBeSet")? Diagnostic.ERROR : Diagnostic.WARNING),
						 DIAGNOSTIC_SOURCE,
						 0,
						 EcorePlugin.INSTANCE.getString("_UI_GenericConstraint_diagnostic", new Object[] { "SourceAndTargetMustBeSet", getObjectLabel(transition, context) }),
						 new Object[] { transition }));
			}
			return false;
		}
		return true;
	}

	/**
	 * Validates the noSpecialCharacters constraint of '<em>Transition</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean validateTransition_noSpecialCharacters(Transition transition, DiagnosticChain diagnostics, Map<Object, Object> context) {
        if (transition_noSpecialCharactersInvOCL == null) {
			OCL.Helper helper = OCL_ENV.createOCLHelper();
			helper.setContext(WorkflowPackage.Literals.TRANSITION);

			EAnnotation ocl = WorkflowPackage.Literals.TRANSITION.getEAnnotation(OCL_ANNOTATION_SOURCE);
			String expr = ocl.getDetails().get("noSpecialCharacters");

			try {
				transition_noSpecialCharactersInvOCL = helper.createInvariant(expr);
			}
			catch (ParserException e) {
				throw new UnsupportedOperationException(e.getLocalizedMessage());
			}
		}

		Query<EClassifier, ?, ?> query = OCL_ENV.createQuery(transition_noSpecialCharactersInvOCL);

		if (!query.check(transition)) {
			if (diagnostics != null) {
				diagnostics.add
					(new BasicDiagnostic
						((doThrowError( WorkflowPackage.Literals.TRANSITION.getEAnnotation("http://www.eclipse.org/emf/2002/Ecore"),"noSpecialCharacters")? Diagnostic.ERROR : Diagnostic.WARNING),
						 DIAGNOSTIC_SOURCE,
						 0,
						 EcorePlugin.INSTANCE.getString("_UI_GenericConstraint_diagnostic", new Object[] { "noSpecialCharacters", getObjectLabel(transition, context) }),
						 new Object[] { transition }));
			}
			return false;
		}
		return true;
	}

	/**
	 * Validates the titleMustNotBeNull constraint of '<em>Transition</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean validateTransition_titleMustNotBeNull(Transition transition, DiagnosticChain diagnostics, Map<Object, Object> context) {
        if (transition_titleMustNotBeNullInvOCL == null) {
			OCL.Helper helper = OCL_ENV.createOCLHelper();
			helper.setContext(WorkflowPackage.Literals.TRANSITION);

			EAnnotation ocl = WorkflowPackage.Literals.TRANSITION.getEAnnotation(OCL_ANNOTATION_SOURCE);
			String expr = ocl.getDetails().get("titleMustNotBeNull");

			try {
				transition_titleMustNotBeNullInvOCL = helper.createInvariant(expr);
			}
			catch (ParserException e) {
				throw new UnsupportedOperationException(e.getLocalizedMessage());
			}
		}

		Query<EClassifier, ?, ?> query = OCL_ENV.createQuery(transition_titleMustNotBeNullInvOCL);

		if (!query.check(transition)) {
			if (diagnostics != null) {
				diagnostics.add
					(new BasicDiagnostic
						((doThrowError( WorkflowPackage.Literals.TRANSITION.getEAnnotation("http://www.eclipse.org/emf/2002/Ecore"),"titleMustNotBeNull")? Diagnostic.ERROR : Diagnostic.WARNING),
						 DIAGNOSTIC_SOURCE,
						 0,
						 EcorePlugin.INSTANCE.getString("_UI_GenericConstraint_diagnostic", new Object[] { "titleMustNotBeNull", getObjectLabel(transition, context) }),
						 new Object[] { transition }));
			}
			return false;
		}
		return true;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean validateState(State state, DiagnosticChain diagnostics, Map<Object, Object> context) {
		boolean result = validate_EveryMultiplicityConforms(state, diagnostics, context);
		if (result || diagnostics != null) result &= validate_EveryDataValueConforms(state, diagnostics, context);
		if (result || diagnostics != null) result &= validate_EveryReferenceIsContained(state, diagnostics, context);
		if (result || diagnostics != null) result &= validate_EveryProxyResolves(state, diagnostics, context);
		if (result || diagnostics != null) result &= validate_UniqueID(state, diagnostics, context);
		if (result || diagnostics != null) result &= validate_EveryKeyUnique(state, diagnostics, context);
		if (result || diagnostics != null) result &= validate_EveryMapEntryUnique(state, diagnostics, context);
		if (result || diagnostics != null) result &= validateState_NoStateWithSameName(state, diagnostics, context);
		if (result || diagnostics != null) result &= validateState_noSpecialCharacters(state, diagnostics, context);
		if (result || diagnostics != null) result &= validateState_NameNull(state, diagnostics, context);
		return result;
	}

	/**
	 * Validates the NoStateWithSameName constraint of '<em>State</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean validateState_NoStateWithSameName(State state, DiagnosticChain diagnostics, Map<Object, Object> context) {
        if (state_NoStateWithSameNameInvOCL == null) {
			OCL.Helper helper = OCL_ENV.createOCLHelper();
			helper.setContext(WorkflowPackage.Literals.STATE);

			EAnnotation ocl = WorkflowPackage.Literals.STATE.getEAnnotation(OCL_ANNOTATION_SOURCE);
			String expr = ocl.getDetails().get("NoStateWithSameName");

			try {
				state_NoStateWithSameNameInvOCL = helper.createInvariant(expr);
			}
			catch (ParserException e) {
				throw new UnsupportedOperationException(e.getLocalizedMessage());
			}
		}

		Query<EClassifier, ?, ?> query = OCL_ENV.createQuery(state_NoStateWithSameNameInvOCL);

		if (!query.check(state)) {
			if (diagnostics != null) {
				diagnostics.add
					(new BasicDiagnostic
						((doThrowError( WorkflowPackage.Literals.STATE.getEAnnotation("http://www.eclipse.org/emf/2002/Ecore"),"NoStateWithSameName")? Diagnostic.ERROR : Diagnostic.WARNING),
						 DIAGNOSTIC_SOURCE,
						 0,
						 EcorePlugin.INSTANCE.getString("_UI_GenericConstraint_diagnostic", new Object[] { "NoStateWithSameName", getObjectLabel(state, context) }),
						 new Object[] { state }));
			}
			return false;
		}
		return true;
	}

	/**
	 * Validates the noSpecialCharacters constraint of '<em>State</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean validateState_noSpecialCharacters(State state, DiagnosticChain diagnostics, Map<Object, Object> context) {
        if (state_noSpecialCharactersInvOCL == null) {
			OCL.Helper helper = OCL_ENV.createOCLHelper();
			helper.setContext(WorkflowPackage.Literals.STATE);

			EAnnotation ocl = WorkflowPackage.Literals.STATE.getEAnnotation(OCL_ANNOTATION_SOURCE);
			String expr = ocl.getDetails().get("noSpecialCharacters");

			try {
				state_noSpecialCharactersInvOCL = helper.createInvariant(expr);
			}
			catch (ParserException e) {
				throw new UnsupportedOperationException(e.getLocalizedMessage());
			}
		}

		Query<EClassifier, ?, ?> query = OCL_ENV.createQuery(state_noSpecialCharactersInvOCL);

		if (!query.check(state)) {
			if (diagnostics != null) {
				diagnostics.add
					(new BasicDiagnostic
						((doThrowError( WorkflowPackage.Literals.STATE.getEAnnotation("http://www.eclipse.org/emf/2002/Ecore"),"noSpecialCharacters")? Diagnostic.ERROR : Diagnostic.WARNING),
						 DIAGNOSTIC_SOURCE,
						 0,
						 EcorePlugin.INSTANCE.getString("_UI_GenericConstraint_diagnostic", new Object[] { "noSpecialCharacters", getObjectLabel(state, context) }),
						 new Object[] { state }));
			}
			return false;
		}
		return true;
	}

	/**
	 * Validates the NameNull constraint of '<em>State</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean validateState_NameNull(State state, DiagnosticChain diagnostics, Map<Object, Object> context) {
        if (state_NameNullInvOCL == null) {
			OCL.Helper helper = OCL_ENV.createOCLHelper();
			helper.setContext(WorkflowPackage.Literals.STATE);

			EAnnotation ocl = WorkflowPackage.Literals.STATE.getEAnnotation(OCL_ANNOTATION_SOURCE);
			String expr = ocl.getDetails().get("NameNull");

			try {
				state_NameNullInvOCL = helper.createInvariant(expr);
			}
			catch (ParserException e) {
				throw new UnsupportedOperationException(e.getLocalizedMessage());
			}
		}

		Query<EClassifier, ?, ?> query = OCL_ENV.createQuery(state_NameNullInvOCL);

		if (!query.check(state)) {
			if (diagnostics != null) {
				diagnostics.add
					(new BasicDiagnostic
						((doThrowError( WorkflowPackage.Literals.STATE.getEAnnotation("http://www.eclipse.org/emf/2002/Ecore"),"NameNull")? Diagnostic.ERROR : Diagnostic.WARNING),
						 DIAGNOSTIC_SOURCE,
						 0,
						 EcorePlugin.INSTANCE.getString("_UI_GenericConstraint_diagnostic", new Object[] { "NameNull", getObjectLabel(state, context) }),
						 new Object[] { state }));
			}
			return false;
		}
		return true;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean validateAttribute(Attribute attribute, DiagnosticChain diagnostics, Map<Object, Object> context) {
		boolean result = validate_EveryMultiplicityConforms(attribute, diagnostics, context);
		if (result || diagnostics != null) result &= validate_EveryDataValueConforms(attribute, diagnostics, context);
		if (result || diagnostics != null) result &= validate_EveryReferenceIsContained(attribute, diagnostics, context);
		if (result || diagnostics != null) result &= validate_EveryProxyResolves(attribute, diagnostics, context);
		if (result || diagnostics != null) result &= validate_UniqueID(attribute, diagnostics, context);
		if (result || diagnostics != null) result &= validate_EveryKeyUnique(attribute, diagnostics, context);
		if (result || diagnostics != null) result &= validate_EveryMapEntryUnique(attribute, diagnostics, context);
		if (result || diagnostics != null) result &= validateAttribute_UniqueNameForTaskAttribute(attribute, diagnostics, context);
		return result;
	}

	/**
	 * Validates the UniqueNameForTaskAttribute constraint of '<em>Attribute</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean validateAttribute_UniqueNameForTaskAttribute(Attribute attribute, DiagnosticChain diagnostics, Map<Object, Object> context) {
        if (attribute_UniqueNameForTaskAttributeInvOCL == null) {
			OCL.Helper helper = OCL_ENV.createOCLHelper();
			helper.setContext(WorkflowPackage.Literals.ATTRIBUTE);

			EAnnotation ocl = WorkflowPackage.Literals.ATTRIBUTE.getEAnnotation(OCL_ANNOTATION_SOURCE);
			String expr = ocl.getDetails().get("UniqueNameForTaskAttribute");

			try {
				attribute_UniqueNameForTaskAttributeInvOCL = helper.createInvariant(expr);
			}
			catch (ParserException e) {
				throw new UnsupportedOperationException(e.getLocalizedMessage());
			}
		}

		Query<EClassifier, ?, ?> query = OCL_ENV.createQuery(attribute_UniqueNameForTaskAttributeInvOCL);

		if (!query.check(attribute)) {
			if (diagnostics != null) {
				diagnostics.add
					(new BasicDiagnostic
						((doThrowError( WorkflowPackage.Literals.ATTRIBUTE.getEAnnotation("http://www.eclipse.org/emf/2002/Ecore"),"UniqueNameForTaskAttribute")? Diagnostic.ERROR : Diagnostic.WARNING),
						 DIAGNOSTIC_SOURCE,
						 0,
						 EcorePlugin.INSTANCE.getString("_UI_GenericConstraint_diagnostic", new Object[] { "UniqueNameForTaskAttribute", getObjectLabel(attribute, context) }),
						 new Object[] { attribute }));
			}
			return false;
		}
		return true;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean validateTransitionTask(TransitionTask transitionTask, DiagnosticChain diagnostics, Map<Object, Object> context) {
		boolean result = validate_EveryMultiplicityConforms(transitionTask, diagnostics, context);
		if (result || diagnostics != null) result &= validate_EveryDataValueConforms(transitionTask, diagnostics, context);
		if (result || diagnostics != null) result &= validate_EveryReferenceIsContained(transitionTask, diagnostics, context);
		if (result || diagnostics != null) result &= validate_EveryProxyResolves(transitionTask, diagnostics, context);
		if (result || diagnostics != null) result &= validate_UniqueID(transitionTask, diagnostics, context);
		if (result || diagnostics != null) result &= validate_EveryKeyUnique(transitionTask, diagnostics, context);
		if (result || diagnostics != null) result &= validate_EveryMapEntryUnique(transitionTask, diagnostics, context);
		if (result || diagnostics != null) result &= validateState_NoStateWithSameName(transitionTask, diagnostics, context);
		if (result || diagnostics != null) result &= validateState_noSpecialCharacters(transitionTask, diagnostics, context);
		if (result || diagnostics != null) result &= validateState_NameNull(transitionTask, diagnostics, context);
		return result;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean validateWfPackage(WfPackage wfPackage, DiagnosticChain diagnostics, Map<Object, Object> context) {
		return validate_EveryDefaultConstraint(wfPackage, diagnostics, context);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean validateBPMEventType(BPMEventType bpmEventType, DiagnosticChain diagnostics, Map<Object, Object> context) {
		return true;
	}

	protected boolean doThrowError(EAnnotation ecore, String ruleName) {
		String warningList = ecore.getDetails().get("warning");
		boolean throwError = true;
		if (warningList != null) {
			List<String> list = Arrays.asList(warningList.split(" "));
			if (list.contains(ruleName)) {
				throwError = false;
			}
		}
		return throwError;
	}

} //WorkflowValidator
