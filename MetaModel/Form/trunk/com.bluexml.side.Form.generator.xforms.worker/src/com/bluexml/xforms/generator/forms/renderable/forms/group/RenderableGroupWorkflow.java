package com.bluexml.xforms.generator.forms.renderable.forms.group;

import java.util.Stack;


import com.bluexml.side.form.FormElement;
import com.bluexml.side.form.FormGroup;
import com.bluexml.xforms.generator.forms.Renderable;
import com.bluexml.xforms.generator.forms.Rendered;
import com.bluexml.xforms.generator.forms.XFormsGenerator;

public class RenderableGroupWorkflow<G extends FormGroup> extends RenderableGroup<G> {

	G group;

	public RenderableGroupWorkflow(XFormsGenerator generationManager, FormElement parent,
			G formElement) {
		super(generationManager, parent, formElement);
		this.group = formElement;
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
		return new Path(PathType.relativePath, group.getId() + "/");
	}
}
