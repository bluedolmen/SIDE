/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package com.bluexml.side.view;

import com.bluexml.side.common.CommonPackage;

import org.eclipse.emf.ecore.EAttribute;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EEnum;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.EReference;

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
 * @see com.bluexml.side.view.ViewFactory
 * @model kind="package"
 * @generated
 */
public interface ViewPackage extends EPackage {
	/**
	 * The package name.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	String eNAME = "view";

	/**
	 * The package namespace URI.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	String eNS_URI = "http://www.kerblue.org/view/1.0";

	/**
	 * The package namespace name.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	String eNS_PREFIX = "view";

	/**
	 * The singleton instance of the package.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	ViewPackage eINSTANCE = com.bluexml.side.view.impl.ViewPackageImpl.init();

	/**
	 * The meta object id for the '{@link com.bluexml.side.view.impl.ViewCollectionImpl <em>Collection</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see com.bluexml.side.view.impl.ViewCollectionImpl
	 * @see com.bluexml.side.view.impl.ViewPackageImpl#getViewCollection()
	 * @generated
	 */
	int VIEW_COLLECTION = 0;

	/**
	 * The feature id for the '<em><b>Stereotypes</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int VIEW_COLLECTION__STEREOTYPES = CommonPackage.PACKAGE__STEREOTYPES;

	/**
	 * The feature id for the '<em><b>Tags</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int VIEW_COLLECTION__TAGS = CommonPackage.PACKAGE__TAGS;

	/**
	 * The feature id for the '<em><b>Comments</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int VIEW_COLLECTION__COMMENTS = CommonPackage.PACKAGE__COMMENTS;

	/**
	 * The feature id for the '<em><b>Documentation</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int VIEW_COLLECTION__DOCUMENTATION = CommonPackage.PACKAGE__DOCUMENTATION;

	/**
	 * The feature id for the '<em><b>Description</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int VIEW_COLLECTION__DESCRIPTION = CommonPackage.PACKAGE__DESCRIPTION;

	/**
	 * The feature id for the '<em><b>Metainfo</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int VIEW_COLLECTION__METAINFO = CommonPackage.PACKAGE__METAINFO;

	/**
	 * The feature id for the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int VIEW_COLLECTION__NAME = CommonPackage.PACKAGE__NAME;

	/**
	 * The feature id for the '<em><b>Stereotype Set</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int VIEW_COLLECTION__STEREOTYPE_SET = CommonPackage.PACKAGE__STEREOTYPE_SET;

	/**
	 * The feature id for the '<em><b>Package Set</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int VIEW_COLLECTION__PACKAGE_SET = CommonPackage.PACKAGE__PACKAGE_SET;

	/**
	 * The feature id for the '<em><b>Views</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int VIEW_COLLECTION__VIEWS = CommonPackage.PACKAGE_FEATURE_COUNT + 0;

	/**
	 * The feature id for the '<em><b>Composed Views</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int VIEW_COLLECTION__COMPOSED_VIEWS = CommonPackage.PACKAGE_FEATURE_COUNT + 1;

	/**
	 * The number of structural features of the '<em>Collection</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int VIEW_COLLECTION_FEATURE_COUNT = CommonPackage.PACKAGE_FEATURE_COUNT + 2;

	/**
	 * The meta object id for the '{@link com.bluexml.side.view.impl.StylableImpl <em>Stylable</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see com.bluexml.side.view.impl.StylableImpl
	 * @see com.bluexml.side.view.impl.ViewPackageImpl#getStylable()
	 * @generated
	 */
	int STYLABLE = 34;

	/**
	 * The feature id for the '<em><b>Styling</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int STYLABLE__STYLING = 0;

	/**
	 * The number of structural features of the '<em>Stylable</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int STYLABLE_FEATURE_COUNT = 1;

	/**
	 * The meta object id for the '{@link com.bluexml.side.view.impl.FieldElementImpl <em>Field Element</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see com.bluexml.side.view.impl.FieldElementImpl
	 * @see com.bluexml.side.view.impl.ViewPackageImpl#getFieldElement()
	 * @generated
	 */
	int FIELD_ELEMENT = 2;

	/**
	 * The feature id for the '<em><b>Styling</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int FIELD_ELEMENT__STYLING = STYLABLE__STYLING;

	/**
	 * The feature id for the '<em><b>Stereotypes</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int FIELD_ELEMENT__STEREOTYPES = STYLABLE_FEATURE_COUNT + 0;

	/**
	 * The feature id for the '<em><b>Tags</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int FIELD_ELEMENT__TAGS = STYLABLE_FEATURE_COUNT + 1;

	/**
	 * The feature id for the '<em><b>Comments</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int FIELD_ELEMENT__COMMENTS = STYLABLE_FEATURE_COUNT + 2;

	/**
	 * The feature id for the '<em><b>Documentation</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int FIELD_ELEMENT__DOCUMENTATION = STYLABLE_FEATURE_COUNT + 3;

	/**
	 * The feature id for the '<em><b>Description</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int FIELD_ELEMENT__DESCRIPTION = STYLABLE_FEATURE_COUNT + 4;

	/**
	 * The feature id for the '<em><b>Metainfo</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int FIELD_ELEMENT__METAINFO = STYLABLE_FEATURE_COUNT + 5;

	/**
	 * The feature id for the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int FIELD_ELEMENT__NAME = STYLABLE_FEATURE_COUNT + 6;

	/**
	 * The feature id for the '<em><b>Map To</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int FIELD_ELEMENT__MAP_TO = STYLABLE_FEATURE_COUNT + 7;

	/**
	 * The feature id for the '<em><b>Prefix</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int FIELD_ELEMENT__PREFIX = STYLABLE_FEATURE_COUNT + 8;

	/**
	 * The feature id for the '<em><b>Suffix</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int FIELD_ELEMENT__SUFFIX = STYLABLE_FEATURE_COUNT + 9;

	/**
	 * The feature id for the '<em><b>Hidden</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int FIELD_ELEMENT__HIDDEN = STYLABLE_FEATURE_COUNT + 10;

	/**
	 * The number of structural features of the '<em>Field Element</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int FIELD_ELEMENT_FEATURE_COUNT = STYLABLE_FEATURE_COUNT + 11;

	/**
	 * The meta object id for the '{@link com.bluexml.side.view.impl.FieldContainerImpl <em>Field Container</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see com.bluexml.side.view.impl.FieldContainerImpl
	 * @see com.bluexml.side.view.impl.ViewPackageImpl#getFieldContainer()
	 * @generated
	 */
	int FIELD_CONTAINER = 1;

	/**
	 * The feature id for the '<em><b>Styling</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int FIELD_CONTAINER__STYLING = FIELD_ELEMENT__STYLING;

	/**
	 * The feature id for the '<em><b>Stereotypes</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int FIELD_CONTAINER__STEREOTYPES = FIELD_ELEMENT__STEREOTYPES;

	/**
	 * The feature id for the '<em><b>Tags</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int FIELD_CONTAINER__TAGS = FIELD_ELEMENT__TAGS;

	/**
	 * The feature id for the '<em><b>Comments</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int FIELD_CONTAINER__COMMENTS = FIELD_ELEMENT__COMMENTS;

	/**
	 * The feature id for the '<em><b>Documentation</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int FIELD_CONTAINER__DOCUMENTATION = FIELD_ELEMENT__DOCUMENTATION;

	/**
	 * The feature id for the '<em><b>Description</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int FIELD_CONTAINER__DESCRIPTION = FIELD_ELEMENT__DESCRIPTION;

	/**
	 * The feature id for the '<em><b>Metainfo</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int FIELD_CONTAINER__METAINFO = FIELD_ELEMENT__METAINFO;

	/**
	 * The feature id for the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int FIELD_CONTAINER__NAME = FIELD_ELEMENT__NAME;

	/**
	 * The feature id for the '<em><b>Map To</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int FIELD_CONTAINER__MAP_TO = FIELD_ELEMENT__MAP_TO;

	/**
	 * The feature id for the '<em><b>Prefix</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int FIELD_CONTAINER__PREFIX = FIELD_ELEMENT__PREFIX;

	/**
	 * The feature id for the '<em><b>Suffix</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int FIELD_CONTAINER__SUFFIX = FIELD_ELEMENT__SUFFIX;

	/**
	 * The feature id for the '<em><b>Hidden</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int FIELD_CONTAINER__HIDDEN = FIELD_ELEMENT__HIDDEN;

	/**
	 * The feature id for the '<em><b>Children</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int FIELD_CONTAINER__CHILDREN = FIELD_ELEMENT_FEATURE_COUNT + 0;

	/**
	 * The feature id for the '<em><b>Disabled</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int FIELD_CONTAINER__DISABLED = FIELD_ELEMENT_FEATURE_COUNT + 1;

	/**
	 * The number of structural features of the '<em>Field Container</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int FIELD_CONTAINER_FEATURE_COUNT = FIELD_ELEMENT_FEATURE_COUNT + 2;

	/**
	 * The meta object id for the '{@link com.bluexml.side.view.impl.AbstractViewImpl <em>Abstract View</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see com.bluexml.side.view.impl.AbstractViewImpl
	 * @see com.bluexml.side.view.impl.ViewPackageImpl#getAbstractView()
	 * @generated
	 */
	int ABSTRACT_VIEW = 3;

	/**
	 * The feature id for the '<em><b>Styling</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ABSTRACT_VIEW__STYLING = FIELD_CONTAINER__STYLING;

	/**
	 * The feature id for the '<em><b>Stereotypes</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ABSTRACT_VIEW__STEREOTYPES = FIELD_CONTAINER__STEREOTYPES;

	/**
	 * The feature id for the '<em><b>Tags</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ABSTRACT_VIEW__TAGS = FIELD_CONTAINER__TAGS;

	/**
	 * The feature id for the '<em><b>Comments</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ABSTRACT_VIEW__COMMENTS = FIELD_CONTAINER__COMMENTS;

	/**
	 * The feature id for the '<em><b>Documentation</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ABSTRACT_VIEW__DOCUMENTATION = FIELD_CONTAINER__DOCUMENTATION;

	/**
	 * The feature id for the '<em><b>Description</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ABSTRACT_VIEW__DESCRIPTION = FIELD_CONTAINER__DESCRIPTION;

	/**
	 * The feature id for the '<em><b>Metainfo</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ABSTRACT_VIEW__METAINFO = FIELD_CONTAINER__METAINFO;

	/**
	 * The feature id for the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ABSTRACT_VIEW__NAME = FIELD_CONTAINER__NAME;

	/**
	 * The feature id for the '<em><b>Map To</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ABSTRACT_VIEW__MAP_TO = FIELD_CONTAINER__MAP_TO;

	/**
	 * The feature id for the '<em><b>Prefix</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ABSTRACT_VIEW__PREFIX = FIELD_CONTAINER__PREFIX;

	/**
	 * The feature id for the '<em><b>Suffix</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ABSTRACT_VIEW__SUFFIX = FIELD_CONTAINER__SUFFIX;

	/**
	 * The feature id for the '<em><b>Hidden</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ABSTRACT_VIEW__HIDDEN = FIELD_CONTAINER__HIDDEN;

	/**
	 * The feature id for the '<em><b>Children</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ABSTRACT_VIEW__CHILDREN = FIELD_CONTAINER__CHILDREN;

	/**
	 * The feature id for the '<em><b>Disabled</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ABSTRACT_VIEW__DISABLED = FIELD_CONTAINER__DISABLED;

	/**
	 * The number of structural features of the '<em>Abstract View</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ABSTRACT_VIEW_FEATURE_COUNT = FIELD_CONTAINER_FEATURE_COUNT + 0;

	/**
	 * The meta object id for the '{@link com.bluexml.side.view.impl.AbstractDataTableImpl <em>Abstract Data Table</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see com.bluexml.side.view.impl.AbstractDataTableImpl
	 * @see com.bluexml.side.view.impl.ViewPackageImpl#getAbstractDataTable()
	 * @generated
	 */
	int ABSTRACT_DATA_TABLE = 5;

	/**
	 * The meta object id for the '{@link com.bluexml.side.view.impl.ColImpl <em>Col</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see com.bluexml.side.view.impl.ColImpl
	 * @see com.bluexml.side.view.impl.ViewPackageImpl#getCol()
	 * @generated
	 */
	int COL = 6;

	/**
	 * The meta object id for the '{@link com.bluexml.side.view.impl.PagingImpl <em>Paging</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see com.bluexml.side.view.impl.PagingImpl
	 * @see com.bluexml.side.view.impl.ViewPackageImpl#getPaging()
	 * @generated
	 */
	int PAGING = 7;

	/**
	 * The meta object id for the '{@link com.bluexml.side.view.impl.SortingImpl <em>Sorting</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see com.bluexml.side.view.impl.SortingImpl
	 * @see com.bluexml.side.view.impl.ViewPackageImpl#getSorting()
	 * @generated
	 */
	int SORTING = 8;

	/**
	 * The meta object id for the '{@link com.bluexml.side.view.impl.FilteringImpl <em>Filtering</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see com.bluexml.side.view.impl.FilteringImpl
	 * @see com.bluexml.side.view.impl.ViewPackageImpl#getFiltering()
	 * @generated
	 */
	int FILTERING = 9;

	/**
	 * The meta object id for the '{@link com.bluexml.side.view.impl.StylingImpl <em>Styling</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see com.bluexml.side.view.impl.StylingImpl
	 * @see com.bluexml.side.view.impl.ViewPackageImpl#getStyling()
	 * @generated
	 */
	int STYLING = 10;

	/**
	 * The meta object id for the '{@link com.bluexml.side.view.impl.DataTableElementImpl <em>Data Table Element</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see com.bluexml.side.view.impl.DataTableElementImpl
	 * @see com.bluexml.side.view.impl.ViewPackageImpl#getDataTableElement()
	 * @generated
	 */
	int DATA_TABLE_ELEMENT = 11;

	/**
	 * The meta object id for the '{@link com.bluexml.side.view.impl.DataListImpl <em>Data List</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see com.bluexml.side.view.impl.DataListImpl
	 * @see com.bluexml.side.view.impl.ViewPackageImpl#getDataList()
	 * @generated
	 */
	int DATA_LIST = 12;

	/**
	 * The meta object id for the '{@link com.bluexml.side.view.impl.DataTableImpl <em>Data Table</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see com.bluexml.side.view.impl.DataTableImpl
	 * @see com.bluexml.side.view.impl.ViewPackageImpl#getDataTable()
	 * @generated
	 */
	int DATA_TABLE = 13;

	/**
	 * The meta object id for the '{@link com.bluexml.side.view.impl.FacetMapImpl <em>Facet Map</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see com.bluexml.side.view.impl.FacetMapImpl
	 * @see com.bluexml.side.view.impl.ViewPackageImpl#getFacetMap()
	 * @generated
	 */
	int FACET_MAP = 14;

	/**
	 * The meta object id for the '{@link com.bluexml.side.view.impl.TreeImpl <em>Tree</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see com.bluexml.side.view.impl.TreeImpl
	 * @see com.bluexml.side.view.impl.ViewPackageImpl#getTree()
	 * @generated
	 */
	int TREE = 15;

	/**
	 * The meta object id for the '{@link com.bluexml.side.view.impl.ComposedViewImpl <em>Composed View</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see com.bluexml.side.view.impl.ComposedViewImpl
	 * @see com.bluexml.side.view.impl.ViewPackageImpl#getComposedView()
	 * @generated
	 */
	int COMPOSED_VIEW = 16;

	/**
	 * The meta object id for the '{@link com.bluexml.side.view.impl.FieldImpl <em>Field</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see com.bluexml.side.view.impl.FieldImpl
	 * @see com.bluexml.side.view.impl.ViewPackageImpl#getField()
	 * @generated
	 */
	int FIELD = 17;

	/**
	 * The meta object id for the '{@link com.bluexml.side.view.impl.TextFieldImpl <em>Text Field</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see com.bluexml.side.view.impl.TextFieldImpl
	 * @see com.bluexml.side.view.impl.ViewPackageImpl#getTextField()
	 * @generated
	 */
	int TEXT_FIELD = 18;

	/**
	 * The meta object id for the '{@link com.bluexml.side.view.impl.PasswordFieldImpl <em>Password Field</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see com.bluexml.side.view.impl.PasswordFieldImpl
	 * @see com.bluexml.side.view.impl.ViewPackageImpl#getPasswordField()
	 * @generated
	 */
	int PASSWORD_FIELD = 20;

	/**
	 * The meta object id for the '{@link com.bluexml.side.view.impl.BooleanFieldImpl <em>Boolean Field</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see com.bluexml.side.view.impl.BooleanFieldImpl
	 * @see com.bluexml.side.view.impl.ViewPackageImpl#getBooleanField()
	 * @generated
	 */
	int BOOLEAN_FIELD = 19;

	/**
	 * The meta object id for the '{@link com.bluexml.side.view.impl.FloatFieldImpl <em>Float Field</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see com.bluexml.side.view.impl.FloatFieldImpl
	 * @see com.bluexml.side.view.impl.ViewPackageImpl#getFloatField()
	 * @generated
	 */
	int FLOAT_FIELD = 21;

	/**
	 * The meta object id for the '{@link com.bluexml.side.view.impl.ActionFieldImpl <em>Action Field</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see com.bluexml.side.view.impl.ActionFieldImpl
	 * @see com.bluexml.side.view.impl.ViewPackageImpl#getActionField()
	 * @generated
	 */
	int ACTION_FIELD = 22;

	/**
	 * The meta object id for the '{@link com.bluexml.side.view.impl.DateFieldImpl <em>Date Field</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see com.bluexml.side.view.impl.DateFieldImpl
	 * @see com.bluexml.side.view.impl.ViewPackageImpl#getDateField()
	 * @generated
	 */
	int DATE_FIELD = 23;

	/**
	 * The meta object id for the '{@link com.bluexml.side.view.impl.TimeFieldImpl <em>Time Field</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see com.bluexml.side.view.impl.TimeFieldImpl
	 * @see com.bluexml.side.view.impl.ViewPackageImpl#getTimeField()
	 * @generated
	 */
	int TIME_FIELD = 24;

	/**
	 * The meta object id for the '{@link com.bluexml.side.view.impl.DateTimeFieldImpl <em>Date Time Field</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see com.bluexml.side.view.impl.DateTimeFieldImpl
	 * @see com.bluexml.side.view.impl.ViewPackageImpl#getDateTimeField()
	 * @generated
	 */
	int DATE_TIME_FIELD = 25;

	/**
	 * The meta object id for the '{@link com.bluexml.side.view.impl.PhoneNumberFieldImpl <em>Phone Number Field</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see com.bluexml.side.view.impl.PhoneNumberFieldImpl
	 * @see com.bluexml.side.view.impl.ViewPackageImpl#getPhoneNumberField()
	 * @generated
	 */
	int PHONE_NUMBER_FIELD = 26;

	/**
	 * The meta object id for the '{@link com.bluexml.side.view.impl.EmailFieldImpl <em>Email Field</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see com.bluexml.side.view.impl.EmailFieldImpl
	 * @see com.bluexml.side.view.impl.ViewPackageImpl#getEmailField()
	 * @generated
	 */
	int EMAIL_FIELD = 27;

	/**
	 * The meta object id for the '{@link com.bluexml.side.view.impl.IntegerFieldImpl <em>Integer Field</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see com.bluexml.side.view.impl.IntegerFieldImpl
	 * @see com.bluexml.side.view.impl.ViewPackageImpl#getIntegerField()
	 * @generated
	 */
	int INTEGER_FIELD = 28;

	/**
	 * The meta object id for the '{@link com.bluexml.side.view.impl.FileFieldImpl <em>File Field</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see com.bluexml.side.view.impl.FileFieldImpl
	 * @see com.bluexml.side.view.impl.ViewPackageImpl#getFileField()
	 * @generated
	 */
	int FILE_FIELD = 29;

	/**
	 * The meta object id for the '{@link com.bluexml.side.view.impl.SelectFieldImpl <em>Select Field</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see com.bluexml.side.view.impl.SelectFieldImpl
	 * @see com.bluexml.side.view.impl.ViewPackageImpl#getSelectField()
	 * @generated
	 */
	int SELECT_FIELD = 30;

	/**
	 * The meta object id for the '{@link com.bluexml.side.view.impl.HtmlFieldImpl <em>Html Field</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see com.bluexml.side.view.impl.HtmlFieldImpl
	 * @see com.bluexml.side.view.impl.ViewPackageImpl#getHtmlField()
	 * @generated
	 */
	int HTML_FIELD = 31;

	/**
	 * The meta object id for the '{@link com.bluexml.side.view.impl.URLFieldImpl <em>URL Field</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see com.bluexml.side.view.impl.URLFieldImpl
	 * @see com.bluexml.side.view.impl.ViewPackageImpl#getURLField()
	 * @generated
	 */
	int URL_FIELD = 32;

	/**
	 * The meta object id for the '{@link com.bluexml.side.view.impl.ImageFieldImpl <em>Image Field</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see com.bluexml.side.view.impl.ImageFieldImpl
	 * @see com.bluexml.side.view.impl.ViewPackageImpl#getImageField()
	 * @generated
	 */
	int IMAGE_FIELD = 33;

	/**
	 * The meta object id for the '{@link com.bluexml.side.view.impl.PaginableImpl <em>Paginable</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see com.bluexml.side.view.impl.PaginableImpl
	 * @see com.bluexml.side.view.impl.ViewPackageImpl#getPaginable()
	 * @generated
	 */
	int PAGINABLE = 35;

	/**
	 * The meta object id for the '{@link com.bluexml.side.view.impl.SortableImpl <em>Sortable</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see com.bluexml.side.view.impl.SortableImpl
	 * @see com.bluexml.side.view.impl.ViewPackageImpl#getSortable()
	 * @generated
	 */
	int SORTABLE = 37;

	/**
	 * The meta object id for the '{@link com.bluexml.side.view.impl.EditableImpl <em>Editable</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see com.bluexml.side.view.impl.EditableImpl
	 * @see com.bluexml.side.view.impl.ViewPackageImpl#getEditable()
	 * @generated
	 */
	int EDITABLE = 38;

	/**
	 * The meta object id for the '{@link com.bluexml.side.view.impl.MovableImpl <em>Movable</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see com.bluexml.side.view.impl.MovableImpl
	 * @see com.bluexml.side.view.impl.ViewPackageImpl#getMovable()
	 * @generated
	 */
	int MOVABLE = 39;

	/**
	 * The meta object id for the '{@link com.bluexml.side.view.impl.FilterableImpl <em>Filterable</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see com.bluexml.side.view.impl.FilterableImpl
	 * @see com.bluexml.side.view.impl.ViewPackageImpl#getFilterable()
	 * @generated
	 */
	int FILTERABLE = 40;

	/**
	 * The meta object id for the '{@link com.bluexml.side.view.impl.FieldGroupImpl <em>Field Group</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see com.bluexml.side.view.impl.FieldGroupImpl
	 * @see com.bluexml.side.view.impl.ViewPackageImpl#getFieldGroup()
	 * @generated
	 */
	int FIELD_GROUP = 41;

	/**
	 * The meta object id for the '{@link com.bluexml.side.view.impl.AbstractViewOfImpl <em>Abstract View Of</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see com.bluexml.side.view.impl.AbstractViewOfImpl
	 * @see com.bluexml.side.view.impl.ViewPackageImpl#getAbstractViewOf()
	 * @generated
	 */
	int ABSTRACT_VIEW_OF = 4;

	/**
	 * The feature id for the '<em><b>Styling</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ABSTRACT_VIEW_OF__STYLING = ABSTRACT_VIEW__STYLING;

	/**
	 * The feature id for the '<em><b>Stereotypes</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ABSTRACT_VIEW_OF__STEREOTYPES = ABSTRACT_VIEW__STEREOTYPES;

	/**
	 * The feature id for the '<em><b>Tags</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ABSTRACT_VIEW_OF__TAGS = ABSTRACT_VIEW__TAGS;

	/**
	 * The feature id for the '<em><b>Comments</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ABSTRACT_VIEW_OF__COMMENTS = ABSTRACT_VIEW__COMMENTS;

	/**
	 * The feature id for the '<em><b>Documentation</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ABSTRACT_VIEW_OF__DOCUMENTATION = ABSTRACT_VIEW__DOCUMENTATION;

	/**
	 * The feature id for the '<em><b>Description</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ABSTRACT_VIEW_OF__DESCRIPTION = ABSTRACT_VIEW__DESCRIPTION;

	/**
	 * The feature id for the '<em><b>Metainfo</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ABSTRACT_VIEW_OF__METAINFO = ABSTRACT_VIEW__METAINFO;

	/**
	 * The feature id for the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ABSTRACT_VIEW_OF__NAME = ABSTRACT_VIEW__NAME;

	/**
	 * The feature id for the '<em><b>Map To</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ABSTRACT_VIEW_OF__MAP_TO = ABSTRACT_VIEW__MAP_TO;

	/**
	 * The feature id for the '<em><b>Prefix</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ABSTRACT_VIEW_OF__PREFIX = ABSTRACT_VIEW__PREFIX;

	/**
	 * The feature id for the '<em><b>Suffix</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ABSTRACT_VIEW_OF__SUFFIX = ABSTRACT_VIEW__SUFFIX;

	/**
	 * The feature id for the '<em><b>Hidden</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ABSTRACT_VIEW_OF__HIDDEN = ABSTRACT_VIEW__HIDDEN;

	/**
	 * The feature id for the '<em><b>Children</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ABSTRACT_VIEW_OF__CHILDREN = ABSTRACT_VIEW__CHILDREN;

	/**
	 * The feature id for the '<em><b>Disabled</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ABSTRACT_VIEW_OF__DISABLED = ABSTRACT_VIEW__DISABLED;

	/**
	 * The feature id for the '<em><b>View Of</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ABSTRACT_VIEW_OF__VIEW_OF = ABSTRACT_VIEW_FEATURE_COUNT + 0;

	/**
	 * The feature id for the '<em><b>Generate Webscript</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ABSTRACT_VIEW_OF__GENERATE_WEBSCRIPT = ABSTRACT_VIEW_FEATURE_COUNT + 1;

	/**
	 * The number of structural features of the '<em>Abstract View Of</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ABSTRACT_VIEW_OF_FEATURE_COUNT = ABSTRACT_VIEW_FEATURE_COUNT + 2;

	/**
	 * The feature id for the '<em><b>Styling</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ABSTRACT_DATA_TABLE__STYLING = ABSTRACT_VIEW_OF__STYLING;

	/**
	 * The feature id for the '<em><b>Stereotypes</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ABSTRACT_DATA_TABLE__STEREOTYPES = ABSTRACT_VIEW_OF__STEREOTYPES;

	/**
	 * The feature id for the '<em><b>Tags</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ABSTRACT_DATA_TABLE__TAGS = ABSTRACT_VIEW_OF__TAGS;

	/**
	 * The feature id for the '<em><b>Comments</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ABSTRACT_DATA_TABLE__COMMENTS = ABSTRACT_VIEW_OF__COMMENTS;

	/**
	 * The feature id for the '<em><b>Documentation</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ABSTRACT_DATA_TABLE__DOCUMENTATION = ABSTRACT_VIEW_OF__DOCUMENTATION;

	/**
	 * The feature id for the '<em><b>Description</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ABSTRACT_DATA_TABLE__DESCRIPTION = ABSTRACT_VIEW_OF__DESCRIPTION;

	/**
	 * The feature id for the '<em><b>Metainfo</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ABSTRACT_DATA_TABLE__METAINFO = ABSTRACT_VIEW_OF__METAINFO;

	/**
	 * The feature id for the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ABSTRACT_DATA_TABLE__NAME = ABSTRACT_VIEW_OF__NAME;

	/**
	 * The feature id for the '<em><b>Map To</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ABSTRACT_DATA_TABLE__MAP_TO = ABSTRACT_VIEW_OF__MAP_TO;

	/**
	 * The feature id for the '<em><b>Prefix</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ABSTRACT_DATA_TABLE__PREFIX = ABSTRACT_VIEW_OF__PREFIX;

	/**
	 * The feature id for the '<em><b>Suffix</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ABSTRACT_DATA_TABLE__SUFFIX = ABSTRACT_VIEW_OF__SUFFIX;

	/**
	 * The feature id for the '<em><b>Hidden</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ABSTRACT_DATA_TABLE__HIDDEN = ABSTRACT_VIEW_OF__HIDDEN;

	/**
	 * The feature id for the '<em><b>Children</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ABSTRACT_DATA_TABLE__CHILDREN = ABSTRACT_VIEW_OF__CHILDREN;

	/**
	 * The feature id for the '<em><b>Disabled</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ABSTRACT_DATA_TABLE__DISABLED = ABSTRACT_VIEW_OF__DISABLED;

	/**
	 * The feature id for the '<em><b>View Of</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ABSTRACT_DATA_TABLE__VIEW_OF = ABSTRACT_VIEW_OF__VIEW_OF;

	/**
	 * The feature id for the '<em><b>Generate Webscript</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ABSTRACT_DATA_TABLE__GENERATE_WEBSCRIPT = ABSTRACT_VIEW_OF__GENERATE_WEBSCRIPT;

	/**
	 * The feature id for the '<em><b>Paging</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ABSTRACT_DATA_TABLE__PAGING = ABSTRACT_VIEW_OF_FEATURE_COUNT + 0;

	/**
	 * The feature id for the '<em><b>Have Row Actions</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ABSTRACT_DATA_TABLE__HAVE_ROW_ACTIONS = ABSTRACT_VIEW_OF_FEATURE_COUNT + 1;

	/**
	 * The feature id for the '<em><b>Have Select Actions</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ABSTRACT_DATA_TABLE__HAVE_SELECT_ACTIONS = ABSTRACT_VIEW_OF_FEATURE_COUNT + 2;

	/**
	 * The feature id for the '<em><b>Have Default Col Actions</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ABSTRACT_DATA_TABLE__HAVE_DEFAULT_COL_ACTIONS = ABSTRACT_VIEW_OF_FEATURE_COUNT + 3;

	/**
	 * The number of structural features of the '<em>Abstract Data Table</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ABSTRACT_DATA_TABLE_FEATURE_COUNT = ABSTRACT_VIEW_OF_FEATURE_COUNT + 4;

	/**
	 * The feature id for the '<em><b>Styling</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int COL__STYLING = FIELD_CONTAINER__STYLING;

	/**
	 * The feature id for the '<em><b>Stereotypes</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int COL__STEREOTYPES = FIELD_CONTAINER__STEREOTYPES;

	/**
	 * The feature id for the '<em><b>Tags</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int COL__TAGS = FIELD_CONTAINER__TAGS;

	/**
	 * The feature id for the '<em><b>Comments</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int COL__COMMENTS = FIELD_CONTAINER__COMMENTS;

	/**
	 * The feature id for the '<em><b>Documentation</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int COL__DOCUMENTATION = FIELD_CONTAINER__DOCUMENTATION;

	/**
	 * The feature id for the '<em><b>Description</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int COL__DESCRIPTION = FIELD_CONTAINER__DESCRIPTION;

	/**
	 * The feature id for the '<em><b>Metainfo</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int COL__METAINFO = FIELD_CONTAINER__METAINFO;

	/**
	 * The feature id for the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int COL__NAME = FIELD_CONTAINER__NAME;

	/**
	 * The feature id for the '<em><b>Map To</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int COL__MAP_TO = FIELD_CONTAINER__MAP_TO;

	/**
	 * The feature id for the '<em><b>Prefix</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int COL__PREFIX = FIELD_CONTAINER__PREFIX;

	/**
	 * The feature id for the '<em><b>Suffix</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int COL__SUFFIX = FIELD_CONTAINER__SUFFIX;

	/**
	 * The feature id for the '<em><b>Hidden</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int COL__HIDDEN = FIELD_CONTAINER__HIDDEN;

	/**
	 * The feature id for the '<em><b>Children</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int COL__CHILDREN = FIELD_CONTAINER__CHILDREN;

	/**
	 * The feature id for the '<em><b>Disabled</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int COL__DISABLED = FIELD_CONTAINER__DISABLED;

	/**
	 * The feature id for the '<em><b>Movable</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int COL__MOVABLE = FIELD_CONTAINER_FEATURE_COUNT + 0;

	/**
	 * The feature id for the '<em><b>Editable</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int COL__EDITABLE = FIELD_CONTAINER_FEATURE_COUNT + 1;

	/**
	 * The feature id for the '<em><b>Filtering</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int COL__FILTERING = FIELD_CONTAINER_FEATURE_COUNT + 2;

	/**
	 * The feature id for the '<em><b>Sorting</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int COL__SORTING = FIELD_CONTAINER_FEATURE_COUNT + 3;

	/**
	 * The feature id for the '<em><b>Operations</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int COL__OPERATIONS = FIELD_CONTAINER_FEATURE_COUNT + 4;

	/**
	 * The number of structural features of the '<em>Col</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int COL_FEATURE_COUNT = FIELD_CONTAINER_FEATURE_COUNT + 5;

	/**
	 * The feature id for the '<em><b>Pagination Style</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PAGING__PAGINATION_STYLE = 0;

	/**
	 * The feature id for the '<em><b>Max Items</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PAGING__MAX_ITEMS = 1;

	/**
	 * The feature id for the '<em><b>Max Page</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PAGING__MAX_PAGE = 2;

	/**
	 * The number of structural features of the '<em>Paging</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PAGING_FEATURE_COUNT = 3;

	/**
	 * The feature id for the '<em><b>Sort Order</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SORTING__SORT_ORDER = 0;

	/**
	 * The feature id for the '<em><b>Sorted</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SORTING__SORTED = 1;

	/**
	 * The number of structural features of the '<em>Sorting</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SORTING_FEATURE_COUNT = 2;

	/**
	 * The feature id for the '<em><b>Default Filter Value</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int FILTERING__DEFAULT_FILTER_VALUE = 0;

	/**
	 * The number of structural features of the '<em>Filtering</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int FILTERING_FEATURE_COUNT = 1;

	/**
	 * The feature id for the '<em><b>Stereotypes</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int STYLING__STEREOTYPES = CommonPackage.META_INFO_GROUP__STEREOTYPES;

	/**
	 * The feature id for the '<em><b>Tags</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int STYLING__TAGS = CommonPackage.META_INFO_GROUP__TAGS;

	/**
	 * The feature id for the '<em><b>Comments</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int STYLING__COMMENTS = CommonPackage.META_INFO_GROUP__COMMENTS;

	/**
	 * The feature id for the '<em><b>Documentation</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int STYLING__DOCUMENTATION = CommonPackage.META_INFO_GROUP__DOCUMENTATION;

	/**
	 * The feature id for the '<em><b>Description</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int STYLING__DESCRIPTION = CommonPackage.META_INFO_GROUP__DESCRIPTION;

	/**
	 * The feature id for the '<em><b>Metainfo</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int STYLING__METAINFO = CommonPackage.META_INFO_GROUP__METAINFO;

	/**
	 * The feature id for the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int STYLING__NAME = CommonPackage.META_INFO_GROUP__NAME;

	/**
	 * The feature id for the '<em><b>Children</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int STYLING__CHILDREN = CommonPackage.META_INFO_GROUP__CHILDREN;

	/**
	 * The feature id for the '<em><b>Style</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int STYLING__STYLE = CommonPackage.META_INFO_GROUP_FEATURE_COUNT + 0;

	/**
	 * The feature id for the '<em><b>Height</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int STYLING__HEIGHT = CommonPackage.META_INFO_GROUP_FEATURE_COUNT + 1;

	/**
	 * The feature id for the '<em><b>Width</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int STYLING__WIDTH = CommonPackage.META_INFO_GROUP_FEATURE_COUNT + 2;

	/**
	 * The number of structural features of the '<em>Styling</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int STYLING_FEATURE_COUNT = CommonPackage.META_INFO_GROUP_FEATURE_COUNT + 3;

	/**
	 * The number of structural features of the '<em>Data Table Element</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DATA_TABLE_ELEMENT_FEATURE_COUNT = 0;

	/**
	 * The feature id for the '<em><b>Styling</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DATA_LIST__STYLING = ABSTRACT_DATA_TABLE__STYLING;

	/**
	 * The feature id for the '<em><b>Stereotypes</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DATA_LIST__STEREOTYPES = ABSTRACT_DATA_TABLE__STEREOTYPES;

	/**
	 * The feature id for the '<em><b>Tags</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DATA_LIST__TAGS = ABSTRACT_DATA_TABLE__TAGS;

	/**
	 * The feature id for the '<em><b>Comments</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DATA_LIST__COMMENTS = ABSTRACT_DATA_TABLE__COMMENTS;

	/**
	 * The feature id for the '<em><b>Documentation</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DATA_LIST__DOCUMENTATION = ABSTRACT_DATA_TABLE__DOCUMENTATION;

	/**
	 * The feature id for the '<em><b>Description</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DATA_LIST__DESCRIPTION = ABSTRACT_DATA_TABLE__DESCRIPTION;

	/**
	 * The feature id for the '<em><b>Metainfo</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DATA_LIST__METAINFO = ABSTRACT_DATA_TABLE__METAINFO;

	/**
	 * The feature id for the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DATA_LIST__NAME = ABSTRACT_DATA_TABLE__NAME;

	/**
	 * The feature id for the '<em><b>Map To</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DATA_LIST__MAP_TO = ABSTRACT_DATA_TABLE__MAP_TO;

	/**
	 * The feature id for the '<em><b>Prefix</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DATA_LIST__PREFIX = ABSTRACT_DATA_TABLE__PREFIX;

	/**
	 * The feature id for the '<em><b>Suffix</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DATA_LIST__SUFFIX = ABSTRACT_DATA_TABLE__SUFFIX;

	/**
	 * The feature id for the '<em><b>Hidden</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DATA_LIST__HIDDEN = ABSTRACT_DATA_TABLE__HIDDEN;

	/**
	 * The feature id for the '<em><b>Children</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DATA_LIST__CHILDREN = ABSTRACT_DATA_TABLE__CHILDREN;

	/**
	 * The feature id for the '<em><b>Disabled</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DATA_LIST__DISABLED = ABSTRACT_DATA_TABLE__DISABLED;

	/**
	 * The feature id for the '<em><b>View Of</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DATA_LIST__VIEW_OF = ABSTRACT_DATA_TABLE__VIEW_OF;

	/**
	 * The feature id for the '<em><b>Generate Webscript</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DATA_LIST__GENERATE_WEBSCRIPT = ABSTRACT_DATA_TABLE__GENERATE_WEBSCRIPT;

	/**
	 * The feature id for the '<em><b>Paging</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DATA_LIST__PAGING = ABSTRACT_DATA_TABLE__PAGING;

	/**
	 * The feature id for the '<em><b>Have Row Actions</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DATA_LIST__HAVE_ROW_ACTIONS = ABSTRACT_DATA_TABLE__HAVE_ROW_ACTIONS;

	/**
	 * The feature id for the '<em><b>Have Select Actions</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DATA_LIST__HAVE_SELECT_ACTIONS = ABSTRACT_DATA_TABLE__HAVE_SELECT_ACTIONS;

	/**
	 * The feature id for the '<em><b>Have Default Col Actions</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DATA_LIST__HAVE_DEFAULT_COL_ACTIONS = ABSTRACT_DATA_TABLE__HAVE_DEFAULT_COL_ACTIONS;

	/**
	 * The feature id for the '<em><b>Movable</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DATA_LIST__MOVABLE = ABSTRACT_DATA_TABLE_FEATURE_COUNT + 0;

	/**
	 * The feature id for the '<em><b>Editable</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DATA_LIST__EDITABLE = ABSTRACT_DATA_TABLE_FEATURE_COUNT + 1;

	/**
	 * The feature id for the '<em><b>Filtering</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DATA_LIST__FILTERING = ABSTRACT_DATA_TABLE_FEATURE_COUNT + 2;

	/**
	 * The feature id for the '<em><b>Sorting</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DATA_LIST__SORTING = ABSTRACT_DATA_TABLE_FEATURE_COUNT + 3;

	/**
	 * The feature id for the '<em><b>Operations</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DATA_LIST__OPERATIONS = ABSTRACT_DATA_TABLE_FEATURE_COUNT + 4;

	/**
	 * The number of structural features of the '<em>Data List</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DATA_LIST_FEATURE_COUNT = ABSTRACT_DATA_TABLE_FEATURE_COUNT + 5;

	/**
	 * The feature id for the '<em><b>Styling</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DATA_TABLE__STYLING = ABSTRACT_DATA_TABLE__STYLING;

	/**
	 * The feature id for the '<em><b>Stereotypes</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DATA_TABLE__STEREOTYPES = ABSTRACT_DATA_TABLE__STEREOTYPES;

	/**
	 * The feature id for the '<em><b>Tags</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DATA_TABLE__TAGS = ABSTRACT_DATA_TABLE__TAGS;

	/**
	 * The feature id for the '<em><b>Comments</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DATA_TABLE__COMMENTS = ABSTRACT_DATA_TABLE__COMMENTS;

	/**
	 * The feature id for the '<em><b>Documentation</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DATA_TABLE__DOCUMENTATION = ABSTRACT_DATA_TABLE__DOCUMENTATION;

	/**
	 * The feature id for the '<em><b>Description</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DATA_TABLE__DESCRIPTION = ABSTRACT_DATA_TABLE__DESCRIPTION;

	/**
	 * The feature id for the '<em><b>Metainfo</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DATA_TABLE__METAINFO = ABSTRACT_DATA_TABLE__METAINFO;

	/**
	 * The feature id for the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DATA_TABLE__NAME = ABSTRACT_DATA_TABLE__NAME;

	/**
	 * The feature id for the '<em><b>Map To</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DATA_TABLE__MAP_TO = ABSTRACT_DATA_TABLE__MAP_TO;

	/**
	 * The feature id for the '<em><b>Prefix</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DATA_TABLE__PREFIX = ABSTRACT_DATA_TABLE__PREFIX;

	/**
	 * The feature id for the '<em><b>Suffix</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DATA_TABLE__SUFFIX = ABSTRACT_DATA_TABLE__SUFFIX;

	/**
	 * The feature id for the '<em><b>Hidden</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DATA_TABLE__HIDDEN = ABSTRACT_DATA_TABLE__HIDDEN;

	/**
	 * The feature id for the '<em><b>Children</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DATA_TABLE__CHILDREN = ABSTRACT_DATA_TABLE__CHILDREN;

	/**
	 * The feature id for the '<em><b>Disabled</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DATA_TABLE__DISABLED = ABSTRACT_DATA_TABLE__DISABLED;

	/**
	 * The feature id for the '<em><b>View Of</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DATA_TABLE__VIEW_OF = ABSTRACT_DATA_TABLE__VIEW_OF;

	/**
	 * The feature id for the '<em><b>Generate Webscript</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DATA_TABLE__GENERATE_WEBSCRIPT = ABSTRACT_DATA_TABLE__GENERATE_WEBSCRIPT;

	/**
	 * The feature id for the '<em><b>Paging</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DATA_TABLE__PAGING = ABSTRACT_DATA_TABLE__PAGING;

	/**
	 * The feature id for the '<em><b>Have Row Actions</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DATA_TABLE__HAVE_ROW_ACTIONS = ABSTRACT_DATA_TABLE__HAVE_ROW_ACTIONS;

	/**
	 * The feature id for the '<em><b>Have Select Actions</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DATA_TABLE__HAVE_SELECT_ACTIONS = ABSTRACT_DATA_TABLE__HAVE_SELECT_ACTIONS;

	/**
	 * The feature id for the '<em><b>Have Default Col Actions</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DATA_TABLE__HAVE_DEFAULT_COL_ACTIONS = ABSTRACT_DATA_TABLE__HAVE_DEFAULT_COL_ACTIONS;

	/**
	 * The feature id for the '<em><b>Operations</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DATA_TABLE__OPERATIONS = ABSTRACT_DATA_TABLE_FEATURE_COUNT + 0;

	/**
	 * The feature id for the '<em><b>Default Col Set Up</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DATA_TABLE__DEFAULT_COL_SET_UP = ABSTRACT_DATA_TABLE_FEATURE_COUNT + 1;

	/**
	 * The number of structural features of the '<em>Data Table</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DATA_TABLE_FEATURE_COUNT = ABSTRACT_DATA_TABLE_FEATURE_COUNT + 2;

	/**
	 * The feature id for the '<em><b>Styling</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int FACET_MAP__STYLING = ABSTRACT_VIEW_OF__STYLING;

	/**
	 * The feature id for the '<em><b>Stereotypes</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int FACET_MAP__STEREOTYPES = ABSTRACT_VIEW_OF__STEREOTYPES;

	/**
	 * The feature id for the '<em><b>Tags</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int FACET_MAP__TAGS = ABSTRACT_VIEW_OF__TAGS;

	/**
	 * The feature id for the '<em><b>Comments</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int FACET_MAP__COMMENTS = ABSTRACT_VIEW_OF__COMMENTS;

	/**
	 * The feature id for the '<em><b>Documentation</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int FACET_MAP__DOCUMENTATION = ABSTRACT_VIEW_OF__DOCUMENTATION;

	/**
	 * The feature id for the '<em><b>Description</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int FACET_MAP__DESCRIPTION = ABSTRACT_VIEW_OF__DESCRIPTION;

	/**
	 * The feature id for the '<em><b>Metainfo</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int FACET_MAP__METAINFO = ABSTRACT_VIEW_OF__METAINFO;

	/**
	 * The feature id for the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int FACET_MAP__NAME = ABSTRACT_VIEW_OF__NAME;

	/**
	 * The feature id for the '<em><b>Map To</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int FACET_MAP__MAP_TO = ABSTRACT_VIEW_OF__MAP_TO;

	/**
	 * The feature id for the '<em><b>Prefix</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int FACET_MAP__PREFIX = ABSTRACT_VIEW_OF__PREFIX;

	/**
	 * The feature id for the '<em><b>Suffix</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int FACET_MAP__SUFFIX = ABSTRACT_VIEW_OF__SUFFIX;

	/**
	 * The feature id for the '<em><b>Hidden</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int FACET_MAP__HIDDEN = ABSTRACT_VIEW_OF__HIDDEN;

	/**
	 * The feature id for the '<em><b>Children</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int FACET_MAP__CHILDREN = ABSTRACT_VIEW_OF__CHILDREN;

	/**
	 * The feature id for the '<em><b>Disabled</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int FACET_MAP__DISABLED = ABSTRACT_VIEW_OF__DISABLED;

	/**
	 * The feature id for the '<em><b>View Of</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int FACET_MAP__VIEW_OF = ABSTRACT_VIEW_OF__VIEW_OF;

	/**
	 * The feature id for the '<em><b>Generate Webscript</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int FACET_MAP__GENERATE_WEBSCRIPT = ABSTRACT_VIEW_OF__GENERATE_WEBSCRIPT;

	/**
	 * The feature id for the '<em><b>Paging</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int FACET_MAP__PAGING = ABSTRACT_VIEW_OF_FEATURE_COUNT + 0;

	/**
	 * The feature id for the '<em><b>Operations</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int FACET_MAP__OPERATIONS = ABSTRACT_VIEW_OF_FEATURE_COUNT + 1;

	/**
	 * The feature id for the '<em><b>Display Empty Facet</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int FACET_MAP__DISPLAY_EMPTY_FACET = ABSTRACT_VIEW_OF_FEATURE_COUNT + 2;

	/**
	 * The feature id for the '<em><b>Facet Display Type</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int FACET_MAP__FACET_DISPLAY_TYPE = ABSTRACT_VIEW_OF_FEATURE_COUNT + 3;

	/**
	 * The number of structural features of the '<em>Facet Map</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int FACET_MAP_FEATURE_COUNT = ABSTRACT_VIEW_OF_FEATURE_COUNT + 4;

	/**
	 * The feature id for the '<em><b>Styling</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TREE__STYLING = ABSTRACT_VIEW_OF__STYLING;

	/**
	 * The feature id for the '<em><b>Stereotypes</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TREE__STEREOTYPES = ABSTRACT_VIEW_OF__STEREOTYPES;

	/**
	 * The feature id for the '<em><b>Tags</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TREE__TAGS = ABSTRACT_VIEW_OF__TAGS;

	/**
	 * The feature id for the '<em><b>Comments</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TREE__COMMENTS = ABSTRACT_VIEW_OF__COMMENTS;

	/**
	 * The feature id for the '<em><b>Documentation</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TREE__DOCUMENTATION = ABSTRACT_VIEW_OF__DOCUMENTATION;

	/**
	 * The feature id for the '<em><b>Description</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TREE__DESCRIPTION = ABSTRACT_VIEW_OF__DESCRIPTION;

	/**
	 * The feature id for the '<em><b>Metainfo</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TREE__METAINFO = ABSTRACT_VIEW_OF__METAINFO;

	/**
	 * The feature id for the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TREE__NAME = ABSTRACT_VIEW_OF__NAME;

	/**
	 * The feature id for the '<em><b>Map To</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TREE__MAP_TO = ABSTRACT_VIEW_OF__MAP_TO;

	/**
	 * The feature id for the '<em><b>Prefix</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TREE__PREFIX = ABSTRACT_VIEW_OF__PREFIX;

	/**
	 * The feature id for the '<em><b>Suffix</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TREE__SUFFIX = ABSTRACT_VIEW_OF__SUFFIX;

	/**
	 * The feature id for the '<em><b>Hidden</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TREE__HIDDEN = ABSTRACT_VIEW_OF__HIDDEN;

	/**
	 * The feature id for the '<em><b>Children</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TREE__CHILDREN = ABSTRACT_VIEW_OF__CHILDREN;

	/**
	 * The feature id for the '<em><b>Disabled</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TREE__DISABLED = ABSTRACT_VIEW_OF__DISABLED;

	/**
	 * The feature id for the '<em><b>View Of</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TREE__VIEW_OF = ABSTRACT_VIEW_OF__VIEW_OF;

	/**
	 * The feature id for the '<em><b>Generate Webscript</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TREE__GENERATE_WEBSCRIPT = ABSTRACT_VIEW_OF__GENERATE_WEBSCRIPT;

	/**
	 * The feature id for the '<em><b>Sorting</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TREE__SORTING = ABSTRACT_VIEW_OF_FEATURE_COUNT + 0;

	/**
	 * The feature id for the '<em><b>Editable</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TREE__EDITABLE = ABSTRACT_VIEW_OF_FEATURE_COUNT + 1;

	/**
	 * The feature id for the '<em><b>Movable</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TREE__MOVABLE = ABSTRACT_VIEW_OF_FEATURE_COUNT + 2;

	/**
	 * The feature id for the '<em><b>Filtering</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TREE__FILTERING = ABSTRACT_VIEW_OF_FEATURE_COUNT + 3;

	/**
	 * The feature id for the '<em><b>Operations</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TREE__OPERATIONS = ABSTRACT_VIEW_OF_FEATURE_COUNT + 4;

	/**
	 * The feature id for the '<em><b>Node Operations</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TREE__NODE_OPERATIONS = ABSTRACT_VIEW_OF_FEATURE_COUNT + 5;

	/**
	 * The feature id for the '<em><b>Node Value</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TREE__NODE_VALUE = ABSTRACT_VIEW_OF_FEATURE_COUNT + 6;

	/**
	 * The feature id for the '<em><b>Default Depth</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TREE__DEFAULT_DEPTH = ABSTRACT_VIEW_OF_FEATURE_COUNT + 7;

	/**
	 * The feature id for the '<em><b>Max Depth</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TREE__MAX_DEPTH = ABSTRACT_VIEW_OF_FEATURE_COUNT + 8;

	/**
	 * The number of structural features of the '<em>Tree</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TREE_FEATURE_COUNT = ABSTRACT_VIEW_OF_FEATURE_COUNT + 9;

	/**
	 * The feature id for the '<em><b>Styling</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int COMPOSED_VIEW__STYLING = ABSTRACT_VIEW__STYLING;

	/**
	 * The feature id for the '<em><b>Stereotypes</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int COMPOSED_VIEW__STEREOTYPES = ABSTRACT_VIEW__STEREOTYPES;

	/**
	 * The feature id for the '<em><b>Tags</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int COMPOSED_VIEW__TAGS = ABSTRACT_VIEW__TAGS;

	/**
	 * The feature id for the '<em><b>Comments</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int COMPOSED_VIEW__COMMENTS = ABSTRACT_VIEW__COMMENTS;

	/**
	 * The feature id for the '<em><b>Documentation</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int COMPOSED_VIEW__DOCUMENTATION = ABSTRACT_VIEW__DOCUMENTATION;

	/**
	 * The feature id for the '<em><b>Description</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int COMPOSED_VIEW__DESCRIPTION = ABSTRACT_VIEW__DESCRIPTION;

	/**
	 * The feature id for the '<em><b>Metainfo</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int COMPOSED_VIEW__METAINFO = ABSTRACT_VIEW__METAINFO;

	/**
	 * The feature id for the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int COMPOSED_VIEW__NAME = ABSTRACT_VIEW__NAME;

	/**
	 * The feature id for the '<em><b>Map To</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int COMPOSED_VIEW__MAP_TO = ABSTRACT_VIEW__MAP_TO;

	/**
	 * The feature id for the '<em><b>Prefix</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int COMPOSED_VIEW__PREFIX = ABSTRACT_VIEW__PREFIX;

	/**
	 * The feature id for the '<em><b>Suffix</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int COMPOSED_VIEW__SUFFIX = ABSTRACT_VIEW__SUFFIX;

	/**
	 * The feature id for the '<em><b>Hidden</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int COMPOSED_VIEW__HIDDEN = ABSTRACT_VIEW__HIDDEN;

	/**
	 * The feature id for the '<em><b>Children</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int COMPOSED_VIEW__CHILDREN = ABSTRACT_VIEW__CHILDREN;

	/**
	 * The feature id for the '<em><b>Disabled</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int COMPOSED_VIEW__DISABLED = ABSTRACT_VIEW__DISABLED;

	/**
	 * The number of structural features of the '<em>Composed View</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int COMPOSED_VIEW_FEATURE_COUNT = ABSTRACT_VIEW_FEATURE_COUNT + 0;

	/**
	 * The feature id for the '<em><b>Styling</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int FIELD__STYLING = FIELD_ELEMENT__STYLING;

	/**
	 * The feature id for the '<em><b>Stereotypes</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int FIELD__STEREOTYPES = FIELD_ELEMENT__STEREOTYPES;

	/**
	 * The feature id for the '<em><b>Tags</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int FIELD__TAGS = FIELD_ELEMENT__TAGS;

	/**
	 * The feature id for the '<em><b>Comments</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int FIELD__COMMENTS = FIELD_ELEMENT__COMMENTS;

	/**
	 * The feature id for the '<em><b>Documentation</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int FIELD__DOCUMENTATION = FIELD_ELEMENT__DOCUMENTATION;

	/**
	 * The feature id for the '<em><b>Description</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int FIELD__DESCRIPTION = FIELD_ELEMENT__DESCRIPTION;

	/**
	 * The feature id for the '<em><b>Metainfo</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int FIELD__METAINFO = FIELD_ELEMENT__METAINFO;

	/**
	 * The feature id for the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int FIELD__NAME = FIELD_ELEMENT__NAME;

	/**
	 * The feature id for the '<em><b>Map To</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int FIELD__MAP_TO = FIELD_ELEMENT__MAP_TO;

	/**
	 * The feature id for the '<em><b>Prefix</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int FIELD__PREFIX = FIELD_ELEMENT__PREFIX;

	/**
	 * The feature id for the '<em><b>Suffix</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int FIELD__SUFFIX = FIELD_ELEMENT__SUFFIX;

	/**
	 * The feature id for the '<em><b>Hidden</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int FIELD__HIDDEN = FIELD_ELEMENT__HIDDEN;

	/**
	 * The feature id for the '<em><b>Patern</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int FIELD__PATERN = FIELD_ELEMENT_FEATURE_COUNT + 0;

	/**
	 * The feature id for the '<em><b>Patern Language</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int FIELD__PATERN_LANGUAGE = FIELD_ELEMENT_FEATURE_COUNT + 1;

	/**
	 * The feature id for the '<em><b>Path</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int FIELD__PATH = FIELD_ELEMENT_FEATURE_COUNT + 2;

	/**
	 * The number of structural features of the '<em>Field</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int FIELD_FEATURE_COUNT = FIELD_ELEMENT_FEATURE_COUNT + 3;

	/**
	 * The feature id for the '<em><b>Styling</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TEXT_FIELD__STYLING = FIELD__STYLING;

	/**
	 * The feature id for the '<em><b>Stereotypes</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TEXT_FIELD__STEREOTYPES = FIELD__STEREOTYPES;

	/**
	 * The feature id for the '<em><b>Tags</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TEXT_FIELD__TAGS = FIELD__TAGS;

	/**
	 * The feature id for the '<em><b>Comments</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TEXT_FIELD__COMMENTS = FIELD__COMMENTS;

	/**
	 * The feature id for the '<em><b>Documentation</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TEXT_FIELD__DOCUMENTATION = FIELD__DOCUMENTATION;

	/**
	 * The feature id for the '<em><b>Description</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TEXT_FIELD__DESCRIPTION = FIELD__DESCRIPTION;

	/**
	 * The feature id for the '<em><b>Metainfo</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TEXT_FIELD__METAINFO = FIELD__METAINFO;

	/**
	 * The feature id for the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TEXT_FIELD__NAME = FIELD__NAME;

	/**
	 * The feature id for the '<em><b>Map To</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TEXT_FIELD__MAP_TO = FIELD__MAP_TO;

	/**
	 * The feature id for the '<em><b>Prefix</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TEXT_FIELD__PREFIX = FIELD__PREFIX;

	/**
	 * The feature id for the '<em><b>Suffix</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TEXT_FIELD__SUFFIX = FIELD__SUFFIX;

	/**
	 * The feature id for the '<em><b>Hidden</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TEXT_FIELD__HIDDEN = FIELD__HIDDEN;

	/**
	 * The feature id for the '<em><b>Patern</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TEXT_FIELD__PATERN = FIELD__PATERN;

	/**
	 * The feature id for the '<em><b>Patern Language</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TEXT_FIELD__PATERN_LANGUAGE = FIELD__PATERN_LANGUAGE;

	/**
	 * The feature id for the '<em><b>Path</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TEXT_FIELD__PATH = FIELD__PATH;

	/**
	 * The feature id for the '<em><b>Widget Type</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TEXT_FIELD__WIDGET_TYPE = FIELD_FEATURE_COUNT + 0;

	/**
	 * The number of structural features of the '<em>Text Field</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TEXT_FIELD_FEATURE_COUNT = FIELD_FEATURE_COUNT + 1;

	/**
	 * The feature id for the '<em><b>Styling</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int BOOLEAN_FIELD__STYLING = FIELD__STYLING;

	/**
	 * The feature id for the '<em><b>Stereotypes</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int BOOLEAN_FIELD__STEREOTYPES = FIELD__STEREOTYPES;

	/**
	 * The feature id for the '<em><b>Tags</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int BOOLEAN_FIELD__TAGS = FIELD__TAGS;

	/**
	 * The feature id for the '<em><b>Comments</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int BOOLEAN_FIELD__COMMENTS = FIELD__COMMENTS;

	/**
	 * The feature id for the '<em><b>Documentation</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int BOOLEAN_FIELD__DOCUMENTATION = FIELD__DOCUMENTATION;

	/**
	 * The feature id for the '<em><b>Description</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int BOOLEAN_FIELD__DESCRIPTION = FIELD__DESCRIPTION;

	/**
	 * The feature id for the '<em><b>Metainfo</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int BOOLEAN_FIELD__METAINFO = FIELD__METAINFO;

	/**
	 * The feature id for the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int BOOLEAN_FIELD__NAME = FIELD__NAME;

	/**
	 * The feature id for the '<em><b>Map To</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int BOOLEAN_FIELD__MAP_TO = FIELD__MAP_TO;

	/**
	 * The feature id for the '<em><b>Prefix</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int BOOLEAN_FIELD__PREFIX = FIELD__PREFIX;

	/**
	 * The feature id for the '<em><b>Suffix</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int BOOLEAN_FIELD__SUFFIX = FIELD__SUFFIX;

	/**
	 * The feature id for the '<em><b>Hidden</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int BOOLEAN_FIELD__HIDDEN = FIELD__HIDDEN;

	/**
	 * The feature id for the '<em><b>Patern</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int BOOLEAN_FIELD__PATERN = FIELD__PATERN;

	/**
	 * The feature id for the '<em><b>Patern Language</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int BOOLEAN_FIELD__PATERN_LANGUAGE = FIELD__PATERN_LANGUAGE;

	/**
	 * The feature id for the '<em><b>Path</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int BOOLEAN_FIELD__PATH = FIELD__PATH;

	/**
	 * The feature id for the '<em><b>Split</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int BOOLEAN_FIELD__SPLIT = FIELD_FEATURE_COUNT + 0;

	/**
	 * The number of structural features of the '<em>Boolean Field</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int BOOLEAN_FIELD_FEATURE_COUNT = FIELD_FEATURE_COUNT + 1;

	/**
	 * The feature id for the '<em><b>Styling</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PASSWORD_FIELD__STYLING = FIELD__STYLING;

	/**
	 * The feature id for the '<em><b>Stereotypes</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PASSWORD_FIELD__STEREOTYPES = FIELD__STEREOTYPES;

	/**
	 * The feature id for the '<em><b>Tags</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PASSWORD_FIELD__TAGS = FIELD__TAGS;

	/**
	 * The feature id for the '<em><b>Comments</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PASSWORD_FIELD__COMMENTS = FIELD__COMMENTS;

	/**
	 * The feature id for the '<em><b>Documentation</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PASSWORD_FIELD__DOCUMENTATION = FIELD__DOCUMENTATION;

	/**
	 * The feature id for the '<em><b>Description</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PASSWORD_FIELD__DESCRIPTION = FIELD__DESCRIPTION;

	/**
	 * The feature id for the '<em><b>Metainfo</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PASSWORD_FIELD__METAINFO = FIELD__METAINFO;

	/**
	 * The feature id for the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PASSWORD_FIELD__NAME = FIELD__NAME;

	/**
	 * The feature id for the '<em><b>Map To</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PASSWORD_FIELD__MAP_TO = FIELD__MAP_TO;

	/**
	 * The feature id for the '<em><b>Prefix</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PASSWORD_FIELD__PREFIX = FIELD__PREFIX;

	/**
	 * The feature id for the '<em><b>Suffix</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PASSWORD_FIELD__SUFFIX = FIELD__SUFFIX;

	/**
	 * The feature id for the '<em><b>Hidden</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PASSWORD_FIELD__HIDDEN = FIELD__HIDDEN;

	/**
	 * The feature id for the '<em><b>Patern</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PASSWORD_FIELD__PATERN = FIELD__PATERN;

	/**
	 * The feature id for the '<em><b>Patern Language</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PASSWORD_FIELD__PATERN_LANGUAGE = FIELD__PATERN_LANGUAGE;

	/**
	 * The feature id for the '<em><b>Path</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PASSWORD_FIELD__PATH = FIELD__PATH;

	/**
	 * The number of structural features of the '<em>Password Field</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PASSWORD_FIELD_FEATURE_COUNT = FIELD_FEATURE_COUNT + 0;

	/**
	 * The feature id for the '<em><b>Styling</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int FLOAT_FIELD__STYLING = FIELD__STYLING;

	/**
	 * The feature id for the '<em><b>Stereotypes</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int FLOAT_FIELD__STEREOTYPES = FIELD__STEREOTYPES;

	/**
	 * The feature id for the '<em><b>Tags</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int FLOAT_FIELD__TAGS = FIELD__TAGS;

	/**
	 * The feature id for the '<em><b>Comments</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int FLOAT_FIELD__COMMENTS = FIELD__COMMENTS;

	/**
	 * The feature id for the '<em><b>Documentation</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int FLOAT_FIELD__DOCUMENTATION = FIELD__DOCUMENTATION;

	/**
	 * The feature id for the '<em><b>Description</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int FLOAT_FIELD__DESCRIPTION = FIELD__DESCRIPTION;

	/**
	 * The feature id for the '<em><b>Metainfo</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int FLOAT_FIELD__METAINFO = FIELD__METAINFO;

	/**
	 * The feature id for the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int FLOAT_FIELD__NAME = FIELD__NAME;

	/**
	 * The feature id for the '<em><b>Map To</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int FLOAT_FIELD__MAP_TO = FIELD__MAP_TO;

	/**
	 * The feature id for the '<em><b>Prefix</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int FLOAT_FIELD__PREFIX = FIELD__PREFIX;

	/**
	 * The feature id for the '<em><b>Suffix</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int FLOAT_FIELD__SUFFIX = FIELD__SUFFIX;

	/**
	 * The feature id for the '<em><b>Hidden</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int FLOAT_FIELD__HIDDEN = FIELD__HIDDEN;

	/**
	 * The feature id for the '<em><b>Patern</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int FLOAT_FIELD__PATERN = FIELD__PATERN;

	/**
	 * The feature id for the '<em><b>Patern Language</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int FLOAT_FIELD__PATERN_LANGUAGE = FIELD__PATERN_LANGUAGE;

	/**
	 * The feature id for the '<em><b>Path</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int FLOAT_FIELD__PATH = FIELD__PATH;

	/**
	 * The number of structural features of the '<em>Float Field</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int FLOAT_FIELD_FEATURE_COUNT = FIELD_FEATURE_COUNT + 0;

	/**
	 * The feature id for the '<em><b>Styling</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ACTION_FIELD__STYLING = FIELD__STYLING;

	/**
	 * The feature id for the '<em><b>Stereotypes</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ACTION_FIELD__STEREOTYPES = FIELD__STEREOTYPES;

	/**
	 * The feature id for the '<em><b>Tags</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ACTION_FIELD__TAGS = FIELD__TAGS;

	/**
	 * The feature id for the '<em><b>Comments</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ACTION_FIELD__COMMENTS = FIELD__COMMENTS;

	/**
	 * The feature id for the '<em><b>Documentation</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ACTION_FIELD__DOCUMENTATION = FIELD__DOCUMENTATION;

	/**
	 * The feature id for the '<em><b>Description</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ACTION_FIELD__DESCRIPTION = FIELD__DESCRIPTION;

	/**
	 * The feature id for the '<em><b>Metainfo</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ACTION_FIELD__METAINFO = FIELD__METAINFO;

	/**
	 * The feature id for the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ACTION_FIELD__NAME = FIELD__NAME;

	/**
	 * The feature id for the '<em><b>Map To</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ACTION_FIELD__MAP_TO = FIELD__MAP_TO;

	/**
	 * The feature id for the '<em><b>Prefix</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ACTION_FIELD__PREFIX = FIELD__PREFIX;

	/**
	 * The feature id for the '<em><b>Suffix</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ACTION_FIELD__SUFFIX = FIELD__SUFFIX;

	/**
	 * The feature id for the '<em><b>Hidden</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ACTION_FIELD__HIDDEN = FIELD__HIDDEN;

	/**
	 * The feature id for the '<em><b>Patern</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ACTION_FIELD__PATERN = FIELD__PATERN;

	/**
	 * The feature id for the '<em><b>Patern Language</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ACTION_FIELD__PATERN_LANGUAGE = FIELD__PATERN_LANGUAGE;

	/**
	 * The feature id for the '<em><b>Path</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ACTION_FIELD__PATH = FIELD__PATH;

	/**
	 * The feature id for the '<em><b>Operations</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ACTION_FIELD__OPERATIONS = FIELD_FEATURE_COUNT + 0;

	/**
	 * The number of structural features of the '<em>Action Field</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ACTION_FIELD_FEATURE_COUNT = FIELD_FEATURE_COUNT + 1;

	/**
	 * The feature id for the '<em><b>Styling</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DATE_FIELD__STYLING = FIELD__STYLING;

	/**
	 * The feature id for the '<em><b>Stereotypes</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DATE_FIELD__STEREOTYPES = FIELD__STEREOTYPES;

	/**
	 * The feature id for the '<em><b>Tags</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DATE_FIELD__TAGS = FIELD__TAGS;

	/**
	 * The feature id for the '<em><b>Comments</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DATE_FIELD__COMMENTS = FIELD__COMMENTS;

	/**
	 * The feature id for the '<em><b>Documentation</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DATE_FIELD__DOCUMENTATION = FIELD__DOCUMENTATION;

	/**
	 * The feature id for the '<em><b>Description</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DATE_FIELD__DESCRIPTION = FIELD__DESCRIPTION;

	/**
	 * The feature id for the '<em><b>Metainfo</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DATE_FIELD__METAINFO = FIELD__METAINFO;

	/**
	 * The feature id for the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DATE_FIELD__NAME = FIELD__NAME;

	/**
	 * The feature id for the '<em><b>Map To</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DATE_FIELD__MAP_TO = FIELD__MAP_TO;

	/**
	 * The feature id for the '<em><b>Prefix</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DATE_FIELD__PREFIX = FIELD__PREFIX;

	/**
	 * The feature id for the '<em><b>Suffix</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DATE_FIELD__SUFFIX = FIELD__SUFFIX;

	/**
	 * The feature id for the '<em><b>Hidden</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DATE_FIELD__HIDDEN = FIELD__HIDDEN;

	/**
	 * The feature id for the '<em><b>Patern</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DATE_FIELD__PATERN = FIELD__PATERN;

	/**
	 * The feature id for the '<em><b>Patern Language</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DATE_FIELD__PATERN_LANGUAGE = FIELD__PATERN_LANGUAGE;

	/**
	 * The feature id for the '<em><b>Path</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DATE_FIELD__PATH = FIELD__PATH;

	/**
	 * The number of structural features of the '<em>Date Field</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DATE_FIELD_FEATURE_COUNT = FIELD_FEATURE_COUNT + 0;

	/**
	 * The feature id for the '<em><b>Styling</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TIME_FIELD__STYLING = FIELD__STYLING;

	/**
	 * The feature id for the '<em><b>Stereotypes</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TIME_FIELD__STEREOTYPES = FIELD__STEREOTYPES;

	/**
	 * The feature id for the '<em><b>Tags</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TIME_FIELD__TAGS = FIELD__TAGS;

	/**
	 * The feature id for the '<em><b>Comments</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TIME_FIELD__COMMENTS = FIELD__COMMENTS;

	/**
	 * The feature id for the '<em><b>Documentation</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TIME_FIELD__DOCUMENTATION = FIELD__DOCUMENTATION;

	/**
	 * The feature id for the '<em><b>Description</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TIME_FIELD__DESCRIPTION = FIELD__DESCRIPTION;

	/**
	 * The feature id for the '<em><b>Metainfo</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TIME_FIELD__METAINFO = FIELD__METAINFO;

	/**
	 * The feature id for the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TIME_FIELD__NAME = FIELD__NAME;

	/**
	 * The feature id for the '<em><b>Map To</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TIME_FIELD__MAP_TO = FIELD__MAP_TO;

	/**
	 * The feature id for the '<em><b>Prefix</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TIME_FIELD__PREFIX = FIELD__PREFIX;

	/**
	 * The feature id for the '<em><b>Suffix</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TIME_FIELD__SUFFIX = FIELD__SUFFIX;

	/**
	 * The feature id for the '<em><b>Hidden</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TIME_FIELD__HIDDEN = FIELD__HIDDEN;

	/**
	 * The feature id for the '<em><b>Patern</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TIME_FIELD__PATERN = FIELD__PATERN;

	/**
	 * The feature id for the '<em><b>Patern Language</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TIME_FIELD__PATERN_LANGUAGE = FIELD__PATERN_LANGUAGE;

	/**
	 * The feature id for the '<em><b>Path</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TIME_FIELD__PATH = FIELD__PATH;

	/**
	 * The number of structural features of the '<em>Time Field</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TIME_FIELD_FEATURE_COUNT = FIELD_FEATURE_COUNT + 0;

	/**
	 * The feature id for the '<em><b>Styling</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DATE_TIME_FIELD__STYLING = FIELD__STYLING;

	/**
	 * The feature id for the '<em><b>Stereotypes</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DATE_TIME_FIELD__STEREOTYPES = FIELD__STEREOTYPES;

	/**
	 * The feature id for the '<em><b>Tags</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DATE_TIME_FIELD__TAGS = FIELD__TAGS;

	/**
	 * The feature id for the '<em><b>Comments</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DATE_TIME_FIELD__COMMENTS = FIELD__COMMENTS;

	/**
	 * The feature id for the '<em><b>Documentation</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DATE_TIME_FIELD__DOCUMENTATION = FIELD__DOCUMENTATION;

	/**
	 * The feature id for the '<em><b>Description</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DATE_TIME_FIELD__DESCRIPTION = FIELD__DESCRIPTION;

	/**
	 * The feature id for the '<em><b>Metainfo</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DATE_TIME_FIELD__METAINFO = FIELD__METAINFO;

	/**
	 * The feature id for the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DATE_TIME_FIELD__NAME = FIELD__NAME;

	/**
	 * The feature id for the '<em><b>Map To</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DATE_TIME_FIELD__MAP_TO = FIELD__MAP_TO;

	/**
	 * The feature id for the '<em><b>Prefix</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DATE_TIME_FIELD__PREFIX = FIELD__PREFIX;

	/**
	 * The feature id for the '<em><b>Suffix</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DATE_TIME_FIELD__SUFFIX = FIELD__SUFFIX;

	/**
	 * The feature id for the '<em><b>Hidden</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DATE_TIME_FIELD__HIDDEN = FIELD__HIDDEN;

	/**
	 * The feature id for the '<em><b>Patern</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DATE_TIME_FIELD__PATERN = FIELD__PATERN;

	/**
	 * The feature id for the '<em><b>Patern Language</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DATE_TIME_FIELD__PATERN_LANGUAGE = FIELD__PATERN_LANGUAGE;

	/**
	 * The feature id for the '<em><b>Path</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DATE_TIME_FIELD__PATH = FIELD__PATH;

	/**
	 * The number of structural features of the '<em>Date Time Field</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DATE_TIME_FIELD_FEATURE_COUNT = FIELD_FEATURE_COUNT + 0;

	/**
	 * The feature id for the '<em><b>Styling</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PHONE_NUMBER_FIELD__STYLING = FIELD__STYLING;

	/**
	 * The feature id for the '<em><b>Stereotypes</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PHONE_NUMBER_FIELD__STEREOTYPES = FIELD__STEREOTYPES;

	/**
	 * The feature id for the '<em><b>Tags</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PHONE_NUMBER_FIELD__TAGS = FIELD__TAGS;

	/**
	 * The feature id for the '<em><b>Comments</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PHONE_NUMBER_FIELD__COMMENTS = FIELD__COMMENTS;

	/**
	 * The feature id for the '<em><b>Documentation</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PHONE_NUMBER_FIELD__DOCUMENTATION = FIELD__DOCUMENTATION;

	/**
	 * The feature id for the '<em><b>Description</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PHONE_NUMBER_FIELD__DESCRIPTION = FIELD__DESCRIPTION;

	/**
	 * The feature id for the '<em><b>Metainfo</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PHONE_NUMBER_FIELD__METAINFO = FIELD__METAINFO;

	/**
	 * The feature id for the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PHONE_NUMBER_FIELD__NAME = FIELD__NAME;

	/**
	 * The feature id for the '<em><b>Map To</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PHONE_NUMBER_FIELD__MAP_TO = FIELD__MAP_TO;

	/**
	 * The feature id for the '<em><b>Prefix</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PHONE_NUMBER_FIELD__PREFIX = FIELD__PREFIX;

	/**
	 * The feature id for the '<em><b>Suffix</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PHONE_NUMBER_FIELD__SUFFIX = FIELD__SUFFIX;

	/**
	 * The feature id for the '<em><b>Hidden</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PHONE_NUMBER_FIELD__HIDDEN = FIELD__HIDDEN;

	/**
	 * The feature id for the '<em><b>Patern</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PHONE_NUMBER_FIELD__PATERN = FIELD__PATERN;

	/**
	 * The feature id for the '<em><b>Patern Language</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PHONE_NUMBER_FIELD__PATERN_LANGUAGE = FIELD__PATERN_LANGUAGE;

	/**
	 * The feature id for the '<em><b>Path</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PHONE_NUMBER_FIELD__PATH = FIELD__PATH;

	/**
	 * The number of structural features of the '<em>Phone Number Field</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PHONE_NUMBER_FIELD_FEATURE_COUNT = FIELD_FEATURE_COUNT + 0;

	/**
	 * The feature id for the '<em><b>Styling</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int EMAIL_FIELD__STYLING = FIELD__STYLING;

	/**
	 * The feature id for the '<em><b>Stereotypes</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int EMAIL_FIELD__STEREOTYPES = FIELD__STEREOTYPES;

	/**
	 * The feature id for the '<em><b>Tags</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int EMAIL_FIELD__TAGS = FIELD__TAGS;

	/**
	 * The feature id for the '<em><b>Comments</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int EMAIL_FIELD__COMMENTS = FIELD__COMMENTS;

	/**
	 * The feature id for the '<em><b>Documentation</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int EMAIL_FIELD__DOCUMENTATION = FIELD__DOCUMENTATION;

	/**
	 * The feature id for the '<em><b>Description</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int EMAIL_FIELD__DESCRIPTION = FIELD__DESCRIPTION;

	/**
	 * The feature id for the '<em><b>Metainfo</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int EMAIL_FIELD__METAINFO = FIELD__METAINFO;

	/**
	 * The feature id for the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int EMAIL_FIELD__NAME = FIELD__NAME;

	/**
	 * The feature id for the '<em><b>Map To</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int EMAIL_FIELD__MAP_TO = FIELD__MAP_TO;

	/**
	 * The feature id for the '<em><b>Prefix</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int EMAIL_FIELD__PREFIX = FIELD__PREFIX;

	/**
	 * The feature id for the '<em><b>Suffix</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int EMAIL_FIELD__SUFFIX = FIELD__SUFFIX;

	/**
	 * The feature id for the '<em><b>Hidden</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int EMAIL_FIELD__HIDDEN = FIELD__HIDDEN;

	/**
	 * The feature id for the '<em><b>Patern</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int EMAIL_FIELD__PATERN = FIELD__PATERN;

	/**
	 * The feature id for the '<em><b>Patern Language</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int EMAIL_FIELD__PATERN_LANGUAGE = FIELD__PATERN_LANGUAGE;

	/**
	 * The feature id for the '<em><b>Path</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int EMAIL_FIELD__PATH = FIELD__PATH;

	/**
	 * The number of structural features of the '<em>Email Field</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int EMAIL_FIELD_FEATURE_COUNT = FIELD_FEATURE_COUNT + 0;

	/**
	 * The feature id for the '<em><b>Styling</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int INTEGER_FIELD__STYLING = FIELD__STYLING;

	/**
	 * The feature id for the '<em><b>Stereotypes</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int INTEGER_FIELD__STEREOTYPES = FIELD__STEREOTYPES;

	/**
	 * The feature id for the '<em><b>Tags</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int INTEGER_FIELD__TAGS = FIELD__TAGS;

	/**
	 * The feature id for the '<em><b>Comments</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int INTEGER_FIELD__COMMENTS = FIELD__COMMENTS;

	/**
	 * The feature id for the '<em><b>Documentation</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int INTEGER_FIELD__DOCUMENTATION = FIELD__DOCUMENTATION;

	/**
	 * The feature id for the '<em><b>Description</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int INTEGER_FIELD__DESCRIPTION = FIELD__DESCRIPTION;

	/**
	 * The feature id for the '<em><b>Metainfo</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int INTEGER_FIELD__METAINFO = FIELD__METAINFO;

	/**
	 * The feature id for the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int INTEGER_FIELD__NAME = FIELD__NAME;

	/**
	 * The feature id for the '<em><b>Map To</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int INTEGER_FIELD__MAP_TO = FIELD__MAP_TO;

	/**
	 * The feature id for the '<em><b>Prefix</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int INTEGER_FIELD__PREFIX = FIELD__PREFIX;

	/**
	 * The feature id for the '<em><b>Suffix</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int INTEGER_FIELD__SUFFIX = FIELD__SUFFIX;

	/**
	 * The feature id for the '<em><b>Hidden</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int INTEGER_FIELD__HIDDEN = FIELD__HIDDEN;

	/**
	 * The feature id for the '<em><b>Patern</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int INTEGER_FIELD__PATERN = FIELD__PATERN;

	/**
	 * The feature id for the '<em><b>Patern Language</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int INTEGER_FIELD__PATERN_LANGUAGE = FIELD__PATERN_LANGUAGE;

	/**
	 * The feature id for the '<em><b>Path</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int INTEGER_FIELD__PATH = FIELD__PATH;

	/**
	 * The number of structural features of the '<em>Integer Field</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int INTEGER_FIELD_FEATURE_COUNT = FIELD_FEATURE_COUNT + 0;

	/**
	 * The feature id for the '<em><b>Styling</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int FILE_FIELD__STYLING = FIELD__STYLING;

	/**
	 * The feature id for the '<em><b>Stereotypes</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int FILE_FIELD__STEREOTYPES = FIELD__STEREOTYPES;

	/**
	 * The feature id for the '<em><b>Tags</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int FILE_FIELD__TAGS = FIELD__TAGS;

	/**
	 * The feature id for the '<em><b>Comments</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int FILE_FIELD__COMMENTS = FIELD__COMMENTS;

	/**
	 * The feature id for the '<em><b>Documentation</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int FILE_FIELD__DOCUMENTATION = FIELD__DOCUMENTATION;

	/**
	 * The feature id for the '<em><b>Description</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int FILE_FIELD__DESCRIPTION = FIELD__DESCRIPTION;

	/**
	 * The feature id for the '<em><b>Metainfo</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int FILE_FIELD__METAINFO = FIELD__METAINFO;

	/**
	 * The feature id for the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int FILE_FIELD__NAME = FIELD__NAME;

	/**
	 * The feature id for the '<em><b>Map To</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int FILE_FIELD__MAP_TO = FIELD__MAP_TO;

	/**
	 * The feature id for the '<em><b>Prefix</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int FILE_FIELD__PREFIX = FIELD__PREFIX;

	/**
	 * The feature id for the '<em><b>Suffix</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int FILE_FIELD__SUFFIX = FIELD__SUFFIX;

	/**
	 * The feature id for the '<em><b>Hidden</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int FILE_FIELD__HIDDEN = FIELD__HIDDEN;

	/**
	 * The feature id for the '<em><b>Patern</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int FILE_FIELD__PATERN = FIELD__PATERN;

	/**
	 * The feature id for the '<em><b>Patern Language</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int FILE_FIELD__PATERN_LANGUAGE = FIELD__PATERN_LANGUAGE;

	/**
	 * The feature id for the '<em><b>Path</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int FILE_FIELD__PATH = FIELD__PATH;

	/**
	 * The number of structural features of the '<em>File Field</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int FILE_FIELD_FEATURE_COUNT = FIELD_FEATURE_COUNT + 0;

	/**
	 * The feature id for the '<em><b>Styling</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SELECT_FIELD__STYLING = FIELD__STYLING;

	/**
	 * The feature id for the '<em><b>Stereotypes</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SELECT_FIELD__STEREOTYPES = FIELD__STEREOTYPES;

	/**
	 * The feature id for the '<em><b>Tags</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SELECT_FIELD__TAGS = FIELD__TAGS;

	/**
	 * The feature id for the '<em><b>Comments</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SELECT_FIELD__COMMENTS = FIELD__COMMENTS;

	/**
	 * The feature id for the '<em><b>Documentation</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SELECT_FIELD__DOCUMENTATION = FIELD__DOCUMENTATION;

	/**
	 * The feature id for the '<em><b>Description</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SELECT_FIELD__DESCRIPTION = FIELD__DESCRIPTION;

	/**
	 * The feature id for the '<em><b>Metainfo</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SELECT_FIELD__METAINFO = FIELD__METAINFO;

	/**
	 * The feature id for the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SELECT_FIELD__NAME = FIELD__NAME;

	/**
	 * The feature id for the '<em><b>Map To</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SELECT_FIELD__MAP_TO = FIELD__MAP_TO;

	/**
	 * The feature id for the '<em><b>Prefix</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SELECT_FIELD__PREFIX = FIELD__PREFIX;

	/**
	 * The feature id for the '<em><b>Suffix</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SELECT_FIELD__SUFFIX = FIELD__SUFFIX;

	/**
	 * The feature id for the '<em><b>Hidden</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SELECT_FIELD__HIDDEN = FIELD__HIDDEN;

	/**
	 * The feature id for the '<em><b>Patern</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SELECT_FIELD__PATERN = FIELD__PATERN;

	/**
	 * The feature id for the '<em><b>Patern Language</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SELECT_FIELD__PATERN_LANGUAGE = FIELD__PATERN_LANGUAGE;

	/**
	 * The feature id for the '<em><b>Path</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SELECT_FIELD__PATH = FIELD__PATH;

	/**
	 * The feature id for the '<em><b>Select Widget</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SELECT_FIELD__SELECT_WIDGET = FIELD_FEATURE_COUNT + 0;

	/**
	 * The number of structural features of the '<em>Select Field</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SELECT_FIELD_FEATURE_COUNT = FIELD_FEATURE_COUNT + 1;

	/**
	 * The feature id for the '<em><b>Styling</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int HTML_FIELD__STYLING = FIELD__STYLING;

	/**
	 * The feature id for the '<em><b>Stereotypes</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int HTML_FIELD__STEREOTYPES = FIELD__STEREOTYPES;

	/**
	 * The feature id for the '<em><b>Tags</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int HTML_FIELD__TAGS = FIELD__TAGS;

	/**
	 * The feature id for the '<em><b>Comments</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int HTML_FIELD__COMMENTS = FIELD__COMMENTS;

	/**
	 * The feature id for the '<em><b>Documentation</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int HTML_FIELD__DOCUMENTATION = FIELD__DOCUMENTATION;

	/**
	 * The feature id for the '<em><b>Description</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int HTML_FIELD__DESCRIPTION = FIELD__DESCRIPTION;

	/**
	 * The feature id for the '<em><b>Metainfo</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int HTML_FIELD__METAINFO = FIELD__METAINFO;

	/**
	 * The feature id for the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int HTML_FIELD__NAME = FIELD__NAME;

	/**
	 * The feature id for the '<em><b>Map To</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int HTML_FIELD__MAP_TO = FIELD__MAP_TO;

	/**
	 * The feature id for the '<em><b>Prefix</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int HTML_FIELD__PREFIX = FIELD__PREFIX;

	/**
	 * The feature id for the '<em><b>Suffix</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int HTML_FIELD__SUFFIX = FIELD__SUFFIX;

	/**
	 * The feature id for the '<em><b>Hidden</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int HTML_FIELD__HIDDEN = FIELD__HIDDEN;

	/**
	 * The feature id for the '<em><b>Patern</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int HTML_FIELD__PATERN = FIELD__PATERN;

	/**
	 * The feature id for the '<em><b>Patern Language</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int HTML_FIELD__PATERN_LANGUAGE = FIELD__PATERN_LANGUAGE;

	/**
	 * The feature id for the '<em><b>Path</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int HTML_FIELD__PATH = FIELD__PATH;

	/**
	 * The number of structural features of the '<em>Html Field</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int HTML_FIELD_FEATURE_COUNT = FIELD_FEATURE_COUNT + 0;

	/**
	 * The feature id for the '<em><b>Styling</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int URL_FIELD__STYLING = FIELD__STYLING;

	/**
	 * The feature id for the '<em><b>Stereotypes</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int URL_FIELD__STEREOTYPES = FIELD__STEREOTYPES;

	/**
	 * The feature id for the '<em><b>Tags</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int URL_FIELD__TAGS = FIELD__TAGS;

	/**
	 * The feature id for the '<em><b>Comments</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int URL_FIELD__COMMENTS = FIELD__COMMENTS;

	/**
	 * The feature id for the '<em><b>Documentation</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int URL_FIELD__DOCUMENTATION = FIELD__DOCUMENTATION;

	/**
	 * The feature id for the '<em><b>Description</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int URL_FIELD__DESCRIPTION = FIELD__DESCRIPTION;

	/**
	 * The feature id for the '<em><b>Metainfo</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int URL_FIELD__METAINFO = FIELD__METAINFO;

	/**
	 * The feature id for the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int URL_FIELD__NAME = FIELD__NAME;

	/**
	 * The feature id for the '<em><b>Map To</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int URL_FIELD__MAP_TO = FIELD__MAP_TO;

	/**
	 * The feature id for the '<em><b>Prefix</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int URL_FIELD__PREFIX = FIELD__PREFIX;

	/**
	 * The feature id for the '<em><b>Suffix</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int URL_FIELD__SUFFIX = FIELD__SUFFIX;

	/**
	 * The feature id for the '<em><b>Hidden</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int URL_FIELD__HIDDEN = FIELD__HIDDEN;

	/**
	 * The feature id for the '<em><b>Patern</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int URL_FIELD__PATERN = FIELD__PATERN;

	/**
	 * The feature id for the '<em><b>Patern Language</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int URL_FIELD__PATERN_LANGUAGE = FIELD__PATERN_LANGUAGE;

	/**
	 * The feature id for the '<em><b>Path</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int URL_FIELD__PATH = FIELD__PATH;

	/**
	 * The number of structural features of the '<em>URL Field</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int URL_FIELD_FEATURE_COUNT = FIELD_FEATURE_COUNT + 0;

	/**
	 * The feature id for the '<em><b>Styling</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int IMAGE_FIELD__STYLING = FILE_FIELD__STYLING;

	/**
	 * The feature id for the '<em><b>Stereotypes</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int IMAGE_FIELD__STEREOTYPES = FILE_FIELD__STEREOTYPES;

	/**
	 * The feature id for the '<em><b>Tags</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int IMAGE_FIELD__TAGS = FILE_FIELD__TAGS;

	/**
	 * The feature id for the '<em><b>Comments</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int IMAGE_FIELD__COMMENTS = FILE_FIELD__COMMENTS;

	/**
	 * The feature id for the '<em><b>Documentation</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int IMAGE_FIELD__DOCUMENTATION = FILE_FIELD__DOCUMENTATION;

	/**
	 * The feature id for the '<em><b>Description</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int IMAGE_FIELD__DESCRIPTION = FILE_FIELD__DESCRIPTION;

	/**
	 * The feature id for the '<em><b>Metainfo</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int IMAGE_FIELD__METAINFO = FILE_FIELD__METAINFO;

	/**
	 * The feature id for the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int IMAGE_FIELD__NAME = FILE_FIELD__NAME;

	/**
	 * The feature id for the '<em><b>Map To</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int IMAGE_FIELD__MAP_TO = FILE_FIELD__MAP_TO;

	/**
	 * The feature id for the '<em><b>Prefix</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int IMAGE_FIELD__PREFIX = FILE_FIELD__PREFIX;

	/**
	 * The feature id for the '<em><b>Suffix</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int IMAGE_FIELD__SUFFIX = FILE_FIELD__SUFFIX;

	/**
	 * The feature id for the '<em><b>Hidden</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int IMAGE_FIELD__HIDDEN = FILE_FIELD__HIDDEN;

	/**
	 * The feature id for the '<em><b>Patern</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int IMAGE_FIELD__PATERN = FILE_FIELD__PATERN;

	/**
	 * The feature id for the '<em><b>Patern Language</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int IMAGE_FIELD__PATERN_LANGUAGE = FILE_FIELD__PATERN_LANGUAGE;

	/**
	 * The feature id for the '<em><b>Path</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int IMAGE_FIELD__PATH = FILE_FIELD__PATH;

	/**
	 * The number of structural features of the '<em>Image Field</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int IMAGE_FIELD_FEATURE_COUNT = FILE_FIELD_FEATURE_COUNT + 0;

	/**
	 * The feature id for the '<em><b>Paging</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PAGINABLE__PAGING = 0;

	/**
	 * The number of structural features of the '<em>Paginable</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PAGINABLE_FEATURE_COUNT = 1;

	/**
	 * The meta object id for the '{@link com.bluexml.side.view.impl.ActionableImpl <em>Actionable</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see com.bluexml.side.view.impl.ActionableImpl
	 * @see com.bluexml.side.view.impl.ViewPackageImpl#getActionable()
	 * @generated
	 */
	int ACTIONABLE = 36;

	/**
	 * The feature id for the '<em><b>Operations</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ACTIONABLE__OPERATIONS = 0;

	/**
	 * The number of structural features of the '<em>Actionable</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ACTIONABLE_FEATURE_COUNT = 1;

	/**
	 * The feature id for the '<em><b>Sorting</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SORTABLE__SORTING = 0;

	/**
	 * The number of structural features of the '<em>Sortable</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SORTABLE_FEATURE_COUNT = 1;

	/**
	 * The feature id for the '<em><b>Editable</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int EDITABLE__EDITABLE = 0;

	/**
	 * The number of structural features of the '<em>Editable</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int EDITABLE_FEATURE_COUNT = 1;

	/**
	 * The feature id for the '<em><b>Movable</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int MOVABLE__MOVABLE = 0;

	/**
	 * The number of structural features of the '<em>Movable</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int MOVABLE_FEATURE_COUNT = 1;

	/**
	 * The feature id for the '<em><b>Filtering</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int FILTERABLE__FILTERING = 0;

	/**
	 * The number of structural features of the '<em>Filterable</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int FILTERABLE_FEATURE_COUNT = 1;

	/**
	 * The feature id for the '<em><b>Styling</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int FIELD_GROUP__STYLING = FIELD_CONTAINER__STYLING;

	/**
	 * The feature id for the '<em><b>Stereotypes</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int FIELD_GROUP__STEREOTYPES = FIELD_CONTAINER__STEREOTYPES;

	/**
	 * The feature id for the '<em><b>Tags</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int FIELD_GROUP__TAGS = FIELD_CONTAINER__TAGS;

	/**
	 * The feature id for the '<em><b>Comments</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int FIELD_GROUP__COMMENTS = FIELD_CONTAINER__COMMENTS;

	/**
	 * The feature id for the '<em><b>Documentation</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int FIELD_GROUP__DOCUMENTATION = FIELD_CONTAINER__DOCUMENTATION;

	/**
	 * The feature id for the '<em><b>Description</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int FIELD_GROUP__DESCRIPTION = FIELD_CONTAINER__DESCRIPTION;

	/**
	 * The feature id for the '<em><b>Metainfo</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int FIELD_GROUP__METAINFO = FIELD_CONTAINER__METAINFO;

	/**
	 * The feature id for the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int FIELD_GROUP__NAME = FIELD_CONTAINER__NAME;

	/**
	 * The feature id for the '<em><b>Map To</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int FIELD_GROUP__MAP_TO = FIELD_CONTAINER__MAP_TO;

	/**
	 * The feature id for the '<em><b>Prefix</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int FIELD_GROUP__PREFIX = FIELD_CONTAINER__PREFIX;

	/**
	 * The feature id for the '<em><b>Suffix</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int FIELD_GROUP__SUFFIX = FIELD_CONTAINER__SUFFIX;

	/**
	 * The feature id for the '<em><b>Hidden</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int FIELD_GROUP__HIDDEN = FIELD_CONTAINER__HIDDEN;

	/**
	 * The feature id for the '<em><b>Children</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int FIELD_GROUP__CHILDREN = FIELD_CONTAINER__CHILDREN;

	/**
	 * The feature id for the '<em><b>Disabled</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int FIELD_GROUP__DISABLED = FIELD_CONTAINER__DISABLED;

	/**
	 * The number of structural features of the '<em>Field Group</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int FIELD_GROUP_FEATURE_COUNT = FIELD_CONTAINER_FEATURE_COUNT + 0;

	/**
	 * The meta object id for the '{@link com.bluexml.side.view.SortOrder <em>Sort Order</em>}' enum.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see com.bluexml.side.view.SortOrder
	 * @see com.bluexml.side.view.impl.ViewPackageImpl#getSortOrder()
	 * @generated
	 */
	int SORT_ORDER = 42;

	/**
	 * The meta object id for the '{@link com.bluexml.side.view.PaginationStyle <em>Pagination Style</em>}' enum.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see com.bluexml.side.view.PaginationStyle
	 * @see com.bluexml.side.view.impl.ViewPackageImpl#getPaginationStyle()
	 * @generated
	 */
	int PAGINATION_STYLE = 43;

	/**
	 * The meta object id for the '{@link com.bluexml.side.view.Halign <em>Halign</em>}' enum.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see com.bluexml.side.view.Halign
	 * @see com.bluexml.side.view.impl.ViewPackageImpl#getHalign()
	 * @generated
	 */
	int HALIGN = 44;

	/**
	 * The meta object id for the '{@link com.bluexml.side.view.LoadingType <em>Loading Type</em>}' enum.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see com.bluexml.side.view.LoadingType
	 * @see com.bluexml.side.view.impl.ViewPackageImpl#getLoadingType()
	 * @generated
	 */
	int LOADING_TYPE = 45;

	/**
	 * The meta object id for the '{@link com.bluexml.side.view.WidgetTextType <em>Widget Text Type</em>}' enum.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see com.bluexml.side.view.WidgetTextType
	 * @see com.bluexml.side.view.impl.ViewPackageImpl#getWidgetTextType()
	 * @generated
	 */
	int WIDGET_TEXT_TYPE = 46;

	/**
	 * The meta object id for the '{@link com.bluexml.side.view.SelectWidgetType <em>Select Widget Type</em>}' enum.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see com.bluexml.side.view.SelectWidgetType
	 * @see com.bluexml.side.view.impl.ViewPackageImpl#getSelectWidgetType()
	 * @generated
	 */
	int SELECT_WIDGET_TYPE = 47;

	/**
	 * The meta object id for the '{@link com.bluexml.side.view.FacetDisplayType <em>Facet Display Type</em>}' enum.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see com.bluexml.side.view.FacetDisplayType
	 * @see com.bluexml.side.view.impl.ViewPackageImpl#getFacetDisplayType()
	 * @generated
	 */
	int FACET_DISPLAY_TYPE = 48;


	/**
	 * Returns the meta object for class '{@link com.bluexml.side.view.ViewCollection <em>Collection</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Collection</em>'.
	 * @see com.bluexml.side.view.ViewCollection
	 * @generated
	 */
	EClass getViewCollection();

	/**
	 * Returns the meta object for the containment reference list '{@link com.bluexml.side.view.ViewCollection#getViews <em>Views</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference list '<em>Views</em>'.
	 * @see com.bluexml.side.view.ViewCollection#getViews()
	 * @see #getViewCollection()
	 * @generated
	 */
	EReference getViewCollection_Views();

	/**
	 * Returns the meta object for the containment reference list '{@link com.bluexml.side.view.ViewCollection#getComposedViews <em>Composed Views</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference list '<em>Composed Views</em>'.
	 * @see com.bluexml.side.view.ViewCollection#getComposedViews()
	 * @see #getViewCollection()
	 * @generated
	 */
	EReference getViewCollection_ComposedViews();

	/**
	 * Returns the meta object for class '{@link com.bluexml.side.view.FieldElement <em>Field Element</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Field Element</em>'.
	 * @see com.bluexml.side.view.FieldElement
	 * @generated
	 */
	EClass getFieldElement();

	/**
	 * Returns the meta object for the reference '{@link com.bluexml.side.view.FieldElement#getMapTo <em>Map To</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference '<em>Map To</em>'.
	 * @see com.bluexml.side.view.FieldElement#getMapTo()
	 * @see #getFieldElement()
	 * @generated
	 */
	EReference getFieldElement_MapTo();

	/**
	 * Returns the meta object for the attribute '{@link com.bluexml.side.view.FieldElement#getPrefix <em>Prefix</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Prefix</em>'.
	 * @see com.bluexml.side.view.FieldElement#getPrefix()
	 * @see #getFieldElement()
	 * @generated
	 */
	EAttribute getFieldElement_Prefix();

	/**
	 * Returns the meta object for the attribute '{@link com.bluexml.side.view.FieldElement#getSuffix <em>Suffix</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Suffix</em>'.
	 * @see com.bluexml.side.view.FieldElement#getSuffix()
	 * @see #getFieldElement()
	 * @generated
	 */
	EAttribute getFieldElement_Suffix();

	/**
	 * Returns the meta object for the attribute '{@link com.bluexml.side.view.FieldElement#isHidden <em>Hidden</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Hidden</em>'.
	 * @see com.bluexml.side.view.FieldElement#isHidden()
	 * @see #getFieldElement()
	 * @generated
	 */
	EAttribute getFieldElement_Hidden();

	/**
	 * Returns the meta object for class '{@link com.bluexml.side.view.AbstractView <em>Abstract View</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Abstract View</em>'.
	 * @see com.bluexml.side.view.AbstractView
	 * @generated
	 */
	EClass getAbstractView();

	/**
	 * Returns the meta object for class '{@link com.bluexml.side.view.AbstractDataTable <em>Abstract Data Table</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Abstract Data Table</em>'.
	 * @see com.bluexml.side.view.AbstractDataTable
	 * @generated
	 */
	EClass getAbstractDataTable();

	/**
	 * Returns the meta object for the containment reference '{@link com.bluexml.side.view.AbstractDataTable#getHaveRowActions <em>Have Row Actions</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference '<em>Have Row Actions</em>'.
	 * @see com.bluexml.side.view.AbstractDataTable#getHaveRowActions()
	 * @see #getAbstractDataTable()
	 * @generated
	 */
	EReference getAbstractDataTable_HaveRowActions();

	/**
	 * Returns the meta object for the containment reference '{@link com.bluexml.side.view.AbstractDataTable#getHaveSelectActions <em>Have Select Actions</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference '<em>Have Select Actions</em>'.
	 * @see com.bluexml.side.view.AbstractDataTable#getHaveSelectActions()
	 * @see #getAbstractDataTable()
	 * @generated
	 */
	EReference getAbstractDataTable_HaveSelectActions();

	/**
	 * Returns the meta object for the containment reference '{@link com.bluexml.side.view.AbstractDataTable#getHaveDefaultColActions <em>Have Default Col Actions</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference '<em>Have Default Col Actions</em>'.
	 * @see com.bluexml.side.view.AbstractDataTable#getHaveDefaultColActions()
	 * @see #getAbstractDataTable()
	 * @generated
	 */
	EReference getAbstractDataTable_HaveDefaultColActions();

	/**
	 * Returns the meta object for class '{@link com.bluexml.side.view.Col <em>Col</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Col</em>'.
	 * @see com.bluexml.side.view.Col
	 * @generated
	 */
	EClass getCol();

	/**
	 * Returns the meta object for class '{@link com.bluexml.side.view.Paging <em>Paging</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Paging</em>'.
	 * @see com.bluexml.side.view.Paging
	 * @generated
	 */
	EClass getPaging();

	/**
	 * Returns the meta object for the attribute '{@link com.bluexml.side.view.Paging#getPaginationStyle <em>Pagination Style</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Pagination Style</em>'.
	 * @see com.bluexml.side.view.Paging#getPaginationStyle()
	 * @see #getPaging()
	 * @generated
	 */
	EAttribute getPaging_PaginationStyle();

	/**
	 * Returns the meta object for the attribute '{@link com.bluexml.side.view.Paging#getMaxItems <em>Max Items</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Max Items</em>'.
	 * @see com.bluexml.side.view.Paging#getMaxItems()
	 * @see #getPaging()
	 * @generated
	 */
	EAttribute getPaging_MaxItems();

	/**
	 * Returns the meta object for the attribute '{@link com.bluexml.side.view.Paging#getMaxPage <em>Max Page</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Max Page</em>'.
	 * @see com.bluexml.side.view.Paging#getMaxPage()
	 * @see #getPaging()
	 * @generated
	 */
	EAttribute getPaging_MaxPage();

	/**
	 * Returns the meta object for class '{@link com.bluexml.side.view.Sorting <em>Sorting</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Sorting</em>'.
	 * @see com.bluexml.side.view.Sorting
	 * @generated
	 */
	EClass getSorting();

	/**
	 * Returns the meta object for the attribute '{@link com.bluexml.side.view.Sorting#getSortOrder <em>Sort Order</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Sort Order</em>'.
	 * @see com.bluexml.side.view.Sorting#getSortOrder()
	 * @see #getSorting()
	 * @generated
	 */
	EAttribute getSorting_SortOrder();

	/**
	 * Returns the meta object for the attribute '{@link com.bluexml.side.view.Sorting#isSorted <em>Sorted</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Sorted</em>'.
	 * @see com.bluexml.side.view.Sorting#isSorted()
	 * @see #getSorting()
	 * @generated
	 */
	EAttribute getSorting_Sorted();

	/**
	 * Returns the meta object for class '{@link com.bluexml.side.view.Filtering <em>Filtering</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Filtering</em>'.
	 * @see com.bluexml.side.view.Filtering
	 * @generated
	 */
	EClass getFiltering();

	/**
	 * Returns the meta object for the attribute '{@link com.bluexml.side.view.Filtering#getDefaultFilterValue <em>Default Filter Value</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Default Filter Value</em>'.
	 * @see com.bluexml.side.view.Filtering#getDefaultFilterValue()
	 * @see #getFiltering()
	 * @generated
	 */
	EAttribute getFiltering_DefaultFilterValue();

	/**
	 * Returns the meta object for class '{@link com.bluexml.side.view.Styling <em>Styling</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Styling</em>'.
	 * @see com.bluexml.side.view.Styling
	 * @generated
	 */
	EClass getStyling();

	/**
	 * Returns the meta object for the attribute '{@link com.bluexml.side.view.Styling#getStyle <em>Style</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Style</em>'.
	 * @see com.bluexml.side.view.Styling#getStyle()
	 * @see #getStyling()
	 * @generated
	 */
	EAttribute getStyling_Style();

	/**
	 * Returns the meta object for the attribute '{@link com.bluexml.side.view.Styling#getHeight <em>Height</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Height</em>'.
	 * @see com.bluexml.side.view.Styling#getHeight()
	 * @see #getStyling()
	 * @generated
	 */
	EAttribute getStyling_Height();

	/**
	 * Returns the meta object for the attribute '{@link com.bluexml.side.view.Styling#getWidth <em>Width</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Width</em>'.
	 * @see com.bluexml.side.view.Styling#getWidth()
	 * @see #getStyling()
	 * @generated
	 */
	EAttribute getStyling_Width();

	/**
	 * Returns the meta object for class '{@link com.bluexml.side.view.DataTableElement <em>Data Table Element</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Data Table Element</em>'.
	 * @see com.bluexml.side.view.DataTableElement
	 * @generated
	 */
	EClass getDataTableElement();

	/**
	 * Returns the meta object for class '{@link com.bluexml.side.view.DataList <em>Data List</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Data List</em>'.
	 * @see com.bluexml.side.view.DataList
	 * @generated
	 */
	EClass getDataList();

	/**
	 * Returns the meta object for class '{@link com.bluexml.side.view.DataTable <em>Data Table</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Data Table</em>'.
	 * @see com.bluexml.side.view.DataTable
	 * @generated
	 */
	EClass getDataTable();

	/**
	 * Returns the meta object for the containment reference '{@link com.bluexml.side.view.DataTable#getDefaultColSetUp <em>Default Col Set Up</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference '<em>Default Col Set Up</em>'.
	 * @see com.bluexml.side.view.DataTable#getDefaultColSetUp()
	 * @see #getDataTable()
	 * @generated
	 */
	EReference getDataTable_DefaultColSetUp();

	/**
	 * Returns the meta object for class '{@link com.bluexml.side.view.FacetMap <em>Facet Map</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Facet Map</em>'.
	 * @see com.bluexml.side.view.FacetMap
	 * @generated
	 */
	EClass getFacetMap();

	/**
	 * Returns the meta object for the attribute '{@link com.bluexml.side.view.FacetMap#isDisplayEmptyFacet <em>Display Empty Facet</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Display Empty Facet</em>'.
	 * @see com.bluexml.side.view.FacetMap#isDisplayEmptyFacet()
	 * @see #getFacetMap()
	 * @generated
	 */
	EAttribute getFacetMap_DisplayEmptyFacet();

	/**
	 * Returns the meta object for the attribute '{@link com.bluexml.side.view.FacetMap#getFacetDisplayType <em>Facet Display Type</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Facet Display Type</em>'.
	 * @see com.bluexml.side.view.FacetMap#getFacetDisplayType()
	 * @see #getFacetMap()
	 * @generated
	 */
	EAttribute getFacetMap_FacetDisplayType();

	/**
	 * Returns the meta object for class '{@link com.bluexml.side.view.Tree <em>Tree</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Tree</em>'.
	 * @see com.bluexml.side.view.Tree
	 * @generated
	 */
	EClass getTree();

	/**
	 * Returns the meta object for the containment reference '{@link com.bluexml.side.view.Tree#getNodeOperations <em>Node Operations</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference '<em>Node Operations</em>'.
	 * @see com.bluexml.side.view.Tree#getNodeOperations()
	 * @see #getTree()
	 * @generated
	 */
	EReference getTree_NodeOperations();

	/**
	 * Returns the meta object for the containment reference '{@link com.bluexml.side.view.Tree#getNodeValue <em>Node Value</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference '<em>Node Value</em>'.
	 * @see com.bluexml.side.view.Tree#getNodeValue()
	 * @see #getTree()
	 * @generated
	 */
	EReference getTree_NodeValue();

	/**
	 * Returns the meta object for the attribute '{@link com.bluexml.side.view.Tree#getDefaultDepth <em>Default Depth</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Default Depth</em>'.
	 * @see com.bluexml.side.view.Tree#getDefaultDepth()
	 * @see #getTree()
	 * @generated
	 */
	EAttribute getTree_DefaultDepth();

	/**
	 * Returns the meta object for the attribute '{@link com.bluexml.side.view.Tree#getMaxDepth <em>Max Depth</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Max Depth</em>'.
	 * @see com.bluexml.side.view.Tree#getMaxDepth()
	 * @see #getTree()
	 * @generated
	 */
	EAttribute getTree_MaxDepth();

	/**
	 * Returns the meta object for class '{@link com.bluexml.side.view.ComposedView <em>Composed View</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Composed View</em>'.
	 * @see com.bluexml.side.view.ComposedView
	 * @generated
	 */
	EClass getComposedView();

	/**
	 * Returns the meta object for class '{@link com.bluexml.side.view.FieldContainer <em>Field Container</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Field Container</em>'.
	 * @see com.bluexml.side.view.FieldContainer
	 * @generated
	 */
	EClass getFieldContainer();

	/**
	 * Returns the meta object for the containment reference list '{@link com.bluexml.side.view.FieldContainer#getChildren <em>Children</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference list '<em>Children</em>'.
	 * @see com.bluexml.side.view.FieldContainer#getChildren()
	 * @see #getFieldContainer()
	 * @generated
	 */
	EReference getFieldContainer_Children();

	/**
	 * Returns the meta object for the containment reference list '{@link com.bluexml.side.view.FieldContainer#getDisabled <em>Disabled</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference list '<em>Disabled</em>'.
	 * @see com.bluexml.side.view.FieldContainer#getDisabled()
	 * @see #getFieldContainer()
	 * @generated
	 */
	EReference getFieldContainer_Disabled();

	/**
	 * Returns the meta object for class '{@link com.bluexml.side.view.Field <em>Field</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Field</em>'.
	 * @see com.bluexml.side.view.Field
	 * @generated
	 */
	EClass getField();

	/**
	 * Returns the meta object for the attribute '{@link com.bluexml.side.view.Field#getPatern <em>Patern</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Patern</em>'.
	 * @see com.bluexml.side.view.Field#getPatern()
	 * @see #getField()
	 * @generated
	 */
	EAttribute getField_Patern();

	/**
	 * Returns the meta object for the attribute '{@link com.bluexml.side.view.Field#getPaternLanguage <em>Patern Language</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Patern Language</em>'.
	 * @see com.bluexml.side.view.Field#getPaternLanguage()
	 * @see #getField()
	 * @generated
	 */
	EAttribute getField_PaternLanguage();

	/**
	 * Returns the meta object for the reference list '{@link com.bluexml.side.view.Field#getPath <em>Path</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference list '<em>Path</em>'.
	 * @see com.bluexml.side.view.Field#getPath()
	 * @see #getField()
	 * @generated
	 */
	EReference getField_Path();

	/**
	 * Returns the meta object for class '{@link com.bluexml.side.view.TextField <em>Text Field</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Text Field</em>'.
	 * @see com.bluexml.side.view.TextField
	 * @generated
	 */
	EClass getTextField();

	/**
	 * Returns the meta object for the attribute '{@link com.bluexml.side.view.TextField#getWidgetType <em>Widget Type</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Widget Type</em>'.
	 * @see com.bluexml.side.view.TextField#getWidgetType()
	 * @see #getTextField()
	 * @generated
	 */
	EAttribute getTextField_WidgetType();

	/**
	 * Returns the meta object for class '{@link com.bluexml.side.view.PasswordField <em>Password Field</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Password Field</em>'.
	 * @see com.bluexml.side.view.PasswordField
	 * @generated
	 */
	EClass getPasswordField();

	/**
	 * Returns the meta object for class '{@link com.bluexml.side.view.BooleanField <em>Boolean Field</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Boolean Field</em>'.
	 * @see com.bluexml.side.view.BooleanField
	 * @generated
	 */
	EClass getBooleanField();

	/**
	 * Returns the meta object for the attribute '{@link com.bluexml.side.view.BooleanField#isSplit <em>Split</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Split</em>'.
	 * @see com.bluexml.side.view.BooleanField#isSplit()
	 * @see #getBooleanField()
	 * @generated
	 */
	EAttribute getBooleanField_Split();

	/**
	 * Returns the meta object for class '{@link com.bluexml.side.view.FloatField <em>Float Field</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Float Field</em>'.
	 * @see com.bluexml.side.view.FloatField
	 * @generated
	 */
	EClass getFloatField();

	/**
	 * Returns the meta object for class '{@link com.bluexml.side.view.ActionField <em>Action Field</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Action Field</em>'.
	 * @see com.bluexml.side.view.ActionField
	 * @generated
	 */
	EClass getActionField();

	/**
	 * Returns the meta object for the containment reference list '{@link com.bluexml.side.view.ActionField#getOperations <em>Operations</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference list '<em>Operations</em>'.
	 * @see com.bluexml.side.view.ActionField#getOperations()
	 * @see #getActionField()
	 * @generated
	 */
	EReference getActionField_Operations();

	/**
	 * Returns the meta object for class '{@link com.bluexml.side.view.DateField <em>Date Field</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Date Field</em>'.
	 * @see com.bluexml.side.view.DateField
	 * @generated
	 */
	EClass getDateField();

	/**
	 * Returns the meta object for class '{@link com.bluexml.side.view.TimeField <em>Time Field</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Time Field</em>'.
	 * @see com.bluexml.side.view.TimeField
	 * @generated
	 */
	EClass getTimeField();

	/**
	 * Returns the meta object for class '{@link com.bluexml.side.view.DateTimeField <em>Date Time Field</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Date Time Field</em>'.
	 * @see com.bluexml.side.view.DateTimeField
	 * @generated
	 */
	EClass getDateTimeField();

	/**
	 * Returns the meta object for class '{@link com.bluexml.side.view.PhoneNumberField <em>Phone Number Field</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Phone Number Field</em>'.
	 * @see com.bluexml.side.view.PhoneNumberField
	 * @generated
	 */
	EClass getPhoneNumberField();

	/**
	 * Returns the meta object for class '{@link com.bluexml.side.view.EmailField <em>Email Field</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Email Field</em>'.
	 * @see com.bluexml.side.view.EmailField
	 * @generated
	 */
	EClass getEmailField();

	/**
	 * Returns the meta object for class '{@link com.bluexml.side.view.IntegerField <em>Integer Field</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Integer Field</em>'.
	 * @see com.bluexml.side.view.IntegerField
	 * @generated
	 */
	EClass getIntegerField();

	/**
	 * Returns the meta object for class '{@link com.bluexml.side.view.FileField <em>File Field</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>File Field</em>'.
	 * @see com.bluexml.side.view.FileField
	 * @generated
	 */
	EClass getFileField();

	/**
	 * Returns the meta object for class '{@link com.bluexml.side.view.SelectField <em>Select Field</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Select Field</em>'.
	 * @see com.bluexml.side.view.SelectField
	 * @generated
	 */
	EClass getSelectField();

	/**
	 * Returns the meta object for the attribute '{@link com.bluexml.side.view.SelectField#getSelectWidget <em>Select Widget</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Select Widget</em>'.
	 * @see com.bluexml.side.view.SelectField#getSelectWidget()
	 * @see #getSelectField()
	 * @generated
	 */
	EAttribute getSelectField_SelectWidget();

	/**
	 * Returns the meta object for class '{@link com.bluexml.side.view.HtmlField <em>Html Field</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Html Field</em>'.
	 * @see com.bluexml.side.view.HtmlField
	 * @generated
	 */
	EClass getHtmlField();

	/**
	 * Returns the meta object for class '{@link com.bluexml.side.view.URLField <em>URL Field</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>URL Field</em>'.
	 * @see com.bluexml.side.view.URLField
	 * @generated
	 */
	EClass getURLField();

	/**
	 * Returns the meta object for class '{@link com.bluexml.side.view.ImageField <em>Image Field</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Image Field</em>'.
	 * @see com.bluexml.side.view.ImageField
	 * @generated
	 */
	EClass getImageField();

	/**
	 * Returns the meta object for class '{@link com.bluexml.side.view.Stylable <em>Stylable</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Stylable</em>'.
	 * @see com.bluexml.side.view.Stylable
	 * @generated
	 */
	EClass getStylable();

	/**
	 * Returns the meta object for the containment reference '{@link com.bluexml.side.view.Stylable#getStyling <em>Styling</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference '<em>Styling</em>'.
	 * @see com.bluexml.side.view.Stylable#getStyling()
	 * @see #getStylable()
	 * @generated
	 */
	EReference getStylable_Styling();

	/**
	 * Returns the meta object for class '{@link com.bluexml.side.view.Paginable <em>Paginable</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Paginable</em>'.
	 * @see com.bluexml.side.view.Paginable
	 * @generated
	 */
	EClass getPaginable();

	/**
	 * Returns the meta object for the containment reference '{@link com.bluexml.side.view.Paginable#getPaging <em>Paging</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference '<em>Paging</em>'.
	 * @see com.bluexml.side.view.Paginable#getPaging()
	 * @see #getPaginable()
	 * @generated
	 */
	EReference getPaginable_Paging();

	/**
	 * Returns the meta object for class '{@link com.bluexml.side.view.Sortable <em>Sortable</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Sortable</em>'.
	 * @see com.bluexml.side.view.Sortable
	 * @generated
	 */
	EClass getSortable();

	/**
	 * Returns the meta object for the containment reference '{@link com.bluexml.side.view.Sortable#getSorting <em>Sorting</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference '<em>Sorting</em>'.
	 * @see com.bluexml.side.view.Sortable#getSorting()
	 * @see #getSortable()
	 * @generated
	 */
	EReference getSortable_Sorting();

	/**
	 * Returns the meta object for class '{@link com.bluexml.side.view.Editable <em>Editable</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Editable</em>'.
	 * @see com.bluexml.side.view.Editable
	 * @generated
	 */
	EClass getEditable();

	/**
	 * Returns the meta object for the attribute '{@link com.bluexml.side.view.Editable#isEditable <em>Editable</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Editable</em>'.
	 * @see com.bluexml.side.view.Editable#isEditable()
	 * @see #getEditable()
	 * @generated
	 */
	EAttribute getEditable_Editable();

	/**
	 * Returns the meta object for class '{@link com.bluexml.side.view.Movable <em>Movable</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Movable</em>'.
	 * @see com.bluexml.side.view.Movable
	 * @generated
	 */
	EClass getMovable();

	/**
	 * Returns the meta object for the attribute '{@link com.bluexml.side.view.Movable#isMovable <em>Movable</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Movable</em>'.
	 * @see com.bluexml.side.view.Movable#isMovable()
	 * @see #getMovable()
	 * @generated
	 */
	EAttribute getMovable_Movable();

	/**
	 * Returns the meta object for class '{@link com.bluexml.side.view.Filterable <em>Filterable</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Filterable</em>'.
	 * @see com.bluexml.side.view.Filterable
	 * @generated
	 */
	EClass getFilterable();

	/**
	 * Returns the meta object for the containment reference '{@link com.bluexml.side.view.Filterable#getFiltering <em>Filtering</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference '<em>Filtering</em>'.
	 * @see com.bluexml.side.view.Filterable#getFiltering()
	 * @see #getFilterable()
	 * @generated
	 */
	EReference getFilterable_Filtering();

	/**
	 * Returns the meta object for class '{@link com.bluexml.side.view.FieldGroup <em>Field Group</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Field Group</em>'.
	 * @see com.bluexml.side.view.FieldGroup
	 * @generated
	 */
	EClass getFieldGroup();

	/**
	 * Returns the meta object for class '{@link com.bluexml.side.view.AbstractViewOf <em>Abstract View Of</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Abstract View Of</em>'.
	 * @see com.bluexml.side.view.AbstractViewOf
	 * @generated
	 */
	EClass getAbstractViewOf();

	/**
	 * Returns the meta object for the reference '{@link com.bluexml.side.view.AbstractViewOf#getViewOf <em>View Of</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference '<em>View Of</em>'.
	 * @see com.bluexml.side.view.AbstractViewOf#getViewOf()
	 * @see #getAbstractViewOf()
	 * @generated
	 */
	EReference getAbstractViewOf_ViewOf();

	/**
	 * Returns the meta object for the attribute '{@link com.bluexml.side.view.AbstractViewOf#isGenerateWebscript <em>Generate Webscript</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Generate Webscript</em>'.
	 * @see com.bluexml.side.view.AbstractViewOf#isGenerateWebscript()
	 * @see #getAbstractViewOf()
	 * @generated
	 */
	EAttribute getAbstractViewOf_GenerateWebscript();

	/**
	 * Returns the meta object for class '{@link com.bluexml.side.view.Actionable <em>Actionable</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Actionable</em>'.
	 * @see com.bluexml.side.view.Actionable
	 * @generated
	 */
	EClass getActionable();

	/**
	 * Returns the meta object for the containment reference '{@link com.bluexml.side.view.Actionable#getOperations <em>Operations</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference '<em>Operations</em>'.
	 * @see com.bluexml.side.view.Actionable#getOperations()
	 * @see #getActionable()
	 * @generated
	 */
	EReference getActionable_Operations();

	/**
	 * Returns the meta object for enum '{@link com.bluexml.side.view.SortOrder <em>Sort Order</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for enum '<em>Sort Order</em>'.
	 * @see com.bluexml.side.view.SortOrder
	 * @generated
	 */
	EEnum getSortOrder();

	/**
	 * Returns the meta object for enum '{@link com.bluexml.side.view.PaginationStyle <em>Pagination Style</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for enum '<em>Pagination Style</em>'.
	 * @see com.bluexml.side.view.PaginationStyle
	 * @generated
	 */
	EEnum getPaginationStyle();

	/**
	 * Returns the meta object for enum '{@link com.bluexml.side.view.Halign <em>Halign</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for enum '<em>Halign</em>'.
	 * @see com.bluexml.side.view.Halign
	 * @generated
	 */
	EEnum getHalign();

	/**
	 * Returns the meta object for enum '{@link com.bluexml.side.view.LoadingType <em>Loading Type</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for enum '<em>Loading Type</em>'.
	 * @see com.bluexml.side.view.LoadingType
	 * @generated
	 */
	EEnum getLoadingType();

	/**
	 * Returns the meta object for enum '{@link com.bluexml.side.view.WidgetTextType <em>Widget Text Type</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for enum '<em>Widget Text Type</em>'.
	 * @see com.bluexml.side.view.WidgetTextType
	 * @generated
	 */
	EEnum getWidgetTextType();

	/**
	 * Returns the meta object for enum '{@link com.bluexml.side.view.SelectWidgetType <em>Select Widget Type</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for enum '<em>Select Widget Type</em>'.
	 * @see com.bluexml.side.view.SelectWidgetType
	 * @generated
	 */
	EEnum getSelectWidgetType();

	/**
	 * Returns the meta object for enum '{@link com.bluexml.side.view.FacetDisplayType <em>Facet Display Type</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for enum '<em>Facet Display Type</em>'.
	 * @see com.bluexml.side.view.FacetDisplayType
	 * @generated
	 */
	EEnum getFacetDisplayType();

	/**
	 * Returns the factory that creates the instances of the model.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the factory that creates the instances of the model.
	 * @generated
	 */
	ViewFactory getViewFactory();

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
		 * The meta object literal for the '{@link com.bluexml.side.view.impl.ViewCollectionImpl <em>Collection</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see com.bluexml.side.view.impl.ViewCollectionImpl
		 * @see com.bluexml.side.view.impl.ViewPackageImpl#getViewCollection()
		 * @generated
		 */
		EClass VIEW_COLLECTION = eINSTANCE.getViewCollection();

		/**
		 * The meta object literal for the '<em><b>Views</b></em>' containment reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference VIEW_COLLECTION__VIEWS = eINSTANCE.getViewCollection_Views();

		/**
		 * The meta object literal for the '<em><b>Composed Views</b></em>' containment reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference VIEW_COLLECTION__COMPOSED_VIEWS = eINSTANCE.getViewCollection_ComposedViews();

		/**
		 * The meta object literal for the '{@link com.bluexml.side.view.impl.FieldElementImpl <em>Field Element</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see com.bluexml.side.view.impl.FieldElementImpl
		 * @see com.bluexml.side.view.impl.ViewPackageImpl#getFieldElement()
		 * @generated
		 */
		EClass FIELD_ELEMENT = eINSTANCE.getFieldElement();

		/**
		 * The meta object literal for the '<em><b>Map To</b></em>' reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference FIELD_ELEMENT__MAP_TO = eINSTANCE.getFieldElement_MapTo();

		/**
		 * The meta object literal for the '<em><b>Prefix</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute FIELD_ELEMENT__PREFIX = eINSTANCE.getFieldElement_Prefix();

		/**
		 * The meta object literal for the '<em><b>Suffix</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute FIELD_ELEMENT__SUFFIX = eINSTANCE.getFieldElement_Suffix();

		/**
		 * The meta object literal for the '<em><b>Hidden</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute FIELD_ELEMENT__HIDDEN = eINSTANCE.getFieldElement_Hidden();

		/**
		 * The meta object literal for the '{@link com.bluexml.side.view.impl.AbstractViewImpl <em>Abstract View</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see com.bluexml.side.view.impl.AbstractViewImpl
		 * @see com.bluexml.side.view.impl.ViewPackageImpl#getAbstractView()
		 * @generated
		 */
		EClass ABSTRACT_VIEW = eINSTANCE.getAbstractView();

		/**
		 * The meta object literal for the '{@link com.bluexml.side.view.impl.AbstractDataTableImpl <em>Abstract Data Table</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see com.bluexml.side.view.impl.AbstractDataTableImpl
		 * @see com.bluexml.side.view.impl.ViewPackageImpl#getAbstractDataTable()
		 * @generated
		 */
		EClass ABSTRACT_DATA_TABLE = eINSTANCE.getAbstractDataTable();

		/**
		 * The meta object literal for the '<em><b>Have Row Actions</b></em>' containment reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference ABSTRACT_DATA_TABLE__HAVE_ROW_ACTIONS = eINSTANCE.getAbstractDataTable_HaveRowActions();

		/**
		 * The meta object literal for the '<em><b>Have Select Actions</b></em>' containment reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference ABSTRACT_DATA_TABLE__HAVE_SELECT_ACTIONS = eINSTANCE.getAbstractDataTable_HaveSelectActions();

		/**
		 * The meta object literal for the '<em><b>Have Default Col Actions</b></em>' containment reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference ABSTRACT_DATA_TABLE__HAVE_DEFAULT_COL_ACTIONS = eINSTANCE.getAbstractDataTable_HaveDefaultColActions();

		/**
		 * The meta object literal for the '{@link com.bluexml.side.view.impl.ColImpl <em>Col</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see com.bluexml.side.view.impl.ColImpl
		 * @see com.bluexml.side.view.impl.ViewPackageImpl#getCol()
		 * @generated
		 */
		EClass COL = eINSTANCE.getCol();

		/**
		 * The meta object literal for the '{@link com.bluexml.side.view.impl.PagingImpl <em>Paging</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see com.bluexml.side.view.impl.PagingImpl
		 * @see com.bluexml.side.view.impl.ViewPackageImpl#getPaging()
		 * @generated
		 */
		EClass PAGING = eINSTANCE.getPaging();

		/**
		 * The meta object literal for the '<em><b>Pagination Style</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute PAGING__PAGINATION_STYLE = eINSTANCE.getPaging_PaginationStyle();

		/**
		 * The meta object literal for the '<em><b>Max Items</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute PAGING__MAX_ITEMS = eINSTANCE.getPaging_MaxItems();

		/**
		 * The meta object literal for the '<em><b>Max Page</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute PAGING__MAX_PAGE = eINSTANCE.getPaging_MaxPage();

		/**
		 * The meta object literal for the '{@link com.bluexml.side.view.impl.SortingImpl <em>Sorting</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see com.bluexml.side.view.impl.SortingImpl
		 * @see com.bluexml.side.view.impl.ViewPackageImpl#getSorting()
		 * @generated
		 */
		EClass SORTING = eINSTANCE.getSorting();

		/**
		 * The meta object literal for the '<em><b>Sort Order</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute SORTING__SORT_ORDER = eINSTANCE.getSorting_SortOrder();

		/**
		 * The meta object literal for the '<em><b>Sorted</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute SORTING__SORTED = eINSTANCE.getSorting_Sorted();

		/**
		 * The meta object literal for the '{@link com.bluexml.side.view.impl.FilteringImpl <em>Filtering</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see com.bluexml.side.view.impl.FilteringImpl
		 * @see com.bluexml.side.view.impl.ViewPackageImpl#getFiltering()
		 * @generated
		 */
		EClass FILTERING = eINSTANCE.getFiltering();

		/**
		 * The meta object literal for the '<em><b>Default Filter Value</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute FILTERING__DEFAULT_FILTER_VALUE = eINSTANCE.getFiltering_DefaultFilterValue();

		/**
		 * The meta object literal for the '{@link com.bluexml.side.view.impl.StylingImpl <em>Styling</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see com.bluexml.side.view.impl.StylingImpl
		 * @see com.bluexml.side.view.impl.ViewPackageImpl#getStyling()
		 * @generated
		 */
		EClass STYLING = eINSTANCE.getStyling();

		/**
		 * The meta object literal for the '<em><b>Style</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute STYLING__STYLE = eINSTANCE.getStyling_Style();

		/**
		 * The meta object literal for the '<em><b>Height</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute STYLING__HEIGHT = eINSTANCE.getStyling_Height();

		/**
		 * The meta object literal for the '<em><b>Width</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute STYLING__WIDTH = eINSTANCE.getStyling_Width();

		/**
		 * The meta object literal for the '{@link com.bluexml.side.view.impl.DataTableElementImpl <em>Data Table Element</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see com.bluexml.side.view.impl.DataTableElementImpl
		 * @see com.bluexml.side.view.impl.ViewPackageImpl#getDataTableElement()
		 * @generated
		 */
		EClass DATA_TABLE_ELEMENT = eINSTANCE.getDataTableElement();

		/**
		 * The meta object literal for the '{@link com.bluexml.side.view.impl.DataListImpl <em>Data List</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see com.bluexml.side.view.impl.DataListImpl
		 * @see com.bluexml.side.view.impl.ViewPackageImpl#getDataList()
		 * @generated
		 */
		EClass DATA_LIST = eINSTANCE.getDataList();

		/**
		 * The meta object literal for the '{@link com.bluexml.side.view.impl.DataTableImpl <em>Data Table</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see com.bluexml.side.view.impl.DataTableImpl
		 * @see com.bluexml.side.view.impl.ViewPackageImpl#getDataTable()
		 * @generated
		 */
		EClass DATA_TABLE = eINSTANCE.getDataTable();

		/**
		 * The meta object literal for the '<em><b>Default Col Set Up</b></em>' containment reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference DATA_TABLE__DEFAULT_COL_SET_UP = eINSTANCE.getDataTable_DefaultColSetUp();

		/**
		 * The meta object literal for the '{@link com.bluexml.side.view.impl.FacetMapImpl <em>Facet Map</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see com.bluexml.side.view.impl.FacetMapImpl
		 * @see com.bluexml.side.view.impl.ViewPackageImpl#getFacetMap()
		 * @generated
		 */
		EClass FACET_MAP = eINSTANCE.getFacetMap();

		/**
		 * The meta object literal for the '<em><b>Display Empty Facet</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute FACET_MAP__DISPLAY_EMPTY_FACET = eINSTANCE.getFacetMap_DisplayEmptyFacet();

		/**
		 * The meta object literal for the '<em><b>Facet Display Type</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute FACET_MAP__FACET_DISPLAY_TYPE = eINSTANCE.getFacetMap_FacetDisplayType();

		/**
		 * The meta object literal for the '{@link com.bluexml.side.view.impl.TreeImpl <em>Tree</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see com.bluexml.side.view.impl.TreeImpl
		 * @see com.bluexml.side.view.impl.ViewPackageImpl#getTree()
		 * @generated
		 */
		EClass TREE = eINSTANCE.getTree();

		/**
		 * The meta object literal for the '<em><b>Node Operations</b></em>' containment reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference TREE__NODE_OPERATIONS = eINSTANCE.getTree_NodeOperations();

		/**
		 * The meta object literal for the '<em><b>Node Value</b></em>' containment reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference TREE__NODE_VALUE = eINSTANCE.getTree_NodeValue();

		/**
		 * The meta object literal for the '<em><b>Default Depth</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute TREE__DEFAULT_DEPTH = eINSTANCE.getTree_DefaultDepth();

		/**
		 * The meta object literal for the '<em><b>Max Depth</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute TREE__MAX_DEPTH = eINSTANCE.getTree_MaxDepth();

		/**
		 * The meta object literal for the '{@link com.bluexml.side.view.impl.ComposedViewImpl <em>Composed View</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see com.bluexml.side.view.impl.ComposedViewImpl
		 * @see com.bluexml.side.view.impl.ViewPackageImpl#getComposedView()
		 * @generated
		 */
		EClass COMPOSED_VIEW = eINSTANCE.getComposedView();

		/**
		 * The meta object literal for the '{@link com.bluexml.side.view.impl.FieldContainerImpl <em>Field Container</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see com.bluexml.side.view.impl.FieldContainerImpl
		 * @see com.bluexml.side.view.impl.ViewPackageImpl#getFieldContainer()
		 * @generated
		 */
		EClass FIELD_CONTAINER = eINSTANCE.getFieldContainer();

		/**
		 * The meta object literal for the '<em><b>Children</b></em>' containment reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference FIELD_CONTAINER__CHILDREN = eINSTANCE.getFieldContainer_Children();

		/**
		 * The meta object literal for the '<em><b>Disabled</b></em>' containment reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference FIELD_CONTAINER__DISABLED = eINSTANCE.getFieldContainer_Disabled();

		/**
		 * The meta object literal for the '{@link com.bluexml.side.view.impl.FieldImpl <em>Field</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see com.bluexml.side.view.impl.FieldImpl
		 * @see com.bluexml.side.view.impl.ViewPackageImpl#getField()
		 * @generated
		 */
		EClass FIELD = eINSTANCE.getField();

		/**
		 * The meta object literal for the '<em><b>Patern</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute FIELD__PATERN = eINSTANCE.getField_Patern();

		/**
		 * The meta object literal for the '<em><b>Patern Language</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute FIELD__PATERN_LANGUAGE = eINSTANCE.getField_PaternLanguage();

		/**
		 * The meta object literal for the '<em><b>Path</b></em>' reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference FIELD__PATH = eINSTANCE.getField_Path();

		/**
		 * The meta object literal for the '{@link com.bluexml.side.view.impl.TextFieldImpl <em>Text Field</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see com.bluexml.side.view.impl.TextFieldImpl
		 * @see com.bluexml.side.view.impl.ViewPackageImpl#getTextField()
		 * @generated
		 */
		EClass TEXT_FIELD = eINSTANCE.getTextField();

		/**
		 * The meta object literal for the '<em><b>Widget Type</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute TEXT_FIELD__WIDGET_TYPE = eINSTANCE.getTextField_WidgetType();

		/**
		 * The meta object literal for the '{@link com.bluexml.side.view.impl.PasswordFieldImpl <em>Password Field</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see com.bluexml.side.view.impl.PasswordFieldImpl
		 * @see com.bluexml.side.view.impl.ViewPackageImpl#getPasswordField()
		 * @generated
		 */
		EClass PASSWORD_FIELD = eINSTANCE.getPasswordField();

		/**
		 * The meta object literal for the '{@link com.bluexml.side.view.impl.BooleanFieldImpl <em>Boolean Field</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see com.bluexml.side.view.impl.BooleanFieldImpl
		 * @see com.bluexml.side.view.impl.ViewPackageImpl#getBooleanField()
		 * @generated
		 */
		EClass BOOLEAN_FIELD = eINSTANCE.getBooleanField();

		/**
		 * The meta object literal for the '<em><b>Split</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute BOOLEAN_FIELD__SPLIT = eINSTANCE.getBooleanField_Split();

		/**
		 * The meta object literal for the '{@link com.bluexml.side.view.impl.FloatFieldImpl <em>Float Field</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see com.bluexml.side.view.impl.FloatFieldImpl
		 * @see com.bluexml.side.view.impl.ViewPackageImpl#getFloatField()
		 * @generated
		 */
		EClass FLOAT_FIELD = eINSTANCE.getFloatField();

		/**
		 * The meta object literal for the '{@link com.bluexml.side.view.impl.ActionFieldImpl <em>Action Field</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see com.bluexml.side.view.impl.ActionFieldImpl
		 * @see com.bluexml.side.view.impl.ViewPackageImpl#getActionField()
		 * @generated
		 */
		EClass ACTION_FIELD = eINSTANCE.getActionField();

		/**
		 * The meta object literal for the '<em><b>Operations</b></em>' containment reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference ACTION_FIELD__OPERATIONS = eINSTANCE.getActionField_Operations();

		/**
		 * The meta object literal for the '{@link com.bluexml.side.view.impl.DateFieldImpl <em>Date Field</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see com.bluexml.side.view.impl.DateFieldImpl
		 * @see com.bluexml.side.view.impl.ViewPackageImpl#getDateField()
		 * @generated
		 */
		EClass DATE_FIELD = eINSTANCE.getDateField();

		/**
		 * The meta object literal for the '{@link com.bluexml.side.view.impl.TimeFieldImpl <em>Time Field</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see com.bluexml.side.view.impl.TimeFieldImpl
		 * @see com.bluexml.side.view.impl.ViewPackageImpl#getTimeField()
		 * @generated
		 */
		EClass TIME_FIELD = eINSTANCE.getTimeField();

		/**
		 * The meta object literal for the '{@link com.bluexml.side.view.impl.DateTimeFieldImpl <em>Date Time Field</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see com.bluexml.side.view.impl.DateTimeFieldImpl
		 * @see com.bluexml.side.view.impl.ViewPackageImpl#getDateTimeField()
		 * @generated
		 */
		EClass DATE_TIME_FIELD = eINSTANCE.getDateTimeField();

		/**
		 * The meta object literal for the '{@link com.bluexml.side.view.impl.PhoneNumberFieldImpl <em>Phone Number Field</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see com.bluexml.side.view.impl.PhoneNumberFieldImpl
		 * @see com.bluexml.side.view.impl.ViewPackageImpl#getPhoneNumberField()
		 * @generated
		 */
		EClass PHONE_NUMBER_FIELD = eINSTANCE.getPhoneNumberField();

		/**
		 * The meta object literal for the '{@link com.bluexml.side.view.impl.EmailFieldImpl <em>Email Field</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see com.bluexml.side.view.impl.EmailFieldImpl
		 * @see com.bluexml.side.view.impl.ViewPackageImpl#getEmailField()
		 * @generated
		 */
		EClass EMAIL_FIELD = eINSTANCE.getEmailField();

		/**
		 * The meta object literal for the '{@link com.bluexml.side.view.impl.IntegerFieldImpl <em>Integer Field</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see com.bluexml.side.view.impl.IntegerFieldImpl
		 * @see com.bluexml.side.view.impl.ViewPackageImpl#getIntegerField()
		 * @generated
		 */
		EClass INTEGER_FIELD = eINSTANCE.getIntegerField();

		/**
		 * The meta object literal for the '{@link com.bluexml.side.view.impl.FileFieldImpl <em>File Field</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see com.bluexml.side.view.impl.FileFieldImpl
		 * @see com.bluexml.side.view.impl.ViewPackageImpl#getFileField()
		 * @generated
		 */
		EClass FILE_FIELD = eINSTANCE.getFileField();

		/**
		 * The meta object literal for the '{@link com.bluexml.side.view.impl.SelectFieldImpl <em>Select Field</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see com.bluexml.side.view.impl.SelectFieldImpl
		 * @see com.bluexml.side.view.impl.ViewPackageImpl#getSelectField()
		 * @generated
		 */
		EClass SELECT_FIELD = eINSTANCE.getSelectField();

		/**
		 * The meta object literal for the '<em><b>Select Widget</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute SELECT_FIELD__SELECT_WIDGET = eINSTANCE.getSelectField_SelectWidget();

		/**
		 * The meta object literal for the '{@link com.bluexml.side.view.impl.HtmlFieldImpl <em>Html Field</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see com.bluexml.side.view.impl.HtmlFieldImpl
		 * @see com.bluexml.side.view.impl.ViewPackageImpl#getHtmlField()
		 * @generated
		 */
		EClass HTML_FIELD = eINSTANCE.getHtmlField();

		/**
		 * The meta object literal for the '{@link com.bluexml.side.view.impl.URLFieldImpl <em>URL Field</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see com.bluexml.side.view.impl.URLFieldImpl
		 * @see com.bluexml.side.view.impl.ViewPackageImpl#getURLField()
		 * @generated
		 */
		EClass URL_FIELD = eINSTANCE.getURLField();

		/**
		 * The meta object literal for the '{@link com.bluexml.side.view.impl.ImageFieldImpl <em>Image Field</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see com.bluexml.side.view.impl.ImageFieldImpl
		 * @see com.bluexml.side.view.impl.ViewPackageImpl#getImageField()
		 * @generated
		 */
		EClass IMAGE_FIELD = eINSTANCE.getImageField();

		/**
		 * The meta object literal for the '{@link com.bluexml.side.view.impl.StylableImpl <em>Stylable</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see com.bluexml.side.view.impl.StylableImpl
		 * @see com.bluexml.side.view.impl.ViewPackageImpl#getStylable()
		 * @generated
		 */
		EClass STYLABLE = eINSTANCE.getStylable();

		/**
		 * The meta object literal for the '<em><b>Styling</b></em>' containment reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference STYLABLE__STYLING = eINSTANCE.getStylable_Styling();

		/**
		 * The meta object literal for the '{@link com.bluexml.side.view.impl.PaginableImpl <em>Paginable</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see com.bluexml.side.view.impl.PaginableImpl
		 * @see com.bluexml.side.view.impl.ViewPackageImpl#getPaginable()
		 * @generated
		 */
		EClass PAGINABLE = eINSTANCE.getPaginable();

		/**
		 * The meta object literal for the '<em><b>Paging</b></em>' containment reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference PAGINABLE__PAGING = eINSTANCE.getPaginable_Paging();

		/**
		 * The meta object literal for the '{@link com.bluexml.side.view.impl.SortableImpl <em>Sortable</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see com.bluexml.side.view.impl.SortableImpl
		 * @see com.bluexml.side.view.impl.ViewPackageImpl#getSortable()
		 * @generated
		 */
		EClass SORTABLE = eINSTANCE.getSortable();

		/**
		 * The meta object literal for the '<em><b>Sorting</b></em>' containment reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference SORTABLE__SORTING = eINSTANCE.getSortable_Sorting();

		/**
		 * The meta object literal for the '{@link com.bluexml.side.view.impl.EditableImpl <em>Editable</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see com.bluexml.side.view.impl.EditableImpl
		 * @see com.bluexml.side.view.impl.ViewPackageImpl#getEditable()
		 * @generated
		 */
		EClass EDITABLE = eINSTANCE.getEditable();

		/**
		 * The meta object literal for the '<em><b>Editable</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute EDITABLE__EDITABLE = eINSTANCE.getEditable_Editable();

		/**
		 * The meta object literal for the '{@link com.bluexml.side.view.impl.MovableImpl <em>Movable</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see com.bluexml.side.view.impl.MovableImpl
		 * @see com.bluexml.side.view.impl.ViewPackageImpl#getMovable()
		 * @generated
		 */
		EClass MOVABLE = eINSTANCE.getMovable();

		/**
		 * The meta object literal for the '<em><b>Movable</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute MOVABLE__MOVABLE = eINSTANCE.getMovable_Movable();

		/**
		 * The meta object literal for the '{@link com.bluexml.side.view.impl.FilterableImpl <em>Filterable</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see com.bluexml.side.view.impl.FilterableImpl
		 * @see com.bluexml.side.view.impl.ViewPackageImpl#getFilterable()
		 * @generated
		 */
		EClass FILTERABLE = eINSTANCE.getFilterable();

		/**
		 * The meta object literal for the '<em><b>Filtering</b></em>' containment reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference FILTERABLE__FILTERING = eINSTANCE.getFilterable_Filtering();

		/**
		 * The meta object literal for the '{@link com.bluexml.side.view.impl.FieldGroupImpl <em>Field Group</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see com.bluexml.side.view.impl.FieldGroupImpl
		 * @see com.bluexml.side.view.impl.ViewPackageImpl#getFieldGroup()
		 * @generated
		 */
		EClass FIELD_GROUP = eINSTANCE.getFieldGroup();

		/**
		 * The meta object literal for the '{@link com.bluexml.side.view.impl.AbstractViewOfImpl <em>Abstract View Of</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see com.bluexml.side.view.impl.AbstractViewOfImpl
		 * @see com.bluexml.side.view.impl.ViewPackageImpl#getAbstractViewOf()
		 * @generated
		 */
		EClass ABSTRACT_VIEW_OF = eINSTANCE.getAbstractViewOf();

		/**
		 * The meta object literal for the '<em><b>View Of</b></em>' reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference ABSTRACT_VIEW_OF__VIEW_OF = eINSTANCE.getAbstractViewOf_ViewOf();

		/**
		 * The meta object literal for the '<em><b>Generate Webscript</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute ABSTRACT_VIEW_OF__GENERATE_WEBSCRIPT = eINSTANCE.getAbstractViewOf_GenerateWebscript();

		/**
		 * The meta object literal for the '{@link com.bluexml.side.view.impl.ActionableImpl <em>Actionable</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see com.bluexml.side.view.impl.ActionableImpl
		 * @see com.bluexml.side.view.impl.ViewPackageImpl#getActionable()
		 * @generated
		 */
		EClass ACTIONABLE = eINSTANCE.getActionable();

		/**
		 * The meta object literal for the '<em><b>Operations</b></em>' containment reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference ACTIONABLE__OPERATIONS = eINSTANCE.getActionable_Operations();

		/**
		 * The meta object literal for the '{@link com.bluexml.side.view.SortOrder <em>Sort Order</em>}' enum.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see com.bluexml.side.view.SortOrder
		 * @see com.bluexml.side.view.impl.ViewPackageImpl#getSortOrder()
		 * @generated
		 */
		EEnum SORT_ORDER = eINSTANCE.getSortOrder();

		/**
		 * The meta object literal for the '{@link com.bluexml.side.view.PaginationStyle <em>Pagination Style</em>}' enum.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see com.bluexml.side.view.PaginationStyle
		 * @see com.bluexml.side.view.impl.ViewPackageImpl#getPaginationStyle()
		 * @generated
		 */
		EEnum PAGINATION_STYLE = eINSTANCE.getPaginationStyle();

		/**
		 * The meta object literal for the '{@link com.bluexml.side.view.Halign <em>Halign</em>}' enum.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see com.bluexml.side.view.Halign
		 * @see com.bluexml.side.view.impl.ViewPackageImpl#getHalign()
		 * @generated
		 */
		EEnum HALIGN = eINSTANCE.getHalign();

		/**
		 * The meta object literal for the '{@link com.bluexml.side.view.LoadingType <em>Loading Type</em>}' enum.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see com.bluexml.side.view.LoadingType
		 * @see com.bluexml.side.view.impl.ViewPackageImpl#getLoadingType()
		 * @generated
		 */
		EEnum LOADING_TYPE = eINSTANCE.getLoadingType();

		/**
		 * The meta object literal for the '{@link com.bluexml.side.view.WidgetTextType <em>Widget Text Type</em>}' enum.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see com.bluexml.side.view.WidgetTextType
		 * @see com.bluexml.side.view.impl.ViewPackageImpl#getWidgetTextType()
		 * @generated
		 */
		EEnum WIDGET_TEXT_TYPE = eINSTANCE.getWidgetTextType();

		/**
		 * The meta object literal for the '{@link com.bluexml.side.view.SelectWidgetType <em>Select Widget Type</em>}' enum.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see com.bluexml.side.view.SelectWidgetType
		 * @see com.bluexml.side.view.impl.ViewPackageImpl#getSelectWidgetType()
		 * @generated
		 */
		EEnum SELECT_WIDGET_TYPE = eINSTANCE.getSelectWidgetType();

		/**
		 * The meta object literal for the '{@link com.bluexml.side.view.FacetDisplayType <em>Facet Display Type</em>}' enum.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see com.bluexml.side.view.FacetDisplayType
		 * @see com.bluexml.side.view.impl.ViewPackageImpl#getFacetDisplayType()
		 * @generated
		 */
		EEnum FACET_DISPLAY_TYPE = eINSTANCE.getFacetDisplayType();

	}

} //ViewPackage
