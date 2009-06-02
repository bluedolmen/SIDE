/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package com.bluexml.side.view;


/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Select Field</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link com.bluexml.side.view.SelectField#getSelectWidget <em>Select Widget</em>}</li>
 * </ul>
 * </p>
 *
 * @see com.bluexml.side.view.ViewPackage#getSelectField()
 * @model
 * @generated
 */
public interface SelectField extends Field {
	/**
	 * Returns the value of the '<em><b>Select Widget</b></em>' attribute.
	 * The literals are from the enumeration {@link com.bluexml.side.view.SelectWidgetType}.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Select Widget</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Select Widget</em>' attribute.
	 * @see com.bluexml.side.view.SelectWidgetType
	 * @see #setSelectWidget(SelectWidgetType)
	 * @see com.bluexml.side.view.ViewPackage#getSelectField_SelectWidget()
	 * @model
	 * @generated
	 */
	SelectWidgetType getSelectWidget();

	/**
	 * Sets the value of the '{@link com.bluexml.side.view.SelectField#getSelectWidget <em>Select Widget</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Select Widget</em>' attribute.
	 * @see com.bluexml.side.view.SelectWidgetType
	 * @see #getSelectWidget()
	 * @generated
	 */
	void setSelectWidget(SelectWidgetType value);
		
} // SelectField
