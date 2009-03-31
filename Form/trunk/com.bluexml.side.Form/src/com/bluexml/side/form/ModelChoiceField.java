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
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link com.bluexml.side.form.ModelChoiceField#getMin_bound <em>Min bound</em>}</li>
 *   <li>{@link com.bluexml.side.form.ModelChoiceField#getMax_bound <em>Max bound</em>}</li>
 *   <li>{@link com.bluexml.side.form.ModelChoiceField#getTarget <em>Target</em>}</li>
 *   <li>{@link com.bluexml.side.form.ModelChoiceField#getAssociation_formClass <em>Association form Class</em>}</li>
 *   <li>{@link com.bluexml.side.form.ModelChoiceField#getWidget <em>Widget</em>}</li>
 * </ul>
 * </p>
 *
 * @see com.bluexml.side.form.formPackage#getModelChoiceField()
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
	 * @return the value of the '<em>Min bound</em>' attribute.
	 * @see #setMin_bound(int)
	 * @see com.bluexml.side.form.formPackage#getModelChoiceField_Min_bound()
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
	 * @see com.bluexml.side.form.formPackage#getModelChoiceField_Max_bound()
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
	 * The list contents are of type {@link com.bluexml.side.form.FormClass}.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Target</em>' reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Target</em>' reference list.
	 * @see com.bluexml.side.form.formPackage#getModelChoiceField_Target()
	 * @model
	 * @generated
	 */
	EList<FormClass> getTarget();

	/**
	 * Returns the value of the '<em><b>Association form Class</b></em>' reference list.
	 * The list contents are of type {@link com.bluexml.side.form.FormClass}.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Association form Class</em>' reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Association form Class</em>' reference list.
	 * @see com.bluexml.side.form.formPackage#getModelChoiceField_Association_formClass()
	 * @model
	 * @generated
	 */
	EList<FormClass> getAssociation_formClass();

	/**
	 * Returns the value of the '<em><b>Widget</b></em>' attribute.
	 * The literals are from the enumeration {@link com.bluexml.side.form.ReferenceWidgetType}.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Widget</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Widget</em>' attribute.
	 * @see com.bluexml.side.form.ReferenceWidgetType
	 * @see #setWidget(ReferenceWidgetType)
	 * @see com.bluexml.side.form.formPackage#getModelChoiceField_Widget()
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

} // ModelChoiceField
