/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package com.bluexml.side.form;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Char Field</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link com.bluexml.side.form.CharField#getMin_length <em>Min length</em>}</li>
 *   <li>{@link com.bluexml.side.form.CharField#getMax_length <em>Max length</em>}</li>
 * </ul>
 * </p>
 *
 * @see com.bluexml.side.form.FormPackage#getCharField()
 * @model annotation="http://www.eclipse.org/emf/2002/Ecore constraints='MinSuperiorToMax'"
 *        annotation="http://www.bluexml.com/OCL MinSuperiorToMax='self.min_length <= self.max_length'"
 * @generated
 */
public interface CharField extends Field {
	/**
	 * Returns the value of the '<em><b>Min length</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Min length</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Min length</em>' attribute.
	 * @see #setMin_length(int)
	 * @see com.bluexml.side.form.FormPackage#getCharField_Min_length()
	 * @model
	 * @generated
	 */
	int getMin_length();

	/**
	 * Sets the value of the '{@link com.bluexml.side.form.CharField#getMin_length <em>Min length</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Min length</em>' attribute.
	 * @see #getMin_length()
	 * @generated
	 */
	void setMin_length(int value);

	/**
	 * Returns the value of the '<em><b>Max length</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Max length</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Max length</em>' attribute.
	 * @see #setMax_length(int)
	 * @see com.bluexml.side.form.FormPackage#getCharField_Max_length()
	 * @model
	 * @generated
	 */
	int getMax_length();

	/**
	 * Sets the value of the '{@link com.bluexml.side.form.CharField#getMax_length <em>Max length</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Max length</em>' attribute.
	 * @see #getMax_length()
	 * @generated
	 */
	void setMax_length(int value);

} // CharField
