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
package com.bluexml.side.Portal.modeler.diagram.preferences;

import java.util.HashMap;

import org.eclipse.jface.resource.StringConverter;
import org.topcased.modeler.preferences.ITopcasedPreferenceInitializer;

/**
 * Initialize the preferences of Pd diagram
 * 
 * @generated
 */
public class PdDiagramTopcasedPreferenceInitializer implements ITopcasedPreferenceInitializer {

	/** 
	 * @see org.topcased.modeler.preferences.ITopcasedPreferenceInitializer#getDefaultPreference()
	 *	@generated
	 */
	public HashMap<String, String> getDefaultPreference() {
		HashMap<String, String> defaultPdPreference = new HashMap<String, String>();
		// Initialize the default value of the PAGE_DEFAULT_BACKGROUND_COLOR property 
		defaultPdPreference.put(PdDiagramPreferenceConstants.PAGE_DEFAULT_BACKGROUND_COLOR, "157,210,60");

		// Initialize the default value of the PAGE_DEFAULT_FOREGROUND_COLOR property
		defaultPdPreference.put(PdDiagramPreferenceConstants.PAGE_DEFAULT_FOREGROUND_COLOR, "0,0,0");

		// Initialize the default value of the PAGE_DEFAULT_FONT property
		defaultPdPreference.put(PdDiagramPreferenceConstants.PAGE_DEFAULT_FONT, StringConverter.asFontData("Lucida Grande-regular-11").toString());

		// Initialize the default value of the ISCHILDPAGE_EDGE_DEFAULT_FONT property
		defaultPdPreference.put(PdDiagramPreferenceConstants.ISCHILDPAGE_EDGE_DEFAULT_FONT, StringConverter.asFontData("Lucida Grande-regular-11").toString());

		// Initialize the default value of the ISCHILDPAGE_EDGE_DEFAULT_FOREGROUND_COLOR property
		defaultPdPreference.put(PdDiagramPreferenceConstants.ISCHILDPAGE_EDGE_DEFAULT_FOREGROUND_COLOR, "0,0,0");

		// Initialize the default value of the ISCHILDPAGE_EDGE_DEFAULT_ROUTER property
		defaultPdPreference.put(PdDiagramPreferenceConstants.ISCHILDPAGE_EDGE_DEFAULT_ROUTER, "ObliqueRouter");

		// Initialize the default value of the PORTALLAYOUT_DEFAULT_BACKGROUND_COLOR property 
		defaultPdPreference.put(PdDiagramPreferenceConstants.PORTALLAYOUT_DEFAULT_BACKGROUND_COLOR, "202,223,83");

		// Initialize the default value of the PORTALLAYOUT_DEFAULT_FOREGROUND_COLOR property
		defaultPdPreference.put(PdDiagramPreferenceConstants.PORTALLAYOUT_DEFAULT_FOREGROUND_COLOR, "0,0,0");

		// Initialize the default value of the PORTALLAYOUT_DEFAULT_FONT property
		defaultPdPreference.put(PdDiagramPreferenceConstants.PORTALLAYOUT_DEFAULT_FONT, StringConverter.asFontData("Lucida Grande-regular-11").toString());

		// Initialize the default value of the USELAYOUT_EDGE_DEFAULT_FONT property
		defaultPdPreference.put(PdDiagramPreferenceConstants.USELAYOUT_EDGE_DEFAULT_FONT, StringConverter.asFontData("Lucida Grande-regular-11").toString());

		// Initialize the default value of the USELAYOUT_EDGE_DEFAULT_FOREGROUND_COLOR property
		defaultPdPreference.put(PdDiagramPreferenceConstants.USELAYOUT_EDGE_DEFAULT_FOREGROUND_COLOR, "0,0,0");

		// Initialize the default value of the USELAYOUT_EDGE_DEFAULT_ROUTER property
		defaultPdPreference.put(PdDiagramPreferenceConstants.USELAYOUT_EDGE_DEFAULT_ROUTER, "ObliqueRouter");

		// Initialize the default value of the PORTLET_DEFAULT_BACKGROUND_COLOR property 
		defaultPdPreference.put(PdDiagramPreferenceConstants.PORTLET_DEFAULT_BACKGROUND_COLOR, "255,255,255");

		// Initialize the default value of the PORTLET_DEFAULT_FOREGROUND_COLOR property
		defaultPdPreference.put(PdDiagramPreferenceConstants.PORTLET_DEFAULT_FOREGROUND_COLOR, "0,0,0");

		// Initialize the default value of the PORTLET_DEFAULT_FONT property
		defaultPdPreference.put(PdDiagramPreferenceConstants.PORTLET_DEFAULT_FONT, StringConverter.asFontData("Lucida Grande-regular-11").toString());

		// Initialize the default value of the PORTLETTYPE_DEFAULT_BACKGROUND_COLOR property 
		defaultPdPreference.put(PdDiagramPreferenceConstants.PORTLETTYPE_DEFAULT_BACKGROUND_COLOR, "255,255,255");

		// Initialize the default value of the PORTLETTYPE_DEFAULT_FOREGROUND_COLOR property
		defaultPdPreference.put(PdDiagramPreferenceConstants.PORTLETTYPE_DEFAULT_FOREGROUND_COLOR, "0,0,0");

		// Initialize the default value of the PORTLETTYPE_DEFAULT_FONT property
		defaultPdPreference.put(PdDiagramPreferenceConstants.PORTLETTYPE_DEFAULT_FONT, StringConverter.asFontData("Lucida Grande-regular-11").toString());

		// Initialize the default value of the PORTLETINTERNAL_DEFAULT_BACKGROUND_COLOR property 
		defaultPdPreference.put(PdDiagramPreferenceConstants.PORTLETINTERNAL_DEFAULT_BACKGROUND_COLOR, "255,255,255");

		// Initialize the default value of the PORTLETINTERNAL_DEFAULT_FOREGROUND_COLOR property
		defaultPdPreference.put(PdDiagramPreferenceConstants.PORTLETINTERNAL_DEFAULT_FOREGROUND_COLOR, "0,0,0");

		// Initialize the default value of the PORTLETINTERNAL_DEFAULT_FONT property
		defaultPdPreference.put(PdDiagramPreferenceConstants.PORTLETINTERNAL_DEFAULT_FONT, StringConverter.asFontData("Lucida Grande-regular-11").toString());

		// Initialize the default value of the ISINTERNALPORTLET_EDGE_DEFAULT_FONT property
		defaultPdPreference.put(PdDiagramPreferenceConstants.ISINTERNALPORTLET_EDGE_DEFAULT_FONT, StringConverter.asFontData("Lucida Grande-regular-11").toString());

		// Initialize the default value of the ISINTERNALPORTLET_EDGE_DEFAULT_FOREGROUND_COLOR property
		defaultPdPreference.put(PdDiagramPreferenceConstants.ISINTERNALPORTLET_EDGE_DEFAULT_FOREGROUND_COLOR, "0,0,0");

		// Initialize the default value of the ISINTERNALPORTLET_EDGE_DEFAULT_ROUTER property
		defaultPdPreference.put(PdDiagramPreferenceConstants.ISINTERNALPORTLET_EDGE_DEFAULT_ROUTER, "ObliqueRouter");

		// Initialize the default value of the HAVEPORTLET_EDGE_DEFAULT_FONT property
		defaultPdPreference.put(PdDiagramPreferenceConstants.HAVEPORTLET_EDGE_DEFAULT_FONT, StringConverter.asFontData("Lucida Grande-regular-11").toString());

		// Initialize the default value of the HAVEPORTLET_EDGE_DEFAULT_FOREGROUND_COLOR property
		defaultPdPreference.put(PdDiagramPreferenceConstants.HAVEPORTLET_EDGE_DEFAULT_FOREGROUND_COLOR, "231,5,5");

		// Initialize the default value of the HAVEPORTLET_EDGE_DEFAULT_ROUTER property
		defaultPdPreference.put(PdDiagramPreferenceConstants.HAVEPORTLET_EDGE_DEFAULT_ROUTER, "ObliqueRouter");

		// Initialize the default value of the INSTANCIATEPORTLETTYPE_EDGE_DEFAULT_FONT property
		defaultPdPreference.put(PdDiagramPreferenceConstants.INSTANCIATEPORTLETTYPE_EDGE_DEFAULT_FONT, StringConverter.asFontData("Lucida Grande-regular-11").toString());

		// Initialize the default value of the INSTANCIATEPORTLETTYPE_EDGE_DEFAULT_FOREGROUND_COLOR property
		defaultPdPreference.put(PdDiagramPreferenceConstants.INSTANCIATEPORTLETTYPE_EDGE_DEFAULT_FOREGROUND_COLOR, "0,0,0");

		// Initialize the default value of the INSTANCIATEPORTLETTYPE_EDGE_DEFAULT_ROUTER property
		defaultPdPreference.put(PdDiagramPreferenceConstants.INSTANCIATEPORTLETTYPE_EDGE_DEFAULT_ROUTER, "ObliqueRouter");

		return defaultPdPreference;
	}
}
