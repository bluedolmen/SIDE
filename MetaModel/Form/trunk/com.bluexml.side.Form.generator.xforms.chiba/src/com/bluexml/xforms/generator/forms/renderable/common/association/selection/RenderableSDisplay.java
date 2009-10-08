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
import com.bluexml.xforms.generator.forms.renderable.common.association.selection.multiple.RenderableSMultipleDisplay;
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
	public Rendered render(String path, Stack<Renderable> parents, Stack<Rendered> renderedParents) {
		RenderedInput renderedInput = new RenderedInput();

		ModelElementBindSimple bindLabel = null;
		Renderable parent = parents.peek();
		if (parent instanceof RenderableSMultipleDisplay) {
			Rendered grandpa = renderedParents.get(renderedParents.size() - 2);
			ModelElementBindHolder bindRepeater = (ModelElementBindHolder) XFormsGenerator.getBind(
					grandpa, 1);
			bindLabel = bindRepeater.getSubBind(0);
		} else {
			bindLabel = XFormsGenerator.getBind(renderedParents.peek(), 2);
		}

		Element output = XFormsGenerator.createElement("output", XFormsGenerator.NAMESPACE_XFORMS);
		bindLabel.addLinkedElement(output);
		renderedInput.setXformsElement(output);

		return renderedInput;
	}
}
