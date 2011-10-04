package com.bluexml.side.portal.generator.liferay;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Properties;

import org.eclipse.core.resources.IFile;
import org.eclipse.emf.ecore.EObject;

import com.bluexml.side.util.generator.acceleo.AbstractAcceleoPackageGenerator;
import com.bluexml.side.util.libs.IFileHelper;
import com.bluexml.side.util.security.SecurityHelper;
import com.bluexml.side.util.security.preferences.SidePreferences;

public class LiferayGenerator extends AbstractAcceleoPackageGenerator {

	private final static String GENERATOR_CODE = "CODE_GED_G_P_LIFERAY_511";
	protected static String GENERATOR_PARAM_FACETMAP_URL = "facetMap.url";
	protected static String GENERATOR_PARAM_ALFRESCO_URL = "alfresco.url";
	protected static String GENERATOR_PARAM_XFORM_URL = "com.bluexml.side.Form.generator.xforms.chiba.webappContext";
	protected static String GENERATOR_PARAM_THEME = "liferay.theme";
	protected static String GENERATOR_PARAM_COLOR = "liferay.colorSchemeId";

	@Override
	public Collection<IFile> buildPackages(String modelId) throws Exception {
		LiferayPackager lpackager = new LiferayPackager(IFileHelper.getIFolder(getTemporaryFolder()), buildModuleProperties(modelId), techVersion);
		return lpackager.buildPackages().values();
	}

	@Override
	protected String getMetamodelURI() {
		return "http://www.kerblue.org/portal/1.0";
	}

	@Override
	protected List<String> getMainTemplates() {
		List<String> result = new ArrayList<String>();

		// lar generation
		result.add("/com.bluexml.side.Portal.generator.liferay.enterprise/com/bluexml/side/portal/generator/liferay/template/LiferayTemplates/importFiles/manifest.xml.mt");
		result.add("/com.bluexml.side.Portal.generator.liferay.enterprise/com/bluexml/side/portal/generator/liferay/template/LiferayTemplates/importFiles/groups/comments.xml.mt");
		result.add("/com.bluexml.side.Portal.generator.liferay.enterprise/com/bluexml/side/portal/generator/liferay/template/LiferayTemplates/importFiles/groups/layout.xml.mt");
		result.add("/com.bluexml.side.Portal.generator.liferay.enterprise/com/bluexml/side/portal/generator/liferay/template/LiferayTemplates/importFiles/groups/portlets_preference.xml.mt");
		result.add("/com.bluexml.side.Portal.generator.liferay.enterprise/com/bluexml/side/portal/generator/liferay/template/LiferayTemplates/importFiles/groups/portlets.xml.mt");
		result.add("/com.bluexml.side.Portal.generator.liferay.enterprise/com/bluexml/side/portal/generator/liferay/template/LiferayTemplates/importFiles/groups/ratings.xml.mt");
		result.add("/com.bluexml.side.Portal.generator.liferay.enterprise/com/bluexml/side/portal/generator/liferay/template/LiferayTemplates/importFiles/groups/tags.xml.mt");

		// theme war generation
		result.add("/com.bluexml.side.Portal.generator.liferay.enterprise/com/bluexml/side/portal/generator/liferay/template/LiferayTemplates/layouttpl/docroot/layout.tpl.mt");
		result.add("/com.bluexml.side.Portal.generator.liferay.enterprise/com/bluexml/side/portal/generator/liferay/template/LiferayTemplates/layouttpl/docroot/layout.wap.tpl.mt");

		result.add("/com.bluexml.side.Portal.generator.liferay.enterprise/com/bluexml/side/portal/generator/liferay/template/LiferayTemplates/layouttpl/docroot/WEB-INF/liferay-layout-templates.xml.mt");
		result.add("/com.bluexml.side.Portal.generator.liferay.enterprise/com/bluexml/side/portal/generator/liferay/template/LiferayTemplates/layouttpl/docroot/WEB-INF/liferay-plugin-package.xml.mt");

		return result;
	}

	public static String getXFORMURL(EObject o) {
		String result = getGenerationParameter(GENERATOR_PARAM_XFORM_URL);
		return result;
	}

	public static String getFacetMapURL(EObject o) {
		String result = getGenerationParameter(GENERATOR_PARAM_FACETMAP_URL);
		if (result == null || result.equals("")) {
			System.out.println("xform url, default value used");
			result = "http://localhost:8080/facetmap";
		}
		return result;
	}

	public static String getAlfrescoURL(EObject o) {
		return getGenerationParameter(GENERATOR_PARAM_ALFRESCO_URL);
	}

	public static String getTheme(EObject o) {
		String result = getGenerationParameter(GENERATOR_PARAM_THEME);
		if (result == null || result.equals("")) {
			System.out.println("liferay theme, default value used");
			result = "";
		}
		return result;
	}

	public static String getColorSchemeId(EObject o) {
		String result = getGenerationParameter(GENERATOR_PARAM_COLOR);
		if (result == null || result.equals("")) {
			System.out.println("liferay colorSchemeId , default value used");
			result = "";
		}
		return result;
	}

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
		return SecurityHelper.check(GENERATOR_CODE, SidePreferences.getKey());
	}

	@Override
	public Collection<IFile> generate(Map<String, List<IFile>> modelsInfo, String idMetamodel) throws Exception {
		if (!check()) {
			throw new Exception("Bad, please to enter your licence number in preferencies page");
		}
		return super.generate(modelsInfo, idMetamodel);
	}

	@Override
	protected List<String> getOptionalTemplates() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	protected Map<String, String> getTemplatesSubstitution() {
		// TODO Auto-generated method stub
		return null;
	}
}
