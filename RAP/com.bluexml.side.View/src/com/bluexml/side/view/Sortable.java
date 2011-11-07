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
 * A representation of the model object '<em><b>Sortable</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link com.bluexml.side.view.Sortable#getSorting <em>Sorting</em>}</li>
 * </ul>
 * </p>
 *
 * @see com.bluexml.side.view.ViewPackage#getSortable()
 * @model abstract="true"
 * @generated
 */
public interface Sortable extends EObject {
	/**
	 * Returns the value of the '<em><b>Sorting</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Sorting</em>' containment reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Sorting</em>' containment reference.
	 * @see #setSorting(Sorting)
	 * @see com.bluexml.side.view.ViewPackage#getSortable_Sorting()
	 * @model containment="true"
	 * @generated
	 */
	Sorting getSorting();

	/**
	 * Sets the value of the '{@link com.bluexml.side.view.Sortable#getSorting <em>Sorting</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Sorting</em>' containment reference.
	 * @see #getSorting()
	 * @generated
	 */
	void setSorting(Sorting value);
		
} // Sortable
