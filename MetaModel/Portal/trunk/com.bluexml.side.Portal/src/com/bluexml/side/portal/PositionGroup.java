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
import org.eclipse.emf.ecore.EObject;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Position Group</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link com.bluexml.side.portal.PositionGroup#getPosition <em>Position</em>}</li>
 *   <li>{@link com.bluexml.side.portal.PositionGroup#getOnColumn <em>On Column</em>}</li>
 *   <li>{@link com.bluexml.side.portal.PositionGroup#getOnLayout <em>On Layout</em>}</li>
 * </ul>
 * </p>
 *
 * @see com.bluexml.side.portal.PortalPackage#getPositionGroup()
 * @model annotation="http://www.bluexml.com/OCL unicPosition='PositionGroup.allInstances() -> select(x : PositionGroup | self.position = x.position and self.onColumn = x.onColumn and self.onLayout = x.onLayout) -> size() < 2 or self.getContainer().oclAsType(HavePortlet).metainfo -> select(x | x.key = \'region-id\') -> size() > 0'"
 *        annotation="http://www.eclipse.org/emf/2002/Ecore constraints='unicPosition'"
 * @generated
 */
public interface PositionGroup extends ModelElement {
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
	 * @see com.bluexml.side.portal.PortalPackage#getPositionGroup_Position()
	 * @model
	 * @generated
	 */
	int getPosition();

	/**
	 * Sets the value of the '{@link com.bluexml.side.portal.PositionGroup#getPosition <em>Position</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Position</em>' attribute.
	 * @see #getPosition()
	 * @generated
	 */
	void setPosition(int value);

	/**
	 * Returns the value of the '<em><b>On Column</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>On Column</em>' reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>On Column</em>' reference.
	 * @see #setOnColumn(Column)
	 * @see com.bluexml.side.portal.PortalPackage#getPositionGroup_OnColumn()
	 * @model
	 * @generated
	 */
	Column getOnColumn();

	/**
	 * Sets the value of the '{@link com.bluexml.side.portal.PositionGroup#getOnColumn <em>On Column</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>On Column</em>' reference.
	 * @see #getOnColumn()
	 * @generated
	 */
	void setOnColumn(Column value);

	/**
	 * Returns the value of the '<em><b>On Layout</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>On Layout</em>' reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>On Layout</em>' reference.
	 * @see #setOnLayout(PortalLayout)
	 * @see com.bluexml.side.portal.PortalPackage#getPositionGroup_OnLayout()
	 * @model
	 * @generated
	 */
	PortalLayout getOnLayout();

	/**
	 * Sets the value of the '{@link com.bluexml.side.portal.PositionGroup#getOnLayout <em>On Layout</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>On Layout</em>' reference.
	 * @see #getOnLayout()
	 * @generated
	 */
	void setOnLayout(PortalLayout value);

} // PositionGroup
