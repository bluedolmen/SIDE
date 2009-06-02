/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package com.bluexml.side.view;

import org.eclipse.emf.ecore.EObject;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Paginable</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link com.bluexml.side.view.Paginable#getPaging <em>Paging</em>}</li>
 * </ul>
 * </p>
 *
 * @see com.bluexml.side.view.ViewPackage#getPaginable()
 * @model abstract="true"
 * @generated
 */
public interface Paginable extends EObject {
	/**
	 * Returns the value of the '<em><b>Paging</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Paging</em>' containment reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Paging</em>' containment reference.
	 * @see #setPaging(Paging)
	 * @see com.bluexml.side.view.ViewPackage#getPaginable_Paging()
	 * @model containment="true"
	 * @generated
	 */
	Paging getPaging();

	/**
	 * Sets the value of the '{@link com.bluexml.side.view.Paginable#getPaging <em>Paging</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Paging</em>' containment reference.
	 * @see #getPaging()
	 * @generated
	 */
	void setPaging(Paging value);
		
} // Paginable
