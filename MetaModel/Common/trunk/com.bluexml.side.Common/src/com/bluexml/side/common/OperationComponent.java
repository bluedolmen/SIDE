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
package com.bluexml.side.common;

import org.eclipse.emf.common.util.EList;


/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Operation Component</b></em>'.
 * <!-- end-user-doc -->
 *
 *
 * @see com.bluexml.side.common.CommonPackage#getOperationComponent()
 * @model abstract="true"
 * @generated
 */
public interface OperationComponent extends NamedModelElement {

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @model kind="operation"
	 *        annotation="http://www.bluexml.com/OCL body='if (self.oclIsKindOf(OperationGroup)) then\n\tself.oclAsType(OperationGroup).children->iterate(e:OperationComponent; result :Set(OperationComponent)=Set{}|result->union(e.getOperations()))->flatten()->asOrderedSet()\nelse\n\tself.oclAsType(OperationComponent)->asOrderedSet()\nendif'"
	 * @generated
	 */
	EList<OperationComponent> getOperations();
} // OperationComponent
