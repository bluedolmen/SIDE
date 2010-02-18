/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package com.bluexml.side.form.util;

import com.bluexml.side.form.*;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

import org.eclipse.emf.common.util.BasicDiagnostic;
import org.eclipse.emf.common.util.Diagnostic;
import org.eclipse.emf.common.util.DiagnosticChain;
import org.eclipse.emf.common.util.ResourceLocator;
import org.eclipse.emf.ecore.EAnnotation;
import org.eclipse.emf.ecore.EClassifier;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.plugin.EcorePlugin;
import org.eclipse.emf.ecore.util.EObjectValidator;
import org.eclipse.ocl.ParserException;
import org.eclipse.ocl.Query;
import org.eclipse.ocl.ecore.Constraint;
import org.eclipse.ocl.ecore.OCL;

import com.bluexml.side.form.ActionField;
import com.bluexml.side.form.BooleanField;
import com.bluexml.side.form.CharField;
import com.bluexml.side.form.ChoiceField;
import com.bluexml.side.form.ChoiceWidgetType;
import com.bluexml.side.form.ClassReference;
import com.bluexml.side.form.DateField;
import com.bluexml.side.form.DateTimeField;
import com.bluexml.side.form.DecimalField;
import com.bluexml.side.form.EmailField;
import com.bluexml.side.form.Field;
import com.bluexml.side.form.FileField;
import com.bluexml.side.form.FloatField;
import com.bluexml.side.form.FormAspect;
import com.bluexml.side.form.FormClass;
import com.bluexml.side.form.FormCollection;
import com.bluexml.side.form.FormContainer;
import com.bluexml.side.form.FormElement;
import com.bluexml.side.form.FormGroup;
import com.bluexml.side.form.FormGroupPresentationType;
import com.bluexml.side.form.FormPackage;
import com.bluexml.side.form.FormWorkflow;
import com.bluexml.side.form.ImageField;
import com.bluexml.side.form.IntegerField;
import com.bluexml.side.form.ModelChoiceField;
import com.bluexml.side.form.ModelChoiceWidgetType;
import com.bluexml.side.form.PasswordField;
import com.bluexml.side.form.PhoneNumberField;
import com.bluexml.side.form.Reference;
import com.bluexml.side.form.RegexField;
import com.bluexml.side.form.TextField;
import com.bluexml.side.form.TextWidgetType;
import com.bluexml.side.form.TimeField;
import com.bluexml.side.form.URLField;
import com.bluexml.side.form.VirtualField;
import com.bluexml.side.form.WorkflowFormCollection;
import com.bluexml.side.util.metaModel.validate.OCLextension.KerblueOCL;

/**
 * <!-- begin-user-doc -->
 * The <b>Validator</b> for the model.
 * <!-- end-user-doc -->
 * @see com.bluexml.side.form.FormPackage
 * @generated
 */
public class FormValidator extends EObjectValidator {
	/**
	 * The cached model package
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public static final FormValidator INSTANCE = new FormValidator();

	/**
	 * A constant for the {@link org.eclipse.emf.common.util.Diagnostic#getSource() source} of diagnostic {@link org.eclipse.emf.common.util.Diagnostic#getCode() codes} from this package.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.eclipse.emf.common.util.Diagnostic#getSource()
	 * @see org.eclipse.emf.common.util.Diagnostic#getCode()
	 * @generated
	 */
	public static final String DIAGNOSTIC_SOURCE = "com.bluexml.side.form";

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
	 * The parsed OCL expression for the definition of the '<em>noSpecialCharacters</em>' invariant constraint.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private static Constraint formElement_noSpecialCharactersInvOCL;

	/**
	 * The parsed OCL expression for the definition of the '<em>validRef</em>' invariant constraint.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private static Constraint formElement_validRefInvOCL;

	/**
	 * The parsed OCL expression for the definition of the '<em>mandatoryHiddenAndNoDefaultValue</em>' invariant constraint.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private static Constraint field_mandatoryHiddenAndNoDefaultValueInvOCL;

	/**
	 * The parsed OCL expression for the definition of the '<em>TwoFormsWithSameName</em>' invariant constraint.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private static Constraint formContainer_TwoFormsWithSameNameInvOCL;

	/**
	 * The parsed OCL expression for the definition of the '<em>ClassMustMatchWithProcessContentType</em>' invariant constraint.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private static Constraint formWorkflow_ClassMustMatchWithProcessContentTypeInvOCL;

	/**
	 * The parsed OCL expression for the definition of the '<em>MinSuperiorToMax</em>' invariant constraint.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private static Constraint charField_MinSuperiorToMaxInvOCL;

	/**
	 * The parsed OCL expression for the definition of the '<em>mustReferenceClass</em>' invariant constraint.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private static Constraint classReference_mustReferenceClassInvOCL;

	/**
	 * The parsed OCL expression for the definition of the '<em>NoLinkForVirtualField</em>' invariant constraint.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private static Constraint virtualField_NoLinkForVirtualFieldInvOCL;

	private static final String OCL_ANNOTATION_SOURCE = "http://www.bluexml.com/OCL";

	private static final OCL OCL_ENV = KerblueOCL.newInstance();

	/**
	 * Creates an instance of the switch.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public FormValidator() {
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
	  return FormPackage.eINSTANCE;
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
			case FormPackage.FORM_ELEMENT:
				return validateFormElement((FormElement)value, diagnostics, context);
			case FormPackage.FORM_COLLECTION:
				return validateFormCollection((FormCollection)value, diagnostics, context);
			case FormPackage.FORM_GROUP:
				return validateFormGroup((FormGroup)value, diagnostics, context);
			case FormPackage.WORKFLOW_FORM_COLLECTION:
				return validateWorkflowFormCollection((WorkflowFormCollection)value, diagnostics, context);
			case FormPackage.CLASS_FORM_COLLECTION:
				return validateClassFormCollection((ClassFormCollection)value, diagnostics, context);
			case FormPackage.FIELD:
				return validateField((Field)value, diagnostics, context);
			case FormPackage.FORM_CONTAINER:
				return validateFormContainer((FormContainer)value, diagnostics, context);
			case FormPackage.FORM_WORKFLOW:
				return validateFormWorkflow((FormWorkflow)value, diagnostics, context);
			case FormPackage.FORM_CLASS:
				return validateFormClass((FormClass)value, diagnostics, context);
			case FormPackage.BOOLEAN_FIELD:
				return validateBooleanField((BooleanField)value, diagnostics, context);
			case FormPackage.CHAR_FIELD:
				return validateCharField((CharField)value, diagnostics, context);
			case FormPackage.DATE_FIELD:
				return validateDateField((DateField)value, diagnostics, context);
			case FormPackage.DATE_TIME_FIELD:
				return validateDateTimeField((DateTimeField)value, diagnostics, context);
			case FormPackage.DECIMAL_FIELD:
				return validateDecimalField((DecimalField)value, diagnostics, context);
			case FormPackage.FLOAT_FIELD:
				return validateFloatField((FloatField)value, diagnostics, context);
			case FormPackage.INTEGER_FIELD:
				return validateIntegerField((IntegerField)value, diagnostics, context);
			case FormPackage.MODEL_CHOICE_FIELD:
				return validateModelChoiceField((ModelChoiceField)value, diagnostics, context);
			case FormPackage.EMAIL_FIELD:
				return validateEmailField((EmailField)value, diagnostics, context);
			case FormPackage.FILE_FIELD:
				return validateFileField((FileField)value, diagnostics, context);
			case FormPackage.IMAGE_FIELD:
				return validateImageField((ImageField)value, diagnostics, context);
			case FormPackage.TIME_FIELD:
				return validateTimeField((TimeField)value, diagnostics, context);
			case FormPackage.URL_FIELD:
				return validateURLField((URLField)value, diagnostics, context);
			case FormPackage.PHONE_NUMBER_FIELD:
				return validatePhoneNumberField((PhoneNumberField)value, diagnostics, context);
			case FormPackage.FORM_ASPECT:
				return validateFormAspect((FormAspect)value, diagnostics, context);
			case FormPackage.REFERENCE:
				return validateReference((Reference)value, diagnostics, context);
			case FormPackage.CHOICE_FIELD:
				return validateChoiceField((ChoiceField)value, diagnostics, context);
			case FormPackage.REGEX_FIELD:
				return validateRegexField((RegexField)value, diagnostics, context);
			case FormPackage.CLASS_REFERENCE:
				return validateClassReference((ClassReference)value, diagnostics, context);
			case FormPackage.PASSWORD_FIELD:
				return validatePasswordField((PasswordField)value, diagnostics, context);
			case FormPackage.VIRTUAL_FIELD:
				return validateVirtualField((VirtualField)value, diagnostics, context);
			case FormPackage.ACTION_FIELD:
				return validateActionField((ActionField)value, diagnostics, context);
			case FormPackage.TEXT_FIELD:
				return validateTextField((TextField)value, diagnostics, context);
			case FormPackage.FORM_SEARCH:
				return validateFormSearch((FormSearch)value, diagnostics, context);
			case FormPackage.NUMERICAL_FIELD:
				return validateNumericalField((NumericalField)value, diagnostics, context);
			case FormPackage.STATIC_TEXT:
				return validateStaticText((StaticText)value, diagnostics, context);
			case FormPackage.SEARCH_FORM_COLLECTION:
				return validateSearchFormCollection((SearchFormCollection)value, diagnostics, context);
			case FormPackage.SEARCH_FIELD:
				return validateSearchField((SearchField)value, diagnostics, context);
			case FormPackage.NUMERICAL_SEARCH_FIELD:
				return validateNumericalSearchField((NumericalSearchField)value, diagnostics, context);
			case FormPackage.CHAR_SEARCH_FIELD:
				return validateCharSearchField((CharSearchField)value, diagnostics, context);
			case FormPackage.DATE_SEARCH_FIELD:
				return validateDateSearchField((DateSearchField)value, diagnostics, context);
			case FormPackage.CHOICE_SEARCH_FIELD:
				return validateChoiceSearchField((ChoiceSearchField)value, diagnostics, context);
			case FormPackage.FILE_SEARCH_FIELD:
				return validateFileSearchField((FileSearchField)value, diagnostics, context);
			case FormPackage.FORM_GROUP_PRESENTATION_TYPE:
				return validateFormGroupPresentationType((FormGroupPresentationType)value, diagnostics, context);
			case FormPackage.TEXT_WIDGET_TYPE:
				return validateTextWidgetType((TextWidgetType)value, diagnostics, context);
			case FormPackage.CHOICE_WIDGET_TYPE:
				return validateChoiceWidgetType((ChoiceWidgetType)value, diagnostics, context);
			case FormPackage.MODEL_CHOICE_WIDGET_TYPE:
				return validateModelChoiceWidgetType((ModelChoiceWidgetType)value, diagnostics, context);
			case FormPackage.CHAR_FIELD_SEARCH_OPERATORS:
				return validateCharFieldSearchOperators((CharFieldSearchOperators)value, diagnostics, context);
			case FormPackage.NUMERICAL_FIELD_SEARCH_OPERATORS:
				return validateNumericalFieldSearchOperators((NumericalFieldSearchOperators)value, diagnostics, context);
			case FormPackage.CHOICE_FIELD_SEARCH_OPERATORS:
				return validateChoiceFieldSearchOperators((ChoiceFieldSearchOperators)value, diagnostics, context);
			case FormPackage.FILE_FIELD_SEARCH_OPERATORS:
				return validateFileFieldSearchOperators((FileFieldSearchOperators)value, diagnostics, context);
			case FormPackage.COMBINATION_OPERATORS:
				return validateCombinationOperators((CombinationOperators)value, diagnostics, context);
			case FormPackage.DATE_FIELD_SEARCH_OPERATORS:
				return validateDateFieldSearchOperators((DateFieldSearchOperators)value, diagnostics, context);
			default:
				return true;
		}
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean validateFormElement(FormElement formElement, DiagnosticChain diagnostics, Map<Object, Object> context) {
		boolean result = validate_EveryMultiplicityConforms(formElement, diagnostics, context);
		if (result || diagnostics != null) result &= validate_EveryDataValueConforms(formElement, diagnostics, context);
		if (result || diagnostics != null) result &= validate_EveryReferenceIsContained(formElement, diagnostics, context);
		if (result || diagnostics != null) result &= validate_EveryProxyResolves(formElement, diagnostics, context);
		if (result || diagnostics != null) result &= validate_UniqueID(formElement, diagnostics, context);
		if (result || diagnostics != null) result &= validate_EveryKeyUnique(formElement, diagnostics, context);
		if (result || diagnostics != null) result &= validate_EveryMapEntryUnique(formElement, diagnostics, context);
		if (result || diagnostics != null) result &= validateFormElement_noSpecialCharacters(formElement, diagnostics, context);
		if (result || diagnostics != null) result &= validateFormElement_validRef(formElement, diagnostics, context);
		return result;
	}

	/**
	 * Validates the noSpecialCharacters constraint of '<em>Element</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean validateFormElement_noSpecialCharacters(FormElement formElement, DiagnosticChain diagnostics, Map<Object, Object> context) {
        if (formElement_noSpecialCharactersInvOCL == null) {
			OCL.Helper helper = OCL_ENV.createOCLHelper();
			helper.setContext(FormPackage.Literals.FORM_ELEMENT);

			EAnnotation ocl = FormPackage.Literals.FORM_ELEMENT.getEAnnotation(OCL_ANNOTATION_SOURCE);
			String expr = ocl.getDetails().get("noSpecialCharacters");

			try {
				formElement_noSpecialCharactersInvOCL = helper.createInvariant(expr);
			}
			catch (ParserException e) {
				throw new UnsupportedOperationException(e.getLocalizedMessage());
			}
		}

		Query<EClassifier, ?, ?> query = OCL_ENV.createQuery(formElement_noSpecialCharactersInvOCL);

		if (!query.check(formElement)) {
			if (diagnostics != null) {
				diagnostics.add
					(new BasicDiagnostic
						((doThrowError( FormPackage.Literals.FORM_ELEMENT.getEAnnotation("http://www.eclipse.org/emf/2002/Ecore"),"noSpecialCharacters")? Diagnostic.ERROR : Diagnostic.WARNING),
						 DIAGNOSTIC_SOURCE,
						 0,
						 EcorePlugin.INSTANCE.getString("_UI_GenericConstraint_diagnostic", new Object[] { "noSpecialCharacters", getObjectLabel(formElement, context) }),
						 new Object[] { formElement }));
			}
			return false;
		}
		return true;
	}

	/**
	 * Validates the validRef constraint of '<em>Element</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean validateFormElement_validRef(FormElement formElement, DiagnosticChain diagnostics, Map<Object, Object> context) {
        if (formElement_validRefInvOCL == null) {
			OCL.Helper helper = OCL_ENV.createOCLHelper();
			helper.setContext(FormPackage.Literals.FORM_ELEMENT);

			EAnnotation ocl = FormPackage.Literals.FORM_ELEMENT.getEAnnotation(OCL_ANNOTATION_SOURCE);
			String expr = ocl.getDetails().get("validRef");

			try {
				formElement_validRefInvOCL = helper.createInvariant(expr);
			}
			catch (ParserException e) {
				throw new UnsupportedOperationException(e.getLocalizedMessage());
			}
		}

		Query<EClassifier, ?, ?> query = OCL_ENV.createQuery(formElement_validRefInvOCL);

		if (!query.check(formElement)) {
			if (diagnostics != null) {
				diagnostics.add
					(new BasicDiagnostic
						((doThrowError( FormPackage.Literals.FORM_ELEMENT.getEAnnotation("http://www.eclipse.org/emf/2002/Ecore"),"validRef")? Diagnostic.ERROR : Diagnostic.WARNING),
						 DIAGNOSTIC_SOURCE,
						 0,
						 EcorePlugin.INSTANCE.getString("_UI_GenericConstraint_diagnostic", new Object[] { "validRef", getObjectLabel(formElement, context) }),
						 new Object[] { formElement }));
			}
			return false;
		}
		return true;
	}

	private boolean doThrowError(EAnnotation ecore, String ruleName) {
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

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean validateFormGroup(FormGroup formGroup, DiagnosticChain diagnostics, Map<Object, Object> context) {
		boolean result = validate_EveryMultiplicityConforms(formGroup, diagnostics, context);
		if (result || diagnostics != null) result &= validate_EveryDataValueConforms(formGroup, diagnostics, context);
		if (result || diagnostics != null) result &= validate_EveryReferenceIsContained(formGroup, diagnostics, context);
		if (result || diagnostics != null) result &= validate_EveryProxyResolves(formGroup, diagnostics, context);
		if (result || diagnostics != null) result &= validate_UniqueID(formGroup, diagnostics, context);
		if (result || diagnostics != null) result &= validate_EveryKeyUnique(formGroup, diagnostics, context);
		if (result || diagnostics != null) result &= validate_EveryMapEntryUnique(formGroup, diagnostics, context);
		if (result || diagnostics != null) result &= validateFormElement_noSpecialCharacters(formGroup, diagnostics, context);
		if (result || diagnostics != null) result &= validateFormElement_validRef(formGroup, diagnostics, context);
		return result;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean validateField(Field field, DiagnosticChain diagnostics, Map<Object, Object> context) {
		boolean result = validate_EveryMultiplicityConforms(field, diagnostics, context);
		if (result || diagnostics != null) result &= validate_EveryDataValueConforms(field, diagnostics, context);
		if (result || diagnostics != null) result &= validate_EveryReferenceIsContained(field, diagnostics, context);
		if (result || diagnostics != null) result &= validate_EveryProxyResolves(field, diagnostics, context);
		if (result || diagnostics != null) result &= validate_UniqueID(field, diagnostics, context);
		if (result || diagnostics != null) result &= validate_EveryKeyUnique(field, diagnostics, context);
		if (result || diagnostics != null) result &= validate_EveryMapEntryUnique(field, diagnostics, context);
		if (result || diagnostics != null) result &= validateFormElement_noSpecialCharacters(field, diagnostics, context);
		if (result || diagnostics != null) result &= validateFormElement_validRef(field, diagnostics, context);
		if (result || diagnostics != null) result &= validateField_mandatoryHiddenAndNoDefaultValue(field, diagnostics, context);
		return result;
	}

	/**
	 * Validates the mandatoryHiddenAndNoDefaultValue constraint of '<em>Field</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean validateField_mandatoryHiddenAndNoDefaultValue(Field field, DiagnosticChain diagnostics, Map<Object, Object> context) {
        if (field_mandatoryHiddenAndNoDefaultValueInvOCL == null) {
			OCL.Helper helper = OCL_ENV.createOCLHelper();
			helper.setContext(FormPackage.Literals.FIELD);

			EAnnotation ocl = FormPackage.Literals.FIELD.getEAnnotation(OCL_ANNOTATION_SOURCE);
			String expr = ocl.getDetails().get("mandatoryHiddenAndNoDefaultValue");

			try {
				field_mandatoryHiddenAndNoDefaultValueInvOCL = helper.createInvariant(expr);
			}
			catch (ParserException e) {
				throw new UnsupportedOperationException(e.getLocalizedMessage());
			}
		}

		Query<EClassifier, ?, ?> query = OCL_ENV.createQuery(field_mandatoryHiddenAndNoDefaultValueInvOCL);

		if (!query.check(field)) {
			if (diagnostics != null) {
				diagnostics.add
					(new BasicDiagnostic
						((doThrowError( FormPackage.Literals.FIELD.getEAnnotation("http://www.eclipse.org/emf/2002/Ecore"),"mandatoryHiddenAndNoDefaultValue")? Diagnostic.ERROR : Diagnostic.WARNING),
						 DIAGNOSTIC_SOURCE,
						 0,
						 EcorePlugin.INSTANCE.getString("_UI_GenericConstraint_diagnostic", new Object[] { "mandatoryHiddenAndNoDefaultValue", getObjectLabel(field, context) }),
						 new Object[] { field }));
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
	public boolean validateBooleanField(BooleanField booleanField, DiagnosticChain diagnostics, Map<Object, Object> context) {
		boolean result = validate_EveryMultiplicityConforms(booleanField, diagnostics, context);
		if (result || diagnostics != null) result &= validate_EveryDataValueConforms(booleanField, diagnostics, context);
		if (result || diagnostics != null) result &= validate_EveryReferenceIsContained(booleanField, diagnostics, context);
		if (result || diagnostics != null) result &= validate_EveryProxyResolves(booleanField, diagnostics, context);
		if (result || diagnostics != null) result &= validate_UniqueID(booleanField, diagnostics, context);
		if (result || diagnostics != null) result &= validate_EveryKeyUnique(booleanField, diagnostics, context);
		if (result || diagnostics != null) result &= validate_EveryMapEntryUnique(booleanField, diagnostics, context);
		if (result || diagnostics != null) result &= validateFormElement_noSpecialCharacters(booleanField, diagnostics, context);
		if (result || diagnostics != null) result &= validateFormElement_validRef(booleanField, diagnostics, context);
		if (result || diagnostics != null) result &= validateField_mandatoryHiddenAndNoDefaultValue(booleanField, diagnostics, context);
		return result;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean validateCharField(CharField charField, DiagnosticChain diagnostics, Map<Object, Object> context) {
		boolean result = validate_EveryMultiplicityConforms(charField, diagnostics, context);
		if (result || diagnostics != null) result &= validate_EveryDataValueConforms(charField, diagnostics, context);
		if (result || diagnostics != null) result &= validate_EveryReferenceIsContained(charField, diagnostics, context);
		if (result || diagnostics != null) result &= validate_EveryProxyResolves(charField, diagnostics, context);
		if (result || diagnostics != null) result &= validate_UniqueID(charField, diagnostics, context);
		if (result || diagnostics != null) result &= validate_EveryKeyUnique(charField, diagnostics, context);
		if (result || diagnostics != null) result &= validate_EveryMapEntryUnique(charField, diagnostics, context);
		if (result || diagnostics != null) result &= validateFormElement_noSpecialCharacters(charField, diagnostics, context);
		if (result || diagnostics != null) result &= validateFormElement_validRef(charField, diagnostics, context);
		if (result || diagnostics != null) result &= validateField_mandatoryHiddenAndNoDefaultValue(charField, diagnostics, context);
		if (result || diagnostics != null) result &= validateCharField_MinSuperiorToMax(charField, diagnostics, context);
		return result;
	}

	/**
	 * Validates the MinSuperiorToMax constraint of '<em>Char Field</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean validateCharField_MinSuperiorToMax(CharField charField, DiagnosticChain diagnostics, Map<Object, Object> context) {
        if (charField_MinSuperiorToMaxInvOCL == null) {
			OCL.Helper helper = OCL_ENV.createOCLHelper();
			helper.setContext(FormPackage.Literals.CHAR_FIELD);

			EAnnotation ocl = FormPackage.Literals.CHAR_FIELD.getEAnnotation(OCL_ANNOTATION_SOURCE);
			String expr = ocl.getDetails().get("MinSuperiorToMax");

			try {
				charField_MinSuperiorToMaxInvOCL = helper.createInvariant(expr);
			}
			catch (ParserException e) {
				throw new UnsupportedOperationException(e.getLocalizedMessage());
			}
		}

		Query<EClassifier, ?, ?> query = OCL_ENV.createQuery(charField_MinSuperiorToMaxInvOCL);

		if (!query.check(charField)) {
			if (diagnostics != null) {
				diagnostics.add
					(new BasicDiagnostic
						((doThrowError( FormPackage.Literals.CHAR_FIELD.getEAnnotation("http://www.eclipse.org/emf/2002/Ecore"),"MinSuperiorToMax")? Diagnostic.ERROR : Diagnostic.WARNING),
						 DIAGNOSTIC_SOURCE,
						 0,
						 EcorePlugin.INSTANCE.getString("_UI_GenericConstraint_diagnostic", new Object[] { "MinSuperiorToMax", getObjectLabel(charField, context) }),
						 new Object[] { charField }));
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
	public boolean validateDateField(DateField dateField, DiagnosticChain diagnostics, Map<Object, Object> context) {
		boolean result = validate_EveryMultiplicityConforms(dateField, diagnostics, context);
		if (result || diagnostics != null) result &= validate_EveryDataValueConforms(dateField, diagnostics, context);
		if (result || diagnostics != null) result &= validate_EveryReferenceIsContained(dateField, diagnostics, context);
		if (result || diagnostics != null) result &= validate_EveryProxyResolves(dateField, diagnostics, context);
		if (result || diagnostics != null) result &= validate_UniqueID(dateField, diagnostics, context);
		if (result || diagnostics != null) result &= validate_EveryKeyUnique(dateField, diagnostics, context);
		if (result || diagnostics != null) result &= validate_EveryMapEntryUnique(dateField, diagnostics, context);
		if (result || diagnostics != null) result &= validateFormElement_noSpecialCharacters(dateField, diagnostics, context);
		if (result || diagnostics != null) result &= validateFormElement_validRef(dateField, diagnostics, context);
		if (result || diagnostics != null) result &= validateField_mandatoryHiddenAndNoDefaultValue(dateField, diagnostics, context);
		return result;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean validateDateTimeField(DateTimeField dateTimeField, DiagnosticChain diagnostics, Map<Object, Object> context) {
		boolean result = validate_EveryMultiplicityConforms(dateTimeField, diagnostics, context);
		if (result || diagnostics != null) result &= validate_EveryDataValueConforms(dateTimeField, diagnostics, context);
		if (result || diagnostics != null) result &= validate_EveryReferenceIsContained(dateTimeField, diagnostics, context);
		if (result || diagnostics != null) result &= validate_EveryProxyResolves(dateTimeField, diagnostics, context);
		if (result || diagnostics != null) result &= validate_UniqueID(dateTimeField, diagnostics, context);
		if (result || diagnostics != null) result &= validate_EveryKeyUnique(dateTimeField, diagnostics, context);
		if (result || diagnostics != null) result &= validate_EveryMapEntryUnique(dateTimeField, diagnostics, context);
		if (result || diagnostics != null) result &= validateFormElement_noSpecialCharacters(dateTimeField, diagnostics, context);
		if (result || diagnostics != null) result &= validateFormElement_validRef(dateTimeField, diagnostics, context);
		if (result || diagnostics != null) result &= validateField_mandatoryHiddenAndNoDefaultValue(dateTimeField, diagnostics, context);
		return result;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean validateDecimalField(DecimalField decimalField, DiagnosticChain diagnostics, Map<Object, Object> context) {
		boolean result = validate_EveryMultiplicityConforms(decimalField, diagnostics, context);
		if (result || diagnostics != null) result &= validate_EveryDataValueConforms(decimalField, diagnostics, context);
		if (result || diagnostics != null) result &= validate_EveryReferenceIsContained(decimalField, diagnostics, context);
		if (result || diagnostics != null) result &= validate_EveryProxyResolves(decimalField, diagnostics, context);
		if (result || diagnostics != null) result &= validate_UniqueID(decimalField, diagnostics, context);
		if (result || diagnostics != null) result &= validate_EveryKeyUnique(decimalField, diagnostics, context);
		if (result || diagnostics != null) result &= validate_EveryMapEntryUnique(decimalField, diagnostics, context);
		if (result || diagnostics != null) result &= validateFormElement_noSpecialCharacters(decimalField, diagnostics, context);
		if (result || diagnostics != null) result &= validateFormElement_validRef(decimalField, diagnostics, context);
		if (result || diagnostics != null) result &= validateField_mandatoryHiddenAndNoDefaultValue(decimalField, diagnostics, context);
		return result;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean validateFloatField(FloatField floatField, DiagnosticChain diagnostics, Map<Object, Object> context) {
		boolean result = validate_EveryMultiplicityConforms(floatField, diagnostics, context);
		if (result || diagnostics != null) result &= validate_EveryDataValueConforms(floatField, diagnostics, context);
		if (result || diagnostics != null) result &= validate_EveryReferenceIsContained(floatField, diagnostics, context);
		if (result || diagnostics != null) result &= validate_EveryProxyResolves(floatField, diagnostics, context);
		if (result || diagnostics != null) result &= validate_UniqueID(floatField, diagnostics, context);
		if (result || diagnostics != null) result &= validate_EveryKeyUnique(floatField, diagnostics, context);
		if (result || diagnostics != null) result &= validate_EveryMapEntryUnique(floatField, diagnostics, context);
		if (result || diagnostics != null) result &= validateFormElement_noSpecialCharacters(floatField, diagnostics, context);
		if (result || diagnostics != null) result &= validateFormElement_validRef(floatField, diagnostics, context);
		if (result || diagnostics != null) result &= validateField_mandatoryHiddenAndNoDefaultValue(floatField, diagnostics, context);
		return result;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean validateIntegerField(IntegerField integerField, DiagnosticChain diagnostics, Map<Object, Object> context) {
		boolean result = validate_EveryMultiplicityConforms(integerField, diagnostics, context);
		if (result || diagnostics != null) result &= validate_EveryDataValueConforms(integerField, diagnostics, context);
		if (result || diagnostics != null) result &= validate_EveryReferenceIsContained(integerField, diagnostics, context);
		if (result || diagnostics != null) result &= validate_EveryProxyResolves(integerField, diagnostics, context);
		if (result || diagnostics != null) result &= validate_UniqueID(integerField, diagnostics, context);
		if (result || diagnostics != null) result &= validate_EveryKeyUnique(integerField, diagnostics, context);
		if (result || diagnostics != null) result &= validate_EveryMapEntryUnique(integerField, diagnostics, context);
		if (result || diagnostics != null) result &= validateFormElement_noSpecialCharacters(integerField, diagnostics, context);
		if (result || diagnostics != null) result &= validateFormElement_validRef(integerField, diagnostics, context);
		if (result || diagnostics != null) result &= validateField_mandatoryHiddenAndNoDefaultValue(integerField, diagnostics, context);
		return result;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean validateModelChoiceField(ModelChoiceField modelChoiceField, DiagnosticChain diagnostics, Map<Object, Object> context) {
		boolean result = validate_EveryMultiplicityConforms(modelChoiceField, diagnostics, context);
		if (result || diagnostics != null) result &= validate_EveryDataValueConforms(modelChoiceField, diagnostics, context);
		if (result || diagnostics != null) result &= validate_EveryReferenceIsContained(modelChoiceField, diagnostics, context);
		if (result || diagnostics != null) result &= validate_EveryProxyResolves(modelChoiceField, diagnostics, context);
		if (result || diagnostics != null) result &= validate_UniqueID(modelChoiceField, diagnostics, context);
		if (result || diagnostics != null) result &= validate_EveryKeyUnique(modelChoiceField, diagnostics, context);
		if (result || diagnostics != null) result &= validate_EveryMapEntryUnique(modelChoiceField, diagnostics, context);
		if (result || diagnostics != null) result &= validateFormElement_noSpecialCharacters(modelChoiceField, diagnostics, context);
		if (result || diagnostics != null) result &= validateFormElement_validRef(modelChoiceField, diagnostics, context);
		if (result || diagnostics != null) result &= validateField_mandatoryHiddenAndNoDefaultValue(modelChoiceField, diagnostics, context);
		if (result || diagnostics != null) result &= validateClassReference_mustReferenceClass(modelChoiceField, diagnostics, context);
		return result;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean validateEmailField(EmailField emailField, DiagnosticChain diagnostics, Map<Object, Object> context) {
		boolean result = validate_EveryMultiplicityConforms(emailField, diagnostics, context);
		if (result || diagnostics != null) result &= validate_EveryDataValueConforms(emailField, diagnostics, context);
		if (result || diagnostics != null) result &= validate_EveryReferenceIsContained(emailField, diagnostics, context);
		if (result || diagnostics != null) result &= validate_EveryProxyResolves(emailField, diagnostics, context);
		if (result || diagnostics != null) result &= validate_UniqueID(emailField, diagnostics, context);
		if (result || diagnostics != null) result &= validate_EveryKeyUnique(emailField, diagnostics, context);
		if (result || diagnostics != null) result &= validate_EveryMapEntryUnique(emailField, diagnostics, context);
		if (result || diagnostics != null) result &= validateFormElement_noSpecialCharacters(emailField, diagnostics, context);
		if (result || diagnostics != null) result &= validateFormElement_validRef(emailField, diagnostics, context);
		if (result || diagnostics != null) result &= validateField_mandatoryHiddenAndNoDefaultValue(emailField, diagnostics, context);
		if (result || diagnostics != null) result &= validateCharField_MinSuperiorToMax(emailField, diagnostics, context);
		return result;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean validateFileField(FileField fileField, DiagnosticChain diagnostics, Map<Object, Object> context) {
		boolean result = validate_EveryMultiplicityConforms(fileField, diagnostics, context);
		if (result || diagnostics != null) result &= validate_EveryDataValueConforms(fileField, diagnostics, context);
		if (result || diagnostics != null) result &= validate_EveryReferenceIsContained(fileField, diagnostics, context);
		if (result || diagnostics != null) result &= validate_EveryProxyResolves(fileField, diagnostics, context);
		if (result || diagnostics != null) result &= validate_UniqueID(fileField, diagnostics, context);
		if (result || diagnostics != null) result &= validate_EveryKeyUnique(fileField, diagnostics, context);
		if (result || diagnostics != null) result &= validate_EveryMapEntryUnique(fileField, diagnostics, context);
		if (result || diagnostics != null) result &= validateFormElement_noSpecialCharacters(fileField, diagnostics, context);
		if (result || diagnostics != null) result &= validateFormElement_validRef(fileField, diagnostics, context);
		if (result || diagnostics != null) result &= validateField_mandatoryHiddenAndNoDefaultValue(fileField, diagnostics, context);
		return result;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean validateImageField(ImageField imageField, DiagnosticChain diagnostics, Map<Object, Object> context) {
		boolean result = validate_EveryMultiplicityConforms(imageField, diagnostics, context);
		if (result || diagnostics != null) result &= validate_EveryDataValueConforms(imageField, diagnostics, context);
		if (result || diagnostics != null) result &= validate_EveryReferenceIsContained(imageField, diagnostics, context);
		if (result || diagnostics != null) result &= validate_EveryProxyResolves(imageField, diagnostics, context);
		if (result || diagnostics != null) result &= validate_UniqueID(imageField, diagnostics, context);
		if (result || diagnostics != null) result &= validate_EveryKeyUnique(imageField, diagnostics, context);
		if (result || diagnostics != null) result &= validate_EveryMapEntryUnique(imageField, diagnostics, context);
		if (result || diagnostics != null) result &= validateFormElement_noSpecialCharacters(imageField, diagnostics, context);
		if (result || diagnostics != null) result &= validateFormElement_validRef(imageField, diagnostics, context);
		if (result || diagnostics != null) result &= validateField_mandatoryHiddenAndNoDefaultValue(imageField, diagnostics, context);
		return result;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean validateTimeField(TimeField timeField, DiagnosticChain diagnostics, Map<Object, Object> context) {
		boolean result = validate_EveryMultiplicityConforms(timeField, diagnostics, context);
		if (result || diagnostics != null) result &= validate_EveryDataValueConforms(timeField, diagnostics, context);
		if (result || diagnostics != null) result &= validate_EveryReferenceIsContained(timeField, diagnostics, context);
		if (result || diagnostics != null) result &= validate_EveryProxyResolves(timeField, diagnostics, context);
		if (result || diagnostics != null) result &= validate_UniqueID(timeField, diagnostics, context);
		if (result || diagnostics != null) result &= validate_EveryKeyUnique(timeField, diagnostics, context);
		if (result || diagnostics != null) result &= validate_EveryMapEntryUnique(timeField, diagnostics, context);
		if (result || diagnostics != null) result &= validateFormElement_noSpecialCharacters(timeField, diagnostics, context);
		if (result || diagnostics != null) result &= validateFormElement_validRef(timeField, diagnostics, context);
		if (result || diagnostics != null) result &= validateField_mandatoryHiddenAndNoDefaultValue(timeField, diagnostics, context);
		return result;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean validateURLField(URLField urlField, DiagnosticChain diagnostics, Map<Object, Object> context) {
		boolean result = validate_EveryMultiplicityConforms(urlField, diagnostics, context);
		if (result || diagnostics != null) result &= validate_EveryDataValueConforms(urlField, diagnostics, context);
		if (result || diagnostics != null) result &= validate_EveryReferenceIsContained(urlField, diagnostics, context);
		if (result || diagnostics != null) result &= validate_EveryProxyResolves(urlField, diagnostics, context);
		if (result || diagnostics != null) result &= validate_UniqueID(urlField, diagnostics, context);
		if (result || diagnostics != null) result &= validate_EveryKeyUnique(urlField, diagnostics, context);
		if (result || diagnostics != null) result &= validate_EveryMapEntryUnique(urlField, diagnostics, context);
		if (result || diagnostics != null) result &= validateFormElement_noSpecialCharacters(urlField, diagnostics, context);
		if (result || diagnostics != null) result &= validateFormElement_validRef(urlField, diagnostics, context);
		if (result || diagnostics != null) result &= validateField_mandatoryHiddenAndNoDefaultValue(urlField, diagnostics, context);
		if (result || diagnostics != null) result &= validateCharField_MinSuperiorToMax(urlField, diagnostics, context);
		return result;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean validatePhoneNumberField(PhoneNumberField phoneNumberField, DiagnosticChain diagnostics, Map<Object, Object> context) {
		boolean result = validate_EveryMultiplicityConforms(phoneNumberField, diagnostics, context);
		if (result || diagnostics != null) result &= validate_EveryDataValueConforms(phoneNumberField, diagnostics, context);
		if (result || diagnostics != null) result &= validate_EveryReferenceIsContained(phoneNumberField, diagnostics, context);
		if (result || diagnostics != null) result &= validate_EveryProxyResolves(phoneNumberField, diagnostics, context);
		if (result || diagnostics != null) result &= validate_UniqueID(phoneNumberField, diagnostics, context);
		if (result || diagnostics != null) result &= validate_EveryKeyUnique(phoneNumberField, diagnostics, context);
		if (result || diagnostics != null) result &= validate_EveryMapEntryUnique(phoneNumberField, diagnostics, context);
		if (result || diagnostics != null) result &= validateFormElement_noSpecialCharacters(phoneNumberField, diagnostics, context);
		if (result || diagnostics != null) result &= validateFormElement_validRef(phoneNumberField, diagnostics, context);
		if (result || diagnostics != null) result &= validateField_mandatoryHiddenAndNoDefaultValue(phoneNumberField, diagnostics, context);
		if (result || diagnostics != null) result &= validateCharField_MinSuperiorToMax(phoneNumberField, diagnostics, context);
		return result;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean validateFormAspect(FormAspect formAspect, DiagnosticChain diagnostics, Map<Object, Object> context) {
		boolean result = validate_EveryMultiplicityConforms(formAspect, diagnostics, context);
		if (result || diagnostics != null) result &= validate_EveryDataValueConforms(formAspect, diagnostics, context);
		if (result || diagnostics != null) result &= validate_EveryReferenceIsContained(formAspect, diagnostics, context);
		if (result || diagnostics != null) result &= validate_EveryProxyResolves(formAspect, diagnostics, context);
		if (result || diagnostics != null) result &= validate_UniqueID(formAspect, diagnostics, context);
		if (result || diagnostics != null) result &= validate_EveryKeyUnique(formAspect, diagnostics, context);
		if (result || diagnostics != null) result &= validate_EveryMapEntryUnique(formAspect, diagnostics, context);
		if (result || diagnostics != null) result &= validateFormElement_noSpecialCharacters(formAspect, diagnostics, context);
		if (result || diagnostics != null) result &= validateFormElement_validRef(formAspect, diagnostics, context);
		return result;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean validateFormClass(FormClass formClass, DiagnosticChain diagnostics, Map<Object, Object> context) {
		boolean result = validate_EveryMultiplicityConforms(formClass, diagnostics, context);
		if (result || diagnostics != null) result &= validate_EveryDataValueConforms(formClass, diagnostics, context);
		if (result || diagnostics != null) result &= validate_EveryReferenceIsContained(formClass, diagnostics, context);
		if (result || diagnostics != null) result &= validate_EveryProxyResolves(formClass, diagnostics, context);
		if (result || diagnostics != null) result &= validate_UniqueID(formClass, diagnostics, context);
		if (result || diagnostics != null) result &= validate_EveryKeyUnique(formClass, diagnostics, context);
		if (result || diagnostics != null) result &= validate_EveryMapEntryUnique(formClass, diagnostics, context);
		if (result || diagnostics != null) result &= validateFormElement_noSpecialCharacters(formClass, diagnostics, context);
		if (result || diagnostics != null) result &= validateFormElement_validRef(formClass, diagnostics, context);
		if (result || diagnostics != null) result &= validateFormContainer_validName(formClass, diagnostics, context);
		if (result || diagnostics != null) result &= validateFormContainer_TwoFormsWithSameName(formClass, diagnostics, context);
		if (result || diagnostics != null) result &= validateClassReference_mustReferenceClass(formClass, diagnostics, context);
		return result;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean validateReference(Reference reference, DiagnosticChain diagnostics, Map<Object, Object> context) {
		boolean result = validate_EveryMultiplicityConforms(reference, diagnostics, context);
		if (result || diagnostics != null) result &= validate_EveryDataValueConforms(reference, diagnostics, context);
		if (result || diagnostics != null) result &= validate_EveryReferenceIsContained(reference, diagnostics, context);
		if (result || diagnostics != null) result &= validate_EveryProxyResolves(reference, diagnostics, context);
		if (result || diagnostics != null) result &= validate_UniqueID(reference, diagnostics, context);
		if (result || diagnostics != null) result &= validate_EveryKeyUnique(reference, diagnostics, context);
		if (result || diagnostics != null) result &= validate_EveryMapEntryUnique(reference, diagnostics, context);
		if (result || diagnostics != null) result &= validateFormElement_noSpecialCharacters(reference, diagnostics, context);
		if (result || diagnostics != null) result &= validateFormElement_validRef(reference, diagnostics, context);
		if (result || diagnostics != null) result &= validateField_mandatoryHiddenAndNoDefaultValue(reference, diagnostics, context);
		if (result || diagnostics != null) result &= validateClassReference_mustReferenceClass(reference, diagnostics, context);
		return result;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean validateFormCollection(FormCollection formCollection, DiagnosticChain diagnostics, Map<Object, Object> context) {
		return validate_EveryDefaultConstraint(formCollection, diagnostics, context);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean validateChoiceField(ChoiceField choiceField, DiagnosticChain diagnostics, Map<Object, Object> context) {
		boolean result = validate_EveryMultiplicityConforms(choiceField, diagnostics, context);
		if (result || diagnostics != null) result &= validate_EveryDataValueConforms(choiceField, diagnostics, context);
		if (result || diagnostics != null) result &= validate_EveryReferenceIsContained(choiceField, diagnostics, context);
		if (result || diagnostics != null) result &= validate_EveryProxyResolves(choiceField, diagnostics, context);
		if (result || diagnostics != null) result &= validate_UniqueID(choiceField, diagnostics, context);
		if (result || diagnostics != null) result &= validate_EveryKeyUnique(choiceField, diagnostics, context);
		if (result || diagnostics != null) result &= validate_EveryMapEntryUnique(choiceField, diagnostics, context);
		if (result || diagnostics != null) result &= validateFormElement_noSpecialCharacters(choiceField, diagnostics, context);
		if (result || diagnostics != null) result &= validateFormElement_validRef(choiceField, diagnostics, context);
		if (result || diagnostics != null) result &= validateField_mandatoryHiddenAndNoDefaultValue(choiceField, diagnostics, context);
		return result;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean validateRegexField(RegexField regexField, DiagnosticChain diagnostics, Map<Object, Object> context) {
		boolean result = validate_EveryMultiplicityConforms(regexField, diagnostics, context);
		if (result || diagnostics != null) result &= validate_EveryDataValueConforms(regexField, diagnostics, context);
		if (result || diagnostics != null) result &= validate_EveryReferenceIsContained(regexField, diagnostics, context);
		if (result || diagnostics != null) result &= validate_EveryProxyResolves(regexField, diagnostics, context);
		if (result || diagnostics != null) result &= validate_UniqueID(regexField, diagnostics, context);
		if (result || diagnostics != null) result &= validate_EveryKeyUnique(regexField, diagnostics, context);
		if (result || diagnostics != null) result &= validate_EveryMapEntryUnique(regexField, diagnostics, context);
		if (result || diagnostics != null) result &= validateFormElement_noSpecialCharacters(regexField, diagnostics, context);
		if (result || diagnostics != null) result &= validateFormElement_validRef(regexField, diagnostics, context);
		if (result || diagnostics != null) result &= validateField_mandatoryHiddenAndNoDefaultValue(regexField, diagnostics, context);
		if (result || diagnostics != null) result &= validateCharField_MinSuperiorToMax(regexField, diagnostics, context);
		return result;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean validateClassReference(ClassReference classReference, DiagnosticChain diagnostics, Map<Object, Object> context) {
		boolean result = validate_EveryMultiplicityConforms(classReference, diagnostics, context);
		if (result || diagnostics != null) result &= validate_EveryDataValueConforms(classReference, diagnostics, context);
		if (result || diagnostics != null) result &= validate_EveryReferenceIsContained(classReference, diagnostics, context);
		if (result || diagnostics != null) result &= validate_EveryProxyResolves(classReference, diagnostics, context);
		if (result || diagnostics != null) result &= validate_UniqueID(classReference, diagnostics, context);
		if (result || diagnostics != null) result &= validate_EveryKeyUnique(classReference, diagnostics, context);
		if (result || diagnostics != null) result &= validate_EveryMapEntryUnique(classReference, diagnostics, context);
		if (result || diagnostics != null) result &= validateClassReference_mustReferenceClass(classReference, diagnostics, context);
		return result;
	}

	/**
	 * Validates the mustReferenceClass constraint of '<em>Class Reference</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean validateClassReference_mustReferenceClass(ClassReference classReference, DiagnosticChain diagnostics, Map<Object, Object> context) {
        if (classReference_mustReferenceClassInvOCL == null) {
			OCL.Helper helper = OCL_ENV.createOCLHelper();
			helper.setContext(FormPackage.Literals.CLASS_REFERENCE);

			EAnnotation ocl = FormPackage.Literals.CLASS_REFERENCE.getEAnnotation(OCL_ANNOTATION_SOURCE);
			String expr = ocl.getDetails().get("mustReferenceClass");

			try {
				classReference_mustReferenceClassInvOCL = helper.createInvariant(expr);
			}
			catch (ParserException e) {
				throw new UnsupportedOperationException(e.getLocalizedMessage());
			}
		}

		Query<EClassifier, ?, ?> query = OCL_ENV.createQuery(classReference_mustReferenceClassInvOCL);

		if (!query.check(classReference)) {
			if (diagnostics != null) {
				diagnostics.add
					(new BasicDiagnostic
						((doThrowError( FormPackage.Literals.CLASS_REFERENCE.getEAnnotation("http://www.eclipse.org/emf/2002/Ecore"),"mustReferenceClass")? Diagnostic.ERROR : Diagnostic.WARNING),
						 DIAGNOSTIC_SOURCE,
						 0,
						 EcorePlugin.INSTANCE.getString("_UI_GenericConstraint_diagnostic", new Object[] { "mustReferenceClass", getObjectLabel(classReference, context) }),
						 new Object[] { classReference }));
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
	public boolean validatePasswordField(PasswordField passwordField, DiagnosticChain diagnostics, Map<Object, Object> context) {
		boolean result = validate_EveryMultiplicityConforms(passwordField, diagnostics, context);
		if (result || diagnostics != null) result &= validate_EveryDataValueConforms(passwordField, diagnostics, context);
		if (result || diagnostics != null) result &= validate_EveryReferenceIsContained(passwordField, diagnostics, context);
		if (result || diagnostics != null) result &= validate_EveryProxyResolves(passwordField, diagnostics, context);
		if (result || diagnostics != null) result &= validate_UniqueID(passwordField, diagnostics, context);
		if (result || diagnostics != null) result &= validate_EveryKeyUnique(passwordField, diagnostics, context);
		if (result || diagnostics != null) result &= validate_EveryMapEntryUnique(passwordField, diagnostics, context);
		if (result || diagnostics != null) result &= validateFormElement_noSpecialCharacters(passwordField, diagnostics, context);
		if (result || diagnostics != null) result &= validateFormElement_validRef(passwordField, diagnostics, context);
		if (result || diagnostics != null) result &= validateField_mandatoryHiddenAndNoDefaultValue(passwordField, diagnostics, context);
		if (result || diagnostics != null) result &= validateCharField_MinSuperiorToMax(passwordField, diagnostics, context);
		return result;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean validateVirtualField(VirtualField virtualField, DiagnosticChain diagnostics, Map<Object, Object> context) {
		boolean result = validate_EveryMultiplicityConforms(virtualField, diagnostics, context);
		if (result || diagnostics != null) result &= validate_EveryDataValueConforms(virtualField, diagnostics, context);
		if (result || diagnostics != null) result &= validate_EveryReferenceIsContained(virtualField, diagnostics, context);
		if (result || diagnostics != null) result &= validate_EveryProxyResolves(virtualField, diagnostics, context);
		if (result || diagnostics != null) result &= validate_UniqueID(virtualField, diagnostics, context);
		if (result || diagnostics != null) result &= validate_EveryKeyUnique(virtualField, diagnostics, context);
		if (result || diagnostics != null) result &= validate_EveryMapEntryUnique(virtualField, diagnostics, context);
		if (result || diagnostics != null) result &= validateFormElement_noSpecialCharacters(virtualField, diagnostics, context);
		if (result || diagnostics != null) result &= validateFormElement_validRef(virtualField, diagnostics, context);
		if (result || diagnostics != null) result &= validateField_mandatoryHiddenAndNoDefaultValue(virtualField, diagnostics, context);
		if (result || diagnostics != null) result &= validateVirtualField_NoLinkForVirtualField(virtualField, diagnostics, context);
		return result;
	}

	/**
	 * Validates the NoLinkForVirtualField constraint of '<em>Virtual Field</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean validateVirtualField_NoLinkForVirtualField(VirtualField virtualField, DiagnosticChain diagnostics, Map<Object, Object> context) {
        if (virtualField_NoLinkForVirtualFieldInvOCL == null) {
			OCL.Helper helper = OCL_ENV.createOCLHelper();
			helper.setContext(FormPackage.Literals.VIRTUAL_FIELD);

			EAnnotation ocl = FormPackage.Literals.VIRTUAL_FIELD.getEAnnotation(OCL_ANNOTATION_SOURCE);
			String expr = ocl.getDetails().get("NoLinkForVirtualField");

			try {
				virtualField_NoLinkForVirtualFieldInvOCL = helper.createInvariant(expr);
			}
			catch (ParserException e) {
				throw new UnsupportedOperationException(e.getLocalizedMessage());
			}
		}

		Query<EClassifier, ?, ?> query = OCL_ENV.createQuery(virtualField_NoLinkForVirtualFieldInvOCL);

		if (!query.check(virtualField)) {
			if (diagnostics != null) {
				diagnostics.add
					(new BasicDiagnostic
						((doThrowError( FormPackage.Literals.VIRTUAL_FIELD.getEAnnotation("http://www.eclipse.org/emf/2002/Ecore"),"NoLinkForVirtualField")? Diagnostic.ERROR : Diagnostic.WARNING),
						 DIAGNOSTIC_SOURCE,
						 0,
						 EcorePlugin.INSTANCE.getString("_UI_GenericConstraint_diagnostic", new Object[] { "NoLinkForVirtualField", getObjectLabel(virtualField, context) }),
						 new Object[] { virtualField }));
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
	public boolean validateActionField(ActionField actionField, DiagnosticChain diagnostics, Map<Object, Object> context) {
		boolean result = validate_EveryMultiplicityConforms(actionField, diagnostics, context);
		if (result || diagnostics != null) result &= validate_EveryDataValueConforms(actionField, diagnostics, context);
		if (result || diagnostics != null) result &= validate_EveryReferenceIsContained(actionField, diagnostics, context);
		if (result || diagnostics != null) result &= validate_EveryProxyResolves(actionField, diagnostics, context);
		if (result || diagnostics != null) result &= validate_UniqueID(actionField, diagnostics, context);
		if (result || diagnostics != null) result &= validate_EveryKeyUnique(actionField, diagnostics, context);
		if (result || diagnostics != null) result &= validate_EveryMapEntryUnique(actionField, diagnostics, context);
		if (result || diagnostics != null) result &= validateFormElement_noSpecialCharacters(actionField, diagnostics, context);
		if (result || diagnostics != null) result &= validateFormElement_validRef(actionField, diagnostics, context);
		if (result || diagnostics != null) result &= validateField_mandatoryHiddenAndNoDefaultValue(actionField, diagnostics, context);
		return result;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean validateTextField(TextField textField, DiagnosticChain diagnostics, Map<Object, Object> context) {
		boolean result = validate_EveryMultiplicityConforms(textField, diagnostics, context);
		if (result || diagnostics != null) result &= validate_EveryDataValueConforms(textField, diagnostics, context);
		if (result || diagnostics != null) result &= validate_EveryReferenceIsContained(textField, diagnostics, context);
		if (result || diagnostics != null) result &= validate_EveryProxyResolves(textField, diagnostics, context);
		if (result || diagnostics != null) result &= validate_UniqueID(textField, diagnostics, context);
		if (result || diagnostics != null) result &= validate_EveryKeyUnique(textField, diagnostics, context);
		if (result || diagnostics != null) result &= validate_EveryMapEntryUnique(textField, diagnostics, context);
		if (result || diagnostics != null) result &= validateFormElement_noSpecialCharacters(textField, diagnostics, context);
		if (result || diagnostics != null) result &= validateFormElement_validRef(textField, diagnostics, context);
		if (result || diagnostics != null) result &= validateField_mandatoryHiddenAndNoDefaultValue(textField, diagnostics, context);
		if (result || diagnostics != null) result &= validateCharField_MinSuperiorToMax(textField, diagnostics, context);
		return result;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean validateFormSearch(FormSearch formSearch, DiagnosticChain diagnostics, Map<Object, Object> context) {
		boolean result = validate_EveryMultiplicityConforms(formSearch, diagnostics, context);
		if (result || diagnostics != null) result &= validate_EveryDataValueConforms(formSearch, diagnostics, context);
		if (result || diagnostics != null) result &= validate_EveryReferenceIsContained(formSearch, diagnostics, context);
		if (result || diagnostics != null) result &= validate_EveryProxyResolves(formSearch, diagnostics, context);
		if (result || diagnostics != null) result &= validate_UniqueID(formSearch, diagnostics, context);
		if (result || diagnostics != null) result &= validate_EveryKeyUnique(formSearch, diagnostics, context);
		if (result || diagnostics != null) result &= validate_EveryMapEntryUnique(formSearch, diagnostics, context);
		if (result || diagnostics != null) result &= validateFormElement_noSpecialCharacters(formSearch, diagnostics, context);
		if (result || diagnostics != null) result &= validateFormElement_validRef(formSearch, diagnostics, context);
		if (result || diagnostics != null) result &= validateFormContainer_validName(formSearch, diagnostics, context);
		if (result || diagnostics != null) result &= validateFormContainer_TwoFormsWithSameName(formSearch, diagnostics, context);
		if (result || diagnostics != null) result &= validateClassReference_mustReferenceClass(formSearch, diagnostics, context);
		return result;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean validateNumericalField(NumericalField numericalField, DiagnosticChain diagnostics, Map<Object, Object> context) {
		boolean result = validate_EveryMultiplicityConforms(numericalField, diagnostics, context);
		if (result || diagnostics != null) result &= validate_EveryDataValueConforms(numericalField, diagnostics, context);
		if (result || diagnostics != null) result &= validate_EveryReferenceIsContained(numericalField, diagnostics, context);
		if (result || diagnostics != null) result &= validate_EveryProxyResolves(numericalField, diagnostics, context);
		if (result || diagnostics != null) result &= validate_UniqueID(numericalField, diagnostics, context);
		if (result || diagnostics != null) result &= validate_EveryKeyUnique(numericalField, diagnostics, context);
		if (result || diagnostics != null) result &= validate_EveryMapEntryUnique(numericalField, diagnostics, context);
		if (result || diagnostics != null) result &= validateFormElement_noSpecialCharacters(numericalField, diagnostics, context);
		if (result || diagnostics != null) result &= validateFormElement_validRef(numericalField, diagnostics, context);
		if (result || diagnostics != null) result &= validateField_mandatoryHiddenAndNoDefaultValue(numericalField, diagnostics, context);
		return result;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean validateStaticText(StaticText staticText, DiagnosticChain diagnostics, Map<Object, Object> context) {
		boolean result = validate_EveryMultiplicityConforms(staticText, diagnostics, context);
		if (result || diagnostics != null) result &= validate_EveryDataValueConforms(staticText, diagnostics, context);
		if (result || diagnostics != null) result &= validate_EveryReferenceIsContained(staticText, diagnostics, context);
		if (result || diagnostics != null) result &= validate_EveryProxyResolves(staticText, diagnostics, context);
		if (result || diagnostics != null) result &= validate_UniqueID(staticText, diagnostics, context);
		if (result || diagnostics != null) result &= validate_EveryKeyUnique(staticText, diagnostics, context);
		if (result || diagnostics != null) result &= validate_EveryMapEntryUnique(staticText, diagnostics, context);
		if (result || diagnostics != null) result &= validateFormElement_noSpecialCharacters(staticText, diagnostics, context);
		if (result || diagnostics != null) result &= validateFormElement_validRef(staticText, diagnostics, context);
		return result;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean validateSearchFormCollection(SearchFormCollection searchFormCollection, DiagnosticChain diagnostics, Map<Object, Object> context) {
		return validate_EveryDefaultConstraint(searchFormCollection, diagnostics, context);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean validateSearchField(SearchField searchField, DiagnosticChain diagnostics, Map<Object, Object> context) {
		boolean result = validate_EveryMultiplicityConforms(searchField, diagnostics, context);
		if (result || diagnostics != null) result &= validate_EveryDataValueConforms(searchField, diagnostics, context);
		if (result || diagnostics != null) result &= validate_EveryReferenceIsContained(searchField, diagnostics, context);
		if (result || diagnostics != null) result &= validate_EveryProxyResolves(searchField, diagnostics, context);
		if (result || diagnostics != null) result &= validate_UniqueID(searchField, diagnostics, context);
		if (result || diagnostics != null) result &= validate_EveryKeyUnique(searchField, diagnostics, context);
		if (result || diagnostics != null) result &= validate_EveryMapEntryUnique(searchField, diagnostics, context);
		if (result || diagnostics != null) result &= validateFormElement_noSpecialCharacters(searchField, diagnostics, context);
		if (result || diagnostics != null) result &= validateFormElement_validRef(searchField, diagnostics, context);
		return result;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean validateNumericalSearchField(NumericalSearchField numericalSearchField, DiagnosticChain diagnostics, Map<Object, Object> context) {
		boolean result = validate_EveryMultiplicityConforms(numericalSearchField, diagnostics, context);
		if (result || diagnostics != null) result &= validate_EveryDataValueConforms(numericalSearchField, diagnostics, context);
		if (result || diagnostics != null) result &= validate_EveryReferenceIsContained(numericalSearchField, diagnostics, context);
		if (result || diagnostics != null) result &= validate_EveryProxyResolves(numericalSearchField, diagnostics, context);
		if (result || diagnostics != null) result &= validate_UniqueID(numericalSearchField, diagnostics, context);
		if (result || diagnostics != null) result &= validate_EveryKeyUnique(numericalSearchField, diagnostics, context);
		if (result || diagnostics != null) result &= validate_EveryMapEntryUnique(numericalSearchField, diagnostics, context);
		if (result || diagnostics != null) result &= validateFormElement_noSpecialCharacters(numericalSearchField, diagnostics, context);
		if (result || diagnostics != null) result &= validateFormElement_validRef(numericalSearchField, diagnostics, context);
		return result;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean validateCharSearchField(CharSearchField charSearchField, DiagnosticChain diagnostics, Map<Object, Object> context) {
		boolean result = validate_EveryMultiplicityConforms(charSearchField, diagnostics, context);
		if (result || diagnostics != null) result &= validate_EveryDataValueConforms(charSearchField, diagnostics, context);
		if (result || diagnostics != null) result &= validate_EveryReferenceIsContained(charSearchField, diagnostics, context);
		if (result || diagnostics != null) result &= validate_EveryProxyResolves(charSearchField, diagnostics, context);
		if (result || diagnostics != null) result &= validate_UniqueID(charSearchField, diagnostics, context);
		if (result || diagnostics != null) result &= validate_EveryKeyUnique(charSearchField, diagnostics, context);
		if (result || diagnostics != null) result &= validate_EveryMapEntryUnique(charSearchField, diagnostics, context);
		if (result || diagnostics != null) result &= validateFormElement_noSpecialCharacters(charSearchField, diagnostics, context);
		if (result || diagnostics != null) result &= validateFormElement_validRef(charSearchField, diagnostics, context);
		return result;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean validateDateSearchField(DateSearchField dateSearchField, DiagnosticChain diagnostics, Map<Object, Object> context) {
		boolean result = validate_EveryMultiplicityConforms(dateSearchField, diagnostics, context);
		if (result || diagnostics != null) result &= validate_EveryDataValueConforms(dateSearchField, diagnostics, context);
		if (result || diagnostics != null) result &= validate_EveryReferenceIsContained(dateSearchField, diagnostics, context);
		if (result || diagnostics != null) result &= validate_EveryProxyResolves(dateSearchField, diagnostics, context);
		if (result || diagnostics != null) result &= validate_UniqueID(dateSearchField, diagnostics, context);
		if (result || diagnostics != null) result &= validate_EveryKeyUnique(dateSearchField, diagnostics, context);
		if (result || diagnostics != null) result &= validate_EveryMapEntryUnique(dateSearchField, diagnostics, context);
		if (result || diagnostics != null) result &= validateFormElement_noSpecialCharacters(dateSearchField, diagnostics, context);
		if (result || diagnostics != null) result &= validateFormElement_validRef(dateSearchField, diagnostics, context);
		return result;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean validateChoiceSearchField(ChoiceSearchField choiceSearchField, DiagnosticChain diagnostics, Map<Object, Object> context) {
		boolean result = validate_EveryMultiplicityConforms(choiceSearchField, diagnostics, context);
		if (result || diagnostics != null) result &= validate_EveryDataValueConforms(choiceSearchField, diagnostics, context);
		if (result || diagnostics != null) result &= validate_EveryReferenceIsContained(choiceSearchField, diagnostics, context);
		if (result || diagnostics != null) result &= validate_EveryProxyResolves(choiceSearchField, diagnostics, context);
		if (result || diagnostics != null) result &= validate_UniqueID(choiceSearchField, diagnostics, context);
		if (result || diagnostics != null) result &= validate_EveryKeyUnique(choiceSearchField, diagnostics, context);
		if (result || diagnostics != null) result &= validate_EveryMapEntryUnique(choiceSearchField, diagnostics, context);
		if (result || diagnostics != null) result &= validateFormElement_noSpecialCharacters(choiceSearchField, diagnostics, context);
		if (result || diagnostics != null) result &= validateFormElement_validRef(choiceSearchField, diagnostics, context);
		return result;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean validateFileSearchField(FileSearchField fileSearchField, DiagnosticChain diagnostics, Map<Object, Object> context) {
		boolean result = validate_EveryMultiplicityConforms(fileSearchField, diagnostics, context);
		if (result || diagnostics != null) result &= validate_EveryDataValueConforms(fileSearchField, diagnostics, context);
		if (result || diagnostics != null) result &= validate_EveryReferenceIsContained(fileSearchField, diagnostics, context);
		if (result || diagnostics != null) result &= validate_EveryProxyResolves(fileSearchField, diagnostics, context);
		if (result || diagnostics != null) result &= validate_UniqueID(fileSearchField, diagnostics, context);
		if (result || diagnostics != null) result &= validate_EveryKeyUnique(fileSearchField, diagnostics, context);
		if (result || diagnostics != null) result &= validate_EveryMapEntryUnique(fileSearchField, diagnostics, context);
		if (result || diagnostics != null) result &= validateFormElement_noSpecialCharacters(fileSearchField, diagnostics, context);
		if (result || diagnostics != null) result &= validateFormElement_validRef(fileSearchField, diagnostics, context);
		return result;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean validateWorkflowFormCollection(WorkflowFormCollection workflowFormCollection, DiagnosticChain diagnostics, Map<Object, Object> context) {
		return validate_EveryDefaultConstraint(workflowFormCollection, diagnostics, context);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean validateClassFormCollection(ClassFormCollection classFormCollection, DiagnosticChain diagnostics, Map<Object, Object> context) {
		return validate_EveryDefaultConstraint(classFormCollection, diagnostics, context);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean validateFormWorkflow(FormWorkflow formWorkflow, DiagnosticChain diagnostics, Map<Object, Object> context) {
		boolean result = validate_EveryMultiplicityConforms(formWorkflow, diagnostics, context);
		if (result || diagnostics != null) result &= validate_EveryDataValueConforms(formWorkflow, diagnostics, context);
		if (result || diagnostics != null) result &= validate_EveryReferenceIsContained(formWorkflow, diagnostics, context);
		if (result || diagnostics != null) result &= validate_EveryProxyResolves(formWorkflow, diagnostics, context);
		if (result || diagnostics != null) result &= validate_UniqueID(formWorkflow, diagnostics, context);
		if (result || diagnostics != null) result &= validate_EveryKeyUnique(formWorkflow, diagnostics, context);
		if (result || diagnostics != null) result &= validate_EveryMapEntryUnique(formWorkflow, diagnostics, context);
		if (result || diagnostics != null) result &= validateFormElement_noSpecialCharacters(formWorkflow, diagnostics, context);
		if (result || diagnostics != null) result &= validateFormElement_validRef(formWorkflow, diagnostics, context);
		if (result || diagnostics != null) result &= validateFormContainer_validName(formWorkflow, diagnostics, context);
		if (result || diagnostics != null) result &= validateFormContainer_TwoFormsWithSameName(formWorkflow, diagnostics, context);
		if (result || diagnostics != null) result &= validateFormWorkflow_ClassMustMatchWithProcessContentType(formWorkflow, diagnostics, context);
		return result;
	}

	/**
	 * Validates the ClassMustMatchWithProcessContentType constraint of '<em>Workflow</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean validateFormWorkflow_ClassMustMatchWithProcessContentType(FormWorkflow formWorkflow, DiagnosticChain diagnostics, Map<Object, Object> context) {
        if (formWorkflow_ClassMustMatchWithProcessContentTypeInvOCL == null) {
			OCL.Helper helper = OCL_ENV.createOCLHelper();
			helper.setContext(FormPackage.Literals.FORM_WORKFLOW);

			EAnnotation ocl = FormPackage.Literals.FORM_WORKFLOW.getEAnnotation(OCL_ANNOTATION_SOURCE);
			String expr = ocl.getDetails().get("ClassMustMatchWithProcessContentType");

			try {
				formWorkflow_ClassMustMatchWithProcessContentTypeInvOCL = helper.createInvariant(expr);
			}
			catch (ParserException e) {
				throw new UnsupportedOperationException(e.getLocalizedMessage());
			}
		}

		Query<EClassifier, ?, ?> query = OCL_ENV.createQuery(formWorkflow_ClassMustMatchWithProcessContentTypeInvOCL);

		if (!query.check(formWorkflow)) {
			if (diagnostics != null) {
				diagnostics.add
					(new BasicDiagnostic
						((doThrowError( FormPackage.Literals.FORM_WORKFLOW.getEAnnotation("http://www.eclipse.org/emf/2002/Ecore"),"ClassMustMatchWithProcessContentType")? Diagnostic.ERROR : Diagnostic.WARNING),
						 DIAGNOSTIC_SOURCE,
						 0,
						 EcorePlugin.INSTANCE.getString("_UI_GenericConstraint_diagnostic", new Object[] { "ClassMustMatchWithProcessContentType", getObjectLabel(formWorkflow, context) }),
						 new Object[] { formWorkflow }));
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
	public boolean validateFormContainer(FormContainer formContainer, DiagnosticChain diagnostics, Map<Object, Object> context) {
		boolean result = validate_EveryMultiplicityConforms(formContainer, diagnostics, context);
		if (result || diagnostics != null) result &= validate_EveryDataValueConforms(formContainer, diagnostics, context);
		if (result || diagnostics != null) result &= validate_EveryReferenceIsContained(formContainer, diagnostics, context);
		if (result || diagnostics != null) result &= validate_EveryProxyResolves(formContainer, diagnostics, context);
		if (result || diagnostics != null) result &= validate_UniqueID(formContainer, diagnostics, context);
		if (result || diagnostics != null) result &= validate_EveryKeyUnique(formContainer, diagnostics, context);
		if (result || diagnostics != null) result &= validate_EveryMapEntryUnique(formContainer, diagnostics, context);
		if (result || diagnostics != null) result &= validateFormElement_noSpecialCharacters(formContainer, diagnostics, context);
		if (result || diagnostics != null) result &= validateFormElement_validRef(formContainer, diagnostics, context);
		if (result || diagnostics != null) result &= validateFormContainer_validName(formContainer, diagnostics, context);
		if (result || diagnostics != null) result &= validateFormContainer_TwoFormsWithSameName(formContainer, diagnostics, context);
		return result;
	}

	/**
	 * Validates the validName constraint of '<em>Container</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean validateFormContainer_validName(FormContainer formContainer, DiagnosticChain diagnostics, Map<Object, Object> context) {
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
						 EcorePlugin.INSTANCE.getString("_UI_GenericConstraint_diagnostic", new Object[] { "validName", getObjectLabel(formContainer, context) }),
						 new Object[] { formContainer }));
			}
			return false;
		}
		return true;
	}

	/**
	 * Validates the TwoFormsWithSameName constraint of '<em>Container</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean validateFormContainer_TwoFormsWithSameName(FormContainer formContainer, DiagnosticChain diagnostics, Map<Object, Object> context) {
        if (formContainer_TwoFormsWithSameNameInvOCL == null) {
			OCL.Helper helper = OCL_ENV.createOCLHelper();
			helper.setContext(FormPackage.Literals.FORM_CONTAINER);

			EAnnotation ocl = FormPackage.Literals.FORM_CONTAINER.getEAnnotation(OCL_ANNOTATION_SOURCE);
			String expr = ocl.getDetails().get("TwoFormsWithSameName");

			try {
				formContainer_TwoFormsWithSameNameInvOCL = helper.createInvariant(expr);
			}
			catch (ParserException e) {
				throw new UnsupportedOperationException(e.getLocalizedMessage());
			}
		}

		Query<EClassifier, ?, ?> query = OCL_ENV.createQuery(formContainer_TwoFormsWithSameNameInvOCL);

		if (!query.check(formContainer)) {
			if (diagnostics != null) {
				diagnostics.add
					(new BasicDiagnostic
						((doThrowError( FormPackage.Literals.FORM_CONTAINER.getEAnnotation("http://www.eclipse.org/emf/2002/Ecore"),"TwoFormsWithSameName")? Diagnostic.ERROR : Diagnostic.WARNING),
						 DIAGNOSTIC_SOURCE,
						 0,
						 EcorePlugin.INSTANCE.getString("_UI_GenericConstraint_diagnostic", new Object[] { "TwoFormsWithSameName", getObjectLabel(formContainer, context) }),
						 new Object[] { formContainer }));
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
	public boolean validateFormGroupPresentationType(FormGroupPresentationType formGroupPresentationType, DiagnosticChain diagnostics, Map<Object, Object> context) {
		return true;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean validateTextWidgetType(TextWidgetType textWidgetType, DiagnosticChain diagnostics, Map<Object, Object> context) {
		return true;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean validateChoiceWidgetType(ChoiceWidgetType choiceWidgetType, DiagnosticChain diagnostics, Map<Object, Object> context) {
		return true;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean validateModelChoiceWidgetType(ModelChoiceWidgetType modelChoiceWidgetType, DiagnosticChain diagnostics, Map<Object, Object> context) {
		return true;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean validateCharFieldSearchOperators(CharFieldSearchOperators charFieldSearchOperators, DiagnosticChain diagnostics, Map<Object, Object> context) {
		return true;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean validateNumericalFieldSearchOperators(NumericalFieldSearchOperators numericalFieldSearchOperators, DiagnosticChain diagnostics, Map<Object, Object> context) {
		return true;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean validateChoiceFieldSearchOperators(ChoiceFieldSearchOperators choiceFieldSearchOperators, DiagnosticChain diagnostics, Map<Object, Object> context) {
		return true;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean validateFileFieldSearchOperators(FileFieldSearchOperators fileFieldSearchOperators, DiagnosticChain diagnostics, Map<Object, Object> context) {
		return true;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean validateCombinationOperators(CombinationOperators combinationOperators, DiagnosticChain diagnostics, Map<Object, Object> context) {
		return true;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean validateDateFieldSearchOperators(DateFieldSearchOperators dateFieldSearchOperators, DiagnosticChain diagnostics, Map<Object, Object> context) {
		return true;
	}

} //formValidator
