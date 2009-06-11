/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package com.bluexml.side.view;

import org.eclipse.emf.ecore.EFactory;

/**
 * <!-- begin-user-doc -->
 * The <b>Factory</b> for the model.
 * It provides a create method for each non-abstract class of the model.
 * <!-- end-user-doc -->
 * @see com.bluexml.side.view.ViewPackage
 * @generated
 */
public interface ViewFactory extends EFactory {
	/**
	 * The singleton instance of the factory.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	ViewFactory eINSTANCE = com.bluexml.side.view.impl.ViewFactoryImpl.init();

	/**
	 * Returns a new object of class '<em>Col</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Col</em>'.
	 * @generated
	 */
	Col createCol();

	/**
	 * Returns a new object of class '<em>Paging</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Paging</em>'.
	 * @generated
	 */
	Paging createPaging();

	/**
	 * Returns a new object of class '<em>Sorting</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Sorting</em>'.
	 * @generated
	 */
	Sorting createSorting();

	/**
	 * Returns a new object of class '<em>Field Group</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Field Group</em>'.
	 * @generated
	 */
	FieldGroup createFieldGroup();

	/**
	 * Returns a new object of class '<em>Text Field</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Text Field</em>'.
	 * @generated
	 */
	TextField createTextField();

	/**
	 * Returns a new object of class '<em>Password Field</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Password Field</em>'.
	 * @generated
	 */
	PasswordField createPasswordField();

	/**
	 * Returns a new object of class '<em>Boolean Field</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Boolean Field</em>'.
	 * @generated
	 */
	BooleanField createBooleanField();

	/**
	 * Returns a new object of class '<em>Float Field</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Float Field</em>'.
	 * @generated
	 */
	FloatField createFloatField();

	/**
	 * Returns a new object of class '<em>Action Field</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Action Field</em>'.
	 * @generated
	 */
	ActionField createActionField();

	/**
	 * Returns a new object of class '<em>Date Field</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Date Field</em>'.
	 * @generated
	 */
	DateField createDateField();

	/**
	 * Returns a new object of class '<em>Time Field</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Time Field</em>'.
	 * @generated
	 */
	TimeField createTimeField();

	/**
	 * Returns a new object of class '<em>Date Time Field</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Date Time Field</em>'.
	 * @generated
	 */
	DateTimeField createDateTimeField();

	/**
	 * Returns a new object of class '<em>Phone Number Field</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Phone Number Field</em>'.
	 * @generated
	 */
	PhoneNumberField createPhoneNumberField();

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
	 * Returns a new object of class '<em>Facet Map</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Facet Map</em>'.
	 * @generated
	 */
	FacetMap createFacetMap();

	/**
	 * Returns a new object of class '<em>Data List</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Data List</em>'.
	 * @generated
	 */
	DataList createDataList();

	/**
	 * Returns a new object of class '<em>Tree</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Tree</em>'.
	 * @generated
	 */
	Tree createTree();

	/**
	 * Returns a new object of class '<em>Data Table</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Data Table</em>'.
	 * @generated
	 */
	DataTable createDataTable();

	/**
	 * Returns a new object of class '<em>Styling</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Styling</em>'.
	 * @generated
	 */
	Styling createStyling();

	/**
	 * Returns a new object of class '<em>Select Field</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Select Field</em>'.
	 * @generated
	 */
	SelectField createSelectField();

	/**
	 * Returns a new object of class '<em>Collection</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Collection</em>'.
	 * @generated
	 */
	ViewCollection createViewCollection();

	/**
	 * Returns a new object of class '<em>Html Field</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Html Field</em>'.
	 * @generated
	 */
	HtmlField createHtmlField();

	/**
	 * Returns a new object of class '<em>Filtering</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Filtering</em>'.
	 * @generated
	 */
	Filtering createFiltering();

	/**
	 * Returns a new object of class '<em>Integer Field</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Integer Field</em>'.
	 * @generated
	 */
	IntegerField createIntegerField();

	/**
	 * Returns the package supported by this factory.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the package supported by this factory.
	 * @generated
	 */
	ViewPackage getViewPackage();

} //ViewFactory
