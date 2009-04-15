/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package com.bluexml.side.form;

import java.util.Map;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Field</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link com.bluexml.side.form.Field#isMandatory <em>Mandatory</em>}</li>
 *   <li>{@link com.bluexml.side.form.Field#getError_messages <em>Error messages</em>}</li>
 *   <li>{@link com.bluexml.side.form.Field#getInitial <em>Initial</em>}</li>
 *   <li>{@link com.bluexml.side.form.Field#isDisabled <em>Disabled</em>}</li>
 *   <li>{@link com.bluexml.side.form.Field#getFieldSize <em>Field Size</em>}</li>
 * </ul>
 * </p>
 *
 * @see com.bluexml.side.form.FormPackage#getField()
 * @model abstract="true"
 * @generated
 */
public interface Field extends FormElement {
	/**
	 * Returns the value of the '<em><b>Mandatory</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Mandatory</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Mandatory</em>' attribute.
	 * @see #setMandatory(boolean)
	 * @see com.bluexml.side.form.FormPackage#getField_Mandatory()
	 * @model
	 * @generated
	 */
	boolean isMandatory();

	/**
	 * Sets the value of the '{@link com.bluexml.side.form.Field#isMandatory <em>Mandatory</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Mandatory</em>' attribute.
	 * @see #isMandatory()
	 * @generated
	 */
	void setMandatory(boolean value);

	/**
	 * Returns the value of the '<em><b>Error messages</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Error messages</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Error messages</em>' attribute.
	 * @see #setError_messages(Map)
	 * @see com.bluexml.side.form.FormPackage#getField_Error_messages()
	 * @model transient="true"
	 * @generated
	 */
	Map<String, String> getError_messages();

	/**
	 * Sets the value of the '{@link com.bluexml.side.form.Field#getError_messages <em>Error messages</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Error messages</em>' attribute.
	 * @see #getError_messages()
	 * @generated
	 */
	void setError_messages(Map<String, String> value);

	/**
	 * Returns the value of the '<em><b>Initial</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Initial</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Initial</em>' attribute.
	 * @see #setInitial(String)
	 * @see com.bluexml.side.form.FormPackage#getField_Initial()
	 * @model
	 * @generated
	 */
	String getInitial();

	/**
	 * Sets the value of the '{@link com.bluexml.side.form.Field#getInitial <em>Initial</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Initial</em>' attribute.
	 * @see #getInitial()
	 * @generated
	 */
	void setInitial(String value);

	/**
	 * Returns the value of the '<em><b>Disabled</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Disabled</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Disabled</em>' attribute.
	 * @see #setDisabled(boolean)
	 * @see com.bluexml.side.form.FormPackage#getField_Disabled()
	 * @model
	 * @generated
	 */
	boolean isDisabled();

	/**
	 * Sets the value of the '{@link com.bluexml.side.form.Field#isDisabled <em>Disabled</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Disabled</em>' attribute.
	 * @see #isDisabled()
	 * @generated
	 */
	void setDisabled(boolean value);

	/**
	 * Returns the value of the '<em><b>Field Size</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Field Size</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Field Size</em>' attribute.
	 * @see #setFieldSize(Integer)
	 * @see com.bluexml.side.form.FormPackage#getField_FieldSize()
	 * @model
	 * @generated
	 */
	Integer getFieldSize();

	/**
	 * Sets the value of the '{@link com.bluexml.side.form.Field#getFieldSize <em>Field Size</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Field Size</em>' attribute.
	 * @see #getFieldSize()
	 * @generated
	 */
	void setFieldSize(Integer value);

} // Field
