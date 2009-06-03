/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package com.bluexml.side.form;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>File Field</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link com.bluexml.side.form.FileField#getInRepository <em>In Repository</em>}</li>
 * </ul>
 * </p>
 *
 * @see com.bluexml.side.form.FormPackage#getFileField()
 * @model
 * @generated
 */
public interface FileField extends Field {

	/**
	 * Returns the value of the '<em><b>In Repository</b></em>' attribute.
	 * The default value is <code>"false"</code>.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>In Repository</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>In Repository</em>' attribute.
	 * @see #setInRepository(Boolean)
	 * @see com.bluexml.side.form.FormPackage#getFileField_InRepository()
	 * @model default="false"
	 * @generated
	 */
	Boolean getInRepository();

	/**
	 * Sets the value of the '{@link com.bluexml.side.form.FileField#getInRepository <em>In Repository</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>In Repository</em>' attribute.
	 * @see #getInRepository()
	 * @generated
	 */
	void setInRepository(Boolean value);
} // FileField
