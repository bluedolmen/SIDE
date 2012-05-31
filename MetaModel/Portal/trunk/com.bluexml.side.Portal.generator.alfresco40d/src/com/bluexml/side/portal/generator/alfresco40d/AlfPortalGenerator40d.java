package com.bluexml.side.portal.generator.alfresco40d;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.eclipse.emf.ecore.EObject;

import com.bluexml.side.portal.generator.alfresco34d.PortalGenerator;

public class AlfPortalGenerator40d extends PortalGenerator {
	protected static final String template40d = "/com.bluexml.side.Portal.generator.alfresco40d/com/bluexml/side/portal/generator/alfresco40d/templates/";

	protected static final String UPLOAD40d = template40d + "upload/";

	protected static final String CREATE_SITE40d = template40d + "create-site/";

	protected static final String PORTAL40d = template40d + "portal/";

	protected static final String CONFIGS40d = template40d + "configs/";

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

		mainTemplates.add(UPLOAD40d + "flash-upload.get.config.xml.mt");
		mainTemplates.add(UPLOAD40d + "flash-upload.get_en.properties.mt");
		mainTemplates.add(UPLOAD40d + "flash-upload.get_fr.properties.mt");
		mainTemplates.add(UPLOAD40d + "flash-upload.get_de.properties.mt");
		mainTemplates.add(UPLOAD40d + "flash-upload.get_es.properties.mt");
		mainTemplates.add(UPLOAD40d + "flash-upload.get_it.properties.mt");
		mainTemplates.add(UPLOAD40d + "flash-upload.get_ja.properties.mt");
		mainTemplates.add(UPLOAD40d + "flash-upload.get_nl.properties.mt");
		mainTemplates.add(UPLOAD40d + "flash-upload.get.properties.mt");

		mainTemplates.add(UPLOAD40d + "html-upload.get.config.xml.mt");
		mainTemplates.add(UPLOAD40d + "html-upload.get_en.properties.mt");
		mainTemplates.add(UPLOAD40d + "html-upload.get_fr.properties.mt");
		mainTemplates.add(UPLOAD40d + "html-upload.get_de.properties.mt");
		mainTemplates.add(UPLOAD40d + "html-upload.get_es.properties.mt");
		mainTemplates.add(UPLOAD40d + "html-upload.get_it.properties.mt");
		mainTemplates.add(UPLOAD40d + "html-upload.get_ja.properties.mt");
		mainTemplates.add(UPLOAD40d + "html-upload.get_nl.properties.mt");
		mainTemplates.add(UPLOAD40d + "html-upload.get.properties.mt");

		mainTemplates.add(CREATE_SITE40d + "create-site.get_ja.properties.mt");
		mainTemplates.add(CREATE_SITE40d + "create-site.get_nl.properties.mt");
		mainTemplates.add(CREATE_SITE40d + "create-site.get.properties.mt");

		return mainTemplates;
	}

	@Override
	protected List<Map<String, String>> getTemplatesSubstitution() {
		List<Map<String, String>> templatesSubstitution = super.getTemplatesSubstitution();
		Map<String, String> templatesSubstitutionMap = new HashMap<String, String>();

		// remove some templates
		templatesSubstitutionMap.put(TEMPLATE_PATH_32R2_portalShare + "title.mt", null);
		templatesSubstitutionMap.put(TEMPLATE_PATH_32R2_portalShare + "navigation.mt", null);

		// overrides 32r2 templates
		templatesSubstitutionMap.put(TEMPLATE_PATH_32R2_portalShare + "presets.mt", PORTAL40d + "presets.mt");
		templatesSubstitutionMap.put(TEMPLATE_PATH_32R2_portalShare + "template_js.mt", PORTAL40d + "template_js.mt");
		templatesSubstitutionMap.put(TEMPLATE_PATH_32R2_portalShare + "shareComponents.mt", PORTAL40d + "template-instance.mt");
		templatesSubstitutionMap.put(TEMPLATE_PATH_32R2_portalShare + "messages-context.mt", CONFIGS40d + "portal-context.mt");

		// overrides 34d templates 
		templatesSubstitutionMap.put(templatesRoot34d + "create-site.get_de.properties.mt", CREATE_SITE40d + "create-site.get_de.properties.mt");
		templatesSubstitutionMap.put(templatesRoot34d + "create-site.get_fr.properties.mt", CREATE_SITE40d + "create-site.get_fr.properties.mt");
		templatesSubstitutionMap.put(templatesRoot34d + "create-site.get_es.properties.mt", CREATE_SITE40d + "create-site.get_es.properties.mt");
		templatesSubstitutionMap.put(templatesRoot34d + "create-site.get_it.properties.mt", CREATE_SITE40d + "create-site.get_it.properties.mt");
		templatesSubstitutionMap.put(templatesRoot34d + "custom-share-config.xml.mt", CONFIGS40d + "custom-share-config.xml.mt");
		templatesSubstitutionMap.put(templatesRoot34d + "document-details.xml.mt", PORTAL40d + "document-details.xml.mt");
		templatesSubstitutionMap.put(templatesRoot34d + "edit-metadata.xml.mt", PORTAL40d + "edit-metadata.xml.mt");
		templatesSubstitutionMap.put(templatesRoot34d + "folder-details.xml.mt", PORTAL40d + "folder-details.xml.mt");
		templatesSubstitutionMap.put(templatesRoot34d + "inline-edit.xml.mt", PORTAL40d + "inline-edit.xml.mt");

		templatesSubstitution.add(templatesSubstitutionMap);
		return templatesSubstitution;
	}

}
