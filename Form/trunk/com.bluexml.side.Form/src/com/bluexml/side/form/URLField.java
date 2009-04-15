/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package com.bluexml.side.form;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>URL Field</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link com.bluexml.side.form.URLField#isVerify_exists <em>Verify exists</em>}</li>
 * </ul>
 * </p>
 *
 * @see com.bluexml.side.form.FormPackage#getURLField()
 * @model
 * @generated
 */
public interface URLField extends CharField {
	/**
	 * Returns the value of the '<em><b>Verify exists</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Verify exists</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Verify exists</em>' attribute.
	 * @see #setVerify_exists(boolean)
	 * @see com.bluexml.side.form.FormPackage#getURLField_Verify_exists()
	 * @model
	 * @generated
	 */
	boolean isVerify_exists();

	/**
	 * Sets the value of the '{@link com.bluexml.side.form.URLField#isVerify_exists <em>Verify exists</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Verify exists</em>' attribute.
	 * @see #isVerify_exists()
	 * @generated
	 */
	void setVerify_exists(boolean value);

} // URLField
