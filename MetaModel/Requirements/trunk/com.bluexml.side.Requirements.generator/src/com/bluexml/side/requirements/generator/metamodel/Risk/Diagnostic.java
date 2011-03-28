/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package com.bluexml.side.requirements.generator.metamodel.Risk;

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
 *   <li>{@link com.bluexml.side.requirements.generator.metamodel.Risk.Diagnostic#getEstimation <em>Estimation</em>}</li>
 * </ul>
 * </p>
 *
 * @see com.bluexml.side.requirements.generator.metamodel.Risk.RiskPackage#getDiagnostic()
 * @model
 * @generated
 */
public interface Diagnostic extends EObject {
	/**
	 * Returns the value of the '<em><b>Estimation</b></em>' containment reference list.
	 * The list contents are of type {@link com.bluexml.side.requirements.generator.metamodel.Risk.Estimation}.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Estimation</em>' containment reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Estimation</em>' containment reference list.
	 * @see com.bluexml.side.requirements.generator.metamodel.Risk.RiskPackage#getDiagnostic_Estimation()
	 * @model containment="true"
	 * @generated
	 */
	EList<Estimation> getEstimation();

} // Diagnostic
