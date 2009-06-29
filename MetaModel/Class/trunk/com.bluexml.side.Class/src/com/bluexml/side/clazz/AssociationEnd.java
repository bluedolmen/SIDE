/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package com.bluexml.side.clazz;

import com.bluexml.side.common.Comment;

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
 *   <li>{@link com.bluexml.side.clazz.AssociationEnd#isNavigable <em>Navigable</em>}</li>
 *   <li>{@link com.bluexml.side.clazz.AssociationEnd#getLinkedClass <em>Linked Class</em>}</li>
 * </ul>
 * </p>
 *
 * @see com.bluexml.side.clazz.ClazzPackage#getAssociationEnd()
 * @model
 * @generated
 */
public interface AssociationEnd extends TitledNamedClassModelElement, Comment {
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
	 * Returns the value of the '<em><b>Navigable</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Navigable</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Navigable</em>' attribute.
	 * @see #setNavigable(boolean)
	 * @see com.bluexml.side.clazz.ClazzPackage#getAssociationEnd_Navigable()
	 * @model
	 * @generated
	 */
	boolean isNavigable();

	/**
	 * Sets the value of the '{@link com.bluexml.side.clazz.AssociationEnd#isNavigable <em>Navigable</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Navigable</em>' attribute.
	 * @see #isNavigable()
	 * @generated
	 */
	void setNavigable(boolean value);

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

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @model kind="operation"
	 *        annotation="http://www.bluexml.com/OCL body='cardMin.toInteger() > 0' description='returns true if the association end is mandatory'"
	 * @generated
	 */
	boolean isMandatory();

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @model kind="operation"
	 *        annotation="http://www.bluexml.com/OCL body='(cardMax.toInteger() > 1) or (cardMax.toInteger() = -1)' description='returns true if the association end has a multiple cardinality'"
	 * @generated
	 */
	boolean isMany();

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @model kind="operation"
	 *        annotation="http://www.bluexml.com/OCL body='let parent : Association = Association.allInstances() -> select(a | a.firstEnd = self or a.secondEnd = self) -> asSequence() -> first() in if (parent.firstEnd = self) then parent.secondEnd else parent.firstEnd endif' description='returns the other side of the containing association'"
	 * @generated
	 */
	AssociationEnd getOpposite();
		
} // AssociationEnd
