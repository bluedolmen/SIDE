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
package com.bluexml.side.workflow;


/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Timer</b></em>'.
 * <!-- end-user-doc -->
 *
 * <!-- begin-model-doc -->
 * Definition: If a 'Timer' is associated to a transition, the transition will be taken when the 'Timer' expires.
 * <!-- end-model-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link com.bluexml.side.workflow.Timer#getDuedate <em>Duedate</em>}</li>
 * </ul>
 * </p>
 *
 * @see com.bluexml.side.workflow.WorkflowPackage#getTimer()
 * @model
 * @generated
 */
public interface Timer extends Action {
	/**
	 * Returns the value of the '<em><b>Duedate</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Duedate</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * Definition: The attribute 'duedate' specifies the duration of a 'Timer'.
	 * 
	 * Constraint/limit: The syntax of the attribute 'Timer' follow the jpdl syntax (http://docs.jboss.org/jbpm/v3/userguide/businesscalendar.html#duration): quantity [business] unit.
	 * 
	 * Example:
	 * - 1 year.
	 * - 1 week.
	 * - 1 month.
	 * - 1 hour.
	 * - 1 min.
	 * - 1 second.
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>Duedate</em>' attribute.
	 * @see #setDuedate(String)
	 * @see com.bluexml.side.workflow.WorkflowPackage#getTimer_Duedate()
	 * @model unique="false" required="true" ordered="false"
	 * @generated
	 */
	String getDuedate();

	/**
	 * Sets the value of the '{@link com.bluexml.side.workflow.Timer#getDuedate <em>Duedate</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Duedate</em>' attribute.
	 * @see #getDuedate()
	 * @generated
	 */
	void setDuedate(String value);

} // Timer
