/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package com.bluexml.side.clazz;


/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Aspect</b></em>'.
 * <!-- end-user-doc -->
 *
 *
 * @see com.bluexml.side.clazz.ClazzPackage#getAspect()
 * @model annotation="http://www.bluexml.com/OCL AspectWithTwoAttributesSameName='self.attributes -> forAll( a1, a2 | a1 <> a2 implies a1.name <>a2.name)'"
 *        annotation="http://www.eclipse.org/emf/2002/Ecore constraints='AspectWithTwoAttributesSameName'"
 * @generated
 */
public interface Aspect extends AbstractClass {

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @model annotation="http://www.bluexml.com/OCL body='self.name = other.name and self.title = other.title'"
	 * @generated
	 */
	boolean equalsForMerge(Aspect other);
} // Aspect
