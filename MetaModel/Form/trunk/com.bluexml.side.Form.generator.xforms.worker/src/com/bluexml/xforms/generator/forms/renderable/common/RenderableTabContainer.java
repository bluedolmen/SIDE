package com.bluexml.xforms.generator.forms.renderable.common;

import java.util.Stack;

import com.bluexml.xforms.generator.forms.Renderable;
import com.bluexml.xforms.generator.forms.Rendered;
import com.bluexml.xforms.generator.forms.rendered.RenderedTabContainer;

/**
 * The Class RenderableTabContainer.
 */
public class RenderableTabContainer extends Renderable {

	/** The div id. */
	private String divId;

	/** The label. */
	private String label;

	/** The show tabs. */
	private boolean showTabs;

	/**
	 * Instantiates a new renderable tab container.
	 * 
	 * @param divId
	 *            the div id
	 * @param label
	 *            the label
	 * @param showTabs
	 *            the show tabs
	 */
	public RenderableTabContainer(String divId, String label, boolean showTabs) {
		super();
		this.divId = divId;
		this.label = label;
		this.showTabs = showTabs;
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
	public Rendered render(String path, Stack<Renderable> parents, Stack<Rendered> renderedParents, boolean isInIMultRepeater) {
		return new RenderedTabContainer(divId, label, showTabs);
	}

}
