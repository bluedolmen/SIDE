/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package com.bluexml.side.form;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Float Field</b></em>'.
 * <!-- end-user-doc -->
 *
 * <!-- begin-model-doc -->
 * Definition: The 'FloatField' specifies a field for float input.
 * 
 * 
 * Inherits:
 *  Field.
 * <!-- end-model-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link com.bluexml.side.form.FloatField#getMin_value <em>Min value</em>}</li>
 *   <li>{@link com.bluexml.side.form.FloatField#getMax_value <em>Max value</em>}</li>
 * </ul>
 * </p>
 *
 * @see com.bluexml.side.form.FormPackage#getFloatField()
 * @model
 * @generated
 */
public interface FloatField extends NumericalField {
	/**
	 * Returns the value of the '<em><b>Min value</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Min value</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * Definition: minimum value for the input. If the 'min_value' attribute is superior than 0, the Float field is mandatory.
	 * Constraint/limit: The input value cannot be superior to the ‘max_value’.
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>Min value</em>' attribute.
	 * @see #setMin_value(float)
	 * @see com.bluexml.side.form.FormPackage#getFloatField_Min_value()
	 * @model
	 * @generated
	 */
	float getMin_value();

	/**
	 * Sets the value of the '{@link com.bluexml.side.form.FloatField#getMin_value <em>Min value</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Min value</em>' attribute.
	 * @see #getMin_value()
	 * @generated
	 */
	void setMin_value(float value);

	/**
	 * Returns the value of the '<em><b>Max value</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Max value</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * Definition: maximum value for the input.
	 * Constraint/limit: The input value cannot be inferior to the 'min_value'.
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>Max value</em>' attribute.
	 * @see #setMax_value(float)
	 * @see com.bluexml.side.form.FormPackage#getFloatField_Max_value()
	 * @model
	 * @generated
	 */
	float getMax_value();

	/**
	 * Sets the value of the '{@link com.bluexml.side.form.FloatField#getMax_value <em>Max value</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Max value</em>' attribute.
	 * @see #getMax_value()
	 * @generated
	 */
	void setMax_value(float value);

} // FloatField
