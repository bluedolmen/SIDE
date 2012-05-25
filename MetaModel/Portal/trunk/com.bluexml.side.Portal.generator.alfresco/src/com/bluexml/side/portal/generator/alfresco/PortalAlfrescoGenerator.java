package com.bluexml.side.portal.generator.alfresco;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
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

	protected static String TEMPLATE_PATH_32R2 = "/com.bluexml.side.Portal.generator.alfresco/com/bluexml/side/portal/generator/alfresco/templates/";
	protected static String TEMPLATE_PATH_32R2_portalShare = TEMPLATE_PATH_32R2 + "portalShare/";

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
		result.add(TEMPLATE_PATH_32R2_portalShare + "presets.mt");
		result.add(TEMPLATE_PATH_32R2_portalShare + "page.mt");
		result.add(TEMPLATE_PATH_32R2_portalShare + "css.mt");
		result.add(TEMPLATE_PATH_32R2_portalShare + "template-instances.mt");
		result.add(TEMPLATE_PATH_32R2_portalShare + "title.mt");
		result.add(TEMPLATE_PATH_32R2_portalShare + "navigation.mt");
		result.add(TEMPLATE_PATH_32R2_portalShare + "shareComponents.mt");
		result.add(TEMPLATE_PATH_32R2_portalShare + "template.mt");
		result.add(TEMPLATE_PATH_32R2_portalShare + "template_js.mt");
		result.add(TEMPLATE_PATH_32R2_portalShare + "messages-context.mt");
		result.add(TEMPLATE_PATH_32R2_portalShare + "messages.mt");
		result.add(TEMPLATE_PATH_32R2_portalShare + "create-site.get.properties.mt");

		result.add(TEMPLATE_PATH_32R2 + "shared/web-framework-config-custom.mt");
		return result;
	}

	@Override
	protected List<String> getOptionalTemplates() {
		List<String> result = new ArrayList<String>();
		if (getGeneratorOptionValue(GENERATOR_OPTIONS_DOCLIST)) {
			result.add(TEMPLATE_PATH_32R2 + "documentLibrary/DocumentLibraryPortletView.ftl.mt");
		}
		// if (getGeneratorOptionValue(GENERATOR_OPTIONS_FORMS)) {
		// see web-framework-config-custom.mt
		// }

		if (getGeneratorOptionValue(GENERATOR_OPTIONS_FACETMAP)) {
			// result.add(TEMPLATE_PATH_32R2_portalShare +"web-framework-config-custom.mt");
			result.add(TEMPLATE_PATH_32R2 + "facetMapIntegration/template.facetMap.xml.mt");
			result.add(TEMPLATE_PATH_32R2 + "facetMapIntegration/facetMap/facetMap.get.desc.xml.mt");
			result.add(TEMPLATE_PATH_32R2 + "facetMapIntegration/facetMap/facetMap.get.head.ftl.mt");
			result.add(TEMPLATE_PATH_32R2 + "facetMapIntegration/facetMap/facetMap.get.html.ftl.mt");
			result.add(TEMPLATE_PATH_32R2 + "facetMapIntegration/facetMap/facetMap.get.js.mt");
			result.add(TEMPLATE_PATH_32R2 + "facetMapIntegration/facetMap/facetMap.css.mt");
		}
		if (getGeneratorOptionValue(GENERATOR_OPTIONS_XFORMS)) {
			// XForm portlet
			result.add(TEMPLATE_PATH_32R2 + "xformsIntegration/Xform/Xform.get.desc.xml.mt");
			result.add(TEMPLATE_PATH_32R2 + "xformsIntegration/Xform/Xform.get.head.ftl.mt");
			result.add(TEMPLATE_PATH_32R2 + "xformsIntegration/Xform/Xform.get.html.ftl.mt");
			result.add(TEMPLATE_PATH_32R2 + "xformsIntegration/Xform/Xform.get.js.mt");
			result.add(TEMPLATE_PATH_32R2 + "xformsIntegration/Xform/Xform.get.properties.mt");
			result.add(TEMPLATE_PATH_32R2 + "xformsIntegration/Xform/Xform.css.mt");
			// callback page (redirect to share page after editing)
			result.add(TEMPLATE_PATH_32R2 + "xformsIntegration/edit-metadataCallBack.html.mt");
			// override edit-metadata page template
			result.add(TEMPLATE_PATH_32R2 + "xformsIntegration/templates-edit-metadataXForm.ftl.mt");
			result.add(TEMPLATE_PATH_32R2 + "xformsIntegration/edit-metadata.xml.mt");
			result.add(TEMPLATE_PATH_32R2 + "xformsIntegration/template.edit-metadata-XForm.edit-metadata.xml.mt");
			result.add(TEMPLATE_PATH_32R2 + "xformsIntegration/edit-metadata-mgr.get.html.ftl.mt");

			// searchForms
			result.add(TEMPLATE_PATH_32R2 + "advancedSearchIntegration/template.XformSearch.advancedSearch.xml.mt");
			result.add(TEMPLATE_PATH_32R2 + "advancedSearchIntegration/searchFormCallBack.html.mt");
			result.add(TEMPLATE_PATH_32R2 + "advancedSearchIntegration/XformSearch/XformSearch.get.desc.xml.mt");
			result.add(TEMPLATE_PATH_32R2 + "advancedSearchIntegration/XformSearch/XformSearch.get.head.ftl.mt");
			result.add(TEMPLATE_PATH_32R2 + "advancedSearchIntegration/XformSearch/XformSearch.get.html.ftl.mt");
			result.add(TEMPLATE_PATH_32R2 + "advancedSearchIntegration/XformSearch/XformSearch.get.js.mt");
			result.add(TEMPLATE_PATH_32R2 + "advancedSearchIntegration/XformSearch/XformSearch.get.properties.mt");
			result.add(TEMPLATE_PATH_32R2 + "advancedSearchIntegration/XformSearch/XformSearch.css.mt");
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

}
