package com.bluexml.xforms.generator.forms.renderable.common;

import java.util.List;
import java.util.Stack;

import com.bluexml.xforms.messages.MsgId;

import com.bluexml.xforms.generator.forms.Renderable;
import com.bluexml.xforms.generator.forms.Rendered;
import com.bluexml.xforms.generator.forms.modelelement.ModelElementSubmission;
import com.bluexml.xforms.generator.forms.rendered.RenderedGroup;

/**
 * The Class RenderableSubmits.
 */
public class RenderableSubmits extends Renderable {

	/**
	 * Instantiates a new renderable submits.
	 * 
	 * @param submissions
	 *            the submissions
	 */
	public RenderableSubmits(List<ModelElementSubmission> submissions) {
		super();
		for (ModelElementSubmission modelElementSubmission : submissions) {
			add(new RenderableSubmit(modelElementSubmission, modelElementSubmission.getCaption(),
					false));
		}
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
		return new RenderedGroup("", MsgId.INT_SUBMIT_BUTTONS_GROUP_ID.getText(), null);
	}

}
