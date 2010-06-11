package com.bluexml.xforms.generator.forms.renderable.forms.group;

import java.util.Stack;


import com.bluexml.side.form.FormClass;
import com.bluexml.side.form.FormElement;
import com.bluexml.xforms.generator.forms.Renderable;
import com.bluexml.xforms.generator.forms.Rendered;
import com.bluexml.xforms.generator.forms.XFormsGenerator;

/**
 * The Class RenderableFormClass.
 */
public class RenderableFormClass extends RenderableGroup<FormClass> {

	/**
	 * Instantiates a new renderable form class.
	 * 
	 * @param generationManager
	 *            the generation manager
	 * @param parent
	 *            the parent
	 * @param form
	 *            the form
	 */
	public RenderableFormClass(XFormsGenerator generationManager, FormElement parent, FormClass form) {
		super(generationManager, parent, form);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.bluexml.xforms.generator.forms.renderable.forms.group.RenderableGroup#getPath(java.lang
	 * .String, java.util.Stack, java.util.Stack)
	 */
	@Override
	public Path getPath(String parentPath, Stack<Renderable> parents,
			Stack<Rendered> renderedParents) {
		return new Path(PathType.relativePath, formElement.getId() + "/");
	}

}
