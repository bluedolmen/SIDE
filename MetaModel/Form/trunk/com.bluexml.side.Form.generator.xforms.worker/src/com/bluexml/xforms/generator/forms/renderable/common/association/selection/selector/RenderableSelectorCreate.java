package com.bluexml.xforms.generator.forms.renderable.common.association.selection.selector;

import java.util.Stack;

import com.bluexml.xforms.generator.forms.FormTypeRendered;
import com.bluexml.xforms.generator.forms.Renderable;
import com.bluexml.xforms.generator.forms.Rendered;
import com.bluexml.xforms.generator.forms.modelelement.ModelElementSubmission;
import com.bluexml.xforms.generator.forms.renderable.common.AssociationBean;
import com.bluexml.xforms.generator.forms.renderable.common.RenderableSubmit;
import com.bluexml.xforms.generator.forms.rendered.RenderedParentGroup;
import com.bluexml.xforms.generator.tools.ModelTools;
import com.bluexml.xforms.messages.MsgId;
import com.bluexml.xforms.messages.MsgPool;

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
		submissionCreate.setAlwaysActive(false); // #1222
		RenderableSubmit createButton = new RenderableSubmit(submissionCreate, MsgPool
				.getMsg(MsgId.CAPTION_BUTTON_CREATE), true);
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
	public Rendered render(String path, Stack<Renderable> parents, Stack<Rendered> renderedParents,
			boolean isInIMultRepeater) {
		RenderedParentGroup renderedParentGroup = new RenderedParentGroup(renderedParents);
		if (bean.getCreateEditFormType().equals(FormTypeRendered.formForm)) {
			// String listForms = "";
			String listForms = MsgId.INT_ACT_PARAM_ANY_DATATYPE + "="; // #1637
			if (bean.getCreateEditForms() != null) {
				listForms += bean.getCreateEditForms().get(0);
			} else {
				listForms += bean.getCreateEditDefaultFormName() + "&"
						+ MsgId.INT_ACT_PARAM_ANY_HINT + "=" + MsgId.INT_ACT_SUFFIX_GET_FORM_CLASS;
			}
			// String action = MsgId.INT_URI_SCHEME_WRITER.getText() +
			// MsgId.INT_ACT_CODE_CREATE_FORM
			// + "/" + bean.getName() + "/" + listForms;
			String action = MsgId.INT_URI_SCHEME_WRITER.getText() + MsgId.INT_ACT_CODE_CREATE_FORM
					+ "?" + MsgId.INT_ACT_PARAM_ANY_ASSOC + "=" + bean.getName() + "&" + listForms;
			submissionCreate.setAction(action);
		} else {
			// String action = MsgId.INT_URI_SCHEME_WRITER.getText()
			// + MsgId.INT_ACT_CODE_CREATE_CLASS + "/" + bean.getName() + "/"
			// + ModelTools.getCompleteName(bean.getDestinationClass());
			String action = MsgId.INT_URI_SCHEME_WRITER.getText() + MsgId.INT_ACT_CODE_CREATE_CLASS
					+ "?" + MsgId.INT_ACT_PARAM_ANY_ASSOC + "=" + bean.getName() + "&"
					+ MsgId.INT_ACT_PARAM_ANY_DATATYPE + "="
					+ ModelTools.getCompleteName(bean.getDestinationClass());
			submissionCreate.setAction(action);
		}
		renderedParentGroup.getParent().addModelElementRoot(submissionCreate);
		return renderedParentGroup;
	}
}
