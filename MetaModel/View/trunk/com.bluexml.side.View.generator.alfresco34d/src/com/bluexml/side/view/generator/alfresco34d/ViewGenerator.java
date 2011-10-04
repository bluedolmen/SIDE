package com.bluexml.side.view.generator.alfresco34d;

import java.util.Map;

public class ViewGenerator extends com.bluexml.side.view.generator.alfresco.extension.sideenterprise.ViewGenerator {

	/*
	 * (non-Javadoc)
	 * @seecom.bluexml.side.view.generator.alfresco.ViewAlfrescoGenerator#
	 * getTemplatesSubstitution()
	 */
	@Override
	protected Map<String, String> getTemplatesSubstitution() {
		Map<String, String> templatesSubstitution = super.getTemplatesSubstitution();
		templatesSubstitution.put("/com.bluexml.side.View.generator.alfresco/com/bluexml/side/view/generator/alfresco/templates/doclistView/doclist_ftl.mt", "/com.bluexml.side.View.generator.alfresco34d/com/bluexml/side/view/generator/alfresco34d/templates/doclist_ftl.mt");
		return templatesSubstitution;
	}

}
