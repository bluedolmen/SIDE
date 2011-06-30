/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package com.bluexml.side.clazz;
 
import org.eclipse.emf.common.util.EList;

import com.bluexml.side.common.Container;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Abstract Class</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link com.bluexml.side.clazz.AbstractClass#getAttributes <em>Attributes</em>}</li>
 *   <li>{@link com.bluexml.side.clazz.AbstractClass#getGeneralizations <em>Generalizations</em>}</li>
 *   <li>{@link com.bluexml.side.clazz.AbstractClass#getAspects <em>Aspects</em>}</li>
 * </ul>
 * </p>
 *
 * @see com.bluexml.side.clazz.ClazzPackage#getAbstractClass()
 * @model abstract="true"
 *        annotation="http://www.bluexml.com/OCL TwoModelElementWithSameName='AbstractClass.allInstances()->select(a | a.name = self.name and a.getContainer() = self.getContainer() and a <> self)->size() = 0' NameNull='not self.name.oclIsUndefined() and self.name <> \'\'' noSpecialCharacters='self.name.regexMatch(\'[\\w]*\') = true' TwoAttributesSameName='self.attributes -> forAll( a1, a2 | a1 <> a2 implies a1.name <>a2.name)'"
 *        annotation="http://www.eclipse.org/emf/2002/Ecore constraints='TwoModelElementWithSameName NameNull noSpecialCharacters TwoAttributesSameName'"
 * @generated
 */
public interface AbstractClass extends TitledNamedClassModelElement, Container {
	/**
	 * Returns the value of the '<em><b>Attributes</b></em>' containment reference list.
	 * The list contents are of type {@link com.bluexml.side.clazz.Attribute}.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Attributes</em>' containment reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Attributes</em>' containment reference list.
	 * @see com.bluexml.side.clazz.ClazzPackage#getAbstractClass_Attributes()
	 * @model containment="true"
	 * @generated
	 */
	EList<Attribute> getAttributes();

	/**
	 * Returns the value of the '<em><b>Generalizations</b></em>' reference list.
	 * The list contents are of type {@link com.bluexml.side.clazz.AbstractClass}.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Generalizations</em>' reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Generalizations</em>' reference list.
	 * @see com.bluexml.side.clazz.ClazzPackage#getAbstractClass_Generalizations()
	 * @model
	 * @generated
	 */
	EList<AbstractClass> getGeneralizations();

	/**
	 * Returns the value of the '<em><b>Aspects</b></em>' reference list.
	 * The list contents are of type {@link com.bluexml.side.clazz.Aspect}.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Aspects</em>' reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Aspects</em>' reference list.
	 * @see com.bluexml.side.clazz.ClazzPackage#getAbstractClass_Aspects()
	 * @model
	 * @generated
	 */
	EList<Aspect> getAspects();

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @model annotation="http://www.bluexml.com/OCL body='self.name = other.name and self.title = other.title'"
	 * @generated
	 */
	boolean equalsForMerge(AbstractClass other);

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @model kind="operation"
	 *        annotation="http://www.bluexml.com/OCL body='self.generalizations  ->  asSet()  -> iterate(e:AbstractClass;result :Set(AbstractClass)= Set{}| result -> including(e) -> union(e.getInheritedClasses()))'"
	 * @generated
	 */
	EList<AbstractClass> getInheritedClasses();

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @model kind="operation"
	 *        annotation="http://www.bluexml.com/OCL body='AbstractClass.allInstances() ->select(e:AbstractClass|e.getInheritedClasses() ->includes(self) )'"
	 * @generated
	 */
	EList<AbstractClass> getAllSubTypes();

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @model kind="operation"
	 *        annotation="http://www.bluexml.com/OCL body='Association.allInstances() ->select(c:Association|self.isSource(c))' description='search association where this clazz is source'"
	 * @generated
	 */
	EList<Association> getSourceAssociations();

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @model required="true" assoRequired="true"
	 *        annotation="http://www.bluexml.com/OCL body='(asso.firstEnd.linkedClass = self and asso.secondEnd.navigable) or (asso.secondEnd.linkedClass = self and asso.firstEnd.navigable)' description='search for class attributes, inherited one and finaly added to the class by aspect'"
	 * @generated
	 */
	boolean isSource(Association asso);

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @model required="true" assoRequired="true"
	 *        annotation="http://www.bluexml.com/OCL body='(asso.firstEnd.linkedClass = self and asso.firstEnd.navigable) or (asso.secondEnd.linkedClass = self and asso.secondEnd.navigable)' description='search for class attributes, inherited one and finaly added to the class by aspect'"
	 * @generated
	 */
	boolean isTarget(Association asso);

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @model kind="operation"
	 *        annotation="http://www.bluexml.com/OCL body='Association.allInstances() ->select(c:Association|self.isTarget(c))' description='search association where this clazz is target'"
	 * @generated
	 */
	EList<Association> getTargetAssociations();

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @model kind="operation"
	 *        annotation="http://www.bluexml.com/OCL body='AssociationEnd.allInstances() -> select (ae | ae.getOpposite().navigable and ae.linkedClass = self )' description='returns association ends where this clazz is source'"
	 * @generated
	 */
	EList<AssociationEnd> getSourceAssociationEnds();

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @model kind="operation"
	 *        annotation="http://www.bluexml.com/OCL body='AssociationEnd.allInstances() -> select (ae | ae.navigable and ae.linkedClass = self)' description='returns association ends where this clazz is target'"
	 * @generated
	 */
	EList<AssociationEnd> getTargetAssociationEnds();

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @model kind="operation"
	 *        annotation="http://www.bluexml.com/OCL body='AbstractClass.allInstances() ->select(e:AbstractClass|e.generalizations() ->includes(self) )'"
	 * @generated
	 */
	EList<Clazz> getSubTypes();

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @model kind="operation"
	 *        annotation="http://www.bluexml.com/OCL body='getAllLinkedAbstractClass().getSourceAssociations()' description='search association where this AbstractClass is source'"
	 * @generated
	 */
	EList<Association> getAllSourceAssociations();

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @model kind="operation"
	 *        annotation="http://www.bluexml.com/OCL body='getAllLinkedAbstractClass().getTargetAssociations()' description='search associations where this AbstractClass is source or one of inheritedClass'"
	 * @generated
	 */
	EList<Association> getAllTargetAssociations();

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @model kind="operation"
	 *        annotation="http://www.bluexml.com/OCL body='self.getAllSourceAssociations().getTarget() ->asOrderedSet()'"
	 * @generated
	 */
	EList<AbstractClass> getAssociatedClasses();

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @model kind="operation"
	 *        annotation="http://www.bluexml.com/OCL body='AssociationEnd.allInstances() -> select (ae | ae.linkedClass.oclIsTypeOf(AbstractClass) and ae.getOpposite().navigable and self.getInheritedClasses() -> including (self) ->includes(ae.linkedClass.oclAsType(AbstractClass)) )' description='returns association ends where this clazz is source including inherited associations (association ends cannot link to aspects)'"
	 * @generated
	 */
	EList<AssociationEnd> getAllSourceAssociationEnds();

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @model kind="operation"
	 *        annotation="http://www.bluexml.com/OCL body='AssociationEnd.allInstances() -> select (ae | ae.navigable and self.getInheritedClasses() -> including (self) ->includes(ae.linkedClass) )' description='returns association ends where this clazz is target including inherited associations (association ends cannot link to aspects)'"
	 * @generated
	 */
	EList<AssociationEnd> getAllTargetAssociationEnds();

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @model kind="operation"
	 *        annotation="http://www.bluexml.com/OCL body='getAllLinkedAbstractClass().attributes' description='search for class attributes, inherited one and finaly added to the class by aspect'"
	 * @generated
	 */
	EList<Attribute> getAllAttributes();

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @model kind="operation"
	 *        annotation="http://www.bluexml.com/OCL body='self.attributes -> asSet() -> union(self.getAspectAttributes())'"
	 * @generated
	 */
	EList<Attribute> getClassAndAspectAttributes();

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @model kind="operation"
	 *        annotation="http://www.bluexml.com/OCL body='self.aspects ->  asSet()  -> iterate(e:Aspect;result :Set(Attribute)= Set{}| result -> union(e.getAllAttributes() ->asSet()))'"
	 * @generated
	 */
	EList<Attribute> getAspectAttributes();

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @model kind="operation"
	 *        annotation="http://www.bluexml.com/OCL body='self.getInheritedClasses() ->asSet().oclAsType(Clazz) ->iterate(cl:Clazz;result:Set(Attribute)=Set{}|result->union(cl.getClassAndAspectAttributes() ->asSet()))' description='search attributes than is describe in inherited classes (with Aspects)'"
	 * @generated
	 */
	EList<Attribute> getAllInheritedClassAndAspectAttributes();

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @model kind="operation"
	 *        annotation="http://www.bluexml.com/OCL body='self.getInheritedClasses().aspects'"
	 * @generated
	 */
	EList<Aspect> getAllInheritedAspects();

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @model kind="operation"
	 *        annotation="http://www.bluexml.com/OCL body='getAllLinkedAbstractClass() -> select(x | x.oclIsTypeOf(Aspect))'"
	 * @generated
	 */
	EList<Aspect> getAllLinkedAspects();

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @model kind="operation"
	 *        annotation="http://www.bluexml.com/OCL body='let selfAndInherited : Set(AbstractClass) = self.getInheritedClasses() -> including(self)\n\t\tin selfAndInherited -> union(selfAndInherited.aspects.getAllLinkedAbstractClass())'"
	 * @generated
	 */
	EList<AbstractClass> getAllLinkedAbstractClass();

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @model kind="operation"
	 *        annotation="http://www.bluexml.com/OCL body='getAllLinkedAbstractClass() -> select(x | x.oclIsTypeOf(Clazz))'"
	 * @generated
	 */
	EList<Clazz> getAllLinkedClasses();
		
} // AbstractClass
