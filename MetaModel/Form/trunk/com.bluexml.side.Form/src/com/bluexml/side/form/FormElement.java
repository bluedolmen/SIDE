/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package com.bluexml.side.form;

import com.bluexml.side.common.ModelElement;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EObject;

import com.bluexml.side.clazz.ClassModelElement;


/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Form Element</b></em>'.
 * <!-- end-user-doc -->
 *
 * <!-- begin-model-doc -->
 * Definition: The FormElement is an element of the form. It can be either a form group or a field. it is an abstract element and so does not directly appear in the Form modeler.
 * <!-- end-model-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link com.bluexml.side.form.FormElement#getLabel <em>Label</em>}</li>
 *   <li>{@link com.bluexml.side.form.FormElement#getId <em>Id</em>}</li>
 *   <li>{@link com.bluexml.side.form.FormElement#isHidden <em>Hidden</em>}</li>
 *   <li>{@link com.bluexml.side.form.FormElement#getHelp_text <em>Help text</em>}</li>
 *   <li>{@link com.bluexml.side.form.FormElement#getRef <em>Ref</em>}</li>
 *   <li>{@link com.bluexml.side.form.FormElement#getStyle <em>Style</em>}</li>
 *   <li>{@link com.bluexml.side.form.FormElement#getXtension <em>Xtension</em>}</li>
 * </ul>
 * </p>
 *
 * @see com.bluexml.side.form.FormPackage#getFormElement()
 * @model abstract="true"
 *        annotation="http://www.eclipse.org/emf/2002/Ecore constraints='noSpecialCharacters validRef'"
 *        annotation="http://www.bluexml.com/OCL noSpecialCharacters='self.id.regexMatch(\'[\\w]*\') = true' validRef='if (not(self.ref.oclIsUndefined()) and self.ref.oclIsKindOf(clazz::Attribute) and self.getContainer().oclIsKindOf(FormClass)) then\r\tself.getContainer().oclAsType(FormClass).real_class.getAllAttributes()->includes(self.ref.oclAsType(clazz::Attribute))\relse\rtrue\rendif'"
 * @generated
 */
public interface FormElement extends ModelElement {
	/**
	 * Returns the value of the '<em><b>Label</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Label</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * Definition: The 'label' attribute specifies the text associated to a form element. This text is displayed on or near the form element. However, if the form element is a static text, the label provides the text to be displayed.
	 * Constraint/Limit: The internationalization convention may be used for the label's value; if the value of the label in the form model is of the form "#label",  the token "label" is then considered as a “delayed message”, whose value will be provided by the forms server at runtime from a language resource file. In the case of Xform Chiba for Alfresco, the language resource file is '<xforms_webapp>/WEB-INF/classes/messages.properties'.
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
	 * Definition: The 'id' attribute specifies a unique identifier of the form element in the form.
	 * Constraint/limit: The 'id' value must not contain space, is mandatory and must be unique in the FormClass or FormWorkflow.
	 * 
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
	 * The default value is <code>""</code>.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Help text</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * Definition: The 'help_text' attribute specifies a text that will appear when the cursor moves over the form element.
	 * 
	 * Constraint/Limit: The internationalization convention may be used for the help_text's value; if the value of the help_text in the form model is of the form "#label",  the token "label" is then considered as a “delayed message”, whose value will be provided by the forms server at runtime from a language resource file. In the case of Xform Chiba for Alfresco, the language resource file is '<xforms_webapp>/WEB-INF/classes/messages.properties'.
	 * 
	 * Example: help_text=This is an help message
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>Help text</em>' attribute.
	 * @see #setHelp_text(String)
	 * @see com.bluexml.side.form.FormPackage#getFormElement_Help_text()
	 * @model default=""
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
	 * Returns the value of the '<em><b>Style</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * Definition: a specific style code associated to the field which may be defined in style file like css file to be applied on the field input at runtime.
	 * Constraint/Limit: the semantic of the style code is not defined in the model but is known by the targeted form engine on which will be deployed the generated forms.  In the case of XForms generation on Alfresco, this style code refers a CSS class.
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>Style</em>' attribute.
	 * @see #setStyle(String)
	 * @see com.bluexml.side.form.FormPackage#getFormElement_Style()
	 * @model
	 * @generated
	 */
	String getStyle();

	/**
	 * Sets the value of the '{@link com.bluexml.side.form.FormElement#getStyle <em>Style</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Style</em>' attribute.
	 * @see #getStyle()
	 * @generated
	 */
	void setStyle(String value);

	/**
	 * Returns the value of the '<em><b>Xtension</b></em>' attribute list.
	 * The list contents are of type {@link java.lang.String}.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * This property serves as both a general-purspose extension point and a convenient way to store some information that doesn't belong anywhere else. The information may be user data or directives for tweaking the form generator.
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>Xtension</em>' attribute list.
	 * @see com.bluexml.side.form.FormPackage#getFormElement_Xtension()
	 * @model
	 * @generated
	 */
	EList<String> getXtension();

	/**
	 * Returns the value of the '<em><b>Hidden</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Hidden</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * Definition: The 'hidden' attribute specifies that a form element will be hidden in the generated form if true or will be visible if false.
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
