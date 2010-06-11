package com.bluexml.xforms.generator.forms.renderable.common;

import com.bluexml.xforms.generator.forms.Renderable;

/**
 * The Class RenderableTabWrapper.
 */
public class RenderableTabWrapper extends RenderableTab {

	/**
	 * Instantiates a new renderable tab wrapper.
	 * 
	 * @param renderable
	 *            the renderable
	 * @param title
	 *            the title
	 */
	public RenderableTabWrapper(Renderable renderable, String title) {
		super(title);
		add(renderable);
	}

}
