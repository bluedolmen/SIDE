/**
 * 
 */
package com.bluexml.xforms.generator.forms.renderable.forms;

import java.util.Stack;

import com.bluexml.side.form.FormElement;
import com.bluexml.side.form.StaticText;
import com.bluexml.xforms.generator.forms.Renderable;
import com.bluexml.xforms.generator.forms.Rendered;
import com.bluexml.xforms.generator.forms.XFormsGenerator;
import com.bluexml.xforms.generator.forms.rendered.RenderedStaticText;

/**
 * @author Amenel
 * 
 */
public class RenderableStaticText extends RenderableFormElement<StaticText> {

	private String textToDisplay;

	/**
	 * 
	 * @param generationManager
	 * @param parent
	 * @param formElement
	 *            the element from the form model. May be <em>null</em> but if so, the text
	 *            parameter should be given
	 * @param text
	 *            the text to render in the XHTML template. Ignored if the form element is not null.
	 */
	public RenderableStaticText(XFormsGenerator generationManager, FormElement parent,
			StaticText formElement, String text) {
		super(generationManager, parent, formElement);
		this.textToDisplay = text;
	}

	@Override
	public void compute() {
		// nothing to do
	}

	@Override
	public Path getPath(String parentPath, Stack<Renderable> parents,
			Stack<Rendered> renderedParents) {
		return ROOT_RELATIVE;
	}

	@Override
	public Rendered render(String path, Stack<Renderable> parents, Stack<Rendered> renderedParents,
			boolean isInIMultRepeater) {
		String text = (formElement != null) ? formElement.getLabel() : textToDisplay;
		RenderedStaticText rendered = new RenderedStaticText(text);
		if (formElement != null) {
			applyStyle(rendered, formElement.getStyle());
		}
		return rendered;
	}

}
