/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package com.bluexml.side.application;

import org.eclipse.emf.common.util.EList;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Configuration</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link com.bluexml.side.application.Configuration#getElements <em>Elements</em>}</li>
 * </ul>
 * </p>
 *
 * @see com.bluexml.side.application.ApplicationPackage#getConfiguration()
 * @model
 * @generated
 */
public interface Configuration extends ModelElement {
	/**
	 * Returns the value of the '<em><b>Elements</b></em>' containment reference list.
	 * The list contents are of type {@link com.bluexml.side.application.ConfigurationElement}.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Elements</em>' containment reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Elements</em>' containment reference list.
	 * @see com.bluexml.side.application.ApplicationPackage#getConfiguration_Elements()
	 * @model containment="true"
	 * @generated
	 */
	EList<ConfigurationElement> getElements();

} // Configuration
