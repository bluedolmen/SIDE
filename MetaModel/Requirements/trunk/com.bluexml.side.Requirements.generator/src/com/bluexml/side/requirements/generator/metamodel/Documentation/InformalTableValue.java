/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package com.bluexml.side.requirements.generator.metamodel.Documentation;

import org.eclipse.emf.common.util.EList;


/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Informal Table Value</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link com.bluexml.side.requirements.generator.metamodel.Documentation.InformalTableValue#getCols <em>Cols</em>}</li>
 *   <li>{@link com.bluexml.side.requirements.generator.metamodel.Documentation.InformalTableValue#getBodyRows <em>Body Rows</em>}</li>
 *   <li>{@link com.bluexml.side.requirements.generator.metamodel.Documentation.InformalTableValue#getHeadRows <em>Head Rows</em>}</li>
 * </ul>
 * </p>
 *
 * @see com.bluexml.side.requirements.generator.metamodel.Documentation.DocumentationPackage#getInformalTableValue()
 * @model
 * @generated
 */
public interface InformalTableValue extends ParagraphValue {
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
	 * @see com.bluexml.side.requirements.generator.metamodel.Documentation.DocumentationPackage#getInformalTableValue_Cols()
	 * @model
	 * @generated
	 */
	int getCols();

	/**
	 * Sets the value of the '{@link com.bluexml.side.requirements.generator.metamodel.Documentation.InformalTableValue#getCols <em>Cols</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Cols</em>' attribute.
	 * @see #getCols()
	 * @generated
	 */
	void setCols(int value);

	/**
	 * Returns the value of the '<em><b>Body Rows</b></em>' containment reference list.
	 * The list contents are of type {@link com.bluexml.side.requirements.generator.metamodel.Documentation.InformalTableValueRow}.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Body Rows</em>' containment reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Body Rows</em>' containment reference list.
	 * @see com.bluexml.side.requirements.generator.metamodel.Documentation.DocumentationPackage#getInformalTableValue_BodyRows()
	 * @model containment="true"
	 * @generated
	 */
	EList<InformalTableValueRow> getBodyRows();

	/**
	 * Returns the value of the '<em><b>Head Rows</b></em>' containment reference list.
	 * The list contents are of type {@link com.bluexml.side.requirements.generator.metamodel.Documentation.InformalTableValueRow}.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Head Rows</em>' containment reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Head Rows</em>' containment reference list.
	 * @see com.bluexml.side.requirements.generator.metamodel.Documentation.DocumentationPackage#getInformalTableValue_HeadRows()
	 * @model containment="true"
	 * @generated
	 */
	EList<InformalTableValueRow> getHeadRows();

} // InformalTableValue
