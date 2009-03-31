/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package com.bluexml.side.KerblueForms.util;

import com.bluexml.side.KerblueForms.*;

import org.eclipse.emf.common.notify.Adapter;
import org.eclipse.emf.common.notify.Notifier;

import org.eclipse.emf.common.notify.impl.AdapterFactoryImpl;

import org.eclipse.emf.ecore.EObject;

/**
 * <!-- begin-user-doc -->
 * The <b>Adapter Factory</b> for the model.
 * It provides an adapter <code>createXXX</code> method for each class of the model.
 * <!-- end-user-doc -->
 * @see com.bluexml.side.KerblueForms.formPackage
 * @generated
 */
public class formAdapterFactory extends AdapterFactoryImpl {
	/**
	 * The cached model package.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected static formPackage modelPackage;

	/**
	 * Creates an instance of the adapter factory.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public formAdapterFactory() {
		if (modelPackage == null) {
			modelPackage = formPackage.eINSTANCE;
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
	protected formSwitch<Adapter> modelSwitch =
		new formSwitch<Adapter>() {
			@Override
			public Adapter caseForm(Form object) {
				return createFormAdapter();
			}
			@Override
			public Adapter caseFormElement(FormElement object) {
				return createFormElementAdapter();
			}
			@Override
			public Adapter caseFormGroup(FormGroup object) {
				return createFormGroupAdapter();
			}
			@Override
			public Adapter caseField(Field object) {
				return createFieldAdapter();
			}
			@Override
			public Adapter caseBooleanField(BooleanField object) {
				return createBooleanFieldAdapter();
			}
			@Override
			public Adapter caseCharField(CharField object) {
				return createCharFieldAdapter();
			}
			@Override
			public Adapter caseDateField(DateField object) {
				return createDateFieldAdapter();
			}
			@Override
			public Adapter caseDateTimeField(DateTimeField object) {
				return createDateTimeFieldAdapter();
			}
			@Override
			public Adapter caseDecimalField(DecimalField object) {
				return createDecimalFieldAdapter();
			}
			@Override
			public Adapter caseFloatField(FloatField object) {
				return createFloatFieldAdapter();
			}
			@Override
			public Adapter caseIntegerField(IntegerField object) {
				return createIntegerFieldAdapter();
			}
			@Override
			public Adapter caseModelChoiceField(ModelChoiceField object) {
				return createModelChoiceFieldAdapter();
			}
			@Override
			public Adapter caseEmailField(EmailField object) {
				return createEmailFieldAdapter();
			}
			@Override
			public Adapter caseFileField(FileField object) {
				return createFileFieldAdapter();
			}
			@Override
			public Adapter caseImageField(ImageField object) {
				return createImageFieldAdapter();
			}
			@Override
			public Adapter caseTimeField(TimeField object) {
				return createTimeFieldAdapter();
			}
			@Override
			public Adapter caseURLField(URLField object) {
				return createURLFieldAdapter();
			}
			@Override
			public Adapter casePhoneNumberField(PhoneNumberField object) {
				return createPhoneNumberFieldAdapter();
			}
			@Override
			public Adapter caseFormAspect(FormAspect object) {
				return createFormAspectAdapter();
			}
			@Override
			public Adapter caseFormClass(FormClass object) {
				return createFormClassAdapter();
			}
			@Override
			public Adapter caseReference(Reference object) {
				return createReferenceAdapter();
			}
			@Override
			public Adapter caseFormCollection(FormCollection object) {
				return createFormCollectionAdapter();
			}
			@Override
			public Adapter caseChoiceField(ChoiceField object) {
				return createChoiceFieldAdapter();
			}
			@Override
			public Adapter caseRegexField(RegexField object) {
				return createRegexFieldAdapter();
			}
			@Override
			public Adapter caseClassReference(ClassReference object) {
				return createClassReferenceAdapter();
			}
			@Override
			public Adapter casePasswordField(PasswordField object) {
				return createPasswordFieldAdapter();
			}
			@Override
			public Adapter caseVirtualField(VirtualField object) {
				return createVirtualFieldAdapter();
			}
			@Override
			public Adapter caseActionField(ActionField object) {
				return createActionFieldAdapter();
			}
			@Override
			public Adapter caseTextField(TextField object) {
				return createTextFieldAdapter();
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
	 * Creates a new adapter for an object of class '{@link com.bluexml.side.KerblueForms.Form <em>Form</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see com.bluexml.side.KerblueForms.Form
	 * @generated
	 */
	public Adapter createFormAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link com.bluexml.side.KerblueForms.FormElement <em>Form Element</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see com.bluexml.side.KerblueForms.FormElement
	 * @generated
	 */
	public Adapter createFormElementAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link com.bluexml.side.KerblueForms.FormGroup <em>Form Group</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see com.bluexml.side.KerblueForms.FormGroup
	 * @generated
	 */
	public Adapter createFormGroupAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link com.bluexml.side.KerblueForms.Field <em>Field</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see com.bluexml.side.KerblueForms.Field
	 * @generated
	 */
	public Adapter createFieldAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link com.bluexml.side.KerblueForms.BooleanField <em>Boolean Field</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see com.bluexml.side.KerblueForms.BooleanField
	 * @generated
	 */
	public Adapter createBooleanFieldAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link com.bluexml.side.KerblueForms.CharField <em>Char Field</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see com.bluexml.side.KerblueForms.CharField
	 * @generated
	 */
	public Adapter createCharFieldAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link com.bluexml.side.KerblueForms.DateField <em>Date Field</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see com.bluexml.side.KerblueForms.DateField
	 * @generated
	 */
	public Adapter createDateFieldAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link com.bluexml.side.KerblueForms.DateTimeField <em>Date Time Field</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see com.bluexml.side.KerblueForms.DateTimeField
	 * @generated
	 */
	public Adapter createDateTimeFieldAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link com.bluexml.side.KerblueForms.DecimalField <em>Decimal Field</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see com.bluexml.side.KerblueForms.DecimalField
	 * @generated
	 */
	public Adapter createDecimalFieldAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link com.bluexml.side.KerblueForms.FloatField <em>Float Field</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see com.bluexml.side.KerblueForms.FloatField
	 * @generated
	 */
	public Adapter createFloatFieldAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link com.bluexml.side.KerblueForms.IntegerField <em>Integer Field</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see com.bluexml.side.KerblueForms.IntegerField
	 * @generated
	 */
	public Adapter createIntegerFieldAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link com.bluexml.side.KerblueForms.ModelChoiceField <em>Model Choice Field</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see com.bluexml.side.KerblueForms.ModelChoiceField
	 * @generated
	 */
	public Adapter createModelChoiceFieldAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link com.bluexml.side.KerblueForms.EmailField <em>Email Field</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see com.bluexml.side.KerblueForms.EmailField
	 * @generated
	 */
	public Adapter createEmailFieldAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link com.bluexml.side.KerblueForms.FileField <em>File Field</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see com.bluexml.side.KerblueForms.FileField
	 * @generated
	 */
	public Adapter createFileFieldAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link com.bluexml.side.KerblueForms.ImageField <em>Image Field</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see com.bluexml.side.KerblueForms.ImageField
	 * @generated
	 */
	public Adapter createImageFieldAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link com.bluexml.side.KerblueForms.TimeField <em>Time Field</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see com.bluexml.side.KerblueForms.TimeField
	 * @generated
	 */
	public Adapter createTimeFieldAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link com.bluexml.side.KerblueForms.URLField <em>URL Field</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see com.bluexml.side.KerblueForms.URLField
	 * @generated
	 */
	public Adapter createURLFieldAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link com.bluexml.side.KerblueForms.PhoneNumberField <em>Phone Number Field</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see com.bluexml.side.KerblueForms.PhoneNumberField
	 * @generated
	 */
	public Adapter createPhoneNumberFieldAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link com.bluexml.side.KerblueForms.FormAspect <em>Form Aspect</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see com.bluexml.side.KerblueForms.FormAspect
	 * @generated
	 */
	public Adapter createFormAspectAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link com.bluexml.side.KerblueForms.FormClass <em>Form Class</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see com.bluexml.side.KerblueForms.FormClass
	 * @generated
	 */
	public Adapter createFormClassAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link com.bluexml.side.KerblueForms.Reference <em>Reference</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see com.bluexml.side.KerblueForms.Reference
	 * @generated
	 */
	public Adapter createReferenceAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link com.bluexml.side.KerblueForms.FormCollection <em>Form Collection</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see com.bluexml.side.KerblueForms.FormCollection
	 * @generated
	 */
	public Adapter createFormCollectionAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link com.bluexml.side.KerblueForms.ChoiceField <em>Choice Field</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see com.bluexml.side.KerblueForms.ChoiceField
	 * @generated
	 */
	public Adapter createChoiceFieldAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link com.bluexml.side.KerblueForms.RegexField <em>Regex Field</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see com.bluexml.side.KerblueForms.RegexField
	 * @generated
	 */
	public Adapter createRegexFieldAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link com.bluexml.side.KerblueForms.ClassReference <em>Class Reference</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see com.bluexml.side.KerblueForms.ClassReference
	 * @generated
	 */
	public Adapter createClassReferenceAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link com.bluexml.side.KerblueForms.PasswordField <em>Password Field</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see com.bluexml.side.KerblueForms.PasswordField
	 * @generated
	 */
	public Adapter createPasswordFieldAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link com.bluexml.side.KerblueForms.VirtualField <em>Virtual Field</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see com.bluexml.side.KerblueForms.VirtualField
	 * @generated
	 */
	public Adapter createVirtualFieldAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link com.bluexml.side.KerblueForms.ActionField <em>Action Field</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see com.bluexml.side.KerblueForms.ActionField
	 * @generated
	 */
	public Adapter createActionFieldAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link com.bluexml.side.KerblueForms.TextField <em>Text Field</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see com.bluexml.side.KerblueForms.TextField
	 * @generated
	 */
	public Adapter createTextFieldAdapter() {
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

} //formAdapterFactory
