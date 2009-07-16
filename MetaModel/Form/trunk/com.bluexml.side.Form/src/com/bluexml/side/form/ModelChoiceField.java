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
 * Definition: The 'ModelChoiceField' makes reference to the associations of classes. An association is, by default seen as a choices list with all elements of the targeted class. This association can be expanded in order to have a form of the targeted elements in the form itself.
 * Operations:
 * - 'Relation / Expand to reference': Available on association, this action allows having the form of the linked class inside the actual form. It creates another form under the same form collection that can be personalized too: this form is directly inserted in the uniting form; we say the form is �inline� instead of �Select�.
 * - 'Relation / Collapse to Model Choice Field�: when an association has been expanded, this operation allows coming back to the Select mode.
 * - �Relation -> Add Reference: Available after an �expand� operation, it works the same way. It will add a form to the original form. Only available on associations with max cardinality set to more than one.
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
	 * Definition: the minimum number of choice items in the list.
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
	 * The literals are from the enumeration {@link com.bluexml.side.form.ReferenceWidgetType}.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Widget</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * Definition: The 'widget' attributes provides the layout for model choice field. 
	 * The possible values are:
	 * - Inline: to integrate the targeted class as a  form inside the current form
	 * - Select: to integrate the targeted  class as a widget of selection
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>Widget</em>' attribute.
	 * @see com.bluexml.side.form.ReferenceWidgetType
	 * @see #setWidget(ReferenceWidgetType)
	 * @see com.bluexml.side.form.FormPackage#getModelChoiceField_Widget()
	 * @model
	 * @generated
	 */
	ReferenceWidgetType getWidget();

	/**
	 * Sets the value of the '{@link com.bluexml.side.form.ModelChoiceField#getWidget <em>Widget</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Widget</em>' attribute.
	 * @see com.bluexml.side.form.ReferenceWidgetType
	 * @see #getWidget()
	 * @generated
	 */
	void setWidget(ReferenceWidgetType value);

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
	 * Definition: The 'Show_actions' attribute specifies that the action field �edit�, �create�, �delete� or others are available if true or are not visible if not.
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

} // ModelChoiceField
