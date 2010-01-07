package com.bluexml.xforms.generator.forms.renderable.common.association.inline;

import java.util.Stack;

import com.bluexml.xforms.generator.forms.Renderable;
import com.bluexml.xforms.generator.forms.Rendered;
import com.bluexml.xforms.generator.forms.modelelement.ModelElementBindHolder;
import com.bluexml.xforms.generator.forms.renderable.common.AssociationBean;
import com.bluexml.xforms.generator.forms.renderable.common.association.AbstractRenderable;
import com.bluexml.xforms.generator.forms.rendered.RenderedRepeater;

/**
 * The Class RenderableIMultipleRepeater.
 */
public class RenderableIMultipleRepeater extends AbstractRenderable {

	/**
	 * Instantiates a new renderable i multiple repeater.
	 * 
	 * @param bean
	 *            the bean
	 * @param associationClassBean
	 *            the association class bean
	 */
	public RenderableIMultipleRepeater(AssociationBean bean) {
		super(bean);
		add(new RenderableIUnique(bean, false, false));
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
		return ROOT_ABSOLUTE;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.bluexml.xforms.generator.forms.Renderable#render(java.lang.String, java.util.Stack,
	 * java.util.Stack)
	 */
	@Override
	public Rendered render(String path, Stack<Renderable> parents, Stack<Rendered> renderedParents, boolean isInIMultRepeater) {
		ModelElementBindHolder parentBind = ((RenderableIMultiple) parents.peek())
				.getBindRepeater();
		String repeaterId = renderedParents.peek().getOptionalData();
		RenderedRepeater renderedRepeater = new RenderedRepeater(parentBind, repeaterId);
		return renderedRepeater;
	}

}
