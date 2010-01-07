package com.bluexml.xforms.generator.forms.renderable.lists;

import java.util.Stack;

import com.bluexml.xforms.generator.forms.Renderable;
import com.bluexml.xforms.generator.forms.Rendered;
import com.bluexml.xforms.generator.forms.XFormsGenerator;
import com.bluexml.xforms.generator.forms.rendered.RenderedDiv;

public class RenderableItemDisplay extends Renderable {

	public RenderableItemDisplay(RenderableClassList renderableClassList) {
		super();
		add(new RenderableItemDisplayLabel(renderableClassList));
		add(new RenderableItemDisplayEdit(renderableClassList));
	}

	@Override
	public Path getPath(String parentPath, Stack<Renderable> parents,
			Stack<Rendered> renderedParents) {
		return ROOT_RELATIVE;
	}

	@Override
	public Rendered render(String path, Stack<Renderable> parents, Stack<Rendered> renderedParents, boolean isInIMultRepeater) {
		return new RenderedDiv(XFormsGenerator.getId("item"));
	}

}
