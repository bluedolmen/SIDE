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
 * A representation of the model object '<em><b>Stylable</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link com.bluexml.side.view.Stylable#getStyling <em>Styling</em>}</li>
 * </ul>
 * </p>
 *
 * @see com.bluexml.side.view.ViewPackage#getStylable()
 * @model abstract="true"
 * @generated
 */
public interface Stylable extends EObject {
	/**
	 * Returns the value of the '<em><b>Styling</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Styling</em>' containment reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Styling</em>' containment reference.
	 * @see #setStyling(Styling)
	 * @see com.bluexml.side.view.ViewPackage#getStylable_Styling()
	 * @model containment="true"
	 * @generated
	 */
	Styling getStyling();

	/**
	 * Sets the value of the '{@link com.bluexml.side.view.Stylable#getStyling <em>Styling</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Styling</em>' containment reference.
	 * @see #getStyling()
	 * @generated
	 */
	void setStyling(Styling value);
		
} // Stylable
