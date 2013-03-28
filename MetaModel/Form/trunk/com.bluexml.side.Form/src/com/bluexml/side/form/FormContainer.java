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
 * A representation of the model object '<em><b>Form Container</b></em>'.
 * <!-- end-user-doc -->
 *
 * <!-- begin-model-doc -->
 * Definition: The 'FormContainer' represents a form and is used as a container for elements. FormClass and FormWorkflow inherits of FormContainer. FormContainer is an abstract element and so does not directly appear in the Form modeler.
 * A FormCollection is at least composed of one FormContainer.
 * Inherits: FormGroup.
 * <!-- end-model-doc -->
 *
 *
 * @see com.bluexml.side.form.FormPackage#getFormContainer()
 * @model abstract="true"
 *        annotation="http://www.eclipse.org/emf/2002/Ecore constraints='validName TwoFormsWithSameName'"
 *        annotation="http://www.bluexml.com/OCL TwoFormsWithSameName='FormContainer.allInstances()->select(a | a.id = self.id and a <> self)->size() = 0'"
 * @generated
 */
public interface FormContainer extends FormGroup {
} // FormContainer
