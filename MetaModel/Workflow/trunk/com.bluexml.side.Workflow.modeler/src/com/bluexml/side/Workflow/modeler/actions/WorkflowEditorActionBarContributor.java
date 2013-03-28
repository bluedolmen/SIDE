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
 * No CopyrightText Defined in the configurator file.
 ******************************************************************************/
package com.bluexml.side.Workflow.modeler.actions;

import org.eclipse.jface.action.IToolBarManager;
import org.eclipse.ui.actions.RetargetAction;
import org.topcased.modeler.actions.ModelerActionBarContributor;

import com.bluexml.side.Workflow.modeler.WorkflowImageRegistry;
import com.bluexml.side.Workflow.modeler.WorkflowPlugin;

/**
 * Generated Actions
 *
 * @not_generated
 */
public class WorkflowEditorActionBarContributor extends
		ModelerActionBarContributor {
	
	@Override
	protected void buildActions() {
		super.buildActions();
		
		//Add action to show/hide responsibility links
		RetargetAction action = new RetargetAction(HideResponsibilityLinks.actionID, WorkflowPlugin.Messages.getString("HideResponsibilityLinks.1"));
        action.setImageDescriptor(WorkflowImageRegistry.getImageDescriptor("HIDERESPONSIBILITYLINKS"));
        addRetargetAction(action);
	}
	
	@Override
	public void contributeToToolBar(IToolBarManager toolBarManager) {
		super.contributeToToolBar(toolBarManager);
		
		toolBarManager.add(getAction(HideResponsibilityLinks.actionID));
	}
}
