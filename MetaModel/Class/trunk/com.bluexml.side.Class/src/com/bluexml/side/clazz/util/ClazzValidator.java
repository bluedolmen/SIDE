/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package com.bluexml.side.clazz.util;

import com.bluexml.side.clazz.*;
import java.util.Arrays;
import java.util.List;
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

import com.bluexml.side.clazz.AbstractClass;
import com.bluexml.side.clazz.Aspect;
import com.bluexml.side.clazz.Association;
import com.bluexml.side.clazz.AssociationEnd;
import com.bluexml.side.clazz.AssociationType;
import com.bluexml.side.clazz.Attribute;
import com.bluexml.side.clazz.ClassComment;
import com.bluexml.side.clazz.ClassModelElement;
import com.bluexml.side.clazz.ClassPackage;
import com.bluexml.side.clazz.Clazz;
import com.bluexml.side.clazz.ClazzPackage;
import com.bluexml.side.clazz.Enumeration;
import com.bluexml.side.clazz.EnumerationLiteral;
import com.bluexml.side.clazz.TitledNamedClassModelElement;
import com.bluexml.side.util.metaModel.validate.OCLextension.KerblueOCL;

/**
 * <!-- begin-user-doc -->
 * The <b>Validator</b> for the model.
 * <!-- end-user-doc -->
 * @see com.bluexml.side.clazz.ClazzPackage
 * @generated
 */
public class ClazzValidator extends EObjectValidator {
	/**
	 * The cached model package
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public static final ClazzValidator INSTANCE = new ClazzValidator();

	/**
	 * A constant for the {@link org.eclipse.emf.common.util.Diagnostic#getSource() source} of diagnostic {@link org.eclipse.emf.common.util.Diagnostic#getCode() codes} from this package.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.eclipse.emf.common.util.Diagnostic#getSource()
	 * @see org.eclipse.emf.common.util.Diagnostic#getCode()
	 * @generated
	 */
	public static final String DIAGNOSTIC_SOURCE = "com.bluexml.side.clazz";

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
	private static Constraint classPackage_PackageNameNullInvOCL;

	/**
	 * The parsed OCL expression for the definition of the '<em>InheritanceCycle</em>' invariant constraint.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private static Constraint clazz_InheritanceCycleInvOCL;
	/**
	 * The parsed OCL expression for the definition of the '<em>reflexiveAssociationMustHaveRole</em>' invariant constraint.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private static Constraint association_reflexiveAssociationMustHaveRoleInvOCL;

	/**
	 * The parsed OCL expression for the definition of the '<em>MinAndMaxTarget</em>' invariant constraint.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private static Constraint association_MinAndMaxTargetInvOCL;
	/**
	 * The parsed OCL expression for the definition of the '<em>MinAndMaxSource</em>' invariant constraint.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private static Constraint association_MinAndMaxSourceInvOCL;
	/**
	 * The parsed OCL expression for the definition of the '<em>NameNull</em>' invariant constraint.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private static Constraint association_NameNullInvOCL;
	/**
	 * The parsed OCL expression for the definition of the '<em>SourceNull</em>' invariant constraint.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private static Constraint association_SourceNullInvOCL;
	/**
	 * The parsed OCL expression for the definition of the '<em>TargetNull</em>' invariant constraint.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private static Constraint association_TargetNullInvOCL;
	/**
	 * The parsed OCL expression for the definition of the '<em>AtLeastOneNavigableEdge</em>' invariant constraint.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private static Constraint association_AtLeastOneNavigableEdgeInvOCL;
	/**
	 * The parsed OCL expression for the definition of the '<em>ClassCantBeReferencedbyTwoSameNameAssociation</em>' invariant constraint.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private static Constraint association_ClassCantBeReferencedbyTwoSameNameAssociationInvOCL;
	/**
	 * The parsed OCL expression for the definition of the '<em>IfAggregationOrCompositionThenUnidirectionalAssociation</em>' invariant constraint.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private static Constraint association_IfAggregationOrCompositionThenUnidirectionalAssociationInvOCL;
	/**
	 * The parsed OCL expression for the definition of the '<em>NameNull</em>' invariant constraint.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private static Constraint attribute_NameNullInvOCL;

	/**
	 * The parsed OCL expression for the definition of the '<em>noSpecialCharacters</em>' invariant constraint.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private static Constraint attribute_noSpecialCharactersInvOCL;

	/**
	 * The parsed OCL expression for the definition of the '<em>enumDynamicAreNotAvailable</em>' invariant constraint.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private static Constraint enumeration_enumDynamicAreNotAvailableInvOCL;

	/**
	 * The parsed OCL expression for the definition of the '<em>TwoModelElementWithSameName</em>' invariant constraint.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private static Constraint abstractClass_TwoModelElementWithSameNameInvOCL;
	/**
	 * The parsed OCL expression for the definition of the '<em>NameNull</em>' invariant constraint.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private static Constraint abstractClass_NameNullInvOCL;
	/**
	 * The parsed OCL expression for the definition of the '<em>noSpecialCharacters</em>' invariant constraint.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private static Constraint abstractClass_noSpecialCharactersInvOCL;
	/**
	 * The parsed OCL expression for the definition of the '<em>TwoAttributesSameName</em>' invariant constraint.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private static Constraint abstractClass_TwoAttributesSameNameInvOCL;
	private static final String OCL_ANNOTATION_SOURCE = "http://www.bluexml.com/OCL";

	private static final OCL OCL_ENV = KerblueOCL.newInstance();
	/**
	 * Creates an instance of the switch.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public ClazzValidator() {
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
	  return ClazzPackage.eINSTANCE;
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
			case ClazzPackage.CLASS_MODEL_ELEMENT:
				return validateClassModelElement((ClassModelElement)value, diagnostics, context);
			case ClazzPackage.CLASS_PACKAGE:
				return validateClassPackage((ClassPackage)value, diagnostics, context);
			case ClazzPackage.CLAZZ:
				return validateClazz((Clazz)value, diagnostics, context);
			case ClazzPackage.ASSOCIATION:
				return validateAssociation((Association)value, diagnostics, context);
			case ClazzPackage.ATTRIBUTE:
				return validateAttribute((Attribute)value, diagnostics, context);
			case ClazzPackage.ENUMERATION:
				return validateEnumeration((Enumeration)value, diagnostics, context);
			case ClazzPackage.ENUMERATION_LITERAL:
				return validateEnumerationLiteral((EnumerationLiteral)value, diagnostics, context);
			case ClazzPackage.ASPECT:
				return validateAspect((Aspect)value, diagnostics, context);
			case ClazzPackage.ABSTRACT_CLASS:
				return validateAbstractClass((AbstractClass)value, diagnostics, context);
			case ClazzPackage.TITLED_NAMED_CLASS_MODEL_ELEMENT:
				return validateTitledNamedClassModelElement((TitledNamedClassModelElement)value, diagnostics, context);
			case ClazzPackage.CLASS_COMMENT:
				return validateClassComment((ClassComment)value, diagnostics, context);
			case ClazzPackage.ASSOCIATION_END:
				return validateAssociationEnd((AssociationEnd)value, diagnostics, context);
			case ClazzPackage.MODEL:
				return validateModel((Model)value, diagnostics, context);
			case ClazzPackage.ASSOCIATION_TYPE:
				return validateAssociationType((AssociationType)value, diagnostics, context);
			default:
				return true;
		}
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean validateClassModelElement(ClassModelElement classModelElement, DiagnosticChain diagnostics, Map<Object, Object> context) {
		return validate_EveryDefaultConstraint(classModelElement, diagnostics, context);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean validateClassPackage(ClassPackage classPackage, DiagnosticChain diagnostics, Map<Object, Object> context) {
		boolean result = validate_EveryMultiplicityConforms(classPackage, diagnostics, context);
		if (result || diagnostics != null) result &= validate_EveryDataValueConforms(classPackage, diagnostics, context);
		if (result || diagnostics != null) result &= validate_EveryReferenceIsContained(classPackage, diagnostics, context);
		if (result || diagnostics != null) result &= validate_EveryProxyResolves(classPackage, diagnostics, context);
		if (result || diagnostics != null) result &= validate_UniqueID(classPackage, diagnostics, context);
		if (result || diagnostics != null) result &= validate_EveryKeyUnique(classPackage, diagnostics, context);
		if (result || diagnostics != null) result &= validate_EveryMapEntryUnique(classPackage, diagnostics, context);
		if (result || diagnostics != null) result &= validateClassPackage_PackageNameNull(classPackage, diagnostics, context);
		return result;
	}

	/**
	 * Validates the PackageNameNull constraint of '<em>Class Package</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean validateClassPackage_PackageNameNull(ClassPackage classPackage, DiagnosticChain diagnostics, Map<Object, Object> context) {
        if (classPackage_PackageNameNullInvOCL == null) {
			OCL.Helper helper = OCL_ENV.createOCLHelper();
			helper.setContext(ClazzPackage.Literals.CLASS_PACKAGE);

			EAnnotation ocl = ClazzPackage.Literals.CLASS_PACKAGE.getEAnnotation(OCL_ANNOTATION_SOURCE);
			String expr = ocl.getDetails().get("PackageNameNull");

			try {
				classPackage_PackageNameNullInvOCL = helper.createInvariant(expr);
			}
			catch (ParserException e) {
				throw new UnsupportedOperationException(e.getLocalizedMessage());
			}
		}

		Query<EClassifier, ?, ?> query = OCL_ENV.createQuery(classPackage_PackageNameNullInvOCL);

		if (!query.check(classPackage)) {
			if (diagnostics != null) {
				diagnostics.add
					(new BasicDiagnostic
						((doThrowError( ClazzPackage.Literals.CLASS_PACKAGE.getEAnnotation("http://www.eclipse.org/emf/2002/Ecore"),"PackageNameNull")? Diagnostic.ERROR : Diagnostic.WARNING),
						 DIAGNOSTIC_SOURCE,
						 0,
						 EcorePlugin.INSTANCE.getString("_UI_GenericConstraint_diagnostic", new Object[] { "PackageNameNull", getObjectLabel(classPackage, context) }),
						 new Object[] { classPackage }));
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
	public boolean validateClazz(Clazz clazz, DiagnosticChain diagnostics, Map<Object, Object> context) {
		boolean result = validate_EveryMultiplicityConforms(clazz, diagnostics, context);
		if (result || diagnostics != null) result &= validate_EveryDataValueConforms(clazz, diagnostics, context);
		if (result || diagnostics != null) result &= validate_EveryReferenceIsContained(clazz, diagnostics, context);
		if (result || diagnostics != null) result &= validate_EveryProxyResolves(clazz, diagnostics, context);
		if (result || diagnostics != null) result &= validate_UniqueID(clazz, diagnostics, context);
		if (result || diagnostics != null) result &= validate_EveryKeyUnique(clazz, diagnostics, context);
		if (result || diagnostics != null) result &= validate_EveryMapEntryUnique(clazz, diagnostics, context);
		if (result || diagnostics != null) result &= validateAbstractClass_TwoModelElementWithSameName(clazz, diagnostics, context);
		if (result || diagnostics != null) result &= validateAbstractClass_NameNull(clazz, diagnostics, context);
		if (result || diagnostics != null) result &= validateAbstractClass_noSpecialCharacters(clazz, diagnostics, context);
		if (result || diagnostics != null) result &= validateAbstractClass_TwoAttributesSameName(clazz, diagnostics, context);
		if (result || diagnostics != null) result &= validateClazz_ClassWithTwoAttributesSameName(clazz, diagnostics, context);
		if (result || diagnostics != null) result &= validateClazz_InheritanceCycle(clazz, diagnostics, context);
		return result;
	}

	/**
	 * Validates the ClassWithTwoAttributesSameName constraint of '<em>Clazz</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean validateClazz_ClassWithTwoAttributesSameName(Clazz clazz, DiagnosticChain diagnostics, Map<Object, Object> context) {
		// TODO implement the constraint
		// -> specify the condition that violates the constraint
		// -> verify the diagnostic details, including severity, code, and message
		// Ensure that you remove @generated or mark it @generated NOT
		if (false) {
			if (diagnostics != null) {
				diagnostics.add
					(new BasicDiagnostic
						(Diagnostic.ERROR,
						 DIAGNOSTIC_SOURCE,
						 0,
						 EcorePlugin.INSTANCE.getString("_UI_GenericConstraint_diagnostic", new Object[] { "ClassWithTwoAttributesSameName", getObjectLabel(clazz, context) }),
						 new Object[] { clazz }));
			}
			return false;
		}
		return true;
	}

	/**
	 * Validates the InheritanceCycle constraint of '<em>Clazz</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean validateClazz_InheritanceCycle(Clazz clazz, DiagnosticChain diagnostics, Map<Object, Object> context) {
        if (clazz_InheritanceCycleInvOCL == null) {
			OCL.Helper helper = OCL_ENV.createOCLHelper();
			helper.setContext(ClazzPackage.Literals.CLAZZ);

			EAnnotation ocl = ClazzPackage.Literals.CLAZZ.getEAnnotation(OCL_ANNOTATION_SOURCE);
			String expr = ocl.getDetails().get("InheritanceCycle");

			try {
				clazz_InheritanceCycleInvOCL = helper.createInvariant(expr);
			}
			catch (ParserException e) {
				throw new UnsupportedOperationException(e.getLocalizedMessage());
			}
		}

		Query<EClassifier, ?, ?> query = OCL_ENV.createQuery(clazz_InheritanceCycleInvOCL);

		if (!query.check(clazz)) {
			if (diagnostics != null) {
				diagnostics.add
					(new BasicDiagnostic
						((doThrowError( ClazzPackage.Literals.CLAZZ.getEAnnotation("http://www.eclipse.org/emf/2002/Ecore"),"InheritanceCycle")? Diagnostic.ERROR : Diagnostic.WARNING),
						 DIAGNOSTIC_SOURCE,
						 0,
						 EcorePlugin.INSTANCE.getString("_UI_GenericConstraint_diagnostic", new Object[] { "InheritanceCycle", getObjectLabel(clazz, context) }),
						 new Object[] { clazz }));
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
	public boolean validateAssociation(Association association, DiagnosticChain diagnostics, Map<Object, Object> context) {
		boolean result = validate_EveryMultiplicityConforms(association, diagnostics, context);
		if (result || diagnostics != null) result &= validate_EveryDataValueConforms(association, diagnostics, context);
		if (result || diagnostics != null) result &= validate_EveryReferenceIsContained(association, diagnostics, context);
		if (result || diagnostics != null) result &= validate_EveryProxyResolves(association, diagnostics, context);
		if (result || diagnostics != null) result &= validate_UniqueID(association, diagnostics, context);
		if (result || diagnostics != null) result &= validate_EveryKeyUnique(association, diagnostics, context);
		if (result || diagnostics != null) result &= validate_EveryMapEntryUnique(association, diagnostics, context);
		if (result || diagnostics != null) result &= validateAssociation_reflexiveAssociationMustHaveRole(association, diagnostics, context);
		if (result || diagnostics != null) result &= validateAssociation_MinAndMaxTarget(association, diagnostics, context);
		if (result || diagnostics != null) result &= validateAssociation_MinAndMaxSource(association, diagnostics, context);
		if (result || diagnostics != null) result &= validateAssociation_NameNull(association, diagnostics, context);
		if (result || diagnostics != null) result &= validateAssociation_SourceNull(association, diagnostics, context);
		if (result || diagnostics != null) result &= validateAssociation_TargetNull(association, diagnostics, context);
		if (result || diagnostics != null) result &= validateAssociation_AtLeastOneNavigableEdge(association, diagnostics, context);
		if (result || diagnostics != null) result &= validateAssociation_ClassCantBeReferencedbyTwoSameNameAssociation(association, diagnostics, context);
		if (result || diagnostics != null) result &= validateAssociation_IfAggregationOrCompositionThenUnidirectionalAssociation(association, diagnostics, context);
		if (result || diagnostics != null) result &= validateAssociation_doubleNavigable(association, diagnostics, context);
		if (result || diagnostics != null) result &= validateAssociation_noSpecialChracters(association, diagnostics, context);
		return result;
	}

	/**
	 * Validates the reflexiveAssociationMustHaveRole constraint of '<em>Association</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean validateAssociation_reflexiveAssociationMustHaveRole(Association association, DiagnosticChain diagnostics, Map<Object, Object> context) {
        if (association_reflexiveAssociationMustHaveRoleInvOCL == null) {
			OCL.Helper helper = OCL_ENV.createOCLHelper();
			helper.setContext(ClazzPackage.Literals.ASSOCIATION);

			EAnnotation ocl = ClazzPackage.Literals.ASSOCIATION.getEAnnotation(OCL_ANNOTATION_SOURCE);
			String expr = ocl.getDetails().get("reflexiveAssociationMustHaveRole");

			try {
				association_reflexiveAssociationMustHaveRoleInvOCL = helper.createInvariant(expr);
			}
			catch (ParserException e) {
				throw new UnsupportedOperationException(e.getLocalizedMessage());
			}
		}

		Query<EClassifier, ?, ?> query = OCL_ENV.createQuery(association_reflexiveAssociationMustHaveRoleInvOCL);

		if (!query.check(association)) {
			if (diagnostics != null) {
				diagnostics.add
					(new BasicDiagnostic
						((doThrowError( ClazzPackage.Literals.ASSOCIATION.getEAnnotation("http://www.eclipse.org/emf/2002/Ecore"),"reflexiveAssociationMustHaveRole")? Diagnostic.ERROR : Diagnostic.WARNING),
						 DIAGNOSTIC_SOURCE,
						 0,
						 EcorePlugin.INSTANCE.getString("_UI_GenericConstraint_diagnostic", new Object[] { "reflexiveAssociationMustHaveRole", getObjectLabel(association, context) }),
						 new Object[] { association }));
			}
			return false;
		}
		return true;
	}

	/**
	 * Validates the MinAndMaxTarget constraint of '<em>Association</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean validateAssociation_MinAndMaxTarget(Association association, DiagnosticChain diagnostics, Map<Object, Object> context) {
        if (association_MinAndMaxTargetInvOCL == null) {
			OCL.Helper helper = OCL_ENV.createOCLHelper();
			helper.setContext(ClazzPackage.Literals.ASSOCIATION);

			EAnnotation ocl = ClazzPackage.Literals.ASSOCIATION.getEAnnotation(OCL_ANNOTATION_SOURCE);
			String expr = ocl.getDetails().get("MinAndMaxTarget");

			try {
				association_MinAndMaxTargetInvOCL = helper.createInvariant(expr);
			}
			catch (ParserException e) {
				throw new UnsupportedOperationException(e.getLocalizedMessage());
			}
		}

		Query<EClassifier, ?, ?> query = OCL_ENV.createQuery(association_MinAndMaxTargetInvOCL);

		if (!query.check(association)) {
			if (diagnostics != null) {
				diagnostics.add
					(new BasicDiagnostic
						((doThrowError( ClazzPackage.Literals.ASSOCIATION.getEAnnotation("http://www.eclipse.org/emf/2002/Ecore"),"MinAndMaxTarget")? Diagnostic.ERROR : Diagnostic.WARNING),
						 DIAGNOSTIC_SOURCE,
						 0,
						 EcorePlugin.INSTANCE.getString("_UI_GenericConstraint_diagnostic", new Object[] { "MinAndMaxTarget", getObjectLabel(association, context) }),
						 new Object[] { association }));
			}
			return false;
		}
		return true;
	}

	/**
	 * Validates the MinAndMaxSource constraint of '<em>Association</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean validateAssociation_MinAndMaxSource(Association association, DiagnosticChain diagnostics, Map<Object, Object> context) {
        if (association_MinAndMaxSourceInvOCL == null) {
			OCL.Helper helper = OCL_ENV.createOCLHelper();
			helper.setContext(ClazzPackage.Literals.ASSOCIATION);

			EAnnotation ocl = ClazzPackage.Literals.ASSOCIATION.getEAnnotation(OCL_ANNOTATION_SOURCE);
			String expr = ocl.getDetails().get("MinAndMaxSource");

			try {
				association_MinAndMaxSourceInvOCL = helper.createInvariant(expr);
			}
			catch (ParserException e) {
				throw new UnsupportedOperationException(e.getLocalizedMessage());
			}
		}

		Query<EClassifier, ?, ?> query = OCL_ENV.createQuery(association_MinAndMaxSourceInvOCL);

		if (!query.check(association)) {
			if (diagnostics != null) {
				diagnostics.add
					(new BasicDiagnostic
						((doThrowError( ClazzPackage.Literals.ASSOCIATION.getEAnnotation("http://www.eclipse.org/emf/2002/Ecore"),"MinAndMaxSource")? Diagnostic.ERROR : Diagnostic.WARNING),
						 DIAGNOSTIC_SOURCE,
						 0,
						 EcorePlugin.INSTANCE.getString("_UI_GenericConstraint_diagnostic", new Object[] { "MinAndMaxSource", getObjectLabel(association, context) }),
						 new Object[] { association }));
			}
			return false;
		}
		return true;
	}

	/**
	 * Validates the NameNull constraint of '<em>Association</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean validateAssociation_NameNull(Association association, DiagnosticChain diagnostics, Map<Object, Object> context) {
        if (association_NameNullInvOCL == null) {
			OCL.Helper helper = OCL_ENV.createOCLHelper();
			helper.setContext(ClazzPackage.Literals.ASSOCIATION);

			EAnnotation ocl = ClazzPackage.Literals.ASSOCIATION.getEAnnotation(OCL_ANNOTATION_SOURCE);
			String expr = ocl.getDetails().get("NameNull");

			try {
				association_NameNullInvOCL = helper.createInvariant(expr);
			}
			catch (ParserException e) {
				throw new UnsupportedOperationException(e.getLocalizedMessage());
			}
		}

		Query<EClassifier, ?, ?> query = OCL_ENV.createQuery(association_NameNullInvOCL);

		if (!query.check(association)) {
			if (diagnostics != null) {
				diagnostics.add
					(new BasicDiagnostic
						((doThrowError( ClazzPackage.Literals.ASSOCIATION.getEAnnotation("http://www.eclipse.org/emf/2002/Ecore"),"NameNull")? Diagnostic.ERROR : Diagnostic.WARNING),
						 DIAGNOSTIC_SOURCE,
						 0,
						 EcorePlugin.INSTANCE.getString("_UI_GenericConstraint_diagnostic", new Object[] { "NameNull", getObjectLabel(association, context) }),
						 new Object[] { association }));
			}
			return false;
		}
		return true;
	}

	/**
	 * Validates the SourceNull constraint of '<em>Association</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean validateAssociation_SourceNull(Association association, DiagnosticChain diagnostics, Map<Object, Object> context) {
        if (association_SourceNullInvOCL == null) {
			OCL.Helper helper = OCL_ENV.createOCLHelper();
			helper.setContext(ClazzPackage.Literals.ASSOCIATION);

			EAnnotation ocl = ClazzPackage.Literals.ASSOCIATION.getEAnnotation(OCL_ANNOTATION_SOURCE);
			String expr = ocl.getDetails().get("SourceNull");

			try {
				association_SourceNullInvOCL = helper.createInvariant(expr);
			}
			catch (ParserException e) {
				throw new UnsupportedOperationException(e.getLocalizedMessage());
			}
		}

		Query<EClassifier, ?, ?> query = OCL_ENV.createQuery(association_SourceNullInvOCL);

		if (!query.check(association)) {
			if (diagnostics != null) {
				diagnostics.add
					(new BasicDiagnostic
						((doThrowError( ClazzPackage.Literals.ASSOCIATION.getEAnnotation("http://www.eclipse.org/emf/2002/Ecore"),"SourceNull")? Diagnostic.ERROR : Diagnostic.WARNING),
						 DIAGNOSTIC_SOURCE,
						 0,
						 EcorePlugin.INSTANCE.getString("_UI_GenericConstraint_diagnostic", new Object[] { "SourceNull", getObjectLabel(association, context) }),
						 new Object[] { association }));
			}
			return false;
		}
		return true;
	}

	/**
	 * Validates the TargetNull constraint of '<em>Association</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean validateAssociation_TargetNull(Association association, DiagnosticChain diagnostics, Map<Object, Object> context) {
        if (association_TargetNullInvOCL == null) {
			OCL.Helper helper = OCL_ENV.createOCLHelper();
			helper.setContext(ClazzPackage.Literals.ASSOCIATION);

			EAnnotation ocl = ClazzPackage.Literals.ASSOCIATION.getEAnnotation(OCL_ANNOTATION_SOURCE);
			String expr = ocl.getDetails().get("TargetNull");

			try {
				association_TargetNullInvOCL = helper.createInvariant(expr);
			}
			catch (ParserException e) {
				throw new UnsupportedOperationException(e.getLocalizedMessage());
			}
		}

		Query<EClassifier, ?, ?> query = OCL_ENV.createQuery(association_TargetNullInvOCL);

		if (!query.check(association)) {
			if (diagnostics != null) {
				diagnostics.add
					(new BasicDiagnostic
						((doThrowError( ClazzPackage.Literals.ASSOCIATION.getEAnnotation("http://www.eclipse.org/emf/2002/Ecore"),"TargetNull")? Diagnostic.ERROR : Diagnostic.WARNING),
						 DIAGNOSTIC_SOURCE,
						 0,
						 EcorePlugin.INSTANCE.getString("_UI_GenericConstraint_diagnostic", new Object[] { "TargetNull", getObjectLabel(association, context) }),
						 new Object[] { association }));
			}
			return false;
		}
		return true;
	}

	/**
	 * Validates the AtLeastOneNavigableEdge constraint of '<em>Association</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean validateAssociation_AtLeastOneNavigableEdge(Association association, DiagnosticChain diagnostics, Map<Object, Object> context) {
        if (association_AtLeastOneNavigableEdgeInvOCL == null) {
			OCL.Helper helper = OCL_ENV.createOCLHelper();
			helper.setContext(ClazzPackage.Literals.ASSOCIATION);

			EAnnotation ocl = ClazzPackage.Literals.ASSOCIATION.getEAnnotation(OCL_ANNOTATION_SOURCE);
			String expr = ocl.getDetails().get("AtLeastOneNavigableEdge");

			try {
				association_AtLeastOneNavigableEdgeInvOCL = helper.createInvariant(expr);
			}
			catch (ParserException e) {
				throw new UnsupportedOperationException(e.getLocalizedMessage());
			}
		}

		Query<EClassifier, ?, ?> query = OCL_ENV.createQuery(association_AtLeastOneNavigableEdgeInvOCL);

		if (!query.check(association)) {
			if (diagnostics != null) {
				diagnostics.add
					(new BasicDiagnostic
						((doThrowError( ClazzPackage.Literals.ASSOCIATION.getEAnnotation("http://www.eclipse.org/emf/2002/Ecore"),"AtLeastOneNavigableEdge")? Diagnostic.ERROR : Diagnostic.WARNING),
						 DIAGNOSTIC_SOURCE,
						 0,
						 EcorePlugin.INSTANCE.getString("_UI_GenericConstraint_diagnostic", new Object[] { "AtLeastOneNavigableEdge", getObjectLabel(association, context) }),
						 new Object[] { association }));
			}
			return false;
		}
		return true;
	}

	/**
	 * Validates the ClassCantBeReferencedbyTwoSameNameAssociation constraint of '<em>Association</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean validateAssociation_ClassCantBeReferencedbyTwoSameNameAssociation(Association association, DiagnosticChain diagnostics, Map<Object, Object> context) {
        if (association_ClassCantBeReferencedbyTwoSameNameAssociationInvOCL == null) {
			OCL.Helper helper = OCL_ENV.createOCLHelper();
			helper.setContext(ClazzPackage.Literals.ASSOCIATION);

			EAnnotation ocl = ClazzPackage.Literals.ASSOCIATION.getEAnnotation(OCL_ANNOTATION_SOURCE);
			String expr = ocl.getDetails().get("ClassCantBeReferencedbyTwoSameNameAssociation");

			try {
				association_ClassCantBeReferencedbyTwoSameNameAssociationInvOCL = helper.createInvariant(expr);
			}
			catch (ParserException e) {
				throw new UnsupportedOperationException(e.getLocalizedMessage());
			}
		}

		Query<EClassifier, ?, ?> query = OCL_ENV.createQuery(association_ClassCantBeReferencedbyTwoSameNameAssociationInvOCL);

		if (!query.check(association)) {
			if (diagnostics != null) {
				diagnostics.add
					(new BasicDiagnostic
						((doThrowError( ClazzPackage.Literals.ASSOCIATION.getEAnnotation("http://www.eclipse.org/emf/2002/Ecore"),"ClassCantBeReferencedbyTwoSameNameAssociation")? Diagnostic.ERROR : Diagnostic.WARNING),
						 DIAGNOSTIC_SOURCE,
						 0,
						 EcorePlugin.INSTANCE.getString("_UI_GenericConstraint_diagnostic", new Object[] { "ClassCantBeReferencedbyTwoSameNameAssociation", getObjectLabel(association, context) }),
						 new Object[] { association }));
			}
			return false;
		}
		return true;
	}

	/**
	 * Validates the IfAggregationOrCompositionThenUnidirectionalAssociation constraint of '<em>Association</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean validateAssociation_IfAggregationOrCompositionThenUnidirectionalAssociation(Association association, DiagnosticChain diagnostics, Map<Object, Object> context) {
        if (association_IfAggregationOrCompositionThenUnidirectionalAssociationInvOCL == null) {
			OCL.Helper helper = OCL_ENV.createOCLHelper();
			helper.setContext(ClazzPackage.Literals.ASSOCIATION);

			EAnnotation ocl = ClazzPackage.Literals.ASSOCIATION.getEAnnotation(OCL_ANNOTATION_SOURCE);
			String expr = ocl.getDetails().get("IfAggregationOrCompositionThenUnidirectionalAssociation");

			try {
				association_IfAggregationOrCompositionThenUnidirectionalAssociationInvOCL = helper.createInvariant(expr);
			}
			catch (ParserException e) {
				throw new UnsupportedOperationException(e.getLocalizedMessage());
			}
		}

		Query<EClassifier, ?, ?> query = OCL_ENV.createQuery(association_IfAggregationOrCompositionThenUnidirectionalAssociationInvOCL);

		if (!query.check(association)) {
			if (diagnostics != null) {
				diagnostics.add
					(new BasicDiagnostic
						((doThrowError( ClazzPackage.Literals.ASSOCIATION.getEAnnotation("http://www.eclipse.org/emf/2002/Ecore"),"IfAggregationOrCompositionThenUnidirectionalAssociation")? Diagnostic.ERROR : Diagnostic.WARNING),
						 DIAGNOSTIC_SOURCE,
						 0,
						 EcorePlugin.INSTANCE.getString("_UI_GenericConstraint_diagnostic", new Object[] { "IfAggregationOrCompositionThenUnidirectionalAssociation", getObjectLabel(association, context) }),
						 new Object[] { association }));
			}
			return false;
		}
		return true;
	}

	/**
	 * Validates the doubleNavigable constraint of '<em>Association</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean validateAssociation_doubleNavigable(Association association, DiagnosticChain diagnostics, Map<Object, Object> context) {
		// TODO implement the constraint
		// -> specify the condition that violates the constraint
		// -> verify the diagnostic details, including severity, code, and message
		// Ensure that you remove @generated or mark it @generated NOT
		if (false) {
			if (diagnostics != null) {
				diagnostics.add
					(new BasicDiagnostic
						(Diagnostic.ERROR,
						 DIAGNOSTIC_SOURCE,
						 0,
						 EcorePlugin.INSTANCE.getString("_UI_GenericConstraint_diagnostic", new Object[] { "doubleNavigable", getObjectLabel(association, context) }),
						 new Object[] { association }));
			}
			return false;
		}
		return true;
	}

	/**
	 * Validates the noSpecialChracters constraint of '<em>Association</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean validateAssociation_noSpecialChracters(Association association, DiagnosticChain diagnostics, Map<Object, Object> context) {
		// TODO implement the constraint
		// -> specify the condition that violates the constraint
		// -> verify the diagnostic details, including severity, code, and message
		// Ensure that you remove @generated or mark it @generated NOT
		if (false) {
			if (diagnostics != null) {
				diagnostics.add
					(new BasicDiagnostic
						(Diagnostic.ERROR,
						 DIAGNOSTIC_SOURCE,
						 0,
						 EcorePlugin.INSTANCE.getString("_UI_GenericConstraint_diagnostic", new Object[] { "noSpecialChracters", getObjectLabel(association, context) }),
						 new Object[] { association }));
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
		if (result || diagnostics != null) result &= validateAttribute_NameNull(attribute, diagnostics, context);
		if (result || diagnostics != null) result &= validateAttribute_noSpecialCharacters(attribute, diagnostics, context);
		return result;
	}

	/**
	 * Validates the NameNull constraint of '<em>Attribute</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean validateAttribute_NameNull(Attribute attribute, DiagnosticChain diagnostics, Map<Object, Object> context) {
        if (attribute_NameNullInvOCL == null) {
			OCL.Helper helper = OCL_ENV.createOCLHelper();
			helper.setContext(ClazzPackage.Literals.ATTRIBUTE);

			EAnnotation ocl = ClazzPackage.Literals.ATTRIBUTE.getEAnnotation(OCL_ANNOTATION_SOURCE);
			String expr = ocl.getDetails().get("NameNull");

			try {
				attribute_NameNullInvOCL = helper.createInvariant(expr);
			}
			catch (ParserException e) {
				throw new UnsupportedOperationException(e.getLocalizedMessage());
			}
		}

		Query<EClassifier, ?, ?> query = OCL_ENV.createQuery(attribute_NameNullInvOCL);

		if (!query.check(attribute)) {
			if (diagnostics != null) {
				diagnostics.add
					(new BasicDiagnostic
						((doThrowError( ClazzPackage.Literals.ATTRIBUTE.getEAnnotation("http://www.eclipse.org/emf/2002/Ecore"),"NameNull")? Diagnostic.ERROR : Diagnostic.WARNING),
						 DIAGNOSTIC_SOURCE,
						 0,
						 EcorePlugin.INSTANCE.getString("_UI_GenericConstraint_diagnostic", new Object[] { "NameNull", getObjectLabel(attribute, context) }),
						 new Object[] { attribute }));
			}
			return false;
		}
		return true;
	}

	/**
	 * Validates the noSpecialCharacters constraint of '<em>Attribute</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean validateAttribute_noSpecialCharacters(Attribute attribute, DiagnosticChain diagnostics, Map<Object, Object> context) {
        if (attribute_noSpecialCharactersInvOCL == null) {
			OCL.Helper helper = OCL_ENV.createOCLHelper();
			helper.setContext(ClazzPackage.Literals.ATTRIBUTE);

			EAnnotation ocl = ClazzPackage.Literals.ATTRIBUTE.getEAnnotation(OCL_ANNOTATION_SOURCE);
			String expr = ocl.getDetails().get("noSpecialCharacters");

			try {
				attribute_noSpecialCharactersInvOCL = helper.createInvariant(expr);
			}
			catch (ParserException e) {
				throw new UnsupportedOperationException(e.getLocalizedMessage());
			}
		}

		Query<EClassifier, ?, ?> query = OCL_ENV.createQuery(attribute_noSpecialCharactersInvOCL);

		if (!query.check(attribute)) {
			if (diagnostics != null) {
				diagnostics.add
					(new BasicDiagnostic
						((doThrowError( ClazzPackage.Literals.ATTRIBUTE.getEAnnotation("http://www.eclipse.org/emf/2002/Ecore"),"noSpecialCharacters")? Diagnostic.ERROR : Diagnostic.WARNING),
						 DIAGNOSTIC_SOURCE,
						 0,
						 EcorePlugin.INSTANCE.getString("_UI_GenericConstraint_diagnostic", new Object[] { "noSpecialCharacters", getObjectLabel(attribute, context) }),
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
	public boolean validateEnumeration(Enumeration enumeration, DiagnosticChain diagnostics, Map<Object, Object> context) {
		boolean result = validate_EveryMultiplicityConforms(enumeration, diagnostics, context);
		if (result || diagnostics != null) result &= validate_EveryDataValueConforms(enumeration, diagnostics, context);
		if (result || diagnostics != null) result &= validate_EveryReferenceIsContained(enumeration, diagnostics, context);
		if (result || diagnostics != null) result &= validate_EveryProxyResolves(enumeration, diagnostics, context);
		if (result || diagnostics != null) result &= validate_UniqueID(enumeration, diagnostics, context);
		if (result || diagnostics != null) result &= validate_EveryKeyUnique(enumeration, diagnostics, context);
		if (result || diagnostics != null) result &= validate_EveryMapEntryUnique(enumeration, diagnostics, context);
		if (result || diagnostics != null) result &= validateEnumeration_enumDynamicAreNotAvailable(enumeration, diagnostics, context);
		return result;
	}

	/**
	 * Validates the enumDynamicAreNotAvailable constraint of '<em>Enumeration</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean validateEnumeration_enumDynamicAreNotAvailable(Enumeration enumeration, DiagnosticChain diagnostics, Map<Object, Object> context) {
        if (enumeration_enumDynamicAreNotAvailableInvOCL == null) {
			OCL.Helper helper = OCL_ENV.createOCLHelper();
			helper.setContext(ClazzPackage.Literals.ENUMERATION);

			EAnnotation ocl = ClazzPackage.Literals.ENUMERATION.getEAnnotation(OCL_ANNOTATION_SOURCE);
			String expr = ocl.getDetails().get("enumDynamicAreNotAvailable");

			try {
				enumeration_enumDynamicAreNotAvailableInvOCL = helper.createInvariant(expr);
			}
			catch (ParserException e) {
				throw new UnsupportedOperationException(e.getLocalizedMessage());
			}
		}

		Query<EClassifier, ?, ?> query = OCL_ENV.createQuery(enumeration_enumDynamicAreNotAvailableInvOCL);

		if (!query.check(enumeration)) {
			if (diagnostics != null) {
				diagnostics.add
					(new BasicDiagnostic
						((doThrowError( ClazzPackage.Literals.ENUMERATION.getEAnnotation("http://www.eclipse.org/emf/2002/Ecore"),"enumDynamicAreNotAvailable")? Diagnostic.ERROR : Diagnostic.WARNING),
						 DIAGNOSTIC_SOURCE,
						 0,
						 EcorePlugin.INSTANCE.getString("_UI_GenericConstraint_diagnostic", new Object[] { "enumDynamicAreNotAvailable", getObjectLabel(enumeration, context) }),
						 new Object[] { enumeration }));
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
	public boolean validateEnumerationLiteral(EnumerationLiteral enumerationLiteral, DiagnosticChain diagnostics, Map<Object, Object> context) {
		return validate_EveryDefaultConstraint(enumerationLiteral, diagnostics, context);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean validateAspect(Aspect aspect, DiagnosticChain diagnostics, Map<Object, Object> context) {
		boolean result = validate_EveryMultiplicityConforms(aspect, diagnostics, context);
		if (result || diagnostics != null) result &= validate_EveryDataValueConforms(aspect, diagnostics, context);
		if (result || diagnostics != null) result &= validate_EveryReferenceIsContained(aspect, diagnostics, context);
		if (result || diagnostics != null) result &= validate_EveryProxyResolves(aspect, diagnostics, context);
		if (result || diagnostics != null) result &= validate_UniqueID(aspect, diagnostics, context);
		if (result || diagnostics != null) result &= validate_EveryKeyUnique(aspect, diagnostics, context);
		if (result || diagnostics != null) result &= validate_EveryMapEntryUnique(aspect, diagnostics, context);
		if (result || diagnostics != null) result &= validateAbstractClass_TwoModelElementWithSameName(aspect, diagnostics, context);
		if (result || diagnostics != null) result &= validateAbstractClass_NameNull(aspect, diagnostics, context);
		if (result || diagnostics != null) result &= validateAbstractClass_noSpecialCharacters(aspect, diagnostics, context);
		if (result || diagnostics != null) result &= validateAbstractClass_TwoAttributesSameName(aspect, diagnostics, context);
		return result;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean validateAbstractClass(AbstractClass abstractClass, DiagnosticChain diagnostics, Map<Object, Object> context) {
		boolean result = validate_EveryMultiplicityConforms(abstractClass, diagnostics, context);
		if (result || diagnostics != null) result &= validate_EveryDataValueConforms(abstractClass, diagnostics, context);
		if (result || diagnostics != null) result &= validate_EveryReferenceIsContained(abstractClass, diagnostics, context);
		if (result || diagnostics != null) result &= validate_EveryProxyResolves(abstractClass, diagnostics, context);
		if (result || diagnostics != null) result &= validate_UniqueID(abstractClass, diagnostics, context);
		if (result || diagnostics != null) result &= validate_EveryKeyUnique(abstractClass, diagnostics, context);
		if (result || diagnostics != null) result &= validate_EveryMapEntryUnique(abstractClass, diagnostics, context);
		if (result || diagnostics != null) result &= validateAbstractClass_TwoModelElementWithSameName(abstractClass, diagnostics, context);
		if (result || diagnostics != null) result &= validateAbstractClass_NameNull(abstractClass, diagnostics, context);
		if (result || diagnostics != null) result &= validateAbstractClass_noSpecialCharacters(abstractClass, diagnostics, context);
		if (result || diagnostics != null) result &= validateAbstractClass_TwoAttributesSameName(abstractClass, diagnostics, context);
		return result;
	}

	/**
	 * Validates the TwoModelElementWithSameName constraint of '<em>Abstract Class</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean validateAbstractClass_TwoModelElementWithSameName(AbstractClass abstractClass, DiagnosticChain diagnostics, Map<Object, Object> context) {
        if (abstractClass_TwoModelElementWithSameNameInvOCL == null) {
			OCL.Helper helper = OCL_ENV.createOCLHelper();
			helper.setContext(ClazzPackage.Literals.ABSTRACT_CLASS);

			EAnnotation ocl = ClazzPackage.Literals.ABSTRACT_CLASS.getEAnnotation(OCL_ANNOTATION_SOURCE);
			String expr = ocl.getDetails().get("TwoModelElementWithSameName");

			try {
				abstractClass_TwoModelElementWithSameNameInvOCL = helper.createInvariant(expr);
			}
			catch (ParserException e) {
				throw new UnsupportedOperationException(e.getLocalizedMessage());
			}
		}

		Query<EClassifier, ?, ?> query = OCL_ENV.createQuery(abstractClass_TwoModelElementWithSameNameInvOCL);

		if (!query.check(abstractClass)) {
			if (diagnostics != null) {
				diagnostics.add
					(new BasicDiagnostic
						((doThrowError( ClazzPackage.Literals.ABSTRACT_CLASS.getEAnnotation("http://www.eclipse.org/emf/2002/Ecore"),"TwoModelElementWithSameName")? Diagnostic.ERROR : Diagnostic.WARNING),
						 DIAGNOSTIC_SOURCE,
						 0,
						 EcorePlugin.INSTANCE.getString("_UI_GenericConstraint_diagnostic", new Object[] { "TwoModelElementWithSameName", getObjectLabel(abstractClass, context) }),
						 new Object[] { abstractClass }));
			}
			return false;
		}
		return true;
	}

	/**
	 * Validates the NameNull constraint of '<em>Abstract Class</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean validateAbstractClass_NameNull(AbstractClass abstractClass, DiagnosticChain diagnostics, Map<Object, Object> context) {
        if (abstractClass_NameNullInvOCL == null) {
			OCL.Helper helper = OCL_ENV.createOCLHelper();
			helper.setContext(ClazzPackage.Literals.ABSTRACT_CLASS);

			EAnnotation ocl = ClazzPackage.Literals.ABSTRACT_CLASS.getEAnnotation(OCL_ANNOTATION_SOURCE);
			String expr = ocl.getDetails().get("NameNull");

			try {
				abstractClass_NameNullInvOCL = helper.createInvariant(expr);
			}
			catch (ParserException e) {
				throw new UnsupportedOperationException(e.getLocalizedMessage());
			}
		}

		Query<EClassifier, ?, ?> query = OCL_ENV.createQuery(abstractClass_NameNullInvOCL);

		if (!query.check(abstractClass)) {
			if (diagnostics != null) {
				diagnostics.add
					(new BasicDiagnostic
						((doThrowError( ClazzPackage.Literals.ABSTRACT_CLASS.getEAnnotation("http://www.eclipse.org/emf/2002/Ecore"),"NameNull")? Diagnostic.ERROR : Diagnostic.WARNING),
						 DIAGNOSTIC_SOURCE,
						 0,
						 EcorePlugin.INSTANCE.getString("_UI_GenericConstraint_diagnostic", new Object[] { "NameNull", getObjectLabel(abstractClass, context) }),
						 new Object[] { abstractClass }));
			}
			return false;
		}
		return true;
	}

	/**
	 * Validates the noSpecialCharacters constraint of '<em>Abstract Class</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean validateAbstractClass_noSpecialCharacters(AbstractClass abstractClass, DiagnosticChain diagnostics, Map<Object, Object> context) {
        if (abstractClass_noSpecialCharactersInvOCL == null) {
			OCL.Helper helper = OCL_ENV.createOCLHelper();
			helper.setContext(ClazzPackage.Literals.ABSTRACT_CLASS);

			EAnnotation ocl = ClazzPackage.Literals.ABSTRACT_CLASS.getEAnnotation(OCL_ANNOTATION_SOURCE);
			String expr = ocl.getDetails().get("noSpecialCharacters");

			try {
				abstractClass_noSpecialCharactersInvOCL = helper.createInvariant(expr);
			}
			catch (ParserException e) {
				throw new UnsupportedOperationException(e.getLocalizedMessage());
			}
		}

		Query<EClassifier, ?, ?> query = OCL_ENV.createQuery(abstractClass_noSpecialCharactersInvOCL);

		if (!query.check(abstractClass)) {
			if (diagnostics != null) {
				diagnostics.add
					(new BasicDiagnostic
						((doThrowError( ClazzPackage.Literals.ABSTRACT_CLASS.getEAnnotation("http://www.eclipse.org/emf/2002/Ecore"),"noSpecialCharacters")? Diagnostic.ERROR : Diagnostic.WARNING),
						 DIAGNOSTIC_SOURCE,
						 0,
						 EcorePlugin.INSTANCE.getString("_UI_GenericConstraint_diagnostic", new Object[] { "noSpecialCharacters", getObjectLabel(abstractClass, context) }),
						 new Object[] { abstractClass }));
			}
			return false;
		}
		return true;
	}

	/**
	 * Validates the TwoAttributesSameName constraint of '<em>Abstract Class</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean validateAbstractClass_TwoAttributesSameName(AbstractClass abstractClass, DiagnosticChain diagnostics, Map<Object, Object> context) {
        if (abstractClass_TwoAttributesSameNameInvOCL == null) {
			OCL.Helper helper = OCL_ENV.createOCLHelper();
			helper.setContext(ClazzPackage.Literals.ABSTRACT_CLASS);

			EAnnotation ocl = ClazzPackage.Literals.ABSTRACT_CLASS.getEAnnotation(OCL_ANNOTATION_SOURCE);
			String expr = ocl.getDetails().get("TwoAttributesSameName");

			try {
				abstractClass_TwoAttributesSameNameInvOCL = helper.createInvariant(expr);
			}
			catch (ParserException e) {
				throw new UnsupportedOperationException(e.getLocalizedMessage());
			}
		}

		Query<EClassifier, ?, ?> query = OCL_ENV.createQuery(abstractClass_TwoAttributesSameNameInvOCL);

		if (!query.check(abstractClass)) {
			if (diagnostics != null) {
				diagnostics.add
					(new BasicDiagnostic
						((doThrowError( ClazzPackage.Literals.ABSTRACT_CLASS.getEAnnotation("http://www.eclipse.org/emf/2002/Ecore"),"TwoAttributesSameName")? Diagnostic.ERROR : Diagnostic.WARNING),
						 DIAGNOSTIC_SOURCE,
						 0,
						 EcorePlugin.INSTANCE.getString("_UI_GenericConstraint_diagnostic", new Object[] { "TwoAttributesSameName", getObjectLabel(abstractClass, context) }),
						 new Object[] { abstractClass }));
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
	public boolean validateTitledNamedClassModelElement(TitledNamedClassModelElement titledNamedClassModelElement, DiagnosticChain diagnostics, Map<Object, Object> context) {
		return validate_EveryDefaultConstraint(titledNamedClassModelElement, diagnostics, context);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean validateClassComment(ClassComment classComment, DiagnosticChain diagnostics, Map<Object, Object> context) {
		return validate_EveryDefaultConstraint(classComment, diagnostics, context);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean validateAssociationEnd(AssociationEnd associationEnd, DiagnosticChain diagnostics, Map<Object, Object> context) {
		return validate_EveryDefaultConstraint(associationEnd, diagnostics, context);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean validateModel(Model model, DiagnosticChain diagnostics, Map<Object, Object> context) {
		boolean result = validate_EveryMultiplicityConforms(model, diagnostics, context);
		if (result || diagnostics != null) result &= validate_EveryDataValueConforms(model, diagnostics, context);
		if (result || diagnostics != null) result &= validate_EveryReferenceIsContained(model, diagnostics, context);
		if (result || diagnostics != null) result &= validate_EveryProxyResolves(model, diagnostics, context);
		if (result || diagnostics != null) result &= validate_UniqueID(model, diagnostics, context);
		if (result || diagnostics != null) result &= validate_EveryKeyUnique(model, diagnostics, context);
		if (result || diagnostics != null) result &= validate_EveryMapEntryUnique(model, diagnostics, context);
		if (result || diagnostics != null) result &= validateClassPackage_PackageNameNull(model, diagnostics, context);
		return result;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean validateAssociationType(AssociationType associationType, DiagnosticChain diagnostics, Map<Object, Object> context) {
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

} //ClazzValidator
