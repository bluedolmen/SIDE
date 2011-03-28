/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package com.bluexml.side.clazz;

import org.eclipse.emf.common.util.EList;

import com.bluexml.side.common.OperationComponent;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Clazz</b></em>'.
 * <!-- end-user-doc -->
 *
 * <!-- begin-model-doc -->
 * Definition: a class or data defines a category of instance of objects which shares exactly the same set of attributes. In ECM applications, a class relates to a content type.  SIDE Data model describes data structure for persistence through class and attributes and keeps notation close to UML class diagram to reduce learning curve.
 * Constraints: the fully qualified name of a class is compose of the successive names of the containing package and the attribute name of the class. This fully qualified name must be unique in your application. For instance, 'org.bluexml.library.mediaVideo' identifies the class mediaVideo contains in the 'library' package, itself contains in the 'bluexml' package, itself contains in the 'org' package.
 * Example: in an Alfresco application the predefined 'cm:content' is modelized as a class.
 * <!-- end-model-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link com.bluexml.side.clazz.Clazz#getOperations <em>Operations</em>}</li>
 *   <li>{@link com.bluexml.side.clazz.Clazz#getAspects <em>Aspects</em>}</li>
 *   <li>{@link com.bluexml.side.clazz.Clazz#isAbstract <em>Abstract</em>}</li>
 *   <li>{@link com.bluexml.side.clazz.Clazz#isDeprecated <em>Deprecated</em>}</li>
 * </ul>
 * </p>
 *
 * @see com.bluexml.side.clazz.ClazzPackage#getClazz()
 * @model annotation="http://www.bluexml.com/OCL InheritanceCycle='not self.generalizations ->closure(generalizations)->includes(self)'"
 *        annotation="http://www.eclipse.org/emf/2002/Ecore constraints='ClassWithTwoAttributesSameName InheritanceCycle'"
 * @generated
 */
public interface Clazz extends AbstractClass {
	/**
	 * Returns the value of the '<em><b>Operations</b></em>' containment reference list.
	 * The list contents are of type {@link com.bluexml.side.common.OperationComponent}.
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
	EList<OperationComponent> getOperations();

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
	 * Returns the value of the '<em><b>Abstract</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Abstract</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * Definition: this attribute indicates if the class is abstract; an abstract class cannot be instanciated but serve to define an object of more general level. For example, in the library, an abstract class 'media' may be defined to group all the kind of medias; the class 'mediaVideo' inherits of media to specialise 'media' as the media on video support.
	 * Constraint: during modeling, think to class and abstract class as real life objects, class being a specialisation of abstract class like the class 'novel' inherits of the abstracts class 'book'.
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>Abstract</em>' attribute.
	 * @see #setAbstract(boolean)
	 * @see com.bluexml.side.clazz.ClazzPackage#getClazz_Abstract()
	 * @model
	 * @generated
	 */
	boolean isAbstract();

	/**
	 * Sets the value of the '{@link com.bluexml.side.clazz.Clazz#isAbstract <em>Abstract</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Abstract</em>' attribute.
	 * @see #isAbstract()
	 * @generated
	 */
	void setAbstract(boolean value);

	/**
	 * Returns the value of the '<em><b>Deprecated</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Deprecated</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * Definition: set this attribute to true if the class is no more used in new version of the model but is only kept to ensure compatibility with previous models.
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>Deprecated</em>' attribute.
	 * @see #setDeprecated(boolean)
	 * @see com.bluexml.side.clazz.ClazzPackage#getClazz_Deprecated()
	 * @model
	 * @generated
	 */
	boolean isDeprecated();

	/**
	 * Sets the value of the '{@link com.bluexml.side.clazz.Clazz#isDeprecated <em>Deprecated</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Deprecated</em>' attribute.
	 * @see #isDeprecated()
	 * @generated
	 */
	void setDeprecated(boolean value);

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
	 *        annotation="http://www.bluexml.com/OCL body='self.attributes -> asSet() -> union(self.getAspectAttributes())'"
	 * @generated
	 */
	EList<Attribute> getClassAndAspectAttributes();

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @model kind="operation"
	 *        annotation="http://www.bluexml.com/OCL body='self.aspects ->  asSet()  -> iterate(e:Aspect;result :Set(Attribute)= Set{}| result -> union(e.getAllAttributesWithoutAspectsAttributes() ->asSet()))'"
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
	 *        annotation="http://www.bluexml.com/OCL body='self.getInheritedClasses()->asSet().oclAsType(Clazz).aspects'"
	 * @generated
	 */
	EList<Aspect> getAllInheritedAspects();

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @model kind="operation"
	 *        annotation="http://www.bluexml.com/OCL body='self.getHasAspects() -> union(self.getAllInheritedAspects())'"
	 * @generated
	 */
	EList<Aspect> getAllAspects();

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @model kind="operation"
	 *        annotation="http://www.bluexml.com/OCL body='self.aspects -> asSet() -> union(self.aspects.getInheritedClasses().oclAsType(Aspect) -> asSet()) ->asOrderedSet()'"
	 * @generated
	 */
	EList<Aspect> getHasAspects();

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @model kind="operation"
	 *        annotation="http://www.bluexml.com/OCL body='self.getInheritedClasses() -> including(self) -> union(self.getAllAspects()) ->iterate(e:AbstractClass;result:Set(Association)=Set{}|result->union(e.getSourceAssociations()))' description='search association where this AbstractClass is source'"
	 * @generated
	 */
	EList<Association> getAllSourceAssociationsIncludingAspect();

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @model kind="operation"
	 *        annotation="http://www.bluexml.com/OCL body='self.getInheritedClasses() -> including(self) -> union(self.getAllAspects()) ->iterate(e:AbstractClass;result:Set(Association)=Set{}|result->union(e.getTargetAssociations()))' description='search associations where this AbstractClass is source or one of inheritedClass'"
	 * @generated
	 */
	EList<Association> getAllTargetAssociationsIncludingAspect();
		
} // Clazz
