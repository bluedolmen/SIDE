package com.bluexml.side.Class.modeler.editor;

import org.eclipse.gef.ContextMenuProvider;
import org.eclipse.gef.EditPartViewer;
import org.eclipse.gef.ui.actions.ActionRegistry;
import org.eclipse.gef.ui.actions.GEFActionConstants;
import org.eclipse.jface.action.IAction;
import org.eclipse.jface.action.IMenuManager;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.StructuredSelection;

import com.bluexml.side.Class.modeler.actions.ImportEumFromTSV;
import com.bluexml.side.Class.modeler.actions.ShowFormAction;
import com.bluexml.side.Class.modeler.actions.ShowViewAction;
import com.bluexml.side.Class.modeler.diagram.actions.DeleteLinkClassAspectAction;
import com.bluexml.side.Class.modeler.diagram.actions.DeleteLinkClassGeneralizationAction;
import com.bluexml.side.Class.modeler.diagram.actions.DeleteLinkEnumerationDependsAction;
import com.bluexml.side.Class.modeler.diagram.edit.GeneralizationEditPart;
import com.bluexml.side.Class.modeler.diagram.edit.dependsEditPart;
import com.bluexml.side.Class.modeler.diagram.edit.hasAspectEditPart;

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
		org.topcased.modeler.editor.ModelerContextMenuProvider p = new org.topcased.modeler.editor.ModelerContextMenuProvider(getViewer(), actionRegistry);
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

		// HasAspect
		if (checkAllElements(getViewer().getSelection(), hasAspectEditPart.class)) {
			action = actionRegistry.getAction(DeleteLinkClassAspectAction.ID);
			if (action.isEnabled()) {
				manager.prependToGroup(GEFActionConstants.GROUP_EDIT, action);
			}
		}

		// Depends of
		if (checkAllElements(getViewer().getSelection(), dependsEditPart.class)) {
			action = actionRegistry.getAction(DeleteLinkEnumerationDependsAction.ID);
			if (action.isEnabled()) {
				manager.prependToGroup(GEFActionConstants.GROUP_EDIT, action);
			}
		}

		// Unlink Generalization
		if (checkAllElements(getViewer().getSelection(), GeneralizationEditPart.class)) {
			action = actionRegistry.getAction(DeleteLinkClassGeneralizationAction.ID);
			if (action.isEnabled()) {
				manager.prependToGroup(GEFActionConstants.GROUP_EDIT, action);
			}
		}
		
	}

	private boolean checkAllElements(ISelection selection, Class className) {
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
