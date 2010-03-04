package com.bluexml.xforms.generator.forms.renderable.common.association.selection.selector;

import java.util.Stack;

import com.bluexml.xforms.messages.MsgId;
import com.bluexml.xforms.messages.MsgPool;
import org.jdom.Element;

import com.bluexml.xforms.generator.forms.Renderable;
import com.bluexml.xforms.generator.forms.Rendered;
import com.bluexml.xforms.generator.forms.XFormsGenerator;
import com.bluexml.xforms.generator.forms.renderable.common.AssociationBean;
import com.bluexml.xforms.generator.forms.rendered.RenderedXMLElement;

public class RenderableSelectorCount extends AbstractRenderableSelectorItem {

	public RenderableSelectorCount(AssociationBean associationBean,
			RenderableSelector renderableSelector) {
		super(associationBean, renderableSelector);
	}

	@Override
	public Path getPath(String parentPath, Stack<Renderable> parents,
			Stack<Rendered> renderedParents) {
		return ROOT_RELATIVE;
	}

	@Override
	public Rendered render(String path, Stack<Renderable> parents, Stack<Rendered> renderedParents, boolean isInIMultRepeater) {
		RenderedXMLElement rendered = new RenderedXMLElement();

		Element div = XFormsGenerator.createElement("div", XFormsGenerator.NAMESPACE_XHTML);
		div.setAttribute("class", MsgId.INT_CSS_SELECT_OUTPUT_ZONE.getText());

		Element outputReturned = XFormsGenerator.createElement("output",
				XFormsGenerator.NAMESPACE_XFORMS);
		outputReturned.setAttribute("ref", getInstancePath() + "query/returned");
		div.addContent(outputReturned);

		Element spanSlash = XFormsGenerator.createElement("span", XFormsGenerator.NAMESPACE_XHTML);
		spanSlash.setText("/");
		div.addContent(spanSlash);

		Element outputCount = XFormsGenerator.createElement("output",
				XFormsGenerator.NAMESPACE_XFORMS);
		outputCount.setAttribute("ref", getInstancePath() + "query/count");
		div.addContent(outputCount);

		Element trigger = XFormsGenerator
				.createElementWithLabel("trigger", XFormsGenerator.NAMESPACE_XFORMS, MsgPool
						.getMsg("selection.list.show.all.results"));
		trigger.setAttribute("appearance", "minimal");
		Element action = XFormsGenerator.createElement("action", XFormsGenerator.NAMESPACE_XFORMS);
		action.setAttribute("event", "DOMActivate", XFormsGenerator.NAMESPACE_EVENTS);
		Element setvalue = XFormsGenerator.createElement("setvalue",
				XFormsGenerator.NAMESPACE_XFORMS);
		setvalue.setAttribute("ref", getInstancePath() + "query/maxResults");
		setvalue.setText("0");
		action.addContent(setvalue);

		Element send = XFormsGenerator.createElement("send", XFormsGenerator.NAMESPACE_XFORMS);
		getModelElementUpdater().addLinkedElement(send);
		action.addContent(send);

		trigger.addContent(action);

		div.addContent(trigger);

		rendered.setXMLElement(div);
		return rendered;
	}

}
