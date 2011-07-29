package com.bluexml.side.clazz.generator.documentation;

import java.util.List;

import com.bluexml.side.util.generator.documentation.DocumentationGenerator;

public class ClassDocumentationGenerator extends DocumentationGenerator {

	public ClassDocumentationGenerator() {
		MMUri = "http://www.kerblue.org/class/1.0"; //$NON-NLS-1$
	}

	@Override
	protected List<String> getMainTemplates() {
		List<String> templates = super.getMainTemplates();
		templates.add("/com.bluexml.side.Class.generator.documentation/templates/content.mt"); //$NON-NLS-1$
		return templates;
	}

}
