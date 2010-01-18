/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package com.bluexml.side.form;

import org.eclipse.emf.common.util.EList;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Form Group</b></em>'.
 * <!-- end-user-doc -->
 *
 * <!-- begin-model-doc -->
 * Definition: The FormGroup defines a logical group of fields. This logical grouping is used to generate tabs, rows group, …. in the generated form. 
 * Constrinat/Limit: The creation of FormGroup is achieved by using the ‘Group in a new group’ operation of Field. 
 * Inherits:  FormElement. 
 * <!-- end-model-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link com.bluexml.side.form.FormGroup#getChildren <em>Children</em>}</li>
 *   <li>{@link com.bluexml.side.form.FormGroup#getPresentation <em>Presentation</em>}</li>
 *   <li>{@link com.bluexml.side.form.FormGroup#getDisabled <em>Disabled</em>}</li>
 * </ul>
 * </p>
 *
 * @see com.bluexml.side.form.FormPackage#getFormGroup()
 * @model
 * @generated
 */
public interface FormGroup extends FormElement {
	/**
	 * Returns the value of the '<em><b>Children</b></em>' containment reference list.
	 * The list contents are of type {@link com.bluexml.side.form.FormElement}.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Children</em>' containment reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Children</em>' containment reference list.
	 * @see com.bluexml.side.form.FormPackage#getFormGroup_Children()
	 * @model containment="true"
	 * @generated
	 */
	EList<FormElement> getChildren();

	/**
	 * Returns the value of the '<em><b>Presentation</b></em>' attribute.
	 * The literals are from the enumeration {@link com.bluexml.side.form.FormGroupPresentationType}.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Presentation</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * Definition: The type of presentation to propose for the group:
	 * - auto: the generator decides the way to present the group.
	 * - horizontal: the group is available under an horizontal separation.
	 * - vertical: the group is available under a vertical separation.
	 * - Tabbed: the group is available under a tab.
	 * - borderless: the group is available under a separation without borders.
	 * 
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>Presentation</em>' attribute.
	 * @see com.bluexml.side.form.FormGroupPresentationType
	 * @see #setPresentation(FormGroupPresentationType)
	 * @see com.bluexml.side.form.FormPackage#getFormGroup_Presentation()
	 * @model extendedMetaData="name='presentation'"
	 * @generated
	 */
	FormGroupPresentationType getPresentation();

	/**
	 * Sets the value of the '{@link com.bluexml.side.form.FormGroup#getPresentation <em>Presentation</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Presentation</em>' attribute.
	 * @see com.bluexml.side.form.FormGroupPresentationType
	 * @see #getPresentation()
	 * @generated
	 */
	void setPresentation(FormGroupPresentationType value);

	/**
	 * Returns the value of the '<em><b>Disabled</b></em>' containment reference list.
	 * The list contents are of type {@link com.bluexml.side.form.FormElement}.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Disabled</em>' containment reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Disabled</em>' containment reference list.
	 * @see com.bluexml.side.form.FormPackage#getFormGroup_Disabled()
	 * @model containment="true"
	 * @generated
	 */
	EList<FormElement> getDisabled();

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * Definition: to get the list of the fields of the FormGroup.
	 * <!-- end-model-doc -->
	 * @model kind="operation"
	 *        annotation="http://www.bluexml.com/OCL body='self.children->select(oclIsKindOf(Field)).oclAsType(Field)->union(self.children->select(oclIsKindOf(FormGroup)).oclAsType(FormGroup).getFields().oclAsType(Field)).oclAsType(Field)'"
	 * @generated
	 */
	EList<Field> getFields();

} // FormGroup
