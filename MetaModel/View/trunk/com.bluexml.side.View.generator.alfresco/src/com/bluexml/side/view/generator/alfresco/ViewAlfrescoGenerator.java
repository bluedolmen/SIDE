package com.bluexml.side.view.generator.alfresco;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Properties;

import com.bluexml.side.util.generator.alfresco.AbstractAlfrescoGenerator;

public class ViewAlfrescoGenerator extends AbstractAlfrescoGenerator {
	
	public static String TEMPLATE_HOME = "/com.bluexml.side.View.generator.alfresco/com/bluexml/side/view/generator/alfresco/templates";
	public static String MMUri = "http://www.kerblue.org/view/1.0";
	public static String GENERATOR_OPTIONS_DOCLIST = "alfresco.view.doclist";

	public ViewAlfrescoGenerator() {
		versionProperty = "com.bluexml.side.View.generator.alfresco.module.version";
	}

	@Override
	protected String getMetamodelURI() {
		return MMUri;
	}

	@Override
	protected List<String> getMainTemplates() {
		List<String> result = new ArrayList<String>();

		// doclist
		result.add(TEMPLATE_HOME + "/doclistView/doclist_ftl.mt");

		// data webscripts
		result.add(TEMPLATE_HOME + "/webscripts/alfrescoGenerator_template_def_get.mt");
		result.add(TEMPLATE_HOME + "/webscripts/alfrescoGenerator_template_def_post.mt");
		result.add(TEMPLATE_HOME + "/webscripts/alfrescoGenerator_template_js_get.mt");
		result.add(TEMPLATE_HOME + "/webscripts/alfrescoGenerator_template_js_post.mt");
		result.add(TEMPLATE_HOME + "/webscripts/json/alfrescoGenerator_template_json.mt");
		result.add(TEMPLATE_HOME + "/webscripts/json/alfrescoGenerator_template_result_get.mt");
		result.add(TEMPLATE_HOME + "/webscripts/json/alfrescoGenerator_template_result_post.mt");
		result.add(TEMPLATE_HOME + "/webscripts/xml/alfrescoGenerator_template_xml.mt");
		result.add(TEMPLATE_HOME + "/webscripts/xml/alfrescoGenerator_template_result_get.mt");
		result.add(TEMPLATE_HOME + "/webscripts/xml/alfrescoGenerator_template_result_post.mt");
		result.add(TEMPLATE_HOME + "/webscripts/rss/alfrescoGenerator_template_rss.mt");
		result.add(TEMPLATE_HOME + "/webscripts/rss/alfrescoGenerator_template_result_get.mt");
		result.add(TEMPLATE_HOME + "/webscripts/rss/alfrescoGenerator_template_result_post.mt");

		result.add(TEMPLATE_HOME + "/webscripts/csv/alfrescoGenerator_template_result_get.mt");
		result.add(TEMPLATE_HOME + "/webscripts/csv/alfrescoGenerator_template_result_post.mt");
		result.add(TEMPLATE_HOME + "/webscripts/csv/alfrescoGenerator_template.mt");
		// services :
		monitor.getLog().addServiceLog("Alfresco Share Views", "Share Document Library", getGenerationParameter(CONFIGURATION_PARAMETER_ALFRESCOSHARE_URL) + "/page/site/yourSiteUrl/documentlibrary");

		return result;
	}

	@Override
	public boolean check() {
		return true;
	}

	@Override
	public Properties buildModuleProperties(String modelId) {
		Date now = new Date();
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy hh:mm:ss");
		Properties props = new Properties();
		props.put("module.id", "SIDE_ViewExtension_" + modelId);
		props.put("module.version", getVersioNumber());
		props.put("module.title", "SIDE view extension");
		props.put("module.description", "this module contains SIDE generated extension to extends Alfresco view,\n build at " + sdf.format(now));
		return props;
	}

}
