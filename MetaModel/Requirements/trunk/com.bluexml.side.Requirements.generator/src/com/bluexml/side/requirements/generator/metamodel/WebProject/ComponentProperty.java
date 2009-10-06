/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package com.bluexml.side.requirements.generator.metamodel.WebProject;

import org.eclipse.emf.ecore.EObject;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Component Property</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link com.bluexml.side.requirements.generator.metamodel.WebProject.ComponentProperty#getName <em>Name</em>}</li>
 *   <li>{@link com.bluexml.side.requirements.generator.metamodel.WebProject.ComponentProperty#isCanRead <em>Can Read</em>}</li>
 *   <li>{@link com.bluexml.side.requirements.generator.metamodel.WebProject.ComponentProperty#isCanUpdate <em>Can Update</em>}</li>
 * </ul>
 * </p>
 *
 * @see com.bluexml.side.requirements.generator.metamodel.WebProject.WebProjectPackage#getComponentProperty()
 * @model abstract="true"
 * @generated
 */
public interface ComponentProperty extends EObject {
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
	 * @see com.bluexml.side.requirements.generator.metamodel.WebProject.WebProjectPackage#getComponentProperty_Name()
	 * @model
	 * @generated
	 */
	String getName();

	/**
	 * Sets the value of the '{@link com.bluexml.side.requirements.generator.metamodel.WebProject.ComponentProperty#getName <em>Name</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Name</em>' attribute.
	 * @see #getName()
	 * @generated
	 */
	void setName(String value);

	/**
	 * Returns the value of the '<em><b>Can Read</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Can Read</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Can Read</em>' attribute.
	 * @see #setCanRead(boolean)
	 * @see com.bluexml.side.requirements.generator.metamodel.WebProject.WebProjectPackage#getComponentProperty_CanRead()
	 * @model
	 * @generated
	 */
	boolean isCanRead();

	/**
	 * Sets the value of the '{@link com.bluexml.side.requirements.generator.metamodel.WebProject.ComponentProperty#isCanRead <em>Can Read</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Can Read</em>' attribute.
	 * @see #isCanRead()
	 * @generated
	 */
	void setCanRead(boolean value);

	/**
	 * Returns the value of the '<em><b>Can Update</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Can Update</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Can Update</em>' attribute.
	 * @see #setCanUpdate(boolean)
	 * @see com.bluexml.side.requirements.generator.metamodel.WebProject.WebProjectPackage#getComponentProperty_CanUpdate()
	 * @model
	 * @generated
	 */
	boolean isCanUpdate();

	/**
	 * Sets the value of the '{@link com.bluexml.side.requirements.generator.metamodel.WebProject.ComponentProperty#isCanUpdate <em>Can Update</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Can Update</em>' attribute.
	 * @see #isCanUpdate()
	 * @generated
	 */
	void setCanUpdate(boolean value);

} // ComponentProperty
