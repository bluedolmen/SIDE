package com.bluexml.xforms.generator.forms.renderable.forms;

import java.util.Stack;


import com.bluexml.side.form.FormElement;
import com.bluexml.side.form.Reference;
import com.bluexml.xforms.generator.FormGenerator;
import com.bluexml.xforms.generator.forms.Renderable;
import com.bluexml.xforms.generator.forms.Rendered;
import com.bluexml.xforms.generator.forms.XFormsGenerator;
import com.bluexml.xforms.generator.forms.renderable.forms.group.RenderableFormContainer;
import com.bluexml.xforms.generator.forms.rendered.RenderedDiv;

public class RenderableReferenceWithAC extends RenderableFormElement<Reference> {

	private int index;

	public RenderableReferenceWithAC(XFormsGenerator generationManager, FormElement parent,
			Reference formElement, RenderableFormContainer renderableTarget,
			RenderableFormContainer renderableAC, int index) {
		super(generationManager, parent, formElement);
		add(renderableTarget);
		add(renderableAC);
		this.index = index;
	}

	@Override
	public void compute() {
		// nothing
	}

	@Override
	public Path getPath(String parentPath, Stack<Renderable> parents,
			Stack<Rendered> renderedParents) {
		return new Path(PathType.relativePath, "item" + index + "/");
	}

	@Override
	public Rendered render(String path, Stack<Renderable> parents, Stack<Rendered> renderedParents, boolean isInIMultRepeater) {
		return new RenderedDiv(XFormsGenerator.getId(FormGenerator.getUniqueName(formElement)
				+ "Item" + index));
	}

}
