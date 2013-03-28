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
package com.bluexml.side.form;


/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Model Choice Search Field</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link com.bluexml.side.form.ModelChoiceSearchField#getWidget <em>Widget</em>}</li>
 * </ul>
 * </p>
 *
 * @see com.bluexml.side.form.FormPackage#getModelChoiceSearchField()
 * @model annotation="http://www.bluexml.com/OCL valideAssociationRef='self.ref.oclAsType(clazz::Association).getTarget()->includes(self.real_class)'"
 *        annotation="http://www.eclipse.org/emf/2002/Ecore constraints='valideAssociationRef'"
 * @generated
 */
public interface ModelChoiceSearchField extends SearchField, ClassReference {

	/**
	 * Returns the value of the '<em><b>Widget</b></em>' attribute.
	 * The literals are from the enumeration {@link com.bluexml.side.form.ModelChoiceWidgetType}.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * Definition: The 'widget' attributes provides the layout for model choice field.
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>Widget</em>' attribute.
	 * @see com.bluexml.side.form.ModelChoiceWidgetType
	 * @see #setWidget(ModelChoiceWidgetType)
	 * @see com.bluexml.side.form.FormPackage#getModelChoiceSearchField_Widget()
	 * @model
	 * @generated
	 */
	ModelChoiceWidgetType getWidget();

	/**
	 * Sets the value of the '{@link com.bluexml.side.form.ModelChoiceSearchField#getWidget <em>Widget</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Widget</em>' attribute.
	 * @see com.bluexml.side.form.ModelChoiceWidgetType
	 * @see #getWidget()
	 * @generated
	 */
	void setWidget(ModelChoiceWidgetType value);
} // ModelChoiceSearchField
