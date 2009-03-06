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
package com.bluexml.side.Workflow.modeler.diagram.preferences;

import java.util.HashMap;

import org.eclipse.jface.resource.StringConverter;
import org.topcased.modeler.preferences.ITopcasedPreferenceInitializer;

/**
 * Initialize the preferences of Wf diagram
 * 
 * @generated
 */
public class WfDiagramTopcasedPreferenceInitializer implements
		ITopcasedPreferenceInitializer {

	/** 
	 * @see org.topcased.modeler.preferences.ITopcasedPreferenceInitializer#getDefaultPreference()
	 *	@generated
	 */
	public HashMap<String, String> getDefaultPreference() {
		HashMap<String, String> defaultWfPreference = new HashMap<String, String>();
		// Initialize the default value of the TASKNODE_DEFAULT_BACKGROUND_COLOR property 
		defaultWfPreference.put(
				WfDiagramPreferenceConstants.TASKNODE_DEFAULT_BACKGROUND_COLOR,
				"255,255,255");

		// Initialize the default value of the TASKNODE_DEFAULT_FOREGROUND_COLOR property
		defaultWfPreference.put(
				WfDiagramPreferenceConstants.TASKNODE_DEFAULT_FOREGROUND_COLOR,
				"0,0,0");

		// Initialize the default value of the TASKNODE_DEFAULT_FONT property
		defaultWfPreference.put(
				WfDiagramPreferenceConstants.TASKNODE_DEFAULT_FONT,
				StringConverter.asFontData("Lucida Grande-regular-11")
						.toString());

		// Initialize the default value of the NODE_DEFAULT_BACKGROUND_COLOR property 
		defaultWfPreference.put(
				WfDiagramPreferenceConstants.NODE_DEFAULT_BACKGROUND_COLOR,
				"255,255,255");

		// Initialize the default value of the NODE_DEFAULT_FOREGROUND_COLOR property
		defaultWfPreference.put(
				WfDiagramPreferenceConstants.NODE_DEFAULT_FOREGROUND_COLOR,
				"0,0,0");

		// Initialize the default value of the NODE_DEFAULT_FONT property
		defaultWfPreference.put(WfDiagramPreferenceConstants.NODE_DEFAULT_FONT,
				StringConverter.asFontData("Lucida Grande-regular-11")
						.toString());

		// Initialize the default value of the STARTSTATE_DEFAULT_BACKGROUND_COLOR property 
		defaultWfPreference
				.put(
						WfDiagramPreferenceConstants.STARTSTATE_DEFAULT_BACKGROUND_COLOR,
						"255,255,255");

		// Initialize the default value of the STARTSTATE_DEFAULT_FOREGROUND_COLOR property
		defaultWfPreference
				.put(
						WfDiagramPreferenceConstants.STARTSTATE_DEFAULT_FOREGROUND_COLOR,
						"0,0,0");

		// Initialize the default value of the STARTSTATE_DEFAULT_FONT property
		defaultWfPreference.put(
				WfDiagramPreferenceConstants.STARTSTATE_DEFAULT_FONT,
				StringConverter.asFontData("Lucida Grande-regular-11")
						.toString());

		// Initialize the default value of the ENDSTATE_DEFAULT_BACKGROUND_COLOR property 
		defaultWfPreference.put(
				WfDiagramPreferenceConstants.ENDSTATE_DEFAULT_BACKGROUND_COLOR,
				"255,255,255");

		// Initialize the default value of the ENDSTATE_DEFAULT_FOREGROUND_COLOR property
		defaultWfPreference.put(
				WfDiagramPreferenceConstants.ENDSTATE_DEFAULT_FOREGROUND_COLOR,
				"0,0,0");

		// Initialize the default value of the ENDSTATE_DEFAULT_FONT property
		defaultWfPreference.put(
				WfDiagramPreferenceConstants.ENDSTATE_DEFAULT_FONT,
				StringConverter.asFontData("Lucida Grande-regular-11")
						.toString());

		// Initialize the default value of the EVENT_DEFAULT_BACKGROUND_COLOR property 
		defaultWfPreference.put(
				WfDiagramPreferenceConstants.EVENT_DEFAULT_BACKGROUND_COLOR,
				"255,255,255");

		// Initialize the default value of the EVENT_DEFAULT_FOREGROUND_COLOR property
		defaultWfPreference.put(
				WfDiagramPreferenceConstants.EVENT_DEFAULT_FOREGROUND_COLOR,
				"0,0,0");

		// Initialize the default value of the EVENT_DEFAULT_FONT property
		defaultWfPreference.put(
				WfDiagramPreferenceConstants.EVENT_DEFAULT_FONT,
				StringConverter.asFontData("Lucida Grande-regular-11")
						.toString());

		// Initialize the default value of the ACTION_DEFAULT_BACKGROUND_COLOR property 
		defaultWfPreference.put(
				WfDiagramPreferenceConstants.ACTION_DEFAULT_BACKGROUND_COLOR,
				"166,210,210");

		// Initialize the default value of the ACTION_DEFAULT_FOREGROUND_COLOR property
		defaultWfPreference.put(
				WfDiagramPreferenceConstants.ACTION_DEFAULT_FOREGROUND_COLOR,
				"0,0,0");

		// Initialize the default value of the ACTION_DEFAULT_FONT property
		defaultWfPreference.put(
				WfDiagramPreferenceConstants.ACTION_DEFAULT_FONT,
				StringConverter.asFontData("Lucida Grande-regular-11")
						.toString());

		// Initialize the default value of the SWIMLANE_DEFAULT_BACKGROUND_COLOR property 
		defaultWfPreference.put(
				WfDiagramPreferenceConstants.SWIMLANE_DEFAULT_BACKGROUND_COLOR,
				"255,255,255");

		// Initialize the default value of the SWIMLANE_DEFAULT_FOREGROUND_COLOR property
		defaultWfPreference.put(
				WfDiagramPreferenceConstants.SWIMLANE_DEFAULT_FOREGROUND_COLOR,
				"0,0,0");

		// Initialize the default value of the SWIMLANE_DEFAULT_FONT property
		defaultWfPreference.put(
				WfDiagramPreferenceConstants.SWIMLANE_DEFAULT_FONT,
				StringConverter.asFontData("Lucida Grande-regular-11")
						.toString());

		// Initialize the default value of the DECISION_DEFAULT_BACKGROUND_COLOR property 
		defaultWfPreference.put(
				WfDiagramPreferenceConstants.DECISION_DEFAULT_BACKGROUND_COLOR,
				"211,226,205");

		// Initialize the default value of the DECISION_DEFAULT_FOREGROUND_COLOR property
		defaultWfPreference.put(
				WfDiagramPreferenceConstants.DECISION_DEFAULT_FOREGROUND_COLOR,
				"0,0,0");

		// Initialize the default value of the DECISION_DEFAULT_FONT property
		defaultWfPreference.put(
				WfDiagramPreferenceConstants.DECISION_DEFAULT_FONT,
				StringConverter.asFontData("Lucida Grande-regular-11")
						.toString());

		// Initialize the default value of the FORK_DEFAULT_BACKGROUND_COLOR property 
		defaultWfPreference.put(
				WfDiagramPreferenceConstants.FORK_DEFAULT_BACKGROUND_COLOR,
				"211,226,205");

		// Initialize the default value of the FORK_DEFAULT_FOREGROUND_COLOR property
		defaultWfPreference.put(
				WfDiagramPreferenceConstants.FORK_DEFAULT_FOREGROUND_COLOR,
				"0,0,0");

		// Initialize the default value of the FORK_DEFAULT_FONT property
		defaultWfPreference.put(WfDiagramPreferenceConstants.FORK_DEFAULT_FONT,
				StringConverter.asFontData("Lucida Grande-regular-11")
						.toString());

		// Initialize the default value of the JOIN_DEFAULT_BACKGROUND_COLOR property 
		defaultWfPreference.put(
				WfDiagramPreferenceConstants.JOIN_DEFAULT_BACKGROUND_COLOR,
				"211,226,205");

		// Initialize the default value of the JOIN_DEFAULT_FOREGROUND_COLOR property
		defaultWfPreference.put(
				WfDiagramPreferenceConstants.JOIN_DEFAULT_FOREGROUND_COLOR,
				"0,0,0");

		// Initialize the default value of the JOIN_DEFAULT_FONT property
		defaultWfPreference.put(WfDiagramPreferenceConstants.JOIN_DEFAULT_FONT,
				StringConverter.asFontData("Lucida Grande-regular-11")
						.toString());

		// Initialize the default value of the TIMER_DEFAULT_BACKGROUND_COLOR property 
		defaultWfPreference.put(
				WfDiagramPreferenceConstants.TIMER_DEFAULT_BACKGROUND_COLOR,
				"211,226,205");

		// Initialize the default value of the TIMER_DEFAULT_FOREGROUND_COLOR property
		defaultWfPreference.put(
				WfDiagramPreferenceConstants.TIMER_DEFAULT_FOREGROUND_COLOR,
				"0,0,0");

		// Initialize the default value of the TIMER_DEFAULT_FONT property
		defaultWfPreference.put(
				WfDiagramPreferenceConstants.TIMER_DEFAULT_FONT,
				StringConverter.asFontData("Lucida Grande-regular-11")
						.toString());

		// Initialize the default value of the TRANSITION_EDGE_DEFAULT_FONT property
		defaultWfPreference.put(
				WfDiagramPreferenceConstants.TRANSITION_EDGE_DEFAULT_FONT,
				StringConverter.asFontData("Lucida Grande-regular-11")
						.toString());

		// Initialize the default value of the TRANSITION_EDGE_DEFAULT_FOREGROUND_COLOR property
		defaultWfPreference
				.put(
						WfDiagramPreferenceConstants.TRANSITION_EDGE_DEFAULT_FOREGROUND_COLOR,
						"156,199,188");

		// Initialize the default value of the TRANSITION_EDGE_DEFAULT_ROUTER property
		defaultWfPreference.put(
				WfDiagramPreferenceConstants.TRANSITION_EDGE_DEFAULT_ROUTER,
				"ObliqueRouter");

		// Initialize the default value of the MANAGE_EDGE_DEFAULT_FONT property
		defaultWfPreference.put(
				WfDiagramPreferenceConstants.MANAGE_EDGE_DEFAULT_FONT,
				StringConverter.asFontData("Lucida Grande-regular-11")
						.toString());

		// Initialize the default value of the MANAGE_EDGE_DEFAULT_FOREGROUND_COLOR property
		defaultWfPreference
				.put(
						WfDiagramPreferenceConstants.MANAGE_EDGE_DEFAULT_FOREGROUND_COLOR,
						"27,134,199");

		// Initialize the default value of the MANAGE_EDGE_DEFAULT_ROUTER property
		defaultWfPreference.put(
				WfDiagramPreferenceConstants.MANAGE_EDGE_DEFAULT_ROUTER,
				"ObliqueRouter");

		// Initialize the default value of the INITIALIZE_EDGE_DEFAULT_FONT property
		defaultWfPreference.put(
				WfDiagramPreferenceConstants.INITIALIZE_EDGE_DEFAULT_FONT,
				StringConverter.asFontData("Lucida Grande-regular-11")
						.toString());

		// Initialize the default value of the INITIALIZE_EDGE_DEFAULT_FOREGROUND_COLOR property
		defaultWfPreference
				.put(
						WfDiagramPreferenceConstants.INITIALIZE_EDGE_DEFAULT_FOREGROUND_COLOR,
						"27,134,199");

		// Initialize the default value of the INITIALIZE_EDGE_DEFAULT_ROUTER property
		defaultWfPreference.put(
				WfDiagramPreferenceConstants.INITIALIZE_EDGE_DEFAULT_ROUTER,
				"ObliqueRouter");

		// Initialize the default value of the ACTIONS_EDGE_DEFAULT_FONT property
		defaultWfPreference.put(
				WfDiagramPreferenceConstants.ACTIONS_EDGE_DEFAULT_FONT,
				StringConverter.asFontData("Lucida Grande-regular-11")
						.toString());

		// Initialize the default value of the ACTIONS_EDGE_DEFAULT_FOREGROUND_COLOR property
		defaultWfPreference
				.put(
						WfDiagramPreferenceConstants.ACTIONS_EDGE_DEFAULT_FOREGROUND_COLOR,
						"34,40,161");

		// Initialize the default value of the ACTIONS_EDGE_DEFAULT_ROUTER property
		defaultWfPreference.put(
				WfDiagramPreferenceConstants.ACTIONS_EDGE_DEFAULT_ROUTER,
				"ObliqueRouter");

		// Initialize the default value of the HASTIMER_EDGE_DEFAULT_FONT property
		defaultWfPreference.put(
				WfDiagramPreferenceConstants.HASTIMER_EDGE_DEFAULT_FONT,
				StringConverter.asFontData("Lucida Grande-regular-11")
						.toString());

		// Initialize the default value of the HASTIMER_EDGE_DEFAULT_FOREGROUND_COLOR property
		defaultWfPreference
				.put(
						WfDiagramPreferenceConstants.HASTIMER_EDGE_DEFAULT_FOREGROUND_COLOR,
						"24,224,0");

		// Initialize the default value of the HASTIMER_EDGE_DEFAULT_ROUTER property
		defaultWfPreference.put(
				WfDiagramPreferenceConstants.HASTIMER_EDGE_DEFAULT_ROUTER,
				"ObliqueRouter");

		// Initialize the default value of the ATTRIBUTE_DEFAULT_BACKGROUND_COLOR property 
		defaultWfPreference
				.put(
						WfDiagramPreferenceConstants.ATTRIBUTE_DEFAULT_BACKGROUND_COLOR,
						"255,255,255");

		// Initialize the default value of the ATTRIBUTE_DEFAULT_FOREGROUND_COLOR property
		defaultWfPreference
				.put(
						WfDiagramPreferenceConstants.ATTRIBUTE_DEFAULT_FOREGROUND_COLOR,
						"0,0,0");

		// Initialize the default value of the ATTRIBUTE_DEFAULT_FONT property
		defaultWfPreference.put(
				WfDiagramPreferenceConstants.ATTRIBUTE_DEFAULT_FONT,
				StringConverter.asFontData("Lucida Grande-regular-11")
						.toString());

		return defaultWfPreference;
	}
}
