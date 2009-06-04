/*******************************************************************************
 * No CopyrightText Defined in the configurator file.
 ******************************************************************************/
package com.bluexml.side.Requirements.modeler.goalDiagram.preferences;

import org.topcased.modeler.preferences.AbstractNodePreferencePage;

import com.bluexml.side.Requirements.modeler.RequirementsPlugin;

/**
 * This class represents a preference page that is contributed to the Preferences dialog. This page is used to modify
 * preferences only. They are stored in the preference store that belongs to the main plug-in class. That way,
 * preferences can be accessed directly via the preference store.
 *
 * @generated
 */
public class PrivilegeGroupPreferencePage extends AbstractNodePreferencePage {
	/**
	 * @see org.topcased.modeler.preferences.AbstractNodePreferencePage#getNodeBackgroundColor()
	 * @generated
	 */
	protected String getNodeBackgroundColor() {
		return ReqDiagramPreferenceConstants.PRIVILEGEGROUP_DEFAULT_BACKGROUND_COLOR;
	}

	/**
	 * @see org.topcased.modeler.preferences.AbstractNodePreferencePage#getNodeFont()
	 * @generated
	 */
	protected String getNodeFont() {
		return ReqDiagramPreferenceConstants.PRIVILEGEGROUP_DEFAULT_FONT;
	}

	/**
	 * @see org.topcased.modeler.preferences.AbstractNodePreferencePage#getNodeForegroundColor()
	 * @generated
	 */
	protected String getNodeForegroundColor() {
		return ReqDiagramPreferenceConstants.PRIVILEGEGROUP_DEFAULT_FOREGROUND_COLOR;
	}

	/**
	 * @see org.topcased.facilities.preferences.AbstractTopcasedPreferencePage#getBundleId()
	 * @generated
	 */
	protected String getBundleId() {
		return RequirementsPlugin.getId();
	}

}
