/*
    Copyright (C) 2007-20013  BlueXML - www.bluexml.com

    This program is free software: you can redistribute it and/or modify
    it under the terms of the GNU Affero General Public License as
    published by the Free Software Foundation, either version 3 of the
    License, or (at your option) any later version.

    This program is distributed in the hope that it will be useful,
    but WITHOUT ANY WARRANTY; without even the implied warranty of
    MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
    GNU Affero General Public License for more details.

    You should have received a copy of the GNU Affero General Public License
    along with this program.  If not, see <http://www.gnu.org/licenses/>.
*/
/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package com.bluexml.side.common;

import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EObject;


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
 *   <li>{@link com.bluexml.side.common.MetaInfo#getEObjectValue <em>EObject Value</em>}</li>
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
	 * Returns the value of the '<em><b>EObject Value</b></em>' reference list.
	 * The list contents are of type {@link org.eclipse.emf.ecore.EObject}.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>EObject Value</em>' reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>EObject Value</em>' reference list.
	 * @see com.bluexml.side.common.CommonPackage#getMetaInfo_EObjectValue()
	 * @model
	 * @generated
	 */
	EList<EObject> getEObjectValue();

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
