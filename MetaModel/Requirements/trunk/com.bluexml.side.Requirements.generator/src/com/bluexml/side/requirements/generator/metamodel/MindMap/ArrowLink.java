/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package com.bluexml.side.requirements.generator.metamodel.MindMap;

import org.eclipse.emf.ecore.EObject;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Arrow Link</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link com.bluexml.side.requirements.generator.metamodel.MindMap.ArrowLink#getColor <em>Color</em>}</li>
 *   <li>{@link com.bluexml.side.requirements.generator.metamodel.MindMap.ArrowLink#getEndarrow <em>Endarrow</em>}</li>
 *   <li>{@link com.bluexml.side.requirements.generator.metamodel.MindMap.ArrowLink#getEndinclination <em>Endinclination</em>}</li>
 *   <li>{@link com.bluexml.side.requirements.generator.metamodel.MindMap.ArrowLink#getStartarrow <em>Startarrow</em>}</li>
 *   <li>{@link com.bluexml.side.requirements.generator.metamodel.MindMap.ArrowLink#getStartinclination <em>Startinclination</em>}</li>
 *   <li>{@link com.bluexml.side.requirements.generator.metamodel.MindMap.ArrowLink#getDestination <em>Destination</em>}</li>
 * </ul>
 * </p>
 *
 * @see com.bluexml.side.requirements.generator.metamodel.MindMap.mindmapPackage#getArrowLink()
 * @model
 * @generated
 */
public interface ArrowLink extends EObject {
	/**
	 * Returns the value of the '<em><b>Color</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Color</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Color</em>' attribute.
	 * @see #setColor(String)
	 * @see com.bluexml.side.requirements.generator.metamodel.MindMap.mindmapPackage#getArrowLink_Color()
	 * @model dataType="org.eclipse.emf.ecore.xml.type.String"
	 * @generated
	 */
	String getColor();

	/**
	 * Sets the value of the '{@link com.bluexml.side.requirements.generator.metamodel.MindMap.ArrowLink#getColor <em>Color</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Color</em>' attribute.
	 * @see #getColor()
	 * @generated
	 */
	void setColor(String value);

	/**
	 * Returns the value of the '<em><b>Destination</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Destination</em>' reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Destination</em>' reference.
	 * @see #setDestination(Node)
	 * @see com.bluexml.side.requirements.generator.metamodel.MindMap.mindmapPackage#getArrowLink_Destination()
	 * @model
	 * @generated
	 */
	Node getDestination();

	/**
	 * Sets the value of the '{@link com.bluexml.side.requirements.generator.metamodel.MindMap.ArrowLink#getDestination <em>Destination</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Destination</em>' reference.
	 * @see #getDestination()
	 * @generated
	 */
	void setDestination(Node value);

	/**
	 * Returns the value of the '<em><b>Endarrow</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Endarrow</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Endarrow</em>' attribute.
	 * @see #setEndarrow(String)
	 * @see com.bluexml.side.requirements.generator.metamodel.MindMap.mindmapPackage#getArrowLink_Endarrow()
	 * @model dataType="org.eclipse.emf.ecore.xml.type.String"
	 * @generated
	 */
	String getEndarrow();

	/**
	 * Sets the value of the '{@link com.bluexml.side.requirements.generator.metamodel.MindMap.ArrowLink#getEndarrow <em>Endarrow</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Endarrow</em>' attribute.
	 * @see #getEndarrow()
	 * @generated
	 */
	void setEndarrow(String value);

	/**
	 * Returns the value of the '<em><b>Endinclination</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Endinclination</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Endinclination</em>' attribute.
	 * @see #setEndinclination(String)
	 * @see com.bluexml.side.requirements.generator.metamodel.MindMap.mindmapPackage#getArrowLink_Endinclination()
	 * @model dataType="org.eclipse.emf.ecore.xml.type.String"
	 * @generated
	 */
	String getEndinclination();

	/**
	 * Sets the value of the '{@link com.bluexml.side.requirements.generator.metamodel.MindMap.ArrowLink#getEndinclination <em>Endinclination</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Endinclination</em>' attribute.
	 * @see #getEndinclination()
	 * @generated
	 */
	void setEndinclination(String value);

	/**
	 * Returns the value of the '<em><b>Startarrow</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Startarrow</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Startarrow</em>' attribute.
	 * @see #setStartarrow(String)
	 * @see com.bluexml.side.requirements.generator.metamodel.MindMap.mindmapPackage#getArrowLink_Startarrow()
	 * @model dataType="org.eclipse.emf.ecore.xml.type.String"
	 * @generated
	 */
	String getStartarrow();

	/**
	 * Sets the value of the '{@link com.bluexml.side.requirements.generator.metamodel.MindMap.ArrowLink#getStartarrow <em>Startarrow</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Startarrow</em>' attribute.
	 * @see #getStartarrow()
	 * @generated
	 */
	void setStartarrow(String value);

	/**
	 * Returns the value of the '<em><b>Startinclination</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Startinclination</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Startinclination</em>' attribute.
	 * @see #setStartinclination(String)
	 * @see com.bluexml.side.requirements.generator.metamodel.MindMap.mindmapPackage#getArrowLink_Startinclination()
	 * @model dataType="org.eclipse.emf.ecore.xml.type.String"
	 * @generated
	 */
	String getStartinclination();

	/**
	 * Sets the value of the '{@link com.bluexml.side.requirements.generator.metamodel.MindMap.ArrowLink#getStartinclination <em>Startinclination</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Startinclination</em>' attribute.
	 * @see #getStartinclination()
	 * @generated
	 */
	void setStartinclination(String value);

} // ArrowLink
