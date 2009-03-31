/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package com.bluexml.side.form;

import org.eclipse.emf.common.util.EList;

import org.eclipse.emf.ecore.EObject;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Form Collection</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link com.bluexml.side.form.FormCollection#getForms <em>Forms</em>}</li>
 * </ul>
 * </p>
 *
 * @see com.bluexml.side.form.formPackage#getFormCollection()
 * @model
 * @generated
 */
public interface FormCollection extends EObject {
	/**
	 * Returns the value of the '<em><b>Forms</b></em>' containment reference list.
	 * The list contents are of type {@link com.bluexml.side.form.Form}.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Forms</em>' containment reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Forms</em>' containment reference list.
	 * @see com.bluexml.side.form.formPackage#getFormCollection_Forms()
	 * @model containment="true"
	 * @generated
	 */
	EList<Form> getForms();

} // FormCollection
