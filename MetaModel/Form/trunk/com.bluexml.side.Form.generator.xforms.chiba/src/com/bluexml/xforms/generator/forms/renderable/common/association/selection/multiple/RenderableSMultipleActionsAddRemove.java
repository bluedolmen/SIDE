package com.bluexml.xforms.generator.forms.renderable.common.association.selection.multiple;

import java.util.Stack;

import com.bluexml.xforms.messages.MsgId;
import org.jdom.Element;

import com.bluexml.xforms.generator.forms.Renderable;
import com.bluexml.xforms.generator.forms.Rendered;
import com.bluexml.xforms.generator.forms.XFormsGenerator;
import com.bluexml.xforms.generator.forms.modelelement.ModelElementBindSimple;
import com.bluexml.xforms.generator.forms.renderable.common.AssociationBean;
import com.bluexml.xforms.generator.forms.renderable.common.association.AbstractRenderable;
import com.bluexml.xforms.generator.forms.rendered.RenderedInput;

/**
 * The Class RenderableSMultipleActionsAddRemove. Adds LEFT and RIGHT buttons in the N-N selection
 * widget.
 */
public class RenderableSMultipleActionsAddRemove extends AbstractRenderable {

	/** The selector bind id. */
	private ModelElementBindSimple selectorBindId;

	/** The selector bind label. */
	private ModelElementBindSimple selectorBindLabel;

	/** The selector bind for the number of selected items. */
	private ModelElementBindSimple selectorBindNb;

	/**
	 * Instantiates a new renderable s multiple actions add remove.
	 * 
	 * @param associationBean
	 *            the association bean
	 * @param selectorBindId
	 *            the selector bind id
	 * @param selectorBindLabel
	 *            the selector bind label
	 */
	public RenderableSMultipleActionsAddRemove(AssociationBean associationBean,
			ModelElementBindSimple selectorBindId, ModelElementBindSimple selectorBindLabel,
			ModelElementBindSimple selectorBindNb) {
		super(associationBean);
		this.selectorBindId = selectorBindId;
		this.selectorBindLabel = selectorBindLabel;
		this.selectorBindNb = selectorBindNb;
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
		return new Path(PathType.absolutePath, path);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.bluexml.xforms.generator.forms.Renderable#render(java.lang.String, java.util.Stack,
	 * java.util.Stack)
	 */
	@Override
	public Rendered render(String path, Stack<Renderable> parents, Stack<Rendered> renderedParents) {
		// FIXME root path
		ModelElementBindSimple bindActions = XFormsGenerator.getBind(renderedParents.peek(), 2);

		RenderedInput rendered = new RenderedInput();
		String repeaterId = renderedParents.peek().getOptionalData();
		Element xformsElement = XFormsGenerator.createElement("div",
				XFormsGenerator.NAMESPACE_XHTML);
		xformsElement.addContent(getTriggerAdd(bindActions, path));
		xformsElement.addContent(XFormsGenerator.createElement("br",
				XFormsGenerator.NAMESPACE_XHTML));
		xformsElement.addContent(getTriggerRemove(bindActions, repeaterId));
		rendered.setXformsElement(xformsElement);
		return rendered;
	}

	/**
	 * Gets the trigger remove.
	 * 
	 * @param bindActions
	 *            the bind actions
	 * @param repeaterId
	 *            the repeater id
	 * 
	 * @return the trigger remove
	 */
	private Element getTriggerRemove(ModelElementBindSimple bindActions, String repeaterId) {
		Element trigger = XFormsGenerator.createTriggerWithLabelImage("resources/images/left.gif");
		Element action = XFormsGenerator.createElement("action", XFormsGenerator.NAMESPACE_XFORMS);
		Element deleteAction = XFormsGenerator.createElement("delete",
				XFormsGenerator.NAMESPACE_XFORMS);
		deleteAction.setAttribute("at", "index('" + repeaterId + "')");
		deleteAction.setAttribute("event", "DOMActivate");
		bindActions.addLinkedElement(deleteAction);
		action.addContent(deleteAction);

		if (bean.isMandatory()) {
			Element setvalueNb = XFormsGenerator.createElement("setvalue",
					XFormsGenerator.NAMESPACE_XFORMS);
			setvalueNb.setAttribute("ref", selectorBindNb.getNodeset());
			setvalueNb.setAttribute("value", ". - 1");
			action.addContent(setvalueNb);
		}

		trigger.addContent(action);
		return trigger;
	}

	/**
	 * Gets the trigger add.
	 * 
	 * @param bindActions
	 *            the bind actions
	 * @param path
	 *            the path
	 * 
	 * @return the trigger add
	 */
	private Element getTriggerAdd(ModelElementBindSimple bindActions, String path) {

		Element trigger = XFormsGenerator.createTriggerWithLabelImage("resources/images/right.gif");
		Element action = XFormsGenerator.createElement("action", XFormsGenerator.NAMESPACE_XFORMS);
		action.setAttribute("event", "DOMActivate", XFormsGenerator.NAMESPACE_EVENTS);
		// <xf:action ev:event="DOMActivate"
		// if="(instance('ComBluexmlDataRubriqueListInstance1')/SELECTEDID ne '') and not(parentchildOf[com.bluexml.data.Rubrique/BXDSID = instance('ComBluexmlDataRubriqueListInstance1')/SELECTEDID])">
		String iftest = "(" + selectorBindId.getNodeset() + " ne '') " + "and not("
				+ bindActions.getNodeset() + "[" + path + MsgId.INT_INSTANCE_SIDEID + " = "
				+ selectorBindId.getNodeset() + "])";
		if (bean.getHiBound() > 1) {
			String maxcount = Integer.toString(bean.getHiBound() + 1);
			iftest = iftest + " and count(instance('minstance')/" + bindActions.getNodeset()
					+ ") < " + maxcount;
		}
		action.setAttribute("if", iftest);

		Element insertAction = XFormsGenerator.createElement("insert",
				XFormsGenerator.NAMESPACE_XFORMS);
		bindActions.addLinkedElement(insertAction);
		insertAction.setAttribute("at", "last()");
		insertAction.setAttribute("position", "after");
		action.addContent(insertAction);

		Element setvalueId = XFormsGenerator.createElement("setvalue",
				XFormsGenerator.NAMESPACE_XFORMS);
		setvalueId.setAttribute("ref", bindActions.getNodeset() + "[last() - 1]/" + path
				+ MsgId.INT_INSTANCE_SIDEID);
		setvalueId.setAttribute("value", selectorBindId.getNodeset());
		action.addContent(setvalueId);

		Element setvalueLabel = XFormsGenerator.createElement("setvalue",
				XFormsGenerator.NAMESPACE_XFORMS);
		setvalueLabel.setAttribute("ref", bindActions.getNodeset() + "[last() - 1]/" + path
				+ MsgId.INT_INSTANCE_SIDELABEL);
		setvalueLabel.setAttribute("value", selectorBindLabel.getNodeset());
		action.addContent(setvalueLabel);

		if (bean.isMandatory()) {
			Element setvalueNb = XFormsGenerator.createElement("setvalue",
					XFormsGenerator.NAMESPACE_XFORMS);
			setvalueNb.setAttribute("ref", selectorBindNb.getNodeset());
			setvalueNb.setAttribute("value", ". + 1");
			action.addContent(setvalueNb);
		}

		trigger.addContent(action);
		return trigger;
	}

}
