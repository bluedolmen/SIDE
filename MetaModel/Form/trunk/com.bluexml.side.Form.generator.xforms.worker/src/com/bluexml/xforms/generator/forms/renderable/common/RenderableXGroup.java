package com.bluexml.xforms.generator.forms.renderable.common;

import java.util.Stack;

import com.bluexml.xforms.generator.forms.Renderable;
import com.bluexml.xforms.generator.forms.Rendered;
import com.bluexml.xforms.generator.forms.XFormsGenerator;
import com.bluexml.xforms.generator.forms.rendered.RenderedGroup;

/**
 * The Class RenderableXGroup.
 */
public class RenderableXGroup extends Renderable {

	/** The title. */
	private String title;

	/** The CSS class. */
	private String style;

	/**
	 * Instantiates a new renderable x group.
	 * 
	 * @param renderable
	 *            the renderable
	 * @param title
	 *            the title
	 * @param style
	 *            TODO
	 */
	public RenderableXGroup(Renderable renderable, String title, String style) {
		super();
		add(renderable);
		this.title = title;
		this.style = style;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.bluexml.xforms.generator.forms.Renderable#getPath(java.lang.String, java.util.Stack,
	 * java.util.Stack)
	 */
	@Override
	public Path getPath(String parentPath, Stack<Renderable> parents,
			Stack<Rendered> renderedParents) {
		return ROOT_RELATIVE;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.bluexml.xforms.generator.forms.Renderable#render(java.lang.String, java.util.Stack,
	 * java.util.Stack)
	 */
	@Override
	public Rendered render(String path, Stack<Renderable> parents, Stack<Rendered> renderedParents,
			boolean isInIMultRepeater) {
		RenderedGroup renderedGroup = new RenderedGroup(title, XFormsGenerator.getId("xgroup"),
				style);

		return renderedGroup;
	}

}
