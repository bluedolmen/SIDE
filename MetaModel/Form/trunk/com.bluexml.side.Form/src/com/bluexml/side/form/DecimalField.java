/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package com.bluexml.side.form;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Decimal Field</b></em>'.
 * <!-- end-user-doc -->
 *
 * <!-- begin-model-doc -->
 * Definition: The 'DecimalField' specifies a field for decimal input.
 * 
 * 
 * Inherits:
 *  Field.
 * <!-- end-model-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link com.bluexml.side.form.DecimalField#getMin_value <em>Min value</em>}</li>
 *   <li>{@link com.bluexml.side.form.DecimalField#getMax_value <em>Max value</em>}</li>
 *   <li>{@link com.bluexml.side.form.DecimalField#getMax_digits <em>Max digits</em>}</li>
 *   <li>{@link com.bluexml.side.form.DecimalField#getDecimal_places <em>Decimal places</em>}</li>
 * </ul>
 * </p>
 *
 * @see com.bluexml.side.form.FormPackage#getDecimalField()
 * @model
 * @generated
 */
public interface DecimalField extends NumericalField {
	/**
	 * Returns the value of the '<em><b>Min value</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Min value</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * Definition: minimum value for the input. If the 'min_value' attribute is superior than 0, the Decimal field is mandatory.
	 * Constraint/limit: The input value cannot be superior to the ‘max_value’.
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>Min value</em>' attribute.
	 * @see #setMin_value(String)
	 * @see com.bluexml.side.form.FormPackage#getDecimalField_Min_value()
	 * @model
	 * @generated
	 */
	String getMin_value();

	/**
	 * Sets the value of the '{@link com.bluexml.side.form.DecimalField#getMin_value <em>Min value</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Min value</em>' attribute.
	 * @see #getMin_value()
	 * @generated
	 */
	void setMin_value(String value);

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
	 * @see #setMax_value(String)
	 * @see com.bluexml.side.form.FormPackage#getDecimalField_Max_value()
	 * @model
	 * @generated
	 */
	String getMax_value();

	/**
	 * Sets the value of the '{@link com.bluexml.side.form.DecimalField#getMax_value <em>Max value</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Max value</em>' attribute.
	 * @see #getMax_value()
	 * @generated
	 */
	void setMax_value(String value);

	/**
	 * Returns the value of the '<em><b>Max digits</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Max digits</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * Definition: maximum number of digits before and after the decimal point. The maximum length of the value is 'max_digits' plus 1 (decimal separator).
	 * Example: for '1.2345', 'max_digits' equals 5 and 'decimal_places' equals 4.
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>Max digits</em>' attribute.
	 * @see #setMax_digits(int)
	 * @see com.bluexml.side.form.FormPackage#getDecimalField_Max_digits()
	 * @model
	 * @generated
	 */
	int getMax_digits();

	/**
	 * Sets the value of the '{@link com.bluexml.side.form.DecimalField#getMax_digits <em>Max digits</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Max digits</em>' attribute.
	 * @see #getMax_digits()
	 * @generated
	 */
	void setMax_digits(int value);

	/**
	 * Returns the value of the '<em><b>Decimal places</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Decimal places</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * 
	 * Definition: number of digits after the decimal separator.
	 * Example: for '45.12345', 'decimal_places' equals 5 and 'max_digits' equals 7.
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>Decimal places</em>' attribute.
	 * @see #setDecimal_places(int)
	 * @see com.bluexml.side.form.FormPackage#getDecimalField_Decimal_places()
	 * @model
	 * @generated
	 */
	int getDecimal_places();

	/**
	 * Sets the value of the '{@link com.bluexml.side.form.DecimalField#getDecimal_places <em>Decimal places</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Decimal places</em>' attribute.
	 * @see #getDecimal_places()
	 * @generated
	 */
	void setDecimal_places(int value);

} // DecimalField
