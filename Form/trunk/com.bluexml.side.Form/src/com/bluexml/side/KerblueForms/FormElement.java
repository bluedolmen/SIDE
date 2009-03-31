/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package com.bluexml.side.KerblueForms;

import com.bluexml.side.clazz.ClassModelElement;

import org.eclipse.emf.ecore.EObject;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Form Element</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link com.bluexml.side.KerblueForms.FormElement#getLabel <em>Label</em>}</li>
 *   <li>{@link com.bluexml.side.KerblueForms.FormElement#getId <em>Id</em>}</li>
 *   <li>{@link com.bluexml.side.KerblueForms.FormElement#getHelp_text <em>Help text</em>}</li>
 *   <li>{@link com.bluexml.side.KerblueForms.FormElement#getRef <em>Ref</em>}</li>
 * </ul>
 * </p>
 *
 * @see com.bluexml.side.KerblueForms.formPackage#getFormElement()
 * @model abstract="true"
 *        annotation="http://www.eclipse.org/emf/2002/Ecore constraints='noSpecialCharacters'"
 *        annotation="http://www.bluexml.com/OCL noSpecialCharacters='self.id.regexMatch(\'\\w\') <> null'"
 * @generated
 */
public interface FormElement extends EObject {
	/**
	 * Returns the value of the '<em><b>Label</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Label</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Label</em>' attribute.
	 * @see #setLabel(String)
	 * @see com.bluexml.side.KerblueForms.formPackage#getFormElement_Label()
	 * @model
	 * @generated
	 */
	String getLabel();

	/**
	 * Sets the value of the '{@link com.bluexml.side.KerblueForms.FormElement#getLabel <em>Label</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Label</em>' attribute.
	 * @see #getLabel()
	 * @generated
	 */
	void setLabel(String value);

	/**
	 * Returns the value of the '<em><b>Id</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Id</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Id</em>' attribute.
	 * @see #setId(String)
	 * @see com.bluexml.side.KerblueForms.formPackage#getFormElement_Id()
	 * @model required="true"
	 * @generated
	 */
	String getId();

	/**
	 * Sets the value of the '{@link com.bluexml.side.KerblueForms.FormElement#getId <em>Id</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Id</em>' attribute.
	 * @see #getId()
	 * @generated
	 */
	void setId(String value);

	/**
	 * Returns the value of the '<em><b>Help text</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Help text</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Help text</em>' attribute.
	 * @see #setHelp_text(String)
	 * @see com.bluexml.side.KerblueForms.formPackage#getFormElement_Help_text()
	 * @model
	 * @generated
	 */
	String getHelp_text();

	/**
	 * Sets the value of the '{@link com.bluexml.side.KerblueForms.FormElement#getHelp_text <em>Help text</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Help text</em>' attribute.
	 * @see #getHelp_text()
	 * @generated
	 */
	void setHelp_text(String value);

	/**
	 * Returns the value of the '<em><b>Ref</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Ref</em>' reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Ref</em>' reference.
	 * @see #setRef(ClassModelElement)
	 * @see com.bluexml.side.KerblueForms.formPackage#getFormElement_Ref()
	 * @model
	 * @generated
	 */
	ClassModelElement getRef();

	/**
	 * Sets the value of the '{@link com.bluexml.side.KerblueForms.FormElement#getRef <em>Ref</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Ref</em>' reference.
	 * @see #getRef()
	 * @generated
	 */
	void setRef(ClassModelElement value);
		
} // FormElement
