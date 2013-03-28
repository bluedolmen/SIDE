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
package com.bluexml.side.view;


/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Select Field</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link com.bluexml.side.view.SelectField#getSelectWidget <em>Select Widget</em>}</li>
 * </ul>
 * </p>
 *
 * @see com.bluexml.side.view.ViewPackage#getSelectField()
 * @model
 * @generated
 */
public interface SelectField extends Field {
	/**
	 * Returns the value of the '<em><b>Select Widget</b></em>' attribute.
	 * The literals are from the enumeration {@link com.bluexml.side.view.SelectWidgetType}.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Select Widget</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Select Widget</em>' attribute.
	 * @see com.bluexml.side.view.SelectWidgetType
	 * @see #setSelectWidget(SelectWidgetType)
	 * @see com.bluexml.side.view.ViewPackage#getSelectField_SelectWidget()
	 * @model
	 * @generated
	 */
	SelectWidgetType getSelectWidget();

	/**
	 * Sets the value of the '{@link com.bluexml.side.view.SelectField#getSelectWidget <em>Select Widget</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Select Widget</em>' attribute.
	 * @see com.bluexml.side.view.SelectWidgetType
	 * @see #getSelectWidget()
	 * @generated
	 */
	void setSelectWidget(SelectWidgetType value);
		
} // SelectField
