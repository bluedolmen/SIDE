/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package com.bluexml.side.clazz.impl;

import org.eclipse.emf.ecore.EAttribute;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EEnum;
import org.eclipse.emf.ecore.EOperation;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.EReference;
import org.eclipse.emf.ecore.EValidator;
import org.eclipse.emf.ecore.impl.EPackageImpl;

import com.bluexml.side.clazz.AbstractClass;
import com.bluexml.side.clazz.Aspect;
import com.bluexml.side.clazz.Association;
import com.bluexml.side.clazz.AssociationEnd;
import com.bluexml.side.clazz.AssociationType;
import com.bluexml.side.clazz.Attribute;
import com.bluexml.side.clazz.ClassComment;
import com.bluexml.side.clazz.ClassModelElement;
import com.bluexml.side.clazz.ClassPackage;
import com.bluexml.side.clazz.Clazz;
import com.bluexml.side.clazz.ClazzFactory;
import com.bluexml.side.clazz.ClazzPackage;
import com.bluexml.side.clazz.Enumeration;
import com.bluexml.side.clazz.EnumerationLiteral;
import com.bluexml.side.clazz.Model;
import com.bluexml.side.clazz.TitledNamedClassModelElement;
import com.bluexml.side.clazz.util.ClazzValidator;
import com.bluexml.side.common.CommonPackage;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model <b>Package</b>.
 * <!-- end-user-doc -->
 * @generated
 */
public class ClazzPackageImpl extends EPackageImpl implements ClazzPackage {
	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass classModelElementEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass classPackageEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass clazzEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass associationEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass attributeEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass enumerationEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass enumerationLiteralEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass aspectEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass abstractClassEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass titledNamedClassModelElementEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass classCommentEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass associationEndEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass modelEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EEnum associationTypeEEnum = null;

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
	 * @see com.bluexml.side.clazz.ClazzPackage#eNS_URI
	 * @see #init()
	 * @generated
	 */
	private ClazzPackageImpl() {
		super(eNS_URI, ClazzFactory.eINSTANCE);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private static boolean isInited = false;

	/**
	 * Creates, registers, and initializes the <b>Package</b> for this model, and for any others upon which it depends.
	 * 
	 * <p>This method is used to initialize {@link ClazzPackage#eINSTANCE} when that field is accessed.
	 * Clients should not invoke it directly. Instead, they should simply access that field to obtain the package.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #eNS_URI
	 * @see #createPackageContents()
	 * @see #initializePackageContents()
	 * @generated
	 */
	public static ClazzPackage init() {
		if (isInited) return (ClazzPackage)EPackage.Registry.INSTANCE.getEPackage(ClazzPackage.eNS_URI);

		// Obtain or create and register package
		ClazzPackageImpl theClazzPackage = (ClazzPackageImpl)(EPackage.Registry.INSTANCE.get(eNS_URI) instanceof ClazzPackageImpl ? EPackage.Registry.INSTANCE.get(eNS_URI) : new ClazzPackageImpl());

		isInited = true;

		// Initialize simple dependencies
		CommonPackage.eINSTANCE.eClass();

		// Create package meta-data objects
		theClazzPackage.createPackageContents();

		// Initialize created meta-data
		theClazzPackage.initializePackageContents();

		// Register package validator
		EValidator.Registry.INSTANCE.put
			(theClazzPackage, 
			 new EValidator.Descriptor() {
				 public EValidator getEValidator() {
					 return ClazzValidator.INSTANCE;
				 }
			 });

		// Mark meta-data to indicate it can't be changed
		theClazzPackage.freeze();

  
		// Update the registry and return the package
		EPackage.Registry.INSTANCE.put(ClazzPackage.eNS_URI, theClazzPackage);
		return theClazzPackage;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getClassModelElement() {
		return classModelElementEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getClassModelElement_HasComments() {
		return (EReference)classModelElementEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getClassPackage() {
		return classPackageEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getClassPackage_ClassSet() {
		return (EReference)classPackageEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getClassPackage_AssociationSet() {
		return (EReference)classPackageEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getClassPackage_AspectSet() {
		return (EReference)classPackageEClass.getEStructuralFeatures().get(2);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getClassPackage_EnumerationSet() {
		return (EReference)classPackageEClass.getEStructuralFeatures().get(3);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getClazz() {
		return clazzEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getClazz_Operations() {
		return (EReference)clazzEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getClazz_Generalizations() {
		return (EReference)clazzEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getClazz_Aspects() {
		return (EReference)clazzEClass.getEStructuralFeatures().get(2);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getClazz_Abstract() {
		return (EAttribute)clazzEClass.getEStructuralFeatures().get(3);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getClazz_Deprecated() {
		return (EAttribute)clazzEClass.getEStructuralFeatures().get(4);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getAssociation() {
		return associationEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getAssociation_AssociationType() {
		return (EAttribute)associationEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getAssociation_FirstEnd() {
		return (EReference)associationEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getAssociation_SecondEnd() {
		return (EReference)associationEClass.getEStructuralFeatures().get(2);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getAttribute() {
		return attributeEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getAttribute_Typ() {
		return (EAttribute)attributeEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getAttribute_InitialValue() {
		return (EAttribute)attributeEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getAttribute_Visibility() {
		return (EAttribute)attributeEClass.getEStructuralFeatures().get(2);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getAttribute_ValueList() {
		return (EReference)attributeEClass.getEStructuralFeatures().get(3);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getAttribute_Unique() {
		return (EAttribute)attributeEClass.getEStructuralFeatures().get(4);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getAttribute_Mockup() {
		return (EAttribute)attributeEClass.getEStructuralFeatures().get(5);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getEnumeration() {
		return enumerationEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getEnumeration_Literals() {
		return (EReference)enumerationEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getEnumeration_Dynamic() {
		return (EAttribute)enumerationEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getEnumeration_Depends() {
		return (EReference)enumerationEClass.getEStructuralFeatures().get(2);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getEnumerationLiteral() {
		return enumerationLiteralEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getEnumerationLiteral_Value() {
		return (EAttribute)enumerationLiteralEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getEnumerationLiteral_Name() {
		return (EAttribute)enumerationLiteralEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getEnumerationLiteral_Enum() {
		return (EReference)enumerationLiteralEClass.getEStructuralFeatures().get(2);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getAspect() {
		return aspectEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getAspect_Generalizations() {
		return (EReference)aspectEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getAbstractClass() {
		return abstractClassEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getAbstractClass_Attributes() {
		return (EReference)abstractClassEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getTitledNamedClassModelElement() {
		return titledNamedClassModelElementEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getTitledNamedClassModelElement_Title() {
		return (EAttribute)titledNamedClassModelElementEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getClassComment() {
		return classCommentEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getAssociationEnd() {
		return associationEndEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getAssociationEnd_CardMin() {
		return (EAttribute)associationEndEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getAssociationEnd_CardMax() {
		return (EAttribute)associationEndEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getAssociationEnd_Navigable() {
		return (EAttribute)associationEndEClass.getEStructuralFeatures().get(2);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getAssociationEnd_LinkedClass() {
		return (EReference)associationEndEClass.getEStructuralFeatures().get(3);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getModel() {
		return modelEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EEnum getAssociationType() {
		return associationTypeEEnum;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public ClazzFactory getClazzFactory() {
		return (ClazzFactory)getEFactoryInstance();
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
		classModelElementEClass = createEClass(CLASS_MODEL_ELEMENT);
		createEReference(classModelElementEClass, CLASS_MODEL_ELEMENT__HAS_COMMENTS);

		classPackageEClass = createEClass(CLASS_PACKAGE);
		createEReference(classPackageEClass, CLASS_PACKAGE__CLASS_SET);
		createEReference(classPackageEClass, CLASS_PACKAGE__ASSOCIATION_SET);
		createEReference(classPackageEClass, CLASS_PACKAGE__ASPECT_SET);
		createEReference(classPackageEClass, CLASS_PACKAGE__ENUMERATION_SET);

		clazzEClass = createEClass(CLAZZ);
		createEReference(clazzEClass, CLAZZ__OPERATIONS);
		createEReference(clazzEClass, CLAZZ__GENERALIZATIONS);
		createEReference(clazzEClass, CLAZZ__ASPECTS);
		createEAttribute(clazzEClass, CLAZZ__ABSTRACT);
		createEAttribute(clazzEClass, CLAZZ__DEPRECATED);

		associationEClass = createEClass(ASSOCIATION);
		createEAttribute(associationEClass, ASSOCIATION__ASSOCIATION_TYPE);
		createEReference(associationEClass, ASSOCIATION__FIRST_END);
		createEReference(associationEClass, ASSOCIATION__SECOND_END);

		attributeEClass = createEClass(ATTRIBUTE);
		createEAttribute(attributeEClass, ATTRIBUTE__TYP);
		createEAttribute(attributeEClass, ATTRIBUTE__INITIAL_VALUE);
		createEAttribute(attributeEClass, ATTRIBUTE__VISIBILITY);
		createEReference(attributeEClass, ATTRIBUTE__VALUE_LIST);
		createEAttribute(attributeEClass, ATTRIBUTE__UNIQUE);
		createEAttribute(attributeEClass, ATTRIBUTE__MOCKUP);

		enumerationEClass = createEClass(ENUMERATION);
		createEReference(enumerationEClass, ENUMERATION__LITERALS);
		createEAttribute(enumerationEClass, ENUMERATION__DYNAMIC);
		createEReference(enumerationEClass, ENUMERATION__DEPENDS);

		enumerationLiteralEClass = createEClass(ENUMERATION_LITERAL);
		createEAttribute(enumerationLiteralEClass, ENUMERATION_LITERAL__VALUE);
		createEAttribute(enumerationLiteralEClass, ENUMERATION_LITERAL__NAME);
		createEReference(enumerationLiteralEClass, ENUMERATION_LITERAL__ENUM);

		aspectEClass = createEClass(ASPECT);
		createEReference(aspectEClass, ASPECT__GENERALIZATIONS);

		abstractClassEClass = createEClass(ABSTRACT_CLASS);
		createEReference(abstractClassEClass, ABSTRACT_CLASS__ATTRIBUTES);

		titledNamedClassModelElementEClass = createEClass(TITLED_NAMED_CLASS_MODEL_ELEMENT);
		createEAttribute(titledNamedClassModelElementEClass, TITLED_NAMED_CLASS_MODEL_ELEMENT__TITLE);

		classCommentEClass = createEClass(CLASS_COMMENT);

		associationEndEClass = createEClass(ASSOCIATION_END);
		createEAttribute(associationEndEClass, ASSOCIATION_END__CARD_MIN);
		createEAttribute(associationEndEClass, ASSOCIATION_END__CARD_MAX);
		createEAttribute(associationEndEClass, ASSOCIATION_END__NAVIGABLE);
		createEReference(associationEndEClass, ASSOCIATION_END__LINKED_CLASS);

		modelEClass = createEClass(MODEL);

		// Create enums
		associationTypeEEnum = createEEnum(ASSOCIATION_TYPE);
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
		classModelElementEClass.getESuperTypes().add(theCommonPackage.getModelElement());
		classPackageEClass.getESuperTypes().add(theCommonPackage.getPackage());
		clazzEClass.getESuperTypes().add(this.getAbstractClass());
		associationEClass.getESuperTypes().add(this.getTitledNamedClassModelElement());
		attributeEClass.getESuperTypes().add(this.getTitledNamedClassModelElement());
		enumerationEClass.getESuperTypes().add(theCommonPackage.getNamedModelElement());
		aspectEClass.getESuperTypes().add(this.getAbstractClass());
		abstractClassEClass.getESuperTypes().add(this.getTitledNamedClassModelElement());
		abstractClassEClass.getESuperTypes().add(theCommonPackage.getContainer());
		titledNamedClassModelElementEClass.getESuperTypes().add(theCommonPackage.getNamedModelElement());
		titledNamedClassModelElementEClass.getESuperTypes().add(this.getClassModelElement());
		classCommentEClass.getESuperTypes().add(theCommonPackage.getComment());
		associationEndEClass.getESuperTypes().add(this.getTitledNamedClassModelElement());
		associationEndEClass.getESuperTypes().add(theCommonPackage.getComment());
		modelEClass.getESuperTypes().add(this.getClassPackage());

		// Initialize classes and features; add operations and parameters
		initEClass(classModelElementEClass, ClassModelElement.class, "ClassModelElement", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEReference(getClassModelElement_HasComments(), this.getClassComment(), null, "hasComments", null, 0, -1, ClassModelElement.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(classPackageEClass, ClassPackage.class, "ClassPackage", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEReference(getClassPackage_ClassSet(), this.getClazz(), null, "classSet", null, 0, -1, ClassPackage.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getClassPackage_AssociationSet(), this.getAssociation(), null, "associationSet", null, 0, -1, ClassPackage.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getClassPackage_AspectSet(), this.getAspect(), null, "aspectSet", null, 0, -1, ClassPackage.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getClassPackage_EnumerationSet(), this.getEnumeration(), null, "enumerationSet", null, 0, -1, ClassPackage.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		addEOperation(classPackageEClass, this.getClassPackage(), "getAllPackages", 0, -1, IS_UNIQUE, IS_ORDERED);

		addEOperation(classPackageEClass, this.getClazz(), "getAllClasses", 0, -1, IS_UNIQUE, IS_ORDERED);

		addEOperation(classPackageEClass, this.getEnumeration(), "getAllEnumerations", 0, -1, IS_UNIQUE, IS_ORDERED);

		addEOperation(classPackageEClass, this.getAspect(), "getAllAspects", 0, -1, IS_UNIQUE, IS_ORDERED);

		addEOperation(classPackageEClass, this.getAssociation(), "getAllAssociations", 0, -1, IS_UNIQUE, IS_ORDERED);

		addEOperation(classPackageEClass, this.getAbstractClass(), "getAllAbstractClasses", 0, -1, IS_UNIQUE, IS_ORDERED);

		initEClass(clazzEClass, Clazz.class, "Clazz", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEReference(getClazz_Operations(), theCommonPackage.getOperationComponent(), null, "operations", null, 0, -1, Clazz.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getClazz_Generalizations(), this.getClazz(), null, "generalizations", null, 0, -1, Clazz.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getClazz_Aspects(), this.getAspect(), null, "aspects", null, 0, -1, Clazz.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getClazz_Abstract(), ecorePackage.getEBoolean(), "abstract", null, 0, 1, Clazz.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getClazz_Deprecated(), ecorePackage.getEBoolean(), "deprecated", null, 0, 1, Clazz.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		addEOperation(clazzEClass, this.getAttribute(), "getAllAttributes", 0, -1, IS_UNIQUE, IS_ORDERED);

		addEOperation(clazzEClass, this.getClazz(), "getDescendants", 0, -1, IS_UNIQUE, IS_ORDERED);

		addEOperation(clazzEClass, this.getAttribute(), "getClassAndAspectAttributes", 0, -1, IS_UNIQUE, IS_ORDERED);

		addEOperation(clazzEClass, this.getClazz(), "getInheritedClasses", 0, -1, IS_UNIQUE, IS_ORDERED);

		addEOperation(clazzEClass, this.getAttribute(), "getAspectAttributes", 0, -1, IS_UNIQUE, IS_ORDERED);

		addEOperation(clazzEClass, this.getAttribute(), "getAllSubTypes", 0, -1, IS_UNIQUE, IS_ORDERED);

		addEOperation(clazzEClass, this.getAttribute(), "getAllInheritedClassAndAspectAttributes", 0, -1, IS_UNIQUE, IS_ORDERED);

		addEOperation(clazzEClass, this.getAttribute(), "getAllInheritedAttributes", 0, -1, IS_UNIQUE, IS_ORDERED);

		addEOperation(clazzEClass, this.getAssociation(), "getSourceAssociations", 0, -1, IS_UNIQUE, IS_ORDERED);

		EOperation op = addEOperation(clazzEClass, ecorePackage.getEBoolean(), "isSource", 1, 1, IS_UNIQUE, IS_ORDERED);
		addEParameter(op, this.getAssociation(), "asso", 1, 1, IS_UNIQUE, IS_ORDERED);

		op = addEOperation(clazzEClass, ecorePackage.getEBoolean(), "isTarget", 1, 1, IS_UNIQUE, IS_ORDERED);
		addEParameter(op, this.getAssociation(), "asso", 1, 1, IS_UNIQUE, IS_ORDERED);

		addEOperation(clazzEClass, this.getAssociation(), "getTargetAssociations", 0, -1, IS_UNIQUE, IS_ORDERED);

		addEOperation(clazzEClass, this.getAssociation(), "getAllSourceAssociations", 0, -1, IS_UNIQUE, IS_ORDERED);

		addEOperation(clazzEClass, this.getAssociation(), "getAllTargetAssociations", 0, -1, IS_UNIQUE, IS_ORDERED);

		addEOperation(clazzEClass, this.getClazz(), "getLinkedClasses", 0, -1, IS_UNIQUE, IS_ORDERED);

		addEOperation(clazzEClass, this.getAssociationEnd(), "getAllSourceAssociationEnds", 0, -1, IS_UNIQUE, IS_ORDERED);

		addEOperation(clazzEClass, this.getAssociationEnd(), "getAllTargetAssociationEnds", 0, -1, IS_UNIQUE, IS_ORDERED);

		addEOperation(clazzEClass, this.getAssociationEnd(), "getSourceAssociationEnds", 0, -1, IS_UNIQUE, IS_ORDERED);

		addEOperation(clazzEClass, this.getAssociationEnd(), "getTargetAssociationEnds", 0, -1, IS_UNIQUE, IS_ORDERED);

		addEOperation(clazzEClass, this.getAspect(), "getAllAspects", 0, -1, IS_UNIQUE, IS_ORDERED);

		addEOperation(clazzEClass, this.getAttribute(), "getAllAttributesWithoutAspectsAttributes", 0, -1, IS_UNIQUE, IS_ORDERED);

		initEClass(associationEClass, Association.class, "Association", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEAttribute(getAssociation_AssociationType(), this.getAssociationType(), "associationType", null, 0, 1, Association.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getAssociation_FirstEnd(), this.getAssociationEnd(), null, "firstEnd", null, 0, 1, Association.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getAssociation_SecondEnd(), this.getAssociationEnd(), null, "secondEnd", null, 0, 1, Association.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		op = addEOperation(associationEClass, ecorePackage.getEBoolean(), "equalsForMerge", 0, 1, IS_UNIQUE, IS_ORDERED);
		addEParameter(op, this.getAssociation(), "other", 0, 1, IS_UNIQUE, IS_ORDERED);

		addEOperation(associationEClass, ecorePackage.getEBoolean(), "isReflexive", 0, 1, IS_UNIQUE, IS_ORDERED);

		addEOperation(associationEClass, this.getClazz(), "getSource", 1, 2, IS_UNIQUE, IS_ORDERED);

		addEOperation(associationEClass, this.getClazz(), "getTarget", 1, 2, IS_UNIQUE, IS_ORDERED);

		op = addEOperation(associationEClass, this.getAssociationEnd(), "getAssociationEnd", 0, 2, IS_UNIQUE, IS_ORDERED);
		addEParameter(op, this.getClazz(), "clazz", 0, 1, IS_UNIQUE, IS_ORDERED);

		initEClass(attributeEClass, Attribute.class, "Attribute", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEAttribute(getAttribute_Typ(), theCommonPackage.getDataType(), "typ", "String", 0, 1, Attribute.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getAttribute_InitialValue(), ecorePackage.getEString(), "initialValue", null, 0, 1, Attribute.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getAttribute_Visibility(), theCommonPackage.getVisibility(), "visibility", "Private", 0, 1, Attribute.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getAttribute_ValueList(), this.getEnumeration(), null, "valueList", null, 0, 1, Attribute.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getAttribute_Unique(), ecorePackage.getEBoolean(), "unique", "false", 0, 1, Attribute.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getAttribute_Mockup(), ecorePackage.getEString(), "mockup", null, 0, -1, Attribute.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(enumerationEClass, Enumeration.class, "Enumeration", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEReference(getEnumeration_Literals(), this.getEnumerationLiteral(), this.getEnumerationLiteral_Enum(), "literals", null, 0, -1, Enumeration.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getEnumeration_Dynamic(), ecorePackage.getEBooleanObject(), "dynamic", "false", 0, 1, Enumeration.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getEnumeration_Depends(), this.getEnumeration(), null, "depends", null, 0, 1, Enumeration.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(enumerationLiteralEClass, EnumerationLiteral.class, "EnumerationLiteral", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEAttribute(getEnumerationLiteral_Value(), ecorePackage.getEString(), "value", null, 0, 1, EnumerationLiteral.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getEnumerationLiteral_Name(), ecorePackage.getEString(), "name", null, 0, 1, EnumerationLiteral.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getEnumerationLiteral_Enum(), this.getEnumeration(), this.getEnumeration_Literals(), "enum", null, 0, 1, EnumerationLiteral.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(aspectEClass, Aspect.class, "Aspect", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEReference(getAspect_Generalizations(), this.getAspect(), null, "generalizations", null, 0, -1, Aspect.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		op = addEOperation(aspectEClass, ecorePackage.getEBoolean(), "equalsForMerge", 0, 1, IS_UNIQUE, IS_ORDERED);
		addEParameter(op, this.getAspect(), "other", 0, 1, IS_UNIQUE, IS_ORDERED);

		initEClass(abstractClassEClass, AbstractClass.class, "AbstractClass", IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEReference(getAbstractClass_Attributes(), this.getAttribute(), null, "attributes", null, 0, -1, AbstractClass.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		op = addEOperation(abstractClassEClass, ecorePackage.getEBoolean(), "equalsForMerge", 0, 1, IS_UNIQUE, IS_ORDERED);
		addEParameter(op, this.getAbstractClass(), "other", 0, 1, IS_UNIQUE, IS_ORDERED);

		initEClass(titledNamedClassModelElementEClass, TitledNamedClassModelElement.class, "TitledNamedClassModelElement", IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEAttribute(getTitledNamedClassModelElement_Title(), ecorePackage.getEString(), "title", null, 0, 1, TitledNamedClassModelElement.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		addEOperation(titledNamedClassModelElementEClass, ecorePackage.getEString(), "getLabel", 1, 1, IS_UNIQUE, IS_ORDERED);

		initEClass(classCommentEClass, ClassComment.class, "ClassComment", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);

		initEClass(associationEndEClass, AssociationEnd.class, "AssociationEnd", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEAttribute(getAssociationEnd_CardMin(), ecorePackage.getEString(), "cardMin", "0", 0, 1, AssociationEnd.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getAssociationEnd_CardMax(), ecorePackage.getEString(), "cardMax", "1", 0, 1, AssociationEnd.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getAssociationEnd_Navigable(), ecorePackage.getEBoolean(), "navigable", null, 0, 1, AssociationEnd.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getAssociationEnd_LinkedClass(), this.getAbstractClass(), null, "linkedClass", null, 0, 1, AssociationEnd.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		addEOperation(associationEndEClass, ecorePackage.getEBoolean(), "isMandatory", 0, 1, IS_UNIQUE, IS_ORDERED);

		addEOperation(associationEndEClass, ecorePackage.getEBoolean(), "isMany", 0, 1, IS_UNIQUE, IS_ORDERED);

		addEOperation(associationEndEClass, this.getAssociationEnd(), "getOpposite", 0, 1, IS_UNIQUE, IS_ORDERED);

		initEClass(modelEClass, Model.class, "Model", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);

		// Initialize enums and add enum literals
		initEEnum(associationTypeEEnum, AssociationType.class, "AssociationType");
		addEEnumLiteral(associationTypeEEnum, AssociationType.DIRECT);
		addEEnumLiteral(associationTypeEEnum, AssociationType.AGGREGATION);
		addEEnumLiteral(associationTypeEEnum, AssociationType.COMPOSITION);

		// Create resource
		createResource(eNS_URI);

		// Create annotations
		// http://www.eclipse.org/emf/2002/Ecore
		createEcoreAnnotations();
		// http://www.bluexml.com/OCL
		createOCLAnnotations();
		// InternalDoc
		createInternalDocAnnotations();
	}

	/**
	 * Initializes the annotations for <b>http://www.bluexml.com/OCL</b>.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected void createOCLAnnotations() {
		String source = "http://www.bluexml.com/OCL";					
		addAnnotation
		  (classPackageEClass, 
		   source, 
		   new String[] {
			 "PackageNameNull", "not self.name.oclIsUndefined() and self.name <> \'\'"
		   });		
		addAnnotation
		  (classPackageEClass.getEOperations().get(0), 
		   source, 
		   new String[] {
			 "body", "ClassPackage.allInstances()"
		   });		
		addAnnotation
		  (classPackageEClass.getEOperations().get(1), 
		   source, 
		   new String[] {
			 "body", "Clazz.allInstances()"
		   });		
		addAnnotation
		  (classPackageEClass.getEOperations().get(2), 
		   source, 
		   new String[] {
			 "body", "Enumeration.allInstances()"
		   });		
		addAnnotation
		  (classPackageEClass.getEOperations().get(3), 
		   source, 
		   new String[] {
			 "body", "Aspect.allInstances()"
		   });		
		addAnnotation
		  (classPackageEClass.getEOperations().get(4), 
		   source, 
		   new String[] {
			 "body", "Association.allInstances()"
		   });		
		addAnnotation
		  (classPackageEClass.getEOperations().get(5), 
		   source, 
		   new String[] {
			 "body", "AbstractClass.allInstances()"
		   });		
		addAnnotation
		  (clazzEClass, 
		   source, 
		   new String[] {
			 "InheritanceCycle", "not self.generalizations.generalizations -> includes(self)"
		   });				
		addAnnotation
		  (clazzEClass.getEOperations().get(0), 
		   source, 
		   new String[] {
			 "body", "self.getAllInheritedClassAndAspectAttributes() -> union(self.getClassAndAspectAttributes())",
			 "description", "search for class attributes, inherited one and finaly added to the class by aspect"
		   });		
		addAnnotation
		  (clazzEClass.getEOperations().get(1), 
		   source, 
		   new String[] {
			 "body", "Clazz.allInstances() ->select(e:Clazz|e.getInheritedClasses() ->includes(self) )",
			 "description", "get all Clazz that inherit from this Clazz"
		   });		
		addAnnotation
		  (clazzEClass.getEOperations().get(2), 
		   source, 
		   new String[] {
			 "body", "self.attributes -> asSet() -> union(self.getAspectAttributes())"
		   });		
		addAnnotation
		  (clazzEClass.getEOperations().get(3), 
		   source, 
		   new String[] {
			 "body", "self.generalizations  ->  asSet()  -> iterate(e:Clazz;result :Set(Clazz)= Set{}| result -> including(e) -> union(e.getInheritedClasses()))"
		   });		
		addAnnotation
		  (clazzEClass.getEOperations().get(4), 
		   source, 
		   new String[] {
			 "body", "self.aspects ->  asSet()  -> iterate(e:Aspect;result :Set(Attribute)= Set{}| result -> union(e.attributes ->asSet()))"
		   });		
		addAnnotation
		  (clazzEClass.getEOperations().get(5), 
		   source, 
		   new String[] {
			 "body", "self.getAllInheritedAttributes() -> union(self.getClassAndAspectAttributes())"
		   });		
		addAnnotation
		  (clazzEClass.getEOperations().get(6), 
		   source, 
		   new String[] {
			 "body", "self.getInheritedClasses() ->asSet() ->iterate(cl:Clazz;result:Set(Attribute)=Set{}|result->union(cl.getClassAndAspectAttributes() ->asSet()))",
			 "description", "search attributes than is describe in inherited classes (with Aspects)"
		   });		
		addAnnotation
		  (clazzEClass.getEOperations().get(7), 
		   source, 
		   new String[] {
			 "body", "self.getInheritedClasses() ->collect(c | c.attributes) -> flatten()",
			 "description", "get all attributes including the inherited ones (excluding aspects)"
		   });		
		addAnnotation
		  (clazzEClass.getEOperations().get(8), 
		   source, 
		   new String[] {
			 "body", "Association.allInstances() ->select(c:Association|self.isSource(c))",
			 "description", "search association where this clazz is source"
		   });		
		addAnnotation
		  (clazzEClass.getEOperations().get(9), 
		   source, 
		   new String[] {
			 "body", "(asso.firstEnd.linkedClass = self and asso.secondEnd.navigable) or (asso.secondEnd.linkedClass = self and asso.firstEnd.navigable)",
			 "description", "search for class attributes, inherited one and finaly added to the class by aspect"
		   });		
		addAnnotation
		  (clazzEClass.getEOperations().get(10), 
		   source, 
		   new String[] {
			 "body", "(asso.firstEnd.linkedClass = self and asso.firstEnd.navigable) or (asso.secondEnd.linkedClass = self and asso.secondEnd.navigable)",
			 "description", "search for class attributes, inherited one and finaly added to the class by aspect"
		   });		
		addAnnotation
		  (clazzEClass.getEOperations().get(11), 
		   source, 
		   new String[] {
			 "body", "Association.allInstances() ->select(c:Association|self.isTarget(c))",
			 "description", "search association where this clazz is target"
		   });		
		addAnnotation
		  (clazzEClass.getEOperations().get(12), 
		   source, 
		   new String[] {
			 "body", "self.getInheritedClasses() -> including(self) ->iterate(e:Clazz;result:Set(Association)=Set{}|result->union(e.getSourceAssociations()))",
			 "description", "search association where this clazz is source"
		   });		
		addAnnotation
		  (clazzEClass.getEOperations().get(13), 
		   source, 
		   new String[] {
			 "body", "self.getInheritedClasses() -> including(self) ->iterate(e:Clazz;result:Set(Association)=Set{}|result->union(e.getTargetAssociations()))",
			 "description", "search associations where this clazz is source or one of inheritedClass"
		   });		
		addAnnotation
		  (clazzEClass.getEOperations().get(14), 
		   source, 
		   new String[] {
			 "body", "self.getAllSourceAssociations().getTarget() ->asOrderedSet()"
		   });		
		addAnnotation
		  (clazzEClass.getEOperations().get(15), 
		   source, 
		   new String[] {
			 "body", "AssociationEnd.allInstances() -> select (ae | ae.linkedClass.oclIsTypeOf(Clazz) and ae.getOpposite().navigable and self.getInheritedClasses() -> including (self) ->includes(ae.linkedClass.oclAsType(Clazz)) )",
			 "description", "returns association ends where this clazz is source including inherited associations (association ends cannot link to aspects)"
		   });		
		addAnnotation
		  (clazzEClass.getEOperations().get(16), 
		   source, 
		   new String[] {
			 "body", "AssociationEnd.allInstances() -> select (ae | ae.navigable and self.getInheritedClasses() -> including (self) ->includes(ae.linkedClass) )",
			 "description", "returns association ends where this clazz is target including inherited associations (association ends cannot link to aspects)"
		   });		
		addAnnotation
		  (clazzEClass.getEOperations().get(17), 
		   source, 
		   new String[] {
			 "body", "AssociationEnd.allInstances() -> select (ae | ae.getOpposite().navigable and ae.linkedClass = self )",
			 "description", "returns association ends where this clazz is source"
		   });		
		addAnnotation
		  (clazzEClass.getEOperations().get(18), 
		   source, 
		   new String[] {
			 "body", "AssociationEnd.allInstances() -> select (ae | ae.navigable and ae.linkedClass = self)",
			 "description", "returns association ends where this clazz is target"
		   });		
		addAnnotation
		  (clazzEClass.getEOperations().get(19), 
		   source, 
		   new String[] {
			 "body", "self.aspects->asSet()->union(self.getInheritedClasses()->asSet().aspects)"
		   });		
		addAnnotation
		  (clazzEClass.getEOperations().get(20), 
		   source, 
		   new String[] {
			 "body", "self.getAllInheritedAttributes()->union(self.attributes)"
		   });				
		addAnnotation
		  (associationEClass, 
		   source, 
		   new String[] {
			 "reflexiveAssociationMustHaveRole", "if (self.firstEnd.oclIsTypeOf(Clazz) and self.secondEnd.oclIsTypeOf(Clazz)) then\n( self.isReflexive() and self.firstEnd.navigable and self.secondEnd.navigable ) implies ( ( not self.firstEnd.name.oclIsUndefined() and self.firstEnd.name <> \'\' ) and ( not self.secondEnd.name.oclIsUndefined() and self.secondEnd.name <> \'\' ))\nelse\ntrue\nendif",
			 "MinAndMaxTarget", "( self.secondEnd.cardMax <> \'-1\' ) implies ( self.secondEnd.cardMin <= self.secondEnd.cardMax )",
			 "MinAndMaxSource", "( self.firstEnd.cardMax <> \'-1\' ) implies ( self.firstEnd.cardMin <= self.firstEnd.cardMax )",
			 "NameNull", "not self.name.oclIsUndefined() and self.name <> \'\'",
			 "SourceNull", "self.firstEnd.linkedClass->notEmpty()",
			 "TargetNull", "self.secondEnd.linkedClass->notEmpty()",
			 "AtLeastOneNavigableEdge", "(firstEnd.navigable or secondEnd.navigable)",
			 "ClassCantBeReferencedbyTwoSameNameAssociation", "self.getSource().getAllSourceAssociations() ->asSet() ->select(a:Association|a.name = self.name)->size() = 1",
			 "IfAggregationOrCompositionThenUnidirectionalAssociation", "(self.associationType <> AssociationType::Direct) implies (self.firstEnd.navigable xor self.secondEnd.navigable )",
			 "twoWayNavigation", "(self.firstEnd.navigable and self.secondEnd.navigable) implies (self.firstEnd.name <> \'\' and self.secondEnd.name <> \'\')",
			 "noSpecialCharacters", "self.name.regexMatch(\'[\\w]*\') = true"
		   });				
		addAnnotation
		  (associationEClass.getEOperations().get(0), 
		   source, 
		   new String[] {
			 "body", "if( self.secondEnd.linkedClass.oclIsKindOf(Classe))\r\nthen\r\nself.secondEnd.linkedClass.oclAsType(Classe).equalsForMerge(other.secondEnd.linkedClass.oclAsType(Classe)) and self.firstEnd.linkedClass.oclAsType(Classe).equalsForMerge(other.firstEnd.linkedClass.oclAsType(Classe))\r\nand self.name = other.name\r\nelse\r\ntrue\r\nendif\r\n"
		   });		
		addAnnotation
		  (associationEClass.getEOperations().get(1), 
		   source, 
		   new String[] {
			 "body", "(self.firstEnd.linkedClass.getInheritedClasses() ->including(self.firstEnd.linkedClass) ->includes(self.secondEnd.linkedClass) and self.secondEnd.navigable)\ror \r(self.secondEnd.linkedClass.getInheritedClasses() ->including(self.secondEnd.linkedClass) ->includes(self.firstEnd.linkedClass) and self.firstEnd.navigable)\r"
		   });		
		addAnnotation
		  (associationEClass.getEOperations().get(2), 
		   source, 
		   new String[] {
			 "body", "if (self.firstEnd.navigable and self.secondEnd.navigable) then \r\tSet{} ->including(self.firstEnd.linkedClass) ->including(self.secondEnd.linkedClass)\relse if (self.firstEnd.navigable) then\r\t\tSet{}->including(self.secondEnd.linkedClass)\r\telse if (self.secondEnd.navigable) then \r\t\t\tSet{}->including(self.firstEnd.linkedClass)\r\t\telse\r\t\t\tSet{}\r\t\tendif\r\tendif\rendif",
			 "description", "get source Clazz"
		   });		
		addAnnotation
		  (associationEClass.getEOperations().get(3), 
		   source, 
		   new String[] {
			 "body", "if (self.firstEnd.navigable and self.secondEnd.navigable) then \r\tSet{} ->including(self.firstEnd.linkedClass) ->including(self.secondEnd.linkedClass)\relse if (self.secondEnd.navigable) then\r\t\tSet{}->including(self.secondEnd.linkedClass)\r\telse if (self.firstEnd.navigable) then \r\t\t\tSet{}->including(self.firstEnd.linkedClass)\r\t\telse\r\t\t\tSet{}\r\t\tendif\r\tendif\rendif",
			 "description", "get source Clazz"
		   });		
		addAnnotation
		  (associationEClass.getEOperations().get(4), 
		   source, 
		   new String[] {
			 "body", "Sequence{self.firstEnd,self.secondEnd} -> select(ae | ae.linkedClass = clazz)\n",
			 "description", "returns the association end for which the parameter class (clazz) is linked to"
		   });									
		addAnnotation
		  (enumerationEClass, 
		   source, 
		   new String[] {
			 "enumDynamicAreNotAvailable", "dynamic=false"
		   });								
		addAnnotation
		  (aspectEClass.getEOperations().get(0), 
		   source, 
		   new String[] {
			 "body", "self.name = other.name and self.title = other.title"
		   });		
		addAnnotation
		  (abstractClassEClass, 
		   source, 
		   new String[] {
			 "TwoModelElementWithSameName", "AbstractClass.allInstances()->select(a | a.name = self.name and a.getContainer() = self.getContainer() and a <> self)->size() = 0",
			 "NameNull", "not self.name.oclIsUndefined() and self.name <> \'\'",
			 "noSpecialCharacters", "self.name.regexMatch(\'[\\w]*\') = true",
			 "TwoAttributesSameName", "self.attributes -> forAll( a1, a2 | a1 <> a2 implies a1.name <>a2.name)"
		   });			
		addAnnotation
		  (abstractClassEClass.getEOperations().get(0), 
		   source, 
		   new String[] {
			 "body", "self.name = other.name and self.title = other.title"
		   });		
		addAnnotation
		  (titledNamedClassModelElementEClass.getEOperations().get(0), 
		   source, 
		   new String[] {
			 "body", "if self.title.oclIsUndefined() or self.title.size() = 0 then\r self.name \relse\r self.title \rendif"
		   });						
		addAnnotation
		  (associationEndEClass.getEOperations().get(0), 
		   source, 
		   new String[] {
			 "body", "cardMin.toInteger() > 0",
			 "description", "returns true if the association end is mandatory"
		   });		
		addAnnotation
		  (associationEndEClass.getEOperations().get(1), 
		   source, 
		   new String[] {
			 "body", "(cardMax.toInteger() > 1) or (cardMax.toInteger() = -1)",
			 "description", "returns true if the association end has a multiple cardinality"
		   });		
		addAnnotation
		  (associationEndEClass.getEOperations().get(2), 
		   source, 
		   new String[] {
			 "body", "let parent : Association = Association.allInstances() -> select(a | a.firstEnd = self or a.secondEnd = self) -> asSequence() -> first() in if (parent.firstEnd = self) then parent.secondEnd else parent.firstEnd endif",
			 "description", "returns the other side of the containing association"
		   });			
	}

	/**
	 * Initializes the annotations for <b>InternalDoc</b>.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected void createInternalDocAnnotations() {
		String source = "InternalDoc";																																																																			
		addAnnotation
		  (classCommentEClass, 
		   source, 
		   new String[] {
			 "documentation", "Used because we can\'t put a Comment (from Common) on the diagram. "
		   });							
	}

	/**
	 * Initializes the annotations for <b>http://www.eclipse.org/emf/2002/Ecore</b>.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected void createEcoreAnnotations() {
		String source = "http://www.eclipse.org/emf/2002/Ecore";				
		addAnnotation
		  (classPackageEClass, 
		   source, 
		   new String[] {
			 "constraints", "PackageNameNull"
		   });										
		addAnnotation
		  (clazzEClass, 
		   source, 
		   new String[] {
			 "constraints", "ClassWithTwoAttributesSameName InheritanceCycle"
		   });																											
		addAnnotation
		  (associationEClass, 
		   source, 
		   new String[] {
			 "constraints", "reflexiveAssociationMustHaveRole MinAndMaxTarget MinAndMaxSource NameNull SourceNull TargetNull AtLeastOneNavigableEdge ClassCantBeReferencedbyTwoSameNameAssociation IfAggregationOrCompositionThenUnidirectionalAssociation doubleNavigable noSpecialChracters",
			 "warning", "twoWayNavigation"
		   });																
		addAnnotation
		  (enumerationEClass, 
		   source, 
		   new String[] {
			 "warning", "enumDynamicAreNotAvailable",
			 "constraints", "enumDynamicAreNotAvailable"
		   });									
		addAnnotation
		  (abstractClassEClass, 
		   source, 
		   new String[] {
			 "constraints", "TwoModelElementWithSameName NameNull noSpecialCharacters TwoAttributesSameName"
		   });												
	}

} //ClazzPackageImpl
