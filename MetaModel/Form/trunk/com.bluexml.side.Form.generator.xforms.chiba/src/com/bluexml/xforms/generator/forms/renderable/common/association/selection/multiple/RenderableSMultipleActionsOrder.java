package com.bluexml.xforms.generator.forms.renderable.common.association.selection.multiple;

import java.util.Stack;

import org.apache.commons.lang.StringUtils;
import org.jdom.Element;

import com.bluexml.xforms.generator.forms.Renderable;
import com.bluexml.xforms.generator.forms.Rendered;
import com.bluexml.xforms.generator.forms.XFormsGenerator;
import com.bluexml.xforms.generator.forms.modelelement.ModelElementBindSimple;
import com.bluexml.xforms.generator.forms.renderable.common.AssociationBean;
import com.bluexml.xforms.generator.forms.renderable.common.association.AbstractRenderable;
import com.bluexml.xforms.generator.forms.rendered.RenderedXMLElement;
import com.bluexml.xforms.messages.MsgId;

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
	public Rendered render(String path, Stack<Renderable> parents, Stack<Rendered> renderedParents,
			boolean isInIMultRepeater) {
		RenderedXMLElement rendered = new RenderedXMLElement();
		if ((getFormGenerator().isInReadOnlyMode() == false) || bean.isDisabled()) { // #1238
			repeaterId = renderedParents.peek().getOptionalData();
			Element xformsElement = XFormsGenerator.createElement("div",
					XFormsGenerator.NAMESPACE_XHTML);
			xformsElement.setAttribute("class", MsgId.INT_CSS_SELECT_TRIGGER_IMG.getText());

			ModelElementBindSimple bindActions = ((RenderableSMultiple) parents.peek())
					.getBindActions();
			String rootPath = isInIMultRepeater ? getRootPath(renderedParents) : null;
			xformsElement.addContent(getTriggerUp(bindActions, rootPath));
			xformsElement.addContent(XFormsGenerator.createElement("br",
					XFormsGenerator.NAMESPACE_XHTML));
			xformsElement.addContent(getTriggerDown(bindActions, rootPath));
			rendered.setXformsElement(xformsElement);
		}
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
		return getTriggerMove(bindActions, true, XFormsGenerator.IMG_UP, rootPath);
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
		return getTriggerMove(bindActions, false, XFormsGenerator.IMG_DOWN, rootPath);
	}

	/**
	 * Common function for the UP and DOWN features. Specifies the condition for triggering the
	 * action, the manipulations of the instance document and possibly, updates the index of the
	 * highlighted item.
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

		Element trigger = XFormsGenerator.createTriggerWithLabelImage(image);
		Element action = XFormsGenerator.createElement("action", XFormsGenerator.NAMESPACE_XFORMS);

		String realActionsNodeset = "instance('minstance')/" + StringUtils.trimToEmpty(rootPath)
				+ bindActions.getNodeset();
		String indexStr = "index('" + repeaterId + "')";
		String ifCondition = indexStr
				+ (moveUp ? " > 1" : (" < (count(" + realActionsNodeset + ") - 1)")); // #1157
		// ifCondition += "not(" + realActionsNodeset + "[" + notMovableIndex + "] is "
		// + realActionsNodeset + "[index('" + repeaterId + "')])";
		// ifCondition += "(" + indexStr + " != " + notMovableIndex + "))";

		action.setAttribute("if", ifCondition);
		action.setAttribute("event", "DOMActivate", XFormsGenerator.NAMESPACE_EVENTS);

		Element insert = XFormsGenerator.createElement("insert", XFormsGenerator.NAMESPACE_XFORMS);
		Element delete = XFormsGenerator.createElement("delete", XFormsGenerator.NAMESPACE_XFORMS);

		if (moveUp) {
			insert.setAttribute("at", indexStr + " - 1");
			insert.setAttribute("position", "before");
			delete.setAttribute("at", indexStr + " + 2");
		} else {
			insert.setAttribute("at", indexStr + " + 1");
			insert.setAttribute("position", "after");
			delete.setAttribute("at", indexStr + " - 2");
		}

		insert.setAttribute("origin", realActionsNodeset + "[" + indexStr + "]");
		bindActions.addLinkedElement(insert);
		bindActions.addLinkedElement(delete);

		action.addContent(insert);
		action.addContent(delete);

		if (!moveUp) {
			Element setindex = XFormsGenerator.createElement("setindex",
					XFormsGenerator.NAMESPACE_XFORMS);
			setindex.setAttribute("repeat", repeaterId);
			setindex.setAttribute("index", indexStr + " - 1");
			action.addContent(setindex);
		}
		trigger.addContent(action);
		return trigger;
	}

}
