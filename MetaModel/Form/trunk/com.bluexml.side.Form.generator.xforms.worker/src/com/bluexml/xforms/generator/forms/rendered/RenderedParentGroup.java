package com.bluexml.xforms.generator.forms.rendered;

import java.util.List;

import com.bluexml.xforms.generator.forms.Renderable;
import com.bluexml.xforms.generator.forms.Rendered;

/**
 * The Class RenderedParentGroup.
 */
public class RenderedParentGroup extends Rendered {

	/** The parent. */
	private Rendered parent = null;

	/**
	 * Instantiates a new rendered parent group.
	 * 
	 * @param renderedParents
	 *            the rendered parents
	 */
	public RenderedParentGroup(List<Rendered> renderedParents) {
		super();
		for (Rendered rendered : renderedParents) {
			if (rendered.isHolder()) {
				parent = rendered;
			}
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.bluexml.xforms.generator.forms.Rendered#addRendered(com.bluexml.xforms.generator.forms
	 * .Rendered, com.bluexml.xforms.generator.forms.Renderable)
	 */
	@Override
	public void addRendered(Rendered rendered, Renderable renderable) {
		parent.addRendered(rendered, renderable);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.bluexml.xforms.generator.forms.Rendered#getChildren()
	 */
	@Override
	public List<Rendered> getChildren() {
		return parent.getChildren();
	}

	/**
	 * Gets the parent.
	 * 
	 * @return the parent
	 */
	public Rendered getParent() {
		return parent;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.bluexml.xforms.generator.forms.Rendered#isHolder()
	 */
	@Override
	public boolean isHolder() {
		return false;
	}

}
