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
 * A representation of the model object '<em><b>Collection</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link com.bluexml.side.view.ViewCollection#getViews <em>Views</em>}</li>
 *   <li>{@link com.bluexml.side.view.ViewCollection#getComposedViews <em>Composed Views</em>}</li>
 * </ul>
 * </p>
 *
 * @see com.bluexml.side.view.ViewPackage#getViewCollection()
 * @model
 * @generated
 */
public interface ViewCollection extends com.bluexml.side.common.Package {
	/**
	 * Returns the value of the '<em><b>Views</b></em>' containment reference list.
	 * The list contents are of type {@link com.bluexml.side.view.AbstractView}.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Views</em>' containment reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Views</em>' containment reference list.
	 * @see com.bluexml.side.view.ViewPackage#getViewCollection_Views()
	 * @model containment="true"
	 * @generated
	 */
	EList<AbstractView> getViews();

	/**
	 * Returns the value of the '<em><b>Composed Views</b></em>' containment reference list.
	 * The list contents are of type {@link com.bluexml.side.view.ComposedView}.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Composed Views</em>' reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Composed Views</em>' containment reference list.
	 * @see com.bluexml.side.view.ViewPackage#getViewCollection_ComposedViews()
	 * @model containment="true"
	 * @generated
	 */
	EList<ComposedView> getComposedViews();

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @model kind="operation"
	 *        annotation="http://www.bluexml.com/OCL description='method to get all instances of AbstractView' body='AbstractView.allInstances()'"
	 * @generated
	 */
	EList<AbstractView> getAllViews();
		
} // ViewCollection
