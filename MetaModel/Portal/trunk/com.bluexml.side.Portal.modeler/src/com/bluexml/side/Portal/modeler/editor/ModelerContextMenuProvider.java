package com.bluexml.side.Portal.modeler.editor;

import org.eclipse.core.runtime.Assert;
import org.eclipse.gef.ContextMenuProvider;
import org.eclipse.gef.EditPartViewer;
import org.eclipse.gef.ui.actions.ActionRegistry;
import org.eclipse.gef.ui.actions.GEFActionConstants;
import org.eclipse.jface.action.IAction;
import org.eclipse.jface.action.IMenuManager;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.StructuredSelection;

import com.bluexml.side.Portal.modeler.actions.ShowViewAction;
import com.bluexml.side.Portal.modeler.diagram.actions.DeleteLinkGeneratedPortel;
import com.bluexml.side.Portal.modeler.diagram.actions.DeleteLinkPageLayoutAction;

public class ModelerContextMenuProvider extends ContextMenuProvider {

	private ActionRegistry actionRegistry;

	public ModelerContextMenuProvider(EditPartViewer viewer, ActionRegistry registry) {
		super(viewer);
		setActionRegistry(registry);
	}

	@Override
	public void buildContextMenu(IMenuManager manager) {
		org.topcased.modeler.editor.ModelerContextMenuProvider p = new org.topcased.modeler.editor.ModelerContextMenuProvider(getViewer(), actionRegistry);
		p.buildContextMenu(manager);

		IAction action;

		action = actionRegistry.getAction(ShowViewAction.ID);
		if (action.isEnabled()) {
			manager.appendToGroup(GEFActionConstants.GROUP_VIEW, action);
		}
		
		action = actionRegistry.getAction(DeleteLinkPageLayoutAction.ID);
		if (action.isEnabled()) {
			manager.prependToGroup(GEFActionConstants.GROUP_EDIT, action);
		}

		IAction action2 = actionRegistry.getAction(DeleteLinkGeneratedPortel.ID);
		if (action2.isEnabled()) {
			manager.prependToGroup(GEFActionConstants.GROUP_EDIT, action2);
		}
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

	public static boolean checkAllElements(ISelection selection, Class className) {
		Assert.isNotNull(selection);
		if (selection instanceof StructuredSelection) {
			StructuredSelection ss = (StructuredSelection) selection;
			for (Object o : ss.toList()) {
				if (!o.getClass().equals(className)) {
					return false;
				}
			}
		}
		return true;
	}
}
