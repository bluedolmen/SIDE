/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package com.bluexml.side.form;

import org.eclipse.emf.ecore.EObject;

import com.bluexml.side.clazz.Clazz;



/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Class Reference</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link com.bluexml.side.form.ClassReference#getReal_class <em>Real class</em>}</li>
 *   <li>{@link com.bluexml.side.form.ClassReference#getAssociation_class <em>Association class</em>}</li>
 * </ul>
 * </p>
 *
 * @see com.bluexml.side.form.formPackage#getClassReference()
 * @model abstract="true"
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
	 * @see #setReal_class(CharField)
	 * @see com.bluexml.side.form.formPackage#getClassReference_Real_class()
	 * @model
	 * @generated
	 */
	CharField getReal_class();

	/**
	 * Sets the value of the '{@link com.bluexml.side.form.ClassReference#getReal_class <em>Real class</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Real class</em>' reference.
	 * @see #getReal_class()
	 * @generated
	 */
	void setReal_class(CharField value);

	/**
	 * Returns the value of the '<em><b>Association class</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Association class</em>' reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Association class</em>' reference.
	 * @see #setAssociation_class(Clazz)
	 * @see com.bluexml.side.form.formPackage#getClassReference_Association_class()
	 * @model
	 * @generated
	 */
	Clazz getAssociation_class();

	/**
	 * Sets the value of the '{@link com.bluexml.side.form.ClassReference#getAssociation_class <em>Association class</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Association class</em>' reference.
	 * @see #getAssociation_class()
	 * @generated
	 */
	void setAssociation_class(Clazz value);

} // ClassReference
