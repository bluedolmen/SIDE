/*******************************************************************************
 * Copyright (c) 2005 AIRBUS FRANCE.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *    David Sciamma (Anyware Technologies), Mathieu Garcia (Anyware Technologies),
 *    Jacques Lescot (Anyware Technologies) - initial API and implementation
 *******************************************************************************/
package com.bluexml.side.Portal.modeler;

import org.eclipse.gef.EditPartViewer;
import org.eclipse.gef.ui.actions.ActionRegistry;
import org.eclipse.gef.ui.actions.GEFActionConstants;
import org.eclipse.jface.action.IAction;
import org.eclipse.jface.action.IMenuManager;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.StructuredSelection;
import org.topcased.modeler.editor.ModelerContextMenuProvider;

import com.bluexml.side.Portal.modeler.diagram.actions.DeleteLinkPageLayoutAction;
import com.bluexml.side.Portal.modeler.diagram.edit.UseLayoutEditPart;

/**
 * Provide a Context Menu for the SAM Editor with customized actions
 * 
 */
public class SidePortalContextMenuProvider extends ModelerContextMenuProvider {
	/**
	 * Constructs a context menu for the specified EditPartViewer and the
	 * Actions registered in the ActionRegistry
	 * 
	 * @param viewer
	 *            the EditPartViewer
	 * @param registry
	 *            the ActionRegistry
	 */
	public SidePortalContextMenuProvider(EditPartViewer viewer,
			ActionRegistry registry) {
		super(viewer, registry);
	}

	/**
	 * Called when the menu is about to show. Construct the context menu by
	 * adding actions common to all editparts.
	 * 
	 * @see org.eclipse.gef.ContextMenuProvider#buildContextMenu(org.eclipse.jface.action.IMenuManager)
	 */
	public void buildContextMenu(IMenuManager manager) {
		super.buildContextMenu(manager);

		// UseLayout
		if (checkAllElements(getViewer().getSelection(),
				UseLayoutEditPart.class)) {
			IAction action = getActionRegistry().getAction(
					DeleteLinkPageLayoutAction.ID);
			if (action.isEnabled()) {
				manager.appendToGroup(GEFActionConstants.GROUP_EDIT, action);
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

}
