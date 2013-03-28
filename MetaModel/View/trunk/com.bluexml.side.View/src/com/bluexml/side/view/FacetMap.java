/*
    Copyright (C) 2007-20013  BlueXML - www.bluexml.com

    This program is free software: you can redistribute it and/or modify
    it under the terms of the GNU Affero General Public License as
    published by the Free Software Foundation, either version 3 of the
    License, or (at your option) any later version.

    This program is distributed in the hope that it will be useful,
    but WITHOUT ANY WARRANTY; without even the implied warranty of
    MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
    GNU Affero General Public License for more details.

    You should have received a copy of the GNU Affero General Public License
    along with this program.  If not, see <http://www.gnu.org/licenses/>.
*/
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
public interface FacetMap extends AbstractViewOf, Paginable, Actionable {
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

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @model kind="operation"
	 *        annotation="http://www.bluexml.com/OCL body='self.children->select(oclIsKindOf(AbstractView))->asOrderedSet()->first().oclAsType(AbstractView).children->select(oclIsKindOf(FieldElement))' description='Get the first element of the abstract View in the Facetmap'"
	 * @generated
	 */
	EList<FieldElement> getResultsAttributes();
		
} // FacetMap
