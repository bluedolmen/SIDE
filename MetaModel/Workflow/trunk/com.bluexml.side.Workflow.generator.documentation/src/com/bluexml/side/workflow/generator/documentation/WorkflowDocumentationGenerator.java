package com.bluexml.side.workflow.generator.documentation;

import java.util.List;

import com.bluexml.side.util.generator.documentation.DocumentationGenerator;

public class WorkflowDocumentationGenerator extends DocumentationGenerator {

	public WorkflowDocumentationGenerator() {
		MMUri = "http://www.kerblue.org/workflow/1.0"; //$NON-NLS-1$
	}

	@Override
	protected List<String> getMainTemplates() {
		List<String> templates = super.getMainTemplates();
		templates.add("//com.bluexml.side.Workflow.generator.documentation/templates/content.mt"); //$NON-NLS-1$
		return templates;
	}
}
