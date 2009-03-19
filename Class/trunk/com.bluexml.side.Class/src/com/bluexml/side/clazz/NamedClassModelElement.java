/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package com.bluexml.side.clazz;



/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Named Class Model Element</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link com.bluexml.side.clazz.NamedClassModelElement#getName <em>Name</em>}</li>
 *   <li>{@link com.bluexml.side.clazz.NamedClassModelElement#getDescription <em>Description</em>}</li>
 * </ul>
 * </p>
 *
 * @see com.bluexml.side.clazz.ClazzPackage#getNamedClassModelElement()
 * @model
 * @generated
 */
public interface NamedClassModelElement extends ClassModelElement {
	/**
	 * Returns the value of the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Name</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Name</em>' attribute.
	 * @see #setName(String)
	 * @see com.bluexml.side.clazz.ClazzPackage#getNamedClassModelElement_Name()
	 * @model
	 * @generated
	 */
	String getName();

	/**
	 * Sets the value of the '{@link com.bluexml.side.clazz.NamedClassModelElement#getName <em>Name</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Name</em>' attribute.
	 * @see #getName()
	 * @generated
	 */
	void setName(String value);

	/**
	 * Returns the value of the '<em><b>Description</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Description</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Description</em>' attribute.
	 * @see #setDescription(String)
	 * @see com.bluexml.side.clazz.ClazzPackage#getNamedClassModelElement_Description()
	 * @model
	 * @generated
	 */
	String getDescription();

	/**
	 * Sets the value of the '{@link com.bluexml.side.clazz.NamedClassModelElement#getDescription <em>Description</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Description</em>' attribute.
	 * @see #getDescription()
	 * @generated
	 */
	void setDescription(String value);

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @model kind="operation" required="true"
	 *        annotation="http://www.bluexml.com/OCL body='if self.getContainer().oclIsUndefined() then\r\tself.name\relse\r\tif self.getContainer().oclIsKindOf(NamedClassModelElement) then\r\t\tself.getContainer().oclAsType(NamedClassModelElement).getFullName().concat(\'.\').concat(self.name)\r\telse\r\t\tself.getContainer().oclAsType(ClassPackage).getFullName().concat(\'.\').concat(self.name)\r\tendif\t\rendif'"
	 * @generated
	 */
	String getFullName();

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @model kind="operation" required="true"
	 *        annotation="http://www.bluexml.com/OCL body='if self.description.oclIsUndefined() or self.description.size() <0 then\r\tself.name\relse\r\tself.description\rendif'"
	 * @generated
	 */
	String getDescriptionOrName();

} // NamedClassModelElement
