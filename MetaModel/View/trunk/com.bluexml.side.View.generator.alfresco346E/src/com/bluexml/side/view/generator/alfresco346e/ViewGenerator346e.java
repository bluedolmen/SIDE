package com.bluexml.side.view.generator.alfresco346e;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.eclipse.emf.ecore.EObject;

import com.bluexml.side.view.generator.alfresco34d.ViewGenerator;

public class ViewGenerator346e extends ViewGenerator {
	public final static String TEMPLATE_HOME_346E = "/com.bluexml.side.View.generator.alfresco346E/com/bluexml/side/view/generator/alfresco346e/templates";

	// acceleo services
	public String getModuleIdService(EObject ob, String modelId) throws Exception {
		return buildModuleProperties(modelId).getProperty("module.id"); //$NON-NLS-1$
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
		
		map.put(TEMPLATE_HOME_34d + "/webscripts/alfrescoGenerator_template_js_get.mt", TEMPLATE_HOME_346E + "/webscripts/alfrescoGenerator_template_js_get.mt");
		
		map.put(TEMPLATE_HOME_34d + "/doclist_ftl.mt", TEMPLATE_HOME_346E + "/doclist_ftl.mt");
		
		templatesSubstitution.add(map);
		return templatesSubstitution;
	}
}
