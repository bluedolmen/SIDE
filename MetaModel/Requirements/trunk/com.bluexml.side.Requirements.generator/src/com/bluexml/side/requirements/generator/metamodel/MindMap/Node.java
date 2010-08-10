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
 * A representation of the model object '<em><b>Node</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link com.bluexml.side.requirements.generator.metamodel.MindMap.Node#getPresentation <em>Presentation</em>}</li>
 *   <li>{@link com.bluexml.side.requirements.generator.metamodel.MindMap.Node#getText <em>Text</em>}</li>
 *   <li>{@link com.bluexml.side.requirements.generator.metamodel.MindMap.Node#getSub <em>Sub</em>}</li>
 *   <li>{@link com.bluexml.side.requirements.generator.metamodel.MindMap.Node#getId <em>Id</em>}</li>
 *   <li>{@link com.bluexml.side.requirements.generator.metamodel.MindMap.Node#getArrowlink <em>Arrowlink</em>}</li>
 * </ul>
 * </p>
 *
 * @see com.bluexml.side.requirements.generator.metamodel.MindMap.mindmapPackage#getNode()
 * @model
 * @generated
 */
public interface Node extends EObject {
	/**
	 * Returns the value of the '<em><b>Presentation</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Presentation</em>' containment reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Presentation</em>' containment reference.
	 * @see #setPresentation(NodePresentation)
	 * @see com.bluexml.side.requirements.generator.metamodel.MindMap.mindmapPackage#getNode_Presentation()
	 * @model containment="true"
	 * @generated
	 */
	NodePresentation getPresentation();

	/**
	 * Sets the value of the '{@link com.bluexml.side.requirements.generator.metamodel.MindMap.Node#getPresentation <em>Presentation</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Presentation</em>' containment reference.
	 * @see #getPresentation()
	 * @generated
	 */
	void setPresentation(NodePresentation value);

	/**
	 * Returns the value of the '<em><b>Arrowlink</b></em>' containment reference list.
	 * The list contents are of type {@link com.bluexml.side.requirements.generator.metamodel.MindMap.ArrowLink}.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Arrowlink</em>' containment reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Arrowlink</em>' containment reference list.
	 * @see com.bluexml.side.requirements.generator.metamodel.MindMap.mindmapPackage#getNode_Arrowlink()
	 * @model containment="true"
	 * @generated
	 */
	EList<ArrowLink> getArrowlink();

	/**
	 * Returns the value of the '<em><b>Text</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Text</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Text</em>' attribute.
	 * @see #setText(String)
	 * @see com.bluexml.side.requirements.generator.metamodel.MindMap.mindmapPackage#getNode_Text()
	 * @model dataType="org.eclipse.emf.ecore.xml.type.String" required="true"
	 * @generated
	 */
	String getText();

	/**
	 * Sets the value of the '{@link com.bluexml.side.requirements.generator.metamodel.MindMap.Node#getText <em>Text</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Text</em>' attribute.
	 * @see #getText()
	 * @generated
	 */
	void setText(String value);

	/**
	 * Returns the value of the '<em><b>Sub</b></em>' containment reference list.
	 * The list contents are of type {@link com.bluexml.side.requirements.generator.metamodel.MindMap.Node}.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Sub</em>' containment reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Sub</em>' containment reference list.
	 * @see com.bluexml.side.requirements.generator.metamodel.MindMap.mindmapPackage#getNode_Sub()
	 * @model containment="true"
	 * @generated
	 */
	EList<Node> getSub();

	/**
	 * Returns the value of the '<em><b>Id</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Id</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Id</em>' attribute.
	 * @see #setId(String)
	 * @see com.bluexml.side.requirements.generator.metamodel.MindMap.mindmapPackage#getNode_Id()
	 * @model
	 * @generated
	 */
	String getId();

	/**
	 * Sets the value of the '{@link com.bluexml.side.requirements.generator.metamodel.MindMap.Node#getId <em>Id</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Id</em>' attribute.
	 * @see #getId()
	 * @generated
	 */
	void setId(String value);

} // Node
