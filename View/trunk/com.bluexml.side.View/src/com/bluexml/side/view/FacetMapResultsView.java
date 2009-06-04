/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package com.bluexml.side.view;


/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Facet Map Results View</b></em>'.
 * <!-- end-user-doc -->
 *
 * <!-- begin-model-doc -->
 * this view is used to displays FacetResults,
 * nb : 
 * <!-- end-model-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link com.bluexml.side.view.FacetMapResultsView#getFacetMapDef <em>Facet Map Def</em>}</li>
 * </ul>
 * </p>
 *
 * @see com.bluexml.side.view.ViewPackage#getFacetMapResultsView()
 * @model
 * @generated
 */
public interface FacetMapResultsView extends DataList {
	/**
	 * Returns the value of the '<em><b>Facet Map Def</b></em>' container reference.
	 * It is bidirectional and its opposite is '{@link com.bluexml.side.view.FacetMap#getResultsViewer <em>Results Viewer</em>}'.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Facet Map Def</em>' container reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Facet Map Def</em>' container reference.
	 * @see #setFacetMapDef(FacetMap)
	 * @see com.bluexml.side.view.ViewPackage#getFacetMapResultsView_FacetMapDef()
	 * @see com.bluexml.side.view.FacetMap#getResultsViewer
	 * @model opposite="resultsViewer" required="true" transient="false"
	 * @generated
	 */
	FacetMap getFacetMapDef();

	/**
	 * Sets the value of the '{@link com.bluexml.side.view.FacetMapResultsView#getFacetMapDef <em>Facet Map Def</em>}' container reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Facet Map Def</em>' container reference.
	 * @see #getFacetMapDef()
	 * @generated
	 */
	void setFacetMapDef(FacetMap value);
		
} // FacetMapResultsView
