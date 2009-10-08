package com.bluexml.xforms.generator.forms.renderable.forms.field;


import com.bluexml.side.form.EmailField;
import com.bluexml.side.form.FormElement;
import com.bluexml.xforms.generator.forms.XFormsGenerator;

public class RenderableMailInput extends RenderableRegexInput<EmailField> {

	public RenderableMailInput(XFormsGenerator generationManager, FormElement parent,
			EmailField formElement) {
		super(generationManager, parent, formElement, MAIL_REGEXP);
	}

}
