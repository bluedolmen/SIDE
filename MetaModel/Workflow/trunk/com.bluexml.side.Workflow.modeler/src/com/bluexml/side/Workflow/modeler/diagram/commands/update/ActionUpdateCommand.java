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
import com.bluexml.side.workflow.Action;
import com.bluexml.side.workflow.Script;
import com.bluexml.side.workflow.Variable;
import com.bluexml.side.workflow.WorkflowFactory;

/**
 * Class that create a command in order to update task parameters <br>
 */
public class ActionUpdateCommand extends Command {

	/** The task currently updated */
	private Action action;

	/** Map containing new association data */
	protected Map<String, Object> newAssociationData;

	/**
	 * Create a command for updating parameters on a given task
	 */
	public ActionUpdateCommand(Action action, Map<String, Object> data) {
		this.action = action;
		newAssociationData = data;
	}

	/**
	 * Get the old values and set the new ones
	 * 
	 * @see org.eclipse.gef.commands.Command#execute()
	 */
	@Override
	public void execute() {
		redo();
	}

	/**
	 * Set the new values
	 * 
	 * @see org.eclipse.gef.commands.Command#redo()
	 */
	@Override
	public void redo() {
		boolean script = (Boolean) newAssociationData.get(ActionEditDialog.ACTION_SCRIPT);
		if (script) {
			Script s = null;
			if (action.getScript().size() == 0) {
				action.getScript().add(WorkflowFactory.eINSTANCE.createScript());
			}
			s = action.getScript().get(0);
			s.setExpression((String) newAssociationData.get(ActionEditDialog.ACTION_SCRIPT_TXT));
			// Perform update for variable
			VariableDataStructure variables = (VariableDataStructure) newAssociationData.get(ActionEditDialog.ACTION_VARIABLE);
			List<Object> newVariables = new ArrayList<Object>();
			Iterator<Object> iterator = (Iterator<Object>) variables.getData().iterator();
			while (iterator.hasNext()) {
				Object object = iterator.next();
				String name = variables.getDisplayName(object);
				String access = variables.getDisplayAccess(object);
				Variable var = WorkflowFactory.eINSTANCE.createVariable();
				var.setName(name);
				var.setAccess(access);
				newVariables.add(var);
			}
			s.getVariable().clear();
			s.getVariable().addAll((Collection<? extends Variable>) newVariables);
			action.setExpression(null);
		} else {
			action.setExpression((String) newAssociationData.get(ActionEditDialog.ACTION_SCRIPT_TXT));
			action.getScript().clear();
		}

		action.setJavaClass((String) newAssociationData.get(ActionEditDialog.ACTION_JAVA_CLASS));

	}
}
