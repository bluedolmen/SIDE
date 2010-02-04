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
 *
 * @see com.bluexml.side.form.FormPackage#getActionField()
 * @model
 * @generated
 */
public interface ActionField extends Field {
} // ActionField
