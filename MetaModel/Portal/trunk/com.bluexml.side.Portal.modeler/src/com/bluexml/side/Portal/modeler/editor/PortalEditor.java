/*******************************************************************************
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
package com.bluexml.side.Portal.modeler.editor;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.core.resources.IProject;
import org.eclipse.core.resources.ProjectScope;
import org.eclipse.core.runtime.Platform;
import org.eclipse.gef.ContextMenuProvider;
import org.eclipse.gef.ui.actions.ActionRegistry;
import org.eclipse.jface.action.IAction;
import org.eclipse.jface.preference.IPreferenceStore;
import org.eclipse.jface.viewers.ISelectionChangedListener;
import org.eclipse.ui.IFileEditorInput;
import org.eclipse.ui.IWorkbenchPart;
import org.eclipse.ui.preferences.ScopedPreferenceStore;
import org.osgi.service.prefs.BackingStoreException;
import org.osgi.service.prefs.Preferences;
import org.topcased.modeler.commands.GEFtoEMFCommandStackWrapper;
import org.topcased.modeler.documentation.EAnnotationDocPage;
import org.topcased.modeler.documentation.IDocPage;
import org.topcased.modeler.editor.Modeler;
import org.topcased.modeler.editor.ModelerGraphicalViewer;

import com.bluexml.side.Portal.modeler.PortalPlugin;
import com.bluexml.side.Portal.modeler.actions.ShowViewAction;
import com.bluexml.side.Portal.modeler.diagram.actions.DeleteLinkGeneratedPortel;
import com.bluexml.side.Portal.modeler.diagram.actions.DeleteLinkPageLayoutAction;

/**
 * Generated Model editor
 *
 * @generated
 */
public class PortalEditor extends Modeler {

	public static final String EDITOR_ID = "com.bluexml.side.Portal.modeler.editor.PortalEditor";

	/**
	 * @see org.topcased.modeler.editor.Modeler#getAdapterFactories()
	 * @generated
	 */
	protected List getAdapterFactories() {
		List factories = new ArrayList();
		factories.add(new com.bluexml.side.portal.provider.PortalItemProviderAdapterFactory());
		factories.add(new com.bluexml.side.Portal.modeler.providers.PortalModelerProviderAdapterFactory());

		factories.addAll(super.getAdapterFactories());

		return factories;
	}

	/**
	 * @see org.topcased.modeler.editor.Modeler#getId()
	 * @generated
	 */
	public String getId() {
		return EDITOR_ID;
	}

	/**
	 * @see org.topcased.modeler.editor.Modeler#getAdapter(java.lang.Class)
	 * @generated
	 */
	public Object getAdapter(Class type) {
		if (type == IDocPage.class) {
			GEFtoEMFCommandStackWrapper stack = new GEFtoEMFCommandStackWrapper(getCommandStack());
			return new EAnnotationDocPage(stack);
		}
		return super.getAdapter(type);
	}

	/**
	 * @see org.topcased.modeler.editor.Modeler#getPreferenceStore()
	 *
	 * @generated
	 */
	public IPreferenceStore getPreferenceStore() {
		IProject project = (((IFileEditorInput) getEditorInput()).getFile()).getProject();

		Preferences root = Platform.getPreferencesService().getRootNode();
		try {
			if (root.node(ProjectScope.SCOPE).node(project.getName()).nodeExists(PortalPlugin.getId())) {
				return new ScopedPreferenceStore(new ProjectScope(project), PortalPlugin.getId());
			}
		} catch (BackingStoreException e) {
			e.printStackTrace();
		}
		return PortalPlugin.getDefault().getPreferenceStore();
	}
	
	/**
     * @see org.topcased.modeler.editor.Modeler#getContextMenuProvider(org.topcased.modeler.editor.ModelerGraphicalViewer)
     */
    protected ContextMenuProvider getContextMenuProvider(ModelerGraphicalViewer viewer)
    {
        return new ModelerContextMenuProvider(viewer, getActionRegistry());
    }
	
	@Override
    protected void createActions()
    {
        super.createActions();
        ActionRegistry registry = getActionRegistry();
        // Delete link between Page and Layout
        DeleteLinkPageLayoutAction deleteLinkToLayout = new DeleteLinkPageLayoutAction(this);
        registry.registerAction(deleteLinkToLayout);
        getSelectionActions().add(deleteLinkToLayout.getId());
        
        DeleteLinkGeneratedPortel deleteGenPort = new DeleteLinkGeneratedPortel(this);
        registry.registerAction(deleteGenPort);
        getSelectionActions().add(deleteGenPort.getId());
        
        IAction action = new ShowViewAction((IWorkbenchPart) this);
		registry.registerAction(action);
    }

	@Override
    protected void configureGraphicalViewer()
    {
        super.configureGraphicalViewer();
        IAction exampleAction = getActionRegistry().getAction(DeleteLinkPageLayoutAction.ID);
        getGraphicalViewer().addSelectionChangedListener((ISelectionChangedListener) exampleAction);
        exampleAction = getActionRegistry().getAction(DeleteLinkGeneratedPortel.ID);
        getGraphicalViewer().addSelectionChangedListener((ISelectionChangedListener) exampleAction);
        
    }
}
