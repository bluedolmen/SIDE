package com.bluexml.side.workflow.generator.alfresco34d;

import java.util.Map;

import com.bluexml.side.util.security.SecurityHelper;
import com.bluexml.side.util.security.preferences.SidePreferences;
import com.bluexml.side.workflow.generator.alfresco.WorkflowGenerator;

public class WorkflowGeneratorCommunity extends WorkflowGenerator {

	@Override
	public String getComponentKey() {
		return "CODE_GED_G_W_ALFRESCO_34D";
	}
	
	@Override
	public boolean check() {
		return SecurityHelper.check(getComponentKey(), SidePreferences.getKey());
	}

	
	/*
	 * (non-Javadoc)
	 * @seecom.bluexml.side.workflow.generator.alfresco.WorkflowGenerator#
	 * getTemplatesSubstitution()
	 */
	@Override
	protected Map<String, String> getTemplatesSubstitution() {
		Map<String, String> templatesSubstitution = super.getTemplatesSubstitution();
		templatesSubstitution.put("/com.bluexml.side.Workflow.generator.alfresco/templates/alfrescoGenerator_jpdl.mt", "/com.bluexml.side.Workflow.generator.alfresco34d/com/bluexml/side/workflow/generator/alfresco34d/templates/alfrescoGenerator_jpdl.mt");
		return templatesSubstitution;
	}

}
