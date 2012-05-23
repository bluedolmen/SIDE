package com.bluexml.side.portal.generator.alfresco40d;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.eclipse.emf.ecore.EObject;

import com.bluexml.side.portal.generator.alfresco34d.PortalGenerator;

public class AlfPortalGenerator40d extends PortalGenerator {
	protected static final String template40d = "/com.bluexml.side.Portal.generator.alfresco40d/com/bluexml/side/portal/generator/alfresco40d/templates/";

	protected static final String UPLOAD = template40d + "upload/";

	protected static final String CREATE_SITE = template40d + "create-site/";
	
	protected static final String PORTAL = template40d + "portal/";

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

		mainTemplates.add(UPLOAD + "flash-upload.get.config.xml.mt");
		mainTemplates.add(UPLOAD + "flash-upload.get_en.properties.mt");
		mainTemplates.add(UPLOAD + "flash-upload.get_fr.properties.mt");
		mainTemplates.add(UPLOAD + "flash-upload.get_de.properties.mt");
		mainTemplates.add(UPLOAD + "flash-upload.get_es.properties.mt");
		mainTemplates.add(UPLOAD + "flash-upload.get_it.properties.mt");
		mainTemplates.add(UPLOAD + "flash-upload.get_ja.properties.mt");
		mainTemplates.add(UPLOAD + "flash-upload.get_nl.properties.mt");
		mainTemplates.add(UPLOAD + "flash-upload.get.properties.mt");

		mainTemplates.add(UPLOAD + "html-upload.get.config.xml.mt");
		mainTemplates.add(UPLOAD + "html-upload.get_en.properties.mt");
		mainTemplates.add(UPLOAD + "html-upload.get_fr.properties.mt");
		mainTemplates.add(UPLOAD + "html-upload.get_de.properties.mt");
		mainTemplates.add(UPLOAD + "html-upload.get_es.properties.mt");
		mainTemplates.add(UPLOAD + "html-upload.get_it.properties.mt");
		mainTemplates.add(UPLOAD + "html-upload.get_ja.properties.mt");
		mainTemplates.add(UPLOAD + "html-upload.get_nl.properties.mt");
		mainTemplates.add(UPLOAD + "html-upload.get.properties.mt");

		
		mainTemplates.add(CREATE_SITE + "create-site.get_ja.properties.mt");
		mainTemplates.add(CREATE_SITE + "create-site.get_nl.properties.mt");
		mainTemplates.add(CREATE_SITE + "create-site.get.properties.mt");
		
		return mainTemplates;
	}

	@Override
	protected List<Map<String, String>> getTemplatesSubstitution() {
		List<Map<String, String>> templatesSubstitution = super.getTemplatesSubstitution();
		Map<String, String> templatesSubstitutionMap = new HashMap<String, String>();

		templatesSubstitutionMap.put(templatesRoot34d + "create-site.get_de.properties.mt", CREATE_SITE + "create-site.get_de.properties.mt");
		templatesSubstitutionMap.put(templatesRoot34d + "create-site.get_fr.properties.mt", CREATE_SITE + "create-site.get_fr.properties.mt");
		templatesSubstitutionMap.put(templatesRoot34d + "create-site.get_es.properties.mt", CREATE_SITE + "create-site.get_es.properties.mt");
		templatesSubstitutionMap.put(templatesRoot34d + "create-site.get_it.properties.mt", CREATE_SITE + "create-site.get_it.properties.mt");
		
		templatesSubstitutionMap.put(templatesRoot32r2 + "portalShare/presets.mt", PORTAL + "presets.mt");

		
		templatesSubstitution.add(templatesSubstitutionMap);
		return templatesSubstitution;
	}

}
