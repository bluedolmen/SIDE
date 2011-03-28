/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package com.bluexml.side.requirements.generator.metamodel.MindMap;

import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EObject;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Maps</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link com.bluexml.side.requirements.generator.metamodel.MindMap.Maps#getMaps <em>Maps</em>}</li>
 * </ul>
 * </p>
 *
 * @see com.bluexml.side.requirements.generator.metamodel.MindMap.mindmapPackage#getMaps()
 * @model
 * @generated
 */
public interface Maps extends EObject {
	/**
	 * Returns the value of the '<em><b>Maps</b></em>' containment reference list.
	 * The list contents are of type {@link com.bluexml.side.requirements.generator.metamodel.MindMap.Map}.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Maps</em>' containment reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Maps</em>' containment reference list.
	 * @see com.bluexml.side.requirements.generator.metamodel.MindMap.mindmapPackage#getMaps_Maps()
	 * @model containment="true"
	 * @generated
	 */
	EList<Map> getMaps();

} // Maps
