/*
    Copyright (C) 2007-20013  BlueXML - www.bluexml.com

    This program is free software: you can redistribute it and/or modify
    it under the terms of the GNU Affero General Public License as
    published by the Free Software Foundation, either version 3 of the
    License, or (at your option) any later version.

    This program is distributed in the hope that it will be useful,
    but WITHOUT ANY WARRANTY; without even the implied warranty of
    MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
    GNU Affero General Public License for more details.

    You should have received a copy of the GNU Affero General Public License
    along with this program.  If not, see <http://www.gnu.org/licenses/>.
*/
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
