package com.bluexml.xforms.generator.forms.renderable.lists;

import java.util.Stack;

import com.bluexml.xforms.messages.MsgId;
import org.jdom.Element;

import com.bluexml.xforms.generator.forms.Renderable;
import com.bluexml.xforms.generator.forms.Rendered;
import com.bluexml.xforms.generator.forms.XFormsGenerator;
import com.bluexml.xforms.generator.forms.modelelement.ModelElementSubmission;
import com.bluexml.xforms.generator.forms.rendered.RenderedInput;
import com.bluexml.xforms.generator.tools.ModelTools;

public class RenderableItemDisplayEdit extends Renderable {

	private RenderableClassList renderableClassList;

	public RenderableItemDisplayEdit(RenderableClassList renderableClassList) {
		super();
		this.renderableClassList = renderableClassList;
	}

	@Override
	public Path getPath(String parentPath, Stack<Renderable> parents,
			Stack<Rendered> renderedParents) {
		return ROOT_RELATIVE;
	}

	@Override
	public Rendered render(String path, Stack<Renderable> parents, Stack<Rendered> renderedParents) {
		RenderedInput rendered = new RenderedInput();

		ModelElementSubmission submission = new ModelElementSubmission("", "", true, false);
		submission.setAlwaysActive(false); // #1222
		submission.setAction(MsgId.INT_URI_SCHEME_WRITER + "edit//"
				+ ModelTools.getCompleteName(renderableClassList.getClasse()));

		Element trigger = XFormsGenerator.createElementWithLabel("trigger",
				XFormsGenerator.NAMESPACE_XFORMS, "Modifier");
		Element action = XFormsGenerator.createElement("action", XFormsGenerator.NAMESPACE_XFORMS);
		action.setAttribute("event", "DOMActivate", XFormsGenerator.NAMESPACE_EVENTS);

		Element setvalue = XFormsGenerator.createElement("setvalue",
				XFormsGenerator.NAMESPACE_XFORMS);
		setvalue.setAttribute("ref", "instance('minstance')/editedid");
		// String rootPath = getRootPath(renderedParents);
		setvalue.setAttribute("value", "instance('instanceList')/item[index('itemRepeater')]/id");
		action.addContent(setvalue);

		Element send = XFormsGenerator.createElement("send", XFormsGenerator.NAMESPACE_XFORMS);
		submission.addLinkedElement(send);
		action.addContent(send);

		trigger.addContent(action);
		rendered.setXformsElement(trigger);

		rendered.addModelElement(submission);

		return rendered;
	}

}
