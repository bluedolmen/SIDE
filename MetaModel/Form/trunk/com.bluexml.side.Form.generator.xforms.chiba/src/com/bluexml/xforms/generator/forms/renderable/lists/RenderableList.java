package com.bluexml.xforms.generator.forms.renderable.lists;

import java.util.Stack;

import com.bluexml.xforms.generator.forms.Renderable;
import com.bluexml.xforms.generator.forms.Rendered;
import com.bluexml.xforms.generator.forms.rendered.RenderedRepeater;

public class RenderableList extends Renderable {

	private RenderableClassList renderableClassList;

	public RenderableList(RenderableClassList renderableClassList) {
		super();
		this.renderableClassList = renderableClassList;
		add(new RenderableItemDisplay(renderableClassList));
	}

	@Override
	public Path getPath(String parentPath, Stack<Renderable> parents,
			Stack<Rendered> renderedParents) {
		return ROOT_RELATIVE;
	}

	@Override
	public Rendered render(String path, Stack<Renderable> parents, Stack<Rendered> renderedParents, boolean isInIMultRepeater) {
		RenderedRepeater renderedRepeater = new RenderedRepeater(renderableClassList
				.getModelElementBindHolder(), "itemRepeater");
		return renderedRepeater;
	}

}
