package com.bluexml.side.portal.generator.alfresco;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Properties;

import com.bluexml.side.util.generator.alfresco.AbstractAlfrescoGenerator;

public class PortalAlfrescoGenerator extends AbstractAlfrescoGenerator {

	private static String MMUri = "http://www.kerblue.org/portal/1.0";
	static String GENERATOR_OPTIONS_DOCLIST = "com.bluexml.side.Portal.generator.alfresco.doclist";
	static String GENERATOR_OPTIONS_FORMS = "com.bluexml.side.Portal.generator.alfresco.forms";

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
		if (getGeneratorOptionValue(GENERATOR_OPTIONS_FORMS)) {
			result.add("/com.bluexml.side.Portal.generator.alfresco/com/bluexml/side/portal/generator/alfresco/templates/formsDetails/web-framework-config-custom.mt");
		}
		return result;
	}

}
