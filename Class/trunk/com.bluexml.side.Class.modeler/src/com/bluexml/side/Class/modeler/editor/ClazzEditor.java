/*******************************************************************************
 * 
 ******************************************************************************/
package com.bluexml.side.Class.modeler.editor;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.core.resources.IProject;
import org.eclipse.core.resources.ProjectScope;
import org.eclipse.core.runtime.Platform;
import org.eclipse.gef.ContextMenuProvider;
import org.eclipse.gef.DefaultEditDomain;
import org.eclipse.gef.GraphicalViewer;
import org.eclipse.gef.ui.actions.ActionRegistry;
import org.eclipse.gef.ui.palette.PaletteViewer;
import org.eclipse.jface.action.IAction;
import org.eclipse.jface.preference.IPreferenceStore;
import org.eclipse.jface.viewers.ISelectionChangedListener;
import org.eclipse.ui.IFileEditorInput;
import org.eclipse.ui.preferences.ScopedPreferenceStore;
import org.osgi.service.prefs.BackingStoreException;
import org.osgi.service.prefs.Preferences;
import org.topcased.modeler.commands.GEFtoEMFCommandStackWrapper;
import org.topcased.modeler.documentation.EAnnotationDocPage;
import org.topcased.modeler.documentation.IDocPage;
import org.topcased.modeler.editor.Modeler;
import org.topcased.modeler.editor.ModelerGraphicalViewer;

import com.bluexml.side.Class.modeler.ClazzPlugin;
import com.bluexml.side.Class.modeler.SideClassContextMenuProvider;
import com.bluexml.side.Class.modeler.diagram.actions.DeleteLinkClassGeneralizationAction;
import com.bluexml.side.Class.modeler.diagram.actions.DeleteLinkEnumerationDependsAction;
import com.bluexml.side.Class.modeler.diagram.actions.DeleteLinkClassAspectAction;
import com.bluexml.side.Class.modeler.diagram.actions.DeleteLinkClassViewAction;

/**
 * Generated Model editor
 *
 * @generated
 */
public class ClazzEditor extends Modeler {

	public static final String EDITOR_ID = "com.bluexml.side.Class.modeler.editor.ClazzEditor";

	/**
	 * @see org.topcased.modeler.editor.Modeler#getAdapterFactories()
	 * @generated
	 */
	protected List getAdapterFactories() {
		List factories = new ArrayList();
		factories
				.add(new com.bluexml.side.clazz.provider.ClazzItemProviderAdapterFactory());
		factories
				.add(new com.bluexml.side.Class.modeler.providers.ClazzModelerProviderAdapterFactory());

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
			GEFtoEMFCommandStackWrapper stack = new GEFtoEMFCommandStackWrapper(
					getCommandStack());
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
		IProject project = (((IFileEditorInput) getEditorInput()).getFile())
				.getProject();

		Preferences root = Platform.getPreferencesService().getRootNode();
		try {
			if (root.node(ProjectScope.SCOPE).node(project.getName())
					.nodeExists(ClazzPlugin.getId())) {
				return new ScopedPreferenceStore(new ProjectScope(project),
						ClazzPlugin.getId());
			}
		} catch (BackingStoreException e) {
			e.printStackTrace();
		}
		return ClazzPlugin.getDefault().getPreferenceStore();
	}
	
	/**
     * @see org.topcased.modeler.editor.Modeler#getContextMenuProvider(org.topcased.modeler.editor.ModelerGraphicalViewer)
     */
    protected ContextMenuProvider getContextMenuProvider(ModelerGraphicalViewer viewer)
    {
        return new SideClassContextMenuProvider(viewer, getActionRegistry());
    }
    
    @Override
    protected void createActions()
    {
        super.createActions();

        ActionRegistry registry = getActionRegistry();
        
        // Delete link between Class and View
        DeleteLinkClassViewAction deleteLinkClassViewAction = new DeleteLinkClassViewAction(this);
        registry.registerAction(deleteLinkClassViewAction);
        getSelectionActions().add(deleteLinkClassViewAction.getId());
        
        // Delete link between Class and Aspect
        DeleteLinkClassAspectAction deleteLinkToAspect = new DeleteLinkClassAspectAction(this);
        registry.registerAction(deleteLinkToAspect);
        getSelectionActions().add(deleteLinkToAspect.getId());
        
        // Delete link between Enumerations (depends)
        DeleteLinkEnumerationDependsAction deleteEnumerationDependsAction = new DeleteLinkEnumerationDependsAction(this);
        registry.registerAction(deleteEnumerationDependsAction);
        getSelectionActions().add(deleteEnumerationDependsAction.getId());
        
        // Delete generalization between Class
        DeleteLinkClassGeneralizationAction deleteLinkClassGeneralizationAction = new DeleteLinkClassGeneralizationAction(this);
        registry.registerAction(deleteLinkClassGeneralizationAction);
        getSelectionActions().add(deleteLinkClassGeneralizationAction.getId());
    }
    
    @Override
    protected void configureGraphicalViewer()
    {
        super.configureGraphicalViewer();

        IAction deleteLinkClassViewAction = getActionRegistry().getAction(DeleteLinkClassViewAction.ID);
        getGraphicalViewer().addSelectionChangedListener((ISelectionChangedListener) deleteLinkClassViewAction);
        
        IAction deleteLinkToAspect = getActionRegistry().getAction(DeleteLinkClassAspectAction.ID);
        getGraphicalViewer().addSelectionChangedListener((ISelectionChangedListener) deleteLinkToAspect);
        
        IAction deleteEnumerationDependsAction = getActionRegistry().getAction(DeleteLinkEnumerationDependsAction.ID);
        getGraphicalViewer().addSelectionChangedListener((ISelectionChangedListener) deleteEnumerationDependsAction);
        
        IAction deleteLinkClassGeneralizationAction = getActionRegistry().getAction(DeleteLinkClassGeneralizationAction.ID);
        getGraphicalViewer().addSelectionChangedListener((ISelectionChangedListener) deleteLinkClassGeneralizationAction);
    }
    
    public void intializeExport(GraphicalViewer viewer) {
		setEditDomain((DefaultEditDomain) viewer.getEditDomain());
		getEditDomain().setPaletteViewer(new PaletteViewer());
		setGraphicalViewer(viewer);
	}
}
