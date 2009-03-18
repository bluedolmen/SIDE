/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package com.bluexml.side.clazz.impl;

import com.bluexml.side.clazz.AbstractClass;
import com.bluexml.side.clazz.AbstractContainer;
import com.bluexml.side.clazz.Aspect;
import com.bluexml.side.clazz.Association;
import com.bluexml.side.clazz.AssociationType;
import com.bluexml.side.clazz.Attribute;
import com.bluexml.side.clazz.AttributeType;
import com.bluexml.side.clazz.ClassModelElement;
import com.bluexml.side.clazz.ClassPackage;
import com.bluexml.side.clazz.Clazz;
import com.bluexml.side.clazz.ClazzFactory;
import com.bluexml.side.clazz.ClazzPackage;
import com.bluexml.side.clazz.Enumeration;
import com.bluexml.side.clazz.EnumerationLiteral;
import com.bluexml.side.clazz.MetaInfo;
import com.bluexml.side.clazz.MetaInfoGroup;
import com.bluexml.side.clazz.NamedClassModelElement;
import com.bluexml.side.clazz.Operation;
import com.bluexml.side.clazz.Parameter;
import com.bluexml.side.clazz.View;
import com.bluexml.side.clazz.ViewItem;
import com.bluexml.side.clazz.Visibility;

import com.bluexml.side.common.CommonPackage;

import org.eclipse.emf.ecore.EAttribute;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EEnum;
import org.eclipse.emf.ecore.EGenericType;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.EReference;

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
	private EClass abstractContainerEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass viewEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass viewItemEClass = null;

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
	public EReference getClassPackage_Views() {
		return (EReference)classPackageEClass.getEStructuralFeatures().get(4);
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
	public EReference getClazz_HasView() {
		return (EReference)clazzEClass.getEStructuralFeatures().get(5);
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
	public EReference getAssociation_Source() {
		return (EReference)associationEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getAssociation_Destination() {
		return (EReference)associationEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getAssociation_IsNavigableSRC() {
		return (EAttribute)associationEClass.getEStructuralFeatures().get(2);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getAssociation_IsNavigableTARGET() {
		return (EAttribute)associationEClass.getEStructuralFeatures().get(3);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getAssociation_MinSRC() {
		return (EAttribute)associationEClass.getEStructuralFeatures().get(4);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getAssociation_MaxSRC() {
		return (EAttribute)associationEClass.getEStructuralFeatures().get(5);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getAssociation_MinTARGET() {
		return (EAttribute)associationEClass.getEStructuralFeatures().get(6);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getAssociation_MaxTARGET() {
		return (EAttribute)associationEClass.getEStructuralFeatures().get(7);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getAssociation_AssociationType() {
		return (EAttribute)associationEClass.getEStructuralFeatures().get(8);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getAssociation_AssociationsClass() {
		return (EReference)associationEClass.getEStructuralFeatures().get(9);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getAssociation_RoleSrc() {
		return (EAttribute)associationEClass.getEStructuralFeatures().get(10);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getAssociation_RoleTarget() {
		return (EAttribute)associationEClass.getEStructuralFeatures().get(11);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getAssociation_Title() {
		return (EAttribute)associationEClass.getEStructuralFeatures().get(12);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getAssociation_RoleSrcTitle() {
		return (EAttribute)associationEClass.getEStructuralFeatures().get(13);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getAssociation_RoleTargetTitle() {
		return (EAttribute)associationEClass.getEStructuralFeatures().get(14);
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
	public EAttribute getAttribute_Title() {
		return (EAttribute)attributeEClass.getEStructuralFeatures().get(3);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getAttribute_ValueList() {
		return (EReference)attributeEClass.getEStructuralFeatures().get(4);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getAttribute_Unique() {
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
	public EClass getAbstractContainer() {
		return abstractContainerEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getAbstractContainer_Title() {
		return (EAttribute)abstractContainerEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getAbstractContainer_Attributes() {
		return (EReference)abstractContainerEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getAbstractContainer_Associations() {
		return (EReference)abstractContainerEClass.getEStructuralFeatures().get(2);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getView() {
		return viewEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getView_Attributes() {
		return (EReference)viewEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getViewItem() {
		return viewItemEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getViewItem_Association() {
		return (EReference)viewItemEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getViewItem_Attribute() {
		return (EReference)viewItemEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getViewItem_Classe() {
		return (EReference)viewItemEClass.getEStructuralFeatures().get(2);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getViewItem_Aspect() {
		return (EReference)viewItemEClass.getEStructuralFeatures().get(3);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getViewItem_Role() {
		return (EAttribute)viewItemEClass.getEStructuralFeatures().get(4);
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

		namedClassModelElementEClass = createEClass(NAMED_CLASS_MODEL_ELEMENT);
		createEAttribute(namedClassModelElementEClass, NAMED_CLASS_MODEL_ELEMENT__NAME);
		createEAttribute(namedClassModelElementEClass, NAMED_CLASS_MODEL_ELEMENT__DESCRIPTION);

		classPackageEClass = createEClass(CLASS_PACKAGE);
		createEReference(classPackageEClass, CLASS_PACKAGE__CLASS_SET);
		createEReference(classPackageEClass, CLASS_PACKAGE__ASSOCIATION_SET);
		createEReference(classPackageEClass, CLASS_PACKAGE__ASPECT_SET);
		createEReference(classPackageEClass, CLASS_PACKAGE__ENUMERATION_SET);
		createEReference(classPackageEClass, CLASS_PACKAGE__VIEWS);

		clazzEClass = createEClass(CLAZZ);
		createEReference(clazzEClass, CLAZZ__OPERATIONS);
		createEReference(clazzEClass, CLAZZ__GENERALIZATIONS);
		createEReference(clazzEClass, CLAZZ__ASPECTS);
		createEAttribute(clazzEClass, CLAZZ__IS_ABSTRACT);
		createEAttribute(clazzEClass, CLAZZ__IS_DEPRECATED);
		createEReference(clazzEClass, CLAZZ__HAS_VIEW);

		associationEClass = createEClass(ASSOCIATION);
		createEReference(associationEClass, ASSOCIATION__SOURCE);
		createEReference(associationEClass, ASSOCIATION__DESTINATION);
		createEAttribute(associationEClass, ASSOCIATION__IS_NAVIGABLE_SRC);
		createEAttribute(associationEClass, ASSOCIATION__IS_NAVIGABLE_TARGET);
		createEAttribute(associationEClass, ASSOCIATION__MIN_SRC);
		createEAttribute(associationEClass, ASSOCIATION__MAX_SRC);
		createEAttribute(associationEClass, ASSOCIATION__MIN_TARGET);
		createEAttribute(associationEClass, ASSOCIATION__MAX_TARGET);
		createEAttribute(associationEClass, ASSOCIATION__ASSOCIATION_TYPE);
		createEReference(associationEClass, ASSOCIATION__ASSOCIATIONS_CLASS);
		createEAttribute(associationEClass, ASSOCIATION__ROLE_SRC);
		createEAttribute(associationEClass, ASSOCIATION__ROLE_TARGET);
		createEAttribute(associationEClass, ASSOCIATION__TITLE);
		createEAttribute(associationEClass, ASSOCIATION__ROLE_SRC_TITLE);
		createEAttribute(associationEClass, ASSOCIATION__ROLE_TARGET_TITLE);

		attributeEClass = createEClass(ATTRIBUTE);
		createEAttribute(attributeEClass, ATTRIBUTE__TYP);
		createEAttribute(attributeEClass, ATTRIBUTE__INITIAL_VALUE);
		createEAttribute(attributeEClass, ATTRIBUTE__VISIBILITY);
		createEAttribute(attributeEClass, ATTRIBUTE__TITLE);
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

		abstractContainerEClass = createEClass(ABSTRACT_CONTAINER);
		createEAttribute(abstractContainerEClass, ABSTRACT_CONTAINER__TITLE);
		createEReference(abstractContainerEClass, ABSTRACT_CONTAINER__ATTRIBUTES);
		createEReference(abstractContainerEClass, ABSTRACT_CONTAINER__ASSOCIATIONS);

		viewEClass = createEClass(VIEW);
		createEReference(viewEClass, VIEW__ATTRIBUTES);

		viewItemEClass = createEClass(VIEW_ITEM);
		createEReference(viewItemEClass, VIEW_ITEM__ASSOCIATION);
		createEReference(viewItemEClass, VIEW_ITEM__ATTRIBUTE);
		createEReference(viewItemEClass, VIEW_ITEM__CLASSE);
		createEReference(viewItemEClass, VIEW_ITEM__ASPECT);
		createEAttribute(viewItemEClass, VIEW_ITEM__ROLE);

		metaInfoEClass = createEClass(META_INFO);
		createEAttribute(metaInfoEClass, META_INFO__KEY);
		createEAttribute(metaInfoEClass, META_INFO__VALUE);
		createEAttribute(metaInfoEClass, META_INFO__VALUE_TYPE);
		createEAttribute(metaInfoEClass, META_INFO__CONSTRAINT_TYPE);
		createEAttribute(metaInfoEClass, META_INFO__VALUE_SET);

		metaInfoGroupEClass = createEClass(META_INFO_GROUP);
		createEAttribute(metaInfoGroupEClass, META_INFO_GROUP__NAME);
		createEReference(metaInfoGroupEClass, META_INFO_GROUP__CONTRAINTS);

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
		associationEClass.getESuperTypes().add(this.getNamedClassModelElement());
		attributeEClass.getESuperTypes().add(this.getNamedClassModelElement());
		enumerationEClass.getESuperTypes().add(this.getNamedClassModelElement());
		operationEClass.getESuperTypes().add(this.getNamedClassModelElement());
		parameterEClass.getESuperTypes().add(this.getNamedClassModelElement());
		aspectEClass.getESuperTypes().add(this.getAbstractClass());
		abstractClassEClass.getESuperTypes().add(this.getAbstractContainer());
		abstractContainerEClass.getESuperTypes().add(this.getNamedClassModelElement());
		viewEClass.getESuperTypes().add(this.getNamedClassModelElement());

		// Initialize classes and features; add operations and parameters
		initEClass(classModelElementEClass, ClassModelElement.class, "ClassModelElement", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEReference(getClassModelElement_Metainfo(), this.getMetaInfo(), null, "metainfo", null, 0, -1, ClassModelElement.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getClassModelElement_Documentation(), ecorePackage.getEString(), "documentation", null, 0, 1, ClassModelElement.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(namedClassModelElementEClass, NamedClassModelElement.class, "NamedClassModelElement", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEAttribute(getNamedClassModelElement_Name(), ecorePackage.getEString(), "name", null, 0, 1, NamedClassModelElement.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getNamedClassModelElement_Description(), ecorePackage.getEString(), "description", null, 0, 1, NamedClassModelElement.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		addEOperation(namedClassModelElementEClass, ecorePackage.getEString(), "getFullName", 0, 1, IS_UNIQUE, IS_ORDERED);

		initEClass(classPackageEClass, ClassPackage.class, "ClassPackage", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEReference(getClassPackage_ClassSet(), this.getClazz(), null, "classSet", null, 0, -1, ClassPackage.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getClassPackage_AssociationSet(), this.getAssociation(), null, "associationSet", null, 0, -1, ClassPackage.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getClassPackage_AspectSet(), this.getAspect(), null, "aspectSet", null, 0, -1, ClassPackage.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getClassPackage_EnumerationSet(), this.getEnumeration(), null, "enumerationSet", null, 0, -1, ClassPackage.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getClassPackage_Views(), this.getView(), null, "views", null, 0, -1, ClassPackage.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		addEOperation(classPackageEClass, ecorePackage.getEString(), "getFullName", 0, 1, IS_UNIQUE, IS_ORDERED);

		initEClass(clazzEClass, Clazz.class, "Clazz", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEReference(getClazz_Operations(), this.getOperation(), null, "operations", null, 0, -1, Clazz.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getClazz_Generalizations(), this.getClazz(), null, "generalizations", null, 0, -1, Clazz.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getClazz_Aspects(), this.getAspect(), null, "aspects", null, 0, -1, Clazz.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getClazz_IsAbstract(), ecorePackage.getEBoolean(), "isAbstract", null, 0, 1, Clazz.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getClazz_IsDeprecated(), ecorePackage.getEBoolean(), "isDeprecated", null, 0, 1, Clazz.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getClazz_HasView(), this.getView(), null, "hasView", null, 0, -1, Clazz.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(associationEClass, Association.class, "Association", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEReference(getAssociation_Source(), this.getClassModelElement(), null, "source", null, 0, 1, Association.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getAssociation_Destination(), this.getClassModelElement(), null, "destination", null, 0, 1, Association.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getAssociation_IsNavigableSRC(), ecorePackage.getEBoolean(), "isNavigableSRC", null, 0, 1, Association.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getAssociation_IsNavigableTARGET(), ecorePackage.getEBoolean(), "isNavigableTARGET", null, 0, 1, Association.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getAssociation_MinSRC(), ecorePackage.getEString(), "minSRC", "1", 0, 1, Association.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getAssociation_MaxSRC(), ecorePackage.getEString(), "maxSRC", "1", 0, 1, Association.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getAssociation_MinTARGET(), ecorePackage.getEString(), "minTARGET", "1", 0, 1, Association.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getAssociation_MaxTARGET(), ecorePackage.getEString(), "maxTARGET", "1", 0, 1, Association.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getAssociation_AssociationType(), this.getAssociationType(), "associationType", null, 0, 1, Association.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getAssociation_AssociationsClass(), this.getClazz(), null, "associationsClass", null, 0, -1, Association.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getAssociation_RoleSrc(), ecorePackage.getEString(), "roleSrc", null, 0, 1, Association.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getAssociation_RoleTarget(), ecorePackage.getEString(), "roleTarget", "", 0, 1, Association.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getAssociation_Title(), ecorePackage.getEString(), "title", null, 0, 1, Association.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getAssociation_RoleSrcTitle(), ecorePackage.getEString(), "roleSrcTitle", null, 0, 1, Association.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getAssociation_RoleTargetTitle(), ecorePackage.getEString(), "roleTargetTitle", "", 0, 1, Association.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(attributeEClass, Attribute.class, "Attribute", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEAttribute(getAttribute_Typ(), this.getAttributeType(), "typ", "void", 0, 1, Attribute.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getAttribute_InitialValue(), ecorePackage.getEString(), "initialValue", null, 0, 1, Attribute.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getAttribute_Visibility(), this.getVisibility(), "visibility", "Private", 0, 1, Attribute.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getAttribute_Title(), ecorePackage.getEString(), "title", null, 0, 1, Attribute.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
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

		initEClass(parameterEClass, Parameter.class, "Parameter", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEAttribute(getParameter_ValueType(), this.getAttributeType(), "valueType", null, 0, 1, Parameter.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(aspectEClass, Aspect.class, "Aspect", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);

		initEClass(abstractClassEClass, AbstractClass.class, "AbstractClass", IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);

		initEClass(abstractContainerEClass, AbstractContainer.class, "AbstractContainer", IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEAttribute(getAbstractContainer_Title(), ecorePackage.getEString(), "title", null, 0, 1, AbstractContainer.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getAbstractContainer_Attributes(), this.getAttribute(), null, "attributes", null, 0, -1, AbstractContainer.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getAbstractContainer_Associations(), this.getAssociation(), null, "associations", null, 0, -1, AbstractContainer.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(viewEClass, View.class, "View", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEReference(getView_Attributes(), this.getViewItem(), null, "attributes", null, 0, -1, View.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(viewItemEClass, ViewItem.class, "ViewItem", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEReference(getViewItem_Association(), this.getAssociation(), null, "association", null, 0, 1, ViewItem.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getViewItem_Attribute(), this.getAttribute(), null, "attribute", null, 0, 1, ViewItem.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getViewItem_Classe(), this.getClazz(), null, "classe", null, 0, 1, ViewItem.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getViewItem_Aspect(), this.getAspect(), null, "aspect", null, 0, 1, ViewItem.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getViewItem_Role(), ecorePackage.getEString(), "Role", null, 0, 1, ViewItem.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(metaInfoEClass, MetaInfo.class, "MetaInfo", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEAttribute(getMetaInfo_Key(), ecorePackage.getEString(), "key", null, 0, 1, MetaInfo.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getMetaInfo_Value(), ecorePackage.getEString(), "value", null, 0, 1, MetaInfo.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		EGenericType g1 = createEGenericType(ecorePackage.getEJavaClass());
		EGenericType g2 = createEGenericType();
		g1.getETypeArguments().add(g2);
		initEAttribute(getMetaInfo_ValueType(), g1, "valueType", null, 0, 1, MetaInfo.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getMetaInfo_ConstraintType(), this.getAttributeType(), "constraintType", null, 0, 1, MetaInfo.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getMetaInfo_ValueSet(), ecorePackage.getEJavaObject(), "valueSet", null, 0, 1, MetaInfo.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(metaInfoGroupEClass, MetaInfoGroup.class, "MetaInfoGroup", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEAttribute(getMetaInfoGroup_Name(), ecorePackage.getEString(), "name", null, 0, 1, MetaInfoGroup.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getMetaInfoGroup_Contraints(), this.getMetaInfo(), null, "contraints", null, 0, -1, MetaInfoGroup.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

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
		  (classPackageEClass.getEOperations().get(0), 
		   source, 
		   new String[] {
			 "body", "if self.getContainer().oclIsUndefined() then\r\tself.name\relse\r\tself.getContainer().oclAsType(ClassPackage).getFullName().concat(\'.\').concat(self.name)\rendif"
		   });
	}

} //ClazzPackageImpl
