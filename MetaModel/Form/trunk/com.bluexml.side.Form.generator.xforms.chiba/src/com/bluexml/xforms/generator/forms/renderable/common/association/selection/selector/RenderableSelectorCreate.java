package com.bluexml.xforms.generator.forms.renderable.common.association.selection.selector;

import java.util.Stack;

import com.bluexml.xforms.messages.MsgId;
import com.bluexml.xforms.messages.MsgPool;

import com.bluexml.xforms.generator.forms.Renderable;
import com.bluexml.xforms.generator.forms.Rendered;
import com.bluexml.xforms.generator.forms.modelelement.ModelElementSubmission;
import com.bluexml.xforms.generator.forms.renderable.common.AssociationBean;
import com.bluexml.xforms.generator.forms.renderable.common.RenderableSubmit;
import com.bluexml.xforms.generator.forms.rendered.RenderedParentGroup;
import com.bluexml.xforms.generator.tools.ModelTools;

/**
 * The Class RenderableSelectorCreate.
 */
public class RenderableSelectorCreate extends AbstractRenderableSelectorItem {

	/** The submission create. */
	private ModelElementSubmission submissionCreate;

	/**
	 * Instantiates a new renderable selector create.
	 * 
	 * @param associationBean
	 *            the association bean
	 * @param renderableAssociationSelectionSelector
	 *            the renderable association selection selector
	 */
	public RenderableSelectorCreate(AssociationBean associationBean,
			RenderableSelector renderableAssociationSelectionSelector) {
		super(associationBean, renderableAssociationSelectionSelector);
		submissionCreate = new ModelElementSubmission("", "", true, false);
		RenderableSubmit createButton = new RenderableSubmit(submissionCreate, MsgPool
				.getMsg(MsgId.CAPTION_BUTTON_CREATE));
		add(createButton);
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
		RenderedParentGroup renderedParentGroup = new RenderedParentGroup(renderedParents);
		if (bean.getCreateEditForm() != null) {
			submissionCreate.setAction(MsgId.INT_URL_SCHEME_WRITER + "createForm/" + bean.getName()
					+ "/" + bean.getCreateEditForm());
		} else {
			submissionCreate.setAction(MsgId.INT_URL_SCHEME_WRITER + "create/" + bean.getName()
					+ "/" + ModelTools.getCompleteName(bean.getDestinationClass()));
		}
		renderedParentGroup.getParent().addModelElement(submissionCreate);
		return renderedParentGroup;
	}
}
