/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package com.bluexml.side.view;

import com.bluexml.side.common.ModelElement;
import com.bluexml.side.common.NamedModelElement;
import com.bluexml.side.common.OperationComponent;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Abstract View</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link com.bluexml.side.view.AbstractView#getViewOf <em>View Of</em>}</li>
 *   <li>{@link com.bluexml.side.view.AbstractView#getOperations <em>Operations</em>}</li>
 *   <li>{@link com.bluexml.side.view.AbstractView#getInnerView <em>Inner View</em>}</li>
 * </ul>
 * </p>
 *
 * @see com.bluexml.side.view.ViewPackage#getAbstractView()
 * @model abstract="true"
 * @generated
 */
public interface AbstractView extends NamedModelElement, Stylable {
	/**
	 * Returns the value of the '<em><b>View Of</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>View Of</em>' reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>View Of</em>' reference.
	 * @see #setViewOf(ModelElement)
	 * @see com.bluexml.side.view.ViewPackage#getAbstractView_ViewOf()
	 * @model
	 * @generated
	 */
	ModelElement getViewOf();

	/**
	 * Sets the value of the '{@link com.bluexml.side.view.AbstractView#getViewOf <em>View Of</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>View Of</em>' reference.
	 * @see #getViewOf()
	 * @generated
	 */
	void setViewOf(ModelElement value);

	/**
	 * Returns the value of the '<em><b>Operations</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Operations</em>' containment reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Operations</em>' containment reference.
	 * @see #setOperations(OperationComponent)
	 * @see com.bluexml.side.view.ViewPackage#getAbstractView_Operations()
	 * @model containment="true"
	 * @generated
	 */
	OperationComponent getOperations();

	/**
	 * Sets the value of the '{@link com.bluexml.side.view.AbstractView#getOperations <em>Operations</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Operations</em>' containment reference.
	 * @see #getOperations()
	 * @generated
	 */
	void setOperations(OperationComponent value);

	/**
	 * Returns the value of the '<em><b>Inner View</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Inner View</em>' containment reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Inner View</em>' containment reference.
	 * @see #setInnerView(AbstractView)
	 * @see com.bluexml.side.view.ViewPackage#getAbstractView_InnerView()
	 * @model containment="true"
	 * @generated
	 */
	AbstractView getInnerView();

	/**
	 * Sets the value of the '{@link com.bluexml.side.view.AbstractView#getInnerView <em>Inner View</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Inner View</em>' containment reference.
	 * @see #getInnerView()
	 * @generated
	 */
	void setInnerView(AbstractView value);
		
} // AbstractView
