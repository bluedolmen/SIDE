/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package com.bluexml.side.portal;

import com.bluexml.side.common.Visibility;
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
 *   <li>{@link com.bluexml.side.portal.Page#getUseLayout <em>Use Layout</em>}</li>
 *   <li>{@link com.bluexml.side.portal.Page#getPortlets <em>Portlets</em>}</li>
 *   <li>{@link com.bluexml.side.portal.Page#getPosition <em>Position</em>}</li>
 *   <li>{@link com.bluexml.side.portal.Page#getIsChildPageOf <em>Is Child Page Of</em>}</li>
 *   <li>{@link com.bluexml.side.portal.Page#getVisibility <em>Visibility</em>}</li>
 *   <li>{@link com.bluexml.side.portal.Page#isGenerate <em>Generate</em>}</li>
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

	/**
	 * Returns the value of the '<em><b>Visibility</b></em>' attribute.
	 * The literals are from the enumeration {@link com.bluexml.side.common.Visibility}.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Visibility</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Visibility</em>' attribute.
	 * @see com.bluexml.side.common.Visibility
	 * @see #setVisibility(Visibility)
	 * @see com.bluexml.side.portal.PortalPackage#getPage_Visibility()
	 * @model
	 * @generated
	 */
	Visibility getVisibility();

	/**
	 * Sets the value of the '{@link com.bluexml.side.portal.Page#getVisibility <em>Visibility</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Visibility</em>' attribute.
	 * @see com.bluexml.side.common.Visibility
	 * @see #getVisibility()
	 * @generated
	 */
	void setVisibility(Visibility value);

	/**
	 * Returns the value of the '<em><b>Generate</b></em>' attribute.
	 * The default value is <code>"true"</code>.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Generate</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Generate</em>' attribute.
	 * @see #setGenerate(boolean)
	 * @see com.bluexml.side.portal.PortalPackage#getPage_Generate()
	 * @model default="true"
	 * @generated
	 */
	boolean isGenerate();

	/**
	 * Sets the value of the '{@link com.bluexml.side.portal.Page#isGenerate <em>Generate</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Generate</em>' attribute.
	 * @see #isGenerate()
	 * @generated
	 */
	void setGenerate(boolean value);

} // Page
