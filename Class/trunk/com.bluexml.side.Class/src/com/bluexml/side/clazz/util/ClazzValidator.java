/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package com.bluexml.side.clazz.util;

import com.bluexml.side.Utils.MetaModel.validate.OCLextension.KerblueOCL;

import com.bluexml.side.clazz.*;

import com.bluexml.side.common.util.CommonValidator;

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
	 * The cached base package validator.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected CommonValidator commonValidator;

	/**
	 * The parsed OCL expression for the definition of the '<em>PackageNameNull</em>' invariant constraint.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private static Constraint classPackage_PackageNameNullInvOCL;

	/**
	 * The parsed OCL expression for the definition of the '<em>ClassWithTwoAttributesSameName</em>' invariant constraint.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private static Constraint clazz_ClassWithTwoAttributesSameNameInvOCL;

	/**
	 * The parsed OCL expression for the definition of the '<em>InheritanceCycle</em>' invariant constraint.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private static Constraint clazz_InheritanceCycleInvOCL;

	/**
	 * The parsed OCL expression for the definition of the '<em>recursiveAssociationMustHaveRole</em>' invariant constraint.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private static Constraint association_recursiveAssociationMustHaveRoleInvOCL;
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
	 * The parsed OCL expression for the definition of the '<em>AssociatioClassCantBeAgregationOrComposition</em>' invariant constraint.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private static Constraint association_AssociatioClassCantBeAgregationOrCompositionInvOCL;
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
	 * The parsed OCL expression for the definition of the '<em>AspectWithTwoAttributesSameName</em>' invariant constraint.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private static Constraint aspect_AspectWithTwoAttributesSameNameInvOCL;

	/**
	 * The parsed OCL expression for the definition of the '<em>TwoModelElementWithSameName</em>' invariant constraint.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private static Constraint abstractContainer_TwoModelElementWithSameNameInvOCL;
	/**
	 * The parsed OCL expression for the definition of the '<em>NameNull</em>' invariant constraint.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private static Constraint abstractContainer_NameNullInvOCL;
	/**
	 * The parsed OCL expression for the definition of the '<em>noSpecialCharacters</em>' invariant constraint.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private static Constraint abstractContainer_noSpecialCharactersInvOCL;
	/**
	 * The parsed OCL expression for the definition of the '<em>AtLeastOneAttribute</em>' invariant constraint.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private static Constraint view_AtLeastOneAttributeInvOCL;

	/**
	 * The parsed OCL expression for the definition of the '<em>ViewWithTwoAttributesSameName</em>' invariant constraint.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private static Constraint view_ViewWithTwoAttributesSameNameInvOCL;

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
		commonValidator = CommonValidator.INSTANCE;
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
			case ClazzPackage.NAMED_CLASS_MODEL_ELEMENT:
				return validateNamedClassModelElement((NamedClassModelElement)value, diagnostics, context);
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
			case ClazzPackage.OPERATION:
				return validateOperation((Operation)value, diagnostics, context);
			case ClazzPackage.PARAMETER:
				return validateParameter((Parameter)value, diagnostics, context);
			case ClazzPackage.ASPECT:
				return validateAspect((Aspect)value, diagnostics, context);
			case ClazzPackage.ABSTRACT_CLASS:
				return validateAbstractClass((AbstractClass)value, diagnostics, context);
			case ClazzPackage.ABSTRACT_CONTAINER:
				return validateAbstractContainer((AbstractContainer)value, diagnostics, context);
			case ClazzPackage.VIEW:
				return validateView((View)value, diagnostics, context);
			case ClazzPackage.VIEW_ITEM:
				return validateViewItem((ViewItem)value, diagnostics, context);
			case ClazzPackage.META_INFO:
				return validateMetaInfo((MetaInfo)value, diagnostics, context);
			case ClazzPackage.META_INFO_GROUP:
				return validateMetaInfoGroup((MetaInfoGroup)value, diagnostics, context);
			case ClazzPackage.TITLED_NAMED_CLASS_MODEL_ELEMENT:
				return validateTitledNamedClassModelElement((TitledNamedClassModelElement)value, diagnostics, context);
			case ClazzPackage.CLASS_COMMENT:
				return validateClassComment((ClassComment)value, diagnostics, context);
			case ClazzPackage.ASSOCIATION_TYPE:
				return validateAssociationType((AssociationType)value, diagnostics, context);
			case ClazzPackage.ATTRIBUTE_TYPE:
				return validateAttributeType((AttributeType)value, diagnostics, context);
			case ClazzPackage.VISIBILITY:
				return validateVisibility((Visibility)value, diagnostics, context);
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
	public boolean validateNamedClassModelElement(NamedClassModelElement namedClassModelElement, DiagnosticChain diagnostics, Map<Object, Object> context) {
		return validate_EveryDefaultConstraint(namedClassModelElement, diagnostics, context);
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
		// TODO override the constraint, if desired
		// -> uncomment the scaffolding
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
						 EcorePlugin.INSTANCE.getString("_UI_GenericConstraint_diagnostic", new Object[] { "PackageNameNull", getObjectLabel(classPackage, context) }),
						 new Object[] { classPackage }));
			}
			return false;
		}
		return commonValidator.validatePackage_PackageNameNull(classPackage, diagnostics, context);
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
		if (result || diagnostics != null) result &= validateAbstractContainer_TwoModelElementWithSameName(clazz, diagnostics, context);
		if (result || diagnostics != null) result &= validateAbstractContainer_NameNull(clazz, diagnostics, context);
		if (result || diagnostics != null) result &= validateAbstractContainer_noSpecialCharacters(clazz, diagnostics, context);
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
        if (clazz_ClassWithTwoAttributesSameNameInvOCL == null) {
			OCL.Helper helper = OCL_ENV.createOCLHelper();
			helper.setContext(ClazzPackage.Literals.CLAZZ);
			
			EAnnotation ocl = ClazzPackage.Literals.CLAZZ.getEAnnotation(OCL_ANNOTATION_SOURCE);
			String expr = ocl.getDetails().get("ClassWithTwoAttributesSameName");
			
			try {
				clazz_ClassWithTwoAttributesSameNameInvOCL = helper.createInvariant(expr);
			}
			catch (ParserException e) {
				throw new UnsupportedOperationException(e.getLocalizedMessage());
			}
		}
		
		Query<EClassifier, ?, ?> query = OCL_ENV.createQuery(clazz_ClassWithTwoAttributesSameNameInvOCL);
		
		if (!query.check(clazz)) {
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
						(Diagnostic.ERROR,
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
		if (result || diagnostics != null) result &= validateAssociation_recursiveAssociationMustHaveRole(association, diagnostics, context);
		if (result || diagnostics != null) result &= validateAssociation_MinAndMaxTarget(association, diagnostics, context);
		if (result || diagnostics != null) result &= validateAssociation_MinAndMaxSource(association, diagnostics, context);
		if (result || diagnostics != null) result &= validateAssociation_AssociatioClassCantBeAgregationOrComposition(association, diagnostics, context);
		if (result || diagnostics != null) result &= validateAssociation_NameNull(association, diagnostics, context);
		if (result || diagnostics != null) result &= validateAssociation_SourceNull(association, diagnostics, context);
		if (result || diagnostics != null) result &= validateAssociation_TargetNull(association, diagnostics, context);
		if (result || diagnostics != null) result &= validateAssociation_AtLeastOneNavigableEdge(association, diagnostics, context);
		if (result || diagnostics != null) result &= validateAssociation_ClassCantBeReferencedbyTwoSameNameAssociation(association, diagnostics, context);
		if (result || diagnostics != null) result &= validateAssociation_IfAggregationOrCompositionThenUnidirectionalAssociation(association, diagnostics, context);
		return result;
	}

	/**
	 * Validates the recursiveAssociationMustHaveRole constraint of '<em>Association</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean validateAssociation_recursiveAssociationMustHaveRole(Association association, DiagnosticChain diagnostics, Map<Object, Object> context) {
        if (association_recursiveAssociationMustHaveRoleInvOCL == null) {
			OCL.Helper helper = OCL_ENV.createOCLHelper();
			helper.setContext(ClazzPackage.Literals.ASSOCIATION);
			
			EAnnotation ocl = ClazzPackage.Literals.ASSOCIATION.getEAnnotation(OCL_ANNOTATION_SOURCE);
			String expr = ocl.getDetails().get("recursiveAssociationMustHaveRole");
			
			try {
				association_recursiveAssociationMustHaveRoleInvOCL = helper.createInvariant(expr);
			}
			catch (ParserException e) {
				throw new UnsupportedOperationException(e.getLocalizedMessage());
			}
		}
		
		Query<EClassifier, ?, ?> query = OCL_ENV.createQuery(association_recursiveAssociationMustHaveRoleInvOCL);
		
		if (!query.check(association)) {
			if (diagnostics != null) {
				diagnostics.add
					(new BasicDiagnostic
						(Diagnostic.ERROR,
						 DIAGNOSTIC_SOURCE,
						 0,
						 EcorePlugin.INSTANCE.getString("_UI_GenericConstraint_diagnostic", new Object[] { "recursiveAssociationMustHaveRole", getObjectLabel(association, context) }),
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
						(Diagnostic.ERROR,
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
						(Diagnostic.ERROR,
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
	 * Validates the AssociatioClassCantBeAgregationOrComposition constraint of '<em>Association</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean validateAssociation_AssociatioClassCantBeAgregationOrComposition(Association association, DiagnosticChain diagnostics, Map<Object, Object> context) {
        if (association_AssociatioClassCantBeAgregationOrCompositionInvOCL == null) {
			OCL.Helper helper = OCL_ENV.createOCLHelper();
			helper.setContext(ClazzPackage.Literals.ASSOCIATION);
			
			EAnnotation ocl = ClazzPackage.Literals.ASSOCIATION.getEAnnotation(OCL_ANNOTATION_SOURCE);
			String expr = ocl.getDetails().get("AssociatioClassCantBeAgregationOrComposition");
			
			try {
				association_AssociatioClassCantBeAgregationOrCompositionInvOCL = helper.createInvariant(expr);
			}
			catch (ParserException e) {
				throw new UnsupportedOperationException(e.getLocalizedMessage());
			}
		}
		
		Query<EClassifier, ?, ?> query = OCL_ENV.createQuery(association_AssociatioClassCantBeAgregationOrCompositionInvOCL);
		
		if (!query.check(association)) {
			if (diagnostics != null) {
				diagnostics.add
					(new BasicDiagnostic
						(Diagnostic.ERROR,
						 DIAGNOSTIC_SOURCE,
						 0,
						 EcorePlugin.INSTANCE.getString("_UI_GenericConstraint_diagnostic", new Object[] { "AssociatioClassCantBeAgregationOrComposition", getObjectLabel(association, context) }),
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
						(Diagnostic.ERROR,
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
						(Diagnostic.ERROR,
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
						(Diagnostic.ERROR,
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
						(Diagnostic.ERROR,
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
						(Diagnostic.ERROR,
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
						(Diagnostic.ERROR,
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
	public boolean validateEnumeration(Enumeration enumeration, DiagnosticChain diagnostics, Map<Object, Object> context) {
		return validate_EveryDefaultConstraint(enumeration, diagnostics, context);
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
	public boolean validateOperation(Operation operation, DiagnosticChain diagnostics, Map<Object, Object> context) {
		return validate_EveryDefaultConstraint(operation, diagnostics, context);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean validateParameter(Parameter parameter, DiagnosticChain diagnostics, Map<Object, Object> context) {
		return validate_EveryDefaultConstraint(parameter, diagnostics, context);
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
		if (result || diagnostics != null) result &= validateAbstractContainer_TwoModelElementWithSameName(aspect, diagnostics, context);
		if (result || diagnostics != null) result &= validateAbstractContainer_NameNull(aspect, diagnostics, context);
		if (result || diagnostics != null) result &= validateAbstractContainer_noSpecialCharacters(aspect, diagnostics, context);
		if (result || diagnostics != null) result &= validateAspect_AspectWithTwoAttributesSameName(aspect, diagnostics, context);
		return result;
	}

	/**
	 * Validates the AspectWithTwoAttributesSameName constraint of '<em>Aspect</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean validateAspect_AspectWithTwoAttributesSameName(Aspect aspect, DiagnosticChain diagnostics, Map<Object, Object> context) {
        if (aspect_AspectWithTwoAttributesSameNameInvOCL == null) {
			OCL.Helper helper = OCL_ENV.createOCLHelper();
			helper.setContext(ClazzPackage.Literals.ASPECT);
			
			EAnnotation ocl = ClazzPackage.Literals.ASPECT.getEAnnotation(OCL_ANNOTATION_SOURCE);
			String expr = ocl.getDetails().get("AspectWithTwoAttributesSameName");
			
			try {
				aspect_AspectWithTwoAttributesSameNameInvOCL = helper.createInvariant(expr);
			}
			catch (ParserException e) {
				throw new UnsupportedOperationException(e.getLocalizedMessage());
			}
		}
		
		Query<EClassifier, ?, ?> query = OCL_ENV.createQuery(aspect_AspectWithTwoAttributesSameNameInvOCL);
		
		if (!query.check(aspect)) {
			if (diagnostics != null) {
				diagnostics.add
					(new BasicDiagnostic
						(Diagnostic.ERROR,
						 DIAGNOSTIC_SOURCE,
						 0,
						 EcorePlugin.INSTANCE.getString("_UI_GenericConstraint_diagnostic", new Object[] { "AspectWithTwoAttributesSameName", getObjectLabel(aspect, context) }),
						 new Object[] { aspect }));
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
	public boolean validateAbstractClass(AbstractClass abstractClass, DiagnosticChain diagnostics, Map<Object, Object> context) {
		boolean result = validate_EveryMultiplicityConforms(abstractClass, diagnostics, context);
		if (result || diagnostics != null) result &= validate_EveryDataValueConforms(abstractClass, diagnostics, context);
		if (result || diagnostics != null) result &= validate_EveryReferenceIsContained(abstractClass, diagnostics, context);
		if (result || diagnostics != null) result &= validate_EveryProxyResolves(abstractClass, diagnostics, context);
		if (result || diagnostics != null) result &= validate_UniqueID(abstractClass, diagnostics, context);
		if (result || diagnostics != null) result &= validate_EveryKeyUnique(abstractClass, diagnostics, context);
		if (result || diagnostics != null) result &= validate_EveryMapEntryUnique(abstractClass, diagnostics, context);
		if (result || diagnostics != null) result &= validateAbstractContainer_TwoModelElementWithSameName(abstractClass, diagnostics, context);
		if (result || diagnostics != null) result &= validateAbstractContainer_NameNull(abstractClass, diagnostics, context);
		if (result || diagnostics != null) result &= validateAbstractContainer_noSpecialCharacters(abstractClass, diagnostics, context);
		return result;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean validateAbstractContainer(AbstractContainer abstractContainer, DiagnosticChain diagnostics, Map<Object, Object> context) {
		boolean result = validate_EveryMultiplicityConforms(abstractContainer, diagnostics, context);
		if (result || diagnostics != null) result &= validate_EveryDataValueConforms(abstractContainer, diagnostics, context);
		if (result || diagnostics != null) result &= validate_EveryReferenceIsContained(abstractContainer, diagnostics, context);
		if (result || diagnostics != null) result &= validate_EveryProxyResolves(abstractContainer, diagnostics, context);
		if (result || diagnostics != null) result &= validate_UniqueID(abstractContainer, diagnostics, context);
		if (result || diagnostics != null) result &= validate_EveryKeyUnique(abstractContainer, diagnostics, context);
		if (result || diagnostics != null) result &= validate_EveryMapEntryUnique(abstractContainer, diagnostics, context);
		if (result || diagnostics != null) result &= validateAbstractContainer_TwoModelElementWithSameName(abstractContainer, diagnostics, context);
		if (result || diagnostics != null) result &= validateAbstractContainer_NameNull(abstractContainer, diagnostics, context);
		if (result || diagnostics != null) result &= validateAbstractContainer_noSpecialCharacters(abstractContainer, diagnostics, context);
		return result;
	}

	/**
	 * Validates the TwoModelElementWithSameName constraint of '<em>Abstract Container</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean validateAbstractContainer_TwoModelElementWithSameName(AbstractContainer abstractContainer, DiagnosticChain diagnostics, Map<Object, Object> context) {
        if (abstractContainer_TwoModelElementWithSameNameInvOCL == null) {
			OCL.Helper helper = OCL_ENV.createOCLHelper();
			helper.setContext(ClazzPackage.Literals.ABSTRACT_CONTAINER);
			
			EAnnotation ocl = ClazzPackage.Literals.ABSTRACT_CONTAINER.getEAnnotation(OCL_ANNOTATION_SOURCE);
			String expr = ocl.getDetails().get("TwoModelElementWithSameName");
			
			try {
				abstractContainer_TwoModelElementWithSameNameInvOCL = helper.createInvariant(expr);
			}
			catch (ParserException e) {
				throw new UnsupportedOperationException(e.getLocalizedMessage());
			}
		}
		
		Query<EClassifier, ?, ?> query = OCL_ENV.createQuery(abstractContainer_TwoModelElementWithSameNameInvOCL);
		
		if (!query.check(abstractContainer)) {
			if (diagnostics != null) {
				diagnostics.add
					(new BasicDiagnostic
						(Diagnostic.ERROR,
						 DIAGNOSTIC_SOURCE,
						 0,
						 EcorePlugin.INSTANCE.getString("_UI_GenericConstraint_diagnostic", new Object[] { "TwoModelElementWithSameName", getObjectLabel(abstractContainer, context) }),
						 new Object[] { abstractContainer }));
			}
			return false;
		}
		return true;
	}

	/**
	 * Validates the NameNull constraint of '<em>Abstract Container</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean validateAbstractContainer_NameNull(AbstractContainer abstractContainer, DiagnosticChain diagnostics, Map<Object, Object> context) {
        if (abstractContainer_NameNullInvOCL == null) {
			OCL.Helper helper = OCL_ENV.createOCLHelper();
			helper.setContext(ClazzPackage.Literals.ABSTRACT_CONTAINER);
			
			EAnnotation ocl = ClazzPackage.Literals.ABSTRACT_CONTAINER.getEAnnotation(OCL_ANNOTATION_SOURCE);
			String expr = ocl.getDetails().get("NameNull");
			
			try {
				abstractContainer_NameNullInvOCL = helper.createInvariant(expr);
			}
			catch (ParserException e) {
				throw new UnsupportedOperationException(e.getLocalizedMessage());
			}
		}
		
		Query<EClassifier, ?, ?> query = OCL_ENV.createQuery(abstractContainer_NameNullInvOCL);
		
		if (!query.check(abstractContainer)) {
			if (diagnostics != null) {
				diagnostics.add
					(new BasicDiagnostic
						(Diagnostic.ERROR,
						 DIAGNOSTIC_SOURCE,
						 0,
						 EcorePlugin.INSTANCE.getString("_UI_GenericConstraint_diagnostic", new Object[] { "NameNull", getObjectLabel(abstractContainer, context) }),
						 new Object[] { abstractContainer }));
			}
			return false;
		}
		return true;
	}

	/**
	 * Validates the noSpecialCharacters constraint of '<em>Abstract Container</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean validateAbstractContainer_noSpecialCharacters(AbstractContainer abstractContainer, DiagnosticChain diagnostics, Map<Object, Object> context) {
        if (abstractContainer_noSpecialCharactersInvOCL == null) {
			OCL.Helper helper = OCL_ENV.createOCLHelper();
			helper.setContext(ClazzPackage.Literals.ABSTRACT_CONTAINER);
			
			EAnnotation ocl = ClazzPackage.Literals.ABSTRACT_CONTAINER.getEAnnotation(OCL_ANNOTATION_SOURCE);
			String expr = ocl.getDetails().get("noSpecialCharacters");
			
			try {
				abstractContainer_noSpecialCharactersInvOCL = helper.createInvariant(expr);
			}
			catch (ParserException e) {
				throw new UnsupportedOperationException(e.getLocalizedMessage());
			}
		}
		
		Query<EClassifier, ?, ?> query = OCL_ENV.createQuery(abstractContainer_noSpecialCharactersInvOCL);
		
		if (!query.check(abstractContainer)) {
			if (diagnostics != null) {
				diagnostics.add
					(new BasicDiagnostic
						(Diagnostic.ERROR,
						 DIAGNOSTIC_SOURCE,
						 0,
						 EcorePlugin.INSTANCE.getString("_UI_GenericConstraint_diagnostic", new Object[] { "noSpecialCharacters", getObjectLabel(abstractContainer, context) }),
						 new Object[] { abstractContainer }));
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
	public boolean validateView(View view, DiagnosticChain diagnostics, Map<Object, Object> context) {
		boolean result = validate_EveryMultiplicityConforms(view, diagnostics, context);
		if (result || diagnostics != null) result &= validate_EveryDataValueConforms(view, diagnostics, context);
		if (result || diagnostics != null) result &= validate_EveryReferenceIsContained(view, diagnostics, context);
		if (result || diagnostics != null) result &= validate_EveryProxyResolves(view, diagnostics, context);
		if (result || diagnostics != null) result &= validate_UniqueID(view, diagnostics, context);
		if (result || diagnostics != null) result &= validate_EveryKeyUnique(view, diagnostics, context);
		if (result || diagnostics != null) result &= validate_EveryMapEntryUnique(view, diagnostics, context);
		if (result || diagnostics != null) result &= validateView_AtLeastOneAttribute(view, diagnostics, context);
		if (result || diagnostics != null) result &= validateView_ViewWithTwoAttributesSameName(view, diagnostics, context);
		return result;
	}

	/**
	 * Validates the AtLeastOneAttribute constraint of '<em>View</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean validateView_AtLeastOneAttribute(View view, DiagnosticChain diagnostics, Map<Object, Object> context) {
        if (view_AtLeastOneAttributeInvOCL == null) {
			OCL.Helper helper = OCL_ENV.createOCLHelper();
			helper.setContext(ClazzPackage.Literals.VIEW);
			
			EAnnotation ocl = ClazzPackage.Literals.VIEW.getEAnnotation(OCL_ANNOTATION_SOURCE);
			String expr = ocl.getDetails().get("AtLeastOneAttribute");
			
			try {
				view_AtLeastOneAttributeInvOCL = helper.createInvariant(expr);
			}
			catch (ParserException e) {
				throw new UnsupportedOperationException(e.getLocalizedMessage());
			}
		}
		
		Query<EClassifier, ?, ?> query = OCL_ENV.createQuery(view_AtLeastOneAttributeInvOCL);
		
		if (!query.check(view)) {
			if (diagnostics != null) {
				diagnostics.add
					(new BasicDiagnostic
						(Diagnostic.ERROR,
						 DIAGNOSTIC_SOURCE,
						 0,
						 EcorePlugin.INSTANCE.getString("_UI_GenericConstraint_diagnostic", new Object[] { "AtLeastOneAttribute", getObjectLabel(view, context) }),
						 new Object[] { view }));
			}
			return false;
		}
		return true;
	}

	/**
	 * Validates the ViewWithTwoAttributesSameName constraint of '<em>View</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean validateView_ViewWithTwoAttributesSameName(View view, DiagnosticChain diagnostics, Map<Object, Object> context) {
        if (view_ViewWithTwoAttributesSameNameInvOCL == null) {
			OCL.Helper helper = OCL_ENV.createOCLHelper();
			helper.setContext(ClazzPackage.Literals.VIEW);
			
			EAnnotation ocl = ClazzPackage.Literals.VIEW.getEAnnotation(OCL_ANNOTATION_SOURCE);
			String expr = ocl.getDetails().get("ViewWithTwoAttributesSameName");
			
			try {
				view_ViewWithTwoAttributesSameNameInvOCL = helper.createInvariant(expr);
			}
			catch (ParserException e) {
				throw new UnsupportedOperationException(e.getLocalizedMessage());
			}
		}
		
		Query<EClassifier, ?, ?> query = OCL_ENV.createQuery(view_ViewWithTwoAttributesSameNameInvOCL);
		
		if (!query.check(view)) {
			if (diagnostics != null) {
				diagnostics.add
					(new BasicDiagnostic
						(Diagnostic.ERROR,
						 DIAGNOSTIC_SOURCE,
						 0,
						 EcorePlugin.INSTANCE.getString("_UI_GenericConstraint_diagnostic", new Object[] { "ViewWithTwoAttributesSameName", getObjectLabel(view, context) }),
						 new Object[] { view }));
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
	public boolean validateViewItem(ViewItem viewItem, DiagnosticChain diagnostics, Map<Object, Object> context) {
		return validate_EveryDefaultConstraint(viewItem, diagnostics, context);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean validateMetaInfo(MetaInfo metaInfo, DiagnosticChain diagnostics, Map<Object, Object> context) {
		return validate_EveryDefaultConstraint(metaInfo, diagnostics, context);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean validateMetaInfoGroup(MetaInfoGroup metaInfoGroup, DiagnosticChain diagnostics, Map<Object, Object> context) {
		return validate_EveryDefaultConstraint(metaInfoGroup, diagnostics, context);
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
	public boolean validateAssociationType(AssociationType associationType, DiagnosticChain diagnostics, Map<Object, Object> context) {
		return true;
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
	public boolean validateVisibility(Visibility visibility, DiagnosticChain diagnostics, Map<Object, Object> context) {
		return true;
	}

} //ClazzValidator
