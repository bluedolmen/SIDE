/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package com.bluexml.side.portal;

import com.bluexml.side.clazz.Clazz;
import com.bluexml.side.clazz.View;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Portlet Internal</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link com.bluexml.side.portal.PortletInternal#getType <em>Type</em>}</li>
 *   <li>{@link com.bluexml.side.portal.PortletInternal#getClass_ <em>Class</em>}</li>
 *   <li>{@link com.bluexml.side.portal.PortletInternal#getView <em>View</em>}</li>
 * </ul>
 * </p>
 *
 * @see com.bluexml.side.portal.PortalPackage#getPortletInternal()
 * @model
 * @generated
 */
public interface PortletInternal extends PortalModelElement {
	/**
	 * Returns the value of the '<em><b>Type</b></em>' attribute.
	 * The literals are from the enumeration {@link com.bluexml.side.portal.InternalPortletType}.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Type</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Type</em>' attribute.
	 * @see com.bluexml.side.portal.InternalPortletType
	 * @see #setType(InternalPortletType)
	 * @see com.bluexml.side.portal.PortalPackage#getPortletInternal_Type()
	 * @model
	 * @generated
	 */
	InternalPortletType getType();

	/**
	 * Sets the value of the '{@link com.bluexml.side.portal.PortletInternal#getType <em>Type</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Type</em>' attribute.
	 * @see com.bluexml.side.portal.InternalPortletType
	 * @see #getType()
	 * @generated
	 */
	void setType(InternalPortletType value);

	/**
	 * Returns the value of the '<em><b>Class</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Class</em>' reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Class</em>' reference.
	 * @see #setClass(Clazz)
	 * @see com.bluexml.side.portal.PortalPackage#getPortletInternal_Class()
	 * @model
	 * @generated
	 */
	Clazz getClass_();

	/**
	 * Sets the value of the '{@link com.bluexml.side.portal.PortletInternal#getClass_ <em>Class</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Class</em>' reference.
	 * @see #getClass_()
	 * @generated
	 */
	void setClass(Clazz value);

	/**
	 * Returns the value of the '<em><b>View</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>View</em>' reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>View</em>' reference.
	 * @see #setView(View)
	 * @see com.bluexml.side.portal.PortalPackage#getPortletInternal_View()
	 * @model
	 * @generated
	 */
	View getView();

	/**
	 * Sets the value of the '{@link com.bluexml.side.portal.PortletInternal#getView <em>View</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>View</em>' reference.
	 * @see #getView()
	 * @generated
	 */
	void setView(View value);

} // PortletInternal
