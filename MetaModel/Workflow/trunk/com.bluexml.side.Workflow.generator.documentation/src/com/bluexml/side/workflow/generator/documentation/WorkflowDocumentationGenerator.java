package com.bluexml.side.workflow.generator.documentation;

import java.util.ArrayList;
import java.util.List;

import com.bluexml.side.util.generator.documentation.DocumentationGenerator;

public class WorkflowDocumentationGenerator extends DocumentationGenerator {

	public WorkflowDocumentationGenerator() {
		MMUri = "http://www.kerblue.org/workflow/1.0";
	}

	@Override
	protected List<String> getTemplates() {
		List<String> templates = new ArrayList<String>();
		templates.add("//com.bluexml.side.Workflow.generator.documentation/src/templates/model2docBook.mt");
		return templates;
	}
}
