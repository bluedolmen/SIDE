package com.bluexml.side.portal.generator.alfresco.enterprise;

import java.text.SimpleDateFormat;
import java.util.Collection;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Properties;

import org.eclipse.core.resources.IFile;
import org.eclipse.emf.ecore.EObject;

import com.bluexml.side.util.security.SecurityHelper;
import com.bluexml.side.util.security.preferences.SidePreferences;

public class PortalAlfrescoGenerator extends com.bluexml.side.portal.generator.alfresco.PortalAlfrescoGenerator {
	public static String GENERATOR_CODE = "CODE_GED_G_P_ALFRESCO_SHARE_30"; //$NON-NLS-1$

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

	public static boolean getGeneratorOptionValue(EObject o, String key) {
		return getGeneratorOptionValue(key);
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

	/*
	 * (non-Javadoc)
	 * @seecom.bluexml.side.portal.generator.alfresco.PortalAlfrescoGenerator#
	 * getMainTemplates()
	 */
	@Override
	protected List<String> getMainTemplates() {
		List<String> result = super.getMainTemplates();
		result.add("/com.bluexml.side.Portal.generator.alfresco.enterprise/com/bluexml/side/portal/generator/alfresco/enterprise/templates/shared/web-framework-config-custom.mt");
		return result;
	}

	@Override
	public boolean check() {
		return SecurityHelper.check(GENERATOR_CODE, SidePreferences.getKey());
	}

	@Override
	public Collection<IFile> generate(Map<String, List<IFile>> modelsInfo, String idMetamodel) throws Exception {
		if (!check()) {
			throw new Exception("Bad, please to enter your licence number in preferencies page");
		}
		return super.generate(modelsInfo, idMetamodel);
	}
}
