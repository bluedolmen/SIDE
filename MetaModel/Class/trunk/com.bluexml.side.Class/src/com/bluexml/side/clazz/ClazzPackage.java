/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package com.bluexml.side.clazz;

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
 * @see com.bluexml.side.clazz.ClazzFactory
 * @model kind="package"
 * @generated
 */
public interface ClazzPackage extends EPackage {
	/**
	 * The package name.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	String eNAME = "clazz";

	/**
	 * The package namespace URI.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	String eNS_URI = "http://www.kerblue.org/class/1.0";

	/**
	 * The package namespace name.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	String eNS_PREFIX = "clazz";

	/**
	 * The singleton instance of the package.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	ClazzPackage eINSTANCE = com.bluexml.side.clazz.impl.ClazzPackageImpl.init();

	/**
	 * The meta object id for the '{@link com.bluexml.side.clazz.impl.ClassModelElementImpl <em>Class Model Element</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see com.bluexml.side.clazz.impl.ClassModelElementImpl
	 * @see com.bluexml.side.clazz.impl.ClazzPackageImpl#getClassModelElement()
	 * @generated
	 */
	int CLASS_MODEL_ELEMENT = 0;

	/**
	 * The feature id for the '<em><b>Stereotypes</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CLASS_MODEL_ELEMENT__STEREOTYPES = CommonPackage.MODEL_ELEMENT__STEREOTYPES;

	/**
	 * The feature id for the '<em><b>Tags</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CLASS_MODEL_ELEMENT__TAGS = CommonPackage.MODEL_ELEMENT__TAGS;

	/**
	 * The feature id for the '<em><b>Comments</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CLASS_MODEL_ELEMENT__COMMENTS = CommonPackage.MODEL_ELEMENT__COMMENTS;

	/**
	 * The feature id for the '<em><b>Documentation</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CLASS_MODEL_ELEMENT__DOCUMENTATION = CommonPackage.MODEL_ELEMENT__DOCUMENTATION;

	/**
	 * The feature id for the '<em><b>Description</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CLASS_MODEL_ELEMENT__DESCRIPTION = CommonPackage.MODEL_ELEMENT__DESCRIPTION;

	/**
	 * The feature id for the '<em><b>Metainfo</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CLASS_MODEL_ELEMENT__METAINFO = CommonPackage.MODEL_ELEMENT__METAINFO;

	/**
	 * The feature id for the '<em><b>Has Comments</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CLASS_MODEL_ELEMENT__HAS_COMMENTS = CommonPackage.MODEL_ELEMENT_FEATURE_COUNT + 0;

	/**
	 * The number of structural features of the '<em>Class Model Element</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CLASS_MODEL_ELEMENT_FEATURE_COUNT = CommonPackage.MODEL_ELEMENT_FEATURE_COUNT + 1;

	/**
	 * The meta object id for the '{@link com.bluexml.side.clazz.impl.ClassPackageImpl <em>Class Package</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see com.bluexml.side.clazz.impl.ClassPackageImpl
	 * @see com.bluexml.side.clazz.impl.ClazzPackageImpl#getClassPackage()
	 * @generated
	 */
	int CLASS_PACKAGE = 1;

	/**
	 * The feature id for the '<em><b>Stereotypes</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CLASS_PACKAGE__STEREOTYPES = CommonPackage.PACKAGE__STEREOTYPES;

	/**
	 * The feature id for the '<em><b>Tags</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CLASS_PACKAGE__TAGS = CommonPackage.PACKAGE__TAGS;

	/**
	 * The feature id for the '<em><b>Comments</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CLASS_PACKAGE__COMMENTS = CommonPackage.PACKAGE__COMMENTS;

	/**
	 * The feature id for the '<em><b>Documentation</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CLASS_PACKAGE__DOCUMENTATION = CommonPackage.PACKAGE__DOCUMENTATION;

	/**
	 * The feature id for the '<em><b>Description</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CLASS_PACKAGE__DESCRIPTION = CommonPackage.PACKAGE__DESCRIPTION;

	/**
	 * The feature id for the '<em><b>Metainfo</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CLASS_PACKAGE__METAINFO = CommonPackage.PACKAGE__METAINFO;

	/**
	 * The feature id for the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CLASS_PACKAGE__NAME = CommonPackage.PACKAGE__NAME;

	/**
	 * The feature id for the '<em><b>Stereotype Set</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CLASS_PACKAGE__STEREOTYPE_SET = CommonPackage.PACKAGE__STEREOTYPE_SET;

	/**
	 * The feature id for the '<em><b>Package Set</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CLASS_PACKAGE__PACKAGE_SET = CommonPackage.PACKAGE__PACKAGE_SET;

	/**
	 * The feature id for the '<em><b>Class Set</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CLASS_PACKAGE__CLASS_SET = CommonPackage.PACKAGE_FEATURE_COUNT + 0;

	/**
	 * The feature id for the '<em><b>Association Set</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CLASS_PACKAGE__ASSOCIATION_SET = CommonPackage.PACKAGE_FEATURE_COUNT + 1;

	/**
	 * The feature id for the '<em><b>Aspect Set</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CLASS_PACKAGE__ASPECT_SET = CommonPackage.PACKAGE_FEATURE_COUNT + 2;

	/**
	 * The feature id for the '<em><b>Enumeration Set</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CLASS_PACKAGE__ENUMERATION_SET = CommonPackage.PACKAGE_FEATURE_COUNT + 3;

	/**
	 * The number of structural features of the '<em>Class Package</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CLASS_PACKAGE_FEATURE_COUNT = CommonPackage.PACKAGE_FEATURE_COUNT + 4;

	/**
	 * The meta object id for the '{@link com.bluexml.side.clazz.impl.TitledNamedClassModelElementImpl <em>Titled Named Class Model Element</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see com.bluexml.side.clazz.impl.TitledNamedClassModelElementImpl
	 * @see com.bluexml.side.clazz.impl.ClazzPackageImpl#getTitledNamedClassModelElement()
	 * @generated
	 */
	int TITLED_NAMED_CLASS_MODEL_ELEMENT = 9;

	/**
	 * The feature id for the '<em><b>Stereotypes</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TITLED_NAMED_CLASS_MODEL_ELEMENT__STEREOTYPES = CommonPackage.NAMED_MODEL_ELEMENT__STEREOTYPES;

	/**
	 * The feature id for the '<em><b>Tags</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TITLED_NAMED_CLASS_MODEL_ELEMENT__TAGS = CommonPackage.NAMED_MODEL_ELEMENT__TAGS;

	/**
	 * The feature id for the '<em><b>Comments</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TITLED_NAMED_CLASS_MODEL_ELEMENT__COMMENTS = CommonPackage.NAMED_MODEL_ELEMENT__COMMENTS;

	/**
	 * The feature id for the '<em><b>Documentation</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TITLED_NAMED_CLASS_MODEL_ELEMENT__DOCUMENTATION = CommonPackage.NAMED_MODEL_ELEMENT__DOCUMENTATION;

	/**
	 * The feature id for the '<em><b>Description</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TITLED_NAMED_CLASS_MODEL_ELEMENT__DESCRIPTION = CommonPackage.NAMED_MODEL_ELEMENT__DESCRIPTION;

	/**
	 * The feature id for the '<em><b>Metainfo</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TITLED_NAMED_CLASS_MODEL_ELEMENT__METAINFO = CommonPackage.NAMED_MODEL_ELEMENT__METAINFO;

	/**
	 * The feature id for the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TITLED_NAMED_CLASS_MODEL_ELEMENT__NAME = CommonPackage.NAMED_MODEL_ELEMENT__NAME;

	/**
	 * The feature id for the '<em><b>Has Comments</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TITLED_NAMED_CLASS_MODEL_ELEMENT__HAS_COMMENTS = CommonPackage.NAMED_MODEL_ELEMENT_FEATURE_COUNT + 0;

	/**
	 * The feature id for the '<em><b>Title</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TITLED_NAMED_CLASS_MODEL_ELEMENT__TITLE = CommonPackage.NAMED_MODEL_ELEMENT_FEATURE_COUNT + 1;

	/**
	 * The number of structural features of the '<em>Titled Named Class Model Element</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TITLED_NAMED_CLASS_MODEL_ELEMENT_FEATURE_COUNT = CommonPackage.NAMED_MODEL_ELEMENT_FEATURE_COUNT + 2;

	/**
	 * The meta object id for the '{@link com.bluexml.side.clazz.impl.AbstractClassImpl <em>Abstract Class</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see com.bluexml.side.clazz.impl.AbstractClassImpl
	 * @see com.bluexml.side.clazz.impl.ClazzPackageImpl#getAbstractClass()
	 * @generated
	 */
	int ABSTRACT_CLASS = 8;

	/**
	 * The feature id for the '<em><b>Stereotypes</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ABSTRACT_CLASS__STEREOTYPES = TITLED_NAMED_CLASS_MODEL_ELEMENT__STEREOTYPES;

	/**
	 * The feature id for the '<em><b>Tags</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ABSTRACT_CLASS__TAGS = TITLED_NAMED_CLASS_MODEL_ELEMENT__TAGS;

	/**
	 * The feature id for the '<em><b>Comments</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ABSTRACT_CLASS__COMMENTS = TITLED_NAMED_CLASS_MODEL_ELEMENT__COMMENTS;

	/**
	 * The feature id for the '<em><b>Documentation</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ABSTRACT_CLASS__DOCUMENTATION = TITLED_NAMED_CLASS_MODEL_ELEMENT__DOCUMENTATION;

	/**
	 * The feature id for the '<em><b>Description</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ABSTRACT_CLASS__DESCRIPTION = TITLED_NAMED_CLASS_MODEL_ELEMENT__DESCRIPTION;

	/**
	 * The feature id for the '<em><b>Metainfo</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ABSTRACT_CLASS__METAINFO = TITLED_NAMED_CLASS_MODEL_ELEMENT__METAINFO;

	/**
	 * The feature id for the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ABSTRACT_CLASS__NAME = TITLED_NAMED_CLASS_MODEL_ELEMENT__NAME;

	/**
	 * The feature id for the '<em><b>Has Comments</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ABSTRACT_CLASS__HAS_COMMENTS = TITLED_NAMED_CLASS_MODEL_ELEMENT__HAS_COMMENTS;

	/**
	 * The feature id for the '<em><b>Title</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ABSTRACT_CLASS__TITLE = TITLED_NAMED_CLASS_MODEL_ELEMENT__TITLE;

	/**
	 * The feature id for the '<em><b>Attributes</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ABSTRACT_CLASS__ATTRIBUTES = TITLED_NAMED_CLASS_MODEL_ELEMENT_FEATURE_COUNT + 0;

	/**
	 * The number of structural features of the '<em>Abstract Class</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ABSTRACT_CLASS_FEATURE_COUNT = TITLED_NAMED_CLASS_MODEL_ELEMENT_FEATURE_COUNT + 1;

	/**
	 * The meta object id for the '{@link com.bluexml.side.clazz.impl.ClazzImpl <em>Clazz</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see com.bluexml.side.clazz.impl.ClazzImpl
	 * @see com.bluexml.side.clazz.impl.ClazzPackageImpl#getClazz()
	 * @generated
	 */
	int CLAZZ = 2;

	/**
	 * The feature id for the '<em><b>Stereotypes</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CLAZZ__STEREOTYPES = ABSTRACT_CLASS__STEREOTYPES;

	/**
	 * The feature id for the '<em><b>Tags</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CLAZZ__TAGS = ABSTRACT_CLASS__TAGS;

	/**
	 * The feature id for the '<em><b>Comments</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CLAZZ__COMMENTS = ABSTRACT_CLASS__COMMENTS;

	/**
	 * The feature id for the '<em><b>Documentation</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CLAZZ__DOCUMENTATION = ABSTRACT_CLASS__DOCUMENTATION;

	/**
	 * The feature id for the '<em><b>Description</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CLAZZ__DESCRIPTION = ABSTRACT_CLASS__DESCRIPTION;

	/**
	 * The feature id for the '<em><b>Metainfo</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CLAZZ__METAINFO = ABSTRACT_CLASS__METAINFO;

	/**
	 * The feature id for the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CLAZZ__NAME = ABSTRACT_CLASS__NAME;

	/**
	 * The feature id for the '<em><b>Has Comments</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CLAZZ__HAS_COMMENTS = ABSTRACT_CLASS__HAS_COMMENTS;

	/**
	 * The feature id for the '<em><b>Title</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CLAZZ__TITLE = ABSTRACT_CLASS__TITLE;

	/**
	 * The feature id for the '<em><b>Attributes</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CLAZZ__ATTRIBUTES = ABSTRACT_CLASS__ATTRIBUTES;

	/**
	 * The feature id for the '<em><b>Operations</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CLAZZ__OPERATIONS = ABSTRACT_CLASS_FEATURE_COUNT + 0;

	/**
	 * The feature id for the '<em><b>Generalizations</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CLAZZ__GENERALIZATIONS = ABSTRACT_CLASS_FEATURE_COUNT + 1;

	/**
	 * The feature id for the '<em><b>Aspects</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CLAZZ__ASPECTS = ABSTRACT_CLASS_FEATURE_COUNT + 2;

	/**
	 * The feature id for the '<em><b>Abstract</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CLAZZ__ABSTRACT = ABSTRACT_CLASS_FEATURE_COUNT + 3;

	/**
	 * The feature id for the '<em><b>Deprecated</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CLAZZ__DEPRECATED = ABSTRACT_CLASS_FEATURE_COUNT + 4;

	/**
	 * The number of structural features of the '<em>Clazz</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CLAZZ_FEATURE_COUNT = ABSTRACT_CLASS_FEATURE_COUNT + 5;

	/**
	 * The meta object id for the '{@link com.bluexml.side.clazz.impl.AssociationImpl <em>Association</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see com.bluexml.side.clazz.impl.AssociationImpl
	 * @see com.bluexml.side.clazz.impl.ClazzPackageImpl#getAssociation()
	 * @generated
	 */
	int ASSOCIATION = 3;

	/**
	 * The feature id for the '<em><b>Stereotypes</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ASSOCIATION__STEREOTYPES = TITLED_NAMED_CLASS_MODEL_ELEMENT__STEREOTYPES;

	/**
	 * The feature id for the '<em><b>Tags</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ASSOCIATION__TAGS = TITLED_NAMED_CLASS_MODEL_ELEMENT__TAGS;

	/**
	 * The feature id for the '<em><b>Comments</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ASSOCIATION__COMMENTS = TITLED_NAMED_CLASS_MODEL_ELEMENT__COMMENTS;

	/**
	 * The feature id for the '<em><b>Documentation</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ASSOCIATION__DOCUMENTATION = TITLED_NAMED_CLASS_MODEL_ELEMENT__DOCUMENTATION;

	/**
	 * The feature id for the '<em><b>Description</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ASSOCIATION__DESCRIPTION = TITLED_NAMED_CLASS_MODEL_ELEMENT__DESCRIPTION;

	/**
	 * The feature id for the '<em><b>Metainfo</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ASSOCIATION__METAINFO = TITLED_NAMED_CLASS_MODEL_ELEMENT__METAINFO;

	/**
	 * The feature id for the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ASSOCIATION__NAME = TITLED_NAMED_CLASS_MODEL_ELEMENT__NAME;

	/**
	 * The feature id for the '<em><b>Has Comments</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ASSOCIATION__HAS_COMMENTS = TITLED_NAMED_CLASS_MODEL_ELEMENT__HAS_COMMENTS;

	/**
	 * The feature id for the '<em><b>Title</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ASSOCIATION__TITLE = TITLED_NAMED_CLASS_MODEL_ELEMENT__TITLE;

	/**
	 * The feature id for the '<em><b>Association Type</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ASSOCIATION__ASSOCIATION_TYPE = TITLED_NAMED_CLASS_MODEL_ELEMENT_FEATURE_COUNT + 0;

	/**
	 * The feature id for the '<em><b>First End</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ASSOCIATION__FIRST_END = TITLED_NAMED_CLASS_MODEL_ELEMENT_FEATURE_COUNT + 1;

	/**
	 * The feature id for the '<em><b>Second End</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ASSOCIATION__SECOND_END = TITLED_NAMED_CLASS_MODEL_ELEMENT_FEATURE_COUNT + 2;

	/**
	 * The feature id for the '<em><b>Ordered</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ASSOCIATION__ORDERED = TITLED_NAMED_CLASS_MODEL_ELEMENT_FEATURE_COUNT + 3;

	/**
	 * The number of structural features of the '<em>Association</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ASSOCIATION_FEATURE_COUNT = TITLED_NAMED_CLASS_MODEL_ELEMENT_FEATURE_COUNT + 4;

	/**
	 * The meta object id for the '{@link com.bluexml.side.clazz.impl.AttributeImpl <em>Attribute</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see com.bluexml.side.clazz.impl.AttributeImpl
	 * @see com.bluexml.side.clazz.impl.ClazzPackageImpl#getAttribute()
	 * @generated
	 */
	int ATTRIBUTE = 4;

	/**
	 * The feature id for the '<em><b>Stereotypes</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ATTRIBUTE__STEREOTYPES = TITLED_NAMED_CLASS_MODEL_ELEMENT__STEREOTYPES;

	/**
	 * The feature id for the '<em><b>Tags</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ATTRIBUTE__TAGS = TITLED_NAMED_CLASS_MODEL_ELEMENT__TAGS;

	/**
	 * The feature id for the '<em><b>Comments</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ATTRIBUTE__COMMENTS = TITLED_NAMED_CLASS_MODEL_ELEMENT__COMMENTS;

	/**
	 * The feature id for the '<em><b>Documentation</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ATTRIBUTE__DOCUMENTATION = TITLED_NAMED_CLASS_MODEL_ELEMENT__DOCUMENTATION;

	/**
	 * The feature id for the '<em><b>Description</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ATTRIBUTE__DESCRIPTION = TITLED_NAMED_CLASS_MODEL_ELEMENT__DESCRIPTION;

	/**
	 * The feature id for the '<em><b>Metainfo</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ATTRIBUTE__METAINFO = TITLED_NAMED_CLASS_MODEL_ELEMENT__METAINFO;

	/**
	 * The feature id for the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ATTRIBUTE__NAME = TITLED_NAMED_CLASS_MODEL_ELEMENT__NAME;

	/**
	 * The feature id for the '<em><b>Has Comments</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ATTRIBUTE__HAS_COMMENTS = TITLED_NAMED_CLASS_MODEL_ELEMENT__HAS_COMMENTS;

	/**
	 * The feature id for the '<em><b>Title</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ATTRIBUTE__TITLE = TITLED_NAMED_CLASS_MODEL_ELEMENT__TITLE;

	/**
	 * The feature id for the '<em><b>Typ</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ATTRIBUTE__TYP = TITLED_NAMED_CLASS_MODEL_ELEMENT_FEATURE_COUNT + 0;

	/**
	 * The feature id for the '<em><b>Initial Value</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ATTRIBUTE__INITIAL_VALUE = TITLED_NAMED_CLASS_MODEL_ELEMENT_FEATURE_COUNT + 1;

	/**
	 * The feature id for the '<em><b>Visibility</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ATTRIBUTE__VISIBILITY = TITLED_NAMED_CLASS_MODEL_ELEMENT_FEATURE_COUNT + 2;

	/**
	 * The feature id for the '<em><b>Value List</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ATTRIBUTE__VALUE_LIST = TITLED_NAMED_CLASS_MODEL_ELEMENT_FEATURE_COUNT + 3;

	/**
	 * The feature id for the '<em><b>Unique</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ATTRIBUTE__UNIQUE = TITLED_NAMED_CLASS_MODEL_ELEMENT_FEATURE_COUNT + 4;

	/**
	 * The feature id for the '<em><b>Mockup</b></em>' attribute list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ATTRIBUTE__MOCKUP = TITLED_NAMED_CLASS_MODEL_ELEMENT_FEATURE_COUNT + 5;

	/**
	 * The number of structural features of the '<em>Attribute</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ATTRIBUTE_FEATURE_COUNT = TITLED_NAMED_CLASS_MODEL_ELEMENT_FEATURE_COUNT + 6;

	/**
	 * The meta object id for the '{@link com.bluexml.side.clazz.impl.EnumerationImpl <em>Enumeration</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see com.bluexml.side.clazz.impl.EnumerationImpl
	 * @see com.bluexml.side.clazz.impl.ClazzPackageImpl#getEnumeration()
	 * @generated
	 */
	int ENUMERATION = 5;

	/**
	 * The feature id for the '<em><b>Stereotypes</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ENUMERATION__STEREOTYPES = CommonPackage.NAMED_MODEL_ELEMENT__STEREOTYPES;

	/**
	 * The feature id for the '<em><b>Tags</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ENUMERATION__TAGS = CommonPackage.NAMED_MODEL_ELEMENT__TAGS;

	/**
	 * The feature id for the '<em><b>Comments</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ENUMERATION__COMMENTS = CommonPackage.NAMED_MODEL_ELEMENT__COMMENTS;

	/**
	 * The feature id for the '<em><b>Documentation</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ENUMERATION__DOCUMENTATION = CommonPackage.NAMED_MODEL_ELEMENT__DOCUMENTATION;

	/**
	 * The feature id for the '<em><b>Description</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ENUMERATION__DESCRIPTION = CommonPackage.NAMED_MODEL_ELEMENT__DESCRIPTION;

	/**
	 * The feature id for the '<em><b>Metainfo</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ENUMERATION__METAINFO = CommonPackage.NAMED_MODEL_ELEMENT__METAINFO;

	/**
	 * The feature id for the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ENUMERATION__NAME = CommonPackage.NAMED_MODEL_ELEMENT__NAME;

	/**
	 * The feature id for the '<em><b>Literals</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ENUMERATION__LITERALS = CommonPackage.NAMED_MODEL_ELEMENT_FEATURE_COUNT + 0;

	/**
	 * The feature id for the '<em><b>Dynamic</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ENUMERATION__DYNAMIC = CommonPackage.NAMED_MODEL_ELEMENT_FEATURE_COUNT + 1;

	/**
	 * The feature id for the '<em><b>Depends</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ENUMERATION__DEPENDS = CommonPackage.NAMED_MODEL_ELEMENT_FEATURE_COUNT + 2;

	/**
	 * The number of structural features of the '<em>Enumeration</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ENUMERATION_FEATURE_COUNT = CommonPackage.NAMED_MODEL_ELEMENT_FEATURE_COUNT + 3;

	/**
	 * The meta object id for the '{@link com.bluexml.side.clazz.impl.EnumerationLiteralImpl <em>Enumeration Literal</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see com.bluexml.side.clazz.impl.EnumerationLiteralImpl
	 * @see com.bluexml.side.clazz.impl.ClazzPackageImpl#getEnumerationLiteral()
	 * @generated
	 */
	int ENUMERATION_LITERAL = 6;

	/**
	 * The feature id for the '<em><b>Value</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ENUMERATION_LITERAL__VALUE = 0;

	/**
	 * The feature id for the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ENUMERATION_LITERAL__NAME = 1;

	/**
	 * The feature id for the '<em><b>Enum</b></em>' container reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ENUMERATION_LITERAL__ENUM = 2;

	/**
	 * The number of structural features of the '<em>Enumeration Literal</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ENUMERATION_LITERAL_FEATURE_COUNT = 3;

	/**
	 * The meta object id for the '{@link com.bluexml.side.clazz.impl.AspectImpl <em>Aspect</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see com.bluexml.side.clazz.impl.AspectImpl
	 * @see com.bluexml.side.clazz.impl.ClazzPackageImpl#getAspect()
	 * @generated
	 */
	int ASPECT = 7;

	/**
	 * The feature id for the '<em><b>Stereotypes</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ASPECT__STEREOTYPES = ABSTRACT_CLASS__STEREOTYPES;

	/**
	 * The feature id for the '<em><b>Tags</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ASPECT__TAGS = ABSTRACT_CLASS__TAGS;

	/**
	 * The feature id for the '<em><b>Comments</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ASPECT__COMMENTS = ABSTRACT_CLASS__COMMENTS;

	/**
	 * The feature id for the '<em><b>Documentation</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ASPECT__DOCUMENTATION = ABSTRACT_CLASS__DOCUMENTATION;

	/**
	 * The feature id for the '<em><b>Description</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ASPECT__DESCRIPTION = ABSTRACT_CLASS__DESCRIPTION;

	/**
	 * The feature id for the '<em><b>Metainfo</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ASPECT__METAINFO = ABSTRACT_CLASS__METAINFO;

	/**
	 * The feature id for the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ASPECT__NAME = ABSTRACT_CLASS__NAME;

	/**
	 * The feature id for the '<em><b>Has Comments</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ASPECT__HAS_COMMENTS = ABSTRACT_CLASS__HAS_COMMENTS;

	/**
	 * The feature id for the '<em><b>Title</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ASPECT__TITLE = ABSTRACT_CLASS__TITLE;

	/**
	 * The feature id for the '<em><b>Attributes</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ASPECT__ATTRIBUTES = ABSTRACT_CLASS__ATTRIBUTES;

	/**
	 * The feature id for the '<em><b>Generalizations</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ASPECT__GENERALIZATIONS = ABSTRACT_CLASS_FEATURE_COUNT + 0;

	/**
	 * The number of structural features of the '<em>Aspect</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ASPECT_FEATURE_COUNT = ABSTRACT_CLASS_FEATURE_COUNT + 1;

	/**
	 * The meta object id for the '{@link com.bluexml.side.clazz.impl.ClassCommentImpl <em>Class Comment</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see com.bluexml.side.clazz.impl.ClassCommentImpl
	 * @see com.bluexml.side.clazz.impl.ClazzPackageImpl#getClassComment()
	 * @generated
	 */
	int CLASS_COMMENT = 10;

	/**
	 * The feature id for the '<em><b>Stereotypes</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CLASS_COMMENT__STEREOTYPES = CommonPackage.COMMENT__STEREOTYPES;

	/**
	 * The feature id for the '<em><b>Tags</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CLASS_COMMENT__TAGS = CommonPackage.COMMENT__TAGS;

	/**
	 * The feature id for the '<em><b>Comments</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CLASS_COMMENT__COMMENTS = CommonPackage.COMMENT__COMMENTS;

	/**
	 * The feature id for the '<em><b>Documentation</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CLASS_COMMENT__DOCUMENTATION = CommonPackage.COMMENT__DOCUMENTATION;

	/**
	 * The feature id for the '<em><b>Description</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CLASS_COMMENT__DESCRIPTION = CommonPackage.COMMENT__DESCRIPTION;

	/**
	 * The feature id for the '<em><b>Metainfo</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CLASS_COMMENT__METAINFO = CommonPackage.COMMENT__METAINFO;

	/**
	 * The feature id for the '<em><b>Value</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CLASS_COMMENT__VALUE = CommonPackage.COMMENT__VALUE;

	/**
	 * The number of structural features of the '<em>Class Comment</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CLASS_COMMENT_FEATURE_COUNT = CommonPackage.COMMENT_FEATURE_COUNT + 0;

	/**
	 * The meta object id for the '{@link com.bluexml.side.clazz.impl.AssociationEndImpl <em>Association End</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see com.bluexml.side.clazz.impl.AssociationEndImpl
	 * @see com.bluexml.side.clazz.impl.ClazzPackageImpl#getAssociationEnd()
	 * @generated
	 */
	int ASSOCIATION_END = 11;

	/**
	 * The feature id for the '<em><b>Stereotypes</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ASSOCIATION_END__STEREOTYPES = TITLED_NAMED_CLASS_MODEL_ELEMENT__STEREOTYPES;

	/**
	 * The feature id for the '<em><b>Tags</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ASSOCIATION_END__TAGS = TITLED_NAMED_CLASS_MODEL_ELEMENT__TAGS;

	/**
	 * The feature id for the '<em><b>Comments</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ASSOCIATION_END__COMMENTS = TITLED_NAMED_CLASS_MODEL_ELEMENT__COMMENTS;

	/**
	 * The feature id for the '<em><b>Documentation</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ASSOCIATION_END__DOCUMENTATION = TITLED_NAMED_CLASS_MODEL_ELEMENT__DOCUMENTATION;

	/**
	 * The feature id for the '<em><b>Description</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ASSOCIATION_END__DESCRIPTION = TITLED_NAMED_CLASS_MODEL_ELEMENT__DESCRIPTION;

	/**
	 * The feature id for the '<em><b>Metainfo</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ASSOCIATION_END__METAINFO = TITLED_NAMED_CLASS_MODEL_ELEMENT__METAINFO;

	/**
	 * The feature id for the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ASSOCIATION_END__NAME = TITLED_NAMED_CLASS_MODEL_ELEMENT__NAME;

	/**
	 * The feature id for the '<em><b>Has Comments</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ASSOCIATION_END__HAS_COMMENTS = TITLED_NAMED_CLASS_MODEL_ELEMENT__HAS_COMMENTS;

	/**
	 * The feature id for the '<em><b>Title</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ASSOCIATION_END__TITLE = TITLED_NAMED_CLASS_MODEL_ELEMENT__TITLE;

	/**
	 * The feature id for the '<em><b>Value</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ASSOCIATION_END__VALUE = TITLED_NAMED_CLASS_MODEL_ELEMENT_FEATURE_COUNT + 0;

	/**
	 * The feature id for the '<em><b>Card Min</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ASSOCIATION_END__CARD_MIN = TITLED_NAMED_CLASS_MODEL_ELEMENT_FEATURE_COUNT + 1;

	/**
	 * The feature id for the '<em><b>Card Max</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ASSOCIATION_END__CARD_MAX = TITLED_NAMED_CLASS_MODEL_ELEMENT_FEATURE_COUNT + 2;

	/**
	 * The feature id for the '<em><b>Navigable</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ASSOCIATION_END__NAVIGABLE = TITLED_NAMED_CLASS_MODEL_ELEMENT_FEATURE_COUNT + 3;

	/**
	 * The feature id for the '<em><b>Linked Class</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ASSOCIATION_END__LINKED_CLASS = TITLED_NAMED_CLASS_MODEL_ELEMENT_FEATURE_COUNT + 4;

	/**
	 * The number of structural features of the '<em>Association End</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ASSOCIATION_END_FEATURE_COUNT = TITLED_NAMED_CLASS_MODEL_ELEMENT_FEATURE_COUNT + 5;

	/**
	 * The meta object id for the '{@link com.bluexml.side.clazz.impl.ModelImpl <em>Model</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see com.bluexml.side.clazz.impl.ModelImpl
	 * @see com.bluexml.side.clazz.impl.ClazzPackageImpl#getModel()
	 * @generated
	 */
	int MODEL = 12;

	/**
	 * The feature id for the '<em><b>Stereotypes</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int MODEL__STEREOTYPES = CLASS_PACKAGE__STEREOTYPES;

	/**
	 * The feature id for the '<em><b>Tags</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int MODEL__TAGS = CLASS_PACKAGE__TAGS;

	/**
	 * The feature id for the '<em><b>Comments</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int MODEL__COMMENTS = CLASS_PACKAGE__COMMENTS;

	/**
	 * The feature id for the '<em><b>Documentation</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int MODEL__DOCUMENTATION = CLASS_PACKAGE__DOCUMENTATION;

	/**
	 * The feature id for the '<em><b>Description</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int MODEL__DESCRIPTION = CLASS_PACKAGE__DESCRIPTION;

	/**
	 * The feature id for the '<em><b>Metainfo</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int MODEL__METAINFO = CLASS_PACKAGE__METAINFO;

	/**
	 * The feature id for the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int MODEL__NAME = CLASS_PACKAGE__NAME;

	/**
	 * The feature id for the '<em><b>Stereotype Set</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int MODEL__STEREOTYPE_SET = CLASS_PACKAGE__STEREOTYPE_SET;

	/**
	 * The feature id for the '<em><b>Package Set</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int MODEL__PACKAGE_SET = CLASS_PACKAGE__PACKAGE_SET;

	/**
	 * The feature id for the '<em><b>Class Set</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int MODEL__CLASS_SET = CLASS_PACKAGE__CLASS_SET;

	/**
	 * The feature id for the '<em><b>Association Set</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int MODEL__ASSOCIATION_SET = CLASS_PACKAGE__ASSOCIATION_SET;

	/**
	 * The feature id for the '<em><b>Aspect Set</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int MODEL__ASPECT_SET = CLASS_PACKAGE__ASPECT_SET;

	/**
	 * The feature id for the '<em><b>Enumeration Set</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int MODEL__ENUMERATION_SET = CLASS_PACKAGE__ENUMERATION_SET;

	/**
	 * The number of structural features of the '<em>Model</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int MODEL_FEATURE_COUNT = CLASS_PACKAGE_FEATURE_COUNT + 0;

	/**
	 * The meta object id for the '{@link com.bluexml.side.clazz.AssociationType <em>Association Type</em>}' enum.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see com.bluexml.side.clazz.AssociationType
	 * @see com.bluexml.side.clazz.impl.ClazzPackageImpl#getAssociationType()
	 * @generated
	 */
	int ASSOCIATION_TYPE = 13;


	/**
	 * Returns the meta object for class '{@link com.bluexml.side.clazz.ClassModelElement <em>Class Model Element</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Class Model Element</em>'.
	 * @see com.bluexml.side.clazz.ClassModelElement
	 * @generated
	 */
	EClass getClassModelElement();

	/**
	 * Returns the meta object for the containment reference list '{@link com.bluexml.side.clazz.ClassModelElement#getHasComments <em>Has Comments</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference list '<em>Has Comments</em>'.
	 * @see com.bluexml.side.clazz.ClassModelElement#getHasComments()
	 * @see #getClassModelElement()
	 * @generated
	 */
	EReference getClassModelElement_HasComments();

	/**
	 * Returns the meta object for class '{@link com.bluexml.side.clazz.ClassPackage <em>Class Package</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Class Package</em>'.
	 * @see com.bluexml.side.clazz.ClassPackage
	 * @generated
	 */
	EClass getClassPackage();

	/**
	 * Returns the meta object for the containment reference list '{@link com.bluexml.side.clazz.ClassPackage#getClassSet <em>Class Set</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference list '<em>Class Set</em>'.
	 * @see com.bluexml.side.clazz.ClassPackage#getClassSet()
	 * @see #getClassPackage()
	 * @generated
	 */
	EReference getClassPackage_ClassSet();

	/**
	 * Returns the meta object for the containment reference list '{@link com.bluexml.side.clazz.ClassPackage#getAssociationSet <em>Association Set</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference list '<em>Association Set</em>'.
	 * @see com.bluexml.side.clazz.ClassPackage#getAssociationSet()
	 * @see #getClassPackage()
	 * @generated
	 */
	EReference getClassPackage_AssociationSet();

	/**
	 * Returns the meta object for the containment reference list '{@link com.bluexml.side.clazz.ClassPackage#getAspectSet <em>Aspect Set</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference list '<em>Aspect Set</em>'.
	 * @see com.bluexml.side.clazz.ClassPackage#getAspectSet()
	 * @see #getClassPackage()
	 * @generated
	 */
	EReference getClassPackage_AspectSet();

	/**
	 * Returns the meta object for the containment reference list '{@link com.bluexml.side.clazz.ClassPackage#getEnumerationSet <em>Enumeration Set</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference list '<em>Enumeration Set</em>'.
	 * @see com.bluexml.side.clazz.ClassPackage#getEnumerationSet()
	 * @see #getClassPackage()
	 * @generated
	 */
	EReference getClassPackage_EnumerationSet();

	/**
	 * Returns the meta object for class '{@link com.bluexml.side.clazz.Clazz <em>Clazz</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Clazz</em>'.
	 * @see com.bluexml.side.clazz.Clazz
	 * @generated
	 */
	EClass getClazz();

	/**
	 * Returns the meta object for the containment reference list '{@link com.bluexml.side.clazz.Clazz#getOperations <em>Operations</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference list '<em>Operations</em>'.
	 * @see com.bluexml.side.clazz.Clazz#getOperations()
	 * @see #getClazz()
	 * @generated
	 */
	EReference getClazz_Operations();

	/**
	 * Returns the meta object for the reference list '{@link com.bluexml.side.clazz.Clazz#getGeneralizations <em>Generalizations</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference list '<em>Generalizations</em>'.
	 * @see com.bluexml.side.clazz.Clazz#getGeneralizations()
	 * @see #getClazz()
	 * @generated
	 */
	EReference getClazz_Generalizations();

	/**
	 * Returns the meta object for the reference list '{@link com.bluexml.side.clazz.Clazz#getAspects <em>Aspects</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference list '<em>Aspects</em>'.
	 * @see com.bluexml.side.clazz.Clazz#getAspects()
	 * @see #getClazz()
	 * @generated
	 */
	EReference getClazz_Aspects();

	/**
	 * Returns the meta object for the attribute '{@link com.bluexml.side.clazz.Clazz#isAbstract <em>Abstract</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Abstract</em>'.
	 * @see com.bluexml.side.clazz.Clazz#isAbstract()
	 * @see #getClazz()
	 * @generated
	 */
	EAttribute getClazz_Abstract();

	/**
	 * Returns the meta object for the attribute '{@link com.bluexml.side.clazz.Clazz#isDeprecated <em>Deprecated</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Deprecated</em>'.
	 * @see com.bluexml.side.clazz.Clazz#isDeprecated()
	 * @see #getClazz()
	 * @generated
	 */
	EAttribute getClazz_Deprecated();

	/**
	 * Returns the meta object for class '{@link com.bluexml.side.clazz.Association <em>Association</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Association</em>'.
	 * @see com.bluexml.side.clazz.Association
	 * @generated
	 */
	EClass getAssociation();

	/**
	 * Returns the meta object for the attribute '{@link com.bluexml.side.clazz.Association#getAssociationType <em>Association Type</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Association Type</em>'.
	 * @see com.bluexml.side.clazz.Association#getAssociationType()
	 * @see #getAssociation()
	 * @generated
	 */
	EAttribute getAssociation_AssociationType();

	/**
	 * Returns the meta object for the containment reference '{@link com.bluexml.side.clazz.Association#getFirstEnd <em>First End</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference '<em>First End</em>'.
	 * @see com.bluexml.side.clazz.Association#getFirstEnd()
	 * @see #getAssociation()
	 * @generated
	 */
	EReference getAssociation_FirstEnd();

	/**
	 * Returns the meta object for the containment reference '{@link com.bluexml.side.clazz.Association#getSecondEnd <em>Second End</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference '<em>Second End</em>'.
	 * @see com.bluexml.side.clazz.Association#getSecondEnd()
	 * @see #getAssociation()
	 * @generated
	 */
	EReference getAssociation_SecondEnd();

	/**
	 * Returns the meta object for the attribute '{@link com.bluexml.side.clazz.Association#isOrdered <em>Ordered</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Ordered</em>'.
	 * @see com.bluexml.side.clazz.Association#isOrdered()
	 * @see #getAssociation()
	 * @generated
	 */
	EAttribute getAssociation_Ordered();

	/**
	 * Returns the meta object for class '{@link com.bluexml.side.clazz.Attribute <em>Attribute</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Attribute</em>'.
	 * @see com.bluexml.side.clazz.Attribute
	 * @generated
	 */
	EClass getAttribute();

	/**
	 * Returns the meta object for the attribute '{@link com.bluexml.side.clazz.Attribute#getTyp <em>Typ</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Typ</em>'.
	 * @see com.bluexml.side.clazz.Attribute#getTyp()
	 * @see #getAttribute()
	 * @generated
	 */
	EAttribute getAttribute_Typ();

	/**
	 * Returns the meta object for the attribute '{@link com.bluexml.side.clazz.Attribute#getInitialValue <em>Initial Value</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Initial Value</em>'.
	 * @see com.bluexml.side.clazz.Attribute#getInitialValue()
	 * @see #getAttribute()
	 * @generated
	 */
	EAttribute getAttribute_InitialValue();

	/**
	 * Returns the meta object for the attribute '{@link com.bluexml.side.clazz.Attribute#getVisibility <em>Visibility</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Visibility</em>'.
	 * @see com.bluexml.side.clazz.Attribute#getVisibility()
	 * @see #getAttribute()
	 * @generated
	 */
	EAttribute getAttribute_Visibility();

	/**
	 * Returns the meta object for the reference '{@link com.bluexml.side.clazz.Attribute#getValueList <em>Value List</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference '<em>Value List</em>'.
	 * @see com.bluexml.side.clazz.Attribute#getValueList()
	 * @see #getAttribute()
	 * @generated
	 */
	EReference getAttribute_ValueList();

	/**
	 * Returns the meta object for the attribute '{@link com.bluexml.side.clazz.Attribute#isUnique <em>Unique</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Unique</em>'.
	 * @see com.bluexml.side.clazz.Attribute#isUnique()
	 * @see #getAttribute()
	 * @generated
	 */
	EAttribute getAttribute_Unique();

	/**
	 * Returns the meta object for the attribute list '{@link com.bluexml.side.clazz.Attribute#getMockup <em>Mockup</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute list '<em>Mockup</em>'.
	 * @see com.bluexml.side.clazz.Attribute#getMockup()
	 * @see #getAttribute()
	 * @generated
	 */
	EAttribute getAttribute_Mockup();

	/**
	 * Returns the meta object for class '{@link com.bluexml.side.clazz.Enumeration <em>Enumeration</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Enumeration</em>'.
	 * @see com.bluexml.side.clazz.Enumeration
	 * @generated
	 */
	EClass getEnumeration();

	/**
	 * Returns the meta object for the containment reference list '{@link com.bluexml.side.clazz.Enumeration#getLiterals <em>Literals</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference list '<em>Literals</em>'.
	 * @see com.bluexml.side.clazz.Enumeration#getLiterals()
	 * @see #getEnumeration()
	 * @generated
	 */
	EReference getEnumeration_Literals();

	/**
	 * Returns the meta object for the attribute '{@link com.bluexml.side.clazz.Enumeration#getDynamic <em>Dynamic</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Dynamic</em>'.
	 * @see com.bluexml.side.clazz.Enumeration#getDynamic()
	 * @see #getEnumeration()
	 * @generated
	 */
	EAttribute getEnumeration_Dynamic();

	/**
	 * Returns the meta object for the reference '{@link com.bluexml.side.clazz.Enumeration#getDepends <em>Depends</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference '<em>Depends</em>'.
	 * @see com.bluexml.side.clazz.Enumeration#getDepends()
	 * @see #getEnumeration()
	 * @generated
	 */
	EReference getEnumeration_Depends();

	/**
	 * Returns the meta object for class '{@link com.bluexml.side.clazz.EnumerationLiteral <em>Enumeration Literal</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Enumeration Literal</em>'.
	 * @see com.bluexml.side.clazz.EnumerationLiteral
	 * @generated
	 */
	EClass getEnumerationLiteral();

	/**
	 * Returns the meta object for the attribute '{@link com.bluexml.side.clazz.EnumerationLiteral#getValue <em>Value</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Value</em>'.
	 * @see com.bluexml.side.clazz.EnumerationLiteral#getValue()
	 * @see #getEnumerationLiteral()
	 * @generated
	 */
	EAttribute getEnumerationLiteral_Value();

	/**
	 * Returns the meta object for the attribute '{@link com.bluexml.side.clazz.EnumerationLiteral#getName <em>Name</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Name</em>'.
	 * @see com.bluexml.side.clazz.EnumerationLiteral#getName()
	 * @see #getEnumerationLiteral()
	 * @generated
	 */
	EAttribute getEnumerationLiteral_Name();

	/**
	 * Returns the meta object for the container reference '{@link com.bluexml.side.clazz.EnumerationLiteral#getEnum <em>Enum</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the container reference '<em>Enum</em>'.
	 * @see com.bluexml.side.clazz.EnumerationLiteral#getEnum()
	 * @see #getEnumerationLiteral()
	 * @generated
	 */
	EReference getEnumerationLiteral_Enum();

	/**
	 * Returns the meta object for class '{@link com.bluexml.side.clazz.Aspect <em>Aspect</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Aspect</em>'.
	 * @see com.bluexml.side.clazz.Aspect
	 * @generated
	 */
	EClass getAspect();

	/**
	 * Returns the meta object for the reference list '{@link com.bluexml.side.clazz.Aspect#getGeneralizations <em>Generalizations</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference list '<em>Generalizations</em>'.
	 * @see com.bluexml.side.clazz.Aspect#getGeneralizations()
	 * @see #getAspect()
	 * @generated
	 */
	EReference getAspect_Generalizations();

	/**
	 * Returns the meta object for class '{@link com.bluexml.side.clazz.AbstractClass <em>Abstract Class</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Abstract Class</em>'.
	 * @see com.bluexml.side.clazz.AbstractClass
	 * @generated
	 */
	EClass getAbstractClass();

	/**
	 * Returns the meta object for the containment reference list '{@link com.bluexml.side.clazz.AbstractClass#getAttributes <em>Attributes</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference list '<em>Attributes</em>'.
	 * @see com.bluexml.side.clazz.AbstractClass#getAttributes()
	 * @see #getAbstractClass()
	 * @generated
	 */
	EReference getAbstractClass_Attributes();

	/**
	 * Returns the meta object for class '{@link com.bluexml.side.clazz.TitledNamedClassModelElement <em>Titled Named Class Model Element</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Titled Named Class Model Element</em>'.
	 * @see com.bluexml.side.clazz.TitledNamedClassModelElement
	 * @generated
	 */
	EClass getTitledNamedClassModelElement();

	/**
	 * Returns the meta object for the attribute '{@link com.bluexml.side.clazz.TitledNamedClassModelElement#getTitle <em>Title</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Title</em>'.
	 * @see com.bluexml.side.clazz.TitledNamedClassModelElement#getTitle()
	 * @see #getTitledNamedClassModelElement()
	 * @generated
	 */
	EAttribute getTitledNamedClassModelElement_Title();

	/**
	 * Returns the meta object for class '{@link com.bluexml.side.clazz.ClassComment <em>Class Comment</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Class Comment</em>'.
	 * @see com.bluexml.side.clazz.ClassComment
	 * @generated
	 */
	EClass getClassComment();

	/**
	 * Returns the meta object for class '{@link com.bluexml.side.clazz.AssociationEnd <em>Association End</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Association End</em>'.
	 * @see com.bluexml.side.clazz.AssociationEnd
	 * @generated
	 */
	EClass getAssociationEnd();

	/**
	 * Returns the meta object for the attribute '{@link com.bluexml.side.clazz.AssociationEnd#getCardMin <em>Card Min</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Card Min</em>'.
	 * @see com.bluexml.side.clazz.AssociationEnd#getCardMin()
	 * @see #getAssociationEnd()
	 * @generated
	 */
	EAttribute getAssociationEnd_CardMin();

	/**
	 * Returns the meta object for the attribute '{@link com.bluexml.side.clazz.AssociationEnd#getCardMax <em>Card Max</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Card Max</em>'.
	 * @see com.bluexml.side.clazz.AssociationEnd#getCardMax()
	 * @see #getAssociationEnd()
	 * @generated
	 */
	EAttribute getAssociationEnd_CardMax();

	/**
	 * Returns the meta object for the attribute '{@link com.bluexml.side.clazz.AssociationEnd#isNavigable <em>Navigable</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Navigable</em>'.
	 * @see com.bluexml.side.clazz.AssociationEnd#isNavigable()
	 * @see #getAssociationEnd()
	 * @generated
	 */
	EAttribute getAssociationEnd_Navigable();

	/**
	 * Returns the meta object for the reference '{@link com.bluexml.side.clazz.AssociationEnd#getLinkedClass <em>Linked Class</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference '<em>Linked Class</em>'.
	 * @see com.bluexml.side.clazz.AssociationEnd#getLinkedClass()
	 * @see #getAssociationEnd()
	 * @generated
	 */
	EReference getAssociationEnd_LinkedClass();

	/**
	 * Returns the meta object for class '{@link com.bluexml.side.clazz.Model <em>Model</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Model</em>'.
	 * @see com.bluexml.side.clazz.Model
	 * @generated
	 */
	EClass getModel();

	/**
	 * Returns the meta object for enum '{@link com.bluexml.side.clazz.AssociationType <em>Association Type</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for enum '<em>Association Type</em>'.
	 * @see com.bluexml.side.clazz.AssociationType
	 * @generated
	 */
	EEnum getAssociationType();

	/**
	 * Returns the factory that creates the instances of the model.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the factory that creates the instances of the model.
	 * @generated
	 */
	ClazzFactory getClazzFactory();

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
		 * The meta object literal for the '{@link com.bluexml.side.clazz.impl.ClassModelElementImpl <em>Class Model Element</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see com.bluexml.side.clazz.impl.ClassModelElementImpl
		 * @see com.bluexml.side.clazz.impl.ClazzPackageImpl#getClassModelElement()
		 * @generated
		 */
		EClass CLASS_MODEL_ELEMENT = eINSTANCE.getClassModelElement();

		/**
		 * The meta object literal for the '<em><b>Has Comments</b></em>' containment reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference CLASS_MODEL_ELEMENT__HAS_COMMENTS = eINSTANCE.getClassModelElement_HasComments();

		/**
		 * The meta object literal for the '{@link com.bluexml.side.clazz.impl.ClassPackageImpl <em>Class Package</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see com.bluexml.side.clazz.impl.ClassPackageImpl
		 * @see com.bluexml.side.clazz.impl.ClazzPackageImpl#getClassPackage()
		 * @generated
		 */
		EClass CLASS_PACKAGE = eINSTANCE.getClassPackage();

		/**
		 * The meta object literal for the '<em><b>Class Set</b></em>' containment reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference CLASS_PACKAGE__CLASS_SET = eINSTANCE.getClassPackage_ClassSet();

		/**
		 * The meta object literal for the '<em><b>Association Set</b></em>' containment reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference CLASS_PACKAGE__ASSOCIATION_SET = eINSTANCE.getClassPackage_AssociationSet();

		/**
		 * The meta object literal for the '<em><b>Aspect Set</b></em>' containment reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference CLASS_PACKAGE__ASPECT_SET = eINSTANCE.getClassPackage_AspectSet();

		/**
		 * The meta object literal for the '<em><b>Enumeration Set</b></em>' containment reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference CLASS_PACKAGE__ENUMERATION_SET = eINSTANCE.getClassPackage_EnumerationSet();

		/**
		 * The meta object literal for the '{@link com.bluexml.side.clazz.impl.ClazzImpl <em>Clazz</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see com.bluexml.side.clazz.impl.ClazzImpl
		 * @see com.bluexml.side.clazz.impl.ClazzPackageImpl#getClazz()
		 * @generated
		 */
		EClass CLAZZ = eINSTANCE.getClazz();

		/**
		 * The meta object literal for the '<em><b>Operations</b></em>' containment reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference CLAZZ__OPERATIONS = eINSTANCE.getClazz_Operations();

		/**
		 * The meta object literal for the '<em><b>Generalizations</b></em>' reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference CLAZZ__GENERALIZATIONS = eINSTANCE.getClazz_Generalizations();

		/**
		 * The meta object literal for the '<em><b>Aspects</b></em>' reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference CLAZZ__ASPECTS = eINSTANCE.getClazz_Aspects();

		/**
		 * The meta object literal for the '<em><b>Abstract</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute CLAZZ__ABSTRACT = eINSTANCE.getClazz_Abstract();

		/**
		 * The meta object literal for the '<em><b>Deprecated</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute CLAZZ__DEPRECATED = eINSTANCE.getClazz_Deprecated();

		/**
		 * The meta object literal for the '{@link com.bluexml.side.clazz.impl.AssociationImpl <em>Association</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see com.bluexml.side.clazz.impl.AssociationImpl
		 * @see com.bluexml.side.clazz.impl.ClazzPackageImpl#getAssociation()
		 * @generated
		 */
		EClass ASSOCIATION = eINSTANCE.getAssociation();

		/**
		 * The meta object literal for the '<em><b>Association Type</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute ASSOCIATION__ASSOCIATION_TYPE = eINSTANCE.getAssociation_AssociationType();

		/**
		 * The meta object literal for the '<em><b>First End</b></em>' containment reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference ASSOCIATION__FIRST_END = eINSTANCE.getAssociation_FirstEnd();

		/**
		 * The meta object literal for the '<em><b>Second End</b></em>' containment reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference ASSOCIATION__SECOND_END = eINSTANCE.getAssociation_SecondEnd();

		/**
		 * The meta object literal for the '<em><b>Ordered</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute ASSOCIATION__ORDERED = eINSTANCE.getAssociation_Ordered();

		/**
		 * The meta object literal for the '{@link com.bluexml.side.clazz.impl.AttributeImpl <em>Attribute</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see com.bluexml.side.clazz.impl.AttributeImpl
		 * @see com.bluexml.side.clazz.impl.ClazzPackageImpl#getAttribute()
		 * @generated
		 */
		EClass ATTRIBUTE = eINSTANCE.getAttribute();

		/**
		 * The meta object literal for the '<em><b>Typ</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute ATTRIBUTE__TYP = eINSTANCE.getAttribute_Typ();

		/**
		 * The meta object literal for the '<em><b>Initial Value</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute ATTRIBUTE__INITIAL_VALUE = eINSTANCE.getAttribute_InitialValue();

		/**
		 * The meta object literal for the '<em><b>Visibility</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute ATTRIBUTE__VISIBILITY = eINSTANCE.getAttribute_Visibility();

		/**
		 * The meta object literal for the '<em><b>Value List</b></em>' reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference ATTRIBUTE__VALUE_LIST = eINSTANCE.getAttribute_ValueList();

		/**
		 * The meta object literal for the '<em><b>Unique</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute ATTRIBUTE__UNIQUE = eINSTANCE.getAttribute_Unique();

		/**
		 * The meta object literal for the '<em><b>Mockup</b></em>' attribute list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute ATTRIBUTE__MOCKUP = eINSTANCE.getAttribute_Mockup();

		/**
		 * The meta object literal for the '{@link com.bluexml.side.clazz.impl.EnumerationImpl <em>Enumeration</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see com.bluexml.side.clazz.impl.EnumerationImpl
		 * @see com.bluexml.side.clazz.impl.ClazzPackageImpl#getEnumeration()
		 * @generated
		 */
		EClass ENUMERATION = eINSTANCE.getEnumeration();

		/**
		 * The meta object literal for the '<em><b>Literals</b></em>' containment reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference ENUMERATION__LITERALS = eINSTANCE.getEnumeration_Literals();

		/**
		 * The meta object literal for the '<em><b>Dynamic</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute ENUMERATION__DYNAMIC = eINSTANCE.getEnumeration_Dynamic();

		/**
		 * The meta object literal for the '<em><b>Depends</b></em>' reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference ENUMERATION__DEPENDS = eINSTANCE.getEnumeration_Depends();

		/**
		 * The meta object literal for the '{@link com.bluexml.side.clazz.impl.EnumerationLiteralImpl <em>Enumeration Literal</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see com.bluexml.side.clazz.impl.EnumerationLiteralImpl
		 * @see com.bluexml.side.clazz.impl.ClazzPackageImpl#getEnumerationLiteral()
		 * @generated
		 */
		EClass ENUMERATION_LITERAL = eINSTANCE.getEnumerationLiteral();

		/**
		 * The meta object literal for the '<em><b>Value</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute ENUMERATION_LITERAL__VALUE = eINSTANCE.getEnumerationLiteral_Value();

		/**
		 * The meta object literal for the '<em><b>Name</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute ENUMERATION_LITERAL__NAME = eINSTANCE.getEnumerationLiteral_Name();

		/**
		 * The meta object literal for the '<em><b>Enum</b></em>' container reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference ENUMERATION_LITERAL__ENUM = eINSTANCE.getEnumerationLiteral_Enum();

		/**
		 * The meta object literal for the '{@link com.bluexml.side.clazz.impl.AspectImpl <em>Aspect</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see com.bluexml.side.clazz.impl.AspectImpl
		 * @see com.bluexml.side.clazz.impl.ClazzPackageImpl#getAspect()
		 * @generated
		 */
		EClass ASPECT = eINSTANCE.getAspect();

		/**
		 * The meta object literal for the '<em><b>Generalizations</b></em>' reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference ASPECT__GENERALIZATIONS = eINSTANCE.getAspect_Generalizations();

		/**
		 * The meta object literal for the '{@link com.bluexml.side.clazz.impl.AbstractClassImpl <em>Abstract Class</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see com.bluexml.side.clazz.impl.AbstractClassImpl
		 * @see com.bluexml.side.clazz.impl.ClazzPackageImpl#getAbstractClass()
		 * @generated
		 */
		EClass ABSTRACT_CLASS = eINSTANCE.getAbstractClass();

		/**
		 * The meta object literal for the '<em><b>Attributes</b></em>' containment reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference ABSTRACT_CLASS__ATTRIBUTES = eINSTANCE.getAbstractClass_Attributes();

		/**
		 * The meta object literal for the '{@link com.bluexml.side.clazz.impl.TitledNamedClassModelElementImpl <em>Titled Named Class Model Element</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see com.bluexml.side.clazz.impl.TitledNamedClassModelElementImpl
		 * @see com.bluexml.side.clazz.impl.ClazzPackageImpl#getTitledNamedClassModelElement()
		 * @generated
		 */
		EClass TITLED_NAMED_CLASS_MODEL_ELEMENT = eINSTANCE.getTitledNamedClassModelElement();

		/**
		 * The meta object literal for the '<em><b>Title</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute TITLED_NAMED_CLASS_MODEL_ELEMENT__TITLE = eINSTANCE.getTitledNamedClassModelElement_Title();

		/**
		 * The meta object literal for the '{@link com.bluexml.side.clazz.impl.ClassCommentImpl <em>Class Comment</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see com.bluexml.side.clazz.impl.ClassCommentImpl
		 * @see com.bluexml.side.clazz.impl.ClazzPackageImpl#getClassComment()
		 * @generated
		 */
		EClass CLASS_COMMENT = eINSTANCE.getClassComment();

		/**
		 * The meta object literal for the '{@link com.bluexml.side.clazz.impl.AssociationEndImpl <em>Association End</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see com.bluexml.side.clazz.impl.AssociationEndImpl
		 * @see com.bluexml.side.clazz.impl.ClazzPackageImpl#getAssociationEnd()
		 * @generated
		 */
		EClass ASSOCIATION_END = eINSTANCE.getAssociationEnd();

		/**
		 * The meta object literal for the '<em><b>Card Min</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute ASSOCIATION_END__CARD_MIN = eINSTANCE.getAssociationEnd_CardMin();

		/**
		 * The meta object literal for the '<em><b>Card Max</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute ASSOCIATION_END__CARD_MAX = eINSTANCE.getAssociationEnd_CardMax();

		/**
		 * The meta object literal for the '<em><b>Navigable</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute ASSOCIATION_END__NAVIGABLE = eINSTANCE.getAssociationEnd_Navigable();

		/**
		 * The meta object literal for the '<em><b>Linked Class</b></em>' reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference ASSOCIATION_END__LINKED_CLASS = eINSTANCE.getAssociationEnd_LinkedClass();

		/**
		 * The meta object literal for the '{@link com.bluexml.side.clazz.impl.ModelImpl <em>Model</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see com.bluexml.side.clazz.impl.ModelImpl
		 * @see com.bluexml.side.clazz.impl.ClazzPackageImpl#getModel()
		 * @generated
		 */
		EClass MODEL = eINSTANCE.getModel();

		/**
		 * The meta object literal for the '{@link com.bluexml.side.clazz.AssociationType <em>Association Type</em>}' enum.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see com.bluexml.side.clazz.AssociationType
		 * @see com.bluexml.side.clazz.impl.ClazzPackageImpl#getAssociationType()
		 * @generated
		 */
		EEnum ASSOCIATION_TYPE = eINSTANCE.getAssociationType();

	}

} //ClazzPackage
