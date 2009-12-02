/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package com.bluexml.side.workflow;

import org.eclipse.emf.common.util.EList;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>State</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link com.bluexml.side.workflow.State#getName <em>Name</em>}</li>
 *   <li>{@link com.bluexml.side.workflow.State#getEvent <em>Event</em>}</li>
 *   <li>{@link com.bluexml.side.workflow.State#getTitle <em>Title</em>}</li>
 *   <li>{@link com.bluexml.side.workflow.State#getStateDescription <em>State Description</em>}</li>
 * </ul>
 * </p>
 *
 * @see com.bluexml.side.workflow.WorkflowPackage#getState()
 * @model abstract="true"
 *        annotation="http://www.bluexml.com/OCL noSpecialCharacters='self.name.regexMatch(\'[\\w]*\') = true' NoStateWithSameName='State.allInstances()->select(s | s.getContainer() = self.getContainer())-> select(n|n.name = self.name and n <> self )->size()=0' NameNull='not self.name.oclIsUndefined() and self.name <> \'\''"
 *        annotation="http://www.eclipse.org/emf/2002/Ecore constraints='NoStateWithSameName noSpecialCharacters NameNull'"
 * @generated
 */
public interface State extends WorkflowModelElement {

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
	 * @see com.bluexml.side.workflow.WorkflowPackage#getState_Name()
	 * @model unique="false" required="true" ordered="false"
	 * @generated
	 */
	String getName();

	/**
	 * Sets the value of the '{@link com.bluexml.side.workflow.State#getName <em>Name</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Name</em>' attribute.
	 * @see #getName()
	 * @generated
	 */
	void setName(String value);

	/**
	 * Returns the value of the '<em><b>Event</b></em>' containment reference list.
	 * The list contents are of type {@link com.bluexml.side.workflow.Event}.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Event</em>' containment reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Event</em>' containment reference list.
	 * @see com.bluexml.side.workflow.WorkflowPackage#getState_Event()
	 * @model containment="true"
	 * @generated
	 */
	EList<Event> getEvent();

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
	 * @see com.bluexml.side.workflow.WorkflowPackage#getState_Title()
	 * @model
	 * @generated
	 */
	String getTitle();

	/**
	 * Sets the value of the '{@link com.bluexml.side.workflow.State#getTitle <em>Title</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Title</em>' attribute.
	 * @see #getTitle()
	 * @generated
	 */
	void setTitle(String value);

	/**
	 * Returns the value of the '<em><b>State Description</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>State Description</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>State Description</em>' attribute.
	 * @see #setStateDescription(String)
	 * @see com.bluexml.side.workflow.WorkflowPackage#getState_StateDescription()
	 * @model
	 * @generated
	 */
	String getStateDescription();

	/**
	 * Sets the value of the '{@link com.bluexml.side.workflow.State#getStateDescription <em>State Description</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>State Description</em>' attribute.
	 * @see #getStateDescription()
	 * @generated
	 */
	void setStateDescription(String value);
} // State
