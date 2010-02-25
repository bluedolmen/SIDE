/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package com.bluexml.side.form;
 
/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Action Field</b></em>'.
 * <!-- end-user-doc -->
 *
 * <!-- begin-model-doc -->
 * Definition: The 'ActionField' represents a button.&#x0D;
 * You must set the ‘name’ attribute to the fully qualified name of a Java class and the ‘Label’ attribute to the text (or code for internationalization) you want to display on the button.<br>
 * This class must implement a ‘run’ method whose signature is : public void run(org.w3c.dom.Node node, java.lang.String dataId)
 * where :\n
 * -	‘node’ is the form tree&#x0D;
 * -	‘dataId’ is the id of the node being edited: dataId is null when in creation mode.<br>
 * When the form is initialized, the button is generated. On click, the Java class loader loads and run the class (check that the class location is in the classpath).
 * &#x0D;&#x0D;
 * 
 * Inherits:
 *  Field.
 * <!-- end-model-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link com.bluexml.side.form.ActionField#getAction_handler <em>Action handler</em>}</li>
 * </ul>
 * </p>
 *
 * @see com.bluexml.side.form.FormPackage#getActionField()
 * @model
 * @generated
 */
public interface ActionField extends Field {

	/**
	 * Returns the value of the '<em><b>Action handler</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * This property refers to the action handler according to contextualized conventions. For instance, the BlueXML XForms generation requires this property to be the fully qualified name of a Java class that implements an interface. See the documentation for more details.
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>Action handler</em>' attribute.
	 * @see #setAction_handler(String)
	 * @see com.bluexml.side.form.FormPackage#getActionField_Action_handler()
	 * @model
	 * @generated
	 */
	String getAction_handler();

	/**
	 * Sets the value of the '{@link com.bluexml.side.form.ActionField#getAction_handler <em>Action handler</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Action handler</em>' attribute.
	 * @see #getAction_handler()
	 * @generated
	 */
	void setAction_handler(String value);
} // ActionField
