/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package com.bluexml.side.common;

import org.eclipse.emf.common.util.EList;

import org.eclipse.emf.ecore.EObject;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Model Element</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link com.bluexml.side.common.ModelElement#getStereotypes <em>Stereotypes</em>}</li>
 *   <li>{@link com.bluexml.side.common.ModelElement#getTags <em>Tags</em>}</li>
 *   <li>{@link com.bluexml.side.common.ModelElement#getComments <em>Comments</em>}</li>
 * </ul>
 * </p>
 *
 * @see com.bluexml.side.common.CommonPackage#getModelElement()
 * @model
 * @generated
 */
public interface ModelElement extends EObject {
	/**
	 * Returns the value of the '<em><b>Stereotypes</b></em>' reference list.
	 * The list contents are of type {@link com.bluexml.side.common.Stereotype}.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Stereotypes</em>' reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Stereotypes</em>' reference list.
	 * @see com.bluexml.side.common.CommonPackage#getModelElement_Stereotypes()
	 * @model
	 * @generated
	 */
	EList<Stereotype> getStereotypes();

	/**
	 * Returns the value of the '<em><b>Tags</b></em>' containment reference list.
	 * The list contents are of type {@link com.bluexml.side.common.Tag}.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Tags</em>' containment reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Tags</em>' containment reference list.
	 * @see com.bluexml.side.common.CommonPackage#getModelElement_Tags()
	 * @model containment="true"
	 * @generated
	 */
	EList<Tag> getTags();

	/**
	 * Returns the value of the '<em><b>Comments</b></em>' containment reference list.
	 * The list contents are of type {@link com.bluexml.side.common.Comment}.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Comments</em>' containment reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Comments</em>' containment reference list.
	 * @see com.bluexml.side.common.CommonPackage#getModelElement_Comments()
	 * @model containment="true"
	 * @generated
	 */
	EList<Comment> getComments();

} // ModelElement
