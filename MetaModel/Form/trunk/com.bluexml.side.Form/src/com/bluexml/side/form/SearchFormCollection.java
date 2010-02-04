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
 * A representation of the model object '<em><b>Search Form Collection</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link com.bluexml.side.form.SearchFormCollection#getSearchForm <em>Search Form</em>}</li>
 * </ul>
 * </p>
 *
 * @see com.bluexml.side.form.FormPackage#getSearchFormCollection()
 * @model
 * @generated
 */
public interface SearchFormCollection extends FormCollection {
	/**
	 * Returns the value of the '<em><b>Search Form</b></em>' containment reference list.
	 * The list contents are of type {@link com.bluexml.side.form.SearchForm}.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Search Form</em>' containment reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Search Form</em>' containment reference list.
	 * @see com.bluexml.side.form.FormPackage#getSearchFormCollection_SearchForm()
	 * @model containment="true"
	 * @generated
	 */
	EList<SearchForm> getSearchForm();
		
} // SearchFormCollection
