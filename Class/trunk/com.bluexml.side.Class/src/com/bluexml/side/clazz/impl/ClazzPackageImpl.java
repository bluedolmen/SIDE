/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package com.bluexml.side.clazz.impl;

import com.bluexml.side.clazz.AbstractClass;
import com.bluexml.side.clazz.Aspect;
import com.bluexml.side.clazz.Association;
import com.bluexml.side.clazz.AssociationEnd;
import com.bluexml.side.clazz.AssociationType;
import com.bluexml.side.clazz.Attribute;
import com.bluexml.side.clazz.AttributeType;
import com.bluexml.side.clazz.ClassComment;
import com.bluexml.side.clazz.ClassModelElement;
import com.bluexml.side.clazz.ClassPackage;
import com.bluexml.side.clazz.Clazz;
import com.bluexml.side.clazz.ClazzFactory;
import com.bluexml.side.clazz.ClazzPackage;
import com.bluexml.side.clazz.Enumeration;
import com.bluexml.side.clazz.EnumerationLiteral;
import com.bluexml.side.clazz.FirstEnd;
import com.bluexml.side.clazz.MetaInfo;
import com.bluexml.side.clazz.MetaInfoGroup;
import com.bluexml.side.clazz.NamedClassModelElement;
import com.bluexml.side.clazz.Operation;
import com.bluexml.side.clazz.Parameter;
import com.bluexml.side.clazz.SecondEnd;
import com.bluexml.side.clazz.TitledNamedClassModelElement;
import com.bluexml.side.clazz.View;
import com.bluexml.side.clazz.ViewItem;
import com.bluexml.side.clazz.Visibility;

import com.bluexml.side.clazz.util.ClazzValidator;
import com.bluexml.side.common.CommonPackage;

import org.eclipse.emf.ecore.EAttribute;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EEnum;
import org.eclipse.emf.ecore.EGenericType;
import org.eclipse.emf.ecore.EOperation;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.EReference;

import org.eclipse.emf.ecore.EValidator;
import org.eclipse.emf.ecore.impl.EPackageImpl;

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
	private EClass namedClassModelElementEClass = null;

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
	private EClass operationEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass parameterEClass = null;

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
	private EClass metaInfoEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass metaInfoGroupEClass = null;

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
	private EClass firstEndEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass secondEndEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EEnum associationTypeEEnum = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EEnum attributeTypeEEnum = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EEnum visibilityEEnum = null;

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
	public static ClazzPackage init() {
		if (isInited) return (ClazzPackage)EPackage.Registry.INSTANCE.getEPackage(ClazzPackage.eNS_URI);

		// Obtain or create and register package
		ClazzPackageImpl theClazzPackage = (ClazzPackageImpl)(EPackage.Registry.INSTANCE.getEPackage(eNS_URI) instanceof ClazzPackageImpl ? EPackage.Registry.INSTANCE.getEPackage(eNS_URI) : new ClazzPackageImpl());

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
	public EReference getClassModelElement_Metainfo() {
		return (EReference)classModelElementEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getClassModelElement_Documentation() {
		return (EAttribute)classModelElementEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getClassModelElement_HasComments() {
		return (EReference)classModelElementEClass.getEStructuralFeatures().get(2);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getNamedClassModelElement() {
		return namedClassModelElementEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getNamedClassModelElement_Name() {
		return (EAttribute)namedClassModelElementEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getNamedClassModelElement_Description() {
		return (EAttribute)namedClassModelElementEClass.getEStructuralFeatures().get(1);
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
	public EAttribute getClazz_IsAbstract() {
		return (EAttribute)clazzEClass.getEStructuralFeatures().get(3);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getClazz_IsDeprecated() {
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
	public EAttribute getEnumeration_IsDynamic() {
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
	public EClass getOperation() {
		return operationEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getOperation_ReturnType() {
		return (EAttribute)operationEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getOperation_Parameters() {
		return (EReference)operationEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getOperation_Visibility() {
		return (EAttribute)operationEClass.getEStructuralFeatures().get(2);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getOperation_Static() {
		return (EAttribute)operationEClass.getEStructuralFeatures().get(3);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getOperation_Body() {
		return (EAttribute)operationEClass.getEStructuralFeatures().get(4);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getParameter() {
		return parameterEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getParameter_ValueType() {
		return (EAttribute)parameterEClass.getEStructuralFeatures().get(0);
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
	public EClass getMetaInfo() {
		return metaInfoEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getMetaInfo_Key() {
		return (EAttribute)metaInfoEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getMetaInfo_Value() {
		return (EAttribute)metaInfoEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getMetaInfo_ValueType() {
		return (EAttribute)metaInfoEClass.getEStructuralFeatures().get(2);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getMetaInfo_ConstraintType() {
		return (EAttribute)metaInfoEClass.getEStructuralFeatures().get(3);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getMetaInfo_ValueSet() {
		return (EAttribute)metaInfoEClass.getEStructuralFeatures().get(4);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getMetaInfoGroup() {
		return metaInfoGroupEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getMetaInfoGroup_Name() {
		return (EAttribute)metaInfoGroupEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getMetaInfoGroup_Contraints() {
		return (EReference)metaInfoGroupEClass.getEStructuralFeatures().get(1);
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
	public EAttribute getAssociationEnd_IsNavigable() {
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
	public EClass getFirstEnd() {
		return firstEndEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getSecondEnd() {
		return secondEndEClass;
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
	public EEnum getAttributeType() {
		return attributeTypeEEnum;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EEnum getVisibility() {
		return visibilityEEnum;
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
		createEReference(classModelElementEClass, CLASS_MODEL_ELEMENT__METAINFO);
		createEAttribute(classModelElementEClass, CLASS_MODEL_ELEMENT__DOCUMENTATION);
		createEReference(classModelElementEClass, CLASS_MODEL_ELEMENT__HAS_COMMENTS);

		namedClassModelElementEClass = createEClass(NAMED_CLASS_MODEL_ELEMENT);
		createEAttribute(namedClassModelElementEClass, NAMED_CLASS_MODEL_ELEMENT__NAME);
		createEAttribute(namedClassModelElementEClass, NAMED_CLASS_MODEL_ELEMENT__DESCRIPTION);

		classPackageEClass = createEClass(CLASS_PACKAGE);
		createEReference(classPackageEClass, CLASS_PACKAGE__CLASS_SET);
		createEReference(classPackageEClass, CLASS_PACKAGE__ASSOCIATION_SET);
		createEReference(classPackageEClass, CLASS_PACKAGE__ASPECT_SET);
		createEReference(classPackageEClass, CLASS_PACKAGE__ENUMERATION_SET);

		clazzEClass = createEClass(CLAZZ);
		createEReference(clazzEClass, CLAZZ__OPERATIONS);
		createEReference(clazzEClass, CLAZZ__GENERALIZATIONS);
		createEReference(clazzEClass, CLAZZ__ASPECTS);
		createEAttribute(clazzEClass, CLAZZ__IS_ABSTRACT);
		createEAttribute(clazzEClass, CLAZZ__IS_DEPRECATED);

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

		enumerationEClass = createEClass(ENUMERATION);
		createEReference(enumerationEClass, ENUMERATION__LITERALS);
		createEAttribute(enumerationEClass, ENUMERATION__IS_DYNAMIC);
		createEReference(enumerationEClass, ENUMERATION__DEPENDS);

		enumerationLiteralEClass = createEClass(ENUMERATION_LITERAL);
		createEAttribute(enumerationLiteralEClass, ENUMERATION_LITERAL__VALUE);
		createEAttribute(enumerationLiteralEClass, ENUMERATION_LITERAL__NAME);
		createEReference(enumerationLiteralEClass, ENUMERATION_LITERAL__ENUM);

		operationEClass = createEClass(OPERATION);
		createEAttribute(operationEClass, OPERATION__RETURN_TYPE);
		createEReference(operationEClass, OPERATION__PARAMETERS);
		createEAttribute(operationEClass, OPERATION__VISIBILITY);
		createEAttribute(operationEClass, OPERATION__STATIC);
		createEAttribute(operationEClass, OPERATION__BODY);

		parameterEClass = createEClass(PARAMETER);
		createEAttribute(parameterEClass, PARAMETER__VALUE_TYPE);

		aspectEClass = createEClass(ASPECT);

		abstractClassEClass = createEClass(ABSTRACT_CLASS);
		createEReference(abstractClassEClass, ABSTRACT_CLASS__ATTRIBUTES);

		metaInfoEClass = createEClass(META_INFO);
		createEAttribute(metaInfoEClass, META_INFO__KEY);
		createEAttribute(metaInfoEClass, META_INFO__VALUE);
		createEAttribute(metaInfoEClass, META_INFO__VALUE_TYPE);
		createEAttribute(metaInfoEClass, META_INFO__CONSTRAINT_TYPE);
		createEAttribute(metaInfoEClass, META_INFO__VALUE_SET);

		metaInfoGroupEClass = createEClass(META_INFO_GROUP);
		createEAttribute(metaInfoGroupEClass, META_INFO_GROUP__NAME);
		createEReference(metaInfoGroupEClass, META_INFO_GROUP__CONTRAINTS);

		titledNamedClassModelElementEClass = createEClass(TITLED_NAMED_CLASS_MODEL_ELEMENT);
		createEAttribute(titledNamedClassModelElementEClass, TITLED_NAMED_CLASS_MODEL_ELEMENT__TITLE);

		classCommentEClass = createEClass(CLASS_COMMENT);

		associationEndEClass = createEClass(ASSOCIATION_END);
		createEAttribute(associationEndEClass, ASSOCIATION_END__CARD_MIN);
		createEAttribute(associationEndEClass, ASSOCIATION_END__CARD_MAX);
		createEAttribute(associationEndEClass, ASSOCIATION_END__IS_NAVIGABLE);
		createEReference(associationEndEClass, ASSOCIATION_END__LINKED_CLASS);

		firstEndEClass = createEClass(FIRST_END);

		secondEndEClass = createEClass(SECOND_END);

		// Create enums
		associationTypeEEnum = createEEnum(ASSOCIATION_TYPE);
		attributeTypeEEnum = createEEnum(ATTRIBUTE_TYPE);
		visibilityEEnum = createEEnum(VISIBILITY);
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
		namedClassModelElementEClass.getESuperTypes().add(this.getClassModelElement());
		classPackageEClass.getESuperTypes().add(theCommonPackage.getPackage());
		clazzEClass.getESuperTypes().add(this.getAbstractClass());
		associationEClass.getESuperTypes().add(this.getTitledNamedClassModelElement());
		attributeEClass.getESuperTypes().add(this.getTitledNamedClassModelElement());
		enumerationEClass.getESuperTypes().add(this.getNamedClassModelElement());
		operationEClass.getESuperTypes().add(this.getNamedClassModelElement());
		parameterEClass.getESuperTypes().add(this.getNamedClassModelElement());
		aspectEClass.getESuperTypes().add(this.getAbstractClass());
		abstractClassEClass.getESuperTypes().add(this.getTitledNamedClassModelElement());
		titledNamedClassModelElementEClass.getESuperTypes().add(this.getNamedClassModelElement());
		classCommentEClass.getESuperTypes().add(theCommonPackage.getComment());
		associationEndEClass.getESuperTypes().add(this.getTitledNamedClassModelElement());
		firstEndEClass.getESuperTypes().add(this.getAssociationEnd());
		secondEndEClass.getESuperTypes().add(this.getAssociationEnd());

		// Initialize classes and features; add operations and parameters
		initEClass(classModelElementEClass, ClassModelElement.class, "ClassModelElement", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEReference(getClassModelElement_Metainfo(), this.getMetaInfo(), null, "metainfo", null, 0, -1, ClassModelElement.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getClassModelElement_Documentation(), ecorePackage.getEString(), "documentation", null, 0, 1, ClassModelElement.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getClassModelElement_HasComments(), this.getClassComment(), null, "hasComments", null, 0, -1, ClassModelElement.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(namedClassModelElementEClass, NamedClassModelElement.class, "NamedClassModelElement", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEAttribute(getNamedClassModelElement_Name(), ecorePackage.getEString(), "name", null, 0, 1, NamedClassModelElement.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getNamedClassModelElement_Description(), ecorePackage.getEString(), "description", null, 0, 1, NamedClassModelElement.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		addEOperation(namedClassModelElementEClass, ecorePackage.getEString(), "getFullName", 1, 1, IS_UNIQUE, IS_ORDERED);

		addEOperation(namedClassModelElementEClass, ecorePackage.getEString(), "getDescriptionOrName", 1, 1, IS_UNIQUE, IS_ORDERED);

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
		initEReference(getClazz_Operations(), this.getOperation(), null, "operations", null, 0, -1, Clazz.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getClazz_Generalizations(), this.getClazz(), null, "generalizations", null, 0, -1, Clazz.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getClazz_Aspects(), this.getAspect(), null, "aspects", null, 0, -1, Clazz.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getClazz_IsAbstract(), ecorePackage.getEBoolean(), "isAbstract", null, 0, 1, Clazz.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getClazz_IsDeprecated(), ecorePackage.getEBoolean(), "isDeprecated", null, 0, 1, Clazz.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		addEOperation(clazzEClass, this.getAttribute(), "getAllAttributes", 0, -1, IS_UNIQUE, IS_ORDERED);

		addEOperation(clazzEClass, this.getAttribute(), "getAllInheritedAttributes", 0, -1, IS_UNIQUE, IS_ORDERED);

		addEOperation(clazzEClass, this.getAttribute(), "getClassAndAspectAttributes", 0, -1, IS_UNIQUE, IS_ORDERED);

		addEOperation(clazzEClass, this.getClazz(), "getInheritedClasses", 0, -1, IS_UNIQUE, IS_ORDERED);

		addEOperation(clazzEClass, this.getAttribute(), "getAspectAttributes", 0, -1, IS_UNIQUE, IS_ORDERED);

		addEOperation(clazzEClass, this.getAttribute(), "getSubTypes", 0, -1, IS_UNIQUE, IS_ORDERED);

		addEOperation(clazzEClass, this.getAttribute(), "getAllInheritedClassAndAspectAttributes", 0, -1, IS_UNIQUE, IS_ORDERED);

		addEOperation(clazzEClass, this.getAssociation(), "getSourceAssociations", 0, -1, IS_UNIQUE, IS_ORDERED);

		EOperation op = addEOperation(clazzEClass, ecorePackage.getEBoolean(), "isSource", 1, 1, IS_UNIQUE, IS_ORDERED);
		addEParameter(op, this.getAssociation(), "asso", 1, 1, IS_UNIQUE, IS_ORDERED);

		op = addEOperation(clazzEClass, ecorePackage.getEBoolean(), "isTarget", 1, 1, IS_UNIQUE, IS_ORDERED);
		addEParameter(op, this.getAssociation(), "asso", 1, 1, IS_UNIQUE, IS_ORDERED);

		addEOperation(clazzEClass, this.getAssociation(), "getTargetAssociations", 0, -1, IS_UNIQUE, IS_ORDERED);

		addEOperation(clazzEClass, this.getAssociation(), "getAllSourceAssociations", 0, -1, IS_UNIQUE, IS_ORDERED);

		addEOperation(clazzEClass, this.getAssociation(), "getAllTargetAssociations", 0, -1, IS_UNIQUE, IS_ORDERED);

		addEOperation(clazzEClass, this.getAssociation(), "isClassAssociationsIn", 0, -1, IS_UNIQUE, IS_ORDERED);

		initEClass(associationEClass, Association.class, "Association", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEAttribute(getAssociation_AssociationType(), this.getAssociationType(), "associationType", null, 0, 1, Association.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getAssociation_FirstEnd(), this.getFirstEnd(), null, "firstEnd", null, 0, 1, Association.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getAssociation_SecondEnd(), this.getSecondEnd(), null, "secondEnd", null, 0, 1, Association.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		op = addEOperation(associationEClass, ecorePackage.getEBoolean(), "equalsForMerge", 0, 1, IS_UNIQUE, IS_ORDERED);
		addEParameter(op, this.getAssociation(), "other", 0, 1, IS_UNIQUE, IS_ORDERED);

		initEClass(attributeEClass, Attribute.class, "Attribute", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEAttribute(getAttribute_Typ(), this.getAttributeType(), "typ", "void", 0, 1, Attribute.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getAttribute_InitialValue(), ecorePackage.getEString(), "initialValue", null, 0, 1, Attribute.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getAttribute_Visibility(), this.getVisibility(), "visibility", "Private", 0, 1, Attribute.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getAttribute_ValueList(), this.getEnumeration(), null, "valueList", null, 0, 1, Attribute.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getAttribute_Unique(), ecorePackage.getEBoolean(), "unique", "false", 0, 1, Attribute.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(enumerationEClass, Enumeration.class, "Enumeration", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEReference(getEnumeration_Literals(), this.getEnumerationLiteral(), this.getEnumerationLiteral_Enum(), "literals", null, 0, -1, Enumeration.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getEnumeration_IsDynamic(), ecorePackage.getEBooleanObject(), "isDynamic", "false", 0, 1, Enumeration.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getEnumeration_Depends(), this.getEnumeration(), null, "depends", null, 0, 1, Enumeration.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(enumerationLiteralEClass, EnumerationLiteral.class, "EnumerationLiteral", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEAttribute(getEnumerationLiteral_Value(), ecorePackage.getEString(), "value", null, 0, 1, EnumerationLiteral.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getEnumerationLiteral_Name(), ecorePackage.getEString(), "name", null, 0, 1, EnumerationLiteral.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getEnumerationLiteral_Enum(), this.getEnumeration(), this.getEnumeration_Literals(), "enum", null, 0, 1, EnumerationLiteral.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(operationEClass, Operation.class, "Operation", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEAttribute(getOperation_ReturnType(), this.getAttributeType(), "returnType", null, 0, 1, Operation.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getOperation_Parameters(), this.getParameter(), null, "parameters", null, 0, -1, Operation.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getOperation_Visibility(), this.getVisibility(), "visibility", null, 0, 1, Operation.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getOperation_Static(), ecorePackage.getEBoolean(), "static", null, 0, 1, Operation.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getOperation_Body(), ecorePackage.getEString(), "body", null, 0, 1, Operation.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		op = addEOperation(operationEClass, ecorePackage.getEBoolean(), "equalsForMerge", 0, 1, IS_UNIQUE, IS_ORDERED);
		addEParameter(op, this.getOperation(), "other", 0, 1, IS_UNIQUE, IS_ORDERED);

		initEClass(parameterEClass, Parameter.class, "Parameter", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEAttribute(getParameter_ValueType(), this.getAttributeType(), "valueType", null, 0, 1, Parameter.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(aspectEClass, Aspect.class, "Aspect", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);

		op = addEOperation(aspectEClass, ecorePackage.getEBoolean(), "equalsForMerge", 0, 1, IS_UNIQUE, IS_ORDERED);
		addEParameter(op, this.getAspect(), "other", 0, 1, IS_UNIQUE, IS_ORDERED);

		initEClass(abstractClassEClass, AbstractClass.class, "AbstractClass", IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEReference(getAbstractClass_Attributes(), this.getAttribute(), null, "attributes", null, 0, -1, AbstractClass.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		op = addEOperation(abstractClassEClass, ecorePackage.getEBoolean(), "equalsForMerge", 0, 1, IS_UNIQUE, IS_ORDERED);
		addEParameter(op, this.getAbstractClass(), "other", 0, 1, IS_UNIQUE, IS_ORDERED);

		initEClass(metaInfoEClass, MetaInfo.class, "MetaInfo", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEAttribute(getMetaInfo_Key(), ecorePackage.getEString(), "key", null, 0, 1, MetaInfo.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getMetaInfo_Value(), ecorePackage.getEString(), "value", null, 0, 1, MetaInfo.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		EGenericType g1 = createEGenericType(ecorePackage.getEJavaClass());
		EGenericType g2 = createEGenericType();
		g1.getETypeArguments().add(g2);
		initEAttribute(getMetaInfo_ValueType(), g1, "valueType", null, 0, 1, MetaInfo.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getMetaInfo_ConstraintType(), this.getAttributeType(), "constraintType", null, 0, 1, MetaInfo.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getMetaInfo_ValueSet(), ecorePackage.getEJavaObject(), "valueSet", null, 0, 1, MetaInfo.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		op = addEOperation(metaInfoEClass, ecorePackage.getEBoolean(), "equalsForMerge", 0, 1, IS_UNIQUE, IS_ORDERED);
		addEParameter(op, this.getMetaInfo(), "other", 0, 1, IS_UNIQUE, IS_ORDERED);

		initEClass(metaInfoGroupEClass, MetaInfoGroup.class, "MetaInfoGroup", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEAttribute(getMetaInfoGroup_Name(), ecorePackage.getEString(), "name", null, 0, 1, MetaInfoGroup.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getMetaInfoGroup_Contraints(), this.getMetaInfo(), null, "contraints", null, 0, -1, MetaInfoGroup.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(titledNamedClassModelElementEClass, TitledNamedClassModelElement.class, "TitledNamedClassModelElement", IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEAttribute(getTitledNamedClassModelElement_Title(), ecorePackage.getEString(), "title", null, 0, 1, TitledNamedClassModelElement.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		addEOperation(titledNamedClassModelElementEClass, ecorePackage.getEString(), "getLabel", 1, 1, IS_UNIQUE, IS_ORDERED);

		initEClass(classCommentEClass, ClassComment.class, "ClassComment", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);

		initEClass(associationEndEClass, AssociationEnd.class, "AssociationEnd", IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEAttribute(getAssociationEnd_CardMin(), ecorePackage.getEString(), "cardMin", "0", 0, 1, AssociationEnd.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getAssociationEnd_CardMax(), ecorePackage.getEString(), "cardMax", "1", 0, 1, AssociationEnd.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getAssociationEnd_IsNavigable(), ecorePackage.getEBoolean(), "isNavigable", null, 0, 1, AssociationEnd.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getAssociationEnd_LinkedClass(), this.getClazz(), null, "linkedClass", null, 0, 1, AssociationEnd.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(firstEndEClass, FirstEnd.class, "FirstEnd", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);

		initEClass(secondEndEClass, SecondEnd.class, "SecondEnd", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);

		// Initialize enums and add enum literals
		initEEnum(associationTypeEEnum, AssociationType.class, "AssociationType");
		addEEnumLiteral(associationTypeEEnum, AssociationType.DIRECT);
		addEEnumLiteral(associationTypeEEnum, AssociationType.AGGREGATION);
		addEEnumLiteral(associationTypeEEnum, AssociationType.COMPOSITION);

		initEEnum(attributeTypeEEnum, AttributeType.class, "AttributeType");
		addEEnumLiteral(attributeTypeEEnum, AttributeType.BOOLEAN);
		addEEnumLiteral(attributeTypeEEnum, AttributeType.BYTE);
		addEEnumLiteral(attributeTypeEEnum, AttributeType.CHAR);
		addEEnumLiteral(attributeTypeEEnum, AttributeType.DOUBLE);
		addEEnumLiteral(attributeTypeEEnum, AttributeType.FLOAT);
		addEEnumLiteral(attributeTypeEEnum, AttributeType.INT);
		addEEnumLiteral(attributeTypeEEnum, AttributeType.LONG);
		addEEnumLiteral(attributeTypeEEnum, AttributeType.SHORT);
		addEEnumLiteral(attributeTypeEEnum, AttributeType.STRING);
		addEEnumLiteral(attributeTypeEEnum, AttributeType.VOID);
		addEEnumLiteral(attributeTypeEEnum, AttributeType.DATE);
		addEEnumLiteral(attributeTypeEEnum, AttributeType.OBJECT);
		addEEnumLiteral(attributeTypeEEnum, AttributeType.DATE_TIME);
		addEEnumLiteral(attributeTypeEEnum, AttributeType.TIME);

		initEEnum(visibilityEEnum, Visibility.class, "Visibility");
		addEEnumLiteral(visibilityEEnum, Visibility.PUBLIC);
		addEEnumLiteral(visibilityEEnum, Visibility.PRIVATE);
		addEEnumLiteral(visibilityEEnum, Visibility.PROTECTED);

		// Create resource
		createResource(eNS_URI);

		// Create annotations
		// http://www.bluexml.com/OCL
		createOCLAnnotations();
		// http://www.eclipse.org/emf/2002/Ecore
		createEcoreAnnotations();
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
		  (namedClassModelElementEClass.getEOperations().get(0), 
		   source, 
		   new String[] {
			 "body", "if self.getContainer().oclIsUndefined() then\r\tself.name\relse\r\tif self.getContainer().oclIsKindOf(NamedClassModelElement) then\r\t\tself.getContainer().oclAsType(NamedClassModelElement).getFullName().concat(\'.\').concat(self.name)\r\telse\r\t\tself.getContainer().oclAsType(ClassPackage).getFullName().concat(\'.\').concat(self.name)\r\tendif\t\rendif"
		   });		
		addAnnotation
		  (namedClassModelElementEClass.getEOperations().get(1), 
		   source, 
		   new String[] {
			 "body", "if self.description.oclIsUndefined() or self.description.size() <0 then\r\tself.name\relse\r\tself.description\rendif"
		   });		
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
			 "InheritanceCycle", "not self.generalizations.generalizations -> includes(self)",
			 "ClassWithTwoAttributesSameName", "self.attributes -> forAll( a1, a2 | a1 <> a2 implies a1.name <>a2.name)"
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
			 "body", "self.getInheritedClasses() ->asSet() ->iterate(cl:Clazz;result:Set(Attribute)=Set{}|result->union(cl.attributes) ->asSet()))",
			 "description", "search attributes than is describe in inherited classes (without Aspects)"
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
			 "body", "Association.allInstances() ->select(c:Association|self.isSource(c))",
			 "description", "search association where this clazz is source"
		   });		
		addAnnotation
		  (clazzEClass.getEOperations().get(8), 
		   source, 
		   new String[] {
			 "body", "(asso.firstEnd.linkedClass = self and asso.firstEnd.isNavigable) or (asso.secondEnd.linkedClass = self and asso.secondEnd.isNavigable)",
			 "description", "search for class attributes, inherited one and finaly added to the class by aspect"
		   });		
		addAnnotation
		  (clazzEClass.getEOperations().get(9), 
		   source, 
		   new String[] {
			 "body", "(asso.firstEnd.linkedClass = self and asso.isNavigable) or (asso.secondEnd.linkedClass = self and asso.isNavigable)",
			 "description", "search for class attributes, inherited one and finaly added to the class by aspect"
		   });		
		addAnnotation
		  (clazzEClass.getEOperations().get(10), 
		   source, 
		   new String[] {
			 "body", "Association.allInstances() ->select(c:Association|self.isTarget(c))",
			 "description", "search association where this clazz is target"
		   });		
		addAnnotation
		  (clazzEClass.getEOperations().get(11), 
		   source, 
		   new String[] {
			 "body", "self.getInheritedClasses() -> including(self) ->iterate(e:Clazz;result:Set(Association)=Set{}|result->union(e.getSourceAssociations()))",
			 "description", "search association where this clazz is source"
		   });		
		addAnnotation
		  (clazzEClass.getEOperations().get(12), 
		   source, 
		   new String[] {
			 "body", "self.getInheritedClasses() -> including(self) ->iterate(e:Clazz;result:Set(Association)=Set{}|result->union(e.getTargetAssociations()))",
			 "description", "search associations where this clazz is source or one of inheritedClass"
		   });		
		addAnnotation
		  (clazzEClass.getEOperations().get(13), 
		   source, 
		   new String[] {
			 "body", "Association.allInstances() ->select(e:Association| self.getInheritedClasses() ->including(self) -> includesAll(e.associationsClass))",
			 "description", "give the list of associations where this clazz is the associatedClazz"
		   });		
		addAnnotation
		  (associationEClass, 
		   source, 
		   new String[] {
			 "recursiveAssociationMustHaveRole", "( self.source = self.destination and self.isNavigableSRC and self.isNavigableTARGET ) implies ( ( not self.roleSrc.oclIsUndefined() and self.roleSrc <> \'\' ) or ( not self.roleTarget.oclIsUndefined() and self.roleTarget <> \'\' ))",
			 "AssociatioClassCantBeAgregationOrComposition", "(self.associationType = AssociationType::Aggregation or self.associationType = AssociationType::Composition) implies self.associationsClass->isEmpty()",
			 "MinAndMaxTarget", "( self.maxTARGET <> \'-1\' ) implies ( self.minTARGET <= self.maxTARGET )",
			 "MinAndMaxSource", "( self.maxSRC <> \'-1\' ) implies ( self.minSRC <= self.maxSRC )",
			 "NameNull", "not self.name.oclIsUndefined() and self.name <> \'\'",
			 "SourceNull", "self.source->notEmpty()",
			 "TargetNull", "self.destination->notEmpty()",
			 "AtLeastOneNavigableEdge", "(isNavigableSRC or isNavigableTARGET)",
			 "ClassCantBeReferencedbyTwoSameNameAssociation", "Association.allInstances()->select(a | a.name = self.name and  a.source = self.source  and a.destination = self.destination and a <> self and self.isNavigableSRC=a.isNavigableSRC and self.isNavigableSRC=true)->size() = 0\nand  \nAssociation.allInstances()->select(a | a.name = self.name and  a.source = self.source  and a.destination = self.destination and a <> self and self.isNavigableTARGET=a.isNavigableTARGET and self.isNavigableTARGET=true)->size() = 0\nand \nAssociation.allInstances()->select(a | a.name = self.name and  a.source = self.destination  and a.destination = self.source and a <> self and self.isNavigableSRC=a.isNavigableTARGET and self.isNavigableSRC=true)->size() = 0\nand \nAssociation.allInstances()->select(a | a.name = self.name and  a.source = self.destination  and a.destination = self.source and a <> self and self.isNavigableTARGET=a.isNavigableSRC and self.isNavigableTARGET=true)->size() = 0\n",
			 "IfAggregationOrCompositionThenUnidirectionalAssociation", "(self.associationType <> AssociationType::Direct) implies (self.isNavigableSRC xor self.isNavigableTARGET )"
		   });			
		addAnnotation
		  (associationEClass.getEOperations().get(0), 
		   source, 
		   new String[] {
			 "body", "if( self.destination.oclIsKindOf(Classe))\r\nthen\r\nself.destination.oclAsType(Classe).equalsForMerge(other.destination.oclAsType(Classe)) and self.source.oclAsType(Classe).equalsForMerge(other.source.oclAsType(Classe))\r\nand self.name = other.name\r\nelse\r\ntrue\r\nendif\r\n"
		   });		
		addAnnotation
		  (operationEClass.getEOperations().get(0), 
		   source, 
		   new String[] {
			 "body", "\r\nself.name = other.name and \r\n\r\nself.parameters->forAll(p:Parameter |   \r\n   other.parameters->exists(z : Parameter | z.name = p.name and z.valueType = p.valueType ))"
		   });		
		addAnnotation
		  (aspectEClass, 
		   source, 
		   new String[] {
			 "AspectWithTwoAttributesSameName", "self.attributes -> forAll( a1, a2 | a1 <> a2 implies a1.name <>a2.name)"
		   });			
		addAnnotation
		  (aspectEClass.getEOperations().get(0), 
		   source, 
		   new String[] {
			 "body", "self.name = other.name and self.title = other.title"
		   });		
		addAnnotation
		  (abstractClassEClass.getEOperations().get(0), 
		   source, 
		   new String[] {
			 "body", "self.name = other.name and self.title = other.title"
		   });		
		addAnnotation
		  (metaInfoEClass.getEOperations().get(0), 
		   source, 
		   new String[] {
			 "body", "self.key = other.key"
		   });		
		addAnnotation
		  (titledNamedClassModelElementEClass.getEOperations().get(0), 
		   source, 
		   new String[] {
			 "body", "if self.title.oclIsUndefined() or self.title.size() <0 then\r\tself.name\relse\r\tself.title\rendif"
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
			 "constraints", "recursiveAssociationMustHaveRole MinAndMaxTarget MinAndMaxSource AssociatioClassCantBeAgregationOrComposition NameNull SourceNull TargetNull AtLeastOneNavigableEdge ClassCantBeReferencedbyTwoSameNameAssociation IfAggregationOrCompositionThenUnidirectionalAssociation"
		   });					
		addAnnotation
		  (aspectEClass, 
		   source, 
		   new String[] {
			 "constraints", "AspectWithTwoAttributesSameName"
		   });				
	}

} //ClazzPackageImpl
