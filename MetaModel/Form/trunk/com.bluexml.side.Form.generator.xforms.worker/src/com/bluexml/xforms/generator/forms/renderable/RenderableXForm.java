package com.bluexml.xforms.generator.forms.renderable;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

import com.bluexml.xforms.generator.forms.FormSubmissionActions;
import com.bluexml.xforms.generator.forms.FormTypeRendered;
import com.bluexml.xforms.generator.forms.Renderable;
import com.bluexml.xforms.generator.forms.Rendered;
import com.bluexml.xforms.generator.forms.modelelement.ModelElementSubmission;
import com.bluexml.xforms.generator.forms.renderable.common.RenderableSubmits;
import com.bluexml.xforms.generator.forms.rendered.RenderedForm;
import com.bluexml.xforms.messages.MsgId;
import com.bluexml.xforms.messages.MsgPool;

/**
 * The Class RenderableXForm. Adds generic elements to generated forms. The elements are submit
 * buttons and the relevant actions in the model. However, these buttons and actions are not
 * relevant for workflow forms, which are to be incorporated into FormForm's.
 */
public class RenderableXForm extends Renderable {

	/** The submissions. */
	private List<ModelElementSubmission> submissions;

	/** The title. */
	private String title;

	private FormTypeRendered formType;

	/**
	 * Instantiates a new renderable x form.
	 * 
	 * @param classeBean
	 *            the classe bean
	 * @param title
	 *            the title
	 * @param classActions
	 *            the class actions
	 * @param formType
	 *            the form type
	 */
	public RenderableXForm(Renderable renderable, String title,
			List<FormSubmissionActions> classActions, FormTypeRendered formType) {
		super();
		this.title = title;
		this.formType = formType;
		submissions = new ArrayList<ModelElementSubmission>();
		for (FormSubmissionActions anAction : classActions) {
			// String action = MsgId.INT_URI_SCHEME_WRITER.getText() + anAction.getName() + "/";
			String action = MsgId.INT_URI_SCHEME_WRITER.getText() + anAction.getName(); // #1637
			String caption = MsgPool.getMsg(anAction.getCaption());
			ModelElementSubmission submission = new ModelElementSubmission(action, caption,
					anAction.isReplaceAll(), anAction.isValidateFirst());
			submissions.add(submission);
		}
		add(renderable);
		RenderableSubmits renderableSubmits = new RenderableSubmits(submissions);
		add(renderableSubmits);
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
		RenderedForm renderedForm = new RenderedForm(title, formType);
		for (ModelElementSubmission modelElementSubmission : submissions) {
			renderedForm.addModelElement(modelElementSubmission);
		}
		return renderedForm;
	}

}
