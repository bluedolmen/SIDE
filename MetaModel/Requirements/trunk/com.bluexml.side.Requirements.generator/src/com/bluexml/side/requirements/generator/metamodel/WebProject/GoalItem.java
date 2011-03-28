/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package com.bluexml.side.requirements.generator.metamodel.WebProject;

import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EObject;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Goal Item</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link com.bluexml.side.requirements.generator.metamodel.WebProject.GoalItem#getLabel <em>Label</em>}</li>
 *   <li>{@link com.bluexml.side.requirements.generator.metamodel.WebProject.GoalItem#getPage <em>Page</em>}</li>
 *   <li>{@link com.bluexml.side.requirements.generator.metamodel.WebProject.GoalItem#getSub <em>Sub</em>}</li>
 * </ul>
 * </p>
 *
 * @see com.bluexml.side.requirements.generator.metamodel.WebProject.WebProjectPackage#getGoalItem()
 * @model
 * @generated
 */
public interface GoalItem extends EObject {
	/**
	 * Returns the value of the '<em><b>Label</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Label</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Label</em>' attribute.
	 * @see #setLabel(String)
	 * @see com.bluexml.side.requirements.generator.metamodel.WebProject.WebProjectPackage#getGoalItem_Label()
	 * @model
	 * @generated
	 */
	String getLabel();

	/**
	 * Sets the value of the '{@link com.bluexml.side.requirements.generator.metamodel.WebProject.GoalItem#getLabel <em>Label</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Label</em>' attribute.
	 * @see #getLabel()
	 * @generated
	 */
	void setLabel(String value);

	/**
	 * Returns the value of the '<em><b>Page</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Page</em>' reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Page</em>' reference.
	 * @see #setPage(Page)
	 * @see com.bluexml.side.requirements.generator.metamodel.WebProject.WebProjectPackage#getGoalItem_Page()
	 * @model required="true"
	 * @generated
	 */
	Page getPage();

	/**
	 * Sets the value of the '{@link com.bluexml.side.requirements.generator.metamodel.WebProject.GoalItem#getPage <em>Page</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Page</em>' reference.
	 * @see #getPage()
	 * @generated
	 */
	void setPage(Page value);

	/**
	 * Returns the value of the '<em><b>Sub</b></em>' containment reference list.
	 * The list contents are of type {@link com.bluexml.side.requirements.generator.metamodel.WebProject.GoalItem}.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Sub</em>' containment reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Sub</em>' containment reference list.
	 * @see com.bluexml.side.requirements.generator.metamodel.WebProject.WebProjectPackage#getGoalItem_Sub()
	 * @model containment="true"
	 * @generated
	 */
	EList<GoalItem> getSub();

} // GoalItem
