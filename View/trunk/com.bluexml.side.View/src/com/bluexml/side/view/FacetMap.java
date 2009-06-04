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
 *   <li>{@link com.bluexml.side.view.FacetMap#isDisplayEmptyFacet <em>Display Empty Facet</em>}</li>
 *   <li>{@link com.bluexml.side.view.FacetMap#getFacetDisplayType <em>Facet Display Type</em>}</li>
 *   <li>{@link com.bluexml.side.view.FacetMap#getResultsViewer <em>Results Viewer</em>}</li>
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
	 * @see #setCriterias(FieldGroup)
	 * @see com.bluexml.side.view.ViewPackage#getFacetMap_Criterias()
	 * @model containment="true" required="true"
	 * @generated
	 */
	FieldGroup getCriterias();

	/**
	 * Sets the value of the '{@link com.bluexml.side.view.FacetMap#getCriterias <em>Criterias</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Criterias</em>' containment reference.
	 * @see #getCriterias()
	 * @generated
	 */
	void setCriterias(FieldGroup value);

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
	 * Returns the value of the '<em><b>Results Viewer</b></em>' containment reference.
	 * It is bidirectional and its opposite is '{@link com.bluexml.side.view.FacetMapResultsView#getFacetMapDef <em>Facet Map Def</em>}'.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Results Viewer</em>' containment reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Results Viewer</em>' containment reference.
	 * @see #setResultsViewer(FacetMapResultsView)
	 * @see com.bluexml.side.view.ViewPackage#getFacetMap_ResultsViewer()
	 * @see com.bluexml.side.view.FacetMapResultsView#getFacetMapDef
	 * @model opposite="facetMapDef" containment="true"
	 * @generated
	 */
	FacetMapResultsView getResultsViewer();

	/**
	 * Sets the value of the '{@link com.bluexml.side.view.FacetMap#getResultsViewer <em>Results Viewer</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Results Viewer</em>' containment reference.
	 * @see #getResultsViewer()
	 * @generated
	 */
	void setResultsViewer(FacetMapResultsView value);
		
} // FacetMap
