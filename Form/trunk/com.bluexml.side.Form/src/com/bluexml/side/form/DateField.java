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
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link com.bluexml.side.form.DateField#getInput_formats <em>Input formats</em>}</li>
 *   <li>{@link com.bluexml.side.form.DateField#getMin_date <em>Min date</em>}</li>
 *   <li>{@link com.bluexml.side.form.DateField#getMax_date <em>Max date</em>}</li>
 * </ul>
 * </p>
 *
 * @see com.bluexml.side.form.formPackage#getDateField()
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
	 * @return the value of the '<em>Input formats</em>' attribute list.
	 * @see com.bluexml.side.form.formPackage#getDateField_Input_formats()
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
	 * @return the value of the '<em>Min date</em>' attribute.
	 * @see #setMin_date(Date)
	 * @see com.bluexml.side.form.formPackage#getDateField_Min_date()
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
	 * @return the value of the '<em>Max date</em>' attribute.
	 * @see #setMax_date(Date)
	 * @see com.bluexml.side.form.formPackage#getDateField_Max_date()
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
