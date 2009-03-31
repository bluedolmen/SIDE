/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package com.bluexml.side.KerblueForms;


/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Virtual Field</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link com.bluexml.side.KerblueForms.VirtualField#getLink <em>Link</em>}</li>
 * </ul>
 * </p>
 *
 * @see com.bluexml.side.KerblueForms.formPackage#getVirtualField()
 * @model annotation="http://www.eclipse.org/emf/2002/Ecore constraints='NoLinkForVirtualField'"
 *        annotation="http://www.bluexml.com/OCL NoLinkForVirtualField='not self.link.oclIsUndefined()'"
 * @generated
 */
public interface VirtualField extends Field {
	/**
	 * Returns the value of the '<em><b>Link</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Link</em>' reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Link</em>' reference.
	 * @see #setLink(Field)
	 * @see com.bluexml.side.KerblueForms.formPackage#getVirtualField_Link()
	 * @model
	 * @generated
	 */
	Field getLink();

	/**
	 * Sets the value of the '{@link com.bluexml.side.KerblueForms.VirtualField#getLink <em>Link</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Link</em>' reference.
	 * @see #getLink()
	 * @generated
	 */
	void setLink(Field value);
		
} // VirtualField
