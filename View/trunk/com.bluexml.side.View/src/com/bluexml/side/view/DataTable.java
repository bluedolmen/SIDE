/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package com.bluexml.side.view;

import org.eclipse.emf.common.util.EList;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Data Table</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link com.bluexml.side.view.DataTable#getDefaultColSetUp <em>Default Col Set Up</em>}</li>
 *   <li>{@link com.bluexml.side.view.DataTable#getCols <em>Cols</em>}</li>
 * </ul>
 * </p>
 *
 * @see com.bluexml.side.view.ViewPackage#getDataTable()
 * @model
 * @generated
 */
public interface DataTable extends AbstractDataTable {
	/**
	 * Returns the value of the '<em><b>Default Col Set Up</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Default Col Set Up</em>' reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Default Col Set Up</em>' containment reference.
	 * @see #setDefaultColSetUp(Col)
	 * @see com.bluexml.side.view.ViewPackage#getDataTable_DefaultColSetUp()
	 * @model containment="true"
	 * @generated
	 */
	Col getDefaultColSetUp();

	/**
	 * Sets the value of the '{@link com.bluexml.side.view.DataTable#getDefaultColSetUp <em>Default Col Set Up</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Default Col Set Up</em>' containment reference.
	 * @see #getDefaultColSetUp()
	 * @generated
	 */
	void setDefaultColSetUp(Col value);

	/**
	 * Returns the value of the '<em><b>Cols</b></em>' containment reference list.
	 * The list contents are of type {@link com.bluexml.side.view.Col}.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Cols</em>' containment reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Cols</em>' containment reference list.
	 * @see com.bluexml.side.view.ViewPackage#getDataTable_Cols()
	 * @model containment="true"
	 * @generated
	 */
	EList<Col> getCols();
		
} // DataTable
