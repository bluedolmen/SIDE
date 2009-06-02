/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package com.bluexml.side.view;

import org.eclipse.emf.ecore.EObject;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Paging</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link com.bluexml.side.view.Paging#getPaginationStyle <em>Pagination Style</em>}</li>
 *   <li>{@link com.bluexml.side.view.Paging#getMaxItems <em>Max Items</em>}</li>
 *   <li>{@link com.bluexml.side.view.Paging#getMaxPage <em>Max Page</em>}</li>
 * </ul>
 * </p>
 *
 * @see com.bluexml.side.view.ViewPackage#getPaging()
 * @model
 * @generated
 */
public interface Paging extends EObject {
	/**
	 * Returns the value of the '<em><b>Pagination Style</b></em>' attribute.
	 * The literals are from the enumeration {@link com.bluexml.side.view.PaginationStyle}.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Pagination Style</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Pagination Style</em>' attribute.
	 * @see com.bluexml.side.view.PaginationStyle
	 * @see #setPaginationStyle(PaginationStyle)
	 * @see com.bluexml.side.view.ViewPackage#getPaging_PaginationStyle()
	 * @model required="true"
	 * @generated
	 */
	PaginationStyle getPaginationStyle();

	/**
	 * Sets the value of the '{@link com.bluexml.side.view.Paging#getPaginationStyle <em>Pagination Style</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Pagination Style</em>' attribute.
	 * @see com.bluexml.side.view.PaginationStyle
	 * @see #getPaginationStyle()
	 * @generated
	 */
	void setPaginationStyle(PaginationStyle value);

	/**
	 * Returns the value of the '<em><b>Max Items</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * use -1 for unlimited
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>Max Items</em>' attribute.
	 * @see #setMaxItems(int)
	 * @see com.bluexml.side.view.ViewPackage#getPaging_MaxItems()
	 * @model
	 * @generated
	 */
	int getMaxItems();

	/**
	 * Sets the value of the '{@link com.bluexml.side.view.Paging#getMaxItems <em>Max Items</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Max Items</em>' attribute.
	 * @see #getMaxItems()
	 * @generated
	 */
	void setMaxItems(int value);

	/**
	 * Returns the value of the '<em><b>Max Page</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Max Page</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Max Page</em>' attribute.
	 * @see #setMaxPage(int)
	 * @see com.bluexml.side.view.ViewPackage#getPaging_MaxPage()
	 * @model
	 * @generated
	 */
	int getMaxPage();

	/**
	 * Sets the value of the '{@link com.bluexml.side.view.Paging#getMaxPage <em>Max Page</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Max Page</em>' attribute.
	 * @see #getMaxPage()
	 * @generated
	 */
	void setMaxPage(int value);
		
} // Paging
