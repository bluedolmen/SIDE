/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package com.bluexml.side.view;

import com.bluexml.side.common.ModelElement;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Abstract View Of</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link com.bluexml.side.view.AbstractViewOf#getViewOf <em>View Of</em>}</li>
 * </ul>
 * </p>
 *
 * @see com.bluexml.side.view.ViewPackage#getAbstractViewOf()
 * @model abstract="true"
 * @generated
 */
public interface AbstractViewOf extends AbstractView {
	/**
	 * Returns the value of the '<em><b>View Of</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>View Of</em>' reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>View Of</em>' reference.
	 * @see #setViewOf(ModelElement)
	 * @see com.bluexml.side.view.ViewPackage#getAbstractViewOf_ViewOf()
	 * @model
	 * @generated
	 */
	ModelElement getViewOf();

	/**
	 * Sets the value of the '{@link com.bluexml.side.view.AbstractViewOf#getViewOf <em>View Of</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>View Of</em>' reference.
	 * @see #getViewOf()
	 * @generated
	 */
	void setViewOf(ModelElement value);

} // AbstractViewOf
