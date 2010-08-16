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
 * A representation of the model object '<em><b>Node Presentation</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link com.bluexml.side.requirements.generator.metamodel.MindMap.NodePresentation#isBold <em>Bold</em>}</li>
 *   <li>{@link com.bluexml.side.requirements.generator.metamodel.MindMap.NodePresentation#isItalic <em>Italic</em>}</li>
 *   <li>{@link com.bluexml.side.requirements.generator.metamodel.MindMap.NodePresentation#getName <em>Name</em>}</li>
 *   <li>{@link com.bluexml.side.requirements.generator.metamodel.MindMap.NodePresentation#getSize <em>Size</em>}</li>
 *   <li>{@link com.bluexml.side.requirements.generator.metamodel.MindMap.NodePresentation#getBackgroundColor <em>Background Color</em>}</li>
 *   <li>{@link com.bluexml.side.requirements.generator.metamodel.MindMap.NodePresentation#getColor <em>Color</em>}</li>
 *   <li>{@link com.bluexml.side.requirements.generator.metamodel.MindMap.NodePresentation#isFolded <em>Folded</em>}</li>
 * </ul>
 * </p>
 *
 * @see com.bluexml.side.requirements.generator.metamodel.MindMap.mindmapPackage#getNodePresentation()
 * @model
 * @generated
 */
public interface NodePresentation extends EObject {
	/**
	 * Returns the value of the '<em><b>Bold</b></em>' attribute.
	 * The default value is <code>"true"</code>.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Bold</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Bold</em>' attribute.
	 * @see #isSetBold()
	 * @see #unsetBold()
	 * @see #setBold(boolean)
	 * @see com.bluexml.side.requirements.generator.metamodel.MindMap.mindmapPackage#getNodePresentation_Bold()
	 * @model default="true" unsettable="true" dataType="org.eclipse.emf.ecore.xml.type.Boolean"
	 * @generated
	 */
	boolean isBold();

	/**
	 * Sets the value of the '{@link com.bluexml.side.requirements.generator.metamodel.MindMap.NodePresentation#isBold <em>Bold</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Bold</em>' attribute.
	 * @see #isSetBold()
	 * @see #unsetBold()
	 * @see #isBold()
	 * @generated
	 */
	void setBold(boolean value);

	/**
	 * Unsets the value of the '{@link com.bluexml.side.requirements.generator.metamodel.MindMap.NodePresentation#isBold <em>Bold</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #isSetBold()
	 * @see #isBold()
	 * @see #setBold(boolean)
	 * @generated
	 */
	void unsetBold();

	/**
	 * Returns whether the value of the '{@link com.bluexml.side.requirements.generator.metamodel.MindMap.NodePresentation#isBold <em>Bold</em>}' attribute is set.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return whether the value of the '<em>Bold</em>' attribute is set.
	 * @see #unsetBold()
	 * @see #isBold()
	 * @see #setBold(boolean)
	 * @generated
	 */
	boolean isSetBold();

	/**
	 * Returns the value of the '<em><b>Italic</b></em>' attribute.
	 * The default value is <code>"true"</code>.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Italic</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Italic</em>' attribute.
	 * @see #isSetItalic()
	 * @see #unsetItalic()
	 * @see #setItalic(boolean)
	 * @see com.bluexml.side.requirements.generator.metamodel.MindMap.mindmapPackage#getNodePresentation_Italic()
	 * @model default="true" unsettable="true" dataType="org.eclipse.emf.ecore.xml.type.Boolean"
	 * @generated
	 */
	boolean isItalic();

	/**
	 * Sets the value of the '{@link com.bluexml.side.requirements.generator.metamodel.MindMap.NodePresentation#isItalic <em>Italic</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Italic</em>' attribute.
	 * @see #isSetItalic()
	 * @see #unsetItalic()
	 * @see #isItalic()
	 * @generated
	 */
	void setItalic(boolean value);

	/**
	 * Unsets the value of the '{@link com.bluexml.side.requirements.generator.metamodel.MindMap.NodePresentation#isItalic <em>Italic</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #isSetItalic()
	 * @see #isItalic()
	 * @see #setItalic(boolean)
	 * @generated
	 */
	void unsetItalic();

	/**
	 * Returns whether the value of the '{@link com.bluexml.side.requirements.generator.metamodel.MindMap.NodePresentation#isItalic <em>Italic</em>}' attribute is set.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return whether the value of the '<em>Italic</em>' attribute is set.
	 * @see #unsetItalic()
	 * @see #isItalic()
	 * @see #setItalic(boolean)
	 * @generated
	 */
	boolean isSetItalic();

	/**
	 * Returns the value of the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Name</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Name</em>' attribute.
	 * @see #setName(String)
	 * @see com.bluexml.side.requirements.generator.metamodel.MindMap.mindmapPackage#getNodePresentation_Name()
	 * @model dataType="org.eclipse.emf.ecore.xml.type.String" required="true"
	 * @generated
	 */
	String getName();

	/**
	 * Sets the value of the '{@link com.bluexml.side.requirements.generator.metamodel.MindMap.NodePresentation#getName <em>Name</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Name</em>' attribute.
	 * @see #getName()
	 * @generated
	 */
	void setName(String value);

	/**
	 * Returns the value of the '<em><b>Size</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Size</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Size</em>' attribute.
	 * @see #setSize(int)
	 * @see com.bluexml.side.requirements.generator.metamodel.MindMap.mindmapPackage#getNodePresentation_Size()
	 * @model dataType="org.eclipse.emf.ecore.xml.type.Int" required="true"
	 * @generated
	 */
	int getSize();

	/**
	 * Sets the value of the '{@link com.bluexml.side.requirements.generator.metamodel.MindMap.NodePresentation#getSize <em>Size</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Size</em>' attribute.
	 * @see #getSize()
	 * @generated
	 */
	void setSize(int value);

	/**
	 * Returns the value of the '<em><b>Background Color</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Background Color</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Background Color</em>' attribute.
	 * @see #setBackgroundColor(String)
	 * @see com.bluexml.side.requirements.generator.metamodel.MindMap.mindmapPackage#getNodePresentation_BackgroundColor()
	 * @model dataType="org.eclipse.emf.ecore.xml.type.String"
	 * @generated
	 */
	String getBackgroundColor();

	/**
	 * Sets the value of the '{@link com.bluexml.side.requirements.generator.metamodel.MindMap.NodePresentation#getBackgroundColor <em>Background Color</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Background Color</em>' attribute.
	 * @see #getBackgroundColor()
	 * @generated
	 */
	void setBackgroundColor(String value);

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
	 * @see com.bluexml.side.requirements.generator.metamodel.MindMap.mindmapPackage#getNodePresentation_Color()
	 * @model dataType="org.eclipse.emf.ecore.xml.type.String"
	 * @generated
	 */
	String getColor();

	/**
	 * Sets the value of the '{@link com.bluexml.side.requirements.generator.metamodel.MindMap.NodePresentation#getColor <em>Color</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Color</em>' attribute.
	 * @see #getColor()
	 * @generated
	 */
	void setColor(String value);

	/**
	 * Returns the value of the '<em><b>Folded</b></em>' attribute.
	 * The default value is <code>"true"</code>.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Folded</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Folded</em>' attribute.
	 * @see #isSetFolded()
	 * @see #unsetFolded()
	 * @see #setFolded(boolean)
	 * @see com.bluexml.side.requirements.generator.metamodel.MindMap.mindmapPackage#getNodePresentation_Folded()
	 * @model default="true" unsettable="true" dataType="org.eclipse.emf.ecore.xml.type.Boolean"
	 * @generated
	 */
	boolean isFolded();

	/**
	 * Sets the value of the '{@link com.bluexml.side.requirements.generator.metamodel.MindMap.NodePresentation#isFolded <em>Folded</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Folded</em>' attribute.
	 * @see #isSetFolded()
	 * @see #unsetFolded()
	 * @see #isFolded()
	 * @generated
	 */
	void setFolded(boolean value);

	/**
	 * Unsets the value of the '{@link com.bluexml.side.requirements.generator.metamodel.MindMap.NodePresentation#isFolded <em>Folded</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #isSetFolded()
	 * @see #isFolded()
	 * @see #setFolded(boolean)
	 * @generated
	 */
	void unsetFolded();

	/**
	 * Returns whether the value of the '{@link com.bluexml.side.requirements.generator.metamodel.MindMap.NodePresentation#isFolded <em>Folded</em>}' attribute is set.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return whether the value of the '<em>Folded</em>' attribute is set.
	 * @see #unsetFolded()
	 * @see #isFolded()
	 * @see #setFolded(boolean)
	 * @generated
	 */
	boolean isSetFolded();

} // NodePresentation
