package com.bluexml.side.clazz.generator.alfresco;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Properties;

import org.eclipse.emf.ecore.EObject;

import com.bluexml.side.util.generator.XMLConflictResolver;
import com.bluexml.side.util.generator.alfresco.AbstractAlfrescoGenerator;

public class ClassAlfrescoGenerator extends AbstractAlfrescoGenerator {
	public static String templateBase = "/com.bluexml.side.Class.generator.alfresco/templates/";
	
	protected static final String ALFRESCO_SEARCH_ASSOCIATION = "alfresco.model.association.searchable";
	/*
	 * final fields used in generation too
	 */
	public static String GENERATOR_OPTIONS_DataGenerator = "com.bluexml.side.Class.generator.alfresco.randomDataGenerator"; //$NON-NLS-1$
	public static String GENERATOR_OPTIONS_PORTAL_doclist = "alfrescoShare.defaultDocList"; //$NON-NLS-1$
	public static String GENERATOR_OPTIONS_SHARE_EXTENSION = "alfresco.share.extension"; //$NON-NLS-1$
	public static String GENERATOR_OPTIONS_UNICITY = "alfresco.unicity"; //$NON-NLS-1$
	public static String GENERATOR_OPTIONS_WEBSCRIPT_REPORT = "alfresco.webscript.report"; //$NON-NLS-1$

	public static String GENERATOR_PARAMETER_WEBSCRIPT_REPORT_RUNAS = "alfresco.report.runas"; //$NON-NLS-1$

	// public static String GENERATOR_OPTIONS_DEFAULTFORMS =
	// "class.alfrescoShare.defaultForms";
	public static String GENERATOR_OPTIONS_SQL_EXTENSION = "com.bluexml.side.Class.generator.alfresco.sql"; //$NON-NLS-1$
	public static String WEBSCRIPT_SIDE_FAMILY = "/service/index/family/SIDE"; //$NON-NLS-1$
	XMLConflictResolver xmlresolver = null;

	public ClassAlfrescoGenerator() {
		versionProperty = "com.bluexml.side.Class.generator.alfresco.module.version"; //$NON-NLS-1$
	}

	public static String MMUri = "http://www.kerblue.org/class/1.0"; //$NON-NLS-1$

	@Override
	protected List<String> getMainTemplates() {
		List<String> result = new ArrayList<String>();
		result.add(templateBase + "Model/alfrescoGenerator_model.mt"); //$NON-NLS-1$
		result.add(templateBase + "Model/alfrescoGenerator_model_properties.mt"); //$NON-NLS-1$
		// Association synchronisation
		result.add(templateBase + "Model/association_synchronization.mt"); //$NON-NLS-1$

		result.add(templateBase + "webClient/alfrescoGenerator_web_client_config.mt"); //$NON-NLS-1$

		result.add(templateBase + "alfrescoGenerator_context.mt"); //$NON-NLS-1$

		// standard alfresco services :
		monitor.getLog().addServiceLog("Alfresco", Activator.Messages.getString("ClassAlfrescoGenerator_4"), getGenerationParameter(CONFIGURATION_PARAMETER_ALFRESCO_URL)); //$NON-NLS-1$ //$NON-NLS-2$
		monitor.getLog().addServiceLog(Activator.Messages.getString("ClassAlfrescoGenerator_5"), Activator.Messages.getString("ClassAlfrescoGenerator_6"), getGenerationParameter(CONFIGURATION_PARAMETER_ALFRESCO_URL) + "/manager/list"); //$NON-NLS-1$ //$NON-NLS-2$//$NON-NLS-3$

		return result;
	}

	@Override
	protected List<String> getOptionalTemplates() {
		List<String> result = new ArrayList<String>();
		if (getGeneratorOptionValue(GENERATOR_OPTIONS_SQL_EXTENSION)) {
			result.add(templateBase + "sqlTemplates/database_dictionary.mt"); //$NON-NLS-1$
			monitor.getLog().addServiceLog(Activator.Messages.getString("ClassAlfrescoGenerator_0"), Activator.Messages.getString("ClassAlfrescoGenerator_1"), getGenerationParameter(CONFIGURATION_PARAMETER_ALFRESCO_URL) + "/service/index/package/extension/com/bluexml/side/sql"); //$NON-NLS-1$ //$NON-NLS-2$//$NON-NLS-3$
		}

		// DefaultdocListView add custom version of docList webScript
		// used by AlfrescoShare to render Document list
		//if (getGeneratorOptionValue(GENERATOR_OPTIONS_PORTAL_doclist)) {
		//result.add(templateBase+ "/shareExtentions/DefaultdocListView/customViews.ftl.mt"); //$NON-NLS-1$
		// default view
		//result.add(templateBase+ "/shareExtentions/DefaultdocListView/defaultdoclistView_ftl.mt"); //$NON-NLS-1$
		//}

		if (getGeneratorOptionValue(GENERATOR_OPTIONS_SHARE_EXTENSION)) {
			result.addAll(getShareExtensionTemplates());
		}

		if (getGeneratorOptionValue(GENERATOR_OPTIONS_WEBSCRIPT_REPORT)) {
			result.add(templateBase + "webscript/alfrescoGenerator_template_def_get.mt"); //$NON-NLS-1$
			result.add(templateBase + "webscript/alfrescoGenerator_template_def_post.mt"); //$NON-NLS-1$
			result.add(templateBase + "webscript/alfrescoGenerator_template_js_get.mt"); //$NON-NLS-1$
			result.add(templateBase + "webscript/alfrescoGenerator_template_js_post.mt"); //$NON-NLS-1$
			// Templates html
			// result.add(templateBase+ "/webscript/html/alfrescoGenerator_template_html.mt");
			// result.add(templateBase+ "/webscript/html/alfrescoGenerator_template_result_post.mt");
			// result.add(templateBase+ "/webscript/html/alfrescoGenerator_template_result_get.mt");

			// Template Json
			result.add(templateBase + "webscript/json/alfrescoGenerator_template_json.mt");
			result.add(templateBase + "webscript/json/alfrescoGenerator_template_result_post.mt");
			result.add(templateBase + "webscript/json/alfrescoGenerator_template_result_get.mt");

			// Templates RSS
			// result.add(templateBase+ "/webscript/rss/alfrescoGenerator_template_rss.mt");
			// result.add(templateBase+ "/webscript/rss/alfrescoGenerator_template_result_post.mt");
			// result.add(templateBase+ "/webscript/rss/alfrescoGenerator_template_result_get.mt");

			result.add(templateBase + "webscript/xml/alfrescoGenerator_template_xml.mt"); //$NON-NLS-1$
			result.add(templateBase + "webscript/xml/alfrescoGenerator_template_result_post.mt"); //$NON-NLS-1$
			result.add(templateBase + "webscript/xml/alfrescoGenerator_template_result_get.mt"); //$NON-NLS-1$

			result.add(templateBase + "webscript/all/alfrescoGenerator_template_def_get.mt"); //$NON-NLS-1$
			result.add(templateBase + "webscript/all/alfrescoGenerator_template_def_post.mt"); //$NON-NLS-1$
			result.add(templateBase + "webscript/all/alfrescoGenerator_template_js_get.mt"); //$NON-NLS-1$
			result.add(templateBase + "webscript/all/alfrescoGenerator_template_js_post.mt"); //$NON-NLS-1$

			result.add(templateBase + "webscript/all/xml/alfrescoGenerator_template_xml.mt"); //$NON-NLS-1$
			result.add(templateBase + "webscript/all/xml/alfrescoGenerator_template_result_post.mt"); //$NON-NLS-1$
			result.add(templateBase + "webscript/all/xml/alfrescoGenerator_template_result_get.mt"); //$NON-NLS-1$
			monitor.getLog().addServiceLog(Activator.Messages.getString("ClassAlfrescoGenerator_2"), //$NON-NLS-1$
					Activator.Messages.getString("ClassAlfrescoGenerator_3"), //$NON-NLS-1$
					getGenerationParameter(CONFIGURATION_PARAMETER_ALFRESCO_URL) + WEBSCRIPT_SIDE_FAMILY);
		}
		if (getGeneratorOptionValue(GENERATOR_OPTIONS_DataGenerator)) {
			monitor.getLog().addServiceLog(Activator.Messages.getString("ClassAlfrescoGenerator_9"), Activator.Messages.getString("ClassAlfrescoGenerator_10"), getGenerationParameter(CONFIGURATION_PARAMETER_ALFRESCO_URL) + "/service/data/form/fillparameters"); //$NON-NLS-3$
		}
		if (getGeneratorOptionValue(GENERATOR_OPTIONS_UNICITY)) {
			result.add(templateBase + "unicity/alfrescoGenerator_unicity.mt"); //$NON-NLS-1$
		}
		return result;
	}

	protected List<String> getShareExtensionTemplates() {
		List<String> result = new ArrayList<String>();
		// generator for alfresco Share web application
		// upload configuration
		result.add(templateBase + "alfrescoshare/uploadForm/flash-upload-js-get-patch.mt"); //$NON-NLS-1$
		result.add(templateBase + "alfrescoshare/uploadForm/html-upload-js-get-patch.mt"); //$NON-NLS-1$
		result.add(templateBase + "alfrescoshare/uploadForm/flash-upload.get.html.ftl.mt"); //$NON-NLS-1$
		result.add(templateBase + "alfrescoshare/defaultdocListView/documentlist.get.properties.mt"); //$NON-NLS-1$

		// add forms/details management
		// provided by sharePortalExtension module 
		//		result.add(templateBase+ "/alfrescoshare/DefaultEditForms/custom-web-framework-application-context.mt"); //$NON-NLS-1$
		// defaults forms
		result.add(templateBase + "alfrescoshare/DefaultEditForms/web-framework-config-defaults.mt"); //$NON-NLS-1$
		return result;
	}

	@Override
	protected String getMetamodelURI() {
		return MMUri;
	}

	@Override
	public Properties buildModuleProperties(String rootPackage) {
		Date now = new Date();
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy hh:mm:ss"); //$NON-NLS-1$
		Properties props = new Properties();
		props.put("module.id", "SIDE_ModelExtension_" + rootPackage); //$NON-NLS-1$ //$NON-NLS-2$
		props.put("module.version", getVersioNumber()); //$NON-NLS-1$
		props.put("module.title", Activator.Messages.getString("ClassAlfrescoGenerator_7")); //$NON-NLS-1$ //$NON-NLS-2$
		props.put("module.description", Activator.Messages.getString("ClassAlfrescoGenerator_8") + sdf.format(now)); //$NON-NLS-1$ //$NON-NLS-2$
		return props;
	}

	public String getModuleIdService(EObject ob, String modelId) throws Exception {
		return buildModuleProperties(modelId).getProperty("module.id"); //$NON-NLS-1$
	}

	public String getRunasforReport(EObject o) {
		String result = getGenerationParameter(GENERATOR_PARAMETER_WEBSCRIPT_REPORT_RUNAS);
		if (result == null || result.equals("")) {

		} else {
			//monitor.addWarningTextAndLog(Activator.Messages.getString("ClassAlfrescoGenerator_11"), "");
		}
		return result;
	}

	/**
	 * This method check if the user have the license to use this generator.
	 * 
	 * @return true if the generator can be used.
	 */
	@Override
	public boolean check() {
		return true;
	}

	@Override
	public boolean checkOption(String optionID) {
		return true;
	}

	@Override
	protected List<Map<String, String>> getTemplatesSubstitution() {
		return new ArrayList<Map<String, String>>();
	}

	/**
	 * this method is used to activated a generation part that can be used in
	 * conjunction with alfresco policy module that
	 * synchronize association modification with a technical property (not
	 * implemented for alfresco &lt; 3.4d)
	 * 
	 * @param o
	 * @return
	 */
	public boolean IsSearchInAssociation(EObject o) {
		System.out.println("AlfrescoGenerator.IsSearchInAssociation()");
		boolean generatorOptionValue = getGeneratorOptionValue(ALFRESCO_SEARCH_ASSOCIATION);
		System.out.println("generatorOptionValue :" + generatorOptionValue);
		return generatorOptionValue;
	}
}
