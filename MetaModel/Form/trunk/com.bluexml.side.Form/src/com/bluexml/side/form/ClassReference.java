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

import org.eclipse.emf.ecore.EObject;

import com.bluexml.side.clazz.AbstractClass;



/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Class Reference</b></em>'.
 * <!-- end-user-doc -->
 *
 * <!-- begin-model-doc -->
 * Definition: The 'ClassReference' is abstract and so is not available through the Form modeler; it has been defined to allow to reference a data class of the data model: for instance, a FormClass and a ModelChoiceField inherits a ClassReference to be associated to a data class of the data model.
 *  The 'Real class' property of this element allows to select the referenced data class.
 * 
 * Example:
 * - Real class=Data class Article.
 * <!-- end-model-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link com.bluexml.side.form.ClassReference#getReal_class <em>Real class</em>}</li>
 * </ul>
 * </p>
 *
 * @see com.bluexml.side.form.FormPackage#getClassReference()
 * @model abstract="true"
 *        annotation="http://www.bluexml.com/OCL mustReferenceClass='not self.real_class.oclIsUndefined()'"
 *        annotation="http://www.eclipse.org/emf/2002/Ecore constraints='mustReferenceClass'"
 * @generated
 */
public interface ClassReference extends EObject {
	/**
	 * Returns the value of the '<em><b>Real class</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Real class</em>' reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Real class</em>' reference.
	 * @see #setReal_class(AbstractClass)
	 * @see com.bluexml.side.form.FormPackage#getClassReference_Real_class()
	 * @model
	 * @generated
	 */
	AbstractClass getReal_class();

	/**
	 * Sets the value of the '{@link com.bluexml.side.form.ClassReference#getReal_class <em>Real class</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Real class</em>' reference.
	 * @see #getReal_class()
	 * @generated
	 */
	void setReal_class(AbstractClass value);

} // ClassReference
