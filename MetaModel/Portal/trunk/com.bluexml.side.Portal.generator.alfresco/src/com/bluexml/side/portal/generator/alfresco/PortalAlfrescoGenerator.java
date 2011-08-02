package com.bluexml.side.portal.generator.alfresco;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;

import org.eclipse.emf.ecore.EObject;

import com.bluexml.side.util.generator.alfresco.AbstractAlfrescoGenerator;

public class PortalAlfrescoGenerator extends AbstractAlfrescoGenerator {

	protected static String GENERATOR_PARAM_SHAREURL = "alfresco.share.url";
	protected static String GENERATOR_PARAM_FACETMAPURL = "facetMap.url";
	protected static String GENERATOR_PARAM_XFORMURL = "com.bluexml.side.Form.generator.xforms.chiba.webappContext";

	protected static String MMUri = "http://www.kerblue.org/portal/1.0";
	protected static String GENERATOR_OPTIONS_DOCLIST = "com.bluexml.side.Portal.generator.alfresco.doclist";
	protected static String GENERATOR_OPTIONS_FORMS = "com.bluexml.side.Portal.generator.alfresco.forms";
	protected static String GENERATOR_OPTIONS_FACETMAP = "com.bluexml.side.Portal.generator.alfresco.facetmap";
	protected static String GENERATOR_OPTIONS_XFORMS = "com.bluexml.side.Portal.generator.alfresco.xforms";

	public PortalAlfrescoGenerator() {
		versionProperty = "com.bluexml.side.Portal.generator.alfresco.module.version"; //$NON-NLS-1$
	}

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

	@Override
	public boolean check() {
		return true;
	}

	@Override
	protected String getMetamodelURI() {
		return MMUri;
	}

	@Override
	protected List<String> getMainTemplates() {
		List<String> result = new ArrayList<String>();
		// general templates, pages, navigation component
		result.add("/com.bluexml.side.Portal.generator.alfresco/com/bluexml/side/portal/generator/alfresco/templates/portalShare/presets.mt");
		result.add("/com.bluexml.side.Portal.generator.alfresco/com/bluexml/side/portal/generator/alfresco/templates/portalShare/page.mt");
		result.add("/com.bluexml.side.Portal.generator.alfresco/com/bluexml/side/portal/generator/alfresco/templates/portalShare/css.mt");
		result.add("/com.bluexml.side.Portal.generator.alfresco/com/bluexml/side/portal/generator/alfresco/templates/portalShare/template-instances.mt");
		result.add("/com.bluexml.side.Portal.generator.alfresco/com/bluexml/side/portal/generator/alfresco/templates/portalShare/title.mt");
		result.add("/com.bluexml.side.Portal.generator.alfresco/com/bluexml/side/portal/generator/alfresco/templates/portalShare/navigation.mt");
		result.add("/com.bluexml.side.Portal.generator.alfresco/com/bluexml/side/portal/generator/alfresco/templates/portalShare/shareComponents.mt");
		result.add("/com.bluexml.side.Portal.generator.alfresco/com/bluexml/side/portal/generator/alfresco/templates/portalShare/template.mt");
		result.add("/com.bluexml.side.Portal.generator.alfresco/com/bluexml/side/portal/generator/alfresco/templates/portalShare/template_js.mt");
		result.add("/com.bluexml.side.Portal.generator.alfresco/com/bluexml/side/portal/generator/alfresco/templates/portalShare/messages-context.mt");
		result.add("/com.bluexml.side.Portal.generator.alfresco/com/bluexml/side/portal/generator/alfresco/templates/portalShare/messages.mt");
		result.add("/com.bluexml.side.Portal.generator.alfresco/com/bluexml/side/portal/generator/alfresco/templates/portalShare/create-site.get.properties.mt");

		result.add("/com.bluexml.side.Portal.generator.alfresco/com/bluexml/side/portal/generator/alfresco/templates/shared/web-framework-config-custom.mt");
		return result;
	}

	@Override
	protected List<String> getOptionalTemplates() {
		List<String> result = new ArrayList<String>();
		if (getGeneratorOptionValue(GENERATOR_OPTIONS_DOCLIST)) {
			result.add("/com.bluexml.side.Portal.generator.alfresco/com/bluexml/side/portal/generator/alfresco/templates/documentLibrary/DocumentLibraryPortletView.ftl.mt");
		}
		// if (getGeneratorOptionValue(GENERATOR_OPTIONS_FORMS)) {
		// see web-framework-config-custom.mt
		// }

		if (getGeneratorOptionValue(GENERATOR_OPTIONS_FACETMAP)) {
			// result.add("/com.bluexml.side.Portal.generator.alfresco/com/bluexml/side/portal/generator/alfresco/templates/portalShare/web-framework-config-custom.mt");
			result.add("/com.bluexml.side.Portal.generator.alfresco/com/bluexml/side/portal/generator/alfresco/templates/facetMapIntegration/template.facetMap.xml.mt");
			result.add("/com.bluexml.side.Portal.generator.alfresco/com/bluexml/side/portal/generator/alfresco/templates/facetMapIntegration/facetMap/facetMap.get.desc.xml.mt");
			result.add("/com.bluexml.side.Portal.generator.alfresco/com/bluexml/side/portal/generator/alfresco/templates/facetMapIntegration/facetMap/facetMap.get.head.ftl.mt");
			result.add("/com.bluexml.side.Portal.generator.alfresco/com/bluexml/side/portal/generator/alfresco/templates/facetMapIntegration/facetMap/facetMap.get.html.ftl.mt");
			result.add("/com.bluexml.side.Portal.generator.alfresco/com/bluexml/side/portal/generator/alfresco/templates/facetMapIntegration/facetMap/facetMap.get.js.mt");
			result.add("/com.bluexml.side.Portal.generator.alfresco/com/bluexml/side/portal/generator/alfresco/templates/facetMapIntegration/facetMap/facetMap.css.mt");
		}
		if (getGeneratorOptionValue(GENERATOR_OPTIONS_XFORMS)) {
			// XForm portlet
			result.add("/com.bluexml.side.Portal.generator.alfresco/com/bluexml/side/portal/generator/alfresco/templates/xformsIntegration/Xform/Xform.get.desc.xml.mt");
			result.add("/com.bluexml.side.Portal.generator.alfresco/com/bluexml/side/portal/generator/alfresco/templates/xformsIntegration/Xform/Xform.get.head.ftl.mt");
			result.add("/com.bluexml.side.Portal.generator.alfresco/com/bluexml/side/portal/generator/alfresco/templates/xformsIntegration/Xform/Xform.get.html.ftl.mt");
			result.add("/com.bluexml.side.Portal.generator.alfresco/com/bluexml/side/portal/generator/alfresco/templates/xformsIntegration/Xform/Xform.get.js.mt");
			result.add("/com.bluexml.side.Portal.generator.alfresco/com/bluexml/side/portal/generator/alfresco/templates/xformsIntegration/Xform/Xform.get.properties.mt");
			result.add("/com.bluexml.side.Portal.generator.alfresco/com/bluexml/side/portal/generator/alfresco/templates/xformsIntegration/Xform/Xform.css.mt");
			// callback page (redirect to share page after editing)
			result.add("/com.bluexml.side.Portal.generator.alfresco/com/bluexml/side/portal/generator/alfresco/templates/xformsIntegration/edit-metadataCallBack.html.mt");
			// override edit-metadata page template
			result.add("/com.bluexml.side.Portal.generator.alfresco/com/bluexml/side/portal/generator/alfresco/templates/xformsIntegration/templates-edit-metadataXForm.ftl.mt");
			result.add("/com.bluexml.side.Portal.generator.alfresco/com/bluexml/side/portal/generator/alfresco/templates/xformsIntegration/edit-metadata.xml.mt");
			result.add("/com.bluexml.side.Portal.generator.alfresco/com/bluexml/side/portal/generator/alfresco/templates/xformsIntegration/template.edit-metadata-XForm.edit-metadata.xml.mt");
			result.add("/com.bluexml.side.Portal.generator.alfresco/com/bluexml/side/portal/generator/alfresco/templates/xformsIntegration/edit-metadata-mgr.get.html.ftl.mt");

			// searchForms
			result.add("/com.bluexml.side.Portal.generator.alfresco/com/bluexml/side/portal/generator/alfresco/templates/advancedSearchIntegration/template.XformSearch.advancedSearch.xml.mt");
			result.add("/com.bluexml.side.Portal.generator.alfresco/com/bluexml/side/portal/generator/alfresco/templates/advancedSearchIntegration/searchFormCallBack.html.mt");
			result.add("/com.bluexml.side.Portal.generator.alfresco/com/bluexml/side/portal/generator/alfresco/templates/advancedSearchIntegration/XformSearch/XformSearch.get.desc.xml.mt");
			result.add("/com.bluexml.side.Portal.generator.alfresco/com/bluexml/side/portal/generator/alfresco/templates/advancedSearchIntegration/XformSearch/XformSearch.get.head.ftl.mt");
			result.add("/com.bluexml.side.Portal.generator.alfresco/com/bluexml/side/portal/generator/alfresco/templates/advancedSearchIntegration/XformSearch/XformSearch.get.html.ftl.mt");
			result.add("/com.bluexml.side.Portal.generator.alfresco/com/bluexml/side/portal/generator/alfresco/templates/advancedSearchIntegration/XformSearch/XformSearch.get.js.mt");
			result.add("/com.bluexml.side.Portal.generator.alfresco/com/bluexml/side/portal/generator/alfresco/templates/advancedSearchIntegration/XformSearch/XformSearch.get.properties.mt");
			result.add("/com.bluexml.side.Portal.generator.alfresco/com/bluexml/side/portal/generator/alfresco/templates/advancedSearchIntegration/XformSearch/XformSearch.css.mt");
		}
		return result;
	}

	public static boolean getGeneratorOptionValue(EObject o, String key) {
		return getGeneratorOptionValue(key);
	}

	public String getXFORMURL(EObject o) {
		return getGenerationParameter(GENERATOR_PARAM_XFORMURL);
	}

	public String getSHAREURL(EObject o) {
		return getGenerationParameter(GENERATOR_PARAM_SHAREURL);
	}

	public String getFacetMapURL(EObject o) {
		return getGenerationParameter(GENERATOR_PARAM_FACETMAPURL);
	}

	@Override
	protected Map<String, String> getTemplatesSubstitution() {
		return new HashMap<String, String>();
	}
}
