/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package com.bluexml.side.form;


/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Model Choice Search Field</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link com.bluexml.side.form.ModelChoiceSearchField#getWidget <em>Widget</em>}</li>
 * </ul>
 * </p>
 *
 * @see com.bluexml.side.form.FormPackage#getModelChoiceSearchField()
 * @model annotation="http://www.bluexml.com/OCL valideAssociationRef='self.ref.oclAsType(clazz::Association).getTarget()->includes(self.real_class)'"
 *        annotation="http://www.eclipse.org/emf/2002/Ecore constraints='valideAssociationRef'"
 * @generated
 */
public interface ModelChoiceSearchField extends SearchField, ClassReference {

	/**
	 * Returns the value of the '<em><b>Widget</b></em>' attribute.
	 * The literals are from the enumeration {@link com.bluexml.side.form.ModelChoiceWidgetType}.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * Definition: The 'widget' attributes provides the layout for model choice field.
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>Widget</em>' attribute.
	 * @see com.bluexml.side.form.ModelChoiceWidgetType
	 * @see #setWidget(ModelChoiceWidgetType)
	 * @see com.bluexml.side.form.FormPackage#getModelChoiceSearchField_Widget()
	 * @model
	 * @generated
	 */
	ModelChoiceWidgetType getWidget();

	/**
	 * Sets the value of the '{@link com.bluexml.side.form.ModelChoiceSearchField#getWidget <em>Widget</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Widget</em>' attribute.
	 * @see com.bluexml.side.form.ModelChoiceWidgetType
	 * @see #getWidget()
	 * @generated
	 */
	void setWidget(ModelChoiceWidgetType value);
} // ModelChoiceSearchField
