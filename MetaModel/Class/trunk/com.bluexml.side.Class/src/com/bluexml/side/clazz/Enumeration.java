/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package com.bluexml.side.clazz;

import com.bluexml.side.common.NamedModelElement;

import org.eclipse.emf.common.util.EList;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Enumeration</b></em>'.
 * <!-- end-user-doc -->
 *
 * <!-- begin-model-doc -->
 * Definition: an enumeration represents a list of values which may be associated to a class property.
 * Example: the list of languages associated to the property 'language' of a book.
 * Deprecated: the link'depends of' was used in previous release of SIDE to model a relation between enumeration like between a department list and a country list; this link has been kept for compatibility reason but is no more useful at the data level and is now managed between fields of Form model as a management rule.
 * <!-- end-model-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link com.bluexml.side.clazz.Enumeration#getLiterals <em>Literals</em>}</li>
 *   <li>{@link com.bluexml.side.clazz.Enumeration#getDynamic <em>Dynamic</em>}</li>
 *   <li>{@link com.bluexml.side.clazz.Enumeration#getDepends <em>Depends</em>}</li>
 * </ul>
 * </p>
 *
 * @see com.bluexml.side.clazz.ClazzPackage#getEnumeration()
 * @model annotation="http://www.bluexml.com/OCL enumDynamicAreNotAvailable='dynamic=false'"
 *        annotation="http://www.eclipse.org/emf/2002/Ecore warning='enumDynamicAreNotAvailable' constraints='enumDynamicAreNotAvailable'"
 * @generated
 */
public interface Enumeration extends NamedModelElement {
	/**
	 * Returns the value of the '<em><b>Literals</b></em>' containment reference list.
	 * The list contents are of type {@link com.bluexml.side.clazz.EnumerationLiteral}.
	 * It is bidirectional and its opposite is '{@link com.bluexml.side.clazz.EnumerationLiteral#getEnum <em>Enum</em>}'.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Literals</em>' containment reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Literals</em>' containment reference list.
	 * @see com.bluexml.side.clazz.ClazzPackage#getEnumeration_Literals()
	 * @see com.bluexml.side.clazz.EnumerationLiteral#getEnum
	 * @model opposite="enum" containment="true"
	 * @generated
	 */
	EList<EnumerationLiteral> getLiterals();

	/**
	 * Returns the value of the '<em><b>Dynamic</b></em>' attribute.
	 * The default value is <code>"false"</code>.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Dynamic</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * Definition: if false, this attribute indicates that the values of the enumeration are predefined and fixed: they are defined during modeling. If true, this attribute indicates that the set of values of the enumeration may be extended at application runtime.
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>Dynamic</em>' attribute.
	 * @see #setDynamic(Boolean)
	 * @see com.bluexml.side.clazz.ClazzPackage#getEnumeration_Dynamic()
	 * @model default="false"
	 * @generated
	 */
	Boolean getDynamic();

	/**
	 * Sets the value of the '{@link com.bluexml.side.clazz.Enumeration#getDynamic <em>Dynamic</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Dynamic</em>' attribute.
	 * @see #getDynamic()
	 * @generated
	 */
	void setDynamic(Boolean value);

	/**
	 * Returns the value of the '<em><b>Depends</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Depends</em>' reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Depends</em>' reference.
	 * @see #setDepends(Enumeration)
	 * @see com.bluexml.side.clazz.ClazzPackage#getEnumeration_Depends()
	 * @model
	 * @generated
	 */
	Enumeration getDepends();

	/**
	 * Sets the value of the '{@link com.bluexml.side.clazz.Enumeration#getDepends <em>Depends</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Depends</em>' reference.
	 * @see #getDepends()
	 * @generated
	 */
	void setDepends(Enumeration value);
		
} // Enumeration
