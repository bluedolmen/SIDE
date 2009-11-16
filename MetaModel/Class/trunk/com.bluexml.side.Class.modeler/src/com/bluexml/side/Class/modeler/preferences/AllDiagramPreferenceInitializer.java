/*******************************************************************************
 * 
 ******************************************************************************/
package com.bluexml.side.Class.modeler.preferences;

import org.eclipse.core.runtime.Preferences;
import org.topcased.modeler.preferences.AbstractTopcasedPreferenceInitializer;

import com.bluexml.side.Class.modeler.ClazzPlugin;
import com.bluexml.side.Class.modeler.editor.ClazzEditor;

/**
 * Initialize preferences to all Clazz diagrams.
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
		return ClazzPlugin.getDefault().getPluginPreferences();
	}

	/**
	 * @see org.topcased.modeler.preferences.AbstractTopcasedPreferenceInitializer#getEditorId()
	 * @generated
	 */
	protected String getEditorId() {
		return ClazzEditor.EDITOR_ID;
	}
}