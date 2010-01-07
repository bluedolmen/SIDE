package com.bluexml.xforms.generator.forms.renderable.classes;

import java.util.List;
import java.util.Stack;


import com.bluexml.side.clazz.Clazz;
import com.bluexml.xforms.generator.forms.Renderable;
import com.bluexml.xforms.generator.forms.Rendered;
import com.bluexml.xforms.generator.forms.rendered.RenderedSelectClass;

/**
 * The Class RenderableClassSelector.
 */
public class RenderableClassSelector extends Renderable {

	/** The sub classes. */
	private List<Clazz> subClasses;

	/**
	 * Instantiates a new renderable class selector.
	 * 
	 * @param subClasses
	 *            the sub classes
	 */
	public RenderableClassSelector(List<Clazz> subClasses) {
		super();
		this.subClasses = subClasses;
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
		return new RenderedSelectClass(path, subClasses);
	}

}
