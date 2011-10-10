package com.bluexml.side.view.generator.alfresco34d;

import java.util.Map;

import com.bluexml.side.util.security.SecurityHelper;
import com.bluexml.side.util.security.preferences.SidePreferences;

public class ViewGenerator extends com.bluexml.side.view.generator.alfresco.extension.sideenterprise.ViewGenerator {

	@Override
	public String getComponentKey() {
		return "CODE_GED_G_V_ALFRESCO_34D";
	}
	
	@Override
	public boolean check() {
		return SecurityHelper.check(getComponentKey(), SidePreferences.getKey());
	}
	
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
