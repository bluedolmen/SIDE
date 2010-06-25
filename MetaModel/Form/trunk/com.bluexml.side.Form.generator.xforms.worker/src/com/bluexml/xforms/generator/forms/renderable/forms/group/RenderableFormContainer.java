package com.bluexml.xforms.generator.forms.renderable.forms.group;

import java.util.Stack;

import com.bluexml.side.form.FormContainer;
import com.bluexml.side.form.FormElement;
import com.bluexml.side.form.FormWorkflow;
import com.bluexml.xforms.generator.forms.Renderable;
import com.bluexml.xforms.generator.forms.Rendered;
import com.bluexml.xforms.generator.forms.XFormsGenerator;
import com.bluexml.xforms.messages.MsgId;

public class RenderableFormContainer extends RenderableGroup<FormContainer> {

	public RenderableFormContainer(XFormsGenerator generationManager, FormElement parent,
			FormContainer form) {
		super(generationManager, parent, form);

		setInWorkflowForm(form instanceof FormWorkflow);

		String style = MsgId.INT_CSS_FORM_TITLE.getText();
		if (form.getStyle() != null) { // #1600
			style = style + " " + form.getStyle();
		}
		setStyleClass(style);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.bluexml.xforms.generator.forms.renderable.forms.group.RenderableGroup
	 * #getPath(java.lang.String, java.util.Stack, java.util.Stack)
	 */
	@Override
	public Path getPath(String parentPath, Stack<Renderable> parents,
			Stack<Rendered> renderedParents) {
		return new Path(PathType.relativePath, formElement.getId() + "/");
	}

}
