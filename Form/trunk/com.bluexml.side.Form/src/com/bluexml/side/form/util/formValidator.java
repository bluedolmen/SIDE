/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package com.bluexml.side.form.util;

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

import com.bluexml.side.KerblueForms.ChoiceField;
import com.bluexml.side.KerblueForms.ChoiceWidgetType;
import com.bluexml.side.Utils.MetaModel.validate.OCLextension.KerblueOCL;
import com.bluexml.side.form.ActionField;
import com.bluexml.side.form.BooleanField;
import com.bluexml.side.form.CharField;
import com.bluexml.side.form.ClassReference;
import com.bluexml.side.form.DateField;
import com.bluexml.side.form.DateTimeField;
import com.bluexml.side.form.DecimalField;
import com.bluexml.side.form.EmailField;
import com.bluexml.side.form.Field;
import com.bluexml.side.form.FileField;
import com.bluexml.side.form.FloatField;
import com.bluexml.side.form.Form;
import com.bluexml.side.form.FormAspect;
import com.bluexml.side.form.FormClass;
import com.bluexml.side.form.FormCollection;
import com.bluexml.side.form.FormElement;
import com.bluexml.side.form.FormGroup;
import com.bluexml.side.form.FormGroupPresentationType;
import com.bluexml.side.form.ImageField;
import com.bluexml.side.form.IntegerField;
import com.bluexml.side.form.ModelChoiceField;
import com.bluexml.side.form.PasswordField;
import com.bluexml.side.form.PhoneNumberField;
import com.bluexml.side.form.Reference;
import com.bluexml.side.form.ReferenceWidgetType;
import com.bluexml.side.form.RegexField;
import com.bluexml.side.form.TextField;
import com.bluexml.side.form.TextWidgetType;
import com.bluexml.side.form.TimeField;
import com.bluexml.side.form.URLField;
import com.bluexml.side.form.VirtualField;
import com.bluexml.side.form.formPackage;

/**
 * <!-- begin-user-doc -->
 * The <b>Validator</b> for the model.
 * <!-- end-user-doc -->
 * @see com.bluexml.side.form.formPackage
 * @generated
 */
public class formValidator extends EObjectValidator {
	/**
	 * The cached model package
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public static final formValidator INSTANCE = new formValidator();

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
	 * The parsed OCL expression for the definition of the '<em>validName</em>' invariant constraint.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private static Constraint form_validNameInvOCL;

	/**
	 * The parsed OCL expression for the definition of the '<em>noSpecialCharacters</em>' invariant constraint.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private static Constraint formElement_noSpecialCharactersInvOCL;

	/**
	 * The parsed OCL expression for the definition of the '<em>MinSuperiorToMax</em>' invariant constraint.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private static Constraint charField_MinSuperiorToMaxInvOCL;

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
	public formValidator() {
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
	  return formPackage.eINSTANCE;
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
			case formPackage.FORM:
				return validateForm((Form)value, diagnostics, context);
			case formPackage.FORM_ELEMENT:
				return validateFormElement((FormElement)value, diagnostics, context);
			case formPackage.FORM_GROUP:
				return validateFormGroup((FormGroup)value, diagnostics, context);
			case formPackage.FIELD:
				return validateField((Field)value, diagnostics, context);
			case formPackage.BOOLEAN_FIELD:
				return validateBooleanField((BooleanField)value, diagnostics, context);
			case formPackage.CHAR_FIELD:
				return validateCharField((CharField)value, diagnostics, context);
			case formPackage.DATE_FIELD:
				return validateDateField((DateField)value, diagnostics, context);
			case formPackage.DATE_TIME_FIELD:
				return validateDateTimeField((DateTimeField)value, diagnostics, context);
			case formPackage.DECIMAL_FIELD:
				return validateDecimalField((DecimalField)value, diagnostics, context);
			case formPackage.FLOAT_FIELD:
				return validateFloatField((FloatField)value, diagnostics, context);
			case formPackage.INTEGER_FIELD:
				return validateIntegerField((IntegerField)value, diagnostics, context);
			case formPackage.MODEL_CHOICE_FIELD:
				return validateModelChoiceField((ModelChoiceField)value, diagnostics, context);
			case formPackage.EMAIL_FIELD:
				return validateEmailField((EmailField)value, diagnostics, context);
			case formPackage.FILE_FIELD:
				return validateFileField((FileField)value, diagnostics, context);
			case formPackage.IMAGE_FIELD:
				return validateImageField((ImageField)value, diagnostics, context);
			case formPackage.TIME_FIELD:
				return validateTimeField((TimeField)value, diagnostics, context);
			case formPackage.URL_FIELD:
				return validateURLField((URLField)value, diagnostics, context);
			case formPackage.PHONE_NUMBER_FIELD:
				return validatePhoneNumberField((PhoneNumberField)value, diagnostics, context);
			case formPackage.FORM_ASPECT:
				return validateFormAspect((FormAspect)value, diagnostics, context);
			case formPackage.FORM_CLASS:
				return validateFormClass((FormClass)value, diagnostics, context);
			case formPackage.REFERENCE:
				return validateReference((Reference)value, diagnostics, context);
			case formPackage.FORM_COLLECTION:
				return validateFormCollection((FormCollection)value, diagnostics, context);
			case formPackage.CHOICE_FIELD:
				return validateChoiceField((ChoiceField)value, diagnostics, context);
			case formPackage.REGEX_FIELD:
				return validateRegexField((RegexField)value, diagnostics, context);
			case formPackage.CLASS_REFERENCE:
				return validateClassReference((ClassReference)value, diagnostics, context);
			case formPackage.PASSWORD_FIELD:
				return validatePasswordField((PasswordField)value, diagnostics, context);
			case formPackage.VIRTUAL_FIELD:
				return validateVirtualField((VirtualField)value, diagnostics, context);
			case formPackage.ACTION_FIELD:
				return validateActionField((ActionField)value, diagnostics, context);
			case formPackage.TEXT_FIELD:
				return validateTextField((TextField)value, diagnostics, context);
			case formPackage.FORM_GROUP_PRESENTATION_TYPE:
				return validateFormGroupPresentationType((FormGroupPresentationType)value, diagnostics, context);
			case formPackage.TEXT_WIDGET_TYPE:
				return validateTextWidgetType((TextWidgetType)value, diagnostics, context);
			case formPackage.CHOICE_WIDGET_TYPE:
				return validateChoiceWidgetType((ChoiceWidgetType)value, diagnostics, context);
			case formPackage.REFERENCE_WIDGET_TYPE:
				return validateReferenceWidgetType((ReferenceWidgetType)value, diagnostics, context);
			default: 
				return true;
		}
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean validateForm(Form form, DiagnosticChain diagnostics, Map<Object, Object> context) {
		boolean result = validate_EveryMultiplicityConforms(form, diagnostics, context);
		if (result || diagnostics != null) result &= validate_EveryDataValueConforms(form, diagnostics, context);
		if (result || diagnostics != null) result &= validate_EveryReferenceIsContained(form, diagnostics, context);
		if (result || diagnostics != null) result &= validate_EveryProxyResolves(form, diagnostics, context);
		if (result || diagnostics != null) result &= validate_UniqueID(form, diagnostics, context);
		if (result || diagnostics != null) result &= validate_EveryKeyUnique(form, diagnostics, context);
		if (result || diagnostics != null) result &= validate_EveryMapEntryUnique(form, diagnostics, context);
		if (result || diagnostics != null) result &= validateForm_validName(form, diagnostics, context);
		if (result || diagnostics != null) result &= validateForm_noSpecialCharacters(form, diagnostics, context);
		return result;
	}

	/**
	 * Validates the validName constraint of '<em>Form</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean validateForm_validName(Form form, DiagnosticChain diagnostics, Map<Object, Object> context) {
        if (form_validNameInvOCL == null) {
			OCL.Helper helper = OCL_ENV.createOCLHelper();
			helper.setContext(formPackage.Literals.FORM);
			
			EAnnotation ocl = formPackage.Literals.FORM.getEAnnotation(OCL_ANNOTATION_SOURCE);
			String expr = ocl.getDetails().get("validName");
			
			try {
				form_validNameInvOCL = helper.createInvariant(expr);
			}
			catch (ParserException e) {
				throw new UnsupportedOperationException(e.getLocalizedMessage());
			}
		}
		
		Query<EClassifier, ?, ?> query = OCL_ENV.createQuery(form_validNameInvOCL);
		
		if (!query.check(form)) {
			if (diagnostics != null) {
				diagnostics.add
					(new BasicDiagnostic
						(Diagnostic.ERROR,
						 DIAGNOSTIC_SOURCE,
						 0,
						 EcorePlugin.INSTANCE.getString("_UI_GenericConstraint_diagnostic", new Object[] { "validName", getObjectLabel(form, context) }),
						 new Object[] { form }));
			}
			return false;
		}
		return true;
	}

	/**
	 * Validates the noSpecialCharacters constraint of '<em>Form</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean validateForm_noSpecialCharacters(Form form, DiagnosticChain diagnostics, Map<Object, Object> context) {
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
						 EcorePlugin.INSTANCE.getString("_UI_GenericConstraint_diagnostic", new Object[] { "noSpecialCharacters", getObjectLabel(form, context) }),
						 new Object[] { form }));
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
	public boolean validateFormElement(FormElement formElement, DiagnosticChain diagnostics, Map<Object, Object> context) {
		boolean result = validate_EveryMultiplicityConforms(formElement, diagnostics, context);
		if (result || diagnostics != null) result &= validate_EveryDataValueConforms(formElement, diagnostics, context);
		if (result || diagnostics != null) result &= validate_EveryReferenceIsContained(formElement, diagnostics, context);
		if (result || diagnostics != null) result &= validate_EveryProxyResolves(formElement, diagnostics, context);
		if (result || diagnostics != null) result &= validate_UniqueID(formElement, diagnostics, context);
		if (result || diagnostics != null) result &= validate_EveryKeyUnique(formElement, diagnostics, context);
		if (result || diagnostics != null) result &= validate_EveryMapEntryUnique(formElement, diagnostics, context);
		if (result || diagnostics != null) result &= validateFormElement_noSpecialCharacters(formElement, diagnostics, context);
		return result;
	}

	/**
	 * Validates the noSpecialCharacters constraint of '<em>Form Element</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean validateFormElement_noSpecialCharacters(FormElement formElement, DiagnosticChain diagnostics, Map<Object, Object> context) {
        if (formElement_noSpecialCharactersInvOCL == null) {
			OCL.Helper helper = OCL_ENV.createOCLHelper();
			helper.setContext(formPackage.Literals.FORM_ELEMENT);
			
			EAnnotation ocl = formPackage.Literals.FORM_ELEMENT.getEAnnotation(OCL_ANNOTATION_SOURCE);
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
						(Diagnostic.ERROR,
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
		return result;
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
			helper.setContext(formPackage.Literals.CHAR_FIELD);
			
			EAnnotation ocl = formPackage.Literals.CHAR_FIELD.getEAnnotation(OCL_ANNOTATION_SOURCE);
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
						(Diagnostic.ERROR,
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
		if (result || diagnostics != null) result &= validateCharField_MinSuperiorToMax(regexField, diagnostics, context);
		return result;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean validateClassReference(ClassReference classReference, DiagnosticChain diagnostics, Map<Object, Object> context) {
		return validate_EveryDefaultConstraint(classReference, diagnostics, context);
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
			helper.setContext(formPackage.Literals.VIRTUAL_FIELD);
			
			EAnnotation ocl = formPackage.Literals.VIRTUAL_FIELD.getEAnnotation(OCL_ANNOTATION_SOURCE);
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
						(Diagnostic.ERROR,
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
		if (result || diagnostics != null) result &= validateCharField_MinSuperiorToMax(textField, diagnostics, context);
		return result;
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
	public boolean validateReferenceWidgetType(ReferenceWidgetType referenceWidgetType, DiagnosticChain diagnostics, Map<Object, Object> context) {
		return true;
	}

} //formValidator
