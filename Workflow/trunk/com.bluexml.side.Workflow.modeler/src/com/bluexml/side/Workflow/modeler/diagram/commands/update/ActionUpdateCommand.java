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

import java.util.Map;

import org.eclipse.gef.commands.Command;

import com.bluexml.side.Workflow.modeler.diagram.dialogs.ActionEditDialog;
import com.bluexml.side.workflow.Action;
import com.bluexml.side.workflow.Script;
import com.bluexml.side.workflow.WorkflowFactory;

/**
 * Class that create a command in order to update task parameters <br>
 */
public class ActionUpdateCommand extends Command {

	/** The task currently updated */
	private Action action;

	/** Map containing new association data */
	protected Map<String,String> newAssociationData;
	
	/**
	 * Create a command for updating parameters on a given task
	 */
	public ActionUpdateCommand(Action action, Map<String,String> data) {
		this.action = action;
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
		Script s = null;
		if (action.getScript().size() == 0)
			action.getScript().add(WorkflowFactory.eINSTANCE.createScript());
		s = action.getScript().get(0);
		s.setExpression((String) newAssociationData
				.get(ActionEditDialog.ACTION_SCRIPT));
		action.setJavaClass((String) newAssociationData
				.get(ActionEditDialog.ACTION_JAVA_CLASS));	
	}
}
