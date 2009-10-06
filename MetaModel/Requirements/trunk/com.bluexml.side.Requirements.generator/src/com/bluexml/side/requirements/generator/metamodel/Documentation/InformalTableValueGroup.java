/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package com.bluexml.side.requirements.generator.metamodel.Documentation;

import org.eclipse.emf.ecore.EObject;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Informal Table Value Group</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link com.bluexml.side.requirements.generator.metamodel.Documentation.InformalTableValueGroup#getCols <em>Cols</em>}</li>
 *   <li>{@link com.bluexml.side.requirements.generator.metamodel.Documentation.InformalTableValueGroup#getThead <em>Thead</em>}</li>
 *   <li>{@link com.bluexml.side.requirements.generator.metamodel.Documentation.InformalTableValueGroup#getTbody <em>Tbody</em>}</li>
 * </ul>
 * </p>
 *
 * @see com.bluexml.side.requirements.generator.metamodel.Documentation.DocumentationPackage#getInformalTableValueGroup()
 * @model
 * @generated
 */
public interface InformalTableValueGroup extends EObject {
	/**
	 * Returns the value of the '<em><b>Cols</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Cols</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Cols</em>' attribute.
	 * @see #setCols(int)
	 * @see com.bluexml.side.requirements.generator.metamodel.Documentation.DocumentationPackage#getInformalTableValueGroup_Cols()
	 * @model
	 * @generated
	 */
	int getCols();

	/**
	 * Sets the value of the '{@link com.bluexml.side.requirements.generator.metamodel.Documentation.InformalTableValueGroup#getCols <em>Cols</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Cols</em>' attribute.
	 * @see #getCols()
	 * @generated
	 */
	void setCols(int value);

	/**
	 * Returns the value of the '<em><b>Thead</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Thead</em>' containment reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Thead</em>' containment reference.
	 * @see #setThead(InformalTableValueHead)
	 * @see com.bluexml.side.requirements.generator.metamodel.Documentation.DocumentationPackage#getInformalTableValueGroup_Thead()
	 * @model containment="true"
	 * @generated
	 */
	InformalTableValueHead getThead();

	/**
	 * Sets the value of the '{@link com.bluexml.side.requirements.generator.metamodel.Documentation.InformalTableValueGroup#getThead <em>Thead</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Thead</em>' containment reference.
	 * @see #getThead()
	 * @generated
	 */
	void setThead(InformalTableValueHead value);

	/**
	 * Returns the value of the '<em><b>Tbody</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Tbody</em>' containment reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Tbody</em>' containment reference.
	 * @see #setTbody(InformalTableValueBody)
	 * @see com.bluexml.side.requirements.generator.metamodel.Documentation.DocumentationPackage#getInformalTableValueGroup_Tbody()
	 * @model containment="true"
	 * @generated
	 */
	InformalTableValueBody getTbody();

	/**
	 * Sets the value of the '{@link com.bluexml.side.requirements.generator.metamodel.Documentation.InformalTableValueGroup#getTbody <em>Tbody</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Tbody</em>' containment reference.
	 * @see #getTbody()
	 * @generated
	 */
	void setTbody(InformalTableValueBody value);

} // InformalTableValueGroup
