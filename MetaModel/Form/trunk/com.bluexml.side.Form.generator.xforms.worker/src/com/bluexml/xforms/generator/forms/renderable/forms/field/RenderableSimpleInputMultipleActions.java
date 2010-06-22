package com.bluexml.xforms.generator.forms.renderable.forms.field;

import java.util.Stack;

import org.apache.commons.lang.StringUtils;
import org.jdom.Element;

import com.bluexml.xforms.generator.forms.Renderable;
import com.bluexml.xforms.generator.forms.Rendered;
import com.bluexml.xforms.generator.forms.XFormsGenerator;
import com.bluexml.xforms.generator.forms.modelelement.ModelElementBindSimple;
import com.bluexml.xforms.generator.forms.rendered.RenderedXMLElement;
import com.bluexml.xforms.messages.MsgId;

/**
 * The add/remove icons for the "standard text inputs with 'multiple' property" widget.
 * 
 * @author Amenel
 */
public class RenderableSimpleInputMultipleActions extends Renderable {

	private RenderableSimpleInputMultiple<?> parent;

	/**
	 * 
	 */
	public RenderableSimpleInputMultipleActions(RenderableSimpleInputMultiple<?> parent) {
		super();
		this.parent = parent;
	}

	@Override
	public Path getPath(String parentPath, Stack<Renderable> parents,
			Stack<Rendered> renderedParents) {
		String path = "";
		return new Path(PathType.absolutePath, path);
	}

	@Override
	public Rendered render(String path, Stack<Renderable> parents, Stack<Rendered> renderedParents,
			boolean isInIMultRepeater) {
		RenderedXMLElement rendered = new RenderedXMLElement();
		Element rootElt = XFormsGenerator.createElement("div", XFormsGenerator.NAMESPACE_XHTML);
		rootElt.setAttribute("class", "xformstdleft " + MsgId.INT_CSS_INPUT_MULTIPLE_TRIGGERS);

		String rootPath = getRootPath(renderedParents);

		// clear button
		rootElt.addContent(getTriggerClear());

		// add button
		rootElt.addContent(getTriggerAdd(path, rootPath));

		// remove button
		rootElt.addContent(getTriggerRemove(rootPath));

		rendered.setXMLElement(rootElt);
		return rendered;
	}

	/**
	 * Adds the button for clearing the input field
	 * 
	 * @return
	 */
	private Element getTriggerClear() {

		ModelElementBindSimple inputBind = parent.getInputBind();
		String inputNodeset = inputBind.getNodeset();

		Element trigger = XFormsGenerator.createTriggerWithLabelImage(XFormsGenerator.IMG_CLEAR);
		Element action = XFormsGenerator.createElement("action", XFormsGenerator.NAMESPACE_XFORMS);

		action.setAttribute("event", "DOMActivate", XFormsGenerator.NAMESPACE_EVENTS);

		Element setvalue = XFormsGenerator.createElement("setvalue",
				XFormsGenerator.NAMESPACE_XFORMS);
		setvalue.setAttribute("ref", inputNodeset);
		action.addContent(setvalue);

		trigger.addContent(action);
		return trigger;
	}

	/**
	 * Adds the button for adding the text currently in the input field to the list of values.
	 * 
	 * @param path
	 * @param rootPath
	 * @return
	 */
	private Element getTriggerAdd(String path, String rootPath) {

		ModelElementBindSimple inputBind = parent.getInputBind();
		ModelElementBindSimple storeBind = parent.getStoreBind();
		String inputNodeset = inputBind.getNodeset();
		String storeNodeset = storeBind.getNodeset();

		if ((StringUtils.trimToNull(rootPath) == null)
				&& (rootPath.startsWith("instance('minstance')/") == false)) {
			inputNodeset = "instance('minstance')/" + inputNodeset;
		}

		Element trigger = XFormsGenerator.createTriggerWithLabelImage(XFormsGenerator.IMG_ADD);
		Element action = XFormsGenerator.createElement("action", XFormsGenerator.NAMESPACE_XFORMS);

		// <xf:action ev:event="DOMActivate"
		// if="(instance('ComBluexmlDataRubriqueListInstance1')/SELECTEDID ne '') and not(parentchildOf[com.bluexml.data.Rubrique/BXDSID = instance('ComBluexmlDataRubriqueListInstance1')/SELECTEDID])">
		action.setAttribute("event", "DOMActivate", XFormsGenerator.NAMESPACE_EVENTS);
		String iftest = "(" + inputNodeset + " ne '')";
		action.setAttribute("if", iftest);

		Element insertAction = XFormsGenerator.createElement("insert",
				XFormsGenerator.NAMESPACE_XFORMS);
		insertAction.setAttribute("at", "last()");
		insertAction.setAttribute("position", "after");
		action.addContent(insertAction);
		storeBind.addLinkedElement(insertAction);

		Element setvalueId = XFormsGenerator.createElement("setvalue",
				XFormsGenerator.NAMESPACE_XFORMS);
		String pathItem = storeNodeset + "[last() - 1]" + "/" + MsgId.INT_INSTANCE_INPUT_MULT_VALUE;
		setvalueId.setAttribute("ref", pathItem);
		setvalueId.setAttribute("value", inputNodeset);
		action.addContent(setvalueId);

		trigger.addContent(action);
		return trigger;
	}

	/**
	 * Adds the button for removing the currently selected item from the list of values.
	 * 
	 * @param rootPath
	 * @return
	 */
	private Element getTriggerRemove(String rootPath) {
		Element trigger = XFormsGenerator.createTriggerWithLabelImage(XFormsGenerator.IMG_REMOVE);
		Element action = XFormsGenerator.createElement("action", XFormsGenerator.NAMESPACE_XFORMS);

		String repeaterId = parent.getRepeaterId();
		ModelElementBindSimple inputBind = parent.getInputBind();
		ModelElementBindSimple storeBind = parent.getStoreBind();
		String inputNodeset = inputBind.getNodeset();
		String storeNodeset = storeBind.getNodeset();

		if ((StringUtils.trimToNull(rootPath) == null)
				&& (rootPath.startsWith("instance('minstance')/") == false)) {
			storeNodeset = "instance('minstance')/" + storeNodeset;
		}

		action.setAttribute("event", "DOMActivate", XFormsGenerator.NAMESPACE_EVENTS);
		String iftest = "(" + storeNodeset + "[1]/value ne '')";
		action.setAttribute("if", iftest);

		// copy the currently selected value back to the input field
		Element copy = XFormsGenerator.createElement("setvalue", XFormsGenerator.NAMESPACE_XFORMS);
		copy.setAttribute("ref", inputNodeset);
		String valueStr = storeNodeset + "[index('" + repeaterId + "')]/value";
		copy.setAttribute("value", valueStr);
		action.addContent(copy);

		// delete the value from the list of values
		Element delete = XFormsGenerator.createElement("delete", XFormsGenerator.NAMESPACE_XFORMS);
		delete.setAttribute("at", "index('" + repeaterId + "')");
		action.addContent(delete);

		trigger.addContent(action);

		storeBind.addLinkedElement(delete);

		return trigger;
	}

}
