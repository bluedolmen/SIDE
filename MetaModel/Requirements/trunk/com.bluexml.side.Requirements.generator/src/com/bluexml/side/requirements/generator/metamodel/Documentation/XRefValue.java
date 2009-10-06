/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package com.bluexml.side.requirements.generator.metamodel.Documentation;


/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>XRef Value</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link com.bluexml.side.requirements.generator.metamodel.Documentation.XRefValue#getLinkend <em>Linkend</em>}</li>
 * </ul>
 * </p>
 *
 * @see com.bluexml.side.requirements.generator.metamodel.Documentation.DocumentationPackage#getXRefValue()
 * @model
 * @generated
 */
public interface XRefValue extends ParagraphValue {
	/**
	 * Returns the value of the '<em><b>Linkend</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Linkend</em>' reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Linkend</em>' reference.
	 * @see #setLinkend(Section)
	 * @see com.bluexml.side.requirements.generator.metamodel.Documentation.DocumentationPackage#getXRefValue_Linkend()
	 * @model required="true"
	 * @generated
	 */
	Section getLinkend();

	/**
	 * Sets the value of the '{@link com.bluexml.side.requirements.generator.metamodel.Documentation.XRefValue#getLinkend <em>Linkend</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Linkend</em>' reference.
	 * @see #getLinkend()
	 * @generated
	 */
	void setLinkend(Section value);

} // XRefValue
