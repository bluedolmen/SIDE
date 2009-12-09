package com.bluexml.xforms.generator.forms.renderable.forms.field;

import java.util.Stack;

import com.bluexml.xforms.messages.MsgId;

import com.bluexml.side.common.ModelElement;
import com.bluexml.side.form.ActionField;
import com.bluexml.side.form.FormElement;
import com.bluexml.xforms.generator.forms.Renderable;
import com.bluexml.xforms.generator.forms.Rendered;
import com.bluexml.xforms.generator.forms.XFormsGenerator;
import com.bluexml.xforms.generator.forms.modelelement.ModelElementSubmission;
import com.bluexml.xforms.generator.forms.renderable.common.RenderableSubmit;
import com.bluexml.xforms.generator.forms.rendered.RenderedParentGroup;

public class RenderableActionField extends Renderable {

	private ModelElementSubmission submission;
	private String label;

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
		// Operation theOp;
		// String infixe = (mel instanceof Transition) ? WORKFLOW_ACTION
		// : USER_ACTION;
		// if (mel instanceof Operation) {
		// theOp = (Operation) mel;
		// actionName = theOp.getName();
		// } else {
		// actionName = formElement.getId();
		// }
		// FIXME: differentiate user actions from workflow actions
		String infixe = MsgId.INT_ACT_CODE_WRKFLW_TRANSITION.getText();
		actionName = formElement.getId();
		label = formElement.getLabel();
		submission = new ModelElementSubmission(MsgId.INT_URI_SCHEME_WRITER + infixe + "/"
				+ actionName, label, true, false);
		RenderableSubmit button = new RenderableSubmit(submission, label, false);
		add(button);
	}

	@Override
	public Path getPath(String parentPath, Stack<Renderable> parents,
			Stack<Rendered> renderedParents) {

		return ROOT_RELATIVE;
	}

	@Override
	public Rendered render(String path, Stack<Renderable> parents, Stack<Rendered> renderedParents) {

		RenderedParentGroup renderedParentGroup = new RenderedParentGroup(renderedParents);
		renderedParentGroup.getParent().addModelElement(submission);
		return renderedParentGroup;
	}

}
