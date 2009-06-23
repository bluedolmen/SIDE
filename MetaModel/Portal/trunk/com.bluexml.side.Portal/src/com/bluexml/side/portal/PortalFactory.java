/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package com.bluexml.side.portal;

import org.eclipse.emf.ecore.EFactory;

/**
 * <!-- begin-user-doc -->
 * The <b>Factory</b> for the model.
 * It provides a create method for each non-abstract class of the model.
 * <!-- end-user-doc -->
 * @see com.bluexml.side.portal.PortalPackage
 * @generated
 */
public interface PortalFactory extends EFactory {
	/**
	 * The singleton instance of the factory.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	PortalFactory eINSTANCE = com.bluexml.side.portal.impl.PortalFactoryImpl.init();

	/**
	 * Returns a new object of class '<em>Portal</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Portal</em>'.
	 * @generated
	 */
	Portal createPortal();

	/**
	 * Returns a new object of class '<em>Page</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Page</em>'.
	 * @generated
	 */
	Page createPage();

	/**
	 * Returns a new object of class '<em>Layout</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Layout</em>'.
	 * @generated
	 */
	PortalLayout createPortalLayout();

	/**
	 * Returns a new object of class '<em>Column</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Column</em>'.
	 * @generated
	 */
	Column createColumn();

	/**
	 * Returns a new object of class '<em>Portlet</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Portlet</em>'.
	 * @generated
	 */
	Portlet createPortlet();

	/**
	 * Returns a new object of class '<em>Portlet Type</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Portlet Type</em>'.
	 * @generated
	 */
	PortletType createPortletType();

	/**
	 * Returns a new object of class '<em>Portlet Internal</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Portlet Internal</em>'.
	 * @generated
	 */
	PortletInternal createPortletInternal();

	/**
	 * Returns a new object of class '<em>Group</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Group</em>'.
	 * @generated
	 */
	Group createGroup();

	/**
	 * Returns a new object of class '<em>Portlet Attribute</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Portlet Attribute</em>'.
	 * @generated
	 */
	PortletAttribute createPortletAttribute();

	/**
	 * Returns a new object of class '<em>Have Portlet</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Have Portlet</em>'.
	 * @generated
	 */
	HavePortlet createHavePortlet();

	/**
	 * Returns a new object of class '<em>Position Group</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Position Group</em>'.
	 * @generated
	 */
	PositionGroup createPositionGroup();

	/**
	 * Returns a new object of class '<em>Instanciate Portlet Type</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Instanciate Portlet Type</em>'.
	 * @generated
	 */
	InstanciatePortletType createInstanciatePortletType();

	/**
	 * Returns a new object of class '<em>Portlet Attribute Instance</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Portlet Attribute Instance</em>'.
	 * @generated
	 */
	PortletAttributeInstance createPortletAttributeInstance();

	/**
	 * Returns a new object of class '<em>is Child Page</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>is Child Page</em>'.
	 * @generated
	 */
	isChildPage createisChildPage();

	/**
	 * Returns the package supported by this factory.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the package supported by this factory.
	 * @generated
	 */
	PortalPackage getPortalPackage();

} //PortalFactory
