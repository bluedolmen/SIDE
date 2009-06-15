/*******************************************************************************
 * No CopyrightText Defined in the configurator file.
 ******************************************************************************/
package com.bluexml.side.Requirements.modeler.goalDiagram.preferences;

import java.util.HashMap;

import org.eclipse.jface.resource.StringConverter;
import org.topcased.modeler.preferences.ITopcasedPreferenceInitializer;

/**
 * Initialize the preferences of Req diagram
 * 
 * @generated
 */
public class ReqDiagramTopcasedPreferenceInitializer implements
		ITopcasedPreferenceInitializer {

	/** 
	 * @see org.topcased.modeler.preferences.ITopcasedPreferenceInitializer#getDefaultPreference()
	 *	@generated
	 */
	public HashMap<String, String> getDefaultPreference() {
		HashMap<String, String> defaultReqPreference = new HashMap<String, String>();
		// Initialize the default value of the IS_RESPONSIBLE_EDGE_DEFAULT_FONT property
		defaultReqPreference.put(
				ReqDiagramPreferenceConstants.IS_RESPONSIBLE_EDGE_DEFAULT_FONT,
				StringConverter.asFontData("Lucida Grande-regular-11")
						.toString());

		// Initialize the default value of the IS_RESPONSIBLE_EDGE_DEFAULT_FOREGROUND_COLOR property
		defaultReqPreference
				.put(
						ReqDiagramPreferenceConstants.IS_RESPONSIBLE_EDGE_DEFAULT_FOREGROUND_COLOR,
						"247,165,0");

		// Initialize the default value of the IS_RESPONSIBLE_EDGE_DEFAULT_ROUTER property
		defaultReqPreference
				.put(
						ReqDiagramPreferenceConstants.IS_RESPONSIBLE_EDGE_DEFAULT_ROUTER,
						"ObliqueRouter");

		// Initialize the default value of the IS_SUB_GOAL_EDGE_DEFAULT_FONT property
		defaultReqPreference.put(
				ReqDiagramPreferenceConstants.IS_SUB_GOAL_EDGE_DEFAULT_FONT,
				StringConverter.asFontData("Lucida Grande-regular-11")
						.toString());

		// Initialize the default value of the IS_SUB_GOAL_EDGE_DEFAULT_FOREGROUND_COLOR property
		defaultReqPreference
				.put(
						ReqDiagramPreferenceConstants.IS_SUB_GOAL_EDGE_DEFAULT_FOREGROUND_COLOR,
						"0,78,155");

		// Initialize the default value of the IS_SUB_GOAL_EDGE_DEFAULT_ROUTER property
		defaultReqPreference.put(
				ReqDiagramPreferenceConstants.IS_SUB_GOAL_EDGE_DEFAULT_ROUTER,
				"ObliqueRouter");

		// Initialize the default value of the RELATIONSHIP_EDGE_DEFAULT_FONT property
		defaultReqPreference.put(
				ReqDiagramPreferenceConstants.RELATIONSHIP_EDGE_DEFAULT_FONT,
				StringConverter.asFontData("Lucida Grande-regular-11")
						.toString());

		// Initialize the default value of the RELATIONSHIP_EDGE_DEFAULT_FOREGROUND_COLOR property
		defaultReqPreference
				.put(
						ReqDiagramPreferenceConstants.RELATIONSHIP_EDGE_DEFAULT_FOREGROUND_COLOR,
						"136,43,172");

		// Initialize the default value of the RELATIONSHIP_EDGE_DEFAULT_ROUTER property
		defaultReqPreference.put(
				ReqDiagramPreferenceConstants.RELATIONSHIP_EDGE_DEFAULT_ROUTER,
				"ObliqueRouter");

		// Initialize the default value of the GOAL_DEFAULT_BACKGROUND_COLOR property 
		defaultReqPreference.put(
				ReqDiagramPreferenceConstants.GOAL_DEFAULT_BACKGROUND_COLOR,
				"184,231,255");

		// Initialize the default value of the GOAL_DEFAULT_FOREGROUND_COLOR property
		defaultReqPreference.put(
				ReqDiagramPreferenceConstants.GOAL_DEFAULT_FOREGROUND_COLOR,
				"0,0,0");

		// Initialize the default value of the GOAL_DEFAULT_FONT property
		defaultReqPreference.put(
				ReqDiagramPreferenceConstants.GOAL_DEFAULT_FONT,
				StringConverter.asFontData("Lucida Grande-regular-11")
						.toString());

		// Initialize the default value of the AGENT_DEFAULT_BACKGROUND_COLOR property 
		defaultReqPreference.put(
				ReqDiagramPreferenceConstants.AGENT_DEFAULT_BACKGROUND_COLOR,
				"255,255,255");

		// Initialize the default value of the AGENT_DEFAULT_FOREGROUND_COLOR property
		defaultReqPreference.put(
				ReqDiagramPreferenceConstants.AGENT_DEFAULT_FOREGROUND_COLOR,
				"0,0,0");

		// Initialize the default value of the AGENT_DEFAULT_FONT property
		defaultReqPreference.put(
				ReqDiagramPreferenceConstants.AGENT_DEFAULT_FONT,
				StringConverter.asFontData("Lucida Grande-regular-11")
						.toString());

		// Initialize the default value of the ENTITY_DEFAULT_BACKGROUND_COLOR property 
		defaultReqPreference.put(
				ReqDiagramPreferenceConstants.ENTITY_DEFAULT_BACKGROUND_COLOR,
				"255,255,255");

		// Initialize the default value of the ENTITY_DEFAULT_FOREGROUND_COLOR property
		defaultReqPreference.put(
				ReqDiagramPreferenceConstants.ENTITY_DEFAULT_FOREGROUND_COLOR,
				"0,0,0");

		// Initialize the default value of the ENTITY_DEFAULT_FONT property
		defaultReqPreference.put(
				ReqDiagramPreferenceConstants.ENTITY_DEFAULT_FONT,
				StringConverter.asFontData("Lucida Grande-regular-11")
						.toString());

		// Initialize the default value of the ATTRIBUTE_DEFAULT_BACKGROUND_COLOR property 
		defaultReqPreference
				.put(
						ReqDiagramPreferenceConstants.ATTRIBUTE_DEFAULT_BACKGROUND_COLOR,
						"255,255,255");

		// Initialize the default value of the ATTRIBUTE_DEFAULT_FOREGROUND_COLOR property
		defaultReqPreference
				.put(
						ReqDiagramPreferenceConstants.ATTRIBUTE_DEFAULT_FOREGROUND_COLOR,
						"0,0,0");

		// Initialize the default value of the ATTRIBUTE_DEFAULT_FONT property
		defaultReqPreference.put(
				ReqDiagramPreferenceConstants.ATTRIBUTE_DEFAULT_FONT,
				StringConverter.asFontData("Lucida Grande-regular-11")
						.toString());

		// Initialize the default value of the PRIVILEGE_DEFAULT_BACKGROUND_COLOR property 
		defaultReqPreference
				.put(
						ReqDiagramPreferenceConstants.PRIVILEGE_DEFAULT_BACKGROUND_COLOR,
						"255,255,255");

		// Initialize the default value of the PRIVILEGE_DEFAULT_FOREGROUND_COLOR property
		defaultReqPreference
				.put(
						ReqDiagramPreferenceConstants.PRIVILEGE_DEFAULT_FOREGROUND_COLOR,
						"0,0,0");

		// Initialize the default value of the PRIVILEGE_DEFAULT_FONT property
		defaultReqPreference.put(
				ReqDiagramPreferenceConstants.PRIVILEGE_DEFAULT_FONT,
				StringConverter.asFontData("Lucida Grande-regular-11")
						.toString());

		// Initialize the default value of the PRIVILEGEGROUP_EDGE_DEFAULT_FONT property
		defaultReqPreference.put(
				ReqDiagramPreferenceConstants.PRIVILEGEGROUP_EDGE_DEFAULT_FONT,
				StringConverter.asFontData("Lucida Grande-regular-11")
						.toString());

		// Initialize the default value of the PRIVILEGEGROUP_EDGE_DEFAULT_FOREGROUND_COLOR property
		defaultReqPreference
				.put(
						ReqDiagramPreferenceConstants.PRIVILEGEGROUP_EDGE_DEFAULT_FOREGROUND_COLOR,
						"0,0,0");

		// Initialize the default value of the PRIVILEGEGROUP_EDGE_DEFAULT_ROUTER property
		defaultReqPreference
				.put(
						ReqDiagramPreferenceConstants.PRIVILEGEGROUP_EDGE_DEFAULT_ROUTER,
						"ObliqueRouter");

		return defaultReqPreference;
	}
}
