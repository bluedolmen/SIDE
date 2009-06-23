/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package com.bluexml.side.clazz;

import org.eclipse.emf.common.util.EList;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Association</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link com.bluexml.side.clazz.Association#getAssociationType <em>Association Type</em>}</li>
 *   <li>{@link com.bluexml.side.clazz.Association#getFirstEnd <em>First End</em>}</li>
 *   <li>{@link com.bluexml.side.clazz.Association#getSecondEnd <em>Second End</em>}</li>
 * </ul>
 * </p>
 *
 * @see com.bluexml.side.clazz.ClazzPackage#getAssociation()
 * @model annotation="http://www.bluexml.com/OCL recursiveAssociationMustHaveRole='( self.isRecursive() and self.firstEnd.isNavigable and self.secondEnd.isNavigable ) implies ( ( not self.firstEnd.name.oclIsUndefined() and self.firstEnd.name <> \'\' ) and ( not self.secondEnd.name.oclIsUndefined() and self.secondEnd.name <> \'\' ))' MinAndMaxTarget='( self.secondEnd.cardMax <> \'-1\' ) implies ( self.secondEnd.cardMin <= self.secondEnd.cardMax )' MinAndMaxSource='( self.firstEnd.cardMax <> \'-1\' ) implies ( self.firstEnd.cardMin <= self.firstEnd.cardMax )' NameNull='not self.name.oclIsUndefined() and self.name <> \'\'' SourceNull='self.firstEnd.linkedClass->notEmpty()' TargetNull='self.secondEnd.linkedClass->notEmpty()' AtLeastOneNavigableEdge='(firstEnd.isNavigable or secondEnd.isNavigable)' ClassCantBeReferencedbyTwoSameNameAssociation='self.getSource().getAllSourceAssociations() ->asSet() ->select(a:Association|a.name = self.name)->size() = 1' IfAggregationOrCompositionThenUnidirectionalAssociation='(self.associationType <> AssociationType::Direct) implies (self.firstEnd.isNavigable xor self.secondEnd.isNavigable )'"
 *        annotation="http://www.eclipse.org/emf/2002/Ecore constraints='recursiveAssociationMustHaveRole MinAndMaxTarget MinAndMaxSource NameNull SourceNull TargetNull AtLeastOneNavigableEdge ClassCantBeReferencedbyTwoSameNameAssociation IfAggregationOrCompositionThenUnidirectionalAssociation'"
 * @generated
 */
public interface Association extends TitledNamedClassModelElement {
	/**
	 * Returns the value of the '<em><b>Association Type</b></em>' attribute.
	 * The literals are from the enumeration {@link com.bluexml.side.clazz.AssociationType}.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Association Type</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Association Type</em>' attribute.
	 * @see com.bluexml.side.clazz.AssociationType
	 * @see #setAssociationType(AssociationType)
	 * @see com.bluexml.side.clazz.ClazzPackage#getAssociation_AssociationType()
	 * @model
	 * @generated
	 */
	AssociationType getAssociationType();

	/**
	 * Sets the value of the '{@link com.bluexml.side.clazz.Association#getAssociationType <em>Association Type</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Association Type</em>' attribute.
	 * @see com.bluexml.side.clazz.AssociationType
	 * @see #getAssociationType()
	 * @generated
	 */
	void setAssociationType(AssociationType value);

	/**
	 * Returns the value of the '<em><b>First End</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>First End</em>' containment reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>First End</em>' containment reference.
	 * @see #setFirstEnd(FirstEnd)
	 * @see com.bluexml.side.clazz.ClazzPackage#getAssociation_FirstEnd()
	 * @model containment="true"
	 * @generated
	 */
	FirstEnd getFirstEnd();

	/**
	 * Sets the value of the '{@link com.bluexml.side.clazz.Association#getFirstEnd <em>First End</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>First End</em>' containment reference.
	 * @see #getFirstEnd()
	 * @generated
	 */
	void setFirstEnd(FirstEnd value);

	/**
	 * Returns the value of the '<em><b>Second End</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Second End</em>' containment reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Second End</em>' containment reference.
	 * @see #setSecondEnd(SecondEnd)
	 * @see com.bluexml.side.clazz.ClazzPackage#getAssociation_SecondEnd()
	 * @model containment="true"
	 * @generated
	 */
	SecondEnd getSecondEnd();

	/**
	 * Sets the value of the '{@link com.bluexml.side.clazz.Association#getSecondEnd <em>Second End</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Second End</em>' containment reference.
	 * @see #getSecondEnd()
	 * @generated
	 */
	void setSecondEnd(SecondEnd value);

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @model annotation="http://www.bluexml.com/OCL body='if( self.secondEnd.linkedClass.oclIsKindOf(Classe))\r\nthen\r\nself.secondEnd.linkedClass.oclAsType(Classe).equalsForMerge(other.secondEnd.linkedClass.oclAsType(Classe)) and self.firstEnd.linkedClass.oclAsType(Classe).equalsForMerge(other.firstEnd.linkedClass.oclAsType(Classe))\r\nand self.name = other.name\r\nelse\r\ntrue\r\nendif\r\n'"
	 * @generated
	 */
	boolean equalsForMerge(Association other);

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @model kind="operation"
	 *        annotation="http://www.bluexml.com/OCL body='(self.firstEnd.linkedClass.getInheritedClasses() ->including(self.firstEnd.linkedClass) ->includes(self.secondEnd.linkedClass) and self.secondEnd.isNavigable)\ror \r(self.secondEnd.linkedClass.getInheritedClasses() ->including(self.secondEnd.linkedClass) ->includes(self.firstEnd.linkedClass) and self.firstEnd.isNavigable)\r'"
	 * @generated
	 */
	boolean isRecursive();

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @model kind="operation" required="true" upper="2"
	 *        annotation="http://www.bluexml.com/OCL body='if (self.firstEnd.isNavigable and self.secondEnd.isNavigable) then \r\tSet{} ->including(self.firstEnd.linkedClass) ->including(self.secondEnd.linkedClass)\relse if (self.firstEnd.isNavigable) then\r\t\tSet{}->including(self.secondEnd.linkedClass)\r\telse if (self.secondEnd.isNavigable) then \r\t\t\tSet{}->including(self.firstEnd.linkedClass)\r\t\telse\r\t\t\tSet{}\r\t\tendif\r\tendif\rendif' description='get source Clazz'"
	 * @generated
	 */
	EList<Clazz> getSource();

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @model kind="operation" required="true" upper="2"
	 *        annotation="http://www.bluexml.com/OCL body='if (self.firstEnd.isNavigable and self.secondEnd.isNavigable) then \r\tSet{} ->including(self.firstEnd.linkedClass) ->including(self.secondEnd.linkedClass)\relse if (self.secondEnd.isNavigable) then\r\t\tSet{}->including(self.secondEnd.linkedClass)\r\telse if (self.firstEnd.isNavigable) then \r\t\t\tSet{}->including(self.firstEnd.linkedClass)\r\t\telse\r\t\t\tSet{}\r\t\tendif\r\tendif\rendif' description='get source Clazz'"
	 * @generated
	 */
	EList<Clazz> getTarget();

} // Association
