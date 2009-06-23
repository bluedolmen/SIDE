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
package com.bluexml.side.Class.modeler.diagram.preferences;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.jface.preference.IPreferenceStore;
import org.topcased.modeler.preferences.AbstractEdgePreferencePage;

import com.bluexml.side.Class.modeler.ClazzPlugin;

/**
 * This class represents a preference page that is contributed to the Preferences dialog. This page is used to modify
 * preferences only. They are stored in the preference store that belongs to the main plug-in class. That way,
 * preferences can be accessed directly via the preference store.
 *
 * @generated
 */
public class includePreferencePage extends AbstractEdgePreferencePage {
	/**
	 * @see org.topcased.modeler.preferences.AbstractEdgePreferencePage#getEdgeFont()
	 * @generated
	 */
	protected String getEdgeFont() {
		return CdDiagramPreferenceConstants.INCLUDE_EDGE_DEFAULT_FONT;
	}

	/**
	 * @see org.topcased.modeler.preferences.AbstractEdgePreferencePage#getEdgeForegroundColor()
	 * @generated
	 */
	protected String getEdgeForegroundColor() {
		return CdDiagramPreferenceConstants.INCLUDE_EDGE_DEFAULT_FOREGROUND_COLOR;
	}

	/**
	 * @see org.topcased.modeler.preferences.AbstractEdgePreferencePage#getEdgeRouter()
	 * @generated
	 */
	protected String getEdgeRouter() {
		return CdDiagramPreferenceConstants.INCLUDE_EDGE_DEFAULT_ROUTER;
	}

	/**
	 * @see org.topcased.modeler.preferences.AbstractEdgePreferencePage#getHiddenElements()
	 * @generated
	 */
	protected List<String> getHiddenElements() {
		List<String> choiceOfValues = new ArrayList<String>();
		IPreferenceStore ps = getPreferenceStore();
		return choiceOfValues;
	}

	/**
	 * @see org.topcased.modeler.preferences.AbstractEdgePreferencePage#getDefaultHiddenElements()
	 * @generated
	 */
	protected List<String> getDefaultHiddenElements() {
		List<String> choiceOfValues = new ArrayList<String>();
		IPreferenceStore ps = getPreferenceStore();
		return choiceOfValues;
	}

	/**
	 * @see org.topcased.modeler.preferences.AbstractEdgePreferencePage#getVisibleElements()
	 * @generated
	 */
	protected List<String> getVisibleElements() {
		List<String> choiceOfValues = new ArrayList<String>();
		IPreferenceStore ps = getPreferenceStore();
		return choiceOfValues;
	}

	/**
	 * @see org.topcased.modeler.preferences.AbstractEdgePreferencePage#getDefaultVisibleElements()
	 * @generated
	 */
	protected List<String> getDefaultVisibleElements() {
		List<String> choiceOfValues = new ArrayList<String>();
		IPreferenceStore ps = getPreferenceStore();
		return choiceOfValues;
	}

	/**
	 * @see org.topcased.modeler.preferences.AbstractEdgePreferencePage#storeEdgeObjectVisibility(java.util.List)
	 * @generated
	 */
	protected void storeEdgeObjectVisibility(List visibleElement) {
		IPreferenceStore ps = getPreferenceStore();
	}

	/**
	 * @see org.topcased.facilities.preferences.AbstractTopcasedPreferencePage#getBundleId()
	 * @generated
	 */
	protected String getBundleId() {
		return ClazzPlugin.getId();
	}

}
