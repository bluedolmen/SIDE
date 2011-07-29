package com.bluexml.side.view.generator.documentation;

import java.util.List;

import com.bluexml.side.util.generator.documentation.DocumentationGenerator;

public class ViewDocumentationGenerator extends DocumentationGenerator {

	public ViewDocumentationGenerator() {
		MMUri = "http://www.kerblue.org/view/1.0"; //$NON-NLS-1$
	}

	@Override
	protected List<String> getMainTemplates() {
		List<String> templates = super.getMainTemplates();
		templates.add("/com.bluexml.side.View.generator.documentation/templates/content.mt"); //$NON-NLS-1$
		return templates;
	}
}
