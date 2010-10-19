package com.bluexml.side.Portal.modeler.editor;

import org.eclipse.gef.ContextMenuProvider;
import org.eclipse.gef.EditPartViewer;
import org.eclipse.gef.ui.actions.ActionRegistry;
import org.eclipse.gef.ui.actions.GEFActionConstants;
import org.eclipse.jface.action.IAction;
import org.eclipse.jface.action.IMenuManager;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.StructuredSelection;

import com.bluexml.side.Portal.modeler.diagram.actions.DeleteLinkGeneratedPortel;
import com.bluexml.side.Portal.modeler.diagram.actions.DeleteLinkPageLayoutAction;
import com.bluexml.side.Portal.modeler.diagram.actions.ShowFormAction;
import com.bluexml.side.Portal.modeler.diagram.actions.ShowViewAction;

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
		
		action = actionRegistry.getAction(ShowFormAction.ID);
		if (action.isEnabled()) {
			manager.appendToGroup(GEFActionConstants.GROUP_VIEW, action);
		}

		action = actionRegistry.getAction(DeleteLinkPageLayoutAction.ID);
		if (action.isEnabled()) {
			manager.prependToGroup(GEFActionConstants.GROUP_EDIT, action);
		}

		action = actionRegistry.getAction(DeleteLinkGeneratedPortel.ID);
		if (action.isEnabled()) {
			manager.prependToGroup(GEFActionConstants.GROUP_EDIT, action);
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

	public static boolean checkAllElements(ISelection selection, Class<?> className) {
		System.out.println("ModelerContextMenuProvider.checkAllElements() Selection :"+selection+" class :"+className.getName());
		if (selection == null) {
			System.out.println("ModelerContextMenuProvider.checkAllElements() selection is null");
			return false;
		}
		if (selection instanceof StructuredSelection) {
			StructuredSelection ss = (StructuredSelection) selection;
			for (Object o : ss.toList()) {
				if (!o.getClass().equals(className)) {
					System.out.println("ModelerContextMenuProvider.checkAllElements() selection is not right class");
					System.err.println("selected object :"+o.getClass().getName());
					System.err.println("Expected class :"+className.getName());
					return false;
				}
			}
		}
		return true;
	}
}
