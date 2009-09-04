/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package com.bluexml.side.form;

import org.eclipse.emf.common.util.EList;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Model Choice Field</b></em>'.
 * <!-- end-user-doc -->
 *
 * <!-- begin-model-doc -->
 * Definition: The 'ModelChoiceField' makes reference to the associations of classes. An association is, by default seen as a select list with all elements of the targeted class. This association can be expanded in order to have a form of the targeted elements in the form itself.
 * Operations:
 * - 'Relation / Expand to reference': Available on association, this action allows having the form of the linked class inside the actual form. It creates another form under the same form collection that can be personalized too: this form is directly inserted in the uniting form; we say the form is ‘inline’ instead of ‘Select’.
 * - 'Relation / Collapse to Model Choice Field’: when an association has been expanded, this operation allows coming back to the Select mode.
 * - ‘Relation / Add Reference': Available after an ‘expand’ operation, it works the same way. It will add a form to the original form. Only available on associations with max cardinality set to more than one.
 * Inherits: ClassReference, Field.
 * <!-- end-model-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link com.bluexml.side.form.ModelChoiceField#getMin_bound <em>Min bound</em>}</li>
 *   <li>{@link com.bluexml.side.form.ModelChoiceField#getMax_bound <em>Max bound</em>}</li>
 *   <li>{@link com.bluexml.side.form.ModelChoiceField#getTarget <em>Target</em>}</li>
 *   <li>{@link com.bluexml.side.form.ModelChoiceField#getAssociation_formClass <em>Association form Class</em>}</li>
 *   <li>{@link com.bluexml.side.form.ModelChoiceField#getWidget <em>Widget</em>}</li>
 *   <li>{@link com.bluexml.side.form.ModelChoiceField#isShow_actions <em>Show actions</em>}</li>
 *   <li>{@link com.bluexml.side.form.ModelChoiceField#getFormat_pattern <em>Format pattern</em>}</li>
 *   <li>{@link com.bluexml.side.form.ModelChoiceField#getLabel_length <em>Label length</em>}</li>
 * </ul>
 * </p>
 *
 * @see com.bluexml.side.form.FormPackage#getModelChoiceField()
 * @model
 * @generated
 */
public interface ModelChoiceField extends Field, ClassReference {
	/**
	 * Returns the value of the '<em><b>Min bound</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Min bound</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * Definition: the number of choice items in the list for pagination.
	 * Constraint/limit: The input value cannot be not superior to the ‘max_bound’.
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>Min bound</em>' attribute.
	 * @see #setMin_bound(int)
	 * @see com.bluexml.side.form.FormPackage#getModelChoiceField_Min_bound()
	 * @model
	 * @generated
	 */
	int getMin_bound();

	/**
	 * Sets the value of the '{@link com.bluexml.side.form.ModelChoiceField#getMin_bound <em>Min bound</em>}' attribute.
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
	 * Definition: the maximum number of choice items in the list. If the number of associated data elements is superior to this value, only the first 'max_bound' number of elements will be returned and processed in the choice list.
	 * Constraint/limit: The input value cannot be inferior to the 'min_bound'.
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>Max bound</em>' attribute.
	 * @see #setMax_bound(int)
	 * @see com.bluexml.side.form.FormPackage#getModelChoiceField_Max_bound()
	 * @model
	 * @generated
	 */
	int getMax_bound();

	/**
	 * Sets the value of the '{@link com.bluexml.side.form.ModelChoiceField#getMax_bound <em>Max bound</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Max bound</em>' attribute.
	 * @see #getMax_bound()
	 * @generated
	 */
	void setMax_bound(int value);

	/**
	 * Returns the value of the '<em><b>Target</b></em>' reference list.
	 * The list contents are of type {@link com.bluexml.side.form.FormContainer}.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Target</em>' reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Target</em>' reference list.
	 * @see com.bluexml.side.form.FormPackage#getModelChoiceField_Target()
	 * @model
	 * @generated
	 */
	EList<FormContainer> getTarget();

	/**
	 * Returns the value of the '<em><b>Association form Class</b></em>' reference list.
	 * The list contents are of type {@link com.bluexml.side.form.FormContainer}.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Association form Class</em>' reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Association form Class</em>' reference list.
	 * @see com.bluexml.side.form.FormPackage#getModelChoiceField_Association_formClass()
	 * @model
	 * @generated
	 */
	EList<FormContainer> getAssociation_formClass();

	/**
	 * Returns the value of the '<em><b>Widget</b></em>' attribute.
	 * The literals are from the enumeration {@link com.bluexml.side.form.ModelChoiceWidgetType}.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Widget</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * Definition: The 'widget' attributes provides the layout for model choice field.
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>Widget</em>' attribute.
	 * @see com.bluexml.side.form.ModelChoiceWidgetType
	 * @see #setWidget(ModelChoiceWidgetType)
	 * @see com.bluexml.side.form.FormPackage#getModelChoiceField_Widget()
	 * @model
	 * @generated
	 */
	ModelChoiceWidgetType getWidget();

	/**
	 * Sets the value of the '{@link com.bluexml.side.form.ModelChoiceField#getWidget <em>Widget</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Widget</em>' attribute.
	 * @see com.bluexml.side.form.ModelChoiceWidgetType
	 * @see #getWidget()
	 * @generated
	 */
	void setWidget(ModelChoiceWidgetType value);

	/**
	 * Returns the value of the '<em><b>Show actions</b></em>' attribute.
	 * The default value is <code>"true"</code>.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Show actions</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * Definition: The 'Show_actions' attribute specifies that the action field ‘edit’, ‘create’, ‘delete’ or others are available if true or are not visible if false. If false, this allows to integrated read-only sub-form.
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>Show actions</em>' attribute.
	 * @see #setShow_actions(boolean)
	 * @see com.bluexml.side.form.FormPackage#getModelChoiceField_Show_actions()
	 * @model default="true"
	 * @generated
	 */
	boolean isShow_actions();

	/**
	 * Sets the value of the '{@link com.bluexml.side.form.ModelChoiceField#isShow_actions <em>Show actions</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Show actions</em>' attribute.
	 * @see #isShow_actions()
	 * @generated
	 */
	void setShow_actions(boolean value);

	/**
	 * Returns the value of the '<em><b>Format pattern</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Format pattern</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * Specifies how items of the list are displayed. Contains plain text and/or references to properties of the target class.
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>Format pattern</em>' attribute.
	 * @see #setFormat_pattern(String)
	 * @see com.bluexml.side.form.FormPackage#getModelChoiceField_Format_pattern()
	 * @model
	 * @generated
	 */
	String getFormat_pattern();

	/**
	 * Sets the value of the '{@link com.bluexml.side.form.ModelChoiceField#getFormat_pattern <em>Format pattern</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Format pattern</em>' attribute.
	 * @see #getFormat_pattern()
	 * @generated
	 */
	void setFormat_pattern(String value);

	/**
	 * Returns the value of the '<em><b>Label length</b></em>' attribute.
	 * The default value is <code>"0"</code>.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Label length</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * The maximum allowable length for formatted labels, always enforced (independently of whether a format pattern is specified). If 0, no truncating is performed. 
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>Label length</em>' attribute.
	 * @see #setLabel_length(int)
	 * @see com.bluexml.side.form.FormPackage#getModelChoiceField_Label_length()
	 * @model default="0" unique="false"
	 * @generated
	 */
	int getLabel_length();

	/**
	 * Sets the value of the '{@link com.bluexml.side.form.ModelChoiceField#getLabel_length <em>Label length</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Label length</em>' attribute.
	 * @see #getLabel_length()
	 * @generated
	 */
	void setLabel_length(int value);

} // ModelChoiceField
