package com.bluexml.xforms.generator.forms.renderable.forms.field;

import java.util.Stack;

import org.jdom.Element;

import com.bluexml.side.form.FormElement;
import com.bluexml.side.form.TextField;
import com.bluexml.side.form.TextWidgetType;
import com.bluexml.xforms.generator.forms.Renderable;
import com.bluexml.xforms.generator.forms.Rendered;
import com.bluexml.xforms.generator.forms.XFormsGenerator;
import com.bluexml.xforms.generator.forms.modelelement.ModelElementBindSimple;
import com.bluexml.xforms.generator.forms.renderable.forms.RenderableField;

public class RenderableTextInput extends RenderableField<TextField> {

	public RenderableTextInput(XFormsGenerator generationManager, FormElement parent,
			TextField formElement) {
		super(generationManager, parent, formElement);
	}

	@Override
	protected Element getCustomElement(Rendered rendered, ModelElementBindSimple meb,
			String slabel, Stack<Renderable> parents, Stack<Rendered> renderedParents) {
		boolean isRichTextEditor = false;
		if (formElement.getWidget() == TextWidgetType.RICH_TEXT_EDITOR) {
			isRichTextEditor = true;
		}
		return getTextAreaElement(meb, slabel, isRichTextEditor);
	}

	@Override
	protected String getXsdType() {
		return "string";
	}

}
