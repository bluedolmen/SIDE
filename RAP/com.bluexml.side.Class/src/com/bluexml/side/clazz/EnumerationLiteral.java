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
 * A representation of the model object '<em><b>Enumeration Literal</b></em>'.
 * <!-- end-user-doc -->
 *
 * <!-- begin-model-doc -->
 * Defintion: the item of an enumeration; it is composed of a name and a value.
 * <!-- end-model-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link com.bluexml.side.clazz.EnumerationLiteral#getValue <em>Value</em>}</li>
 *   <li>{@link com.bluexml.side.clazz.EnumerationLiteral#getName <em>Name</em>}</li>
 *   <li>{@link com.bluexml.side.clazz.EnumerationLiteral#getEnum <em>Enum</em>}</li>
 * </ul>
 * </p>
 *
 * @see com.bluexml.side.clazz.ClazzPackage#getEnumerationLiteral()
 * @model annotation="http://www.bluexml.com/OCL NameNull='not self.name.oclIsUndefined() and self.name <> \'\'' noSpecialCharacters='self.name.regexMatch(\'[\\w]*\') = true'"
 *        annotation="http://www.eclipse.org/emf/2002/Ecore constraints='NameNull'"
 * @generated
 */
public interface EnumerationLiteral extends EObject {
	/**
	 * Returns the value of the '<em><b>Value</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Value</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * Definition: the value of an item of an enumeration.
	 * Constraints: a single value may be defined for a name.
	 * Example: consider the enumeration mediaType : {(videoType, Video), (paperType, Paper), (audioType, Audio),(numericType, File), ...}; a Video media has the mediaType of name videoType and of value Video.
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>Value</em>' attribute.
	 * @see #setValue(String)
	 * @see com.bluexml.side.clazz.ClazzPackage#getEnumerationLiteral_Value()
	 * @model
	 * @generated
	 */
	String getValue();

	/**
	 * Sets the value of the '{@link com.bluexml.side.clazz.EnumerationLiteral#getValue <em>Value</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Value</em>' attribute.
	 * @see #getValue()
	 * @generated
	 */
	void setValue(String value);

	/**
	 * Returns the value of the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Name</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * Definition: the identifier of an item of an enumeration.
	 * Constraint: the name is unique in an enumeration
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>Name</em>' attribute.
	 * @see #setName(String)
	 * @see com.bluexml.side.clazz.ClazzPackage#getEnumerationLiteral_Name()
	 * @model
	 * @generated
	 */
	String getName();

	/**
	 * Sets the value of the '{@link com.bluexml.side.clazz.EnumerationLiteral#getName <em>Name</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Name</em>' attribute.
	 * @see #getName()
	 * @generated
	 */
	void setName(String value);

	/**
	 * Returns the value of the '<em><b>Enum</b></em>' container reference.
	 * It is bidirectional and its opposite is '{@link com.bluexml.side.clazz.Enumeration#getLiterals <em>Literals</em>}'.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Enum</em>' container reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Enum</em>' container reference.
	 * @see #setEnum(Enumeration)
	 * @see com.bluexml.side.clazz.ClazzPackage#getEnumerationLiteral_Enum()
	 * @see com.bluexml.side.clazz.Enumeration#getLiterals
	 * @model opposite="literals" transient="false"
	 * @generated
	 */
	Enumeration getEnum();

	/**
	 * Sets the value of the '{@link com.bluexml.side.clazz.EnumerationLiteral#getEnum <em>Enum</em>}' container reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Enum</em>' container reference.
	 * @see #getEnum()
	 * @generated
	 */
	void setEnum(Enumeration value);
		
} // EnumerationLiteral
