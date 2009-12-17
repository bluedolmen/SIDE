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
 * <!-- begin-model-doc -->
 * Definition: Association allows to link data sharing particular relationships; for instance, an association may be created between a resume content and a working contract content.
 * <!-- end-model-doc -->
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
 * @model annotation="http://www.bluexml.com/OCL reflexiveAssociationMustHaveRole='if (self.firstEnd.oclIsTypeOf(Clazz) and self.secondEnd.oclIsTypeOf(Clazz)) then\n( self.isReflexive() and self.firstEnd.navigable and self.secondEnd.navigable ) implies ( ( not self.firstEnd.name.oclIsUndefined() and self.firstEnd.name <> \'\' ) and ( not self.secondEnd.name.oclIsUndefined() and self.secondEnd.name <> \'\' ))\nelse\ntrue\nendif' MinAndMaxTarget='( self.secondEnd.cardMax <> \'-1\' ) implies ( self.secondEnd.cardMin <= self.secondEnd.cardMax )' MinAndMaxSource='( self.firstEnd.cardMax <> \'-1\' ) implies ( self.firstEnd.cardMin <= self.firstEnd.cardMax )' NameNull='not self.name.oclIsUndefined() and self.name <> \'\'' SourceNull='self.firstEnd.linkedClass->notEmpty()' TargetNull='self.secondEnd.linkedClass->notEmpty()' AtLeastOneNavigableEdge='(firstEnd.navigable or secondEnd.navigable)' ClassCantBeReferencedbyTwoSameNameAssociation='self.getSource().getAllSourceAssociations() ->asSet() ->select(a:Association|a.name = self.name)->size() = 1' IfAggregationOrCompositionThenUnidirectionalAssociation='(self.associationType <> AssociationType::Direct) implies (self.firstEnd.navigable xor self.secondEnd.navigable )' twoWayNavigation='(self.firstEnd.navigable and self.secondEnd.navigable) implies (self.firstEnd.name <> \'\' and self.secondEnd.name <> \'\')' noSpecialCharacters='self.name.regexMatch(\'[\\w]*\') = true'"
 *        annotation="http://www.eclipse.org/emf/2002/Ecore constraints='reflexiveAssociationMustHaveRole MinAndMaxTarget MinAndMaxSource NameNull SourceNull TargetNull AtLeastOneNavigableEdge ClassCantBeReferencedbyTwoSameNameAssociation IfAggregationOrCompositionThenUnidirectionalAssociation doubleNavigable noSpecialChracters' warning='twoWayNavigation'"
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
	 * <!-- begin-model-doc -->
	 * Definition: an association may be of various type according to the relationship which must be established between two types of data:
	 * - Simple: this indicates that there are links between associated data instanes.
	 * - Aggregation: this indicates a stronger relation between data types which is usually a relation of inclusion; for instance, an association between a wiki and a page.
	 * - Composition: this indicates a composite aggregation and that if a container data type instance is suppressed, the associated contained data type instances must also be suppressed; for instance, if a wiki is suppressed, all its pages must be suppressed.
	 * <!-- end-model-doc -->
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
	 * @see #setFirstEnd(AssociationEnd)
	 * @see com.bluexml.side.clazz.ClazzPackage#getAssociation_FirstEnd()
	 * @model containment="true"
	 * @generated
	 */
	AssociationEnd getFirstEnd();

	/**
	 * Sets the value of the '{@link com.bluexml.side.clazz.Association#getFirstEnd <em>First End</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>First End</em>' containment reference.
	 * @see #getFirstEnd()
	 * @generated
	 */
	void setFirstEnd(AssociationEnd value);

	/**
	 * Returns the value of the '<em><b>Second End</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Second End</em>' containment reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Second End</em>' containment reference.
	 * @see #setSecondEnd(AssociationEnd)
	 * @see com.bluexml.side.clazz.ClazzPackage#getAssociation_SecondEnd()
	 * @model containment="true"
	 * @generated
	 */
	AssociationEnd getSecondEnd();

	/**
	 * Sets the value of the '{@link com.bluexml.side.clazz.Association#getSecondEnd <em>Second End</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Second End</em>' containment reference.
	 * @see #getSecondEnd()
	 * @generated
	 */
	void setSecondEnd(AssociationEnd value);

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
	 *        annotation="http://www.bluexml.com/OCL body='(self.firstEnd.linkedClass.getInheritedClasses() ->including(self.firstEnd.linkedClass) ->includes(self.secondEnd.linkedClass) and self.secondEnd.navigable)\ror \r(self.secondEnd.linkedClass.getInheritedClasses() ->including(self.secondEnd.linkedClass) ->includes(self.firstEnd.linkedClass) and self.firstEnd.navigable)\r'"
	 * @generated
	 */
	boolean isReflexive();

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @model kind="operation" required="true" upper="2"
	 *        annotation="http://www.bluexml.com/OCL body='if (self.firstEnd.navigable and self.secondEnd.navigable) then \r\tSet{} ->including(self.firstEnd.linkedClass) ->including(self.secondEnd.linkedClass)\relse if (self.firstEnd.navigable) then\r\t\tSet{}->including(self.secondEnd.linkedClass)\r\telse if (self.secondEnd.navigable) then \r\t\t\tSet{}->including(self.firstEnd.linkedClass)\r\t\telse\r\t\t\tSet{}\r\t\tendif\r\tendif\rendif' description='get source Clazz'"
	 * @generated
	 */
	EList<Clazz> getSource();

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @model kind="operation" required="true" upper="2"
	 *        annotation="http://www.bluexml.com/OCL body='if (self.firstEnd.navigable and self.secondEnd.navigable) then \r\tSet{} ->including(self.firstEnd.linkedClass) ->including(self.secondEnd.linkedClass)\relse if (self.secondEnd.navigable) then\r\t\tSet{}->including(self.secondEnd.linkedClass)\r\telse if (self.firstEnd.navigable) then \r\t\t\tSet{}->including(self.firstEnd.linkedClass)\r\t\telse\r\t\t\tSet{}\r\t\tendif\r\tendif\rendif' description='get source Clazz'"
	 * @generated
	 */
	EList<Clazz> getTarget();

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @model upper="2"
	 *        annotation="http://www.bluexml.com/OCL body='Sequence{self.firstEnd,self.secondEnd} -> select(ae | ae.linkedClass = clazz)\n' description='returns the association end for which the parameter class (clazz) is linked to'"
	 * @generated
	 */
	EList<AssociationEnd> getAssociationEnd(Clazz clazz);
		
} // Association
