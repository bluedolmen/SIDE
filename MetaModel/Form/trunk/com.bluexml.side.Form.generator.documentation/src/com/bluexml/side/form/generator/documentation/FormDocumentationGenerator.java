package com.bluexml.side.form.generator.documentation;

import java.util.ArrayList;
import java.util.List;

import com.bluexml.side.util.generator.documentation.DocumentationGenerator;

public class FormDocumentationGenerator extends DocumentationGenerator {

	public FormDocumentationGenerator() {
		MMUri = "http://www.kerblue.org/form/1.0";
	}

	@Override
	protected List<String> getTemplates() {
		List<String> templates = new ArrayList<String>();
		templates.add("/com.bluexml.side.Form.generator.documentation/src/templates/model2docBook.mt");
		return templates;
	}
}
