/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package com.bluexml.side.requirements.generator.metamodel.Diagnostic;

import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EObject;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Diagnostic</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link com.bluexml.side.requirements.generator.metamodel.Diagnostic.Diagnostic#getProblem <em>Problem</em>}</li>
 * </ul>
 * </p>
 *
 * @see com.bluexml.side.requirements.generator.metamodel.Diagnostic.DiagnosticPackage#getDiagnostic()
 * @model
 * @generated
 */
public interface Diagnostic extends EObject {
	/**
	 * Returns the value of the '<em><b>Problem</b></em>' containment reference list.
	 * The list contents are of type {@link com.bluexml.side.requirements.generator.metamodel.Diagnostic.Problem}.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Problem</em>' containment reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Problem</em>' containment reference list.
	 * @see com.bluexml.side.requirements.generator.metamodel.Diagnostic.DiagnosticPackage#getDiagnostic_Problem()
	 * @model containment="true"
	 * @generated
	 */
	EList<Problem> getProblem();

} // Diagnostic
