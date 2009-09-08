/*******************************************************************************
 * No CopyrightText Defined in the configurator file.
 ******************************************************************************/
package com.bluexml.side.Workflow.modeler.editor;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.core.resources.IProject;
import org.eclipse.core.resources.ProjectScope;
import org.eclipse.core.runtime.Platform;
import org.eclipse.gef.ContextMenuProvider;
import org.eclipse.gef.ui.actions.ActionRegistry;
import org.eclipse.jface.action.IAction;
import org.eclipse.jface.preference.IPreferenceStore;
import org.eclipse.jface.viewers.ISelection;
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

import com.bluexml.side.Workflow.modeler.WorkflowPlugin;
import com.bluexml.side.Workflow.modeler.actions.popup.ShowFormAction;

/**
 * Generated Model editor
 *
 * @generated
 */
public class WorkflowEditor extends Modeler {

	public static final String EDITOR_ID = "com.bluexml.side.Workflow.modeler.editor.WorkflowEditor";

	/**
	 * @see org.topcased.modeler.editor.Modeler#getAdapterFactories()
	 * @generated
	 */
	protected List getAdapterFactories() {
		List factories = new ArrayList();
		factories
				.add(new com.bluexml.side.workflow.provider.WorkflowItemProviderAdapterFactory());
		factories
				.add(new com.bluexml.side.Workflow.modeler.providers.WorkflowModelerProviderAdapterFactory());

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
					.nodeExists(WorkflowPlugin.getId())) {
				return new ScopedPreferenceStore(new ProjectScope(project),
						WorkflowPlugin.getId());
			}
		} catch (BackingStoreException e) {
			e.printStackTrace();
		}
		return WorkflowPlugin.getDefault().getPreferenceStore();
	}
	
	@Override
	protected void createActions() {
		super.createActions();
		ActionRegistry registry = getActionRegistry();
		
		IAction action = new ShowFormAction((IWorkbenchPart) this);
		registry.registerAction(action);
	}
	
	@Override
	protected ContextMenuProvider getContextMenuProvider(
			ModelerGraphicalViewer viewer) {
		return new ModelerContextMenuProvider(this, viewer, getActionRegistry());
	}
	
	@Override
	public void selectionChanged(IWorkbenchPart part, ISelection selection) {
		super.selectionChanged(part, selection);
		
		ActionRegistry registry = getActionRegistry();
		ShowFormAction SFaction = (ShowFormAction) registry.getAction(ShowFormAction.ID);
		if (SFaction != null)
			SFaction.setSelection(selection);
	}

}
