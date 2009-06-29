/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package com.bluexml.side.clazz;

import com.bluexml.side.common.DataType;
import com.bluexml.side.common.Visibility;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Attribute</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link com.bluexml.side.clazz.Attribute#getTyp <em>Typ</em>}</li>
 *   <li>{@link com.bluexml.side.clazz.Attribute#getInitialValue <em>Initial Value</em>}</li>
 *   <li>{@link com.bluexml.side.clazz.Attribute#getVisibility <em>Visibility</em>}</li>
 *   <li>{@link com.bluexml.side.clazz.Attribute#getValueList <em>Value List</em>}</li>
 *   <li>{@link com.bluexml.side.clazz.Attribute#isUnique <em>Unique</em>}</li>
 * </ul>
 * </p>
 *
 * @see com.bluexml.side.clazz.ClazzPackage#getAttribute()
 * @model
 * @generated
 */
public interface Attribute extends TitledNamedClassModelElement {
	/**
	 * Returns the value of the '<em><b>Typ</b></em>' attribute.
	 * The default value is <code>"void"</code>.
	 * The literals are from the enumeration {@link com.bluexml.side.common.DataType}.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Typ</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Typ</em>' attribute.
	 * @see com.bluexml.side.common.DataType
	 * @see #setTyp(DataType)
	 * @see com.bluexml.side.clazz.ClazzPackage#getAttribute_Typ()
	 * @model default="void"
	 * @generated
	 */
	DataType getTyp();

	/**
	 * Sets the value of the '{@link com.bluexml.side.clazz.Attribute#getTyp <em>Typ</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Typ</em>' attribute.
	 * @see com.bluexml.side.common.DataType
	 * @see #getTyp()
	 * @generated
	 */
	void setTyp(DataType value);

	/**
	 * Returns the value of the '<em><b>Initial Value</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Initial Value</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Initial Value</em>' attribute.
	 * @see #setInitialValue(String)
	 * @see com.bluexml.side.clazz.ClazzPackage#getAttribute_InitialValue()
	 * @model
	 * @generated
	 */
	String getInitialValue();

	/**
	 * Sets the value of the '{@link com.bluexml.side.clazz.Attribute#getInitialValue <em>Initial Value</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Initial Value</em>' attribute.
	 * @see #getInitialValue()
	 * @generated
	 */
	void setInitialValue(String value);

	/**
	 * Returns the value of the '<em><b>Visibility</b></em>' attribute.
	 * The default value is <code>"Private"</code>.
	 * The literals are from the enumeration {@link com.bluexml.side.common.Visibility}.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Visibility</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Visibility</em>' attribute.
	 * @see com.bluexml.side.common.Visibility
	 * @see #setVisibility(Visibility)
	 * @see com.bluexml.side.clazz.ClazzPackage#getAttribute_Visibility()
	 * @model default="Private"
	 * @generated
	 */
	Visibility getVisibility();

	/**
	 * Sets the value of the '{@link com.bluexml.side.clazz.Attribute#getVisibility <em>Visibility</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Visibility</em>' attribute.
	 * @see com.bluexml.side.common.Visibility
	 * @see #getVisibility()
	 * @generated
	 */
	void setVisibility(Visibility value);

	/**
	 * Returns the value of the '<em><b>Value List</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Value List</em>' reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Value List</em>' reference.
	 * @see #setValueList(Enumeration)
	 * @see com.bluexml.side.clazz.ClazzPackage#getAttribute_ValueList()
	 * @model
	 * @generated
	 */
	Enumeration getValueList();

	/**
	 * Sets the value of the '{@link com.bluexml.side.clazz.Attribute#getValueList <em>Value List</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Value List</em>' reference.
	 * @see #getValueList()
	 * @generated
	 */
	void setValueList(Enumeration value);

	/**
	 * Returns the value of the '<em><b>Unique</b></em>' attribute.
	 * The default value is <code>"false"</code>.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Unique</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Unique</em>' attribute.
	 * @see #setUnique(boolean)
	 * @see com.bluexml.side.clazz.ClazzPackage#getAttribute_Unique()
	 * @model default="false"
	 * @generated
	 */
	boolean isUnique();

	/**
	 * Sets the value of the '{@link com.bluexml.side.clazz.Attribute#isUnique <em>Unique</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Unique</em>' attribute.
	 * @see #isUnique()
	 * @generated
	 */
	void setUnique(boolean value);
		
} // Attribute
