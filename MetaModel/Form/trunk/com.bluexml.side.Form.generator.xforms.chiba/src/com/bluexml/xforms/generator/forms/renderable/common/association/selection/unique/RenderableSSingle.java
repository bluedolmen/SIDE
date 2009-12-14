package com.bluexml.xforms.generator.forms.renderable.common.association.selection.unique;

import java.util.Stack;

import org.jdom.Element;

import com.bluexml.xforms.messages.MsgId;

import com.bluexml.xforms.generator.forms.Renderable;
import com.bluexml.xforms.generator.forms.Rendered;
import com.bluexml.xforms.generator.forms.modelelement.ModelElementBindSimple;
import com.bluexml.xforms.generator.forms.renderable.common.AssociationBean;
import com.bluexml.xforms.generator.forms.renderable.common.association.AbstractRenderable;
import com.bluexml.xforms.generator.forms.renderable.common.association.inline.RenderableIUnique;
import com.bluexml.xforms.generator.forms.renderable.common.association.selection.RenderableSDisplay;
import com.bluexml.xforms.generator.forms.renderable.common.association.selection.RenderableSEdit;
import com.bluexml.xforms.generator.forms.renderable.common.association.selection.selector.RenderableSelector;
import com.bluexml.xforms.generator.forms.rendered.RenderedLine;

/**
 * The Class RenderableSSingle.
 */
public class RenderableSSingle extends AbstractRenderable {

	private ModelElementBindSimple selectorBindId; // #1156

	/**
	 * Instantiates a new renderable s single.
	 * 
	 * @param associationBean
	 *            the association bean
	 * @param selector
	 *            the selector
	 * @param associationClassBean
	 *            the association class bean
	 */
	public RenderableSSingle(AssociationBean associationBean, RenderableSelector selector,
			AssociationBean associationClassBean) {
		super(associationBean);

		add(selector);
		selectorBindId = selector.getBindId(); // #1156

		if (associationBean.isDisabled() == false) {
			add(new RenderableSSingleActions(associationBean, selector.getBindId(), selector
					.getBindLabel(), selector.getBindMandatory(), selector));
		}
		add(new RenderableSDisplay(associationBean));
		if (associationBean.isShowingActions()) {
			add(new RenderableSEdit(associationBean));
		}

		if (associationClassBean != null) {
			add(new RenderableIUnique(associationClassBean, true, true));
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
		Rendered rendered = new RenderedLine();
		Element div = rendered.getXformsElement();
		div.setAttribute("class", MsgId.INT_CSS_SELECT_WIDGET.getText());

		String targetPath = path;
//		if (StringUtils.trimToNull(targetPath) != null) {
//			System.out.println("found a non empty path");
//		}
		ModelElementBindSimple bindLabel = new ModelElementBindSimple("");
		ModelElementBindSimple bindId = new ModelElementBindSimple("");

		bindLabel.setNodeset(targetPath + MsgId.INT_INSTANCE_SIDELABEL);
		bindId.setNodeset(targetPath + MsgId.INT_INSTANCE_SIDEID);

		if (bean.isMandatory()) { // #978
			// no visual cues here, but useful for causing XForms validation to
			// fail if nothing is selected
			bindId.setRequired(true);
			bindLabel.setRequired(true);
			//** #1156
			String constraint = "instance('minstance')/" + bindId.getNodeset() + " ne ''";
			selectorBindId.setConstraint(constraint); 
			//** #1156
		}

		rendered.addModelElement(bindId);
		rendered.addModelElement(bindLabel);

		return rendered;
	}

}
