package com.bluexml.side.clazz.generator.alfresco;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Properties;

import org.eclipse.emf.ecore.EObject;

import com.bluexml.side.util.generator.XMLConflictResolver;
import com.bluexml.side.util.generator.alfresco.AbstractAlfrescoGenerator;

public class ClassAlfrescoGenerator extends AbstractAlfrescoGenerator {

	protected static final String versionProperty = "com.bluexml.side.Class.generator.alfresco.module.version";
	/*
	 * final fields used in generation too
	 */
	public static String GENERATOR_OPTIONS_PORTAL = "alfrescoShare.defaultDocList";
	public static String GENERATOR_OPTIONS_SHARE_EXTENSION = "alfresco.share.extension";
	public static String GENERATOR_OPTIONS_WEBSCRIPT_REPORT = "alfresco.webscript.report";
	public static String GENERATOR_OPTIONS_DEFAULTFORMS = "class.alfrescoShare.defaultForms";
	public static String GENERATOR_OPTIONS_SQL_EXTENSION = "com.bluexml.side.Class.generator.alfresco.sql";
	public static String GENERATOR_CODE = "CODE_GED_G_C_ALFRESCO_3";
	public static String WEBSCRIPT_SIDE_FAMILY="/service/index/family/SIDE";
	XMLConflictResolver xmlresolver = null;

	public XMLConflictResolver getXmlresolver() {
		if (xmlresolver == null) {
			xmlresolver = new XMLConflictResolver(this.getCresolver());
		}
		return xmlresolver;
	}

	public static String MMUri = "http://www.kerblue.org/class/1.0";

	public List<String> classTemplates = null;

	public List<String> getClassTemplates() {
		if (classTemplates == null) {
			List<String> result = new ArrayList<String>();

			result.add("/com.bluexml.side.Class.generator.alfresco/templates/Model/alfrescoGenerator_model.mt");
			result.add("/com.bluexml.side.Class.generator.alfresco/templates/Model/alfrescoGenerator_model_properties.mt");
			// Association synchronisation
			result.add("/com.bluexml.side.Class.generator.alfresco/templates/Model/association_synchronization.mt");

			result.add("/com.bluexml.side.Class.generator.alfresco/templates/webClient/alfrescoGenerator_web_client_config.mt");

			result.add("/com.bluexml.side.Class.generator.alfresco/templates/alfrescoGenerator_context.mt");

			if (getGeneratorOptionValue(GENERATOR_OPTIONS_SQL_EXTENSION)) {
				result.add("/com.bluexml.side.Class.generator.alfresco/templates/sqlTemplates/database_dictionary.mt");
			}
			
			// DefaultdocListView add custom version of docList webScript
			// used by AlfrescoShare to render Document list
			if (getGeneratorOptionValue(GENERATOR_OPTIONS_PORTAL)) {
				result.add("/com.bluexml.side.Class.generator.alfresco/templates/shareExtentions/DefaultdocListView/customViews.ftl.mt");
				// default view
				result.add("/com.bluexml.side.Class.generator.alfresco/templates/shareExtentions/DefaultdocListView/defaultdoclistView_ftl.mt");
			}
			
			if (getGeneratorOptionValue(GENERATOR_OPTIONS_SHARE_EXTENSION)) {
				// static resources to enable to use view in doclist now com.bluexml.side.Integration.alfresco.doclist dependence
//				result.add("/com.bluexml.side.Class.generator.alfresco/templates/shareExtentions/DefaultdocListView/doclist.get.desc.xml.mt");
//				result.add("/com.bluexml.side.Class.generator.alfresco/templates/shareExtentions/DefaultdocListView/doclist.get.html.ftl.mt");
//				result.add("/com.bluexml.side.Class.generator.alfresco/templates/shareExtentions/DefaultdocListView/doclist.get.js.mt");
//				result.add("/com.bluexml.side.Class.generator.alfresco/templates/shareExtentions/DefaultdocListView/doclist.get.json.ftl.mt");

				// generator for alfresco Share web application
				result.add("/com.bluexml.side.Class.generator.alfresco/templates/alfrescoshare/uploadForm/file-upload-response-get-patch.mt");
				result.add("/com.bluexml.side.Class.generator.alfresco/templates/alfrescoshare/uploadForm/flash-upload-js-get-patch.mt");
				result.add("/com.bluexml.side.Class.generator.alfresco/templates/alfrescoshare/uploadForm/html-upload-js-get-patch.mt");
				result.add("/com.bluexml.side.Class.generator.alfresco/templates/alfrescoshare/defaultdocListView/documentlist.get.properties.mt");

			}

			if (getGeneratorOptionValue(GENERATOR_OPTIONS_WEBSCRIPT_REPORT)) {
				result.add("/com.bluexml.side.Class.generator.alfresco/templates/webscript/alfrescoGenerator_template_def_get.mt");
				result.add("/com.bluexml.side.Class.generator.alfresco/templates/webscript/alfrescoGenerator_template_def_post.mt");
				result.add("/com.bluexml.side.Class.generator.alfresco/templates/webscript/alfrescoGenerator_template_js_get.mt");
				result.add("/com.bluexml.side.Class.generator.alfresco/templates/webscript/alfrescoGenerator_template_js_post.mt");
				// Templates html
				//result.add("/com.bluexml.side.Class.generator.alfresco/templates/webscript/html/alfrescoGenerator_template_html.mt");
				//result.add("/com.bluexml.side.Class.generator.alfresco/templates/webscript/html/alfrescoGenerator_template_result_post.mt");
				//result.add("/com.bluexml.side.Class.generator.alfresco/templates/webscript/html/alfrescoGenerator_template_result_get.mt");
				
				// Template Json
				//result.add("/com.bluexml.side.Class.generator.alfresco/templates/webscript/json/alfrescoGenerator_template_json.mt");
				//result.add("/com.bluexml.side.Class.generator.alfresco/templates/webscript/json/alfrescoGenerator_template_result_post.mt");
				//result.add("/com.bluexml.side.Class.generator.alfresco/templates/webscript/json/alfrescoGenerator_template_result_get.mt");

				// Templates RSS
				//result.add("/com.bluexml.side.Class.generator.alfresco/templates/webscript/rss/alfrescoGenerator_template_rss.mt");
				//result.add("/com.bluexml.side.Class.generator.alfresco/templates/webscript/rss/alfrescoGenerator_template_result_post.mt");
				//result.add("/com.bluexml.side.Class.generator.alfresco/templates/webscript/rss/alfrescoGenerator_template_result_get.mt");

				result.add("/com.bluexml.side.Class.generator.alfresco/templates/webscript/xml/alfrescoGenerator_template_xml.mt");
				result.add("/com.bluexml.side.Class.generator.alfresco/templates/webscript/xml/alfrescoGenerator_template_result_post.mt");
				result.add("/com.bluexml.side.Class.generator.alfresco/templates/webscript/xml/alfrescoGenerator_template_result_get.mt");

				result.add("/com.bluexml.side.Class.generator.alfresco/templates/webscript/all/alfrescoGenerator_template_def_get.mt");
				result.add("/com.bluexml.side.Class.generator.alfresco/templates/webscript/all/alfrescoGenerator_template_def_post.mt");
				result.add("/com.bluexml.side.Class.generator.alfresco/templates/webscript/all/alfrescoGenerator_template_js_get.mt");
				result.add("/com.bluexml.side.Class.generator.alfresco/templates/webscript/all/alfrescoGenerator_template_js_post.mt");

				result.add("/com.bluexml.side.Class.generator.alfresco/templates/webscript/all/xml/alfrescoGenerator_template_xml.mt");
				result.add("/com.bluexml.side.Class.generator.alfresco/templates/webscript/all/xml/alfrescoGenerator_template_result_post.mt");
				result.add("/com.bluexml.side.Class.generator.alfresco/templates/webscript/all/xml/alfrescoGenerator_template_result_get.mt");
				addServiceLog("SIDE content type webscript", "webscript can be use to list document for reporting purpose", getGenerationParameter(CONFIGURATION_PARAMETER_ALFRESCO_HOME)+WEBSCRIPT_SIDE_FAMILY);
			}

			if (getGeneratorOptionValue(GENERATOR_OPTIONS_DEFAULTFORMS)) {
				// default Forms for custom types
				result.add("/com.bluexml.side.Class.generator.alfresco/templates/alfrescoshare/DefaultEditForms/web-framework-config-custom.mt");
			}

			classTemplates = result;
		}
		return classTemplates;
	}

	public void setClassTemplates(List<String> classTemplates) {
		this.classTemplates = classTemplates;
	}

	@Override
	protected String getMetamodelURI() {
		return MMUri;
	}

	@Override
	protected List<String> getTemplates() {
		return getClassTemplates();
	}

	public Properties buildModuleProperties(String rootPackage) {
		Date now = new Date();
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy hh:mm:ss");
		Properties props = new Properties();
		props.put("module.id", "SIDE_ModelExtension_" + rootPackage);
		props.put("module.version", getVersioNumber());
		props.put("module.title", "S-IDE model extension");
		props.put("module.description", "this module contains S-IDE generated extension to extends Alfresco Data Model,\n build at " + sdf.format(now));
		return props;
	}


	public String getModuleIdService(EObject ob, String modelId) throws Exception {
		return buildModuleProperties(modelId).getProperty("module.id");
	}

	/**
	 * This method check if the user have the license to use this generator.
	 * 
	 * @return true if the generator can be used.
	 */
	public boolean check() {
		return true;
		//return SecurityHelper.check(GENERATOR_CODE, SidePreferences.getKey());
	}

}
