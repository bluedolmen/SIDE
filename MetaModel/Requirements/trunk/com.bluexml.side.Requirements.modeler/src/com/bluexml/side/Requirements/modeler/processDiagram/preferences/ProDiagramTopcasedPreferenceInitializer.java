/*
    Copyright (C) 2007-20013  BlueXML - www.bluexml.com

    This program is free software: you can redistribute it and/or modify
    it under the terms of the GNU Affero General Public License as
    published by the Free Software Foundation, either version 3 of the
    License, or (at your option) any later version.

    This program is distributed in the hope that it will be useful,
    but WITHOUT ANY WARRANTY; without even the implied warranty of
    MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
    GNU Affero General Public License for more details.

    You should have received a copy of the GNU Affero General Public License
    along with this program.  If not, see <http://www.gnu.org/licenses/>.
*/
/*******************************************************************************
 * No CopyrightText Defined in the configurator file.
 ******************************************************************************/
package com.bluexml.side.Requirements.modeler.processDiagram.preferences;

import java.util.HashMap;

import org.eclipse.jface.resource.StringConverter;
import org.topcased.modeler.preferences.ITopcasedPreferenceInitializer;

/**
 * Initialize the preferences of Pro diagram
 * 
 * @generated
 */
public class ProDiagramTopcasedPreferenceInitializer implements
		ITopcasedPreferenceInitializer {

	/** 
	 * @see org.topcased.modeler.preferences.ITopcasedPreferenceInitializer#getDefaultPreference()
	 *	@generated
	 */
	public HashMap<String, String> getDefaultPreference() {
		HashMap<String, String> defaultProPreference = new HashMap<String, String>();
		// Initialize the default value of the IS_RESPONSIBLE_EDGE_DEFAULT_FONT property
		defaultProPreference.put(
				ProDiagramPreferenceConstants.IS_RESPONSIBLE_EDGE_DEFAULT_FONT,
				StringConverter.asFontData("Lucida Grande-regular-11")
						.toString());

		// Initialize the default value of the IS_RESPONSIBLE_EDGE_DEFAULT_FOREGROUND_COLOR property
		defaultProPreference
				.put(
						ProDiagramPreferenceConstants.IS_RESPONSIBLE_EDGE_DEFAULT_FOREGROUND_COLOR,
						"247,165,0");

		// Initialize the default value of the IS_RESPONSIBLE_EDGE_DEFAULT_ROUTER property
		defaultProPreference
				.put(
						ProDiagramPreferenceConstants.IS_RESPONSIBLE_EDGE_DEFAULT_ROUTER,
						"ObliqueRouter");

		// Initialize the default value of the IS_SUB_GOAL_EDGE_DEFAULT_FONT property
		defaultProPreference.put(
				ProDiagramPreferenceConstants.IS_SUB_GOAL_EDGE_DEFAULT_FONT,
				StringConverter.asFontData("Lucida Grande-regular-11")
						.toString());

		// Initialize the default value of the IS_SUB_GOAL_EDGE_DEFAULT_FOREGROUND_COLOR property
		defaultProPreference
				.put(
						ProDiagramPreferenceConstants.IS_SUB_GOAL_EDGE_DEFAULT_FOREGROUND_COLOR,
						"0,78,155");

		// Initialize the default value of the IS_SUB_GOAL_EDGE_DEFAULT_ROUTER property
		defaultProPreference.put(
				ProDiagramPreferenceConstants.IS_SUB_GOAL_EDGE_DEFAULT_ROUTER,
				"ObliqueRouter");

		// Initialize the default value of the RELATIONSHIP_EDGE_DEFAULT_FONT property
		defaultProPreference.put(
				ProDiagramPreferenceConstants.RELATIONSHIP_EDGE_DEFAULT_FONT,
				StringConverter.asFontData("Lucida Grande-regular-11")
						.toString());

		// Initialize the default value of the RELATIONSHIP_EDGE_DEFAULT_FOREGROUND_COLOR property
		defaultProPreference
				.put(
						ProDiagramPreferenceConstants.RELATIONSHIP_EDGE_DEFAULT_FOREGROUND_COLOR,
						"136,43,172");

		// Initialize the default value of the RELATIONSHIP_EDGE_DEFAULT_ROUTER property
		defaultProPreference.put(
				ProDiagramPreferenceConstants.RELATIONSHIP_EDGE_DEFAULT_ROUTER,
				"ObliqueRouter");

		// Initialize the default value of the GOAL_DEFAULT_BACKGROUND_COLOR property 
		defaultProPreference.put(
				ProDiagramPreferenceConstants.GOAL_DEFAULT_BACKGROUND_COLOR,
				"184,231,255");

		// Initialize the default value of the GOAL_DEFAULT_FOREGROUND_COLOR property
		defaultProPreference.put(
				ProDiagramPreferenceConstants.GOAL_DEFAULT_FOREGROUND_COLOR,
				"0,0,0");

		// Initialize the default value of the GOAL_DEFAULT_FONT property
		defaultProPreference.put(
				ProDiagramPreferenceConstants.GOAL_DEFAULT_FONT,
				StringConverter.asFontData("Lucida Grande-regular-11")
						.toString());

		// Initialize the default value of the AGENT_DEFAULT_BACKGROUND_COLOR property 
		defaultProPreference.put(
				ProDiagramPreferenceConstants.AGENT_DEFAULT_BACKGROUND_COLOR,
				"255,255,255");

		// Initialize the default value of the AGENT_DEFAULT_FOREGROUND_COLOR property
		defaultProPreference.put(
				ProDiagramPreferenceConstants.AGENT_DEFAULT_FOREGROUND_COLOR,
				"0,0,0");

		// Initialize the default value of the AGENT_DEFAULT_FONT property
		defaultProPreference.put(
				ProDiagramPreferenceConstants.AGENT_DEFAULT_FONT,
				StringConverter.asFontData("Lucida Grande-regular-11")
						.toString());

		// Initialize the default value of the ENTITY_DEFAULT_BACKGROUND_COLOR property 
		defaultProPreference.put(
				ProDiagramPreferenceConstants.ENTITY_DEFAULT_BACKGROUND_COLOR,
				"255,255,255");

		// Initialize the default value of the ENTITY_DEFAULT_FOREGROUND_COLOR property
		defaultProPreference.put(
				ProDiagramPreferenceConstants.ENTITY_DEFAULT_FOREGROUND_COLOR,
				"0,0,0");

		// Initialize the default value of the ENTITY_DEFAULT_FONT property
		defaultProPreference.put(
				ProDiagramPreferenceConstants.ENTITY_DEFAULT_FONT,
				StringConverter.asFontData("Lucida Grande-regular-11")
						.toString());

		// Initialize the default value of the ATTRIBUTE_DEFAULT_BACKGROUND_COLOR property 
		defaultProPreference
				.put(
						ProDiagramPreferenceConstants.ATTRIBUTE_DEFAULT_BACKGROUND_COLOR,
						"255,255,255");

		// Initialize the default value of the ATTRIBUTE_DEFAULT_FOREGROUND_COLOR property
		defaultProPreference
				.put(
						ProDiagramPreferenceConstants.ATTRIBUTE_DEFAULT_FOREGROUND_COLOR,
						"0,0,0");

		// Initialize the default value of the ATTRIBUTE_DEFAULT_FONT property
		defaultProPreference.put(
				ProDiagramPreferenceConstants.ATTRIBUTE_DEFAULT_FONT,
				StringConverter.asFontData("Lucida Grande-regular-11")
						.toString());

		// Initialize the default value of the PRIVILEGE_DEFAULT_BACKGROUND_COLOR property 
		defaultProPreference
				.put(
						ProDiagramPreferenceConstants.PRIVILEGE_DEFAULT_BACKGROUND_COLOR,
						"255,255,255");

		// Initialize the default value of the PRIVILEGE_DEFAULT_FOREGROUND_COLOR property
		defaultProPreference
				.put(
						ProDiagramPreferenceConstants.PRIVILEGE_DEFAULT_FOREGROUND_COLOR,
						"0,0,0");

		// Initialize the default value of the PRIVILEGE_DEFAULT_FONT property
		defaultProPreference.put(
				ProDiagramPreferenceConstants.PRIVILEGE_DEFAULT_FONT,
				StringConverter.asFontData("Lucida Grande-regular-11")
						.toString());

		// Initialize the default value of the PRIVILEGEGROUP_EDGE_DEFAULT_FONT property
		defaultProPreference.put(
				ProDiagramPreferenceConstants.PRIVILEGEGROUP_EDGE_DEFAULT_FONT,
				StringConverter.asFontData("Lucida Grande-regular-11")
						.toString());

		// Initialize the default value of the PRIVILEGEGROUP_EDGE_DEFAULT_FOREGROUND_COLOR property
		defaultProPreference
				.put(
						ProDiagramPreferenceConstants.PRIVILEGEGROUP_EDGE_DEFAULT_FOREGROUND_COLOR,
						"72,230,89");

		// Initialize the default value of the PRIVILEGEGROUP_EDGE_DEFAULT_ROUTER property
		defaultProPreference
				.put(
						ProDiagramPreferenceConstants.PRIVILEGEGROUP_EDGE_DEFAULT_ROUTER,
						"ObliqueRouter");

		// Initialize the default value of the GOALSTEP_EDGE_DEFAULT_FONT property
		defaultProPreference.put(
				ProDiagramPreferenceConstants.GOALSTEP_EDGE_DEFAULT_FONT,
				StringConverter.asFontData("Lucida Grande-regular-11")
						.toString());

		// Initialize the default value of the GOALSTEP_EDGE_DEFAULT_FOREGROUND_COLOR property
		defaultProPreference
				.put(
						ProDiagramPreferenceConstants.GOALSTEP_EDGE_DEFAULT_FOREGROUND_COLOR,
						"156,199,188");

		// Initialize the default value of the GOALSTEP_EDGE_DEFAULT_ROUTER property
		defaultProPreference.put(
				ProDiagramPreferenceConstants.GOALSTEP_EDGE_DEFAULT_ROUTER,
				"ObliqueRouter");

		// Initialize the default value of the IS_PARENT_EDGE_DEFAULT_FONT property
		defaultProPreference.put(
				ProDiagramPreferenceConstants.IS_PARENT_EDGE_DEFAULT_FONT,
				StringConverter.asFontData("Lucida Grande-regular-11")
						.toString());

		// Initialize the default value of the IS_PARENT_EDGE_DEFAULT_FOREGROUND_COLOR property
		defaultProPreference
				.put(
						ProDiagramPreferenceConstants.IS_PARENT_EDGE_DEFAULT_FOREGROUND_COLOR,
						"50,50,50");

		// Initialize the default value of the IS_PARENT_EDGE_DEFAULT_ROUTER property
		defaultProPreference.put(
				ProDiagramPreferenceConstants.IS_PARENT_EDGE_DEFAULT_ROUTER,
				"ObliqueRouter");

		return defaultProPreference;
	}
}
