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
 * A representation of the model object '<em><b>File Search Field</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link com.bluexml.side.form.FileSearchField#getOperators <em>Operators</em>}</li>
 *   <li>{@link com.bluexml.side.form.FileSearchField#getDefaultOperator <em>Default Operator</em>}</li>
 * </ul>
 * </p>
 *
 * @see com.bluexml.side.form.FormPackage#getFileSearchField()
 * @model
 * @generated
 */
public interface FileSearchField extends SearchField {
	/**
	 * Returns the value of the '<em><b>Operators</b></em>' attribute list.
	 * The list contents are of type {@link com.bluexml.side.form.FileFieldSearchOperators}.
	 * The literals are from the enumeration {@link com.bluexml.side.form.FileFieldSearchOperators}.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Operators</em>' attribute list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Operators</em>' attribute list.
	 * @see com.bluexml.side.form.FileFieldSearchOperators
	 * @see com.bluexml.side.form.FormPackage#getFileSearchField_Operators()
	 * @model
	 * @generated
	 */
	EList<FileFieldSearchOperators> getOperators();

	/**
	 * Returns the value of the '<em><b>Default Operator</b></em>' attribute.
	 * The literals are from the enumeration {@link com.bluexml.side.form.FileFieldSearchOperators}.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Default Operator</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Default Operator</em>' attribute.
	 * @see com.bluexml.side.form.FileFieldSearchOperators
	 * @see #setDefaultOperator(FileFieldSearchOperators)
	 * @see com.bluexml.side.form.FormPackage#getFileSearchField_DefaultOperator()
	 * @model
	 * @generated
	 */
	FileFieldSearchOperators getDefaultOperator();

	/**
	 * Sets the value of the '{@link com.bluexml.side.form.FileSearchField#getDefaultOperator <em>Default Operator</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Default Operator</em>' attribute.
	 * @see com.bluexml.side.form.FileFieldSearchOperators
	 * @see #getDefaultOperator()
	 * @generated
	 */
	void setDefaultOperator(FileFieldSearchOperators value);
		
} // FileSearchField
