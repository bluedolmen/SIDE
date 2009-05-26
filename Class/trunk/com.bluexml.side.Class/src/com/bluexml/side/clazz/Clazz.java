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
 * A representation of the model object '<em><b>Clazz</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link com.bluexml.side.clazz.Clazz#getOperations <em>Operations</em>}</li>
 *   <li>{@link com.bluexml.side.clazz.Clazz#getGeneralizations <em>Generalizations</em>}</li>
 *   <li>{@link com.bluexml.side.clazz.Clazz#getAspects <em>Aspects</em>}</li>
 *   <li>{@link com.bluexml.side.clazz.Clazz#isIsAbstract <em>Is Abstract</em>}</li>
 *   <li>{@link com.bluexml.side.clazz.Clazz#isIsDeprecated <em>Is Deprecated</em>}</li>
 *   <li>{@link com.bluexml.side.clazz.Clazz#getHasView <em>Has View</em>}</li>
 * </ul>
 * </p>
 *
 * @see com.bluexml.side.clazz.ClazzPackage#getClazz()
 * @model annotation="http://www.bluexml.com/OCL InheritanceCycle='not self.generalizations.generalizations -> includes(self)' ClassWithTwoAttributesSameName='self.attributes -> forAll( a1, a2 | a1 <> a2 implies a1.name <>a2.name)'"
 *        annotation="http://www.eclipse.org/emf/2002/Ecore constraints='ClassWithTwoAttributesSameName InheritanceCycle'"
 * @generated
 */
public interface Clazz extends AbstractClass {
	/**
	 * Returns the value of the '<em><b>Operations</b></em>' containment reference list.
	 * The list contents are of type {@link com.bluexml.side.clazz.Operation}.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Operations</em>' containment reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Operations</em>' containment reference list.
	 * @see com.bluexml.side.clazz.ClazzPackage#getClazz_Operations()
	 * @model containment="true"
	 * @generated
	 */
	EList<Operation> getOperations();

	/**
	 * Returns the value of the '<em><b>Generalizations</b></em>' reference list.
	 * The list contents are of type {@link com.bluexml.side.clazz.Clazz}.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Generalizations</em>' containment reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Generalizations</em>' reference list.
	 * @see com.bluexml.side.clazz.ClazzPackage#getClazz_Generalizations()
	 * @model
	 * @generated
	 */
	EList<Clazz> getGeneralizations();

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
	 * @see com.bluexml.side.clazz.ClazzPackage#getClazz_Aspects()
	 * @model
	 * @generated
	 */
	EList<Aspect> getAspects();

	/**
	 * Returns the value of the '<em><b>Is Abstract</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Is Abstract</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Is Abstract</em>' attribute.
	 * @see #setIsAbstract(boolean)
	 * @see com.bluexml.side.clazz.ClazzPackage#getClazz_IsAbstract()
	 * @model
	 * @generated
	 */
	boolean isIsAbstract();

	/**
	 * Sets the value of the '{@link com.bluexml.side.clazz.Clazz#isIsAbstract <em>Is Abstract</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Is Abstract</em>' attribute.
	 * @see #isIsAbstract()
	 * @generated
	 */
	void setIsAbstract(boolean value);

	/**
	 * Returns the value of the '<em><b>Is Deprecated</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Is Deprecated</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Is Deprecated</em>' attribute.
	 * @see #setIsDeprecated(boolean)
	 * @see com.bluexml.side.clazz.ClazzPackage#getClazz_IsDeprecated()
	 * @model
	 * @generated
	 */
	boolean isIsDeprecated();

	/**
	 * Sets the value of the '{@link com.bluexml.side.clazz.Clazz#isIsDeprecated <em>Is Deprecated</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Is Deprecated</em>' attribute.
	 * @see #isIsDeprecated()
	 * @generated
	 */
	void setIsDeprecated(boolean value);

	/**
	 * Returns the value of the '<em><b>Has View</b></em>' containment reference list.
	 * The list contents are of type {@link com.bluexml.side.clazz.View}.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Has View</em>' reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Has View</em>' containment reference list.
	 * @see com.bluexml.side.clazz.ClazzPackage#getClazz_HasView()
	 * @model containment="true"
	 * @generated
	 */
	EList<View> getHasView();

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @model kind="operation"
	 *        annotation="http://www.bluexml.com/OCL body='self.getAllInheritedClassAndAspectAttributes() -> union(self.getClassAndAspectAttributes())' description='search for class attributes, inherited one and finaly added to the class by aspect'"
	 * @generated
	 */
	EList<Attribute> getAllAttributes();

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @model kind="operation"
	 *        annotation="http://www.bluexml.com/OCL body='self.getInheritedClasses() ->asSet() ->iterate(cl:Clazz;result:Set(Attribute)=Set{}|result->union(cl.attributes) ->asSet()))' description='search attributes than is describe in inherited classes (without Aspects)'"
	 * @generated
	 */
	EList<Attribute> getAllInheritedAttributes();

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
	 *        annotation="http://www.bluexml.com/OCL body='self.generalizations  ->  asSet()  -> iterate(e:Clazz;result :Set(Clazz)= Set{}| result -> including(e) -> union(e.getInheritedClasses()))'"
	 * @generated
	 */
	EList<Clazz> getInheritedClasses();

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @model kind="operation"
	 *        annotation="http://www.bluexml.com/OCL body='self.aspects ->  asSet()  -> iterate(e:Aspect;result :Set(Attribute)= Set{}| result -> union(e.attributes ->asSet()))'"
	 * @generated
	 */
	EList<Attribute> getAspectAttributes();

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @model kind="operation"
	 *        annotation="http://www.bluexml.com/OCL body='self.getAllInheritedAttributes() -> union(self.getClassAndAspectAttributes())'"
	 * @generated
	 */
	EList<Attribute> getSubTypes();

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @model kind="operation"
	 *        annotation="http://www.bluexml.com/OCL body='self.getInheritedClasses() ->asSet() ->iterate(cl:Clazz;result:Set(Attribute)=Set{}|result->union(cl.getClassAndAspectAttributes() ->asSet()))' description='search attributes than is describe in inherited classes (with Aspects)'"
	 * @generated
	 */
	EList<Attribute> getAllInheritedClassAndAspectAttributes();

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
	 *        annotation="http://www.bluexml.com/OCL body='(asso.firstEnd.linkedClass = self and asso.firstEnd.isNavigable) or (asso.secondEnd.linkedClass = self and asso.secondEnd.isNavigable)' description='search for class attributes, inherited one and finaly added to the class by aspect'"
	 * @generated
	 */
	boolean isSource(Association asso);

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @model required="true" assoRequired="true"
	 *        annotation="http://www.bluexml.com/OCL body='(asso.firstEnd.linkedClass = self and asso.isNavigable) or (asso.secondEnd.linkedClass = self and asso.isNavigable)' description='search for class attributes, inherited one and finaly added to the class by aspect'"
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
	 *        annotation="http://www.bluexml.com/OCL body='self.getInheritedClasses() -> including(self) ->iterate(e:Clazz;result:Set(Association)=Set{}|result->union(e.getSourceAssociations()))' description='search association where this clazz is source'"
	 * @generated
	 */
	EList<Association> getAllSourceAssociations();

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @model kind="operation"
	 *        annotation="http://www.bluexml.com/OCL body='self.getInheritedClasses() -> including(self) ->iterate(e:Clazz;result:Set(Association)=Set{}|result->union(e.getTargetAssociations()))' description='search associations where this clazz is source or one of inheritedClass'"
	 * @generated
	 */
	EList<Association> getAllTargetAssociations();

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @model kind="operation"
	 *        annotation="http://www.bluexml.com/OCL body='Association.allInstances() ->select(e:Association| self.getInheritedClasses() ->including(self) -> includesAll(e.associationsClass))' description='give the list of associations where this clazz is the associatedClazz'"
	 * @generated
	 */
	EList<Association> isClassAssociationsIn();

} // Clazz
