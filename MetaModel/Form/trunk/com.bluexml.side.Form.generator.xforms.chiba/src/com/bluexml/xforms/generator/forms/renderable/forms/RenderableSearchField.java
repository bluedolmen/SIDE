/**
 * 
 */
package com.bluexml.xforms.generator.forms.renderable.forms;

import java.util.Stack;

import com.bluexml.side.form.CharSearchField;
import com.bluexml.side.form.FormElement;
import com.bluexml.side.form.SearchField;
import com.bluexml.xforms.generator.forms.Renderable;
import com.bluexml.xforms.generator.forms.Rendered;
import com.bluexml.xforms.generator.forms.XFormsGenerator;
import com.bluexml.xforms.generator.forms.modelelement.ModelElementBindHolder;
import com.bluexml.xforms.generator.forms.renderable.forms.search.RenderableCharSearchField;

/**
 * @author Amenel
 * 
 */
public abstract class RenderableSearchField<F extends SearchField> extends
		RenderableFormElement<SearchField> {

	private ModelElementBindHolder bind;
	
	public static Renderable getRenderable(XFormsGenerator generationManager, FormElement parent,
			SearchField formElement) {
		Renderable renderable = null;
		if (formElement instanceof CharSearchField) {
			return new RenderableCharSearchField(generationManager, parent, formElement);
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
		return ROOT_RELATIVE;
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
		// TODO Auto-generated method stub
		return null;
	}

	/**
	 * @return the root bind
	 */
	public ModelElementBindHolder getBind() {
		if (bind == null) {
			bind = new ModelElementBindHolder("");
		}
		return bind;
	}

	/**
	 * @param bind 
	 */
	public void setRootBind(ModelElementBindHolder bind) {
		this.bind = bind;
	}

}
