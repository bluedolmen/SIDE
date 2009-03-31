/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package com.bluexml.side.form;

import org.eclipse.emf.ecore.EAttribute;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EEnum;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.EReference;

import com.bluexml.side.form.impl.formPackageImpl;

/**
 * <!-- begin-user-doc -->
 * The <b>Package</b> for the model.
 * It contains accessors for the meta objects to represent
 * <ul>
 *   <li>each class,</li>
 *   <li>each feature of each class,</li>
 *   <li>each enum,</li>
 *   <li>and each data type</li>
 * </ul>
 * <!-- end-user-doc -->
 * @see KerblueForms.KerblueFormsFactory
 * @model kind="package"
 * @generated
 */
public interface formPackage extends EPackage {
	/**
	 * The package name.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	String eNAME = "KerblueForms";

	/**
	 * The package namespace URI.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	String eNS_URI = "http://www.bluexml.com/kerblue/forms";

	/**
	 * The package namespace name.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	String eNS_PREFIX = "kerblue.com.bluexml.forms";

	/**
	 * The singleton instance of the package.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	formPackage eINSTANCE = formPackageImpl.init();

	/**
	 * The meta object id for the '{@link KerblueForms.impl.FormImpl <em>Form</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see KerblueForms.impl.FormImpl
	 * @see KerblueForms.impl.KerblueFormsPackageImpl#getForm()
	 * @generated
	 */
	int FORM = 0;

	/**
	 * The feature id for the '<em><b>Root</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int FORM__ROOT = 0;

	/**
	 * The feature id for the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int FORM__NAME = 1;

	/**
	 * The number of structural features of the '<em>Form</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int FORM_FEATURE_COUNT = 2;

	/**
	 * The meta object id for the '{@link KerblueForms.impl.FormElementImpl <em>Form Element</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see KerblueForms.impl.FormElementImpl
	 * @see KerblueForms.impl.KerblueFormsPackageImpl#getFormElement()
	 * @generated
	 */
	int FORM_ELEMENT = 1;

	/**
	 * The feature id for the '<em><b>Label</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int FORM_ELEMENT__LABEL = 0;

	/**
	 * The feature id for the '<em><b>Id</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int FORM_ELEMENT__ID = 1;

	/**
	 * The feature id for the '<em><b>Help text</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int FORM_ELEMENT__HELP_TEXT = 2;

	/**
	 * The feature id for the '<em><b>Ref</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int FORM_ELEMENT__REF = 3;

	/**
	 * The number of structural features of the '<em>Form Element</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int FORM_ELEMENT_FEATURE_COUNT = 4;

	/**
	 * The meta object id for the '{@link KerblueForms.impl.FormGroupImpl <em>Form Group</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see KerblueForms.impl.FormGroupImpl
	 * @see KerblueForms.impl.KerblueFormsPackageImpl#getFormGroup()
	 * @generated
	 */
	int FORM_GROUP = 2;

	/**
	 * The feature id for the '<em><b>Label</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int FORM_GROUP__LABEL = FORM_ELEMENT__LABEL;

	/**
	 * The feature id for the '<em><b>Id</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int FORM_GROUP__ID = FORM_ELEMENT__ID;

	/**
	 * The feature id for the '<em><b>Help text</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int FORM_GROUP__HELP_TEXT = FORM_ELEMENT__HELP_TEXT;

	/**
	 * The feature id for the '<em><b>Ref</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int FORM_GROUP__REF = FORM_ELEMENT__REF;

	/**
	 * The feature id for the '<em><b>Children</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int FORM_GROUP__CHILDREN = FORM_ELEMENT_FEATURE_COUNT + 0;

	/**
	 * The feature id for the '<em><b>Presentation</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int FORM_GROUP__PRESENTATION = FORM_ELEMENT_FEATURE_COUNT + 1;

	/**
	 * The feature id for the '<em><b>Disabled</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int FORM_GROUP__DISABLED = FORM_ELEMENT_FEATURE_COUNT + 2;

	/**
	 * The number of structural features of the '<em>Form Group</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int FORM_GROUP_FEATURE_COUNT = FORM_ELEMENT_FEATURE_COUNT + 3;

	/**
	 * The meta object id for the '{@link KerblueForms.impl.FieldImpl <em>Field</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see KerblueForms.impl.FieldImpl
	 * @see KerblueForms.impl.KerblueFormsPackageImpl#getField()
	 * @generated
	 */
	int FIELD = 3;

	/**
	 * The feature id for the '<em><b>Label</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int FIELD__LABEL = FORM_ELEMENT__LABEL;

	/**
	 * The feature id for the '<em><b>Id</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int FIELD__ID = FORM_ELEMENT__ID;

	/**
	 * The feature id for the '<em><b>Help text</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int FIELD__HELP_TEXT = FORM_ELEMENT__HELP_TEXT;

	/**
	 * The feature id for the '<em><b>Ref</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int FIELD__REF = FORM_ELEMENT__REF;

	/**
	 * The feature id for the '<em><b>Mandatory</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int FIELD__MANDATORY = FORM_ELEMENT_FEATURE_COUNT + 0;

	/**
	 * The feature id for the '<em><b>Hidden</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int FIELD__HIDDEN = FORM_ELEMENT_FEATURE_COUNT + 1;

	/**
	 * The feature id for the '<em><b>Error messages</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int FIELD__ERROR_MESSAGES = FORM_ELEMENT_FEATURE_COUNT + 2;

	/**
	 * The feature id for the '<em><b>Initial</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int FIELD__INITIAL = FORM_ELEMENT_FEATURE_COUNT + 3;

	/**
	 * The feature id for the '<em><b>Disabled</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int FIELD__DISABLED = FORM_ELEMENT_FEATURE_COUNT + 4;

	/**
	 * The feature id for the '<em><b>Field Size</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int FIELD__FIELD_SIZE = FORM_ELEMENT_FEATURE_COUNT + 5;

	/**
	 * The number of structural features of the '<em>Field</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int FIELD_FEATURE_COUNT = FORM_ELEMENT_FEATURE_COUNT + 6;

	/**
	 * The meta object id for the '{@link KerblueForms.impl.BooleanFieldImpl <em>Boolean Field</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see KerblueForms.impl.BooleanFieldImpl
	 * @see KerblueForms.impl.KerblueFormsPackageImpl#getBooleanField()
	 * @generated
	 */
	int BOOLEAN_FIELD = 4;

	/**
	 * The feature id for the '<em><b>Label</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int BOOLEAN_FIELD__LABEL = FIELD__LABEL;

	/**
	 * The feature id for the '<em><b>Id</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int BOOLEAN_FIELD__ID = FIELD__ID;

	/**
	 * The feature id for the '<em><b>Help text</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int BOOLEAN_FIELD__HELP_TEXT = FIELD__HELP_TEXT;

	/**
	 * The feature id for the '<em><b>Ref</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int BOOLEAN_FIELD__REF = FIELD__REF;

	/**
	 * The feature id for the '<em><b>Mandatory</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int BOOLEAN_FIELD__MANDATORY = FIELD__MANDATORY;

	/**
	 * The feature id for the '<em><b>Hidden</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int BOOLEAN_FIELD__HIDDEN = FIELD__HIDDEN;

	/**
	 * The feature id for the '<em><b>Error messages</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int BOOLEAN_FIELD__ERROR_MESSAGES = FIELD__ERROR_MESSAGES;

	/**
	 * The feature id for the '<em><b>Initial</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int BOOLEAN_FIELD__INITIAL = FIELD__INITIAL;

	/**
	 * The feature id for the '<em><b>Disabled</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int BOOLEAN_FIELD__DISABLED = FIELD__DISABLED;

	/**
	 * The feature id for the '<em><b>Field Size</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int BOOLEAN_FIELD__FIELD_SIZE = FIELD__FIELD_SIZE;

	/**
	 * The number of structural features of the '<em>Boolean Field</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int BOOLEAN_FIELD_FEATURE_COUNT = FIELD_FEATURE_COUNT + 0;

	/**
	 * The meta object id for the '{@link KerblueForms.impl.CharFieldImpl <em>Char Field</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see KerblueForms.impl.CharFieldImpl
	 * @see KerblueForms.impl.KerblueFormsPackageImpl#getCharField()
	 * @generated
	 */
	int CHAR_FIELD = 5;

	/**
	 * The feature id for the '<em><b>Label</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CHAR_FIELD__LABEL = FIELD__LABEL;

	/**
	 * The feature id for the '<em><b>Id</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CHAR_FIELD__ID = FIELD__ID;

	/**
	 * The feature id for the '<em><b>Help text</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CHAR_FIELD__HELP_TEXT = FIELD__HELP_TEXT;

	/**
	 * The feature id for the '<em><b>Ref</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CHAR_FIELD__REF = FIELD__REF;

	/**
	 * The feature id for the '<em><b>Mandatory</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CHAR_FIELD__MANDATORY = FIELD__MANDATORY;

	/**
	 * The feature id for the '<em><b>Hidden</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CHAR_FIELD__HIDDEN = FIELD__HIDDEN;

	/**
	 * The feature id for the '<em><b>Error messages</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CHAR_FIELD__ERROR_MESSAGES = FIELD__ERROR_MESSAGES;

	/**
	 * The feature id for the '<em><b>Initial</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CHAR_FIELD__INITIAL = FIELD__INITIAL;

	/**
	 * The feature id for the '<em><b>Disabled</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CHAR_FIELD__DISABLED = FIELD__DISABLED;

	/**
	 * The feature id for the '<em><b>Field Size</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CHAR_FIELD__FIELD_SIZE = FIELD__FIELD_SIZE;

	/**
	 * The feature id for the '<em><b>Min length</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CHAR_FIELD__MIN_LENGTH = FIELD_FEATURE_COUNT + 0;

	/**
	 * The feature id for the '<em><b>Max length</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CHAR_FIELD__MAX_LENGTH = FIELD_FEATURE_COUNT + 1;

	/**
	 * The number of structural features of the '<em>Char Field</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CHAR_FIELD_FEATURE_COUNT = FIELD_FEATURE_COUNT + 2;

	/**
	 * The meta object id for the '{@link KerblueForms.impl.DateFieldImpl <em>Date Field</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see KerblueForms.impl.DateFieldImpl
	 * @see KerblueForms.impl.KerblueFormsPackageImpl#getDateField()
	 * @generated
	 */
	int DATE_FIELD = 6;

	/**
	 * The feature id for the '<em><b>Label</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DATE_FIELD__LABEL = FIELD__LABEL;

	/**
	 * The feature id for the '<em><b>Id</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DATE_FIELD__ID = FIELD__ID;

	/**
	 * The feature id for the '<em><b>Help text</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DATE_FIELD__HELP_TEXT = FIELD__HELP_TEXT;

	/**
	 * The feature id for the '<em><b>Ref</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DATE_FIELD__REF = FIELD__REF;

	/**
	 * The feature id for the '<em><b>Mandatory</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DATE_FIELD__MANDATORY = FIELD__MANDATORY;

	/**
	 * The feature id for the '<em><b>Hidden</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DATE_FIELD__HIDDEN = FIELD__HIDDEN;

	/**
	 * The feature id for the '<em><b>Error messages</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DATE_FIELD__ERROR_MESSAGES = FIELD__ERROR_MESSAGES;

	/**
	 * The feature id for the '<em><b>Initial</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DATE_FIELD__INITIAL = FIELD__INITIAL;

	/**
	 * The feature id for the '<em><b>Disabled</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DATE_FIELD__DISABLED = FIELD__DISABLED;

	/**
	 * The feature id for the '<em><b>Field Size</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DATE_FIELD__FIELD_SIZE = FIELD__FIELD_SIZE;

	/**
	 * The feature id for the '<em><b>Input formats</b></em>' attribute list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DATE_FIELD__INPUT_FORMATS = FIELD_FEATURE_COUNT + 0;

	/**
	 * The feature id for the '<em><b>Min date</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DATE_FIELD__MIN_DATE = FIELD_FEATURE_COUNT + 1;

	/**
	 * The feature id for the '<em><b>Max date</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DATE_FIELD__MAX_DATE = FIELD_FEATURE_COUNT + 2;

	/**
	 * The number of structural features of the '<em>Date Field</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DATE_FIELD_FEATURE_COUNT = FIELD_FEATURE_COUNT + 3;

	/**
	 * The meta object id for the '{@link KerblueForms.impl.DateTimeFieldImpl <em>Date Time Field</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see KerblueForms.impl.DateTimeFieldImpl
	 * @see KerblueForms.impl.KerblueFormsPackageImpl#getDateTimeField()
	 * @generated
	 */
	int DATE_TIME_FIELD = 7;

	/**
	 * The feature id for the '<em><b>Label</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DATE_TIME_FIELD__LABEL = DATE_FIELD__LABEL;

	/**
	 * The feature id for the '<em><b>Id</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DATE_TIME_FIELD__ID = DATE_FIELD__ID;

	/**
	 * The feature id for the '<em><b>Help text</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DATE_TIME_FIELD__HELP_TEXT = DATE_FIELD__HELP_TEXT;

	/**
	 * The feature id for the '<em><b>Ref</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DATE_TIME_FIELD__REF = DATE_FIELD__REF;

	/**
	 * The feature id for the '<em><b>Mandatory</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DATE_TIME_FIELD__MANDATORY = DATE_FIELD__MANDATORY;

	/**
	 * The feature id for the '<em><b>Hidden</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DATE_TIME_FIELD__HIDDEN = DATE_FIELD__HIDDEN;

	/**
	 * The feature id for the '<em><b>Error messages</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DATE_TIME_FIELD__ERROR_MESSAGES = DATE_FIELD__ERROR_MESSAGES;

	/**
	 * The feature id for the '<em><b>Initial</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DATE_TIME_FIELD__INITIAL = DATE_FIELD__INITIAL;

	/**
	 * The feature id for the '<em><b>Disabled</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DATE_TIME_FIELD__DISABLED = DATE_FIELD__DISABLED;

	/**
	 * The feature id for the '<em><b>Field Size</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DATE_TIME_FIELD__FIELD_SIZE = DATE_FIELD__FIELD_SIZE;

	/**
	 * The feature id for the '<em><b>Input formats</b></em>' attribute list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DATE_TIME_FIELD__INPUT_FORMATS = DATE_FIELD__INPUT_FORMATS;

	/**
	 * The feature id for the '<em><b>Min date</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DATE_TIME_FIELD__MIN_DATE = DATE_FIELD__MIN_DATE;

	/**
	 * The feature id for the '<em><b>Max date</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DATE_TIME_FIELD__MAX_DATE = DATE_FIELD__MAX_DATE;

	/**
	 * The number of structural features of the '<em>Date Time Field</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DATE_TIME_FIELD_FEATURE_COUNT = DATE_FIELD_FEATURE_COUNT + 0;

	/**
	 * The meta object id for the '{@link KerblueForms.impl.DecimalFieldImpl <em>Decimal Field</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see KerblueForms.impl.DecimalFieldImpl
	 * @see KerblueForms.impl.KerblueFormsPackageImpl#getDecimalField()
	 * @generated
	 */
	int DECIMAL_FIELD = 8;

	/**
	 * The feature id for the '<em><b>Label</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DECIMAL_FIELD__LABEL = FIELD__LABEL;

	/**
	 * The feature id for the '<em><b>Id</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DECIMAL_FIELD__ID = FIELD__ID;

	/**
	 * The feature id for the '<em><b>Help text</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DECIMAL_FIELD__HELP_TEXT = FIELD__HELP_TEXT;

	/**
	 * The feature id for the '<em><b>Ref</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DECIMAL_FIELD__REF = FIELD__REF;

	/**
	 * The feature id for the '<em><b>Mandatory</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DECIMAL_FIELD__MANDATORY = FIELD__MANDATORY;

	/**
	 * The feature id for the '<em><b>Hidden</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DECIMAL_FIELD__HIDDEN = FIELD__HIDDEN;

	/**
	 * The feature id for the '<em><b>Error messages</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DECIMAL_FIELD__ERROR_MESSAGES = FIELD__ERROR_MESSAGES;

	/**
	 * The feature id for the '<em><b>Initial</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DECIMAL_FIELD__INITIAL = FIELD__INITIAL;

	/**
	 * The feature id for the '<em><b>Disabled</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DECIMAL_FIELD__DISABLED = FIELD__DISABLED;

	/**
	 * The feature id for the '<em><b>Field Size</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DECIMAL_FIELD__FIELD_SIZE = FIELD__FIELD_SIZE;

	/**
	 * The feature id for the '<em><b>Min value</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DECIMAL_FIELD__MIN_VALUE = FIELD_FEATURE_COUNT + 0;

	/**
	 * The feature id for the '<em><b>Max value</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DECIMAL_FIELD__MAX_VALUE = FIELD_FEATURE_COUNT + 1;

	/**
	 * The feature id for the '<em><b>Max digits</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DECIMAL_FIELD__MAX_DIGITS = FIELD_FEATURE_COUNT + 2;

	/**
	 * The feature id for the '<em><b>Decimal places</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DECIMAL_FIELD__DECIMAL_PLACES = FIELD_FEATURE_COUNT + 3;

	/**
	 * The number of structural features of the '<em>Decimal Field</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DECIMAL_FIELD_FEATURE_COUNT = FIELD_FEATURE_COUNT + 4;

	/**
	 * The meta object id for the '{@link KerblueForms.impl.FloatFieldImpl <em>Float Field</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see KerblueForms.impl.FloatFieldImpl
	 * @see KerblueForms.impl.KerblueFormsPackageImpl#getFloatField()
	 * @generated
	 */
	int FLOAT_FIELD = 9;

	/**
	 * The feature id for the '<em><b>Label</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int FLOAT_FIELD__LABEL = FIELD__LABEL;

	/**
	 * The feature id for the '<em><b>Id</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int FLOAT_FIELD__ID = FIELD__ID;

	/**
	 * The feature id for the '<em><b>Help text</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int FLOAT_FIELD__HELP_TEXT = FIELD__HELP_TEXT;

	/**
	 * The feature id for the '<em><b>Ref</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int FLOAT_FIELD__REF = FIELD__REF;

	/**
	 * The feature id for the '<em><b>Mandatory</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int FLOAT_FIELD__MANDATORY = FIELD__MANDATORY;

	/**
	 * The feature id for the '<em><b>Hidden</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int FLOAT_FIELD__HIDDEN = FIELD__HIDDEN;

	/**
	 * The feature id for the '<em><b>Error messages</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int FLOAT_FIELD__ERROR_MESSAGES = FIELD__ERROR_MESSAGES;

	/**
	 * The feature id for the '<em><b>Initial</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int FLOAT_FIELD__INITIAL = FIELD__INITIAL;

	/**
	 * The feature id for the '<em><b>Disabled</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int FLOAT_FIELD__DISABLED = FIELD__DISABLED;

	/**
	 * The feature id for the '<em><b>Field Size</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int FLOAT_FIELD__FIELD_SIZE = FIELD__FIELD_SIZE;

	/**
	 * The feature id for the '<em><b>Min value</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int FLOAT_FIELD__MIN_VALUE = FIELD_FEATURE_COUNT + 0;

	/**
	 * The feature id for the '<em><b>Max value</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int FLOAT_FIELD__MAX_VALUE = FIELD_FEATURE_COUNT + 1;

	/**
	 * The number of structural features of the '<em>Float Field</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int FLOAT_FIELD_FEATURE_COUNT = FIELD_FEATURE_COUNT + 2;

	/**
	 * The meta object id for the '{@link KerblueForms.impl.IntegerFieldImpl <em>Integer Field</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see KerblueForms.impl.IntegerFieldImpl
	 * @see KerblueForms.impl.KerblueFormsPackageImpl#getIntegerField()
	 * @generated
	 */
	int INTEGER_FIELD = 10;

	/**
	 * The feature id for the '<em><b>Label</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int INTEGER_FIELD__LABEL = FIELD__LABEL;

	/**
	 * The feature id for the '<em><b>Id</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int INTEGER_FIELD__ID = FIELD__ID;

	/**
	 * The feature id for the '<em><b>Help text</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int INTEGER_FIELD__HELP_TEXT = FIELD__HELP_TEXT;

	/**
	 * The feature id for the '<em><b>Ref</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int INTEGER_FIELD__REF = FIELD__REF;

	/**
	 * The feature id for the '<em><b>Mandatory</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int INTEGER_FIELD__MANDATORY = FIELD__MANDATORY;

	/**
	 * The feature id for the '<em><b>Hidden</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int INTEGER_FIELD__HIDDEN = FIELD__HIDDEN;

	/**
	 * The feature id for the '<em><b>Error messages</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int INTEGER_FIELD__ERROR_MESSAGES = FIELD__ERROR_MESSAGES;

	/**
	 * The feature id for the '<em><b>Initial</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int INTEGER_FIELD__INITIAL = FIELD__INITIAL;

	/**
	 * The feature id for the '<em><b>Disabled</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int INTEGER_FIELD__DISABLED = FIELD__DISABLED;

	/**
	 * The feature id for the '<em><b>Field Size</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int INTEGER_FIELD__FIELD_SIZE = FIELD__FIELD_SIZE;

	/**
	 * The feature id for the '<em><b>Min value</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int INTEGER_FIELD__MIN_VALUE = FIELD_FEATURE_COUNT + 0;

	/**
	 * The feature id for the '<em><b>Max value</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int INTEGER_FIELD__MAX_VALUE = FIELD_FEATURE_COUNT + 1;

	/**
	 * The number of structural features of the '<em>Integer Field</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int INTEGER_FIELD_FEATURE_COUNT = FIELD_FEATURE_COUNT + 2;

	/**
	 * The meta object id for the '{@link KerblueForms.impl.ModelChoiceFieldImpl <em>Model Choice Field</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see KerblueForms.impl.ModelChoiceFieldImpl
	 * @see KerblueForms.impl.KerblueFormsPackageImpl#getModelChoiceField()
	 * @generated
	 */
	int MODEL_CHOICE_FIELD = 11;

	/**
	 * The feature id for the '<em><b>Label</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int MODEL_CHOICE_FIELD__LABEL = FIELD__LABEL;

	/**
	 * The feature id for the '<em><b>Id</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int MODEL_CHOICE_FIELD__ID = FIELD__ID;

	/**
	 * The feature id for the '<em><b>Help text</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int MODEL_CHOICE_FIELD__HELP_TEXT = FIELD__HELP_TEXT;

	/**
	 * The feature id for the '<em><b>Ref</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int MODEL_CHOICE_FIELD__REF = FIELD__REF;

	/**
	 * The feature id for the '<em><b>Mandatory</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int MODEL_CHOICE_FIELD__MANDATORY = FIELD__MANDATORY;

	/**
	 * The feature id for the '<em><b>Hidden</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int MODEL_CHOICE_FIELD__HIDDEN = FIELD__HIDDEN;

	/**
	 * The feature id for the '<em><b>Error messages</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int MODEL_CHOICE_FIELD__ERROR_MESSAGES = FIELD__ERROR_MESSAGES;

	/**
	 * The feature id for the '<em><b>Initial</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int MODEL_CHOICE_FIELD__INITIAL = FIELD__INITIAL;

	/**
	 * The feature id for the '<em><b>Disabled</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int MODEL_CHOICE_FIELD__DISABLED = FIELD__DISABLED;

	/**
	 * The feature id for the '<em><b>Field Size</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int MODEL_CHOICE_FIELD__FIELD_SIZE = FIELD__FIELD_SIZE;

	/**
	 * The feature id for the '<em><b>Real class</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int MODEL_CHOICE_FIELD__REAL_CLASS = FIELD_FEATURE_COUNT + 0;

	/**
	 * The feature id for the '<em><b>Association class</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int MODEL_CHOICE_FIELD__ASSOCIATION_CLASS = FIELD_FEATURE_COUNT + 1;

	/**
	 * The feature id for the '<em><b>Min bound</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int MODEL_CHOICE_FIELD__MIN_BOUND = FIELD_FEATURE_COUNT + 2;

	/**
	 * The feature id for the '<em><b>Max bound</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int MODEL_CHOICE_FIELD__MAX_BOUND = FIELD_FEATURE_COUNT + 3;

	/**
	 * The feature id for the '<em><b>Target</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int MODEL_CHOICE_FIELD__TARGET = FIELD_FEATURE_COUNT + 4;

	/**
	 * The feature id for the '<em><b>Association form Class</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int MODEL_CHOICE_FIELD__ASSOCIATION_FORM_CLASS = FIELD_FEATURE_COUNT + 5;

	/**
	 * The feature id for the '<em><b>Widget</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int MODEL_CHOICE_FIELD__WIDGET = FIELD_FEATURE_COUNT + 6;

	/**
	 * The feature id for the '<em><b>Show actions</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int MODEL_CHOICE_FIELD__SHOW_ACTIONS = FIELD_FEATURE_COUNT + 7;

	/**
	 * The number of structural features of the '<em>Model Choice Field</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int MODEL_CHOICE_FIELD_FEATURE_COUNT = FIELD_FEATURE_COUNT + 8;

	/**
	 * The meta object id for the '{@link KerblueForms.impl.EmailFieldImpl <em>Email Field</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see KerblueForms.impl.EmailFieldImpl
	 * @see KerblueForms.impl.KerblueFormsPackageImpl#getEmailField()
	 * @generated
	 */
	int EMAIL_FIELD = 12;

	/**
	 * The feature id for the '<em><b>Label</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int EMAIL_FIELD__LABEL = CHAR_FIELD__LABEL;

	/**
	 * The feature id for the '<em><b>Id</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int EMAIL_FIELD__ID = CHAR_FIELD__ID;

	/**
	 * The feature id for the '<em><b>Help text</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int EMAIL_FIELD__HELP_TEXT = CHAR_FIELD__HELP_TEXT;

	/**
	 * The feature id for the '<em><b>Ref</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int EMAIL_FIELD__REF = CHAR_FIELD__REF;

	/**
	 * The feature id for the '<em><b>Mandatory</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int EMAIL_FIELD__MANDATORY = CHAR_FIELD__MANDATORY;

	/**
	 * The feature id for the '<em><b>Hidden</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int EMAIL_FIELD__HIDDEN = CHAR_FIELD__HIDDEN;

	/**
	 * The feature id for the '<em><b>Error messages</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int EMAIL_FIELD__ERROR_MESSAGES = CHAR_FIELD__ERROR_MESSAGES;

	/**
	 * The feature id for the '<em><b>Initial</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int EMAIL_FIELD__INITIAL = CHAR_FIELD__INITIAL;

	/**
	 * The feature id for the '<em><b>Disabled</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int EMAIL_FIELD__DISABLED = CHAR_FIELD__DISABLED;

	/**
	 * The feature id for the '<em><b>Field Size</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int EMAIL_FIELD__FIELD_SIZE = CHAR_FIELD__FIELD_SIZE;

	/**
	 * The feature id for the '<em><b>Min length</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int EMAIL_FIELD__MIN_LENGTH = CHAR_FIELD__MIN_LENGTH;

	/**
	 * The feature id for the '<em><b>Max length</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int EMAIL_FIELD__MAX_LENGTH = CHAR_FIELD__MAX_LENGTH;

	/**
	 * The number of structural features of the '<em>Email Field</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int EMAIL_FIELD_FEATURE_COUNT = CHAR_FIELD_FEATURE_COUNT + 0;

	/**
	 * The meta object id for the '{@link KerblueForms.impl.FileFieldImpl <em>File Field</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see KerblueForms.impl.FileFieldImpl
	 * @see KerblueForms.impl.KerblueFormsPackageImpl#getFileField()
	 * @generated
	 */
	int FILE_FIELD = 13;

	/**
	 * The feature id for the '<em><b>Label</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int FILE_FIELD__LABEL = FIELD__LABEL;

	/**
	 * The feature id for the '<em><b>Id</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int FILE_FIELD__ID = FIELD__ID;

	/**
	 * The feature id for the '<em><b>Help text</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int FILE_FIELD__HELP_TEXT = FIELD__HELP_TEXT;

	/**
	 * The feature id for the '<em><b>Ref</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int FILE_FIELD__REF = FIELD__REF;

	/**
	 * The feature id for the '<em><b>Mandatory</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int FILE_FIELD__MANDATORY = FIELD__MANDATORY;

	/**
	 * The feature id for the '<em><b>Hidden</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int FILE_FIELD__HIDDEN = FIELD__HIDDEN;

	/**
	 * The feature id for the '<em><b>Error messages</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int FILE_FIELD__ERROR_MESSAGES = FIELD__ERROR_MESSAGES;

	/**
	 * The feature id for the '<em><b>Initial</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int FILE_FIELD__INITIAL = FIELD__INITIAL;

	/**
	 * The feature id for the '<em><b>Disabled</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int FILE_FIELD__DISABLED = FIELD__DISABLED;

	/**
	 * The feature id for the '<em><b>Field Size</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int FILE_FIELD__FIELD_SIZE = FIELD__FIELD_SIZE;

	/**
	 * The number of structural features of the '<em>File Field</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int FILE_FIELD_FEATURE_COUNT = FIELD_FEATURE_COUNT + 0;

	/**
	 * The meta object id for the '{@link KerblueForms.impl.ImageFieldImpl <em>Image Field</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see KerblueForms.impl.ImageFieldImpl
	 * @see KerblueForms.impl.KerblueFormsPackageImpl#getImageField()
	 * @generated
	 */
	int IMAGE_FIELD = 14;

	/**
	 * The feature id for the '<em><b>Label</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int IMAGE_FIELD__LABEL = FILE_FIELD__LABEL;

	/**
	 * The feature id for the '<em><b>Id</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int IMAGE_FIELD__ID = FILE_FIELD__ID;

	/**
	 * The feature id for the '<em><b>Help text</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int IMAGE_FIELD__HELP_TEXT = FILE_FIELD__HELP_TEXT;

	/**
	 * The feature id for the '<em><b>Ref</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int IMAGE_FIELD__REF = FILE_FIELD__REF;

	/**
	 * The feature id for the '<em><b>Mandatory</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int IMAGE_FIELD__MANDATORY = FILE_FIELD__MANDATORY;

	/**
	 * The feature id for the '<em><b>Hidden</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int IMAGE_FIELD__HIDDEN = FILE_FIELD__HIDDEN;

	/**
	 * The feature id for the '<em><b>Error messages</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int IMAGE_FIELD__ERROR_MESSAGES = FILE_FIELD__ERROR_MESSAGES;

	/**
	 * The feature id for the '<em><b>Initial</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int IMAGE_FIELD__INITIAL = FILE_FIELD__INITIAL;

	/**
	 * The feature id for the '<em><b>Disabled</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int IMAGE_FIELD__DISABLED = FILE_FIELD__DISABLED;

	/**
	 * The feature id for the '<em><b>Field Size</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int IMAGE_FIELD__FIELD_SIZE = FILE_FIELD__FIELD_SIZE;

	/**
	 * The number of structural features of the '<em>Image Field</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int IMAGE_FIELD_FEATURE_COUNT = FILE_FIELD_FEATURE_COUNT + 0;

	/**
	 * The meta object id for the '{@link KerblueForms.impl.TimeFieldImpl <em>Time Field</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see KerblueForms.impl.TimeFieldImpl
	 * @see KerblueForms.impl.KerblueFormsPackageImpl#getTimeField()
	 * @generated
	 */
	int TIME_FIELD = 15;

	/**
	 * The feature id for the '<em><b>Label</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TIME_FIELD__LABEL = DATE_FIELD__LABEL;

	/**
	 * The feature id for the '<em><b>Id</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TIME_FIELD__ID = DATE_FIELD__ID;

	/**
	 * The feature id for the '<em><b>Help text</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TIME_FIELD__HELP_TEXT = DATE_FIELD__HELP_TEXT;

	/**
	 * The feature id for the '<em><b>Ref</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TIME_FIELD__REF = DATE_FIELD__REF;

	/**
	 * The feature id for the '<em><b>Mandatory</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TIME_FIELD__MANDATORY = DATE_FIELD__MANDATORY;

	/**
	 * The feature id for the '<em><b>Hidden</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TIME_FIELD__HIDDEN = DATE_FIELD__HIDDEN;

	/**
	 * The feature id for the '<em><b>Error messages</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TIME_FIELD__ERROR_MESSAGES = DATE_FIELD__ERROR_MESSAGES;

	/**
	 * The feature id for the '<em><b>Initial</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TIME_FIELD__INITIAL = DATE_FIELD__INITIAL;

	/**
	 * The feature id for the '<em><b>Disabled</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TIME_FIELD__DISABLED = DATE_FIELD__DISABLED;

	/**
	 * The feature id for the '<em><b>Field Size</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TIME_FIELD__FIELD_SIZE = DATE_FIELD__FIELD_SIZE;

	/**
	 * The feature id for the '<em><b>Input formats</b></em>' attribute list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TIME_FIELD__INPUT_FORMATS = DATE_FIELD__INPUT_FORMATS;

	/**
	 * The feature id for the '<em><b>Min date</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TIME_FIELD__MIN_DATE = DATE_FIELD__MIN_DATE;

	/**
	 * The feature id for the '<em><b>Max date</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TIME_FIELD__MAX_DATE = DATE_FIELD__MAX_DATE;

	/**
	 * The number of structural features of the '<em>Time Field</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TIME_FIELD_FEATURE_COUNT = DATE_FIELD_FEATURE_COUNT + 0;

	/**
	 * The meta object id for the '{@link KerblueForms.impl.URLFieldImpl <em>URL Field</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see KerblueForms.impl.URLFieldImpl
	 * @see KerblueForms.impl.KerblueFormsPackageImpl#getURLField()
	 * @generated
	 */
	int URL_FIELD = 16;

	/**
	 * The feature id for the '<em><b>Label</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int URL_FIELD__LABEL = CHAR_FIELD__LABEL;

	/**
	 * The feature id for the '<em><b>Id</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int URL_FIELD__ID = CHAR_FIELD__ID;

	/**
	 * The feature id for the '<em><b>Help text</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int URL_FIELD__HELP_TEXT = CHAR_FIELD__HELP_TEXT;

	/**
	 * The feature id for the '<em><b>Ref</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int URL_FIELD__REF = CHAR_FIELD__REF;

	/**
	 * The feature id for the '<em><b>Mandatory</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int URL_FIELD__MANDATORY = CHAR_FIELD__MANDATORY;

	/**
	 * The feature id for the '<em><b>Hidden</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int URL_FIELD__HIDDEN = CHAR_FIELD__HIDDEN;

	/**
	 * The feature id for the '<em><b>Error messages</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int URL_FIELD__ERROR_MESSAGES = CHAR_FIELD__ERROR_MESSAGES;

	/**
	 * The feature id for the '<em><b>Initial</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int URL_FIELD__INITIAL = CHAR_FIELD__INITIAL;

	/**
	 * The feature id for the '<em><b>Disabled</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int URL_FIELD__DISABLED = CHAR_FIELD__DISABLED;

	/**
	 * The feature id for the '<em><b>Field Size</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int URL_FIELD__FIELD_SIZE = CHAR_FIELD__FIELD_SIZE;

	/**
	 * The feature id for the '<em><b>Min length</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int URL_FIELD__MIN_LENGTH = CHAR_FIELD__MIN_LENGTH;

	/**
	 * The feature id for the '<em><b>Max length</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int URL_FIELD__MAX_LENGTH = CHAR_FIELD__MAX_LENGTH;

	/**
	 * The feature id for the '<em><b>Verify exists</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int URL_FIELD__VERIFY_EXISTS = CHAR_FIELD_FEATURE_COUNT + 0;

	/**
	 * The number of structural features of the '<em>URL Field</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int URL_FIELD_FEATURE_COUNT = CHAR_FIELD_FEATURE_COUNT + 1;

	/**
	 * The meta object id for the '{@link KerblueForms.impl.PhoneNumberFieldImpl <em>Phone Number Field</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see KerblueForms.impl.PhoneNumberFieldImpl
	 * @see KerblueForms.impl.KerblueFormsPackageImpl#getPhoneNumberField()
	 * @generated
	 */
	int PHONE_NUMBER_FIELD = 17;

	/**
	 * The feature id for the '<em><b>Label</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PHONE_NUMBER_FIELD__LABEL = CHAR_FIELD__LABEL;

	/**
	 * The feature id for the '<em><b>Id</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PHONE_NUMBER_FIELD__ID = CHAR_FIELD__ID;

	/**
	 * The feature id for the '<em><b>Help text</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PHONE_NUMBER_FIELD__HELP_TEXT = CHAR_FIELD__HELP_TEXT;

	/**
	 * The feature id for the '<em><b>Ref</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PHONE_NUMBER_FIELD__REF = CHAR_FIELD__REF;

	/**
	 * The feature id for the '<em><b>Mandatory</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PHONE_NUMBER_FIELD__MANDATORY = CHAR_FIELD__MANDATORY;

	/**
	 * The feature id for the '<em><b>Hidden</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PHONE_NUMBER_FIELD__HIDDEN = CHAR_FIELD__HIDDEN;

	/**
	 * The feature id for the '<em><b>Error messages</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PHONE_NUMBER_FIELD__ERROR_MESSAGES = CHAR_FIELD__ERROR_MESSAGES;

	/**
	 * The feature id for the '<em><b>Initial</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PHONE_NUMBER_FIELD__INITIAL = CHAR_FIELD__INITIAL;

	/**
	 * The feature id for the '<em><b>Disabled</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PHONE_NUMBER_FIELD__DISABLED = CHAR_FIELD__DISABLED;

	/**
	 * The feature id for the '<em><b>Field Size</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PHONE_NUMBER_FIELD__FIELD_SIZE = CHAR_FIELD__FIELD_SIZE;

	/**
	 * The feature id for the '<em><b>Min length</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PHONE_NUMBER_FIELD__MIN_LENGTH = CHAR_FIELD__MIN_LENGTH;

	/**
	 * The feature id for the '<em><b>Max length</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PHONE_NUMBER_FIELD__MAX_LENGTH = CHAR_FIELD__MAX_LENGTH;

	/**
	 * The feature id for the '<em><b>Input formats</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PHONE_NUMBER_FIELD__INPUT_FORMATS = CHAR_FIELD_FEATURE_COUNT + 0;

	/**
	 * The number of structural features of the '<em>Phone Number Field</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PHONE_NUMBER_FIELD_FEATURE_COUNT = CHAR_FIELD_FEATURE_COUNT + 1;

	/**
	 * The meta object id for the '{@link KerblueForms.impl.FormAspectImpl <em>Form Aspect</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see KerblueForms.impl.FormAspectImpl
	 * @see KerblueForms.impl.KerblueFormsPackageImpl#getFormAspect()
	 * @generated
	 */
	int FORM_ASPECT = 18;

	/**
	 * The feature id for the '<em><b>Label</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int FORM_ASPECT__LABEL = FORM_GROUP__LABEL;

	/**
	 * The feature id for the '<em><b>Id</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int FORM_ASPECT__ID = FORM_GROUP__ID;

	/**
	 * The feature id for the '<em><b>Help text</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int FORM_ASPECT__HELP_TEXT = FORM_GROUP__HELP_TEXT;

	/**
	 * The feature id for the '<em><b>Ref</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int FORM_ASPECT__REF = FORM_GROUP__REF;

	/**
	 * The feature id for the '<em><b>Children</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int FORM_ASPECT__CHILDREN = FORM_GROUP__CHILDREN;

	/**
	 * The feature id for the '<em><b>Presentation</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int FORM_ASPECT__PRESENTATION = FORM_GROUP__PRESENTATION;

	/**
	 * The feature id for the '<em><b>Disabled</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int FORM_ASPECT__DISABLED = FORM_GROUP__DISABLED;

	/**
	 * The number of structural features of the '<em>Form Aspect</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int FORM_ASPECT_FEATURE_COUNT = FORM_GROUP_FEATURE_COUNT + 0;

	/**
	 * The meta object id for the '{@link KerblueForms.impl.FormClassImpl <em>Form Class</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see KerblueForms.impl.FormClassImpl
	 * @see KerblueForms.impl.KerblueFormsPackageImpl#getFormClass()
	 * @generated
	 */
	int FORM_CLASS = 19;

	/**
	 * The feature id for the '<em><b>Label</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int FORM_CLASS__LABEL = FORM_GROUP__LABEL;

	/**
	 * The feature id for the '<em><b>Id</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int FORM_CLASS__ID = FORM_GROUP__ID;

	/**
	 * The feature id for the '<em><b>Help text</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int FORM_CLASS__HELP_TEXT = FORM_GROUP__HELP_TEXT;

	/**
	 * The feature id for the '<em><b>Ref</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int FORM_CLASS__REF = FORM_GROUP__REF;

	/**
	 * The feature id for the '<em><b>Children</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int FORM_CLASS__CHILDREN = FORM_GROUP__CHILDREN;

	/**
	 * The feature id for the '<em><b>Presentation</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int FORM_CLASS__PRESENTATION = FORM_GROUP__PRESENTATION;

	/**
	 * The feature id for the '<em><b>Disabled</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int FORM_CLASS__DISABLED = FORM_GROUP__DISABLED;

	/**
	 * The feature id for the '<em><b>Real class</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int FORM_CLASS__REAL_CLASS = FORM_GROUP_FEATURE_COUNT + 0;

	/**
	 * The feature id for the '<em><b>Association class</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int FORM_CLASS__ASSOCIATION_CLASS = FORM_GROUP_FEATURE_COUNT + 1;

	/**
	 * The number of structural features of the '<em>Form Class</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int FORM_CLASS_FEATURE_COUNT = FORM_GROUP_FEATURE_COUNT + 2;

	/**
	 * The meta object id for the '{@link KerblueForms.impl.ReferenceImpl <em>Reference</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see KerblueForms.impl.ReferenceImpl
	 * @see KerblueForms.impl.KerblueFormsPackageImpl#getReference()
	 * @generated
	 */
	int REFERENCE = 20;

	/**
	 * The feature id for the '<em><b>Label</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int REFERENCE__LABEL = MODEL_CHOICE_FIELD__LABEL;

	/**
	 * The feature id for the '<em><b>Id</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int REFERENCE__ID = MODEL_CHOICE_FIELD__ID;

	/**
	 * The feature id for the '<em><b>Help text</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int REFERENCE__HELP_TEXT = MODEL_CHOICE_FIELD__HELP_TEXT;

	/**
	 * The feature id for the '<em><b>Ref</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int REFERENCE__REF = MODEL_CHOICE_FIELD__REF;

	/**
	 * The feature id for the '<em><b>Mandatory</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int REFERENCE__MANDATORY = MODEL_CHOICE_FIELD__MANDATORY;

	/**
	 * The feature id for the '<em><b>Hidden</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int REFERENCE__HIDDEN = MODEL_CHOICE_FIELD__HIDDEN;

	/**
	 * The feature id for the '<em><b>Error messages</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int REFERENCE__ERROR_MESSAGES = MODEL_CHOICE_FIELD__ERROR_MESSAGES;

	/**
	 * The feature id for the '<em><b>Initial</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int REFERENCE__INITIAL = MODEL_CHOICE_FIELD__INITIAL;

	/**
	 * The feature id for the '<em><b>Disabled</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int REFERENCE__DISABLED = MODEL_CHOICE_FIELD__DISABLED;

	/**
	 * The feature id for the '<em><b>Field Size</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int REFERENCE__FIELD_SIZE = MODEL_CHOICE_FIELD__FIELD_SIZE;

	/**
	 * The feature id for the '<em><b>Real class</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int REFERENCE__REAL_CLASS = MODEL_CHOICE_FIELD__REAL_CLASS;

	/**
	 * The feature id for the '<em><b>Association class</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int REFERENCE__ASSOCIATION_CLASS = MODEL_CHOICE_FIELD__ASSOCIATION_CLASS;

	/**
	 * The feature id for the '<em><b>Min bound</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int REFERENCE__MIN_BOUND = MODEL_CHOICE_FIELD__MIN_BOUND;

	/**
	 * The feature id for the '<em><b>Max bound</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int REFERENCE__MAX_BOUND = MODEL_CHOICE_FIELD__MAX_BOUND;

	/**
	 * The feature id for the '<em><b>Target</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int REFERENCE__TARGET = MODEL_CHOICE_FIELD__TARGET;

	/**
	 * The feature id for the '<em><b>Association form Class</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int REFERENCE__ASSOCIATION_FORM_CLASS = MODEL_CHOICE_FIELD__ASSOCIATION_FORM_CLASS;

	/**
	 * The feature id for the '<em><b>Widget</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int REFERENCE__WIDGET = MODEL_CHOICE_FIELD__WIDGET;

	/**
	 * The feature id for the '<em><b>Show actions</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int REFERENCE__SHOW_ACTIONS = MODEL_CHOICE_FIELD__SHOW_ACTIONS;

	/**
	 * The number of structural features of the '<em>Reference</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int REFERENCE_FEATURE_COUNT = MODEL_CHOICE_FIELD_FEATURE_COUNT + 0;


	/**
	 * The meta object id for the '{@link KerblueForms.impl.FormCollectionImpl <em>Form Collection</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see KerblueForms.impl.FormCollectionImpl
	 * @see KerblueForms.impl.KerblueFormsPackageImpl#getFormCollection()
	 * @generated
	 */
	int FORM_COLLECTION = 21;

	/**
	 * The feature id for the '<em><b>Forms</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int FORM_COLLECTION__FORMS = 0;

	/**
	 * The number of structural features of the '<em>Form Collection</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int FORM_COLLECTION_FEATURE_COUNT = 1;

	/**
	 * The meta object id for the '{@link KerblueForms.impl.ChoiceFieldImpl <em>Choice Field</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see KerblueForms.impl.ChoiceFieldImpl
	 * @see KerblueForms.impl.KerblueFormsPackageImpl#getChoiceField()
	 * @generated
	 */
	int CHOICE_FIELD = 22;

	/**
	 * The feature id for the '<em><b>Label</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CHOICE_FIELD__LABEL = FIELD__LABEL;

	/**
	 * The feature id for the '<em><b>Id</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CHOICE_FIELD__ID = FIELD__ID;

	/**
	 * The feature id for the '<em><b>Help text</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CHOICE_FIELD__HELP_TEXT = FIELD__HELP_TEXT;

	/**
	 * The feature id for the '<em><b>Ref</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CHOICE_FIELD__REF = FIELD__REF;

	/**
	 * The feature id for the '<em><b>Mandatory</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CHOICE_FIELD__MANDATORY = FIELD__MANDATORY;

	/**
	 * The feature id for the '<em><b>Hidden</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CHOICE_FIELD__HIDDEN = FIELD__HIDDEN;

	/**
	 * The feature id for the '<em><b>Error messages</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CHOICE_FIELD__ERROR_MESSAGES = FIELD__ERROR_MESSAGES;

	/**
	 * The feature id for the '<em><b>Initial</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CHOICE_FIELD__INITIAL = FIELD__INITIAL;

	/**
	 * The feature id for the '<em><b>Disabled</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CHOICE_FIELD__DISABLED = FIELD__DISABLED;

	/**
	 * The feature id for the '<em><b>Field Size</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CHOICE_FIELD__FIELD_SIZE = FIELD__FIELD_SIZE;

	/**
	 * The feature id for the '<em><b>Min bound</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CHOICE_FIELD__MIN_BOUND = FIELD_FEATURE_COUNT + 0;

	/**
	 * The feature id for the '<em><b>Max bound</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CHOICE_FIELD__MAX_BOUND = FIELD_FEATURE_COUNT + 1;

	/**
	 * The feature id for the '<em><b>Widget</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CHOICE_FIELD__WIDGET = FIELD_FEATURE_COUNT + 2;

	/**
	 * The feature id for the '<em><b>Multiple</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CHOICE_FIELD__MULTIPLE = FIELD_FEATURE_COUNT + 3;

	/**
	 * The feature id for the '<em><b>Filter Parent</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CHOICE_FIELD__FILTER_PARENT = FIELD_FEATURE_COUNT + 4;

	/**
	 * The feature id for the '<em><b>Filter Data</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CHOICE_FIELD__FILTER_DATA = FIELD_FEATURE_COUNT + 5;

	/**
	 * The number of structural features of the '<em>Choice Field</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CHOICE_FIELD_FEATURE_COUNT = FIELD_FEATURE_COUNT + 6;

	/**
	 * The meta object id for the '{@link KerblueForms.impl.RegexFieldImpl <em>Regex Field</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see KerblueForms.impl.RegexFieldImpl
	 * @see KerblueForms.impl.KerblueFormsPackageImpl#getRegexField()
	 * @generated
	 */
	int REGEX_FIELD = 23;

	/**
	 * The feature id for the '<em><b>Label</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int REGEX_FIELD__LABEL = CHAR_FIELD__LABEL;

	/**
	 * The feature id for the '<em><b>Id</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int REGEX_FIELD__ID = CHAR_FIELD__ID;

	/**
	 * The feature id for the '<em><b>Help text</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int REGEX_FIELD__HELP_TEXT = CHAR_FIELD__HELP_TEXT;

	/**
	 * The feature id for the '<em><b>Ref</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int REGEX_FIELD__REF = CHAR_FIELD__REF;

	/**
	 * The feature id for the '<em><b>Mandatory</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int REGEX_FIELD__MANDATORY = CHAR_FIELD__MANDATORY;

	/**
	 * The feature id for the '<em><b>Hidden</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int REGEX_FIELD__HIDDEN = CHAR_FIELD__HIDDEN;

	/**
	 * The feature id for the '<em><b>Error messages</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int REGEX_FIELD__ERROR_MESSAGES = CHAR_FIELD__ERROR_MESSAGES;

	/**
	 * The feature id for the '<em><b>Initial</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int REGEX_FIELD__INITIAL = CHAR_FIELD__INITIAL;

	/**
	 * The feature id for the '<em><b>Disabled</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int REGEX_FIELD__DISABLED = CHAR_FIELD__DISABLED;

	/**
	 * The feature id for the '<em><b>Field Size</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int REGEX_FIELD__FIELD_SIZE = CHAR_FIELD__FIELD_SIZE;

	/**
	 * The feature id for the '<em><b>Min length</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int REGEX_FIELD__MIN_LENGTH = CHAR_FIELD__MIN_LENGTH;

	/**
	 * The feature id for the '<em><b>Max length</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int REGEX_FIELD__MAX_LENGTH = CHAR_FIELD__MAX_LENGTH;

	/**
	 * The feature id for the '<em><b>Regex</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int REGEX_FIELD__REGEX = CHAR_FIELD_FEATURE_COUNT + 0;

	/**
	 * The number of structural features of the '<em>Regex Field</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int REGEX_FIELD_FEATURE_COUNT = CHAR_FIELD_FEATURE_COUNT + 1;

	/**
	 * The meta object id for the '{@link KerblueForms.impl.ClassReferenceImpl <em>Class Reference</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see KerblueForms.impl.ClassReferenceImpl
	 * @see KerblueForms.impl.KerblueFormsPackageImpl#getClassReference()
	 * @generated
	 */
	int CLASS_REFERENCE = 24;

	/**
	 * The feature id for the '<em><b>Real class</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CLASS_REFERENCE__REAL_CLASS = 0;

	/**
	 * The feature id for the '<em><b>Association class</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CLASS_REFERENCE__ASSOCIATION_CLASS = 1;

	/**
	 * The number of structural features of the '<em>Class Reference</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CLASS_REFERENCE_FEATURE_COUNT = 2;

	/**
	 * The meta object id for the '{@link KerblueForms.impl.PasswordFieldImpl <em>Password Field</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see KerblueForms.impl.PasswordFieldImpl
	 * @see KerblueForms.impl.KerblueFormsPackageImpl#getPasswordField()
	 * @generated
	 */
	int PASSWORD_FIELD = 25;

	/**
	 * The feature id for the '<em><b>Label</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PASSWORD_FIELD__LABEL = CHAR_FIELD__LABEL;

	/**
	 * The feature id for the '<em><b>Id</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PASSWORD_FIELD__ID = CHAR_FIELD__ID;

	/**
	 * The feature id for the '<em><b>Help text</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PASSWORD_FIELD__HELP_TEXT = CHAR_FIELD__HELP_TEXT;

	/**
	 * The feature id for the '<em><b>Ref</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PASSWORD_FIELD__REF = CHAR_FIELD__REF;

	/**
	 * The feature id for the '<em><b>Mandatory</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PASSWORD_FIELD__MANDATORY = CHAR_FIELD__MANDATORY;

	/**
	 * The feature id for the '<em><b>Hidden</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PASSWORD_FIELD__HIDDEN = CHAR_FIELD__HIDDEN;

	/**
	 * The feature id for the '<em><b>Error messages</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PASSWORD_FIELD__ERROR_MESSAGES = CHAR_FIELD__ERROR_MESSAGES;

	/**
	 * The feature id for the '<em><b>Initial</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PASSWORD_FIELD__INITIAL = CHAR_FIELD__INITIAL;

	/**
	 * The feature id for the '<em><b>Disabled</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PASSWORD_FIELD__DISABLED = CHAR_FIELD__DISABLED;

	/**
	 * The feature id for the '<em><b>Field Size</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PASSWORD_FIELD__FIELD_SIZE = CHAR_FIELD__FIELD_SIZE;

	/**
	 * The feature id for the '<em><b>Min length</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PASSWORD_FIELD__MIN_LENGTH = CHAR_FIELD__MIN_LENGTH;

	/**
	 * The feature id for the '<em><b>Max length</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PASSWORD_FIELD__MAX_LENGTH = CHAR_FIELD__MAX_LENGTH;

	/**
	 * The number of structural features of the '<em>Password Field</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PASSWORD_FIELD_FEATURE_COUNT = CHAR_FIELD_FEATURE_COUNT + 0;

	/**
	 * The meta object id for the '{@link KerblueForms.impl.VirtualFieldImpl <em>Virtual Field</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see KerblueForms.impl.VirtualFieldImpl
	 * @see KerblueForms.impl.KerblueFormsPackageImpl#getVirtualField()
	 * @generated
	 */
	int VIRTUAL_FIELD = 26;

	/**
	 * The feature id for the '<em><b>Label</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int VIRTUAL_FIELD__LABEL = FIELD__LABEL;

	/**
	 * The feature id for the '<em><b>Id</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int VIRTUAL_FIELD__ID = FIELD__ID;

	/**
	 * The feature id for the '<em><b>Help text</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int VIRTUAL_FIELD__HELP_TEXT = FIELD__HELP_TEXT;

	/**
	 * The feature id for the '<em><b>Ref</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int VIRTUAL_FIELD__REF = FIELD__REF;

	/**
	 * The feature id for the '<em><b>Mandatory</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int VIRTUAL_FIELD__MANDATORY = FIELD__MANDATORY;

	/**
	 * The feature id for the '<em><b>Hidden</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int VIRTUAL_FIELD__HIDDEN = FIELD__HIDDEN;

	/**
	 * The feature id for the '<em><b>Error messages</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int VIRTUAL_FIELD__ERROR_MESSAGES = FIELD__ERROR_MESSAGES;

	/**
	 * The feature id for the '<em><b>Initial</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int VIRTUAL_FIELD__INITIAL = FIELD__INITIAL;

	/**
	 * The feature id for the '<em><b>Disabled</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int VIRTUAL_FIELD__DISABLED = FIELD__DISABLED;

	/**
	 * The feature id for the '<em><b>Field Size</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int VIRTUAL_FIELD__FIELD_SIZE = FIELD__FIELD_SIZE;

	/**
	 * The feature id for the '<em><b>Link</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int VIRTUAL_FIELD__LINK = FIELD_FEATURE_COUNT + 0;

	/**
	 * The number of structural features of the '<em>Virtual Field</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int VIRTUAL_FIELD_FEATURE_COUNT = FIELD_FEATURE_COUNT + 1;

	/**
	 * The meta object id for the '{@link KerblueForms.impl.ActionFieldImpl <em>Action Field</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see KerblueForms.impl.ActionFieldImpl
	 * @see KerblueForms.impl.KerblueFormsPackageImpl#getActionField()
	 * @generated
	 */
	int ACTION_FIELD = 27;

	/**
	 * The feature id for the '<em><b>Label</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ACTION_FIELD__LABEL = FIELD__LABEL;

	/**
	 * The feature id for the '<em><b>Id</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ACTION_FIELD__ID = FIELD__ID;

	/**
	 * The feature id for the '<em><b>Help text</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ACTION_FIELD__HELP_TEXT = FIELD__HELP_TEXT;

	/**
	 * The feature id for the '<em><b>Ref</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ACTION_FIELD__REF = FIELD__REF;

	/**
	 * The feature id for the '<em><b>Mandatory</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ACTION_FIELD__MANDATORY = FIELD__MANDATORY;

	/**
	 * The feature id for the '<em><b>Hidden</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ACTION_FIELD__HIDDEN = FIELD__HIDDEN;

	/**
	 * The feature id for the '<em><b>Error messages</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ACTION_FIELD__ERROR_MESSAGES = FIELD__ERROR_MESSAGES;

	/**
	 * The feature id for the '<em><b>Initial</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ACTION_FIELD__INITIAL = FIELD__INITIAL;

	/**
	 * The feature id for the '<em><b>Disabled</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ACTION_FIELD__DISABLED = FIELD__DISABLED;

	/**
	 * The feature id for the '<em><b>Field Size</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ACTION_FIELD__FIELD_SIZE = FIELD__FIELD_SIZE;

	/**
	 * The number of structural features of the '<em>Action Field</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ACTION_FIELD_FEATURE_COUNT = FIELD_FEATURE_COUNT + 0;

	/**
	 * The meta object id for the '{@link KerblueForms.impl.TextFieldImpl <em>Text Field</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see KerblueForms.impl.TextFieldImpl
	 * @see KerblueForms.impl.KerblueFormsPackageImpl#getTextField()
	 * @generated
	 */
	int TEXT_FIELD = 28;

	/**
	 * The feature id for the '<em><b>Label</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TEXT_FIELD__LABEL = CHAR_FIELD__LABEL;

	/**
	 * The feature id for the '<em><b>Id</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TEXT_FIELD__ID = CHAR_FIELD__ID;

	/**
	 * The feature id for the '<em><b>Help text</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TEXT_FIELD__HELP_TEXT = CHAR_FIELD__HELP_TEXT;

	/**
	 * The feature id for the '<em><b>Ref</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TEXT_FIELD__REF = CHAR_FIELD__REF;

	/**
	 * The feature id for the '<em><b>Mandatory</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TEXT_FIELD__MANDATORY = CHAR_FIELD__MANDATORY;

	/**
	 * The feature id for the '<em><b>Hidden</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TEXT_FIELD__HIDDEN = CHAR_FIELD__HIDDEN;

	/**
	 * The feature id for the '<em><b>Error messages</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TEXT_FIELD__ERROR_MESSAGES = CHAR_FIELD__ERROR_MESSAGES;

	/**
	 * The feature id for the '<em><b>Initial</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TEXT_FIELD__INITIAL = CHAR_FIELD__INITIAL;

	/**
	 * The feature id for the '<em><b>Disabled</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TEXT_FIELD__DISABLED = CHAR_FIELD__DISABLED;

	/**
	 * The feature id for the '<em><b>Field Size</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TEXT_FIELD__FIELD_SIZE = CHAR_FIELD__FIELD_SIZE;

	/**
	 * The feature id for the '<em><b>Min length</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TEXT_FIELD__MIN_LENGTH = CHAR_FIELD__MIN_LENGTH;

	/**
	 * The feature id for the '<em><b>Max length</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TEXT_FIELD__MAX_LENGTH = CHAR_FIELD__MAX_LENGTH;

	/**
	 * The feature id for the '<em><b>Widget</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TEXT_FIELD__WIDGET = CHAR_FIELD_FEATURE_COUNT + 0;

	/**
	 * The number of structural features of the '<em>Text Field</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TEXT_FIELD_FEATURE_COUNT = CHAR_FIELD_FEATURE_COUNT + 1;

	/**
	 * The meta object id for the '{@link KerblueForms.FormGroupPresentationType <em>Form Group Presentation Type</em>}' enum.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see KerblueForms.FormGroupPresentationType
	 * @see KerblueForms.impl.KerblueFormsPackageImpl#getFormGroupPresentationType()
	 * @generated
	 */
	int FORM_GROUP_PRESENTATION_TYPE = 29;


	/**
	 * The meta object id for the '{@link KerblueForms.TextWidgetType <em>Text Widget Type</em>}' enum.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see KerblueForms.TextWidgetType
	 * @see KerblueForms.impl.KerblueFormsPackageImpl#getTextWidgetType()
	 * @generated
	 */
	int TEXT_WIDGET_TYPE = 30;

	/**
	 * The meta object id for the '{@link KerblueForms.ChoiceWidgetType <em>Choice Widget Type</em>}' enum.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see KerblueForms.ChoiceWidgetType
	 * @see KerblueForms.impl.KerblueFormsPackageImpl#getChoiceWidgetType()
	 * @generated
	 */
	int CHOICE_WIDGET_TYPE = 31;

	/**
	 * The meta object id for the '{@link KerblueForms.ReferenceWidgetType <em>Reference Widget Type</em>}' enum.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see KerblueForms.ReferenceWidgetType
	 * @see KerblueForms.impl.KerblueFormsPackageImpl#getReferenceWidgetType()
	 * @generated
	 */
	int REFERENCE_WIDGET_TYPE = 32;


	/**
	 * Returns the meta object for class '{@link KerblueForms.Form <em>Form</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Form</em>'.
	 * @see KerblueForms.Form
	 * @generated
	 */
	EClass getForm();

	/**
	 * Returns the meta object for the containment reference '{@link KerblueForms.Form#getRoot <em>Root</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference '<em>Root</em>'.
	 * @see KerblueForms.Form#getRoot()
	 * @see #getForm()
	 * @generated
	 */
	EReference getForm_Root();

	/**
	 * Returns the meta object for the attribute '{@link KerblueForms.Form#getName <em>Name</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Name</em>'.
	 * @see KerblueForms.Form#getName()
	 * @see #getForm()
	 * @generated
	 */
	EAttribute getForm_Name();

	/**
	 * Returns the meta object for class '{@link KerblueForms.FormElement <em>Form Element</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Form Element</em>'.
	 * @see KerblueForms.FormElement
	 * @generated
	 */
	EClass getFormElement();

	/**
	 * Returns the meta object for the attribute '{@link KerblueForms.FormElement#getLabel <em>Label</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Label</em>'.
	 * @see KerblueForms.FormElement#getLabel()
	 * @see #getFormElement()
	 * @generated
	 */
	EAttribute getFormElement_Label();

	/**
	 * Returns the meta object for the attribute '{@link KerblueForms.FormElement#getId <em>Id</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Id</em>'.
	 * @see KerblueForms.FormElement#getId()
	 * @see #getFormElement()
	 * @generated
	 */
	EAttribute getFormElement_Id();

	/**
	 * Returns the meta object for the attribute '{@link KerblueForms.FormElement#getHelp_text <em>Help text</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Help text</em>'.
	 * @see KerblueForms.FormElement#getHelp_text()
	 * @see #getFormElement()
	 * @generated
	 */
	EAttribute getFormElement_Help_text();

	/**
	 * Returns the meta object for the reference '{@link KerblueForms.FormElement#getRef <em>Ref</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference '<em>Ref</em>'.
	 * @see KerblueForms.FormElement#getRef()
	 * @see #getFormElement()
	 * @generated
	 */
	EReference getFormElement_Ref();

	/**
	 * Returns the meta object for class '{@link KerblueForms.FormGroup <em>Form Group</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Form Group</em>'.
	 * @see KerblueForms.FormGroup
	 * @generated
	 */
	EClass getFormGroup();

	/**
	 * Returns the meta object for the containment reference list '{@link KerblueForms.FormGroup#getChildren <em>Children</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference list '<em>Children</em>'.
	 * @see KerblueForms.FormGroup#getChildren()
	 * @see #getFormGroup()
	 * @generated
	 */
	EReference getFormGroup_Children();

	/**
	 * Returns the meta object for the attribute '{@link KerblueForms.FormGroup#getPresentation <em>Presentation</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Presentation</em>'.
	 * @see KerblueForms.FormGroup#getPresentation()
	 * @see #getFormGroup()
	 * @generated
	 */
	EAttribute getFormGroup_Presentation();

	/**
	 * Returns the meta object for the containment reference list '{@link KerblueForms.FormGroup#getDisabled <em>Disabled</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference list '<em>Disabled</em>'.
	 * @see KerblueForms.FormGroup#getDisabled()
	 * @see #getFormGroup()
	 * @generated
	 */
	EReference getFormGroup_Disabled();

	/**
	 * Returns the meta object for class '{@link KerblueForms.Field <em>Field</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Field</em>'.
	 * @see KerblueForms.Field
	 * @generated
	 */
	EClass getField();

	/**
	 * Returns the meta object for the attribute '{@link KerblueForms.Field#isMandatory <em>Mandatory</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Mandatory</em>'.
	 * @see KerblueForms.Field#isMandatory()
	 * @see #getField()
	 * @generated
	 */
	EAttribute getField_Mandatory();

	/**
	 * Returns the meta object for the attribute '{@link KerblueForms.Field#isHidden <em>Hidden</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Hidden</em>'.
	 * @see KerblueForms.Field#isHidden()
	 * @see #getField()
	 * @generated
	 */
	EAttribute getField_Hidden();

	/**
	 * Returns the meta object for the attribute '{@link KerblueForms.Field#getError_messages <em>Error messages</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Error messages</em>'.
	 * @see KerblueForms.Field#getError_messages()
	 * @see #getField()
	 * @generated
	 */
	EAttribute getField_Error_messages();

	/**
	 * Returns the meta object for the attribute '{@link KerblueForms.Field#getInitial <em>Initial</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Initial</em>'.
	 * @see KerblueForms.Field#getInitial()
	 * @see #getField()
	 * @generated
	 */
	EAttribute getField_Initial();

	/**
	 * Returns the meta object for the attribute '{@link KerblueForms.Field#isDisabled <em>Disabled</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Disabled</em>'.
	 * @see KerblueForms.Field#isDisabled()
	 * @see #getField()
	 * @generated
	 */
	EAttribute getField_Disabled();

	/**
	 * Returns the meta object for the attribute '{@link KerblueForms.Field#getFieldSize <em>Field Size</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Field Size</em>'.
	 * @see KerblueForms.Field#getFieldSize()
	 * @see #getField()
	 * @generated
	 */
	EAttribute getField_FieldSize();

	/**
	 * Returns the meta object for class '{@link KerblueForms.BooleanField <em>Boolean Field</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Boolean Field</em>'.
	 * @see KerblueForms.BooleanField
	 * @generated
	 */
	EClass getBooleanField();

	/**
	 * Returns the meta object for class '{@link KerblueForms.CharField <em>Char Field</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Char Field</em>'.
	 * @see KerblueForms.CharField
	 * @generated
	 */
	EClass getCharField();

	/**
	 * Returns the meta object for the attribute '{@link KerblueForms.CharField#getMin_length <em>Min length</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Min length</em>'.
	 * @see KerblueForms.CharField#getMin_length()
	 * @see #getCharField()
	 * @generated
	 */
	EAttribute getCharField_Min_length();

	/**
	 * Returns the meta object for the attribute '{@link KerblueForms.CharField#getMax_length <em>Max length</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Max length</em>'.
	 * @see KerblueForms.CharField#getMax_length()
	 * @see #getCharField()
	 * @generated
	 */
	EAttribute getCharField_Max_length();

	/**
	 * Returns the meta object for class '{@link KerblueForms.DateField <em>Date Field</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Date Field</em>'.
	 * @see KerblueForms.DateField
	 * @generated
	 */
	EClass getDateField();

	/**
	 * Returns the meta object for the attribute list '{@link KerblueForms.DateField#getInput_formats <em>Input formats</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute list '<em>Input formats</em>'.
	 * @see KerblueForms.DateField#getInput_formats()
	 * @see #getDateField()
	 * @generated
	 */
	EAttribute getDateField_Input_formats();

	/**
	 * Returns the meta object for the attribute '{@link KerblueForms.DateField#getMin_date <em>Min date</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Min date</em>'.
	 * @see KerblueForms.DateField#getMin_date()
	 * @see #getDateField()
	 * @generated
	 */
	EAttribute getDateField_Min_date();

	/**
	 * Returns the meta object for the attribute '{@link KerblueForms.DateField#getMax_date <em>Max date</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Max date</em>'.
	 * @see KerblueForms.DateField#getMax_date()
	 * @see #getDateField()
	 * @generated
	 */
	EAttribute getDateField_Max_date();

	/**
	 * Returns the meta object for class '{@link KerblueForms.DateTimeField <em>Date Time Field</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Date Time Field</em>'.
	 * @see KerblueForms.DateTimeField
	 * @generated
	 */
	EClass getDateTimeField();

	/**
	 * Returns the meta object for class '{@link KerblueForms.DecimalField <em>Decimal Field</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Decimal Field</em>'.
	 * @see KerblueForms.DecimalField
	 * @generated
	 */
	EClass getDecimalField();

	/**
	 * Returns the meta object for the attribute '{@link KerblueForms.DecimalField#getMin_value <em>Min value</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Min value</em>'.
	 * @see KerblueForms.DecimalField#getMin_value()
	 * @see #getDecimalField()
	 * @generated
	 */
	EAttribute getDecimalField_Min_value();

	/**
	 * Returns the meta object for the attribute '{@link KerblueForms.DecimalField#getMax_value <em>Max value</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Max value</em>'.
	 * @see KerblueForms.DecimalField#getMax_value()
	 * @see #getDecimalField()
	 * @generated
	 */
	EAttribute getDecimalField_Max_value();

	/**
	 * Returns the meta object for the attribute '{@link KerblueForms.DecimalField#getMax_digits <em>Max digits</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Max digits</em>'.
	 * @see KerblueForms.DecimalField#getMax_digits()
	 * @see #getDecimalField()
	 * @generated
	 */
	EAttribute getDecimalField_Max_digits();

	/**
	 * Returns the meta object for the attribute '{@link KerblueForms.DecimalField#getDecimal_places <em>Decimal places</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Decimal places</em>'.
	 * @see KerblueForms.DecimalField#getDecimal_places()
	 * @see #getDecimalField()
	 * @generated
	 */
	EAttribute getDecimalField_Decimal_places();

	/**
	 * Returns the meta object for class '{@link KerblueForms.FloatField <em>Float Field</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Float Field</em>'.
	 * @see KerblueForms.FloatField
	 * @generated
	 */
	EClass getFloatField();

	/**
	 * Returns the meta object for the attribute '{@link KerblueForms.FloatField#getMin_value <em>Min value</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Min value</em>'.
	 * @see KerblueForms.FloatField#getMin_value()
	 * @see #getFloatField()
	 * @generated
	 */
	EAttribute getFloatField_Min_value();

	/**
	 * Returns the meta object for the attribute '{@link KerblueForms.FloatField#getMax_value <em>Max value</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Max value</em>'.
	 * @see KerblueForms.FloatField#getMax_value()
	 * @see #getFloatField()
	 * @generated
	 */
	EAttribute getFloatField_Max_value();

	/**
	 * Returns the meta object for class '{@link KerblueForms.IntegerField <em>Integer Field</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Integer Field</em>'.
	 * @see KerblueForms.IntegerField
	 * @generated
	 */
	EClass getIntegerField();

	/**
	 * Returns the meta object for the attribute '{@link KerblueForms.IntegerField#getMin_value <em>Min value</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Min value</em>'.
	 * @see KerblueForms.IntegerField#getMin_value()
	 * @see #getIntegerField()
	 * @generated
	 */
	EAttribute getIntegerField_Min_value();

	/**
	 * Returns the meta object for the attribute '{@link KerblueForms.IntegerField#getMax_value <em>Max value</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Max value</em>'.
	 * @see KerblueForms.IntegerField#getMax_value()
	 * @see #getIntegerField()
	 * @generated
	 */
	EAttribute getIntegerField_Max_value();

	/**
	 * Returns the meta object for class '{@link KerblueForms.ModelChoiceField <em>Model Choice Field</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Model Choice Field</em>'.
	 * @see KerblueForms.ModelChoiceField
	 * @generated
	 */
	EClass getModelChoiceField();

	/**
	 * Returns the meta object for the attribute '{@link KerblueForms.ModelChoiceField#getMin_bound <em>Min bound</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Min bound</em>'.
	 * @see KerblueForms.ModelChoiceField#getMin_bound()
	 * @see #getModelChoiceField()
	 * @generated
	 */
	EAttribute getModelChoiceField_Min_bound();

	/**
	 * Returns the meta object for the attribute '{@link KerblueForms.ModelChoiceField#getMax_bound <em>Max bound</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Max bound</em>'.
	 * @see KerblueForms.ModelChoiceField#getMax_bound()
	 * @see #getModelChoiceField()
	 * @generated
	 */
	EAttribute getModelChoiceField_Max_bound();

	/**
	 * Returns the meta object for the reference list '{@link KerblueForms.ModelChoiceField#getTarget <em>Target</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference list '<em>Target</em>'.
	 * @see KerblueForms.ModelChoiceField#getTarget()
	 * @see #getModelChoiceField()
	 * @generated
	 */
	EReference getModelChoiceField_Target();

	/**
	 * Returns the meta object for the reference list '{@link KerblueForms.ModelChoiceField#getAssociation_formClass <em>Association form Class</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference list '<em>Association form Class</em>'.
	 * @see KerblueForms.ModelChoiceField#getAssociation_formClass()
	 * @see #getModelChoiceField()
	 * @generated
	 */
	EReference getModelChoiceField_Association_formClass();

	/**
	 * Returns the meta object for the attribute '{@link KerblueForms.ModelChoiceField#getWidget <em>Widget</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Widget</em>'.
	 * @see KerblueForms.ModelChoiceField#getWidget()
	 * @see #getModelChoiceField()
	 * @generated
	 */
	EAttribute getModelChoiceField_Widget();

	/**
	 * Returns the meta object for the attribute '{@link KerblueForms.ModelChoiceField#isShow_actions <em>Show actions</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Show actions</em>'.
	 * @see KerblueForms.ModelChoiceField#isShow_actions()
	 * @see #getModelChoiceField()
	 * @generated
	 */
	EAttribute getModelChoiceField_Show_actions();

	/**
	 * Returns the meta object for class '{@link KerblueForms.EmailField <em>Email Field</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Email Field</em>'.
	 * @see KerblueForms.EmailField
	 * @generated
	 */
	EClass getEmailField();

	/**
	 * Returns the meta object for class '{@link KerblueForms.FileField <em>File Field</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>File Field</em>'.
	 * @see KerblueForms.FileField
	 * @generated
	 */
	EClass getFileField();

	/**
	 * Returns the meta object for class '{@link KerblueForms.ImageField <em>Image Field</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Image Field</em>'.
	 * @see KerblueForms.ImageField
	 * @generated
	 */
	EClass getImageField();

	/**
	 * Returns the meta object for class '{@link KerblueForms.TimeField <em>Time Field</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Time Field</em>'.
	 * @see KerblueForms.TimeField
	 * @generated
	 */
	EClass getTimeField();

	/**
	 * Returns the meta object for class '{@link KerblueForms.URLField <em>URL Field</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>URL Field</em>'.
	 * @see KerblueForms.URLField
	 * @generated
	 */
	EClass getURLField();

	/**
	 * Returns the meta object for the attribute '{@link KerblueForms.URLField#isVerify_exists <em>Verify exists</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Verify exists</em>'.
	 * @see KerblueForms.URLField#isVerify_exists()
	 * @see #getURLField()
	 * @generated
	 */
	EAttribute getURLField_Verify_exists();

	/**
	 * Returns the meta object for class '{@link KerblueForms.PhoneNumberField <em>Phone Number Field</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Phone Number Field</em>'.
	 * @see KerblueForms.PhoneNumberField
	 * @generated
	 */
	EClass getPhoneNumberField();

	/**
	 * Returns the meta object for the attribute '{@link KerblueForms.PhoneNumberField#getInput_formats <em>Input formats</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Input formats</em>'.
	 * @see KerblueForms.PhoneNumberField#getInput_formats()
	 * @see #getPhoneNumberField()
	 * @generated
	 */
	EAttribute getPhoneNumberField_Input_formats();

	/**
	 * Returns the meta object for class '{@link KerblueForms.FormAspect <em>Form Aspect</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Form Aspect</em>'.
	 * @see KerblueForms.FormAspect
	 * @generated
	 */
	EClass getFormAspect();

	/**
	 * Returns the meta object for class '{@link KerblueForms.FormClass <em>Form Class</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Form Class</em>'.
	 * @see KerblueForms.FormClass
	 * @generated
	 */
	EClass getFormClass();

	/**
	 * Returns the meta object for class '{@link KerblueForms.Reference <em>Reference</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Reference</em>'.
	 * @see KerblueForms.Reference
	 * @generated
	 */
	EClass getReference();

	/**
	 * Returns the meta object for class '{@link KerblueForms.FormCollection <em>Form Collection</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Form Collection</em>'.
	 * @see KerblueForms.FormCollection
	 * @generated
	 */
	EClass getFormCollection();

	/**
	 * Returns the meta object for the containment reference list '{@link KerblueForms.FormCollection#getForms <em>Forms</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference list '<em>Forms</em>'.
	 * @see KerblueForms.FormCollection#getForms()
	 * @see #getFormCollection()
	 * @generated
	 */
	EReference getFormCollection_Forms();

	/**
	 * Returns the meta object for class '{@link KerblueForms.ChoiceField <em>Choice Field</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Choice Field</em>'.
	 * @see KerblueForms.ChoiceField
	 * @generated
	 */
	EClass getChoiceField();

	/**
	 * Returns the meta object for the attribute '{@link KerblueForms.ChoiceField#getMin_bound <em>Min bound</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Min bound</em>'.
	 * @see KerblueForms.ChoiceField#getMin_bound()
	 * @see #getChoiceField()
	 * @generated
	 */
	EAttribute getChoiceField_Min_bound();

	/**
	 * Returns the meta object for the attribute '{@link KerblueForms.ChoiceField#getMax_bound <em>Max bound</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Max bound</em>'.
	 * @see KerblueForms.ChoiceField#getMax_bound()
	 * @see #getChoiceField()
	 * @generated
	 */
	EAttribute getChoiceField_Max_bound();

	/**
	 * Returns the meta object for the attribute '{@link KerblueForms.ChoiceField#getWidget <em>Widget</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Widget</em>'.
	 * @see KerblueForms.ChoiceField#getWidget()
	 * @see #getChoiceField()
	 * @generated
	 */
	EAttribute getChoiceField_Widget();

	/**
	 * Returns the meta object for the attribute '{@link KerblueForms.ChoiceField#isMultiple <em>Multiple</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Multiple</em>'.
	 * @see KerblueForms.ChoiceField#isMultiple()
	 * @see #getChoiceField()
	 * @generated
	 */
	EAttribute getChoiceField_Multiple();

	/**
	 * Returns the meta object for the attribute '{@link KerblueForms.ChoiceField#getFilterParent <em>Filter Parent</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Filter Parent</em>'.
	 * @see KerblueForms.ChoiceField#getFilterParent()
	 * @see #getChoiceField()
	 * @generated
	 */
	EAttribute getChoiceField_FilterParent();

	/**
	 * Returns the meta object for the attribute '{@link KerblueForms.ChoiceField#getFilterData <em>Filter Data</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Filter Data</em>'.
	 * @see KerblueForms.ChoiceField#getFilterData()
	 * @see #getChoiceField()
	 * @generated
	 */
	EAttribute getChoiceField_FilterData();

	/**
	 * Returns the meta object for class '{@link KerblueForms.RegexField <em>Regex Field</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Regex Field</em>'.
	 * @see KerblueForms.RegexField
	 * @generated
	 */
	EClass getRegexField();

	/**
	 * Returns the meta object for the attribute '{@link KerblueForms.RegexField#getRegex <em>Regex</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Regex</em>'.
	 * @see KerblueForms.RegexField#getRegex()
	 * @see #getRegexField()
	 * @generated
	 */
	EAttribute getRegexField_Regex();

	/**
	 * Returns the meta object for class '{@link KerblueForms.ClassReference <em>Class Reference</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Class Reference</em>'.
	 * @see KerblueForms.ClassReference
	 * @generated
	 */
	EClass getClassReference();

	/**
	 * Returns the meta object for the reference '{@link KerblueForms.ClassReference#getReal_class <em>Real class</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference '<em>Real class</em>'.
	 * @see KerblueForms.ClassReference#getReal_class()
	 * @see #getClassReference()
	 * @generated
	 */
	EReference getClassReference_Real_class();

	/**
	 * Returns the meta object for the reference '{@link KerblueForms.ClassReference#getAssociation_class <em>Association class</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference '<em>Association class</em>'.
	 * @see KerblueForms.ClassReference#getAssociation_class()
	 * @see #getClassReference()
	 * @generated
	 */
	EReference getClassReference_Association_class();

	/**
	 * Returns the meta object for class '{@link KerblueForms.PasswordField <em>Password Field</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Password Field</em>'.
	 * @see KerblueForms.PasswordField
	 * @generated
	 */
	EClass getPasswordField();

	/**
	 * Returns the meta object for class '{@link KerblueForms.VirtualField <em>Virtual Field</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Virtual Field</em>'.
	 * @see KerblueForms.VirtualField
	 * @generated
	 */
	EClass getVirtualField();

	/**
	 * Returns the meta object for the reference '{@link KerblueForms.VirtualField#getLink <em>Link</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference '<em>Link</em>'.
	 * @see KerblueForms.VirtualField#getLink()
	 * @see #getVirtualField()
	 * @generated
	 */
	EReference getVirtualField_Link();

	/**
	 * Returns the meta object for class '{@link KerblueForms.ActionField <em>Action Field</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Action Field</em>'.
	 * @see KerblueForms.ActionField
	 * @generated
	 */
	EClass getActionField();

	/**
	 * Returns the meta object for class '{@link KerblueForms.TextField <em>Text Field</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Text Field</em>'.
	 * @see KerblueForms.TextField
	 * @generated
	 */
	EClass getTextField();

	/**
	 * Returns the meta object for the attribute '{@link KerblueForms.TextField#getWidget <em>Widget</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Widget</em>'.
	 * @see KerblueForms.TextField#getWidget()
	 * @see #getTextField()
	 * @generated
	 */
	EAttribute getTextField_Widget();

	/**
	 * Returns the meta object for enum '{@link KerblueForms.FormGroupPresentationType <em>Form Group Presentation Type</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for enum '<em>Form Group Presentation Type</em>'.
	 * @see KerblueForms.FormGroupPresentationType
	 * @generated
	 */
	EEnum getFormGroupPresentationType();

	/**
	 * Returns the meta object for enum '{@link KerblueForms.TextWidgetType <em>Text Widget Type</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for enum '<em>Text Widget Type</em>'.
	 * @see KerblueForms.TextWidgetType
	 * @generated
	 */
	EEnum getTextWidgetType();

	/**
	 * Returns the meta object for enum '{@link KerblueForms.ChoiceWidgetType <em>Choice Widget Type</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for enum '<em>Choice Widget Type</em>'.
	 * @see KerblueForms.ChoiceWidgetType
	 * @generated
	 */
	EEnum getChoiceWidgetType();

	/**
	 * Returns the meta object for enum '{@link KerblueForms.ReferenceWidgetType <em>Reference Widget Type</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for enum '<em>Reference Widget Type</em>'.
	 * @see KerblueForms.ReferenceWidgetType
	 * @generated
	 */
	EEnum getReferenceWidgetType();

	/**
	 * Returns the factory that creates the instances of the model.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the factory that creates the instances of the model.
	 * @generated
	 */
	formFactory getKerblueFormsFactory();

	/**
	 * <!-- begin-user-doc -->
	 * Defines literals for the meta objects that represent
	 * <ul>
	 *   <li>each class,</li>
	 *   <li>each feature of each class,</li>
	 *   <li>each enum,</li>
	 *   <li>and each data type</li>
	 * </ul>
	 * <!-- end-user-doc -->
	 * @generated
	 */
	interface Literals {
		/**
		 * The meta object literal for the '{@link KerblueForms.impl.FormImpl <em>Form</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see KerblueForms.impl.FormImpl
		 * @see KerblueForms.impl.KerblueFormsPackageImpl#getForm()
		 * @generated
		 */
		EClass FORM = eINSTANCE.getForm();

		/**
		 * The meta object literal for the '<em><b>Root</b></em>' containment reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference FORM__ROOT = eINSTANCE.getForm_Root();

		/**
		 * The meta object literal for the '<em><b>Name</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute FORM__NAME = eINSTANCE.getForm_Name();

		/**
		 * The meta object literal for the '{@link KerblueForms.impl.FormElementImpl <em>Form Element</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see KerblueForms.impl.FormElementImpl
		 * @see KerblueForms.impl.KerblueFormsPackageImpl#getFormElement()
		 * @generated
		 */
		EClass FORM_ELEMENT = eINSTANCE.getFormElement();

		/**
		 * The meta object literal for the '<em><b>Label</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute FORM_ELEMENT__LABEL = eINSTANCE.getFormElement_Label();

		/**
		 * The meta object literal for the '<em><b>Id</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute FORM_ELEMENT__ID = eINSTANCE.getFormElement_Id();

		/**
		 * The meta object literal for the '<em><b>Help text</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute FORM_ELEMENT__HELP_TEXT = eINSTANCE.getFormElement_Help_text();

		/**
		 * The meta object literal for the '<em><b>Ref</b></em>' reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference FORM_ELEMENT__REF = eINSTANCE.getFormElement_Ref();

		/**
		 * The meta object literal for the '{@link KerblueForms.impl.FormGroupImpl <em>Form Group</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see KerblueForms.impl.FormGroupImpl
		 * @see KerblueForms.impl.KerblueFormsPackageImpl#getFormGroup()
		 * @generated
		 */
		EClass FORM_GROUP = eINSTANCE.getFormGroup();

		/**
		 * The meta object literal for the '<em><b>Children</b></em>' containment reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference FORM_GROUP__CHILDREN = eINSTANCE.getFormGroup_Children();

		/**
		 * The meta object literal for the '<em><b>Presentation</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute FORM_GROUP__PRESENTATION = eINSTANCE.getFormGroup_Presentation();

		/**
		 * The meta object literal for the '<em><b>Disabled</b></em>' containment reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference FORM_GROUP__DISABLED = eINSTANCE.getFormGroup_Disabled();

		/**
		 * The meta object literal for the '{@link KerblueForms.impl.FieldImpl <em>Field</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see KerblueForms.impl.FieldImpl
		 * @see KerblueForms.impl.KerblueFormsPackageImpl#getField()
		 * @generated
		 */
		EClass FIELD = eINSTANCE.getField();

		/**
		 * The meta object literal for the '<em><b>Mandatory</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute FIELD__MANDATORY = eINSTANCE.getField_Mandatory();

		/**
		 * The meta object literal for the '<em><b>Hidden</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute FIELD__HIDDEN = eINSTANCE.getField_Hidden();

		/**
		 * The meta object literal for the '<em><b>Error messages</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute FIELD__ERROR_MESSAGES = eINSTANCE.getField_Error_messages();

		/**
		 * The meta object literal for the '<em><b>Initial</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute FIELD__INITIAL = eINSTANCE.getField_Initial();

		/**
		 * The meta object literal for the '<em><b>Disabled</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute FIELD__DISABLED = eINSTANCE.getField_Disabled();

		/**
		 * The meta object literal for the '<em><b>Field Size</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute FIELD__FIELD_SIZE = eINSTANCE.getField_FieldSize();

		/**
		 * The meta object literal for the '{@link KerblueForms.impl.BooleanFieldImpl <em>Boolean Field</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see KerblueForms.impl.BooleanFieldImpl
		 * @see KerblueForms.impl.KerblueFormsPackageImpl#getBooleanField()
		 * @generated
		 */
		EClass BOOLEAN_FIELD = eINSTANCE.getBooleanField();

		/**
		 * The meta object literal for the '{@link KerblueForms.impl.CharFieldImpl <em>Char Field</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see KerblueForms.impl.CharFieldImpl
		 * @see KerblueForms.impl.KerblueFormsPackageImpl#getCharField()
		 * @generated
		 */
		EClass CHAR_FIELD = eINSTANCE.getCharField();

		/**
		 * The meta object literal for the '<em><b>Min length</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute CHAR_FIELD__MIN_LENGTH = eINSTANCE.getCharField_Min_length();

		/**
		 * The meta object literal for the '<em><b>Max length</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute CHAR_FIELD__MAX_LENGTH = eINSTANCE.getCharField_Max_length();

		/**
		 * The meta object literal for the '{@link KerblueForms.impl.DateFieldImpl <em>Date Field</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see KerblueForms.impl.DateFieldImpl
		 * @see KerblueForms.impl.KerblueFormsPackageImpl#getDateField()
		 * @generated
		 */
		EClass DATE_FIELD = eINSTANCE.getDateField();

		/**
		 * The meta object literal for the '<em><b>Input formats</b></em>' attribute list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute DATE_FIELD__INPUT_FORMATS = eINSTANCE.getDateField_Input_formats();

		/**
		 * The meta object literal for the '<em><b>Min date</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute DATE_FIELD__MIN_DATE = eINSTANCE.getDateField_Min_date();

		/**
		 * The meta object literal for the '<em><b>Max date</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute DATE_FIELD__MAX_DATE = eINSTANCE.getDateField_Max_date();

		/**
		 * The meta object literal for the '{@link KerblueForms.impl.DateTimeFieldImpl <em>Date Time Field</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see KerblueForms.impl.DateTimeFieldImpl
		 * @see KerblueForms.impl.KerblueFormsPackageImpl#getDateTimeField()
		 * @generated
		 */
		EClass DATE_TIME_FIELD = eINSTANCE.getDateTimeField();

		/**
		 * The meta object literal for the '{@link KerblueForms.impl.DecimalFieldImpl <em>Decimal Field</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see KerblueForms.impl.DecimalFieldImpl
		 * @see KerblueForms.impl.KerblueFormsPackageImpl#getDecimalField()
		 * @generated
		 */
		EClass DECIMAL_FIELD = eINSTANCE.getDecimalField();

		/**
		 * The meta object literal for the '<em><b>Min value</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute DECIMAL_FIELD__MIN_VALUE = eINSTANCE.getDecimalField_Min_value();

		/**
		 * The meta object literal for the '<em><b>Max value</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute DECIMAL_FIELD__MAX_VALUE = eINSTANCE.getDecimalField_Max_value();

		/**
		 * The meta object literal for the '<em><b>Max digits</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute DECIMAL_FIELD__MAX_DIGITS = eINSTANCE.getDecimalField_Max_digits();

		/**
		 * The meta object literal for the '<em><b>Decimal places</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute DECIMAL_FIELD__DECIMAL_PLACES = eINSTANCE.getDecimalField_Decimal_places();

		/**
		 * The meta object literal for the '{@link KerblueForms.impl.FloatFieldImpl <em>Float Field</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see KerblueForms.impl.FloatFieldImpl
		 * @see KerblueForms.impl.KerblueFormsPackageImpl#getFloatField()
		 * @generated
		 */
		EClass FLOAT_FIELD = eINSTANCE.getFloatField();

		/**
		 * The meta object literal for the '<em><b>Min value</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute FLOAT_FIELD__MIN_VALUE = eINSTANCE.getFloatField_Min_value();

		/**
		 * The meta object literal for the '<em><b>Max value</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute FLOAT_FIELD__MAX_VALUE = eINSTANCE.getFloatField_Max_value();

		/**
		 * The meta object literal for the '{@link KerblueForms.impl.IntegerFieldImpl <em>Integer Field</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see KerblueForms.impl.IntegerFieldImpl
		 * @see KerblueForms.impl.KerblueFormsPackageImpl#getIntegerField()
		 * @generated
		 */
		EClass INTEGER_FIELD = eINSTANCE.getIntegerField();

		/**
		 * The meta object literal for the '<em><b>Min value</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute INTEGER_FIELD__MIN_VALUE = eINSTANCE.getIntegerField_Min_value();

		/**
		 * The meta object literal for the '<em><b>Max value</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute INTEGER_FIELD__MAX_VALUE = eINSTANCE.getIntegerField_Max_value();

		/**
		 * The meta object literal for the '{@link KerblueForms.impl.ModelChoiceFieldImpl <em>Model Choice Field</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see KerblueForms.impl.ModelChoiceFieldImpl
		 * @see KerblueForms.impl.KerblueFormsPackageImpl#getModelChoiceField()
		 * @generated
		 */
		EClass MODEL_CHOICE_FIELD = eINSTANCE.getModelChoiceField();

		/**
		 * The meta object literal for the '<em><b>Min bound</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute MODEL_CHOICE_FIELD__MIN_BOUND = eINSTANCE.getModelChoiceField_Min_bound();

		/**
		 * The meta object literal for the '<em><b>Max bound</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute MODEL_CHOICE_FIELD__MAX_BOUND = eINSTANCE.getModelChoiceField_Max_bound();

		/**
		 * The meta object literal for the '<em><b>Target</b></em>' reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference MODEL_CHOICE_FIELD__TARGET = eINSTANCE.getModelChoiceField_Target();

		/**
		 * The meta object literal for the '<em><b>Association form Class</b></em>' reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference MODEL_CHOICE_FIELD__ASSOCIATION_FORM_CLASS = eINSTANCE.getModelChoiceField_Association_formClass();

		/**
		 * The meta object literal for the '<em><b>Widget</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute MODEL_CHOICE_FIELD__WIDGET = eINSTANCE.getModelChoiceField_Widget();

		/**
		 * The meta object literal for the '<em><b>Show actions</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute MODEL_CHOICE_FIELD__SHOW_ACTIONS = eINSTANCE.getModelChoiceField_Show_actions();

		/**
		 * The meta object literal for the '{@link KerblueForms.impl.EmailFieldImpl <em>Email Field</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see KerblueForms.impl.EmailFieldImpl
		 * @see KerblueForms.impl.KerblueFormsPackageImpl#getEmailField()
		 * @generated
		 */
		EClass EMAIL_FIELD = eINSTANCE.getEmailField();

		/**
		 * The meta object literal for the '{@link KerblueForms.impl.FileFieldImpl <em>File Field</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see KerblueForms.impl.FileFieldImpl
		 * @see KerblueForms.impl.KerblueFormsPackageImpl#getFileField()
		 * @generated
		 */
		EClass FILE_FIELD = eINSTANCE.getFileField();

		/**
		 * The meta object literal for the '{@link KerblueForms.impl.ImageFieldImpl <em>Image Field</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see KerblueForms.impl.ImageFieldImpl
		 * @see KerblueForms.impl.KerblueFormsPackageImpl#getImageField()
		 * @generated
		 */
		EClass IMAGE_FIELD = eINSTANCE.getImageField();

		/**
		 * The meta object literal for the '{@link KerblueForms.impl.TimeFieldImpl <em>Time Field</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see KerblueForms.impl.TimeFieldImpl
		 * @see KerblueForms.impl.KerblueFormsPackageImpl#getTimeField()
		 * @generated
		 */
		EClass TIME_FIELD = eINSTANCE.getTimeField();

		/**
		 * The meta object literal for the '{@link KerblueForms.impl.URLFieldImpl <em>URL Field</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see KerblueForms.impl.URLFieldImpl
		 * @see KerblueForms.impl.KerblueFormsPackageImpl#getURLField()
		 * @generated
		 */
		EClass URL_FIELD = eINSTANCE.getURLField();

		/**
		 * The meta object literal for the '<em><b>Verify exists</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute URL_FIELD__VERIFY_EXISTS = eINSTANCE.getURLField_Verify_exists();

		/**
		 * The meta object literal for the '{@link KerblueForms.impl.PhoneNumberFieldImpl <em>Phone Number Field</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see KerblueForms.impl.PhoneNumberFieldImpl
		 * @see KerblueForms.impl.KerblueFormsPackageImpl#getPhoneNumberField()
		 * @generated
		 */
		EClass PHONE_NUMBER_FIELD = eINSTANCE.getPhoneNumberField();

		/**
		 * The meta object literal for the '<em><b>Input formats</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute PHONE_NUMBER_FIELD__INPUT_FORMATS = eINSTANCE.getPhoneNumberField_Input_formats();

		/**
		 * The meta object literal for the '{@link KerblueForms.impl.FormAspectImpl <em>Form Aspect</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see KerblueForms.impl.FormAspectImpl
		 * @see KerblueForms.impl.KerblueFormsPackageImpl#getFormAspect()
		 * @generated
		 */
		EClass FORM_ASPECT = eINSTANCE.getFormAspect();

		/**
		 * The meta object literal for the '{@link KerblueForms.impl.FormClassImpl <em>Form Class</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see KerblueForms.impl.FormClassImpl
		 * @see KerblueForms.impl.KerblueFormsPackageImpl#getFormClass()
		 * @generated
		 */
		EClass FORM_CLASS = eINSTANCE.getFormClass();

		/**
		 * The meta object literal for the '{@link KerblueForms.impl.ReferenceImpl <em>Reference</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see KerblueForms.impl.ReferenceImpl
		 * @see KerblueForms.impl.KerblueFormsPackageImpl#getReference()
		 * @generated
		 */
		EClass REFERENCE = eINSTANCE.getReference();

		/**
		 * The meta object literal for the '{@link KerblueForms.impl.FormCollectionImpl <em>Form Collection</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see KerblueForms.impl.FormCollectionImpl
		 * @see KerblueForms.impl.KerblueFormsPackageImpl#getFormCollection()
		 * @generated
		 */
		EClass FORM_COLLECTION = eINSTANCE.getFormCollection();

		/**
		 * The meta object literal for the '<em><b>Forms</b></em>' containment reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference FORM_COLLECTION__FORMS = eINSTANCE.getFormCollection_Forms();

		/**
		 * The meta object literal for the '{@link KerblueForms.impl.ChoiceFieldImpl <em>Choice Field</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see KerblueForms.impl.ChoiceFieldImpl
		 * @see KerblueForms.impl.KerblueFormsPackageImpl#getChoiceField()
		 * @generated
		 */
		EClass CHOICE_FIELD = eINSTANCE.getChoiceField();

		/**
		 * The meta object literal for the '<em><b>Min bound</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute CHOICE_FIELD__MIN_BOUND = eINSTANCE.getChoiceField_Min_bound();

		/**
		 * The meta object literal for the '<em><b>Max bound</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute CHOICE_FIELD__MAX_BOUND = eINSTANCE.getChoiceField_Max_bound();

		/**
		 * The meta object literal for the '<em><b>Widget</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute CHOICE_FIELD__WIDGET = eINSTANCE.getChoiceField_Widget();

		/**
		 * The meta object literal for the '<em><b>Multiple</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute CHOICE_FIELD__MULTIPLE = eINSTANCE.getChoiceField_Multiple();

		/**
		 * The meta object literal for the '<em><b>Filter Parent</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute CHOICE_FIELD__FILTER_PARENT = eINSTANCE.getChoiceField_FilterParent();

		/**
		 * The meta object literal for the '<em><b>Filter Data</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute CHOICE_FIELD__FILTER_DATA = eINSTANCE.getChoiceField_FilterData();

		/**
		 * The meta object literal for the '{@link KerblueForms.impl.RegexFieldImpl <em>Regex Field</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see KerblueForms.impl.RegexFieldImpl
		 * @see KerblueForms.impl.KerblueFormsPackageImpl#getRegexField()
		 * @generated
		 */
		EClass REGEX_FIELD = eINSTANCE.getRegexField();

		/**
		 * The meta object literal for the '<em><b>Regex</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute REGEX_FIELD__REGEX = eINSTANCE.getRegexField_Regex();

		/**
		 * The meta object literal for the '{@link KerblueForms.impl.ClassReferenceImpl <em>Class Reference</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see KerblueForms.impl.ClassReferenceImpl
		 * @see KerblueForms.impl.KerblueFormsPackageImpl#getClassReference()
		 * @generated
		 */
		EClass CLASS_REFERENCE = eINSTANCE.getClassReference();

		/**
		 * The meta object literal for the '<em><b>Real class</b></em>' reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference CLASS_REFERENCE__REAL_CLASS = eINSTANCE.getClassReference_Real_class();

		/**
		 * The meta object literal for the '<em><b>Association class</b></em>' reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference CLASS_REFERENCE__ASSOCIATION_CLASS = eINSTANCE.getClassReference_Association_class();

		/**
		 * The meta object literal for the '{@link KerblueForms.impl.PasswordFieldImpl <em>Password Field</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see KerblueForms.impl.PasswordFieldImpl
		 * @see KerblueForms.impl.KerblueFormsPackageImpl#getPasswordField()
		 * @generated
		 */
		EClass PASSWORD_FIELD = eINSTANCE.getPasswordField();

		/**
		 * The meta object literal for the '{@link KerblueForms.impl.VirtualFieldImpl <em>Virtual Field</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see KerblueForms.impl.VirtualFieldImpl
		 * @see KerblueForms.impl.KerblueFormsPackageImpl#getVirtualField()
		 * @generated
		 */
		EClass VIRTUAL_FIELD = eINSTANCE.getVirtualField();

		/**
		 * The meta object literal for the '<em><b>Link</b></em>' reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference VIRTUAL_FIELD__LINK = eINSTANCE.getVirtualField_Link();

		/**
		 * The meta object literal for the '{@link KerblueForms.impl.ActionFieldImpl <em>Action Field</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see KerblueForms.impl.ActionFieldImpl
		 * @see KerblueForms.impl.KerblueFormsPackageImpl#getActionField()
		 * @generated
		 */
		EClass ACTION_FIELD = eINSTANCE.getActionField();

		/**
		 * The meta object literal for the '{@link KerblueForms.impl.TextFieldImpl <em>Text Field</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see KerblueForms.impl.TextFieldImpl
		 * @see KerblueForms.impl.KerblueFormsPackageImpl#getTextField()
		 * @generated
		 */
		EClass TEXT_FIELD = eINSTANCE.getTextField();

		/**
		 * The meta object literal for the '<em><b>Widget</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute TEXT_FIELD__WIDGET = eINSTANCE.getTextField_Widget();

		/**
		 * The meta object literal for the '{@link KerblueForms.FormGroupPresentationType <em>Form Group Presentation Type</em>}' enum.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see KerblueForms.FormGroupPresentationType
		 * @see KerblueForms.impl.KerblueFormsPackageImpl#getFormGroupPresentationType()
		 * @generated
		 */
		EEnum FORM_GROUP_PRESENTATION_TYPE = eINSTANCE.getFormGroupPresentationType();

		/**
		 * The meta object literal for the '{@link KerblueForms.TextWidgetType <em>Text Widget Type</em>}' enum.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see KerblueForms.TextWidgetType
		 * @see KerblueForms.impl.KerblueFormsPackageImpl#getTextWidgetType()
		 * @generated
		 */
		EEnum TEXT_WIDGET_TYPE = eINSTANCE.getTextWidgetType();

		/**
		 * The meta object literal for the '{@link KerblueForms.ChoiceWidgetType <em>Choice Widget Type</em>}' enum.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see KerblueForms.ChoiceWidgetType
		 * @see KerblueForms.impl.KerblueFormsPackageImpl#getChoiceWidgetType()
		 * @generated
		 */
		EEnum CHOICE_WIDGET_TYPE = eINSTANCE.getChoiceWidgetType();

		/**
		 * The meta object literal for the '{@link KerblueForms.ReferenceWidgetType <em>Reference Widget Type</em>}' enum.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see KerblueForms.ReferenceWidgetType
		 * @see KerblueForms.impl.KerblueFormsPackageImpl#getReferenceWidgetType()
		 * @generated
		 */
		EEnum REFERENCE_WIDGET_TYPE = eINSTANCE.getReferenceWidgetType();

	}

} //KerblueFormsPackage
