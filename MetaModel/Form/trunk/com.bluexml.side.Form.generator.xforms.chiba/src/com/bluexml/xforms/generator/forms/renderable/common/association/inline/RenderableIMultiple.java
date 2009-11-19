package com.bluexml.xforms.generator.forms.renderable.common.association.inline;

import java.util.Stack;

import com.bluexml.xforms.generator.forms.Renderable;
import com.bluexml.xforms.generator.forms.Rendered;
import com.bluexml.xforms.generator.forms.XFormsGenerator;
import com.bluexml.xforms.generator.forms.modelelement.ModelElementBindHolder;
import com.bluexml.xforms.generator.forms.modelelement.ModelElementBindSimple;
import com.bluexml.xforms.generator.forms.renderable.common.AssociationBean;
import com.bluexml.xforms.generator.forms.renderable.common.association.AbstractRenderable;
import com.bluexml.xforms.generator.forms.rendered.RenderedParentGroup;

/**
 * The Class RenderableIMultiple.
 */
public class RenderableIMultiple extends AbstractRenderable {

	/**
	 * Instantiates a new renderable i multiple.
	 * 
	 * @param bean
	 *            the bean
	 * @param associationClassBean
	 *            the association class bean
	 */
	public RenderableIMultiple(AssociationBean bean, AssociationBean associationClassBean) {
		super(bean);
		add(new RenderableIMultipleRepeater(bean, associationClassBean));
		add(new RenderableIMultipleTriggers(bean));
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

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.bluexml.xforms.generator.forms.Renderable#render(java.lang.String, java.util.Stack,
	 * java.util.Stack)
	 */
	@Override
	public Rendered render(String path, Stack<Renderable> parents, Stack<Rendered> renderedParents) {
		String nodeSetItems = computeNodeSetItems(path);
		String nodeSetActions = computeNodeSetActions(path);
		Rendered rendered = new RenderedParentGroup(renderedParents);
		rendered.setOptionalData(XFormsGenerator.getId(bean.getName() + "Repeater"));
		rendered.addModelElement(new ModelElementBindHolder(nodeSetItems));
		ModelElementBindSimple bindActions = new ModelElementBindSimple(nodeSetActions);
		bindActions.setRepeaterRootBind(true); // #1241
		rendered.addModelElement(bindActions);
		return rendered;
	}

}
