package com.bluexml.xforms.generator.forms.renderable.lists;

import java.util.Stack;

import com.bluexml.xforms.messages.MsgId;

import com.bluexml.xforms.generator.forms.Renderable;
import com.bluexml.xforms.generator.forms.Rendered;
import com.bluexml.xforms.generator.forms.modelelement.ModelElementSubmission;
import com.bluexml.xforms.generator.forms.renderable.common.RenderableSubmit;
import com.bluexml.xforms.generator.forms.rendered.RenderedParentGroup;
import com.bluexml.xforms.generator.tools.ModelTools;

public class RenderableListCreate extends Renderable {

	/** The submission create. */
	private ModelElementSubmission submissionCreate;
	private RenderableClassList renderableClassList;

	public RenderableListCreate(RenderableClassList renderableClassList) {
		super();
		this.renderableClassList = renderableClassList;
		submissionCreate = new ModelElementSubmission("", "", true, false);
		RenderableSubmit createButton = new RenderableSubmit(submissionCreate, "Créer", true);
		add(createButton);
	}

	@Override
	public Path getPath(String parentPath, Stack<Renderable> parents,
			Stack<Rendered> renderedParents) {
		return ROOT_RELATIVE;
	}

	@Override
	public Rendered render(String path, Stack<Renderable> parents, Stack<Rendered> renderedParents) {
		RenderedParentGroup renderedParentGroup = new RenderedParentGroup(renderedParents);
		submissionCreate.setAction(MsgId.INT_URI_SCHEME_WRITER + "create//"
				+ ModelTools.getCompleteName(renderableClassList.getClasse()));
		renderedParentGroup.getParent().addModelElement(submissionCreate);
		return renderedParentGroup;
	}

}
