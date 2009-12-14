package com.bluexml.xforms.generator.forms.renderable.common.association.selection.multiple;

import java.util.Stack;

import org.apache.commons.lang.StringUtils;
import org.jdom.Element;

import com.bluexml.xforms.messages.MsgId;

import com.bluexml.xforms.generator.forms.Renderable;
import com.bluexml.xforms.generator.forms.Rendered;
import com.bluexml.xforms.generator.forms.XFormsGenerator;
import com.bluexml.xforms.generator.forms.modelelement.ModelElementBindHolder;
import com.bluexml.xforms.generator.forms.modelelement.ModelElementBindSimple;
import com.bluexml.xforms.generator.forms.renderable.common.AssociationBean;
import com.bluexml.xforms.generator.forms.renderable.common.association.AbstractRenderable;
import com.bluexml.xforms.generator.forms.renderable.common.association.selection.selector.RenderableSelector;
import com.bluexml.xforms.generator.forms.rendered.RenderedLine;

/**
 * The Class RenderableSMultiple.
 */
public class RenderableSMultiple extends AbstractRenderable {

	/** The renderable association selection multiple actions add remove. */
	private RenderableSMultipleActionsAddRemove renderableAssoSelMultActionsAddRemove;

	/** The renderable association selection multiple display. */
	private RenderableSMultipleDisplay renderableAssoSelMultDisplay;

	private ModelElementBindSimple selectorBindId; // #1156

	/**
	 * Instantiates a new renderable s multiple.
	 * 
	 * @param associationBean
	 *            the association bean
	 * @param selector
	 *            the selector
	 * @param associationClassBean
	 *            the association class bean
	 */
	public RenderableSMultiple(AssociationBean associationBean, RenderableSelector selector,
			AssociationBean associationClassBean) {
		super(associationBean);

		add(selector);
		if (associationBean.isDisabled() == false) {
			renderableAssoSelMultActionsAddRemove = new RenderableSMultipleActionsAddRemove(
					associationBean, selector);
			add(renderableAssoSelMultActionsAddRemove);
		}
		renderableAssoSelMultDisplay = new RenderableSMultipleDisplay(associationBean);
		add(renderableAssoSelMultDisplay);
		if (associationBean.isDisabled() == false) {
			add(new RenderableSMultipleActionsOrder(associationBean));
		}

		if (associationClassBean != null) {
			add(new RenderableSMultipleAssociationClass(associationClassBean));
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
		ModelElementBindHolder bindRepeater = new ModelElementBindHolder("");
		ModelElementBindSimple bindActions = new ModelElementBindSimple("");

		String dataPath = "";
		ModelElementBindSimple bindLabel = new ModelElementBindSimple(dataPath
				+ MsgId.INT_INSTANCE_SIDELABEL);
		ModelElementBindSimple bindId = new ModelElementBindSimple(dataPath
				+ MsgId.INT_INSTANCE_SIDEID);

		bindRepeater.addSubBind(bindLabel);
		bindRepeater.addSubBind(bindId);

		String nodeSetItems = computeNodeSetItems(path);
		String nodeSetActions = computeNodeSetActions(path);
		Rendered rendered = new RenderedLine();
		Element div = rendered.getXformsElement();
		div.setAttribute("class", MsgId.INT_CSS_SELECT_WIDGET.getText());
		rendered.setOptionalData(XFormsGenerator.getId(bean.getName() + "Repeater"));
		bindRepeater.setNodeset(nodeSetItems);
		rendered.addModelElement(bindRepeater);
		bindActions.setNodeset(nodeSetActions);
		rendered.addModelElement(bindActions);
		bindActions.setRepeaterRootBind(true); // #1241

		if (getFormGenerator().isInReadOnlyMode() == false) {
			if (bean.isMandatory()) { // #978
				bindId.setRequired(true);
				bindLabel.setRequired(true);

				int pos = path.length() - 1;
				String pathShortened = path.charAt(pos) == '/' ? path.substring(0, pos) : path;
				String constraint = "instance('minstance')/" + pathShortened + "[1]/"
						+ bindId.getNodeset() + " ne ''";
				if (StringUtils.contains(selectorBindId.getConstraint(), constraint) == false) {
					selectorBindId.setConstraint(constraint);
				}
			}
		}
		
		return rendered;
	}

}
