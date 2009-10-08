package com.bluexml.xforms.generator.forms.renderable.common.association.selection.multiple;

import java.util.Stack;

import com.bluexml.xforms.generator.forms.Renderable;
import com.bluexml.xforms.generator.forms.Rendered;
import com.bluexml.xforms.generator.forms.XFormsGenerator;
import com.bluexml.xforms.generator.forms.modelelement.ModelElementBindHolder;
import com.bluexml.xforms.generator.forms.renderable.common.AssociationBean;
import com.bluexml.xforms.generator.forms.renderable.common.association.AbstractRenderable;
import com.bluexml.xforms.generator.forms.renderable.common.association.selection.RenderableSDisplay;
import com.bluexml.xforms.generator.forms.renderable.common.association.selection.RenderableSEdit;
import com.bluexml.xforms.generator.forms.rendered.RenderedRepeater;

/**
 * The Class RenderableSMultipleDisplay.
 */
public class RenderableSMultipleDisplay extends AbstractRenderable {

	/** The edit button. */
	private RenderableSEdit editButton;

	/**
	 * Instantiates a new renderable s multiple display.
	 * 
	 * @param associationBean
	 *            the association bean
	 */
	public RenderableSMultipleDisplay(AssociationBean associationBean) {
		super(associationBean);
		add(new RenderableSDisplay(associationBean));
		if (associationBean.isShowingActions()) {
			editButton = new RenderableSEdit(associationBean);
			add(editButton);
		}
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
		return ROOT_ABSOLUTE;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.bluexml.xforms.generator.forms.Renderable#render(java.lang.String, java.util.Stack,
	 * java.util.Stack)
	 */
	@Override
	public Rendered render(String path, Stack<Renderable> parents, Stack<Rendered> renderedParents) {
		String repeaterId = renderedParents.peek().getOptionalData();
		ModelElementBindHolder bindRepeater = (ModelElementBindHolder) XFormsGenerator.getBind(
				renderedParents.peek(), 1);
		RenderedRepeater renderedRepeater = new RenderedRepeater(bindRepeater, repeaterId);
		return renderedRepeater;
	}

}
