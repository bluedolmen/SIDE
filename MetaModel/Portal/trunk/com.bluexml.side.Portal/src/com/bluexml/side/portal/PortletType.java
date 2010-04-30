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
 * A representation of the model object '<em><b>Portlet Type</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link com.bluexml.side.portal.PortletType#getId <em>Id</em>}</li>
 *   <li>{@link com.bluexml.side.portal.PortletType#getName <em>Name</em>}</li>
 *   <li>{@link com.bluexml.side.portal.PortletType#getHaveAttribute <em>Have Attribute</em>}</li>
 *   <li>{@link com.bluexml.side.portal.PortletType#isInstanceable <em>Instanceable</em>}</li>
 * </ul>
 * </p>
 *
 * @see com.bluexml.side.portal.PortalPackage#getPortletType()
 * @model annotation="http://www.bluexml.com/OCL haveIdentifier='not (self.id.oclIsUndefined() or self.id = \'\' or self.name.oclIsUndefined() or self.name. = \'\')'"
 *        annotation="http://www.eclipse.org/emf/2002/Ecore constraints='haveIdentifier'"
 * @generated
 */
public interface PortletType extends PortalModelElement {
	/**
	 * Returns the value of the '<em><b>Id</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Id</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Id</em>' attribute.
	 * @see #setId(String)
	 * @see com.bluexml.side.portal.PortalPackage#getPortletType_Id()
	 * @model
	 * @generated
	 */
	String getId();

	/**
	 * Sets the value of the '{@link com.bluexml.side.portal.PortletType#getId <em>Id</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Id</em>' attribute.
	 * @see #getId()
	 * @generated
	 */
	void setId(String value);

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
	 * @see com.bluexml.side.portal.PortalPackage#getPortletType_Name()
	 * @model
	 * @generated
	 */
	String getName();

	/**
	 * Sets the value of the '{@link com.bluexml.side.portal.PortletType#getName <em>Name</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Name</em>' attribute.
	 * @see #getName()
	 * @generated
	 */
	void setName(String value);

	/**
	 * Returns the value of the '<em><b>Have Attribute</b></em>' containment reference list.
	 * The list contents are of type {@link com.bluexml.side.portal.PortletAttribute}.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Have Attribute</em>' containment reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Have Attribute</em>' containment reference list.
	 * @see com.bluexml.side.portal.PortalPackage#getPortletType_HaveAttribute()
	 * @model containment="true"
	 * @generated
	 */
	EList<PortletAttribute> getHaveAttribute();

	/**
	 * Returns the value of the '<em><b>Instanceable</b></em>' attribute.
	 * The default value is <code>"true"</code>.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Instanceable</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Instanceable</em>' attribute.
	 * @see #setInstanceable(boolean)
	 * @see com.bluexml.side.portal.PortalPackage#getPortletType_Instanceable()
	 * @model default="true"
	 * @generated
	 */
	boolean isInstanceable();

	/**
	 * Sets the value of the '{@link com.bluexml.side.portal.PortletType#isInstanceable <em>Instanceable</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Instanceable</em>' attribute.
	 * @see #isInstanceable()
	 * @generated
	 */
	void setInstanceable(boolean value);

} // PortletType
