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
 * A representation of the model object '<em><b>Fork</b></em>'.
 * <!-- end-user-doc -->
 *
 * <!-- begin-model-doc -->
 * Definition: The 'Fork' splits one path of execution into multiple concurrent paths of execution.
 * 
 * Inherits: 
 * - TransitionTask.
 * <!-- end-model-doc -->
 *
 *
 * @see com.bluexml.side.workflow.WorkflowPackage#getFork()
 * @model annotation="http://www.bluexml.com/OCL ForkMustBeFollowedByJoin='self.getAllNextStates()->select(s | s.oclIsTypeOf(Join))->size()>0'"
 *        annotation="http://www.eclipse.org/emf/2002/Ecore constraints='ForkMustBeFollowedByJoin'"
 * @generated
 */
public interface Fork extends TransitionTask {

} // Fork
