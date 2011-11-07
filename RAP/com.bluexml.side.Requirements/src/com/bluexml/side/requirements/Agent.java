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
 * A representation of the model object '<em><b>Agent</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link com.bluexml.side.requirements.Agent#isIsHuman <em>Is Human</em>}</li>
 *   <li>{@link com.bluexml.side.requirements.Agent#getIsResponsible <em>Is Responsible</em>}</li>
 * </ul>
 * </p>
 *
 * @see com.bluexml.side.requirements.RequirementsPackage#getAgent()
 * @model
 * @generated
 */
public interface Agent extends AnnotableElement {
	/**
	 * Returns the value of the '<em><b>Is Human</b></em>' attribute.
	 * The default value is <code>"true"</code>.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Is Human</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Is Human</em>' attribute.
	 * @see #setIsHuman(boolean)
	 * @see com.bluexml.side.requirements.RequirementsPackage#getAgent_IsHuman()
	 * @model default="true"
	 * @generated
	 */
	boolean isIsHuman();

	/**
	 * Sets the value of the '{@link com.bluexml.side.requirements.Agent#isIsHuman <em>Is Human</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Is Human</em>' attribute.
	 * @see #isIsHuman()
	 * @generated
	 */
	void setIsHuman(boolean value);

	/**
	 * Returns the value of the '<em><b>Is Responsible</b></em>' reference list.
	 * The list contents are of type {@link com.bluexml.side.requirements.Goal}.
	 * It is bidirectional and its opposite is '{@link com.bluexml.side.requirements.Goal#getResponsible <em>Responsible</em>}'.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Is Responsible</em>' reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Is Responsible</em>' reference list.
	 * @see com.bluexml.side.requirements.RequirementsPackage#getAgent_IsResponsible()
	 * @see com.bluexml.side.requirements.Goal#getResponsible
	 * @model opposite="responsible"
	 * @generated
	 */
	EList<Goal> getIsResponsible();

} // Agent
