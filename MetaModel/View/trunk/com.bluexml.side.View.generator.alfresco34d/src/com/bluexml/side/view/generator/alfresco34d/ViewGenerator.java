package com.bluexml.side.view.generator.alfresco34d;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.eclipse.emf.ecore.EObject;

public class ViewGenerator extends com.bluexml.side.view.generator.alfresco.ViewAlfrescoGenerator {

	public final static String OPTION_datagrid = "view.generator.share.datagrid";

	@Override
	protected List<String> getOptionalTemplates() {
		List<String> optionalTemplates = super.getOptionalTemplates();
		if (getGeneratorOptionValue(OPTION_datagrid)) {
			optionalTemplates.add("/com.bluexml.side.View.generator.alfresco34d/com/bluexml/side/view/generator/alfresco34d/templates/datagrids/context.xml.mt");
			optionalTemplates.add("/com.bluexml.side.View.generator.alfresco34d/com/bluexml/side/view/generator/alfresco34d/templates/datagrids/formGenerator.mt");
		}
		return optionalTemplates;
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
		map.put("/com.bluexml.side.View.generator.alfresco/com/bluexml/side/view/generator/alfresco/templates/doclistView/doclist_ftl.mt", "/com.bluexml.side.View.generator.alfresco34d/com/bluexml/side/view/generator/alfresco34d/templates/doclist_ftl.mt");
		templatesSubstitution.add(map);
		return templatesSubstitution;
	}

	// acceleo services
	public String getModuleIdService(EObject ob, String modelId) throws Exception {
		return buildModuleProperties(modelId).getProperty("module.id"); //$NON-NLS-1$
	}
}
