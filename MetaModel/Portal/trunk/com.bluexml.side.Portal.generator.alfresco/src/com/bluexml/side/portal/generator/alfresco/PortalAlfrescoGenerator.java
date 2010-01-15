package com.bluexml.side.portal.generator.alfresco;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Properties;

import org.eclipse.emf.ecore.EObject;

import com.bluexml.side.util.generator.AbstractGenerator;
import com.bluexml.side.util.generator.alfresco.AbstractAlfrescoGenerator;

public class PortalAlfrescoGenerator extends AbstractAlfrescoGenerator {

	static String GENERATOR_PARAM_SHAREURL = "alfresco.share.url";

	static String GENERATOR_PARAM_XFORMURL = "com.bluexml.side.Form.generator.xforms.chiba.webappContext";

	private static String MMUri = "http://www.kerblue.org/portal/1.0";
	static String GENERATOR_OPTIONS_DOCLIST = "com.bluexml.side.Portal.generator.alfresco.doclist";
	static String GENERATOR_OPTIONS_FORMS = "com.bluexml.side.Portal.generator.alfresco.forms";
	static String GENERATOR_OPTIONS_FACETMAP = "com.bluexml.side.Portal.generator.alfresco.facetmap";
	static String GENERATOR_OPTIONS_XFORMS = "com.bluexml.side.Portal.generator.alfresco.xforms";

	@Override
	public Properties buildModuleProperties(String modelId) {
		Date now = new Date();
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy hh:mm:ss");
		Properties props = new Properties();
		props.put("module.id", "SIDE_PortalExtension_" + modelId);
		props.put("module.version", getVersioNumber());
		props.put("module.title", "S-IDE portal extension");
		props.put("module.description", "this module contains S-IDE generated extension to extends Alfresco Share,\n build at " + sdf.format(now));
		return props;
	}

	public boolean check() {
		return true;
	}

	@Override
	protected String getMetamodelURI() {
		return MMUri;
	}

	@Override
	protected List<String> getTemplates() {
		List<String> result = new ArrayList<String>();
		if (getGeneratorOptionValue(GENERATOR_OPTIONS_DOCLIST)) {
			result.add("/com.bluexml.side.Portal.generator.alfresco/com/bluexml/side/portal/generator/alfresco/templates/documentLibrary/DocumentLibraryPortletView.ftl.mt");
		}
		// if (getGeneratorOptionValue(GENERATOR_OPTIONS_FORMS)) {
		// see web-framework-config-custom.mt
		// }
		if (getGeneratorOptionValue(GENERATOR_OPTIONS_FACETMAP)) {
			// result.add("/com.bluexml.side.Portal.generator.alfresco/com/bluexml/side/portal/generator/alfresco/templates/portalShare/web-framework-config-custom.mt");
			result.add("/com.bluexml.side.Portal.generator.alfresco/com/bluexml/side/portal/generator/alfresco/templates/portalShare/presets.mt");
			result.add("/com.bluexml.side.Portal.generator.alfresco/com/bluexml/side/portal/generator/alfresco/templates/portalShare/page.mt");
			result.add("/com.bluexml.side.Portal.generator.alfresco/com/bluexml/side/portal/generator/alfresco/templates/portalShare/css.mt");
			result.add("/com.bluexml.side.Portal.generator.alfresco/com/bluexml/side/portal/generator/alfresco/templates/portalShare/template-instances.mt");
			result.add("/com.bluexml.side.Portal.generator.alfresco/com/bluexml/side/portal/generator/alfresco/templates/portalShare/title.mt");
			result.add("/com.bluexml.side.Portal.generator.alfresco/com/bluexml/side/portal/generator/alfresco/templates/portalShare/navigation.mt");
			result.add("/com.bluexml.side.Portal.generator.alfresco/com/bluexml/side/portal/generator/alfresco/templates/portalShare/shareComponents.mt");
			result.add("/com.bluexml.side.Portal.generator.alfresco/com/bluexml/side/portal/generator/alfresco/templates/portalShare/template.mt");
			result.add("/com.bluexml.side.Portal.generator.alfresco/com/bluexml/side/portal/generator/alfresco/templates/portalShare/template_js.mt");
			result.add("/com.bluexml.side.Portal.generator.alfresco/com/bluexml/side/portal/generator/alfresco/templates/portalShare/messages.mt");
		}
		if (getGeneratorOptionValue(GENERATOR_OPTIONS_XFORMS)) {
			// XForm portlet
			result.add("/com.bluexml.side.Portal.generator.alfresco/com/bluexml/side/portal/generator/alfresco/templates/xformsIntegration/Xform/Xform.get.desc.xml.mt");
			result.add("/com.bluexml.side.Portal.generator.alfresco/com/bluexml/side/portal/generator/alfresco/templates/xformsIntegration/Xform/Xform.get.head.ftl.mt");
			result.add("/com.bluexml.side.Portal.generator.alfresco/com/bluexml/side/portal/generator/alfresco/templates/xformsIntegration/Xform/Xform.get.html.ftl.mt");
			result.add("/com.bluexml.side.Portal.generator.alfresco/com/bluexml/side/portal/generator/alfresco/templates/xformsIntegration/Xform/Xform.get.js.mt");
			result.add("/com.bluexml.side.Portal.generator.alfresco/com/bluexml/side/portal/generator/alfresco/templates/xformsIntegration/Xform/Xform.get.properties.mt");
			// callback page (redirect to share page before editing)
			result.add("/com.bluexml.side.Portal.generator.alfresco/com/bluexml/side/portal/generator/alfresco/templates/xformsIntegration/edit-metadataCallBack.html.mt");
			// override edit-metadata page template
			result.add("/com.bluexml.side.Portal.generator.alfresco/com/bluexml/side/portal/generator/alfresco/templates/xformsIntegration/templates-edit-metadataXForm.ftl.mt");
			result.add("/com.bluexml.side.Portal.generator.alfresco/com/bluexml/side/portal/generator/alfresco/templates/xformsIntegration/edit-metadata.xml.mt");
			result.add("/com.bluexml.side.Portal.generator.alfresco/com/bluexml/side/portal/generator/alfresco/templates/xformsIntegration/template.edit-metadata-XForm.edit-metadata.xml.mt");
			result.add("/com.bluexml.side.Portal.generator.alfresco/com/bluexml/side/portal/generator/alfresco/templates/xformsIntegration/edit-metadata-mgr.get.html.ftl.mt");
		}

		// shared resources
		// includes of GENERATOR_OPTIONS_FORMS and GENERATOR_OPTIONS_FACETMAP
		// options
		result.add("/com.bluexml.side.Portal.generator.alfresco/com/bluexml/side/portal/generator/alfresco/templates/shared/web-framework-config-custom.mt");
		return result;
	}

	public static boolean getGeneratorOptionValue(EObject o, String key) {
		System.out.println("options :" + AbstractGenerator.generatorOptions.values());

		return getGeneratorOptionValue(key);
	}

	public String getXFORMURL(EObject o) {
		String result = getGenerationParameter(GENERATOR_PARAM_XFORMURL);
		if (result == null || result.equals("")) {
			System.out.println("xform url, default value used");
			result = "http://localhost:8080/xforms";
		}
		return result;
	}
	public String getSHAREURL(EObject o) {
		String result = getGenerationParameter(GENERATOR_PARAM_SHAREURL);
		if (result == null || result.equals("")) {
			System.out.println("share url, default value used");
			result = "http://localhost:8080/share";
		}
		return result;
	}
}
