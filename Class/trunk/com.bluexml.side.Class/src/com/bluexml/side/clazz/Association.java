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
 * @model annotation="http://www.bluexml.com/OCL recursiveAssociationMustHaveRole='( self.source = self.destination and self.isNavigableSRC and self.isNavigableTARGET ) implies ( ( not self.roleSrc.oclIsUndefined() and self.roleSrc <> \'\' ) or ( not self.roleTarget.oclIsUndefined() and self.roleTarget <> \'\' ))' AssociatioClassCantBeAgregationOrComposition='(self.associationType = AssociationType::Aggregation or self.associationType = AssociationType::Composition) implies self.associationsClass->isEmpty()' MinAndMaxTarget='( self.maxTARGET <> \'-1\' ) implies ( self.minTARGET <= self.maxTARGET )' MinAndMaxSource='( self.maxSRC <> \'-1\' ) implies ( self.minSRC <= self.maxSRC )' NameNull='not self.name.oclIsUndefined() and self.name <> \'\'' SourceNull='self.source->notEmpty()' TargetNull='self.destination->notEmpty()' AtLeastOneNavigableEdge='(isNavigableSRC or isNavigableTARGET)' ClassCantBeReferencedbyTwoSameNameAssociation='Association.allInstances()->select(a | a.name = self.name and  a.source = self.source  and a.destination = self.destination and a <> self and self.isNavigableSRC=a.isNavigableSRC and self.isNavigableSRC=true)->size() = 0\nand  \nAssociation.allInstances()->select(a | a.name = self.name and  a.source = self.source  and a.destination = self.destination and a <> self and self.isNavigableTARGET=a.isNavigableTARGET and self.isNavigableTARGET=true)->size() = 0\nand \nAssociation.allInstances()->select(a | a.name = self.name and  a.source = self.destination  and a.destination = self.source and a <> self and self.isNavigableSRC=a.isNavigableTARGET and self.isNavigableSRC=true)->size() = 0\nand \nAssociation.allInstances()->select(a | a.name = self.name and  a.source = self.destination  and a.destination = self.source and a <> self and self.isNavigableTARGET=a.isNavigableSRC and self.isNavigableTARGET=true)->size() = 0\n' IfAggregationOrCompositionThenUnidirectionalAssociation='(self.associationType <> AssociationType::Direct) implies (self.isNavigableSRC xor self.isNavigableTARGET )'"
 *        annotation="http://www.eclipse.org/emf/2002/Ecore constraints='recursiveAssociationMustHaveRole MinAndMaxTarget MinAndMaxSource AssociatioClassCantBeAgregationOrComposition NameNull SourceNull TargetNull AtLeastOneNavigableEdge ClassCantBeReferencedbyTwoSameNameAssociation IfAggregationOrCompositionThenUnidirectionalAssociation'"
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
	 * @model annotation="http://www.bluexml.com/OCL body='if( self.destination.oclIsKindOf(Classe))\r\nthen\r\nself.destination.oclAsType(Classe).equalsForMerge(other.destination.oclAsType(Classe)) and self.source.oclAsType(Classe).equalsForMerge(other.source.oclAsType(Classe))\r\nand self.name = other.name\r\nelse\r\ntrue\r\nendif\r\n'"
	 * @generated
	 */
	boolean equalsForMerge(Association other);

} // Association
