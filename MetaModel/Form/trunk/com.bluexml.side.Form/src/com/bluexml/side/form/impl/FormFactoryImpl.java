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
import com.bluexml.side.form.ChoiceWidgetType;
import com.bluexml.side.form.DateField;
import com.bluexml.side.form.DateTimeField;
import com.bluexml.side.form.DecimalField;
import com.bluexml.side.form.EmailField;
import com.bluexml.side.form.FileField;
import com.bluexml.side.form.FloatField;
import com.bluexml.side.form.FormAspect;
import com.bluexml.side.form.FormClass;
import com.bluexml.side.form.FormCollection;
import com.bluexml.side.form.FormFactory;
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

/**
 * <!-- begin-user-doc -->
 * An implementation of the model <b>Factory</b>.
 * <!-- end-user-doc -->
 * @generated
 */
public class FormFactoryImpl extends EFactoryImpl implements FormFactory {
	/**
	 * Creates the default factory implementation.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public static FormFactory init() {
		try {
			FormFactory theFormFactory = (FormFactory)EPackage.Registry.INSTANCE.getEFactory("http://www.kerblue.org/form/1.0"); 
			if (theFormFactory != null) {
				return theFormFactory;
			}
		}
		catch (Exception exception) {
			EcorePlugin.INSTANCE.log(exception);
		}
		return new FormFactoryImpl();
	}

	/**
	 * Creates an instance of the factory.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public FormFactoryImpl() {
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
			case FormPackage.FORM_GROUP: return createFormGroup();
			case FormPackage.WORKFLOW_FORM_COLLECTION: return createWorkflowFormCollection();
			case FormPackage.CLASS_FORM_COLLECTION: return createClassFormCollection();
			case FormPackage.FORM_WORKFLOW: return createFormWorkflow();
			case FormPackage.FORM_CLASS: return createFormClass();
			case FormPackage.BOOLEAN_FIELD: return createBooleanField();
			case FormPackage.CHAR_FIELD: return createCharField();
			case FormPackage.DATE_FIELD: return createDateField();
			case FormPackage.DATE_TIME_FIELD: return createDateTimeField();
			case FormPackage.DECIMAL_FIELD: return createDecimalField();
			case FormPackage.FLOAT_FIELD: return createFloatField();
			case FormPackage.INTEGER_FIELD: return createIntegerField();
			case FormPackage.MODEL_CHOICE_FIELD: return createModelChoiceField();
			case FormPackage.EMAIL_FIELD: return createEmailField();
			case FormPackage.FILE_FIELD: return createFileField();
			case FormPackage.IMAGE_FIELD: return createImageField();
			case FormPackage.TIME_FIELD: return createTimeField();
			case FormPackage.URL_FIELD: return createURLField();
			case FormPackage.PHONE_NUMBER_FIELD: return createPhoneNumberField();
			case FormPackage.FORM_ASPECT: return createFormAspect();
			case FormPackage.REFERENCE: return createReference();
			case FormPackage.CHOICE_FIELD: return createChoiceField();
			case FormPackage.REGEX_FIELD: return createRegexField();
			case FormPackage.PASSWORD_FIELD: return createPasswordField();
			case FormPackage.VIRTUAL_FIELD: return createVirtualField();
			case FormPackage.ACTION_FIELD: return createActionField();
			case FormPackage.TEXT_FIELD: return createTextField();
			case FormPackage.SEARCH_FORM: return createSearchForm();
			case FormPackage.NUMERICAL_FIELD: return createNumericalField();
			case FormPackage.STATIC_TEXT: return createStaticText();
			case FormPackage.SEARCH_FORM_COLLECTION: return createSearchFormCollection();
			case FormPackage.SEARCH_FIELD: return createSearchField();
			case FormPackage.NUMERICAL_SEARCH_FIELD: return createNumericalSearchField();
			case FormPackage.CHAR_SEARCH_FIELD: return createCharSearchField();
			case FormPackage.DATE_SEARCH_FIELD: return createDateSearchField();
			case FormPackage.CHOICE_SEARCH_FIELD: return createChoiceSearchField();
			case FormPackage.FILE_SEARCH_FIELD: return createFileSearchField();
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
			case FormPackage.FORM_GROUP_PRESENTATION_TYPE:
				return createFormGroupPresentationTypeFromString(eDataType, initialValue);
			case FormPackage.TEXT_WIDGET_TYPE:
				return createTextWidgetTypeFromString(eDataType, initialValue);
			case FormPackage.CHOICE_WIDGET_TYPE:
				return createChoiceWidgetTypeFromString(eDataType, initialValue);
			case FormPackage.MODEL_CHOICE_WIDGET_TYPE:
				return createModelChoiceWidgetTypeFromString(eDataType, initialValue);
			case FormPackage.CHAR_FIELD_SEARCH_OPERATORS:
				return createCharFieldSearchOperatorsFromString(eDataType, initialValue);
			case FormPackage.NUMERICAL_FIELD_SEARCH_OPERATORS:
				return createNumericalFieldSearchOperatorsFromString(eDataType, initialValue);
			case FormPackage.CHOICE_FIELD_SEARCH_OPERATORS:
				return createChoiceFieldSearchOperatorsFromString(eDataType, initialValue);
			case FormPackage.FILE_FIELD_SEARCH_OPERATORS:
				return createFileFieldSearchOperatorsFromString(eDataType, initialValue);
			case FormPackage.COMBINATION_OPERATORS:
				return createCombinationOperatorsFromString(eDataType, initialValue);
			case FormPackage.DATE_FIELD_SEARCH_OPERATORS:
				return createDateFieldSearchOperatorsFromString(eDataType, initialValue);
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
			case FormPackage.FORM_GROUP_PRESENTATION_TYPE:
				return convertFormGroupPresentationTypeToString(eDataType, instanceValue);
			case FormPackage.TEXT_WIDGET_TYPE:
				return convertTextWidgetTypeToString(eDataType, instanceValue);
			case FormPackage.CHOICE_WIDGET_TYPE:
				return convertChoiceWidgetTypeToString(eDataType, instanceValue);
			case FormPackage.MODEL_CHOICE_WIDGET_TYPE:
				return convertModelChoiceWidgetTypeToString(eDataType, instanceValue);
			case FormPackage.CHAR_FIELD_SEARCH_OPERATORS:
				return convertCharFieldSearchOperatorsToString(eDataType, instanceValue);
			case FormPackage.NUMERICAL_FIELD_SEARCH_OPERATORS:
				return convertNumericalFieldSearchOperatorsToString(eDataType, instanceValue);
			case FormPackage.CHOICE_FIELD_SEARCH_OPERATORS:
				return convertChoiceFieldSearchOperatorsToString(eDataType, instanceValue);
			case FormPackage.FILE_FIELD_SEARCH_OPERATORS:
				return convertFileFieldSearchOperatorsToString(eDataType, instanceValue);
			case FormPackage.COMBINATION_OPERATORS:
				return convertCombinationOperatorsToString(eDataType, instanceValue);
			case FormPackage.DATE_FIELD_SEARCH_OPERATORS:
				return convertDateFieldSearchOperatorsToString(eDataType, instanceValue);
			default:
				throw new IllegalArgumentException("The datatype '" + eDataType.getName() + "' is not a valid classifier");
		}
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
	public SearchForm createSearchForm() {
		SearchFormImpl searchForm = new SearchFormImpl();
		return searchForm;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NumericalField createNumericalField() {
		NumericalFieldImpl numericalField = new NumericalFieldImpl();
		return numericalField;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public StaticText createStaticText() {
		StaticTextImpl staticText = new StaticTextImpl();
		return staticText;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public SearchFormCollection createSearchFormCollection() {
		SearchFormCollectionImpl searchFormCollection = new SearchFormCollectionImpl();
		return searchFormCollection;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public SearchField createSearchField() {
		SearchFieldImpl searchField = new SearchFieldImpl();
		return searchField;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NumericalSearchField createNumericalSearchField() {
		NumericalSearchFieldImpl numericalSearchField = new NumericalSearchFieldImpl();
		return numericalSearchField;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public CharSearchField createCharSearchField() {
		CharSearchFieldImpl charSearchField = new CharSearchFieldImpl();
		return charSearchField;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public DateSearchField createDateSearchField() {
		DateSearchFieldImpl dateSearchField = new DateSearchFieldImpl();
		return dateSearchField;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public ChoiceSearchField createChoiceSearchField() {
		ChoiceSearchFieldImpl choiceSearchField = new ChoiceSearchFieldImpl();
		return choiceSearchField;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public FileSearchField createFileSearchField() {
		FileSearchFieldImpl fileSearchField = new FileSearchFieldImpl();
		return fileSearchField;
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
	public ClassFormCollection createClassFormCollection() {
		ClassFormCollectionImpl classFormCollection = new ClassFormCollectionImpl();
		return classFormCollection;
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
	public ModelChoiceWidgetType createModelChoiceWidgetTypeFromString(EDataType eDataType, String initialValue) {
		ModelChoiceWidgetType result = ModelChoiceWidgetType.get(initialValue);
		if (result == null) throw new IllegalArgumentException("The value '" + initialValue + "' is not a valid enumerator of '" + eDataType.getName() + "'");
		return result;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String convertModelChoiceWidgetTypeToString(EDataType eDataType, Object instanceValue) {
		return instanceValue == null ? null : instanceValue.toString();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public CharFieldSearchOperators createCharFieldSearchOperatorsFromString(EDataType eDataType, String initialValue) {
		CharFieldSearchOperators result = CharFieldSearchOperators.get(initialValue);
		if (result == null) throw new IllegalArgumentException("The value '" + initialValue + "' is not a valid enumerator of '" + eDataType.getName() + "'");
		return result;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String convertCharFieldSearchOperatorsToString(EDataType eDataType, Object instanceValue) {
		return instanceValue == null ? null : instanceValue.toString();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NumericalFieldSearchOperators createNumericalFieldSearchOperatorsFromString(EDataType eDataType, String initialValue) {
		NumericalFieldSearchOperators result = NumericalFieldSearchOperators.get(initialValue);
		if (result == null) throw new IllegalArgumentException("The value '" + initialValue + "' is not a valid enumerator of '" + eDataType.getName() + "'");
		return result;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String convertNumericalFieldSearchOperatorsToString(EDataType eDataType, Object instanceValue) {
		return instanceValue == null ? null : instanceValue.toString();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public ChoiceFieldSearchOperators createChoiceFieldSearchOperatorsFromString(EDataType eDataType, String initialValue) {
		ChoiceFieldSearchOperators result = ChoiceFieldSearchOperators.get(initialValue);
		if (result == null) throw new IllegalArgumentException("The value '" + initialValue + "' is not a valid enumerator of '" + eDataType.getName() + "'");
		return result;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String convertChoiceFieldSearchOperatorsToString(EDataType eDataType, Object instanceValue) {
		return instanceValue == null ? null : instanceValue.toString();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public FileFieldSearchOperators createFileFieldSearchOperatorsFromString(EDataType eDataType, String initialValue) {
		FileFieldSearchOperators result = FileFieldSearchOperators.get(initialValue);
		if (result == null) throw new IllegalArgumentException("The value '" + initialValue + "' is not a valid enumerator of '" + eDataType.getName() + "'");
		return result;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String convertFileFieldSearchOperatorsToString(EDataType eDataType, Object instanceValue) {
		return instanceValue == null ? null : instanceValue.toString();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public CombinationOperators createCombinationOperatorsFromString(EDataType eDataType, String initialValue) {
		CombinationOperators result = CombinationOperators.get(initialValue);
		if (result == null) throw new IllegalArgumentException("The value '" + initialValue + "' is not a valid enumerator of '" + eDataType.getName() + "'");
		return result;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String convertCombinationOperatorsToString(EDataType eDataType, Object instanceValue) {
		return instanceValue == null ? null : instanceValue.toString();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public DateFieldSearchOperators createDateFieldSearchOperatorsFromString(EDataType eDataType, String initialValue) {
		DateFieldSearchOperators result = DateFieldSearchOperators.get(initialValue);
		if (result == null) throw new IllegalArgumentException("The value '" + initialValue + "' is not a valid enumerator of '" + eDataType.getName() + "'");
		return result;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String convertDateFieldSearchOperatorsToString(EDataType eDataType, Object instanceValue) {
		return instanceValue == null ? null : instanceValue.toString();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public FormPackage getFormPackage() {
		return (FormPackage)getEPackage();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @deprecated
	 * @generated
	 */
	@Deprecated
	public static FormPackage getPackage() {
		return FormPackage.eINSTANCE;
	}

	

} //formFactoryImpl
