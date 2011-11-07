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
 * <!-- begin-model-doc -->
 * Definition: an aspect allows to group attributes in order to isolate a specific characteristic of a data; an aspect may be associated to many different class or content type. To link an aspect to a data type, use the 'Has aspect' link.
 * Example: if you want to have an automatic revision number for all the content types you defined, you can create an aspect 'Revision' which contains an attribute 'revisionNumber'. In your data modeling, you apply the aspect 'Revision' to all the content types.
 * Constraints: the fully qualified name of an aspect is compose of the successive names of the containing package and the attribute name of the aspect. This fully qualified name must be unique in your application. For instance, 'org.bluexml.library.revision' identifies the aspect Revision contains in the 'library' package, itself contains in the 'bluexml' package, itself contains in the 'org' package.
 * <!-- end-model-doc -->
 *
 *
 * @see com.bluexml.side.clazz.ClazzPackage#getAspect()
 * @model
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
