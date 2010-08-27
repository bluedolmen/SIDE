/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package com.bluexml.side.view;

import org.eclipse.emf.common.util.EList;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Field Container</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link com.bluexml.side.view.FieldContainer#getChildren <em>Children</em>}</li>
 *   <li>{@link com.bluexml.side.view.FieldContainer#getDisabled <em>Disabled</em>}</li>
 * </ul>
 * </p>
 *
 * @see com.bluexml.side.view.ViewPackage#getFieldContainer()
 * @model abstract="true"
 * @generated
 */
public interface FieldContainer extends FieldElement {
	/**
	 * Returns the value of the '<em><b>Children</b></em>' containment reference list.
	 * The list contents are of type {@link com.bluexml.side.view.FieldElement}.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * use to list undesirable item
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>Children</em>' containment reference list.
	 * @see com.bluexml.side.view.ViewPackage#getFieldContainer_Children()
	 * @model containment="true"
	 * @generated
	 */
	EList<FieldElement> getChildren();

	/**
	 * Returns the value of the '<em><b>Disabled</b></em>' containment reference list.
	 * The list contents are of type {@link com.bluexml.side.view.FieldElement}.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * list fieldElement that must be hidden
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>Disabled</em>' containment reference list.
	 * @see com.bluexml.side.view.ViewPackage#getFieldContainer_Disabled()
	 * @model containment="true"
	 * @generated
	 */
	EList<FieldElement> getDisabled();
		
} // FieldContainer
