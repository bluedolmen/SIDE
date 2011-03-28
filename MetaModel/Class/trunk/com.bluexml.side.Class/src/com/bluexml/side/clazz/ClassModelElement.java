/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package com.bluexml.side.clazz;

import org.eclipse.emf.common.util.EList;

import com.bluexml.side.common.ModelElement;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Class Model Element</b></em>'.
 * <!-- end-user-doc -->
 *
 * <!-- begin-model-doc -->
 * Definition: 'Has comment' allows to link a class to a comment.
 * <!-- end-model-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link com.bluexml.side.clazz.ClassModelElement#getHasComments <em>Has Comments</em>}</li>
 * </ul>
 * </p>
 *
 * @see com.bluexml.side.clazz.ClazzPackage#getClassModelElement()
 * @model
 * @generated
 */
public interface ClassModelElement extends ModelElement {
	/**
	 * Returns the value of the '<em><b>Has Comments</b></em>' containment reference list.
	 * The list contents are of type {@link com.bluexml.side.clazz.ClassComment}.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Has Comments</em>' containment reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Has Comments</em>' containment reference list.
	 * @see com.bluexml.side.clazz.ClazzPackage#getClassModelElement_HasComments()
	 * @model containment="true"
	 * @generated
	 */
	EList<ClassComment> getHasComments();
		
} // ClassModelElement
