/*
    Copyright (C) 2007-20013  BlueXML - www.bluexml.com

    This program is free software: you can redistribute it and/or modify
    it under the terms of the GNU Affero General Public License as
    published by the Free Software Foundation, either version 3 of the
    License, or (at your option) any later version.

    This program is distributed in the hope that it will be useful,
    but WITHOUT ANY WARRANTY; without even the implied warranty of
    MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
    GNU Affero General Public License for more details.

    You should have received a copy of the GNU Affero General Public License
    along with this program.  If not, see <http://www.gnu.org/licenses/>.
*/
/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package com.bluexml.side.requirements;

import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EObject;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Goal Step</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link com.bluexml.side.requirements.GoalStep#getNextGoals <em>Next Goals</em>}</li>
 *   <li>{@link com.bluexml.side.requirements.GoalStep#getProcess <em>Process</em>}</li>
 * </ul>
 * </p>
 *
 * @see com.bluexml.side.requirements.RequirementsPackage#getGoalStep()
 * @model
 * @generated
 */
public interface GoalStep extends EObject {
	/**
	 * Returns the value of the '<em><b>Next Goals</b></em>' reference list.
	 * The list contents are of type {@link com.bluexml.side.requirements.Goal}.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Next Goals</em>' reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Next Goals</em>' reference list.
	 * @see com.bluexml.side.requirements.RequirementsPackage#getGoalStep_NextGoals()
	 * @model
	 * @generated
	 */
	EList<Goal> getNextGoals();

	/**
	 * Returns the value of the '<em><b>Process</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Process</em>' reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Process</em>' reference.
	 * @see #setProcess(com.bluexml.side.requirements.Process)
	 * @see com.bluexml.side.requirements.RequirementsPackage#getGoalStep_Process()
	 * @model
	 * @generated
	 */
	com.bluexml.side.requirements.Process getProcess();

	/**
	 * Sets the value of the '{@link com.bluexml.side.requirements.GoalStep#getProcess <em>Process</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Process</em>' reference.
	 * @see #getProcess()
	 * @generated
	 */
	void setProcess(com.bluexml.side.requirements.Process value);

} // GoalStep
