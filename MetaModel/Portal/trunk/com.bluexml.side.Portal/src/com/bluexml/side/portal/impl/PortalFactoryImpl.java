/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package com.bluexml.side.portal.impl;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EDataType;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.impl.EFactoryImpl;
import org.eclipse.emf.ecore.plugin.EcorePlugin;

import com.bluexml.side.portal.Column;
import com.bluexml.side.portal.Group;
import com.bluexml.side.portal.HavePortlet;
import com.bluexml.side.portal.InstanciatePortletType;
import com.bluexml.side.portal.InternalPortletType;
import com.bluexml.side.portal.Page;
import com.bluexml.side.portal.Portal;
import com.bluexml.side.portal.PortalFactory;
import com.bluexml.side.portal.PortalLayout;
import com.bluexml.side.portal.PortalPackage;
import com.bluexml.side.portal.Portlet;
import com.bluexml.side.portal.PortletAttribute;
import com.bluexml.side.portal.PortletAttributeInstance;
import com.bluexml.side.portal.PortletInternal;
import com.bluexml.side.portal.PortletType;
import com.bluexml.side.portal.PortletTypeAttributeType;
import com.bluexml.side.portal.PositionGroup;
import com.bluexml.side.portal.isChildPage;
import com.bluexml.side.portal.widthUnit;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model <b>Factory</b>.
 * <!-- end-user-doc -->
 * @generated
 */
public class PortalFactoryImpl extends EFactoryImpl implements PortalFactory {
	/**
	 * Creates the default factory implementation.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public static PortalFactory init() {
		try {
			PortalFactory thePortalFactory = (PortalFactory)EPackage.Registry.INSTANCE.getEFactory("http://www.kerblue.org/portal/1.0"); 
			if (thePortalFactory != null) {
				return thePortalFactory;
			}
		}
		catch (Exception exception) {
			EcorePlugin.INSTANCE.log(exception);
		}
		return new PortalFactoryImpl();
	}

	/**
	 * Creates an instance of the factory.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public PortalFactoryImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EObject create(EClass eClass) {
		switch (eClass.getClassifierID()) {
			case PortalPackage.PORTAL: return createPortal();
			case PortalPackage.PAGE: return createPage();
			case PortalPackage.PORTAL_LAYOUT: return createPortalLayout();
			case PortalPackage.COLUMN: return createColumn();
			case PortalPackage.PORTLET: return createPortlet();
			case PortalPackage.PORTLET_TYPE: return createPortletType();
			case PortalPackage.PORTLET_INTERNAL: return createPortletInternal();
			case PortalPackage.GROUP: return createGroup();
			case PortalPackage.PORTLET_ATTRIBUTE: return createPortletAttribute();
			case PortalPackage.HAVE_PORTLET: return createHavePortlet();
			case PortalPackage.POSITION_GROUP: return createPositionGroup();
			case PortalPackage.INSTANCIATE_PORTLET_TYPE: return createInstanciatePortletType();
			case PortalPackage.PORTLET_ATTRIBUTE_INSTANCE: return createPortletAttributeInstance();
			case PortalPackage.IS_CHILD_PAGE: return createisChildPage();
			default:
				throw new IllegalArgumentException("The class '" + eClass.getName() + "' is not a valid classifier");
		}
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Object createFromString(EDataType eDataType, String initialValue) {
		switch (eDataType.getClassifierID()) {
			case PortalPackage.WIDTH_UNIT:
				return createwidthUnitFromString(eDataType, initialValue);
			case PortalPackage.INTERNAL_PORTLET_TYPE:
				return createInternalPortletTypeFromString(eDataType, initialValue);
			case PortalPackage.PORTLET_TYPE_ATTRIBUTE_TYPE:
				return createPortletTypeAttributeTypeFromString(eDataType, initialValue);
			default:
				throw new IllegalArgumentException("The datatype '" + eDataType.getName() + "' is not a valid classifier");
		}
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public String convertToString(EDataType eDataType, Object instanceValue) {
		switch (eDataType.getClassifierID()) {
			case PortalPackage.WIDTH_UNIT:
				return convertwidthUnitToString(eDataType, instanceValue);
			case PortalPackage.INTERNAL_PORTLET_TYPE:
				return convertInternalPortletTypeToString(eDataType, instanceValue);
			case PortalPackage.PORTLET_TYPE_ATTRIBUTE_TYPE:
				return convertPortletTypeAttributeTypeToString(eDataType, instanceValue);
			default:
				throw new IllegalArgumentException("The datatype '" + eDataType.getName() + "' is not a valid classifier");
		}
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Portal createPortal() {
		PortalImpl portal = new PortalImpl();
		return portal;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Page createPage() {
		PageImpl page = new PageImpl();
		return page;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public PortalLayout createPortalLayout() {
		PortalLayoutImpl portalLayout = new PortalLayoutImpl();
		return portalLayout;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Column createColumn() {
		ColumnImpl column = new ColumnImpl();
		return column;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Portlet createPortlet() {
		PortletImpl portlet = new PortletImpl();
		return portlet;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public PortletType createPortletType() {
		PortletTypeImpl portletType = new PortletTypeImpl();
		return portletType;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public PortletInternal createPortletInternal() {
		PortletInternalImpl portletInternal = new PortletInternalImpl();
		return portletInternal;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Group createGroup() {
		GroupImpl group = new GroupImpl();
		return group;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public PortletAttribute createPortletAttribute() {
		PortletAttributeImpl portletAttribute = new PortletAttributeImpl();
		return portletAttribute;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public HavePortlet createHavePortlet() {
		HavePortletImpl havePortlet = new HavePortletImpl();
		return havePortlet;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public PositionGroup createPositionGroup() {
		PositionGroupImpl positionGroup = new PositionGroupImpl();
		return positionGroup;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public InstanciatePortletType createInstanciatePortletType() {
		InstanciatePortletTypeImpl instanciatePortletType = new InstanciatePortletTypeImpl();
		return instanciatePortletType;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public PortletAttributeInstance createPortletAttributeInstance() {
		PortletAttributeInstanceImpl portletAttributeInstance = new PortletAttributeInstanceImpl();
		return portletAttributeInstance;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public isChildPage createisChildPage() {
		isChildPageImpl isChildPage = new isChildPageImpl();
		return isChildPage;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public widthUnit createwidthUnitFromString(EDataType eDataType, String initialValue) {
		widthUnit result = widthUnit.get(initialValue);
		if (result == null) throw new IllegalArgumentException("The value '" + initialValue + "' is not a valid enumerator of '" + eDataType.getName() + "'");
		return result;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String convertwidthUnitToString(EDataType eDataType, Object instanceValue) {
		return instanceValue == null ? null : instanceValue.toString();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public InternalPortletType createInternalPortletTypeFromString(EDataType eDataType, String initialValue) {
		InternalPortletType result = InternalPortletType.get(initialValue);
		if (result == null) throw new IllegalArgumentException("The value '" + initialValue + "' is not a valid enumerator of '" + eDataType.getName() + "'");
		return result;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String convertInternalPortletTypeToString(EDataType eDataType, Object instanceValue) {
		return instanceValue == null ? null : instanceValue.toString();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public PortletTypeAttributeType createPortletTypeAttributeTypeFromString(EDataType eDataType, String initialValue) {
		PortletTypeAttributeType result = PortletTypeAttributeType.get(initialValue);
		if (result == null) throw new IllegalArgumentException("The value '" + initialValue + "' is not a valid enumerator of '" + eDataType.getName() + "'");
		return result;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String convertPortletTypeAttributeTypeToString(EDataType eDataType, Object instanceValue) {
		return instanceValue == null ? null : instanceValue.toString();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public PortalPackage getPortalPackage() {
		return (PortalPackage)getEPackage();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @deprecated
	 * @generated
	 */
	@Deprecated
	public static PortalPackage getPackage() {
		return PortalPackage.eINSTANCE;
	}

} //PortalFactoryImpl
