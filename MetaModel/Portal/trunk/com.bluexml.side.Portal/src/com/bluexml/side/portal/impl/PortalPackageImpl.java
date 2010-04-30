/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package com.bluexml.side.portal.impl;

import com.bluexml.side.clazz.ClazzPackage;

import com.bluexml.side.common.CommonPackage;

import com.bluexml.side.form.FormPackage;
import com.bluexml.side.portal.AbstractPortletAttributes;
import com.bluexml.side.portal.Column;
import com.bluexml.side.portal.Group;
import com.bluexml.side.portal.HavePortlet;
import com.bluexml.side.portal.InstanciatePortletType;
import com.bluexml.side.portal.InternalPortletType;
import com.bluexml.side.portal.Page;
import com.bluexml.side.portal.Portal;
import com.bluexml.side.portal.PortalFactory;
import com.bluexml.side.portal.PortalLayout;
import com.bluexml.side.portal.PortalModelElement;
import com.bluexml.side.portal.PortalPackage;
import com.bluexml.side.portal.Portlet;
import com.bluexml.side.portal.PortletAttribute;
import com.bluexml.side.portal.PortletAttributeInstance;
import com.bluexml.side.portal.PortletInternal;
import com.bluexml.side.portal.PortletType;
import com.bluexml.side.portal.PortletTypeAttributeType;
import com.bluexml.side.portal.PositionGroup;
import com.bluexml.side.portal.isChildPage;
import com.bluexml.side.portal.util.PortalValidator;
import com.bluexml.side.portal.widthUnit;

import com.bluexml.side.view.ViewPackage;
import org.eclipse.emf.ecore.EAttribute;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EEnum;
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
public class PortalPackageImpl extends EPackageImpl implements PortalPackage {
	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass portalModelElementEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass portalEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass pageEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass portalLayoutEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass columnEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass portletEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass portletTypeEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass portletInternalEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass abstractPortletAttributesEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass groupEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass portletAttributeEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass havePortletEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass positionGroupEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass instanciatePortletTypeEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass portletAttributeInstanceEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass isChildPageEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EEnum widthUnitEEnum = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EEnum internalPortletTypeEEnum = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EEnum portletTypeAttributeTypeEEnum = null;

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
	 * @see com.bluexml.side.portal.PortalPackage#eNS_URI
	 * @see #init()
	 * @generated
	 */
	private PortalPackageImpl() {
		super(eNS_URI, PortalFactory.eINSTANCE);
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
	 * <p>This method is used to initialize {@link PortalPackage#eINSTANCE} when that field is accessed.
	 * Clients should not invoke it directly. Instead, they should simply access that field to obtain the package.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #eNS_URI
	 * @see #createPackageContents()
	 * @see #initializePackageContents()
	 * @generated
	 */
	public static PortalPackage init() {
		if (isInited) return (PortalPackage)EPackage.Registry.INSTANCE.getEPackage(PortalPackage.eNS_URI);

		// Obtain or create and register package
		PortalPackageImpl thePortalPackage = (PortalPackageImpl)(EPackage.Registry.INSTANCE.get(eNS_URI) instanceof PortalPackageImpl ? EPackage.Registry.INSTANCE.get(eNS_URI) : new PortalPackageImpl());

		isInited = true;

		// Initialize simple dependencies
		FormPackage.eINSTANCE.eClass();
		ViewPackage.eINSTANCE.eClass();

		// Create package meta-data objects
		thePortalPackage.createPackageContents();

		// Initialize created meta-data
		thePortalPackage.initializePackageContents();

		// Register package validator
		EValidator.Registry.INSTANCE.put
			(thePortalPackage, 
			 new EValidator.Descriptor() {
				 public EValidator getEValidator() {
					 return PortalValidator.INSTANCE;
				 }
			 });

		// Mark meta-data to indicate it can't be changed
		thePortalPackage.freeze();

  
		// Update the registry and return the package
		EPackage.Registry.INSTANCE.put(PortalPackage.eNS_URI, thePortalPackage);
		return thePortalPackage;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getPortalModelElement() {
		return portalModelElementEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getPortal() {
		return portalEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getPortal_PageSet() {
		return (EReference)portalEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getPortal_LayoutSet() {
		return (EReference)portalEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getPortal_PortletSet() {
		return (EReference)portalEClass.getEStructuralFeatures().get(2);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getPortal_PortletSetType() {
		return (EReference)portalEClass.getEStructuralFeatures().get(3);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getPortal_InternalPortletSet() {
		return (EReference)portalEClass.getEStructuralFeatures().get(4);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getPage() {
		return pageEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getPage_ID() {
		return (EAttribute)pageEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getPage_Title() {
		return (EAttribute)pageEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getPage_UseLayout() {
		return (EReference)pageEClass.getEStructuralFeatures().get(2);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getPage_Portlets() {
		return (EReference)pageEClass.getEStructuralFeatures().get(3);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getPage_Position() {
		return (EAttribute)pageEClass.getEStructuralFeatures().get(4);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getPage_IsChildPageOf() {
		return (EReference)pageEClass.getEStructuralFeatures().get(5);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getPage_Visibility() {
		return (EAttribute)pageEClass.getEStructuralFeatures().get(6);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getPage_Generate() {
		return (EAttribute)pageEClass.getEStructuralFeatures().get(7);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getPortalLayout() {
		return portalLayoutEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getPortalLayout_Name() {
		return (EAttribute)portalLayoutEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getPortalLayout_Columns() {
		return (EReference)portalLayoutEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getPortalLayout_ColumnMode() {
		return (EAttribute)portalLayoutEClass.getEStructuralFeatures().get(2);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getColumn() {
		return columnEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getColumn_Name() {
		return (EAttribute)columnEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getColumn_Width() {
		return (EAttribute)columnEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getColumn_Unit() {
		return (EAttribute)columnEClass.getEStructuralFeatures().get(2);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getPortlet() {
		return portletEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getPortlet_Name() {
		return (EAttribute)portletEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getPortlet_IsPortletInternal() {
		return (EReference)portletEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getPortlet_IsInstanceOfPortletType() {
		return (EReference)portletEClass.getEStructuralFeatures().get(2);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getPortletType() {
		return portletTypeEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getPortletType_Id() {
		return (EAttribute)portletTypeEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getPortletType_Name() {
		return (EAttribute)portletTypeEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getPortletType_HaveAttribute() {
		return (EReference)portletTypeEClass.getEStructuralFeatures().get(2);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getPortletType_Instanceable() {
		return (EAttribute)portletTypeEClass.getEStructuralFeatures().get(3);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getPortletInternal() {
		return portletInternalEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getPortletInternal_Type() {
		return (EAttribute)portletInternalEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getPortletInternal_View() {
		return (EReference)portletInternalEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getPortletInternal_Form() {
		return (EReference)portletInternalEClass.getEStructuralFeatures().get(2);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getAbstractPortletAttributes() {
		return abstractPortletAttributesEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getGroup() {
		return groupEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getGroup_UsePage() {
		return (EReference)groupEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getGroup_Name() {
		return (EAttribute)groupEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getPortletAttribute() {
		return portletAttributeEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getPortletAttribute_Name() {
		return (EAttribute)portletAttributeEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getPortletAttribute_Type() {
		return (EAttribute)portletAttributeEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getPortletAttribute_Required() {
		return (EAttribute)portletAttributeEClass.getEStructuralFeatures().get(2);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getPortletAttribute_MultiValued() {
		return (EAttribute)portletAttributeEClass.getEStructuralFeatures().get(3);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getHavePortlet() {
		return havePortletEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getHavePortlet_AssociationPortlet() {
		return (EReference)havePortletEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getHavePortlet_AssociationPage() {
		return (EReference)havePortletEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getHavePortlet_PositionGroup() {
		return (EReference)havePortletEClass.getEStructuralFeatures().get(2);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getPositionGroup() {
		return positionGroupEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getPositionGroup_Position() {
		return (EAttribute)positionGroupEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getPositionGroup_OnColumn() {
		return (EReference)positionGroupEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getPositionGroup_OnLayout() {
		return (EReference)positionGroupEClass.getEStructuralFeatures().get(2);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getInstanciatePortletType() {
		return instanciatePortletTypeEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getInstanciatePortletType_PortletType() {
		return (EReference)instanciatePortletTypeEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getInstanciatePortletType_Instances() {
		return (EReference)instanciatePortletTypeEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getPortletAttributeInstance() {
		return portletAttributeInstanceEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getPortletAttributeInstance_Value() {
		return (EAttribute)portletAttributeInstanceEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getPortletAttributeInstance_InstanceOf() {
		return (EReference)portletAttributeInstanceEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getisChildPage() {
		return isChildPageEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getisChildPage_Inherit() {
		return (EAttribute)isChildPageEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getisChildPage_IsChildPageOf() {
		return (EReference)isChildPageEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EEnum getwidthUnit() {
		return widthUnitEEnum;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EEnum getInternalPortletType() {
		return internalPortletTypeEEnum;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EEnum getPortletTypeAttributeType() {
		return portletTypeAttributeTypeEEnum;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public PortalFactory getPortalFactory() {
		return (PortalFactory)getEFactoryInstance();
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
		portalModelElementEClass = createEClass(PORTAL_MODEL_ELEMENT);

		portalEClass = createEClass(PORTAL);
		createEReference(portalEClass, PORTAL__PAGE_SET);
		createEReference(portalEClass, PORTAL__LAYOUT_SET);
		createEReference(portalEClass, PORTAL__PORTLET_SET);
		createEReference(portalEClass, PORTAL__PORTLET_SET_TYPE);
		createEReference(portalEClass, PORTAL__INTERNAL_PORTLET_SET);

		pageEClass = createEClass(PAGE);
		createEAttribute(pageEClass, PAGE__ID);
		createEAttribute(pageEClass, PAGE__TITLE);
		createEReference(pageEClass, PAGE__USE_LAYOUT);
		createEReference(pageEClass, PAGE__PORTLETS);
		createEAttribute(pageEClass, PAGE__POSITION);
		createEReference(pageEClass, PAGE__IS_CHILD_PAGE_OF);
		createEAttribute(pageEClass, PAGE__VISIBILITY);
		createEAttribute(pageEClass, PAGE__GENERATE);

		portalLayoutEClass = createEClass(PORTAL_LAYOUT);
		createEAttribute(portalLayoutEClass, PORTAL_LAYOUT__NAME);
		createEReference(portalLayoutEClass, PORTAL_LAYOUT__COLUMNS);
		createEAttribute(portalLayoutEClass, PORTAL_LAYOUT__COLUMN_MODE);

		columnEClass = createEClass(COLUMN);
		createEAttribute(columnEClass, COLUMN__NAME);
		createEAttribute(columnEClass, COLUMN__WIDTH);
		createEAttribute(columnEClass, COLUMN__UNIT);

		portletEClass = createEClass(PORTLET);
		createEAttribute(portletEClass, PORTLET__NAME);
		createEReference(portletEClass, PORTLET__IS_PORTLET_INTERNAL);
		createEReference(portletEClass, PORTLET__IS_INSTANCE_OF_PORTLET_TYPE);

		portletTypeEClass = createEClass(PORTLET_TYPE);
		createEAttribute(portletTypeEClass, PORTLET_TYPE__ID);
		createEAttribute(portletTypeEClass, PORTLET_TYPE__NAME);
		createEReference(portletTypeEClass, PORTLET_TYPE__HAVE_ATTRIBUTE);
		createEAttribute(portletTypeEClass, PORTLET_TYPE__INSTANCEABLE);

		portletInternalEClass = createEClass(PORTLET_INTERNAL);
		createEAttribute(portletInternalEClass, PORTLET_INTERNAL__TYPE);
		createEReference(portletInternalEClass, PORTLET_INTERNAL__VIEW);
		createEReference(portletInternalEClass, PORTLET_INTERNAL__FORM);

		abstractPortletAttributesEClass = createEClass(ABSTRACT_PORTLET_ATTRIBUTES);

		groupEClass = createEClass(GROUP);
		createEReference(groupEClass, GROUP__USE_PAGE);
		createEAttribute(groupEClass, GROUP__NAME);

		portletAttributeEClass = createEClass(PORTLET_ATTRIBUTE);
		createEAttribute(portletAttributeEClass, PORTLET_ATTRIBUTE__NAME);
		createEAttribute(portletAttributeEClass, PORTLET_ATTRIBUTE__TYPE);
		createEAttribute(portletAttributeEClass, PORTLET_ATTRIBUTE__REQUIRED);
		createEAttribute(portletAttributeEClass, PORTLET_ATTRIBUTE__MULTI_VALUED);

		havePortletEClass = createEClass(HAVE_PORTLET);
		createEReference(havePortletEClass, HAVE_PORTLET__ASSOCIATION_PORTLET);
		createEReference(havePortletEClass, HAVE_PORTLET__ASSOCIATION_PAGE);
		createEReference(havePortletEClass, HAVE_PORTLET__POSITION_GROUP);

		positionGroupEClass = createEClass(POSITION_GROUP);
		createEAttribute(positionGroupEClass, POSITION_GROUP__POSITION);
		createEReference(positionGroupEClass, POSITION_GROUP__ON_COLUMN);
		createEReference(positionGroupEClass, POSITION_GROUP__ON_LAYOUT);

		instanciatePortletTypeEClass = createEClass(INSTANCIATE_PORTLET_TYPE);
		createEReference(instanciatePortletTypeEClass, INSTANCIATE_PORTLET_TYPE__PORTLET_TYPE);
		createEReference(instanciatePortletTypeEClass, INSTANCIATE_PORTLET_TYPE__INSTANCES);

		portletAttributeInstanceEClass = createEClass(PORTLET_ATTRIBUTE_INSTANCE);
		createEAttribute(portletAttributeInstanceEClass, PORTLET_ATTRIBUTE_INSTANCE__VALUE);
		createEReference(portletAttributeInstanceEClass, PORTLET_ATTRIBUTE_INSTANCE__INSTANCE_OF);

		isChildPageEClass = createEClass(IS_CHILD_PAGE);
		createEAttribute(isChildPageEClass, IS_CHILD_PAGE__INHERIT);
		createEReference(isChildPageEClass, IS_CHILD_PAGE__IS_CHILD_PAGE_OF);

		// Create enums
		widthUnitEEnum = createEEnum(WIDTH_UNIT);
		internalPortletTypeEEnum = createEEnum(INTERNAL_PORTLET_TYPE);
		portletTypeAttributeTypeEEnum = createEEnum(PORTLET_TYPE_ATTRIBUTE_TYPE);
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
		ViewPackage theViewPackage = (ViewPackage)EPackage.Registry.INSTANCE.getEPackage(ViewPackage.eNS_URI);
		FormPackage theFormPackage = (FormPackage)EPackage.Registry.INSTANCE.getEPackage(FormPackage.eNS_URI);

		// Create type parameters

		// Set bounds for type parameters

		// Add supertypes to classes
		portalModelElementEClass.getESuperTypes().add(theCommonPackage.getModelElement());
		portalEClass.getESuperTypes().add(theCommonPackage.getPackage());
		pageEClass.getESuperTypes().add(this.getPortalModelElement());
		portalLayoutEClass.getESuperTypes().add(this.getPortalModelElement());
		portletEClass.getESuperTypes().add(this.getPortalModelElement());
		portletTypeEClass.getESuperTypes().add(this.getPortalModelElement());
		portletInternalEClass.getESuperTypes().add(this.getPortalModelElement());
		portletAttributeEClass.getESuperTypes().add(this.getAbstractPortletAttributes());

		// Initialize classes and features; add operations and parameters
		initEClass(portalModelElementEClass, PortalModelElement.class, "PortalModelElement", IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);

		initEClass(portalEClass, Portal.class, "Portal", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEReference(getPortal_PageSet(), this.getPage(), null, "pageSet", null, 0, -1, Portal.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getPortal_LayoutSet(), this.getPortalLayout(), null, "layoutSet", null, 0, -1, Portal.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getPortal_PortletSet(), this.getPortlet(), null, "portletSet", null, 0, -1, Portal.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getPortal_PortletSetType(), this.getPortletType(), null, "portletSetType", null, 0, -1, Portal.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getPortal_InternalPortletSet(), this.getPortletInternal(), null, "internalPortletSet", null, 0, -1, Portal.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(pageEClass, Page.class, "Page", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEAttribute(getPage_ID(), ecorePackage.getEString(), "ID", null, 0, 1, Page.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getPage_Title(), ecorePackage.getEString(), "title", null, 0, 1, Page.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getPage_UseLayout(), this.getPortalLayout(), null, "useLayout", null, 0, 1, Page.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getPage_Portlets(), this.getHavePortlet(), null, "portlets", null, 0, -1, Page.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getPage_Position(), ecorePackage.getEInt(), "position", null, 0, 1, Page.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getPage_IsChildPageOf(), this.getisChildPage(), null, "isChildPageOf", null, 0, 1, Page.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getPage_Visibility(), theCommonPackage.getVisibility(), "visibility", null, 0, 1, Page.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getPage_Generate(), ecorePackage.getEBoolean(), "generate", "true", 0, 1, Page.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(portalLayoutEClass, PortalLayout.class, "PortalLayout", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEAttribute(getPortalLayout_Name(), ecorePackage.getEString(), "name", null, 0, 1, PortalLayout.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, !IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getPortalLayout_Columns(), this.getColumn(), null, "columns", null, 0, -1, PortalLayout.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getPortalLayout_ColumnMode(), ecorePackage.getEBoolean(), "columnMode", "true", 0, 1, PortalLayout.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(columnEClass, Column.class, "Column", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEAttribute(getColumn_Name(), ecorePackage.getEString(), "name", null, 0, 1, Column.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getColumn_Width(), ecorePackage.getEInt(), "width", null, 0, 1, Column.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getColumn_Unit(), this.getwidthUnit(), "unit", null, 0, 1, Column.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(portletEClass, Portlet.class, "Portlet", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEAttribute(getPortlet_Name(), ecorePackage.getEString(), "name", null, 0, 1, Portlet.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getPortlet_IsPortletInternal(), this.getPortletInternal(), null, "isPortletInternal", null, 0, 1, Portlet.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getPortlet_IsInstanceOfPortletType(), this.getInstanciatePortletType(), null, "isInstanceOfPortletType", null, 0, 1, Portlet.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(portletTypeEClass, PortletType.class, "PortletType", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEAttribute(getPortletType_Id(), ecorePackage.getEString(), "id", null, 0, 1, PortletType.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getPortletType_Name(), ecorePackage.getEString(), "name", null, 0, 1, PortletType.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getPortletType_HaveAttribute(), this.getPortletAttribute(), null, "haveAttribute", null, 0, -1, PortletType.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getPortletType_Instanceable(), ecorePackage.getEBoolean(), "instanceable", "true", 0, 1, PortletType.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(portletInternalEClass, PortletInternal.class, "PortletInternal", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEAttribute(getPortletInternal_Type(), this.getInternalPortletType(), "type", null, 0, 1, PortletInternal.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getPortletInternal_View(), theViewPackage.getAbstractView(), null, "view", null, 0, 1, PortletInternal.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getPortletInternal_Form(), theFormPackage.getFormCollection(), null, "form", null, 0, 1, PortletInternal.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(abstractPortletAttributesEClass, AbstractPortletAttributes.class, "AbstractPortletAttributes", IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);

		initEClass(groupEClass, Group.class, "Group", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEReference(getGroup_UsePage(), this.getPage(), null, "usePage", null, 0, 1, Group.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getGroup_Name(), ecorePackage.getEString(), "name", null, 0, 1, Group.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(portletAttributeEClass, PortletAttribute.class, "PortletAttribute", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEAttribute(getPortletAttribute_Name(), ecorePackage.getEString(), "name", null, 0, 1, PortletAttribute.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getPortletAttribute_Type(), this.getPortletTypeAttributeType(), "type", null, 0, 1, PortletAttribute.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getPortletAttribute_Required(), ecorePackage.getEBoolean(), "required", null, 0, 1, PortletAttribute.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getPortletAttribute_MultiValued(), ecorePackage.getEBoolean(), "multiValued", "false", 0, 1, PortletAttribute.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(havePortletEClass, HavePortlet.class, "HavePortlet", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEReference(getHavePortlet_AssociationPortlet(), this.getPortlet(), null, "associationPortlet", null, 0, 1, HavePortlet.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getHavePortlet_AssociationPage(), this.getPage(), null, "associationPage", null, 0, 1, HavePortlet.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getHavePortlet_PositionGroup(), this.getPositionGroup(), null, "positionGroup", null, 0, -1, HavePortlet.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(positionGroupEClass, PositionGroup.class, "PositionGroup", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEAttribute(getPositionGroup_Position(), ecorePackage.getEInt(), "position", null, 0, 1, PositionGroup.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getPositionGroup_OnColumn(), this.getColumn(), null, "onColumn", null, 0, 1, PositionGroup.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getPositionGroup_OnLayout(), this.getPortalLayout(), null, "onLayout", null, 0, 1, PositionGroup.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(instanciatePortletTypeEClass, InstanciatePortletType.class, "InstanciatePortletType", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEReference(getInstanciatePortletType_PortletType(), this.getPortletType(), null, "portletType", null, 0, 1, InstanciatePortletType.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getInstanciatePortletType_Instances(), this.getPortletAttributeInstance(), null, "instances", null, 0, -1, InstanciatePortletType.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(portletAttributeInstanceEClass, PortletAttributeInstance.class, "PortletAttributeInstance", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEAttribute(getPortletAttributeInstance_Value(), ecorePackage.getEString(), "value", "", 0, 1, PortletAttributeInstance.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getPortletAttributeInstance_InstanceOf(), this.getPortletAttribute(), null, "instanceOf", null, 0, 1, PortletAttributeInstance.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(isChildPageEClass, isChildPage.class, "isChildPage", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEAttribute(getisChildPage_Inherit(), ecorePackage.getEBoolean(), "inherit", null, 0, 1, isChildPage.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getisChildPage_IsChildPageOf(), this.getPage(), null, "isChildPageOf", null, 0, 1, isChildPage.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		// Initialize enums and add enum literals
		initEEnum(widthUnitEEnum, widthUnit.class, "widthUnit");
		addEEnumLiteral(widthUnitEEnum, widthUnit.PERCENT);
		addEEnumLiteral(widthUnitEEnum, widthUnit.PIXEL);

		initEEnum(internalPortletTypeEEnum, InternalPortletType.class, "InternalPortletType");
		addEEnumLiteral(internalPortletTypeEEnum, InternalPortletType.VIEW);
		addEEnumLiteral(internalPortletTypeEEnum, InternalPortletType.FORM);

		initEEnum(portletTypeAttributeTypeEEnum, PortletTypeAttributeType.class, "PortletTypeAttributeType");
		addEEnumLiteral(portletTypeAttributeTypeEEnum, PortletTypeAttributeType.STRING);
		addEEnumLiteral(portletTypeAttributeTypeEEnum, PortletTypeAttributeType.INT);
		addEEnumLiteral(portletTypeAttributeTypeEEnum, PortletTypeAttributeType.BOOLEAN);
		addEEnumLiteral(portletTypeAttributeTypeEEnum, PortletTypeAttributeType.DOUBLE);
		addEEnumLiteral(portletTypeAttributeTypeEEnum, PortletTypeAttributeType.DATE);
		addEEnumLiteral(portletTypeAttributeTypeEEnum, PortletTypeAttributeType.DATE_TIME);

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
		  (portalEClass, 
		   source, 
		   new String[] {
			 "portalNameEmpty", "not (self.name.oclIsUndefined() or self.name =\'\')"
		   });			
		addAnnotation
		  (portletEClass, 
		   source, 
		   new String[] {
			 "haveType", "not (self.isPortletInternal.oclIsUndefined() and self.isInstanceOfPortletType.oclIsUndefined())"
		   });			
		addAnnotation
		  (portletTypeEClass, 
		   source, 
		   new String[] {
			 "haveIdentifier", "not (self.id.oclIsUndefined() or self.id = \'\' or self.name.oclIsUndefined() or self.name. = \'\')"
		   });			
		addAnnotation
		  (portletInternalEClass, 
		   source, 
		   new String[] {
			 "haveType", "not self.type.oclIsUndefined()",
			 "isConsistent", "not (self.form.oclIsUndefined() and self.view.oclIsUndefined())"
		   });			
		addAnnotation
		  (havePortletEClass, 
		   source, 
		   new String[] {
			 "isvalide", "not (self.associationPage.oclIsUndefined() or self.associationPortlet.oclIsUndefined())"
		   });			
		addAnnotation
		  (instanciatePortletTypeEClass, 
		   source, 
		   new String[] {
			 "haveType", "not self.portletType.oclIsUndefined()"
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
		  (portalEClass, 
		   source, 
		   new String[] {
			 "constraints", "portalNameEmpty"
		   });			
		addAnnotation
		  (portletEClass, 
		   source, 
		   new String[] {
			 "constraints", "haveType"
		   });			
		addAnnotation
		  (portletTypeEClass, 
		   source, 
		   new String[] {
			 "constraints", "haveIdentifier"
		   });			
		addAnnotation
		  (portletInternalEClass, 
		   source, 
		   new String[] {
			 "constraints", "haveType"
		   });			
		addAnnotation
		  (havePortletEClass, 
		   source, 
		   new String[] {
			 "constraints", "isvalide"
		   });			
		addAnnotation
		  (instanciatePortletTypeEClass, 
		   source, 
		   new String[] {
			 "constraints", "haveType"
		   });
	}

} //PortalPackageImpl
