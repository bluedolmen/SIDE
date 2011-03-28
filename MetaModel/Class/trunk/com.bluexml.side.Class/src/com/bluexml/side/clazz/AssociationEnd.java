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
 * <!-- begin-model-doc -->
 * Definition: one of the two sides of an association; it references a class/data.
 * <!-- end-model-doc -->
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
 * @model annotation="http://www.bluexml.com/OCL noSpecialCharacters='self.name.regexMatch(\'[\\w\\(\\),]*\') = true'"
 *        annotation="http://www.eclipse.org/emf/2002/Ecore constraints='noSpecialChracters'"
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
	 * <!-- begin-model-doc -->
	 * Definition: the minimum number (minimum cardinality) of instances of the targeted class which participate to the association.
	 * Example: let's consider a relation between a resume content and a working contract. The resume end association having a minimum cardinality of 0 means that a working contract may not be related to a resume. A cardinality of 1 indicates that at least one resume must be associated to a working contract.
	 * Constraints: the number must be equals or superior to 0.
	 * <!-- end-model-doc -->
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
	 * <!-- begin-model-doc -->
	 * Definition: the maximum number (maximum cardinality) of instances of the targeted class which participate to the association.
	 * Example: let's consider a relation between a resume content and a working contract. The resume end association having a maximum cardinality of 1 means that a working contract is related to at most one resume. A cardinality of * indicates that as many resume as you want may be associated to a working contract.
	 * Constraints: the number must be superior to 0.
	 * <!-- end-model-doc -->
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
	 * <!-- begin-model-doc -->
	 * Definition: if false, this boolean indicates that the association is not navigable ie it is not possible to pass through the association.
	 * Example: let's consider a relation between a resume content and a working contract which is not navigable on the resume end association and navigable on the working contract. This means that the working contract instances will not store resume instances list (it is not possible from a working contract to list the associated resume) and that the resume instances will store working contract instances list (it is possible from a resume to list the associated working contracts).
	 * <!-- end-model-doc -->
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
	 * @see #setLinkedClass(AbstractClass)
	 * @see com.bluexml.side.clazz.ClazzPackage#getAssociationEnd_LinkedClass()
	 * @model
	 * @generated
	 */
	AbstractClass getLinkedClass();

	/**
	 * Sets the value of the '{@link com.bluexml.side.clazz.AssociationEnd#getLinkedClass <em>Linked Class</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Linked Class</em>' reference.
	 * @see #getLinkedClass()
	 * @generated
	 */
	void setLinkedClass(AbstractClass value);
	
	//Add to be conformed with previous versions
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
