/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package com.bluexml.side.workflow;

import org.eclipse.emf.common.util.EList;

import com.bluexml.side.common.DataType;
import com.bluexml.side.common.ModelElement;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Attribute</b></em>'.
 * <!-- end-user-doc -->
 *
 * <!-- begin-model-doc -->
 * Definition: 'Attribute' specifies properties for TaskNode or StartState.
 * <!-- end-model-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link com.bluexml.side.workflow.Attribute#getTyp <em>Typ</em>}</li>
 *   <li>{@link com.bluexml.side.workflow.Attribute#getTitle <em>Title</em>}</li>
 *   <li>{@link com.bluexml.side.workflow.Attribute#getName <em>Name</em>}</li>
 *   <li>{@link com.bluexml.side.workflow.Attribute#getAllowedValues <em>Allowed Values</em>}</li>
 * </ul>
 * </p>
 *
 * @see com.bluexml.side.workflow.WorkflowPackage#getAttribute()
 * @model annotation="http://www.bluexml.com/OCL UniqueNameForTaskAttribute='Attribute.allInstances() -> select(n|n.name = self.name and n <> self )->size()=0'"
 *        annotation="http://www.eclipse.org/emf/2002/Ecore constraints='UniqueNameForTaskAttribute'"
 * @generated
 */
public interface Attribute extends ModelElement {
	/**
	 * Returns the value of the '<em><b>Typ</b></em>' attribute.
	 * The default value is <code>"String"</code>.
	 * The literals are from the enumeration {@link com.bluexml.side.common.DataType}.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Typ</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * Definition: The attribute 'typ' specifies the type of an 'Attribute'.
	 * 
	 * Example: This is the list of the different type of an attribute: boolean, byte, char, double, float, int, long, short, String, void, Date, Object, DateTime and Time.
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>Typ</em>' attribute.
	 * @see com.bluexml.side.common.DataType
	 * @see #setTyp(DataType)
	 * @see com.bluexml.side.workflow.WorkflowPackage#getAttribute_Typ()
	 * @model default="String"
	 * @generated
	 */
	DataType getTyp();

	/**
	 * Sets the value of the '{@link com.bluexml.side.workflow.Attribute#getTyp <em>Typ</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Typ</em>' attribute.
	 * @see com.bluexml.side.common.DataType
	 * @see #getTyp()
	 * @generated
	 */
	void setTyp(DataType value);

	/**
	 * Returns the value of the '<em><b>Title</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Title</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * Definition: The 'title' attribute specifies the text that will appear in the target platform.
	 * <!-- end-model-doc -->
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
	 * Returns the value of the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Name</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * Definition: The 'name' attribute specifies the name of the 'Attribute'.
	 * 
	 * Constraint/limit: The 'name' of the 'Attribute' must be unique in all the process.
	 * <!-- end-model-doc -->
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

	/**
	 * Returns the value of the '<em><b>Allowed Values</b></em>' attribute list.
	 * The list contents are of type {@link java.lang.String}.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Allowed Values</em>' attribute list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Allowed Values</em>' attribute list.
	 * @see com.bluexml.side.workflow.WorkflowPackage#getAttribute_AllowedValues()
	 * @model ordered="false"
	 * @generated
	 */
	EList<String> getAllowedValues();

} // Attribute
