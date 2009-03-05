/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package com.bluexml.side.common;

import org.eclipse.emf.ecore.EAttribute;
import org.eclipse.emf.ecore.EClass;
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
 * @see com.bluexml.side.common.CommonFactory
 * @model kind="package"
 * @generated
 */
public interface CommonPackage extends EPackage {
	/**
	 * The package name.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	String eNAME = "common";

	/**
	 * The package namespace URI.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	String eNS_URI = "http://www.kerblue.org/common/1.0/";

	/**
	 * The package namespace name.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	String eNS_PREFIX = "common";

	/**
	 * The singleton instance of the package.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	CommonPackage eINSTANCE = com.bluexml.side.common.impl.CommonPackageImpl.init();

	/**
	 * The meta object id for the '{@link com.bluexml.side.common.impl.ModelElementImpl <em>Model Element</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see com.bluexml.side.common.impl.ModelElementImpl
	 * @see com.bluexml.side.common.impl.CommonPackageImpl#getModelElement()
	 * @generated
	 */
	int MODEL_ELEMENT = 0;

	/**
	 * The feature id for the '<em><b>Stereotypes</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int MODEL_ELEMENT__STEREOTYPES = 0;

	/**
	 * The feature id for the '<em><b>Tags</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int MODEL_ELEMENT__TAGS = 1;

	/**
	 * The feature id for the '<em><b>Comments</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int MODEL_ELEMENT__COMMENTS = 2;

	/**
	 * The number of structural features of the '<em>Model Element</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int MODEL_ELEMENT_FEATURE_COUNT = 3;

	/**
	 * The meta object id for the '{@link com.bluexml.side.common.impl.NamedModelElementImpl <em>Named Model Element</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see com.bluexml.side.common.impl.NamedModelElementImpl
	 * @see com.bluexml.side.common.impl.CommonPackageImpl#getNamedModelElement()
	 * @generated
	 */
	int NAMED_MODEL_ELEMENT = 1;

	/**
	 * The feature id for the '<em><b>Stereotypes</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int NAMED_MODEL_ELEMENT__STEREOTYPES = MODEL_ELEMENT__STEREOTYPES;

	/**
	 * The feature id for the '<em><b>Tags</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int NAMED_MODEL_ELEMENT__TAGS = MODEL_ELEMENT__TAGS;

	/**
	 * The feature id for the '<em><b>Comments</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int NAMED_MODEL_ELEMENT__COMMENTS = MODEL_ELEMENT__COMMENTS;

	/**
	 * The feature id for the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int NAMED_MODEL_ELEMENT__NAME = MODEL_ELEMENT_FEATURE_COUNT + 0;

	/**
	 * The feature id for the '<em><b>Documentation</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int NAMED_MODEL_ELEMENT__DOCUMENTATION = MODEL_ELEMENT_FEATURE_COUNT + 1;

	/**
	 * The number of structural features of the '<em>Named Model Element</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int NAMED_MODEL_ELEMENT_FEATURE_COUNT = MODEL_ELEMENT_FEATURE_COUNT + 2;

	/**
	 * The meta object id for the '{@link com.bluexml.side.common.impl.CommentImpl <em>Comment</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see com.bluexml.side.common.impl.CommentImpl
	 * @see com.bluexml.side.common.impl.CommonPackageImpl#getComment()
	 * @generated
	 */
	int COMMENT = 2;

	/**
	 * The feature id for the '<em><b>Stereotypes</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int COMMENT__STEREOTYPES = MODEL_ELEMENT__STEREOTYPES;

	/**
	 * The feature id for the '<em><b>Tags</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int COMMENT__TAGS = MODEL_ELEMENT__TAGS;

	/**
	 * The feature id for the '<em><b>Comments</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int COMMENT__COMMENTS = MODEL_ELEMENT__COMMENTS;

	/**
	 * The feature id for the '<em><b>Value</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int COMMENT__VALUE = MODEL_ELEMENT_FEATURE_COUNT + 0;

	/**
	 * The number of structural features of the '<em>Comment</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int COMMENT_FEATURE_COUNT = MODEL_ELEMENT_FEATURE_COUNT + 1;

	/**
	 * The meta object id for the '{@link com.bluexml.side.common.impl.TagImpl <em>Tag</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see com.bluexml.side.common.impl.TagImpl
	 * @see com.bluexml.side.common.impl.CommonPackageImpl#getTag()
	 * @generated
	 */
	int TAG = 3;

	/**
	 * The feature id for the '<em><b>Key</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TAG__KEY = 0;

	/**
	 * The feature id for the '<em><b>Value</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TAG__VALUE = 1;

	/**
	 * The number of structural features of the '<em>Tag</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TAG_FEATURE_COUNT = 2;

	/**
	 * The meta object id for the '{@link com.bluexml.side.common.impl.StereotypeImpl <em>Stereotype</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see com.bluexml.side.common.impl.StereotypeImpl
	 * @see com.bluexml.side.common.impl.CommonPackageImpl#getStereotype()
	 * @generated
	 */
	int STEREOTYPE = 4;

	/**
	 * The feature id for the '<em><b>Stereotypes</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int STEREOTYPE__STEREOTYPES = NAMED_MODEL_ELEMENT__STEREOTYPES;

	/**
	 * The feature id for the '<em><b>Tags</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int STEREOTYPE__TAGS = NAMED_MODEL_ELEMENT__TAGS;

	/**
	 * The feature id for the '<em><b>Comments</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int STEREOTYPE__COMMENTS = NAMED_MODEL_ELEMENT__COMMENTS;

	/**
	 * The feature id for the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int STEREOTYPE__NAME = NAMED_MODEL_ELEMENT__NAME;

	/**
	 * The feature id for the '<em><b>Documentation</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int STEREOTYPE__DOCUMENTATION = NAMED_MODEL_ELEMENT__DOCUMENTATION;

	/**
	 * The number of structural features of the '<em>Stereotype</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int STEREOTYPE_FEATURE_COUNT = NAMED_MODEL_ELEMENT_FEATURE_COUNT + 0;

	/**
	 * The meta object id for the '{@link com.bluexml.side.common.impl.PackageImpl <em>Package</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see com.bluexml.side.common.impl.PackageImpl
	 * @see com.bluexml.side.common.impl.CommonPackageImpl#getPackage()
	 * @generated
	 */
	int PACKAGE = 5;

	/**
	 * The feature id for the '<em><b>Stereotypes</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PACKAGE__STEREOTYPES = NAMED_MODEL_ELEMENT__STEREOTYPES;

	/**
	 * The feature id for the '<em><b>Tags</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PACKAGE__TAGS = NAMED_MODEL_ELEMENT__TAGS;

	/**
	 * The feature id for the '<em><b>Comments</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PACKAGE__COMMENTS = NAMED_MODEL_ELEMENT__COMMENTS;

	/**
	 * The feature id for the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PACKAGE__NAME = NAMED_MODEL_ELEMENT__NAME;

	/**
	 * The feature id for the '<em><b>Documentation</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PACKAGE__DOCUMENTATION = NAMED_MODEL_ELEMENT__DOCUMENTATION;

	/**
	 * The feature id for the '<em><b>Stereotype Set</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PACKAGE__STEREOTYPE_SET = NAMED_MODEL_ELEMENT_FEATURE_COUNT + 0;

	/**
	 * The feature id for the '<em><b>Package Set</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PACKAGE__PACKAGE_SET = NAMED_MODEL_ELEMENT_FEATURE_COUNT + 1;

	/**
	 * The number of structural features of the '<em>Package</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PACKAGE_FEATURE_COUNT = NAMED_MODEL_ELEMENT_FEATURE_COUNT + 2;


	/**
	 * Returns the meta object for class '{@link com.bluexml.side.common.ModelElement <em>Model Element</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Model Element</em>'.
	 * @see com.bluexml.side.common.ModelElement
	 * @generated
	 */
	EClass getModelElement();

	/**
	 * Returns the meta object for the reference list '{@link com.bluexml.side.common.ModelElement#getStereotypes <em>Stereotypes</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference list '<em>Stereotypes</em>'.
	 * @see com.bluexml.side.common.ModelElement#getStereotypes()
	 * @see #getModelElement()
	 * @generated
	 */
	EReference getModelElement_Stereotypes();

	/**
	 * Returns the meta object for the containment reference list '{@link com.bluexml.side.common.ModelElement#getTags <em>Tags</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference list '<em>Tags</em>'.
	 * @see com.bluexml.side.common.ModelElement#getTags()
	 * @see #getModelElement()
	 * @generated
	 */
	EReference getModelElement_Tags();

	/**
	 * Returns the meta object for the containment reference list '{@link com.bluexml.side.common.ModelElement#getComments <em>Comments</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference list '<em>Comments</em>'.
	 * @see com.bluexml.side.common.ModelElement#getComments()
	 * @see #getModelElement()
	 * @generated
	 */
	EReference getModelElement_Comments();

	/**
	 * Returns the meta object for class '{@link com.bluexml.side.common.NamedModelElement <em>Named Model Element</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Named Model Element</em>'.
	 * @see com.bluexml.side.common.NamedModelElement
	 * @generated
	 */
	EClass getNamedModelElement();

	/**
	 * Returns the meta object for the attribute '{@link com.bluexml.side.common.NamedModelElement#getName <em>Name</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Name</em>'.
	 * @see com.bluexml.side.common.NamedModelElement#getName()
	 * @see #getNamedModelElement()
	 * @generated
	 */
	EAttribute getNamedModelElement_Name();

	/**
	 * Returns the meta object for the attribute '{@link com.bluexml.side.common.NamedModelElement#getDocumentation <em>Documentation</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Documentation</em>'.
	 * @see com.bluexml.side.common.NamedModelElement#getDocumentation()
	 * @see #getNamedModelElement()
	 * @generated
	 */
	EAttribute getNamedModelElement_Documentation();

	/**
	 * Returns the meta object for class '{@link com.bluexml.side.common.Comment <em>Comment</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Comment</em>'.
	 * @see com.bluexml.side.common.Comment
	 * @generated
	 */
	EClass getComment();

	/**
	 * Returns the meta object for the attribute '{@link com.bluexml.side.common.Comment#getValue <em>Value</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Value</em>'.
	 * @see com.bluexml.side.common.Comment#getValue()
	 * @see #getComment()
	 * @generated
	 */
	EAttribute getComment_Value();

	/**
	 * Returns the meta object for class '{@link com.bluexml.side.common.Tag <em>Tag</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Tag</em>'.
	 * @see com.bluexml.side.common.Tag
	 * @generated
	 */
	EClass getTag();

	/**
	 * Returns the meta object for the attribute '{@link com.bluexml.side.common.Tag#getKey <em>Key</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Key</em>'.
	 * @see com.bluexml.side.common.Tag#getKey()
	 * @see #getTag()
	 * @generated
	 */
	EAttribute getTag_Key();

	/**
	 * Returns the meta object for the attribute '{@link com.bluexml.side.common.Tag#getValue <em>Value</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Value</em>'.
	 * @see com.bluexml.side.common.Tag#getValue()
	 * @see #getTag()
	 * @generated
	 */
	EAttribute getTag_Value();

	/**
	 * Returns the meta object for class '{@link com.bluexml.side.common.Stereotype <em>Stereotype</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Stereotype</em>'.
	 * @see com.bluexml.side.common.Stereotype
	 * @generated
	 */
	EClass getStereotype();

	/**
	 * Returns the meta object for class '{@link com.bluexml.side.common.Package <em>Package</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Package</em>'.
	 * @see com.bluexml.side.common.Package
	 * @generated
	 */
	EClass getPackage();

	/**
	 * Returns the meta object for the containment reference list '{@link com.bluexml.side.common.Package#getStereotypeSet <em>Stereotype Set</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference list '<em>Stereotype Set</em>'.
	 * @see com.bluexml.side.common.Package#getStereotypeSet()
	 * @see #getPackage()
	 * @generated
	 */
	EReference getPackage_StereotypeSet();

	/**
	 * Returns the meta object for the containment reference list '{@link com.bluexml.side.common.Package#getPackageSet <em>Package Set</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference list '<em>Package Set</em>'.
	 * @see com.bluexml.side.common.Package#getPackageSet()
	 * @see #getPackage()
	 * @generated
	 */
	EReference getPackage_PackageSet();

	/**
	 * Returns the factory that creates the instances of the model.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the factory that creates the instances of the model.
	 * @generated
	 */
	CommonFactory getCommonFactory();

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
		 * The meta object literal for the '{@link com.bluexml.side.common.impl.ModelElementImpl <em>Model Element</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see com.bluexml.side.common.impl.ModelElementImpl
		 * @see com.bluexml.side.common.impl.CommonPackageImpl#getModelElement()
		 * @generated
		 */
		EClass MODEL_ELEMENT = eINSTANCE.getModelElement();

		/**
		 * The meta object literal for the '<em><b>Stereotypes</b></em>' reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference MODEL_ELEMENT__STEREOTYPES = eINSTANCE.getModelElement_Stereotypes();

		/**
		 * The meta object literal for the '<em><b>Tags</b></em>' containment reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference MODEL_ELEMENT__TAGS = eINSTANCE.getModelElement_Tags();

		/**
		 * The meta object literal for the '<em><b>Comments</b></em>' containment reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference MODEL_ELEMENT__COMMENTS = eINSTANCE.getModelElement_Comments();

		/**
		 * The meta object literal for the '{@link com.bluexml.side.common.impl.NamedModelElementImpl <em>Named Model Element</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see com.bluexml.side.common.impl.NamedModelElementImpl
		 * @see com.bluexml.side.common.impl.CommonPackageImpl#getNamedModelElement()
		 * @generated
		 */
		EClass NAMED_MODEL_ELEMENT = eINSTANCE.getNamedModelElement();

		/**
		 * The meta object literal for the '<em><b>Name</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute NAMED_MODEL_ELEMENT__NAME = eINSTANCE.getNamedModelElement_Name();

		/**
		 * The meta object literal for the '<em><b>Documentation</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute NAMED_MODEL_ELEMENT__DOCUMENTATION = eINSTANCE.getNamedModelElement_Documentation();

		/**
		 * The meta object literal for the '{@link com.bluexml.side.common.impl.CommentImpl <em>Comment</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see com.bluexml.side.common.impl.CommentImpl
		 * @see com.bluexml.side.common.impl.CommonPackageImpl#getComment()
		 * @generated
		 */
		EClass COMMENT = eINSTANCE.getComment();

		/**
		 * The meta object literal for the '<em><b>Value</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute COMMENT__VALUE = eINSTANCE.getComment_Value();

		/**
		 * The meta object literal for the '{@link com.bluexml.side.common.impl.TagImpl <em>Tag</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see com.bluexml.side.common.impl.TagImpl
		 * @see com.bluexml.side.common.impl.CommonPackageImpl#getTag()
		 * @generated
		 */
		EClass TAG = eINSTANCE.getTag();

		/**
		 * The meta object literal for the '<em><b>Key</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute TAG__KEY = eINSTANCE.getTag_Key();

		/**
		 * The meta object literal for the '<em><b>Value</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute TAG__VALUE = eINSTANCE.getTag_Value();

		/**
		 * The meta object literal for the '{@link com.bluexml.side.common.impl.StereotypeImpl <em>Stereotype</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see com.bluexml.side.common.impl.StereotypeImpl
		 * @see com.bluexml.side.common.impl.CommonPackageImpl#getStereotype()
		 * @generated
		 */
		EClass STEREOTYPE = eINSTANCE.getStereotype();

		/**
		 * The meta object literal for the '{@link com.bluexml.side.common.impl.PackageImpl <em>Package</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see com.bluexml.side.common.impl.PackageImpl
		 * @see com.bluexml.side.common.impl.CommonPackageImpl#getPackage()
		 * @generated
		 */
		EClass PACKAGE = eINSTANCE.getPackage();

		/**
		 * The meta object literal for the '<em><b>Stereotype Set</b></em>' containment reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference PACKAGE__STEREOTYPE_SET = eINSTANCE.getPackage_StereotypeSet();

		/**
		 * The meta object literal for the '<em><b>Package Set</b></em>' containment reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference PACKAGE__PACKAGE_SET = eINSTANCE.getPackage_PackageSet();

	}

} //CommonPackage
