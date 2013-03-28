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
package com.bluexml.side.form;


/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Workflow Form Collection</b></em>'.
 * <!-- end-user-doc -->
 *
 * <!-- begin-model-doc -->
 * Definition: The 'WorkflowFormCollection' contains all the workflow forms. It is similar to the FormCollection but instead of containing 'FormClass' elements it contains 'FormWorkflow' elements to design paperless procedures. 
 * Inherits: FormCollection.
 * <!-- end-model-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link com.bluexml.side.form.WorkflowFormCollection#getLinked_process <em>Linked process</em>}</li>
 * </ul>
 * </p>
 *
 * @see com.bluexml.side.form.FormPackage#getWorkflowFormCollection()
 * @model
 * @generated
 */
public interface WorkflowFormCollection extends FormCollection {
	/**
	 * Returns the value of the '<em><b>Linked process</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Linked process</em>' reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Linked process</em>' reference.
	 * @see #setLinked_process(com.bluexml.side.workflow.Process)
	 * @see com.bluexml.side.form.FormPackage#getWorkflowFormCollection_Linked_process()
	 * @model
	 * @generated
	 */
	com.bluexml.side.workflow.Process getLinked_process();

	/**
	 * Sets the value of the '{@link com.bluexml.side.form.WorkflowFormCollection#getLinked_process <em>Linked process</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Linked process</em>' reference.
	 * @see #getLinked_process()
	 * @generated
	 */
	void setLinked_process(com.bluexml.side.workflow.Process value);
		
} // WorkflowFormCollection
