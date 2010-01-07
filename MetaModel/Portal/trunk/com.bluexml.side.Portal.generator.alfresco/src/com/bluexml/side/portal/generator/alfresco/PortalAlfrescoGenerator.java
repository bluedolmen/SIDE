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

	private static String MMUri = "http://www.kerblue.org/portal/1.0";
	static String GENERATOR_OPTIONS_DOCLIST = "com.bluexml.side.Portal.generator.alfresco.doclist";
	static String GENERATOR_OPTIONS_FORMS = "com.bluexml.side.Portal.generator.alfresco.forms";
	static String GENERATOR_OPTIONS_FACETMAP = "com.bluexml.side.Portal.generator.alfresco.facetmap";

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
//		if (getGeneratorOptionValue(GENERATOR_OPTIONS_FORMS)) {
//			result.add("/com.bluexml.side.Portal.generator.alfresco/com/bluexml/side/portal/generator/alfresco/templates/formsDetails/web-framework-config-custom.mt");
//		}
		if (getGeneratorOptionValue(GENERATOR_OPTIONS_FACETMAP)) {
			//result.add("/com.bluexml.side.Portal.generator.alfresco/com/bluexml/side/portal/generator/alfresco/templates/portalShare/web-framework-config-custom.mt");
			result.add("/com.bluexml.side.Portal.generator.alfresco/com/bluexml/side/portal/generator/alfresco/templates/portalShare/presets.mt");
			result.add("/com.bluexml.side.Portal.generator.alfresco/com/bluexml/side/portal/generator/alfresco/templates/portalShare/page.mt");
			result.add("/com.bluexml.side.Portal.generator.alfresco/com/bluexml/side/portal/generator/alfresco/templates/portalShare/css.mt");
			result.add("/com.bluexml.side.Portal.generator.alfresco/com/bluexml/side/portal/generator/alfresco/templates/portalShare/template-instances.mt");
			result.add("/com.bluexml.side.Portal.generator.alfresco/com/bluexml/side/portal/generator/alfresco/templates/portalShare/title.mt");
			result.add("/com.bluexml.side.Portal.generator.alfresco/com/bluexml/side/portal/generator/alfresco/templates/portalShare/navigation.mt");
			result.add("/com.bluexml.side.Portal.generator.alfresco/com/bluexml/side/portal/generator/alfresco/templates/portalShare/template.mt");
			result.add("/com.bluexml.side.Portal.generator.alfresco/com/bluexml/side/portal/generator/alfresco/templates/portalShare/template_js.mt");
			result.add("/com.bluexml.side.Portal.generator.alfresco/com/bluexml/side/portal/generator/alfresco/templates/portalShare/messages.mt");
		}
		// shared resources
		// includes of GENERATOR_OPTIONS_FORMS and GENERATOR_OPTIONS_FACETMAP options 
		result.add("/com.bluexml.side.Portal.generator.alfresco/com/bluexml/side/portal/generator/alfresco/templates/shared/web-framework-config-custom.mt");
		return result;
	}

	public static boolean getGeneratorOptionValue(EObject o,String key) {
		System.out.println("options :"+AbstractGenerator.generatorOptions.values());
		
		return getGeneratorOptionValue(key);
	}
}
