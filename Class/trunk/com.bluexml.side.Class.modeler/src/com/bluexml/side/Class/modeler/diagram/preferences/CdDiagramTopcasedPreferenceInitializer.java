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

import java.util.HashMap;

import org.eclipse.jface.resource.StringConverter;
import org.topcased.modeler.preferences.ITopcasedPreferenceInitializer;

/**
 * Initialize the preferences of Cd diagram
 * 
 * @generated
 */
public class CdDiagramTopcasedPreferenceInitializer implements
		ITopcasedPreferenceInitializer {

	/** 
	 * @see org.topcased.modeler.preferences.ITopcasedPreferenceInitializer#getDefaultPreference()
	 *	@generated
	 */
	public HashMap<String, String> getDefaultPreference() {
		HashMap<String, String> defaultCdPreference = new HashMap<String, String>();
		// Initialize the default value of the STEREOTYPE_DEFAULT_BACKGROUND_COLOR property 
		defaultCdPreference
				.put(
						CdDiagramPreferenceConstants.STEREOTYPE_DEFAULT_BACKGROUND_COLOR,
						"191,249,217");

		// Initialize the default value of the STEREOTYPE_DEFAULT_FOREGROUND_COLOR property
		defaultCdPreference
				.put(
						CdDiagramPreferenceConstants.STEREOTYPE_DEFAULT_FOREGROUND_COLOR,
						"0,0,0");

		// Initialize the default value of the STEREOTYPE_DEFAULT_FONT property
		defaultCdPreference.put(
				CdDiagramPreferenceConstants.STEREOTYPE_DEFAULT_FONT,
				StringConverter.asFontData("Segoe UI-regular-9").toString());

		// Initialize the default value of the CLAZZ_DEFAULT_BACKGROUND_COLOR property 
		defaultCdPreference.put(
				CdDiagramPreferenceConstants.CLAZZ_DEFAULT_BACKGROUND_COLOR,
				"255,255,255");

		// Initialize the default value of the CLAZZ_DEFAULT_FOREGROUND_COLOR property
		defaultCdPreference.put(
				CdDiagramPreferenceConstants.CLAZZ_DEFAULT_FOREGROUND_COLOR,
				"0,0,0");

		// Initialize the default value of the CLAZZ_DEFAULT_FONT property
		defaultCdPreference.put(
				CdDiagramPreferenceConstants.CLAZZ_DEFAULT_FONT,
				StringConverter.asFontData("Segoe UI-regular-9").toString());

		// Initialize the default value of the ASPECT_DEFAULT_BACKGROUND_COLOR property 
		defaultCdPreference.put(
				CdDiagramPreferenceConstants.ASPECT_DEFAULT_BACKGROUND_COLOR,
				"255,255,255");

		// Initialize the default value of the ASPECT_DEFAULT_FOREGROUND_COLOR property
		defaultCdPreference.put(
				CdDiagramPreferenceConstants.ASPECT_DEFAULT_FOREGROUND_COLOR,
				"0,0,0");

		// Initialize the default value of the ASPECT_DEFAULT_FONT property
		defaultCdPreference.put(
				CdDiagramPreferenceConstants.ASPECT_DEFAULT_FONT,
				StringConverter.asFontData("Segoe UI-regular-9").toString());

		// Initialize the default value of the ATTRIBUTE_DEFAULT_BACKGROUND_COLOR property 
		defaultCdPreference
				.put(
						CdDiagramPreferenceConstants.ATTRIBUTE_DEFAULT_BACKGROUND_COLOR,
						"255,255,255");

		// Initialize the default value of the ATTRIBUTE_DEFAULT_FOREGROUND_COLOR property
		defaultCdPreference
				.put(
						CdDiagramPreferenceConstants.ATTRIBUTE_DEFAULT_FOREGROUND_COLOR,
						"0,0,0");

		// Initialize the default value of the ATTRIBUTE_DEFAULT_FONT property
		defaultCdPreference.put(
				CdDiagramPreferenceConstants.ATTRIBUTE_DEFAULT_FONT,
				StringConverter.asFontData("Segoe UI-regular-9").toString());

		// Initialize the default value of the OPERATION_DEFAULT_BACKGROUND_COLOR property 
		defaultCdPreference
				.put(
						CdDiagramPreferenceConstants.OPERATION_DEFAULT_BACKGROUND_COLOR,
						"255,255,255");

		// Initialize the default value of the OPERATION_DEFAULT_FOREGROUND_COLOR property
		defaultCdPreference
				.put(
						CdDiagramPreferenceConstants.OPERATION_DEFAULT_FOREGROUND_COLOR,
						"0,0,0");

		// Initialize the default value of the OPERATION_DEFAULT_FONT property
		defaultCdPreference.put(
				CdDiagramPreferenceConstants.OPERATION_DEFAULT_FONT,
				StringConverter.asFontData("Segoe UI-regular-9").toString());

		// Initialize the default value of the COMMENT_DEFAULT_BACKGROUND_COLOR property 
		defaultCdPreference.put(
				CdDiagramPreferenceConstants.COMMENT_DEFAULT_BACKGROUND_COLOR,
				"191,249,217");

		// Initialize the default value of the COMMENT_DEFAULT_FOREGROUND_COLOR property
		defaultCdPreference.put(
				CdDiagramPreferenceConstants.COMMENT_DEFAULT_FOREGROUND_COLOR,
				"0,0,0");

		// Initialize the default value of the COMMENT_DEFAULT_FONT property
		defaultCdPreference.put(
				CdDiagramPreferenceConstants.COMMENT_DEFAULT_FONT,
				StringConverter.asFontData("Segoe UI-regular-9").toString());

		// Initialize the default value of the ENUMERATION_DEFAULT_BACKGROUND_COLOR property 
		defaultCdPreference
				.put(
						CdDiagramPreferenceConstants.ENUMERATION_DEFAULT_BACKGROUND_COLOR,
						"255,255,255");

		// Initialize the default value of the ENUMERATION_DEFAULT_FOREGROUND_COLOR property
		defaultCdPreference
				.put(
						CdDiagramPreferenceConstants.ENUMERATION_DEFAULT_FOREGROUND_COLOR,
						"0,0,0");

		// Initialize the default value of the ENUMERATION_DEFAULT_FONT property
		defaultCdPreference.put(
				CdDiagramPreferenceConstants.ENUMERATION_DEFAULT_FONT,
				StringConverter.asFontData("Segoe UI-regular-9").toString());

		// Initialize the default value of the ENUMERATIONLITERAL_DEFAULT_BACKGROUND_COLOR property 
		defaultCdPreference
				.put(
						CdDiagramPreferenceConstants.ENUMERATIONLITERAL_DEFAULT_BACKGROUND_COLOR,
						"255,255,255");

		// Initialize the default value of the ENUMERATIONLITERAL_DEFAULT_FOREGROUND_COLOR property
		defaultCdPreference
				.put(
						CdDiagramPreferenceConstants.ENUMERATIONLITERAL_DEFAULT_FOREGROUND_COLOR,
						"0,0,0");

		// Initialize the default value of the ENUMERATIONLITERAL_DEFAULT_FONT property
		defaultCdPreference.put(
				CdDiagramPreferenceConstants.ENUMERATIONLITERAL_DEFAULT_FONT,
				StringConverter.asFontData("Segoe UI-regular-9").toString());

		// Initialize the default value of the VIEW_DEFAULT_BACKGROUND_COLOR property 
		defaultCdPreference.put(
				CdDiagramPreferenceConstants.VIEW_DEFAULT_BACKGROUND_COLOR,
				"218,248,239");

		// Initialize the default value of the VIEW_DEFAULT_FOREGROUND_COLOR property
		defaultCdPreference.put(
				CdDiagramPreferenceConstants.VIEW_DEFAULT_FOREGROUND_COLOR,
				"0,0,0");

		// Initialize the default value of the VIEW_DEFAULT_FONT property
		defaultCdPreference.put(CdDiagramPreferenceConstants.VIEW_DEFAULT_FONT,
				StringConverter.asFontData("Segoe UI-regular-9").toString());

		// Initialize the default value of the ASSOCIATION_EDGE_DEFAULT_FONT property
		defaultCdPreference.put(
				CdDiagramPreferenceConstants.ASSOCIATION_EDGE_DEFAULT_FONT,
				StringConverter.asFontData("Segoe UI-regular-9").toString());

		// Initialize the default value of the ASSOCIATION_EDGE_DEFAULT_FOREGROUND_COLOR property
		defaultCdPreference
				.put(
						CdDiagramPreferenceConstants.ASSOCIATION_EDGE_DEFAULT_FOREGROUND_COLOR,
						"0,0,0");

		// Initialize the default value of the ASSOCIATION_EDGE_DEFAULT_ROUTER property
		defaultCdPreference.put(
				CdDiagramPreferenceConstants.ASSOCIATION_EDGE_DEFAULT_ROUTER,
				"ObliqueRouter");

		// Initialize the default value of the ISCOMMENTED_EDGE_DEFAULT_FONT property
		defaultCdPreference.put(
				CdDiagramPreferenceConstants.ISCOMMENTED_EDGE_DEFAULT_FONT,
				StringConverter.asFontData("Segoe UI-regular-9").toString());

		// Initialize the default value of the ISCOMMENTED_EDGE_DEFAULT_FOREGROUND_COLOR property
		defaultCdPreference
				.put(
						CdDiagramPreferenceConstants.ISCOMMENTED_EDGE_DEFAULT_FOREGROUND_COLOR,
						"128,128,192");

		// Initialize the default value of the ISCOMMENTED_EDGE_DEFAULT_ROUTER property
		defaultCdPreference.put(
				CdDiagramPreferenceConstants.ISCOMMENTED_EDGE_DEFAULT_ROUTER,
				"ObliqueRouter");

		// Initialize the default value of the ISSTEREOTYPED_EDGE_DEFAULT_FONT property
		defaultCdPreference.put(
				CdDiagramPreferenceConstants.ISSTEREOTYPED_EDGE_DEFAULT_FONT,
				StringConverter.asFontData("Segoe UI-regular-9").toString());

		// Initialize the default value of the ISSTEREOTYPED_EDGE_DEFAULT_FOREGROUND_COLOR property
		defaultCdPreference
				.put(
						CdDiagramPreferenceConstants.ISSTEREOTYPED_EDGE_DEFAULT_FOREGROUND_COLOR,
						"214,214,214");

		// Initialize the default value of the ISSTEREOTYPED_EDGE_DEFAULT_ROUTER property
		defaultCdPreference.put(
				CdDiagramPreferenceConstants.ISSTEREOTYPED_EDGE_DEFAULT_ROUTER,
				"ObliqueRouter");

		// Initialize the default value of the ISASSOCIATIONCLASS_EDGE_DEFAULT_FONT property
		defaultCdPreference
				.put(
						CdDiagramPreferenceConstants.ISASSOCIATIONCLASS_EDGE_DEFAULT_FONT,
						StringConverter.asFontData("Segoe UI-regular-9")
								.toString());

		// Initialize the default value of the ISASSOCIATIONCLASS_EDGE_DEFAULT_FOREGROUND_COLOR property
		defaultCdPreference
				.put(
						CdDiagramPreferenceConstants.ISASSOCIATIONCLASS_EDGE_DEFAULT_FOREGROUND_COLOR,
						"192,192,192");

		// Initialize the default value of the ISASSOCIATIONCLASS_EDGE_DEFAULT_ROUTER property
		defaultCdPreference
				.put(
						CdDiagramPreferenceConstants.ISASSOCIATIONCLASS_EDGE_DEFAULT_ROUTER,
						"ObliqueRouter");

		// Initialize the default value of the INCLUDE_EDGE_DEFAULT_FONT property
		defaultCdPreference.put(
				CdDiagramPreferenceConstants.INCLUDE_EDGE_DEFAULT_FONT,
				StringConverter.asFontData("Segoe UI-regular-9").toString());

		// Initialize the default value of the INCLUDE_EDGE_DEFAULT_FOREGROUND_COLOR property
		defaultCdPreference
				.put(
						CdDiagramPreferenceConstants.INCLUDE_EDGE_DEFAULT_FOREGROUND_COLOR,
						"192,192,192");

		// Initialize the default value of the INCLUDE_EDGE_DEFAULT_ROUTER property
		defaultCdPreference.put(
				CdDiagramPreferenceConstants.INCLUDE_EDGE_DEFAULT_ROUTER,
				"ObliqueRouter");

		// Initialize the default value of the HASVIEW_EDGE_DEFAULT_FONT property
		defaultCdPreference.put(
				CdDiagramPreferenceConstants.HASVIEW_EDGE_DEFAULT_FONT,
				StringConverter.asFontData("Segoe UI-regular-9").toString());

		// Initialize the default value of the HASVIEW_EDGE_DEFAULT_FOREGROUND_COLOR property
		defaultCdPreference
				.put(
						CdDiagramPreferenceConstants.HASVIEW_EDGE_DEFAULT_FOREGROUND_COLOR,
						"0,128,128");

		// Initialize the default value of the HASVIEW_EDGE_DEFAULT_ROUTER property
		defaultCdPreference.put(
				CdDiagramPreferenceConstants.HASVIEW_EDGE_DEFAULT_ROUTER,
				"ObliqueRouter");

		// Initialize the default value of the GENERALIZATION_EDGE_DEFAULT_FONT property
		defaultCdPreference.put(
				CdDiagramPreferenceConstants.GENERALIZATION_EDGE_DEFAULT_FONT,
				StringConverter.asFontData("Segoe UI-regular-9").toString());

		// Initialize the default value of the GENERALIZATION_EDGE_DEFAULT_FOREGROUND_COLOR property
		defaultCdPreference
				.put(
						CdDiagramPreferenceConstants.GENERALIZATION_EDGE_DEFAULT_FOREGROUND_COLOR,
						"0,0,255");

		// Initialize the default value of the GENERALIZATION_EDGE_DEFAULT_ROUTER property
		defaultCdPreference
				.put(
						CdDiagramPreferenceConstants.GENERALIZATION_EDGE_DEFAULT_ROUTER,
						"ObliqueRouter");

		return defaultCdPreference;
	}
}
