/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package com.bluexml.side.clazz;

import com.bluexml.side.common.DataType;
import com.bluexml.side.common.Visibility;
import org.eclipse.emf.common.util.EList;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Attribute</b></em>'.
 * <!-- end-user-doc -->
 *
 * <!-- begin-model-doc -->
 * Definition: a class property descibes a characteristic of the concerned class. For example, the property 'title' of the class 'book' gives the title of the book.
 * Constraints: 
 * - the name of a class property must be unique between all the direct and inherited property of a class.
 * - a property is associated to a single class or abstract class; a class or an abstract class may contain zero, one or several properties.
 * 
 * <!-- end-model-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link com.bluexml.side.clazz.Attribute#getTyp <em>Typ</em>}</li>
 *   <li>{@link com.bluexml.side.clazz.Attribute#getInitialValue <em>Initial Value</em>}</li>
 *   <li>{@link com.bluexml.side.clazz.Attribute#getVisibility <em>Visibility</em>}</li>
 *   <li>{@link com.bluexml.side.clazz.Attribute#getValueList <em>Value List</em>}</li>
 *   <li>{@link com.bluexml.side.clazz.Attribute#isUnique <em>Unique</em>}</li>
 *   <li>{@link com.bluexml.side.clazz.Attribute#getMockup <em>Mockup</em>}</li>
 * </ul>
 * </p>
 *
 * @see com.bluexml.side.clazz.ClazzPackage#getAttribute()
 * @model annotation="http://www.bluexml.com/OCL NameNull='not self.name.oclIsUndefined() and self.name <> \'\'' noSpecialCharacters='self.name.regexMatch(\'[\\w]*\') = true'"
 *        annotation="http://www.eclipse.org/emf/2002/Ecore constraints='NameNull noSpecialCharacters '"
 * @generated
 */
public interface Attribute extends TitledNamedClassModelElement {
	/**
	 * Returns the value of the '<em><b>Typ</b></em>' attribute.
	 * The default value is <code>"String"</code>.
	 * The literals are from the enumeration {@link com.bluexml.side.common.DataType}.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Typ</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * Definition: the type of a class property allows to define its format; the supported property types are boolean, byte, char, double, float, int, long, short, String, Date, Object.
	 * 
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>Typ</em>' attribute.
	 * @see com.bluexml.side.common.DataType
	 * @see #setTyp(DataType)
	 * @see com.bluexml.side.clazz.ClazzPackage#getAttribute_Typ()
	 * @model default="String"
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
	 * <!-- begin-model-doc -->
	 * Definition: this attribute allows to set up an initial value to the class property at creation of an object.
	 * <!-- end-model-doc -->
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
	 * <!-- begin-model-doc -->
	 * Definition: this attribute indicates the scope of the class property the possible values are:
	 * - public: the class property is visible to other classes through association.
	 * - private: the class property is not visible to other classes.
	 * - protected: the class property cannot be updated.
	 * <!-- end-model-doc -->
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
	 * <!-- begin-model-doc -->
	 * Definition: if true, this attribute inidcates that the class property is part of the unique constraint of the class. This means that the set of values of all the unique property must be unique through all the instances of the class.
	 * <!-- end-model-doc -->
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

	/**
	 * Returns the value of the '<em><b>Mockup</b></em>' attribute list.
	 * The list contents are of type {@link java.lang.String}.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Mockup</em>' attribute list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Mockup</em>' attribute list.
	 * @see com.bluexml.side.clazz.ClazzPackage#getAttribute_Mockup()
	 * @model
	 * @generated
	 */
	EList<String> getMockup();
		
} // Attribute
