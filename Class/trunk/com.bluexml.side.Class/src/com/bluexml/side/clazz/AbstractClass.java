/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package com.bluexml.side.clazz;



/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Abstract Class</b></em>'.
 * <!-- end-user-doc -->
 *
 *
 * @see com.bluexml.side.clazz.ClazzPackage#getAbstractClass()
 * @model abstract="true"
 * @generated
 */
public interface AbstractClass extends AbstractContainer {

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @model annotation="http://www.bluexml.com/OCL body='self.name = other.name and self.title = other.title'"
	 * @generated
	 */
	boolean equalsForMerge(AbstractClass other);
} // AbstractClass
