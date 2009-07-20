package com.bluexml.side.view.generator.documentation;

import java.util.ArrayList;
import java.util.List;

import com.bluexml.side.util.generator.documentation.DocumentationGenerator;

public class ViewDocumentationGenerator extends DocumentationGenerator {

	public ViewDocumentationGenerator() {
		MMUri = "http://www.kerblue.org/view/1.0";
	}

	@Override
	protected List<String> getTemplates() {
		List<String> templates = new ArrayList<String>();
		templates.add("/com.bluexml.side.View.generator.documentation/src/templates/model2docBook.mt");
		return templates;
	}
}
