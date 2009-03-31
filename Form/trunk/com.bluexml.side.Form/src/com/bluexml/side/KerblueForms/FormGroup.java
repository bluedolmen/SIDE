/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package com.bluexml.side.KerblueForms;

import org.eclipse.emf.common.util.EList;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Form Group</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link com.bluexml.side.KerblueForms.FormGroup#getChildren <em>Children</em>}</li>
 *   <li>{@link com.bluexml.side.KerblueForms.FormGroup#getPresentation <em>Presentation</em>}</li>
 *   <li>{@link com.bluexml.side.KerblueForms.FormGroup#getDisabled <em>Disabled</em>}</li>
 * </ul>
 * </p>
 *
 * @see com.bluexml.side.KerblueForms.formPackage#getFormGroup()
 * @model
 * @generated
 */
public interface FormGroup extends FormElement {
	/**
	 * Returns the value of the '<em><b>Children</b></em>' containment reference list.
	 * The list contents are of type {@link com.bluexml.side.KerblueForms.FormElement}.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Children</em>' containment reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Children</em>' containment reference list.
	 * @see com.bluexml.side.KerblueForms.formPackage#getFormGroup_Children()
	 * @model containment="true"
	 * @generated
	 */
	EList<FormElement> getChildren();

	/**
	 * Returns the value of the '<em><b>Presentation</b></em>' attribute.
	 * The literals are from the enumeration {@link com.bluexml.side.KerblueForms.FormGroupPresentationType}.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Presentation</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Presentation</em>' attribute.
	 * @see com.bluexml.side.KerblueForms.FormGroupPresentationType
	 * @see #setPresentation(FormGroupPresentationType)
	 * @see com.bluexml.side.KerblueForms.formPackage#getFormGroup_Presentation()
	 * @model
	 * @generated
	 */
	FormGroupPresentationType getPresentation();

	/**
	 * Sets the value of the '{@link com.bluexml.side.KerblueForms.FormGroup#getPresentation <em>Presentation</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Presentation</em>' attribute.
	 * @see com.bluexml.side.KerblueForms.FormGroupPresentationType
	 * @see #getPresentation()
	 * @generated
	 */
	void setPresentation(FormGroupPresentationType value);

	/**
	 * Returns the value of the '<em><b>Disabled</b></em>' containment reference list.
	 * The list contents are of type {@link com.bluexml.side.KerblueForms.FormElement}.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Disabled</em>' containment reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Disabled</em>' containment reference list.
	 * @see com.bluexml.side.KerblueForms.formPackage#getFormGroup_Disabled()
	 * @model containment="true"
	 * @generated
	 */
	EList<FormElement> getDisabled();
		
} // FormGroup
