/*******************************************************************************
 * No CopyrightText Defined in the configurator file.
 ******************************************************************************/
package com.bluexml.side.Requirements.modeler.preferences;

import org.eclipse.core.runtime.Preferences;
import org.topcased.modeler.preferences.AbstractTopcasedPreferenceInitializer;

import com.bluexml.side.Requirements.modeler.RequirementsPlugin;
import com.bluexml.side.Requirements.modeler.editor.RequirementsEditor;

/**
 * Initialize preferences to all Requirements diagrams.
 * @generated
 */
public class AllDiagramPreferenceInitializer extends
		AbstractTopcasedPreferenceInitializer {

	/**
	 * @see org.topcased.modeler.preferences.AbstractTopcasedPreferenceInitializer#getPreferences()
	 * @generated
	 */
	@Override
	protected Preferences getPreferences() {
		return RequirementsPlugin.getDefault().getPluginPreferences();
	}

	/**
	 * @see org.topcased.modeler.preferences.AbstractTopcasedPreferenceInitializer#getEditorId()
	 * @generated
	 */
	protected String getEditorId() {
		return RequirementsEditor.EDITOR_ID;
	}
}