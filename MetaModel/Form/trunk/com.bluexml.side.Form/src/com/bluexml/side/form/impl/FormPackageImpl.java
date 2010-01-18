/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package com.bluexml.side.form.impl;

import org.eclipse.emf.ecore.EAttribute;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EEnum;
import org.eclipse.emf.ecore.EGenericType;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.EReference;
import org.eclipse.emf.ecore.EValidator;
import org.eclipse.emf.ecore.impl.EPackageImpl;

import com.bluexml.side.clazz.ClazzPackage;
import com.bluexml.side.common.CommonPackage;
import com.bluexml.side.form.ActionField;
import com.bluexml.side.form.BooleanField;
import com.bluexml.side.form.CharField;
import com.bluexml.side.form.CharFieldSearchOperators;
import com.bluexml.side.form.ChoiceField;
import com.bluexml.side.form.ChoiceFieldSearchOperators;
import com.bluexml.side.form.ChoiceWidgetType;
import com.bluexml.side.form.ClassFormCollection;
import com.bluexml.side.form.ClassReference;
import com.bluexml.side.form.CombinationOperators;
import com.bluexml.side.form.DateField;
import com.bluexml.side.form.DateTimeField;
import com.bluexml.side.form.DecimalField;
import com.bluexml.side.form.EmailField;
import com.bluexml.side.form.Field;
import com.bluexml.side.form.FieldSearchOperators;
import com.bluexml.side.form.FileField;
import com.bluexml.side.form.FileFieldPreviewType;
import com.bluexml.side.form.FileFieldSearchOperators;
import com.bluexml.side.form.FloatField;
import com.bluexml.side.form.FormAspect;
import com.bluexml.side.form.FormClass;
import com.bluexml.side.form.FormCollection;
import com.bluexml.side.form.FormContainer;
import com.bluexml.side.form.FormElement;
import com.bluexml.side.form.FormFactory;
import com.bluexml.side.form.FormGroup;
import com.bluexml.side.form.FormGroupPresentationType;
import com.bluexml.side.form.FormPackage;
import com.bluexml.side.form.FormWorkflow;
import com.bluexml.side.form.ImageField;
import com.bluexml.side.form.IntegerField;
import com.bluexml.side.form.ModelChoiceField;
import com.bluexml.side.form.ModelChoiceWidgetType;
import com.bluexml.side.form.NumericalField;
import com.bluexml.side.form.NumericalFieldSearchOperators;
import com.bluexml.side.form.PasswordField;
import com.bluexml.side.form.PhoneNumberField;
import com.bluexml.side.form.Reference;
import com.bluexml.side.form.RegexField;
import com.bluexml.side.form.SearchForm;
import com.bluexml.side.form.SearchOperatorConfiguration;
import com.bluexml.side.form.StaticText;
import com.bluexml.side.form.TextField;
import com.bluexml.side.form.TextWidgetType;
import com.bluexml.side.form.TimeField;
import com.bluexml.side.form.URLField;
import com.bluexml.side.form.VirtualField;
import com.bluexml.side.form.WorkflowFormCollection;
import com.bluexml.side.form.util.FormValidator;
import com.bluexml.side.workflow.WorkflowPackage;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model <b>Package</b>.
 * <!-- end-user-doc -->
 * @generated
 */
public class FormPackageImpl extends EPackageImpl implements FormPackage {
	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass formElementEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass formGroupEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass fieldEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass booleanFieldEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass charFieldEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass dateFieldEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass dateTimeFieldEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass decimalFieldEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass floatFieldEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass integerFieldEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass modelChoiceFieldEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass emailFieldEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass fileFieldEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass imageFieldEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass timeFieldEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass urlFieldEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass phoneNumberFieldEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass formAspectEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass formClassEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass referenceEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass formCollectionEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass choiceFieldEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass regexFieldEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass classReferenceEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass passwordFieldEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass virtualFieldEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass actionFieldEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass textFieldEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass searchFormEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass numericalFieldEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass searchOperatorConfigurationEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass staticTextEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass workflowFormCollectionEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass classFormCollectionEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass formWorkflowEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass formContainerEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EEnum formGroupPresentationTypeEEnum = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EEnum textWidgetTypeEEnum = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EEnum choiceWidgetTypeEEnum = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EEnum modelChoiceWidgetTypeEEnum = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EEnum fieldSearchOperatorsEEnum = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EEnum charFieldSearchOperatorsEEnum = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EEnum numericalFieldSearchOperatorsEEnum = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EEnum choiceFieldSearchOperatorsEEnum = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EEnum fileFieldSearchOperatorsEEnum = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EEnum combinationOperatorsEEnum = null;

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
	 * @see com.bluexml.side.form.FormPackage#eNS_URI
	 * @see #init()
	 * @generated
	 */
	private FormPackageImpl() {
		super(eNS_URI, FormFactory.eINSTANCE);
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
	 * <p>This method is used to initialize {@link FormPackage#eINSTANCE} when that field is accessed.
	 * Clients should not invoke it directly. Instead, they should simply access that field to obtain the package.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #eNS_URI
	 * @see #createPackageContents()
	 * @see #initializePackageContents()
	 * @generated
	 */
	public static FormPackage init() {
		if (isInited) return (FormPackage)EPackage.Registry.INSTANCE.getEPackage(FormPackage.eNS_URI);

		// Obtain or create and register package
		FormPackageImpl theFormPackage = (FormPackageImpl)(EPackage.Registry.INSTANCE.get(eNS_URI) instanceof FormPackageImpl ? EPackage.Registry.INSTANCE.get(eNS_URI) : new FormPackageImpl());

		isInited = true;

		// Initialize simple dependencies
		WorkflowPackage.eINSTANCE.eClass();

		// Create package meta-data objects
		theFormPackage.createPackageContents();

		// Initialize created meta-data
		theFormPackage.initializePackageContents();

		// Register package validator
		EValidator.Registry.INSTANCE.put
			(theFormPackage, 
			 new EValidator.Descriptor() {
				 public EValidator getEValidator() {
					 return FormValidator.INSTANCE;
				 }
			 });

		// Mark meta-data to indicate it can't be changed
		theFormPackage.freeze();

  
		// Update the registry and return the package
		EPackage.Registry.INSTANCE.put(FormPackage.eNS_URI, theFormPackage);
		return theFormPackage;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getFormElement() {
		return formElementEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getFormElement_Label() {
		return (EAttribute)formElementEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getFormElement_Id() {
		return (EAttribute)formElementEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getFormElement_Help_text() {
		return (EAttribute)formElementEClass.getEStructuralFeatures().get(2);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getFormElement_Ref() {
		return (EReference)formElementEClass.getEStructuralFeatures().get(3);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getFormElement_Hidden() {
		return (EAttribute)formElementEClass.getEStructuralFeatures().get(4);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getFormGroup() {
		return formGroupEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getFormGroup_Children() {
		return (EReference)formGroupEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getFormGroup_Presentation() {
		return (EAttribute)formGroupEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getFormGroup_Disabled() {
		return (EReference)formGroupEClass.getEStructuralFeatures().get(2);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getField() {
		return fieldEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getField_Mandatory() {
		return (EAttribute)fieldEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getField_Error_messages() {
		return (EAttribute)fieldEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getField_Initial() {
		return (EAttribute)fieldEClass.getEStructuralFeatures().get(2);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getField_Disabled() {
		return (EAttribute)fieldEClass.getEStructuralFeatures().get(3);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getField_Field_size() {
		return (EAttribute)fieldEClass.getEStructuralFeatures().get(4);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getField_Style() {
		return (EAttribute)fieldEClass.getEStructuralFeatures().get(5);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getField_SearchOperatorConfiguration() {
		return (EReference)fieldEClass.getEStructuralFeatures().get(6);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getField_Mockup() {
		return (EAttribute)fieldEClass.getEStructuralFeatures().get(7);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getBooleanField() {
		return booleanFieldEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getCharField() {
		return charFieldEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getCharField_Min_length() {
		return (EAttribute)charFieldEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getCharField_Max_length() {
		return (EAttribute)charFieldEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getDateField() {
		return dateFieldEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getDateField_Input_formats() {
		return (EAttribute)dateFieldEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getDateField_Min_date() {
		return (EAttribute)dateFieldEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getDateField_Max_date() {
		return (EAttribute)dateFieldEClass.getEStructuralFeatures().get(2);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getDateTimeField() {
		return dateTimeFieldEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getDecimalField() {
		return decimalFieldEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getDecimalField_Min_value() {
		return (EAttribute)decimalFieldEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getDecimalField_Max_value() {
		return (EAttribute)decimalFieldEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getDecimalField_Max_digits() {
		return (EAttribute)decimalFieldEClass.getEStructuralFeatures().get(2);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getDecimalField_Decimal_places() {
		return (EAttribute)decimalFieldEClass.getEStructuralFeatures().get(3);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getFloatField() {
		return floatFieldEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getFloatField_Min_value() {
		return (EAttribute)floatFieldEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getFloatField_Max_value() {
		return (EAttribute)floatFieldEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getIntegerField() {
		return integerFieldEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getIntegerField_Min_value() {
		return (EAttribute)integerFieldEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getIntegerField_Max_value() {
		return (EAttribute)integerFieldEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getModelChoiceField() {
		return modelChoiceFieldEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getModelChoiceField_Min_bound() {
		return (EAttribute)modelChoiceFieldEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getModelChoiceField_Max_bound() {
		return (EAttribute)modelChoiceFieldEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getModelChoiceField_Target() {
		return (EReference)modelChoiceFieldEClass.getEStructuralFeatures().get(2);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getModelChoiceField_Association_formClass() {
		return (EReference)modelChoiceFieldEClass.getEStructuralFeatures().get(3);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getModelChoiceField_Widget() {
		return (EAttribute)modelChoiceFieldEClass.getEStructuralFeatures().get(4);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getModelChoiceField_Show_actions() {
		return (EAttribute)modelChoiceFieldEClass.getEStructuralFeatures().get(5);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getModelChoiceField_Format_pattern() {
		return (EAttribute)modelChoiceFieldEClass.getEStructuralFeatures().get(6);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getModelChoiceField_Label_length() {
		return (EAttribute)modelChoiceFieldEClass.getEStructuralFeatures().get(7);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getEmailField() {
		return emailFieldEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getFileField() {
		return fileFieldEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getFileField_InRepository() {
		return (EAttribute)fileFieldEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getImageField() {
		return imageFieldEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getTimeField() {
		return timeFieldEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getURLField() {
		return urlFieldEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getURLField_Verify_exists() {
		return (EAttribute)urlFieldEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getPhoneNumberField() {
		return phoneNumberFieldEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getPhoneNumberField_Input_formats() {
		return (EAttribute)phoneNumberFieldEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getFormAspect() {
		return formAspectEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getFormClass() {
		return formClassEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getFormClass_Has_content() {
		return (EAttribute)formClassEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getReference() {
		return referenceEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getFormCollection() {
		return formCollectionEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getFormCollection_Forms() {
		return (EReference)formCollectionEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getChoiceField() {
		return choiceFieldEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getChoiceField_Min_bound() {
		return (EAttribute)choiceFieldEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getChoiceField_Max_bound() {
		return (EAttribute)choiceFieldEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getChoiceField_Widget() {
		return (EAttribute)choiceFieldEClass.getEStructuralFeatures().get(2);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getChoiceField_Multiple() {
		return (EAttribute)choiceFieldEClass.getEStructuralFeatures().get(3);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getRegexField() {
		return regexFieldEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getRegexField_Regex() {
		return (EAttribute)regexFieldEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getClassReference() {
		return classReferenceEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getClassReference_Real_class() {
		return (EReference)classReferenceEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getPasswordField() {
		return passwordFieldEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getVirtualField() {
		return virtualFieldEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getVirtualField_Link() {
		return (EReference)virtualFieldEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getActionField() {
		return actionFieldEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getTextField() {
		return textFieldEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getTextField_Widget() {
		return (EAttribute)textFieldEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getSearchForm() {
		return searchFormEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getSearchForm_DataForm() {
		return (EReference)searchFormEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getSearchForm_CombinationOperator() {
		return (EAttribute)searchFormEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getNumericalField() {
		return numericalFieldEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getSearchOperatorConfiguration() {
		return searchOperatorConfigurationEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getSearchOperatorConfiguration_DefaultOperator() {
		return (EAttribute)searchOperatorConfigurationEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getSearchOperatorConfiguration_ProposedOperators() {
		return (EAttribute)searchOperatorConfigurationEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getStaticText() {
		return staticTextEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getStaticText_Value() {
		return (EAttribute)staticTextEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getWorkflowFormCollection() {
		return workflowFormCollectionEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getWorkflowFormCollection_Linked_process() {
		return (EReference)workflowFormCollectionEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getClassFormCollection() {
		return classFormCollectionEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getFormWorkflow() {
		return formWorkflowEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getFormWorkflow_DataForm() {
		return (EReference)formWorkflowEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getFormContainer() {
		return formContainerEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EEnum getFormGroupPresentationType() {
		return formGroupPresentationTypeEEnum;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EEnum getTextWidgetType() {
		return textWidgetTypeEEnum;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EEnum getChoiceWidgetType() {
		return choiceWidgetTypeEEnum;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EEnum getModelChoiceWidgetType() {
		return modelChoiceWidgetTypeEEnum;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EEnum getFieldSearchOperators() {
		return fieldSearchOperatorsEEnum;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EEnum getCharFieldSearchOperators() {
		return charFieldSearchOperatorsEEnum;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EEnum getNumericalFieldSearchOperators() {
		return numericalFieldSearchOperatorsEEnum;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EEnum getChoiceFieldSearchOperators() {
		return choiceFieldSearchOperatorsEEnum;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EEnum getFileFieldSearchOperators() {
		return fileFieldSearchOperatorsEEnum;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EEnum getCombinationOperators() {
		return combinationOperatorsEEnum;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public FormFactory getFormFactory() {
		return (FormFactory)getEFactoryInstance();
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
		formElementEClass = createEClass(FORM_ELEMENT);
		createEAttribute(formElementEClass, FORM_ELEMENT__LABEL);
		createEAttribute(formElementEClass, FORM_ELEMENT__ID);
		createEAttribute(formElementEClass, FORM_ELEMENT__HELP_TEXT);
		createEReference(formElementEClass, FORM_ELEMENT__REF);
		createEAttribute(formElementEClass, FORM_ELEMENT__HIDDEN);

		formCollectionEClass = createEClass(FORM_COLLECTION);
		createEReference(formCollectionEClass, FORM_COLLECTION__FORMS);

		formGroupEClass = createEClass(FORM_GROUP);
		createEReference(formGroupEClass, FORM_GROUP__CHILDREN);
		createEAttribute(formGroupEClass, FORM_GROUP__PRESENTATION);
		createEReference(formGroupEClass, FORM_GROUP__DISABLED);

		workflowFormCollectionEClass = createEClass(WORKFLOW_FORM_COLLECTION);
		createEReference(workflowFormCollectionEClass, WORKFLOW_FORM_COLLECTION__LINKED_PROCESS);

		classFormCollectionEClass = createEClass(CLASS_FORM_COLLECTION);

		fieldEClass = createEClass(FIELD);
		createEAttribute(fieldEClass, FIELD__MANDATORY);
		createEAttribute(fieldEClass, FIELD__ERROR_MESSAGES);
		createEAttribute(fieldEClass, FIELD__INITIAL);
		createEAttribute(fieldEClass, FIELD__DISABLED);
		createEAttribute(fieldEClass, FIELD__FIELD_SIZE);
		createEAttribute(fieldEClass, FIELD__STYLE);
		createEReference(fieldEClass, FIELD__SEARCH_OPERATOR_CONFIGURATION);
		createEAttribute(fieldEClass, FIELD__MOCKUP);

		formContainerEClass = createEClass(FORM_CONTAINER);

		formWorkflowEClass = createEClass(FORM_WORKFLOW);
		createEReference(formWorkflowEClass, FORM_WORKFLOW__DATA_FORM);

		formClassEClass = createEClass(FORM_CLASS);
		createEAttribute(formClassEClass, FORM_CLASS__HAS_CONTENT);

		booleanFieldEClass = createEClass(BOOLEAN_FIELD);

		charFieldEClass = createEClass(CHAR_FIELD);
		createEAttribute(charFieldEClass, CHAR_FIELD__MIN_LENGTH);
		createEAttribute(charFieldEClass, CHAR_FIELD__MAX_LENGTH);

		dateFieldEClass = createEClass(DATE_FIELD);
		createEAttribute(dateFieldEClass, DATE_FIELD__INPUT_FORMATS);
		createEAttribute(dateFieldEClass, DATE_FIELD__MIN_DATE);
		createEAttribute(dateFieldEClass, DATE_FIELD__MAX_DATE);

		dateTimeFieldEClass = createEClass(DATE_TIME_FIELD);

		decimalFieldEClass = createEClass(DECIMAL_FIELD);
		createEAttribute(decimalFieldEClass, DECIMAL_FIELD__MIN_VALUE);
		createEAttribute(decimalFieldEClass, DECIMAL_FIELD__MAX_VALUE);
		createEAttribute(decimalFieldEClass, DECIMAL_FIELD__MAX_DIGITS);
		createEAttribute(decimalFieldEClass, DECIMAL_FIELD__DECIMAL_PLACES);

		floatFieldEClass = createEClass(FLOAT_FIELD);
		createEAttribute(floatFieldEClass, FLOAT_FIELD__MIN_VALUE);
		createEAttribute(floatFieldEClass, FLOAT_FIELD__MAX_VALUE);

		integerFieldEClass = createEClass(INTEGER_FIELD);
		createEAttribute(integerFieldEClass, INTEGER_FIELD__MIN_VALUE);
		createEAttribute(integerFieldEClass, INTEGER_FIELD__MAX_VALUE);

		modelChoiceFieldEClass = createEClass(MODEL_CHOICE_FIELD);
		createEAttribute(modelChoiceFieldEClass, MODEL_CHOICE_FIELD__MIN_BOUND);
		createEAttribute(modelChoiceFieldEClass, MODEL_CHOICE_FIELD__MAX_BOUND);
		createEReference(modelChoiceFieldEClass, MODEL_CHOICE_FIELD__TARGET);
		createEReference(modelChoiceFieldEClass, MODEL_CHOICE_FIELD__ASSOCIATION_FORM_CLASS);
		createEAttribute(modelChoiceFieldEClass, MODEL_CHOICE_FIELD__WIDGET);
		createEAttribute(modelChoiceFieldEClass, MODEL_CHOICE_FIELD__SHOW_ACTIONS);
		createEAttribute(modelChoiceFieldEClass, MODEL_CHOICE_FIELD__FORMAT_PATTERN);
		createEAttribute(modelChoiceFieldEClass, MODEL_CHOICE_FIELD__LABEL_LENGTH);

		emailFieldEClass = createEClass(EMAIL_FIELD);

		fileFieldEClass = createEClass(FILE_FIELD);
		createEAttribute(fileFieldEClass, FILE_FIELD__IN_REPOSITORY);

		imageFieldEClass = createEClass(IMAGE_FIELD);

		timeFieldEClass = createEClass(TIME_FIELD);

		urlFieldEClass = createEClass(URL_FIELD);
		createEAttribute(urlFieldEClass, URL_FIELD__VERIFY_EXISTS);

		phoneNumberFieldEClass = createEClass(PHONE_NUMBER_FIELD);
		createEAttribute(phoneNumberFieldEClass, PHONE_NUMBER_FIELD__INPUT_FORMATS);

		formAspectEClass = createEClass(FORM_ASPECT);

		referenceEClass = createEClass(REFERENCE);

		choiceFieldEClass = createEClass(CHOICE_FIELD);
		createEAttribute(choiceFieldEClass, CHOICE_FIELD__MIN_BOUND);
		createEAttribute(choiceFieldEClass, CHOICE_FIELD__MAX_BOUND);
		createEAttribute(choiceFieldEClass, CHOICE_FIELD__WIDGET);
		createEAttribute(choiceFieldEClass, CHOICE_FIELD__MULTIPLE);

		regexFieldEClass = createEClass(REGEX_FIELD);
		createEAttribute(regexFieldEClass, REGEX_FIELD__REGEX);

		classReferenceEClass = createEClass(CLASS_REFERENCE);
		createEReference(classReferenceEClass, CLASS_REFERENCE__REAL_CLASS);

		passwordFieldEClass = createEClass(PASSWORD_FIELD);

		virtualFieldEClass = createEClass(VIRTUAL_FIELD);
		createEReference(virtualFieldEClass, VIRTUAL_FIELD__LINK);

		actionFieldEClass = createEClass(ACTION_FIELD);

		textFieldEClass = createEClass(TEXT_FIELD);
		createEAttribute(textFieldEClass, TEXT_FIELD__WIDGET);

		searchFormEClass = createEClass(SEARCH_FORM);
		createEReference(searchFormEClass, SEARCH_FORM__DATA_FORM);
		createEAttribute(searchFormEClass, SEARCH_FORM__COMBINATION_OPERATOR);

		numericalFieldEClass = createEClass(NUMERICAL_FIELD);

		searchOperatorConfigurationEClass = createEClass(SEARCH_OPERATOR_CONFIGURATION);
		createEAttribute(searchOperatorConfigurationEClass, SEARCH_OPERATOR_CONFIGURATION__DEFAULT_OPERATOR);
		createEAttribute(searchOperatorConfigurationEClass, SEARCH_OPERATOR_CONFIGURATION__PROPOSED_OPERATORS);

		staticTextEClass = createEClass(STATIC_TEXT);
		createEAttribute(staticTextEClass, STATIC_TEXT__VALUE);

		// Create enums
		formGroupPresentationTypeEEnum = createEEnum(FORM_GROUP_PRESENTATION_TYPE);
		textWidgetTypeEEnum = createEEnum(TEXT_WIDGET_TYPE);
		choiceWidgetTypeEEnum = createEEnum(CHOICE_WIDGET_TYPE);
		modelChoiceWidgetTypeEEnum = createEEnum(MODEL_CHOICE_WIDGET_TYPE);
		fieldSearchOperatorsEEnum = createEEnum(FIELD_SEARCH_OPERATORS);
		charFieldSearchOperatorsEEnum = createEEnum(CHAR_FIELD_SEARCH_OPERATORS);
		numericalFieldSearchOperatorsEEnum = createEEnum(NUMERICAL_FIELD_SEARCH_OPERATORS);
		choiceFieldSearchOperatorsEEnum = createEEnum(CHOICE_FIELD_SEARCH_OPERATORS);
		fileFieldSearchOperatorsEEnum = createEEnum(FILE_FIELD_SEARCH_OPERATORS);
		combinationOperatorsEEnum = createEEnum(COMBINATION_OPERATORS);
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
		WorkflowPackage theWorkflowPackage = (WorkflowPackage)EPackage.Registry.INSTANCE.getEPackage(WorkflowPackage.eNS_URI);
		ClazzPackage theClazzPackage = (ClazzPackage)EPackage.Registry.INSTANCE.getEPackage(ClazzPackage.eNS_URI);

		// Create type parameters

		// Set bounds for type parameters

		// Add supertypes to classes
		formElementEClass.getESuperTypes().add(theCommonPackage.getModelElement());
		formCollectionEClass.getESuperTypes().add(theCommonPackage.getPackage());
		formGroupEClass.getESuperTypes().add(this.getFormElement());
		workflowFormCollectionEClass.getESuperTypes().add(this.getFormCollection());
		classFormCollectionEClass.getESuperTypes().add(this.getFormCollection());
		fieldEClass.getESuperTypes().add(this.getFormElement());
		formContainerEClass.getESuperTypes().add(this.getFormGroup());
		formWorkflowEClass.getESuperTypes().add(this.getFormContainer());
		formClassEClass.getESuperTypes().add(this.getFormContainer());
		formClassEClass.getESuperTypes().add(this.getClassReference());
		booleanFieldEClass.getESuperTypes().add(this.getField());
		charFieldEClass.getESuperTypes().add(this.getField());
		dateFieldEClass.getESuperTypes().add(this.getField());
		dateTimeFieldEClass.getESuperTypes().add(this.getDateField());
		decimalFieldEClass.getESuperTypes().add(this.getNumericalField());
		floatFieldEClass.getESuperTypes().add(this.getNumericalField());
		integerFieldEClass.getESuperTypes().add(this.getNumericalField());
		modelChoiceFieldEClass.getESuperTypes().add(this.getField());
		modelChoiceFieldEClass.getESuperTypes().add(this.getClassReference());
		emailFieldEClass.getESuperTypes().add(this.getCharField());
		fileFieldEClass.getESuperTypes().add(this.getField());
		imageFieldEClass.getESuperTypes().add(this.getFileField());
		timeFieldEClass.getESuperTypes().add(this.getDateField());
		urlFieldEClass.getESuperTypes().add(this.getCharField());
		phoneNumberFieldEClass.getESuperTypes().add(this.getCharField());
		formAspectEClass.getESuperTypes().add(this.getFormGroup());
		referenceEClass.getESuperTypes().add(this.getModelChoiceField());
		choiceFieldEClass.getESuperTypes().add(this.getField());
		regexFieldEClass.getESuperTypes().add(this.getCharField());
		passwordFieldEClass.getESuperTypes().add(this.getCharField());
		virtualFieldEClass.getESuperTypes().add(this.getField());
		actionFieldEClass.getESuperTypes().add(this.getField());
		textFieldEClass.getESuperTypes().add(this.getCharField());
		searchFormEClass.getESuperTypes().add(this.getFormContainer());
		numericalFieldEClass.getESuperTypes().add(this.getField());
		staticTextEClass.getESuperTypes().add(this.getFormElement());

		// Initialize classes and features; add operations and parameters
		initEClass(formElementEClass, FormElement.class, "FormElement", IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEAttribute(getFormElement_Label(), ecorePackage.getEString(), "label", null, 0, 1, FormElement.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getFormElement_Id(), ecorePackage.getEString(), "id", null, 1, 1, FormElement.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getFormElement_Help_text(), ecorePackage.getEString(), "help_text", null, 0, 1, FormElement.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getFormElement_Ref(), theCommonPackage.getModelElement(), null, "ref", null, 0, 1, FormElement.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getFormElement_Hidden(), ecorePackage.getEBoolean(), "hidden", null, 0, 1, FormElement.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(formCollectionEClass, FormCollection.class, "FormCollection", IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEReference(getFormCollection_Forms(), this.getFormContainer(), null, "forms", null, 0, -1, FormCollection.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(formGroupEClass, FormGroup.class, "FormGroup", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEReference(getFormGroup_Children(), this.getFormElement(), null, "children", null, 0, -1, FormGroup.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getFormGroup_Presentation(), this.getFormGroupPresentationType(), "presentation", null, 0, 1, FormGroup.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getFormGroup_Disabled(), this.getFormElement(), null, "disabled", null, 0, -1, FormGroup.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		addEOperation(formGroupEClass, this.getField(), "getFields", 0, -1, IS_UNIQUE, IS_ORDERED);

		initEClass(workflowFormCollectionEClass, WorkflowFormCollection.class, "WorkflowFormCollection", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEReference(getWorkflowFormCollection_Linked_process(), theWorkflowPackage.getProcess(), null, "linked_process", null, 0, 1, WorkflowFormCollection.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(classFormCollectionEClass, ClassFormCollection.class, "ClassFormCollection", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);

		initEClass(fieldEClass, Field.class, "Field", IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEAttribute(getField_Mandatory(), ecorePackage.getEBoolean(), "mandatory", null, 0, 1, Field.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		EGenericType g1 = createEGenericType(ecorePackage.getEMap());
		EGenericType g2 = createEGenericType(ecorePackage.getEString());
		g1.getETypeArguments().add(g2);
		g2 = createEGenericType(ecorePackage.getEString());
		g1.getETypeArguments().add(g2);
		initEAttribute(getField_Error_messages(), g1, "error_messages", null, 0, 1, Field.class, IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getField_Initial(), ecorePackage.getEString(), "initial", null, 0, 1, Field.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getField_Disabled(), ecorePackage.getEBoolean(), "disabled", null, 0, 1, Field.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getField_Field_size(), ecorePackage.getEInt(), "field_size", null, 0, 1, Field.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getField_Style(), ecorePackage.getEString(), "style", null, 0, 1, Field.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getField_SearchOperatorConfiguration(), this.getSearchOperatorConfiguration(), null, "searchOperatorConfiguration", null, 0, 1, Field.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getField_Mockup(), ecorePackage.getEString(), "mockup", null, 0, -1, Field.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		addEOperation(fieldEClass, ecorePackage.getEString(), "getLabel", 0, 1, IS_UNIQUE, IS_ORDERED);

		addEOperation(fieldEClass, ecorePackage.getEString(), "getInitialProposedOperators", 0, -1, IS_UNIQUE, IS_ORDERED);

		initEClass(formContainerEClass, FormContainer.class, "FormContainer", IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);

		addEOperation(formContainerEClass, ecorePackage.getEString(), "getLabel", 0, 1, IS_UNIQUE, IS_ORDERED);

		initEClass(formWorkflowEClass, FormWorkflow.class, "FormWorkflow", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEReference(getFormWorkflow_DataForm(), this.getFormClass(), null, "DataForm", null, 0, 1, FormWorkflow.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(formClassEClass, FormClass.class, "FormClass", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEAttribute(getFormClass_Has_content(), ecorePackage.getEBoolean(), "Has_content", "false", 0, 1, FormClass.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(booleanFieldEClass, BooleanField.class, "BooleanField", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);

		initEClass(charFieldEClass, CharField.class, "CharField", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEAttribute(getCharField_Min_length(), ecorePackage.getEInt(), "min_length", null, 0, 1, CharField.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getCharField_Max_length(), ecorePackage.getEInt(), "max_length", null, 0, 1, CharField.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(dateFieldEClass, DateField.class, "DateField", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEAttribute(getDateField_Input_formats(), ecorePackage.getEString(), "input_formats", null, 0, -1, DateField.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getDateField_Min_date(), ecorePackage.getEDate(), "min_date", null, 0, 1, DateField.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getDateField_Max_date(), ecorePackage.getEDate(), "max_date", null, 0, 1, DateField.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(dateTimeFieldEClass, DateTimeField.class, "DateTimeField", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);

		initEClass(decimalFieldEClass, DecimalField.class, "DecimalField", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEAttribute(getDecimalField_Min_value(), ecorePackage.getEString(), "min_value", null, 0, 1, DecimalField.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getDecimalField_Max_value(), ecorePackage.getEString(), "max_value", null, 0, 1, DecimalField.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getDecimalField_Max_digits(), ecorePackage.getEInt(), "max_digits", null, 0, 1, DecimalField.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getDecimalField_Decimal_places(), ecorePackage.getEInt(), "decimal_places", null, 0, 1, DecimalField.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(floatFieldEClass, FloatField.class, "FloatField", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEAttribute(getFloatField_Min_value(), ecorePackage.getEFloat(), "min_value", null, 0, 1, FloatField.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getFloatField_Max_value(), ecorePackage.getEFloat(), "max_value", null, 0, 1, FloatField.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(integerFieldEClass, IntegerField.class, "IntegerField", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEAttribute(getIntegerField_Min_value(), ecorePackage.getEInt(), "min_value", null, 0, 1, IntegerField.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getIntegerField_Max_value(), ecorePackage.getEInt(), "max_value", null, 0, 1, IntegerField.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(modelChoiceFieldEClass, ModelChoiceField.class, "ModelChoiceField", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEAttribute(getModelChoiceField_Min_bound(), ecorePackage.getEInt(), "min_bound", null, 0, 1, ModelChoiceField.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getModelChoiceField_Max_bound(), ecorePackage.getEInt(), "max_bound", null, 0, 1, ModelChoiceField.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getModelChoiceField_Target(), this.getFormContainer(), null, "target", null, 0, -1, ModelChoiceField.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getModelChoiceField_Association_formClass(), this.getFormContainer(), null, "association_formClass", null, 0, -1, ModelChoiceField.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getModelChoiceField_Widget(), this.getModelChoiceWidgetType(), "widget", null, 0, 1, ModelChoiceField.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getModelChoiceField_Show_actions(), ecorePackage.getEBoolean(), "show_actions", "true", 0, 1, ModelChoiceField.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getModelChoiceField_Format_pattern(), ecorePackage.getEString(), "format_pattern", null, 0, 1, ModelChoiceField.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getModelChoiceField_Label_length(), ecorePackage.getEInt(), "label_length", "0", 0, 1, ModelChoiceField.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, !IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(emailFieldEClass, EmailField.class, "EmailField", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);

		initEClass(fileFieldEClass, FileField.class, "FileField", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEAttribute(getFileField_InRepository(), ecorePackage.getEBoolean(), "inRepository", "false", 0, 1, FileField.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(imageFieldEClass, ImageField.class, "ImageField", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);

		initEClass(timeFieldEClass, TimeField.class, "TimeField", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);

		initEClass(urlFieldEClass, URLField.class, "URLField", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEAttribute(getURLField_Verify_exists(), ecorePackage.getEBoolean(), "verify_exists", null, 0, 1, URLField.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(phoneNumberFieldEClass, PhoneNumberField.class, "PhoneNumberField", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEAttribute(getPhoneNumberField_Input_formats(), ecorePackage.getEString(), "input_formats", null, 0, 1, PhoneNumberField.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(formAspectEClass, FormAspect.class, "FormAspect", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);

		initEClass(referenceEClass, Reference.class, "Reference", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);

		initEClass(choiceFieldEClass, ChoiceField.class, "ChoiceField", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEAttribute(getChoiceField_Min_bound(), ecorePackage.getEInt(), "min_bound", null, 0, 1, ChoiceField.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getChoiceField_Max_bound(), ecorePackage.getEInt(), "max_bound", null, 0, 1, ChoiceField.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getChoiceField_Widget(), this.getChoiceWidgetType(), "widget", null, 0, 1, ChoiceField.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getChoiceField_Multiple(), ecorePackage.getEBoolean(), "multiple", null, 0, 1, ChoiceField.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(regexFieldEClass, RegexField.class, "RegexField", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEAttribute(getRegexField_Regex(), ecorePackage.getEString(), "regex", null, 0, 1, RegexField.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(classReferenceEClass, ClassReference.class, "ClassReference", IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEReference(getClassReference_Real_class(), theClazzPackage.getClazz(), null, "real_class", null, 0, 1, ClassReference.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(passwordFieldEClass, PasswordField.class, "PasswordField", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);

		initEClass(virtualFieldEClass, VirtualField.class, "VirtualField", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEReference(getVirtualField_Link(), this.getField(), null, "link", null, 0, 1, VirtualField.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(actionFieldEClass, ActionField.class, "ActionField", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);

		initEClass(textFieldEClass, TextField.class, "TextField", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEAttribute(getTextField_Widget(), this.getTextWidgetType(), "widget", null, 0, 1, TextField.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(searchFormEClass, SearchForm.class, "SearchForm", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEReference(getSearchForm_DataForm(), this.getFormClass(), null, "dataForm", null, 0, 1, SearchForm.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getSearchForm_CombinationOperator(), this.getCombinationOperators(), "combinationOperator", null, 0, 1, SearchForm.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(numericalFieldEClass, NumericalField.class, "NumericalField", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);

		initEClass(searchOperatorConfigurationEClass, SearchOperatorConfiguration.class, "SearchOperatorConfiguration", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEAttribute(getSearchOperatorConfiguration_DefaultOperator(), ecorePackage.getEString(), "defaultOperator", null, 0, 1, SearchOperatorConfiguration.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getSearchOperatorConfiguration_ProposedOperators(), ecorePackage.getEString(), "proposedOperators", null, 0, -1, SearchOperatorConfiguration.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(staticTextEClass, StaticText.class, "StaticText", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEAttribute(getStaticText_Value(), ecorePackage.getEString(), "value", null, 0, 1, StaticText.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		// Initialize enums and add enum literals
		initEEnum(formGroupPresentationTypeEEnum, FormGroupPresentationType.class, "FormGroupPresentationType");
		addEEnumLiteral(formGroupPresentationTypeEEnum, FormGroupPresentationType.AUTO);
		addEEnumLiteral(formGroupPresentationTypeEEnum, FormGroupPresentationType.HORIZONTAL);
		addEEnumLiteral(formGroupPresentationTypeEEnum, FormGroupPresentationType.VERTICAL);
		addEEnumLiteral(formGroupPresentationTypeEEnum, FormGroupPresentationType.TABBED);
		addEEnumLiteral(formGroupPresentationTypeEEnum, FormGroupPresentationType.BORDERLESS);

		initEEnum(textWidgetTypeEEnum, TextWidgetType.class, "TextWidgetType");
		addEEnumLiteral(textWidgetTypeEEnum, TextWidgetType.TEXT_AREA);
		addEEnumLiteral(textWidgetTypeEEnum, TextWidgetType.RICH_TEXT_EDITOR);

		initEEnum(choiceWidgetTypeEEnum, ChoiceWidgetType.class, "ChoiceWidgetType");
		addEEnumLiteral(choiceWidgetTypeEEnum, ChoiceWidgetType.SHOW_ONE);
		addEEnumLiteral(choiceWidgetTypeEEnum, ChoiceWidgetType.LIST_ALL);
		addEEnumLiteral(choiceWidgetTypeEEnum, ChoiceWidgetType.INLINE);

		initEEnum(modelChoiceWidgetTypeEEnum, ModelChoiceWidgetType.class, "ModelChoiceWidgetType");
		addEEnumLiteral(modelChoiceWidgetTypeEEnum, ModelChoiceWidgetType.SELECT);
		addEEnumLiteral(modelChoiceWidgetTypeEEnum, ModelChoiceWidgetType.INLINE);

		initEEnum(fieldSearchOperatorsEEnum, FieldSearchOperators.class, "FieldSearchOperators");
		addEEnumLiteral(fieldSearchOperatorsEEnum, FieldSearchOperators.EQUALS);
		addEEnumLiteral(fieldSearchOperatorsEEnum, FieldSearchOperators.EMPTY);

		initEEnum(charFieldSearchOperatorsEEnum, CharFieldSearchOperators.class, "CharFieldSearchOperators");
		addEEnumLiteral(charFieldSearchOperatorsEEnum, CharFieldSearchOperators.CONTAINS);
		addEEnumLiteral(charFieldSearchOperatorsEEnum, CharFieldSearchOperators.ICONTAINS);
		addEEnumLiteral(charFieldSearchOperatorsEEnum, CharFieldSearchOperators.STARTS_WITH);
		addEEnumLiteral(charFieldSearchOperatorsEEnum, CharFieldSearchOperators.ISTARTS_WITH);
		addEEnumLiteral(charFieldSearchOperatorsEEnum, CharFieldSearchOperators.ENDS_WITH);
		addEEnumLiteral(charFieldSearchOperatorsEEnum, CharFieldSearchOperators.IENDS_WITH);

		initEEnum(numericalFieldSearchOperatorsEEnum, NumericalFieldSearchOperators.class, "NumericalFieldSearchOperators");
		addEEnumLiteral(numericalFieldSearchOperatorsEEnum, NumericalFieldSearchOperators.BETWEEN);
		addEEnumLiteral(numericalFieldSearchOperatorsEEnum, NumericalFieldSearchOperators.BELOW);
		addEEnumLiteral(numericalFieldSearchOperatorsEEnum, NumericalFieldSearchOperators.ABOVE);

		initEEnum(choiceFieldSearchOperatorsEEnum, ChoiceFieldSearchOperators.class, "ChoiceFieldSearchOperators");
		addEEnumLiteral(choiceFieldSearchOperatorsEEnum, ChoiceFieldSearchOperators.ALL);
		addEEnumLiteral(choiceFieldSearchOperatorsEEnum, ChoiceFieldSearchOperators.ONE_OF);
		addEEnumLiteral(choiceFieldSearchOperatorsEEnum, ChoiceFieldSearchOperators.NONE);
		addEEnumLiteral(choiceFieldSearchOperatorsEEnum, ChoiceFieldSearchOperators.ALL_BUT);

		initEEnum(fileFieldSearchOperatorsEEnum, FileFieldSearchOperators.class, "FileFieldSearchOperators");
		addEEnumLiteral(fileFieldSearchOperatorsEEnum, FileFieldSearchOperators.FILE_TYPE);
		addEEnumLiteral(fileFieldSearchOperatorsEEnum, FileFieldSearchOperators.CONTENTS);

		initEEnum(combinationOperatorsEEnum, CombinationOperators.class, "CombinationOperators");
		addEEnumLiteral(combinationOperatorsEEnum, CombinationOperators.AND);
		addEEnumLiteral(combinationOperatorsEEnum, CombinationOperators.OR);

		// Create resource
		createResource(eNS_URI);

		// Create annotations
		// http://www.eclipse.org/emf/2002/Ecore
		createEcoreAnnotations();
		// http://www.bluexml.com/OCL
		createOCLAnnotations();
		// http:///org/eclipse/emf/ecore/util/ExtendedMetaData
		createExtendedMetaDataAnnotations();
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
		  (formElementEClass, 
		   source, 
		   new String[] {
			 "constraints", "noSpecialCharacters"
		   });																	
		addAnnotation
		  (fieldEClass, 
		   source, 
		   new String[] {
			 "warning", "mandatoryHiddenAndNoDefaultValue",
			 "constraints", "mandatoryHiddenAndNoDefaultValue"
		   });									
		addAnnotation
		  (formContainerEClass, 
		   source, 
		   new String[] {
			 "constraints", "validName TwoFormsWithSameName"
		   });								
		addAnnotation
		  (formWorkflowEClass, 
		   source, 
		   new String[] {
			 "constraints", "ClassMustMatchWithProcessContentType"
		   });					
		addAnnotation
		  (charFieldEClass, 
		   source, 
		   new String[] {
			 "constraints", "MinSuperiorToMax"
		   });																																																	
		addAnnotation
		  (classReferenceEClass, 
		   source, 
		   new String[] {
			 "constraints", "mustReferenceClass"
		   });			
		addAnnotation
		  (virtualFieldEClass, 
		   source, 
		   new String[] {
			 "constraints", "NoLinkForVirtualField"
		   });																		
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
		  (formElementEClass, 
		   source, 
		   new String[] {
			 "noSpecialCharacters", "self.id.regexMatch(\'[\\w]*\') = true"
		   });									
		addAnnotation
		  (formGroupEClass.getEOperations().get(0), 
		   source, 
		   new String[] {
			 "body", "self.children->select(oclIsKindOf(Field)).oclAsType(Field)->union(self.children->select(oclIsKindOf(FormGroup)).oclAsType(FormGroup).getFields().oclAsType(Field)).oclAsType(Field)"
		   });							
		addAnnotation
		  (fieldEClass, 
		   source, 
		   new String[] {
			 "mandatoryHiddenAndNoDefaultValue", "self.initial.oclIsUndefined() or self.initial = \'\' implies self.mandatory = false or self.hidden = false"
		   });			
		addAnnotation
		  (fieldEClass.getEOperations().get(0), 
		   source, 
		   new String[] {
			 "body", "if self.label.oclIsUndefined() or self.label.size() = 0 then\r self.id \relse\r self.label \rendif"
		   });									
		addAnnotation
		  (formContainerEClass, 
		   source, 
		   new String[] {
			 "TwoFormsWithSameName", "FormContainer.allInstances()->select(a | a.id = self.id and a <> self)->size() = 0"
		   });			
		addAnnotation
		  (formContainerEClass.getEOperations().get(0), 
		   source, 
		   new String[] {
			 "body", "if self.label.oclIsUndefined() or self.label.size() = 0 then\r self.name \relse\r self.label \rendif"
		   });				
		addAnnotation
		  (formWorkflowEClass, 
		   source, 
		   new String[] {
			 "ClassMustMatchWithProcessContentType", "(not(self.getContainer().oclIsUndefined()) and not(self.getContainer().oclAsType(WorkflowFormCollection).linked_process.contentType.oclIsUndefined()) and (not self.DataForm.oclIsUndefined())) implies (self.getContainer().oclAsType(WorkflowFormCollection).linked_process.contentType = self.DataForm.real_class)"
		   });							
		addAnnotation
		  (charFieldEClass, 
		   source, 
		   new String[] {
			 "MinSuperiorToMax", "self.min_length <= self.max_length"
		   });																																															
		addAnnotation
		  (classReferenceEClass, 
		   source, 
		   new String[] {
			 "mustReferenceClass", "not self.real_class.oclIsUndefined()"
		   });					
		addAnnotation
		  (virtualFieldEClass, 
		   source, 
		   new String[] {
			 "NoLinkForVirtualField", "not self.link.oclIsUndefined()"
		   });																	
	}

	/**
	 * Initializes the annotations for <b>http:///org/eclipse/emf/ecore/util/ExtendedMetaData</b>.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected void createExtendedMetaDataAnnotations() {
		String source = "http:///org/eclipse/emf/ecore/util/ExtendedMetaData";														
		addAnnotation
		  (getFormGroup_Presentation(), 
		   source, 
		   new String[] {
			 "name", "presentation"
		   });																																																																																											
	}

	public FormFactory getFormsFactory() {
		// TODO Auto-generated method stub
		return null;
	}

} //formPackageImpl
