package com.bluexml.xforms.generator.forms.renderable.forms;

import java.util.Stack;

import com.bluexml.side.form.FormElement;
import com.bluexml.side.form.VirtualField;
import com.bluexml.xforms.generator.FormGeneratorsManager;
import com.bluexml.xforms.generator.forms.Renderable;
import com.bluexml.xforms.generator.forms.Rendered;
import com.bluexml.xforms.generator.forms.XFormsGenerator;
import com.bluexml.xforms.generator.forms.rendered.RenderedParentGroup;

/**
 * The Class RenderableVirtualField.
 */
public class RenderableVirtualField extends RenderableFormElement<VirtualField> {

	/**
	 * Instantiates a new renderable virtual field.
	 * 
	 * @param generationManager
	 *            the generation manager
	 * @param parent
	 *            the parent
	 * @param formElement
	 *            the form element
	 */
	public RenderableVirtualField(XFormsGenerator generationManager, FormElement parent,
			VirtualField formElement) {
		super(generationManager, parent, formElement);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.bluexml.xforms.generator.forms.renderable.forms.RenderableFormElement#compute()
	 */
	@Override
	public void compute() {
		addFormElement(formElement.getLink());
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
		return new Path(PathType.relativePath, FormGeneratorsManager.getUniqueName(formElement)
				+ "/");
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
		return new RenderedParentGroup(renderedParents);
	}

}
