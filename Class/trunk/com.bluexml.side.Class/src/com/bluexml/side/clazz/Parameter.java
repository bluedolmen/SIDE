/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package com.bluexml.side.clazz;


/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Parameter</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link com.bluexml.side.clazz.Parameter#getValueType <em>Value Type</em>}</li>
 * </ul>
 * </p>
 *
 * @see com.bluexml.side.clazz.ClazzPackage#getParameter()
 * @model
 * @generated
 */
public interface Parameter extends NamedClassModelElement {
	/**
	 * Returns the value of the '<em><b>Value Type</b></em>' attribute.
	 * The literals are from the enumeration {@link com.bluexml.side.clazz.AttributeType}.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Value Type</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Value Type</em>' attribute.
	 * @see com.bluexml.side.clazz.AttributeType
	 * @see #setValueType(AttributeType)
	 * @see com.bluexml.side.clazz.ClazzPackage#getParameter_ValueType()
	 * @model
	 * @generated
	 */
	AttributeType getValueType();

	/**
	 * Sets the value of the '{@link com.bluexml.side.clazz.Parameter#getValueType <em>Value Type</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Value Type</em>' attribute.
	 * @see com.bluexml.side.clazz.AttributeType
	 * @see #getValueType()
	 * @generated
	 */
	void setValueType(AttributeType value);
	
	/**
	 * Sets the value of the '{@link org.topcased.MMUseCase.Parameter#getValueType <em>Value Type</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Value Type</em>' attribute.
	 * @see org.topcased.MMUseCase.AttributeType
	 * @see #getValueType()
	 * @_generated
	 */
	void setValueType(String value);
} // Parameter
