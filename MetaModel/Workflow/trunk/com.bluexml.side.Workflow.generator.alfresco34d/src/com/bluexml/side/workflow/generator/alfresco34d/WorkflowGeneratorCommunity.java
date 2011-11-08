package com.bluexml.side.workflow.generator.alfresco34d;

import java.util.Map;

import com.bluexml.side.workflow.generator.alfresco.WorkflowGenerator;

public class WorkflowGeneratorCommunity extends WorkflowGenerator {

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
