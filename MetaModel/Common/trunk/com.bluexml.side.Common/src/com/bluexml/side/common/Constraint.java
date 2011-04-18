/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package com.bluexml.side.common;

import org.eclipse.emf.common.util.EList;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Constraint</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link com.bluexml.side.common.Constraint#getConstraintType <em>Constraint Type</em>}</li>
 *   <li>{@link com.bluexml.side.common.Constraint#getParams <em>Params</em>}</li>
 * </ul>
 * </p>
 *
 * @see com.bluexml.side.common.CommonPackage#getConstraint()
 * @model
 * @generated
 */
public interface Constraint extends NamedModelElement {
	/**
	 * Returns the value of the '<em><b>Constraint Type</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Constraint Type</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Constraint Type</em>' attribute.
	 * @see #setConstraintType(String)
	 * @see com.bluexml.side.common.CommonPackage#getConstraint_ConstraintType()
	 * @model
	 * @generated
	 */
	String getConstraintType();

	/**
	 * Sets the value of the '{@link com.bluexml.side.common.Constraint#getConstraintType <em>Constraint Type</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Constraint Type</em>' attribute.
	 * @see #getConstraintType()
	 * @generated
	 */
	void setConstraintType(String value);

	/**
	 * Returns the value of the '<em><b>Params</b></em>' containment reference list.
	 * The list contents are of type {@link com.bluexml.side.common.ConstraintParam}.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Params</em>' containment reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Params</em>' containment reference list.
	 * @see com.bluexml.side.common.CommonPackage#getConstraint_Params()
	 * @model containment="true"
	 * @generated
	 */
	EList<ConstraintParam> getParams();
		
} // Constraint
