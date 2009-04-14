/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package com.bluexml.side.form.impl;

import com.bluexml.side.form.*;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EDataType;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.impl.EFactoryImpl;
import org.eclipse.emf.ecore.plugin.EcorePlugin;

import com.bluexml.side.form.ActionField;
import com.bluexml.side.form.BooleanField;
import com.bluexml.side.form.CharField;
import com.bluexml.side.form.ChoiceField;
import com.bluexml.side.form.DateField;
import com.bluexml.side.form.DateTimeField;
import com.bluexml.side.form.DecimalField;
import com.bluexml.side.form.EmailField;
import com.bluexml.side.form.FileField;
import com.bluexml.side.form.FloatField;
import com.bluexml.side.form.Form;
import com.bluexml.side.form.FormAspect;
import com.bluexml.side.form.FormClass;
import com.bluexml.side.form.FormCollection;
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
import com.bluexml.side.form.formFactory;
import com.bluexml.side.form.formPackage;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model <b>Factory</b>.
 * <!-- end-user-doc -->
 * @generated
 */
public class formFactoryImpl extends EFactoryImpl implements formFactory {
	/**
	 * Creates the default factory implementation.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public static formFactory init() {
		try {
			formFactory theformFactory = (formFactory)EPackage.Registry.INSTANCE.getEFactory("http://www.kerblue.org/form/1.0"); 
			if (theformFactory != null) {
				return theformFactory;
			}
		}
		catch (Exception exception) {
			EcorePlugin.INSTANCE.log(exception);
		}
		return new formFactoryImpl();
	}

	/**
	 * Creates an instance of the factory.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public formFactoryImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EObject create(EClass eClass) {
		switch (eClass.getClassifierID()) {
			case formPackage.FORM: return createForm();
			case formPackage.FORM_GROUP: return createFormGroup();
			case formPackage.BOOLEAN_FIELD: return createBooleanField();
			case formPackage.CHAR_FIELD: return createCharField();
			case formPackage.DATE_FIELD: return createDateField();
			case formPackage.DATE_TIME_FIELD: return createDateTimeField();
			case formPackage.DECIMAL_FIELD: return createDecimalField();
			case formPackage.FLOAT_FIELD: return createFloatField();
			case formPackage.INTEGER_FIELD: return createIntegerField();
			case formPackage.MODEL_CHOICE_FIELD: return createModelChoiceField();
			case formPackage.EMAIL_FIELD: return createEmailField();
			case formPackage.FILE_FIELD: return createFileField();
			case formPackage.IMAGE_FIELD: return createImageField();
			case formPackage.TIME_FIELD: return createTimeField();
			case formPackage.URL_FIELD: return createURLField();
			case formPackage.PHONE_NUMBER_FIELD: return createPhoneNumberField();
			case formPackage.FORM_ASPECT: return createFormAspect();
			case formPackage.FORM_CLASS: return createFormClass();
			case formPackage.REFERENCE: return createReference();
			case formPackage.FORM_COLLECTION: return createFormCollection();
			case formPackage.CHOICE_FIELD: return createChoiceField();
			case formPackage.REGEX_FIELD: return createRegexField();
			case formPackage.PASSWORD_FIELD: return createPasswordField();
			case formPackage.VIRTUAL_FIELD: return createVirtualField();
			case formPackage.ACTION_FIELD: return createActionField();
			case formPackage.TEXT_FIELD: return createTextField();
			case formPackage.WORKFLOW_FORM_COLLECTION: return createWorkflowFormCollection();
			case formPackage.FORM_WORKFLOW: return createFormWorkflow();
			default:
				throw new IllegalArgumentException("The class '" + eClass.getName() + "' is not a valid classifier");
		}
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Object createFromString(EDataType eDataType, String initialValue) {
		switch (eDataType.getClassifierID()) {
			case formPackage.FORM_GROUP_PRESENTATION_TYPE:
				return createFormGroupPresentationTypeFromString(eDataType, initialValue);
			case formPackage.TEXT_WIDGET_TYPE:
				return createTextWidgetTypeFromString(eDataType, initialValue);
			case formPackage.CHOICE_WIDGET_TYPE:
				return createChoiceWidgetTypeFromString(eDataType, initialValue);
			case formPackage.REFERENCE_WIDGET_TYPE:
				return createReferenceWidgetTypeFromString(eDataType, initialValue);
			default:
				throw new IllegalArgumentException("The datatype '" + eDataType.getName() + "' is not a valid classifier");
		}
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public String convertToString(EDataType eDataType, Object instanceValue) {
		switch (eDataType.getClassifierID()) {
			case formPackage.FORM_GROUP_PRESENTATION_TYPE:
				return convertFormGroupPresentationTypeToString(eDataType, instanceValue);
			case formPackage.TEXT_WIDGET_TYPE:
				return convertTextWidgetTypeToString(eDataType, instanceValue);
			case formPackage.CHOICE_WIDGET_TYPE:
				return convertChoiceWidgetTypeToString(eDataType, instanceValue);
			case formPackage.REFERENCE_WIDGET_TYPE:
				return convertReferenceWidgetTypeToString(eDataType, instanceValue);
			default:
				throw new IllegalArgumentException("The datatype '" + eDataType.getName() + "' is not a valid classifier");
		}
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Form createForm() {
		FormImpl form = new FormImpl();
		return form;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public FormGroup createFormGroup() {
		FormGroupImpl formGroup = new FormGroupImpl();
		return formGroup;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public BooleanField createBooleanField() {
		BooleanFieldImpl booleanField = new BooleanFieldImpl();
		return booleanField;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public CharField createCharField() {
		CharFieldImpl charField = new CharFieldImpl();
		return charField;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public DateField createDateField() {
		DateFieldImpl dateField = new DateFieldImpl();
		return dateField;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public DateTimeField createDateTimeField() {
		DateTimeFieldImpl dateTimeField = new DateTimeFieldImpl();
		return dateTimeField;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public DecimalField createDecimalField() {
		DecimalFieldImpl decimalField = new DecimalFieldImpl();
		return decimalField;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public FloatField createFloatField() {
		FloatFieldImpl floatField = new FloatFieldImpl();
		return floatField;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public IntegerField createIntegerField() {
		IntegerFieldImpl integerField = new IntegerFieldImpl();
		return integerField;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public ModelChoiceField createModelChoiceField() {
		ModelChoiceFieldImpl modelChoiceField = new ModelChoiceFieldImpl();
		return modelChoiceField;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EmailField createEmailField() {
		EmailFieldImpl emailField = new EmailFieldImpl();
		return emailField;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public FileField createFileField() {
		FileFieldImpl fileField = new FileFieldImpl();
		return fileField;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public ImageField createImageField() {
		ImageFieldImpl imageField = new ImageFieldImpl();
		return imageField;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public TimeField createTimeField() {
		TimeFieldImpl timeField = new TimeFieldImpl();
		return timeField;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public URLField createURLField() {
		URLFieldImpl urlField = new URLFieldImpl();
		return urlField;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public PhoneNumberField createPhoneNumberField() {
		PhoneNumberFieldImpl phoneNumberField = new PhoneNumberFieldImpl();
		return phoneNumberField;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public FormAspect createFormAspect() {
		FormAspectImpl formAspect = new FormAspectImpl();
		return formAspect;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public FormClass createFormClass() {
		FormClassImpl formClass = new FormClassImpl();
		return formClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Reference createReference() {
		ReferenceImpl reference = new ReferenceImpl();
		return reference;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public FormCollection createFormCollection() {
		FormCollectionImpl formCollection = new FormCollectionImpl();
		return formCollection;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public ChoiceField createChoiceField() {
		ChoiceFieldImpl choiceField = new ChoiceFieldImpl();
		return choiceField;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public RegexField createRegexField() {
		RegexFieldImpl regexField = new RegexFieldImpl();
		return regexField;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public PasswordField createPasswordField() {
		PasswordFieldImpl passwordField = new PasswordFieldImpl();
		return passwordField;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public VirtualField createVirtualField() {
		VirtualFieldImpl virtualField = new VirtualFieldImpl();
		return virtualField;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public ActionField createActionField() {
		ActionFieldImpl actionField = new ActionFieldImpl();
		return actionField;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public TextField createTextField() {
		TextFieldImpl textField = new TextFieldImpl();
		return textField;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public WorkflowFormCollection createWorkflowFormCollection() {
		WorkflowFormCollectionImpl workflowFormCollection = new WorkflowFormCollectionImpl();
		return workflowFormCollection;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public FormWorkflow createFormWorkflow() {
		FormWorkflowImpl formWorkflow = new FormWorkflowImpl();
		return formWorkflow;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public FormGroupPresentationType createFormGroupPresentationTypeFromString(EDataType eDataType, String initialValue) {
		FormGroupPresentationType result = FormGroupPresentationType.get(initialValue);
		if (result == null) throw new IllegalArgumentException("The value '" + initialValue + "' is not a valid enumerator of '" + eDataType.getName() + "'");
		return result;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String convertFormGroupPresentationTypeToString(EDataType eDataType, Object instanceValue) {
		return instanceValue == null ? null : instanceValue.toString();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public TextWidgetType createTextWidgetTypeFromString(EDataType eDataType, String initialValue) {
		TextWidgetType result = TextWidgetType.get(initialValue);
		if (result == null) throw new IllegalArgumentException("The value '" + initialValue + "' is not a valid enumerator of '" + eDataType.getName() + "'");
		return result;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String convertTextWidgetTypeToString(EDataType eDataType, Object instanceValue) {
		return instanceValue == null ? null : instanceValue.toString();
	}


	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public ChoiceWidgetType createChoiceWidgetTypeFromString(EDataType eDataType, String initialValue) {
		ChoiceWidgetType result = ChoiceWidgetType.get(initialValue);
		if (result == null) throw new IllegalArgumentException("The value '" + initialValue + "' is not a valid enumerator of '" + eDataType.getName() + "'");
		return result;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String convertChoiceWidgetTypeToString(EDataType eDataType, Object instanceValue) {
		return instanceValue == null ? null : instanceValue.toString();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public ReferenceWidgetType createReferenceWidgetTypeFromString(EDataType eDataType, String initialValue) {
		ReferenceWidgetType result = ReferenceWidgetType.get(initialValue);
		if (result == null) throw new IllegalArgumentException("The value '" + initialValue + "' is not a valid enumerator of '" + eDataType.getName() + "'");
		return result;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String convertReferenceWidgetTypeToString(EDataType eDataType, Object instanceValue) {
		return instanceValue == null ? null : instanceValue.toString();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public formPackage getformPackage() {
		return (formPackage)getEPackage();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @deprecated
	 * @generated
	 */
	@Deprecated
	public static formPackage getPackage() {
		return formPackage.eINSTANCE;
	}

	

} //formFactoryImpl
