/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package com.bluexml.side.portal;

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
 * @see com.bluexml.side.portal.PortalFactory
 * @model kind="package"
 * @generated
 */
public interface PortalPackage extends EPackage {
	/**
	 * The package name.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	String eNAME = "portal";

	/**
	 * The package namespace URI.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	String eNS_URI = "http://www.kerblue.org/portal/1.0";

	/**
	 * The package namespace name.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	String eNS_PREFIX = "portal";

	/**
	 * The singleton instance of the package.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	PortalPackage eINSTANCE = com.bluexml.side.portal.impl.PortalPackageImpl.init();

	/**
	 * The meta object id for the '{@link com.bluexml.side.portal.impl.PortalModelElementImpl <em>Model Element</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see com.bluexml.side.portal.impl.PortalModelElementImpl
	 * @see com.bluexml.side.portal.impl.PortalPackageImpl#getPortalModelElement()
	 * @generated
	 */
	int PORTAL_MODEL_ELEMENT = 0;

	/**
	 * The feature id for the '<em><b>Stereotypes</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PORTAL_MODEL_ELEMENT__STEREOTYPES = CommonPackage.MODEL_ELEMENT__STEREOTYPES;

	/**
	 * The feature id for the '<em><b>Tags</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PORTAL_MODEL_ELEMENT__TAGS = CommonPackage.MODEL_ELEMENT__TAGS;

	/**
	 * The feature id for the '<em><b>Comments</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PORTAL_MODEL_ELEMENT__COMMENTS = CommonPackage.MODEL_ELEMENT__COMMENTS;

	/**
	 * The feature id for the '<em><b>Documentation</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PORTAL_MODEL_ELEMENT__DOCUMENTATION = CommonPackage.MODEL_ELEMENT__DOCUMENTATION;

	/**
	 * The feature id for the '<em><b>Description</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PORTAL_MODEL_ELEMENT__DESCRIPTION = CommonPackage.MODEL_ELEMENT__DESCRIPTION;

	/**
	 * The feature id for the '<em><b>Metainfo</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PORTAL_MODEL_ELEMENT__METAINFO = CommonPackage.MODEL_ELEMENT__METAINFO;

	/**
	 * The number of structural features of the '<em>Model Element</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PORTAL_MODEL_ELEMENT_FEATURE_COUNT = CommonPackage.MODEL_ELEMENT_FEATURE_COUNT + 0;

	/**
	 * The meta object id for the '{@link com.bluexml.side.portal.impl.PortalImpl <em>Portal</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see com.bluexml.side.portal.impl.PortalImpl
	 * @see com.bluexml.side.portal.impl.PortalPackageImpl#getPortal()
	 * @generated
	 */
	int PORTAL = 1;

	/**
	 * The feature id for the '<em><b>Stereotypes</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PORTAL__STEREOTYPES = CommonPackage.PACKAGE__STEREOTYPES;

	/**
	 * The feature id for the '<em><b>Tags</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PORTAL__TAGS = CommonPackage.PACKAGE__TAGS;

	/**
	 * The feature id for the '<em><b>Comments</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PORTAL__COMMENTS = CommonPackage.PACKAGE__COMMENTS;

	/**
	 * The feature id for the '<em><b>Documentation</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PORTAL__DOCUMENTATION = CommonPackage.PACKAGE__DOCUMENTATION;

	/**
	 * The feature id for the '<em><b>Description</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PORTAL__DESCRIPTION = CommonPackage.PACKAGE__DESCRIPTION;

	/**
	 * The feature id for the '<em><b>Metainfo</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PORTAL__METAINFO = CommonPackage.PACKAGE__METAINFO;

	/**
	 * The feature id for the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PORTAL__NAME = CommonPackage.PACKAGE__NAME;

	/**
	 * The feature id for the '<em><b>Stereotype Set</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PORTAL__STEREOTYPE_SET = CommonPackage.PACKAGE__STEREOTYPE_SET;

	/**
	 * The feature id for the '<em><b>Package Set</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PORTAL__PACKAGE_SET = CommonPackage.PACKAGE__PACKAGE_SET;

	/**
	 * The feature id for the '<em><b>Page Set</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PORTAL__PAGE_SET = CommonPackage.PACKAGE_FEATURE_COUNT + 0;

	/**
	 * The feature id for the '<em><b>Layout Set</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PORTAL__LAYOUT_SET = CommonPackage.PACKAGE_FEATURE_COUNT + 1;

	/**
	 * The feature id for the '<em><b>Portlet Set</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PORTAL__PORTLET_SET = CommonPackage.PACKAGE_FEATURE_COUNT + 2;

	/**
	 * The feature id for the '<em><b>Portlet Set Type</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PORTAL__PORTLET_SET_TYPE = CommonPackage.PACKAGE_FEATURE_COUNT + 3;

	/**
	 * The feature id for the '<em><b>Internal Portlet Set</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PORTAL__INTERNAL_PORTLET_SET = CommonPackage.PACKAGE_FEATURE_COUNT + 4;

	/**
	 * The number of structural features of the '<em>Portal</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PORTAL_FEATURE_COUNT = CommonPackage.PACKAGE_FEATURE_COUNT + 5;

	/**
	 * The meta object id for the '{@link com.bluexml.side.portal.impl.PageImpl <em>Page</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see com.bluexml.side.portal.impl.PageImpl
	 * @see com.bluexml.side.portal.impl.PortalPackageImpl#getPage()
	 * @generated
	 */
	int PAGE = 2;

	/**
	 * The feature id for the '<em><b>Stereotypes</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PAGE__STEREOTYPES = PORTAL_MODEL_ELEMENT__STEREOTYPES;

	/**
	 * The feature id for the '<em><b>Tags</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PAGE__TAGS = PORTAL_MODEL_ELEMENT__TAGS;

	/**
	 * The feature id for the '<em><b>Comments</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PAGE__COMMENTS = PORTAL_MODEL_ELEMENT__COMMENTS;

	/**
	 * The feature id for the '<em><b>Documentation</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PAGE__DOCUMENTATION = PORTAL_MODEL_ELEMENT__DOCUMENTATION;

	/**
	 * The feature id for the '<em><b>Description</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PAGE__DESCRIPTION = PORTAL_MODEL_ELEMENT__DESCRIPTION;

	/**
	 * The feature id for the '<em><b>Metainfo</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PAGE__METAINFO = PORTAL_MODEL_ELEMENT__METAINFO;

	/**
	 * The feature id for the '<em><b>ID</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PAGE__ID = PORTAL_MODEL_ELEMENT_FEATURE_COUNT + 0;

	/**
	 * The feature id for the '<em><b>Title</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PAGE__TITLE = PORTAL_MODEL_ELEMENT_FEATURE_COUNT + 1;

	/**
	 * The feature id for the '<em><b>Use Layout</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PAGE__USE_LAYOUT = PORTAL_MODEL_ELEMENT_FEATURE_COUNT + 2;

	/**
	 * The feature id for the '<em><b>Portlets</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PAGE__PORTLETS = PORTAL_MODEL_ELEMENT_FEATURE_COUNT + 3;

	/**
	 * The feature id for the '<em><b>Position</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PAGE__POSITION = PORTAL_MODEL_ELEMENT_FEATURE_COUNT + 4;

	/**
	 * The feature id for the '<em><b>Is Child Page Of</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PAGE__IS_CHILD_PAGE_OF = PORTAL_MODEL_ELEMENT_FEATURE_COUNT + 5;

	/**
	 * The feature id for the '<em><b>Visibility</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PAGE__VISIBILITY = PORTAL_MODEL_ELEMENT_FEATURE_COUNT + 6;

	/**
	 * The feature id for the '<em><b>Generate</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PAGE__GENERATE = PORTAL_MODEL_ELEMENT_FEATURE_COUNT + 7;

	/**
	 * The number of structural features of the '<em>Page</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PAGE_FEATURE_COUNT = PORTAL_MODEL_ELEMENT_FEATURE_COUNT + 8;

	/**
	 * The meta object id for the '{@link com.bluexml.side.portal.impl.PortalLayoutImpl <em>Layout</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see com.bluexml.side.portal.impl.PortalLayoutImpl
	 * @see com.bluexml.side.portal.impl.PortalPackageImpl#getPortalLayout()
	 * @generated
	 */
	int PORTAL_LAYOUT = 3;

	/**
	 * The feature id for the '<em><b>Stereotypes</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PORTAL_LAYOUT__STEREOTYPES = PORTAL_MODEL_ELEMENT__STEREOTYPES;

	/**
	 * The feature id for the '<em><b>Tags</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PORTAL_LAYOUT__TAGS = PORTAL_MODEL_ELEMENT__TAGS;

	/**
	 * The feature id for the '<em><b>Comments</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PORTAL_LAYOUT__COMMENTS = PORTAL_MODEL_ELEMENT__COMMENTS;

	/**
	 * The feature id for the '<em><b>Documentation</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PORTAL_LAYOUT__DOCUMENTATION = PORTAL_MODEL_ELEMENT__DOCUMENTATION;

	/**
	 * The feature id for the '<em><b>Description</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PORTAL_LAYOUT__DESCRIPTION = PORTAL_MODEL_ELEMENT__DESCRIPTION;

	/**
	 * The feature id for the '<em><b>Metainfo</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PORTAL_LAYOUT__METAINFO = PORTAL_MODEL_ELEMENT__METAINFO;

	/**
	 * The feature id for the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PORTAL_LAYOUT__NAME = PORTAL_MODEL_ELEMENT_FEATURE_COUNT + 0;

	/**
	 * The feature id for the '<em><b>Columns</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PORTAL_LAYOUT__COLUMNS = PORTAL_MODEL_ELEMENT_FEATURE_COUNT + 1;

	/**
	 * The feature id for the '<em><b>Column Mode</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PORTAL_LAYOUT__COLUMN_MODE = PORTAL_MODEL_ELEMENT_FEATURE_COUNT + 2;

	/**
	 * The number of structural features of the '<em>Layout</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PORTAL_LAYOUT_FEATURE_COUNT = PORTAL_MODEL_ELEMENT_FEATURE_COUNT + 3;

	/**
	 * The meta object id for the '{@link com.bluexml.side.portal.impl.ColumnImpl <em>Column</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see com.bluexml.side.portal.impl.ColumnImpl
	 * @see com.bluexml.side.portal.impl.PortalPackageImpl#getColumn()
	 * @generated
	 */
	int COLUMN = 4;

	/**
	 * The feature id for the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int COLUMN__NAME = 0;

	/**
	 * The feature id for the '<em><b>Width</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int COLUMN__WIDTH = 1;

	/**
	 * The feature id for the '<em><b>Unit</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int COLUMN__UNIT = 2;

	/**
	 * The number of structural features of the '<em>Column</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int COLUMN_FEATURE_COUNT = 3;

	/**
	 * The meta object id for the '{@link com.bluexml.side.portal.impl.PortletImpl <em>Portlet</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see com.bluexml.side.portal.impl.PortletImpl
	 * @see com.bluexml.side.portal.impl.PortalPackageImpl#getPortlet()
	 * @generated
	 */
	int PORTLET = 5;

	/**
	 * The feature id for the '<em><b>Stereotypes</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PORTLET__STEREOTYPES = PORTAL_MODEL_ELEMENT__STEREOTYPES;

	/**
	 * The feature id for the '<em><b>Tags</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PORTLET__TAGS = PORTAL_MODEL_ELEMENT__TAGS;

	/**
	 * The feature id for the '<em><b>Comments</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PORTLET__COMMENTS = PORTAL_MODEL_ELEMENT__COMMENTS;

	/**
	 * The feature id for the '<em><b>Documentation</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PORTLET__DOCUMENTATION = PORTAL_MODEL_ELEMENT__DOCUMENTATION;

	/**
	 * The feature id for the '<em><b>Description</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PORTLET__DESCRIPTION = PORTAL_MODEL_ELEMENT__DESCRIPTION;

	/**
	 * The feature id for the '<em><b>Metainfo</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PORTLET__METAINFO = PORTAL_MODEL_ELEMENT__METAINFO;

	/**
	 * The feature id for the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PORTLET__NAME = PORTAL_MODEL_ELEMENT_FEATURE_COUNT + 0;

	/**
	 * The feature id for the '<em><b>Is Portlet Internal</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PORTLET__IS_PORTLET_INTERNAL = PORTAL_MODEL_ELEMENT_FEATURE_COUNT + 1;

	/**
	 * The feature id for the '<em><b>Is Instance Of Portlet Type</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PORTLET__IS_INSTANCE_OF_PORTLET_TYPE = PORTAL_MODEL_ELEMENT_FEATURE_COUNT + 2;

	/**
	 * The number of structural features of the '<em>Portlet</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PORTLET_FEATURE_COUNT = PORTAL_MODEL_ELEMENT_FEATURE_COUNT + 3;

	/**
	 * The meta object id for the '{@link com.bluexml.side.portal.impl.PortletTypeImpl <em>Portlet Type</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see com.bluexml.side.portal.impl.PortletTypeImpl
	 * @see com.bluexml.side.portal.impl.PortalPackageImpl#getPortletType()
	 * @generated
	 */
	int PORTLET_TYPE = 6;

	/**
	 * The feature id for the '<em><b>Stereotypes</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PORTLET_TYPE__STEREOTYPES = PORTAL_MODEL_ELEMENT__STEREOTYPES;

	/**
	 * The feature id for the '<em><b>Tags</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PORTLET_TYPE__TAGS = PORTAL_MODEL_ELEMENT__TAGS;

	/**
	 * The feature id for the '<em><b>Comments</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PORTLET_TYPE__COMMENTS = PORTAL_MODEL_ELEMENT__COMMENTS;

	/**
	 * The feature id for the '<em><b>Documentation</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PORTLET_TYPE__DOCUMENTATION = PORTAL_MODEL_ELEMENT__DOCUMENTATION;

	/**
	 * The feature id for the '<em><b>Description</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PORTLET_TYPE__DESCRIPTION = PORTAL_MODEL_ELEMENT__DESCRIPTION;

	/**
	 * The feature id for the '<em><b>Metainfo</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PORTLET_TYPE__METAINFO = PORTAL_MODEL_ELEMENT__METAINFO;

	/**
	 * The feature id for the '<em><b>Id</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PORTLET_TYPE__ID = PORTAL_MODEL_ELEMENT_FEATURE_COUNT + 0;

	/**
	 * The feature id for the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PORTLET_TYPE__NAME = PORTAL_MODEL_ELEMENT_FEATURE_COUNT + 1;

	/**
	 * The feature id for the '<em><b>Have Attribute</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PORTLET_TYPE__HAVE_ATTRIBUTE = PORTAL_MODEL_ELEMENT_FEATURE_COUNT + 2;

	/**
	 * The feature id for the '<em><b>Instanceable</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PORTLET_TYPE__INSTANCEABLE = PORTAL_MODEL_ELEMENT_FEATURE_COUNT + 3;

	/**
	 * The number of structural features of the '<em>Portlet Type</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PORTLET_TYPE_FEATURE_COUNT = PORTAL_MODEL_ELEMENT_FEATURE_COUNT + 4;

	/**
	 * The meta object id for the '{@link com.bluexml.side.portal.impl.PortletInternalImpl <em>Portlet Internal</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see com.bluexml.side.portal.impl.PortletInternalImpl
	 * @see com.bluexml.side.portal.impl.PortalPackageImpl#getPortletInternal()
	 * @generated
	 */
	int PORTLET_INTERNAL = 7;

	/**
	 * The feature id for the '<em><b>Stereotypes</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PORTLET_INTERNAL__STEREOTYPES = PORTAL_MODEL_ELEMENT__STEREOTYPES;

	/**
	 * The feature id for the '<em><b>Tags</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PORTLET_INTERNAL__TAGS = PORTAL_MODEL_ELEMENT__TAGS;

	/**
	 * The feature id for the '<em><b>Comments</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PORTLET_INTERNAL__COMMENTS = PORTAL_MODEL_ELEMENT__COMMENTS;

	/**
	 * The feature id for the '<em><b>Documentation</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PORTLET_INTERNAL__DOCUMENTATION = PORTAL_MODEL_ELEMENT__DOCUMENTATION;

	/**
	 * The feature id for the '<em><b>Description</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PORTLET_INTERNAL__DESCRIPTION = PORTAL_MODEL_ELEMENT__DESCRIPTION;

	/**
	 * The feature id for the '<em><b>Metainfo</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PORTLET_INTERNAL__METAINFO = PORTAL_MODEL_ELEMENT__METAINFO;

	/**
	 * The feature id for the '<em><b>Type</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PORTLET_INTERNAL__TYPE = PORTAL_MODEL_ELEMENT_FEATURE_COUNT + 0;

	/**
	 * The feature id for the '<em><b>View</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PORTLET_INTERNAL__VIEW = PORTAL_MODEL_ELEMENT_FEATURE_COUNT + 1;

	/**
	 * The feature id for the '<em><b>Form</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PORTLET_INTERNAL__FORM = PORTAL_MODEL_ELEMENT_FEATURE_COUNT + 2;

	/**
	 * The number of structural features of the '<em>Portlet Internal</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PORTLET_INTERNAL_FEATURE_COUNT = PORTAL_MODEL_ELEMENT_FEATURE_COUNT + 3;

	/**
	 * The meta object id for the '{@link com.bluexml.side.portal.impl.AbstractPortletAttributesImpl <em>Abstract Portlet Attributes</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see com.bluexml.side.portal.impl.AbstractPortletAttributesImpl
	 * @see com.bluexml.side.portal.impl.PortalPackageImpl#getAbstractPortletAttributes()
	 * @generated
	 */
	int ABSTRACT_PORTLET_ATTRIBUTES = 8;

	/**
	 * The number of structural features of the '<em>Abstract Portlet Attributes</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ABSTRACT_PORTLET_ATTRIBUTES_FEATURE_COUNT = 0;

	/**
	 * The meta object id for the '{@link com.bluexml.side.portal.impl.GroupImpl <em>Group</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see com.bluexml.side.portal.impl.GroupImpl
	 * @see com.bluexml.side.portal.impl.PortalPackageImpl#getGroup()
	 * @generated
	 */
	int GROUP = 9;

	/**
	 * The feature id for the '<em><b>Use Page</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int GROUP__USE_PAGE = 0;

	/**
	 * The feature id for the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int GROUP__NAME = 1;

	/**
	 * The number of structural features of the '<em>Group</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int GROUP_FEATURE_COUNT = 2;

	/**
	 * The meta object id for the '{@link com.bluexml.side.portal.impl.PortletAttributeImpl <em>Portlet Attribute</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see com.bluexml.side.portal.impl.PortletAttributeImpl
	 * @see com.bluexml.side.portal.impl.PortalPackageImpl#getPortletAttribute()
	 * @generated
	 */
	int PORTLET_ATTRIBUTE = 10;

	/**
	 * The feature id for the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PORTLET_ATTRIBUTE__NAME = ABSTRACT_PORTLET_ATTRIBUTES_FEATURE_COUNT + 0;

	/**
	 * The feature id for the '<em><b>Type</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PORTLET_ATTRIBUTE__TYPE = ABSTRACT_PORTLET_ATTRIBUTES_FEATURE_COUNT + 1;

	/**
	 * The feature id for the '<em><b>Required</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PORTLET_ATTRIBUTE__REQUIRED = ABSTRACT_PORTLET_ATTRIBUTES_FEATURE_COUNT + 2;

	/**
	 * The feature id for the '<em><b>Multi Valued</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PORTLET_ATTRIBUTE__MULTI_VALUED = ABSTRACT_PORTLET_ATTRIBUTES_FEATURE_COUNT + 3;

	/**
	 * The number of structural features of the '<em>Portlet Attribute</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PORTLET_ATTRIBUTE_FEATURE_COUNT = ABSTRACT_PORTLET_ATTRIBUTES_FEATURE_COUNT + 4;

	/**
	 * The meta object id for the '{@link com.bluexml.side.portal.impl.HavePortletImpl <em>Have Portlet</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see com.bluexml.side.portal.impl.HavePortletImpl
	 * @see com.bluexml.side.portal.impl.PortalPackageImpl#getHavePortlet()
	 * @generated
	 */
	int HAVE_PORTLET = 11;

	/**
	 * The feature id for the '<em><b>Association Portlet</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int HAVE_PORTLET__ASSOCIATION_PORTLET = 0;

	/**
	 * The feature id for the '<em><b>Association Page</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int HAVE_PORTLET__ASSOCIATION_PAGE = 1;

	/**
	 * The feature id for the '<em><b>Position Group</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int HAVE_PORTLET__POSITION_GROUP = 2;

	/**
	 * The number of structural features of the '<em>Have Portlet</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int HAVE_PORTLET_FEATURE_COUNT = 3;

	/**
	 * The meta object id for the '{@link com.bluexml.side.portal.impl.PositionGroupImpl <em>Position Group</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see com.bluexml.side.portal.impl.PositionGroupImpl
	 * @see com.bluexml.side.portal.impl.PortalPackageImpl#getPositionGroup()
	 * @generated
	 */
	int POSITION_GROUP = 12;

	/**
	 * The feature id for the '<em><b>Position</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int POSITION_GROUP__POSITION = 0;

	/**
	 * The feature id for the '<em><b>On Column</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int POSITION_GROUP__ON_COLUMN = 1;

	/**
	 * The feature id for the '<em><b>On Layout</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int POSITION_GROUP__ON_LAYOUT = 2;

	/**
	 * The number of structural features of the '<em>Position Group</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int POSITION_GROUP_FEATURE_COUNT = 3;

	/**
	 * The meta object id for the '{@link com.bluexml.side.portal.impl.InstanciatePortletTypeImpl <em>Instanciate Portlet Type</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see com.bluexml.side.portal.impl.InstanciatePortletTypeImpl
	 * @see com.bluexml.side.portal.impl.PortalPackageImpl#getInstanciatePortletType()
	 * @generated
	 */
	int INSTANCIATE_PORTLET_TYPE = 13;

	/**
	 * The feature id for the '<em><b>Portlet Type</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int INSTANCIATE_PORTLET_TYPE__PORTLET_TYPE = 0;

	/**
	 * The feature id for the '<em><b>Instances</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int INSTANCIATE_PORTLET_TYPE__INSTANCES = 1;

	/**
	 * The number of structural features of the '<em>Instanciate Portlet Type</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int INSTANCIATE_PORTLET_TYPE_FEATURE_COUNT = 2;

	/**
	 * The meta object id for the '{@link com.bluexml.side.portal.impl.PortletAttributeInstanceImpl <em>Portlet Attribute Instance</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see com.bluexml.side.portal.impl.PortletAttributeInstanceImpl
	 * @see com.bluexml.side.portal.impl.PortalPackageImpl#getPortletAttributeInstance()
	 * @generated
	 */
	int PORTLET_ATTRIBUTE_INSTANCE = 14;

	/**
	 * The feature id for the '<em><b>Value</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PORTLET_ATTRIBUTE_INSTANCE__VALUE = 0;

	/**
	 * The feature id for the '<em><b>Instance Of</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PORTLET_ATTRIBUTE_INSTANCE__INSTANCE_OF = 1;

	/**
	 * The number of structural features of the '<em>Portlet Attribute Instance</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PORTLET_ATTRIBUTE_INSTANCE_FEATURE_COUNT = 2;

	/**
	 * The meta object id for the '{@link com.bluexml.side.portal.impl.isChildPageImpl <em>is Child Page</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see com.bluexml.side.portal.impl.isChildPageImpl
	 * @see com.bluexml.side.portal.impl.PortalPackageImpl#getisChildPage()
	 * @generated
	 */
	int IS_CHILD_PAGE = 15;

	/**
	 * The feature id for the '<em><b>Inherit</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int IS_CHILD_PAGE__INHERIT = 0;

	/**
	 * The feature id for the '<em><b>Is Child Page Of</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int IS_CHILD_PAGE__IS_CHILD_PAGE_OF = 1;

	/**
	 * The number of structural features of the '<em>is Child Page</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int IS_CHILD_PAGE_FEATURE_COUNT = 2;

	/**
	 * The meta object id for the '{@link com.bluexml.side.portal.widthUnit <em>width Unit</em>}' enum.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see com.bluexml.side.portal.widthUnit
	 * @see com.bluexml.side.portal.impl.PortalPackageImpl#getwidthUnit()
	 * @generated
	 */
	int WIDTH_UNIT = 16;

	/**
	 * The meta object id for the '{@link com.bluexml.side.portal.InternalPortletType <em>Internal Portlet Type</em>}' enum.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see com.bluexml.side.portal.InternalPortletType
	 * @see com.bluexml.side.portal.impl.PortalPackageImpl#getInternalPortletType()
	 * @generated
	 */
	int INTERNAL_PORTLET_TYPE = 17;

	/**
	 * The meta object id for the '{@link com.bluexml.side.portal.PortletTypeAttributeType <em>Portlet Type Attribute Type</em>}' enum.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see com.bluexml.side.portal.PortletTypeAttributeType
	 * @see com.bluexml.side.portal.impl.PortalPackageImpl#getPortletTypeAttributeType()
	 * @generated
	 */
	int PORTLET_TYPE_ATTRIBUTE_TYPE = 18;


	/**
	 * Returns the meta object for class '{@link com.bluexml.side.portal.PortalModelElement <em>Model Element</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Model Element</em>'.
	 * @see com.bluexml.side.portal.PortalModelElement
	 * @generated
	 */
	EClass getPortalModelElement();

	/**
	 * Returns the meta object for class '{@link com.bluexml.side.portal.Portal <em>Portal</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Portal</em>'.
	 * @see com.bluexml.side.portal.Portal
	 * @generated
	 */
	EClass getPortal();

	/**
	 * Returns the meta object for the containment reference list '{@link com.bluexml.side.portal.Portal#getPageSet <em>Page Set</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference list '<em>Page Set</em>'.
	 * @see com.bluexml.side.portal.Portal#getPageSet()
	 * @see #getPortal()
	 * @generated
	 */
	EReference getPortal_PageSet();

	/**
	 * Returns the meta object for the containment reference list '{@link com.bluexml.side.portal.Portal#getLayoutSet <em>Layout Set</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference list '<em>Layout Set</em>'.
	 * @see com.bluexml.side.portal.Portal#getLayoutSet()
	 * @see #getPortal()
	 * @generated
	 */
	EReference getPortal_LayoutSet();

	/**
	 * Returns the meta object for the containment reference list '{@link com.bluexml.side.portal.Portal#getPortletSet <em>Portlet Set</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference list '<em>Portlet Set</em>'.
	 * @see com.bluexml.side.portal.Portal#getPortletSet()
	 * @see #getPortal()
	 * @generated
	 */
	EReference getPortal_PortletSet();

	/**
	 * Returns the meta object for the containment reference list '{@link com.bluexml.side.portal.Portal#getPortletSetType <em>Portlet Set Type</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference list '<em>Portlet Set Type</em>'.
	 * @see com.bluexml.side.portal.Portal#getPortletSetType()
	 * @see #getPortal()
	 * @generated
	 */
	EReference getPortal_PortletSetType();

	/**
	 * Returns the meta object for the containment reference list '{@link com.bluexml.side.portal.Portal#getInternalPortletSet <em>Internal Portlet Set</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference list '<em>Internal Portlet Set</em>'.
	 * @see com.bluexml.side.portal.Portal#getInternalPortletSet()
	 * @see #getPortal()
	 * @generated
	 */
	EReference getPortal_InternalPortletSet();

	/**
	 * Returns the meta object for class '{@link com.bluexml.side.portal.Page <em>Page</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Page</em>'.
	 * @see com.bluexml.side.portal.Page
	 * @generated
	 */
	EClass getPage();

	/**
	 * Returns the meta object for the attribute '{@link com.bluexml.side.portal.Page#getID <em>ID</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>ID</em>'.
	 * @see com.bluexml.side.portal.Page#getID()
	 * @see #getPage()
	 * @generated
	 */
	EAttribute getPage_ID();

	/**
	 * Returns the meta object for the attribute '{@link com.bluexml.side.portal.Page#getTitle <em>Title</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Title</em>'.
	 * @see com.bluexml.side.portal.Page#getTitle()
	 * @see #getPage()
	 * @generated
	 */
	EAttribute getPage_Title();

	/**
	 * Returns the meta object for the reference '{@link com.bluexml.side.portal.Page#getUseLayout <em>Use Layout</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference '<em>Use Layout</em>'.
	 * @see com.bluexml.side.portal.Page#getUseLayout()
	 * @see #getPage()
	 * @generated
	 */
	EReference getPage_UseLayout();

	/**
	 * Returns the meta object for the containment reference list '{@link com.bluexml.side.portal.Page#getPortlets <em>Portlets</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference list '<em>Portlets</em>'.
	 * @see com.bluexml.side.portal.Page#getPortlets()
	 * @see #getPage()
	 * @generated
	 */
	EReference getPage_Portlets();

	/**
	 * Returns the meta object for the attribute '{@link com.bluexml.side.portal.Page#getPosition <em>Position</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Position</em>'.
	 * @see com.bluexml.side.portal.Page#getPosition()
	 * @see #getPage()
	 * @generated
	 */
	EAttribute getPage_Position();

	/**
	 * Returns the meta object for the containment reference '{@link com.bluexml.side.portal.Page#getIsChildPageOf <em>Is Child Page Of</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference '<em>Is Child Page Of</em>'.
	 * @see com.bluexml.side.portal.Page#getIsChildPageOf()
	 * @see #getPage()
	 * @generated
	 */
	EReference getPage_IsChildPageOf();

	/**
	 * Returns the meta object for the attribute '{@link com.bluexml.side.portal.Page#getVisibility <em>Visibility</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Visibility</em>'.
	 * @see com.bluexml.side.portal.Page#getVisibility()
	 * @see #getPage()
	 * @generated
	 */
	EAttribute getPage_Visibility();

	/**
	 * Returns the meta object for the attribute '{@link com.bluexml.side.portal.Page#isGenerate <em>Generate</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Generate</em>'.
	 * @see com.bluexml.side.portal.Page#isGenerate()
	 * @see #getPage()
	 * @generated
	 */
	EAttribute getPage_Generate();

	/**
	 * Returns the meta object for class '{@link com.bluexml.side.portal.PortalLayout <em>Layout</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Layout</em>'.
	 * @see com.bluexml.side.portal.PortalLayout
	 * @generated
	 */
	EClass getPortalLayout();

	/**
	 * Returns the meta object for the attribute '{@link com.bluexml.side.portal.PortalLayout#getName <em>Name</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Name</em>'.
	 * @see com.bluexml.side.portal.PortalLayout#getName()
	 * @see #getPortalLayout()
	 * @generated
	 */
	EAttribute getPortalLayout_Name();

	/**
	 * Returns the meta object for the containment reference list '{@link com.bluexml.side.portal.PortalLayout#getColumns <em>Columns</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference list '<em>Columns</em>'.
	 * @see com.bluexml.side.portal.PortalLayout#getColumns()
	 * @see #getPortalLayout()
	 * @generated
	 */
	EReference getPortalLayout_Columns();

	/**
	 * Returns the meta object for the attribute '{@link com.bluexml.side.portal.PortalLayout#isColumnMode <em>Column Mode</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Column Mode</em>'.
	 * @see com.bluexml.side.portal.PortalLayout#isColumnMode()
	 * @see #getPortalLayout()
	 * @generated
	 */
	EAttribute getPortalLayout_ColumnMode();

	/**
	 * Returns the meta object for class '{@link com.bluexml.side.portal.Column <em>Column</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Column</em>'.
	 * @see com.bluexml.side.portal.Column
	 * @generated
	 */
	EClass getColumn();

	/**
	 * Returns the meta object for the attribute '{@link com.bluexml.side.portal.Column#getName <em>Name</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Name</em>'.
	 * @see com.bluexml.side.portal.Column#getName()
	 * @see #getColumn()
	 * @generated
	 */
	EAttribute getColumn_Name();

	/**
	 * Returns the meta object for the attribute '{@link com.bluexml.side.portal.Column#getWidth <em>Width</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Width</em>'.
	 * @see com.bluexml.side.portal.Column#getWidth()
	 * @see #getColumn()
	 * @generated
	 */
	EAttribute getColumn_Width();

	/**
	 * Returns the meta object for the attribute '{@link com.bluexml.side.portal.Column#getUnit <em>Unit</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Unit</em>'.
	 * @see com.bluexml.side.portal.Column#getUnit()
	 * @see #getColumn()
	 * @generated
	 */
	EAttribute getColumn_Unit();

	/**
	 * Returns the meta object for class '{@link com.bluexml.side.portal.Portlet <em>Portlet</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Portlet</em>'.
	 * @see com.bluexml.side.portal.Portlet
	 * @generated
	 */
	EClass getPortlet();

	/**
	 * Returns the meta object for the attribute '{@link com.bluexml.side.portal.Portlet#getName <em>Name</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Name</em>'.
	 * @see com.bluexml.side.portal.Portlet#getName()
	 * @see #getPortlet()
	 * @generated
	 */
	EAttribute getPortlet_Name();

	/**
	 * Returns the meta object for the reference '{@link com.bluexml.side.portal.Portlet#getIsPortletInternal <em>Is Portlet Internal</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference '<em>Is Portlet Internal</em>'.
	 * @see com.bluexml.side.portal.Portlet#getIsPortletInternal()
	 * @see #getPortlet()
	 * @generated
	 */
	EReference getPortlet_IsPortletInternal();

	/**
	 * Returns the meta object for the containment reference '{@link com.bluexml.side.portal.Portlet#getIsInstanceOfPortletType <em>Is Instance Of Portlet Type</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference '<em>Is Instance Of Portlet Type</em>'.
	 * @see com.bluexml.side.portal.Portlet#getIsInstanceOfPortletType()
	 * @see #getPortlet()
	 * @generated
	 */
	EReference getPortlet_IsInstanceOfPortletType();

	/**
	 * Returns the meta object for class '{@link com.bluexml.side.portal.PortletType <em>Portlet Type</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Portlet Type</em>'.
	 * @see com.bluexml.side.portal.PortletType
	 * @generated
	 */
	EClass getPortletType();

	/**
	 * Returns the meta object for the attribute '{@link com.bluexml.side.portal.PortletType#getId <em>Id</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Id</em>'.
	 * @see com.bluexml.side.portal.PortletType#getId()
	 * @see #getPortletType()
	 * @generated
	 */
	EAttribute getPortletType_Id();

	/**
	 * Returns the meta object for the attribute '{@link com.bluexml.side.portal.PortletType#getName <em>Name</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Name</em>'.
	 * @see com.bluexml.side.portal.PortletType#getName()
	 * @see #getPortletType()
	 * @generated
	 */
	EAttribute getPortletType_Name();

	/**
	 * Returns the meta object for the containment reference list '{@link com.bluexml.side.portal.PortletType#getHaveAttribute <em>Have Attribute</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference list '<em>Have Attribute</em>'.
	 * @see com.bluexml.side.portal.PortletType#getHaveAttribute()
	 * @see #getPortletType()
	 * @generated
	 */
	EReference getPortletType_HaveAttribute();

	/**
	 * Returns the meta object for the attribute '{@link com.bluexml.side.portal.PortletType#isInstanceable <em>Instanceable</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Instanceable</em>'.
	 * @see com.bluexml.side.portal.PortletType#isInstanceable()
	 * @see #getPortletType()
	 * @generated
	 */
	EAttribute getPortletType_Instanceable();

	/**
	 * Returns the meta object for class '{@link com.bluexml.side.portal.PortletInternal <em>Portlet Internal</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Portlet Internal</em>'.
	 * @see com.bluexml.side.portal.PortletInternal
	 * @generated
	 */
	EClass getPortletInternal();

	/**
	 * Returns the meta object for the attribute '{@link com.bluexml.side.portal.PortletInternal#getType <em>Type</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Type</em>'.
	 * @see com.bluexml.side.portal.PortletInternal#getType()
	 * @see #getPortletInternal()
	 * @generated
	 */
	EAttribute getPortletInternal_Type();

	/**
	 * Returns the meta object for the reference '{@link com.bluexml.side.portal.PortletInternal#getView <em>View</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference '<em>View</em>'.
	 * @see com.bluexml.side.portal.PortletInternal#getView()
	 * @see #getPortletInternal()
	 * @generated
	 */
	EReference getPortletInternal_View();

	/**
	 * Returns the meta object for the reference '{@link com.bluexml.side.portal.PortletInternal#getForm <em>Form</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference '<em>Form</em>'.
	 * @see com.bluexml.side.portal.PortletInternal#getForm()
	 * @see #getPortletInternal()
	 * @generated
	 */
	EReference getPortletInternal_Form();

	/**
	 * Returns the meta object for class '{@link com.bluexml.side.portal.AbstractPortletAttributes <em>Abstract Portlet Attributes</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Abstract Portlet Attributes</em>'.
	 * @see com.bluexml.side.portal.AbstractPortletAttributes
	 * @generated
	 */
	EClass getAbstractPortletAttributes();

	/**
	 * Returns the meta object for class '{@link com.bluexml.side.portal.Group <em>Group</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Group</em>'.
	 * @see com.bluexml.side.portal.Group
	 * @generated
	 */
	EClass getGroup();

	/**
	 * Returns the meta object for the reference '{@link com.bluexml.side.portal.Group#getUsePage <em>Use Page</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference '<em>Use Page</em>'.
	 * @see com.bluexml.side.portal.Group#getUsePage()
	 * @see #getGroup()
	 * @generated
	 */
	EReference getGroup_UsePage();

	/**
	 * Returns the meta object for the attribute '{@link com.bluexml.side.portal.Group#getName <em>Name</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Name</em>'.
	 * @see com.bluexml.side.portal.Group#getName()
	 * @see #getGroup()
	 * @generated
	 */
	EAttribute getGroup_Name();

	/**
	 * Returns the meta object for class '{@link com.bluexml.side.portal.PortletAttribute <em>Portlet Attribute</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Portlet Attribute</em>'.
	 * @see com.bluexml.side.portal.PortletAttribute
	 * @generated
	 */
	EClass getPortletAttribute();

	/**
	 * Returns the meta object for the attribute '{@link com.bluexml.side.portal.PortletAttribute#getName <em>Name</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Name</em>'.
	 * @see com.bluexml.side.portal.PortletAttribute#getName()
	 * @see #getPortletAttribute()
	 * @generated
	 */
	EAttribute getPortletAttribute_Name();

	/**
	 * Returns the meta object for the attribute '{@link com.bluexml.side.portal.PortletAttribute#getType <em>Type</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Type</em>'.
	 * @see com.bluexml.side.portal.PortletAttribute#getType()
	 * @see #getPortletAttribute()
	 * @generated
	 */
	EAttribute getPortletAttribute_Type();

	/**
	 * Returns the meta object for the attribute '{@link com.bluexml.side.portal.PortletAttribute#isRequired <em>Required</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Required</em>'.
	 * @see com.bluexml.side.portal.PortletAttribute#isRequired()
	 * @see #getPortletAttribute()
	 * @generated
	 */
	EAttribute getPortletAttribute_Required();

	/**
	 * Returns the meta object for the attribute '{@link com.bluexml.side.portal.PortletAttribute#isMultiValued <em>Multi Valued</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Multi Valued</em>'.
	 * @see com.bluexml.side.portal.PortletAttribute#isMultiValued()
	 * @see #getPortletAttribute()
	 * @generated
	 */
	EAttribute getPortletAttribute_MultiValued();

	/**
	 * Returns the meta object for class '{@link com.bluexml.side.portal.HavePortlet <em>Have Portlet</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Have Portlet</em>'.
	 * @see com.bluexml.side.portal.HavePortlet
	 * @generated
	 */
	EClass getHavePortlet();

	/**
	 * Returns the meta object for the reference '{@link com.bluexml.side.portal.HavePortlet#getAssociationPortlet <em>Association Portlet</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference '<em>Association Portlet</em>'.
	 * @see com.bluexml.side.portal.HavePortlet#getAssociationPortlet()
	 * @see #getHavePortlet()
	 * @generated
	 */
	EReference getHavePortlet_AssociationPortlet();

	/**
	 * Returns the meta object for the reference '{@link com.bluexml.side.portal.HavePortlet#getAssociationPage <em>Association Page</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference '<em>Association Page</em>'.
	 * @see com.bluexml.side.portal.HavePortlet#getAssociationPage()
	 * @see #getHavePortlet()
	 * @generated
	 */
	EReference getHavePortlet_AssociationPage();

	/**
	 * Returns the meta object for the containment reference list '{@link com.bluexml.side.portal.HavePortlet#getPositionGroup <em>Position Group</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference list '<em>Position Group</em>'.
	 * @see com.bluexml.side.portal.HavePortlet#getPositionGroup()
	 * @see #getHavePortlet()
	 * @generated
	 */
	EReference getHavePortlet_PositionGroup();

	/**
	 * Returns the meta object for class '{@link com.bluexml.side.portal.PositionGroup <em>Position Group</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Position Group</em>'.
	 * @see com.bluexml.side.portal.PositionGroup
	 * @generated
	 */
	EClass getPositionGroup();

	/**
	 * Returns the meta object for the attribute '{@link com.bluexml.side.portal.PositionGroup#getPosition <em>Position</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Position</em>'.
	 * @see com.bluexml.side.portal.PositionGroup#getPosition()
	 * @see #getPositionGroup()
	 * @generated
	 */
	EAttribute getPositionGroup_Position();

	/**
	 * Returns the meta object for the reference '{@link com.bluexml.side.portal.PositionGroup#getOnColumn <em>On Column</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference '<em>On Column</em>'.
	 * @see com.bluexml.side.portal.PositionGroup#getOnColumn()
	 * @see #getPositionGroup()
	 * @generated
	 */
	EReference getPositionGroup_OnColumn();

	/**
	 * Returns the meta object for the reference '{@link com.bluexml.side.portal.PositionGroup#getOnLayout <em>On Layout</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference '<em>On Layout</em>'.
	 * @see com.bluexml.side.portal.PositionGroup#getOnLayout()
	 * @see #getPositionGroup()
	 * @generated
	 */
	EReference getPositionGroup_OnLayout();

	/**
	 * Returns the meta object for class '{@link com.bluexml.side.portal.InstanciatePortletType <em>Instanciate Portlet Type</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Instanciate Portlet Type</em>'.
	 * @see com.bluexml.side.portal.InstanciatePortletType
	 * @generated
	 */
	EClass getInstanciatePortletType();

	/**
	 * Returns the meta object for the reference '{@link com.bluexml.side.portal.InstanciatePortletType#getPortletType <em>Portlet Type</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference '<em>Portlet Type</em>'.
	 * @see com.bluexml.side.portal.InstanciatePortletType#getPortletType()
	 * @see #getInstanciatePortletType()
	 * @generated
	 */
	EReference getInstanciatePortletType_PortletType();

	/**
	 * Returns the meta object for the containment reference list '{@link com.bluexml.side.portal.InstanciatePortletType#getInstances <em>Instances</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference list '<em>Instances</em>'.
	 * @see com.bluexml.side.portal.InstanciatePortletType#getInstances()
	 * @see #getInstanciatePortletType()
	 * @generated
	 */
	EReference getInstanciatePortletType_Instances();

	/**
	 * Returns the meta object for class '{@link com.bluexml.side.portal.PortletAttributeInstance <em>Portlet Attribute Instance</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Portlet Attribute Instance</em>'.
	 * @see com.bluexml.side.portal.PortletAttributeInstance
	 * @generated
	 */
	EClass getPortletAttributeInstance();

	/**
	 * Returns the meta object for the attribute '{@link com.bluexml.side.portal.PortletAttributeInstance#getValue <em>Value</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Value</em>'.
	 * @see com.bluexml.side.portal.PortletAttributeInstance#getValue()
	 * @see #getPortletAttributeInstance()
	 * @generated
	 */
	EAttribute getPortletAttributeInstance_Value();

	/**
	 * Returns the meta object for the reference '{@link com.bluexml.side.portal.PortletAttributeInstance#getInstanceOf <em>Instance Of</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference '<em>Instance Of</em>'.
	 * @see com.bluexml.side.portal.PortletAttributeInstance#getInstanceOf()
	 * @see #getPortletAttributeInstance()
	 * @generated
	 */
	EReference getPortletAttributeInstance_InstanceOf();

	/**
	 * Returns the meta object for class '{@link com.bluexml.side.portal.isChildPage <em>is Child Page</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>is Child Page</em>'.
	 * @see com.bluexml.side.portal.isChildPage
	 * @generated
	 */
	EClass getisChildPage();

	/**
	 * Returns the meta object for the attribute '{@link com.bluexml.side.portal.isChildPage#isInherit <em>Inherit</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Inherit</em>'.
	 * @see com.bluexml.side.portal.isChildPage#isInherit()
	 * @see #getisChildPage()
	 * @generated
	 */
	EAttribute getisChildPage_Inherit();

	/**
	 * Returns the meta object for the reference '{@link com.bluexml.side.portal.isChildPage#getIsChildPageOf <em>Is Child Page Of</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference '<em>Is Child Page Of</em>'.
	 * @see com.bluexml.side.portal.isChildPage#getIsChildPageOf()
	 * @see #getisChildPage()
	 * @generated
	 */
	EReference getisChildPage_IsChildPageOf();

	/**
	 * Returns the meta object for enum '{@link com.bluexml.side.portal.widthUnit <em>width Unit</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for enum '<em>width Unit</em>'.
	 * @see com.bluexml.side.portal.widthUnit
	 * @generated
	 */
	EEnum getwidthUnit();

	/**
	 * Returns the meta object for enum '{@link com.bluexml.side.portal.InternalPortletType <em>Internal Portlet Type</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for enum '<em>Internal Portlet Type</em>'.
	 * @see com.bluexml.side.portal.InternalPortletType
	 * @generated
	 */
	EEnum getInternalPortletType();

	/**
	 * Returns the meta object for enum '{@link com.bluexml.side.portal.PortletTypeAttributeType <em>Portlet Type Attribute Type</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for enum '<em>Portlet Type Attribute Type</em>'.
	 * @see com.bluexml.side.portal.PortletTypeAttributeType
	 * @generated
	 */
	EEnum getPortletTypeAttributeType();

	/**
	 * Returns the factory that creates the instances of the model.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the factory that creates the instances of the model.
	 * @generated
	 */
	PortalFactory getPortalFactory();

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
		 * The meta object literal for the '{@link com.bluexml.side.portal.impl.PortalModelElementImpl <em>Model Element</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see com.bluexml.side.portal.impl.PortalModelElementImpl
		 * @see com.bluexml.side.portal.impl.PortalPackageImpl#getPortalModelElement()
		 * @generated
		 */
		EClass PORTAL_MODEL_ELEMENT = eINSTANCE.getPortalModelElement();

		/**
		 * The meta object literal for the '{@link com.bluexml.side.portal.impl.PortalImpl <em>Portal</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see com.bluexml.side.portal.impl.PortalImpl
		 * @see com.bluexml.side.portal.impl.PortalPackageImpl#getPortal()
		 * @generated
		 */
		EClass PORTAL = eINSTANCE.getPortal();

		/**
		 * The meta object literal for the '<em><b>Page Set</b></em>' containment reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference PORTAL__PAGE_SET = eINSTANCE.getPortal_PageSet();

		/**
		 * The meta object literal for the '<em><b>Layout Set</b></em>' containment reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference PORTAL__LAYOUT_SET = eINSTANCE.getPortal_LayoutSet();

		/**
		 * The meta object literal for the '<em><b>Portlet Set</b></em>' containment reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference PORTAL__PORTLET_SET = eINSTANCE.getPortal_PortletSet();

		/**
		 * The meta object literal for the '<em><b>Portlet Set Type</b></em>' containment reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference PORTAL__PORTLET_SET_TYPE = eINSTANCE.getPortal_PortletSetType();

		/**
		 * The meta object literal for the '<em><b>Internal Portlet Set</b></em>' containment reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference PORTAL__INTERNAL_PORTLET_SET = eINSTANCE.getPortal_InternalPortletSet();

		/**
		 * The meta object literal for the '{@link com.bluexml.side.portal.impl.PageImpl <em>Page</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see com.bluexml.side.portal.impl.PageImpl
		 * @see com.bluexml.side.portal.impl.PortalPackageImpl#getPage()
		 * @generated
		 */
		EClass PAGE = eINSTANCE.getPage();

		/**
		 * The meta object literal for the '<em><b>ID</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute PAGE__ID = eINSTANCE.getPage_ID();

		/**
		 * The meta object literal for the '<em><b>Title</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute PAGE__TITLE = eINSTANCE.getPage_Title();

		/**
		 * The meta object literal for the '<em><b>Use Layout</b></em>' reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference PAGE__USE_LAYOUT = eINSTANCE.getPage_UseLayout();

		/**
		 * The meta object literal for the '<em><b>Portlets</b></em>' containment reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference PAGE__PORTLETS = eINSTANCE.getPage_Portlets();

		/**
		 * The meta object literal for the '<em><b>Position</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute PAGE__POSITION = eINSTANCE.getPage_Position();

		/**
		 * The meta object literal for the '<em><b>Is Child Page Of</b></em>' containment reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference PAGE__IS_CHILD_PAGE_OF = eINSTANCE.getPage_IsChildPageOf();

		/**
		 * The meta object literal for the '<em><b>Visibility</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute PAGE__VISIBILITY = eINSTANCE.getPage_Visibility();

		/**
		 * The meta object literal for the '<em><b>Generate</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute PAGE__GENERATE = eINSTANCE.getPage_Generate();

		/**
		 * The meta object literal for the '{@link com.bluexml.side.portal.impl.PortalLayoutImpl <em>Layout</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see com.bluexml.side.portal.impl.PortalLayoutImpl
		 * @see com.bluexml.side.portal.impl.PortalPackageImpl#getPortalLayout()
		 * @generated
		 */
		EClass PORTAL_LAYOUT = eINSTANCE.getPortalLayout();

		/**
		 * The meta object literal for the '<em><b>Name</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute PORTAL_LAYOUT__NAME = eINSTANCE.getPortalLayout_Name();

		/**
		 * The meta object literal for the '<em><b>Columns</b></em>' containment reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference PORTAL_LAYOUT__COLUMNS = eINSTANCE.getPortalLayout_Columns();

		/**
		 * The meta object literal for the '<em><b>Column Mode</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute PORTAL_LAYOUT__COLUMN_MODE = eINSTANCE.getPortalLayout_ColumnMode();

		/**
		 * The meta object literal for the '{@link com.bluexml.side.portal.impl.ColumnImpl <em>Column</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see com.bluexml.side.portal.impl.ColumnImpl
		 * @see com.bluexml.side.portal.impl.PortalPackageImpl#getColumn()
		 * @generated
		 */
		EClass COLUMN = eINSTANCE.getColumn();

		/**
		 * The meta object literal for the '<em><b>Name</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute COLUMN__NAME = eINSTANCE.getColumn_Name();

		/**
		 * The meta object literal for the '<em><b>Width</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute COLUMN__WIDTH = eINSTANCE.getColumn_Width();

		/**
		 * The meta object literal for the '<em><b>Unit</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute COLUMN__UNIT = eINSTANCE.getColumn_Unit();

		/**
		 * The meta object literal for the '{@link com.bluexml.side.portal.impl.PortletImpl <em>Portlet</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see com.bluexml.side.portal.impl.PortletImpl
		 * @see com.bluexml.side.portal.impl.PortalPackageImpl#getPortlet()
		 * @generated
		 */
		EClass PORTLET = eINSTANCE.getPortlet();

		/**
		 * The meta object literal for the '<em><b>Name</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute PORTLET__NAME = eINSTANCE.getPortlet_Name();

		/**
		 * The meta object literal for the '<em><b>Is Portlet Internal</b></em>' reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference PORTLET__IS_PORTLET_INTERNAL = eINSTANCE.getPortlet_IsPortletInternal();

		/**
		 * The meta object literal for the '<em><b>Is Instance Of Portlet Type</b></em>' containment reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference PORTLET__IS_INSTANCE_OF_PORTLET_TYPE = eINSTANCE.getPortlet_IsInstanceOfPortletType();

		/**
		 * The meta object literal for the '{@link com.bluexml.side.portal.impl.PortletTypeImpl <em>Portlet Type</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see com.bluexml.side.portal.impl.PortletTypeImpl
		 * @see com.bluexml.side.portal.impl.PortalPackageImpl#getPortletType()
		 * @generated
		 */
		EClass PORTLET_TYPE = eINSTANCE.getPortletType();

		/**
		 * The meta object literal for the '<em><b>Id</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute PORTLET_TYPE__ID = eINSTANCE.getPortletType_Id();

		/**
		 * The meta object literal for the '<em><b>Name</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute PORTLET_TYPE__NAME = eINSTANCE.getPortletType_Name();

		/**
		 * The meta object literal for the '<em><b>Have Attribute</b></em>' containment reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference PORTLET_TYPE__HAVE_ATTRIBUTE = eINSTANCE.getPortletType_HaveAttribute();

		/**
		 * The meta object literal for the '<em><b>Instanceable</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute PORTLET_TYPE__INSTANCEABLE = eINSTANCE.getPortletType_Instanceable();

		/**
		 * The meta object literal for the '{@link com.bluexml.side.portal.impl.PortletInternalImpl <em>Portlet Internal</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see com.bluexml.side.portal.impl.PortletInternalImpl
		 * @see com.bluexml.side.portal.impl.PortalPackageImpl#getPortletInternal()
		 * @generated
		 */
		EClass PORTLET_INTERNAL = eINSTANCE.getPortletInternal();

		/**
		 * The meta object literal for the '<em><b>Type</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute PORTLET_INTERNAL__TYPE = eINSTANCE.getPortletInternal_Type();

		/**
		 * The meta object literal for the '<em><b>View</b></em>' reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference PORTLET_INTERNAL__VIEW = eINSTANCE.getPortletInternal_View();

		/**
		 * The meta object literal for the '<em><b>Form</b></em>' reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference PORTLET_INTERNAL__FORM = eINSTANCE.getPortletInternal_Form();

		/**
		 * The meta object literal for the '{@link com.bluexml.side.portal.impl.AbstractPortletAttributesImpl <em>Abstract Portlet Attributes</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see com.bluexml.side.portal.impl.AbstractPortletAttributesImpl
		 * @see com.bluexml.side.portal.impl.PortalPackageImpl#getAbstractPortletAttributes()
		 * @generated
		 */
		EClass ABSTRACT_PORTLET_ATTRIBUTES = eINSTANCE.getAbstractPortletAttributes();

		/**
		 * The meta object literal for the '{@link com.bluexml.side.portal.impl.GroupImpl <em>Group</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see com.bluexml.side.portal.impl.GroupImpl
		 * @see com.bluexml.side.portal.impl.PortalPackageImpl#getGroup()
		 * @generated
		 */
		EClass GROUP = eINSTANCE.getGroup();

		/**
		 * The meta object literal for the '<em><b>Use Page</b></em>' reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference GROUP__USE_PAGE = eINSTANCE.getGroup_UsePage();

		/**
		 * The meta object literal for the '<em><b>Name</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute GROUP__NAME = eINSTANCE.getGroup_Name();

		/**
		 * The meta object literal for the '{@link com.bluexml.side.portal.impl.PortletAttributeImpl <em>Portlet Attribute</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see com.bluexml.side.portal.impl.PortletAttributeImpl
		 * @see com.bluexml.side.portal.impl.PortalPackageImpl#getPortletAttribute()
		 * @generated
		 */
		EClass PORTLET_ATTRIBUTE = eINSTANCE.getPortletAttribute();

		/**
		 * The meta object literal for the '<em><b>Name</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute PORTLET_ATTRIBUTE__NAME = eINSTANCE.getPortletAttribute_Name();

		/**
		 * The meta object literal for the '<em><b>Type</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute PORTLET_ATTRIBUTE__TYPE = eINSTANCE.getPortletAttribute_Type();

		/**
		 * The meta object literal for the '<em><b>Required</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute PORTLET_ATTRIBUTE__REQUIRED = eINSTANCE.getPortletAttribute_Required();

		/**
		 * The meta object literal for the '<em><b>Multi Valued</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute PORTLET_ATTRIBUTE__MULTI_VALUED = eINSTANCE.getPortletAttribute_MultiValued();

		/**
		 * The meta object literal for the '{@link com.bluexml.side.portal.impl.HavePortletImpl <em>Have Portlet</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see com.bluexml.side.portal.impl.HavePortletImpl
		 * @see com.bluexml.side.portal.impl.PortalPackageImpl#getHavePortlet()
		 * @generated
		 */
		EClass HAVE_PORTLET = eINSTANCE.getHavePortlet();

		/**
		 * The meta object literal for the '<em><b>Association Portlet</b></em>' reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference HAVE_PORTLET__ASSOCIATION_PORTLET = eINSTANCE.getHavePortlet_AssociationPortlet();

		/**
		 * The meta object literal for the '<em><b>Association Page</b></em>' reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference HAVE_PORTLET__ASSOCIATION_PAGE = eINSTANCE.getHavePortlet_AssociationPage();

		/**
		 * The meta object literal for the '<em><b>Position Group</b></em>' containment reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference HAVE_PORTLET__POSITION_GROUP = eINSTANCE.getHavePortlet_PositionGroup();

		/**
		 * The meta object literal for the '{@link com.bluexml.side.portal.impl.PositionGroupImpl <em>Position Group</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see com.bluexml.side.portal.impl.PositionGroupImpl
		 * @see com.bluexml.side.portal.impl.PortalPackageImpl#getPositionGroup()
		 * @generated
		 */
		EClass POSITION_GROUP = eINSTANCE.getPositionGroup();

		/**
		 * The meta object literal for the '<em><b>Position</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute POSITION_GROUP__POSITION = eINSTANCE.getPositionGroup_Position();

		/**
		 * The meta object literal for the '<em><b>On Column</b></em>' reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference POSITION_GROUP__ON_COLUMN = eINSTANCE.getPositionGroup_OnColumn();

		/**
		 * The meta object literal for the '<em><b>On Layout</b></em>' reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference POSITION_GROUP__ON_LAYOUT = eINSTANCE.getPositionGroup_OnLayout();

		/**
		 * The meta object literal for the '{@link com.bluexml.side.portal.impl.InstanciatePortletTypeImpl <em>Instanciate Portlet Type</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see com.bluexml.side.portal.impl.InstanciatePortletTypeImpl
		 * @see com.bluexml.side.portal.impl.PortalPackageImpl#getInstanciatePortletType()
		 * @generated
		 */
		EClass INSTANCIATE_PORTLET_TYPE = eINSTANCE.getInstanciatePortletType();

		/**
		 * The meta object literal for the '<em><b>Portlet Type</b></em>' reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference INSTANCIATE_PORTLET_TYPE__PORTLET_TYPE = eINSTANCE.getInstanciatePortletType_PortletType();

		/**
		 * The meta object literal for the '<em><b>Instances</b></em>' containment reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference INSTANCIATE_PORTLET_TYPE__INSTANCES = eINSTANCE.getInstanciatePortletType_Instances();

		/**
		 * The meta object literal for the '{@link com.bluexml.side.portal.impl.PortletAttributeInstanceImpl <em>Portlet Attribute Instance</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see com.bluexml.side.portal.impl.PortletAttributeInstanceImpl
		 * @see com.bluexml.side.portal.impl.PortalPackageImpl#getPortletAttributeInstance()
		 * @generated
		 */
		EClass PORTLET_ATTRIBUTE_INSTANCE = eINSTANCE.getPortletAttributeInstance();

		/**
		 * The meta object literal for the '<em><b>Value</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute PORTLET_ATTRIBUTE_INSTANCE__VALUE = eINSTANCE.getPortletAttributeInstance_Value();

		/**
		 * The meta object literal for the '<em><b>Instance Of</b></em>' reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference PORTLET_ATTRIBUTE_INSTANCE__INSTANCE_OF = eINSTANCE.getPortletAttributeInstance_InstanceOf();

		/**
		 * The meta object literal for the '{@link com.bluexml.side.portal.impl.isChildPageImpl <em>is Child Page</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see com.bluexml.side.portal.impl.isChildPageImpl
		 * @see com.bluexml.side.portal.impl.PortalPackageImpl#getisChildPage()
		 * @generated
		 */
		EClass IS_CHILD_PAGE = eINSTANCE.getisChildPage();

		/**
		 * The meta object literal for the '<em><b>Inherit</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute IS_CHILD_PAGE__INHERIT = eINSTANCE.getisChildPage_Inherit();

		/**
		 * The meta object literal for the '<em><b>Is Child Page Of</b></em>' reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference IS_CHILD_PAGE__IS_CHILD_PAGE_OF = eINSTANCE.getisChildPage_IsChildPageOf();

		/**
		 * The meta object literal for the '{@link com.bluexml.side.portal.widthUnit <em>width Unit</em>}' enum.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see com.bluexml.side.portal.widthUnit
		 * @see com.bluexml.side.portal.impl.PortalPackageImpl#getwidthUnit()
		 * @generated
		 */
		EEnum WIDTH_UNIT = eINSTANCE.getwidthUnit();

		/**
		 * The meta object literal for the '{@link com.bluexml.side.portal.InternalPortletType <em>Internal Portlet Type</em>}' enum.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see com.bluexml.side.portal.InternalPortletType
		 * @see com.bluexml.side.portal.impl.PortalPackageImpl#getInternalPortletType()
		 * @generated
		 */
		EEnum INTERNAL_PORTLET_TYPE = eINSTANCE.getInternalPortletType();

		/**
		 * The meta object literal for the '{@link com.bluexml.side.portal.PortletTypeAttributeType <em>Portlet Type Attribute Type</em>}' enum.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see com.bluexml.side.portal.PortletTypeAttributeType
		 * @see com.bluexml.side.portal.impl.PortalPackageImpl#getPortletTypeAttributeType()
		 * @generated
		 */
		EEnum PORTLET_TYPE_ATTRIBUTE_TYPE = eINSTANCE.getPortletTypeAttributeType();

	}

} //PortalPackage
