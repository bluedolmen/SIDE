package com.bluexml.xforms.generator.forms.renderable.lists;

import java.util.Stack;

import org.jdom.Element;

import com.bluexml.xforms.generator.forms.Renderable;
import com.bluexml.xforms.generator.forms.Rendered;
import com.bluexml.xforms.generator.forms.XFormsGenerator;
import com.bluexml.xforms.generator.forms.rendered.RenderedInput;

public class RenderableItemDisplayLabel extends Renderable {

	private RenderableClassList renderableClassList;

	public RenderableItemDisplayLabel(RenderableClassList renderableClassList) {
		super();
		this.renderableClassList = renderableClassList;
	}

	@Override
	public Path getPath(String parentPath, Stack<Renderable> parents,
			Stack<Rendered> renderedParents) {
		return ROOT_RELATIVE;
	}

	@Override
	public Rendered render(String path, Stack<Renderable> parents, Stack<Rendered> renderedParents, boolean isInIMultRepeater) {

		RenderedInput renderedInput = new RenderedInput();

		Element output = XFormsGenerator.createElement("output", XFormsGenerator.NAMESPACE_XFORMS);
		renderableClassList.getBindLabel().addLinkedElement(output);
		renderedInput.setXformsElement(output);

		return renderedInput;
	}

}
