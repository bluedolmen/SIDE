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
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link com.bluexml.side.clazz.ClassModelElement#getMetainfo <em>Metainfo</em>}</li>
 *   <li>{@link com.bluexml.side.clazz.ClassModelElement#getDocumentation <em>Documentation</em>}</li>
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
	 * Returns the value of the '<em><b>Metainfo</b></em>' containment reference list.
	 * The list contents are of type {@link com.bluexml.side.clazz.MetaInfo}.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Metainfo</em>' containment reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Metainfo</em>' containment reference list.
	 * @see com.bluexml.side.clazz.ClazzPackage#getClassModelElement_Metainfo()
	 * @model containment="true"
	 * @generated
	 */
	EList<MetaInfo> getMetainfo();

	/**
	 * Returns the value of the '<em><b>Documentation</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Documentation</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Documentation</em>' attribute.
	 * @see #setDocumentation(String)
	 * @see com.bluexml.side.clazz.ClazzPackage#getClassModelElement_Documentation()
	 * @model
	 * @generated
	 */
	String getDocumentation();

	/**
	 * Sets the value of the '{@link com.bluexml.side.clazz.ClassModelElement#getDocumentation <em>Documentation</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Documentation</em>' attribute.
	 * @see #getDocumentation()
	 * @generated
	 */
	void setDocumentation(String value);

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
