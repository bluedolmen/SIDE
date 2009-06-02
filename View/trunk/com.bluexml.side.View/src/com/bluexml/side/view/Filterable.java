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
 * A representation of the model object '<em><b>Filterable</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link com.bluexml.side.view.Filterable#getFiltering <em>Filtering</em>}</li>
 * </ul>
 * </p>
 *
 * @see com.bluexml.side.view.ViewPackage#getFilterable()
 * @model abstract="true"
 * @generated
 */
public interface Filterable extends EObject {
	/**
	 * Returns the value of the '<em><b>Filtering</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Filtering</em>' containment reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Filtering</em>' containment reference.
	 * @see #setFiltering(Filtering)
	 * @see com.bluexml.side.view.ViewPackage#getFilterable_Filtering()
	 * @model containment="true"
	 * @generated
	 */
	Filtering getFiltering();

	/**
	 * Sets the value of the '{@link com.bluexml.side.view.Filterable#getFiltering <em>Filtering</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Filtering</em>' containment reference.
	 * @see #getFiltering()
	 * @generated
	 */
	void setFiltering(Filtering value);
		
} // Filterable
