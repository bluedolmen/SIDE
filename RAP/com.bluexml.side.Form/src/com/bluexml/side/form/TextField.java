/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package com.bluexml.side.form;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Text Field</b></em>'.
 * <!-- end-user-doc -->
 *
 * <!-- begin-model-doc -->
 * Definition: The 'TextField' is designed for multi-line text input.
 * Operations: same as Charfield
 * Inherits: CharField
 * <!-- end-model-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link com.bluexml.side.form.TextField#getWidget <em>Widget</em>}</li>
 * </ul>
 * </p>
 *
 * @see com.bluexml.side.form.FormPackage#getTextField()
 * @model
 * @generated
 */
public interface TextField extends CharField {
	/**
	 * Returns the value of the '<em><b>Widget</b></em>' attribute.
	 * The literals are from the enumeration {@link com.bluexml.side.form.TextWidgetType}.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Widget</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * Definition: the type of widget to use
	 * The possible values are:
	 * - TextArea for simple multi-line text input field
	 * - Rich Text Editor for inline html formatting editor.
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>Widget</em>' attribute.
	 * @see com.bluexml.side.form.TextWidgetType
	 * @see #setWidget(TextWidgetType)
	 * @see com.bluexml.side.form.FormPackage#getTextField_Widget()
	 * @model
	 * @generated
	 */
	TextWidgetType getWidget();

	/**
	 * Sets the value of the '{@link com.bluexml.side.form.TextField#getWidget <em>Widget</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Widget</em>' attribute.
	 * @see com.bluexml.side.form.TextWidgetType
	 * @see #getWidget()
	 * @generated
	 */
	void setWidget(TextWidgetType value);

} // TextField
