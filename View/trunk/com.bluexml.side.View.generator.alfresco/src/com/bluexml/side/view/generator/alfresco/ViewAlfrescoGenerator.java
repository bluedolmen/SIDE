package com.bluexml.side.view.generator.alfresco;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Properties;

import com.bluexml.side.application.generator.alfresco.AbstractAlfrescoGenerator;
public class ViewAlfrescoGenerator extends AbstractAlfrescoGenerator{

	
	public static String GENERATOR_OPTIONS_DOCLIST = "alfresco.view.doclist";
	@Override
	protected String getMetamodelURI() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	protected List<String> getTemplates() {
		List<String> result = new ArrayList<String>();
		if (getGeneratorOptionValue(GENERATOR_OPTIONS_DOCLIST)) {
			result.add("/com.bluexml.side.View.generator.alfresco/com/bluexml/side/view/generator/alfresco/templates/doclistView/doclist_ftl.mt");
		}
		return result;
	}

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
		props.put("module.title", "S-IDE view extension");
		props.put("module.description", "this module contains S-IDE generated extension to extends Alfresco view,\n build at " + sdf.format(now));
		return null;
	}

}
