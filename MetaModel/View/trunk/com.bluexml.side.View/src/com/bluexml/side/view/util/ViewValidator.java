/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package com.bluexml.side.view.util;


import java.util.Map;

import org.eclipse.emf.common.util.Diagnostic;
import org.eclipse.emf.common.util.DiagnosticChain;
import org.eclipse.emf.common.util.ResourceLocator;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.util.EObjectValidator;
import org.eclipse.ocl.ecore.OCL;

import com.bluexml.side.common.util.CommonValidator;
import com.bluexml.side.util.metaModel.validate.OCLextension.KerblueOCL;
import com.bluexml.side.view.AbstractDataTable;
import com.bluexml.side.view.AbstractView;
import com.bluexml.side.view.ActionField;
import com.bluexml.side.view.BooleanField;
import com.bluexml.side.view.Col;
import com.bluexml.side.view.DataList;
import com.bluexml.side.view.DataTable;
import com.bluexml.side.view.DataTableElement;
import com.bluexml.side.view.DateField;
import com.bluexml.side.view.DateTimeField;
import com.bluexml.side.view.Editable;
import com.bluexml.side.view.EmailField;
import com.bluexml.side.view.FacetDisplayType;
import com.bluexml.side.view.FacetMap;
import com.bluexml.side.view.Field;
import com.bluexml.side.view.FieldContainer;
import com.bluexml.side.view.FieldElement;
import com.bluexml.side.view.FieldGroup;
import com.bluexml.side.view.FileField;
import com.bluexml.side.view.Filterable;
import com.bluexml.side.view.Filtering;
import com.bluexml.side.view.FloatField;
import com.bluexml.side.view.Halign;
import com.bluexml.side.view.HtmlField;
import com.bluexml.side.view.ImageField;
import com.bluexml.side.view.IntegerField;
import com.bluexml.side.view.LoadingType;
import com.bluexml.side.view.Movable;
import com.bluexml.side.view.Paginable;
import com.bluexml.side.view.PaginationStyle;
import com.bluexml.side.view.Paging;
import com.bluexml.side.view.PasswordField;
import com.bluexml.side.view.PhoneNumberField;
import com.bluexml.side.view.SelectField;
import com.bluexml.side.view.SelectWidgetType;
import com.bluexml.side.view.SortOrder;
import com.bluexml.side.view.Sortable;
import com.bluexml.side.view.Sorting;
import com.bluexml.side.view.Stylable;
import com.bluexml.side.view.Styling;
import com.bluexml.side.view.TextField;
import com.bluexml.side.view.TimeField;
import com.bluexml.side.view.Tree;
import com.bluexml.side.view.URLField;
import com.bluexml.side.view.ViewCollection;
import com.bluexml.side.view.ViewPackage;
import com.bluexml.side.view.WidgetTextType;

/**
 * <!-- begin-user-doc -->
 * The <b>Validator</b> for the model.
 * <!-- end-user-doc -->
 * @see com.bluexml.side.view.ViewPackage
 * @generated
 */
public class ViewValidator extends EObjectValidator {
	/**
	 * The cached model package
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public static final ViewValidator INSTANCE = new ViewValidator();

	/**
	 * A constant for the {@link org.eclipse.emf.common.util.Diagnostic#getSource() source} of diagnostic {@link org.eclipse.emf.common.util.Diagnostic#getCode() codes} from this package.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.eclipse.emf.common.util.Diagnostic#getSource()
	 * @see org.eclipse.emf.common.util.Diagnostic#getCode()
	 * @generated
	 */
	public static final String DIAGNOSTIC_SOURCE = "com.bluexml.side.view";

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
	 * The cached base package validator.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected CommonValidator commonValidator;

	private static final String OCL_ANNOTATION_SOURCE = "http://www.bluexml.com/OCL";
	
	private static final OCL OCL_ENV = KerblueOCL.newInstance();
	/**
	 * Creates an instance of the switch.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public ViewValidator() {
		super();
		commonValidator = CommonValidator.INSTANCE;
	}

	/**
	 * Returns the package of this validator switch.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EPackage getEPackage() {
	  return ViewPackage.eINSTANCE;
	}

	/**
	 * Calls <code>validateXXX</code> for the corresponding classifier of the model.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected boolean validate(int classifierID, Object value, DiagnosticChain diagnostics, Map<Object, Object> context) {
		switch (classifierID) {
			case ViewPackage.VIEW_COLLECTION:
				return validateViewCollection((ViewCollection)value, diagnostics, context);
			case ViewPackage.FIELD_CONTAINER:
				return validateFieldContainer((FieldContainer)value, diagnostics, context);
			case ViewPackage.FIELD_ELEMENT:
				return validateFieldElement((FieldElement)value, diagnostics, context);
			case ViewPackage.ABSTRACT_VIEW:
				return validateAbstractView((AbstractView)value, diagnostics, context);
			case ViewPackage.ABSTRACT_DATA_TABLE:
				return validateAbstractDataTable((AbstractDataTable)value, diagnostics, context);
			case ViewPackage.COL:
				return validateCol((Col)value, diagnostics, context);
			case ViewPackage.PAGING:
				return validatePaging((Paging)value, diagnostics, context);
			case ViewPackage.SORTING:
				return validateSorting((Sorting)value, diagnostics, context);
			case ViewPackage.FILTERING:
				return validateFiltering((Filtering)value, diagnostics, context);
			case ViewPackage.STYLING:
				return validateStyling((Styling)value, diagnostics, context);
			case ViewPackage.DATA_TABLE_ELEMENT:
				return validateDataTableElement((DataTableElement)value, diagnostics, context);
			case ViewPackage.DATA_LIST:
				return validateDataList((DataList)value, diagnostics, context);
			case ViewPackage.DATA_TABLE:
				return validateDataTable((DataTable)value, diagnostics, context);
			case ViewPackage.FACET_MAP:
				return validateFacetMap((FacetMap)value, diagnostics, context);
			case ViewPackage.TREE:
				return validateTree((Tree)value, diagnostics, context);
			case ViewPackage.FIELD:
				return validateField((Field)value, diagnostics, context);
			case ViewPackage.TEXT_FIELD:
				return validateTextField((TextField)value, diagnostics, context);
			case ViewPackage.PASSWORD_FIELD:
				return validatePasswordField((PasswordField)value, diagnostics, context);
			case ViewPackage.BOOLEAN_FIELD:
				return validateBooleanField((BooleanField)value, diagnostics, context);
			case ViewPackage.FLOAT_FIELD:
				return validateFloatField((FloatField)value, diagnostics, context);
			case ViewPackage.ACTION_FIELD:
				return validateActionField((ActionField)value, diagnostics, context);
			case ViewPackage.DATE_FIELD:
				return validateDateField((DateField)value, diagnostics, context);
			case ViewPackage.TIME_FIELD:
				return validateTimeField((TimeField)value, diagnostics, context);
			case ViewPackage.DATE_TIME_FIELD:
				return validateDateTimeField((DateTimeField)value, diagnostics, context);
			case ViewPackage.PHONE_NUMBER_FIELD:
				return validatePhoneNumberField((PhoneNumberField)value, diagnostics, context);
			case ViewPackage.EMAIL_FIELD:
				return validateEmailField((EmailField)value, diagnostics, context);
			case ViewPackage.INTEGER_FIELD:
				return validateIntegerField((IntegerField)value, diagnostics, context);
			case ViewPackage.FILE_FIELD:
				return validateFileField((FileField)value, diagnostics, context);
			case ViewPackage.SELECT_FIELD:
				return validateSelectField((SelectField)value, diagnostics, context);
			case ViewPackage.HTML_FIELD:
				return validateHtmlField((HtmlField)value, diagnostics, context);
			case ViewPackage.URL_FIELD:
				return validateURLField((URLField)value, diagnostics, context);
			case ViewPackage.IMAGE_FIELD:
				return validateImageField((ImageField)value, diagnostics, context);
			case ViewPackage.STYLABLE:
				return validateStylable((Stylable)value, diagnostics, context);
			case ViewPackage.PAGINABLE:
				return validatePaginable((Paginable)value, diagnostics, context);
			case ViewPackage.SORTABLE:
				return validateSortable((Sortable)value, diagnostics, context);
			case ViewPackage.EDITABLE:
				return validateEditable((Editable)value, diagnostics, context);
			case ViewPackage.MOVABLE:
				return validateMovable((Movable)value, diagnostics, context);
			case ViewPackage.FILTERABLE:
				return validateFilterable((Filterable)value, diagnostics, context);
			case ViewPackage.FIELD_GROUP:
				return validateFieldGroup((FieldGroup)value, diagnostics, context);
			case ViewPackage.SORT_ORDER:
				return validateSortOrder((SortOrder)value, diagnostics, context);
			case ViewPackage.PAGINATION_STYLE:
				return validatePaginationStyle((PaginationStyle)value, diagnostics, context);
			case ViewPackage.HALIGN:
				return validateHalign((Halign)value, diagnostics, context);
			case ViewPackage.LOADING_TYPE:
				return validateLoadingType((LoadingType)value, diagnostics, context);
			case ViewPackage.WIDGET_TEXT_TYPE:
				return validateWidgetTextType((WidgetTextType)value, diagnostics, context);
			case ViewPackage.SELECT_WIDGET_TYPE:
				return validateSelectWidgetType((SelectWidgetType)value, diagnostics, context);
			case ViewPackage.FACET_DISPLAY_TYPE:
				return validateFacetDisplayType((FacetDisplayType)value, diagnostics, context);
			default:
				return true;
		}
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean validateViewCollection(ViewCollection viewCollection, DiagnosticChain diagnostics, Map<Object, Object> context) {
		boolean result = validate_EveryMultiplicityConforms(viewCollection, diagnostics, context);
		if (result || diagnostics != null) result &= validate_EveryDataValueConforms(viewCollection, diagnostics, context);
		if (result || diagnostics != null) result &= validate_EveryReferenceIsContained(viewCollection, diagnostics, context);
		if (result || diagnostics != null) result &= validate_EveryProxyResolves(viewCollection, diagnostics, context);
		if (result || diagnostics != null) result &= validate_UniqueID(viewCollection, diagnostics, context);
		if (result || diagnostics != null) result &= validate_EveryKeyUnique(viewCollection, diagnostics, context);
		if (result || diagnostics != null) result &= validate_EveryMapEntryUnique(viewCollection, diagnostics, context);
		if (result || diagnostics != null) result &= commonValidator.validatePackage_PackageNameNull(viewCollection, diagnostics, context);
		return result;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean validateFieldContainer(FieldContainer fieldContainer, DiagnosticChain diagnostics, Map<Object, Object> context) {
		boolean result = validate_EveryMultiplicityConforms(fieldContainer, diagnostics, context);
		if (result || diagnostics != null) result &= validate_EveryDataValueConforms(fieldContainer, diagnostics, context);
		if (result || diagnostics != null) result &= validate_EveryReferenceIsContained(fieldContainer, diagnostics, context);
		if (result || diagnostics != null) result &= validate_EveryProxyResolves(fieldContainer, diagnostics, context);
		if (result || diagnostics != null) result &= validate_UniqueID(fieldContainer, diagnostics, context);
		if (result || diagnostics != null) result &= validate_EveryKeyUnique(fieldContainer, diagnostics, context);
		if (result || diagnostics != null) result &= validate_EveryMapEntryUnique(fieldContainer, diagnostics, context);
		if (result || diagnostics != null) result &= validateFieldElement_noFieldMapped(fieldContainer, diagnostics, context);
		return result;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean validateFieldElement(FieldElement fieldElement, DiagnosticChain diagnostics, Map<Object, Object> context) {
		boolean result = validate_EveryMultiplicityConforms(fieldElement, diagnostics, context);
		if (result || diagnostics != null) result &= validate_EveryDataValueConforms(fieldElement, diagnostics, context);
		if (result || diagnostics != null) result &= validate_EveryReferenceIsContained(fieldElement, diagnostics, context);
		if (result || diagnostics != null) result &= validate_EveryProxyResolves(fieldElement, diagnostics, context);
		if (result || diagnostics != null) result &= validate_UniqueID(fieldElement, diagnostics, context);
		if (result || diagnostics != null) result &= validate_EveryKeyUnique(fieldElement, diagnostics, context);
		if (result || diagnostics != null) result &= validate_EveryMapEntryUnique(fieldElement, diagnostics, context);
		if (result || diagnostics != null) result &= validateFieldElement_noFieldMapped(fieldElement, diagnostics, context);
		return result;
	}

	/**
	 * Validates the noFieldMapped constraint of '<em>Field Element</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean validateFieldElement_noFieldMapped(FieldElement fieldElement, DiagnosticChain diagnostics, Map<Object, Object> context) {
		// TODO implement the constraint
		// -> specify the condition that violates the constraint
		// -> verify the diagnostic details, including severity, code, and message
		// Ensure that you remove @generated or mark it @generated NOT
		if (false) {
			if (diagnostics != null) {
				diagnostics.add
					(createDiagnostic
						(Diagnostic.ERROR,
						 DIAGNOSTIC_SOURCE,
						 0,
						 "_UI_GenericConstraint_diagnostic",
						 new Object[] { "noFieldMapped", getObjectLabel(fieldElement, context) },
						 new Object[] { fieldElement },
						 context));
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
	public boolean validateAbstractView(AbstractView abstractView, DiagnosticChain diagnostics, Map<Object, Object> context) {
		boolean result = validate_EveryMultiplicityConforms(abstractView, diagnostics, context);
		if (result || diagnostics != null) result &= validate_EveryDataValueConforms(abstractView, diagnostics, context);
		if (result || diagnostics != null) result &= validate_EveryReferenceIsContained(abstractView, diagnostics, context);
		if (result || diagnostics != null) result &= validate_EveryProxyResolves(abstractView, diagnostics, context);
		if (result || diagnostics != null) result &= validate_UniqueID(abstractView, diagnostics, context);
		if (result || diagnostics != null) result &= validate_EveryKeyUnique(abstractView, diagnostics, context);
		if (result || diagnostics != null) result &= validate_EveryMapEntryUnique(abstractView, diagnostics, context);
		if (result || diagnostics != null) result &= validateFieldElement_noFieldMapped(abstractView, diagnostics, context);
		return result;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean validateAbstractDataTable(AbstractDataTable abstractDataTable, DiagnosticChain diagnostics, Map<Object, Object> context) {
		boolean result = validate_EveryMultiplicityConforms(abstractDataTable, diagnostics, context);
		if (result || diagnostics != null) result &= validate_EveryDataValueConforms(abstractDataTable, diagnostics, context);
		if (result || diagnostics != null) result &= validate_EveryReferenceIsContained(abstractDataTable, diagnostics, context);
		if (result || diagnostics != null) result &= validate_EveryProxyResolves(abstractDataTable, diagnostics, context);
		if (result || diagnostics != null) result &= validate_UniqueID(abstractDataTable, diagnostics, context);
		if (result || diagnostics != null) result &= validate_EveryKeyUnique(abstractDataTable, diagnostics, context);
		if (result || diagnostics != null) result &= validate_EveryMapEntryUnique(abstractDataTable, diagnostics, context);
		if (result || diagnostics != null) result &= validateFieldElement_noFieldMapped(abstractDataTable, diagnostics, context);
		return result;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean validateCol(Col col, DiagnosticChain diagnostics, Map<Object, Object> context) {
		boolean result = validate_EveryMultiplicityConforms(col, diagnostics, context);
		if (result || diagnostics != null) result &= validate_EveryDataValueConforms(col, diagnostics, context);
		if (result || diagnostics != null) result &= validate_EveryReferenceIsContained(col, diagnostics, context);
		if (result || diagnostics != null) result &= validate_EveryProxyResolves(col, diagnostics, context);
		if (result || diagnostics != null) result &= validate_UniqueID(col, diagnostics, context);
		if (result || diagnostics != null) result &= validate_EveryKeyUnique(col, diagnostics, context);
		if (result || diagnostics != null) result &= validate_EveryMapEntryUnique(col, diagnostics, context);
		if (result || diagnostics != null) result &= validateFieldElement_noFieldMapped(col, diagnostics, context);
		return result;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean validatePaging(Paging paging, DiagnosticChain diagnostics, Map<Object, Object> context) {
		return validate_EveryDefaultConstraint(paging, diagnostics, context);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean validateSorting(Sorting sorting, DiagnosticChain diagnostics, Map<Object, Object> context) {
		return validate_EveryDefaultConstraint(sorting, diagnostics, context);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean validateFiltering(Filtering filtering, DiagnosticChain diagnostics, Map<Object, Object> context) {
		return validate_EveryDefaultConstraint(filtering, diagnostics, context);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean validateStyling(Styling styling, DiagnosticChain diagnostics, Map<Object, Object> context) {
		return validate_EveryDefaultConstraint(styling, diagnostics, context);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean validateDataTableElement(DataTableElement dataTableElement, DiagnosticChain diagnostics, Map<Object, Object> context) {
		return validate_EveryDefaultConstraint(dataTableElement, diagnostics, context);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean validateDataList(DataList dataList, DiagnosticChain diagnostics, Map<Object, Object> context) {
		boolean result = validate_EveryMultiplicityConforms(dataList, diagnostics, context);
		if (result || diagnostics != null) result &= validate_EveryDataValueConforms(dataList, diagnostics, context);
		if (result || diagnostics != null) result &= validate_EveryReferenceIsContained(dataList, diagnostics, context);
		if (result || diagnostics != null) result &= validate_EveryProxyResolves(dataList, diagnostics, context);
		if (result || diagnostics != null) result &= validate_UniqueID(dataList, diagnostics, context);
		if (result || diagnostics != null) result &= validate_EveryKeyUnique(dataList, diagnostics, context);
		if (result || diagnostics != null) result &= validate_EveryMapEntryUnique(dataList, diagnostics, context);
		if (result || diagnostics != null) result &= validateFieldElement_noFieldMapped(dataList, diagnostics, context);
		return result;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean validateDataTable(DataTable dataTable, DiagnosticChain diagnostics, Map<Object, Object> context) {
		boolean result = validate_EveryMultiplicityConforms(dataTable, diagnostics, context);
		if (result || diagnostics != null) result &= validate_EveryDataValueConforms(dataTable, diagnostics, context);
		if (result || diagnostics != null) result &= validate_EveryReferenceIsContained(dataTable, diagnostics, context);
		if (result || diagnostics != null) result &= validate_EveryProxyResolves(dataTable, diagnostics, context);
		if (result || diagnostics != null) result &= validate_UniqueID(dataTable, diagnostics, context);
		if (result || diagnostics != null) result &= validate_EveryKeyUnique(dataTable, diagnostics, context);
		if (result || diagnostics != null) result &= validate_EveryMapEntryUnique(dataTable, diagnostics, context);
		if (result || diagnostics != null) result &= validateFieldElement_noFieldMapped(dataTable, diagnostics, context);
		return result;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean validateFacetMap(FacetMap facetMap, DiagnosticChain diagnostics, Map<Object, Object> context) {
		boolean result = validate_EveryMultiplicityConforms(facetMap, diagnostics, context);
		if (result || diagnostics != null) result &= validate_EveryDataValueConforms(facetMap, diagnostics, context);
		if (result || diagnostics != null) result &= validate_EveryReferenceIsContained(facetMap, diagnostics, context);
		if (result || diagnostics != null) result &= validate_EveryProxyResolves(facetMap, diagnostics, context);
		if (result || diagnostics != null) result &= validate_UniqueID(facetMap, diagnostics, context);
		if (result || diagnostics != null) result &= validate_EveryKeyUnique(facetMap, diagnostics, context);
		if (result || diagnostics != null) result &= validate_EveryMapEntryUnique(facetMap, diagnostics, context);
		if (result || diagnostics != null) result &= validateFieldElement_noFieldMapped(facetMap, diagnostics, context);
		return result;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean validateTree(Tree tree, DiagnosticChain diagnostics, Map<Object, Object> context) {
		boolean result = validate_EveryMultiplicityConforms(tree, diagnostics, context);
		if (result || diagnostics != null) result &= validate_EveryDataValueConforms(tree, diagnostics, context);
		if (result || diagnostics != null) result &= validate_EveryReferenceIsContained(tree, diagnostics, context);
		if (result || diagnostics != null) result &= validate_EveryProxyResolves(tree, diagnostics, context);
		if (result || diagnostics != null) result &= validate_UniqueID(tree, diagnostics, context);
		if (result || diagnostics != null) result &= validate_EveryKeyUnique(tree, diagnostics, context);
		if (result || diagnostics != null) result &= validate_EveryMapEntryUnique(tree, diagnostics, context);
		if (result || diagnostics != null) result &= validateFieldElement_noFieldMapped(tree, diagnostics, context);
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
		if (result || diagnostics != null) result &= validateFieldElement_noFieldMapped(field, diagnostics, context);
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
		if (result || diagnostics != null) result &= validateFieldElement_noFieldMapped(textField, diagnostics, context);
		return result;
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
		if (result || diagnostics != null) result &= validateFieldElement_noFieldMapped(passwordField, diagnostics, context);
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
		if (result || diagnostics != null) result &= validateFieldElement_noFieldMapped(booleanField, diagnostics, context);
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
		if (result || diagnostics != null) result &= validateFieldElement_noFieldMapped(floatField, diagnostics, context);
		return result;
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
		if (result || diagnostics != null) result &= validateFieldElement_noFieldMapped(actionField, diagnostics, context);
		return result;
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
		if (result || diagnostics != null) result &= validateFieldElement_noFieldMapped(dateField, diagnostics, context);
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
		if (result || diagnostics != null) result &= validateFieldElement_noFieldMapped(timeField, diagnostics, context);
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
		if (result || diagnostics != null) result &= validateFieldElement_noFieldMapped(dateTimeField, diagnostics, context);
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
		if (result || diagnostics != null) result &= validateFieldElement_noFieldMapped(phoneNumberField, diagnostics, context);
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
		if (result || diagnostics != null) result &= validateFieldElement_noFieldMapped(emailField, diagnostics, context);
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
		if (result || diagnostics != null) result &= validateFieldElement_noFieldMapped(integerField, diagnostics, context);
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
		if (result || diagnostics != null) result &= validateFieldElement_noFieldMapped(fileField, diagnostics, context);
		return result;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean validateSelectField(SelectField selectField, DiagnosticChain diagnostics, Map<Object, Object> context) {
		boolean result = validate_EveryMultiplicityConforms(selectField, diagnostics, context);
		if (result || diagnostics != null) result &= validate_EveryDataValueConforms(selectField, diagnostics, context);
		if (result || diagnostics != null) result &= validate_EveryReferenceIsContained(selectField, diagnostics, context);
		if (result || diagnostics != null) result &= validate_EveryProxyResolves(selectField, diagnostics, context);
		if (result || diagnostics != null) result &= validate_UniqueID(selectField, diagnostics, context);
		if (result || diagnostics != null) result &= validate_EveryKeyUnique(selectField, diagnostics, context);
		if (result || diagnostics != null) result &= validate_EveryMapEntryUnique(selectField, diagnostics, context);
		if (result || diagnostics != null) result &= validateFieldElement_noFieldMapped(selectField, diagnostics, context);
		return result;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean validateHtmlField(HtmlField htmlField, DiagnosticChain diagnostics, Map<Object, Object> context) {
		boolean result = validate_EveryMultiplicityConforms(htmlField, diagnostics, context);
		if (result || diagnostics != null) result &= validate_EveryDataValueConforms(htmlField, diagnostics, context);
		if (result || diagnostics != null) result &= validate_EveryReferenceIsContained(htmlField, diagnostics, context);
		if (result || diagnostics != null) result &= validate_EveryProxyResolves(htmlField, diagnostics, context);
		if (result || diagnostics != null) result &= validate_UniqueID(htmlField, diagnostics, context);
		if (result || diagnostics != null) result &= validate_EveryKeyUnique(htmlField, diagnostics, context);
		if (result || diagnostics != null) result &= validate_EveryMapEntryUnique(htmlField, diagnostics, context);
		if (result || diagnostics != null) result &= validateFieldElement_noFieldMapped(htmlField, diagnostics, context);
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
		if (result || diagnostics != null) result &= validateFieldElement_noFieldMapped(urlField, diagnostics, context);
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
		if (result || diagnostics != null) result &= validateFieldElement_noFieldMapped(imageField, diagnostics, context);
		return result;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean validateStylable(Stylable stylable, DiagnosticChain diagnostics, Map<Object, Object> context) {
		return validate_EveryDefaultConstraint(stylable, diagnostics, context);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean validatePaginable(Paginable paginable, DiagnosticChain diagnostics, Map<Object, Object> context) {
		return validate_EveryDefaultConstraint(paginable, diagnostics, context);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean validateSortable(Sortable sortable, DiagnosticChain diagnostics, Map<Object, Object> context) {
		return validate_EveryDefaultConstraint(sortable, diagnostics, context);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean validateEditable(Editable editable, DiagnosticChain diagnostics, Map<Object, Object> context) {
		return validate_EveryDefaultConstraint(editable, diagnostics, context);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean validateMovable(Movable movable, DiagnosticChain diagnostics, Map<Object, Object> context) {
		return validate_EveryDefaultConstraint(movable, diagnostics, context);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean validateFilterable(Filterable filterable, DiagnosticChain diagnostics, Map<Object, Object> context) {
		return validate_EveryDefaultConstraint(filterable, diagnostics, context);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean validateFieldGroup(FieldGroup fieldGroup, DiagnosticChain diagnostics, Map<Object, Object> context) {
		boolean result = validate_EveryMultiplicityConforms(fieldGroup, diagnostics, context);
		if (result || diagnostics != null) result &= validate_EveryDataValueConforms(fieldGroup, diagnostics, context);
		if (result || diagnostics != null) result &= validate_EveryReferenceIsContained(fieldGroup, diagnostics, context);
		if (result || diagnostics != null) result &= validate_EveryProxyResolves(fieldGroup, diagnostics, context);
		if (result || diagnostics != null) result &= validate_UniqueID(fieldGroup, diagnostics, context);
		if (result || diagnostics != null) result &= validate_EveryKeyUnique(fieldGroup, diagnostics, context);
		if (result || diagnostics != null) result &= validate_EveryMapEntryUnique(fieldGroup, diagnostics, context);
		if (result || diagnostics != null) result &= validateFieldElement_noFieldMapped(fieldGroup, diagnostics, context);
		return result;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean validateSortOrder(SortOrder sortOrder, DiagnosticChain diagnostics, Map<Object, Object> context) {
		return true;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean validatePaginationStyle(PaginationStyle paginationStyle, DiagnosticChain diagnostics, Map<Object, Object> context) {
		return true;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean validateHalign(Halign halign, DiagnosticChain diagnostics, Map<Object, Object> context) {
		return true;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean validateLoadingType(LoadingType loadingType, DiagnosticChain diagnostics, Map<Object, Object> context) {
		return true;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean validateWidgetTextType(WidgetTextType widgetTextType, DiagnosticChain diagnostics, Map<Object, Object> context) {
		return true;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean validateSelectWidgetType(SelectWidgetType selectWidgetType, DiagnosticChain diagnostics, Map<Object, Object> context) {
		return true;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean validateFacetDisplayType(FacetDisplayType facetDisplayType, DiagnosticChain diagnostics, Map<Object, Object> context) {
		return true;
	}

	/**
	 * Returns the resource locator that will be used to fetch messages for this validator's diagnostics.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public ResourceLocator getResourceLocator() {
		// TODO
		// Specialize this to return a resource locator for messages specific to this validator.
		// Ensure that you remove @generated or mark it @generated NOT
		return super.getResourceLocator();
	}

} //ViewValidator
