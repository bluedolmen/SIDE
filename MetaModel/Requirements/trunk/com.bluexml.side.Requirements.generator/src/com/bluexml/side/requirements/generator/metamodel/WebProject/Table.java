/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package com.bluexml.side.requirements.generator.metamodel.WebProject;

import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EObject;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Table</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link com.bluexml.side.requirements.generator.metamodel.WebProject.Table#getName <em>Name</em>}</li>
 *   <li>{@link com.bluexml.side.requirements.generator.metamodel.WebProject.Table#getFields <em>Fields</em>}</li>
 *   <li>{@link com.bluexml.side.requirements.generator.metamodel.WebProject.Table#getPrimaryKey <em>Primary Key</em>}</li>
 * </ul>
 * </p>
 *
 * @see com.bluexml.side.requirements.generator.metamodel.WebProject.WebProjectPackage#getTable()
 * @model
 * @generated
 */
public interface Table extends EObject {
	/**
	 * Returns the value of the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Name</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Name</em>' attribute.
	 * @see #setName(String)
	 * @see com.bluexml.side.requirements.generator.metamodel.WebProject.WebProjectPackage#getTable_Name()
	 * @model
	 * @generated
	 */
	String getName();

	/**
	 * Sets the value of the '{@link com.bluexml.side.requirements.generator.metamodel.WebProject.Table#getName <em>Name</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Name</em>' attribute.
	 * @see #getName()
	 * @generated
	 */
	void setName(String value);

	/**
	 * Returns the value of the '<em><b>Fields</b></em>' containment reference list.
	 * The list contents are of type {@link com.bluexml.side.requirements.generator.metamodel.WebProject.Field}.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Fields</em>' containment reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Fields</em>' containment reference list.
	 * @see com.bluexml.side.requirements.generator.metamodel.WebProject.WebProjectPackage#getTable_Fields()
	 * @model containment="true"
	 * @generated
	 */
	EList<Field> getFields();

	/**
	 * Returns the value of the '<em><b>Primary Key</b></em>' reference list.
	 * The list contents are of type {@link com.bluexml.side.requirements.generator.metamodel.WebProject.Field}.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Primary Key</em>' reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Primary Key</em>' reference list.
	 * @see com.bluexml.side.requirements.generator.metamodel.WebProject.WebProjectPackage#getTable_PrimaryKey()
	 * @model
	 * @generated
	 */
	EList<Field> getPrimaryKey();

} // Table
