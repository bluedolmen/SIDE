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
 * @see com.bluexml.side.form.formFactory
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
	String eNAME = "form";

	/**
	 * The package namespace URI.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	String eNS_URI = "http://www.kerblue.org/form/1.0";

	/**
	 * The package namespace name.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	String eNS_PREFIX = "form";

	/**
	 * The singleton instance of the package.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	formPackage eINSTANCE = com.bluexml.side.form.impl.formPackageImpl.init();

	/**
	 * The meta object id for the '{@link com.bluexml.side.form.impl.FormImpl <em>Form</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see com.bluexml.side.form.impl.FormImpl
	 * @see com.bluexml.side.form.impl.formPackageImpl#getForm()
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
	 * The meta object id for the '{@link com.bluexml.side.form.impl.FormElementImpl <em>Form Element</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see com.bluexml.side.form.impl.FormElementImpl
	 * @see com.bluexml.side.form.impl.formPackageImpl#getFormElement()
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
	 * The feature id for the '<em><b>Hidden</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int FORM_ELEMENT__HIDDEN = 4;

	/**
	 * The number of structural features of the '<em>Form Element</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int FORM_ELEMENT_FEATURE_COUNT = 5;

	/**
	 * The meta object id for the '{@link com.bluexml.side.form.impl.FormGroupImpl <em>Form Group</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see com.bluexml.side.form.impl.FormGroupImpl
	 * @see com.bluexml.side.form.impl.formPackageImpl#getFormGroup()
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
	 * The feature id for the '<em><b>Hidden</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int FORM_GROUP__HIDDEN = FORM_ELEMENT__HIDDEN;

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
	 * The meta object id for the '{@link com.bluexml.side.form.impl.FieldImpl <em>Field</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see com.bluexml.side.form.impl.FieldImpl
	 * @see com.bluexml.side.form.impl.formPackageImpl#getField()
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
	 * The feature id for the '<em><b>Hidden</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int FIELD__HIDDEN = FORM_ELEMENT__HIDDEN;

	/**
	 * The feature id for the '<em><b>Mandatory</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int FIELD__MANDATORY = FORM_ELEMENT_FEATURE_COUNT + 0;

	/**
	 * The feature id for the '<em><b>Error messages</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int FIELD__ERROR_MESSAGES = FORM_ELEMENT_FEATURE_COUNT + 1;

	/**
	 * The feature id for the '<em><b>Initial</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int FIELD__INITIAL = FORM_ELEMENT_FEATURE_COUNT + 2;

	/**
	 * The feature id for the '<em><b>Disabled</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int FIELD__DISABLED = FORM_ELEMENT_FEATURE_COUNT + 3;

	/**
	 * The feature id for the '<em><b>Field Size</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int FIELD__FIELD_SIZE = FORM_ELEMENT_FEATURE_COUNT + 4;

	/**
	 * The number of structural features of the '<em>Field</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int FIELD_FEATURE_COUNT = FORM_ELEMENT_FEATURE_COUNT + 5;

	/**
	 * The meta object id for the '{@link com.bluexml.side.form.impl.BooleanFieldImpl <em>Boolean Field</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see com.bluexml.side.form.impl.BooleanFieldImpl
	 * @see com.bluexml.side.form.impl.formPackageImpl#getBooleanField()
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
	 * The feature id for the '<em><b>Hidden</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int BOOLEAN_FIELD__HIDDEN = FIELD__HIDDEN;

	/**
	 * The feature id for the '<em><b>Mandatory</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int BOOLEAN_FIELD__MANDATORY = FIELD__MANDATORY;

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
	 * The meta object id for the '{@link com.bluexml.side.form.impl.CharFieldImpl <em>Char Field</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see com.bluexml.side.form.impl.CharFieldImpl
	 * @see com.bluexml.side.form.impl.formPackageImpl#getCharField()
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
	 * The feature id for the '<em><b>Hidden</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CHAR_FIELD__HIDDEN = FIELD__HIDDEN;

	/**
	 * The feature id for the '<em><b>Mandatory</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CHAR_FIELD__MANDATORY = FIELD__MANDATORY;

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
	 * The meta object id for the '{@link com.bluexml.side.form.impl.DateFieldImpl <em>Date Field</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see com.bluexml.side.form.impl.DateFieldImpl
	 * @see com.bluexml.side.form.impl.formPackageImpl#getDateField()
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
	 * The feature id for the '<em><b>Hidden</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DATE_FIELD__HIDDEN = FIELD__HIDDEN;

	/**
	 * The feature id for the '<em><b>Mandatory</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DATE_FIELD__MANDATORY = FIELD__MANDATORY;

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
	 * The meta object id for the '{@link com.bluexml.side.form.impl.DateTimeFieldImpl <em>Date Time Field</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see com.bluexml.side.form.impl.DateTimeFieldImpl
	 * @see com.bluexml.side.form.impl.formPackageImpl#getDateTimeField()
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
	 * The feature id for the '<em><b>Hidden</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DATE_TIME_FIELD__HIDDEN = DATE_FIELD__HIDDEN;

	/**
	 * The feature id for the '<em><b>Mandatory</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DATE_TIME_FIELD__MANDATORY = DATE_FIELD__MANDATORY;

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
	 * The meta object id for the '{@link com.bluexml.side.form.impl.DecimalFieldImpl <em>Decimal Field</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see com.bluexml.side.form.impl.DecimalFieldImpl
	 * @see com.bluexml.side.form.impl.formPackageImpl#getDecimalField()
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
	 * The feature id for the '<em><b>Hidden</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DECIMAL_FIELD__HIDDEN = FIELD__HIDDEN;

	/**
	 * The feature id for the '<em><b>Mandatory</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DECIMAL_FIELD__MANDATORY = FIELD__MANDATORY;

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
	 * The meta object id for the '{@link com.bluexml.side.form.impl.FloatFieldImpl <em>Float Field</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see com.bluexml.side.form.impl.FloatFieldImpl
	 * @see com.bluexml.side.form.impl.formPackageImpl#getFloatField()
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
	 * The feature id for the '<em><b>Hidden</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int FLOAT_FIELD__HIDDEN = FIELD__HIDDEN;

	/**
	 * The feature id for the '<em><b>Mandatory</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int FLOAT_FIELD__MANDATORY = FIELD__MANDATORY;

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
	 * The meta object id for the '{@link com.bluexml.side.form.impl.IntegerFieldImpl <em>Integer Field</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see com.bluexml.side.form.impl.IntegerFieldImpl
	 * @see com.bluexml.side.form.impl.formPackageImpl#getIntegerField()
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
	 * The feature id for the '<em><b>Hidden</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int INTEGER_FIELD__HIDDEN = FIELD__HIDDEN;

	/**
	 * The feature id for the '<em><b>Mandatory</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int INTEGER_FIELD__MANDATORY = FIELD__MANDATORY;

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
	 * The meta object id for the '{@link com.bluexml.side.form.impl.ModelChoiceFieldImpl <em>Model Choice Field</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see com.bluexml.side.form.impl.ModelChoiceFieldImpl
	 * @see com.bluexml.side.form.impl.formPackageImpl#getModelChoiceField()
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
	 * The feature id for the '<em><b>Hidden</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int MODEL_CHOICE_FIELD__HIDDEN = FIELD__HIDDEN;

	/**
	 * The feature id for the '<em><b>Mandatory</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int MODEL_CHOICE_FIELD__MANDATORY = FIELD__MANDATORY;

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
	 * The meta object id for the '{@link com.bluexml.side.form.impl.EmailFieldImpl <em>Email Field</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see com.bluexml.side.form.impl.EmailFieldImpl
	 * @see com.bluexml.side.form.impl.formPackageImpl#getEmailField()
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
	 * The feature id for the '<em><b>Hidden</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int EMAIL_FIELD__HIDDEN = CHAR_FIELD__HIDDEN;

	/**
	 * The feature id for the '<em><b>Mandatory</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int EMAIL_FIELD__MANDATORY = CHAR_FIELD__MANDATORY;

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
	 * The meta object id for the '{@link com.bluexml.side.form.impl.FileFieldImpl <em>File Field</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see com.bluexml.side.form.impl.FileFieldImpl
	 * @see com.bluexml.side.form.impl.formPackageImpl#getFileField()
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
	 * The feature id for the '<em><b>Hidden</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int FILE_FIELD__HIDDEN = FIELD__HIDDEN;

	/**
	 * The feature id for the '<em><b>Mandatory</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int FILE_FIELD__MANDATORY = FIELD__MANDATORY;

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
	 * The meta object id for the '{@link com.bluexml.side.form.impl.ImageFieldImpl <em>Image Field</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see com.bluexml.side.form.impl.ImageFieldImpl
	 * @see com.bluexml.side.form.impl.formPackageImpl#getImageField()
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
	 * The feature id for the '<em><b>Hidden</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int IMAGE_FIELD__HIDDEN = FILE_FIELD__HIDDEN;

	/**
	 * The feature id for the '<em><b>Mandatory</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int IMAGE_FIELD__MANDATORY = FILE_FIELD__MANDATORY;

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
	 * The meta object id for the '{@link com.bluexml.side.form.impl.TimeFieldImpl <em>Time Field</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see com.bluexml.side.form.impl.TimeFieldImpl
	 * @see com.bluexml.side.form.impl.formPackageImpl#getTimeField()
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
	 * The feature id for the '<em><b>Hidden</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TIME_FIELD__HIDDEN = DATE_FIELD__HIDDEN;

	/**
	 * The feature id for the '<em><b>Mandatory</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TIME_FIELD__MANDATORY = DATE_FIELD__MANDATORY;

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
	 * The meta object id for the '{@link com.bluexml.side.form.impl.URLFieldImpl <em>URL Field</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see com.bluexml.side.form.impl.URLFieldImpl
	 * @see com.bluexml.side.form.impl.formPackageImpl#getURLField()
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
	 * The feature id for the '<em><b>Hidden</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int URL_FIELD__HIDDEN = CHAR_FIELD__HIDDEN;

	/**
	 * The feature id for the '<em><b>Mandatory</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int URL_FIELD__MANDATORY = CHAR_FIELD__MANDATORY;

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
	 * The meta object id for the '{@link com.bluexml.side.form.impl.PhoneNumberFieldImpl <em>Phone Number Field</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see com.bluexml.side.form.impl.PhoneNumberFieldImpl
	 * @see com.bluexml.side.form.impl.formPackageImpl#getPhoneNumberField()
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
	 * The feature id for the '<em><b>Hidden</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PHONE_NUMBER_FIELD__HIDDEN = CHAR_FIELD__HIDDEN;

	/**
	 * The feature id for the '<em><b>Mandatory</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PHONE_NUMBER_FIELD__MANDATORY = CHAR_FIELD__MANDATORY;

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
	 * The meta object id for the '{@link com.bluexml.side.form.impl.FormAspectImpl <em>Form Aspect</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see com.bluexml.side.form.impl.FormAspectImpl
	 * @see com.bluexml.side.form.impl.formPackageImpl#getFormAspect()
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
	 * The feature id for the '<em><b>Hidden</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int FORM_ASPECT__HIDDEN = FORM_GROUP__HIDDEN;

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
	 * The meta object id for the '{@link com.bluexml.side.form.impl.FormContainerImpl <em>Form Container</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see com.bluexml.side.form.impl.FormContainerImpl
	 * @see com.bluexml.side.form.impl.formPackageImpl#getFormContainer()
	 * @generated
	 */
	int FORM_CONTAINER = 31;

	/**
	 * The feature id for the '<em><b>Label</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int FORM_CONTAINER__LABEL = FORM_GROUP__LABEL;

	/**
	 * The feature id for the '<em><b>Id</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int FORM_CONTAINER__ID = FORM_GROUP__ID;

	/**
	 * The feature id for the '<em><b>Help text</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int FORM_CONTAINER__HELP_TEXT = FORM_GROUP__HELP_TEXT;

	/**
	 * The feature id for the '<em><b>Ref</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int FORM_CONTAINER__REF = FORM_GROUP__REF;

	/**
	 * The feature id for the '<em><b>Hidden</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int FORM_CONTAINER__HIDDEN = FORM_GROUP__HIDDEN;

	/**
	 * The feature id for the '<em><b>Children</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int FORM_CONTAINER__CHILDREN = FORM_GROUP__CHILDREN;

	/**
	 * The feature id for the '<em><b>Presentation</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int FORM_CONTAINER__PRESENTATION = FORM_GROUP__PRESENTATION;

	/**
	 * The feature id for the '<em><b>Disabled</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int FORM_CONTAINER__DISABLED = FORM_GROUP__DISABLED;

	/**
	 * The number of structural features of the '<em>Form Container</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int FORM_CONTAINER_FEATURE_COUNT = FORM_GROUP_FEATURE_COUNT + 0;

	/**
	 * The meta object id for the '{@link com.bluexml.side.form.impl.FormClassImpl <em>Form Class</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see com.bluexml.side.form.impl.FormClassImpl
	 * @see com.bluexml.side.form.impl.formPackageImpl#getFormClass()
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
	int FORM_CLASS__LABEL = FORM_CONTAINER__LABEL;

	/**
	 * The feature id for the '<em><b>Id</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int FORM_CLASS__ID = FORM_CONTAINER__ID;

	/**
	 * The feature id for the '<em><b>Help text</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int FORM_CLASS__HELP_TEXT = FORM_CONTAINER__HELP_TEXT;

	/**
	 * The feature id for the '<em><b>Ref</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int FORM_CLASS__REF = FORM_CONTAINER__REF;

	/**
	 * The feature id for the '<em><b>Hidden</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int FORM_CLASS__HIDDEN = FORM_CONTAINER__HIDDEN;

	/**
	 * The feature id for the '<em><b>Children</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int FORM_CLASS__CHILDREN = FORM_CONTAINER__CHILDREN;

	/**
	 * The feature id for the '<em><b>Presentation</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int FORM_CLASS__PRESENTATION = FORM_CONTAINER__PRESENTATION;

	/**
	 * The feature id for the '<em><b>Disabled</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int FORM_CLASS__DISABLED = FORM_CONTAINER__DISABLED;

	/**
	 * The feature id for the '<em><b>Real class</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int FORM_CLASS__REAL_CLASS = FORM_CONTAINER_FEATURE_COUNT + 0;

	/**
	 * The feature id for the '<em><b>Association class</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int FORM_CLASS__ASSOCIATION_CLASS = FORM_CONTAINER_FEATURE_COUNT + 1;

	/**
	 * The number of structural features of the '<em>Form Class</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int FORM_CLASS_FEATURE_COUNT = FORM_CONTAINER_FEATURE_COUNT + 2;

	/**
	 * The meta object id for the '{@link com.bluexml.side.form.impl.ReferenceImpl <em>Reference</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see com.bluexml.side.form.impl.ReferenceImpl
	 * @see com.bluexml.side.form.impl.formPackageImpl#getReference()
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
	 * The feature id for the '<em><b>Hidden</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int REFERENCE__HIDDEN = MODEL_CHOICE_FIELD__HIDDEN;

	/**
	 * The feature id for the '<em><b>Mandatory</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int REFERENCE__MANDATORY = MODEL_CHOICE_FIELD__MANDATORY;

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
	 * The meta object id for the '{@link com.bluexml.side.form.impl.FormCollectionImpl <em>Form Collection</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see com.bluexml.side.form.impl.FormCollectionImpl
	 * @see com.bluexml.side.form.impl.formPackageImpl#getFormCollection()
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
	 * The meta object id for the '{@link com.bluexml.side.form.impl.ChoiceFieldImpl <em>Choice Field</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see com.bluexml.side.form.impl.ChoiceFieldImpl
	 * @see com.bluexml.side.form.impl.formPackageImpl#getChoiceField()
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
	 * The feature id for the '<em><b>Hidden</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CHOICE_FIELD__HIDDEN = FIELD__HIDDEN;

	/**
	 * The feature id for the '<em><b>Mandatory</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CHOICE_FIELD__MANDATORY = FIELD__MANDATORY;

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
	 * The meta object id for the '{@link com.bluexml.side.form.impl.RegexFieldImpl <em>Regex Field</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see com.bluexml.side.form.impl.RegexFieldImpl
	 * @see com.bluexml.side.form.impl.formPackageImpl#getRegexField()
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
	 * The feature id for the '<em><b>Hidden</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int REGEX_FIELD__HIDDEN = CHAR_FIELD__HIDDEN;

	/**
	 * The feature id for the '<em><b>Mandatory</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int REGEX_FIELD__MANDATORY = CHAR_FIELD__MANDATORY;

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
	 * The meta object id for the '{@link com.bluexml.side.form.impl.ClassReferenceImpl <em>Class Reference</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see com.bluexml.side.form.impl.ClassReferenceImpl
	 * @see com.bluexml.side.form.impl.formPackageImpl#getClassReference()
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
	 * The meta object id for the '{@link com.bluexml.side.form.impl.PasswordFieldImpl <em>Password Field</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see com.bluexml.side.form.impl.PasswordFieldImpl
	 * @see com.bluexml.side.form.impl.formPackageImpl#getPasswordField()
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
	 * The feature id for the '<em><b>Hidden</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PASSWORD_FIELD__HIDDEN = CHAR_FIELD__HIDDEN;

	/**
	 * The feature id for the '<em><b>Mandatory</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PASSWORD_FIELD__MANDATORY = CHAR_FIELD__MANDATORY;

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
	 * The meta object id for the '{@link com.bluexml.side.form.impl.VirtualFieldImpl <em>Virtual Field</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see com.bluexml.side.form.impl.VirtualFieldImpl
	 * @see com.bluexml.side.form.impl.formPackageImpl#getVirtualField()
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
	 * The feature id for the '<em><b>Hidden</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int VIRTUAL_FIELD__HIDDEN = FIELD__HIDDEN;

	/**
	 * The feature id for the '<em><b>Mandatory</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int VIRTUAL_FIELD__MANDATORY = FIELD__MANDATORY;

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
	 * The meta object id for the '{@link com.bluexml.side.form.impl.ActionFieldImpl <em>Action Field</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see com.bluexml.side.form.impl.ActionFieldImpl
	 * @see com.bluexml.side.form.impl.formPackageImpl#getActionField()
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
	 * The feature id for the '<em><b>Hidden</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ACTION_FIELD__HIDDEN = FIELD__HIDDEN;

	/**
	 * The feature id for the '<em><b>Mandatory</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ACTION_FIELD__MANDATORY = FIELD__MANDATORY;

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
	 * The meta object id for the '{@link com.bluexml.side.form.impl.TextFieldImpl <em>Text Field</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see com.bluexml.side.form.impl.TextFieldImpl
	 * @see com.bluexml.side.form.impl.formPackageImpl#getTextField()
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
	 * The feature id for the '<em><b>Hidden</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TEXT_FIELD__HIDDEN = CHAR_FIELD__HIDDEN;

	/**
	 * The feature id for the '<em><b>Mandatory</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TEXT_FIELD__MANDATORY = CHAR_FIELD__MANDATORY;

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
	 * The meta object id for the '{@link com.bluexml.side.form.impl.WorkflowFormCollectionImpl <em>Workflow Form Collection</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see com.bluexml.side.form.impl.WorkflowFormCollectionImpl
	 * @see com.bluexml.side.form.impl.formPackageImpl#getWorkflowFormCollection()
	 * @generated
	 */
	int WORKFLOW_FORM_COLLECTION = 29;

	/**
	 * The feature id for the '<em><b>Forms</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int WORKFLOW_FORM_COLLECTION__FORMS = FORM_COLLECTION__FORMS;

	/**
	 * The feature id for the '<em><b>Linked process</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int WORKFLOW_FORM_COLLECTION__LINKED_PROCESS = FORM_COLLECTION_FEATURE_COUNT + 0;

	/**
	 * The number of structural features of the '<em>Workflow Form Collection</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int WORKFLOW_FORM_COLLECTION_FEATURE_COUNT = FORM_COLLECTION_FEATURE_COUNT + 1;

	/**
	 * The meta object id for the '{@link com.bluexml.side.form.impl.FormWorkflowImpl <em>Form Workflow</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see com.bluexml.side.form.impl.FormWorkflowImpl
	 * @see com.bluexml.side.form.impl.formPackageImpl#getFormWorkflow()
	 * @generated
	 */
	int FORM_WORKFLOW = 30;

	/**
	 * The feature id for the '<em><b>Label</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int FORM_WORKFLOW__LABEL = FORM_CONTAINER__LABEL;

	/**
	 * The feature id for the '<em><b>Id</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int FORM_WORKFLOW__ID = FORM_CONTAINER__ID;

	/**
	 * The feature id for the '<em><b>Help text</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int FORM_WORKFLOW__HELP_TEXT = FORM_CONTAINER__HELP_TEXT;

	/**
	 * The feature id for the '<em><b>Ref</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int FORM_WORKFLOW__REF = FORM_CONTAINER__REF;

	/**
	 * The feature id for the '<em><b>Hidden</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int FORM_WORKFLOW__HIDDEN = FORM_CONTAINER__HIDDEN;

	/**
	 * The feature id for the '<em><b>Children</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int FORM_WORKFLOW__CHILDREN = FORM_CONTAINER__CHILDREN;

	/**
	 * The feature id for the '<em><b>Presentation</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int FORM_WORKFLOW__PRESENTATION = FORM_CONTAINER__PRESENTATION;

	/**
	 * The feature id for the '<em><b>Disabled</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int FORM_WORKFLOW__DISABLED = FORM_CONTAINER__DISABLED;

	/**
	 * The number of structural features of the '<em>Form Workflow</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int FORM_WORKFLOW_FEATURE_COUNT = FORM_CONTAINER_FEATURE_COUNT + 0;

	/**
	 * The meta object id for the '{@link com.bluexml.side.form.FormGroupPresentationType <em>Form Group Presentation Type</em>}' enum.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see com.bluexml.side.form.FormGroupPresentationType
	 * @see com.bluexml.side.form.impl.formPackageImpl#getFormGroupPresentationType()
	 * @generated
	 */
	int FORM_GROUP_PRESENTATION_TYPE = 32;


	/**
	 * The meta object id for the '{@link com.bluexml.side.form.TextWidgetType <em>Text Widget Type</em>}' enum.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see com.bluexml.side.form.TextWidgetType
	 * @see com.bluexml.side.form.impl.formPackageImpl#getTextWidgetType()
	 * @generated
	 */
	int TEXT_WIDGET_TYPE = 33;

	/**
	 * The meta object id for the '{@link com.bluexml.side.form.ChoiceWidgetType <em>Choice Widget Type</em>}' enum.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see com.bluexml.side.form.ChoiceWidgetType
	 * @see com.bluexml.side.form.impl.formPackageImpl#getChoiceWidgetType()
	 * @generated
	 */
	int CHOICE_WIDGET_TYPE = 34;

	/**
	 * The meta object id for the '{@link com.bluexml.side.form.ReferenceWidgetType <em>Reference Widget Type</em>}' enum.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see com.bluexml.side.form.ReferenceWidgetType
	 * @see com.bluexml.side.form.impl.formPackageImpl#getReferenceWidgetType()
	 * @generated
	 */
	int REFERENCE_WIDGET_TYPE = 35;


	/**
	 * Returns the meta object for class '{@link com.bluexml.side.form.Form <em>Form</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Form</em>'.
	 * @see com.bluexml.side.form.Form
	 * @generated
	 */
	EClass getForm();

	/**
	 * Returns the meta object for the containment reference '{@link com.bluexml.side.form.Form#getRoot <em>Root</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference '<em>Root</em>'.
	 * @see com.bluexml.side.form.Form#getRoot()
	 * @see #getForm()
	 * @generated
	 */
	EReference getForm_Root();

	/**
	 * Returns the meta object for the attribute '{@link com.bluexml.side.form.Form#getName <em>Name</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Name</em>'.
	 * @see com.bluexml.side.form.Form#getName()
	 * @see #getForm()
	 * @generated
	 */
	EAttribute getForm_Name();

	/**
	 * Returns the meta object for class '{@link com.bluexml.side.form.FormElement <em>Form Element</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Form Element</em>'.
	 * @see com.bluexml.side.form.FormElement
	 * @generated
	 */
	EClass getFormElement();

	/**
	 * Returns the meta object for the attribute '{@link com.bluexml.side.form.FormElement#getLabel <em>Label</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Label</em>'.
	 * @see com.bluexml.side.form.FormElement#getLabel()
	 * @see #getFormElement()
	 * @generated
	 */
	EAttribute getFormElement_Label();

	/**
	 * Returns the meta object for the attribute '{@link com.bluexml.side.form.FormElement#getId <em>Id</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Id</em>'.
	 * @see com.bluexml.side.form.FormElement#getId()
	 * @see #getFormElement()
	 * @generated
	 */
	EAttribute getFormElement_Id();

	/**
	 * Returns the meta object for the attribute '{@link com.bluexml.side.form.FormElement#getHelp_text <em>Help text</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Help text</em>'.
	 * @see com.bluexml.side.form.FormElement#getHelp_text()
	 * @see #getFormElement()
	 * @generated
	 */
	EAttribute getFormElement_Help_text();

	/**
	 * Returns the meta object for the reference '{@link com.bluexml.side.form.FormElement#getRef <em>Ref</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference '<em>Ref</em>'.
	 * @see com.bluexml.side.form.FormElement#getRef()
	 * @see #getFormElement()
	 * @generated
	 */
	EReference getFormElement_Ref();

	/**
	 * Returns the meta object for the attribute '{@link com.bluexml.side.form.FormElement#isHidden <em>Hidden</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Hidden</em>'.
	 * @see com.bluexml.side.form.FormElement#isHidden()
	 * @see #getFormElement()
	 * @generated
	 */
	EAttribute getFormElement_Hidden();

	/**
	 * Returns the meta object for class '{@link com.bluexml.side.form.FormGroup <em>Form Group</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Form Group</em>'.
	 * @see com.bluexml.side.form.FormGroup
	 * @generated
	 */
	EClass getFormGroup();

	/**
	 * Returns the meta object for the containment reference list '{@link com.bluexml.side.form.FormGroup#getChildren <em>Children</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference list '<em>Children</em>'.
	 * @see com.bluexml.side.form.FormGroup#getChildren()
	 * @see #getFormGroup()
	 * @generated
	 */
	EReference getFormGroup_Children();

	/**
	 * Returns the meta object for the attribute '{@link com.bluexml.side.form.FormGroup#getPresentation <em>Presentation</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Presentation</em>'.
	 * @see com.bluexml.side.form.FormGroup#getPresentation()
	 * @see #getFormGroup()
	 * @generated
	 */
	EAttribute getFormGroup_Presentation();

	/**
	 * Returns the meta object for the containment reference list '{@link com.bluexml.side.form.FormGroup#getDisabled <em>Disabled</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference list '<em>Disabled</em>'.
	 * @see com.bluexml.side.form.FormGroup#getDisabled()
	 * @see #getFormGroup()
	 * @generated
	 */
	EReference getFormGroup_Disabled();

	/**
	 * Returns the meta object for class '{@link com.bluexml.side.form.Field <em>Field</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Field</em>'.
	 * @see com.bluexml.side.form.Field
	 * @generated
	 */
	EClass getField();

	/**
	 * Returns the meta object for the attribute '{@link com.bluexml.side.form.Field#isMandatory <em>Mandatory</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Mandatory</em>'.
	 * @see com.bluexml.side.form.Field#isMandatory()
	 * @see #getField()
	 * @generated
	 */
	EAttribute getField_Mandatory();

	/**
	 * Returns the meta object for the attribute '{@link com.bluexml.side.form.Field#getError_messages <em>Error messages</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Error messages</em>'.
	 * @see com.bluexml.side.form.Field#getError_messages()
	 * @see #getField()
	 * @generated
	 */
	EAttribute getField_Error_messages();

	/**
	 * Returns the meta object for the attribute '{@link com.bluexml.side.form.Field#getInitial <em>Initial</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Initial</em>'.
	 * @see com.bluexml.side.form.Field#getInitial()
	 * @see #getField()
	 * @generated
	 */
	EAttribute getField_Initial();

	/**
	 * Returns the meta object for the attribute '{@link com.bluexml.side.form.Field#isDisabled <em>Disabled</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Disabled</em>'.
	 * @see com.bluexml.side.form.Field#isDisabled()
	 * @see #getField()
	 * @generated
	 */
	EAttribute getField_Disabled();

	/**
	 * Returns the meta object for the attribute '{@link com.bluexml.side.form.Field#getFieldSize <em>Field Size</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Field Size</em>'.
	 * @see com.bluexml.side.form.Field#getFieldSize()
	 * @see #getField()
	 * @generated
	 */
	EAttribute getField_FieldSize();

	/**
	 * Returns the meta object for class '{@link com.bluexml.side.form.BooleanField <em>Boolean Field</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Boolean Field</em>'.
	 * @see com.bluexml.side.form.BooleanField
	 * @generated
	 */
	EClass getBooleanField();

	/**
	 * Returns the meta object for class '{@link com.bluexml.side.form.CharField <em>Char Field</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Char Field</em>'.
	 * @see com.bluexml.side.form.CharField
	 * @generated
	 */
	EClass getCharField();

	/**
	 * Returns the meta object for the attribute '{@link com.bluexml.side.form.CharField#getMin_length <em>Min length</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Min length</em>'.
	 * @see com.bluexml.side.form.CharField#getMin_length()
	 * @see #getCharField()
	 * @generated
	 */
	EAttribute getCharField_Min_length();

	/**
	 * Returns the meta object for the attribute '{@link com.bluexml.side.form.CharField#getMax_length <em>Max length</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Max length</em>'.
	 * @see com.bluexml.side.form.CharField#getMax_length()
	 * @see #getCharField()
	 * @generated
	 */
	EAttribute getCharField_Max_length();

	/**
	 * Returns the meta object for class '{@link com.bluexml.side.form.DateField <em>Date Field</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Date Field</em>'.
	 * @see com.bluexml.side.form.DateField
	 * @generated
	 */
	EClass getDateField();

	/**
	 * Returns the meta object for the attribute list '{@link com.bluexml.side.form.DateField#getInput_formats <em>Input formats</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute list '<em>Input formats</em>'.
	 * @see com.bluexml.side.form.DateField#getInput_formats()
	 * @see #getDateField()
	 * @generated
	 */
	EAttribute getDateField_Input_formats();

	/**
	 * Returns the meta object for the attribute '{@link com.bluexml.side.form.DateField#getMin_date <em>Min date</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Min date</em>'.
	 * @see com.bluexml.side.form.DateField#getMin_date()
	 * @see #getDateField()
	 * @generated
	 */
	EAttribute getDateField_Min_date();

	/**
	 * Returns the meta object for the attribute '{@link com.bluexml.side.form.DateField#getMax_date <em>Max date</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Max date</em>'.
	 * @see com.bluexml.side.form.DateField#getMax_date()
	 * @see #getDateField()
	 * @generated
	 */
	EAttribute getDateField_Max_date();

	/**
	 * Returns the meta object for class '{@link com.bluexml.side.form.DateTimeField <em>Date Time Field</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Date Time Field</em>'.
	 * @see com.bluexml.side.form.DateTimeField
	 * @generated
	 */
	EClass getDateTimeField();

	/**
	 * Returns the meta object for class '{@link com.bluexml.side.form.DecimalField <em>Decimal Field</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Decimal Field</em>'.
	 * @see com.bluexml.side.form.DecimalField
	 * @generated
	 */
	EClass getDecimalField();

	/**
	 * Returns the meta object for the attribute '{@link com.bluexml.side.form.DecimalField#getMin_value <em>Min value</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Min value</em>'.
	 * @see com.bluexml.side.form.DecimalField#getMin_value()
	 * @see #getDecimalField()
	 * @generated
	 */
	EAttribute getDecimalField_Min_value();

	/**
	 * Returns the meta object for the attribute '{@link com.bluexml.side.form.DecimalField#getMax_value <em>Max value</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Max value</em>'.
	 * @see com.bluexml.side.form.DecimalField#getMax_value()
	 * @see #getDecimalField()
	 * @generated
	 */
	EAttribute getDecimalField_Max_value();

	/**
	 * Returns the meta object for the attribute '{@link com.bluexml.side.form.DecimalField#getMax_digits <em>Max digits</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Max digits</em>'.
	 * @see com.bluexml.side.form.DecimalField#getMax_digits()
	 * @see #getDecimalField()
	 * @generated
	 */
	EAttribute getDecimalField_Max_digits();

	/**
	 * Returns the meta object for the attribute '{@link com.bluexml.side.form.DecimalField#getDecimal_places <em>Decimal places</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Decimal places</em>'.
	 * @see com.bluexml.side.form.DecimalField#getDecimal_places()
	 * @see #getDecimalField()
	 * @generated
	 */
	EAttribute getDecimalField_Decimal_places();

	/**
	 * Returns the meta object for class '{@link com.bluexml.side.form.FloatField <em>Float Field</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Float Field</em>'.
	 * @see com.bluexml.side.form.FloatField
	 * @generated
	 */
	EClass getFloatField();

	/**
	 * Returns the meta object for the attribute '{@link com.bluexml.side.form.FloatField#getMin_value <em>Min value</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Min value</em>'.
	 * @see com.bluexml.side.form.FloatField#getMin_value()
	 * @see #getFloatField()
	 * @generated
	 */
	EAttribute getFloatField_Min_value();

	/**
	 * Returns the meta object for the attribute '{@link com.bluexml.side.form.FloatField#getMax_value <em>Max value</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Max value</em>'.
	 * @see com.bluexml.side.form.FloatField#getMax_value()
	 * @see #getFloatField()
	 * @generated
	 */
	EAttribute getFloatField_Max_value();

	/**
	 * Returns the meta object for class '{@link com.bluexml.side.form.IntegerField <em>Integer Field</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Integer Field</em>'.
	 * @see com.bluexml.side.form.IntegerField
	 * @generated
	 */
	EClass getIntegerField();

	/**
	 * Returns the meta object for the attribute '{@link com.bluexml.side.form.IntegerField#getMin_value <em>Min value</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Min value</em>'.
	 * @see com.bluexml.side.form.IntegerField#getMin_value()
	 * @see #getIntegerField()
	 * @generated
	 */
	EAttribute getIntegerField_Min_value();

	/**
	 * Returns the meta object for the attribute '{@link com.bluexml.side.form.IntegerField#getMax_value <em>Max value</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Max value</em>'.
	 * @see com.bluexml.side.form.IntegerField#getMax_value()
	 * @see #getIntegerField()
	 * @generated
	 */
	EAttribute getIntegerField_Max_value();

	/**
	 * Returns the meta object for class '{@link com.bluexml.side.form.ModelChoiceField <em>Model Choice Field</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Model Choice Field</em>'.
	 * @see com.bluexml.side.form.ModelChoiceField
	 * @generated
	 */
	EClass getModelChoiceField();

	/**
	 * Returns the meta object for the attribute '{@link com.bluexml.side.form.ModelChoiceField#getMin_bound <em>Min bound</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Min bound</em>'.
	 * @see com.bluexml.side.form.ModelChoiceField#getMin_bound()
	 * @see #getModelChoiceField()
	 * @generated
	 */
	EAttribute getModelChoiceField_Min_bound();

	/**
	 * Returns the meta object for the attribute '{@link com.bluexml.side.form.ModelChoiceField#getMax_bound <em>Max bound</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Max bound</em>'.
	 * @see com.bluexml.side.form.ModelChoiceField#getMax_bound()
	 * @see #getModelChoiceField()
	 * @generated
	 */
	EAttribute getModelChoiceField_Max_bound();

	/**
	 * Returns the meta object for the reference list '{@link com.bluexml.side.form.ModelChoiceField#getTarget <em>Target</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference list '<em>Target</em>'.
	 * @see com.bluexml.side.form.ModelChoiceField#getTarget()
	 * @see #getModelChoiceField()
	 * @generated
	 */
	EReference getModelChoiceField_Target();

	/**
	 * Returns the meta object for the reference list '{@link com.bluexml.side.form.ModelChoiceField#getAssociation_formClass <em>Association form Class</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference list '<em>Association form Class</em>'.
	 * @see com.bluexml.side.form.ModelChoiceField#getAssociation_formClass()
	 * @see #getModelChoiceField()
	 * @generated
	 */
	EReference getModelChoiceField_Association_formClass();

	/**
	 * Returns the meta object for the attribute '{@link com.bluexml.side.form.ModelChoiceField#getWidget <em>Widget</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Widget</em>'.
	 * @see com.bluexml.side.form.ModelChoiceField#getWidget()
	 * @see #getModelChoiceField()
	 * @generated
	 */
	EAttribute getModelChoiceField_Widget();

	/**
	 * Returns the meta object for the attribute '{@link com.bluexml.side.form.ModelChoiceField#isShow_actions <em>Show actions</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Show actions</em>'.
	 * @see com.bluexml.side.form.ModelChoiceField#isShow_actions()
	 * @see #getModelChoiceField()
	 * @generated
	 */
	EAttribute getModelChoiceField_Show_actions();

	/**
	 * Returns the meta object for class '{@link com.bluexml.side.form.EmailField <em>Email Field</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Email Field</em>'.
	 * @see com.bluexml.side.form.EmailField
	 * @generated
	 */
	EClass getEmailField();

	/**
	 * Returns the meta object for class '{@link com.bluexml.side.form.FileField <em>File Field</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>File Field</em>'.
	 * @see com.bluexml.side.form.FileField
	 * @generated
	 */
	EClass getFileField();

	/**
	 * Returns the meta object for class '{@link com.bluexml.side.form.ImageField <em>Image Field</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Image Field</em>'.
	 * @see com.bluexml.side.form.ImageField
	 * @generated
	 */
	EClass getImageField();

	/**
	 * Returns the meta object for class '{@link com.bluexml.side.form.TimeField <em>Time Field</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Time Field</em>'.
	 * @see com.bluexml.side.form.TimeField
	 * @generated
	 */
	EClass getTimeField();

	/**
	 * Returns the meta object for class '{@link com.bluexml.side.form.URLField <em>URL Field</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>URL Field</em>'.
	 * @see com.bluexml.side.form.URLField
	 * @generated
	 */
	EClass getURLField();

	/**
	 * Returns the meta object for the attribute '{@link com.bluexml.side.form.URLField#isVerify_exists <em>Verify exists</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Verify exists</em>'.
	 * @see com.bluexml.side.form.URLField#isVerify_exists()
	 * @see #getURLField()
	 * @generated
	 */
	EAttribute getURLField_Verify_exists();

	/**
	 * Returns the meta object for class '{@link com.bluexml.side.form.PhoneNumberField <em>Phone Number Field</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Phone Number Field</em>'.
	 * @see com.bluexml.side.form.PhoneNumberField
	 * @generated
	 */
	EClass getPhoneNumberField();

	/**
	 * Returns the meta object for the attribute '{@link com.bluexml.side.form.PhoneNumberField#getInput_formats <em>Input formats</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Input formats</em>'.
	 * @see com.bluexml.side.form.PhoneNumberField#getInput_formats()
	 * @see #getPhoneNumberField()
	 * @generated
	 */
	EAttribute getPhoneNumberField_Input_formats();

	/**
	 * Returns the meta object for class '{@link com.bluexml.side.form.FormAspect <em>Form Aspect</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Form Aspect</em>'.
	 * @see com.bluexml.side.form.FormAspect
	 * @generated
	 */
	EClass getFormAspect();

	/**
	 * Returns the meta object for class '{@link com.bluexml.side.form.FormClass <em>Form Class</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Form Class</em>'.
	 * @see com.bluexml.side.form.FormClass
	 * @generated
	 */
	EClass getFormClass();

	/**
	 * Returns the meta object for class '{@link com.bluexml.side.form.Reference <em>Reference</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Reference</em>'.
	 * @see com.bluexml.side.form.Reference
	 * @generated
	 */
	EClass getReference();

	/**
	 * Returns the meta object for class '{@link com.bluexml.side.form.FormCollection <em>Form Collection</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Form Collection</em>'.
	 * @see com.bluexml.side.form.FormCollection
	 * @generated
	 */
	EClass getFormCollection();

	/**
	 * Returns the meta object for the containment reference list '{@link com.bluexml.side.form.FormCollection#getForms <em>Forms</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference list '<em>Forms</em>'.
	 * @see com.bluexml.side.form.FormCollection#getForms()
	 * @see #getFormCollection()
	 * @generated
	 */
	EReference getFormCollection_Forms();

	/**
	 * Returns the meta object for class '{@link com.bluexml.side.form.ChoiceField <em>Choice Field</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Choice Field</em>'.
	 * @see com.bluexml.side.form.ChoiceField
	 * @generated
	 */
	EClass getChoiceField();

	/**
	 * Returns the meta object for the attribute '{@link com.bluexml.side.form.ChoiceField#getMin_bound <em>Min bound</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Min bound</em>'.
	 * @see com.bluexml.side.form.ChoiceField#getMin_bound()
	 * @see #getChoiceField()
	 * @generated
	 */
	EAttribute getChoiceField_Min_bound();

	/**
	 * Returns the meta object for the attribute '{@link com.bluexml.side.form.ChoiceField#getMax_bound <em>Max bound</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Max bound</em>'.
	 * @see com.bluexml.side.form.ChoiceField#getMax_bound()
	 * @see #getChoiceField()
	 * @generated
	 */
	EAttribute getChoiceField_Max_bound();

	/**
	 * Returns the meta object for the attribute '{@link com.bluexml.side.form.ChoiceField#getWidget <em>Widget</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Widget</em>'.
	 * @see com.bluexml.side.form.ChoiceField#getWidget()
	 * @see #getChoiceField()
	 * @generated
	 */
	EAttribute getChoiceField_Widget();

	/**
	 * Returns the meta object for the attribute '{@link com.bluexml.side.form.ChoiceField#isMultiple <em>Multiple</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Multiple</em>'.
	 * @see com.bluexml.side.form.ChoiceField#isMultiple()
	 * @see #getChoiceField()
	 * @generated
	 */
	EAttribute getChoiceField_Multiple();

	/**
	 * Returns the meta object for the attribute '{@link com.bluexml.side.form.ChoiceField#getFilterParent <em>Filter Parent</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Filter Parent</em>'.
	 * @see com.bluexml.side.form.ChoiceField#getFilterParent()
	 * @see #getChoiceField()
	 * @generated
	 */
	EAttribute getChoiceField_FilterParent();

	/**
	 * Returns the meta object for the attribute '{@link com.bluexml.side.form.ChoiceField#getFilterData <em>Filter Data</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Filter Data</em>'.
	 * @see com.bluexml.side.form.ChoiceField#getFilterData()
	 * @see #getChoiceField()
	 * @generated
	 */
	EAttribute getChoiceField_FilterData();

	/**
	 * Returns the meta object for class '{@link com.bluexml.side.form.RegexField <em>Regex Field</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Regex Field</em>'.
	 * @see com.bluexml.side.form.RegexField
	 * @generated
	 */
	EClass getRegexField();

	/**
	 * Returns the meta object for the attribute '{@link com.bluexml.side.form.RegexField#getRegex <em>Regex</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Regex</em>'.
	 * @see com.bluexml.side.form.RegexField#getRegex()
	 * @see #getRegexField()
	 * @generated
	 */
	EAttribute getRegexField_Regex();

	/**
	 * Returns the meta object for class '{@link com.bluexml.side.form.ClassReference <em>Class Reference</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Class Reference</em>'.
	 * @see com.bluexml.side.form.ClassReference
	 * @generated
	 */
	EClass getClassReference();

	/**
	 * Returns the meta object for the reference '{@link com.bluexml.side.form.ClassReference#getReal_class <em>Real class</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference '<em>Real class</em>'.
	 * @see com.bluexml.side.form.ClassReference#getReal_class()
	 * @see #getClassReference()
	 * @generated
	 */
	EReference getClassReference_Real_class();

	/**
	 * Returns the meta object for the reference '{@link com.bluexml.side.form.ClassReference#getAssociation_class <em>Association class</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference '<em>Association class</em>'.
	 * @see com.bluexml.side.form.ClassReference#getAssociation_class()
	 * @see #getClassReference()
	 * @generated
	 */
	EReference getClassReference_Association_class();

	/**
	 * Returns the meta object for class '{@link com.bluexml.side.form.PasswordField <em>Password Field</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Password Field</em>'.
	 * @see com.bluexml.side.form.PasswordField
	 * @generated
	 */
	EClass getPasswordField();

	/**
	 * Returns the meta object for class '{@link com.bluexml.side.form.VirtualField <em>Virtual Field</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Virtual Field</em>'.
	 * @see com.bluexml.side.form.VirtualField
	 * @generated
	 */
	EClass getVirtualField();

	/**
	 * Returns the meta object for the reference '{@link com.bluexml.side.form.VirtualField#getLink <em>Link</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference '<em>Link</em>'.
	 * @see com.bluexml.side.form.VirtualField#getLink()
	 * @see #getVirtualField()
	 * @generated
	 */
	EReference getVirtualField_Link();

	/**
	 * Returns the meta object for class '{@link com.bluexml.side.form.ActionField <em>Action Field</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Action Field</em>'.
	 * @see com.bluexml.side.form.ActionField
	 * @generated
	 */
	EClass getActionField();

	/**
	 * Returns the meta object for class '{@link com.bluexml.side.form.TextField <em>Text Field</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Text Field</em>'.
	 * @see com.bluexml.side.form.TextField
	 * @generated
	 */
	EClass getTextField();

	/**
	 * Returns the meta object for the attribute '{@link com.bluexml.side.form.TextField#getWidget <em>Widget</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Widget</em>'.
	 * @see com.bluexml.side.form.TextField#getWidget()
	 * @see #getTextField()
	 * @generated
	 */
	EAttribute getTextField_Widget();

	/**
	 * Returns the meta object for class '{@link com.bluexml.side.form.WorkflowFormCollection <em>Workflow Form Collection</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Workflow Form Collection</em>'.
	 * @see com.bluexml.side.form.WorkflowFormCollection
	 * @generated
	 */
	EClass getWorkflowFormCollection();

	/**
	 * Returns the meta object for the reference '{@link com.bluexml.side.form.WorkflowFormCollection#getLinked_process <em>Linked process</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference '<em>Linked process</em>'.
	 * @see com.bluexml.side.form.WorkflowFormCollection#getLinked_process()
	 * @see #getWorkflowFormCollection()
	 * @generated
	 */
	EReference getWorkflowFormCollection_Linked_process();

	/**
	 * Returns the meta object for class '{@link com.bluexml.side.form.FormWorkflow <em>Form Workflow</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Form Workflow</em>'.
	 * @see com.bluexml.side.form.FormWorkflow
	 * @generated
	 */
	EClass getFormWorkflow();

	/**
	 * Returns the meta object for class '{@link com.bluexml.side.form.FormContainer <em>Form Container</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Form Container</em>'.
	 * @see com.bluexml.side.form.FormContainer
	 * @generated
	 */
	EClass getFormContainer();

	/**
	 * Returns the meta object for enum '{@link com.bluexml.side.form.FormGroupPresentationType <em>Form Group Presentation Type</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for enum '<em>Form Group Presentation Type</em>'.
	 * @see com.bluexml.side.form.FormGroupPresentationType
	 * @generated
	 */
	EEnum getFormGroupPresentationType();

	/**
	 * Returns the meta object for enum '{@link com.bluexml.side.form.TextWidgetType <em>Text Widget Type</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for enum '<em>Text Widget Type</em>'.
	 * @see com.bluexml.side.form.TextWidgetType
	 * @generated
	 */
	EEnum getTextWidgetType();

	/**
	 * Returns the meta object for enum '{@link com.bluexml.side.form.ChoiceWidgetType <em>Choice Widget Type</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for enum '<em>Choice Widget Type</em>'.
	 * @see com.bluexml.side.form.ChoiceWidgetType
	 * @generated
	 */
	EEnum getChoiceWidgetType();

	/**
	 * Returns the meta object for enum '{@link com.bluexml.side.form.ReferenceWidgetType <em>Reference Widget Type</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for enum '<em>Reference Widget Type</em>'.
	 * @see com.bluexml.side.form.ReferenceWidgetType
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
	formFactory getformFactory();

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
		 * The meta object literal for the '{@link com.bluexml.side.form.impl.FormImpl <em>Form</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see com.bluexml.side.form.impl.FormImpl
		 * @see com.bluexml.side.form.impl.formPackageImpl#getForm()
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
		 * The meta object literal for the '{@link com.bluexml.side.form.impl.FormElementImpl <em>Form Element</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see com.bluexml.side.form.impl.FormElementImpl
		 * @see com.bluexml.side.form.impl.formPackageImpl#getFormElement()
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
		 * The meta object literal for the '<em><b>Hidden</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute FORM_ELEMENT__HIDDEN = eINSTANCE.getFormElement_Hidden();

		/**
		 * The meta object literal for the '{@link com.bluexml.side.form.impl.FormGroupImpl <em>Form Group</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see com.bluexml.side.form.impl.FormGroupImpl
		 * @see com.bluexml.side.form.impl.formPackageImpl#getFormGroup()
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
		 * The meta object literal for the '{@link com.bluexml.side.form.impl.FieldImpl <em>Field</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see com.bluexml.side.form.impl.FieldImpl
		 * @see com.bluexml.side.form.impl.formPackageImpl#getField()
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
		 * The meta object literal for the '{@link com.bluexml.side.form.impl.BooleanFieldImpl <em>Boolean Field</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see com.bluexml.side.form.impl.BooleanFieldImpl
		 * @see com.bluexml.side.form.impl.formPackageImpl#getBooleanField()
		 * @generated
		 */
		EClass BOOLEAN_FIELD = eINSTANCE.getBooleanField();

		/**
		 * The meta object literal for the '{@link com.bluexml.side.form.impl.CharFieldImpl <em>Char Field</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see com.bluexml.side.form.impl.CharFieldImpl
		 * @see com.bluexml.side.form.impl.formPackageImpl#getCharField()
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
		 * The meta object literal for the '{@link com.bluexml.side.form.impl.DateFieldImpl <em>Date Field</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see com.bluexml.side.form.impl.DateFieldImpl
		 * @see com.bluexml.side.form.impl.formPackageImpl#getDateField()
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
		 * The meta object literal for the '{@link com.bluexml.side.form.impl.DateTimeFieldImpl <em>Date Time Field</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see com.bluexml.side.form.impl.DateTimeFieldImpl
		 * @see com.bluexml.side.form.impl.formPackageImpl#getDateTimeField()
		 * @generated
		 */
		EClass DATE_TIME_FIELD = eINSTANCE.getDateTimeField();

		/**
		 * The meta object literal for the '{@link com.bluexml.side.form.impl.DecimalFieldImpl <em>Decimal Field</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see com.bluexml.side.form.impl.DecimalFieldImpl
		 * @see com.bluexml.side.form.impl.formPackageImpl#getDecimalField()
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
		 * The meta object literal for the '{@link com.bluexml.side.form.impl.FloatFieldImpl <em>Float Field</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see com.bluexml.side.form.impl.FloatFieldImpl
		 * @see com.bluexml.side.form.impl.formPackageImpl#getFloatField()
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
		 * The meta object literal for the '{@link com.bluexml.side.form.impl.IntegerFieldImpl <em>Integer Field</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see com.bluexml.side.form.impl.IntegerFieldImpl
		 * @see com.bluexml.side.form.impl.formPackageImpl#getIntegerField()
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
		 * The meta object literal for the '{@link com.bluexml.side.form.impl.ModelChoiceFieldImpl <em>Model Choice Field</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see com.bluexml.side.form.impl.ModelChoiceFieldImpl
		 * @see com.bluexml.side.form.impl.formPackageImpl#getModelChoiceField()
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
		 * The meta object literal for the '{@link com.bluexml.side.form.impl.EmailFieldImpl <em>Email Field</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see com.bluexml.side.form.impl.EmailFieldImpl
		 * @see com.bluexml.side.form.impl.formPackageImpl#getEmailField()
		 * @generated
		 */
		EClass EMAIL_FIELD = eINSTANCE.getEmailField();

		/**
		 * The meta object literal for the '{@link com.bluexml.side.form.impl.FileFieldImpl <em>File Field</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see com.bluexml.side.form.impl.FileFieldImpl
		 * @see com.bluexml.side.form.impl.formPackageImpl#getFileField()
		 * @generated
		 */
		EClass FILE_FIELD = eINSTANCE.getFileField();

		/**
		 * The meta object literal for the '{@link com.bluexml.side.form.impl.ImageFieldImpl <em>Image Field</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see com.bluexml.side.form.impl.ImageFieldImpl
		 * @see com.bluexml.side.form.impl.formPackageImpl#getImageField()
		 * @generated
		 */
		EClass IMAGE_FIELD = eINSTANCE.getImageField();

		/**
		 * The meta object literal for the '{@link com.bluexml.side.form.impl.TimeFieldImpl <em>Time Field</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see com.bluexml.side.form.impl.TimeFieldImpl
		 * @see com.bluexml.side.form.impl.formPackageImpl#getTimeField()
		 * @generated
		 */
		EClass TIME_FIELD = eINSTANCE.getTimeField();

		/**
		 * The meta object literal for the '{@link com.bluexml.side.form.impl.URLFieldImpl <em>URL Field</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see com.bluexml.side.form.impl.URLFieldImpl
		 * @see com.bluexml.side.form.impl.formPackageImpl#getURLField()
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
		 * The meta object literal for the '{@link com.bluexml.side.form.impl.PhoneNumberFieldImpl <em>Phone Number Field</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see com.bluexml.side.form.impl.PhoneNumberFieldImpl
		 * @see com.bluexml.side.form.impl.formPackageImpl#getPhoneNumberField()
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
		 * The meta object literal for the '{@link com.bluexml.side.form.impl.FormAspectImpl <em>Form Aspect</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see com.bluexml.side.form.impl.FormAspectImpl
		 * @see com.bluexml.side.form.impl.formPackageImpl#getFormAspect()
		 * @generated
		 */
		EClass FORM_ASPECT = eINSTANCE.getFormAspect();

		/**
		 * The meta object literal for the '{@link com.bluexml.side.form.impl.FormClassImpl <em>Form Class</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see com.bluexml.side.form.impl.FormClassImpl
		 * @see com.bluexml.side.form.impl.formPackageImpl#getFormClass()
		 * @generated
		 */
		EClass FORM_CLASS = eINSTANCE.getFormClass();

		/**
		 * The meta object literal for the '{@link com.bluexml.side.form.impl.ReferenceImpl <em>Reference</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see com.bluexml.side.form.impl.ReferenceImpl
		 * @see com.bluexml.side.form.impl.formPackageImpl#getReference()
		 * @generated
		 */
		EClass REFERENCE = eINSTANCE.getReference();

		/**
		 * The meta object literal for the '{@link com.bluexml.side.form.impl.FormCollectionImpl <em>Form Collection</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see com.bluexml.side.form.impl.FormCollectionImpl
		 * @see com.bluexml.side.form.impl.formPackageImpl#getFormCollection()
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
		 * The meta object literal for the '{@link com.bluexml.side.form.impl.ChoiceFieldImpl <em>Choice Field</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see com.bluexml.side.form.impl.ChoiceFieldImpl
		 * @see com.bluexml.side.form.impl.formPackageImpl#getChoiceField()
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
		 * The meta object literal for the '{@link com.bluexml.side.form.impl.RegexFieldImpl <em>Regex Field</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see com.bluexml.side.form.impl.RegexFieldImpl
		 * @see com.bluexml.side.form.impl.formPackageImpl#getRegexField()
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
		 * The meta object literal for the '{@link com.bluexml.side.form.impl.ClassReferenceImpl <em>Class Reference</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see com.bluexml.side.form.impl.ClassReferenceImpl
		 * @see com.bluexml.side.form.impl.formPackageImpl#getClassReference()
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
		 * The meta object literal for the '{@link com.bluexml.side.form.impl.PasswordFieldImpl <em>Password Field</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see com.bluexml.side.form.impl.PasswordFieldImpl
		 * @see com.bluexml.side.form.impl.formPackageImpl#getPasswordField()
		 * @generated
		 */
		EClass PASSWORD_FIELD = eINSTANCE.getPasswordField();

		/**
		 * The meta object literal for the '{@link com.bluexml.side.form.impl.VirtualFieldImpl <em>Virtual Field</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see com.bluexml.side.form.impl.VirtualFieldImpl
		 * @see com.bluexml.side.form.impl.formPackageImpl#getVirtualField()
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
		 * The meta object literal for the '{@link com.bluexml.side.form.impl.ActionFieldImpl <em>Action Field</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see com.bluexml.side.form.impl.ActionFieldImpl
		 * @see com.bluexml.side.form.impl.formPackageImpl#getActionField()
		 * @generated
		 */
		EClass ACTION_FIELD = eINSTANCE.getActionField();

		/**
		 * The meta object literal for the '{@link com.bluexml.side.form.impl.TextFieldImpl <em>Text Field</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see com.bluexml.side.form.impl.TextFieldImpl
		 * @see com.bluexml.side.form.impl.formPackageImpl#getTextField()
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
		 * The meta object literal for the '{@link com.bluexml.side.form.impl.WorkflowFormCollectionImpl <em>Workflow Form Collection</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see com.bluexml.side.form.impl.WorkflowFormCollectionImpl
		 * @see com.bluexml.side.form.impl.formPackageImpl#getWorkflowFormCollection()
		 * @generated
		 */
		EClass WORKFLOW_FORM_COLLECTION = eINSTANCE.getWorkflowFormCollection();

		/**
		 * The meta object literal for the '<em><b>Linked process</b></em>' reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference WORKFLOW_FORM_COLLECTION__LINKED_PROCESS = eINSTANCE.getWorkflowFormCollection_Linked_process();

		/**
		 * The meta object literal for the '{@link com.bluexml.side.form.impl.FormWorkflowImpl <em>Form Workflow</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see com.bluexml.side.form.impl.FormWorkflowImpl
		 * @see com.bluexml.side.form.impl.formPackageImpl#getFormWorkflow()
		 * @generated
		 */
		EClass FORM_WORKFLOW = eINSTANCE.getFormWorkflow();

		/**
		 * The meta object literal for the '{@link com.bluexml.side.form.impl.FormContainerImpl <em>Form Container</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see com.bluexml.side.form.impl.FormContainerImpl
		 * @see com.bluexml.side.form.impl.formPackageImpl#getFormContainer()
		 * @generated
		 */
		EClass FORM_CONTAINER = eINSTANCE.getFormContainer();

		/**
		 * The meta object literal for the '{@link com.bluexml.side.form.FormGroupPresentationType <em>Form Group Presentation Type</em>}' enum.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see com.bluexml.side.form.FormGroupPresentationType
		 * @see com.bluexml.side.form.impl.formPackageImpl#getFormGroupPresentationType()
		 * @generated
		 */
		EEnum FORM_GROUP_PRESENTATION_TYPE = eINSTANCE.getFormGroupPresentationType();

		/**
		 * The meta object literal for the '{@link com.bluexml.side.form.TextWidgetType <em>Text Widget Type</em>}' enum.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see com.bluexml.side.form.TextWidgetType
		 * @see com.bluexml.side.form.impl.formPackageImpl#getTextWidgetType()
		 * @generated
		 */
		EEnum TEXT_WIDGET_TYPE = eINSTANCE.getTextWidgetType();

		/**
		 * The meta object literal for the '{@link com.bluexml.side.form.ChoiceWidgetType <em>Choice Widget Type</em>}' enum.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see com.bluexml.side.form.ChoiceWidgetType
		 * @see com.bluexml.side.form.impl.formPackageImpl#getChoiceWidgetType()
		 * @generated
		 */
		EEnum CHOICE_WIDGET_TYPE = eINSTANCE.getChoiceWidgetType();

		/**
		 * The meta object literal for the '{@link com.bluexml.side.form.ReferenceWidgetType <em>Reference Widget Type</em>}' enum.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see com.bluexml.side.form.ReferenceWidgetType
		 * @see com.bluexml.side.form.impl.formPackageImpl#getReferenceWidgetType()
		 * @generated
		 */
		EEnum REFERENCE_WIDGET_TYPE = eINSTANCE.getReferenceWidgetType();

	}

} //KerblueFormsPackage
