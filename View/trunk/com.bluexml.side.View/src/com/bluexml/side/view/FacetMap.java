/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package com.bluexml.side.view;


/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Facet Map</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link com.bluexml.side.view.FacetMap#isDisplayEmptyFacet <em>Display Empty Facet</em>}</li>
 *   <li>{@link com.bluexml.side.view.FacetMap#getFacetDisplayType <em>Facet Display Type</em>}</li>
 * </ul>
 * </p>
 *
 * @see com.bluexml.side.view.ViewPackage#getFacetMap()
 * @model
 * @generated
 */
public interface FacetMap extends AbstractView, Paginable {
	/**
	 * Returns the value of the '<em><b>Display Empty Facet</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Display Empty Facet</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Display Empty Facet</em>' attribute.
	 * @see #setDisplayEmptyFacet(boolean)
	 * @see com.bluexml.side.view.ViewPackage#getFacetMap_DisplayEmptyFacet()
	 * @model
	 * @generated
	 */
	boolean isDisplayEmptyFacet();

	/**
	 * Sets the value of the '{@link com.bluexml.side.view.FacetMap#isDisplayEmptyFacet <em>Display Empty Facet</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Display Empty Facet</em>' attribute.
	 * @see #isDisplayEmptyFacet()
	 * @generated
	 */
	void setDisplayEmptyFacet(boolean value);

	/**
	 * Returns the value of the '<em><b>Facet Display Type</b></em>' attribute.
	 * The literals are from the enumeration {@link com.bluexml.side.view.FacetDisplayType}.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Facet Display Type</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Facet Display Type</em>' attribute.
	 * @see com.bluexml.side.view.FacetDisplayType
	 * @see #setFacetDisplayType(FacetDisplayType)
	 * @see com.bluexml.side.view.ViewPackage#getFacetMap_FacetDisplayType()
	 * @model
	 * @generated
	 */
	FacetDisplayType getFacetDisplayType();

	/**
	 * Sets the value of the '{@link com.bluexml.side.view.FacetMap#getFacetDisplayType <em>Facet Display Type</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Facet Display Type</em>' attribute.
	 * @see com.bluexml.side.view.FacetDisplayType
	 * @see #getFacetDisplayType()
	 * @generated
	 */
	void setFacetDisplayType(FacetDisplayType value);
		
} // FacetMap
