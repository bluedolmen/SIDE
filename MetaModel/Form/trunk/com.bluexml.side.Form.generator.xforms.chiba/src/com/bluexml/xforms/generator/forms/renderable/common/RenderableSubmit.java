package com.bluexml.xforms.generator.forms.renderable.common;

import java.util.Stack;

import org.jdom.Element;

import com.bluexml.xforms.generator.forms.Renderable;
import com.bluexml.xforms.generator.forms.Rendered;
import com.bluexml.xforms.generator.forms.XFormsGenerator;
import com.bluexml.xforms.generator.forms.modelelement.ModelElementSubmission;
import com.bluexml.xforms.generator.forms.rendered.RenderedInput;

/**
 * The Class RenderableSubmit.
 */
public class RenderableSubmit extends Renderable {

	/** The label. */
	private String label;

	/** The submission. */
	private ModelElementSubmission submission;

	private boolean hideIfReadOnlyMode; // #1238

	/**
	 * Instantiates a new renderable submit.
	 * 
	 * @param submission
	 *            the submission
	 * @param label
	 *            the label
	 */
	public RenderableSubmit(ModelElementSubmission submission, String label,
			boolean hideIfReadOnlyMode) {
		super();
		this.submission = submission;
		this.label = label;
		this.hideIfReadOnlyMode = hideIfReadOnlyMode;
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
		RenderedInput rendered = new RenderedInput();

		if (hideIfReadOnlyMode && getFormGenerator().isInReadOnlyMode()) {
			return rendered;
		}
		Element submit = XFormsGenerator.createElementWithLabel("submit",
				XFormsGenerator.NAMESPACE_XFORMS, label);
		submission.addLinkedElement(submit);
		rendered.setXformsElement(submit);

		return rendered;
	}

}
