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
 * A representation of the model object '<em><b>Page</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link com.bluexml.side.portal.Page#getID <em>ID</em>}</li>
 *   <li>{@link com.bluexml.side.portal.Page#getTitle <em>Title</em>}</li>
 *   <li>{@link com.bluexml.side.portal.Page#getDescription <em>Description</em>}</li>
 *   <li>{@link com.bluexml.side.portal.Page#getUseLayout <em>Use Layout</em>}</li>
 *   <li>{@link com.bluexml.side.portal.Page#getPortlets <em>Portlets</em>}</li>
 *   <li>{@link com.bluexml.side.portal.Page#getPosition <em>Position</em>}</li>
 *   <li>{@link com.bluexml.side.portal.Page#getIsChildPageOf <em>Is Child Page Of</em>}</li>
 * </ul>
 * </p>
 *
 * @see com.bluexml.side.portal.PortalPackage#getPage()
 * @model
 * @generated
 */
public interface Page extends PortalModelElement {
	/**
	 * Returns the value of the '<em><b>ID</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>ID</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>ID</em>' attribute.
	 * @see #setID(String)
	 * @see com.bluexml.side.portal.PortalPackage#getPage_ID()
	 * @model id="true"
	 * @generated
	 */
	String getID();

	/**
	 * Sets the value of the '{@link com.bluexml.side.portal.Page#getID <em>ID</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>ID</em>' attribute.
	 * @see #getID()
	 * @generated
	 */
	void setID(String value);

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
	 * @see com.bluexml.side.portal.PortalPackage#getPage_Title()
	 * @model
	 * @generated
	 */
	String getTitle();

	/**
	 * Sets the value of the '{@link com.bluexml.side.portal.Page#getTitle <em>Title</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Title</em>' attribute.
	 * @see #getTitle()
	 * @generated
	 */
	void setTitle(String value);

	/**
	 * Returns the value of the '<em><b>Description</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Description</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Description</em>' attribute.
	 * @see #setDescription(String)
	 * @see com.bluexml.side.portal.PortalPackage#getPage_Description()
	 * @model
	 * @generated
	 */
	String getDescription();

	/**
	 * Sets the value of the '{@link com.bluexml.side.portal.Page#getDescription <em>Description</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Description</em>' attribute.
	 * @see #getDescription()
	 * @generated
	 */
	void setDescription(String value);

	/**
	 * Returns the value of the '<em><b>Use Layout</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Use Layout</em>' reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Use Layout</em>' reference.
	 * @see #setUseLayout(PortalLayout)
	 * @see com.bluexml.side.portal.PortalPackage#getPage_UseLayout()
	 * @model
	 * @generated
	 */
	PortalLayout getUseLayout();

	/**
	 * Sets the value of the '{@link com.bluexml.side.portal.Page#getUseLayout <em>Use Layout</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Use Layout</em>' reference.
	 * @see #getUseLayout()
	 * @generated
	 */
	void setUseLayout(PortalLayout value);

	/**
	 * Returns the value of the '<em><b>Portlets</b></em>' containment reference list.
	 * The list contents are of type {@link com.bluexml.side.portal.HavePortlet}.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Portlets</em>' containment reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Portlets</em>' containment reference list.
	 * @see com.bluexml.side.portal.PortalPackage#getPage_Portlets()
	 * @model containment="true"
	 * @generated
	 */
	EList<HavePortlet> getPortlets();

	/**
	 * Returns the value of the '<em><b>Position</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Position</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Position</em>' attribute.
	 * @see #setPosition(int)
	 * @see com.bluexml.side.portal.PortalPackage#getPage_Position()
	 * @model
	 * @generated
	 */
	int getPosition();

	/**
	 * Sets the value of the '{@link com.bluexml.side.portal.Page#getPosition <em>Position</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Position</em>' attribute.
	 * @see #getPosition()
	 * @generated
	 */
	void setPosition(int value);

	/**
	 * Returns the value of the '<em><b>Is Child Page Of</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Is Child Page Of</em>' containment reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Is Child Page Of</em>' containment reference.
	 * @see #setIsChildPageOf(isChildPage)
	 * @see com.bluexml.side.portal.PortalPackage#getPage_IsChildPageOf()
	 * @model containment="true"
	 * @generated
	 */
	isChildPage getIsChildPageOf();

	/**
	 * Sets the value of the '{@link com.bluexml.side.portal.Page#getIsChildPageOf <em>Is Child Page Of</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Is Child Page Of</em>' containment reference.
	 * @see #getIsChildPageOf()
	 * @generated
	 */
	void setIsChildPageOf(isChildPage value);

} // Page
