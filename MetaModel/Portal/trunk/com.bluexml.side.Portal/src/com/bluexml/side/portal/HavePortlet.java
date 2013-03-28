/*
    Copyright (C) 2007-20013  BlueXML - www.bluexml.com

    This program is free software: you can redistribute it and/or modify
    it under the terms of the GNU Affero General Public License as
    published by the Free Software Foundation, either version 3 of the
    License, or (at your option) any later version.

    This program is distributed in the hope that it will be useful,
    but WITHOUT ANY WARRANTY; without even the implied warranty of
    MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
    GNU Affero General Public License for more details.

    You should have received a copy of the GNU Affero General Public License
    along with this program.  If not, see <http://www.gnu.org/licenses/>.
*/
/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package com.bluexml.side.portal;

import com.bluexml.side.common.ModelElement;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EObject;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Have Portlet</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link com.bluexml.side.portal.HavePortlet#getAssociationPortlet <em>Association Portlet</em>}</li>
 *   <li>{@link com.bluexml.side.portal.HavePortlet#getAssociationPage <em>Association Page</em>}</li>
 *   <li>{@link com.bluexml.side.portal.HavePortlet#getPositionGroup <em>Position Group</em>}</li>
 * </ul>
 * </p>
 *
 * @see com.bluexml.side.portal.PortalPackage#getHavePortlet()
 * @model annotation="http://www.bluexml.com/OCL isvalide='not (self.associationPage.oclIsUndefined() or self.associationPortlet.oclIsUndefined())'"
 *        annotation="http://www.eclipse.org/emf/2002/Ecore constraints='isvalide'"
 * @generated
 */
public interface HavePortlet extends ModelElement {
	/**
	 * Returns the value of the '<em><b>Association Portlet</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Association Portlet</em>' reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Association Portlet</em>' reference.
	 * @see #setAssociationPortlet(Portlet)
	 * @see com.bluexml.side.portal.PortalPackage#getHavePortlet_AssociationPortlet()
	 * @model
	 * @generated
	 */
	Portlet getAssociationPortlet();

	/**
	 * Sets the value of the '{@link com.bluexml.side.portal.HavePortlet#getAssociationPortlet <em>Association Portlet</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Association Portlet</em>' reference.
	 * @see #getAssociationPortlet()
	 * @generated
	 */
	void setAssociationPortlet(Portlet value);

	/**
	 * Returns the value of the '<em><b>Association Page</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Association Page</em>' reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Association Page</em>' reference.
	 * @see #setAssociationPage(Page)
	 * @see com.bluexml.side.portal.PortalPackage#getHavePortlet_AssociationPage()
	 * @model
	 * @generated
	 */
	Page getAssociationPage();

	/**
	 * Sets the value of the '{@link com.bluexml.side.portal.HavePortlet#getAssociationPage <em>Association Page</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Association Page</em>' reference.
	 * @see #getAssociationPage()
	 * @generated
	 */
	void setAssociationPage(Page value);

	/**
	 * Returns the value of the '<em><b>Position Group</b></em>' containment reference list.
	 * The list contents are of type {@link com.bluexml.side.portal.PositionGroup}.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Position Group</em>' containment reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Position Group</em>' containment reference list.
	 * @see com.bluexml.side.portal.PortalPackage#getHavePortlet_PositionGroup()
	 * @model containment="true"
	 * @generated
	 */
	EList<PositionGroup> getPositionGroup();

} // HavePortlet
