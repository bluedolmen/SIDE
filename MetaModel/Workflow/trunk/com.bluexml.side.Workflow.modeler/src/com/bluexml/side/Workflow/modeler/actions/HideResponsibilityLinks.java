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
