/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package com.bluexml.side.workflow;

import com.bluexml.side.clazz.AttributeType;
import com.bluexml.side.clazz.Enumeration;

import org.eclipse.emf.ecore.EObject;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Attribute</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link com.bluexml.side.workflow.Attribute#getTyp <em>Typ</em>}</li>
 *   <li>{@link com.bluexml.side.workflow.Attribute#getTitle <em>Title</em>}</li>
 *   <li>{@link com.bluexml.side.workflow.Attribute#getValueList <em>Value List</em>}</li>
 *   <li>{@link com.bluexml.side.workflow.Attribute#getName <em>Name</em>}</li>
 * </ul>
 * </p>
 *
 * @see com.bluexml.side.workflow.WorkflowPackage#getAttribute()
 * @model
 * @generated
 */
public interface Attribute extends EObject {
	/**
	 * Returns the value of the '<em><b>Typ</b></em>' attribute.
	 * The default value is <code>"void"</code>.
	 * The literals are from the enumeration {@link com.bluexml.side.clazz.AttributeType}.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Typ</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Typ</em>' attribute.
	 * @see com.bluexml.side.clazz.AttributeType
	 * @see #setTyp(AttributeType)
	 * @see com.bluexml.side.workflow.WorkflowPackage#getAttribute_Typ()
	 * @model default="void"
	 * @generated
	 */
	AttributeType getTyp();

	/**
	 * Sets the value of the '{@link com.bluexml.side.workflow.Attribute#getTyp <em>Typ</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Typ</em>' attribute.
	 * @see com.bluexml.side.clazz.AttributeType
	 * @see #getTyp()
	 * @generated
	 */
	void setTyp(AttributeType value);

	/**
	 * Returns the value of the '<em><b>Title</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Title</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Title</em>' attribute.
	 * @see #setTitle(String)
	 * @see com.bluexml.side.workflow.WorkflowPackage#getAttribute_Title()
	 * @model
	 * @generated
	 */
	String getTitle();

	/**
	 * Sets the value of the '{@link com.bluexml.side.workflow.Attribute#getTitle <em>Title</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Title</em>' attribute.
	 * @see #getTitle()
	 * @generated
	 */
	void setTitle(String value);

	/**
	 * Returns the value of the '<em><b>Value List</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Value List</em>' reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Value List</em>' reference.
	 * @see #setValueList(Enumeration)
	 * @see com.bluexml.side.workflow.WorkflowPackage#getAttribute_ValueList()
	 * @model
	 * @generated
	 */
	Enumeration getValueList();

	/**
	 * Sets the value of the '{@link com.bluexml.side.workflow.Attribute#getValueList <em>Value List</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Value List</em>' reference.
	 * @see #getValueList()
	 * @generated
	 */
	void setValueList(Enumeration value);

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
	 * @see com.bluexml.side.workflow.WorkflowPackage#getAttribute_Name()
	 * @model
	 * @generated
	 */
	String getName();

	/**
	 * Sets the value of the '{@link com.bluexml.side.workflow.Attribute#getName <em>Name</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Name</em>' attribute.
	 * @see #getName()
	 * @generated
	 */
	void setName(String value);

} // Attribute
