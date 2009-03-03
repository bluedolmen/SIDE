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
 * A representation of the model object '<em><b>View Item</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link com.bluexml.side.clazz.ViewItem#getAssociation <em>Association</em>}</li>
 *   <li>{@link com.bluexml.side.clazz.ViewItem#getAttribute <em>Attribute</em>}</li>
 *   <li>{@link com.bluexml.side.clazz.ViewItem#getClasse <em>Classe</em>}</li>
 *   <li>{@link com.bluexml.side.clazz.ViewItem#getAspect <em>Aspect</em>}</li>
 * </ul>
 * </p>
 *
 * @see com.bluexml.side.clazz.ClazzPackage#getViewItem()
 * @model
 * @generated
 */
public interface ViewItem extends EObject {
	/**
	 * Returns the value of the '<em><b>Association</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Association</em>' reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Association</em>' reference.
	 * @see #setAssociation(Association)
	 * @see com.bluexml.side.clazz.ClazzPackage#getViewItem_Association()
	 * @model
	 * @generated
	 */
	Association getAssociation();

	/**
	 * Sets the value of the '{@link com.bluexml.side.clazz.ViewItem#getAssociation <em>Association</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Association</em>' reference.
	 * @see #getAssociation()
	 * @generated
	 */
	void setAssociation(Association value);

	/**
	 * Returns the value of the '<em><b>Attribute</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Attribute</em>' reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Attribute</em>' reference.
	 * @see #setAttribute(Attribute)
	 * @see com.bluexml.side.clazz.ClazzPackage#getViewItem_Attribute()
	 * @model
	 * @generated
	 */
	Attribute getAttribute();

	/**
	 * Sets the value of the '{@link com.bluexml.side.clazz.ViewItem#getAttribute <em>Attribute</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Attribute</em>' reference.
	 * @see #getAttribute()
	 * @generated
	 */
	void setAttribute(Attribute value);

	/**
	 * Returns the value of the '<em><b>Classe</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Classe</em>' reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Classe</em>' reference.
	 * @see #setClasse(Clazz)
	 * @see com.bluexml.side.clazz.ClazzPackage#getViewItem_Classe()
	 * @model
	 * @generated
	 */
	Clazz getClasse();

	/**
	 * Sets the value of the '{@link com.bluexml.side.clazz.ViewItem#getClasse <em>Classe</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Classe</em>' reference.
	 * @see #getClasse()
	 * @generated
	 */
	void setClasse(Clazz value);

	/**
	 * Returns the value of the '<em><b>Aspect</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Aspect</em>' reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Aspect</em>' reference.
	 * @see #setAspect(Aspect)
	 * @see com.bluexml.side.clazz.ClazzPackage#getViewItem_Aspect()
	 * @model
	 * @generated
	 */
	Aspect getAspect();

	/**
	 * Sets the value of the '{@link com.bluexml.side.clazz.ViewItem#getAspect <em>Aspect</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Aspect</em>' reference.
	 * @see #getAspect()
	 * @generated
	 */
	void setAspect(Aspect value);

} // ViewItem
