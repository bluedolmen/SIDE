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
 * Inherits:
 * - Field.
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
public interface FloatField extends Field {
	/**
	 * Returns the value of the '<em><b>Min value</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Min value</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * Definition: The 'min_value' attribute specifies a minimum value for the input.
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>Min value</em>' attribute.
	 * @see #setMin_value(int)
	 * @see com.bluexml.side.form.FormPackage#getFloatField_Min_value()
	 * @model
	 * @generated
	 */
	int getMin_value();

	/**
	 * Sets the value of the '{@link com.bluexml.side.form.FloatField#getMin_value <em>Min value</em>}' attribute.
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
	 * Definition: The 'max_value' attribute specifies a maximum value for the input.
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>Max value</em>' attribute.
	 * @see #setMax_value(int)
	 * @see com.bluexml.side.form.FormPackage#getFloatField_Max_value()
	 * @model
	 * @generated
	 */
	int getMax_value();

	/**
	 * Sets the value of the '{@link com.bluexml.side.form.FloatField#getMax_value <em>Max value</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Max value</em>' attribute.
	 * @see #getMax_value()
	 * @generated
	 */
	void setMax_value(int value);

} // FloatField
