package com.bluexml.side.workflow.generator.alfresco34d;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.bluexml.side.workflow.generator.alfresco.WorkflowGenerator;

public class WorkflowGeneratorCommunity extends WorkflowGenerator {

	/*
	 * (non-Javadoc)
	 * @seecom.bluexml.side.workflow.generator.alfresco.WorkflowGenerator#
	 * getTemplatesSubstitution()
	 */
	@Override
	protected List<Map<String, String>> getTemplatesSubstitution() {
		List<Map<String, String>> templatesSubstitution = super.getTemplatesSubstitution();
		Map<String, String> map = new HashMap<String, String>();
		map.put("/com.bluexml.side.Workflow.generator.alfresco/templates/alfrescoGenerator_jpdl.mt", "/com.bluexml.side.Workflow.generator.alfresco34d/com/bluexml/side/workflow/generator/alfresco34d/templates/alfrescoGenerator_jpdl.mt");
		templatesSubstitution.add(map);
		return templatesSubstitution;
	}

}
