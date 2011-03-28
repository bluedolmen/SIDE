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
 * A representation of the model object '<em><b>Schema</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link com.bluexml.side.requirements.generator.metamodel.WebProject.Schema#getTables <em>Tables</em>}</li>
 * </ul>
 * </p>
 *
 * @see com.bluexml.side.requirements.generator.metamodel.WebProject.WebProjectPackage#getSchema()
 * @model
 * @generated
 */
public interface Schema extends EObject {
	/**
	 * Returns the value of the '<em><b>Tables</b></em>' containment reference list.
	 * The list contents are of type {@link com.bluexml.side.requirements.generator.metamodel.WebProject.Table}.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Tables</em>' containment reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Tables</em>' containment reference list.
	 * @see com.bluexml.side.requirements.generator.metamodel.WebProject.WebProjectPackage#getSchema_Tables()
	 * @model containment="true"
	 * @generated
	 */
	EList<Table> getTables();

} // Schema
