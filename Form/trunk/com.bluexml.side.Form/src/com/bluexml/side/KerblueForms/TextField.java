/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package com.bluexml.side.KerblueForms;


/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Text Field</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link com.bluexml.side.KerblueForms.TextField#getWidget <em>Widget</em>}</li>
 * </ul>
 * </p>
 *
 * @see com.bluexml.side.KerblueForms.formPackage#getTextField()
 * @model
 * @generated
 */
public interface TextField extends CharField {
	/**
	 * Returns the value of the '<em><b>Widget</b></em>' attribute.
	 * The literals are from the enumeration {@link com.bluexml.side.KerblueForms.TextWidgetType}.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Widget</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Widget</em>' attribute.
	 * @see com.bluexml.side.KerblueForms.TextWidgetType
	 * @see #setWidget(TextWidgetType)
	 * @see com.bluexml.side.KerblueForms.formPackage#getTextField_Widget()
	 * @model
	 * @generated
	 */
	TextWidgetType getWidget();

	/**
	 * Sets the value of the '{@link com.bluexml.side.KerblueForms.TextField#getWidget <em>Widget</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Widget</em>' attribute.
	 * @see com.bluexml.side.KerblueForms.TextWidgetType
	 * @see #getWidget()
	 * @generated
	 */
	void setWidget(TextWidgetType value);
		
} // TextField
