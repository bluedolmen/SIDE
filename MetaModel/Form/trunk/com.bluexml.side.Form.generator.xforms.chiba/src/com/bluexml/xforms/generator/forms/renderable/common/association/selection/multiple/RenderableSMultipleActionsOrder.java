package com.bluexml.xforms.generator.forms.renderable.common.association.selection.multiple;

import java.util.Stack;

import org.jdom.Element;

import com.bluexml.xforms.generator.forms.Renderable;
import com.bluexml.xforms.generator.forms.Rendered;
import com.bluexml.xforms.generator.forms.XFormsGenerator;
import com.bluexml.xforms.generator.forms.modelelement.ModelElementBindSimple;
import com.bluexml.xforms.generator.forms.renderable.common.AssociationBean;
import com.bluexml.xforms.generator.forms.renderable.common.association.AbstractRenderable;
import com.bluexml.xforms.generator.forms.rendered.RenderedInput;

/**
 * The Class RenderableSMultipleActionsOrder.
 */
public class RenderableSMultipleActionsOrder extends AbstractRenderable {

	/** The repeater id. */
	private String repeaterId;

	/**
	 * Instantiates a new renderable s multiple actions order.
	 * 
	 * @param associationBean
	 *            the association bean
	 */
	public RenderableSMultipleActionsOrder(AssociationBean associationBean) {
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
		RenderedInput rendered = new RenderedInput();
		repeaterId = renderedParents.peek().getOptionalData();
		Element xformsElement = XFormsGenerator.createElement("div",
				XFormsGenerator.NAMESPACE_XHTML);
		ModelElementBindSimple bindActions = XFormsGenerator.getBind(renderedParents.peek(), 2);
		String rootPath = getRootPath(renderedParents);
		xformsElement.addContent(getTriggerUp(bindActions, rootPath));
		xformsElement.addContent(XFormsGenerator.createElement("br",
				XFormsGenerator.NAMESPACE_XHTML));
		xformsElement.addContent(getTriggerDown(bindActions, rootPath));
		rendered.setXformsElement(xformsElement);
		return rendered;
	}

	/**
	 * Gets the trigger up.
	 * 
	 * @param bindActions
	 *            the bind actions
	 * @param rootPath
	 *            the root path
	 * 
	 * @return the trigger up
	 */
	private Element getTriggerUp(ModelElementBindSimple bindActions, String rootPath) {
		return getTriggerMove(bindActions, true, "resources/images/up.gif", rootPath);
	}

	/**
	 * Gets the trigger down.
	 * 
	 * @param bindActions
	 *            the bind actions
	 * @param rootPath
	 *            the root path
	 * 
	 * @return the trigger down
	 */
	private Element getTriggerDown(ModelElementBindSimple bindActions, String rootPath) {
		return getTriggerMove(bindActions, false, "resources/images/down.gif", rootPath);
	}

	/**
	 * Gets the trigger move.
	 * 
	 * @param bindActions
	 *            the bind actions
	 * @param moveUp
	 *            the move up
	 * @param image
	 *            the image
	 * @param rootPath
	 *            the root path
	 * 
	 * @return the trigger move
	 */
	private Element getTriggerMove(ModelElementBindSimple bindActions, boolean moveUp,
			String image, String rootPath) {
		String delta = "- 1";
		String notMovableIndex = "1";
		if (!moveUp) {
			delta = "+ 1";
			notMovableIndex = "last() - 1";
		}

		Element trigger = XFormsGenerator.createTriggerWithLabelImage(image);
		Element action = XFormsGenerator.createElement("action", XFormsGenerator.NAMESPACE_XFORMS);

		String ifCondition = "";
		ifCondition += "(index('" + repeaterId + "') > 0) and "; // #1157
		ifCondition += "not(" + bindActions.getNodeset() + "[" + notMovableIndex + "] is "
				+ bindActions.getNodeset() + "[index('" + repeaterId + "')])";
		action.setAttribute("if", ifCondition);
		action.setAttribute("event", "DOMActivate", XFormsGenerator.NAMESPACE_EVENTS);

		Element insert = XFormsGenerator.createElement("insert", XFormsGenerator.NAMESPACE_XFORMS);
		Element delete = XFormsGenerator.createElement("delete", XFormsGenerator.NAMESPACE_XFORMS);

		if (moveUp) {
			insert.setAttribute("at", "index('" + repeaterId + "') - 1");
			insert.setAttribute("position", "before");
			delete.setAttribute("at", "index('" + repeaterId + "') + 2");
		} else {
			insert.setAttribute("at", "index('" + repeaterId + "') + 1");
			insert.setAttribute("position", "after");
			delete.setAttribute("at", "index('" + repeaterId + "') - 2");
		}

		insert.setAttribute("origin", "instance('minstance')/" + rootPath
				+ bindActions.getNodeset() + "[index('" + repeaterId + "')]");
		bindActions.addLinkedElement(insert);
		bindActions.addLinkedElement(delete);

		action.addContent(insert);
		action.addContent(delete);

		Element setindex = XFormsGenerator.createElement("setindex",
				XFormsGenerator.NAMESPACE_XFORMS);
		setindex.setAttribute("repeat", repeaterId);
		setindex.setAttribute("index", "index('" + repeaterId + "') " + delta);
		action.addContent(setindex);

		trigger.addContent(action);
		return trigger;
	}

}
