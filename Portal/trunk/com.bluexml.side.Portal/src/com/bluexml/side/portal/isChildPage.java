/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package com.bluexml.side.portal;

import org.eclipse.emf.ecore.EObject;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>is Child Page</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link com.bluexml.side.portal.isChildPage#isInherit <em>Inherit</em>}</li>
 *   <li>{@link com.bluexml.side.portal.isChildPage#getIsChildPageOf <em>Is Child Page Of</em>}</li>
 * </ul>
 * </p>
 *
 * @see com.bluexml.side.portal.PortalPackage#getisChildPage()
 * @model
 * @generated
 */
public interface isChildPage extends EObject {
	/**
	 * Returns the value of the '<em><b>Inherit</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Inherit</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Inherit</em>' attribute.
	 * @see #setInherit(boolean)
	 * @see com.bluexml.side.portal.PortalPackage#getisChildPage_Inherit()
	 * @model
	 * @generated
	 */
	boolean isInherit();

	/**
	 * Sets the value of the '{@link com.bluexml.side.portal.isChildPage#isInherit <em>Inherit</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Inherit</em>' attribute.
	 * @see #isInherit()
	 * @generated
	 */
	void setInherit(boolean value);

	/**
	 * Returns the value of the '<em><b>Is Child Page Of</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Is Child Page Of</em>' reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Is Child Page Of</em>' reference.
	 * @see #setIsChildPageOf(Page)
	 * @see com.bluexml.side.portal.PortalPackage#getisChildPage_IsChildPageOf()
	 * @model
	 * @generated
	 */
	Page getIsChildPageOf();

	/**
	 * Sets the value of the '{@link com.bluexml.side.portal.isChildPage#getIsChildPageOf <em>Is Child Page Of</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Is Child Page Of</em>' reference.
	 * @see #getIsChildPageOf()
	 * @generated
	 */
	void setIsChildPageOf(Page value);

} // isChildPage
