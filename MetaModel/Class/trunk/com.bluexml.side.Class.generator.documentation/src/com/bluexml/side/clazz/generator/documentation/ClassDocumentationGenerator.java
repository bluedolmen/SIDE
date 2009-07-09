package com.bluexml.side.clazz.generator.documentation;

import java.util.ArrayList;
import java.util.List;

import com.bluexml.side.util.generator.documentation.DocumentationGenerator;

public class ClassDocumentationGenerator extends DocumentationGenerator {

	public ClassDocumentationGenerator() {
		MMUri = "http://www.kerblue.org/class/1.0";
	}

	@Override
	protected List<String> getTemplates() {
		List<String> templates = new ArrayList<String>();
		templates.add("/com.bluexml.side.Class.generator.documentation/src/templates/model2docBook.mt");
		return templates;
	}
}
