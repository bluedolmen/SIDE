/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package com.bluexml.side.common;

import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EObject;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Model Element</b></em>'.
 * <!-- end-user-doc -->
 *
 * <!-- begin-model-doc -->
 * Definition: main elements of model inherits of ModelElement which allows to declare the description and the documentation attributes.
 * <!-- end-model-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link com.bluexml.side.common.ModelElement#getStereotypes <em>Stereotypes</em>}</li>
 *   <li>{@link com.bluexml.side.common.ModelElement#getTags <em>Tags</em>}</li>
 *   <li>{@link com.bluexml.side.common.ModelElement#getComments <em>Comments</em>}</li>
 *   <li>{@link com.bluexml.side.common.ModelElement#getDocumentation <em>Documentation</em>}</li>
 *   <li>{@link com.bluexml.side.common.ModelElement#getDescription <em>Description</em>}</li>
 *   <li>{@link com.bluexml.side.common.ModelElement#getMetainfo <em>Metainfo</em>}</li>
 *   <li>{@link com.bluexml.side.common.ModelElement#getConstraints <em>Constraints</em>}</li>
 * </ul>
 * </p>
 *
 * @see com.bluexml.side.common.CommonPackage#getModelElement()
 * @model
 * @generated
 */
public interface ModelElement extends EObject {
	/**
	 * Returns the value of the '<em><b>Stereotypes</b></em>' reference list.
	 * The list contents are of type {@link com.bluexml.side.common.Stereotype}.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Stereotypes</em>' reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Stereotypes</em>' reference list.
	 * @see com.bluexml.side.common.CommonPackage#getModelElement_Stereotypes()
	 * @model
	 * @generated
	 */
	EList<Stereotype> getStereotypes();

	/**
	 * Returns the value of the '<em><b>Tags</b></em>' containment reference list.
	 * The list contents are of type {@link com.bluexml.side.common.Tag}.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Tags</em>' containment reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Tags</em>' containment reference list.
	 * @see com.bluexml.side.common.CommonPackage#getModelElement_Tags()
	 * @model containment="true"
	 * @generated
	 */
	EList<Tag> getTags();

	/**
	 * Returns the value of the '<em><b>Comments</b></em>' containment reference list.
	 * The list contents are of type {@link com.bluexml.side.common.Comment}.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Comments</em>' containment reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Comments</em>' containment reference list.
	 * @see com.bluexml.side.common.CommonPackage#getModelElement_Comments()
	 * @model containment="true"
	 * @generated
	 */
	EList<Comment> getComments();

	/**
	 * Returns the value of the '<em><b>Documentation</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Documentation</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * Definition: the attribute documentation is associated to all the elements of a model to allow associating an explanation of the element; this explanation is used in the generation of the model documentation. 
	 * It is highly recommended to fill up this attribute at each creation of an element to provide a good documentation and to avoid having to spend huge time lately. 
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>Documentation</em>' attribute.
	 * @see #setDocumentation(String)
	 * @see com.bluexml.side.common.CommonPackage#getModelElement_Documentation()
	 * @model
	 * @generated
	 */
	String getDocumentation();

	/**
	 * Sets the value of the '{@link com.bluexml.side.common.ModelElement#getDocumentation <em>Documentation</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Documentation</em>' attribute.
	 * @see #getDocumentation()
	 * @generated
	 */
	void setDocumentation(String value);

	/**
	 * Returns the value of the '<em><b>Description</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Description</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * Definition: the attribute description is associated to many elements of a model to allow a description of the element; this description is used in the generation of the model documentation. 
	 * It is highly recommended to fill up this attribute at each creation of an element to provide a good documentation and to avoid having to spend huge time lately. 
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>Description</em>' attribute.
	 * @see #setDescription(String)
	 * @see com.bluexml.side.common.CommonPackage#getModelElement_Description()
	 * @model
	 * @generated
	 */
	String getDescription();

	/**
	 * Sets the value of the '{@link com.bluexml.side.common.ModelElement#getDescription <em>Description</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Description</em>' attribute.
	 * @see #getDescription()
	 * @generated
	 */
	void setDescription(String value);

	/**
	 * Returns the value of the '<em><b>Metainfo</b></em>' containment reference list.
	 * The list contents are of type {@link com.bluexml.side.common.MetaInfo}.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Metainfo</em>' containment reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Metainfo</em>' containment reference list.
	 * @see com.bluexml.side.common.CommonPackage#getModelElement_Metainfo()
	 * @model containment="true"
	 * @generated
	 */
	EList<MetaInfo> getMetainfo();

	/**
	 * Returns the value of the '<em><b>Constraints</b></em>' reference list.
	 * The list contents are of type {@link com.bluexml.side.common.Constraint}.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Constraints</em>' reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Constraints</em>' reference list.
	 * @see com.bluexml.side.common.CommonPackage#getModelElement_Constraints()
	 * @model
	 * @generated
	 */
	EList<Constraint> getConstraints();

} // ModelElement
