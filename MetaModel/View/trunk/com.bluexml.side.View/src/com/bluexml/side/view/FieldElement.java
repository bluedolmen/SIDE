/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package com.bluexml.side.view;

import com.bluexml.side.common.ModelElement;
import com.bluexml.side.common.NamedModelElement;
import org.eclipse.emf.common.util.EList;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Field Element</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link com.bluexml.side.view.FieldElement#getMapTo <em>Map To</em>}</li>
 *   <li>{@link com.bluexml.side.view.FieldElement#getPrefix <em>Prefix</em>}</li>
 *   <li>{@link com.bluexml.side.view.FieldElement#getSuffix <em>Suffix</em>}</li>
 *   <li>{@link com.bluexml.side.view.FieldElement#isHidden <em>Hidden</em>}</li>
 * </ul>
 * </p>
 *
 * @see com.bluexml.side.view.ViewPackage#getFieldElement()
 * @model abstract="true"
 *        annotation="http://www.bluexml.com/OCL noSpecialCharacters='self.name.regexMatch(\'[\\w]*\') = true'"
 *        annotation="http://www.eclipse.org/emf/2002/Ecore constraints='noSpecialCharacters'"
 * @generated
 */
public interface FieldElement extends Stylable, NamedModelElement {
	/**
	 * Returns the value of the '<em><b>Map To</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * this may be use to show of the path use to reach the data to display exemple for attribute Object : ClassA ->association1 ->ClassB ->AspectA ->Attribute1
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>Map To</em>' reference.
	 * @see #setMapTo(ModelElement)
	 * @see com.bluexml.side.view.ViewPackage#getFieldElement_MapTo()
	 * @model
	 * @generated
	 */
	ModelElement getMapTo();

	/**
	 * Sets the value of the '{@link com.bluexml.side.view.FieldElement#getMapTo <em>Map To</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Map To</em>' reference.
	 * @see #getMapTo()
	 * @generated
	 */
	void setMapTo(ModelElement value);

	/**
	 * Returns the value of the '<em><b>Prefix</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Prefix</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Prefix</em>' attribute.
	 * @see #setPrefix(String)
	 * @see com.bluexml.side.view.ViewPackage#getFieldElement_Prefix()
	 * @model
	 * @generated
	 */
	String getPrefix();

	/**
	 * Sets the value of the '{@link com.bluexml.side.view.FieldElement#getPrefix <em>Prefix</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Prefix</em>' attribute.
	 * @see #getPrefix()
	 * @generated
	 */
	void setPrefix(String value);

	/**
	 * Returns the value of the '<em><b>Suffix</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Suffix</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Suffix</em>' attribute.
	 * @see #setSuffix(String)
	 * @see com.bluexml.side.view.ViewPackage#getFieldElement_Suffix()
	 * @model
	 * @generated
	 */
	String getSuffix();

	/**
	 * Sets the value of the '{@link com.bluexml.side.view.FieldElement#getSuffix <em>Suffix</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Suffix</em>' attribute.
	 * @see #getSuffix()
	 * @generated
	 */
	void setSuffix(String value);

	/**
	 * Returns the value of the '<em><b>Hidden</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Hidden</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Hidden</em>' attribute.
	 * @see #setHidden(boolean)
	 * @see com.bluexml.side.view.ViewPackage#getFieldElement_Hidden()
	 * @model
	 * @generated
	 */
	boolean isHidden();

	/**
	 * Sets the value of the '{@link com.bluexml.side.view.FieldElement#isHidden <em>Hidden</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Hidden</em>' attribute.
	 * @see #isHidden()
	 * @generated
	 */
	void setHidden(boolean value);

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @model kind="operation"
	 *        annotation="http://www.bluexml.com/OCL description='return the root model element (viewCollection)' body='ViewCollection.allInstances()'"
	 * @generated
	 */
	EList<ViewCollection> getViewCollection();
		
} // FieldElement
