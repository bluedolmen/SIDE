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

	public RenderableStaticText(XFormsGenerator generationManager, FormElement parent,
			StaticText formElement) {
		super(generationManager, parent, formElement);
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
		return new RenderedStaticText(formElement.getLabel());
	}

}
