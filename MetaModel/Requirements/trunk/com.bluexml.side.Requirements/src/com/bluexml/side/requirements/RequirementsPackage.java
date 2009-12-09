/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package com.bluexml.side.requirements;

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
 * @see com.bluexml.side.requirements.RequirementsFactory
 * @model kind="package"
 * @generated
 */
public interface RequirementsPackage extends EPackage {
	/**
	 * The package name.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	String eNAME = "requirements";

	/**
	 * The package namespace URI.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	String eNS_URI = "http://www.kerblue.org/requirements/1.0";

	/**
	 * The package namespace name.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	String eNS_PREFIX = "requirements";

	/**
	 * The singleton instance of the package.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	RequirementsPackage eINSTANCE = com.bluexml.side.requirements.impl.RequirementsPackageImpl.init();

	/**
	 * The meta object id for the '{@link com.bluexml.side.requirements.impl.ModelElementImpl <em>Model Element</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see com.bluexml.side.requirements.impl.ModelElementImpl
	 * @see com.bluexml.side.requirements.impl.RequirementsPackageImpl#getModelElement()
	 * @generated
	 */
	int MODEL_ELEMENT = 0;

	/**
	 * The number of structural features of the '<em>Model Element</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int MODEL_ELEMENT_FEATURE_COUNT = 0;

	/**
	 * The meta object id for the '{@link com.bluexml.side.requirements.impl.BasicElementImpl <em>Basic Element</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see com.bluexml.side.requirements.impl.BasicElementImpl
	 * @see com.bluexml.side.requirements.impl.RequirementsPackageImpl#getBasicElement()
	 * @generated
	 */
	int BASIC_ELEMENT = 1;

	/**
	 * The feature id for the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int BASIC_ELEMENT__NAME = MODEL_ELEMENT_FEATURE_COUNT + 0;

	/**
	 * The feature id for the '<em><b>Documentation</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int BASIC_ELEMENT__DOCUMENTATION = MODEL_ELEMENT_FEATURE_COUNT + 1;

	/**
	 * The feature id for the '<em><b>Id</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int BASIC_ELEMENT__ID = MODEL_ELEMENT_FEATURE_COUNT + 2;

	/**
	 * The number of structural features of the '<em>Basic Element</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int BASIC_ELEMENT_FEATURE_COUNT = MODEL_ELEMENT_FEATURE_COUNT + 3;

	/**
	 * The meta object id for the '{@link com.bluexml.side.requirements.impl.EntityImpl <em>Entity</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see com.bluexml.side.requirements.impl.EntityImpl
	 * @see com.bluexml.side.requirements.impl.RequirementsPackageImpl#getEntity()
	 * @generated
	 */
	int ENTITY = 2;

	/**
	 * The feature id for the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ENTITY__NAME = BASIC_ELEMENT__NAME;

	/**
	 * The feature id for the '<em><b>Documentation</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ENTITY__DOCUMENTATION = BASIC_ELEMENT__DOCUMENTATION;

	/**
	 * The feature id for the '<em><b>Id</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ENTITY__ID = BASIC_ELEMENT__ID;

	/**
	 * The feature id for the '<em><b>Parent</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ENTITY__PARENT = BASIC_ELEMENT_FEATURE_COUNT + 0;

	/**
	 * The feature id for the '<em><b>Attributes</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ENTITY__ATTRIBUTES = BASIC_ELEMENT_FEATURE_COUNT + 1;

	/**
	 * The number of structural features of the '<em>Entity</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ENTITY_FEATURE_COUNT = BASIC_ELEMENT_FEATURE_COUNT + 2;

	/**
	 * The meta object id for the '{@link com.bluexml.side.requirements.impl.RelationShipImpl <em>Relation Ship</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see com.bluexml.side.requirements.impl.RelationShipImpl
	 * @see com.bluexml.side.requirements.impl.RequirementsPackageImpl#getRelationShip()
	 * @generated
	 */
	int RELATION_SHIP = 3;

	/**
	 * The feature id for the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int RELATION_SHIP__NAME = BASIC_ELEMENT__NAME;

	/**
	 * The feature id for the '<em><b>Documentation</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int RELATION_SHIP__DOCUMENTATION = BASIC_ELEMENT__DOCUMENTATION;

	/**
	 * The feature id for the '<em><b>Id</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int RELATION_SHIP__ID = BASIC_ELEMENT__ID;

	/**
	 * The feature id for the '<em><b>Source</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int RELATION_SHIP__SOURCE = BASIC_ELEMENT_FEATURE_COUNT + 0;

	/**
	 * The feature id for the '<em><b>Target</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int RELATION_SHIP__TARGET = BASIC_ELEMENT_FEATURE_COUNT + 1;

	/**
	 * The feature id for the '<em><b>Source Min</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int RELATION_SHIP__SOURCE_MIN = BASIC_ELEMENT_FEATURE_COUNT + 2;

	/**
	 * The feature id for the '<em><b>Source Max</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int RELATION_SHIP__SOURCE_MAX = BASIC_ELEMENT_FEATURE_COUNT + 3;

	/**
	 * The feature id for the '<em><b>Target Min</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int RELATION_SHIP__TARGET_MIN = BASIC_ELEMENT_FEATURE_COUNT + 4;

	/**
	 * The feature id for the '<em><b>Target Max</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int RELATION_SHIP__TARGET_MAX = BASIC_ELEMENT_FEATURE_COUNT + 5;

	/**
	 * The number of structural features of the '<em>Relation Ship</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int RELATION_SHIP_FEATURE_COUNT = BASIC_ELEMENT_FEATURE_COUNT + 6;

	/**
	 * The meta object id for the '{@link com.bluexml.side.requirements.impl.AttributeImpl <em>Attribute</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see com.bluexml.side.requirements.impl.AttributeImpl
	 * @see com.bluexml.side.requirements.impl.RequirementsPackageImpl#getAttribute()
	 * @generated
	 */
	int ATTRIBUTE = 4;

	/**
	 * The feature id for the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ATTRIBUTE__NAME = BASIC_ELEMENT__NAME;

	/**
	 * The feature id for the '<em><b>Documentation</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ATTRIBUTE__DOCUMENTATION = BASIC_ELEMENT__DOCUMENTATION;

	/**
	 * The feature id for the '<em><b>Id</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ATTRIBUTE__ID = BASIC_ELEMENT__ID;

	/**
	 * The feature id for the '<em><b>Type</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ATTRIBUTE__TYPE = BASIC_ELEMENT_FEATURE_COUNT + 0;

	/**
	 * The number of structural features of the '<em>Attribute</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ATTRIBUTE_FEATURE_COUNT = BASIC_ELEMENT_FEATURE_COUNT + 1;

	/**
	 * The meta object id for the '{@link com.bluexml.side.requirements.impl.OrganizationImpl <em>Organization</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see com.bluexml.side.requirements.impl.OrganizationImpl
	 * @see com.bluexml.side.requirements.impl.RequirementsPackageImpl#getOrganization()
	 * @generated
	 */
	int ORGANIZATION = 5;

	/**
	 * The meta object id for the '{@link com.bluexml.side.requirements.impl.AnnotableElementImpl <em>Annotable Element</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see com.bluexml.side.requirements.impl.AnnotableElementImpl
	 * @see com.bluexml.side.requirements.impl.RequirementsPackageImpl#getAnnotableElement()
	 * @generated
	 */
	int ANNOTABLE_ELEMENT = 14;

	/**
	 * The feature id for the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ANNOTABLE_ELEMENT__NAME = BASIC_ELEMENT__NAME;

	/**
	 * The feature id for the '<em><b>Documentation</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ANNOTABLE_ELEMENT__DOCUMENTATION = BASIC_ELEMENT__DOCUMENTATION;

	/**
	 * The feature id for the '<em><b>Id</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ANNOTABLE_ELEMENT__ID = BASIC_ELEMENT__ID;

	/**
	 * The feature id for the '<em><b>Annotation</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ANNOTABLE_ELEMENT__ANNOTATION = BASIC_ELEMENT_FEATURE_COUNT + 0;

	/**
	 * The number of structural features of the '<em>Annotable Element</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ANNOTABLE_ELEMENT_FEATURE_COUNT = BASIC_ELEMENT_FEATURE_COUNT + 1;

	/**
	 * The feature id for the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ORGANIZATION__NAME = ANNOTABLE_ELEMENT__NAME;

	/**
	 * The feature id for the '<em><b>Documentation</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ORGANIZATION__DOCUMENTATION = ANNOTABLE_ELEMENT__DOCUMENTATION;

	/**
	 * The feature id for the '<em><b>Id</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ORGANIZATION__ID = ANNOTABLE_ELEMENT__ID;

	/**
	 * The feature id for the '<em><b>Annotation</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ORGANIZATION__ANNOTATION = ANNOTABLE_ELEMENT__ANNOTATION;

	/**
	 * The feature id for the '<em><b>Child Elements</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ORGANIZATION__CHILD_ELEMENTS = ANNOTABLE_ELEMENT_FEATURE_COUNT + 0;

	/**
	 * The number of structural features of the '<em>Organization</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ORGANIZATION_FEATURE_COUNT = ANNOTABLE_ELEMENT_FEATURE_COUNT + 1;

	/**
	 * The meta object id for the '{@link com.bluexml.side.requirements.impl.AgentImpl <em>Agent</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see com.bluexml.side.requirements.impl.AgentImpl
	 * @see com.bluexml.side.requirements.impl.RequirementsPackageImpl#getAgent()
	 * @generated
	 */
	int AGENT = 6;

	/**
	 * The feature id for the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int AGENT__NAME = ANNOTABLE_ELEMENT__NAME;

	/**
	 * The feature id for the '<em><b>Documentation</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int AGENT__DOCUMENTATION = ANNOTABLE_ELEMENT__DOCUMENTATION;

	/**
	 * The feature id for the '<em><b>Id</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int AGENT__ID = ANNOTABLE_ELEMENT__ID;

	/**
	 * The feature id for the '<em><b>Annotation</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int AGENT__ANNOTATION = ANNOTABLE_ELEMENT__ANNOTATION;

	/**
	 * The feature id for the '<em><b>Is Human</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int AGENT__IS_HUMAN = ANNOTABLE_ELEMENT_FEATURE_COUNT + 0;

	/**
	 * The feature id for the '<em><b>Is Responsible</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int AGENT__IS_RESPONSIBLE = ANNOTABLE_ELEMENT_FEATURE_COUNT + 1;

	/**
	 * The number of structural features of the '<em>Agent</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int AGENT_FEATURE_COUNT = ANNOTABLE_ELEMENT_FEATURE_COUNT + 2;

	/**
	 * The meta object id for the '{@link com.bluexml.side.requirements.impl.GoalImpl <em>Goal</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see com.bluexml.side.requirements.impl.GoalImpl
	 * @see com.bluexml.side.requirements.impl.RequirementsPackageImpl#getGoal()
	 * @generated
	 */
	int GOAL = 7;

	/**
	 * The feature id for the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int GOAL__NAME = ANNOTABLE_ELEMENT__NAME;

	/**
	 * The feature id for the '<em><b>Documentation</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int GOAL__DOCUMENTATION = ANNOTABLE_ELEMENT__DOCUMENTATION;

	/**
	 * The feature id for the '<em><b>Id</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int GOAL__ID = ANNOTABLE_ELEMENT__ID;

	/**
	 * The feature id for the '<em><b>Annotation</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int GOAL__ANNOTATION = ANNOTABLE_ELEMENT__ANNOTATION;

	/**
	 * The feature id for the '<em><b>Subgoals</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int GOAL__SUBGOALS = ANNOTABLE_ELEMENT_FEATURE_COUNT + 0;

	/**
	 * The feature id for the '<em><b>Priority</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int GOAL__PRIORITY = ANNOTABLE_ELEMENT_FEATURE_COUNT + 1;

	/**
	 * The feature id for the '<em><b>Responsible</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int GOAL__RESPONSIBLE = ANNOTABLE_ELEMENT_FEATURE_COUNT + 2;

	/**
	 * The feature id for the '<em><b>Privilege Group</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int GOAL__PRIVILEGE_GROUP = ANNOTABLE_ELEMENT_FEATURE_COUNT + 3;

	/**
	 * The feature id for the '<em><b>Step</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int GOAL__STEP = ANNOTABLE_ELEMENT_FEATURE_COUNT + 4;

	/**
	 * The number of structural features of the '<em>Goal</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int GOAL_FEATURE_COUNT = ANNOTABLE_ELEMENT_FEATURE_COUNT + 5;

	/**
	 * The meta object id for the '{@link com.bluexml.side.requirements.impl.PrivilegeImpl <em>Privilege</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see com.bluexml.side.requirements.impl.PrivilegeImpl
	 * @see com.bluexml.side.requirements.impl.RequirementsPackageImpl#getPrivilege()
	 * @generated
	 */
	int PRIVILEGE = 8;

	/**
	 * The feature id for the '<em><b>Element</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PRIVILEGE__ELEMENT = 0;

	/**
	 * The feature id for the '<em><b>Category</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PRIVILEGE__CATEGORY = 1;

	/**
	 * The number of structural features of the '<em>Privilege</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PRIVILEGE_FEATURE_COUNT = 2;

	/**
	 * The meta object id for the '{@link com.bluexml.side.requirements.impl.RequirementsDefinitionImpl <em>Definition</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see com.bluexml.side.requirements.impl.RequirementsDefinitionImpl
	 * @see com.bluexml.side.requirements.impl.RequirementsPackageImpl#getRequirementsDefinition()
	 * @generated
	 */
	int REQUIREMENTS_DEFINITION = 9;

	/**
	 * The feature id for the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int REQUIREMENTS_DEFINITION__NAME = ORGANIZATION__NAME;

	/**
	 * The feature id for the '<em><b>Documentation</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int REQUIREMENTS_DEFINITION__DOCUMENTATION = ORGANIZATION__DOCUMENTATION;

	/**
	 * The feature id for the '<em><b>Id</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int REQUIREMENTS_DEFINITION__ID = ORGANIZATION__ID;

	/**
	 * The feature id for the '<em><b>Annotation</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int REQUIREMENTS_DEFINITION__ANNOTATION = ORGANIZATION__ANNOTATION;

	/**
	 * The feature id for the '<em><b>Child Elements</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int REQUIREMENTS_DEFINITION__CHILD_ELEMENTS = ORGANIZATION__CHILD_ELEMENTS;

	/**
	 * The feature id for the '<em><b>Version</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int REQUIREMENTS_DEFINITION__VERSION = ORGANIZATION_FEATURE_COUNT + 0;

	/**
	 * The feature id for the '<em><b>Date</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int REQUIREMENTS_DEFINITION__DATE = ORGANIZATION_FEATURE_COUNT + 1;

	/**
	 * The number of structural features of the '<em>Definition</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int REQUIREMENTS_DEFINITION_FEATURE_COUNT = ORGANIZATION_FEATURE_COUNT + 2;

	/**
	 * The meta object id for the '{@link com.bluexml.side.requirements.impl.PrivilegeGroupImpl <em>Privilege Group</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see com.bluexml.side.requirements.impl.PrivilegeGroupImpl
	 * @see com.bluexml.side.requirements.impl.RequirementsPackageImpl#getPrivilegeGroup()
	 * @generated
	 */
	int PRIVILEGE_GROUP = 10;

	/**
	 * The feature id for the '<em><b>Entry Point</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PRIVILEGE_GROUP__ENTRY_POINT = MODEL_ELEMENT_FEATURE_COUNT + 0;

	/**
	 * The feature id for the '<em><b>Privileges</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PRIVILEGE_GROUP__PRIVILEGES = MODEL_ELEMENT_FEATURE_COUNT + 1;

	/**
	 * The feature id for the '<em><b>Documentation</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PRIVILEGE_GROUP__DOCUMENTATION = MODEL_ELEMENT_FEATURE_COUNT + 2;

	/**
	 * The number of structural features of the '<em>Privilege Group</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PRIVILEGE_GROUP_FEATURE_COUNT = MODEL_ELEMENT_FEATURE_COUNT + 3;

	/**
	 * The meta object id for the '{@link com.bluexml.side.requirements.impl.ProcessImpl <em>Process</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see com.bluexml.side.requirements.impl.ProcessImpl
	 * @see com.bluexml.side.requirements.impl.RequirementsPackageImpl#getProcess()
	 * @generated
	 */
	int PROCESS = 11;

	/**
	 * The feature id for the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PROCESS__NAME = ORGANIZATION__NAME;

	/**
	 * The feature id for the '<em><b>Documentation</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PROCESS__DOCUMENTATION = ORGANIZATION__DOCUMENTATION;

	/**
	 * The feature id for the '<em><b>Id</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PROCESS__ID = ORGANIZATION__ID;

	/**
	 * The feature id for the '<em><b>Annotation</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PROCESS__ANNOTATION = ORGANIZATION__ANNOTATION;

	/**
	 * The feature id for the '<em><b>Child Elements</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PROCESS__CHILD_ELEMENTS = ORGANIZATION__CHILD_ELEMENTS;

	/**
	 * The number of structural features of the '<em>Process</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PROCESS_FEATURE_COUNT = ORGANIZATION_FEATURE_COUNT + 0;

	/**
	 * The meta object id for the '{@link com.bluexml.side.requirements.impl.GoalStepImpl <em>Goal Step</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see com.bluexml.side.requirements.impl.GoalStepImpl
	 * @see com.bluexml.side.requirements.impl.RequirementsPackageImpl#getGoalStep()
	 * @generated
	 */
	int GOAL_STEP = 12;

	/**
	 * The feature id for the '<em><b>Next Goals</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int GOAL_STEP__NEXT_GOALS = 0;

	/**
	 * The feature id for the '<em><b>Process</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int GOAL_STEP__PROCESS = 1;

	/**
	 * The number of structural features of the '<em>Goal Step</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int GOAL_STEP_FEATURE_COUNT = 2;

	/**
	 * The meta object id for the '{@link com.bluexml.side.requirements.impl.AnnotationImpl <em>Annotation</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see com.bluexml.side.requirements.impl.AnnotationImpl
	 * @see com.bluexml.side.requirements.impl.RequirementsPackageImpl#getAnnotation()
	 * @generated
	 */
	int ANNOTATION = 13;

	/**
	 * The feature id for the '<em><b>Author</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ANNOTATION__AUTHOR = 0;

	/**
	 * The feature id for the '<em><b>Annotation</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ANNOTATION__ANNOTATION = 1;

	/**
	 * The feature id for the '<em><b>Date</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ANNOTATION__DATE = 2;

	/**
	 * The feature id for the '<em><b>Status</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ANNOTATION__STATUS = 3;

	/**
	 * The feature id for the '<em><b>Id</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ANNOTATION__ID = 4;

	/**
	 * The feature id for the '<em><b>Comment</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ANNOTATION__COMMENT = 5;

	/**
	 * The number of structural features of the '<em>Annotation</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ANNOTATION_FEATURE_COUNT = 6;

	/**
	 * The meta object id for the '{@link com.bluexml.side.requirements.AttributeType <em>Attribute Type</em>}' enum.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see com.bluexml.side.requirements.AttributeType
	 * @see com.bluexml.side.requirements.impl.RequirementsPackageImpl#getAttributeType()
	 * @generated
	 */
	int ATTRIBUTE_TYPE = 15;

	/**
	 * The meta object id for the '{@link com.bluexml.side.requirements.PriorityLevel <em>Priority Level</em>}' enum.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see com.bluexml.side.requirements.PriorityLevel
	 * @see com.bluexml.side.requirements.impl.RequirementsPackageImpl#getPriorityLevel()
	 * @generated
	 */
	int PRIORITY_LEVEL = 16;

	/**
	 * The meta object id for the '{@link com.bluexml.side.requirements.PrivilegeNature <em>Privilege Nature</em>}' enum.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see com.bluexml.side.requirements.PrivilegeNature
	 * @see com.bluexml.side.requirements.impl.RequirementsPackageImpl#getPrivilegeNature()
	 * @generated
	 */
	int PRIVILEGE_NATURE = 17;


	/**
	 * The meta object id for the '{@link com.bluexml.side.requirements.AnnotationStatus <em>Annotation Status</em>}' enum.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see com.bluexml.side.requirements.AnnotationStatus
	 * @see com.bluexml.side.requirements.impl.RequirementsPackageImpl#getAnnotationStatus()
	 * @generated
	 */
	int ANNOTATION_STATUS = 18;


	/**
	 * Returns the meta object for class '{@link com.bluexml.side.requirements.ModelElement <em>Model Element</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Model Element</em>'.
	 * @see com.bluexml.side.requirements.ModelElement
	 * @generated
	 */
	EClass getModelElement();

	/**
	 * Returns the meta object for class '{@link com.bluexml.side.requirements.BasicElement <em>Basic Element</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Basic Element</em>'.
	 * @see com.bluexml.side.requirements.BasicElement
	 * @generated
	 */
	EClass getBasicElement();

	/**
	 * Returns the meta object for the attribute '{@link com.bluexml.side.requirements.BasicElement#getName <em>Name</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Name</em>'.
	 * @see com.bluexml.side.requirements.BasicElement#getName()
	 * @see #getBasicElement()
	 * @generated
	 */
	EAttribute getBasicElement_Name();

	/**
	 * Returns the meta object for the attribute '{@link com.bluexml.side.requirements.BasicElement#getDocumentation <em>Documentation</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Documentation</em>'.
	 * @see com.bluexml.side.requirements.BasicElement#getDocumentation()
	 * @see #getBasicElement()
	 * @generated
	 */
	EAttribute getBasicElement_Documentation();

	/**
	 * Returns the meta object for the attribute '{@link com.bluexml.side.requirements.BasicElement#getId <em>Id</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Id</em>'.
	 * @see com.bluexml.side.requirements.BasicElement#getId()
	 * @see #getBasicElement()
	 * @generated
	 */
	EAttribute getBasicElement_Id();

	/**
	 * Returns the meta object for class '{@link com.bluexml.side.requirements.Entity <em>Entity</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Entity</em>'.
	 * @see com.bluexml.side.requirements.Entity
	 * @generated
	 */
	EClass getEntity();

	/**
	 * Returns the meta object for the reference '{@link com.bluexml.side.requirements.Entity#getParent <em>Parent</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference '<em>Parent</em>'.
	 * @see com.bluexml.side.requirements.Entity#getParent()
	 * @see #getEntity()
	 * @generated
	 */
	EReference getEntity_Parent();

	/**
	 * Returns the meta object for the containment reference list '{@link com.bluexml.side.requirements.Entity#getAttributes <em>Attributes</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference list '<em>Attributes</em>'.
	 * @see com.bluexml.side.requirements.Entity#getAttributes()
	 * @see #getEntity()
	 * @generated
	 */
	EReference getEntity_Attributes();

	/**
	 * Returns the meta object for class '{@link com.bluexml.side.requirements.RelationShip <em>Relation Ship</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Relation Ship</em>'.
	 * @see com.bluexml.side.requirements.RelationShip
	 * @generated
	 */
	EClass getRelationShip();

	/**
	 * Returns the meta object for the reference '{@link com.bluexml.side.requirements.RelationShip#getSource <em>Source</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference '<em>Source</em>'.
	 * @see com.bluexml.side.requirements.RelationShip#getSource()
	 * @see #getRelationShip()
	 * @generated
	 */
	EReference getRelationShip_Source();

	/**
	 * Returns the meta object for the reference '{@link com.bluexml.side.requirements.RelationShip#getTarget <em>Target</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference '<em>Target</em>'.
	 * @see com.bluexml.side.requirements.RelationShip#getTarget()
	 * @see #getRelationShip()
	 * @generated
	 */
	EReference getRelationShip_Target();

	/**
	 * Returns the meta object for the attribute '{@link com.bluexml.side.requirements.RelationShip#getSourceMin <em>Source Min</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Source Min</em>'.
	 * @see com.bluexml.side.requirements.RelationShip#getSourceMin()
	 * @see #getRelationShip()
	 * @generated
	 */
	EAttribute getRelationShip_SourceMin();

	/**
	 * Returns the meta object for the attribute '{@link com.bluexml.side.requirements.RelationShip#getSourceMax <em>Source Max</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Source Max</em>'.
	 * @see com.bluexml.side.requirements.RelationShip#getSourceMax()
	 * @see #getRelationShip()
	 * @generated
	 */
	EAttribute getRelationShip_SourceMax();

	/**
	 * Returns the meta object for the attribute '{@link com.bluexml.side.requirements.RelationShip#getTargetMin <em>Target Min</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Target Min</em>'.
	 * @see com.bluexml.side.requirements.RelationShip#getTargetMin()
	 * @see #getRelationShip()
	 * @generated
	 */
	EAttribute getRelationShip_TargetMin();

	/**
	 * Returns the meta object for the attribute '{@link com.bluexml.side.requirements.RelationShip#getTargetMax <em>Target Max</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Target Max</em>'.
	 * @see com.bluexml.side.requirements.RelationShip#getTargetMax()
	 * @see #getRelationShip()
	 * @generated
	 */
	EAttribute getRelationShip_TargetMax();

	/**
	 * Returns the meta object for class '{@link com.bluexml.side.requirements.Attribute <em>Attribute</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Attribute</em>'.
	 * @see com.bluexml.side.requirements.Attribute
	 * @generated
	 */
	EClass getAttribute();

	/**
	 * Returns the meta object for the attribute '{@link com.bluexml.side.requirements.Attribute#getType <em>Type</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Type</em>'.
	 * @see com.bluexml.side.requirements.Attribute#getType()
	 * @see #getAttribute()
	 * @generated
	 */
	EAttribute getAttribute_Type();

	/**
	 * Returns the meta object for class '{@link com.bluexml.side.requirements.Organization <em>Organization</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Organization</em>'.
	 * @see com.bluexml.side.requirements.Organization
	 * @generated
	 */
	EClass getOrganization();

	/**
	 * Returns the meta object for the containment reference list '{@link com.bluexml.side.requirements.Organization#getChildElements <em>Child Elements</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference list '<em>Child Elements</em>'.
	 * @see com.bluexml.side.requirements.Organization#getChildElements()
	 * @see #getOrganization()
	 * @generated
	 */
	EReference getOrganization_ChildElements();

	/**
	 * Returns the meta object for class '{@link com.bluexml.side.requirements.Agent <em>Agent</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Agent</em>'.
	 * @see com.bluexml.side.requirements.Agent
	 * @generated
	 */
	EClass getAgent();

	/**
	 * Returns the meta object for the attribute '{@link com.bluexml.side.requirements.Agent#isIsHuman <em>Is Human</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Is Human</em>'.
	 * @see com.bluexml.side.requirements.Agent#isIsHuman()
	 * @see #getAgent()
	 * @generated
	 */
	EAttribute getAgent_IsHuman();

	/**
	 * Returns the meta object for the reference list '{@link com.bluexml.side.requirements.Agent#getIsResponsible <em>Is Responsible</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference list '<em>Is Responsible</em>'.
	 * @see com.bluexml.side.requirements.Agent#getIsResponsible()
	 * @see #getAgent()
	 * @generated
	 */
	EReference getAgent_IsResponsible();

	/**
	 * Returns the meta object for class '{@link com.bluexml.side.requirements.Goal <em>Goal</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Goal</em>'.
	 * @see com.bluexml.side.requirements.Goal
	 * @generated
	 */
	EClass getGoal();

	/**
	 * Returns the meta object for the containment reference list '{@link com.bluexml.side.requirements.Goal#getSubgoals <em>Subgoals</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference list '<em>Subgoals</em>'.
	 * @see com.bluexml.side.requirements.Goal#getSubgoals()
	 * @see #getGoal()
	 * @generated
	 */
	EReference getGoal_Subgoals();

	/**
	 * Returns the meta object for the attribute '{@link com.bluexml.side.requirements.Goal#getPriority <em>Priority</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Priority</em>'.
	 * @see com.bluexml.side.requirements.Goal#getPriority()
	 * @see #getGoal()
	 * @generated
	 */
	EAttribute getGoal_Priority();

	/**
	 * Returns the meta object for the reference list '{@link com.bluexml.side.requirements.Goal#getResponsible <em>Responsible</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference list '<em>Responsible</em>'.
	 * @see com.bluexml.side.requirements.Goal#getResponsible()
	 * @see #getGoal()
	 * @generated
	 */
	EReference getGoal_Responsible();

	/**
	 * Returns the meta object for the containment reference list '{@link com.bluexml.side.requirements.Goal#getPrivilegeGroup <em>Privilege Group</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference list '<em>Privilege Group</em>'.
	 * @see com.bluexml.side.requirements.Goal#getPrivilegeGroup()
	 * @see #getGoal()
	 * @generated
	 */
	EReference getGoal_PrivilegeGroup();

	/**
	 * Returns the meta object for the containment reference list '{@link com.bluexml.side.requirements.Goal#getStep <em>Step</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference list '<em>Step</em>'.
	 * @see com.bluexml.side.requirements.Goal#getStep()
	 * @see #getGoal()
	 * @generated
	 */
	EReference getGoal_Step();

	/**
	 * Returns the meta object for class '{@link com.bluexml.side.requirements.Privilege <em>Privilege</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Privilege</em>'.
	 * @see com.bluexml.side.requirements.Privilege
	 * @generated
	 */
	EClass getPrivilege();

	/**
	 * Returns the meta object for the reference '{@link com.bluexml.side.requirements.Privilege#getElement <em>Element</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference '<em>Element</em>'.
	 * @see com.bluexml.side.requirements.Privilege#getElement()
	 * @see #getPrivilege()
	 * @generated
	 */
	EReference getPrivilege_Element();

	/**
	 * Returns the meta object for the attribute '{@link com.bluexml.side.requirements.Privilege#getCategory <em>Category</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Category</em>'.
	 * @see com.bluexml.side.requirements.Privilege#getCategory()
	 * @see #getPrivilege()
	 * @generated
	 */
	EAttribute getPrivilege_Category();

	/**
	 * Returns the meta object for class '{@link com.bluexml.side.requirements.RequirementsDefinition <em>Definition</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Definition</em>'.
	 * @see com.bluexml.side.requirements.RequirementsDefinition
	 * @generated
	 */
	EClass getRequirementsDefinition();

	/**
	 * Returns the meta object for the attribute '{@link com.bluexml.side.requirements.RequirementsDefinition#getVersion <em>Version</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Version</em>'.
	 * @see com.bluexml.side.requirements.RequirementsDefinition#getVersion()
	 * @see #getRequirementsDefinition()
	 * @generated
	 */
	EAttribute getRequirementsDefinition_Version();

	/**
	 * Returns the meta object for the attribute '{@link com.bluexml.side.requirements.RequirementsDefinition#getDate <em>Date</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Date</em>'.
	 * @see com.bluexml.side.requirements.RequirementsDefinition#getDate()
	 * @see #getRequirementsDefinition()
	 * @generated
	 */
	EAttribute getRequirementsDefinition_Date();

	/**
	 * Returns the meta object for class '{@link com.bluexml.side.requirements.PrivilegeGroup <em>Privilege Group</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Privilege Group</em>'.
	 * @see com.bluexml.side.requirements.PrivilegeGroup
	 * @generated
	 */
	EClass getPrivilegeGroup();

	/**
	 * Returns the meta object for the reference '{@link com.bluexml.side.requirements.PrivilegeGroup#getEntryPoint <em>Entry Point</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference '<em>Entry Point</em>'.
	 * @see com.bluexml.side.requirements.PrivilegeGroup#getEntryPoint()
	 * @see #getPrivilegeGroup()
	 * @generated
	 */
	EReference getPrivilegeGroup_EntryPoint();

	/**
	 * Returns the meta object for the containment reference list '{@link com.bluexml.side.requirements.PrivilegeGroup#getPrivileges <em>Privileges</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference list '<em>Privileges</em>'.
	 * @see com.bluexml.side.requirements.PrivilegeGroup#getPrivileges()
	 * @see #getPrivilegeGroup()
	 * @generated
	 */
	EReference getPrivilegeGroup_Privileges();

	/**
	 * Returns the meta object for the attribute '{@link com.bluexml.side.requirements.PrivilegeGroup#getDocumentation <em>Documentation</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Documentation</em>'.
	 * @see com.bluexml.side.requirements.PrivilegeGroup#getDocumentation()
	 * @see #getPrivilegeGroup()
	 * @generated
	 */
	EAttribute getPrivilegeGroup_Documentation();

	/**
	 * Returns the meta object for class '{@link com.bluexml.side.requirements.Process <em>Process</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Process</em>'.
	 * @see com.bluexml.side.requirements.Process
	 * @generated
	 */
	EClass getProcess();

	/**
	 * Returns the meta object for class '{@link com.bluexml.side.requirements.GoalStep <em>Goal Step</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Goal Step</em>'.
	 * @see com.bluexml.side.requirements.GoalStep
	 * @generated
	 */
	EClass getGoalStep();

	/**
	 * Returns the meta object for the reference list '{@link com.bluexml.side.requirements.GoalStep#getNextGoals <em>Next Goals</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference list '<em>Next Goals</em>'.
	 * @see com.bluexml.side.requirements.GoalStep#getNextGoals()
	 * @see #getGoalStep()
	 * @generated
	 */
	EReference getGoalStep_NextGoals();

	/**
	 * Returns the meta object for the reference '{@link com.bluexml.side.requirements.GoalStep#getProcess <em>Process</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference '<em>Process</em>'.
	 * @see com.bluexml.side.requirements.GoalStep#getProcess()
	 * @see #getGoalStep()
	 * @generated
	 */
	EReference getGoalStep_Process();

	/**
	 * Returns the meta object for class '{@link com.bluexml.side.requirements.Annotation <em>Annotation</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Annotation</em>'.
	 * @see com.bluexml.side.requirements.Annotation
	 * @generated
	 */
	EClass getAnnotation();

	/**
	 * Returns the meta object for the attribute '{@link com.bluexml.side.requirements.Annotation#getId <em>Id</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Id</em>'.
	 * @see com.bluexml.side.requirements.Annotation#getId()
	 * @see #getAnnotation()
	 * @generated
	 */
	EAttribute getAnnotation_Id();

	/**
	 * Returns the meta object for the attribute '{@link com.bluexml.side.requirements.Annotation#getAuthor <em>Author</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Author</em>'.
	 * @see com.bluexml.side.requirements.Annotation#getAuthor()
	 * @see #getAnnotation()
	 * @generated
	 */
	EAttribute getAnnotation_Author();

	/**
	 * Returns the meta object for the attribute '{@link com.bluexml.side.requirements.Annotation#getAnnotation <em>Annotation</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Annotation</em>'.
	 * @see com.bluexml.side.requirements.Annotation#getAnnotation()
	 * @see #getAnnotation()
	 * @generated
	 */
	EAttribute getAnnotation_Annotation();

	/**
	 * Returns the meta object for the attribute '{@link com.bluexml.side.requirements.Annotation#getComment <em>Comment</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Comment</em>'.
	 * @see com.bluexml.side.requirements.Annotation#getComment()
	 * @see #getAnnotation()
	 * @generated
	 */
	EAttribute getAnnotation_Comment();

	/**
	 * Returns the meta object for the attribute '{@link com.bluexml.side.requirements.Annotation#getDate <em>Date</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Date</em>'.
	 * @see com.bluexml.side.requirements.Annotation#getDate()
	 * @see #getAnnotation()
	 * @generated
	 */
	EAttribute getAnnotation_Date();

	/**
	 * Returns the meta object for the attribute '{@link com.bluexml.side.requirements.Annotation#getStatus <em>Status</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Status</em>'.
	 * @see com.bluexml.side.requirements.Annotation#getStatus()
	 * @see #getAnnotation()
	 * @generated
	 */
	EAttribute getAnnotation_Status();

	/**
	 * Returns the meta object for class '{@link com.bluexml.side.requirements.AnnotableElement <em>Annotable Element</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Annotable Element</em>'.
	 * @see com.bluexml.side.requirements.AnnotableElement
	 * @generated
	 */
	EClass getAnnotableElement();

	/**
	 * Returns the meta object for the containment reference list '{@link com.bluexml.side.requirements.AnnotableElement#getAnnotation <em>Annotation</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference list '<em>Annotation</em>'.
	 * @see com.bluexml.side.requirements.AnnotableElement#getAnnotation()
	 * @see #getAnnotableElement()
	 * @generated
	 */
	EReference getAnnotableElement_Annotation();

	/**
	 * Returns the meta object for enum '{@link com.bluexml.side.requirements.AttributeType <em>Attribute Type</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for enum '<em>Attribute Type</em>'.
	 * @see com.bluexml.side.requirements.AttributeType
	 * @generated
	 */
	EEnum getAttributeType();

	/**
	 * Returns the meta object for enum '{@link com.bluexml.side.requirements.PriorityLevel <em>Priority Level</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for enum '<em>Priority Level</em>'.
	 * @see com.bluexml.side.requirements.PriorityLevel
	 * @generated
	 */
	EEnum getPriorityLevel();

	/**
	 * Returns the meta object for enum '{@link com.bluexml.side.requirements.PrivilegeNature <em>Privilege Nature</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for enum '<em>Privilege Nature</em>'.
	 * @see com.bluexml.side.requirements.PrivilegeNature
	 * @generated
	 */
	EEnum getPrivilegeNature();

	/**
	 * Returns the meta object for enum '{@link com.bluexml.side.requirements.AnnotationStatus <em>Annotation Status</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for enum '<em>Annotation Status</em>'.
	 * @see com.bluexml.side.requirements.AnnotationStatus
	 * @generated
	 */
	EEnum getAnnotationStatus();

	/**
	 * Returns the factory that creates the instances of the model.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the factory that creates the instances of the model.
	 * @generated
	 */
	RequirementsFactory getRequirementsFactory();

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
		 * The meta object literal for the '{@link com.bluexml.side.requirements.impl.ModelElementImpl <em>Model Element</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see com.bluexml.side.requirements.impl.ModelElementImpl
		 * @see com.bluexml.side.requirements.impl.RequirementsPackageImpl#getModelElement()
		 * @generated
		 */
		EClass MODEL_ELEMENT = eINSTANCE.getModelElement();

		/**
		 * The meta object literal for the '{@link com.bluexml.side.requirements.impl.BasicElementImpl <em>Basic Element</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see com.bluexml.side.requirements.impl.BasicElementImpl
		 * @see com.bluexml.side.requirements.impl.RequirementsPackageImpl#getBasicElement()
		 * @generated
		 */
		EClass BASIC_ELEMENT = eINSTANCE.getBasicElement();

		/**
		 * The meta object literal for the '<em><b>Name</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute BASIC_ELEMENT__NAME = eINSTANCE.getBasicElement_Name();

		/**
		 * The meta object literal for the '<em><b>Documentation</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute BASIC_ELEMENT__DOCUMENTATION = eINSTANCE.getBasicElement_Documentation();

		/**
		 * The meta object literal for the '<em><b>Id</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute BASIC_ELEMENT__ID = eINSTANCE.getBasicElement_Id();

		/**
		 * The meta object literal for the '{@link com.bluexml.side.requirements.impl.EntityImpl <em>Entity</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see com.bluexml.side.requirements.impl.EntityImpl
		 * @see com.bluexml.side.requirements.impl.RequirementsPackageImpl#getEntity()
		 * @generated
		 */
		EClass ENTITY = eINSTANCE.getEntity();

		/**
		 * The meta object literal for the '<em><b>Parent</b></em>' reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference ENTITY__PARENT = eINSTANCE.getEntity_Parent();

		/**
		 * The meta object literal for the '<em><b>Attributes</b></em>' containment reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference ENTITY__ATTRIBUTES = eINSTANCE.getEntity_Attributes();

		/**
		 * The meta object literal for the '{@link com.bluexml.side.requirements.impl.RelationShipImpl <em>Relation Ship</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see com.bluexml.side.requirements.impl.RelationShipImpl
		 * @see com.bluexml.side.requirements.impl.RequirementsPackageImpl#getRelationShip()
		 * @generated
		 */
		EClass RELATION_SHIP = eINSTANCE.getRelationShip();

		/**
		 * The meta object literal for the '<em><b>Source</b></em>' reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference RELATION_SHIP__SOURCE = eINSTANCE.getRelationShip_Source();

		/**
		 * The meta object literal for the '<em><b>Target</b></em>' reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference RELATION_SHIP__TARGET = eINSTANCE.getRelationShip_Target();

		/**
		 * The meta object literal for the '<em><b>Source Min</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute RELATION_SHIP__SOURCE_MIN = eINSTANCE.getRelationShip_SourceMin();

		/**
		 * The meta object literal for the '<em><b>Source Max</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute RELATION_SHIP__SOURCE_MAX = eINSTANCE.getRelationShip_SourceMax();

		/**
		 * The meta object literal for the '<em><b>Target Min</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute RELATION_SHIP__TARGET_MIN = eINSTANCE.getRelationShip_TargetMin();

		/**
		 * The meta object literal for the '<em><b>Target Max</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute RELATION_SHIP__TARGET_MAX = eINSTANCE.getRelationShip_TargetMax();

		/**
		 * The meta object literal for the '{@link com.bluexml.side.requirements.impl.AttributeImpl <em>Attribute</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see com.bluexml.side.requirements.impl.AttributeImpl
		 * @see com.bluexml.side.requirements.impl.RequirementsPackageImpl#getAttribute()
		 * @generated
		 */
		EClass ATTRIBUTE = eINSTANCE.getAttribute();

		/**
		 * The meta object literal for the '<em><b>Type</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute ATTRIBUTE__TYPE = eINSTANCE.getAttribute_Type();

		/**
		 * The meta object literal for the '{@link com.bluexml.side.requirements.impl.OrganizationImpl <em>Organization</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see com.bluexml.side.requirements.impl.OrganizationImpl
		 * @see com.bluexml.side.requirements.impl.RequirementsPackageImpl#getOrganization()
		 * @generated
		 */
		EClass ORGANIZATION = eINSTANCE.getOrganization();

		/**
		 * The meta object literal for the '<em><b>Child Elements</b></em>' containment reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference ORGANIZATION__CHILD_ELEMENTS = eINSTANCE.getOrganization_ChildElements();

		/**
		 * The meta object literal for the '{@link com.bluexml.side.requirements.impl.AgentImpl <em>Agent</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see com.bluexml.side.requirements.impl.AgentImpl
		 * @see com.bluexml.side.requirements.impl.RequirementsPackageImpl#getAgent()
		 * @generated
		 */
		EClass AGENT = eINSTANCE.getAgent();

		/**
		 * The meta object literal for the '<em><b>Is Human</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute AGENT__IS_HUMAN = eINSTANCE.getAgent_IsHuman();

		/**
		 * The meta object literal for the '<em><b>Is Responsible</b></em>' reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference AGENT__IS_RESPONSIBLE = eINSTANCE.getAgent_IsResponsible();

		/**
		 * The meta object literal for the '{@link com.bluexml.side.requirements.impl.GoalImpl <em>Goal</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see com.bluexml.side.requirements.impl.GoalImpl
		 * @see com.bluexml.side.requirements.impl.RequirementsPackageImpl#getGoal()
		 * @generated
		 */
		EClass GOAL = eINSTANCE.getGoal();

		/**
		 * The meta object literal for the '<em><b>Subgoals</b></em>' containment reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference GOAL__SUBGOALS = eINSTANCE.getGoal_Subgoals();

		/**
		 * The meta object literal for the '<em><b>Priority</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute GOAL__PRIORITY = eINSTANCE.getGoal_Priority();

		/**
		 * The meta object literal for the '<em><b>Responsible</b></em>' reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference GOAL__RESPONSIBLE = eINSTANCE.getGoal_Responsible();

		/**
		 * The meta object literal for the '<em><b>Privilege Group</b></em>' containment reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference GOAL__PRIVILEGE_GROUP = eINSTANCE.getGoal_PrivilegeGroup();

		/**
		 * The meta object literal for the '<em><b>Step</b></em>' containment reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference GOAL__STEP = eINSTANCE.getGoal_Step();

		/**
		 * The meta object literal for the '{@link com.bluexml.side.requirements.impl.PrivilegeImpl <em>Privilege</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see com.bluexml.side.requirements.impl.PrivilegeImpl
		 * @see com.bluexml.side.requirements.impl.RequirementsPackageImpl#getPrivilege()
		 * @generated
		 */
		EClass PRIVILEGE = eINSTANCE.getPrivilege();

		/**
		 * The meta object literal for the '<em><b>Element</b></em>' reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference PRIVILEGE__ELEMENT = eINSTANCE.getPrivilege_Element();

		/**
		 * The meta object literal for the '<em><b>Category</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute PRIVILEGE__CATEGORY = eINSTANCE.getPrivilege_Category();

		/**
		 * The meta object literal for the '{@link com.bluexml.side.requirements.impl.RequirementsDefinitionImpl <em>Definition</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see com.bluexml.side.requirements.impl.RequirementsDefinitionImpl
		 * @see com.bluexml.side.requirements.impl.RequirementsPackageImpl#getRequirementsDefinition()
		 * @generated
		 */
		EClass REQUIREMENTS_DEFINITION = eINSTANCE.getRequirementsDefinition();

		/**
		 * The meta object literal for the '<em><b>Version</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute REQUIREMENTS_DEFINITION__VERSION = eINSTANCE.getRequirementsDefinition_Version();

		/**
		 * The meta object literal for the '<em><b>Date</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute REQUIREMENTS_DEFINITION__DATE = eINSTANCE.getRequirementsDefinition_Date();

		/**
		 * The meta object literal for the '{@link com.bluexml.side.requirements.impl.PrivilegeGroupImpl <em>Privilege Group</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see com.bluexml.side.requirements.impl.PrivilegeGroupImpl
		 * @see com.bluexml.side.requirements.impl.RequirementsPackageImpl#getPrivilegeGroup()
		 * @generated
		 */
		EClass PRIVILEGE_GROUP = eINSTANCE.getPrivilegeGroup();

		/**
		 * The meta object literal for the '<em><b>Entry Point</b></em>' reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference PRIVILEGE_GROUP__ENTRY_POINT = eINSTANCE.getPrivilegeGroup_EntryPoint();

		/**
		 * The meta object literal for the '<em><b>Privileges</b></em>' containment reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference PRIVILEGE_GROUP__PRIVILEGES = eINSTANCE.getPrivilegeGroup_Privileges();

		/**
		 * The meta object literal for the '<em><b>Documentation</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute PRIVILEGE_GROUP__DOCUMENTATION = eINSTANCE.getPrivilegeGroup_Documentation();

		/**
		 * The meta object literal for the '{@link com.bluexml.side.requirements.impl.ProcessImpl <em>Process</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see com.bluexml.side.requirements.impl.ProcessImpl
		 * @see com.bluexml.side.requirements.impl.RequirementsPackageImpl#getProcess()
		 * @generated
		 */
		EClass PROCESS = eINSTANCE.getProcess();

		/**
		 * The meta object literal for the '{@link com.bluexml.side.requirements.impl.GoalStepImpl <em>Goal Step</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see com.bluexml.side.requirements.impl.GoalStepImpl
		 * @see com.bluexml.side.requirements.impl.RequirementsPackageImpl#getGoalStep()
		 * @generated
		 */
		EClass GOAL_STEP = eINSTANCE.getGoalStep();

		/**
		 * The meta object literal for the '<em><b>Next Goals</b></em>' reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference GOAL_STEP__NEXT_GOALS = eINSTANCE.getGoalStep_NextGoals();

		/**
		 * The meta object literal for the '<em><b>Process</b></em>' reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference GOAL_STEP__PROCESS = eINSTANCE.getGoalStep_Process();

		/**
		 * The meta object literal for the '{@link com.bluexml.side.requirements.impl.AnnotationImpl <em>Annotation</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see com.bluexml.side.requirements.impl.AnnotationImpl
		 * @see com.bluexml.side.requirements.impl.RequirementsPackageImpl#getAnnotation()
		 * @generated
		 */
		EClass ANNOTATION = eINSTANCE.getAnnotation();

		/**
		 * The meta object literal for the '<em><b>Id</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute ANNOTATION__ID = eINSTANCE.getAnnotation_Id();

		/**
		 * The meta object literal for the '<em><b>Author</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute ANNOTATION__AUTHOR = eINSTANCE.getAnnotation_Author();

		/**
		 * The meta object literal for the '<em><b>Annotation</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute ANNOTATION__ANNOTATION = eINSTANCE.getAnnotation_Annotation();

		/**
		 * The meta object literal for the '<em><b>Comment</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute ANNOTATION__COMMENT = eINSTANCE.getAnnotation_Comment();

		/**
		 * The meta object literal for the '<em><b>Date</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute ANNOTATION__DATE = eINSTANCE.getAnnotation_Date();

		/**
		 * The meta object literal for the '<em><b>Status</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute ANNOTATION__STATUS = eINSTANCE.getAnnotation_Status();

		/**
		 * The meta object literal for the '{@link com.bluexml.side.requirements.impl.AnnotableElementImpl <em>Annotable Element</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see com.bluexml.side.requirements.impl.AnnotableElementImpl
		 * @see com.bluexml.side.requirements.impl.RequirementsPackageImpl#getAnnotableElement()
		 * @generated
		 */
		EClass ANNOTABLE_ELEMENT = eINSTANCE.getAnnotableElement();

		/**
		 * The meta object literal for the '<em><b>Annotation</b></em>' containment reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference ANNOTABLE_ELEMENT__ANNOTATION = eINSTANCE.getAnnotableElement_Annotation();

		/**
		 * The meta object literal for the '{@link com.bluexml.side.requirements.AttributeType <em>Attribute Type</em>}' enum.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see com.bluexml.side.requirements.AttributeType
		 * @see com.bluexml.side.requirements.impl.RequirementsPackageImpl#getAttributeType()
		 * @generated
		 */
		EEnum ATTRIBUTE_TYPE = eINSTANCE.getAttributeType();

		/**
		 * The meta object literal for the '{@link com.bluexml.side.requirements.PriorityLevel <em>Priority Level</em>}' enum.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see com.bluexml.side.requirements.PriorityLevel
		 * @see com.bluexml.side.requirements.impl.RequirementsPackageImpl#getPriorityLevel()
		 * @generated
		 */
		EEnum PRIORITY_LEVEL = eINSTANCE.getPriorityLevel();

		/**
		 * The meta object literal for the '{@link com.bluexml.side.requirements.PrivilegeNature <em>Privilege Nature</em>}' enum.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see com.bluexml.side.requirements.PrivilegeNature
		 * @see com.bluexml.side.requirements.impl.RequirementsPackageImpl#getPrivilegeNature()
		 * @generated
		 */
		EEnum PRIVILEGE_NATURE = eINSTANCE.getPrivilegeNature();

		/**
		 * The meta object literal for the '{@link com.bluexml.side.requirements.AnnotationStatus <em>Annotation Status</em>}' enum.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see com.bluexml.side.requirements.AnnotationStatus
		 * @see com.bluexml.side.requirements.impl.RequirementsPackageImpl#getAnnotationStatus()
		 * @generated
		 */
		EEnum ANNOTATION_STATUS = eINSTANCE.getAnnotationStatus();

	}

} //RequirementsPackage
