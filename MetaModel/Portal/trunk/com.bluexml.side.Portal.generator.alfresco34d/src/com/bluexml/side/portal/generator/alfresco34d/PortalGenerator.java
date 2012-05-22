package com.bluexml.side.portal.generator.alfresco34d;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;

import org.eclipse.emf.ecore.EObject;

import com.bluexml.side.portal.generator.alfresco.PortalAlfrescoGenerator;

public class PortalGenerator extends PortalAlfrescoGenerator {

	protected static String GENERATOR_OPTIONS_XFORMS_34d = "com.bluexml.side.Portal.generator.alfresco.xforms34d";
	protected final static String templatesRoot34d = "/com.bluexml.side.Portal.generator.alfresco34d/com/bluexml/side/portal/generator/alfresco34d/templates/";
	protected final static String templatesRoot32r2 = "/com.bluexml.side.Portal.generator.alfresco/com/bluexml/side/portal/generator/alfresco/templates/";

	@Override
	public Properties buildModuleProperties(String modelId) {
		Date now = new Date();
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy hh:mm:ss");
		Properties props = new Properties();
		props.put("module.id", "SIDE_PortalExtension_" + modelId);
		props.put("module.version", getVersioNumber());
		props.put("module.title", "SIDE portal extension");
		props.put("module.description", "this module contains SIDE generated extension to extends Alfresco Share,\n build at " + sdf.format(now));
		return props;
	}

	/*
	 * (non-Javadoc)
	 * @seecom.bluexml.side.portal.generator.alfresco.PortalAlfrescoGenerator#
	 * getOptionalTemplates()
	 */
	@Override
	protected List<String> getOptionalTemplates() {
		List<String> result = super.getOptionalTemplates();
		// override option definition 
		if (getGeneratorOptionValue(GENERATOR_OPTIONS_XFORMS_34d)) {
			// searchForms
			result.add(templatesRoot32r2 + "advancedSearchIntegration/template.XformSearch.advancedSearch.xml.mt");
			result.add(templatesRoot34d + "searchFormCallBack.html.mt");
			result.add(templatesRoot32r2 + "advancedSearchIntegration/XformSearch/XformSearch.get.desc.xml.mt");
			result.add(templatesRoot32r2 + "advancedSearchIntegration/XformSearch/XformSearch.get.head.ftl.mt");
			result.add(templatesRoot32r2 + "advancedSearchIntegration/XformSearch/XformSearch.get.html.ftl.mt");
			result.add(templatesRoot32r2 + "advancedSearchIntegration/XformSearch/XformSearch.get.js.mt");
			result.add(templatesRoot32r2 + "advancedSearchIntegration/XformSearch/XformSearch.get.properties.mt");
			result.add(templatesRoot32r2 + "advancedSearchIntegration/XformSearch/XformSearch.css.mt");
		}
		return result;
	}

	/*
	 * (non-Javadoc)
	 * @seecom.bluexml.side.portal.generator.alfresco.PortalAlfrescoGenerator#
	 * getTemplatesSubstitution()
	 */
	@Override
	protected List<Map<String, String>> getTemplatesSubstitution() {
		List<Map<String, String>> templatesSubstitution = super.getTemplatesSubstitution();
		Map<String, String> map = new HashMap<String, String>();

		// replace template to be compliant with alfresco community 3.4
		map.put(templatesRoot32r2 + "documentLibrary/DocumentLibraryPortletView.ftl.mt", templatesRoot34d + "customViews.ftl.mt");

		map.put(templatesRoot32r2 + "portalShare/create-site.get.properties.mt", templatesRoot34d + "create-site.get.properties.mt");
		
		templatesSubstitution.add(map);
		return templatesSubstitution;
	}

	@Override
	protected List<String> getMainTemplates() {
		List<String> result = super.getMainTemplates();

		result.add(templatesRoot34d + "create-site.get_de.properties.mt");
		result.add(templatesRoot34d + "create-site.get_fr.properties.mt");
		result.add(templatesRoot34d + "create-site.get_es.properties.mt");
		result.add(templatesRoot34d + "create-site.get_it.properties.mt");
		// select the right formId for existing page component
		result.add(templatesRoot34d + "document-details.xml.mt");
		result.add(templatesRoot34d + "edit-metadata.xml.mt");
		result.add(templatesRoot34d + "folder-details.xml.mt");
		result.add(templatesRoot34d + "inline-edit.xml.mt");

		result.add(templatesRoot34d + "toolbar.get.config.xml.mt");		
		
		result.add(templatesRoot34d + "custom-share-config.xml.mt");
		
		return result;
	}

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
}
