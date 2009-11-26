package com.bluexml.side.Class.modeler.editor;

import org.eclipse.gef.ContextMenuProvider;
import org.eclipse.gef.EditPartViewer;
import org.eclipse.gef.ui.actions.ActionRegistry;
import org.eclipse.gef.ui.actions.GEFActionConstants;
import org.eclipse.jface.action.IAction;
import org.eclipse.jface.action.IMenuManager;

import com.bluexml.side.Class.modeler.actions.ImportEumFromTSV;
import com.bluexml.side.Class.modeler.actions.ShowFormAction;
import com.bluexml.side.Class.modeler.actions.ShowViewAction;

public class ModelerContextMenuProvider extends ContextMenuProvider {

	private ActionRegistry actionRegistry;

	public ModelerContextMenuProvider(EditPartViewer viewer) {
		super(viewer);
	}

	public ModelerContextMenuProvider(EditPartViewer viewer, ActionRegistry registry) {
		super(viewer);
		setActionRegistry(registry);
	}

	@Override
	public void buildContextMenu(IMenuManager manager) {
		org.topcased.modeler.editor.ModelerContextMenuProvider p = new org.topcased.modeler.editor.ModelerContextMenuProvider(
				getViewer(), actionRegistry);
		p.buildContextMenu(manager);

		IAction action;

		action = actionRegistry.getAction(ShowFormAction.ID);
		if (action.isEnabled())
			manager.appendToGroup(GEFActionConstants.GROUP_VIEW, action);
		
		action = actionRegistry.getAction(ShowViewAction.ID);
		if (action.isEnabled())
			manager.appendToGroup(GEFActionConstants.GROUP_VIEW, action);
		
		action = actionRegistry.getAction(ImportEumFromTSV.ID);
		if (action.isEnabled())
			manager.appendToGroup(GEFActionConstants.GROUP_VIEW, action);
		
	}

	/**
	 * Set the ActionRegistry for this ContextMenuProvider
	 * 
	 * @param registry
	 *            the ActionRegistry
	 */
	protected void setActionRegistry(ActionRegistry registry) {
		actionRegistry = registry;
	}

}
