package com.bluexml.xforms.generator.forms.renderable.forms.group;

import java.util.Stack;


import com.bluexml.side.form.FormAspect;
import com.bluexml.side.form.FormElement;
import com.bluexml.xforms.generator.forms.Renderable;
import com.bluexml.xforms.generator.forms.Rendered;
import com.bluexml.xforms.generator.forms.XFormsGenerator;

/**
 * The Class RenderableAspect.<br>
 * Not used as aspect groups can be edited in modeler by adding non aspect<br>
 * Generation is then based on field references which point to real attribute
 */
@Deprecated
public class RenderableAspect extends RenderableGroup<FormAspect> {

	/**
	 * Instantiates a new renderable aspect.
	 * 
	 * @param generationManager
	 *            the generation manager
	 * @param parent
	 *            the parent
	 * @param aspect
	 *            the aspect
	 */
	public RenderableAspect(XFormsGenerator generationManager, FormElement parent, FormAspect aspect) {
		super(generationManager, parent, aspect);
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
