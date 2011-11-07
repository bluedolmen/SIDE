/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package com.bluexml.side.view.impl;

import com.bluexml.side.view.*;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EDataType;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.impl.EFactoryImpl;
import org.eclipse.emf.ecore.plugin.EcorePlugin;

import com.bluexml.side.view.ActionField;
import com.bluexml.side.view.Actionable;
import com.bluexml.side.view.BooleanField;
import com.bluexml.side.view.Col;
import com.bluexml.side.view.ComposedView;
import com.bluexml.side.view.DataList;
import com.bluexml.side.view.DataTable;
import com.bluexml.side.view.DateField;
import com.bluexml.side.view.DateTimeField;
import com.bluexml.side.view.EmailField;
import com.bluexml.side.view.FacetDisplayType;
import com.bluexml.side.view.FacetMap;
import com.bluexml.side.view.FieldGroup;
import com.bluexml.side.view.FileField;
import com.bluexml.side.view.Filtering;
import com.bluexml.side.view.FloatField;
import com.bluexml.side.view.Halign;
import com.bluexml.side.view.HtmlField;
import com.bluexml.side.view.ImageField;
import com.bluexml.side.view.IntegerField;
import com.bluexml.side.view.LoadingType;
import com.bluexml.side.view.PaginationStyle;
import com.bluexml.side.view.Paging;
import com.bluexml.side.view.PasswordField;
import com.bluexml.side.view.PhoneNumberField;
import com.bluexml.side.view.SelectField;
import com.bluexml.side.view.SelectWidgetType;
import com.bluexml.side.view.SortOrder;
import com.bluexml.side.view.Sorting;
import com.bluexml.side.view.Styling;
import com.bluexml.side.view.TextField;
import com.bluexml.side.view.TimeField;
import com.bluexml.side.view.Tree;
import com.bluexml.side.view.URLField;
import com.bluexml.side.view.ViewCollection;
import com.bluexml.side.view.ViewFactory;
import com.bluexml.side.view.ViewPackage;
import com.bluexml.side.view.WidgetTextType;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model <b>Factory</b>.
 * <!-- end-user-doc -->
 * @generated
 */
public class ViewFactoryImpl extends EFactoryImpl implements ViewFactory {
	/**
	 * Creates the default factory implementation.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public static ViewFactory init() {
		try {
			ViewFactory theViewFactory = (ViewFactory)EPackage.Registry.INSTANCE.getEFactory("http://www.kerblue.org/view/1.0"); 
			if (theViewFactory != null) {
				return theViewFactory;
			}
		}
		catch (Exception exception) {
			EcorePlugin.INSTANCE.log(exception);
		}
		return new ViewFactoryImpl();
	}

	/**
	 * Creates an instance of the factory.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public ViewFactoryImpl() {
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
			case ViewPackage.VIEW_COLLECTION: return createViewCollection();
			case ViewPackage.COL: return createCol();
			case ViewPackage.PAGING: return createPaging();
			case ViewPackage.SORTING: return createSorting();
			case ViewPackage.FILTERING: return createFiltering();
			case ViewPackage.STYLING: return createStyling();
			case ViewPackage.DATA_LIST: return createDataList();
			case ViewPackage.DATA_TABLE: return createDataTable();
			case ViewPackage.FACET_MAP: return createFacetMap();
			case ViewPackage.TREE: return createTree();
			case ViewPackage.COMPOSED_VIEW: return createComposedView();
			case ViewPackage.TEXT_FIELD: return createTextField();
			case ViewPackage.BOOLEAN_FIELD: return createBooleanField();
			case ViewPackage.PASSWORD_FIELD: return createPasswordField();
			case ViewPackage.FLOAT_FIELD: return createFloatField();
			case ViewPackage.ACTION_FIELD: return createActionField();
			case ViewPackage.DATE_FIELD: return createDateField();
			case ViewPackage.TIME_FIELD: return createTimeField();
			case ViewPackage.DATE_TIME_FIELD: return createDateTimeField();
			case ViewPackage.PHONE_NUMBER_FIELD: return createPhoneNumberField();
			case ViewPackage.EMAIL_FIELD: return createEmailField();
			case ViewPackage.INTEGER_FIELD: return createIntegerField();
			case ViewPackage.FILE_FIELD: return createFileField();
			case ViewPackage.SELECT_FIELD: return createSelectField();
			case ViewPackage.HTML_FIELD: return createHtmlField();
			case ViewPackage.URL_FIELD: return createURLField();
			case ViewPackage.IMAGE_FIELD: return createImageField();
			case ViewPackage.ACTIONABLE: return createActionable();
			case ViewPackage.FIELD_GROUP: return createFieldGroup();
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
			case ViewPackage.SORT_ORDER:
				return createSortOrderFromString(eDataType, initialValue);
			case ViewPackage.PAGINATION_STYLE:
				return createPaginationStyleFromString(eDataType, initialValue);
			case ViewPackage.HALIGN:
				return createHalignFromString(eDataType, initialValue);
			case ViewPackage.LOADING_TYPE:
				return createLoadingTypeFromString(eDataType, initialValue);
			case ViewPackage.WIDGET_TEXT_TYPE:
				return createWidgetTextTypeFromString(eDataType, initialValue);
			case ViewPackage.SELECT_WIDGET_TYPE:
				return createSelectWidgetTypeFromString(eDataType, initialValue);
			case ViewPackage.FACET_DISPLAY_TYPE:
				return createFacetDisplayTypeFromString(eDataType, initialValue);
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
			case ViewPackage.SORT_ORDER:
				return convertSortOrderToString(eDataType, instanceValue);
			case ViewPackage.PAGINATION_STYLE:
				return convertPaginationStyleToString(eDataType, instanceValue);
			case ViewPackage.HALIGN:
				return convertHalignToString(eDataType, instanceValue);
			case ViewPackage.LOADING_TYPE:
				return convertLoadingTypeToString(eDataType, instanceValue);
			case ViewPackage.WIDGET_TEXT_TYPE:
				return convertWidgetTextTypeToString(eDataType, instanceValue);
			case ViewPackage.SELECT_WIDGET_TYPE:
				return convertSelectWidgetTypeToString(eDataType, instanceValue);
			case ViewPackage.FACET_DISPLAY_TYPE:
				return convertFacetDisplayTypeToString(eDataType, instanceValue);
			default:
				throw new IllegalArgumentException("The datatype '" + eDataType.getName() + "' is not a valid classifier");
		}
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public ViewCollection createViewCollection() {
		ViewCollectionImpl viewCollection = new ViewCollectionImpl();
		return viewCollection;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Col createCol() {
		ColImpl col = new ColImpl();
		return col;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Paging createPaging() {
		PagingImpl paging = new PagingImpl();
		return paging;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Sorting createSorting() {
		SortingImpl sorting = new SortingImpl();
		return sorting;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Filtering createFiltering() {
		FilteringImpl filtering = new FilteringImpl();
		return filtering;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Styling createStyling() {
		StylingImpl styling = new StylingImpl();
		return styling;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public DataList createDataList() {
		DataListImpl dataList = new DataListImpl();
		return dataList;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public DataTable createDataTable() {
		DataTableImpl dataTable = new DataTableImpl();
		return dataTable;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public FacetMap createFacetMap() {
		FacetMapImpl facetMap = new FacetMapImpl();
		return facetMap;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Tree createTree() {
		TreeImpl tree = new TreeImpl();
		return tree;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public ComposedView createComposedView() {
		ComposedViewImpl composedView = new ComposedViewImpl();
		return composedView;
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
	public PasswordField createPasswordField() {
		PasswordFieldImpl passwordField = new PasswordFieldImpl();
		return passwordField;
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
	public FloatField createFloatField() {
		FloatFieldImpl floatField = new FloatFieldImpl();
		return floatField;
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
	public DateField createDateField() {
		DateFieldImpl dateField = new DateFieldImpl();
		return dateField;
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
	public DateTimeField createDateTimeField() {
		DateTimeFieldImpl dateTimeField = new DateTimeFieldImpl();
		return dateTimeField;
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
	public EmailField createEmailField() {
		EmailFieldImpl emailField = new EmailFieldImpl();
		return emailField;
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
	public FileField createFileField() {
		FileFieldImpl fileField = new FileFieldImpl();
		return fileField;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public SelectField createSelectField() {
		SelectFieldImpl selectField = new SelectFieldImpl();
		return selectField;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public HtmlField createHtmlField() {
		HtmlFieldImpl htmlField = new HtmlFieldImpl();
		return htmlField;
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
	public ImageField createImageField() {
		ImageFieldImpl imageField = new ImageFieldImpl();
		return imageField;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public FieldGroup createFieldGroup() {
		FieldGroupImpl fieldGroup = new FieldGroupImpl();
		return fieldGroup;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Actionable createActionable() {
		ActionableImpl actionable = new ActionableImpl();
		return actionable;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public SortOrder createSortOrderFromString(EDataType eDataType, String initialValue) {
		SortOrder result = SortOrder.get(initialValue);
		if (result == null) throw new IllegalArgumentException("The value '" + initialValue + "' is not a valid enumerator of '" + eDataType.getName() + "'");
		return result;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String convertSortOrderToString(EDataType eDataType, Object instanceValue) {
		return instanceValue == null ? null : instanceValue.toString();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public PaginationStyle createPaginationStyleFromString(EDataType eDataType, String initialValue) {
		PaginationStyle result = PaginationStyle.get(initialValue);
		if (result == null) throw new IllegalArgumentException("The value '" + initialValue + "' is not a valid enumerator of '" + eDataType.getName() + "'");
		return result;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String convertPaginationStyleToString(EDataType eDataType, Object instanceValue) {
		return instanceValue == null ? null : instanceValue.toString();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Halign createHalignFromString(EDataType eDataType, String initialValue) {
		Halign result = Halign.get(initialValue);
		if (result == null) throw new IllegalArgumentException("The value '" + initialValue + "' is not a valid enumerator of '" + eDataType.getName() + "'");
		return result;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String convertHalignToString(EDataType eDataType, Object instanceValue) {
		return instanceValue == null ? null : instanceValue.toString();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public LoadingType createLoadingTypeFromString(EDataType eDataType, String initialValue) {
		LoadingType result = LoadingType.get(initialValue);
		if (result == null) throw new IllegalArgumentException("The value '" + initialValue + "' is not a valid enumerator of '" + eDataType.getName() + "'");
		return result;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String convertLoadingTypeToString(EDataType eDataType, Object instanceValue) {
		return instanceValue == null ? null : instanceValue.toString();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public WidgetTextType createWidgetTextTypeFromString(EDataType eDataType, String initialValue) {
		WidgetTextType result = WidgetTextType.get(initialValue);
		if (result == null) throw new IllegalArgumentException("The value '" + initialValue + "' is not a valid enumerator of '" + eDataType.getName() + "'");
		return result;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String convertWidgetTextTypeToString(EDataType eDataType, Object instanceValue) {
		return instanceValue == null ? null : instanceValue.toString();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public SelectWidgetType createSelectWidgetTypeFromString(EDataType eDataType, String initialValue) {
		SelectWidgetType result = SelectWidgetType.get(initialValue);
		if (result == null) throw new IllegalArgumentException("The value '" + initialValue + "' is not a valid enumerator of '" + eDataType.getName() + "'");
		return result;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String convertSelectWidgetTypeToString(EDataType eDataType, Object instanceValue) {
		return instanceValue == null ? null : instanceValue.toString();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public FacetDisplayType createFacetDisplayTypeFromString(EDataType eDataType, String initialValue) {
		FacetDisplayType result = FacetDisplayType.get(initialValue);
		if (result == null) throw new IllegalArgumentException("The value '" + initialValue + "' is not a valid enumerator of '" + eDataType.getName() + "'");
		return result;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String convertFacetDisplayTypeToString(EDataType eDataType, Object instanceValue) {
		return instanceValue == null ? null : instanceValue.toString();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public ViewPackage getViewPackage() {
		return (ViewPackage)getEPackage();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @deprecated
	 * @generated
	 */
	@Deprecated
	public static ViewPackage getPackage() {
		return ViewPackage.eINSTANCE;
	}

} //ViewFactoryImpl
