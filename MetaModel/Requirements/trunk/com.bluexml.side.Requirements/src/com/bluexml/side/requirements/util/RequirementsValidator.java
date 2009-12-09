/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package com.bluexml.side.requirements.util;

import com.bluexml.side.requirements.Agent;
import com.bluexml.side.requirements.AnnotableElement;
import com.bluexml.side.requirements.Annotation;
import com.bluexml.side.requirements.AnnotationStatus;
import com.bluexml.side.requirements.Attribute;
import com.bluexml.side.requirements.AttributeType;
import com.bluexml.side.requirements.BasicElement;
import com.bluexml.side.requirements.Entity;
import com.bluexml.side.requirements.Goal;
import com.bluexml.side.requirements.GoalStep;
import com.bluexml.side.requirements.ModelElement;
import com.bluexml.side.requirements.Organization;
import com.bluexml.side.requirements.PriorityLevel;
import com.bluexml.side.requirements.Privilege;
import com.bluexml.side.requirements.PrivilegeGroup;
import com.bluexml.side.requirements.PrivilegeNature;
import com.bluexml.side.requirements.RelationShip;
import com.bluexml.side.requirements.RequirementsDefinition;
import com.bluexml.side.requirements.RequirementsPackage;

import com.bluexml.side.util.metaModel.validate.OCLextension.KerblueOCL;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

import org.eclipse.emf.common.util.BasicDiagnostic;
import org.eclipse.emf.common.util.Diagnostic;
import org.eclipse.emf.common.util.DiagnosticChain;
import org.eclipse.emf.ecore.EAnnotation;
import org.eclipse.emf.ecore.EClassifier;
import org.eclipse.emf.common.util.ResourceLocator;

import org.eclipse.emf.ecore.EPackage;

import org.eclipse.emf.ecore.plugin.EcorePlugin;
import org.eclipse.emf.ecore.util.EObjectValidator;
import org.eclipse.ocl.ParserException;
import org.eclipse.ocl.Query;
import org.eclipse.ocl.ecore.Constraint;
import org.eclipse.ocl.ecore.OCL;

/**
 * <!-- begin-user-doc -->
 * The <b>Validator</b> for the model.
 * <!-- end-user-doc -->
 * @see com.bluexml.side.requirements.RequirementsPackage
 * @generated
 */
public class RequirementsValidator extends EObjectValidator {
	/**
	 * The cached model package
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public static final RequirementsValidator INSTANCE = new RequirementsValidator();

	/**
	 * A constant for the {@link org.eclipse.emf.common.util.Diagnostic#getSource() source} of diagnostic {@link org.eclipse.emf.common.util.Diagnostic#getCode() codes} from this package.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.eclipse.emf.common.util.Diagnostic#getSource()
	 * @see org.eclipse.emf.common.util.Diagnostic#getCode()
	 * @generated
	 */
	public static final String DIAGNOSTIC_SOURCE = "com.bluexml.side.requirements";

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
	 * The parsed OCL expression for the definition of the '<em>uniqueName</em>' invariant constraint.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private static Constraint goal_uniqueNameInvOCL;

	private static final String OCL_ANNOTATION_SOURCE = "http://www.bluexml.com/OCL";

	private static final OCL OCL_ENV = KerblueOCL.newInstance();

	/**
	 * Creates an instance of the switch.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public RequirementsValidator() {
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
	  return RequirementsPackage.eINSTANCE;
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
			case RequirementsPackage.MODEL_ELEMENT:
				return validateModelElement((ModelElement)value, diagnostics, context);
			case RequirementsPackage.BASIC_ELEMENT:
				return validateBasicElement((BasicElement)value, diagnostics, context);
			case RequirementsPackage.ENTITY:
				return validateEntity((Entity)value, diagnostics, context);
			case RequirementsPackage.RELATION_SHIP:
				return validateRelationShip((RelationShip)value, diagnostics, context);
			case RequirementsPackage.ATTRIBUTE:
				return validateAttribute((Attribute)value, diagnostics, context);
			case RequirementsPackage.ORGANIZATION:
				return validateOrganization((Organization)value, diagnostics, context);
			case RequirementsPackage.AGENT:
				return validateAgent((Agent)value, diagnostics, context);
			case RequirementsPackage.GOAL:
				return validateGoal((Goal)value, diagnostics, context);
			case RequirementsPackage.PRIVILEGE:
				return validatePrivilege((Privilege)value, diagnostics, context);
			case RequirementsPackage.REQUIREMENTS_DEFINITION:
				return validateRequirementsDefinition((RequirementsDefinition)value, diagnostics, context);
			case RequirementsPackage.PRIVILEGE_GROUP:
				return validatePrivilegeGroup((PrivilegeGroup)value, diagnostics, context);
			case RequirementsPackage.PROCESS:
				return validateProcess((com.bluexml.side.requirements.Process)value, diagnostics, context);
			case RequirementsPackage.GOAL_STEP:
				return validateGoalStep((GoalStep)value, diagnostics, context);
			case RequirementsPackage.ANNOTATION:
				return validateAnnotation((Annotation)value, diagnostics, context);
			case RequirementsPackage.ANNOTABLE_ELEMENT:
				return validateAnnotableElement((AnnotableElement)value, diagnostics, context);
			case RequirementsPackage.ATTRIBUTE_TYPE:
				return validateAttributeType((AttributeType)value, diagnostics, context);
			case RequirementsPackage.PRIORITY_LEVEL:
				return validatePriorityLevel((PriorityLevel)value, diagnostics, context);
			case RequirementsPackage.PRIVILEGE_NATURE:
				return validatePrivilegeNature((PrivilegeNature)value, diagnostics, context);
			case RequirementsPackage.ANNOTATION_STATUS:
				return validateAnnotationStatus((AnnotationStatus)value, diagnostics, context);
			default:
				return true;
		}
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean validateModelElement(ModelElement modelElement, DiagnosticChain diagnostics, Map<Object, Object> context) {
		return validate_EveryDefaultConstraint(modelElement, diagnostics, context);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean validateBasicElement(BasicElement basicElement, DiagnosticChain diagnostics, Map<Object, Object> context) {
		return validate_EveryDefaultConstraint(basicElement, diagnostics, context);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean validateEntity(Entity entity, DiagnosticChain diagnostics, Map<Object, Object> context) {
		return validate_EveryDefaultConstraint(entity, diagnostics, context);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean validateRelationShip(RelationShip relationShip, DiagnosticChain diagnostics, Map<Object, Object> context) {
		return validate_EveryDefaultConstraint(relationShip, diagnostics, context);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean validateAttribute(Attribute attribute, DiagnosticChain diagnostics, Map<Object, Object> context) {
		return validate_EveryDefaultConstraint(attribute, diagnostics, context);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean validateOrganization(Organization organization, DiagnosticChain diagnostics, Map<Object, Object> context) {
		return validate_EveryDefaultConstraint(organization, diagnostics, context);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean validateAgent(Agent agent, DiagnosticChain diagnostics, Map<Object, Object> context) {
		return validate_EveryDefaultConstraint(agent, diagnostics, context);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean validateGoal(Goal goal, DiagnosticChain diagnostics, Map<Object, Object> context) {
		boolean result = validate_EveryMultiplicityConforms(goal, diagnostics, context);
		if (result || diagnostics != null) result &= validate_EveryDataValueConforms(goal, diagnostics, context);
		if (result || diagnostics != null) result &= validate_EveryReferenceIsContained(goal, diagnostics, context);
		if (result || diagnostics != null) result &= validate_EveryProxyResolves(goal, diagnostics, context);
		if (result || diagnostics != null) result &= validate_UniqueID(goal, diagnostics, context);
		if (result || diagnostics != null) result &= validate_EveryKeyUnique(goal, diagnostics, context);
		if (result || diagnostics != null) result &= validate_EveryMapEntryUnique(goal, diagnostics, context);
		if (result || diagnostics != null) result &= validateGoal_uniqueName(goal, diagnostics, context);
		return result;
	}

	/**
	 * Validates the uniqueName constraint of '<em>Goal</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean validateGoal_uniqueName(Goal goal, DiagnosticChain diagnostics, Map<Object, Object> context) {
        if (goal_uniqueNameInvOCL == null) {
			OCL.Helper helper = OCL_ENV.createOCLHelper();
			helper.setContext(RequirementsPackage.Literals.GOAL);

			EAnnotation ocl = RequirementsPackage.Literals.GOAL.getEAnnotation(OCL_ANNOTATION_SOURCE);
			String expr = ocl.getDetails().get("uniqueName");

			try {
				goal_uniqueNameInvOCL = helper.createInvariant(expr);
			}
			catch (ParserException e) {
				throw new UnsupportedOperationException(e.getLocalizedMessage());
			}
		}

		Query<EClassifier, ?, ?> query = OCL_ENV.createQuery(goal_uniqueNameInvOCL);

		if (!query.check(goal)) {
			if (diagnostics != null) {
				diagnostics.add
					(new BasicDiagnostic
						((doThrowError( RequirementsPackage.Literals.GOAL.getEAnnotation("http://www.eclipse.org/emf/2002/Ecore"),"uniqueName")? Diagnostic.ERROR : Diagnostic.WARNING),
						 DIAGNOSTIC_SOURCE,
						 0,
						 EcorePlugin.INSTANCE.getString("_UI_GenericConstraint_diagnostic", new Object[] { "uniqueName", getObjectLabel(goal, context) }),
						 new Object[] { goal }));
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
	public boolean validatePrivilege(Privilege privilege, DiagnosticChain diagnostics, Map<Object, Object> context) {
		return validate_EveryDefaultConstraint(privilege, diagnostics, context);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean validateRequirementsDefinition(RequirementsDefinition requirementsDefinition, DiagnosticChain diagnostics, Map<Object, Object> context) {
		return validate_EveryDefaultConstraint(requirementsDefinition, diagnostics, context);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean validatePrivilegeGroup(PrivilegeGroup privilegeGroup, DiagnosticChain diagnostics, Map<Object, Object> context) {
		return validate_EveryDefaultConstraint(privilegeGroup, diagnostics, context);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean validateProcess(com.bluexml.side.requirements.Process process, DiagnosticChain diagnostics, Map<Object, Object> context) {
		return validate_EveryDefaultConstraint(process, diagnostics, context);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean validateGoalStep(GoalStep goalStep, DiagnosticChain diagnostics, Map<Object, Object> context) {
		return validate_EveryDefaultConstraint(goalStep, diagnostics, context);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean validateAnnotation(Annotation annotation, DiagnosticChain diagnostics, Map<Object, Object> context) {
		return validate_EveryDefaultConstraint(annotation, diagnostics, context);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean validateAnnotableElement(AnnotableElement annotableElement, DiagnosticChain diagnostics, Map<Object, Object> context) {
		return validate_EveryDefaultConstraint(annotableElement, diagnostics, context);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean validateAttributeType(AttributeType attributeType, DiagnosticChain diagnostics, Map<Object, Object> context) {
		return true;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean validatePriorityLevel(PriorityLevel priorityLevel, DiagnosticChain diagnostics, Map<Object, Object> context) {
		return true;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean validatePrivilegeNature(PrivilegeNature privilegeNature, DiagnosticChain diagnostics, Map<Object, Object> context) {
		return true;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean validateAnnotationStatus(AnnotationStatus annotationStatus, DiagnosticChain diagnostics, Map<Object, Object> context) {
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

} //RequirementsValidator
