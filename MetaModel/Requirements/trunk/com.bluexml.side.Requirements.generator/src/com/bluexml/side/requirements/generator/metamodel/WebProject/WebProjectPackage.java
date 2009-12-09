/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package com.bluexml.side.requirements.generator.metamodel.WebProject;

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
 * @see com.bluexml.side.requirements.generator.metamodel.WebProject.WebProjectFactory
 * @model kind="package"
 * @generated
 */
public interface WebProjectPackage extends EPackage {
	/**
	 * The package name.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	String eNAME = "WebProject";

	/**
	 * The package namespace URI.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	String eNS_URI = "http://www.bluexml.com/rwm/webproject/1.0/";

	/**
	 * The package namespace name.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	String eNS_PREFIX = "WebProject";

	/**
	 * The singleton instance of the package.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	WebProjectPackage eINSTANCE = com.bluexml.side.requirements.generator.metamodel.WebProject.impl.WebProjectPackageImpl.init();

	/**
	 * The meta object id for the '{@link com.bluexml.side.requirements.generator.metamodel.WebProject.impl.ProjectImpl <em>Project</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see com.bluexml.side.requirements.generator.metamodel.WebProject.impl.ProjectImpl
	 * @see com.bluexml.side.requirements.generator.metamodel.WebProject.impl.WebProjectPackageImpl#getProject()
	 * @generated
	 */
	int PROJECT = 0;

	/**
	 * The feature id for the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PROJECT__NAME = 0;

	/**
	 * The feature id for the '<em><b>Pages</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PROJECT__PAGES = 1;

	/**
	 * The feature id for the '<em><b>Schema</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PROJECT__SCHEMA = 2;

	/**
	 * The number of structural features of the '<em>Project</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PROJECT_FEATURE_COUNT = 3;

	/**
	 * The meta object id for the '{@link com.bluexml.side.requirements.generator.metamodel.WebProject.impl.LinkImpl <em>Link</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see com.bluexml.side.requirements.generator.metamodel.WebProject.impl.LinkImpl
	 * @see com.bluexml.side.requirements.generator.metamodel.WebProject.impl.WebProjectPackageImpl#getLink()
	 * @generated
	 */
	int LINK = 1;

	/**
	 * The feature id for the '<em><b>Label</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int LINK__LABEL = 0;

	/**
	 * The feature id for the '<em><b>Page</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int LINK__PAGE = 1;

	/**
	 * The number of structural features of the '<em>Link</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int LINK_FEATURE_COUNT = 2;

	/**
	 * The meta object id for the '{@link com.bluexml.side.requirements.generator.metamodel.WebProject.impl.PageImpl <em>Page</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see com.bluexml.side.requirements.generator.metamodel.WebProject.impl.PageImpl
	 * @see com.bluexml.side.requirements.generator.metamodel.WebProject.impl.WebProjectPackageImpl#getPage()
	 * @generated
	 */
	int PAGE = 2;

	/**
	 * The feature id for the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PAGE__NAME = 0;

	/**
	 * The feature id for the '<em><b>Title</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PAGE__TITLE = 1;

	/**
	 * The feature id for the '<em><b>Id</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PAGE__ID = 2;

	/**
	 * The number of structural features of the '<em>Page</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PAGE_FEATURE_COUNT = 3;

	/**
	 * The meta object id for the '{@link com.bluexml.side.requirements.generator.metamodel.WebProject.impl.LoginPageImpl <em>Login Page</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see com.bluexml.side.requirements.generator.metamodel.WebProject.impl.LoginPageImpl
	 * @see com.bluexml.side.requirements.generator.metamodel.WebProject.impl.WebProjectPackageImpl#getLoginPage()
	 * @generated
	 */
	int LOGIN_PAGE = 3;

	/**
	 * The feature id for the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int LOGIN_PAGE__NAME = PAGE__NAME;

	/**
	 * The feature id for the '<em><b>Title</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int LOGIN_PAGE__TITLE = PAGE__TITLE;

	/**
	 * The feature id for the '<em><b>Id</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int LOGIN_PAGE__ID = PAGE__ID;

	/**
	 * The feature id for the '<em><b>Links</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int LOGIN_PAGE__LINKS = PAGE_FEATURE_COUNT + 0;

	/**
	 * The number of structural features of the '<em>Login Page</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int LOGIN_PAGE_FEATURE_COUNT = PAGE_FEATURE_COUNT + 1;

	/**
	 * The meta object id for the '{@link com.bluexml.side.requirements.generator.metamodel.WebProject.impl.GoalPageImpl <em>Goal Page</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see com.bluexml.side.requirements.generator.metamodel.WebProject.impl.GoalPageImpl
	 * @see com.bluexml.side.requirements.generator.metamodel.WebProject.impl.WebProjectPackageImpl#getGoalPage()
	 * @generated
	 */
	int GOAL_PAGE = 4;

	/**
	 * The feature id for the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int GOAL_PAGE__NAME = PAGE__NAME;

	/**
	 * The feature id for the '<em><b>Title</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int GOAL_PAGE__TITLE = PAGE__TITLE;

	/**
	 * The feature id for the '<em><b>Id</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int GOAL_PAGE__ID = PAGE__ID;

	/**
	 * The feature id for the '<em><b>Items</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int GOAL_PAGE__ITEMS = PAGE_FEATURE_COUNT + 0;

	/**
	 * The number of structural features of the '<em>Goal Page</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int GOAL_PAGE_FEATURE_COUNT = PAGE_FEATURE_COUNT + 1;

	/**
	 * The meta object id for the '{@link com.bluexml.side.requirements.generator.metamodel.WebProject.impl.GoalItemImpl <em>Goal Item</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see com.bluexml.side.requirements.generator.metamodel.WebProject.impl.GoalItemImpl
	 * @see com.bluexml.side.requirements.generator.metamodel.WebProject.impl.WebProjectPackageImpl#getGoalItem()
	 * @generated
	 */
	int GOAL_ITEM = 5;

	/**
	 * The feature id for the '<em><b>Label</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int GOAL_ITEM__LABEL = 0;

	/**
	 * The feature id for the '<em><b>Page</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int GOAL_ITEM__PAGE = 1;

	/**
	 * The feature id for the '<em><b>Sub</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int GOAL_ITEM__SUB = 2;

	/**
	 * The number of structural features of the '<em>Goal Item</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int GOAL_ITEM_FEATURE_COUNT = 3;

	/**
	 * The meta object id for the '{@link com.bluexml.side.requirements.generator.metamodel.WebProject.impl.DataPageImpl <em>Data Page</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see com.bluexml.side.requirements.generator.metamodel.WebProject.impl.DataPageImpl
	 * @see com.bluexml.side.requirements.generator.metamodel.WebProject.impl.WebProjectPackageImpl#getDataPage()
	 * @generated
	 */
	int DATA_PAGE = 6;

	/**
	 * The feature id for the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DATA_PAGE__NAME = PAGE__NAME;

	/**
	 * The feature id for the '<em><b>Title</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DATA_PAGE__TITLE = PAGE__TITLE;

	/**
	 * The feature id for the '<em><b>Id</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DATA_PAGE__ID = PAGE__ID;

	/**
	 * The feature id for the '<em><b>Components</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DATA_PAGE__COMPONENTS = PAGE_FEATURE_COUNT + 0;

	/**
	 * The feature id for the '<em><b>Main Component</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DATA_PAGE__MAIN_COMPONENT = PAGE_FEATURE_COUNT + 1;

	/**
	 * The number of structural features of the '<em>Data Page</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DATA_PAGE_FEATURE_COUNT = PAGE_FEATURE_COUNT + 2;

	/**
	 * The meta object id for the '{@link com.bluexml.side.requirements.generator.metamodel.WebProject.impl.FramePageImpl <em>Frame Page</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see com.bluexml.side.requirements.generator.metamodel.WebProject.impl.FramePageImpl
	 * @see com.bluexml.side.requirements.generator.metamodel.WebProject.impl.WebProjectPackageImpl#getFramePage()
	 * @generated
	 */
	int FRAME_PAGE = 7;

	/**
	 * The feature id for the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int FRAME_PAGE__NAME = PAGE__NAME;

	/**
	 * The feature id for the '<em><b>Title</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int FRAME_PAGE__TITLE = PAGE__TITLE;

	/**
	 * The feature id for the '<em><b>Id</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int FRAME_PAGE__ID = PAGE__ID;

	/**
	 * The feature id for the '<em><b>Col1</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int FRAME_PAGE__COL1 = PAGE_FEATURE_COUNT + 0;

	/**
	 * The number of structural features of the '<em>Frame Page</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int FRAME_PAGE_FEATURE_COUNT = PAGE_FEATURE_COUNT + 1;

	/**
	 * The meta object id for the '{@link com.bluexml.side.requirements.generator.metamodel.WebProject.impl.ComponentImpl <em>Component</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see com.bluexml.side.requirements.generator.metamodel.WebProject.impl.ComponentImpl
	 * @see com.bluexml.side.requirements.generator.metamodel.WebProject.impl.WebProjectPackageImpl#getComponent()
	 * @generated
	 */
	int COMPONENT = 8;

	/**
	 * The feature id for the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int COMPONENT__NAME = 0;

	/**
	 * The feature id for the '<em><b>Can Create</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int COMPONENT__CAN_CREATE = 1;

	/**
	 * The feature id for the '<em><b>Can Read</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int COMPONENT__CAN_READ = 2;

	/**
	 * The feature id for the '<em><b>Can Update</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int COMPONENT__CAN_UPDATE = 3;

	/**
	 * The feature id for the '<em><b>Can Delete</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int COMPONENT__CAN_DELETE = 4;

	/**
	 * The feature id for the '<em><b>Table</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int COMPONENT__TABLE = 5;

	/**
	 * The feature id for the '<em><b>Properties</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int COMPONENT__PROPERTIES = 6;

	/**
	 * The number of structural features of the '<em>Component</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int COMPONENT_FEATURE_COUNT = 7;

	/**
	 * The meta object id for the '{@link com.bluexml.side.requirements.generator.metamodel.WebProject.impl.ComponentPropertyImpl <em>Component Property</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see com.bluexml.side.requirements.generator.metamodel.WebProject.impl.ComponentPropertyImpl
	 * @see com.bluexml.side.requirements.generator.metamodel.WebProject.impl.WebProjectPackageImpl#getComponentProperty()
	 * @generated
	 */
	int COMPONENT_PROPERTY = 9;

	/**
	 * The feature id for the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int COMPONENT_PROPERTY__NAME = 0;

	/**
	 * The feature id for the '<em><b>Can Read</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int COMPONENT_PROPERTY__CAN_READ = 1;

	/**
	 * The feature id for the '<em><b>Can Update</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int COMPONENT_PROPERTY__CAN_UPDATE = 2;

	/**
	 * The number of structural features of the '<em>Component Property</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int COMPONENT_PROPERTY_FEATURE_COUNT = 3;

	/**
	 * The meta object id for the '{@link com.bluexml.side.requirements.generator.metamodel.WebProject.impl.ComponentAttributeImpl <em>Component Attribute</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see com.bluexml.side.requirements.generator.metamodel.WebProject.impl.ComponentAttributeImpl
	 * @see com.bluexml.side.requirements.generator.metamodel.WebProject.impl.WebProjectPackageImpl#getComponentAttribute()
	 * @generated
	 */
	int COMPONENT_ATTRIBUTE = 10;

	/**
	 * The feature id for the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int COMPONENT_ATTRIBUTE__NAME = COMPONENT_PROPERTY__NAME;

	/**
	 * The feature id for the '<em><b>Can Read</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int COMPONENT_ATTRIBUTE__CAN_READ = COMPONENT_PROPERTY__CAN_READ;

	/**
	 * The feature id for the '<em><b>Can Update</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int COMPONENT_ATTRIBUTE__CAN_UPDATE = COMPONENT_PROPERTY__CAN_UPDATE;

	/**
	 * The feature id for the '<em><b>Field</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int COMPONENT_ATTRIBUTE__FIELD = COMPONENT_PROPERTY_FEATURE_COUNT + 0;

	/**
	 * The number of structural features of the '<em>Component Attribute</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int COMPONENT_ATTRIBUTE_FEATURE_COUNT = COMPONENT_PROPERTY_FEATURE_COUNT + 1;

	/**
	 * The meta object id for the '{@link com.bluexml.side.requirements.generator.metamodel.WebProject.impl.ComponentRelationShipImpl <em>Component Relation Ship</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see com.bluexml.side.requirements.generator.metamodel.WebProject.impl.ComponentRelationShipImpl
	 * @see com.bluexml.side.requirements.generator.metamodel.WebProject.impl.WebProjectPackageImpl#getComponentRelationShip()
	 * @generated
	 */
	int COMPONENT_RELATION_SHIP = 11;

	/**
	 * The feature id for the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int COMPONENT_RELATION_SHIP__NAME = COMPONENT_PROPERTY__NAME;

	/**
	 * The feature id for the '<em><b>Can Read</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int COMPONENT_RELATION_SHIP__CAN_READ = COMPONENT_PROPERTY__CAN_READ;

	/**
	 * The feature id for the '<em><b>Can Update</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int COMPONENT_RELATION_SHIP__CAN_UPDATE = COMPONENT_PROPERTY__CAN_UPDATE;

	/**
	 * The feature id for the '<em><b>Id Left</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int COMPONENT_RELATION_SHIP__ID_LEFT = COMPONENT_PROPERTY_FEATURE_COUNT + 0;

	/**
	 * The feature id for the '<em><b>Id Right</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int COMPONENT_RELATION_SHIP__ID_RIGHT = COMPONENT_PROPERTY_FEATURE_COUNT + 1;

	/**
	 * The feature id for the '<em><b>Mandatory Left</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int COMPONENT_RELATION_SHIP__MANDATORY_LEFT = COMPONENT_PROPERTY_FEATURE_COUNT + 2;

	/**
	 * The feature id for the '<em><b>Mandatory Right</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int COMPONENT_RELATION_SHIP__MANDATORY_RIGHT = COMPONENT_PROPERTY_FEATURE_COUNT + 3;

	/**
	 * The feature id for the '<em><b>Many Left</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int COMPONENT_RELATION_SHIP__MANY_LEFT = COMPONENT_PROPERTY_FEATURE_COUNT + 4;

	/**
	 * The feature id for the '<em><b>Many Right</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int COMPONENT_RELATION_SHIP__MANY_RIGHT = COMPONENT_PROPERTY_FEATURE_COUNT + 5;

	/**
	 * The number of structural features of the '<em>Component Relation Ship</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int COMPONENT_RELATION_SHIP_FEATURE_COUNT = COMPONENT_PROPERTY_FEATURE_COUNT + 6;

	/**
	 * The meta object id for the '{@link com.bluexml.side.requirements.generator.metamodel.WebProject.impl.SchemaImpl <em>Schema</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see com.bluexml.side.requirements.generator.metamodel.WebProject.impl.SchemaImpl
	 * @see com.bluexml.side.requirements.generator.metamodel.WebProject.impl.WebProjectPackageImpl#getSchema()
	 * @generated
	 */
	int SCHEMA = 12;

	/**
	 * The feature id for the '<em><b>Tables</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SCHEMA__TABLES = 0;

	/**
	 * The number of structural features of the '<em>Schema</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SCHEMA_FEATURE_COUNT = 1;

	/**
	 * The meta object id for the '{@link com.bluexml.side.requirements.generator.metamodel.WebProject.impl.TableImpl <em>Table</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see com.bluexml.side.requirements.generator.metamodel.WebProject.impl.TableImpl
	 * @see com.bluexml.side.requirements.generator.metamodel.WebProject.impl.WebProjectPackageImpl#getTable()
	 * @generated
	 */
	int TABLE = 13;

	/**
	 * The feature id for the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TABLE__NAME = 0;

	/**
	 * The feature id for the '<em><b>Fields</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TABLE__FIELDS = 1;

	/**
	 * The feature id for the '<em><b>Primary Key</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TABLE__PRIMARY_KEY = 2;

	/**
	 * The number of structural features of the '<em>Table</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TABLE_FEATURE_COUNT = 3;

	/**
	 * The meta object id for the '{@link com.bluexml.side.requirements.generator.metamodel.WebProject.impl.FieldImpl <em>Field</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see com.bluexml.side.requirements.generator.metamodel.WebProject.impl.FieldImpl
	 * @see com.bluexml.side.requirements.generator.metamodel.WebProject.impl.WebProjectPackageImpl#getField()
	 * @generated
	 */
	int FIELD = 14;

	/**
	 * The feature id for the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int FIELD__NAME = 0;

	/**
	 * The feature id for the '<em><b>Data Type</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int FIELD__DATA_TYPE = 1;

	/**
	 * The feature id for the '<em><b>Autoincrement</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int FIELD__AUTOINCREMENT = 2;

	/**
	 * The number of structural features of the '<em>Field</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int FIELD_FEATURE_COUNT = 3;

	/**
	 * The meta object id for the '{@link com.bluexml.side.requirements.generator.metamodel.WebProject.dataType <em>data Type</em>}' enum.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see com.bluexml.side.requirements.generator.metamodel.WebProject.dataType
	 * @see com.bluexml.side.requirements.generator.metamodel.WebProject.impl.WebProjectPackageImpl#getdataType()
	 * @generated
	 */
	int DATA_TYPE = 15;


	/**
	 * Returns the meta object for class '{@link com.bluexml.side.requirements.generator.metamodel.WebProject.Project <em>Project</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Project</em>'.
	 * @see com.bluexml.side.requirements.generator.metamodel.WebProject.Project
	 * @generated
	 */
	EClass getProject();

	/**
	 * Returns the meta object for the attribute '{@link com.bluexml.side.requirements.generator.metamodel.WebProject.Project#getName <em>Name</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Name</em>'.
	 * @see com.bluexml.side.requirements.generator.metamodel.WebProject.Project#getName()
	 * @see #getProject()
	 * @generated
	 */
	EAttribute getProject_Name();

	/**
	 * Returns the meta object for the containment reference list '{@link com.bluexml.side.requirements.generator.metamodel.WebProject.Project#getPages <em>Pages</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference list '<em>Pages</em>'.
	 * @see com.bluexml.side.requirements.generator.metamodel.WebProject.Project#getPages()
	 * @see #getProject()
	 * @generated
	 */
	EReference getProject_Pages();

	/**
	 * Returns the meta object for the containment reference '{@link com.bluexml.side.requirements.generator.metamodel.WebProject.Project#getSchema <em>Schema</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference '<em>Schema</em>'.
	 * @see com.bluexml.side.requirements.generator.metamodel.WebProject.Project#getSchema()
	 * @see #getProject()
	 * @generated
	 */
	EReference getProject_Schema();

	/**
	 * Returns the meta object for class '{@link com.bluexml.side.requirements.generator.metamodel.WebProject.Link <em>Link</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Link</em>'.
	 * @see com.bluexml.side.requirements.generator.metamodel.WebProject.Link
	 * @generated
	 */
	EClass getLink();

	/**
	 * Returns the meta object for the attribute '{@link com.bluexml.side.requirements.generator.metamodel.WebProject.Link#getLabel <em>Label</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Label</em>'.
	 * @see com.bluexml.side.requirements.generator.metamodel.WebProject.Link#getLabel()
	 * @see #getLink()
	 * @generated
	 */
	EAttribute getLink_Label();

	/**
	 * Returns the meta object for the reference '{@link com.bluexml.side.requirements.generator.metamodel.WebProject.Link#getPage <em>Page</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference '<em>Page</em>'.
	 * @see com.bluexml.side.requirements.generator.metamodel.WebProject.Link#getPage()
	 * @see #getLink()
	 * @generated
	 */
	EReference getLink_Page();

	/**
	 * Returns the meta object for class '{@link com.bluexml.side.requirements.generator.metamodel.WebProject.Page <em>Page</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Page</em>'.
	 * @see com.bluexml.side.requirements.generator.metamodel.WebProject.Page
	 * @generated
	 */
	EClass getPage();

	/**
	 * Returns the meta object for the attribute '{@link com.bluexml.side.requirements.generator.metamodel.WebProject.Page#getName <em>Name</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Name</em>'.
	 * @see com.bluexml.side.requirements.generator.metamodel.WebProject.Page#getName()
	 * @see #getPage()
	 * @generated
	 */
	EAttribute getPage_Name();

	/**
	 * Returns the meta object for the attribute '{@link com.bluexml.side.requirements.generator.metamodel.WebProject.Page#getTitle <em>Title</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Title</em>'.
	 * @see com.bluexml.side.requirements.generator.metamodel.WebProject.Page#getTitle()
	 * @see #getPage()
	 * @generated
	 */
	EAttribute getPage_Title();

	/**
	 * Returns the meta object for the attribute '{@link com.bluexml.side.requirements.generator.metamodel.WebProject.Page#getId <em>Id</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Id</em>'.
	 * @see com.bluexml.side.requirements.generator.metamodel.WebProject.Page#getId()
	 * @see #getPage()
	 * @generated
	 */
	EAttribute getPage_Id();

	/**
	 * Returns the meta object for class '{@link com.bluexml.side.requirements.generator.metamodel.WebProject.LoginPage <em>Login Page</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Login Page</em>'.
	 * @see com.bluexml.side.requirements.generator.metamodel.WebProject.LoginPage
	 * @generated
	 */
	EClass getLoginPage();

	/**
	 * Returns the meta object for the containment reference list '{@link com.bluexml.side.requirements.generator.metamodel.WebProject.LoginPage#getLinks <em>Links</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference list '<em>Links</em>'.
	 * @see com.bluexml.side.requirements.generator.metamodel.WebProject.LoginPage#getLinks()
	 * @see #getLoginPage()
	 * @generated
	 */
	EReference getLoginPage_Links();

	/**
	 * Returns the meta object for class '{@link com.bluexml.side.requirements.generator.metamodel.WebProject.GoalPage <em>Goal Page</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Goal Page</em>'.
	 * @see com.bluexml.side.requirements.generator.metamodel.WebProject.GoalPage
	 * @generated
	 */
	EClass getGoalPage();

	/**
	 * Returns the meta object for the containment reference list '{@link com.bluexml.side.requirements.generator.metamodel.WebProject.GoalPage#getItems <em>Items</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference list '<em>Items</em>'.
	 * @see com.bluexml.side.requirements.generator.metamodel.WebProject.GoalPage#getItems()
	 * @see #getGoalPage()
	 * @generated
	 */
	EReference getGoalPage_Items();

	/**
	 * Returns the meta object for class '{@link com.bluexml.side.requirements.generator.metamodel.WebProject.GoalItem <em>Goal Item</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Goal Item</em>'.
	 * @see com.bluexml.side.requirements.generator.metamodel.WebProject.GoalItem
	 * @generated
	 */
	EClass getGoalItem();

	/**
	 * Returns the meta object for the attribute '{@link com.bluexml.side.requirements.generator.metamodel.WebProject.GoalItem#getLabel <em>Label</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Label</em>'.
	 * @see com.bluexml.side.requirements.generator.metamodel.WebProject.GoalItem#getLabel()
	 * @see #getGoalItem()
	 * @generated
	 */
	EAttribute getGoalItem_Label();

	/**
	 * Returns the meta object for the reference '{@link com.bluexml.side.requirements.generator.metamodel.WebProject.GoalItem#getPage <em>Page</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference '<em>Page</em>'.
	 * @see com.bluexml.side.requirements.generator.metamodel.WebProject.GoalItem#getPage()
	 * @see #getGoalItem()
	 * @generated
	 */
	EReference getGoalItem_Page();

	/**
	 * Returns the meta object for the containment reference list '{@link com.bluexml.side.requirements.generator.metamodel.WebProject.GoalItem#getSub <em>Sub</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference list '<em>Sub</em>'.
	 * @see com.bluexml.side.requirements.generator.metamodel.WebProject.GoalItem#getSub()
	 * @see #getGoalItem()
	 * @generated
	 */
	EReference getGoalItem_Sub();

	/**
	 * Returns the meta object for class '{@link com.bluexml.side.requirements.generator.metamodel.WebProject.DataPage <em>Data Page</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Data Page</em>'.
	 * @see com.bluexml.side.requirements.generator.metamodel.WebProject.DataPage
	 * @generated
	 */
	EClass getDataPage();

	/**
	 * Returns the meta object for the containment reference list '{@link com.bluexml.side.requirements.generator.metamodel.WebProject.DataPage#getComponents <em>Components</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference list '<em>Components</em>'.
	 * @see com.bluexml.side.requirements.generator.metamodel.WebProject.DataPage#getComponents()
	 * @see #getDataPage()
	 * @generated
	 */
	EReference getDataPage_Components();

	/**
	 * Returns the meta object for the reference '{@link com.bluexml.side.requirements.generator.metamodel.WebProject.DataPage#getMainComponent <em>Main Component</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference '<em>Main Component</em>'.
	 * @see com.bluexml.side.requirements.generator.metamodel.WebProject.DataPage#getMainComponent()
	 * @see #getDataPage()
	 * @generated
	 */
	EReference getDataPage_MainComponent();

	/**
	 * Returns the meta object for class '{@link com.bluexml.side.requirements.generator.metamodel.WebProject.FramePage <em>Frame Page</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Frame Page</em>'.
	 * @see com.bluexml.side.requirements.generator.metamodel.WebProject.FramePage
	 * @generated
	 */
	EClass getFramePage();

	/**
	 * Returns the meta object for the reference '{@link com.bluexml.side.requirements.generator.metamodel.WebProject.FramePage#getCol1 <em>Col1</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference '<em>Col1</em>'.
	 * @see com.bluexml.side.requirements.generator.metamodel.WebProject.FramePage#getCol1()
	 * @see #getFramePage()
	 * @generated
	 */
	EReference getFramePage_Col1();

	/**
	 * Returns the meta object for class '{@link com.bluexml.side.requirements.generator.metamodel.WebProject.Component <em>Component</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Component</em>'.
	 * @see com.bluexml.side.requirements.generator.metamodel.WebProject.Component
	 * @generated
	 */
	EClass getComponent();

	/**
	 * Returns the meta object for the attribute '{@link com.bluexml.side.requirements.generator.metamodel.WebProject.Component#getName <em>Name</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Name</em>'.
	 * @see com.bluexml.side.requirements.generator.metamodel.WebProject.Component#getName()
	 * @see #getComponent()
	 * @generated
	 */
	EAttribute getComponent_Name();

	/**
	 * Returns the meta object for the attribute '{@link com.bluexml.side.requirements.generator.metamodel.WebProject.Component#isCanCreate <em>Can Create</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Can Create</em>'.
	 * @see com.bluexml.side.requirements.generator.metamodel.WebProject.Component#isCanCreate()
	 * @see #getComponent()
	 * @generated
	 */
	EAttribute getComponent_CanCreate();

	/**
	 * Returns the meta object for the attribute '{@link com.bluexml.side.requirements.generator.metamodel.WebProject.Component#isCanRead <em>Can Read</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Can Read</em>'.
	 * @see com.bluexml.side.requirements.generator.metamodel.WebProject.Component#isCanRead()
	 * @see #getComponent()
	 * @generated
	 */
	EAttribute getComponent_CanRead();

	/**
	 * Returns the meta object for the attribute '{@link com.bluexml.side.requirements.generator.metamodel.WebProject.Component#isCanUpdate <em>Can Update</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Can Update</em>'.
	 * @see com.bluexml.side.requirements.generator.metamodel.WebProject.Component#isCanUpdate()
	 * @see #getComponent()
	 * @generated
	 */
	EAttribute getComponent_CanUpdate();

	/**
	 * Returns the meta object for the attribute '{@link com.bluexml.side.requirements.generator.metamodel.WebProject.Component#isCanDelete <em>Can Delete</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Can Delete</em>'.
	 * @see com.bluexml.side.requirements.generator.metamodel.WebProject.Component#isCanDelete()
	 * @see #getComponent()
	 * @generated
	 */
	EAttribute getComponent_CanDelete();

	/**
	 * Returns the meta object for the reference '{@link com.bluexml.side.requirements.generator.metamodel.WebProject.Component#getTable <em>Table</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference '<em>Table</em>'.
	 * @see com.bluexml.side.requirements.generator.metamodel.WebProject.Component#getTable()
	 * @see #getComponent()
	 * @generated
	 */
	EReference getComponent_Table();

	/**
	 * Returns the meta object for the containment reference list '{@link com.bluexml.side.requirements.generator.metamodel.WebProject.Component#getProperties <em>Properties</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference list '<em>Properties</em>'.
	 * @see com.bluexml.side.requirements.generator.metamodel.WebProject.Component#getProperties()
	 * @see #getComponent()
	 * @generated
	 */
	EReference getComponent_Properties();

	/**
	 * Returns the meta object for class '{@link com.bluexml.side.requirements.generator.metamodel.WebProject.ComponentProperty <em>Component Property</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Component Property</em>'.
	 * @see com.bluexml.side.requirements.generator.metamodel.WebProject.ComponentProperty
	 * @generated
	 */
	EClass getComponentProperty();

	/**
	 * Returns the meta object for the attribute '{@link com.bluexml.side.requirements.generator.metamodel.WebProject.ComponentProperty#getName <em>Name</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Name</em>'.
	 * @see com.bluexml.side.requirements.generator.metamodel.WebProject.ComponentProperty#getName()
	 * @see #getComponentProperty()
	 * @generated
	 */
	EAttribute getComponentProperty_Name();

	/**
	 * Returns the meta object for the attribute '{@link com.bluexml.side.requirements.generator.metamodel.WebProject.ComponentProperty#isCanRead <em>Can Read</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Can Read</em>'.
	 * @see com.bluexml.side.requirements.generator.metamodel.WebProject.ComponentProperty#isCanRead()
	 * @see #getComponentProperty()
	 * @generated
	 */
	EAttribute getComponentProperty_CanRead();

	/**
	 * Returns the meta object for the attribute '{@link com.bluexml.side.requirements.generator.metamodel.WebProject.ComponentProperty#isCanUpdate <em>Can Update</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Can Update</em>'.
	 * @see com.bluexml.side.requirements.generator.metamodel.WebProject.ComponentProperty#isCanUpdate()
	 * @see #getComponentProperty()
	 * @generated
	 */
	EAttribute getComponentProperty_CanUpdate();

	/**
	 * Returns the meta object for class '{@link com.bluexml.side.requirements.generator.metamodel.WebProject.ComponentAttribute <em>Component Attribute</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Component Attribute</em>'.
	 * @see com.bluexml.side.requirements.generator.metamodel.WebProject.ComponentAttribute
	 * @generated
	 */
	EClass getComponentAttribute();

	/**
	 * Returns the meta object for the reference '{@link com.bluexml.side.requirements.generator.metamodel.WebProject.ComponentAttribute#getField <em>Field</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference '<em>Field</em>'.
	 * @see com.bluexml.side.requirements.generator.metamodel.WebProject.ComponentAttribute#getField()
	 * @see #getComponentAttribute()
	 * @generated
	 */
	EReference getComponentAttribute_Field();

	/**
	 * Returns the meta object for class '{@link com.bluexml.side.requirements.generator.metamodel.WebProject.ComponentRelationShip <em>Component Relation Ship</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Component Relation Ship</em>'.
	 * @see com.bluexml.side.requirements.generator.metamodel.WebProject.ComponentRelationShip
	 * @generated
	 */
	EClass getComponentRelationShip();

	/**
	 * Returns the meta object for the reference '{@link com.bluexml.side.requirements.generator.metamodel.WebProject.ComponentRelationShip#getIdLeft <em>Id Left</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference '<em>Id Left</em>'.
	 * @see com.bluexml.side.requirements.generator.metamodel.WebProject.ComponentRelationShip#getIdLeft()
	 * @see #getComponentRelationShip()
	 * @generated
	 */
	EReference getComponentRelationShip_IdLeft();

	/**
	 * Returns the meta object for the reference '{@link com.bluexml.side.requirements.generator.metamodel.WebProject.ComponentRelationShip#getIdRight <em>Id Right</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference '<em>Id Right</em>'.
	 * @see com.bluexml.side.requirements.generator.metamodel.WebProject.ComponentRelationShip#getIdRight()
	 * @see #getComponentRelationShip()
	 * @generated
	 */
	EReference getComponentRelationShip_IdRight();

	/**
	 * Returns the meta object for the attribute '{@link com.bluexml.side.requirements.generator.metamodel.WebProject.ComponentRelationShip#isMandatoryLeft <em>Mandatory Left</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Mandatory Left</em>'.
	 * @see com.bluexml.side.requirements.generator.metamodel.WebProject.ComponentRelationShip#isMandatoryLeft()
	 * @see #getComponentRelationShip()
	 * @generated
	 */
	EAttribute getComponentRelationShip_MandatoryLeft();

	/**
	 * Returns the meta object for the attribute '{@link com.bluexml.side.requirements.generator.metamodel.WebProject.ComponentRelationShip#isMandatoryRight <em>Mandatory Right</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Mandatory Right</em>'.
	 * @see com.bluexml.side.requirements.generator.metamodel.WebProject.ComponentRelationShip#isMandatoryRight()
	 * @see #getComponentRelationShip()
	 * @generated
	 */
	EAttribute getComponentRelationShip_MandatoryRight();

	/**
	 * Returns the meta object for the attribute '{@link com.bluexml.side.requirements.generator.metamodel.WebProject.ComponentRelationShip#isManyLeft <em>Many Left</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Many Left</em>'.
	 * @see com.bluexml.side.requirements.generator.metamodel.WebProject.ComponentRelationShip#isManyLeft()
	 * @see #getComponentRelationShip()
	 * @generated
	 */
	EAttribute getComponentRelationShip_ManyLeft();

	/**
	 * Returns the meta object for the attribute '{@link com.bluexml.side.requirements.generator.metamodel.WebProject.ComponentRelationShip#isManyRight <em>Many Right</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Many Right</em>'.
	 * @see com.bluexml.side.requirements.generator.metamodel.WebProject.ComponentRelationShip#isManyRight()
	 * @see #getComponentRelationShip()
	 * @generated
	 */
	EAttribute getComponentRelationShip_ManyRight();

	/**
	 * Returns the meta object for class '{@link com.bluexml.side.requirements.generator.metamodel.WebProject.Schema <em>Schema</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Schema</em>'.
	 * @see com.bluexml.side.requirements.generator.metamodel.WebProject.Schema
	 * @generated
	 */
	EClass getSchema();

	/**
	 * Returns the meta object for the containment reference list '{@link com.bluexml.side.requirements.generator.metamodel.WebProject.Schema#getTables <em>Tables</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference list '<em>Tables</em>'.
	 * @see com.bluexml.side.requirements.generator.metamodel.WebProject.Schema#getTables()
	 * @see #getSchema()
	 * @generated
	 */
	EReference getSchema_Tables();

	/**
	 * Returns the meta object for class '{@link com.bluexml.side.requirements.generator.metamodel.WebProject.Table <em>Table</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Table</em>'.
	 * @see com.bluexml.side.requirements.generator.metamodel.WebProject.Table
	 * @generated
	 */
	EClass getTable();

	/**
	 * Returns the meta object for the attribute '{@link com.bluexml.side.requirements.generator.metamodel.WebProject.Table#getName <em>Name</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Name</em>'.
	 * @see com.bluexml.side.requirements.generator.metamodel.WebProject.Table#getName()
	 * @see #getTable()
	 * @generated
	 */
	EAttribute getTable_Name();

	/**
	 * Returns the meta object for the containment reference list '{@link com.bluexml.side.requirements.generator.metamodel.WebProject.Table#getFields <em>Fields</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference list '<em>Fields</em>'.
	 * @see com.bluexml.side.requirements.generator.metamodel.WebProject.Table#getFields()
	 * @see #getTable()
	 * @generated
	 */
	EReference getTable_Fields();

	/**
	 * Returns the meta object for the reference list '{@link com.bluexml.side.requirements.generator.metamodel.WebProject.Table#getPrimaryKey <em>Primary Key</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference list '<em>Primary Key</em>'.
	 * @see com.bluexml.side.requirements.generator.metamodel.WebProject.Table#getPrimaryKey()
	 * @see #getTable()
	 * @generated
	 */
	EReference getTable_PrimaryKey();

	/**
	 * Returns the meta object for class '{@link com.bluexml.side.requirements.generator.metamodel.WebProject.Field <em>Field</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Field</em>'.
	 * @see com.bluexml.side.requirements.generator.metamodel.WebProject.Field
	 * @generated
	 */
	EClass getField();

	/**
	 * Returns the meta object for the attribute '{@link com.bluexml.side.requirements.generator.metamodel.WebProject.Field#getName <em>Name</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Name</em>'.
	 * @see com.bluexml.side.requirements.generator.metamodel.WebProject.Field#getName()
	 * @see #getField()
	 * @generated
	 */
	EAttribute getField_Name();

	/**
	 * Returns the meta object for the attribute '{@link com.bluexml.side.requirements.generator.metamodel.WebProject.Field#getDataType <em>Data Type</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Data Type</em>'.
	 * @see com.bluexml.side.requirements.generator.metamodel.WebProject.Field#getDataType()
	 * @see #getField()
	 * @generated
	 */
	EAttribute getField_DataType();

	/**
	 * Returns the meta object for the attribute '{@link com.bluexml.side.requirements.generator.metamodel.WebProject.Field#isAutoincrement <em>Autoincrement</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Autoincrement</em>'.
	 * @see com.bluexml.side.requirements.generator.metamodel.WebProject.Field#isAutoincrement()
	 * @see #getField()
	 * @generated
	 */
	EAttribute getField_Autoincrement();

	/**
	 * Returns the meta object for enum '{@link com.bluexml.side.requirements.generator.metamodel.WebProject.dataType <em>data Type</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for enum '<em>data Type</em>'.
	 * @see com.bluexml.side.requirements.generator.metamodel.WebProject.dataType
	 * @generated
	 */
	EEnum getdataType();

	/**
	 * Returns the factory that creates the instances of the model.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the factory that creates the instances of the model.
	 * @generated
	 */
	WebProjectFactory getWebProjectFactory();

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
		 * The meta object literal for the '{@link com.bluexml.side.requirements.generator.metamodel.WebProject.impl.ProjectImpl <em>Project</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see com.bluexml.side.requirements.generator.metamodel.WebProject.impl.ProjectImpl
		 * @see com.bluexml.side.requirements.generator.metamodel.WebProject.impl.WebProjectPackageImpl#getProject()
		 * @generated
		 */
		EClass PROJECT = eINSTANCE.getProject();

		/**
		 * The meta object literal for the '<em><b>Name</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute PROJECT__NAME = eINSTANCE.getProject_Name();

		/**
		 * The meta object literal for the '<em><b>Pages</b></em>' containment reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference PROJECT__PAGES = eINSTANCE.getProject_Pages();

		/**
		 * The meta object literal for the '<em><b>Schema</b></em>' containment reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference PROJECT__SCHEMA = eINSTANCE.getProject_Schema();

		/**
		 * The meta object literal for the '{@link com.bluexml.side.requirements.generator.metamodel.WebProject.impl.LinkImpl <em>Link</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see com.bluexml.side.requirements.generator.metamodel.WebProject.impl.LinkImpl
		 * @see com.bluexml.side.requirements.generator.metamodel.WebProject.impl.WebProjectPackageImpl#getLink()
		 * @generated
		 */
		EClass LINK = eINSTANCE.getLink();

		/**
		 * The meta object literal for the '<em><b>Label</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute LINK__LABEL = eINSTANCE.getLink_Label();

		/**
		 * The meta object literal for the '<em><b>Page</b></em>' reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference LINK__PAGE = eINSTANCE.getLink_Page();

		/**
		 * The meta object literal for the '{@link com.bluexml.side.requirements.generator.metamodel.WebProject.impl.PageImpl <em>Page</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see com.bluexml.side.requirements.generator.metamodel.WebProject.impl.PageImpl
		 * @see com.bluexml.side.requirements.generator.metamodel.WebProject.impl.WebProjectPackageImpl#getPage()
		 * @generated
		 */
		EClass PAGE = eINSTANCE.getPage();

		/**
		 * The meta object literal for the '<em><b>Name</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute PAGE__NAME = eINSTANCE.getPage_Name();

		/**
		 * The meta object literal for the '<em><b>Title</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute PAGE__TITLE = eINSTANCE.getPage_Title();

		/**
		 * The meta object literal for the '<em><b>Id</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute PAGE__ID = eINSTANCE.getPage_Id();

		/**
		 * The meta object literal for the '{@link com.bluexml.side.requirements.generator.metamodel.WebProject.impl.LoginPageImpl <em>Login Page</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see com.bluexml.side.requirements.generator.metamodel.WebProject.impl.LoginPageImpl
		 * @see com.bluexml.side.requirements.generator.metamodel.WebProject.impl.WebProjectPackageImpl#getLoginPage()
		 * @generated
		 */
		EClass LOGIN_PAGE = eINSTANCE.getLoginPage();

		/**
		 * The meta object literal for the '<em><b>Links</b></em>' containment reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference LOGIN_PAGE__LINKS = eINSTANCE.getLoginPage_Links();

		/**
		 * The meta object literal for the '{@link com.bluexml.side.requirements.generator.metamodel.WebProject.impl.GoalPageImpl <em>Goal Page</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see com.bluexml.side.requirements.generator.metamodel.WebProject.impl.GoalPageImpl
		 * @see com.bluexml.side.requirements.generator.metamodel.WebProject.impl.WebProjectPackageImpl#getGoalPage()
		 * @generated
		 */
		EClass GOAL_PAGE = eINSTANCE.getGoalPage();

		/**
		 * The meta object literal for the '<em><b>Items</b></em>' containment reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference GOAL_PAGE__ITEMS = eINSTANCE.getGoalPage_Items();

		/**
		 * The meta object literal for the '{@link com.bluexml.side.requirements.generator.metamodel.WebProject.impl.GoalItemImpl <em>Goal Item</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see com.bluexml.side.requirements.generator.metamodel.WebProject.impl.GoalItemImpl
		 * @see com.bluexml.side.requirements.generator.metamodel.WebProject.impl.WebProjectPackageImpl#getGoalItem()
		 * @generated
		 */
		EClass GOAL_ITEM = eINSTANCE.getGoalItem();

		/**
		 * The meta object literal for the '<em><b>Label</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute GOAL_ITEM__LABEL = eINSTANCE.getGoalItem_Label();

		/**
		 * The meta object literal for the '<em><b>Page</b></em>' reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference GOAL_ITEM__PAGE = eINSTANCE.getGoalItem_Page();

		/**
		 * The meta object literal for the '<em><b>Sub</b></em>' containment reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference GOAL_ITEM__SUB = eINSTANCE.getGoalItem_Sub();

		/**
		 * The meta object literal for the '{@link com.bluexml.side.requirements.generator.metamodel.WebProject.impl.DataPageImpl <em>Data Page</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see com.bluexml.side.requirements.generator.metamodel.WebProject.impl.DataPageImpl
		 * @see com.bluexml.side.requirements.generator.metamodel.WebProject.impl.WebProjectPackageImpl#getDataPage()
		 * @generated
		 */
		EClass DATA_PAGE = eINSTANCE.getDataPage();

		/**
		 * The meta object literal for the '<em><b>Components</b></em>' containment reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference DATA_PAGE__COMPONENTS = eINSTANCE.getDataPage_Components();

		/**
		 * The meta object literal for the '<em><b>Main Component</b></em>' reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference DATA_PAGE__MAIN_COMPONENT = eINSTANCE.getDataPage_MainComponent();

		/**
		 * The meta object literal for the '{@link com.bluexml.side.requirements.generator.metamodel.WebProject.impl.FramePageImpl <em>Frame Page</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see com.bluexml.side.requirements.generator.metamodel.WebProject.impl.FramePageImpl
		 * @see com.bluexml.side.requirements.generator.metamodel.WebProject.impl.WebProjectPackageImpl#getFramePage()
		 * @generated
		 */
		EClass FRAME_PAGE = eINSTANCE.getFramePage();

		/**
		 * The meta object literal for the '<em><b>Col1</b></em>' reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference FRAME_PAGE__COL1 = eINSTANCE.getFramePage_Col1();

		/**
		 * The meta object literal for the '{@link com.bluexml.side.requirements.generator.metamodel.WebProject.impl.ComponentImpl <em>Component</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see com.bluexml.side.requirements.generator.metamodel.WebProject.impl.ComponentImpl
		 * @see com.bluexml.side.requirements.generator.metamodel.WebProject.impl.WebProjectPackageImpl#getComponent()
		 * @generated
		 */
		EClass COMPONENT = eINSTANCE.getComponent();

		/**
		 * The meta object literal for the '<em><b>Name</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute COMPONENT__NAME = eINSTANCE.getComponent_Name();

		/**
		 * The meta object literal for the '<em><b>Can Create</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute COMPONENT__CAN_CREATE = eINSTANCE.getComponent_CanCreate();

		/**
		 * The meta object literal for the '<em><b>Can Read</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute COMPONENT__CAN_READ = eINSTANCE.getComponent_CanRead();

		/**
		 * The meta object literal for the '<em><b>Can Update</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute COMPONENT__CAN_UPDATE = eINSTANCE.getComponent_CanUpdate();

		/**
		 * The meta object literal for the '<em><b>Can Delete</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute COMPONENT__CAN_DELETE = eINSTANCE.getComponent_CanDelete();

		/**
		 * The meta object literal for the '<em><b>Table</b></em>' reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference COMPONENT__TABLE = eINSTANCE.getComponent_Table();

		/**
		 * The meta object literal for the '<em><b>Properties</b></em>' containment reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference COMPONENT__PROPERTIES = eINSTANCE.getComponent_Properties();

		/**
		 * The meta object literal for the '{@link com.bluexml.side.requirements.generator.metamodel.WebProject.impl.ComponentPropertyImpl <em>Component Property</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see com.bluexml.side.requirements.generator.metamodel.WebProject.impl.ComponentPropertyImpl
		 * @see com.bluexml.side.requirements.generator.metamodel.WebProject.impl.WebProjectPackageImpl#getComponentProperty()
		 * @generated
		 */
		EClass COMPONENT_PROPERTY = eINSTANCE.getComponentProperty();

		/**
		 * The meta object literal for the '<em><b>Name</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute COMPONENT_PROPERTY__NAME = eINSTANCE.getComponentProperty_Name();

		/**
		 * The meta object literal for the '<em><b>Can Read</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute COMPONENT_PROPERTY__CAN_READ = eINSTANCE.getComponentProperty_CanRead();

		/**
		 * The meta object literal for the '<em><b>Can Update</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute COMPONENT_PROPERTY__CAN_UPDATE = eINSTANCE.getComponentProperty_CanUpdate();

		/**
		 * The meta object literal for the '{@link com.bluexml.side.requirements.generator.metamodel.WebProject.impl.ComponentAttributeImpl <em>Component Attribute</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see com.bluexml.side.requirements.generator.metamodel.WebProject.impl.ComponentAttributeImpl
		 * @see com.bluexml.side.requirements.generator.metamodel.WebProject.impl.WebProjectPackageImpl#getComponentAttribute()
		 * @generated
		 */
		EClass COMPONENT_ATTRIBUTE = eINSTANCE.getComponentAttribute();

		/**
		 * The meta object literal for the '<em><b>Field</b></em>' reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference COMPONENT_ATTRIBUTE__FIELD = eINSTANCE.getComponentAttribute_Field();

		/**
		 * The meta object literal for the '{@link com.bluexml.side.requirements.generator.metamodel.WebProject.impl.ComponentRelationShipImpl <em>Component Relation Ship</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see com.bluexml.side.requirements.generator.metamodel.WebProject.impl.ComponentRelationShipImpl
		 * @see com.bluexml.side.requirements.generator.metamodel.WebProject.impl.WebProjectPackageImpl#getComponentRelationShip()
		 * @generated
		 */
		EClass COMPONENT_RELATION_SHIP = eINSTANCE.getComponentRelationShip();

		/**
		 * The meta object literal for the '<em><b>Id Left</b></em>' reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference COMPONENT_RELATION_SHIP__ID_LEFT = eINSTANCE.getComponentRelationShip_IdLeft();

		/**
		 * The meta object literal for the '<em><b>Id Right</b></em>' reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference COMPONENT_RELATION_SHIP__ID_RIGHT = eINSTANCE.getComponentRelationShip_IdRight();

		/**
		 * The meta object literal for the '<em><b>Mandatory Left</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute COMPONENT_RELATION_SHIP__MANDATORY_LEFT = eINSTANCE.getComponentRelationShip_MandatoryLeft();

		/**
		 * The meta object literal for the '<em><b>Mandatory Right</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute COMPONENT_RELATION_SHIP__MANDATORY_RIGHT = eINSTANCE.getComponentRelationShip_MandatoryRight();

		/**
		 * The meta object literal for the '<em><b>Many Left</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute COMPONENT_RELATION_SHIP__MANY_LEFT = eINSTANCE.getComponentRelationShip_ManyLeft();

		/**
		 * The meta object literal for the '<em><b>Many Right</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute COMPONENT_RELATION_SHIP__MANY_RIGHT = eINSTANCE.getComponentRelationShip_ManyRight();

		/**
		 * The meta object literal for the '{@link com.bluexml.side.requirements.generator.metamodel.WebProject.impl.SchemaImpl <em>Schema</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see com.bluexml.side.requirements.generator.metamodel.WebProject.impl.SchemaImpl
		 * @see com.bluexml.side.requirements.generator.metamodel.WebProject.impl.WebProjectPackageImpl#getSchema()
		 * @generated
		 */
		EClass SCHEMA = eINSTANCE.getSchema();

		/**
		 * The meta object literal for the '<em><b>Tables</b></em>' containment reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference SCHEMA__TABLES = eINSTANCE.getSchema_Tables();

		/**
		 * The meta object literal for the '{@link com.bluexml.side.requirements.generator.metamodel.WebProject.impl.TableImpl <em>Table</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see com.bluexml.side.requirements.generator.metamodel.WebProject.impl.TableImpl
		 * @see com.bluexml.side.requirements.generator.metamodel.WebProject.impl.WebProjectPackageImpl#getTable()
		 * @generated
		 */
		EClass TABLE = eINSTANCE.getTable();

		/**
		 * The meta object literal for the '<em><b>Name</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute TABLE__NAME = eINSTANCE.getTable_Name();

		/**
		 * The meta object literal for the '<em><b>Fields</b></em>' containment reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference TABLE__FIELDS = eINSTANCE.getTable_Fields();

		/**
		 * The meta object literal for the '<em><b>Primary Key</b></em>' reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference TABLE__PRIMARY_KEY = eINSTANCE.getTable_PrimaryKey();

		/**
		 * The meta object literal for the '{@link com.bluexml.side.requirements.generator.metamodel.WebProject.impl.FieldImpl <em>Field</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see com.bluexml.side.requirements.generator.metamodel.WebProject.impl.FieldImpl
		 * @see com.bluexml.side.requirements.generator.metamodel.WebProject.impl.WebProjectPackageImpl#getField()
		 * @generated
		 */
		EClass FIELD = eINSTANCE.getField();

		/**
		 * The meta object literal for the '<em><b>Name</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute FIELD__NAME = eINSTANCE.getField_Name();

		/**
		 * The meta object literal for the '<em><b>Data Type</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute FIELD__DATA_TYPE = eINSTANCE.getField_DataType();

		/**
		 * The meta object literal for the '<em><b>Autoincrement</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute FIELD__AUTOINCREMENT = eINSTANCE.getField_Autoincrement();

		/**
		 * The meta object literal for the '{@link com.bluexml.side.requirements.generator.metamodel.WebProject.dataType <em>data Type</em>}' enum.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see com.bluexml.side.requirements.generator.metamodel.WebProject.dataType
		 * @see com.bluexml.side.requirements.generator.metamodel.WebProject.impl.WebProjectPackageImpl#getdataType()
		 * @generated
		 */
		EEnum DATA_TYPE = eINSTANCE.getdataType();

	}

} //WebProjectPackage
