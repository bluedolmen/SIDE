/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package com.bluexml.side.KerblueForms;


/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Choice Field</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link com.bluexml.side.KerblueForms.ChoiceField#getMin_bound <em>Min bound</em>}</li>
 *   <li>{@link com.bluexml.side.KerblueForms.ChoiceField#getMax_bound <em>Max bound</em>}</li>
 *   <li>{@link com.bluexml.side.KerblueForms.ChoiceField#getWidget <em>Widget</em>}</li>
 *   <li>{@link com.bluexml.side.KerblueForms.ChoiceField#isMultiple <em>Multiple</em>}</li>
 *   <li>{@link com.bluexml.side.KerblueForms.ChoiceField#getFilterParent <em>Filter Parent</em>}</li>
 *   <li>{@link com.bluexml.side.KerblueForms.ChoiceField#getFilterData <em>Filter Data</em>}</li>
 * </ul>
 * </p>
 *
 * @see com.bluexml.side.KerblueForms.formPackage#getChoiceField()
 * @model
 * @generated
 */
public interface ChoiceField extends Field {
	/**
	 * Returns the value of the '<em><b>Min bound</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Min bound</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Min bound</em>' attribute.
	 * @see #setMin_bound(int)
	 * @see com.bluexml.side.KerblueForms.formPackage#getChoiceField_Min_bound()
	 * @model
	 * @generated
	 */
	int getMin_bound();

	/**
	 * Sets the value of the '{@link com.bluexml.side.KerblueForms.ChoiceField#getMin_bound <em>Min bound</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Min bound</em>' attribute.
	 * @see #getMin_bound()
	 * @generated
	 */
	void setMin_bound(int value);

	/**
	 * Returns the value of the '<em><b>Max bound</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Max bound</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Max bound</em>' attribute.
	 * @see #setMax_bound(int)
	 * @see com.bluexml.side.KerblueForms.formPackage#getChoiceField_Max_bound()
	 * @model
	 * @generated
	 */
	int getMax_bound();

	/**
	 * Sets the value of the '{@link com.bluexml.side.KerblueForms.ChoiceField#getMax_bound <em>Max bound</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Max bound</em>' attribute.
	 * @see #getMax_bound()
	 * @generated
	 */
	void setMax_bound(int value);

	/**
	 * Returns the value of the '<em><b>Widget</b></em>' attribute.
	 * The literals are from the enumeration {@link com.bluexml.side.KerblueForms.ChoiceWidgetType}.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Widget</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Widget</em>' attribute.
	 * @see com.bluexml.side.KerblueForms.ChoiceWidgetType
	 * @see #setWidget(ChoiceWidgetType)
	 * @see com.bluexml.side.KerblueForms.formPackage#getChoiceField_Widget()
	 * @model
	 * @generated
	 */
	ChoiceWidgetType getWidget();

	/**
	 * Sets the value of the '{@link com.bluexml.side.KerblueForms.ChoiceField#getWidget <em>Widget</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Widget</em>' attribute.
	 * @see com.bluexml.side.KerblueForms.ChoiceWidgetType
	 * @see #getWidget()
	 * @generated
	 */
	void setWidget(ChoiceWidgetType value);

	/**
	 * Returns the value of the '<em><b>Multiple</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Multiple</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Multiple</em>' attribute.
	 * @see #setMultiple(boolean)
	 * @see com.bluexml.side.KerblueForms.formPackage#getChoiceField_Multiple()
	 * @model
	 * @generated
	 */
	boolean isMultiple();

	/**
	 * Sets the value of the '{@link com.bluexml.side.KerblueForms.ChoiceField#isMultiple <em>Multiple</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Multiple</em>' attribute.
	 * @see #isMultiple()
	 * @generated
	 */
	void setMultiple(boolean value);

	/**
	 * Returns the value of the '<em><b>Filter Parent</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Filter Parent</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Filter Parent</em>' attribute.
	 * @see #setFilterParent(String)
	 * @see com.bluexml.side.KerblueForms.formPackage#getChoiceField_FilterParent()
	 * @model
	 * @generated
	 */
	String getFilterParent();

	/**
	 * Sets the value of the '{@link com.bluexml.side.KerblueForms.ChoiceField#getFilterParent <em>Filter Parent</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Filter Parent</em>' attribute.
	 * @see #getFilterParent()
	 * @generated
	 */
	void setFilterParent(String value);

	/**
	 * Returns the value of the '<em><b>Filter Data</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Filter Data</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Filter Data</em>' attribute.
	 * @see #setFilterData(String)
	 * @see com.bluexml.side.KerblueForms.formPackage#getChoiceField_FilterData()
	 * @model
	 * @generated
	 */
	String getFilterData();

	/**
	 * Sets the value of the '{@link com.bluexml.side.KerblueForms.ChoiceField#getFilterData <em>Filter Data</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Filter Data</em>' attribute.
	 * @see #getFilterData()
	 * @generated
	 */
	void setFilterData(String value);
		
} // ChoiceField
