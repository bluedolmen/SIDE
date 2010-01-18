/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package com.bluexml.side.form;

import java.util.Date;
import org.eclipse.emf.common.util.EList;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Date Field</b></em>'.
 * <!-- end-user-doc -->
 *
 * <!-- begin-model-doc -->
 * Definition: The 'DateField' provides a field for date input.
 * 
 * 
 * Inherits:
 *  Field.
 * <!-- end-model-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link com.bluexml.side.form.DateField#getInput_formats <em>Input formats</em>}</li>
 *   <li>{@link com.bluexml.side.form.DateField#getMin_date <em>Min date</em>}</li>
 *   <li>{@link com.bluexml.side.form.DateField#getMax_date <em>Max date</em>}</li>
 * </ul>
 * </p>
 *
 * @see com.bluexml.side.form.FormPackage#getDateField()
 * @model
 * @generated
 */
public interface DateField extends Field {
	/**
	 * Returns the value of the '<em><b>Input formats</b></em>' attribute list.
	 * The list contents are of type {@link java.lang.String}.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Input formats</em>' attribute list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * Definition: The 'input_formats' attribute specifies a format for date which is conform to a Java Regular Expression (java.util.regex).
	 * 
	 * Example:
	 * 
	 * - MM/DD/YYYY to match a mm/dd/yyyy date format.
	 * - (19|20)\d\d[- /.](0[1-9]|1[012])[- /.](0[1-9]|[12][0-9]|3[01]) to match a data yyyy-mm-dd format from between 1900-01-01 and 2099-12-31, with a choice of four separators.
	 * 
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>Input formats</em>' attribute list.
	 * @see com.bluexml.side.form.FormPackage#getDateField_Input_formats()
	 * @model
	 * @generated
	 */
	EList<String> getInput_formats();

	/**
	 * Returns the value of the '<em><b>Min date</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Min date</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * Definition: The 'min_date' attribute specifies a minimum date input. The format of the value must be conform to the 'input_formats' attribute of 'DateField'.
	 * 
	 * 
	 * Constraint/limit: The input date cannot be superior to the ‘max_date’. If a 'min_date' has been specified in the form model, the DateField is mandatory.
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>Min date</em>' attribute.
	 * @see #setMin_date(Date)
	 * @see com.bluexml.side.form.FormPackage#getDateField_Min_date()
	 * @model
	 * @generated
	 */
	Date getMin_date();

	/**
	 * Sets the value of the '{@link com.bluexml.side.form.DateField#getMin_date <em>Min date</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Min date</em>' attribute.
	 * @see #getMin_date()
	 * @generated
	 */
	void setMin_date(Date value);

	/**
	 * Returns the value of the '<em><b>Max date</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Max date</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * Definition: The 'max_date' attribute specifies a maximum date input. The format of the data must be conform to the 'input_formats' attribute of 'DateField'.
	 * 
	 * 
	 * 
	 * Constraint/limit: The input date cannot be inferior to the 'min_date'.
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>Max date</em>' attribute.
	 * @see #setMax_date(Date)
	 * @see com.bluexml.side.form.FormPackage#getDateField_Max_date()
	 * @model
	 * @generated
	 */
	Date getMax_date();

	/**
	 * Sets the value of the '{@link com.bluexml.side.form.DateField#getMax_date <em>Max date</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Max date</em>' attribute.
	 * @see #getMax_date()
	 * @generated
	 */
	void setMax_date(Date value);

} // DateField
