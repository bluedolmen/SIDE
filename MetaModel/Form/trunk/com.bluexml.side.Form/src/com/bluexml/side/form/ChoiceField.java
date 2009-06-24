/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package com.bluexml.side.form;



/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Choice Field</b></em>'.
 * <!-- end-user-doc -->
 *
 * <!-- begin-model-doc -->
 * Definition: The 'ModelChoiceField' make reference to the associations of classes. An association is, by default see as a choices list with all element of the targeted class. This association can be expanded in order to have a form of the targeted elements in the form itself.
 * Operations:
 * - 'Relation / Expand to reference': Available on association this action allows having the form of the linked class inside the actual form. It creates another form under the same form collection that can be personalized too: this form is directly inserted in the uniting form; we say the form is ÔinlineÕ instead of ÔSelectÕ.
 * - 'Relation / Collapse to Model Choice FieldÕ: when an association has been expanded, this operation allows to come back to the Select mode.
 * - 'Relation -> Add Reference: Available after an expand and it works the same way. It will add a form to the original form. Only available on associations with max cardinality set to more than one.
 * Inherits: 
 * - ClassReference.
 * - Field.
 * 
 * <!-- end-model-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link com.bluexml.side.form.ChoiceField#getMin_bound <em>Min bound</em>}</li>
 *   <li>{@link com.bluexml.side.form.ChoiceField#getMax_bound <em>Max bound</em>}</li>
 *   <li>{@link com.bluexml.side.form.ChoiceField#getWidget <em>Widget</em>}</li>
 *   <li>{@link com.bluexml.side.form.ChoiceField#isMultiple <em>Multiple</em>}</li>
 *   <li>{@link com.bluexml.side.form.ChoiceField#getFilterParent <em>Filter Parent</em>}</li>
 *   <li>{@link com.bluexml.side.form.ChoiceField#getFilterData <em>Filter Data</em>}</li>
 * </ul>
 * </p>
 *
 * @see com.bluexml.side.form.FormPackage#getChoiceField()
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
	 * <!-- begin-model-doc -->
	 * Definition: The 'min_bound' attribute defines the number minimum of choice.
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>Min bound</em>' attribute.
	 * @see #setMin_bound(int)
	 * @see com.bluexml.side.form.FormPackage#getChoiceField_Min_bound()
	 * @model
	 * @generated
	 */
	int getMin_bound();

	/**
	 * Sets the value of the '{@link com.bluexml.side.form.ChoiceField#getMin_bound <em>Min bound</em>}' attribute.
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
	 * <!-- begin-model-doc -->
	 * Definition: The 'max_bound' attribute defines the number maximum of choice in the list.
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>Max bound</em>' attribute.
	 * @see #setMax_bound(int)
	 * @see com.bluexml.side.form.FormPackage#getChoiceField_Max_bound()
	 * @model
	 * @generated
	 */
	int getMax_bound();

	/**
	 * Sets the value of the '{@link com.bluexml.side.form.ChoiceField#getMax_bound <em>Max bound</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Max bound</em>' attribute.
	 * @see #getMax_bound()
	 * @generated
	 */
	void setMax_bound(int value);

	/**
	 * Returns the value of the '<em><b>Widget</b></em>' attribute.
	 * The literals are from the enumeration {@link com.bluexml.side.form.ChoiceWidgetType}.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Widget</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * Definition: The 'widget' attributes provides a layout for model choice field. 
	 * 
	 * The possible values are:
	 * - Association Class Inline: in the case of an association class, to integrate the targeted class as a form inside the current form.
	 * - Association Class Select: in the case of an association class, to integrate the targeted class as a widget of selection
	 * - Inline: to integrate the targeted class as a  form inside the current form
	 * - Select: to integrate the targeted  class as a widget of selection
	 * 
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>Widget</em>' attribute.
	 * @see com.bluexml.side.form.ChoiceWidgetType
	 * @see #setWidget(ChoiceWidgetType)
	 * @see com.bluexml.side.form.FormPackage#getChoiceField_Widget()
	 * @model
	 * @generated
	 */
	ChoiceWidgetType getWidget();

	/**
	 * Sets the value of the '{@link com.bluexml.side.form.ChoiceField#getWidget <em>Widget</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Widget</em>' attribute.
	 * @see com.bluexml.side.form.ChoiceWidgetType
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
	 * <!-- begin-model-doc -->
	 * Definition: The 'multiple' attribute allows to select multiple choice.
	 * 
	 * Example:
	 * - 'false': allows no multiple selection.
	 * - 'true': allows multiple selection.
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>Multiple</em>' attribute.
	 * @see #setMultiple(boolean)
	 * @see com.bluexml.side.form.FormPackage#getChoiceField_Multiple()
	 * @model
	 * @generated
	 */
	boolean isMultiple();

	/**
	 * Sets the value of the '{@link com.bluexml.side.form.ChoiceField#isMultiple <em>Multiple</em>}' attribute.
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
	 * <!-- begin-model-doc -->
	 * Definition: The 'filterParent" attribute filter choice by parent.
	 * 
	 * Example: filter a list of department by country.
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>Filter Parent</em>' attribute.
	 * @see #setFilterParent(String)
	 * @see com.bluexml.side.form.FormPackage#getChoiceField_FilterParent()
	 * @model
	 * @generated
	 */
	String getFilterParent();

	/**
	 * Sets the value of the '{@link com.bluexml.side.form.ChoiceField#getFilterParent <em>Filter Parent</em>}' attribute.
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
	 * <!-- begin-model-doc -->
	 * Definition: The 'filterData" attribute filter choice by data.
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>Filter Data</em>' attribute.
	 * @see #setFilterData(String)
	 * @see com.bluexml.side.form.FormPackage#getChoiceField_FilterData()
	 * @model
	 * @generated
	 */
	String getFilterData();

	/**
	 * Sets the value of the '{@link com.bluexml.side.form.ChoiceField#getFilterData <em>Filter Data</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Filter Data</em>' attribute.
	 * @see #getFilterData()
	 * @generated
	 */
	void setFilterData(String value);

} // ChoiceField
