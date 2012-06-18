package com.bluexml.side.portal.generator.alfresco346e;

import java.util.List;

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

}
