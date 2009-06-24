/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package com.bluexml.side.form;

import com.bluexml.side.common.ModelElement;
import org.eclipse.emf.ecore.EObject;

import com.bluexml.side.clazz.ClassModelElement;


/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Form Element</b></em>'.
 * <!-- end-user-doc -->
 *
 * <!-- begin-model-doc -->
 * Definition: The FormElement is an element of the form. It can be a form group or a field.
 * <!-- end-model-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link com.bluexml.side.form.FormElement#getLabel <em>Label</em>}</li>
 *   <li>{@link com.bluexml.side.form.FormElement#getId <em>Id</em>}</li>
 *   <li>{@link com.bluexml.side.form.FormElement#getHelp_text <em>Help text</em>}</li>
 *   <li>{@link com.bluexml.side.form.FormElement#getRef <em>Ref</em>}</li>
 *   <li>{@link com.bluexml.side.form.FormElement#isHidden <em>Hidden</em>}</li>
 * </ul>
 * </p>
 *
 * @see com.bluexml.side.form.FormPackage#getFormElement()
 * @model abstract="true"
 *        annotation="http://www.eclipse.org/emf/2002/Ecore constraints='noSpecialCharacters'"
 *        annotation="http://www.bluexml.com/OCL noSpecialCharacters='self.id.regexMatch(\'\\w*\') = true'"
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
	 * <!-- begin-model-doc -->
	 * Definition: The 'label' attribute specifies the text associated to a form element.
	 * 
	 * Example: label=Description
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>Label</em>' attribute.
	 * @see #setLabel(String)
	 * @see com.bluexml.side.form.FormPackage#getFormElement_Label()
	 * @model
	 * @generated
	 */
	String getLabel();

	/**
	 * Sets the value of the '{@link com.bluexml.side.form.FormElement#getLabel <em>Label</em>}' attribute.
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
	 * <!-- begin-model-doc -->
	 * Definition: The 'id' attribute specifies a unique identifier to a form element.
	 * 
	 * Constraint/limit: The 'id' value must not contain space.
	 * 
	 * Example: id=Description
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>Id</em>' attribute.
	 * @see #setId(String)
	 * @see com.bluexml.side.form.FormPackage#getFormElement_Id()
	 * @model required="true"
	 * @generated
	 */
	String getId();

	/**
	 * Sets the value of the '{@link com.bluexml.side.form.FormElement#getId <em>Id</em>}' attribute.
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
	 * <!-- begin-model-doc -->
	 * Definition: The 'help_text' attribute specifies a text that will appear when the cursor moves over the form element.
	 * 
	 * Example: help_text=This is a help message
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>Help text</em>' attribute.
	 * @see #setHelp_text(String)
	 * @see com.bluexml.side.form.FormPackage#getFormElement_Help_text()
	 * @model
	 * @generated
	 */
	String getHelp_text();

	/**
	 * Sets the value of the '{@link com.bluexml.side.form.FormElement#getHelp_text <em>Help text</em>}' attribute.
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
	 * @see #setRef(ModelElement)
	 * @see com.bluexml.side.form.FormPackage#getFormElement_Ref()
	 * @model
	 * @generated
	 */
	ModelElement getRef();

	/**
	 * Sets the value of the '{@link com.bluexml.side.form.FormElement#getRef <em>Ref</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Ref</em>' reference.
	 * @see #getRef()
	 * @generated
	 */
	void setRef(ModelElement value);

	/**
	 * Returns the value of the '<em><b>Hidden</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Hidden</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * Definition: The 'hidden' attribute specifies if a form element will be hidden or not in the form.
	 * 
	 * Example:
	 * - 'false': the form element will not be hidden.
	 * - 'true': the form element will be hidden.
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>Hidden</em>' attribute.
	 * @see #setHidden(boolean)
	 * @see com.bluexml.side.form.FormPackage#getFormElement_Hidden()
	 * @model
	 * @generated
	 */
	boolean isHidden();

	/**
	 * Sets the value of the '{@link com.bluexml.side.form.FormElement#isHidden <em>Hidden</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Hidden</em>' attribute.
	 * @see #isHidden()
	 * @generated
	 */
	void setHidden(boolean value);

} // FormElement
