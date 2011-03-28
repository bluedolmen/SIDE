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
