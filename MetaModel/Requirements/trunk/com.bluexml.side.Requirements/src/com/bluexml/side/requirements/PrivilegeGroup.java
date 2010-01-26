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
 * A representation of the model object '<em><b>Privilege Group</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link com.bluexml.side.requirements.PrivilegeGroup#getEntryPoint <em>Entry Point</em>}</li>
 *   <li>{@link com.bluexml.side.requirements.PrivilegeGroup#getPrivileges <em>Privileges</em>}</li>
 *   <li>{@link com.bluexml.side.requirements.PrivilegeGroup#getDocumentation <em>Documentation</em>}</li>
 * </ul>
 * </p>
 *
 * @see com.bluexml.side.requirements.RequirementsPackage#getPrivilegeGroup()
 * @model
 * @generated
 */
public interface PrivilegeGroup extends ModelElement {
	/**
	 * Returns the value of the '<em><b>Entry Point</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Entry Point</em>' reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Entry Point</em>' reference.
	 * @see #setEntryPoint(Entity)
	 * @see com.bluexml.side.requirements.RequirementsPackage#getPrivilegeGroup_EntryPoint()
	 * @model required="true"
	 * @generated
	 */
	Entity getEntryPoint();

	/**
	 * Sets the value of the '{@link com.bluexml.side.requirements.PrivilegeGroup#getEntryPoint <em>Entry Point</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Entry Point</em>' reference.
	 * @see #getEntryPoint()
	 * @generated
	 */
	void setEntryPoint(Entity value);

	/**
	 * Returns the value of the '<em><b>Privileges</b></em>' containment reference list.
	 * The list contents are of type {@link com.bluexml.side.requirements.Privilege}.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Privileges</em>' containment reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Privileges</em>' containment reference list.
	 * @see com.bluexml.side.requirements.RequirementsPackage#getPrivilegeGroup_Privileges()
	 * @model containment="true"
	 * @generated
	 */
	EList<Privilege> getPrivileges();

	/**
	 * Returns the value of the '<em><b>Documentation</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Documentation</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Documentation</em>' attribute.
	 * @see #setDocumentation(String)
	 * @see com.bluexml.side.requirements.RequirementsPackage#getPrivilegeGroup_Documentation()
	 * @model
	 * @generated
	 */
	String getDocumentation();

	/**
	 * Sets the value of the '{@link com.bluexml.side.requirements.PrivilegeGroup#getDocumentation <em>Documentation</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Documentation</em>' attribute.
	 * @see #getDocumentation()
	 * @generated
	 */
	void setDocumentation(String value);

} // PrivilegeGroup
