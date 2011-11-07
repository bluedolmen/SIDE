/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package com.bluexml.side.requirements;


/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Attribute</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link com.bluexml.side.requirements.Attribute#getType <em>Type</em>}</li>
 * </ul>
 * </p>
 *
 * @see com.bluexml.side.requirements.RequirementsPackage#getAttribute()
 * @model
 * @generated
 */
public interface Attribute extends BasicElement {
	/**
	 * Returns the value of the '<em><b>Type</b></em>' attribute.
	 * The literals are from the enumeration {@link com.bluexml.side.requirements.AttributeType}.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Type</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Type</em>' attribute.
	 * @see com.bluexml.side.requirements.AttributeType
	 * @see #setType(AttributeType)
	 * @see com.bluexml.side.requirements.RequirementsPackage#getAttribute_Type()
	 * @model
	 * @generated
	 */
	AttributeType getType();

	/**
	 * Sets the value of the '{@link com.bluexml.side.requirements.Attribute#getType <em>Type</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Type</em>' attribute.
	 * @see com.bluexml.side.requirements.AttributeType
	 * @see #getType()
	 * @generated
	 */
	void setType(AttributeType value);

} // Attribute
