/*******************************************************************************
 * 	Copyright (C) BlueXML 2005-2008
 *
 * This program is free software; you can redistribute it and/or modify
 *  it under the terms of the GNU General Public License as published by
 *  the Free Software Foundation; either version 2.
 * 
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 * 
 * You should have received a copy of the GNU General Public License
 * along with this program; if not, write to the Free Software
 * Foundation, Inc., 59 Temple Place, Boston, MA 02111.
 ******************************************************************************/
package com.bluexml.side.Workflow.modeler.diagram.commands.update;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.eclipse.gef.commands.Command;

import com.bluexml.side.Workflow.modeler.diagram.dialogs.ActionEditDialog;
import com.bluexml.side.Workflow.modeler.diagram.dialogs.VariableDataStructure;
import com.bluexml.side.workflow.ProcessState;
import com.bluexml.side.workflow.Variable;
import com.bluexml.side.workflow.WorkflowFactory;

/**
 * Class that create a command in order to update task parameters <br>
 */
public class ProcessStateUpdateCommand extends Command {

	/** The task currently updated */
	private ProcessState pState;

	/** Map containing new association data */
	protected Map<String, Object> newAssociationData;
	
	/**
	 * Create a command for updating parameters on a given task
	 */
	public ProcessStateUpdateCommand(ProcessState state, Map<String,Object> data) {
		this.pState = state;
		this.newAssociationData = data;
	}

	/**
	 * Get the old values and set the new ones
	 * 
	 * @see org.eclipse.gef.commands.Command#execute()
	 */
	public void execute() {
		redo();
	}

	/**
	 * Set the new values
	 * 
	 * @see org.eclipse.gef.commands.Command#redo()
	 */
	public void redo() {
		// Perform update for variable
		VariableDataStructure variables = (VariableDataStructure) newAssociationData.get(ActionEditDialog.ACTION_VARIABLE);
		List<Object> newVariables = new ArrayList<Object>();
		Iterator<Object> iterator = (Iterator<Object>) variables.getData().iterator();
		while (iterator.hasNext()) {
			Object object = iterator.next();
			String name = variables.getDisplayName(object);
			String access = variables.getDisplayAccess(object);
			String mname = variables.getDisplayMappedName(object);
			Variable var = WorkflowFactory.eINSTANCE.createVariable();
			var.setName(name);
			var.setAccess(access);
			var.setMappedName(mname);
			newVariables.add(var);
		}
		pState.getVariable().clear();
		pState.getVariable().addAll((Collection<? extends Variable>) newVariables);
	}
}
