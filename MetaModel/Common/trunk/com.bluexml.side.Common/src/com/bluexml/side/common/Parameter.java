/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package com.bluexml.side.common;


/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Parameter</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link com.bluexml.side.common.Parameter#getValueType <em>Value Type</em>}</li>
 * </ul>
 * </p>
 *
 * @see com.bluexml.side.common.CommonPackage#getParameter()
 * @model
 * @generated
 */
public interface Parameter extends NamedModelElement {
	/**
	 * Returns the value of the '<em><b>Value Type</b></em>' attribute.
	 * The literals are from the enumeration {@link com.bluexml.side.common.DataType}.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Value Type</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Value Type</em>' attribute.
	 * @see com.bluexml.side.common.DataType
	 * @see #setValueType(DataType)
	 * @see com.bluexml.side.common.CommonPackage#getParameter_ValueType()
	 * @model
	 * @generated
	 */
	DataType getValueType();

	/**
	 * Sets the value of the '{@link com.bluexml.side.common.Parameter#getValueType <em>Value Type</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Value Type</em>' attribute.
	 * @see com.bluexml.side.common.DataType
	 * @see #getValueType()
	 * @generated
	 */
	void setValueType(DataType value);
		
} // Parameter
