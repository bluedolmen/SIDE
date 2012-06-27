package com.bluexml.side.view.generator.alfresco34d;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.eclipse.emf.ecore.EObject;

public class ViewGenerator extends com.bluexml.side.view.generator.alfresco.ViewAlfrescoGenerator {

	public final static String TEMPLATE_HOME_34d = "/com.bluexml.side.View.generator.alfresco34d/com/bluexml/side/view/generator/alfresco34d/templates";
	public final static String OPTION_datagrid = "view.generator.share.datagrid";

	@Override
	protected List<String> getOptionalTemplates() {
		List<String> optionalTemplates = super.getOptionalTemplates();
		if (getGeneratorOptionValue(OPTION_datagrid)) {
			optionalTemplates.add(TEMPLATE_HOME_34d + "/datagrids/context.xml.mt");
			optionalTemplates.add(TEMPLATE_HOME_34d + "/datagrids/formGenerator.mt");
		}
		return optionalTemplates;
	}

	/*
	 * (non-Javadoc)
	 * @seecom.bluexml.side.view.generator.alfresco.ViewAlfrescoGenerator#
	 * getTemplatesSubstitution()
	 */
	@Override
	protected List<Map<String, String>> getTemplatesSubstitution() {
		List<Map<String, String>> templatesSubstitution = super.getTemplatesSubstitution();
		Map<String, String> map = new HashMap<String, String>();
		map.put(TEMPLATE_HOME + "/doclistView/doclist_ftl.mt", TEMPLATE_HOME_34d + "/doclist_ftl.mt");

		map.put(TEMPLATE_HOME + "/webscripts/csv/alfrescoGenerator_template_result_get.mt", TEMPLATE_HOME_34d + "/webscripts/csv/alfrescoGenerator_template_result_get.mt");
		map.put(TEMPLATE_HOME + "/webscripts/csv/alfrescoGenerator_template_result_post.mt", TEMPLATE_HOME_34d + "/webscripts/csv/alfrescoGenerator_template_result_post.mt");
		map.put(TEMPLATE_HOME + "/webscripts/csv/alfrescoGenerator_template.mt", TEMPLATE_HOME_34d + "/webscripts/csv/alfrescoGenerator_template.mt");
		map.put(TEMPLATE_HOME + "/webscripts/json/alfrescoGenerator_template_json.mt", TEMPLATE_HOME_34d + "/webscripts/json/alfrescoGenerator_template_json.mt");
		map.put(TEMPLATE_HOME + "/webscripts/json/alfrescoGenerator_template_result_get.mt", TEMPLATE_HOME_34d + "/webscripts/json/alfrescoGenerator_template_result_get.mt");
		map.put(TEMPLATE_HOME + "/webscripts/json/alfrescoGenerator_template_result_post.mt", TEMPLATE_HOME_34d + "/webscripts/json/alfrescoGenerator_template_result_post.mt");
		map.put(TEMPLATE_HOME + "/webscripts/rss/alfrescoGenerator_template_result_get.mt", TEMPLATE_HOME_34d + "/webscripts/rss/alfrescoGenerator_template_result_get.mt");
		map.put(TEMPLATE_HOME + "/webscripts/rss/alfrescoGenerator_template_result_post.mt", TEMPLATE_HOME_34d + "/webscripts/rss/alfrescoGenerator_template_result_post.mt");
		map.put(TEMPLATE_HOME + "/webscripts/rss/alfrescoGenerator_template_rss.mt", TEMPLATE_HOME_34d + "/webscripts/rss/alfrescoGenerator_template_rss.mt");
		map.put(TEMPLATE_HOME + "/webscripts/xml/alfrescoGenerator_template_result_get.mt", TEMPLATE_HOME_34d + "/webscripts/xml/alfrescoGenerator_template_result_get.mt");
		map.put(TEMPLATE_HOME + "/webscripts/xml/alfrescoGenerator_template_result_post.mt", TEMPLATE_HOME_34d + "/webscripts/xml/alfrescoGenerator_template_result_post.mt");
		map.put(TEMPLATE_HOME + "/webscripts/xml/alfrescoGenerator_template_xml.mt", TEMPLATE_HOME_34d + "/webscripts/xml/alfrescoGenerator_template_xml.mt");
		map.put(TEMPLATE_HOME + "/webscripts/alfrescoGenerator_template_def_get.mt", TEMPLATE_HOME_34d + "/webscripts/alfrescoGenerator_template_def_get.mt");
		map.put(TEMPLATE_HOME + "/webscripts/alfrescoGenerator_template_def_post.mt", TEMPLATE_HOME_34d + "/webscripts/alfrescoGenerator_template_def_post.mt");
		map.put(TEMPLATE_HOME + "/webscripts/alfrescoGenerator_template_js_get.mt", TEMPLATE_HOME_34d + "/webscripts/alfrescoGenerator_template_js_get.mt");
		map.put(TEMPLATE_HOME + "/webscripts/alfrescoGenerator_template_js_post.mt", TEMPLATE_HOME_34d + "/webscripts/alfrescoGenerator_template_js_post.mt");
		templatesSubstitution.add(map);
		return templatesSubstitution;
	}

	// acceleo services
	public String getModuleIdService(EObject ob, String modelId) throws Exception {
		return buildModuleProperties(modelId).getProperty("module.id"); //$NON-NLS-1$
	}
}
