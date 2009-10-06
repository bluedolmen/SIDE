/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package com.bluexml.side.requirements.generator.metamodel.Diagnostic;

import org.eclipse.emf.ecore.EObject;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Problem</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link com.bluexml.side.requirements.generator.metamodel.Diagnostic.Problem#getSeverity <em>Severity</em>}</li>
 *   <li>{@link com.bluexml.side.requirements.generator.metamodel.Diagnostic.Problem#getElementType <em>Element Type</em>}</li>
 *   <li>{@link com.bluexml.side.requirements.generator.metamodel.Diagnostic.Problem#getElementName <em>Element Name</em>}</li>
 *   <li>{@link com.bluexml.side.requirements.generator.metamodel.Diagnostic.Problem#getDescription <em>Description</em>}</li>
 * </ul>
 * </p>
 *
 * @see com.bluexml.side.requirements.generator.metamodel.Diagnostic.DiagnosticPackage#getProblem()
 * @model
 * @generated
 */
public interface Problem extends EObject {
	/**
	 * Returns the value of the '<em><b>Severity</b></em>' attribute.
	 * The literals are from the enumeration {@link com.bluexml.side.requirements.generator.metamodel.Diagnostic.Severity}.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Severity</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Severity</em>' attribute.
	 * @see com.bluexml.side.requirements.generator.metamodel.Diagnostic.Severity
	 * @see #setSeverity(Severity)
	 * @see com.bluexml.side.requirements.generator.metamodel.Diagnostic.DiagnosticPackage#getProblem_Severity()
	 * @model required="true" ordered="false"
	 * @generated
	 */
	Severity getSeverity();

	/**
	 * Sets the value of the '{@link com.bluexml.side.requirements.generator.metamodel.Diagnostic.Problem#getSeverity <em>Severity</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Severity</em>' attribute.
	 * @see com.bluexml.side.requirements.generator.metamodel.Diagnostic.Severity
	 * @see #getSeverity()
	 * @generated
	 */
	void setSeverity(Severity value);

	/**
	 * Returns the value of the '<em><b>Element Type</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Element Type</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Element Type</em>' attribute.
	 * @see #setElementType(String)
	 * @see com.bluexml.side.requirements.generator.metamodel.Diagnostic.DiagnosticPackage#getProblem_ElementType()
	 * @model required="true" ordered="false"
	 * @generated
	 */
	String getElementType();

	/**
	 * Sets the value of the '{@link com.bluexml.side.requirements.generator.metamodel.Diagnostic.Problem#getElementType <em>Element Type</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Element Type</em>' attribute.
	 * @see #getElementType()
	 * @generated
	 */
	void setElementType(String value);

	/**
	 * Returns the value of the '<em><b>Element Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Element Name</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Element Name</em>' attribute.
	 * @see #setElementName(String)
	 * @see com.bluexml.side.requirements.generator.metamodel.Diagnostic.DiagnosticPackage#getProblem_ElementName()
	 * @model required="true" ordered="false"
	 * @generated
	 */
	String getElementName();

	/**
	 * Sets the value of the '{@link com.bluexml.side.requirements.generator.metamodel.Diagnostic.Problem#getElementName <em>Element Name</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Element Name</em>' attribute.
	 * @see #getElementName()
	 * @generated
	 */
	void setElementName(String value);

	/**
	 * Returns the value of the '<em><b>Description</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Description</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Description</em>' attribute.
	 * @see #setDescription(String)
	 * @see com.bluexml.side.requirements.generator.metamodel.Diagnostic.DiagnosticPackage#getProblem_Description()
	 * @model required="true" ordered="false"
	 * @generated
	 */
	String getDescription();

	/**
	 * Sets the value of the '{@link com.bluexml.side.requirements.generator.metamodel.Diagnostic.Problem#getDescription <em>Description</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Description</em>' attribute.
	 * @see #getDescription()
	 * @generated
	 */
	void setDescription(String value);

} // Problem
