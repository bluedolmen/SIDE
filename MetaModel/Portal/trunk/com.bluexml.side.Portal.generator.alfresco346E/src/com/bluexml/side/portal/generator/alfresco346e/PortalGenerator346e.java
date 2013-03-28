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
package com.bluexml.side.portal.generator.alfresco346e;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.eclipse.emf.ecore.EObject;

import com.bluexml.side.portal.generator.alfresco34d.PortalGenerator;

public class PortalGenerator346e extends PortalGenerator {
	static String template346e = "/com.bluexml.side.Portal.generator.alfresco346E/com/bluexml/side/portal/generator/alfresco346e/templates/";

	@Override
	public String getXFORMURL(EObject o) {
		return getGenerationParameter(GENERATOR_PARAM_XFORMURL);
	}

	@Override
	public String getSHAREURL(EObject o) {
		return getGenerationParameter(GENERATOR_PARAM_SHAREURL);
	}

	@Override
	public String getFacetMapURL(EObject o) {
		return getGenerationParameter(GENERATOR_PARAM_FACETMAPURL);
	}

	@Override
	protected List<String> getMainTemplates() {

		List<String> mainTemplates = super.getMainTemplates();

		mainTemplates.add(template346e + "flash-upload.get.config.xml.mt");
		mainTemplates.add(template346e + "flash-upload.get_en.properties.mt");
		mainTemplates.add(template346e + "flash-upload.get_fr.properties.mt");
		mainTemplates.add(template346e + "flash-upload.get_de.properties.mt");
		mainTemplates.add(template346e + "flash-upload.get_es.properties.mt");
		mainTemplates.add(template346e + "flash-upload.get_it.properties.mt");

		mainTemplates.add(template346e + "html-upload.get.config.xml.mt");
		mainTemplates.add(template346e + "html-upload.get_en.properties.mt");
		mainTemplates.add(template346e + "html-upload.get_fr.properties.mt");
		mainTemplates.add(template346e + "html-upload.get_de.properties.mt");
		mainTemplates.add(template346e + "html-upload.get_es.properties.mt");
		mainTemplates.add(template346e + "html-upload.get_it.properties.mt");

		mainTemplates.add(template346e + "searchResultView.mt");

		return mainTemplates;
	}

	@Override
	protected List<Map<String, String>> getTemplatesSubstitution() {
		
		List<Map<String, String>> templatesSubstitution = super.getTemplatesSubstitution();
		Map<String, String> map = new HashMap<String, String>();

		// replace template to be compliant with alfresco community 3.4.6
		map.put(templatesRoot34d + "customViews.ftl.mt", template346e + "customViews.ftl.mt");

		templatesSubstitution.add(map);
		return templatesSubstitution;
	}

}
