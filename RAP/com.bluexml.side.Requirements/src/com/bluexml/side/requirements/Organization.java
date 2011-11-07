/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package com.bluexml.side.requirements;

import org.eclipse.emf.common.util.EList;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Organization</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link com.bluexml.side.requirements.Organization#getChildElements <em>Child Elements</em>}</li>
 * </ul>
 * </p>
 *
 * @see com.bluexml.side.requirements.RequirementsPackage#getOrganization()
 * @model
 * @generated
 */
public interface Organization extends AnnotableElement {
	/**
	 * Returns the value of the '<em><b>Child Elements</b></em>' containment reference list.
	 * The list contents are of type {@link com.bluexml.side.requirements.ModelElement}.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Child Elements</em>' containment reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Child Elements</em>' containment reference list.
	 * @see com.bluexml.side.requirements.RequirementsPackage#getOrganization_ChildElements()
	 * @model containment="true"
	 * @generated
	 */
	EList<ModelElement> getChildElements();

} // Organization
