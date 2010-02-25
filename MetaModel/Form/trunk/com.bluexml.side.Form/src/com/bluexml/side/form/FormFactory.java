/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package com.bluexml.side.form;

import org.eclipse.emf.ecore.EFactory;


/**
 * <!-- begin-user-doc -->
 * The <b>Factory</b> for the model.
 * It provides a create method for each non-abstract class of the model.
 * <!-- end-user-doc -->
 * @see com.bluexml.side.form.FormPackage
 * @generated
 */
public interface FormFactory extends EFactory {
	/**
	 * The singleton instance of the factory.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	FormFactory eINSTANCE = com.bluexml.side.form.impl.FormFactoryImpl.init();

	/**
	 * Returns a new object of class '<em>Group</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Group</em>'.
	 * @generated
	 */
	FormGroup createFormGroup();

	/**
	 * Returns a new object of class '<em>Boolean Field</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Boolean Field</em>'.
	 * @generated
	 */
	BooleanField createBooleanField();

	/**
	 * Returns a new object of class '<em>Char Field</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Char Field</em>'.
	 * @generated
	 */
	CharField createCharField();

	/**
	 * Returns a new object of class '<em>Date Field</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Date Field</em>'.
	 * @generated
	 */
	DateField createDateField();

	/**
	 * Returns a new object of class '<em>Date Time Field</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Date Time Field</em>'.
	 * @generated
	 */
	DateTimeField createDateTimeField();

	/**
	 * Returns a new object of class '<em>Decimal Field</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Decimal Field</em>'.
	 * @generated
	 */
	DecimalField createDecimalField();

	/**
	 * Returns a new object of class '<em>Float Field</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Float Field</em>'.
	 * @generated
	 */
	FloatField createFloatField();

	/**
	 * Returns a new object of class '<em>Integer Field</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Integer Field</em>'.
	 * @generated
	 */
	IntegerField createIntegerField();

	/**
	 * Returns a new object of class '<em>Model Choice Field</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Model Choice Field</em>'.
	 * @generated
	 */
	ModelChoiceField createModelChoiceField();

	/**
	 * Returns a new object of class '<em>Email Field</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Email Field</em>'.
	 * @generated
	 */
	EmailField createEmailField();

	/**
	 * Returns a new object of class '<em>File Field</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>File Field</em>'.
	 * @generated
	 */
	FileField createFileField();

	/**
	 * Returns a new object of class '<em>Image Field</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Image Field</em>'.
	 * @generated
	 */
	ImageField createImageField();

	/**
	 * Returns a new object of class '<em>Time Field</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Time Field</em>'.
	 * @generated
	 */
	TimeField createTimeField();

	/**
	 * Returns a new object of class '<em>URL Field</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>URL Field</em>'.
	 * @generated
	 */
	URLField createURLField();

	/**
	 * Returns a new object of class '<em>Phone Number Field</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Phone Number Field</em>'.
	 * @generated
	 */
	PhoneNumberField createPhoneNumberField();

	/**
	 * Returns a new object of class '<em>Aspect</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Aspect</em>'.
	 * @generated
	 */
	FormAspect createFormAspect();

	/**
	 * Returns a new object of class '<em>Class</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Class</em>'.
	 * @generated
	 */
	FormClass createFormClass();

	/**
	 * Returns a new object of class '<em>Reference</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Reference</em>'.
	 * @generated
	 */
	Reference createReference();

	/**
	 * Returns a new object of class '<em>Choice Field</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Choice Field</em>'.
	 * @generated
	 */
	ChoiceField createChoiceField();

	/**
	 * Returns a new object of class '<em>Regex Field</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Regex Field</em>'.
	 * @generated
	 */
	RegexField createRegexField();

	/**
	 * Returns a new object of class '<em>Password Field</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Password Field</em>'.
	 * @generated
	 */
	PasswordField createPasswordField();

	/**
	 * Returns a new object of class '<em>Virtual Field</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Virtual Field</em>'.
	 * @generated
	 */
	VirtualField createVirtualField();

	/**
	 * Returns a new object of class '<em>Action Field</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Action Field</em>'.
	 * @generated
	 */
	ActionField createActionField();

	/**
	 * Returns a new object of class '<em>Text Field</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Text Field</em>'.
	 * @generated
	 */
	TextField createTextField();

	/**
	 * Returns a new object of class '<em>Search</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Search</em>'.
	 * @generated
	 */
	FormSearch createFormSearch();

	/**
	 * Returns a new object of class '<em>Numerical Field</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Numerical Field</em>'.
	 * @generated
	 */
	NumericalField createNumericalField();

	/**
	 * Returns a new object of class '<em>Static Text</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Static Text</em>'.
	 * @generated
	 */
	StaticText createStaticText();

	/**
	 * Returns a new object of class '<em>Search Form Collection</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Search Form Collection</em>'.
	 * @generated
	 */
	SearchFormCollection createSearchFormCollection();

	/**
	 * Returns a new object of class '<em>Search Field</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Search Field</em>'.
	 * @generated
	 */
	SearchField createSearchField();

	/**
	 * Returns a new object of class '<em>Numerical Search Field</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Numerical Search Field</em>'.
	 * @generated
	 */
	NumericalSearchField createNumericalSearchField();

	/**
	 * Returns a new object of class '<em>Char Search Field</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Char Search Field</em>'.
	 * @generated
	 */
	CharSearchField createCharSearchField();

	/**
	 * Returns a new object of class '<em>Date Search Field</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Date Search Field</em>'.
	 * @generated
	 */
	DateSearchField createDateSearchField();

	/**
	 * Returns a new object of class '<em>Choice Search Field</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Choice Search Field</em>'.
	 * @generated
	 */
	ChoiceSearchField createChoiceSearchField();

	/**
	 * Returns a new object of class '<em>File Search Field</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>File Search Field</em>'.
	 * @generated
	 */
	FileSearchField createFileSearchField();

	/**
	 * Returns a new object of class '<em>Boolean Search Field</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Boolean Search Field</em>'.
	 * @generated
	 */
	BooleanSearchField createBooleanSearchField();

	/**
	 * Returns a new object of class '<em>Workflow Form Collection</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Workflow Form Collection</em>'.
	 * @generated
	 */
	WorkflowFormCollection createWorkflowFormCollection();

	/**
	 * Returns a new object of class '<em>Class Form Collection</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Class Form Collection</em>'.
	 * @generated
	 */
	ClassFormCollection createClassFormCollection();

	/**
	 * Returns a new object of class '<em>Workflow</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Workflow</em>'.
	 * @generated
	 */
	FormWorkflow createFormWorkflow();

	/**
	 * Returns the package supported by this factory.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the package supported by this factory.
	 * @generated
	 */
	FormPackage getFormPackage();

} //KerblueFormsFactory
