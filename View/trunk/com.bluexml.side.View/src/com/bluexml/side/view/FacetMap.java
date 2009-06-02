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
 *   <li>{@link com.bluexml.side.view.FacetMap#getCriterias <em>Criterias</em>}</li>
 * </ul>
 * </p>
 *
 * @see com.bluexml.side.view.ViewPackage#getFacetMap()
 * @model
 * @generated
 */
public interface FacetMap extends AbstractView, Paginable {
	/**
	 * Returns the value of the '<em><b>Criterias</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Criterias</em>' containment reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Criterias</em>' containment reference.
	 * @see #setCriterias(FieldElement)
	 * @see com.bluexml.side.view.ViewPackage#getFacetMap_Criterias()
	 * @model containment="true" required="true"
	 * @generated
	 */
	FieldElement getCriterias();

	/**
	 * Sets the value of the '{@link com.bluexml.side.view.FacetMap#getCriterias <em>Criterias</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Criterias</em>' containment reference.
	 * @see #getCriterias()
	 * @generated
	 */
	void setCriterias(FieldElement value);
		
} // FacetMap
