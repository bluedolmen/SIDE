/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package com.bluexml.side.portal;

import org.eclipse.emf.common.util.EList;


/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Portlet</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link com.bluexml.side.portal.Portlet#getName <em>Name</em>}</li>
 *   <li>{@link com.bluexml.side.portal.Portlet#getIsPortletInternal <em>Is Portlet Internal</em>}</li>
 *   <li>{@link com.bluexml.side.portal.Portlet#getIsInstanceOfPortletType <em>Is Instance Of Portlet Type</em>}</li>
 *   <li>{@link com.bluexml.side.portal.Portlet#getTitle <em>Title</em>}</li>
 *   <li>{@link com.bluexml.side.portal.Portlet#getSubPortlets <em>Sub Portlets</em>}</li>
 * </ul>
 * </p>
 *
 * @see com.bluexml.side.portal.PortalPackage#getPortlet()
 * @model annotation="http://www.bluexml.com/OCL haveType='not (self.isPortletInternal.oclIsUndefined() and self.isInstanceOfPortletType.oclIsUndefined() and self.subPortlets->size() = 0 and metainfo->size() = 0)'"
 *        annotation="http://www.eclipse.org/emf/2002/Ecore constraints='haveType'"
 * @generated
 */
public interface Portlet extends PortalModelElement {
	/**
	 * Returns the value of the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Name</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Name</em>' attribute.
	 * @see #setName(String)
	 * @see com.bluexml.side.portal.PortalPackage#getPortlet_Name()
	 * @model unique="false"
	 * @generated
	 */
	String getName();

	/**
	 * Sets the value of the '{@link com.bluexml.side.portal.Portlet#getName <em>Name</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Name</em>' attribute.
	 * @see #getName()
	 * @generated
	 */
	void setName(String value);

	/**
	 * Returns the value of the '<em><b>Is Portlet Internal</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Is Portlet Internal</em>' reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Is Portlet Internal</em>' reference.
	 * @see #setIsPortletInternal(PortletInternal)
	 * @see com.bluexml.side.portal.PortalPackage#getPortlet_IsPortletInternal()
	 * @model
	 * @generated
	 */
	PortletInternal getIsPortletInternal();

	/**
	 * Sets the value of the '{@link com.bluexml.side.portal.Portlet#getIsPortletInternal <em>Is Portlet Internal</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Is Portlet Internal</em>' reference.
	 * @see #getIsPortletInternal()
	 * @generated
	 */
	void setIsPortletInternal(PortletInternal value);

	/**
	 * Returns the value of the '<em><b>Is Instance Of Portlet Type</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Is Instance Of Portlet Type</em>' containment reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Is Instance Of Portlet Type</em>' containment reference.
	 * @see #setIsInstanceOfPortletType(InstanciatePortletType)
	 * @see com.bluexml.side.portal.PortalPackage#getPortlet_IsInstanceOfPortletType()
	 * @model containment="true"
	 * @generated
	 */
	InstanciatePortletType getIsInstanceOfPortletType();

	/**
	 * Sets the value of the '{@link com.bluexml.side.portal.Portlet#getIsInstanceOfPortletType <em>Is Instance Of Portlet Type</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Is Instance Of Portlet Type</em>' containment reference.
	 * @see #getIsInstanceOfPortletType()
	 * @generated
	 */
	void setIsInstanceOfPortletType(InstanciatePortletType value);

	/**
	 * Returns the value of the '<em><b>Title</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Title</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Title</em>' attribute.
	 * @see #setTitle(String)
	 * @see com.bluexml.side.portal.PortalPackage#getPortlet_Title()
	 * @model
	 * @generated
	 */
	String getTitle();

	/**
	 * Sets the value of the '{@link com.bluexml.side.portal.Portlet#getTitle <em>Title</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Title</em>' attribute.
	 * @see #getTitle()
	 * @generated
	 */
	void setTitle(String value);

	/**
	 * Returns the value of the '<em><b>Sub Portlets</b></em>' reference list.
	 * The list contents are of type {@link com.bluexml.side.portal.Portlet}.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Sub Portlets</em>' reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Sub Portlets</em>' reference list.
	 * @see com.bluexml.side.portal.PortalPackage#getPortlet_SubPortlets()
	 * @model
	 * @generated
	 */
	EList<Portlet> getSubPortlets();

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @model kind="operation" required="true"
	 *        annotation="http://www.bluexml.com/OCL body='if self.title.oclIsUndefined() or self.title.size() = 0 then\r self.name \relse\r self.title \rendif'"
	 * @generated
	 */
	String getLabel();

} // Portlet
