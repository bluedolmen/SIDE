/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package com.bluexml.side.requirements.generator.metamodel.Documentation;


/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Informal Table Value</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link com.bluexml.side.requirements.generator.metamodel.Documentation.InformalTableValue#getTgroup <em>Tgroup</em>}</li>
 * </ul>
 * </p>
 *
 * @see com.bluexml.side.requirements.generator.metamodel.Documentation.DocumentationPackage#getInformalTableValue()
 * @model
 * @generated
 */
public interface InformalTableValue extends ParagraphValue {
	/**
	 * Returns the value of the '<em><b>Tgroup</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Tgroup</em>' containment reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Tgroup</em>' containment reference.
	 * @see #setTgroup(InformalTableValueGroup)
	 * @see com.bluexml.side.requirements.generator.metamodel.Documentation.DocumentationPackage#getInformalTableValue_Tgroup()
	 * @model containment="true" required="true"
	 * @generated
	 */
	InformalTableValueGroup getTgroup();

	/**
	 * Sets the value of the '{@link com.bluexml.side.requirements.generator.metamodel.Documentation.InformalTableValue#getTgroup <em>Tgroup</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Tgroup</em>' containment reference.
	 * @see #getTgroup()
	 * @generated
	 */
	void setTgroup(InformalTableValueGroup value);

} // InformalTableValue
