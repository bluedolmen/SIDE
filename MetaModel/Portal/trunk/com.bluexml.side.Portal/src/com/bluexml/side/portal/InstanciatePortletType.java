/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package com.bluexml.side.portal;

import org.eclipse.emf.common.util.EList;

import org.eclipse.emf.ecore.EObject;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Instanciate Portlet Type</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link com.bluexml.side.portal.InstanciatePortletType#getPortletType <em>Portlet Type</em>}</li>
 *   <li>{@link com.bluexml.side.portal.InstanciatePortletType#getInstances <em>Instances</em>}</li>
 * </ul>
 * </p>
 *
 * @see com.bluexml.side.portal.PortalPackage#getInstanciatePortletType()
 * @model annotation="http://www.bluexml.com/OCL haveType='not self.portletType.oclIsUndefined()'"
 *        annotation="http://www.eclipse.org/emf/2002/Ecore constraints='haveType'"
 * @generated
 */
public interface InstanciatePortletType extends EObject {
	/**
	 * Returns the value of the '<em><b>Portlet Type</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Portlet Type</em>' reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Portlet Type</em>' reference.
	 * @see #setPortletType(PortletType)
	 * @see com.bluexml.side.portal.PortalPackage#getInstanciatePortletType_PortletType()
	 * @model
	 * @generated
	 */
	PortletType getPortletType();

	/**
	 * Sets the value of the '{@link com.bluexml.side.portal.InstanciatePortletType#getPortletType <em>Portlet Type</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Portlet Type</em>' reference.
	 * @see #getPortletType()
	 * @generated
	 */
	void setPortletType(PortletType value);

	/**
	 * Returns the value of the '<em><b>Instances</b></em>' containment reference list.
	 * The list contents are of type {@link com.bluexml.side.portal.PortletAttributeInstance}.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Instances</em>' containment reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Instances</em>' containment reference list.
	 * @see com.bluexml.side.portal.PortalPackage#getInstanciatePortletType_Instances()
	 * @model containment="true"
	 * @generated
	 */
	EList<PortletAttributeInstance> getInstances();

} // InstanciatePortletType
