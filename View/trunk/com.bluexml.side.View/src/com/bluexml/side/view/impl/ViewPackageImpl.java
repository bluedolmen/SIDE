/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package com.bluexml.side.view.impl;

import com.bluexml.side.common.CommonPackage;

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
import com.bluexml.side.view.DefaultCol;
import com.bluexml.side.view.Editable;
import com.bluexml.side.view.EmailField;
import com.bluexml.side.view.FacetDisplayType;
import com.bluexml.side.view.FacetMap;
import com.bluexml.side.view.FacetMapResultsView;
import com.bluexml.side.view.Field;
import com.bluexml.side.view.FieldElement;
import com.bluexml.side.view.FieldGroup;
import com.bluexml.side.view.FileField;
import com.bluexml.side.view.Filterable;
import com.bluexml.side.view.Filtering;
import com.bluexml.side.view.FloatField;
import com.bluexml.side.view.Halign;
import com.bluexml.side.view.HtmlField;
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
import com.bluexml.side.view.ViewCollection;
import com.bluexml.side.view.ViewFactory;
import com.bluexml.side.view.ViewPackage;
import com.bluexml.side.view.WidgetTextType;

import org.eclipse.emf.ecore.EAttribute;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EEnum;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.EReference;

import org.eclipse.emf.ecore.impl.EPackageImpl;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model <b>Package</b>.
 * <!-- end-user-doc -->
 * @generated
 */
public class ViewPackageImpl extends EPackageImpl implements ViewPackage {
	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass abstractDataTableEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass colEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass pagingEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass sortingEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass dataTableElementEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass fieldGroupEClass = null;

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
	private EClass textFieldEClass = null;

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
	private EClass booleanFieldEClass = null;

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
	private EClass actionFieldEClass = null;

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
	private EClass timeFieldEClass = null;

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
	private EClass phoneNumberFieldEClass = null;

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
	private EClass fieldElementEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass facetMapEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass dataListEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass treeEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass dataTableEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass stylingEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass abstractViewEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass selectFieldEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass viewCollectionEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass htmlFieldEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass stylableEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass paginableEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass sortableEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass editableEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass movableEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass filterableEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass filteringEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass facetMapResultsViewEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EEnum sortOrderEEnum = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EEnum paginationStyleEEnum = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EEnum halignEEnum = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EEnum loadingTypeEEnum = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EEnum widgetTextTypeEEnum = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EEnum selectWidgetTypeEEnum = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EEnum facetDisplayTypeEEnum = null;

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
	 * @see com.bluexml.side.view.ViewPackage#eNS_URI
	 * @see #init()
	 * @generated
	 */
	private ViewPackageImpl() {
		super(eNS_URI, ViewFactory.eINSTANCE);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private static boolean isInited = false;

	/**
	 * Creates, registers, and initializes the <b>Package</b> for this
	 * model, and for any others upon which it depends.  Simple
	 * dependencies are satisfied by calling this method on all
	 * dependent packages before doing anything else.  This method drives
	 * initialization for interdependent packages directly, in parallel
	 * with this package, itself.
	 * <p>Of this package and its interdependencies, all packages which
	 * have not yet been registered by their URI values are first created
	 * and registered.  The packages are then initialized in two steps:
	 * meta-model objects for all of the packages are created before any
	 * are initialized, since one package's meta-model objects may refer to
	 * those of another.
	 * <p>Invocation of this method will not affect any packages that have
	 * already been initialized.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #eNS_URI
	 * @see #createPackageContents()
	 * @see #initializePackageContents()
	 * @generated
	 */
	public static ViewPackage init() {
		if (isInited) return (ViewPackage)EPackage.Registry.INSTANCE.getEPackage(ViewPackage.eNS_URI);

		// Obtain or create and register package
		ViewPackageImpl theViewPackage = (ViewPackageImpl)(EPackage.Registry.INSTANCE.getEPackage(eNS_URI) instanceof ViewPackageImpl ? EPackage.Registry.INSTANCE.getEPackage(eNS_URI) : new ViewPackageImpl());

		isInited = true;

		// Initialize simple dependencies
		CommonPackage.eINSTANCE.eClass();

		// Create package meta-data objects
		theViewPackage.createPackageContents();

		// Initialize created meta-data
		theViewPackage.initializePackageContents();

		// Mark meta-data to indicate it can't be changed
		theViewPackage.freeze();

		return theViewPackage;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getAbstractDataTable() {
		return abstractDataTableEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getAbstractDataTable_HaveRowActions() {
		return (EReference)abstractDataTableEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getAbstractDataTable_HaveSelectActions() {
		return (EReference)abstractDataTableEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getAbstractDataTable_HaveDefaultColActions() {
		return (EReference)abstractDataTableEClass.getEStructuralFeatures().get(2);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getCol() {
		return colEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getCol_Actions() {
		return (EReference)colEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getPaging() {
		return pagingEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getPaging_PaginationStyle() {
		return (EAttribute)pagingEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getPaging_MaxItems() {
		return (EAttribute)pagingEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getPaging_MaxPage() {
		return (EAttribute)pagingEClass.getEStructuralFeatures().get(2);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getSorting() {
		return sortingEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getSorting_SortOrder() {
		return (EAttribute)sortingEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getSorting_Sorted() {
		return (EAttribute)sortingEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getDataTableElement() {
		return dataTableElementEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getFieldGroup() {
		return fieldGroupEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getFieldGroup_Children() {
		return (EReference)fieldGroupEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getFieldGroup_Disabled() {
		return (EReference)fieldGroupEClass.getEStructuralFeatures().get(1);
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
	public EAttribute getField_Patern() {
		return (EAttribute)fieldEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getField_PatrenLanguage() {
		return (EAttribute)fieldEClass.getEStructuralFeatures().get(1);
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
	public EAttribute getTextField_WidgetType() {
		return (EAttribute)textFieldEClass.getEStructuralFeatures().get(0);
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
	public EClass getBooleanField() {
		return booleanFieldEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getBooleanField_Split() {
		return (EAttribute)booleanFieldEClass.getEStructuralFeatures().get(0);
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
	public EClass getActionField() {
		return actionFieldEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getActionField_Operations() {
		return (EReference)actionFieldEClass.getEStructuralFeatures().get(0);
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
	public EClass getTimeField() {
		return timeFieldEClass;
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
	public EClass getPhoneNumberField() {
		return phoneNumberFieldEClass;
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
	public EClass getFieldElement() {
		return fieldElementEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getFieldElement_MapTo() {
		return (EReference)fieldElementEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getFieldElement_Prefix() {
		return (EAttribute)fieldElementEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getFieldElement_Suffix() {
		return (EAttribute)fieldElementEClass.getEStructuralFeatures().get(2);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getFieldElement_Hidden() {
		return (EAttribute)fieldElementEClass.getEStructuralFeatures().get(3);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getFacetMap() {
		return facetMapEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getFacetMap_Criterias() {
		return (EReference)facetMapEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getFacetMap_DisplayEmptyFacet() {
		return (EAttribute)facetMapEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getFacetMap_FacetDisplayType() {
		return (EAttribute)facetMapEClass.getEStructuralFeatures().get(2);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getFacetMap_ResultsViewer() {
		return (EReference)facetMapEClass.getEStructuralFeatures().get(3);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getDataList() {
		return dataListEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getDataList_Col() {
		return (EReference)dataListEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getTree() {
		return treeEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getTree_NodeOperations() {
		return (EReference)treeEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getTree_NodeValue() {
		return (EReference)treeEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getTree_DefaultDepth() {
		return (EAttribute)treeEClass.getEStructuralFeatures().get(2);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getTree_MaxDepth() {
		return (EAttribute)treeEClass.getEStructuralFeatures().get(3);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getDataTable() {
		return dataTableEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getDataTable_DefaultColSetUp() {
		return (EReference)dataTableEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getDataTable_Cols() {
		return (EReference)dataTableEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getStyling() {
		return stylingEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getStyling_Style() {
		return (EAttribute)stylingEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getStyling_Height() {
		return (EAttribute)stylingEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getStyling_Width() {
		return (EAttribute)stylingEClass.getEStructuralFeatures().get(2);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getAbstractView() {
		return abstractViewEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getAbstractView_ViewOf() {
		return (EReference)abstractViewEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getAbstractView_Operations() {
		return (EReference)abstractViewEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getSelectField() {
		return selectFieldEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getSelectField_SelectWidget() {
		return (EAttribute)selectFieldEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getViewCollection() {
		return viewCollectionEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getViewCollection_Views() {
		return (EReference)viewCollectionEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getHtmlField() {
		return htmlFieldEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getStylable() {
		return stylableEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getStylable_Styling() {
		return (EReference)stylableEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getPaginable() {
		return paginableEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getPaginable_Paging() {
		return (EReference)paginableEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getSortable() {
		return sortableEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getSortable_Sorting() {
		return (EReference)sortableEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getEditable() {
		return editableEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getEditable_Editable() {
		return (EAttribute)editableEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getMovable() {
		return movableEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getMovable_Movable() {
		return (EAttribute)movableEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getFilterable() {
		return filterableEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getFilterable_Filtering() {
		return (EReference)filterableEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getFiltering() {
		return filteringEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getFiltering_DefaultFilterValue() {
		return (EAttribute)filteringEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getFacetMapResultsView() {
		return facetMapResultsViewEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getFacetMapResultsView_FacetMapDef() {
		return (EReference)facetMapResultsViewEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EEnum getSortOrder() {
		return sortOrderEEnum;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EEnum getPaginationStyle() {
		return paginationStyleEEnum;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EEnum getHalign() {
		return halignEEnum;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EEnum getLoadingType() {
		return loadingTypeEEnum;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EEnum getWidgetTextType() {
		return widgetTextTypeEEnum;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EEnum getSelectWidgetType() {
		return selectWidgetTypeEEnum;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EEnum getFacetDisplayType() {
		return facetDisplayTypeEEnum;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public ViewFactory getViewFactory() {
		return (ViewFactory)getEFactoryInstance();
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
		abstractDataTableEClass = createEClass(ABSTRACT_DATA_TABLE);
		createEReference(abstractDataTableEClass, ABSTRACT_DATA_TABLE__HAVE_ROW_ACTIONS);
		createEReference(abstractDataTableEClass, ABSTRACT_DATA_TABLE__HAVE_SELECT_ACTIONS);
		createEReference(abstractDataTableEClass, ABSTRACT_DATA_TABLE__HAVE_DEFAULT_COL_ACTIONS);

		colEClass = createEClass(COL);
		createEReference(colEClass, COL__ACTIONS);

		pagingEClass = createEClass(PAGING);
		createEAttribute(pagingEClass, PAGING__PAGINATION_STYLE);
		createEAttribute(pagingEClass, PAGING__MAX_ITEMS);
		createEAttribute(pagingEClass, PAGING__MAX_PAGE);

		sortingEClass = createEClass(SORTING);
		createEAttribute(sortingEClass, SORTING__SORT_ORDER);
		createEAttribute(sortingEClass, SORTING__SORTED);

		dataTableElementEClass = createEClass(DATA_TABLE_ELEMENT);

		fieldGroupEClass = createEClass(FIELD_GROUP);
		createEReference(fieldGroupEClass, FIELD_GROUP__CHILDREN);
		createEReference(fieldGroupEClass, FIELD_GROUP__DISABLED);

		fieldEClass = createEClass(FIELD);
		createEAttribute(fieldEClass, FIELD__PATERN);
		createEAttribute(fieldEClass, FIELD__PATREN_LANGUAGE);

		textFieldEClass = createEClass(TEXT_FIELD);
		createEAttribute(textFieldEClass, TEXT_FIELD__WIDGET_TYPE);

		passwordFieldEClass = createEClass(PASSWORD_FIELD);

		booleanFieldEClass = createEClass(BOOLEAN_FIELD);
		createEAttribute(booleanFieldEClass, BOOLEAN_FIELD__SPLIT);

		floatFieldEClass = createEClass(FLOAT_FIELD);

		actionFieldEClass = createEClass(ACTION_FIELD);
		createEReference(actionFieldEClass, ACTION_FIELD__OPERATIONS);

		dateFieldEClass = createEClass(DATE_FIELD);

		timeFieldEClass = createEClass(TIME_FIELD);

		dateTimeFieldEClass = createEClass(DATE_TIME_FIELD);

		phoneNumberFieldEClass = createEClass(PHONE_NUMBER_FIELD);

		emailFieldEClass = createEClass(EMAIL_FIELD);

		fileFieldEClass = createEClass(FILE_FIELD);

		fieldElementEClass = createEClass(FIELD_ELEMENT);
		createEReference(fieldElementEClass, FIELD_ELEMENT__MAP_TO);
		createEAttribute(fieldElementEClass, FIELD_ELEMENT__PREFIX);
		createEAttribute(fieldElementEClass, FIELD_ELEMENT__SUFFIX);
		createEAttribute(fieldElementEClass, FIELD_ELEMENT__HIDDEN);

		facetMapEClass = createEClass(FACET_MAP);
		createEReference(facetMapEClass, FACET_MAP__CRITERIAS);
		createEAttribute(facetMapEClass, FACET_MAP__DISPLAY_EMPTY_FACET);
		createEAttribute(facetMapEClass, FACET_MAP__FACET_DISPLAY_TYPE);
		createEReference(facetMapEClass, FACET_MAP__RESULTS_VIEWER);

		dataListEClass = createEClass(DATA_LIST);
		createEReference(dataListEClass, DATA_LIST__COL);

		treeEClass = createEClass(TREE);
		createEReference(treeEClass, TREE__NODE_OPERATIONS);
		createEReference(treeEClass, TREE__NODE_VALUE);
		createEAttribute(treeEClass, TREE__DEFAULT_DEPTH);
		createEAttribute(treeEClass, TREE__MAX_DEPTH);

		dataTableEClass = createEClass(DATA_TABLE);
		createEReference(dataTableEClass, DATA_TABLE__DEFAULT_COL_SET_UP);
		createEReference(dataTableEClass, DATA_TABLE__COLS);

		stylingEClass = createEClass(STYLING);
		createEAttribute(stylingEClass, STYLING__STYLE);
		createEAttribute(stylingEClass, STYLING__HEIGHT);
		createEAttribute(stylingEClass, STYLING__WIDTH);

		abstractViewEClass = createEClass(ABSTRACT_VIEW);
		createEReference(abstractViewEClass, ABSTRACT_VIEW__VIEW_OF);
		createEReference(abstractViewEClass, ABSTRACT_VIEW__OPERATIONS);

		selectFieldEClass = createEClass(SELECT_FIELD);
		createEAttribute(selectFieldEClass, SELECT_FIELD__SELECT_WIDGET);

		viewCollectionEClass = createEClass(VIEW_COLLECTION);
		createEReference(viewCollectionEClass, VIEW_COLLECTION__VIEWS);

		htmlFieldEClass = createEClass(HTML_FIELD);

		stylableEClass = createEClass(STYLABLE);
		createEReference(stylableEClass, STYLABLE__STYLING);

		paginableEClass = createEClass(PAGINABLE);
		createEReference(paginableEClass, PAGINABLE__PAGING);

		sortableEClass = createEClass(SORTABLE);
		createEReference(sortableEClass, SORTABLE__SORTING);

		editableEClass = createEClass(EDITABLE);
		createEAttribute(editableEClass, EDITABLE__EDITABLE);

		movableEClass = createEClass(MOVABLE);
		createEAttribute(movableEClass, MOVABLE__MOVABLE);

		filterableEClass = createEClass(FILTERABLE);
		createEReference(filterableEClass, FILTERABLE__FILTERING);

		filteringEClass = createEClass(FILTERING);
		createEAttribute(filteringEClass, FILTERING__DEFAULT_FILTER_VALUE);

		facetMapResultsViewEClass = createEClass(FACET_MAP_RESULTS_VIEW);
		createEReference(facetMapResultsViewEClass, FACET_MAP_RESULTS_VIEW__FACET_MAP_DEF);

		// Create enums
		sortOrderEEnum = createEEnum(SORT_ORDER);
		paginationStyleEEnum = createEEnum(PAGINATION_STYLE);
		halignEEnum = createEEnum(HALIGN);
		loadingTypeEEnum = createEEnum(LOADING_TYPE);
		widgetTextTypeEEnum = createEEnum(WIDGET_TEXT_TYPE);
		selectWidgetTypeEEnum = createEEnum(SELECT_WIDGET_TYPE);
		facetDisplayTypeEEnum = createEEnum(FACET_DISPLAY_TYPE);
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

		// Create type parameters

		// Set bounds for type parameters

		// Add supertypes to classes
		abstractDataTableEClass.getESuperTypes().add(this.getDataTableElement());
		abstractDataTableEClass.getESuperTypes().add(this.getAbstractView());
		abstractDataTableEClass.getESuperTypes().add(this.getPaginable());
		colEClass.getESuperTypes().add(this.getMovable());
		colEClass.getESuperTypes().add(this.getEditable());
		colEClass.getESuperTypes().add(this.getFilterable());
		colEClass.getESuperTypes().add(this.getFieldGroup());
		colEClass.getESuperTypes().add(this.getSortable());
		fieldGroupEClass.getESuperTypes().add(this.getFieldElement());
		fieldEClass.getESuperTypes().add(this.getFieldElement());
		textFieldEClass.getESuperTypes().add(this.getField());
		passwordFieldEClass.getESuperTypes().add(this.getField());
		booleanFieldEClass.getESuperTypes().add(this.getField());
		floatFieldEClass.getESuperTypes().add(this.getField());
		actionFieldEClass.getESuperTypes().add(this.getField());
		dateFieldEClass.getESuperTypes().add(this.getField());
		timeFieldEClass.getESuperTypes().add(this.getField());
		dateTimeFieldEClass.getESuperTypes().add(this.getField());
		phoneNumberFieldEClass.getESuperTypes().add(this.getField());
		emailFieldEClass.getESuperTypes().add(this.getField());
		fileFieldEClass.getESuperTypes().add(this.getField());
		fieldElementEClass.getESuperTypes().add(this.getStylable());
		fieldElementEClass.getESuperTypes().add(theCommonPackage.getNamedModelElement());
		facetMapEClass.getESuperTypes().add(this.getAbstractView());
		facetMapEClass.getESuperTypes().add(this.getPaginable());
		dataListEClass.getESuperTypes().add(this.getAbstractDataTable());
		treeEClass.getESuperTypes().add(this.getAbstractView());
		treeEClass.getESuperTypes().add(this.getSortable());
		treeEClass.getESuperTypes().add(this.getEditable());
		treeEClass.getESuperTypes().add(this.getMovable());
		treeEClass.getESuperTypes().add(this.getFilterable());
		dataTableEClass.getESuperTypes().add(this.getAbstractDataTable());
		stylingEClass.getESuperTypes().add(theCommonPackage.getMetaInfoGroup());
		abstractViewEClass.getESuperTypes().add(theCommonPackage.getNamedModelElement());
		abstractViewEClass.getESuperTypes().add(this.getStylable());
		selectFieldEClass.getESuperTypes().add(this.getField());
		viewCollectionEClass.getESuperTypes().add(theCommonPackage.getNamedModelElement());
		htmlFieldEClass.getESuperTypes().add(this.getField());
		facetMapResultsViewEClass.getESuperTypes().add(this.getDataList());

		// Initialize classes and features; add operations and parameters
		initEClass(abstractDataTableEClass, AbstractDataTable.class, "AbstractDataTable", IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEReference(getAbstractDataTable_HaveRowActions(), theCommonPackage.getOperationComponent(), null, "haveRowActions", null, 0, 1, AbstractDataTable.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getAbstractDataTable_HaveSelectActions(), theCommonPackage.getOperationComponent(), null, "haveSelectActions", null, 0, 1, AbstractDataTable.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getAbstractDataTable_HaveDefaultColActions(), theCommonPackage.getOperationComponent(), null, "haveDefaultColActions", null, 0, 1, AbstractDataTable.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(colEClass, Col.class, "Col", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEReference(getCol_Actions(), theCommonPackage.getOperationComponent(), null, "actions", null, 0, 1, Col.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(pagingEClass, Paging.class, "Paging", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEAttribute(getPaging_PaginationStyle(), this.getPaginationStyle(), "paginationStyle", null, 1, 1, Paging.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getPaging_MaxItems(), ecorePackage.getEInt(), "maxItems", null, 0, 1, Paging.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getPaging_MaxPage(), ecorePackage.getEInt(), "maxPage", null, 0, 1, Paging.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(sortingEClass, Sorting.class, "Sorting", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEAttribute(getSorting_SortOrder(), this.getSortOrder(), "sortOrder", null, 0, 1, Sorting.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getSorting_Sorted(), ecorePackage.getEBoolean(), "sorted", "false", 0, 1, Sorting.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(dataTableElementEClass, DataTableElement.class, "DataTableElement", IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);

		initEClass(fieldGroupEClass, FieldGroup.class, "FieldGroup", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEReference(getFieldGroup_Children(), this.getFieldElement(), null, "children", null, 0, -1, FieldGroup.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getFieldGroup_Disabled(), this.getFieldElement(), null, "disabled", null, 0, -1, FieldGroup.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(fieldEClass, Field.class, "Field", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEAttribute(getField_Patern(), ecorePackage.getEString(), "patern", null, 0, 1, Field.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getField_PatrenLanguage(), ecorePackage.getEString(), "patrenLanguage", null, 0, 1, Field.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(textFieldEClass, TextField.class, "TextField", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEAttribute(getTextField_WidgetType(), this.getWidgetTextType(), "widgetType", null, 0, 1, TextField.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(passwordFieldEClass, PasswordField.class, "PasswordField", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);

		initEClass(booleanFieldEClass, BooleanField.class, "BooleanField", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEAttribute(getBooleanField_Split(), ecorePackage.getEBoolean(), "split", null, 0, 1, BooleanField.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(floatFieldEClass, FloatField.class, "FloatField", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);

		initEClass(actionFieldEClass, ActionField.class, "ActionField", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEReference(getActionField_Operations(), theCommonPackage.getOperationComponent(), null, "operations", null, 0, -1, ActionField.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(dateFieldEClass, DateField.class, "DateField", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);

		initEClass(timeFieldEClass, TimeField.class, "TimeField", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);

		initEClass(dateTimeFieldEClass, DateTimeField.class, "DateTimeField", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);

		initEClass(phoneNumberFieldEClass, PhoneNumberField.class, "PhoneNumberField", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);

		initEClass(emailFieldEClass, EmailField.class, "EmailField", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);

		initEClass(fileFieldEClass, FileField.class, "FileField", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);

		initEClass(fieldElementEClass, FieldElement.class, "FieldElement", IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEReference(getFieldElement_MapTo(), theCommonPackage.getModelElement(), null, "mapTo", null, 0, 1, FieldElement.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getFieldElement_Prefix(), ecorePackage.getEString(), "prefix", null, 0, 1, FieldElement.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getFieldElement_Suffix(), ecorePackage.getEString(), "suffix", null, 0, 1, FieldElement.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getFieldElement_Hidden(), ecorePackage.getEBoolean(), "hidden", null, 0, 1, FieldElement.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(facetMapEClass, FacetMap.class, "FacetMap", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEReference(getFacetMap_Criterias(), this.getFieldGroup(), null, "criterias", null, 1, 1, FacetMap.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getFacetMap_DisplayEmptyFacet(), ecorePackage.getEBoolean(), "displayEmptyFacet", null, 0, 1, FacetMap.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getFacetMap_FacetDisplayType(), this.getFacetDisplayType(), "facetDisplayType", null, 0, 1, FacetMap.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getFacetMap_ResultsViewer(), this.getFacetMapResultsView(), this.getFacetMapResultsView_FacetMapDef(), "resultsViewer", null, 0, 1, FacetMap.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(dataListEClass, DataList.class, "DataList", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEReference(getDataList_Col(), this.getCol(), null, "col", null, 1, 1, DataList.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(treeEClass, Tree.class, "Tree", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEReference(getTree_NodeOperations(), theCommonPackage.getOperationComponent(), null, "nodeOperations", null, 0, 1, Tree.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getTree_NodeValue(), this.getFieldElement(), null, "nodeValue", null, 1, 1, Tree.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getTree_DefaultDepth(), ecorePackage.getEInt(), "defaultDepth", "1", 0, 1, Tree.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getTree_MaxDepth(), ecorePackage.getEInt(), "maxDepth", null, 0, 1, Tree.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(dataTableEClass, DataTable.class, "DataTable", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEReference(getDataTable_DefaultColSetUp(), this.getCol(), null, "defaultColSetUp", null, 0, 1, DataTable.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getDataTable_Cols(), this.getCol(), null, "cols", null, 0, -1, DataTable.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(stylingEClass, Styling.class, "Styling", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEAttribute(getStyling_Style(), ecorePackage.getEString(), "style", null, 0, 1, Styling.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getStyling_Height(), ecorePackage.getEInt(), "height", null, 0, 1, Styling.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getStyling_Width(), ecorePackage.getEInt(), "width", null, 0, 1, Styling.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(abstractViewEClass, AbstractView.class, "AbstractView", IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEReference(getAbstractView_ViewOf(), theCommonPackage.getModelElement(), null, "viewOf", null, 0, 1, AbstractView.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getAbstractView_Operations(), theCommonPackage.getOperationComponent(), null, "operations", null, 0, 1, AbstractView.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(selectFieldEClass, SelectField.class, "SelectField", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEAttribute(getSelectField_SelectWidget(), this.getSelectWidgetType(), "selectWidget", null, 0, 1, SelectField.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(viewCollectionEClass, ViewCollection.class, "ViewCollection", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEReference(getViewCollection_Views(), this.getAbstractView(), null, "views", null, 0, -1, ViewCollection.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(htmlFieldEClass, HtmlField.class, "HtmlField", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);

		initEClass(stylableEClass, Stylable.class, "Stylable", IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEReference(getStylable_Styling(), this.getStyling(), null, "styling", null, 0, 1, Stylable.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(paginableEClass, Paginable.class, "Paginable", IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEReference(getPaginable_Paging(), this.getPaging(), null, "paging", null, 0, 1, Paginable.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(sortableEClass, Sortable.class, "Sortable", IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEReference(getSortable_Sorting(), this.getSorting(), null, "sorting", null, 0, 1, Sortable.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(editableEClass, Editable.class, "Editable", IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEAttribute(getEditable_Editable(), ecorePackage.getEBoolean(), "editable", "false", 0, 1, Editable.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(movableEClass, Movable.class, "Movable", IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEAttribute(getMovable_Movable(), ecorePackage.getEBoolean(), "movable", null, 0, 1, Movable.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(filterableEClass, Filterable.class, "Filterable", IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEReference(getFilterable_Filtering(), this.getFiltering(), null, "filtering", null, 0, 1, Filterable.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(filteringEClass, Filtering.class, "Filtering", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEAttribute(getFiltering_DefaultFilterValue(), ecorePackage.getEString(), "defaultFilterValue", null, 0, 1, Filtering.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(facetMapResultsViewEClass, FacetMapResultsView.class, "FacetMapResultsView", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEReference(getFacetMapResultsView_FacetMapDef(), this.getFacetMap(), this.getFacetMap_ResultsViewer(), "facetMapDef", null, 1, 1, FacetMapResultsView.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		// Initialize enums and add enum literals
		initEEnum(sortOrderEEnum, SortOrder.class, "SortOrder");
		addEEnumLiteral(sortOrderEEnum, SortOrder.ASC);
		addEEnumLiteral(sortOrderEEnum, SortOrder.DESC);

		initEEnum(paginationStyleEEnum, PaginationStyle.class, "PaginationStyle");
		addEEnumLiteral(paginationStyleEEnum, PaginationStyle.PAGE);
		addEEnumLiteral(paginationStyleEEnum, PaginationStyle.SCROLL);
		addEEnumLiteral(paginationStyleEEnum, PaginationStyle.NONE);

		initEEnum(halignEEnum, Halign.class, "Halign");
		addEEnumLiteral(halignEEnum, Halign.CENTER);
		addEEnumLiteral(halignEEnum, Halign.LEFT);
		addEEnumLiteral(halignEEnum, Halign.RIGHT);

		initEEnum(loadingTypeEEnum, LoadingType.class, "LoadingType");
		addEEnumLiteral(loadingTypeEEnum, LoadingType.STATIC);
		addEEnumLiteral(loadingTypeEEnum, LoadingType.DYNAMIC);

		initEEnum(widgetTextTypeEEnum, WidgetTextType.class, "WidgetTextType");
		addEEnumLiteral(widgetTextTypeEEnum, WidgetTextType.MONOLINE);
		addEEnumLiteral(widgetTextTypeEEnum, WidgetTextType.MULTILINE);

		initEEnum(selectWidgetTypeEEnum, SelectWidgetType.class, "SelectWidgetType");
		addEEnumLiteral(selectWidgetTypeEEnum, SelectWidgetType.MONOLINE);
		addEEnumLiteral(selectWidgetTypeEEnum, SelectWidgetType.AUTOCOMPLETE);
		addEEnumLiteral(selectWidgetTypeEEnum, SelectWidgetType.MULTILINE);

		initEEnum(facetDisplayTypeEEnum, FacetDisplayType.class, "FacetDisplayType");
		addEEnumLiteral(facetDisplayTypeEEnum, FacetDisplayType.LIST);
		addEEnumLiteral(facetDisplayTypeEEnum, FacetDisplayType.CLOOD);
		addEEnumLiteral(facetDisplayTypeEEnum, FacetDisplayType.IMPROVED_CLOOD);

		// Create resource
		createResource(eNS_URI);

		// Create annotations
		// http://www.topcased.org/documentation
		createDocumentationAnnotations();
	}

	/**
	 * Initializes the annotations for <b>http://www.topcased.org/documentation</b>.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected void createDocumentationAnnotations() {
		String source = "http://www.topcased.org/documentation";		
		addAnnotation
		  (getAbstractDataTable_HaveRowActions(), 
		   source, 
		   new String[] {
			 "documentation", "set action list available on each row"
		   });		
		addAnnotation
		  (getAbstractDataTable_HaveSelectActions(), 
		   source, 
		   new String[] {
			 "documentation", "set available actions on selected rows"
		   });		
		addAnnotation
		  (getAbstractDataTable_HaveDefaultColActions(), 
		   source, 
		   new String[] {
			 "documentation", "use this to setup default actions available on each colomn"
		   });										
	}

} //ViewPackageImpl
