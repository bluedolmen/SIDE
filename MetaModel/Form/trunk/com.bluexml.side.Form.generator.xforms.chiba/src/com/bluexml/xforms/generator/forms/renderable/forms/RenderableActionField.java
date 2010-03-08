package com.bluexml.xforms.generator.forms.renderable.forms;

import java.util.Stack;

import org.apache.commons.lang.StringUtils;

import com.bluexml.xforms.messages.MsgId;

import com.bluexml.side.common.ModelElement;
import com.bluexml.side.form.ActionField;
import com.bluexml.side.form.FormElement;
import com.bluexml.side.workflow.Transition;
import com.bluexml.xforms.generator.forms.Renderable;
import com.bluexml.xforms.generator.forms.Rendered;
import com.bluexml.xforms.generator.forms.XFormsGenerator;
import com.bluexml.xforms.generator.forms.modelelement.ModelElementSubmission;
import com.bluexml.xforms.generator.forms.renderable.common.RenderableSubmit;
import com.bluexml.xforms.generator.forms.rendered.RenderedParentGroup;

public class RenderableActionField extends Renderable {

	private ModelElementSubmission submission;
	private String label;

	private final boolean DEFAULT_VALIDATE_FIRST = true;

	public RenderableActionField(XFormsGenerator generationManager, FormElement parent,
			ActionField formElement) {

		super();
		/*
		 * Ds un wrkflw, une transition de name "Relire" et de label "Envoyer à la relecture" nous
		 * donne un id="transition/Relire". En dehors d'un workflow, l'id du bouton est "Relire".
		 * Dans tous les cas, "Envoyer à la relecture" est le texte affiché sur le bouton.
		 */
		ModelElement mel = formElement.getRef();
		mel = (ModelElement) generationManager.getFormGenerator().getRealObject(mel);
		String actionName;
		String infixe;

		if ((mel != null) && (mel instanceof Transition)) { // this is a workflow transition button
			actionName = formElement.getId();
			infixe = MsgId.INT_ACT_CODE_WRKFLW_TRANSITION.getText();
		} else { // this is a user-added action button
			actionName = StringUtils.trim(formElement.getAction_handler());
			infixe = MsgId.INT_ACT_CODE_EXECUTE.getText();
		}
		label = formElement.getLabel();
		submission = new ModelElementSubmission(MsgId.INT_URI_SCHEME_WRITER + infixe + "/"
				+ actionName, label, true, DEFAULT_VALIDATE_FIRST);
		RenderableSubmit button = new RenderableSubmit(submission, label, false);
		add(button);
	}

	@Override
	public Path getPath(String parentPath, Stack<Renderable> parents,
			Stack<Rendered> renderedParents) {

		return ROOT_RELATIVE;
	}

	@Override
	public Rendered render(String path, Stack<Renderable> parents, Stack<Rendered> renderedParents,
			boolean isInIMultRepeater) {

		RenderedParentGroup renderedParentGroup = new RenderedParentGroup(renderedParents);
		renderedParentGroup.getParent().addModelElement(submission);
		return renderedParentGroup;
	}

}
