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
import org.eclipse.jface.preference.IPreferenceStore;
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
}
