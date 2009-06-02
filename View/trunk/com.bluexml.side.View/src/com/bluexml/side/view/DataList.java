/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package com.bluexml.side.view;


/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Data List</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link com.bluexml.side.view.DataList#getCol <em>Col</em>}</li>
 * </ul>
 * </p>
 *
 * @see com.bluexml.side.view.ViewPackage#getDataList()
 * @model
 * @generated
 */
public interface DataList extends AbstractDataTable {
	/**
	 * Returns the value of the '<em><b>Col</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Col</em>' containment reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Col</em>' containment reference.
	 * @see #setCol(Col)
	 * @see com.bluexml.side.view.ViewPackage#getDataList_Col()
	 * @model containment="true" required="true"
	 * @generated
	 */
	Col getCol();

	/**
	 * Sets the value of the '{@link com.bluexml.side.view.DataList#getCol <em>Col</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Col</em>' containment reference.
	 * @see #getCol()
	 * @generated
	 */
	void setCol(Col value);
		
} // DataList
