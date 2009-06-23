/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package com.bluexml.side.requirements;

import org.eclipse.emf.ecore.EObject;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Privilege</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link com.bluexml.side.requirements.Privilege#getElement <em>Element</em>}</li>
 *   <li>{@link com.bluexml.side.requirements.Privilege#getCategory <em>Category</em>}</li>
 * </ul>
 * </p>
 *
 * @see com.bluexml.side.requirements.RequirementsPackage#getPrivilege()
 * @model
 * @generated
 */
public interface Privilege extends EObject {
	/**
	 * Returns the value of the '<em><b>Element</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Element</em>' reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Element</em>' reference.
	 * @see #setElement(BasicElement)
	 * @see com.bluexml.side.requirements.RequirementsPackage#getPrivilege_Element()
	 * @model required="true"
	 * @generated
	 */
	BasicElement getElement();

	/**
	 * Sets the value of the '{@link com.bluexml.side.requirements.Privilege#getElement <em>Element</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Element</em>' reference.
	 * @see #getElement()
	 * @generated
	 */
	void setElement(BasicElement value);

	/**
	 * Returns the value of the '<em><b>Category</b></em>' attribute.
	 * The literals are from the enumeration {@link com.bluexml.side.requirements.PrivilegeNature}.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Category</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Category</em>' attribute.
	 * @see com.bluexml.side.requirements.PrivilegeNature
	 * @see #setCategory(PrivilegeNature)
	 * @see com.bluexml.side.requirements.RequirementsPackage#getPrivilege_Category()
	 * @model
	 * @generated
	 */
	PrivilegeNature getCategory();

	/**
	 * Sets the value of the '{@link com.bluexml.side.requirements.Privilege#getCategory <em>Category</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Category</em>' attribute.
	 * @see com.bluexml.side.requirements.PrivilegeNature
	 * @see #getCategory()
	 * @generated
	 */
	void setCategory(PrivilegeNature value);

} // Privilege
