/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package com.bluexml.side.view;


/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Text Field</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link com.bluexml.side.view.TextField#getWidgetType <em>Widget Type</em>}</li>
 * </ul>
 * </p>
 *
 * @see com.bluexml.side.view.ViewPackage#getTextField()
 * @model
 * @generated
 */
public interface TextField extends Field {
	/**
	 * Returns the value of the '<em><b>Widget Type</b></em>' attribute.
	 * The literals are from the enumeration {@link com.bluexml.side.view.WidgetTextType}.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Widget Type</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Widget Type</em>' attribute.
	 * @see com.bluexml.side.view.WidgetTextType
	 * @see #setWidgetType(WidgetTextType)
	 * @see com.bluexml.side.view.ViewPackage#getTextField_WidgetType()
	 * @model
	 * @generated
	 */
	WidgetTextType getWidgetType();

	/**
	 * Sets the value of the '{@link com.bluexml.side.view.TextField#getWidgetType <em>Widget Type</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Widget Type</em>' attribute.
	 * @see com.bluexml.side.view.WidgetTextType
	 * @see #getWidgetType()
	 * @generated
	 */
	void setWidgetType(WidgetTextType value);
		
} // TextField
