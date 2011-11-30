/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package com.bluexml.side.common;


/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Meta Info</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link com.bluexml.side.common.MetaInfo#getKey <em>Key</em>}</li>
 *   <li>{@link com.bluexml.side.common.MetaInfo#getValue <em>Value</em>}</li>
 *   <li>{@link com.bluexml.side.common.MetaInfo#getValueType <em>Value Type</em>}</li>
 *   <li>{@link com.bluexml.side.common.MetaInfo#getConstraintType <em>Constraint Type</em>}</li>
 *   <li>{@link com.bluexml.side.common.MetaInfo#getValueSet <em>Value Set</em>}</li>
 *   <li>{@link com.bluexml.side.common.MetaInfo#getMultilineValue <em>Multiline Value</em>}</li>
 * </ul>
 * </p>
 *
 * @see com.bluexml.side.common.CommonPackage#getMetaInfo()
 * @model
 * @generated
 */
public interface MetaInfo extends MetaData {
	/**
	 * Returns the value of the '<em><b>Key</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Key</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Key</em>' attribute.
	 * @see #setKey(String)
	 * @see com.bluexml.side.common.CommonPackage#getMetaInfo_Key()
	 * @model
	 * @generated
	 */
	String getKey();

	/**
	 * Sets the value of the '{@link com.bluexml.side.common.MetaInfo#getKey <em>Key</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Key</em>' attribute.
	 * @see #getKey()
	 * @generated
	 */
	void setKey(String value);

	/**
	 * Returns the value of the '<em><b>Value</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Value</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Value</em>' attribute.
	 * @see #setValue(String)
	 * @see com.bluexml.side.common.CommonPackage#getMetaInfo_Value()
	 * @model
	 * @generated
	 */
	String getValue();

	/**
	 * Sets the value of the '{@link com.bluexml.side.common.MetaInfo#getValue <em>Value</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Value</em>' attribute.
	 * @see #getValue()
	 * @generated
	 */
	void setValue(String value);

	/**
	 * Returns the value of the '<em><b>Value Type</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Value Type</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Value Type</em>' attribute.
	 * @see #setValueType(Class)
	 * @see com.bluexml.side.common.CommonPackage#getMetaInfo_ValueType()
	 * @model
	 * @generated
	 */
	Class<?> getValueType();

	/**
	 * Sets the value of the '{@link com.bluexml.side.common.MetaInfo#getValueType <em>Value Type</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Value Type</em>' attribute.
	 * @see #getValueType()
	 * @generated
	 */
	void setValueType(Class<?> value);

	/**
	 * Returns the value of the '<em><b>Constraint Type</b></em>' attribute.
	 * The literals are from the enumeration {@link com.bluexml.side.common.DataType}.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Constraint Type</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Constraint Type</em>' attribute.
	 * @see com.bluexml.side.common.DataType
	 * @see #setConstraintType(DataType)
	 * @see com.bluexml.side.common.CommonPackage#getMetaInfo_ConstraintType()
	 * @model
	 * @generated
	 */
	DataType getConstraintType();

	/**
	 * Sets the value of the '{@link com.bluexml.side.common.MetaInfo#getConstraintType <em>Constraint Type</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Constraint Type</em>' attribute.
	 * @see com.bluexml.side.common.DataType
	 * @see #getConstraintType()
	 * @generated
	 */
	void setConstraintType(DataType value);

	/**
	 * Returns the value of the '<em><b>Value Set</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Value Set</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Value Set</em>' attribute.
	 * @see #setValueSet(Object)
	 * @see com.bluexml.side.common.CommonPackage#getMetaInfo_ValueSet()
	 * @model
	 * @generated
	 */
	Object getValueSet();

	/**
	 * Sets the value of the '{@link com.bluexml.side.common.MetaInfo#getValueSet <em>Value Set</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Value Set</em>' attribute.
	 * @see #getValueSet()
	 * @generated
	 */
	void setValueSet(Object value);

	/**
	 * Returns the value of the '<em><b>Multiline Value</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Multiline Value</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Multiline Value</em>' attribute.
	 * @see #setMultilineValue(String)
	 * @see com.bluexml.side.common.CommonPackage#getMetaInfo_MultilineValue()
	 * @model
	 * @generated
	 */
	String getMultilineValue();

	/**
	 * Sets the value of the '{@link com.bluexml.side.common.MetaInfo#getMultilineValue <em>Multiline Value</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Multiline Value</em>' attribute.
	 * @see #getMultilineValue()
	 * @generated
	 */
	void setMultilineValue(String value);

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @model annotation="http://www.bluexml.com/OCL body='self.key = other.key'"
	 * @generated
	 */
	boolean equalsForMerge(MetaInfo other);
	
	/**
	 * Clone the meta-information
	 * 
	 * @param mi The meta-information to clone
	 * @_generated
	 */
	void clone(MetaInfo mi);
	
	void setDefaultValueBoolean(boolean b);
	
	boolean getDefaultValueBoolean();
} // MetaInfo
