/**
 * 
 */
package com.bluexml.xforms.generator.forms.renderable.forms;

import java.util.Stack;

import com.bluexml.side.form.BooleanSearchField;
import com.bluexml.side.form.CharSearchField;
import com.bluexml.side.form.ChoiceSearchField;
import com.bluexml.side.form.DateSearchField;
import com.bluexml.side.form.FileSearchField;
import com.bluexml.side.form.FormElement;
import com.bluexml.side.form.NumericalSearchField;
import com.bluexml.side.form.SearchField;
import com.bluexml.xforms.generator.forms.Renderable;
import com.bluexml.xforms.generator.forms.Rendered;
import com.bluexml.xforms.generator.forms.XFormsGenerator;
import com.bluexml.xforms.generator.forms.renderable.forms.search.RenderableBooleanSearchField;
import com.bluexml.xforms.generator.forms.renderable.forms.search.RenderableCharSearchField;
import com.bluexml.xforms.generator.forms.renderable.forms.search.RenderableChoiceSearchField;
import com.bluexml.xforms.generator.forms.renderable.forms.search.RenderableDateSearchField;
import com.bluexml.xforms.generator.forms.renderable.forms.search.RenderableFileSearchField;
import com.bluexml.xforms.generator.forms.renderable.forms.search.RenderableNumericalSearchField;
import com.bluexml.xforms.generator.forms.rendered.search.RenderedSearchField;

/**
 * @author Amenel
 * 
 */
public abstract class RenderableSearchField<F extends SearchField> extends
		RenderableFormElement<SearchField> {

	public static Renderable getRenderable(XFormsGenerator generationManager, FormElement parent,
			SearchField formElement) {
		Renderable renderable = null;
		if (formElement instanceof CharSearchField) {
			return new RenderableCharSearchField(generationManager, parent, formElement);
		} else if (formElement instanceof DateSearchField) {
			return new RenderableDateSearchField(generationManager, parent, formElement);
		} else if (formElement instanceof NumericalSearchField) {
			return new RenderableNumericalSearchField(generationManager, parent, formElement);
		} else if (formElement instanceof ChoiceSearchField) {
			return new RenderableChoiceSearchField(generationManager, parent, formElement);
		} else if (formElement instanceof FileSearchField) {
			return new RenderableFileSearchField(generationManager, parent, formElement);
		} else if (formElement instanceof BooleanSearchField) {
			return new RenderableBooleanSearchField(generationManager, parent, formElement);
		}
		return renderable;
	}

	public RenderableSearchField(XFormsGenerator generationManager, FormElement parent,
			SearchField formElement) {
		super(generationManager, parent, formElement);
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
		return new Path(PathType.relativePath, formElement.getId() + "/");
	}

	
	/* (non-Javadoc)
	 * @see com.bluexml.xforms.generator.forms.renderable.forms.RenderableFormElement#compute()
	 */
	@Override
	public void compute() {
		// nothing to do: search fields are not supposed to have children.
	}

	/* (non-Javadoc)
	 * @see com.bluexml.xforms.generator.forms.Renderable#render(java.lang.String, java.util.Stack, java.util.Stack, boolean)
	 */
	@Override
	public Rendered render(String path, Stack<Renderable> parents, Stack<Rendered> renderedParents,
			boolean isInIMultRepeater) {
		Rendered rendered = new RenderedSearchField();
		applyStyle(rendered);
		return rendered;
	}

	protected void applyStyle(Rendered rendered) {
		applyStyle(rendered, formElement.getStyle());
	}

}
