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
 * A representation of the model object '<em><b>Movable</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link com.bluexml.side.view.Movable#isMovable <em>Movable</em>}</li>
 * </ul>
 * </p>
 *
 * @see com.bluexml.side.view.ViewPackage#getMovable()
 * @model abstract="true"
 * @generated
 */
public interface Movable extends EObject {
	/**
	 * Returns the value of the '<em><b>Movable</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Movable</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Movable</em>' attribute.
	 * @see #setMovable(boolean)
	 * @see com.bluexml.side.view.ViewPackage#getMovable_Movable()
	 * @model
	 * @generated
	 */
	boolean isMovable();

	/**
	 * Sets the value of the '{@link com.bluexml.side.view.Movable#isMovable <em>Movable</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Movable</em>' attribute.
	 * @see #isMovable()
	 * @generated
	 */
	void setMovable(boolean value);
		
} // Movable
