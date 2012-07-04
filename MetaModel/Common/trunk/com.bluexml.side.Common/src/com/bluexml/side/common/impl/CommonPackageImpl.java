/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package com.bluexml.side.common.impl;

import static com.bluexml.side.common.CommonPackage.CONTAINER;

import org.eclipse.emf.ecore.EAttribute;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EEnum;
import org.eclipse.emf.ecore.EGenericType;
import org.eclipse.emf.ecore.EOperation;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.EReference;
import org.eclipse.emf.ecore.impl.EPackageImpl;

import com.bluexml.side.common.Comment;
import com.bluexml.side.common.CommonFactory;
import com.bluexml.side.common.CommonPackage;
import com.bluexml.side.common.Constraint;
import com.bluexml.side.common.ConstraintParam;
import com.bluexml.side.common.CustomDataType;
import com.bluexml.side.common.DataType;
import com.bluexml.side.common.MetaData;
import com.bluexml.side.common.MetaInfo;
import com.bluexml.side.common.MetaInfoGroup;
import com.bluexml.side.common.ModelElement;
import com.bluexml.side.common.NameSpace;
import com.bluexml.side.common.NamedModelElement;
import com.bluexml.side.common.Operation;
import com.bluexml.side.common.OperationComponent;
import com.bluexml.side.common.OperationGroup;
import com.bluexml.side.common.Parameter;
import com.bluexml.side.common.Stereotype;
import com.bluexml.side.common.Tag;
import com.bluexml.side.common.Visibility;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model <b>Package</b>.
 * <!-- end-user-doc -->
 * @generated
 */
public class CommonPackageImpl extends EPackageImpl implements CommonPackage {
	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass modelElementEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass namedModelElementEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass commentEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass tagEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass stereotypeEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass packageEClass = null;

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
	private EClass operationGroupEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass operationComponentEClass = null;

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
	private EClass metaDataEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass containerEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass constraintEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass constraintParamEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass nameSpaceEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass customDataTypeEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EEnum dataTypeEEnum = null;

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
	 * @see com.bluexml.side.common.CommonPackage#eNS_URI
	 * @see #init()
	 * @generated
	 */
	private CommonPackageImpl() {
		super(eNS_URI, CommonFactory.eINSTANCE);
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
	 * <p>This method is used to initialize {@link CommonPackage#eINSTANCE} when that field is accessed.
	 * Clients should not invoke it directly. Instead, they should simply access that field to obtain the package.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #eNS_URI
	 * @see #createPackageContents()
	 * @see #initializePackageContents()
	 * @generated
	 */
	public static CommonPackage init() {
		if (isInited) return (CommonPackage)EPackage.Registry.INSTANCE.getEPackage(CommonPackage.eNS_URI);

		// Obtain or create and register package
		CommonPackageImpl theCommonPackage = (CommonPackageImpl)(EPackage.Registry.INSTANCE.get(eNS_URI) instanceof CommonPackageImpl ? EPackage.Registry.INSTANCE.get(eNS_URI) : new CommonPackageImpl());

		isInited = true;

		// Create package meta-data objects
		theCommonPackage.createPackageContents();

		// Initialize created meta-data
		theCommonPackage.initializePackageContents();

		// Mark meta-data to indicate it can't be changed
		theCommonPackage.freeze();

  
		// Update the registry and return the package
		EPackage.Registry.INSTANCE.put(CommonPackage.eNS_URI, theCommonPackage);
		return theCommonPackage;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getModelElement() {
		return modelElementEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getModelElement_Stereotypes() {
		return (EReference)modelElementEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getModelElement_Tags() {
		return (EReference)modelElementEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getModelElement_Comments() {
		return (EReference)modelElementEClass.getEStructuralFeatures().get(2);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getModelElement_Documentation() {
		return (EAttribute)modelElementEClass.getEStructuralFeatures().get(3);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getModelElement_Description() {
		return (EAttribute)modelElementEClass.getEStructuralFeatures().get(4);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getModelElement_Metainfo() {
		return (EReference)modelElementEClass.getEStructuralFeatures().get(5);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getModelElement_Constraints() {
		return (EReference)modelElementEClass.getEStructuralFeatures().get(6);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getModelElement_Namespace() {
		return (EReference)modelElementEClass.getEStructuralFeatures().get(7);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getNamedModelElement() {
		return namedModelElementEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getNamedModelElement_Name() {
		return (EAttribute)namedModelElementEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getComment() {
		return commentEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getComment_Value() {
		return (EAttribute)commentEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getTag() {
		return tagEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getTag_Key() {
		return (EAttribute)tagEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getTag_Value() {
		return (EAttribute)tagEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getStereotype() {
		return stereotypeEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getPackage() {
		return packageEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getPackage_StereotypeSet() {
		return (EReference)packageEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getPackage_PackageSet() {
		return (EReference)packageEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getPackage_NamespaceSet() {
		return (EReference)packageEClass.getEStructuralFeatures().get(2);
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
	public EClass getOperationGroup() {
		return operationGroupEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getOperationGroup_Children() {
		return (EReference)operationGroupEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getOperationComponent() {
		return operationComponentEClass;
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
	public EAttribute getMetaInfo_MultilineValue() {
		return (EAttribute)metaInfoEClass.getEStructuralFeatures().get(5);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getMetaInfo_EObjectValue() {
		return (EReference)metaInfoEClass.getEStructuralFeatures().get(6);
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
	public EReference getMetaInfoGroup_Children() {
		return (EReference)metaInfoGroupEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getMetaData() {
		return metaDataEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getContainer() {
		return containerEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getConstraint() {
		return constraintEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getConstraint_ConstraintType() {
		return (EAttribute)constraintEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getConstraint_Params() {
		return (EReference)constraintEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getConstraintParam() {
		return constraintParamEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getConstraintParam_Values() {
		return (EAttribute)constraintParamEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getNameSpace() {
		return nameSpaceEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getNameSpace_Prefix() {
		return (EAttribute)nameSpaceEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getNameSpace_URI() {
		return (EAttribute)nameSpaceEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getCustomDataType() {
		return customDataTypeEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getCustomDataType_DataTypeImp() {
		return (EAttribute)customDataTypeEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EEnum getDataType() {
		return dataTypeEEnum;
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
	public CommonFactory getCommonFactory() {
		return (CommonFactory)getEFactoryInstance();
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
		modelElementEClass = createEClass(MODEL_ELEMENT);
		createEReference(modelElementEClass, MODEL_ELEMENT__STEREOTYPES);
		createEReference(modelElementEClass, MODEL_ELEMENT__TAGS);
		createEReference(modelElementEClass, MODEL_ELEMENT__COMMENTS);
		createEAttribute(modelElementEClass, MODEL_ELEMENT__DOCUMENTATION);
		createEAttribute(modelElementEClass, MODEL_ELEMENT__DESCRIPTION);
		createEReference(modelElementEClass, MODEL_ELEMENT__METAINFO);
		createEReference(modelElementEClass, MODEL_ELEMENT__CONSTRAINTS);
		createEReference(modelElementEClass, MODEL_ELEMENT__NAMESPACE);

		namedModelElementEClass = createEClass(NAMED_MODEL_ELEMENT);
		createEAttribute(namedModelElementEClass, NAMED_MODEL_ELEMENT__NAME);

		commentEClass = createEClass(COMMENT);
		createEAttribute(commentEClass, COMMENT__VALUE);

		tagEClass = createEClass(TAG);
		createEAttribute(tagEClass, TAG__KEY);
		createEAttribute(tagEClass, TAG__VALUE);

		stereotypeEClass = createEClass(STEREOTYPE);

		packageEClass = createEClass(PACKAGE);
		createEReference(packageEClass, PACKAGE__STEREOTYPE_SET);
		createEReference(packageEClass, PACKAGE__PACKAGE_SET);
		createEReference(packageEClass, PACKAGE__NAMESPACE_SET);

		operationEClass = createEClass(OPERATION);
		createEAttribute(operationEClass, OPERATION__RETURN_TYPE);
		createEReference(operationEClass, OPERATION__PARAMETERS);
		createEAttribute(operationEClass, OPERATION__VISIBILITY);
		createEAttribute(operationEClass, OPERATION__STATIC);
		createEAttribute(operationEClass, OPERATION__BODY);

		parameterEClass = createEClass(PARAMETER);
		createEAttribute(parameterEClass, PARAMETER__VALUE_TYPE);

		operationGroupEClass = createEClass(OPERATION_GROUP);
		createEReference(operationGroupEClass, OPERATION_GROUP__CHILDREN);

		operationComponentEClass = createEClass(OPERATION_COMPONENT);

		metaInfoEClass = createEClass(META_INFO);
		createEAttribute(metaInfoEClass, META_INFO__KEY);
		createEAttribute(metaInfoEClass, META_INFO__VALUE);
		createEAttribute(metaInfoEClass, META_INFO__VALUE_TYPE);
		createEAttribute(metaInfoEClass, META_INFO__CONSTRAINT_TYPE);
		createEAttribute(metaInfoEClass, META_INFO__VALUE_SET);
		createEAttribute(metaInfoEClass, META_INFO__MULTILINE_VALUE);
		createEReference(metaInfoEClass, META_INFO__EOBJECT_VALUE);

		metaInfoGroupEClass = createEClass(META_INFO_GROUP);
		createEReference(metaInfoGroupEClass, META_INFO_GROUP__CHILDREN);

		metaDataEClass = createEClass(META_DATA);

		containerEClass = createEClass(CONTAINER);

		constraintEClass = createEClass(CONSTRAINT);
		createEAttribute(constraintEClass, CONSTRAINT__CONSTRAINT_TYPE);
		createEReference(constraintEClass, CONSTRAINT__PARAMS);

		constraintParamEClass = createEClass(CONSTRAINT_PARAM);
		createEAttribute(constraintParamEClass, CONSTRAINT_PARAM__VALUES);

		nameSpaceEClass = createEClass(NAME_SPACE);
		createEAttribute(nameSpaceEClass, NAME_SPACE__PREFIX);
		createEAttribute(nameSpaceEClass, NAME_SPACE__URI);

		customDataTypeEClass = createEClass(CUSTOM_DATA_TYPE);
		createEAttribute(customDataTypeEClass, CUSTOM_DATA_TYPE__DATA_TYPE_IMP);

		// Create enums
		dataTypeEEnum = createEEnum(DATA_TYPE);
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

		// Create type parameters

		// Set bounds for type parameters

		// Add supertypes to classes
		namedModelElementEClass.getESuperTypes().add(this.getModelElement());
		commentEClass.getESuperTypes().add(this.getModelElement());
		stereotypeEClass.getESuperTypes().add(this.getNamedModelElement());
		packageEClass.getESuperTypes().add(this.getNamedModelElement());
		operationEClass.getESuperTypes().add(this.getOperationComponent());
		parameterEClass.getESuperTypes().add(this.getNamedModelElement());
		operationGroupEClass.getESuperTypes().add(this.getOperationComponent());
		operationComponentEClass.getESuperTypes().add(this.getNamedModelElement());
		metaInfoEClass.getESuperTypes().add(this.getMetaData());
		metaInfoGroupEClass.getESuperTypes().add(this.getMetaData());
		metaInfoGroupEClass.getESuperTypes().add(this.getNamedModelElement());
		constraintEClass.getESuperTypes().add(this.getNamedModelElement());
		constraintParamEClass.getESuperTypes().add(this.getNamedModelElement());
		customDataTypeEClass.getESuperTypes().add(this.getNamedModelElement());

		// Initialize classes and features; add operations and parameters
		initEClass(modelElementEClass, ModelElement.class, "ModelElement", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEReference(getModelElement_Stereotypes(), this.getStereotype(), null, "stereotypes", null, 0, -1, ModelElement.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getModelElement_Tags(), this.getTag(), null, "tags", null, 0, -1, ModelElement.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getModelElement_Comments(), this.getComment(), null, "comments", null, 0, -1, ModelElement.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getModelElement_Documentation(), ecorePackage.getEString(), "documentation", null, 0, 1, ModelElement.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getModelElement_Description(), ecorePackage.getEString(), "description", null, 0, 1, ModelElement.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getModelElement_Metainfo(), this.getMetaInfo(), null, "metainfo", null, 0, -1, ModelElement.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getModelElement_Constraints(), this.getConstraint(), null, "constraints", null, 0, -1, ModelElement.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getModelElement_Namespace(), this.getNameSpace(), null, "namespace", null, 0, 1, ModelElement.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		addEOperation(modelElementEClass, this.getNameSpace(), "getLogicalNameSpace", 0, 1, IS_UNIQUE, IS_ORDERED);

		initEClass(namedModelElementEClass, NamedModelElement.class, "NamedModelElement", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEAttribute(getNamedModelElement_Name(), ecorePackage.getEString(), "name", null, 0, 1, NamedModelElement.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		addEOperation(namedModelElementEClass, ecorePackage.getEString(), "getFullName", 1, 1, IS_UNIQUE, IS_ORDERED);

		addEOperation(namedModelElementEClass, ecorePackage.getEString(), "getDocumentationOrName", 0, 1, IS_UNIQUE, IS_ORDERED);

		addEOperation(namedModelElementEClass, ecorePackage.getEString(), "getDescriptionOrName", 0, 1, IS_UNIQUE, IS_ORDERED);

		initEClass(commentEClass, Comment.class, "Comment", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEAttribute(getComment_Value(), ecorePackage.getEString(), "value", null, 0, 1, Comment.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(tagEClass, Tag.class, "Tag", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEAttribute(getTag_Key(), ecorePackage.getEString(), "key", null, 0, 1, Tag.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getTag_Value(), ecorePackage.getEString(), "value", null, 0, 1, Tag.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		EOperation op = addEOperation(tagEClass, ecorePackage.getEBoolean(), "equalsForMerge", 0, 1, IS_UNIQUE, IS_ORDERED);
		addEParameter(op, this.getTag(), "other", 0, 1, IS_UNIQUE, IS_ORDERED);

		initEClass(stereotypeEClass, Stereotype.class, "Stereotype", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);

		op = addEOperation(stereotypeEClass, ecorePackage.getEBoolean(), "EqualsForMerge", 0, 1, IS_UNIQUE, IS_ORDERED);
		addEParameter(op, this.getStereotype(), "other", 0, 1, IS_UNIQUE, IS_ORDERED);

		initEClass(packageEClass, com.bluexml.side.common.Package.class, "Package", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEReference(getPackage_StereotypeSet(), this.getStereotype(), null, "stereotypeSet", null, 0, -1, com.bluexml.side.common.Package.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getPackage_PackageSet(), this.getPackage(), null, "packageSet", null, 0, -1, com.bluexml.side.common.Package.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getPackage_NamespaceSet(), this.getNameSpace(), null, "namespaceSet", null, 0, -1, com.bluexml.side.common.Package.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		op = addEOperation(packageEClass, ecorePackage.getEBoolean(), "equalsForMerge", 0, 1, IS_UNIQUE, IS_ORDERED);
		addEParameter(op, this.getPackage(), "other", 0, 1, IS_UNIQUE, IS_ORDERED);

		addEOperation(packageEClass, this.getPackage(), "getRootPackage", 0, 1, IS_UNIQUE, IS_ORDERED);

		addEOperation(packageEClass, this.getNameSpace(), "getAllNamespaces", 0, -1, IS_UNIQUE, IS_ORDERED);

		initEClass(operationEClass, Operation.class, "Operation", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEAttribute(getOperation_ReturnType(), this.getDataType(), "returnType", null, 0, 1, Operation.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getOperation_Parameters(), this.getParameter(), null, "parameters", null, 0, -1, Operation.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getOperation_Visibility(), this.getVisibility(), "visibility", null, 0, 1, Operation.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getOperation_Static(), ecorePackage.getEBoolean(), "static", null, 0, 1, Operation.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getOperation_Body(), ecorePackage.getEString(), "body", null, 0, 1, Operation.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		op = addEOperation(operationEClass, ecorePackage.getEBoolean(), "equalsForMerge", 0, 1, IS_UNIQUE, IS_ORDERED);
		addEParameter(op, this.getOperation(), "other", 0, 1, IS_UNIQUE, IS_ORDERED);

		op = addEOperation(operationEClass, this.getParameter(), "getParameter", 0, 1, IS_UNIQUE, IS_ORDERED);
		addEParameter(op, ecorePackage.getEString(), "pname", 1, 1, IS_UNIQUE, IS_ORDERED);

		initEClass(parameterEClass, Parameter.class, "Parameter", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEAttribute(getParameter_ValueType(), this.getDataType(), "valueType", null, 0, 1, Parameter.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(operationGroupEClass, OperationGroup.class, "OperationGroup", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEReference(getOperationGroup_Children(), this.getOperationComponent(), null, "children", null, 0, -1, OperationGroup.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(operationComponentEClass, OperationComponent.class, "OperationComponent", IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);

		addEOperation(operationComponentEClass, this.getOperationComponent(), "getOperations", 0, -1, IS_UNIQUE, IS_ORDERED);

		initEClass(metaInfoEClass, MetaInfo.class, "MetaInfo", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEAttribute(getMetaInfo_Key(), ecorePackage.getEString(), "key", null, 0, 1, MetaInfo.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getMetaInfo_Value(), ecorePackage.getEString(), "value", null, 0, 1, MetaInfo.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		EGenericType g1 = createEGenericType(ecorePackage.getEJavaClass());
		EGenericType g2 = createEGenericType();
		g1.getETypeArguments().add(g2);
		initEAttribute(getMetaInfo_ValueType(), g1, "valueType", null, 0, 1, MetaInfo.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getMetaInfo_ConstraintType(), this.getDataType(), "constraintType", null, 0, 1, MetaInfo.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getMetaInfo_ValueSet(), ecorePackage.getEJavaObject(), "valueSet", null, 0, 1, MetaInfo.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getMetaInfo_MultilineValue(), ecorePackage.getEString(), "multilineValue", null, 0, 1, MetaInfo.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getMetaInfo_EObjectValue(), ecorePackage.getEObject(), null, "EObjectValue", null, 0, -1, MetaInfo.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		op = addEOperation(metaInfoEClass, ecorePackage.getEBoolean(), "equalsForMerge", 0, 1, IS_UNIQUE, IS_ORDERED);
		addEParameter(op, this.getMetaInfo(), "other", 0, 1, IS_UNIQUE, IS_ORDERED);

		initEClass(metaInfoGroupEClass, MetaInfoGroup.class, "MetaInfoGroup", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEReference(getMetaInfoGroup_Children(), this.getMetaData(), null, "children", null, 0, -1, MetaInfoGroup.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(metaDataEClass, MetaData.class, "MetaData", IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);

		initEClass(containerEClass, com.bluexml.side.common.Container.class, "Container", IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);

		initEClass(constraintEClass, Constraint.class, "Constraint", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEAttribute(getConstraint_ConstraintType(), ecorePackage.getEString(), "constraintType", null, 0, 1, Constraint.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getConstraint_Params(), this.getConstraintParam(), null, "params", null, 0, -1, Constraint.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(constraintParamEClass, ConstraintParam.class, "ConstraintParam", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEAttribute(getConstraintParam_Values(), ecorePackage.getEString(), "values", null, 0, -1, ConstraintParam.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(nameSpaceEClass, NameSpace.class, "NameSpace", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEAttribute(getNameSpace_Prefix(), ecorePackage.getEString(), "prefix", null, 0, 1, NameSpace.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getNameSpace_URI(), ecorePackage.getEString(), "URI", null, 0, 1, NameSpace.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(customDataTypeEClass, CustomDataType.class, "CustomDataType", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEAttribute(getCustomDataType_DataTypeImp(), ecorePackage.getEString(), "dataTypeImp", "java.lang.String", 1, 1, CustomDataType.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		// Initialize enums and add enum literals
		initEEnum(dataTypeEEnum, DataType.class, "DataType");
		addEEnumLiteral(dataTypeEEnum, DataType.CUSTOM);
		addEEnumLiteral(dataTypeEEnum, DataType.BOOLEAN);
		addEEnumLiteral(dataTypeEEnum, DataType.BYTE);
		addEEnumLiteral(dataTypeEEnum, DataType.CHAR);
		addEEnumLiteral(dataTypeEEnum, DataType.DOUBLE);
		addEEnumLiteral(dataTypeEEnum, DataType.FLOAT);
		addEEnumLiteral(dataTypeEEnum, DataType.INT);
		addEEnumLiteral(dataTypeEEnum, DataType.LONG);
		addEEnumLiteral(dataTypeEEnum, DataType.SHORT);
		addEEnumLiteral(dataTypeEEnum, DataType.STRING);
		addEEnumLiteral(dataTypeEEnum, DataType.DATE);
		addEEnumLiteral(dataTypeEEnum, DataType.OBJECT);
		addEEnumLiteral(dataTypeEEnum, DataType.DATE_TIME);
		addEEnumLiteral(dataTypeEEnum, DataType.TIME);

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
		  (modelElementEClass.getEOperations().get(0), 
		   source, 
		   new String[] {
			 "body", "if not(self.namespace.oclIsUndefined()) then\r\tself.namespace\relse\r\tif not(self.getContainer().oclIsUndefined()) and self.getContainer().oclIsKindOf(common::ModelElement) then\r\t\tself.getContainer().oclAsType(common::ModelElement).getLogicalNameSpace()\r\telse\r\t\tnull\r\tendif\rendif"
		   });					
		addAnnotation
		  (namedModelElementEClass.getEOperations().get(0), 
		   source, 
		   new String[] {
			 "body", "if self.getContainer().oclIsUndefined() then\r\t\'\'\relse\r\tif self.getContainer().oclIsKindOf(NamedModelElement) then\n\t\tlet parent : String = self.getContainer().oclAsType(NamedModelElement).getFullName()\n\t\tin\n\t\t\tif ((not(parent.oclIsUndefined())) and (parent.size() > 0)) then\n\t\t\t\tparent.concat(\'.\').concat(if self.name.oclIsUndefined() then \'\' else self.name endif)\n\t\t\telse\n\t\t\t\tif self.name.oclIsUndefined() then \'\' else self.name endif\n\t\t\tendif\r\telse\r\t\t\'\'\r\tendif\t\rendif"
		   });		
		addAnnotation
		  (namedModelElementEClass.getEOperations().get(1), 
		   source, 
		   new String[] {
			 "body", "if self.documentation.oclIsUndefined() or self.documentation.size() <0 then\r\tself.name\relse\r\tself.documentation\rendif"
		   });		
		addAnnotation
		  (namedModelElementEClass.getEOperations().get(2), 
		   source, 
		   new String[] {
			 "body", "if self.description.oclIsUndefined() or self.description.size() <0 then\r\tself.name\relse\r\tself.description\rendif"
		   });			
		addAnnotation
		  (tagEClass.getEOperations().get(0), 
		   source, 
		   new String[] {
			 "body", "self.key = other.key"
		   });		
		addAnnotation
		  (stereotypeEClass.getEOperations().get(0), 
		   source, 
		   new String[] {
			 "body", "self.name = other.name"
		   });			
		addAnnotation
		  (packageEClass.getEOperations().get(0), 
		   source, 
		   new String[] {
			 "body", "self.name = other.name"
		   });		
		addAnnotation
		  (packageEClass.getEOperations().get(1), 
		   source, 
		   new String[] {
			 "body", "if self.getContainer().oclIsUndefined() then\r\tself\relse\r\tself.getContainer().oclAsType(Package).getRootPackage()\rendif"
		   });		
		addAnnotation
		  (packageEClass.getEOperations().get(2), 
		   source, 
		   new String[] {
			 "body", "common::NameSpace.allInstances()"
		   });		
		addAnnotation
		  (operationEClass.getEOperations().get(0), 
		   source, 
		   new String[] {
			 "body", "self.name = other.name and \r\n\r\nself.parameters->forAll(p:Parameter |   \r\n   other.parameters->exists(z : Parameter | z.name = p.name and z.valueType = p.valueType ))"
		   });		
		addAnnotation
		  (operationEClass.getEOperations().get(1), 
		   source, 
		   new String[] {
			 "body", "self.getParameters()->select(e:Parameter|e.name =pname)->first()"
		   });			
		addAnnotation
		  (operationComponentEClass.getEOperations().get(0), 
		   source, 
		   new String[] {
			 "body", "if (self.oclIsKindOf(OperationGroup)) then\n\tself.oclAsType(OperationGroup).children->iterate(e:OperationComponent; result :Set(OperationComponent)=Set{}|result->union(e.getOperations()))->flatten()->asOrderedSet()\nelse\n\tself.oclAsType(OperationComponent)->asOrderedSet()\nendif"
		   });		
		addAnnotation
		  (metaInfoEClass.getEOperations().get(0), 
		   source, 
		   new String[] {
			 "body", "self.key = other.key"
		   });
	}

} //CommonPackageImpl
