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
 * A representation of the model object '<em><b>Decision</b></em>'.
 * <!-- end-user-doc -->
 *
 * <!-- begin-model-doc -->
 * Definition: The 'Decision' evaluates the attribute 'condition' of the leaving transitions. The leaving transision with a true condition will be taken. If all conditions are false, the taken leaving transition will be the one without condition.
 * 
 * Constraint/limit:
 * - 'Decision' can have n leaving 'Transition'.
 * - 'Decision' must have only one leaving 'Transition' witout a condition.
 * 
 * Inherits: 
 * - TransitionTask.
 * <!-- end-model-doc -->
 *
 *
 * @see com.bluexml.side.workflow.WorkflowPackage#getDecision()
 * @model annotation="http://www.bluexml.com/OCL DecisionMustHaveOnlyOneTransitionWithCondition='self.transition -> select (t1| t1.condition -> isEmpty() or t1.condition =\'\') -> size() = 1'"
 *        annotation="http://www.eclipse.org/emf/2002/Ecore constraints='DecisionMustHaveOnlyOneTransitionWithCondition'"
 * @generated
 */
public interface Decision extends TransitionTask {

} // Decision
