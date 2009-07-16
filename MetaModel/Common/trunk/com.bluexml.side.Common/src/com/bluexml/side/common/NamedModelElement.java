/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package com.bluexml.side.common;


/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Named Model Element</b></em>'.
 * <!-- end-user-doc -->
 *
 * <!-- begin-model-doc -->
 * Definition: main elements of model inherits of NameModelElement which allows to declare the name and which provides multiple OCL operations used by the modelers and the generators.
 * <!-- end-model-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link com.bluexml.side.common.NamedModelElement#getName <em>Name</em>}</li>
 * </ul>
 * </p>
 *
 * @see com.bluexml.side.common.CommonPackage#getNamedModelElement()
 * @model
 * @generated
 */
public interface NamedModelElement extends ModelElement {
	/**
	 * Returns the value of the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Name</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * Definition: the name of the concerned element; this name is used as leaf of the fully qualified name of an element: the other part of the fully qualified name are composed by the tree package names.
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>Name</em>' attribute.
	 * @see #setName(String)
	 * @see com.bluexml.side.common.CommonPackage#getNamedModelElement_Name()
	 * @model
	 * @generated
	 */
	String getName();

	/**
	 * Sets the value of the '{@link com.bluexml.side.common.NamedModelElement#getName <em>Name</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Name</em>' attribute.
	 * @see #getName()
	 * @generated
	 */
	void setName(String value);

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @model kind="operation" required="true"
	 *        annotation="http://www.bluexml.com/OCL body='if self.getContainer().oclIsUndefined() then\r\tself.name\relse\r\tif self.getContainer().oclIsKindOf(NamedModelElement) then\r\t\tself.getContainer().oclAsType(NamedModelElement).getFullName().concat(\'.\').concat(self.name)\r\telse\r\t\t\'\'\r\tendif\t\rendif'"
	 * @generated
	 */
	String getFullName();

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @model kind="operation"
	 *        annotation="http://www.bluexml.com/OCL body='if self.documentation.oclIsUndefined() or self.documentation.size() <0 then\r\tself.name\relse\r\tself.documentation\rendif'"
	 * @generated
	 */
	String getDocumentationOrName();

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @model kind="operation"
	 *        annotation="http://www.bluexml.com/OCL body='if self.description.oclIsUndefined() or self.description.size() <0 then\r\tself.name\relse\r\tself.description\rendif'"
	 * @generated
	 */
	String getDescriptionOrName();

} // NamedModelElement
