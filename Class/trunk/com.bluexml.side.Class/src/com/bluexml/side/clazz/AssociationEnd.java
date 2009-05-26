/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package com.bluexml.side.clazz;

import org.eclipse.emf.ecore.EObject;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Association End</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link com.bluexml.side.clazz.AssociationEnd#getCardMin <em>Card Min</em>}</li>
 *   <li>{@link com.bluexml.side.clazz.AssociationEnd#getCardMax <em>Card Max</em>}</li>
 *   <li>{@link com.bluexml.side.clazz.AssociationEnd#isIsNavigable <em>Is Navigable</em>}</li>
 *   <li>{@link com.bluexml.side.clazz.AssociationEnd#getLinkedClass <em>Linked Class</em>}</li>
 * </ul>
 * </p>
 *
 * @see com.bluexml.side.clazz.ClazzPackage#getAssociationEnd()
 * @model abstract="true"
 * @generated
 */
public interface AssociationEnd extends TitledNamedClassModelElement {
	/**
	 * Returns the value of the '<em><b>Card Min</b></em>' attribute.
	 * The default value is <code>"0"</code>.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Card Min</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Card Min</em>' attribute.
	 * @see #setCardMin(String)
	 * @see com.bluexml.side.clazz.ClazzPackage#getAssociationEnd_CardMin()
	 * @model default="0"
	 * @generated
	 */
	String getCardMin();

	/**
	 * Sets the value of the '{@link com.bluexml.side.clazz.AssociationEnd#getCardMin <em>Card Min</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Card Min</em>' attribute.
	 * @see #getCardMin()
	 * @generated
	 */
	void setCardMin(String value);

	/**
	 * Returns the value of the '<em><b>Card Max</b></em>' attribute.
	 * The default value is <code>"1"</code>.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Card Max</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Card Max</em>' attribute.
	 * @see #setCardMax(String)
	 * @see com.bluexml.side.clazz.ClazzPackage#getAssociationEnd_CardMax()
	 * @model default="1"
	 * @generated
	 */
	String getCardMax();

	/**
	 * Sets the value of the '{@link com.bluexml.side.clazz.AssociationEnd#getCardMax <em>Card Max</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Card Max</em>' attribute.
	 * @see #getCardMax()
	 * @generated
	 */
	void setCardMax(String value);

	/**
	 * Returns the value of the '<em><b>Is Navigable</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Is Navigable</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Is Navigable</em>' attribute.
	 * @see #setIsNavigable(boolean)
	 * @see com.bluexml.side.clazz.ClazzPackage#getAssociationEnd_IsNavigable()
	 * @model
	 * @generated
	 */
	boolean isIsNavigable();

	/**
	 * Sets the value of the '{@link com.bluexml.side.clazz.AssociationEnd#isIsNavigable <em>Is Navigable</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Is Navigable</em>' attribute.
	 * @see #isIsNavigable()
	 * @generated
	 */
	void setIsNavigable(boolean value);

	/**
	 * Returns the value of the '<em><b>Linked Class</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Linked Class</em>' reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Linked Class</em>' reference.
	 * @see #setLinkedClass(Clazz)
	 * @see com.bluexml.side.clazz.ClazzPackage#getAssociationEnd_LinkedClass()
	 * @model
	 * @generated
	 */
	Clazz getLinkedClass();

	/**
	 * Sets the value of the '{@link com.bluexml.side.clazz.AssociationEnd#getLinkedClass <em>Linked Class</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Linked Class</em>' reference.
	 * @see #getLinkedClass()
	 * @generated
	 */
	void setLinkedClass(Clazz value);
		
} // AssociationEnd
