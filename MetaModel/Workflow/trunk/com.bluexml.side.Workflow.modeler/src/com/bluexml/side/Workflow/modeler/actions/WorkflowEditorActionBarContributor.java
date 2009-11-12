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