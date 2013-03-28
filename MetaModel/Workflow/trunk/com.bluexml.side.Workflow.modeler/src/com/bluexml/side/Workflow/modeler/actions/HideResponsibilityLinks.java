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
package com.bluexml.side.Workflow.modeler.actions;

import org.eclipse.gef.ui.actions.WorkbenchPartAction;
import org.eclipse.jface.viewers.ISelectionChangedListener;
import org.eclipse.jface.viewers.SelectionChangedEvent;
import org.eclipse.ui.IWorkbenchPart;

import com.bluexml.side.Workflow.modeler.WorkflowImageRegistry;
import com.bluexml.side.Workflow.modeler.WorkflowPlugin;
import com.bluexml.side.Workflow.modeler.editor.WorkflowEditor;

public class HideResponsibilityLinks extends WorkbenchPartAction implements ISelectionChangedListener {

	public static String actionID = "com.bluexml.side.Workflow.modeler.actions.HideResponsibilityLinks";
	public static boolean showResponsibilityLinks = true;

	public HideResponsibilityLinks(IWorkbenchPart part) {
		super(part);
	}
	
	@Override
	protected void init() {
		super.init();
		setId(actionID);
		setToolTipText(WorkflowPlugin.Messages.getString("HideResponsibilityLinks.1"));
		setImageDescriptor(WorkflowImageRegistry.getImageDescriptor("HIDERESPONSIBILITYLINKS"));
	}
	
	@Override
	public void run() {
		super.run();
		showResponsibilityLinks = !showResponsibilityLinks;
		WorkflowEditor editor = (WorkflowEditor) getWorkbenchPart();
		editor.refreshActiveDiagram();
	}
	
	@Override
	protected boolean calculateEnabled() {
		return true;
	}

	public void selectionChanged(SelectionChangedEvent event) {
		//Nothing to do
	}
}
