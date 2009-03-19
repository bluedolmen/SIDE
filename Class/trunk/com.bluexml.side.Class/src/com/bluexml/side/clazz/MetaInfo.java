/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package com.bluexml.side.clazz;

import org.eclipse.emf.ecore.EObject;


/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Meta Info</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link com.bluexml.side.clazz.MetaInfo#getKey <em>Key</em>}</li>
 *   <li>{@link com.bluexml.side.clazz.MetaInfo#getValue <em>Value</em>}</li>
 *   <li>{@link com.bluexml.side.clazz.MetaInfo#getValueType <em>Value Type</em>}</li>
 *   <li>{@link com.bluexml.side.clazz.MetaInfo#getConstraintType <em>Constraint Type</em>}</li>
 *   <li>{@link com.bluexml.side.clazz.MetaInfo#getValueSet <em>Value Set</em>}</li>
 * </ul>
 * </p>
 *
 * @see com.bluexml.side.clazz.ClazzPackage#getMetaInfo()
 * @model
 * @generated
 */
public interface MetaInfo extends EObject {
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
	 * @see com.bluexml.side.clazz.ClazzPackage#getMetaInfo_Key()
	 * @model
	 * @generated
	 */
	String getKey();

	/**
	 * Sets the value of the '{@link com.bluexml.side.clazz.MetaInfo#getKey <em>Key</em>}' attribute.
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
	 * @see com.bluexml.side.clazz.ClazzPackage#getMetaInfo_Value()
	 * @model
	 * @generated
	 */
	String getValue();

	/**
	 * Sets the value of the '{@link com.bluexml.side.clazz.MetaInfo#getValue <em>Value</em>}' attribute.
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
	 * @see com.bluexml.side.clazz.ClazzPackage#getMetaInfo_ValueType()
	 * @model
	 * @generated
	 */
	Class<?> getValueType();

	/**
	 * Sets the value of the '{@link com.bluexml.side.clazz.MetaInfo#getValueType <em>Value Type</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Value Type</em>' attribute.
	 * @see #getValueType()
	 * @generated
	 */
	void setValueType(Class<?> value);

	/**
	 * Returns the value of the '<em><b>Constraint Type</b></em>' attribute.
	 * The literals are from the enumeration {@link com.bluexml.side.clazz.AttributeType}.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Constraint Type</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Constraint Type</em>' attribute.
	 * @see com.bluexml.side.clazz.AttributeType
	 * @see #setConstraintType(AttributeType)
	 * @see com.bluexml.side.clazz.ClazzPackage#getMetaInfo_ConstraintType()
	 * @model
	 * @generated
	 */
	AttributeType getConstraintType();

	/**
	 * Sets the value of the '{@link com.bluexml.side.clazz.MetaInfo#getConstraintType <em>Constraint Type</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Constraint Type</em>' attribute.
	 * @see com.bluexml.side.clazz.AttributeType
	 * @see #getConstraintType()
	 * @generated
	 */
	void setConstraintType(AttributeType value);

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
	 * @see com.bluexml.side.clazz.ClazzPackage#getMetaInfo_ValueSet()
	 * @model
	 * @generated
	 */
	Object getValueSet();

	/**
	 * Sets the value of the '{@link com.bluexml.side.clazz.MetaInfo#getValueSet <em>Value Set</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Value Set</em>' attribute.
	 * @see #getValueSet()
	 * @generated
	 */
	void setValueSet(Object value);
	
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
