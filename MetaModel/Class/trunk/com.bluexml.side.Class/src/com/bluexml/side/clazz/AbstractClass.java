/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package com.bluexml.side.clazz;
 
import com.bluexml.side.common.Container;

import org.eclipse.emf.common.util.EList;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Abstract Class</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link com.bluexml.side.clazz.AbstractClass#getAttributes <em>Attributes</em>}</li>
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
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @model annotation="http://www.bluexml.com/OCL body='self.name = other.name and self.title = other.title'"
	 * @generated
	 */
	boolean equalsForMerge(AbstractClass other);
		
} // AbstractClass
