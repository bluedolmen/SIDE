package com.bluexml.xforms.generator.forms.renderable.lists;

import java.util.Stack;

import com.bluexml.xforms.generator.forms.Renderable;
import com.bluexml.xforms.generator.forms.Rendered;
import com.bluexml.xforms.generator.forms.modelelement.ModelElementSubmission;
import com.bluexml.xforms.generator.forms.renderable.common.RenderableSubmit;
import com.bluexml.xforms.generator.forms.rendered.RenderedParentGroup;
import com.bluexml.xforms.generator.tools.ModelTools;
import com.bluexml.xforms.messages.MsgId;

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
	public Rendered render(String path, Stack<Renderable> parents, Stack<Rendered> renderedParents,
			boolean isInIMultRepeater) {
		RenderedParentGroup renderedParentGroup = new RenderedParentGroup(renderedParents);
		// String action = MsgId.INT_URI_SCHEME_WRITER + "create//"
		// + ModelTools.getCompleteName(renderableClassList.getClasse());
		String action = MsgId.INT_URI_SCHEME_WRITER + "create?" + MsgId.INT_ACT_PARAM_ANY_ASSOC
				+ "=" + "&" + MsgId.INT_ACT_PARAM_ANY_DATATYPE + "="
				+ ModelTools.getCompleteName(renderableClassList.getClasse());
		submissionCreate.setAction(action);
		renderedParentGroup.getParent().addModelElement(submissionCreate);
		return renderedParentGroup;
	}

}
