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

import org.eclipse.emf.common.util.EList;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Portal</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link com.bluexml.side.portal.Portal#getPageSet <em>Page Set</em>}</li>
 *   <li>{@link com.bluexml.side.portal.Portal#getLayoutSet <em>Layout Set</em>}</li>
 *   <li>{@link com.bluexml.side.portal.Portal#getPortletSet <em>Portlet Set</em>}</li>
 *   <li>{@link com.bluexml.side.portal.Portal#getPortletSetType <em>Portlet Set Type</em>}</li>
 *   <li>{@link com.bluexml.side.portal.Portal#getInternalPortletSet <em>Internal Portlet Set</em>}</li>
 * </ul>
 * </p>
 *
 * @see com.bluexml.side.portal.PortalPackage#getPortal()
 * @model annotation="http://www.bluexml.com/OCL portalNameEmpty='not (self.name.oclIsUndefined() or self.name =\'\')'"
 *        annotation="http://www.eclipse.org/emf/2002/Ecore constraints='portalNameEmpty'"
 * @generated
 */
public interface Portal extends com.bluexml.side.common.Package {
	/**
	 * Returns the value of the '<em><b>Page Set</b></em>' containment reference list.
	 * The list contents are of type {@link com.bluexml.side.portal.Page}.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Page Set</em>' containment reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Page Set</em>' containment reference list.
	 * @see com.bluexml.side.portal.PortalPackage#getPortal_PageSet()
	 * @model containment="true"
	 * @generated
	 */
	EList<Page> getPageSet();

	/**
	 * Returns the value of the '<em><b>Layout Set</b></em>' containment reference list.
	 * The list contents are of type {@link com.bluexml.side.portal.PortalLayout}.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Layout Set</em>' containment reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Layout Set</em>' containment reference list.
	 * @see com.bluexml.side.portal.PortalPackage#getPortal_LayoutSet()
	 * @model containment="true"
	 * @generated
	 */
	EList<PortalLayout> getLayoutSet();

	/**
	 * Returns the value of the '<em><b>Portlet Set</b></em>' containment reference list.
	 * The list contents are of type {@link com.bluexml.side.portal.Portlet}.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Portlet Set</em>' containment reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Portlet Set</em>' containment reference list.
	 * @see com.bluexml.side.portal.PortalPackage#getPortal_PortletSet()
	 * @model containment="true"
	 * @generated
	 */
	EList<Portlet> getPortletSet();

	/**
	 * Returns the value of the '<em><b>Portlet Set Type</b></em>' containment reference list.
	 * The list contents are of type {@link com.bluexml.side.portal.PortletType}.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Portlet Set Type</em>' containment reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Portlet Set Type</em>' containment reference list.
	 * @see com.bluexml.side.portal.PortalPackage#getPortal_PortletSetType()
	 * @model containment="true"
	 * @generated
	 */
	EList<PortletType> getPortletSetType();

	/**
	 * Returns the value of the '<em><b>Internal Portlet Set</b></em>' containment reference list.
	 * The list contents are of type {@link com.bluexml.side.portal.PortletInternal}.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Internal Portlet Set</em>' containment reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Internal Portlet Set</em>' containment reference list.
	 * @see com.bluexml.side.portal.PortalPackage#getPortal_InternalPortletSet()
	 * @model containment="true"
	 * @generated
	 */
	EList<PortletInternal> getInternalPortletSet();

} // Portal
