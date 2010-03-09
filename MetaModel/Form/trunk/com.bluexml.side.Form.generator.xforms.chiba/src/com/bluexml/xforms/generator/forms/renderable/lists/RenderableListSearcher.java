package com.bluexml.xforms.generator.forms.renderable.lists;

import java.util.Stack;

import org.jdom.Element;

import com.bluexml.xforms.generator.forms.Renderable;
import com.bluexml.xforms.generator.forms.Rendered;
import com.bluexml.xforms.generator.forms.XFormsGenerator;
import com.bluexml.xforms.generator.forms.rendered.RenderedInput;
import com.bluexml.xforms.messages.MsgId;
import com.bluexml.xforms.messages.MsgPool;

public class RenderableListSearcher extends Renderable {

	private RenderableClassList renderableClassList;

	public RenderableListSearcher(RenderableClassList renderableClassList) {
		super();
		this.renderableClassList = renderableClassList;
	}

	@Override
	public Path getPath(String parentPath, Stack<Renderable> parents,
			Stack<Rendered> renderedParents) {
		return ROOT_RELATIVE;
	}

	@Override
	public Rendered render(String path, Stack<Renderable> parents, Stack<Rendered> renderedParents,
			boolean isInIMultRepeater) {
		RenderedInput rendered = new RenderedInput();

		Element input = XFormsGenerator.createElement("input", XFormsGenerator.NAMESPACE_XFORMS);
		input.setAttribute("id", XFormsGenerator.getId("searchInput"));
		input.setAttribute("incremental", "true");
		input.setAttribute("ref", "instance('instanceList')/query/query");
		
		Element label = XFormsGenerator.createElement("label", XFormsGenerator.NAMESPACE_XFORMS);
		label.setText(MsgPool.getMsg(MsgId.MSG_SELECT_LIST_SEARCH_LABEL));
		input.addContent(label);
		
		Element action = XFormsGenerator.createElement("action", XFormsGenerator.NAMESPACE_XFORMS);
		action.setAttribute("event", "xforms-value-changed", XFormsGenerator.NAMESPACE_EVENTS);
		Element send = XFormsGenerator.createElement("send", XFormsGenerator.NAMESPACE_XFORMS);
		renderableClassList.getModelElementListUpdater().addLinkedElement(send);
		action.addContent(send);
		input.addContent(action);

		rendered.setXformsElement(input);
		return rendered;
	}

}
