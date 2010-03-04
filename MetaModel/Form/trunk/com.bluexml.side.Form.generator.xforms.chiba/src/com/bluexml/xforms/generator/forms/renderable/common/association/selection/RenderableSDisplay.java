package com.bluexml.xforms.generator.forms.renderable.common.association.selection;

import java.util.Stack;

import org.jdom.Element;

import com.bluexml.xforms.generator.forms.Renderable;
import com.bluexml.xforms.generator.forms.Rendered;
import com.bluexml.xforms.generator.forms.XFormsGenerator;
import com.bluexml.xforms.generator.forms.modelelement.ModelElementBindHolder;
import com.bluexml.xforms.generator.forms.modelelement.ModelElementBindSimple;
import com.bluexml.xforms.generator.forms.renderable.common.AssociationBean;
import com.bluexml.xforms.generator.forms.renderable.common.association.AbstractRenderable;
import com.bluexml.xforms.generator.forms.renderable.common.association.selection.multiple.RenderableSMultiple;
import com.bluexml.xforms.generator.forms.renderable.common.association.selection.multiple.RenderableSMultipleDisplay;
import com.bluexml.xforms.generator.forms.renderable.common.association.selection.unique.RenderableSSingle;
import com.bluexml.xforms.generator.forms.rendered.RenderedInput;

/**
 * The Class RenderableSDisplay.
 */
public class RenderableSDisplay extends AbstractRenderable {

	/**
	 * Instantiates a new renderable s display.
	 * 
	 * @param associationBean
	 *            the association bean
	 */
	public RenderableSDisplay(AssociationBean associationBean) {
		super(associationBean);
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
		String path = "";
		return new Path(PathType.relativePath, path);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.bluexml.xforms.generator.forms.Renderable#render(java.lang.String, java.util.Stack,
	 * java.util.Stack)
	 */
	@Override
	public Rendered render(String path, Stack<Renderable> parents, Stack<Rendered> renderedParents, boolean isInIMultRepeater) {
		RenderedInput renderedInput = new RenderedInput();

		ModelElementBindSimple bindLabel = null;
		Renderable parent = parents.peek();

		if (parent instanceof RenderableSMultipleDisplay) {
			RenderableSMultiple grandpa = (RenderableSMultiple) parents.get(parents.size() - 2);
			ModelElementBindHolder bindRepeater = grandpa.getBindRepeater();
			bindLabel = bindRepeater.getSubBind(0);
		} else { // we're in a Nx1 widget
			bindLabel = ((RenderableSSingle) parent).getSelectedBindLabel();
		}

		Element output = XFormsGenerator.createElement("output", XFormsGenerator.NAMESPACE_XFORMS);
		bindLabel.addLinkedElement(output);
		renderedInput.setXformsElement(output);
		applyStyle(renderedInput, "side_select_selected_item");

		return renderedInput;
	}
}
