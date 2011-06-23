/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package com.bluexml.side.common;

import org.eclipse.emf.common.util.EList;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Package</b></em>'.
 * <!-- end-user-doc -->
 *
 * <!-- begin-model-doc -->
 * Definition: used to organize the objects inside a model under a tree similar to Java package organization. It is highly recommended to use the packages to organize the elements and to ensure that they have understandable fully qualified name.
 * <!-- end-model-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link com.bluexml.side.common.Package#getStereotypeSet <em>Stereotype Set</em>}</li>
 *   <li>{@link com.bluexml.side.common.Package#getPackageSet <em>Package Set</em>}</li>
 *   <li>{@link com.bluexml.side.common.Package#getNamespaceSet <em>Namespace Set</em>}</li>
 * </ul>
 * </p>
 *
 * @see com.bluexml.side.common.CommonPackage#getPackage()
 * @model
 * @generated
 */
public interface Package extends NamedModelElement {
	/**
	 * Returns the value of the '<em><b>Stereotype Set</b></em>' containment reference list.
	 * The list contents are of type {@link com.bluexml.side.common.Stereotype}.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Stereotype Set</em>' containment reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Stereotype Set</em>' containment reference list.
	 * @see com.bluexml.side.common.CommonPackage#getPackage_StereotypeSet()
	 * @model containment="true"
	 * @generated
	 */
	EList<Stereotype> getStereotypeSet();

	/**
	 * Returns the value of the '<em><b>Package Set</b></em>' containment reference list.
	 * The list contents are of type {@link com.bluexml.side.common.Package}.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Package Set</em>' containment reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Package Set</em>' containment reference list.
	 * @see com.bluexml.side.common.CommonPackage#getPackage_PackageSet()
	 * @model containment="true"
	 * @generated
	 */
	EList<Package> getPackageSet();

	/**
	 * Returns the value of the '<em><b>Namespace Set</b></em>' containment reference list.
	 * The list contents are of type {@link com.bluexml.side.common.NameSpace}.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Namespace Set</em>' containment reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Namespace Set</em>' containment reference list.
	 * @see com.bluexml.side.common.CommonPackage#getPackage_NamespaceSet()
	 * @model containment="true"
	 * @generated
	 */
	EList<NameSpace> getNamespaceSet();

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @model annotation="http://www.bluexml.com/OCL body='self.name = other.name'"
	 * @generated
	 */
	boolean equalsForMerge(Package other);

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @model kind="operation"
	 *        annotation="http://www.bluexml.com/OCL body='if self.getContainer().oclIsUndefined() then\r\tself\relse\r\tself.getContainer().oclAsType(Package).getRootPackage()\rendif'"
	 * @generated
	 */
	Package getRootPackage();

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @model kind="operation"
	 *        annotation="http://www.bluexml.com/OCL body='common::NameSpace.allInstances()'"
	 * @generated
	 */
	EList<NameSpace> getAllNamespaces();

} // Package
