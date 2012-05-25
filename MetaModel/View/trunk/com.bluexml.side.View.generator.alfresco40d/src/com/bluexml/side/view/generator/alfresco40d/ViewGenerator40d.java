package com.bluexml.side.view.generator.alfresco40d;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.bluexml.side.view.generator.alfresco34d.ViewGenerator;

public class ViewGenerator40d extends ViewGenerator {
	public final static String TEMPLATE_HOME_40d = "/com.bluexml.side.View.generator.alfresco40d/com/bluexml/side/view/generator/alfresco40d/templates";

	@Override
	protected List<String> getMainTemplates() {
		// TODO Auto-generated method stub
		List<String> mainTemplates = super.getMainTemplates();
		mainTemplates.add(TEMPLATE_HOME_40d + "/doclib2/share-doclib2.mt");

		return mainTemplates;
	}

	/*
	 * (non-Javadoc)
	 * @seecom.bluexml.side.view.generator.alfresco.ViewAlfrescoGenerator#
	 * getTemplatesSubstitution()
	 */
	@Override
	protected List<Map<String, String>> getTemplatesSubstitution() {
		List<Map<String, String>> templatesSubstitution = super.getTemplatesSubstitution();
		Map<String, String> map = new HashMap<String, String>();

		map.put(TEMPLATE_HOME_34d + "/webscripts/alfrescoGenerator_template_js_get.mt", TEMPLATE_HOME_40d + "/webscripts/alfrescoGenerator_template_js_get.mt");

		templatesSubstitution.add(map);
		return templatesSubstitution;
	}
}
