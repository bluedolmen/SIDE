package com.bluexml.side.form.generator.documentation;

import java.util.List;

import com.bluexml.side.util.generator.documentation.DocumentationGenerator;

public class FormDocumentationGenerator extends DocumentationGenerator {

	public FormDocumentationGenerator() {
		MMUri = "http://www.kerblue.org/form/1.0"; //$NON-NLS-1$
	}

	@Override
	protected List<String> getTemplates() {
		templates.add("/com.bluexml.side.Form.generator.documentation/src/templates/content.mt"); //$NON-NLS-1$
		return templates;
	}
}
