/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package com.bluexml.side.form;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Integer Field</b></em>'.
 * <!-- end-user-doc -->
 *
 * <!-- begin-model-doc -->
 * Definition: The 'IntegerField' specifies a field for integer input.
 * 
 * 
 * Inherits:
 *  Field.
 * <!-- end-model-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link com.bluexml.side.form.IntegerField#getMin_value <em>Min value</em>}</li>
 *   <li>{@link com.bluexml.side.form.IntegerField#getMax_value <em>Max value</em>}</li>
 * </ul>
 * </p>
 *
 * @see com.bluexml.side.form.FormPackage#getIntegerField()
 * @model
 * @generated
 */
public interface IntegerField extends NumericalField {
	/**
	 * Returns the value of the '<em><b>Min value</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Min value</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * Definition: minimum value for the input. If the 'min_value' attribute is superior than 0, the Interger field is mandatory.
	 * Constraint/limit: The input value cannot be superior to the ‘max_value’.
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>Min value</em>' attribute.
	 * @see #setMin_value(int)
	 * @see com.bluexml.side.form.FormPackage#getIntegerField_Min_value()
	 * @model
	 * @generated
	 */
	int getMin_value();

	/**
	 * Sets the value of the '{@link com.bluexml.side.form.IntegerField#getMin_value <em>Min value</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Min value</em>' attribute.
	 * @see #getMin_value()
	 * @generated
	 */
	void setMin_value(int value);

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
	 * @see #setMax_value(int)
	 * @see com.bluexml.side.form.FormPackage#getIntegerField_Max_value()
	 * @model
	 * @generated
	 */
	int getMax_value();

	/**
	 * Sets the value of the '{@link com.bluexml.side.form.IntegerField#getMax_value <em>Max value</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Max value</em>' attribute.
	 * @see #getMax_value()
	 * @generated
	 */
	void setMax_value(int value);

} // IntegerField
