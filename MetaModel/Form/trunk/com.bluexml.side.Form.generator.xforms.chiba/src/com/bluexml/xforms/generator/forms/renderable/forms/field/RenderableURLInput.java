package com.bluexml.xforms.generator.forms.renderable.forms.field;


import com.bluexml.side.form.FormElement;
import com.bluexml.side.form.URLField;
import com.bluexml.xforms.generator.forms.XFormsGenerator;

public class RenderableURLInput extends RenderableRegexInput<URLField> {

	public RenderableURLInput(XFormsGenerator generationManager, FormElement parent,
			URLField formElement) {
		super(generationManager, parent, formElement, URL_REGEXP);
	}

}
